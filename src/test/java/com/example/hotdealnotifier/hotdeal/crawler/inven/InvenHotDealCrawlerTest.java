package com.example.hotdealnotifier.hotdeal.crawler.inven;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvenHotDealCrawlerTest {

    @Test
    @DisplayName("인벤 핫딜 크롤링")
    void crawl() throws Exception {
        //given
        InvenHotDealCrawler crawler = new InvenHotDealCrawler();

        //when
        crawler.crawl();

        //then

    }
}
