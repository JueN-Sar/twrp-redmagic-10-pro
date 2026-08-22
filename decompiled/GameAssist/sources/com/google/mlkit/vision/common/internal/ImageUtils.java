package com.google.mlkit.vision.common.internal;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.mlkit.common.MlKitException;
import com.google.mlkit.vision.common.InputImage;
import java.nio.ByteBuffer;

@KeepForSdk
/* loaded from: classes.dex */
public class ImageUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final GmsLogger f16060a = new GmsLogger("MLKitImageUtils", "");

    /* renamed from: b, reason: collision with root package name */
    private static final ImageUtils f16061b = new ImageUtils();

    private ImageUtils() {
    }

    public static ImageUtils b() {
        return f16061b;
    }

    public IObjectWrapper a(InputImage inputImage) {
        int g2 = inputImage.g();
        if (g2 == -1) {
            return ObjectWrapper.wrap((Bitmap) Preconditions.i(inputImage.d()));
        }
        if (g2 != 17) {
            if (g2 == 35) {
                return ObjectWrapper.wrap(inputImage.i());
            }
            if (g2 != 842094169) {
                throw new MlKitException("Unsupported image format: " + inputImage.g(), 3);
            }
        }
        return ObjectWrapper.wrap((ByteBuffer) Preconditions.i(inputImage.e()));
    }

    public int c(InputImage inputImage) {
        return inputImage.g();
    }

    public int d(InputImage inputImage) {
        if (inputImage.g() == -1) {
            return ((Bitmap) Preconditions.i(inputImage.d())).getAllocationByteCount();
        }
        if (inputImage.g() == 17 || inputImage.g() == 842094169) {
            return ((ByteBuffer) Preconditions.i(inputImage.e())).limit();
        }
        if (inputImage.g() != 35) {
            return 0;
        }
        return (((Image.Plane[]) Preconditions.i(inputImage.j()))[0].getBuffer().limit() * 3) / 2;
    }

    public Matrix e(int i2, int i3, int i4) {
        if (i4 == 0) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postTranslate((-i2) / 2.0f, (-i3) / 2.0f);
        matrix.postRotate(i4 * 90);
        int i5 = i4 % 2;
        int i6 = i5 != 0 ? i3 : i2;
        if (i5 == 0) {
            i2 = i3;
        }
        matrix.postTranslate(i6 / 2.0f, i2 / 2.0f);
        return matrix;
    }
}
