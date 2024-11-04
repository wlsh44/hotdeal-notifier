package com.example.hotdealnotifier.user.adapter.out.persistence;

import com.example.hotdealnotifier.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToDomainEntity(UserEntity userEntity) {
        return User.withId(new User.UserId(userEntity.getId()));
    }

    public UserEntity mapToJpaEntity(User user) {
        return UserEntity.builder()
                .id(user.getId().id())
                .build();
    }
}
