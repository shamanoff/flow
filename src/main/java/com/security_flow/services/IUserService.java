package com.security_flow.services;


import com.security_flow.model.User;

public interface IUserService {

    void save(User user);

    User findByUserName(String userName);
}
