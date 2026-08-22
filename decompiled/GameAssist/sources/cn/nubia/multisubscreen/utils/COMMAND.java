package cn.nubia.multisubscreen.utils;

/* loaded from: classes.dex */
public enum COMMAND {
    NOTIFY_NUMERICAL,
    NOTIFY_DATA,
    NOTIFY_KEYS,
    NOTIFY_STATUS,
    SET_DATA,
    GET_NUMERICAL,
    GET_DATA,
    MODIFY_DATA,
    CONNECT,
    UNKNOWN;

    public static COMMAND d(String str) {
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return UNKNOWN;
        }
    }
}
