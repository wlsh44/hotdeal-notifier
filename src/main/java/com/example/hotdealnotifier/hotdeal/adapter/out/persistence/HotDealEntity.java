package com.example.hotdealnotifier.hotdeal.adapter.out.persistence;

import com.example.hotdealnotifier.common.jpa.BaseEntity;
import com.example.hotdealnotifier.hotdeal.domain.Platform;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "HOTDEAL")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class HotDealEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String url;

    private String price;

    private String image;

    private String shoppingMall;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Builder
    private HotDealEntity(Long id, String title, String url, String price, String image, String shoppingMall, Platform platform) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.price = price;
        this.image = image;
        this.shoppingMall = shoppingMall;
        this.platform = platform;
    }
}
