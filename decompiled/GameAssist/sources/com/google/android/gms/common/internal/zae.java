package com.google.android.gms.common.internal;

import android.content.Intent;
import androidx.fragment.app.Fragment;

/* loaded from: classes.dex */
final class zae extends zag {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Intent f11054c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ Fragment f11055h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ int f11056i;

    @Override // com.google.android.gms.common.internal.zag
    public final void a() {
        Intent intent = this.f11054c;
        if (intent != null) {
            this.f11055h.startActivityForResult(intent, this.f11056i);
        }
    }
}
