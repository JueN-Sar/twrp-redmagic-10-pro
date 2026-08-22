package com.zte.distbus.basetransfer.servicemanager.model;

/* loaded from: classes.dex */
public class CallBackResult {
    private String deviceId;
    private String profile;
    private Boolean result;
    private String uuid;

    public CallBackResult(String str, String str2, Boolean bool) {
        this.uuid = str;
        this.profile = str2;
        this.result = bool;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getProfile() {
        return this.profile;
    }

    public Boolean getResult() {
        return this.result;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }
}
