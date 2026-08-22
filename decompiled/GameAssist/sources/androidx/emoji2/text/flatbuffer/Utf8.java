package androidx.emoji2.text.flatbuffer;

/* loaded from: classes.dex */
public abstract class Utf8 {

    /* renamed from: a, reason: collision with root package name */
    private static Utf8 f3838a;

    static class DecodeUtil {
        static void a(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i2) {
            if (f(b3) || (((b2 << 28) + (b3 + 112)) >> 30) != 0 || f(b4) || f(b5)) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            int k2 = ((b2 & 7) << 18) | (k(b3) << 12) | (k(b4) << 6) | k(b5);
            cArr[i2] = e(k2);
            cArr[i2 + 1] = j(k2);
        }

        static void b(byte b2, char[] cArr, int i2) {
            cArr[i2] = (char) b2;
        }

        static void c(byte b2, byte b3, byte b4, char[] cArr, int i2) {
            if (f(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || f(b4)))) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            cArr[i2] = (char) (((b2 & 15) << 12) | (k(b3) << 6) | k(b4));
        }

        static void d(byte b2, byte b3, char[] cArr, int i2) {
            if (b2 < -62) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
            }
            if (f(b3)) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
            cArr[i2] = (char) (((b2 & 31) << 6) | k(b3));
        }

        private static char e(int i2) {
            return (char) ((i2 >>> 10) + 55232);
        }

        private static boolean f(byte b2) {
            return b2 > -65;
        }

        static boolean g(byte b2) {
            return b2 >= 0;
        }

        static boolean h(byte b2) {
            return b2 < -16;
        }

        static boolean i(byte b2) {
            return b2 < -32;
        }

        private static char j(int i2) {
            return (char) ((i2 & 1023) + 56320);
        }

        private static int k(byte b2) {
            return b2 & 63;
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i2, int i3) {
            super("Unpaired surrogate at index " + i2 + " of " + i3);
        }
    }

    public static Utf8 a() {
        if (f3838a == null) {
            f3838a = new Utf8Safe();
        }
        return f3838a;
    }
}
