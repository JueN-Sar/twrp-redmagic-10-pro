package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.time.Clock;
import java.util.Map;

/* loaded from: classes.dex */
final class AutoValue_SchedulerConfig extends SchedulerConfig {

    /* renamed from: a, reason: collision with root package name */
    private final Clock f10311a;

    /* renamed from: b, reason: collision with root package name */
    private final Map f10312b;

    AutoValue_SchedulerConfig(Clock clock, Map map) {
        if (clock == null) {
            throw new NullPointerException("Null clock");
        }
        this.f10311a = clock;
        if (map == null) {
            throw new NullPointerException("Null values");
        }
        this.f10312b = map;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    Clock e() {
        return this.f10311a;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SchedulerConfig)) {
            return false;
        }
        SchedulerConfig schedulerConfig = (SchedulerConfig) obj;
        return this.f10311a.equals(schedulerConfig.e()) && this.f10312b.equals(schedulerConfig.h());
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig
    Map h() {
        return this.f10312b;
    }

    public int hashCode() {
        return this.f10312b.hashCode() ^ ((this.f10311a.hashCode() ^ 1000003) * 1000003);
    }

    public String toString() {
        return "SchedulerConfig{clock=" + this.f10311a + ", values=" + this.f10312b + "}";
    }
}
