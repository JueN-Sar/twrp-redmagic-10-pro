package com.zte.distbus.basetransfer.model;

/* loaded from: classes.dex */
public class ConnectionParam {
    private String fileTransferProfile;
    private String msgTransferProfile;
    private String streamTransferProfile;
    private int type;

    public ConnectionParam() {
    }

    public String getFileTransferProfile() {
        return this.fileTransferProfile;
    }

    public String getMsgTransferProfile() {
        return this.msgTransferProfile;
    }

    public String getStreamTransferProfile() {
        return this.streamTransferProfile;
    }

    public int getType() {
        return this.type;
    }

    public void setFileTransferProfile(String str) {
        this.fileTransferProfile = str;
    }

    public void setMsgTransferProfile(String str) {
        this.msgTransferProfile = str;
    }

    public void setStreamTransferProfile(String str) {
        this.streamTransferProfile = str;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public ConnectionParam(int i2) {
        this.type = i2;
    }
}
