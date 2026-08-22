package cn.nubia.componentsdk.pay.secret;

/* loaded from: classes.dex */
public class Hex {

    /* renamed from: a, reason: collision with root package name */
    private static char[] f6045a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i2 = 0; i2 < bArr.length; i2++) {
            sb.append(f6045a[(bArr[i2] & 240) >>> 4]);
            sb.append(f6045a[bArr[i2] & 15]);
        }
        return sb.toString();
    }

    public static byte[] b(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[str.length() / 2];
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            bArr[i3] = new Integer(Integer.parseInt("" + charArray[i2] + charArray[i2 + 1], 16) & 255).byteValue();
            i2 += 2;
            i3++;
        }
        return bArr;
    }
}
