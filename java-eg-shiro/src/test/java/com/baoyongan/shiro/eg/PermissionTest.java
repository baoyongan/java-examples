package com.baoyongan.shiro.eg;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted(){

        login("classpath:roles/shiro-permission.ini","zhang","123");

        Assert.assertTrue(subject().isPermitted("user:create:1"));

        Assert.assertTrue(subject().isPermittedAll("user:update","user:delete"));

        Assert.assertFalse(subject().isPermitted("user:view"));

    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission(){
        login("classpath:roles/shiro-permission.ini","zhang","123");

        subject().checkPermission("user:create");

        subject().checkPermissions("user:update","user:delete");

        subject().checkPermission("user:view");
    }
}
