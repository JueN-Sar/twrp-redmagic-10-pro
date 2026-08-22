package androidx.constraintlayout.motion.utils;

import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.KeyCycleOscillator;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintAttribute;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public abstract class ViewOscillator extends KeyCycleOscillator {

    static class AlphaSet extends ViewOscillator {
        AlphaSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setAlpha(a(f2));
        }
    }

    static class CustomSet extends ViewOscillator {

        /* renamed from: h, reason: collision with root package name */
        float[] f2098h = new float[1];

        /* renamed from: i, reason: collision with root package name */
        protected ConstraintAttribute f2099i;

        CustomSet() {
        }

        @Override // androidx.constraintlayout.core.motion.utils.KeyCycleOscillator
        protected void c(Object obj) {
            this.f2099i = (ConstraintAttribute) obj;
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            this.f2098h[0] = a(f2);
            CustomSupport.b(this.f2099i, view, this.f2098h);
        }
    }

    static class ElevationSet extends ViewOscillator {
        ElevationSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setElevation(a(f2));
        }
    }

    public static class PathRotateSet extends ViewOscillator {
        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
        }

        public void k(View view, float f2, double d2, double d3) {
            view.setRotation(a(f2) + ((float) Math.toDegrees(Math.atan2(d3, d2))));
        }
    }

    static class ProgressSet extends ViewOscillator {

        /* renamed from: h, reason: collision with root package name */
        boolean f2100h = false;

        ProgressSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            Method method;
            if (view instanceof MotionLayout) {
                ((MotionLayout) view).setProgress(a(f2));
                return;
            }
            if (this.f2100h) {
                return;
            }
            try {
                method = view.getClass().getMethod("setProgress", Float.TYPE);
            } catch (NoSuchMethodException unused) {
                this.f2100h = true;
                method = null;
            }
            if (method != null) {
                try {
                    method.invoke(view, Float.valueOf(a(f2)));
                } catch (IllegalAccessException e2) {
                    Log.e("ViewOscillator", "unable to setProgress", e2);
                } catch (InvocationTargetException e3) {
                    Log.e("ViewOscillator", "unable to setProgress", e3);
                }
            }
        }
    }

    static class RotationSet extends ViewOscillator {
        RotationSet() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setRotation(a(f2));
        }
    }

    static class RotationXset extends ViewOscillator {
        RotationXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setRotationX(a(f2));
        }
    }

    static class RotationYset extends ViewOscillator {
        RotationYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setRotationY(a(f2));
        }
    }

    static class ScaleXset extends ViewOscillator {
        ScaleXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setScaleX(a(f2));
        }
    }

    static class ScaleYset extends ViewOscillator {
        ScaleYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setScaleY(a(f2));
        }
    }

    static class TranslationXset extends ViewOscillator {
        TranslationXset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setTranslationX(a(f2));
        }
    }

    static class TranslationYset extends ViewOscillator {
        TranslationYset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setTranslationY(a(f2));
        }
    }

    static class TranslationZset extends ViewOscillator {
        TranslationZset() {
        }

        @Override // androidx.constraintlayout.motion.utils.ViewOscillator
        public void j(View view, float f2) {
            view.setTranslationZ(a(f2));
        }
    }

    public static ViewOscillator i(String str) {
        if (str.startsWith("CUSTOM")) {
            return new CustomSet();
        }
        switch (str) {
            case "rotationX":
                return new RotationXset();
            case "rotationY":
                return new RotationYset();
            case "translationX":
                return new TranslationXset();
            case "translationY":
                return new TranslationYset();
            case "translationZ":
                return new TranslationZset();
            case "progress":
                return new ProgressSet();
            case "scaleX":
                return new ScaleXset();
            case "scaleY":
                return new ScaleYset();
            case "waveVariesBy":
                return new AlphaSet();
            case "rotation":
                return new RotationSet();
            case "elevation":
                return new ElevationSet();
            case "transitionPathRotate":
                return new PathRotateSet();
            case "alpha":
                return new AlphaSet();
            case "waveOffset":
                return new AlphaSet();
            default:
                return null;
        }
    }

    public abstract void j(View view, float f2);
}
