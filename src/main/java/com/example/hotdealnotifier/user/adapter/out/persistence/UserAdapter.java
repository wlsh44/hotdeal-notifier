package com.example.hotdealnotifier.user.adapter.out.persistence;

import com.example.hotdealnotifier.user.application.port.out.UserCommandPort;
import com.example.hotdealnotifier.user.application.port.out.UserQueryPort;
import com.example.hotdealnotifier.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserQueryPort, UserCommandPort {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    public User findById(User.UserId userId) {
        UserEntity userEntity = userJpaRepository.findById(userId.id())
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        return userMapper.mapToDomainEntity(userEntity);
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll().stream()
                .map(userMapper::mapToDomainEntity)
                .toList();
    }

    @Override
    public Optional<User> findByExternalId(String externalId) {
        return userJpaRepository.findByExternalId(externalId)
                .map(userMapper::mapToDomainEntity);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userJpaRepository.save(userMapper.mapToJpaEntity(user));
        return userMapper.mapToDomainEntity(userEntity);
    }
}
