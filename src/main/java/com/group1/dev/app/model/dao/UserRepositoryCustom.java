package com.group1.dev.app.model.dao;

import com.group1.dev.app.model.entity.EntityUser;

public interface UserRepositoryCustom {
    public EntityUser findUser(String username, String password);
}