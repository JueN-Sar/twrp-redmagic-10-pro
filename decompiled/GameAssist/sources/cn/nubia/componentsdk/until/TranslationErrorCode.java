package cn.nubia.componentsdk.until;

/* loaded from: classes.dex */
public class TranslationErrorCode {
    public static int a(int i2) {
        PayLog.a("TranslationErrorCode", "origin errorCode:" + i2);
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 104) {
            return 10001;
        }
        if (i2 == 110 || i2 == 120 || i2 == 122) {
            return 10002;
        }
        if (i2 == 106) {
            return 10003;
        }
        if (i2 == 41 || i2 == 51) {
            return 10004;
        }
        if (i2 == -104 || i2 == -108 || i2 == -106 || i2 == -110) {
            return 10005;
        }
        if (i2 == -102 || i2 == 112) {
            return 10006;
        }
        if (i2 == 126) {
            return 10007;
        }
        if (i2 == 127) {
            return 10008;
        }
        return i2 == 128 ? 10009 : -1;
    }
}
