package com.example.hotdealnotifier.hotdeal.crawler.quasarzone;

import com.example.hotdealnotifier.hotdeal.crawler.QuasarZoneHotDealCrawler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QuasarZoneHotDealCrawlerTest {

    @Test
    @DisplayName("퀘이사존 크롤링 테스트")
    void crawl() throws Exception {
        //given
        QuasarZoneHotDealCrawler crawler = new QuasarZoneHotDealCrawler();

        //when
        crawler.crawl();

        //then

    }
}
