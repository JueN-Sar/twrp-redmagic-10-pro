package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public class DefaultClock implements Clock {

    /* renamed from: a, reason: collision with root package name */
    private static final DefaultClock f11255a = new DefaultClock();

    private DefaultClock() {
    }

    public static Clock a() {
        return f11255a;
    }
}
