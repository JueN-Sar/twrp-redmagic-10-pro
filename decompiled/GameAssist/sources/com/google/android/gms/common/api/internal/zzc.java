package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/* loaded from: classes.dex */
final class zzc implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ LifecycleCallback f10877c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ String f10878h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zzd f10879i;

    zzc(zzd zzdVar, LifecycleCallback lifecycleCallback, String str) {
        this.f10879i = zzdVar;
        this.f10877c = lifecycleCallback;
        this.f10878h = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        zzd zzdVar = this.f10879i;
        i2 = zzdVar.j0;
        if (i2 > 0) {
            LifecycleCallback lifecycleCallback = this.f10877c;
            bundle = zzdVar.k0;
            if (bundle != null) {
                String str = this.f10878h;
                bundle3 = zzdVar.k0;
                bundle2 = bundle3.getBundle(str);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        i3 = this.f10879i.j0;
        if (i3 >= 2) {
            this.f10877c.onStart();
        }
        i4 = this.f10879i.j0;
        if (i4 >= 3) {
            this.f10877c.onResume();
        }
        i5 = this.f10879i.j0;
        if (i5 >= 4) {
            this.f10877c.onStop();
        }
        i6 = this.f10879i.j0;
        if (i6 >= 5) {
            this.f10877c.onDestroy();
        }
    }
}
