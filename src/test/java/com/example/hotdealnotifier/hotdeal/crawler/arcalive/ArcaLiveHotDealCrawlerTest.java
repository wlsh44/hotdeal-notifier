package com.example.hotdealnotifier.hotdeal.crawler.arcalive;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArcaLiveHotDealCrawlerTest {

    @Test
    @DisplayName("아카라이브 핫딜 크롤링")
    void crawl() throws Exception {
        //given
        ArcaLiveHotDealCrawler crawler = new ArcaLiveHotDealCrawler();

        //when
        crawler.crawl();

        //then

    }
}
