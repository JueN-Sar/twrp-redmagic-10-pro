package com.zte.distbus.basetransfer.device.shake;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class Shake128 {
    private static final long[] RC = {1, 32898, -9223372036854742902L, -9223372034707259392L, 32907, 2147483649L, -9223372034707259263L, -9223372036854743031L, 138, 136, 2147516425L, 2147483658L, 2147516555L, -9223372036854775669L, -9223372036854742903L, -9223372036854743037L, -9223372036854743038L, -9223372036854775680L, 32778, -9223372034707292150L, -9223372034707259263L, -9223372036854742912L, 2147483649L, -9223372034707259384L};
    private static final int[][] R = {new int[]{0, 36, 3, 41, 18}, new int[]{1, 44, 10, 45, 2}, new int[]{62, 6, 43, 15, 61}, new int[]{28, 55, 25, 21, 56}, new int[]{27, 20, 39, 8, 14}};

    private static void f(long[][] jArr, long[][] jArr2, long[] jArr3, long[] jArr4) {
        for (int i2 = 0; i2 < 24; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                long[] jArr5 = jArr[i3];
                jArr3[i3] = jArr5[4] ^ (((jArr5[0] ^ jArr5[1]) ^ jArr5[2]) ^ jArr5[3]);
            }
            int i4 = 0;
            while (i4 < 5) {
                int i5 = i4 + 1;
                jArr4[i4] = jArr3[(i4 + 4) % 5] ^ rot(jArr3[i5 % 5], 1);
                i4 = i5;
            }
            for (int i6 = 0; i6 < 5; i6++) {
                for (int i7 = 0; i7 < 5; i7++) {
                    long[] jArr6 = jArr[i7];
                    jArr6[i6] = jArr6[i6] ^ jArr4[i7];
                }
            }
            for (int i8 = 0; i8 < 5; i8++) {
                for (int i9 = 0; i9 < 5; i9++) {
                    jArr2[i8][((i9 * 2) + (i8 * 3)) % 5] = rot(jArr[i9][i8], R[i9][i8]);
                }
            }
            for (int i10 = 0; i10 < 5; i10++) {
                int i11 = 0;
                while (i11 < 5) {
                    int i12 = i11 + 1;
                    jArr[i11][i10] = jArr2[i11][i10] ^ ((~jArr2[i12 % 5][i10]) & jArr2[(i11 + 2) % 5][i10]);
                    i11 = i12;
                }
            }
            long[] jArr7 = jArr[0];
            jArr7[0] = jArr7[0] ^ RC[i2];
        }
    }

    public static byte[] getHash(int i2, byte[] bArr) {
        return getHash(i2, bArr, 0, bArr.length);
    }

    private static long getWord(byte[] bArr, int i2) {
        return (bArr[i2 + 7] << 56) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16) | ((bArr[i2 + 3] & 255) << 24) | ((bArr[i2 + 4] & 255) << 32) | ((bArr[i2 + 5] & 255) << 40) | ((bArr[i2 + 6] & 255) << 48);
    }

    private static void hashBlock(long[] jArr, long[][] jArr2, long[][] jArr3, long[] jArr4, long[] jArr5) {
        for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                int i4 = (i2 * 5) + i3;
                if (i4 == 21) {
                    break;
                }
                long[] jArr6 = jArr2[i3];
                jArr6[i2] = jArr6[i2] ^ jArr[i4];
            }
        }
        f(jArr2, jArr3, jArr4, jArr5);
    }

    private static long rot(long j2, int i2) {
        return (j2 >>> (64 - i2)) | (j2 << i2);
    }

    public static byte[] getHash(int i2, byte[] bArr, int i3, int i4) {
        int i5;
        if (i2 < 2) {
            throw new UnsupportedOperationException("too small length of the hash, require hashByteLength >= 2");
        }
        if (i2 > 8192) {
            throw new UnsupportedOperationException("too big length of the hash, require hashByteLength <= 8192");
        }
        int i6 = 5;
        int i7 = 0;
        Class cls = Long.TYPE;
        long[][] jArr = (long[][]) Array.newInstance((Class<?>) cls, 5, 5);
        long[][] jArr2 = (long[][]) Array.newInstance((Class<?>) cls, 5, 5);
        long[] jArr3 = new long[5];
        long[] jArr4 = new long[5];
        long[] jArr5 = new long[21];
        int i8 = i3 + i4;
        int i9 = i8 - 7;
        int i10 = i3;
        loop0: while (true) {
            i5 = 0;
            while (i10 < i9) {
                int i11 = i5 + 1;
                jArr5[i5] = getWord(bArr, i10);
                i10 += 8;
                if (i11 == 21) {
                    break;
                }
                i5 = i11;
            }
            hashBlock(jArr5, jArr, jArr2, jArr3, jArr4);
        }
        byte[] bArr2 = new byte[8];
        int i12 = 0;
        while (i10 < i8) {
            bArr2[i12] = bArr[i10];
            i12++;
            i10++;
        }
        bArr2[i12] = 31;
        if (i5 == 20) {
            bArr2[7] = (byte) (bArr2[7] | 128);
        } else {
            int i13 = i5 + 1;
            jArr5[i5] = getWord(bArr2, 0);
            while (true) {
                i5 = i13;
                if (i5 >= 20) {
                    break;
                }
                i13 = i5 + 1;
                jArr5[i5] = 0;
            }
            bArr2 = new byte[8];
            bArr2[7] = (byte) (bArr2[7] | 128);
        }
        jArr5[i5] = getWord(bArr2, 0);
        hashBlock(jArr5, jArr, jArr2, jArr3, jArr4);
        byte[] bArr3 = new byte[i2];
        int i14 = 0;
        while (true) {
            int i15 = i7;
            while (i15 < i6) {
                int i16 = i7;
                while (i16 < i6) {
                    if ((i15 * 5) + i16 < 21) {
                        long j2 = jArr[i16][i15];
                        int i17 = i7;
                        while (i17 < 8) {
                            int i18 = i14 + 1;
                            bArr3[i14] = (byte) j2;
                            if (i18 == i2) {
                                return bArr3;
                            }
                            j2 >>= 8;
                            i17++;
                            i14 = i18;
                        }
                    }
                    i16++;
                    i6 = 5;
                    i7 = 0;
                }
                i15++;
                i6 = 5;
                i7 = 0;
            }
            f(jArr, jArr2, jArr3, jArr4);
            i6 = 5;
            i7 = 0;
        }
    }
}
