package cn.nubia.componentsdk.pay;

import android.content.Context;
import cn.nubia.componentsdk.pay.secret.Hex;
import cn.nubia.componentsdk.pay.secret.RSA;
import cn.nubia.componentsdk.pay.secret.ThreeDes;
import cn.nubia.componentsdk.until.PayLog;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes.dex */
public class Util {

    /* renamed from: a, reason: collision with root package name */
    private static HashMap f6027a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private static HashMap f6028b = new HashMap();

    public static String a(String str) {
        byte[] b2 = Hex.b(str);
        for (int i2 = 0; i2 < b2.length; i2++) {
            b2[i2] = (byte) (~b2[i2]);
        }
        return new String(b2);
    }

    public static byte[] b() {
        if (Constant.f5931c == null) {
            Constant.f5931c = ThreeDes.d(new Date().getTime() + "");
        }
        PayLog.a("desKey", Constant.f5931c + "");
        return Constant.f5931c;
    }

    public static int c(Context context, String str, String str2) {
        if (context == null) {
            return -1;
        }
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    public static String d() {
        return Constant.f5933e;
    }

    public static byte[] e(byte[] bArr, Context context) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return RSA.a(f(context), bArr);
    }

    public static RSAPublicKey f(Context context) {
        return RSA.c(Constant.f5933e, "10001");
    }
}
