package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class ViewSpline extends SplineSet {

    static class AlphaSet extends ViewSpline {
        AlphaSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setAlpha(a(f2));
        }
    }

    public static class CustomSet extends ViewSpline {

        /* renamed from: f, reason: collision with root package name */
        String f2101f;

        /* renamed from: g, reason: collision with root package name */
        SparseArray f2102g;

        /* renamed from: h, reason: collision with root package name */
        float[] f2103h;

        public CustomSet(String str, SparseArray sparseArray) {
            this.f2101f = str.split(",")[1];
            this.f2102g = sparseArray;
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void c(int i2, float f2) {
            throw new RuntimeException("call of custom attribute setPoint");
        }

        @Override // androidx.constraintlayout.core.motion.utils.SplineSet
        public void e(int i2) {
            int size = this.f2102g.size();
            int h2 = ((ConstraintAttribute) this.f2102g.valueAt(0)).h();
            double[] dArr = new double[size];
            this.f2103h = new float[h2];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, h2);
            for (int i3 = 0; i3 < size; i3++) {
                int keyAt = this.f2102g.keyAt(i3);
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2102g.valueAt(i3);
                dArr[i3] = keyAt * 0.01d;
                constraintAttribute.f(this.f2103h);
                int i4 = 0;
                while (true) {
                    if (i4 < this.f2103h.length) {
                        dArr2[i3][i4] = r6[i4];
                        i4++;
                    }
                }
            }
            this.f1827a = CurveFit.a(i2, dArr, dArr2);
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            this.f1827a.e(f2, this.f2103h);
            CustomSupport.b((ConstraintAttribute) this.f2102g.valueAt(0), view, this.f2103h);
        }

        public void i(int i2, ConstraintAttribute constraintAttribute) {
            this.f2102g.append(i2, constraintAttribute);
        }
    }

    static class ElevationSet extends ViewSpline {
        ElevationSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setElevation(a(f2));
        }
    }

    public static class PathRotate extends ViewSpline {
        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
        }

        public void i(View view, float f2, double d2, double d3) {
            view.setRotation(a(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    static class PivotXset extends ViewSpline {
        PivotXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setPivotX(a(f2));
        }
    }

    static class PivotYset extends ViewSpline {
        PivotYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setPivotY(a(f2));
        }
    }

    static class ProgressSet extends ViewSpline {

        /* renamed from: f, reason: collision with root package name */
        boolean f2104f = false;

        ProgressSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f2));
                return;
            }
            if (this.f2104f) {
                return;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f2104f = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f2)));
                } catch (IllegalAccessException e2) {
                    Log.e("ViewSpline", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("ViewSpline", "unable to setProgress", e3);
                }
            }
        }
    }

    static class RotationSet extends ViewSpline {
        RotationSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setRotation(a(f2));
        }
    }

    static class RotationXset extends ViewSpline {
        RotationXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setRotationX(a(f2));
        }
    }

    static class RotationYset extends ViewSpline {
        RotationYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setRotationY(a(f2));
        }
    }

    static class ScaleXset extends ViewSpline {
        ScaleXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setScaleX(a(f2));
        }
    }

    static class ScaleYset extends ViewSpline {
        ScaleYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setScaleY(a(f2));
        }
    }

    static class TranslationXset extends ViewSpline {
        TranslationXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setTranslationX(a(f2));
        }
    }

    static class TranslationYset extends ViewSpline {
        TranslationYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setTranslationY(a(f2));
        }
    }

    static class TranslationZset extends ViewSpline {
        TranslationZset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewSpline
        public void h(View view, float f2) {
            view.setTranslationZ(a(f2));
        }
    }

    public static ViewSpline f(String str, SparseArray sparseArray) {
        return new CustomSet(str, sparseArray);
    }

    public static ViewSpline g(String str) {
        str.hashCode();
        switch (str) {
        }
        return new AlphaSet();
    }

    public abstract void h(View view, float f2);
}
