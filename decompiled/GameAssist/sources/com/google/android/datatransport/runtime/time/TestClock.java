package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public class TestClock implements Clock {

    /* renamed from: a, reason: collision with root package name */
    private final AtomicLong f10434a;

    @Override // com.google.android.datatransport.runtime.time.Clock
    public long a() {
        return this.f10434a.get();
    }
}
