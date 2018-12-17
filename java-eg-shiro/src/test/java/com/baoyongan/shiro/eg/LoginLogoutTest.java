package com.baoyongan.shiro.eg;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

import static sun.misc.Version.println;

public class LoginLogoutTest {


    @Test
    public void testHelloWorld(){

        Factory<SecurityManager> factory= new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager= factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        // 通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定；
        Subject subject=SecurityUtils.getSubject();

        // principals 身份
        // credentials 凭证
        // 获取身份验证的Token，如用户名/密码；
        UsernamePasswordToken token= new UsernamePasswordToken("zhang","123");

        try {
            // 身份验证 调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录
            subject.login(token);
        }catch (AuthenticationException e){
            // 身份验证失败
            System.err.println("用户名或密码错误");
            // 如果身份验证失败请捕获AuthenticationException或其子类，常见的如： DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系；对于页面的错误消息展示，最好使用如“用户名/密码错误”而不是“用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        // 最后可以调用subject.logout退出，其会自动委托给SecurityManager.logout方法退出
        subject.logout();

    }

    // shiro-realm.
    @Test
    public void testMyRealm1(){

        Factory<SecurityManager> factory= new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        SecurityManager securityManager= factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        // 通过SecurityUtils得到Subject，其会自动绑定到当前线程；如果在web环境在请求结束时需要解除绑定；
        Subject subject=SecurityUtils.getSubject();

        // principals 身份
        // credentials 凭证
        // 获取身份验证的Token，如用户名/密码；
        UsernamePasswordToken token= new UsernamePasswordToken("zhang","123");

        try {
            // 身份验证 调用subject.login方法进行登录，其会自动委托给SecurityManager.login方法进行登录
            subject.login(token);
        }catch (AuthenticationException e){
            // 身份验证失败
            System.err.println("用户名或密码错误");
            // 如果身份验证失败请捕获AuthenticationException或其子类，常见的如： DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系；对于页面的错误消息展示，最好使用如“用户名/密码错误”而不是“用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        // 最后可以调用subject.logout退出，其会自动委托给SecurityManager.logout方法退出
        subject.logout();
    }

    @Test
    public void testCustomMultiRealm() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }

    @Test
    public void testJDBCRealm() {

        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //6、退出
        subject.logout();
    }



}
