package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public interface LifecycleFragment {
    void b(String str, LifecycleCallback lifecycleCallback);

    LifecycleCallback c(String str, Class cls);

    Activity g();

    void startActivityForResult(Intent intent, int i2);
}
