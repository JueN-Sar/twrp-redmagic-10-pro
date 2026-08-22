package com.google.mlkit.vision.common;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.mlkit.common.sdkinternal.MLTaskInput;
import com.google.mlkit.vision.common.internal.ImageConvertUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import javax.annotation.concurrent.Immutable;

@Immutable
/* loaded from: classes.dex */
public class InputImage implements MLTaskInput {

    /* renamed from: a, reason: collision with root package name */
    private volatile Bitmap f16050a;

    /* renamed from: b, reason: collision with root package name */
    private volatile ByteBuffer f16051b;

    /* renamed from: c, reason: collision with root package name */
    private volatile zzb f16052c;

    /* renamed from: d, reason: collision with root package name */
    private final int f16053d;

    /* renamed from: e, reason: collision with root package name */
    private final int f16054e;

    /* renamed from: f, reason: collision with root package name */
    private final int f16055f;

    /* renamed from: g, reason: collision with root package name */
    private final int f16056g;

    /* renamed from: h, reason: collision with root package name */
    private final Matrix f16057h;

    @Retention(RetentionPolicy.CLASS)
    public @interface ImageFormat {
    }

    private InputImage(Bitmap bitmap, int i2) {
        this.f16050a = (Bitmap) Preconditions.i(bitmap);
        this.f16053d = bitmap.getWidth();
        this.f16054e = bitmap.getHeight();
        m(i2);
        this.f16055f = i2;
        this.f16056g = -1;
        this.f16057h = null;
    }

    public static InputImage a(Bitmap bitmap, int i2) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(bitmap, i2);
        o(-1, 1, elapsedRealtime, bitmap.getHeight(), bitmap.getWidth(), bitmap.getAllocationByteCount(), i2);
        return inputImage;
    }

    public static InputImage b(ByteBuffer byteBuffer, int i2, int i3, int i4, int i5) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        InputImage inputImage = new InputImage(byteBuffer, i2, i3, i4, i5);
        o(i5, 3, elapsedRealtime, i3, i2, byteBuffer.limit(), i4);
        return inputImage;
    }

    public static InputImage c(Image image, int i2) {
        return n(image, i2, null);
    }

    private static int m(int i2) {
        boolean z = true;
        if (i2 != 0 && i2 != 90 && i2 != 180) {
            if (i2 == 270) {
                i2 = 270;
            } else {
                z = false;
            }
        }
        Preconditions.b(z, "Invalid rotation. Only 0, 90, 180, 270 are supported currently.");
        return i2;
    }

    private static InputImage n(Image image, int i2, Matrix matrix) {
        InputImage inputImage;
        int limit;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        Preconditions.j(image, "Please provide a valid image");
        m(i2);
        boolean z = true;
        if (image.getFormat() != 256 && image.getFormat() != 35) {
            z = false;
        }
        Preconditions.b(z, "Only JPEG and YUV_420_888 are supported now");
        Image.Plane[] planes = image.getPlanes();
        if (image.getFormat() == 256) {
            limit = image.getPlanes()[0].getBuffer().limit();
            inputImage = new InputImage(ImageConvertUtils.e().c(image, i2), 0);
        } else {
            for (Image.Plane plane : planes) {
                if (plane.getBuffer() != null) {
                    plane.getBuffer().rewind();
                }
            }
            inputImage = new InputImage(image, image.getWidth(), image.getHeight(), i2, matrix);
            limit = (image.getPlanes()[0].getBuffer().limit() * 3) / 2;
        }
        int i3 = limit;
        InputImage inputImage2 = inputImage;
        o(image.getFormat(), 5, elapsedRealtime, image.getHeight(), image.getWidth(), i3, i2);
        return inputImage2;
    }

    private static void o(int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        zzmu.a(zzms.b("vision-common"), i2, i3, j2, i4, i5, i6, i7);
    }

    public Bitmap d() {
        return this.f16050a;
    }

    public ByteBuffer e() {
        return this.f16051b;
    }

    public Matrix f() {
        return this.f16057h;
    }

    public int g() {
        return this.f16056g;
    }

    public int h() {
        return this.f16054e;
    }

    public Image i() {
        if (this.f16052c == null) {
            return null;
        }
        return this.f16052c.a();
    }

    public Image.Plane[] j() {
        if (this.f16052c == null) {
            return null;
        }
        return this.f16052c.b();
    }

    public int k() {
        return this.f16055f;
    }

    public int l() {
        return this.f16053d;
    }

    private InputImage(Image image, int i2, int i3, int i4, Matrix matrix) {
        Preconditions.i(image);
        this.f16052c = new zzb(image);
        this.f16053d = i2;
        this.f16054e = i3;
        m(i4);
        this.f16055f = i4;
        this.f16056g = 35;
        this.f16057h = matrix;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private InputImage(java.nio.ByteBuffer r5, int r6, int r7, int r8, int r9) {
        /*
            r4 = this;
            r4.<init>()
            r0 = 842094169(0x32315659, float:1.0322389E-8)
            r1 = 0
            r2 = 1
            if (r9 == r0) goto Lf
            r0 = 17
            if (r9 != r0) goto L11
            r9 = r0
        Lf:
            r0 = r2
            goto L12
        L11:
            r0 = r1
        L12:
            com.google.android.gms.common.internal.Preconditions.a(r0)
            java.lang.Object r0 = com.google.android.gms.common.internal.Preconditions.i(r5)
            java.nio.ByteBuffer r0 = (java.nio.ByteBuffer) r0
            r4.f16051b = r0
            int r0 = r5.limit()
            int r3 = r6 * r7
            if (r0 <= r3) goto L26
            r1 = r2
        L26:
            java.lang.String r0 = "Image dimension, ByteBuffer size and format don't match. Please check if the ByteBuffer is in the decalred format."
            com.google.android.gms.common.internal.Preconditions.b(r1, r0)
            r5.rewind()
            r4.f16053d = r6
            r4.f16054e = r7
            m(r8)
            r4.f16055f = r8
            r4.f16056g = r9
            r5 = 0
            r4.f16057h = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.vision.common.InputImage.<init>(java.nio.ByteBuffer, int, int, int, int):void");
    }
}
