package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class TimeModule_UptimeClockFactory implements Factory<Clock> {

    private static final class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static final TimeModule_UptimeClockFactory f10436a = new TimeModule_UptimeClockFactory();
    }

    public static TimeModule_UptimeClockFactory a() {
        return InstanceHolder.f10436a;
    }

    public static Clock c() {
        return (Clock) Preconditions.c(TimeModule.b(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Clock get() {
        return c();
    }
}
