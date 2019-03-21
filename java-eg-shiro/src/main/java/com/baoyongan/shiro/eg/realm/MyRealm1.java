package com.baoyongan.shiro.eg.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm{
    @Override
    public String getName() {
        return "MyRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username= (String) authenticationToken.getPrincipal(); // 得到用户名
        String password= new String((char[]) authenticationToken.getCredentials()); // 得到密码
        if(!"zhang".equals(username))
            throw new UnknownAccountException();

        if(!"123".equals(password))
            throw new IncorrectCredentialsException();

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
