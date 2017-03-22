package com.cs.system.entity;

import com.cs.common.baseEntity.BaseRequestDTO;

/**
 * Created by s0c00q3 on 2017/3/20.
 */
public class SystemPermission extends BaseRequestDTO {
    private Integer id;
    private String permissionUrl;
    private String permissionName;
    private String enabled;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}
