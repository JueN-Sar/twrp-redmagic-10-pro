package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.auto.value.AutoValue;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AutoValue
/* loaded from: classes.dex */
public abstract class SchedulerConfig {

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Clock f10324a;

        /* renamed from: b, reason: collision with root package name */
        private Map f10325b = new HashMap();

        public Builder a(Priority priority, ConfigValue configValue) {
            this.f10325b.put(priority, configValue);
            return this;
        }

        public SchedulerConfig b() {
            if (this.f10324a == null) {
                throw new NullPointerException("missing required property: clock");
            }
            if (this.f10325b.keySet().size() < Priority.values().length) {
                throw new IllegalStateException("Not all priorities have been configured");
            }
            Map map = this.f10325b;
            this.f10325b = new HashMap();
            return SchedulerConfig.d(this.f10324a, map);
        }

        public Builder c(Clock clock) {
            this.f10324a = clock;
            return this;
        }
    }

    @AutoValue
    public static abstract class ConfigValue {

        @AutoValue.Builder
        public static abstract class Builder {
            public abstract ConfigValue a();

            public abstract Builder b(long j2);

            public abstract Builder c(Set set);

            public abstract Builder d(long j2);
        }

        public static Builder a() {
            return new AutoValue_SchedulerConfig_ConfigValue.Builder().c(Collections.emptySet());
        }

        abstract long b();

        abstract Set c();

        abstract long d();
    }

    public enum Flag {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING
    }

    private long a(int i2, long j2) {
        return (long) (Math.pow(3.0d, i2 - 1) * j2 * Math.max(1.0d, Math.log(10000.0d) / Math.log((j2 > 1 ? j2 : 2L) * r7)));
    }

    public static Builder b() {
        return new Builder();
    }

    static SchedulerConfig d(Clock clock, Map map) {
        return new AutoValue_SchedulerConfig(clock, map);
    }

    public static SchedulerConfig f(Clock clock) {
        return b().a(Priority.DEFAULT, ConfigValue.a().b(30000L).d(86400000L).a()).a(Priority.HIGHEST, ConfigValue.a().b(1000L).d(86400000L).a()).a(Priority.VERY_LOW, ConfigValue.a().b(86400000L).d(86400000L).c(i(Flag.NETWORK_UNMETERED, Flag.DEVICE_IDLE)).a()).c(clock).b();
    }

    private static Set i(Object... objArr) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(objArr)));
    }

    private void j(JobInfo.Builder builder, Set set) {
        if (set.contains(Flag.NETWORK_UNMETERED)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (set.contains(Flag.DEVICE_CHARGING)) {
            builder.setRequiresCharging(true);
        }
        if (set.contains(Flag.DEVICE_IDLE)) {
            builder.setRequiresDeviceIdle(true);
        }
    }

    public JobInfo.Builder c(JobInfo.Builder builder, Priority priority, long j2, int i2) {
        builder.setMinimumLatency(g(priority, j2, i2));
        j(builder, ((ConfigValue) h().get(priority)).c());
        return builder;
    }

    abstract Clock e();

    public long g(Priority priority, long j2, int i2) {
        long a2 = j2 - e().a();
        ConfigValue configValue = (ConfigValue) h().get(priority);
        return Math.min(Math.max(a(i2, configValue.b()), a2), configValue.d());
    }

    abstract Map h();
}
