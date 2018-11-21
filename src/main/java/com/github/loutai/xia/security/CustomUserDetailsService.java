package com.github.loutai.xia.security;

import com.github.loutai.xia.common.Constant;
import com.github.loutai.xia.entity.XiaUser;
import com.github.loutai.xia.repository.XiaUserRepository;
import com.github.loutai.xia.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private XiaUserRepository userRepository;

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        XiaUser user = userRepository.
                findByAccountAndDeletedAndStatus(account, Constant.DELETE_N, Constant.USER_STATUS_NORMAL);

        if (user != null) {
            List<SimpleGrantedAuthority> authorityList = userService.getUserAuthority(user.getId());
            CustomUserDetails userDetails =
                    new CustomUserDetails(user.getUsername(), user.getPassword(), authorityList);
            userDetails.setAccount(user.getAccount());
            userDetails.setUserId(user.getId());
            return userDetails;

        } else {
            return null;
        }
    }
}
