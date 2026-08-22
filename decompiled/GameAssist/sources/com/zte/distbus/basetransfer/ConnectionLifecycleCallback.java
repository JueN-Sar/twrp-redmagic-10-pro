package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public interface ConnectionLifecycleCallback {
    void onBandwidthChanged();

    void onConnectionInitiated(String str, ConnectionInfo connectionInfo);

    void onConnectionResult(String str, ConnectionResolution connectionResolution);

    void onDisconnected(String str);
}
