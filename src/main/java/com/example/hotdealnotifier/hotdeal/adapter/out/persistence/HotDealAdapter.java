package com.example.hotdealnotifier.hotdeal.adapter.out.persistence;

import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealCommandPort;
import com.example.hotdealnotifier.hotdeal.application.port.out.HotDealQueryPort;
import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

//adapter
@Component
@RequiredArgsConstructor
public class HotDealAdapter implements HotDealQueryPort, HotDealCommandPort {

    private final HotDealJpaRepository hotDealJpaRepository;
    private final HotDealMapper hotDealMapper;
    private final HotDealInMemoryCacheRepository hotDealInMemoryCacheRepository;

    @Override
    public void saveAll(List<HotDeal> newHotDealList) {
        List<HotDealEntity> hotDealEntityList = newHotDealList.stream()
                .map(hotDealMapper::mapToJpaEntity)
                .toList();
        hotDealJpaRepository.saveAll(hotDealEntityList);
    }

    @Override
    public void saveHotDealCacheList(Platform platform, List<HotDeal> newHotDealList) {
        hotDealInMemoryCacheRepository.saveHotDealCacheList(platform, newHotDealList);
    }

    @Override
    public List<HotDeal> findAllCacheBy(Platform platform) {
        return hotDealInMemoryCacheRepository.findAllCacheBy(platform);
    }
}
