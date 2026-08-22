package com.zte.distbus.basetransfer;

import com.zte.distbus.basetransfer.servicemanager.model.WifiParam;

/* loaded from: classes.dex */
public final class ConnectionResolution {
    private WifiParam localWifiParam;
    private WifiParam remoteWifiParam;
    private Status status;

    public ConnectionResolution(Status status) {
        this(status, null, null);
    }

    public WifiParam getLocalWifiParam() {
        return this.localWifiParam;
    }

    public WifiParam getRemoteWifiParam() {
        return this.remoteWifiParam;
    }

    public Status getStatus() {
        return this.status;
    }

    public ConnectionResolution(Status status, WifiParam wifiParam, WifiParam wifiParam2) {
        this.status = status;
        this.localWifiParam = wifiParam;
        this.remoteWifiParam = wifiParam2;
    }
}
