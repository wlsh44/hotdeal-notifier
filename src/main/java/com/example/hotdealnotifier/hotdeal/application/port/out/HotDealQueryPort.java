package com.example.hotdealnotifier.hotdeal.application.port.out;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;

import java.util.List;

public interface HotDealQueryPort {
    List<HotDeal> findAllCacheBy(Platform platform);
}
