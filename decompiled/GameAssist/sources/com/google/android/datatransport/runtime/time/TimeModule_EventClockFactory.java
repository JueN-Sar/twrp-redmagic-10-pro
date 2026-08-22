package com.google.android.datatransport.runtime.time;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class TimeModule_EventClockFactory implements Factory<Clock> {

    private static final class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static final TimeModule_EventClockFactory f10435a = new TimeModule_EventClockFactory();
    }

    public static TimeModule_EventClockFactory a() {
        return InstanceHolder.f10435a;
    }

    public static Clock b() {
        return (Clock) Preconditions.c(TimeModule.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Clock get() {
        return b();
    }
}
