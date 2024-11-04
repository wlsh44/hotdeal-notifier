package com.example.hotdealnotifier.user.application.port.out;

import com.example.hotdealnotifier.user.domain.User;

import java.util.List;

public interface UserQueryPort {
    User findById(User.UserId userId);

    List<User> findAll();
}
