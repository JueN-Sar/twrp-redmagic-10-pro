package com.google.android.gms.common.internal;

import android.net.Uri;

/* loaded from: classes.dex */
public final class zzu {

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f11131a;

    /* renamed from: b, reason: collision with root package name */
    private static final Uri f11132b;

    static {
        Uri parse = Uri.parse("https://plus.google.com/");
        f11131a = parse;
        f11132b = parse.buildUpon().appendPath("circles").appendPath("find").build();
    }
}
