package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.Utf8;
import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class Utf8Safe extends Utf8 {

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i2, int i3) {
            super("Unpaired surrogate at index " + i2 + " of " + i3);
        }
    }

    public static String b(byte[] bArr, int i2, int i3) {
        if ((i2 | i3 | ((bArr.length - i2) - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        int i4 = i2 + i3;
        char[] cArr = new char[i3];
        int i5 = 0;
        while (i2 < i4) {
            byte b2 = bArr[i2];
            if (!Utf8.DecodeUtil.g(b2)) {
                break;
            }
            i2++;
            Utf8.DecodeUtil.b(b2, cArr, i5);
            i5++;
        }
        int i6 = i5;
        while (i2 < i4) {
            int i7 = i2 + 1;
            byte b3 = bArr[i2];
            if (Utf8.DecodeUtil.g(b3)) {
                int i8 = i6 + 1;
                Utf8.DecodeUtil.b(b3, cArr, i6);
                while (i7 < i4) {
                    byte b4 = bArr[i7];
                    if (!Utf8.DecodeUtil.g(b4)) {
                        break;
                    }
                    i7++;
                    Utf8.DecodeUtil.b(b4, cArr, i8);
                    i8++;
                }
                i6 = i8;
                i2 = i7;
            } else if (Utf8.DecodeUtil.i(b3)) {
                if (i7 >= i4) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                i2 += 2;
                Utf8.DecodeUtil.d(b3, bArr[i7], cArr, i6);
                i6++;
            } else if (Utf8.DecodeUtil.h(b3)) {
                if (i7 >= i4 - 1) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                int i9 = i2 + 2;
                i2 += 3;
                Utf8.DecodeUtil.c(b3, bArr[i7], bArr[i9], cArr, i6);
                i6++;
            } else {
                if (i7 >= i4 - 2) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                byte b5 = bArr[i7];
                int i10 = i2 + 3;
                byte b6 = bArr[i2 + 2];
                i2 += 4;
                Utf8.DecodeUtil.a(b3, b5, b6, bArr[i10], cArr, i6);
                i6 += 2;
            }
        }
        return new String(cArr, 0, i6);
    }

    public static String c(ByteBuffer byteBuffer, int i2, int i3) {
        if ((i2 | i3 | ((byteBuffer.limit() - i2) - i3)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i2), Integer.valueOf(i3)));
        }
        int i4 = i2 + i3;
        char[] cArr = new char[i3];
        int i5 = 0;
        while (i2 < i4) {
            byte b2 = byteBuffer.get(i2);
            if (!Utf8.DecodeUtil.g(b2)) {
                break;
            }
            i2++;
            Utf8.DecodeUtil.b(b2, cArr, i5);
            i5++;
        }
        int i6 = i5;
        while (i2 < i4) {
            int i7 = i2 + 1;
            byte b3 = byteBuffer.get(i2);
            if (Utf8.DecodeUtil.g(b3)) {
                int i8 = i6 + 1;
                Utf8.DecodeUtil.b(b3, cArr, i6);
                while (i7 < i4) {
                    byte b4 = byteBuffer.get(i7);
                    if (!Utf8.DecodeUtil.g(b4)) {
                        break;
                    }
                    i7++;
                    Utf8.DecodeUtil.b(b4, cArr, i8);
                    i8++;
                }
                i6 = i8;
                i2 = i7;
            } else if (Utf8.DecodeUtil.i(b3)) {
                if (i7 >= i4) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                i2 += 2;
                Utf8.DecodeUtil.d(b3, byteBuffer.get(i7), cArr, i6);
                i6++;
            } else if (Utf8.DecodeUtil.h(b3)) {
                if (i7 >= i4 - 1) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                int i9 = i2 + 2;
                i2 += 3;
                Utf8.DecodeUtil.c(b3, byteBuffer.get(i7), byteBuffer.get(i9), cArr, i6);
                i6++;
            } else {
                if (i7 >= i4 - 2) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                }
                byte b5 = byteBuffer.get(i7);
                int i10 = i2 + 3;
                byte b6 = byteBuffer.get(i2 + 2);
                i2 += 4;
                Utf8.DecodeUtil.a(b3, b5, b6, byteBuffer.get(i10), cArr, i6);
                i6 += 2;
            }
        }
        return new String(cArr, 0, i6);
    }
}
