package com.github.loutai.xia.security;

import com.github.loutai.xia.entity.XiaUser;
import com.github.loutai.xia.repository.XiaUserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SecurityContextUtil {

    @Resource
    private XiaUserRepository userRepository;

    private static UserDetails getCurrentUserDetails() {
        return (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

    public XiaUser getCurrentUser() {
        CustomUserDetails userDetails = (CustomUserDetails) getCurrentUserDetails();
        return userRepository.findById(userDetails.getUserId()).get();
    }

    public CustomUserDetails getCurrentUserDetail() {
        UserDetails userDetails = getCurrentUserDetails();
        return (CustomUserDetails) userDetails;
    }
}
