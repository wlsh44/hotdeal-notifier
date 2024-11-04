package com.example.hotdealnotifier.hotdeal.application.port.out;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;

import java.util.List;

public interface HotDealCommandPort {
    void saveAll(List<HotDeal> newHotDealList);

    void saveHotDealCacheList(Platform platform, List<HotDeal> newHotDealList);
}
