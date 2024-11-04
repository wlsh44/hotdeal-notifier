package com.example.hotdealnotifier.support;

import com.example.hotdealnotifier.hotdeal.crawler.HotDealDetector;
import com.example.hotdealnotifier.hotdeal.crawler.arcalive.ArcaLiveHotDealCrawler;
import com.example.hotdealnotifier.hotdeal.crawler.fmkorea.FmKoreaHotDealCrawler;
import com.example.hotdealnotifier.hotdeal.crawler.inven.InvenHotDealCrawler;
import com.example.hotdealnotifier.hotdeal.crawler.quasarzone.QuasarZoneHotDealCrawler;
import com.example.hotdealnotifier.support.db.DatabaseCleanerExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@SpringBootTest
@Import(IntegrationTest.MockConfiguration.class)
@ExtendWith(DatabaseCleanerExtension.class)
public abstract class IntegrationTest {

    @MockBean
    protected HotDealDetector hotDealDetector;

    @SpyBean
    protected ArcaLiveHotDealCrawler arcaLiveHotDealCrawler;

    @SpyBean
    protected FmKoreaHotDealCrawler fmKoreaHotDealCrawler;

    @SpyBean
    protected InvenHotDealCrawler invenHotDealCrawler;

    @SpyBean
    protected QuasarZoneHotDealCrawler quasarZoneHotDealCrawler;

    @TestConfiguration
    public static class MockConfiguration {

        @Bean
        @Primary
        public ApplicationEventPublisher publisher() {
            return mock(ApplicationEventPublisher.class);
        }
    }

}
