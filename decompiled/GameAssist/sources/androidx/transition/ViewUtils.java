package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;

/* loaded from: classes.dex */
class ViewUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final ViewUtilsApi19 f5585a = new ViewUtilsApi29();

    /* renamed from: b, reason: collision with root package name */
    static final Property f5586b = new Property<View, Float>(Float.class, "translationAlpha") { // from class: androidx.transition.ViewUtils.1
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Float get(View view) {
            return Float.valueOf(ViewUtils.b(view));
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Float f2) {
            ViewUtils.f(view, f2.floatValue());
        }
    };

    /* renamed from: c, reason: collision with root package name */
    static final Property f5587c = new Property<View, Rect>(Rect.class, "clipBounds") { // from class: androidx.transition.ViewUtils.2
        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Rect get(View view) {
            return view.getClipBounds();
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(View view, Rect rect) {
            view.setClipBounds(rect);
        }
    };

    static void a(View view) {
        f5585a.a(view);
    }

    static float b(View view) {
        return f5585a.c(view);
    }

    static void c(View view) {
        f5585a.d(view);
    }

    static void d(View view, Matrix matrix) {
        f5585a.e(view, matrix);
    }

    static void e(View view, int i2, int i3, int i4, int i5) {
        f5585a.f(view, i2, i3, i4, i5);
    }

    static void f(View view, float f2) {
        f5585a.g(view, f2);
    }

    static void g(View view, int i2) {
        f5585a.h(view, i2);
    }

    static void h(View view, Matrix matrix) {
        f5585a.i(view, matrix);
    }

    static void i(View view, Matrix matrix) {
        f5585a.j(view, matrix);
    }
}
