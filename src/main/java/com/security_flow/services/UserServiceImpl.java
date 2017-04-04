package com.security_flow.services;


import com.security_flow.model.Roles;
import com.security_flow.model.User;
import com.security_flow.repository.RoleRep;
import com.security_flow.repository.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRep userRep;
    @Autowired
    private RoleRep roleRep;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        List<Roles> roles = new ArrayList<>();
        roles.add(roleRep.findOne(1L));
        userRep.save(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userRep.findByUsername(userName);
    }
}
