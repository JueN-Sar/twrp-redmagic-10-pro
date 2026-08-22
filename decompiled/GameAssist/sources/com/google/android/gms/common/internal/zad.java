package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

/* loaded from: classes.dex */
final class zad extends zag {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Intent f11051c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Activity f11052h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ int f11053i;

    zad(Intent intent, Activity activity, int i2) {
        this.f11051c = intent;
        this.f11052h = activity;
        this.f11053i = i2;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void a() {
        Intent intent = this.f11051c;
        if (intent != null) {
            this.f11052h.startActivityForResult(intent, this.f11053i);
        }
    }
}
