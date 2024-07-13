package com.lcwd.user.service.services;

import java.util.List;

import com.lcwd.user.service.entities.User;

public interface UserService {
    User create(User user);

    List<User> get();

    User get(String id);

    void delete(String id);

    User update(User user);
}
