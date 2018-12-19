package com.baoyongan.shiro.eg.service;

import com.baoyongan.shiro.eg.dao.PermissionDao;
import com.baoyongan.shiro.eg.dao.PermissionDaoImpl;
import com.baoyongan.shiro.eg.entity.Permission;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class PermissionServiceImpl implements PermissionService {

    private PermissionDao permissionDao = new PermissionDaoImpl();

    public Permission createPermission(Permission permission) {
        return permissionDao.createPermission(permission);
    }

    public void deletePermission(Long permissionId) {
        permissionDao.deletePermission(permissionId);
    }
}
