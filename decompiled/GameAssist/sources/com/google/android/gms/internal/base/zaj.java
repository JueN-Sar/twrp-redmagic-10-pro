package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable;

/* loaded from: classes.dex */
final class zaj extends Drawable.ConstantState {

    /* renamed from: a, reason: collision with root package name */
    int f11370a;

    /* renamed from: b, reason: collision with root package name */
    int f11371b;

    zaj(zaj zajVar) {
        if (zajVar != null) {
            this.f11370a = zajVar.f11370a;
            this.f11371b = zajVar.f11371b;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        return this.f11370a;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        return new zak(this);
    }
}
