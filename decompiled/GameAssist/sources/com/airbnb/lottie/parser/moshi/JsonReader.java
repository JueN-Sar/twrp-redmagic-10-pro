package com.airbnb.lottie.parser.moshi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Arrays;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

/* loaded from: classes.dex */
public abstract class JsonReader implements Closeable {

    /* renamed from: m, reason: collision with root package name */
    private static final String[] f9863m = new String[128];

    /* renamed from: c, reason: collision with root package name */
    int f9864c;

    /* renamed from: h, reason: collision with root package name */
    int[] f9865h = new int[32];

    /* renamed from: i, reason: collision with root package name */
    String[] f9866i = new String[32];

    /* renamed from: j, reason: collision with root package name */
    int[] f9867j = new int[32];

    /* renamed from: k, reason: collision with root package name */
    boolean f9868k;

    /* renamed from: l, reason: collision with root package name */
    boolean f9869l;

    public static final class Options {

        /* renamed from: a, reason: collision with root package name */
        final String[] f9870a;

        /* renamed from: b, reason: collision with root package name */
        final okio.Options f9871b;

        private Options(String[] strArr, okio.Options options) {
            this.f9870a = strArr;
            this.f9871b = options;
        }

        public static Options a(String... strArr) {
            try {
                ByteString[] byteStringArr = new ByteString[strArr.length];
                Buffer buffer = new Buffer();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    JsonReader.I(buffer, strArr[i2]);
                    buffer.readByte();
                    byteStringArr[i2] = buffer.C();
                }
                return new Options((String[]) strArr.clone(), okio.Options.g(byteStringArr));
            } catch (IOException e2) {
                throw new AssertionError(e2);
            }
        }
    }

    public enum Token {
        BEGIN_ARRAY,
        END_ARRAY,
        BEGIN_OBJECT,
        END_OBJECT,
        NAME,
        STRING,
        NUMBER,
        BOOLEAN,
        NULL,
        END_DOCUMENT
    }

    static {
        for (int i2 = 0; i2 <= 31; i2++) {
            f9863m[i2] = String.format("\\u%04x", Integer.valueOf(i2));
        }
        String[] strArr = f9863m;
        strArr[34] = "\\\"";
        strArr[92] = "\\\\";
        strArr[9] = "\\t";
        strArr[8] = "\\b";
        strArr[10] = "\\n";
        strArr[13] = "\\r";
        strArr[12] = "\\f";
    }

    JsonReader() {
    }

    public static JsonReader B(BufferedSource bufferedSource) {
        return new JsonUtf8Reader(bufferedSource);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void I(okio.BufferedSink r7, java.lang.String r8) {
        /*
            java.lang.String[] r0 = com.airbnb.lottie.parser.moshi.JsonReader.f9863m
            r1 = 34
            r7.writeByte(r1)
            int r2 = r8.length()
            r3 = 0
            r4 = r3
        Ld:
            if (r3 >= r2) goto L36
            char r5 = r8.charAt(r3)
            r6 = 128(0x80, float:1.8E-43)
            if (r5 >= r6) goto L1c
            r5 = r0[r5]
            if (r5 != 0) goto L29
            goto L33
        L1c:
            r6 = 8232(0x2028, float:1.1535E-41)
            if (r5 != r6) goto L23
            java.lang.String r5 = "\\u2028"
            goto L29
        L23:
            r6 = 8233(0x2029, float:1.1537E-41)
            if (r5 != r6) goto L33
            java.lang.String r5 = "\\u2029"
        L29:
            if (r4 >= r3) goto L2e
            r7.y(r8, r4, r3)
        L2e:
            r7.v(r5)
            int r4 = r3 + 1
        L33:
            int r3 = r3 + 1
            goto Ld
        L36:
            if (r4 >= r2) goto L3b
            r7.y(r8, r4, r2)
        L3b:
            r7.writeByte(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonReader.I(okio.BufferedSink, java.lang.String):void");
    }

    public abstract String A();

    public abstract Token C();

    final void D(int i2) {
        int i3 = this.f9864c;
        int[] iArr = this.f9865h;
        if (i3 == iArr.length) {
            if (i3 == 256) {
                throw new JsonDataException("Nesting too deep at " + i());
            }
            this.f9865h = Arrays.copyOf(iArr, iArr.length * 2);
            String[] strArr = this.f9866i;
            this.f9866i = (String[]) Arrays.copyOf(strArr, strArr.length * 2);
            int[] iArr2 = this.f9867j;
            this.f9867j = Arrays.copyOf(iArr2, iArr2.length * 2);
        }
        int[] iArr3 = this.f9865h;
        int i4 = this.f9864c;
        this.f9864c = i4 + 1;
        iArr3[i4] = i2;
    }

    public abstract int E(Options options);

    public abstract void F();

    public abstract void G();

    final JsonEncodingException L(String str) {
        throw new JsonEncodingException(str + " at path " + i());
    }

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void h();

    public final String i() {
        return JsonScope.a(this.f9864c, this.f9865h, this.f9866i, this.f9867j);
    }

    public abstract boolean j();

    public abstract boolean k();

    public abstract double p();

    public abstract int s();

    public abstract String t();
}
