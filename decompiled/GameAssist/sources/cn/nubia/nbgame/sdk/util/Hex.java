package cn.nubia.nbgame.sdk.util;

/* loaded from: classes.dex */
public class Hex {

    /* renamed from: a, reason: collision with root package name */
    private static char[] f8313a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append(f8313a[(bArr[i2] & 240) >>> 4]);
            sb.append(f8313a[bArr[i2] & 15]);
        }
        return sb.toString();
    }
}
