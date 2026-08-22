package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
/* loaded from: classes.dex */
public interface ConnectionCallbacks {
    void onConnected(Bundle bundle);

    void onConnectionSuspended(int i2);
}
