package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.crawler.fmkorea.FmKoreaHotDealCrawler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FmKoreaHotDealCrawlerTest {

    @Test
    @DisplayName("에펨코리아 핫딜 크롤링")
    void crawl() throws Exception {
        //given
        FmKoreaHotDealCrawler fmKoreaHotDealCrawler = new FmKoreaHotDealCrawler();

        //when
        fmKoreaHotDealCrawler.crawl();

        //then

    }
}
