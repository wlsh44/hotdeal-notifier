package com.example.hotdealnotifier.hotdeal.adapter.out.persistence;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class HotDealMapperTest {

    HotDealMapper hotDealMapper;

    @BeforeEach
    void setUp() {
        hotDealMapper = new HotDealMapper();
    }

    @Test
    @DisplayName("mapToJpaEntity 메서드 테스트")
    void mapToJpaEntity() throws Exception {
        //given
        HotDeal hotDeal = HotDeal.withId(
                new HotDeal.HotDealId(1L),
                "title",
                "url",
                "price",
                "image",
                "shoppingMall",
                Platform.QUASAR_ZONE
        );

        //when
        HotDealEntity hotDealEntity = hotDealMapper.mapToJpaEntity(hotDeal);

        //then
        assertAll(
                () -> assertThat(hotDealEntity.getId()).isEqualTo(1L),
                () -> assertThat(hotDealEntity.getTitle()).isEqualTo("title"),
                () -> assertThat(hotDealEntity.getUrl()).isEqualTo("url"),
                () -> assertThat(hotDealEntity.getPrice()).isEqualTo("price"),
                () -> assertThat(hotDealEntity.getImage()).isEqualTo("image"),
                () -> assertThat(hotDealEntity.getShoppingMall()).isEqualTo("shoppingMall"),
                () -> assertThat(hotDealEntity.getPlatform()).isEqualTo(Platform.QUASAR_ZONE)
        );
    }

    @Test
    @DisplayName("mapToDomainEntity 메서드 테스트")
    void mapToDomainEntity() throws Exception {
        //given
        HotDealEntity hotDealEntity = HotDealEntity.builder()
                .id(1L)
                .title("title")
                .url("url")
                .price("price")
                .image("image")
                .shoppingMall("shoppingMall")
                .platform(Platform.QUASAR_ZONE)
                .build();

        //when
        HotDeal hotDeal = hotDealMapper.mapToDomainEntity(hotDealEntity);

        //then
        assertAll(
                () -> assertThat(hotDeal.getId().value()).isEqualTo(1L),
                () -> assertThat(hotDeal.getTitle()).isEqualTo("title"),
                () -> assertThat(hotDeal.getUrl()).isEqualTo("url"),
                () -> assertThat(hotDeal.getPrice()).isEqualTo("price"),
                () -> assertThat(hotDeal.getImage()).isEqualTo("image"),
                () -> assertThat(hotDeal.getShoppingMall()).isEqualTo("shoppingMall"),
                () -> assertThat(hotDeal.getPlatform()).isEqualTo(Platform.QUASAR_ZONE)
        );
    }
}
