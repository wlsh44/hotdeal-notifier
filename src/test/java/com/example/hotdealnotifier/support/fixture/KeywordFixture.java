package com.example.hotdealnotifier.support.fixture;

import com.example.hotdealnotifier.keyword.domain.Keyword;

public abstract class KeywordFixture extends BaseFixture {

    public static Keyword create(Long userId) {
        return builder().userId(userId).build();
    }

    public static KeywordBuilder builder() {
        return new KeywordBuilder();
    }

    public static class KeywordBuilder {
        private Long id = getUniqueId();
        private Long userId = 1L;
        private String text = "text";

        public KeywordBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public KeywordBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public KeywordBuilder text(String text) {
            this.text = text;
            return this;
        }

        public Keyword build() {
            return Keyword.withId(new Keyword.KeywordId(id), text, userId);
        }
    }
}
