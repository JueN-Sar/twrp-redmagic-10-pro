package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig;

/* loaded from: classes.dex */
final class AutoValue_EventStoreConfig extends EventStoreConfig {

    /* renamed from: b, reason: collision with root package name */
    private final long f10365b;

    /* renamed from: c, reason: collision with root package name */
    private final int f10366c;

    /* renamed from: d, reason: collision with root package name */
    private final int f10367d;

    /* renamed from: e, reason: collision with root package name */
    private final long f10368e;

    /* renamed from: f, reason: collision with root package name */
    private final int f10369f;

    static final class Builder extends EventStoreConfig.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Long f10370a;

        /* renamed from: b, reason: collision with root package name */
        private Integer f10371b;

        /* renamed from: c, reason: collision with root package name */
        private Integer f10372c;

        /* renamed from: d, reason: collision with root package name */
        private Long f10373d;

        /* renamed from: e, reason: collision with root package name */
        private Integer f10374e;

        Builder() {
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig a() {
            String str = "";
            if (this.f10370a == null) {
                str = " maxStorageSizeInBytes";
            }
            if (this.f10371b == null) {
                str = str + " loadBatchSize";
            }
            if (this.f10372c == null) {
                str = str + " criticalSectionEnterTimeoutMs";
            }
            if (this.f10373d == null) {
                str = str + " eventCleanUpAge";
            }
            if (this.f10374e == null) {
                str = str + " maxBlobByteSizePerRow";
            }
            if (str.isEmpty()) {
                return new AutoValue_EventStoreConfig(this.f10370a.longValue(), this.f10371b.intValue(), this.f10372c.intValue(), this.f10373d.longValue(), this.f10374e.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder b(int i2) {
            this.f10372c = Integer.valueOf(i2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder c(long j2) {
            this.f10373d = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder d(int i2) {
            this.f10371b = Integer.valueOf(i2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder e(int i2) {
            this.f10374e = Integer.valueOf(i2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig.Builder
        EventStoreConfig.Builder f(long j2) {
            this.f10370a = Long.valueOf(j2);
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int b() {
        return this.f10367d;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    long c() {
        return this.f10368e;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int d() {
        return this.f10366c;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    int e() {
        return this.f10369f;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EventStoreConfig)) {
            return false;
        }
        EventStoreConfig eventStoreConfig = (EventStoreConfig) obj;
        return this.f10365b == eventStoreConfig.f() && this.f10366c == eventStoreConfig.d() && this.f10367d == eventStoreConfig.b() && this.f10368e == eventStoreConfig.c() && this.f10369f == eventStoreConfig.e();
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.EventStoreConfig
    long f() {
        return this.f10365b;
    }

    public int hashCode() {
        long j2 = this.f10365b;
        int i2 = (((((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.f10366c) * 1000003) ^ this.f10367d) * 1000003;
        long j3 = this.f10368e;
        return this.f10369f ^ ((i2 ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003);
    }

    public String toString() {
        return "EventStoreConfig{maxStorageSizeInBytes=" + this.f10365b + ", loadBatchSize=" + this.f10366c + ", criticalSectionEnterTimeoutMs=" + this.f10367d + ", eventCleanUpAge=" + this.f10368e + ", maxBlobByteSizePerRow=" + this.f10369f + "}";
    }

    private AutoValue_EventStoreConfig(long j2, int i2, int i3, long j3, int i4) {
        this.f10365b = j2;
        this.f10366c = i2;
        this.f10367d = i3;
        this.f10368e = j3;
        this.f10369f = i4;
    }
}
