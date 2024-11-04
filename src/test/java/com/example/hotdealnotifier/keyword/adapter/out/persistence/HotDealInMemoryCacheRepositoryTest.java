package com.example.hotdealnotifier.keyword.adapter.out.persistence;

import com.example.hotdealnotifier.hotdeal.crawler.HotDealCrawler;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import com.example.hotdealnotifier.support.fixture.HotDealFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HotDealInMemoryCacheRepositoryTest {

    @InjectMocks
    HotDealInMemoryCacheRepository repository;

    @Test
    @DisplayName("핫딜 인메모리 repository 테스트")
    void hotDealInMemoryRepositoryTest() throws Exception {
        //given
        HotDeal givenHotDeal = HotDealFixture.create();

        //when
        repository.saveHotDealCacheList(Platform.QUASAR_ZONE, List.of(givenHotDeal));

        //then
        List<HotDeal> hotDealList = repository.findAllCacheBy(Platform.QUASAR_ZONE);
        assertThat(hotDealList).hasSize(1);
        assertThat(hotDealList.contains(givenHotDeal)).isTrue();
    }
}
