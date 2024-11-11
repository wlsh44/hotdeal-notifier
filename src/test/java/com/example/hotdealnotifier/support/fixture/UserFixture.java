package com.example.hotdealnotifier.support.fixture;

import com.example.hotdealnotifier.user.domain.User;

public abstract class UserFixture extends BaseFixture {

    public static User create() {
        return builder().build();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id = getUniqueId();
        private String externalId = "externalId";

        public UserBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder externalId(String externalId) {
            this.externalId = externalId;
            return this;
        }

        public User build() {
            return User.withId(new User.UserId(id), externalId);
        }
    }
}
