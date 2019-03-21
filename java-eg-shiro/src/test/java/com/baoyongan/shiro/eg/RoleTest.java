package com.baoyongan.shiro.eg;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RoleTest extends BaseTest {

    @Test
    public void testHashRole(){

        login("classpath:roles/shiro-role.ini","zhang","123");

        Assert.assertTrue(subject().hasRole("role1"));

        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1","role2")));

        boolean[] results=subject().hasRoles(Arrays.asList("role1","role2","role3"));

        Assert.assertEquals(true,results[0]);
        Assert.assertEquals(true,results[1]);
        Assert.assertEquals(false,results[2]);

        // 我们可以通过 用户判断是否拥有某个权限，无法通过用户获取所有的权限列表；
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole(){
        login("classpath:roles/shiro-role.ini","zhang","123");

        subject().checkRole("role1");

        subject().checkRoles("role1","role3");
    }
}
