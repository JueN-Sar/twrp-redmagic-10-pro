package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public final class zbuo {

    /* renamed from: a, reason: collision with root package name */
    static final Charset f12984a;

    /* renamed from: b, reason: collision with root package name */
    public static final byte[] f12985b;

    static {
        Charset.forName("US-ASCII");
        f12984a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        f12985b = bArr;
        ByteBuffer.wrap(bArr);
        int i2 = zbtg.f12950a;
        try {
            new zbte(bArr, 0, 0, false, null).c(0);
        } catch (zbuq e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    static int b(int i2, byte[] bArr, int i3, int i4) {
        for (int i5 = 0; i5 < i4; i5++) {
            i2 = (i2 * 31) + bArr[i5];
        }
        return i2;
    }

    static Object c(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("messageType");
    }

    static boolean d(zbvm zbvmVar) {
        if (zbvmVar instanceof zbsk) {
            throw null;
        }
        return false;
    }
}
