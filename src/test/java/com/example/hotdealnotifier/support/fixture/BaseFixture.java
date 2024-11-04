package com.example.hotdealnotifier.support.fixture;

import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseFixture {

    private static final AtomicLong idGenerator = new AtomicLong();

    protected static Long getUniqueId() {
        return idGenerator.incrementAndGet();
    }
}
