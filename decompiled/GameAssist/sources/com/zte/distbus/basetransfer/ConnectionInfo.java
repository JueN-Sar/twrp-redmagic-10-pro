package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public final class ConnectionInfo {
    private String authCode;
    private String endpointName;
    private boolean isRemoteConnect;
    private String param;

    public ConnectionInfo(String str, String str2, boolean z, String str3) {
        this.endpointName = str;
        this.authCode = str2;
        this.isRemoteConnect = z;
        this.param = str3;
    }

    public String getAuthCode() {
        return this.authCode;
    }

    public String getEndpointName() {
        return this.endpointName;
    }

    public String getParam() {
        return this.param;
    }

    public boolean isRemoteConnect() {
        return this.isRemoteConnect;
    }
}
