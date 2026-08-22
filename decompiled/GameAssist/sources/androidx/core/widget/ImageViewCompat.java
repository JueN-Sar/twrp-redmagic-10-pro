package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.widget.ImageView;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
public class ImageViewCompat {

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static ColorStateList a(ImageView imageView) {
            return imageView.getImageTintList();
        }

        @DoNotInline
        static PorterDuff.Mode b(ImageView imageView) {
            return imageView.getImageTintMode();
        }

        @DoNotInline
        static void c(ImageView imageView, ColorStateList colorStateList) {
            imageView.setImageTintList(colorStateList);
        }

        @DoNotInline
        static void d(ImageView imageView, PorterDuff.Mode mode) {
            imageView.setImageTintMode(mode);
        }
    }

    public static ColorStateList a(ImageView imageView) {
        return Api21Impl.a(imageView);
    }

    public static PorterDuff.Mode b(ImageView imageView) {
        return Api21Impl.b(imageView);
    }

    public static void c(ImageView imageView, ColorStateList colorStateList) {
        Api21Impl.c(imageView, colorStateList);
    }

    public static void d(ImageView imageView, PorterDuff.Mode mode) {
        Api21Impl.d(imageView, mode);
    }
}
