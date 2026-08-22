package androidx.transition;

import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class ViewUtilsApi19 {

    /* renamed from: b, reason: collision with root package name */
    private static boolean f5588b = true;

    /* renamed from: c, reason: collision with root package name */
    private static Method f5589c;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f5590d;

    /* renamed from: e, reason: collision with root package name */
    private static Field f5591e;

    /* renamed from: f, reason: collision with root package name */
    private static boolean f5592f;

    /* renamed from: a, reason: collision with root package name */
    private float[] f5593a;

    @RequiresApi
    static class Api29Impl {
        @DoNotInline
        static float a(View view) {
            return view.getTransitionAlpha();
        }

        @DoNotInline
        static void b(View view, float f2) {
            view.setTransitionAlpha(f2);
        }
    }

    ViewUtilsApi19() {
    }

    private void b() {
        if (f5590d) {
            return;
        }
        try {
            Class cls = Integer.TYPE;
            Method declaredMethod = View.class.getDeclaredMethod("setFrame", cls, cls, cls, cls);
            f5589c = declaredMethod;
            declaredMethod.setAccessible(true);
        } catch (NoSuchMethodException e2) {
            Log.i("ViewUtilsApi19", "Failed to retrieve setFrame method", e2);
        }
        f5590d = true;
    }

    public void a(View view) {
    }

    public float c(View view) {
        if (f5588b) {
            try {
                return Api29Impl.a(view);
            } catch (NoSuchMethodError unused) {
                f5588b = false;
            }
        }
        return view.getAlpha();
    }

    public void d(View view) {
    }

    public void e(View view, Matrix matrix) {
        if (matrix == null || matrix.isIdentity()) {
            view.setPivotX(view.getWidth() / 2);
            view.setPivotY(view.getHeight() / 2);
            view.setTranslationX(0.0f);
            view.setTranslationY(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            view.setRotation(0.0f);
            return;
        }
        float[] fArr = this.f5593a;
        if (fArr == null) {
            fArr = new float[9];
            this.f5593a = fArr;
        }
        matrix.getValues(fArr);
        float f2 = fArr[3];
        float sqrt = ((float) Math.sqrt(1.0f - (f2 * f2))) * (fArr[0] < 0.0f ? -1 : 1);
        float degrees = (float) Math.toDegrees(Math.atan2(f2, sqrt));
        float f3 = fArr[0] / sqrt;
        float f4 = fArr[4] / sqrt;
        float f5 = fArr[2];
        float f6 = fArr[5];
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationX(f5);
        view.setTranslationY(f6);
        view.setRotation(degrees);
        view.setScaleX(f3);
        view.setScaleY(f4);
    }

    public void f(View view, int i2, int i3, int i4, int i5) {
        b();
        Method method = f5589c;
        if (method != null) {
            try {
                method.invoke(view, Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5));
            } catch (IllegalAccessException unused) {
            } catch (InvocationTargetException e2) {
                throw new RuntimeException(e2.getCause());
            }
        }
    }

    public void g(View view, float f2) {
        if (f5588b) {
            try {
                Api29Impl.b(view, f2);
                return;
            } catch (NoSuchMethodError unused) {
                f5588b = false;
            }
        }
        view.setAlpha(f2);
    }

    public void h(View view, int i2) {
        if (!f5592f) {
            try {
                Field declaredField = View.class.getDeclaredField("mViewFlags");
                f5591e = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException unused) {
                Log.i("ViewUtilsApi19", "fetchViewFlagsField: ");
            }
            f5592f = true;
        }
        Field field = f5591e;
        if (field != null) {
            try {
                f5591e.setInt(view, (field.getInt(view) & (-13)) | i2);
            } catch (IllegalAccessException unused2) {
            }
        }
    }

    public void i(View view, Matrix matrix) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            i((View) parent, matrix);
            matrix.preTranslate(-r0.getScrollX(), -r0.getScrollY());
        }
        matrix.preTranslate(view.getLeft(), view.getTop());
        Matrix matrix2 = view.getMatrix();
        if (matrix2.isIdentity()) {
            return;
        }
        matrix.preConcat(matrix2);
    }

    public void j(View view, Matrix matrix) {
        Object parent = view.getParent();
        if (parent instanceof View) {
            j((View) parent, matrix);
            matrix.postTranslate(r0.getScrollX(), r0.getScrollY());
        }
        matrix.postTranslate(-view.getLeft(), -view.getTop());
        Matrix matrix2 = view.getMatrix();
        if (matrix2.isIdentity()) {
            return;
        }
        Matrix matrix3 = new Matrix();
        if (matrix2.invert(matrix3)) {
            matrix.postConcat(matrix3);
        }
    }
}
