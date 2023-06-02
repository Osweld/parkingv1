package com.oswelddev.parkingv1.services;

import com.oswelddev.parkingv1.auth.models.AuthUser;
import com.oswelddev.parkingv1.models.entity.User;
import com.oswelddev.parkingv1.models.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public class ImplUserDetailsService  implements UserDetailsService {


    private final UserRepository userRepository;

    public ImplUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new  UsernameNotFoundException("The username:"+username+" does not exist"));
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRol().getRol()));
        //if(authorities.isEmpty()) throw new UsernameNotFoundException("the user has no assigned roles");

        return new AuthUser(user.getId(),user.getUsername(),user.getPassword(),user.getActive(),true,true,true,authorities);
    }
}
