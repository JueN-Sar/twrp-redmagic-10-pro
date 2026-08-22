package com.google.android.gms.common.api.internal;

import android.app.Dialog;

/* loaded from: classes.dex */
final class zan extends zabw {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Dialog f10855a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ zao f10856b;

    zan(zao zaoVar, Dialog dialog) {
        this.f10856b = zaoVar;
        this.f10855a = dialog;
    }

    @Override // com.google.android.gms.common.api.internal.zabw
    public final void a() {
        this.f10856b.f10858h.d();
        if (this.f10855a.isShowing()) {
            this.f10855a.dismiss();
        }
    }
}
