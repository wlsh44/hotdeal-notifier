package com.example.hotdealnotifier.hotdeal.adapter.out.persistence;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import com.example.hotdealnotifier.support.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HotDealAdapterTest extends RepositoryTest {

    @Autowired
    HotDealAdapter hotDealAdapter;

    @Autowired
    HotDealJpaRepository hotDealJpaRepository;

    @Autowired
    HotDealMapper hotDealMapper;

    @Test
    @DisplayName("save 메서드 테스트")
    void saveAll() throws Exception {
        //given
        List<HotDeal> hotDealList = List.of(
                HotDeal.of(
                        "title",
                        "url",
                        "price",
                        "image",
                        "shoppingMall",
                        Platform.QUASAR_ZONE
                )
        );

        //when
        hotDealAdapter.saveAll(hotDealList);

        //then
        List<HotDealEntity> hotDealEntityList = hotDealJpaRepository.findAll();
        assertAll(
                () -> assertThat(hotDealEntityList).hasSize(1),
                () -> assertThat(hotDealEntityList.get(0).getId()).isNotNull(),
                () -> assertThat(hotDealEntityList.get(0).getTitle()).isEqualTo("title"),
                () -> assertThat(hotDealEntityList.get(0).getUrl()).isEqualTo("url"),
                () -> assertThat(hotDealEntityList.get(0).getPrice()).isEqualTo("price"),
                () -> assertThat(hotDealEntityList.get(0).getImage()).isEqualTo("image"),
                () -> assertThat(hotDealEntityList.get(0).getShoppingMall()).isEqualTo("shoppingMall"),
                () -> assertThat(hotDealEntityList.get(0).getPlatform()).isEqualTo(Platform.QUASAR_ZONE)
        );
    }
}
