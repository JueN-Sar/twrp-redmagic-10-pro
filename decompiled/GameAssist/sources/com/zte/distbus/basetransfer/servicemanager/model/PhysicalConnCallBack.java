package com.zte.distbus.basetransfer.servicemanager.model;

import com.zte.distbus.basetransfer.ConnectionResolution;

/* loaded from: classes.dex */
public interface PhysicalConnCallBack {
    void onConnectionResult(String str, ConnectionResolution connectionResolution, WifiParam wifiParam, WifiParam wifiParam2);

    void onDisconnect(String str, int i2);
}
