package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;

import java.util.List;

public interface HotDealCrawler {
    List<HotDeal> crawl();
}
