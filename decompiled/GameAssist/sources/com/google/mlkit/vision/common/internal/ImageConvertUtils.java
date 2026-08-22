package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.media.Image;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@KeepForSdk
/* loaded from: classes.dex */
public class ImageConvertUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final ImageConvertUtils f16059a = new ImageConvertUtils();

    private ImageConvertUtils() {
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return byteBuffer;
        }
        byteBuffer.rewind();
        byte[] bArr = new byte[byteBuffer.limit()];
        byteBuffer.get(bArr);
        return ByteBuffer.wrap(bArr);
    }

    public static ImageConvertUtils e() {
        return f16059a;
    }

    public static Bitmap h(ByteBuffer byteBuffer, int i2, int i3, int i4) {
        byte[] k2 = k(i(byteBuffer, true).array(), i2, i3);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(k2, 0, k2.length);
        return j(decodeByteArray, i4, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    public static ByteBuffer i(ByteBuffer byteBuffer, boolean z) {
        int i2;
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        int i3 = limit / 6;
        ByteBuffer allocate = z ? ByteBuffer.allocate(limit) : ByteBuffer.allocateDirect(limit);
        int i4 = 0;
        while (true) {
            i2 = i3 * 4;
            if (i4 >= i2) {
                break;
            }
            allocate.put(i4, byteBuffer.get(i4));
            i4++;
        }
        for (int i5 = 0; i5 < i3 + i3; i5++) {
            allocate.put(i2 + i5, byteBuffer.get(((i5 % 2) * i3) + i2 + (i5 / 2)));
        }
        return allocate;
    }

    public static Bitmap j(Bitmap bitmap, int i2, int i3, int i4) {
        if (i2 == 0) {
            return Bitmap.createBitmap(bitmap, 0, 0, i3, i4);
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(i2);
        return Bitmap.createBitmap(bitmap, 0, 0, i3, i4, matrix, true);
    }

    private static byte[] k(byte[] bArr, int i2, int i3) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i2, i3, null);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                yuvImage.compressToJpeg(new Rect(0, 0, i2, i3), 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable th2) {
                    try {
                        Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                    } catch (Exception unused) {
                    }
                }
                throw th;
            }
        } catch (IOException e2) {
            Log.w("ImageConvertUtils", "Error closing ByteArrayOutputStream");
            throw new MlKitException("Image conversion error from NV21 format", 13, e2);
        }
    }

    private static final void l(Image.Plane plane, int i2, int i3, byte[] bArr, int i4, int i5) {
        ByteBuffer buffer = plane.getBuffer();
        buffer.rewind();
        int limit = ((buffer.limit() + plane.getRowStride()) - 1) / plane.getRowStride();
        if (limit == 0) {
            return;
        }
        int i6 = i2 / (i3 / limit);
        int i7 = 0;
        for (int i8 = 0; i8 < limit; i8++) {
            int i9 = i7;
            for (int i10 = 0; i10 < i6; i10++) {
                bArr[i4] = buffer.get(i9);
                i4 += i5;
                i9 += plane.getPixelStride();
            }
            i7 += plane.getRowStride();
        }
    }

    public byte[] b(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray() && byteBuffer.arrayOffset() == 0) {
            return byteBuffer.array();
        }
        byteBuffer.rewind();
        int limit = byteBuffer.limit();
        byte[] bArr = new byte[limit];
        byteBuffer.get(bArr, 0, limit);
        return bArr;
    }

    public Bitmap c(Image image, int i2) {
        Preconditions.b(image.getFormat() == 256, "Only JPEG is supported now");
        Image.Plane[] planes = image.getPlanes();
        if (planes == null || planes.length != 1) {
            throw new IllegalArgumentException("Unexpected image format, JPEG should have exactly 1 image plane");
        }
        ByteBuffer buffer = planes[0].getBuffer();
        buffer.rewind();
        int remaining = buffer.remaining();
        byte[] bArr = new byte[remaining];
        buffer.get(bArr);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, remaining);
        return j(decodeByteArray, i2, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    public Bitmap d(InputImage inputImage) {
        int g2 = inputImage.g();
        if (g2 == -1) {
            return j((Bitmap) Preconditions.i(inputImage.d()), inputImage.k(), inputImage.l(), inputImage.h());
        }
        if (g2 == 17) {
            return f((ByteBuffer) Preconditions.i(inputImage.e()), inputImage.l(), inputImage.h(), inputImage.k());
        }
        if (g2 == 35) {
            return f(g((Image.Plane[]) Preconditions.i(inputImage.j()), inputImage.l(), inputImage.h()), inputImage.l(), inputImage.h(), inputImage.k());
        }
        if (g2 == 842094169) {
            return h((ByteBuffer) Preconditions.i(inputImage.e()), inputImage.l(), inputImage.h(), inputImage.k());
        }
        throw new MlKitException("Unsupported image format", 13);
    }

    public Bitmap f(ByteBuffer byteBuffer, int i2, int i3, int i4) {
        byte[] k2 = k(b(byteBuffer), i2, i3);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(k2, 0, k2.length);
        return j(decodeByteArray, i4, decodeByteArray.getWidth(), decodeByteArray.getHeight());
    }

    public ByteBuffer g(Image.Plane[] planeArr, int i2, int i3) {
        int i4 = i2 * i3;
        int i5 = i4 / 4;
        byte[] bArr = new byte[i5 + i5 + i4];
        ByteBuffer buffer = planeArr[1].getBuffer();
        ByteBuffer buffer2 = planeArr[2].getBuffer();
        int position = buffer2.position();
        int limit = buffer.limit();
        buffer2.position(position + 1);
        buffer.limit(limit - 1);
        int i6 = (i4 + i4) / 4;
        boolean z = buffer2.remaining() == i6 + (-2) && buffer2.compareTo(buffer) == 0;
        buffer2.position(position);
        buffer.limit(limit);
        if (z) {
            planeArr[0].getBuffer().get(bArr, 0, i4);
            ByteBuffer buffer3 = planeArr[1].getBuffer();
            planeArr[2].getBuffer().get(bArr, i4, 1);
            buffer3.get(bArr, i4 + 1, i6 - 1);
        } else {
            l(planeArr[0], i2, i3, bArr, 0, 1);
            l(planeArr[1], i2, i3, bArr, i4 + 1, 2);
            l(planeArr[2], i2, i3, bArr, i4, 2);
        }
        return ByteBuffer.wrap(bArr);
    }
}
