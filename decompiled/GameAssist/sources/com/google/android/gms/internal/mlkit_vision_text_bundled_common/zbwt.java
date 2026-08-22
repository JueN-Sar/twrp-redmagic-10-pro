package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

/* loaded from: classes.dex */
final class zbwt {
    static /* bridge */ /* synthetic */ void a(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i2) {
        if (e(b3) || (((b2 << 28) + (b3 + 112)) >> 30) != 0 || e(b4) || e(b5)) {
            throw new zbuq("Protocol message had invalid UTF-8.");
        }
        int i3 = ((b2 & 7) << 18) | ((b3 & 63) << 12) | ((b4 & 63) << 6) | (b5 & 63);
        cArr[i2] = (char) ((i3 >>> 10) + 55232);
        cArr[i2 + 1] = (char) ((i3 & 1023) + 56320);
    }

    static /* bridge */ /* synthetic */ void b(byte b2, byte b3, byte b4, char[] cArr, int i2) {
        if (!e(b3)) {
            if (b2 == -32) {
                if (b3 >= -96) {
                    b2 = -32;
                }
            }
            if (b2 == -19) {
                if (b3 < -96) {
                    b2 = -19;
                }
            }
            if (!e(b4)) {
                cArr[i2] = (char) (((b2 & 15) << 12) | ((b3 & 63) << 6) | (b4 & 63));
                return;
            }
        }
        throw new zbuq("Protocol message had invalid UTF-8.");
    }

    static /* bridge */ /* synthetic */ void c(byte b2, byte b3, char[] cArr, int i2) {
        if (b2 < -62 || e(b3)) {
            throw new zbuq("Protocol message had invalid UTF-8.");
        }
        cArr[i2] = (char) (((b2 & 31) << 6) | (b3 & 63));
    }

    static /* bridge */ /* synthetic */ boolean d(byte b2) {
        return b2 >= 0;
    }

    private static boolean e(byte b2) {
        return b2 > -65;
    }
}
