package com.zte.distbus.basetransfer;

/* loaded from: classes.dex */
public class Status {
    public static final int BLE_CONNECTED = 102;
    public static final int BLE_CONNECTING = 101;
    public static final int BLE_DISCONNECTED = 100;
    public static final int BLE_DISCONNECTING = 103;
    public static final int BLE_ERROR = 104;
    public static final int CANCELLED = 2;
    public static final int ERROR = 1;
    public static final int ERROR_STREAM_LOCAL_FAILED = 20;
    public static final int ERROR_STREAM_REMOTE_FAILED = 21;
    public static final int ERROR_WIFI_LOCAL_DISABLE = 10;
    public static final int ERROR_WIFI_P2P_LOCAL_BUSY = 14;
    public static final int ERROR_WIFI_P2P_LOCAL_FAILED = 12;
    public static final int ERROR_WIFI_P2P_REMOTE_BUSY = 15;
    public static final int ERROR_WIFI_P2P_REMOTE_FAILED = 13;
    public static final int ERROR_WIFI_REMOTE_DISABLE = 11;
    public static final int IN_PROCESS = 3;
    public static final int RECEIVER_CANCELLED = 6;
    public static final int REJECT = 4;
    public static final int SENDER_CANCELLED = 5;
    public static final int SUCCESS = 0;
    private int code;
    private String msg;

    public Status(int i2) {
        this.code = i2;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public Status(int i2, String str) {
        this.code = i2;
        this.msg = str;
    }
}
