package com.github.loutai.xia.service;

import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaApplication;
import com.github.loutai.xia.entity.XiaMenu;
import com.github.loutai.xia.entity.XiaUser;
import com.github.loutai.xia.repository.XiaApplicationRepository;
import com.github.loutai.xia.repository.XiaMenuRepository;
import com.github.loutai.xia.security.SecurityContextUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class IndexService {

    @Resource
    private XiaMenuRepository menuRepository;

    @Resource
    private XiaApplicationRepository applicationRepository;

    @Resource
    private SecurityContextUtil securityContextUtil;

    public ServiceResult getCurrentUserApplications() {
        XiaUser user = securityContextUtil.getCurrentUser();
        List<XiaApplication> applications = applicationRepository.getByUserId(user.getId());
        return ServiceResult.ofSuccess(applications);
    }

    public ServiceResult getCurrentUserMenus(Integer appId) {
        XiaUser user = securityContextUtil.getCurrentUser();
        List<XiaMenu> menus = menuRepository.getUserMenuByAppId(user.getId(), appId);
        List<XiaMenu> lost = new ArrayList<>();
        menus.forEach(menu -> fetchParent(menu, menus, lost));
        menus.addAll(lost);
        menus.sort(Comparator.comparingInt(XiaMenu::getSorts));
        return ServiceResult.ofSuccess(menus);
    }

    private void fetchParent(XiaMenu menu, List<XiaMenu> menus, List<XiaMenu> lost) {
        Integer parentId = menu.getParentId();
        if (parentId != null) {
            boolean isFetched = false;
            for (XiaMenu _menu : menus) {
                if (parentId.equals(_menu.getId())) {
                    isFetched = true;
                }
            }
            if (!isFetched) {
                for (XiaMenu _menu : lost) {
                    if (parentId.equals(_menu.getId())) {
                        isFetched = true;
                    }
                }
            }
            if (!isFetched) {
                XiaMenu parent = menuRepository.findById(parentId).get();
                lost.add(parent);
                if (parent.getParentId() != null) {
                    fetchParent(parent, menus, lost);
                }
            }
        }
    }

    @Transactional
    public ServiceResult switchDefaultApp(Integer appId) {
        Integer userId = securityContextUtil.getCurrentUserDetail().getUserId();
        applicationRepository.removeDefault(userId);
        applicationRepository.setDefault(userId, appId);
        return ServiceResult.ofSuccess();
    }
}
