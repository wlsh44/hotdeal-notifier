package com.example.hotdealnotifier.hotdeal.application.service;

import com.example.hotdealnotifier.hotdeal.crawler.HotDealDetector;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.NewHotDealDetectedEvent;
import com.example.hotdealnotifier.keyword.application.port.out.KeywordCommandPort;
import com.example.hotdealnotifier.keyword.domain.Keyword;
import com.example.hotdealnotifier.support.IntegrationTest;
import com.example.hotdealnotifier.support.fixture.HotDealFixture;
import com.example.hotdealnotifier.support.fixture.KeywordFixture;
import com.example.hotdealnotifier.support.fixture.UserFixture;
import com.example.hotdealnotifier.user.application.port.out.UserCommandPort;
import com.example.hotdealnotifier.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class HotDealServiceTest extends IntegrationTest {

    @Autowired
    HotDealService hotDealService;

    @Autowired
    HotDealDetector hotDealDetector;

    @Autowired
    KeywordCommandPort keywordCommandPort;

    @Autowired
    UserCommandPort userCommandPort;

    @Autowired
    ApplicationEventPublisher publisher;

    @Test
    @DisplayName("새 핫딜에 키워드가 포함 되어있는 경우 이벤트가 발행된다")
    void crawl() throws Exception {
        //given
        User givenUser = userCommandPort.save(UserFixture.create());
        Keyword givenKeyword = KeywordFixture.builder()
                .userId(givenUser.getId().id())
                .text("돼지").build();
        keywordCommandPort.save(givenKeyword);
        HotDeal givenHotDeal = HotDealFixture.builder()
                .title("국내산 한돈 돼지 삼겹살 어쩌구").build();
        given(hotDealDetector.detect())
                .willReturn(List.of(givenHotDeal));
        Map<HotDeal, List<User>> hotDealUserListMap = Map.of(givenHotDeal, List.of(givenUser));
        NewHotDealDetectedEvent expectEvent = new NewHotDealDetectedEvent(hotDealUserListMap);

        //when
        hotDealService.crawl();

        //then
        verify(publisher, times(1)).publishEvent(expectEvent);
    }

    @Test
    @DisplayName("새 핫딜에 키워드가 포함 되어있지 않은 경우 이벤트가 발행되지 않는다")
    void crawl_newHotDealNotContainKeyword() throws Exception {
        //given
        User givenUser = userCommandPort.save(UserFixture.create());
        Keyword givenKeyword = KeywordFixture.builder().userId(givenUser.getId().id())
                .text("소고기").build();
        keywordCommandPort.save(givenKeyword);
        HotDeal givenHotDeal = HotDealFixture.builder()
                .title("국내산 한돈 돼지 삼겹살 어쩌구").build();
        given(hotDealDetector.detect())
                .willReturn(List.of(givenHotDeal));

        //when
        hotDealService.crawl();

        //then
        verify(publisher, times(0)).publishEvent(any());
    }
}
