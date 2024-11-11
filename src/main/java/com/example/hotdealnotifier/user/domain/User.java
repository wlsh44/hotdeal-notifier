package com.example.hotdealnotifier.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(of = "id")
public class User {

    private UserId id;
    private String externalId;

    private User(UserId id, String externalId) {
        this.id = id;
        this.externalId = externalId;
    }

    public User(String externalId) {
        this.externalId = externalId;
    }

    public static User withId(UserId userId, String externalId) {
        return new User(userId, externalId);
    }

    public record UserId(
            Long id
    ) {
    }
}
