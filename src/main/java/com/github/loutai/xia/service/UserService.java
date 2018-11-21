package com.github.loutai.xia.service;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.common.ServiceResult;
import com.github.loutai.xia.entity.XiaPermission;
import com.github.loutai.xia.entity.XiaUser;
import com.github.loutai.xia.repository.XiaPermissionRepository;
import com.github.loutai.xia.repository.XiaUserRepository;
import com.github.loutai.xia.security.SecurityContextUtil;
import lombok.val;
import org.springframework.data.domain.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    private SecurityContextUtil securityContextUtil;

    @Resource
    private XiaUserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private XiaPermissionRepository permissionRepository;

    public Page<XiaUser> queryPage(int page, int rows, String username) {
        Pageable pageable = PageRequest.of(page - 1, rows, new Sort(Sort.DEFAULT_DIRECTION, "id"));

        XiaUser entity = new XiaUser();

        entity.setDeleted(Constant.DELETE_N);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id")
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withMatcher("deleted", ExampleMatcher.GenericPropertyMatchers.exact());

        if (!StringUtils.isEmpty(username)) {
            entity.setUsername(username);
        }

        Example<XiaUser> example = Example.of(entity, exampleMatcher);
        return userRepository.findAll(example, pageable);
    }


    public ServiceResult add(XiaUser entity) {
        XiaUser _entity = userRepository.findByAccountAndDeleted(entity.getAccount(), Constant.DELETE_N);
        if (_entity != null) {
            return ServiceResult.ofError("账号已存在！");
        } else {
            entity.setDeleted(Constant.DELETE_N);
            entity.setCreateDate(new Date());
            entity.setCreateBy(securityContextUtil.getCurrentUser().getId());
            entity.setPassword(passwordEncoder.encode(Constant.DEFAULT_PASSWORD));
            userRepository.saveAndFlush(entity);
            return ServiceResult.ofSuccess();
        }
    }

    public ServiceResult update(XiaUser entity) {
        XiaUser _entity = userRepository.getOne(entity.getId());
        _entity.setStatus(entity.getStatus());
        _entity.setUsername(entity.getUsername());
        _entity.setUpdateDate(new Date());
        _entity.setUpdateBy(securityContextUtil.getCurrentUser().getId());
        userRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    public ServiceResult delete(Integer id) {
        XiaUser _entity = userRepository.getOne(id);
        _entity.setDeleted(Constant.DELETE_Y);
        userRepository.saveAndFlush(_entity);
        return ServiceResult.ofSuccess();
    }

    @Transactional
    public ServiceResult modifyUser(XiaUser user, String newPassword) {
        val _userDetail = securityContextUtil.getCurrentUserDetail();
        val _user = userRepository.findById(_userDetail.getUserId()).get();
        if (!StringUtils.isEmpty(newPassword)) {
            String oldPassword = user.getPassword();
            if (StringUtils.isEmpty(oldPassword) || !passwordEncoder.matches(oldPassword, _user.getPassword())) {
                return ServiceResult.ofError("密码错误");
            }
            _user.setPassword(passwordEncoder.encode(newPassword));
        }
        _user.setUsername(user.getUsername());
        _user.setUpdateDate(new Date());
        _user.setUpdateBy(_user.getId());
        userRepository.saveAndFlush(_user);
        return ServiceResult.ofSuccess();
    }

    public List<Map<String, Object>> dic() {
        return userRepository.dic();
    }

    public ServiceResult listALl() {
        List<XiaUser> users = userRepository.findByDeleted(Constant.DELETE_N);
        users.forEach(user -> user.setPassword(null));
        return ServiceResult.ofSuccess(users);
    }

    public List<SimpleGrantedAuthority> getUserAuthority(Integer userId) {
        List<XiaPermission> permissions = permissionRepository.findUserPermission(userId);
        return permissions.parallelStream()
                .map(permission -> new SimpleGrantedAuthority(permission.getId().toString()))
                .collect(Collectors.toList());
    }
}
