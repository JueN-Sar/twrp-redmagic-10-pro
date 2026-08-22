package cn.nubia.componentsdk.pay.secret;

/* loaded from: classes.dex */
public class Base64Utils {
    public static byte[] a(String str) {
        return Base64.a(str.getBytes());
    }

    public static String b(byte[] bArr) {
        return new String(Base64.c(bArr));
    }
}
