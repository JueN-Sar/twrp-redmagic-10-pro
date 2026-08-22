package com.google.android.gms.common.internal;

import android.content.Intent;
import com.google.android.gms.common.api.internal.LifecycleFragment;

/* loaded from: classes.dex */
final class zaf extends zag {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ Intent f11057c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ LifecycleFragment f11058h;

    zaf(Intent intent, LifecycleFragment lifecycleFragment, int i2) {
        this.f11057c = intent;
        this.f11058h = lifecycleFragment;
    }

    @Override // com.google.android.gms.common.internal.zag
    public final void a() {
        Intent intent = this.f11057c;
        if (intent != null) {
            this.f11058h.startActivityForResult(intent, 2);
        }
    }
}
