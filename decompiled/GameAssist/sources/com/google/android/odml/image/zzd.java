package com.google.android.odml.image;

import android.graphics.Bitmap;

/* loaded from: classes.dex */
final /* synthetic */ class zzd {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ int[] f15782a;

    static {
        int[] iArr = new int[Bitmap.Config.values().length];
        f15782a = iArr;
        try {
            iArr[Bitmap.Config.ALPHA_8.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f15782a[Bitmap.Config.ARGB_8888.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}
