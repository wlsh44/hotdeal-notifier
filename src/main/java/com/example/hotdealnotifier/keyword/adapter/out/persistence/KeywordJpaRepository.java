package com.example.hotdealnotifier.keyword.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordJpaRepository extends JpaRepository<KeywordEntity, Long> {

    List<KeywordEntity> findAllByUserId(Long userId);
}
