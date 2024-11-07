package com.example.hotdealnotifier.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class User {

    private UserId id;

    private User(UserId id) {
        this.id = id;
    }

    public User() {
    }

    public static User withId(UserId userId) {
        return new User(userId);
    }

    public record UserId(
            Long id
    ) {
    }
}
