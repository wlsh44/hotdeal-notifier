package com.example.hotdealnotifier.hotdeal.adapter.out.persistence;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class HotDealMapper {

    public HotDealEntity mapToJpaEntity(HotDeal hotDeal) {
        return HotDealEntity.builder()
                .id(Objects.isNull(hotDeal.getId()) ? null : hotDeal.getId().value())
                .title(hotDeal.getTitle())
                .price(hotDeal.getPrice())
                .shoppingMall(hotDeal.getShoppingMall())
                .url(hotDeal.getUrl())
                .image(hotDeal.getImage())
                .platform(hotDeal.getPlatform())
                .build();
    }

    public HotDeal mapToDomainEntity(HotDealEntity hotDealEntity) {
        return HotDeal.withId(
                new HotDeal.HotDealId(hotDealEntity.getId()),
                hotDealEntity.getTitle(),
                hotDealEntity.getUrl(),
                hotDealEntity.getPrice(),
                hotDealEntity.getImage(),
                hotDealEntity.getShoppingMall(),
                hotDealEntity.getPlatform()
        );
    }
}
