package com.example.hotdealnotifier.keyword.adapter.out.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "KEYWORD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KeywordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    private Long userId;

    @Builder
    private KeywordEntity(Long id, String text, Long userId) {
        this.id = id;
        this.text = text;
        this.userId = userId;
    }
}
