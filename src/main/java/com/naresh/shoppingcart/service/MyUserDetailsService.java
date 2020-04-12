package com.naresh.shoppingcart.service;

import com.naresh.shoppingcart.dao.UserRepository;
import com.naresh.shoppingcart.model.user.MyUserDetails;
import com.naresh.shoppingcart.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Qualifier("myUserDetailsService")
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("User Not Found :" + userName));
        return user.map(MyUserDetails::new).get();
    }
}
