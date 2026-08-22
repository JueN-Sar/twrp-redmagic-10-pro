package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import java.util.Set;

/* loaded from: classes.dex */
final class AutoValue_SchedulerConfig_ConfigValue extends SchedulerConfig.ConfigValue {

    /* renamed from: a, reason: collision with root package name */
    private final long f10313a;

    /* renamed from: b, reason: collision with root package name */
    private final long f10314b;

    /* renamed from: c, reason: collision with root package name */
    private final Set f10315c;

    static final class Builder extends SchedulerConfig.ConfigValue.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Long f10316a;

        /* renamed from: b, reason: collision with root package name */
        private Long f10317b;

        /* renamed from: c, reason: collision with root package name */
        private Set f10318c;

        Builder() {
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue a() {
            String str = "";
            if (this.f10316a == null) {
                str = " delta";
            }
            if (this.f10317b == null) {
                str = str + " maxAllowedDelay";
            }
            if (this.f10318c == null) {
                str = str + " flags";
            }
            if (str.isEmpty()) {
                return new AutoValue_SchedulerConfig_ConfigValue(this.f10316a.longValue(), this.f10317b.longValue(), this.f10318c);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder b(long j2) {
            this.f10316a = Long.valueOf(j2);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder c(Set set) {
            if (set == null) {
                throw new NullPointerException("Null flags");
            }
            this.f10318c = set;
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder d(long j2) {
            this.f10317b = Long.valueOf(j2);
            return this;
        }
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    long b() {
        return this.f10313a;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    Set c() {
        return this.f10315c;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    long d() {
        return this.f10314b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SchedulerConfig.ConfigValue)) {
            return false;
        }
        SchedulerConfig.ConfigValue configValue = (SchedulerConfig.ConfigValue) obj;
        return this.f10313a == configValue.b() && this.f10314b == configValue.d() && this.f10315c.equals(configValue.c());
    }

    public int hashCode() {
        long j2 = this.f10313a;
        int i2 = (((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003;
        long j3 = this.f10314b;
        return this.f10315c.hashCode() ^ ((i2 ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003);
    }

    public String toString() {
        return "ConfigValue{delta=" + this.f10313a + ", maxAllowedDelay=" + this.f10314b + ", flags=" + this.f10315c + "}";
    }

    private AutoValue_SchedulerConfig_ConfigValue(long j2, long j3, Set set) {
        this.f10313a = j2;
        this.f10314b = j3;
        this.f10315c = set;
    }
}
