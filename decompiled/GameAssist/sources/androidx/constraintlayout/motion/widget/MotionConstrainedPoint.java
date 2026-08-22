package androidx.constraintlayout.motion.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
class MotionConstrainedPoint implements Comparable<MotionConstrainedPoint> {
    static String[] J = {"position", "x", "y", "width", "height", "pathRotate"};
    private float B;
    private float C;
    private float D;
    private float E;
    private float F;

    /* renamed from: i, reason: collision with root package name */
    int f2202i;
    private Easing z;

    /* renamed from: c, reason: collision with root package name */
    public float f2200c = 0.0f;

    /* renamed from: h, reason: collision with root package name */
    int f2201h = 0;

    /* renamed from: j, reason: collision with root package name */
    LinkedHashMap f2203j = new LinkedHashMap();

    /* renamed from: k, reason: collision with root package name */
    int f2204k = 0;

    /* renamed from: l, reason: collision with root package name */
    double[] f2205l = new double[18];

    /* renamed from: m, reason: collision with root package name */
    double[] f2206m = new double[18];

    /* renamed from: n, reason: collision with root package name */
    private float f2207n = 1.0f;

    /* renamed from: o, reason: collision with root package name */
    private boolean f2208o = false;

    /* renamed from: p, reason: collision with root package name */
    private float f2209p = 0.0f;

    /* renamed from: q, reason: collision with root package name */
    private float f2210q = 0.0f;

    /* renamed from: r, reason: collision with root package name */
    private float f2211r = 0.0f;

    /* renamed from: s, reason: collision with root package name */
    private float f2212s = 1.0f;
    private float t = 1.0f;
    private float u = Float.NaN;
    private float v = Float.NaN;
    private float w = 0.0f;
    private float x = 0.0f;
    private float y = 0.0f;
    private int A = 0;
    private float G = Float.NaN;
    private float H = Float.NaN;
    private int I = -1;

    MotionConstrainedPoint() {
    }

    private boolean h(float f2, float f3) {
        return (Float.isNaN(f2) || Float.isNaN(f3)) ? Float.isNaN(f2) != Float.isNaN(f3) : Math.abs(f2 - f3) > 1.0E-6f;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public void c(HashMap hashMap, int i2) {
        for (String str : hashMap.keySet()) {
            ViewSpline viewSpline = (ViewSpline) hashMap.get(str);
            if (viewSpline != null) {
                str.hashCode();
                char c2 = 65535;
                switch (str.hashCode()) {
                    case -1249320806:
                        if (str.equals("rotationX")) {
                            c2 = 0;
                            break;
                        }
                        break;
                    case -1249320805:
                        if (str.equals("rotationY")) {
                            c2 = 1;
                            break;
                        }
                        break;
                    case -1225497657:
                        if (str.equals("translationX")) {
                            c2 = 2;
                            break;
                        }
                        break;
                    case -1225497656:
                        if (str.equals("translationY")) {
                            c2 = 3;
                            break;
                        }
                        break;
                    case -1225497655:
                        if (str.equals("translationZ")) {
                            c2 = 4;
                            break;
                        }
                        break;
                    case -1001078227:
                        if (str.equals("progress")) {
                            c2 = 5;
                            break;
                        }
                        break;
                    case -908189618:
                        if (str.equals("scaleX")) {
                            c2 = 6;
                            break;
                        }
                        break;
                    case -908189617:
                        if (str.equals("scaleY")) {
                            c2 = 7;
                            break;
                        }
                        break;
                    case -760884510:
                        if (str.equals("transformPivotX")) {
                            c2 = '\b';
                            break;
                        }
                        break;
                    case -760884509:
                        if (str.equals("transformPivotY")) {
                            c2 = '\t';
                            break;
                        }
                        break;
                    case -40300674:
                        if (str.equals("rotation")) {
                            c2 = '\n';
                            break;
                        }
                        break;
                    case -4379043:
                        if (str.equals("elevation")) {
                            c2 = 11;
                            break;
                        }
                        break;
                    case 37232917:
                        if (str.equals("transitionPathRotate")) {
                            c2 = '\f';
                            break;
                        }
                        break;
                    case 92909918:
                        if (str.equals("alpha")) {
                            c2 = '\r';
                            break;
                        }
                        break;
                }
                switch (c2) {
                    case 0:
                        viewSpline.c(i2, Float.isNaN(this.f2211r) ? 0.0f : this.f2211r);
                        break;
                    case 1:
                        viewSpline.c(i2, Float.isNaN(this.f2200c) ? 0.0f : this.f2200c);
                        break;
                    case 2:
                        viewSpline.c(i2, Float.isNaN(this.w) ? 0.0f : this.w);
                        break;
                    case 3:
                        viewSpline.c(i2, Float.isNaN(this.x) ? 0.0f : this.x);
                        break;
                    case 4:
                        viewSpline.c(i2, Float.isNaN(this.y) ? 0.0f : this.y);
                        break;
                    case 5:
                        viewSpline.c(i2, Float.isNaN(this.H) ? 0.0f : this.H);
                        break;
                    case 6:
                        viewSpline.c(i2, Float.isNaN(this.f2212s) ? 1.0f : this.f2212s);
                        break;
                    case 7:
                        viewSpline.c(i2, Float.isNaN(this.t) ? 1.0f : this.t);
                        break;
                    case '\b':
                        viewSpline.c(i2, Float.isNaN(this.u) ? 0.0f : this.u);
                        break;
                    case '\t':
                        viewSpline.c(i2, Float.isNaN(this.v) ? 0.0f : this.v);
                        break;
                    case '\n':
                        viewSpline.c(i2, Float.isNaN(this.f2210q) ? 0.0f : this.f2210q);
                        break;
                    case 11:
                        viewSpline.c(i2, Float.isNaN(this.f2209p) ? 0.0f : this.f2209p);
                        break;
                    case '\f':
                        viewSpline.c(i2, Float.isNaN(this.G) ? 0.0f : this.G);
                        break;
                    case '\r':
                        viewSpline.c(i2, Float.isNaN(this.f2207n) ? 1.0f : this.f2207n);
                        break;
                    default:
                        if (str.startsWith("CUSTOM")) {
                            String str2 = str.split(",")[1];
                            if (this.f2203j.containsKey(str2)) {
                                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2203j.get(str2);
                                if (viewSpline instanceof ViewSpline.CustomSet) {
                                    ((ViewSpline.CustomSet) viewSpline).i(i2, constraintAttribute);
                                    break;
                                } else {
                                    Log.e("MotionPaths", str + " ViewSpline not a CustomSet frame = " + i2 + ", value" + constraintAttribute.e() + viewSpline);
                                    break;
                                }
                            } else {
                                break;
                            }
                        } else {
                            Log.e("MotionPaths", "UNKNOWN spline " + str);
                            break;
                        }
                }
            }
        }
    }

    public void d(View view) {
        this.f2202i = view.getVisibility();
        this.f2207n = view.getVisibility() != 0 ? 0.0f : view.getAlpha();
        this.f2208o = false;
        this.f2209p = view.getElevation();
        this.f2210q = view.getRotation();
        this.f2211r = view.getRotationX();
        this.f2200c = view.getRotationY();
        this.f2212s = view.getScaleX();
        this.t = view.getScaleY();
        this.u = view.getPivotX();
        this.v = view.getPivotY();
        this.w = view.getTranslationX();
        this.x = view.getTranslationY();
        this.y = view.getTranslationZ();
    }

    public void e(ConstraintSet.Constraint constraint) {
        ConstraintSet.PropertySet propertySet = constraint.f2489c;
        int i2 = propertySet.f2543c;
        this.f2201h = i2;
        int i3 = propertySet.f2542b;
        this.f2202i = i3;
        this.f2207n = (i3 == 0 || i2 != 0) ? propertySet.f2544d : 0.0f;
        ConstraintSet.Transform transform = constraint.f2492f;
        this.f2208o = transform.f2559m;
        this.f2209p = transform.f2560n;
        this.f2210q = transform.f2548b;
        this.f2211r = transform.f2549c;
        this.f2200c = transform.f2550d;
        this.f2212s = transform.f2551e;
        this.t = transform.f2552f;
        this.u = transform.f2553g;
        this.v = transform.f2554h;
        this.w = transform.f2556j;
        this.x = transform.f2557k;
        this.y = transform.f2558l;
        this.z = Easing.c(constraint.f2490d.f2530d);
        ConstraintSet.Motion motion = constraint.f2490d;
        this.G = motion.f2535i;
        this.A = motion.f2532f;
        this.I = motion.f2528b;
        this.H = constraint.f2489c.f2545e;
        for (String str : constraint.f2493g.keySet()) {
            ConstraintAttribute constraintAttribute = (ConstraintAttribute) constraint.f2493g.get(str);
            if (constraintAttribute.g()) {
                this.f2203j.put(str, constraintAttribute);
            }
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(MotionConstrainedPoint motionConstrainedPoint) {
        return Float.compare(this.B, motionConstrainedPoint.B);
    }

    void j(MotionConstrainedPoint motionConstrainedPoint, HashSet hashSet) {
        if (h(this.f2207n, motionConstrainedPoint.f2207n)) {
            hashSet.add("alpha");
        }
        if (h(this.f2209p, motionConstrainedPoint.f2209p)) {
            hashSet.add("elevation");
        }
        int i2 = this.f2202i;
        int i3 = motionConstrainedPoint.f2202i;
        if (i2 != i3 && this.f2201h == 0 && (i2 == 0 || i3 == 0)) {
            hashSet.add("alpha");
        }
        if (h(this.f2210q, motionConstrainedPoint.f2210q)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.G) || !Float.isNaN(motionConstrainedPoint.G)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.H) || !Float.isNaN(motionConstrainedPoint.H)) {
            hashSet.add("progress");
        }
        if (h(this.f2211r, motionConstrainedPoint.f2211r)) {
            hashSet.add("rotationX");
        }
        if (h(this.f2200c, motionConstrainedPoint.f2200c)) {
            hashSet.add("rotationY");
        }
        if (h(this.u, motionConstrainedPoint.u)) {
            hashSet.add("transformPivotX");
        }
        if (h(this.v, motionConstrainedPoint.v)) {
            hashSet.add("transformPivotY");
        }
        if (h(this.f2212s, motionConstrainedPoint.f2212s)) {
            hashSet.add("scaleX");
        }
        if (h(this.t, motionConstrainedPoint.t)) {
            hashSet.add("scaleY");
        }
        if (h(this.w, motionConstrainedPoint.w)) {
            hashSet.add("translationX");
        }
        if (h(this.x, motionConstrainedPoint.x)) {
            hashSet.add("translationY");
        }
        if (h(this.y, motionConstrainedPoint.y)) {
            hashSet.add("translationZ");
        }
    }

    void l(float f2, float f3, float f4, float f5) {
        this.C = f2;
        this.D = f3;
        this.E = f4;
        this.F = f5;
    }

    public void n(Rect rect, View view, int i2, float f2) {
        l(rect.left, rect.top, rect.width(), rect.height());
        d(view);
        this.u = Float.NaN;
        this.v = Float.NaN;
        if (i2 == 1) {
            this.f2210q = f2 - 90.0f;
        } else {
            if (i2 != 2) {
                return;
            }
            this.f2210q = f2 + 90.0f;
        }
    }

    public void o(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        l(rect.left, rect.top, rect.width(), rect.height());
        e(constraintSet.y(i3));
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return;
                    }
                }
            }
            float f2 = this.f2210q + 90.0f;
            this.f2210q = f2;
            if (f2 > 180.0f) {
                this.f2210q = f2 - 360.0f;
                return;
            }
            return;
        }
        this.f2210q -= 90.0f;
    }

    public void p(View view) {
        l(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        d(view);
    }
}
