package com.github.loutai.xia.security;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.entity.XiaConfig;
import com.github.loutai.xia.entity.XiaPermission;
import com.github.loutai.xia.repository.XiaPermissionRepository;
import com.github.loutai.xia.service.ConfigService;
import com.google.common.collect.Lists;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class CustomInvocationSecurityMetadataSourceService implements
        FilterInvocationSecurityMetadataSource {

    @Resource
    private XiaPermissionRepository permissionRepository;

    @Resource
    private ConfigService configService;

    private HashMap<String, Collection<ConfigAttribute>> mappedPermissions =null;
    private Set<String> freeAccessUrls = null;

    public void loadSystemPermissions(){
        mappedPermissions = new HashMap<>();
        List<XiaPermission> permissions = permissionRepository.findAll();
        for(XiaPermission permission : permissions) {
            if (!StringUtils.isEmpty(permission.getResource())) {
                mappedPermissions.put(permission.getResource(),
                        Lists.newArrayList(new SecurityConfig(permission.getId().toString()))); // url -> [permissionId]
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (freeAccessUrls == null ) {
            loadFreeAccessUrl();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        for (String freeAccessUrl : freeAccessUrls) {
            matcher = new AntPathRequestMatcher(freeAccessUrl);
            if (matcher.matches(request)) {
                return null;
            }
        }
        if(mappedPermissions ==null) {
            loadSystemPermissions();
        }
        for (String url : mappedPermissions.keySet()) {
            matcher = new AntPathRequestMatcher(url);
            if(matcher.matches(request)) {
                return mappedPermissions.get(url);
            }
        }
        return null;
    }

    public void loadFreeAccessUrl() {
        XiaConfig config = configService.findByAppIdAndCode(Constant.APP_ID_XIA, Constant.CONFIG_CODE_FREE_ACCESS_URL);
        String[] urls = config.getValue().split(",");
        freeAccessUrls = new HashSet<>(Lists.newArrayList(urls));
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
