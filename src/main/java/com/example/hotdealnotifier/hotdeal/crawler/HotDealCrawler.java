package com.example.hotdealnotifier.hotdeal.crawler;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;

import java.util.List;

public interface HotDealCrawler {
    List<HotDeal> crawl();
    Platform getPlatform();
}
