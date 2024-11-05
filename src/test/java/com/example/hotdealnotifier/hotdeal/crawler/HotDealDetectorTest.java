package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealCommandPort;
import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealQueryPort;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import com.example.hotdealnotifier.support.fixture.HotDealFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class HotDealDetectorTest {

    @Test
    @DisplayName("핫딜 감지 테스트")
    void detect() throws Exception {
        //given
        HotDealCrawler givenHotDealCrawler = mock(HotDealCrawler.class);
        given(givenHotDealCrawler.getPlatform())
                .willReturn(Platform.QUASAR_ZONE);
        HotDealDetector hotDealDetector = new HotDealDetector(List.of(givenHotDealCrawler), mock(HotDealCommandPort.class), mock(HotDealQueryPort.class));
        HotDeal givenNewHotDeal = HotDealFixture.create();
        given(givenHotDealCrawler.crawl())
                .willReturn(List.of(givenNewHotDeal));

        //when
        List<HotDeal> newHotDealList = hotDealDetector.detect();

        //then
        assertThat(newHotDealList).hasSize(1);
        assertThat(newHotDealList.contains(givenNewHotDeal)).isTrue();
    }
}
