package com.example.hotdealnotifier.notification.domain;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;

public record HotDealInfo(
        String title,
        String url,
        String price,
        String image,
        String shoppingMall,
        Platform platform
) {

    public static HotDealInfo from(HotDeal hotDeal) {
        return new HotDealInfo(
                hotDeal.getTitle(),
                hotDeal.getUrl(),
                hotDeal.getPrice(),
                hotDeal.getImage(),
                hotDeal.getShoppingMall(),
                hotDeal.getPlatform()
        );
    }
}
