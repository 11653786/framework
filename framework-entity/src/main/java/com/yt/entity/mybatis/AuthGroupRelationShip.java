package com.yt.entity.mybatis;

import java.io.Serializable;

public class AuthGroupRelationShip implements Serializable {
    private Integer id;

    private Integer authId;

    private Integer authGroupId;

    public AuthGroupRelationShip() {
    }

    public AuthGroupRelationShip(Integer authId, Integer authGroupId) {
        this.authId = authId;
        this.authGroupId = authGroupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getAuthGroupId() {
        return authGroupId;
    }

    public void setAuthGroupId(Integer authGroupId) {
        this.authGroupId = authGroupId;
    }
}