package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/* loaded from: classes.dex */
final class zza implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ LifecycleCallback f10870c;

    /* renamed from: h, reason: collision with root package name */
    final /* synthetic */ String f10871h;

    /* renamed from: i, reason: collision with root package name */
    final /* synthetic */ zzb f10872i;

    zza(zzb zzbVar, LifecycleCallback lifecycleCallback, String str) {
        this.f10872i = zzbVar;
        this.f10870c = lifecycleCallback;
        this.f10871h = str;
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
        zzb zzbVar = this.f10872i;
        i2 = zzbVar.f10875h;
        if (i2 > 0) {
            LifecycleCallback lifecycleCallback = this.f10870c;
            bundle = zzbVar.f10876i;
            if (bundle != null) {
                String str = this.f10871h;
                bundle3 = zzbVar.f10876i;
                bundle2 = bundle3.getBundle(str);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        i3 = this.f10872i.f10875h;
        if (i3 >= 2) {
            this.f10870c.onStart();
        }
        i4 = this.f10872i.f10875h;
        if (i4 >= 3) {
            this.f10870c.onResume();
        }
        i5 = this.f10872i.f10875h;
        if (i5 >= 4) {
            this.f10870c.onStop();
        }
        i6 = this.f10872i.f10875h;
        if (i6 >= 5) {
            this.f10870c.onDestroy();
        }
    }
}
