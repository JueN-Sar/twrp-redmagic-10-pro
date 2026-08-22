package com.google.android.gms.internal.mlkit_common;

import android.os.Build;

/* loaded from: classes.dex */
public final class zzi {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f11633a = {"com.android.", "com.google.", "com.chrome.", "com.nest.", "com.waymo.", "com.waze"};

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f11634b;

    /* renamed from: c, reason: collision with root package name */
    private static final String[] f11635c;

    /* renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ int f11636d = 0;

    static {
        String str = Build.HARDWARE;
        f11634b = new String[]{"media", (str.equals("goldfish") || str.equals("ranchu")) ? "androidx.test.services.storage.runfiles" : ""};
        f11635c = new String[]{"", "", "com.google.android.apps.docs.storage.legacy"};
    }
}
