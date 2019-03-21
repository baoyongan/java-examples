package com.baoyongan.shiro.eg;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticatorTest {


    public void login(String configFile){

        Factory<SecurityManager> factory= new IniSecurityManagerFactory(configFile);

        SecurityManager securityManager= factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        // 通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定；
        Subject subject=SecurityUtils.getSubject();

        // principals 身份
        // credentials 凭证
        // 获取身份验证的Token，如用户名/密码；
        UsernamePasswordToken token= new UsernamePasswordToken("zhang","123");

        subject.login(token);

    }

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:shiro-authenticator-all-fail.ini");
        SecurityUtils.getSubject();
    }

}
