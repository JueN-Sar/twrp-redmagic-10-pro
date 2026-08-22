package cn.nubia.componentsdk.pay.secret;

/* loaded from: classes.dex */
public class FormatTransfer {
    public static int a(byte[] bArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 3; i3++) {
            byte b2 = bArr[3 - i3];
            if (b2 < 0) {
                i2 += 256;
            }
            i2 = (i2 + b2) * 256;
        }
        byte b3 = bArr[0];
        if (b3 < 0) {
            i2 += 256;
        }
        return i2 + b3;
    }

    public static short b(byte[] bArr) {
        int i2 = bArr[1];
        if (i2 < 0) {
            i2 += 256;
        }
        int i3 = i2 * 256;
        int i4 = bArr[0];
        if (i4 < 0) {
            i3 += 256;
        }
        return (short) (i3 + i4);
    }

    public static byte[] c(int i2) {
        return new byte[]{(byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) ((i2 >> 16) & 255), (byte) ((i2 >> 24) & 255)};
    }

    public static byte[] d(short s2) {
        return new byte[]{(byte) (s2 & 255), (byte) ((s2 >> 8) & 255)};
    }
}
