package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.AutoValue_EventStoreConfig;
import com.google.auto.value.AutoValue;

@AutoValue
/* loaded from: classes.dex */
abstract class EventStoreConfig {

    /* renamed from: a, reason: collision with root package name */
    static final EventStoreConfig f10378a = a().f(10485760).d(200).b(10000).c(604800000).e(81920).a();

    @AutoValue.Builder
    static abstract class Builder {
        Builder() {
        }

        abstract EventStoreConfig a();

        abstract Builder b(int i2);

        abstract Builder c(long j2);

        abstract Builder d(int i2);

        abstract Builder e(int i2);

        abstract Builder f(long j2);
    }

    EventStoreConfig() {
    }

    static Builder a() {
        return new AutoValue_EventStoreConfig.Builder();
    }

    abstract int b();

    abstract long c();

    abstract int d();

    abstract int e();

    abstract long f();
}
