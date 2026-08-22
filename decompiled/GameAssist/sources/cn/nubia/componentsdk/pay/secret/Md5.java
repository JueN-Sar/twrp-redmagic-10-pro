package cn.nubia.componentsdk.pay.secret;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class Md5 {

    /* renamed from: a, reason: collision with root package name */
    protected static char[] f6046a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr, 0, bArr.length);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String b(byte[] bArr) {
        byte[] a2 = a(bArr);
        if (a2 != null) {
            return Hex.a(a2);
        }
        return null;
    }
}
