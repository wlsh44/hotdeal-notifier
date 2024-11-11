package com.example.hotdealnotifier.hotdeal.domain;

import com.example.hotdealnotifier.common.jpa.BaseEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class HotDeal {

    private HotDealId id;
    private String title;
    private String url;
    private String price;
    private String image;
    private String shoppingMall;
    private Platform platform;

    private HotDeal(HotDealId id, String title, String url, String price, String image, String shoppingMall, Platform platform) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.price = price;
        this.image = image;
        this.shoppingMall = shoppingMall;
        this.platform = platform;
    }

    public static HotDeal of(String title, String url, String price, String image, String shoppingMall, Platform platform) {
        return new HotDeal(null, title, url, price, image, shoppingMall, platform);
    }

    public static HotDeal withId(HotDealId hotDealId, String title, String url, String price, String image, String shoppingMall, Platform platform) {
        return new HotDeal(hotDealId, title, url, price, image, shoppingMall, platform);
    }

    public boolean isContainedInHotDealList(List<HotDeal> hotDealList) {
        return hotDealList.stream()
                .anyMatch(this::isSameHotDeal);
    }

    private boolean isSameHotDeal(HotDeal hotDeal) {
        return title.equals(hotDeal.title) &&
                url.equals(hotDeal.url) &&
                price.equals(hotDeal.price) &&
                platform.equals(hotDeal.platform) &&
                shoppingMall.equals(hotDeal.shoppingMall);
    }

    public boolean isContainingKeyword(String keyword) {
        return title.contains(keyword);
    }

    public record HotDealId(Long value) {
    }
}
