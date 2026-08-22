package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.ColorSpace;
import android.graphics.Paint;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;

/* loaded from: classes.dex */
public final class BitmapCompat {

    @RequiresApi
    static class Api27Impl {
        @DoNotInline
        static Bitmap a(Bitmap bitmap) {
            if (bitmap.getConfig() != Bitmap.Config.HARDWARE) {
                return bitmap;
            }
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            return bitmap.copy(Api31Impl.a(bitmap), true);
        }

        @DoNotInline
        static Bitmap b(int i2, int i3, Bitmap bitmap, boolean z) {
            Bitmap.Config config = bitmap.getConfig();
            ColorSpace colorSpace = bitmap.getColorSpace();
            ColorSpace colorSpace2 = ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB);
            if (z && !bitmap.getColorSpace().equals(colorSpace2)) {
                config = Bitmap.Config.RGBA_F16;
                colorSpace = colorSpace2;
            } else if (bitmap.getConfig() == Bitmap.Config.HARDWARE) {
                Bitmap.Config config2 = Bitmap.Config.ARGB_8888;
                config = Api31Impl.a(bitmap);
            }
            return Bitmap.createBitmap(i2, i3, config, bitmap.hasAlpha(), colorSpace);
        }

        @DoNotInline
        static boolean c(Bitmap bitmap) {
            return bitmap.getConfig() == Bitmap.Config.RGBA_F16 && bitmap.getColorSpace().equals(ColorSpace.get(ColorSpace.Named.LINEAR_EXTENDED_SRGB));
        }
    }

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(Paint paint) {
            paint.setBlendMode(BlendMode.SRC);
        }
    }

    @RequiresApi
    static class Api31Impl {
        @DoNotInline
        static Bitmap.Config a(Bitmap bitmap) {
            return bitmap.getHardwareBuffer().getFormat() == 22 ? Bitmap.Config.RGBA_F16 : Bitmap.Config.ARGB_8888;
        }
    }

    @VisibleForTesting
    static int sizeAtStep(int i2, int i3, int i4, int i5) {
        return i4 == 0 ? i3 : i4 > 0 ? i2 * (1 << (i5 - i4)) : i3 << ((-i4) - 1);
    }
}
