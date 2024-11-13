package com.example.hotdealnotifier.hotdeal.adapter.out.persistence;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class HotDealInMemoryCacheRepository {

    private final Map<Platform, List<HotDeal>> savedHotDealMap = new HashMap<>();

    public void saveHotDealCacheList(Platform platform, List<HotDeal> newHotDealList) {
        savedHotDealMap.put(platform, newHotDealList);
    }

    public List<HotDeal> findAllCacheBy(Platform platform) {
        return savedHotDealMap.getOrDefault(platform, Collections.emptyList());
    }
}
