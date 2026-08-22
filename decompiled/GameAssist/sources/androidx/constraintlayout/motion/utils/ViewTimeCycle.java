package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class ViewTimeCycle extends TimeCycleSplineSet {

    static class AlphaSet extends ViewTimeCycle {
        AlphaSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setAlpha(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    public static class CustomSet extends ViewTimeCycle {

        /* renamed from: l, reason: collision with root package name */
        String f2110l;

        /* renamed from: m, reason: collision with root package name */
        SparseArray f2111m;

        /* renamed from: n, reason: collision with root package name */
        SparseArray f2112n = new SparseArray();

        /* renamed from: o, reason: collision with root package name */
        float[] f2113o;

        public CustomSet(String str, SparseArray sparseArray) {
            this.f2110l = str.split(",")[1];
            this.f2111m = sparseArray;
        }

        @Override // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void b(int i2, float f2, float f3, int i3, float f4) {
            throw new RuntimeException("Wrong call for custom attribute");
        }

        @Override // androidx.constraintlayout.core.motion.utils.TimeCycleSplineSet
        public void e(int i2) {
            int size = this.f2111m.size();
            int h2 = ((ConstraintAttribute) this.f2111m.valueAt(0)).h();
            double[] dArr = new double[size];
            int i3 = h2 + 2;
            this.f2113o = new float[i3];
            this.f1878g = new float[h2];
            double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, size, i3);
            for (int i4 = 0; i4 < size; i4++) {
                int keyAt = this.f2111m.keyAt(i4);
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2111m.valueAt(i4);
                float[] fArr = (float[]) this.f2112n.valueAt(i4);
                dArr[i4] = keyAt * 0.01d;
                constraintAttribute.f(this.f2113o);
                int i5 = 0;
                while (true) {
                    if (i5 < this.f2113o.length) {
                        dArr2[i4][i5] = r8[i5];
                        i5++;
                    }
                }
                double[] dArr3 = dArr2[i4];
                dArr3[h2] = fArr[0];
                dArr3[h2 + 1] = fArr[1];
            }
            this.f1872a = CurveFit.a(i2, dArr, dArr2);
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            this.f1872a.e(f2, this.f2113o);
            float[] fArr = this.f2113o;
            float f3 = fArr[fArr.length - 2];
            float f4 = fArr[fArr.length - 1];
            long j3 = j2 - this.f1880i;
            if (Float.isNaN(this.f1881j)) {
                float a2 = keyCache.a(view, this.f2110l, 0);
                this.f1881j = a2;
                if (Float.isNaN(a2)) {
                    this.f1881j = 0.0f;
                }
            }
            float f5 = (float) ((this.f1881j + ((j3 * 1.0E-9d) * f3)) % 1.0d);
            this.f1881j = f5;
            this.f1880i = j2;
            float a3 = a(f5);
            this.f1879h = false;
            int i2 = 0;
            while (true) {
                float[] fArr2 = this.f1878g;
                if (i2 >= fArr2.length) {
                    break;
                }
                boolean z = this.f1879h;
                float f6 = this.f2113o[i2];
                this.f1879h = z | (((double) f6) != 0.0d);
                fArr2[i2] = (f6 * a3) + f4;
                i2++;
            }
            CustomSupport.b((ConstraintAttribute) this.f2111m.valueAt(0), view, this.f1878g);
            if (f3 != 0.0f) {
                this.f1879h = true;
            }
            return this.f1879h;
        }

        public void j(int i2, ConstraintAttribute constraintAttribute, float f2, int i3, float f3) {
            this.f2111m.append(i2, constraintAttribute);
            this.f2112n.append(i2, new float[]{f2, f3});
            this.f1873b = Math.max(this.f1873b, i3);
        }
    }

    static class ElevationSet extends ViewTimeCycle {
        ElevationSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setElevation(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    public static class PathRotate extends ViewTimeCycle {
        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            return this.f1879h;
        }

        public boolean j(View view, KeyCache keyCache, float f2, long j2, double d2, double d3) {
            view.setRotation(f(f2, j2, view, keyCache) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
            return this.f1879h;
        }
    }

    static class ProgressSet extends ViewTimeCycle {

        /* renamed from: l, reason: collision with root package name */
        boolean f2114l = false;

        ProgressSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(f(f2, j2, view, keyCache));
            } else {
                if (this.f2114l) {
                    return false;
                }
                try {
                    method = view.getClass().getMethod("setProgress", Float.TYPE);
                } catch (NoSuchMethodException unused) {
                    this.f2114l = true;
                    method = null;
                }
                if (method != null) {
                    try {
                        method.invoke(view, Float.valueOf(f(f2, j2, view, keyCache)));
                    } catch (IllegalAccessException e2) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e2);
                    } catch (InvocationTargetException e3) {
                        Log.e("ViewTimeCycle", "unable to setProgress", e3);
                    }
                }
            }
            return this.f1879h;
        }
    }

    static class RotationSet extends ViewTimeCycle {
        RotationSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setRotation(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class RotationXset extends ViewTimeCycle {
        RotationXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setRotationX(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class RotationYset extends ViewTimeCycle {
        RotationYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setRotationY(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class ScaleXset extends ViewTimeCycle {
        ScaleXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setScaleX(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class ScaleYset extends ViewTimeCycle {
        ScaleYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setScaleY(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class TranslationXset extends ViewTimeCycle {
        TranslationXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setTranslationX(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class TranslationYset extends ViewTimeCycle {
        TranslationYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setTranslationY(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    static class TranslationZset extends ViewTimeCycle {
        TranslationZset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewTimeCycle
        public boolean i(View view, float f2, long j2, KeyCache keyCache) {
            view.setTranslationZ(f(f2, j2, view, keyCache));
            return this.f1879h;
        }
    }

    public static ViewTimeCycle g(String str, SparseArray sparseArray) {
        return new CustomSet(str, sparseArray);
    }

    public static ViewTimeCycle h(String str, long j2) {
        ViewTimeCycle rotationXset;
        str.hashCode();
        switch (str) {
            case "rotationX":
                rotationXset = new RotationXset();
                break;
            case "rotationY":
                rotationXset = new RotationYset();
                break;
            case "translationX":
                rotationXset = new TranslationXset();
                break;
            case "translationY":
                rotationXset = new TranslationYset();
                break;
            case "translationZ":
                rotationXset = new TranslationZset();
                break;
            case "progress":
                rotationXset = new ProgressSet();
                break;
            case "scaleX":
                rotationXset = new ScaleXset();
                break;
            case "scaleY":
                rotationXset = new ScaleYset();
                break;
            case "rotation":
                rotationXset = new RotationSet();
                break;
            case "elevation":
                rotationXset = new ElevationSet();
                break;
            case "transitionPathRotate":
                rotationXset = new PathRotate();
                break;
            case "alpha":
                rotationXset = new AlphaSet();
                break;
            default:
                return null;
        }
        rotationXset.c(j2);
        return rotationXset;
    }

    public float f(float f2, long j2, View view, KeyCache keyCache) {
        this.f1872a.e(f2, this.f1878g);
        float[] fArr = this.f1878g;
        float f3 = fArr[1];
        if (f3 == 0.0f) {
            this.f1879h = false;
            return fArr[2];
        }
        if (Float.isNaN(this.f1881j)) {
            float a2 = keyCache.a(view, this.f1877f, 0);
            this.f1881j = a2;
            if (Float.isNaN(a2)) {
                this.f1881j = 0.0f;
            }
        }
        float f4 = (float) ((this.f1881j + (((j2 - this.f1880i) * 1.0E-9d) * f3)) % 1.0d);
        this.f1881j = f4;
        keyCache.b(view, this.f1877f, 0, f4);
        this.f1880i = j2;
        float f5 = this.f1878g[0];
        float a3 = (a(this.f1881j) * f5) + this.f1878g[2];
        this.f1879h = (f5 == 0.0f && f3 == 0.0f) ? false : true;
        return a3;
    }

    public abstract boolean i(View view, float f2, long j2, KeyCache keyCache);
}
