package com.zte.distbus.basetransfer.servicemanager.model;

/* loaded from: classes.dex */
public class ServiceParam {
    private String deviceId;
    private String profile;
    private String uuid;

    public ServiceParam(String str, String str2) {
        this.uuid = str;
        this.profile = str2;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getProfile() {
        return this.profile;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }
}
