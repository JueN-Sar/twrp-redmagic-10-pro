package cn.nubia.multisubscreen.utils;

/* loaded from: classes.dex */
public enum ACTION {
    REQUEST_FROM_PRI,
    RESPONSE_FROM_PRI,
    REQUEST_FROM_SEC,
    RESPONSE_FROM_SEC,
    UNKNOWN;

    public static ACTION d(String str) {
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return UNKNOWN;
        }
    }
}
