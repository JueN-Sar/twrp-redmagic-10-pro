package androidx.transition;

import android.graphics.Matrix;
import android.widget.ImageView;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

/* loaded from: classes.dex */
class ImageViewUtils {

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(ImageView imageView, Matrix matrix) {
            imageView.animateTransform(matrix);
        }
    }

    static void a(ImageView imageView, Matrix matrix) {
        Api29Impl.a(imageView, matrix);
    }
}
