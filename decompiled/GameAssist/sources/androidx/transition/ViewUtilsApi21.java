package androidx.transition;

import android.graphics.Matrix;
import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;

@RequiresApi
/* loaded from: classes.dex */
class ViewUtilsApi21 extends ViewUtilsApi19 {

    /* renamed from: g, reason: collision with root package name */
    private static boolean f5594g = true;

    /* renamed from: h, reason: collision with root package name */
    private static boolean f5595h = true;

    /* renamed from: i, reason: collision with root package name */
    private static boolean f5596i = true;

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static void a(View view, Matrix matrix) {
            view.setAnimationMatrix(matrix);
        }

        @DoNotInline
        static void b(View view, Matrix matrix) {
            view.transformMatrixToGlobal(matrix);
        }

        @DoNotInline
        static void c(View view, Matrix matrix) {
            view.transformMatrixToLocal(matrix);
        }
    }

    ViewUtilsApi21() {
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void e(View view, Matrix matrix) {
        if (f5594g) {
            try {
                Api29Impl.a(view, matrix);
            } catch (NoSuchMethodError unused) {
                f5594g = false;
            }
        }
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void i(View view, Matrix matrix) {
        if (f5595h) {
            try {
                Api29Impl.b(view, matrix);
            } catch (NoSuchMethodError unused) {
                f5595h = false;
            }
        }
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void j(View view, Matrix matrix) {
        if (f5596i) {
            try {
                Api29Impl.c(view, matrix);
            } catch (NoSuchMethodError unused) {
                f5596i = false;
            }
        }
    }
}
