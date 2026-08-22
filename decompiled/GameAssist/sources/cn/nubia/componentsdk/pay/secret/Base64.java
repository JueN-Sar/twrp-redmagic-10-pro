package cn.nubia.componentsdk.pay.secret;

/* loaded from: classes.dex */
public class Base64 {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f6043a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: b, reason: collision with root package name */
    private static final byte[] f6044b = new byte[128];

    static {
        for (int i2 = 0; i2 < 128; i2++) {
            f6044b[i2] = -1;
        }
        for (int i3 = 65; i3 <= 90; i3++) {
            f6044b[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 97; i4 <= 122; i4++) {
            f6044b[i4] = (byte) (i4 - 71);
        }
        for (int i5 = 48; i5 <= 57; i5++) {
            f6044b[i5] = (byte) (i5 + 4);
        }
        byte[] bArr = f6044b;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    public static byte[] a(byte[] bArr) {
        byte[] b2 = b(bArr);
        byte[] bArr2 = b2[b2.length + (-2)] == 61 ? new byte[(((b2.length / 4) - 1) * 3) + 1] : b2[b2.length + (-1)] == 61 ? new byte[(((b2.length / 4) - 1) * 3) + 2] : new byte[(b2.length / 4) * 3];
        int i2 = 0;
        int i3 = 0;
        while (i2 < b2.length - 4) {
            byte[] bArr3 = f6044b;
            byte b3 = bArr3[b2[i2]];
            byte b4 = bArr3[b2[i2 + 1]];
            byte b5 = bArr3[b2[i2 + 2]];
            byte b6 = bArr3[b2[i2 + 3]];
            bArr2[i3] = (byte) ((b3 << 2) | (b4 >> 4));
            bArr2[i3 + 1] = (byte) ((b4 << 4) | (b5 >> 2));
            bArr2[i3 + 2] = (byte) (b6 | (b5 << 6));
            i2 += 4;
            i3 += 3;
        }
        if (b2[b2.length - 2] == 61) {
            byte[] bArr4 = f6044b;
            bArr2[bArr2.length - 1] = (byte) ((bArr4[b2[b2.length - 3]] >> 4) | (bArr4[b2[b2.length - 4]] << 2));
        } else if (b2[b2.length - 1] == 61) {
            byte[] bArr5 = f6044b;
            byte b7 = bArr5[b2[b2.length - 4]];
            byte b8 = bArr5[b2[b2.length - 3]];
            byte b9 = bArr5[b2[b2.length - 2]];
            bArr2[bArr2.length - 2] = (byte) ((b7 << 2) | (b8 >> 4));
            bArr2[bArr2.length - 1] = (byte) ((b9 >> 2) | (b8 << 4));
        } else {
            byte[] bArr6 = f6044b;
            byte b10 = bArr6[b2[b2.length - 4]];
            byte b11 = bArr6[b2[b2.length - 3]];
            byte b12 = bArr6[b2[b2.length - 2]];
            byte b13 = bArr6[b2[b2.length - 1]];
            bArr2[bArr2.length - 3] = (byte) ((b10 << 2) | (b11 >> 4));
            bArr2[bArr2.length - 2] = (byte) ((b11 << 4) | (b12 >> 2));
            bArr2[bArr2.length - 1] = (byte) (b13 | (b12 << 6));
        }
        return bArr2;
    }

    private static byte[] b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            if (d(bArr[i3])) {
                bArr2[i2] = bArr[i3];
                i2++;
            }
        }
        byte[] bArr3 = new byte[i2];
        System.arraycopy(bArr2, 0, bArr3, 0, i2);
        return bArr3;
    }

    public static byte[] c(byte[] bArr) {
        int length = bArr.length % 3;
        byte[] bArr2 = length == 0 ? new byte[(bArr.length * 4) / 3] : new byte[((bArr.length / 3) + 1) * 4];
        int length2 = bArr.length - length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length2) {
            int i4 = bArr[i2] & 255;
            int i5 = bArr[i2 + 1] & 255;
            byte b2 = bArr[i2 + 2];
            byte[] bArr3 = f6043a;
            bArr2[i3] = bArr3[(i4 >>> 2) & 63];
            bArr2[i3 + 1] = bArr3[((i4 << 4) | (i5 >>> 4)) & 63];
            bArr2[i3 + 2] = bArr3[((i5 << 2) | ((b2 & 255) >>> 6)) & 63];
            bArr2[i3 + 3] = bArr3[b2 & 63];
            i2 += 3;
            i3 += 4;
        }
        if (length == 1) {
            int i6 = bArr[bArr.length - 1] & 255;
            int length3 = bArr2.length - 4;
            byte[] bArr4 = f6043a;
            bArr2[length3] = bArr4[(i6 >>> 2) & 63];
            bArr2[bArr2.length - 3] = bArr4[(i6 << 4) & 63];
            bArr2[bArr2.length - 2] = 61;
            bArr2[bArr2.length - 1] = 61;
        } else if (length == 2) {
            int i7 = bArr[bArr.length - 2] & 255;
            int i8 = bArr[bArr.length - 1] & 255;
            int length4 = bArr2.length - 4;
            byte[] bArr5 = f6043a;
            bArr2[length4] = bArr5[(i7 >>> 2) & 63];
            bArr2[bArr2.length - 3] = bArr5[((i7 << 4) | (i8 >>> 4)) & 63];
            bArr2[bArr2.length - 2] = bArr5[(i8 << 2) & 63];
            bArr2[bArr2.length - 1] = 61;
        }
        return bArr2;
    }

    private static boolean d(byte b2) {
        if (b2 == 61) {
            return true;
        }
        return b2 >= 0 && b2 < 128 && f6044b[b2] != -1;
    }
}
