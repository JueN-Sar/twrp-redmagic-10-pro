package cn.nubia.multisubscreen.data;

/* loaded from: classes.dex */
public class ConnectData extends BatchData {
    public static final String KEY_AUTHORIZED = "reply_authorized";
    public static final String KEY_CONNECT = "request_connect";
    public static final String KEY_SUSPEND = "suspend";

    public ConnectData setAuthorized(boolean z) {
        put(KEY_AUTHORIZED, z ? "0" : "1");
        return this;
    }

    public ConnectData setConnected(boolean z) {
        put(KEY_CONNECT, z ? "0" : "1");
        return this;
    }

    public ConnectData setSuspend(boolean z) {
        put(KEY_SUSPEND, z ? "0" : "1");
        return this;
    }
}
