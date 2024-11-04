package com.example.hotdealnotifier.support.fixture;

import com.example.hotdealnotifier.hotdeal.domain.HotDeal;
import com.example.hotdealnotifier.hotdeal.domain.Platform;

public class HotDealFixture {

    public static HotDeal create() {
        return builder().build();
    }

    public static HotDealBuilder builder() {
        return new HotDealBuilder();
    }

    public static class HotDealBuilder {
        private String title = "title";
        private String url = "url";
        private Platform platform = Platform.QUASAR_ZONE;
        private String image = "image";
        private String price = "price";
        private String shoppingMall = "shoppingMall";

        public HotDealBuilder title(String title) {
            this.title = title;
            return this;
        }

        public HotDealBuilder url(String url) {
            this.url = url;
            return this;
        }

        public HotDealBuilder platform(Platform platform) {
            this.platform = platform;
            return this;
        }

        public HotDealBuilder price(String price) {
            this.price = price;
            return this;
        }

        public HotDealBuilder shoppingMall(String shoppingMall) {
            this.shoppingMall = shoppingMall;
            return this;
        }

        public HotDealBuilder image(String image) {
            this.image = image;
            return this;
        }

        public HotDeal build() {
            return HotDeal.of(title, url, price, image, shoppingMall, platform);
        }
    }
}
