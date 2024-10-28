package com.example.hotdealnotifier.hotdeal.crawler.quasarzone;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
