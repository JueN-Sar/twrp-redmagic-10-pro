package com.google.android.gms.common;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.concurrent.BlockingQueue;

@KeepForSdk
/* loaded from: classes.dex */
public class BlockingServiceConnection implements ServiceConnection {

    /* renamed from: c, reason: collision with root package name */
    private final BlockingQueue f10483c;

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f10483c.add(iBinder);
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
    }
}
