package com.example.hotdealnotifier.hotdeal.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class HotDeal {

    private String title;
    private String url;
    private String price;
    private String image;
    private String shoppingMall;
    private Platform platform;

    @Builder
    public HotDeal(String title, String url, String price, String image, String shoppingMall, Platform platform) {
        this.title = title;
        this.url = url;
        this.price = price;
        this.image = image;
        this.shoppingMall = shoppingMall;
        this.platform = platform;
    }
}
