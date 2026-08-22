package cn.nubia.nbgame.sdk.util;

import java.util.Date;

/* loaded from: classes.dex */
public class ThreeDesUtil {
    public static byte[] a() {
        if (Constant.f8312a == null) {
            Constant.f8312a = b(new Date().getTime() + "");
        }
        return Constant.f8312a;
    }

    public static byte[] b(String str) {
        byte[] bArr = new byte[24];
        System.arraycopy(Md5.b((str + "test").getBytes()).getBytes(), 0, bArr, 0, 24);
        return bArr;
    }
}
