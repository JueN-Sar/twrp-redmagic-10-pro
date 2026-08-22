package com.google.mlkit.vision.text.pipeline;

import android.os.RemoteException;
import com.google.android.gms.internal.mlkit_vision_text_bundled_common.zbki;

/* loaded from: classes.dex */
public abstract class zbo {
    static zbo c(int i2, RemoteException remoteException) {
        return new zbb(i2, zbki.e(remoteException));
    }

    public abstract int a();

    public abstract zbki b();

    public final boolean d() {
        return !b().c();
    }
}
