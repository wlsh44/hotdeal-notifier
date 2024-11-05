package com.example.hotdealnotifier.user.application.port.out;

import com.example.hotdealnotifier.user.domain.User;

public interface UserCommandPort {

    User save(User user);
}
