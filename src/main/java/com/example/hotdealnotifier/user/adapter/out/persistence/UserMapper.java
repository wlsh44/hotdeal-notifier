package com.example.hotdealnotifier.user.adapter.out.persistence;

import com.example.hotdealnotifier.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

    public User mapToDomainEntity(UserEntity userEntity) {
        return User.withId(new User.UserId(userEntity.getId()), userEntity.getExternalId());
    }

    public UserEntity mapToJpaEntity(User user) {
        return UserEntity.builder()
                .id(Objects.isNull(user.getId()) ? null : user.getId().id())
                .externalId(user.getExternalId())
                .build();
    }
}
