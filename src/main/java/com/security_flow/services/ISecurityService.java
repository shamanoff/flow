package com.security_flow.services;

public interface ISecurityService {

    String findLoggedInUserName();

    void autoLogin(String userName, String password);

}
