package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;

/* loaded from: classes.dex */
public abstract class zag {

    /* renamed from: a, reason: collision with root package name */
    final zad f10947a;

    /* renamed from: b, reason: collision with root package name */
    protected int f10948b;

    protected abstract void a(Drawable drawable, boolean z, boolean z2, boolean z3);

    final void b(Context context, zam zamVar, boolean z) {
        int i2 = this.f10948b;
        a(i2 != 0 ? context.getResources().getDrawable(i2) : null, z, false, false);
    }

    final void c(Context context, Bitmap bitmap, boolean z) {
        Asserts.c(bitmap);
        a(new BitmapDrawable(context.getResources(), bitmap), false, false, true);
    }
}
