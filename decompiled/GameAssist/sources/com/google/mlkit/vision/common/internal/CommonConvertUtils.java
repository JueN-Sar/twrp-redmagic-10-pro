package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.os.SystemClock;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.mlkit_vision_common.zzms;
import com.google.android.gms.internal.mlkit_vision_common.zzmu;
import com.google.android.gms.internal.mlkit_vision_common.zzmw;
import com.google.android.odml.image.BitmapExtractor;
import com.google.android.odml.image.ByteBufferExtractor;
import com.google.android.odml.image.ImageProperties;
import com.google.android.odml.image.MediaImageExtractor;
import com.google.android.odml.image.MlImage;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

@KeepForSdk
/* loaded from: classes.dex */
public class CommonConvertUtils {
    public static InputImage a(MlImage mlImage) {
        InputImage a2;
        ImageProperties imageProperties = (ImageProperties) mlImage.a().get(0);
        int b2 = imageProperties.b();
        if (b2 != 1) {
            a2 = null;
            if (b2 == 2) {
                ByteBuffer a3 = ByteBufferExtractor.a(mlImage);
                int a4 = imageProperties.a();
                Integer num = a4 != 4 ? a4 != 5 ? null : 842094169 : 17;
                if (num != null) {
                    e(num.intValue(), 3, SystemClock.elapsedRealtime(), mlImage.c(), mlImage.e(), a3.limit(), mlImage.d());
                    a2 = InputImage.b(a3, mlImage.e(), mlImage.c(), mlImage.d(), num.intValue());
                }
            } else if (b2 == 3) {
                Image a5 = MediaImageExtractor.a(mlImage);
                e(a5.getFormat(), 5, SystemClock.elapsedRealtime(), mlImage.c(), mlImage.e(), a5.getFormat() == 256 ? a5.getPlanes()[0].getBuffer().limit() : (a5.getPlanes()[0].getBuffer().limit() * 3) / 2, mlImage.d());
                a2 = InputImage.c(a5, mlImage.d());
            }
        } else {
            Bitmap a6 = BitmapExtractor.a(mlImage);
            e(-1, 1, SystemClock.elapsedRealtime(), mlImage.c(), mlImage.e(), a6.getAllocationByteCount(), mlImage.d());
            a2 = InputImage.a(a6, mlImage.d());
        }
        if (a2 != null) {
            zzmw.a();
        }
        return a2;
    }

    public static int b(int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 90) {
            return 1;
        }
        if (i2 == 180) {
            return 2;
        }
        if (i2 == 270) {
            return 3;
        }
        throw new IllegalArgumentException("Invalid rotation: " + i2);
    }

    public static void c(Point[] pointArr, Matrix matrix) {
        int length = pointArr.length;
        float[] fArr = new float[length + length];
        for (int i2 = 0; i2 < pointArr.length; i2++) {
            Point point = pointArr[i2];
            int i3 = i2 + i2;
            fArr[i3] = point.x;
            fArr[i3 + 1] = point.y;
        }
        matrix.mapPoints(fArr);
        for (int i4 = 0; i4 < pointArr.length; i4++) {
            int i5 = i4 + i4;
            pointArr[i4].set((int) fArr[i5], (int) fArr[i5 + 1]);
        }
    }

    public static void d(Rect rect, Matrix matrix) {
        RectF rectF = new RectF(rect);
        matrix.mapRect(rectF);
        rect.set((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
    }

    private static void e(int i2, int i3, long j2, int i4, int i5, int i6, int i7) {
        zzmu.b(zzms.b("vision-common"), i2, i3, j2, i4, i5, i6, i7);
    }
}
