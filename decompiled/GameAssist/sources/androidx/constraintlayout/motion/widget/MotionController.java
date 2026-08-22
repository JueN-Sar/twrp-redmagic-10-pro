package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.CurveFit;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.core.motion.utils.VelocityMatrix;
import androidx.constraintlayout.motion.utils.CustomSupport;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.motion.utils.ViewSpline;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.utils.ViewTimeCycle;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MotionController {
    private HashMap B;
    private HashMap C;
    private HashMap D;
    private KeyTrigger[] E;
    private int F;
    private int G;
    private View H;
    private int I;
    private float J;
    private Interpolator K;
    private boolean L;

    /* renamed from: b, reason: collision with root package name */
    View f2214b;

    /* renamed from: c, reason: collision with root package name */
    int f2215c;

    /* renamed from: e, reason: collision with root package name */
    String f2217e;

    /* renamed from: k, reason: collision with root package name */
    private CurveFit[] f2223k;

    /* renamed from: l, reason: collision with root package name */
    private CurveFit f2224l;

    /* renamed from: p, reason: collision with root package name */
    float f2228p;

    /* renamed from: q, reason: collision with root package name */
    float f2229q;

    /* renamed from: r, reason: collision with root package name */
    private int[] f2230r;

    /* renamed from: s, reason: collision with root package name */
    private double[] f2231s;
    private double[] t;
    private String[] u;
    private int[] v;

    /* renamed from: a, reason: collision with root package name */
    Rect f2213a = new Rect();

    /* renamed from: d, reason: collision with root package name */
    boolean f2216d = false;

    /* renamed from: f, reason: collision with root package name */
    private int f2218f = -1;

    /* renamed from: g, reason: collision with root package name */
    private MotionPaths f2219g = new MotionPaths();

    /* renamed from: h, reason: collision with root package name */
    private MotionPaths f2220h = new MotionPaths();

    /* renamed from: i, reason: collision with root package name */
    private MotionConstrainedPoint f2221i = new MotionConstrainedPoint();

    /* renamed from: j, reason: collision with root package name */
    private MotionConstrainedPoint f2222j = new MotionConstrainedPoint();

    /* renamed from: m, reason: collision with root package name */
    float f2225m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    float f2226n = 0.0f;

    /* renamed from: o, reason: collision with root package name */
    float f2227o = 1.0f;
    private int w = 4;
    private float[] x = new float[4];
    private ArrayList y = new ArrayList();
    private float[] z = new float[1];
    private ArrayList A = new ArrayList();

    MotionController(View view) {
        int i2 = Key.f2122f;
        this.F = i2;
        this.G = i2;
        this.H = null;
        this.I = i2;
        this.J = Float.NaN;
        this.K = null;
        this.L = false;
        H(view);
    }

    private float g(float f2, float[] fArr) {
        float f3 = 0.0f;
        if (fArr != null) {
            fArr[0] = 1.0f;
        } else {
            float f4 = this.f2227o;
            if (f4 != 1.0d) {
                float f5 = this.f2226n;
                if (f2 < f5) {
                    f2 = 0.0f;
                }
                if (f2 > f5 && f2 < 1.0d) {
                    f2 = Math.min((f2 - f5) * f4, 1.0f);
                }
            }
        }
        Easing easing = this.f2219g.f2279c;
        Iterator it = this.y.iterator();
        float f6 = Float.NaN;
        while (it.hasNext()) {
            MotionPaths motionPaths = (MotionPaths) it.next();
            Easing easing2 = motionPaths.f2279c;
            if (easing2 != null) {
                float f7 = motionPaths.f2281i;
                if (f7 < f2) {
                    easing = easing2;
                    f3 = f7;
                } else if (Float.isNaN(f6)) {
                    f6 = motionPaths.f2281i;
                }
            }
        }
        if (easing == null) {
            return f2;
        }
        float f8 = (Float.isNaN(f6) ? 1.0f : f6) - f3;
        double d2 = (f2 - f3) / f8;
        float a2 = f3 + (((float) easing.a(d2)) * f8);
        if (fArr != null) {
            fArr[0] = (float) easing.b(d2);
        }
        return a2;
    }

    private static Interpolator p(Context context, int i2, String str, int i3) {
        if (i2 == -2) {
            return AnimationUtils.loadInterpolator(context, i3);
        }
        if (i2 == -1) {
            final Easing c2 = Easing.c(str);
            return new Interpolator() { // from class: androidx.constraintlayout.motion.widget.MotionController.1
                @Override // android.animation.TimeInterpolator
                public float getInterpolation(float f2) {
                    return (float) Easing.this.a(f2);
                }
            };
        }
        if (i2 == 0) {
            return new AccelerateDecelerateInterpolator();
        }
        if (i2 == 1) {
            return new AccelerateInterpolator();
        }
        if (i2 == 2) {
            return new DecelerateInterpolator();
        }
        if (i2 == 4) {
            return new BounceInterpolator();
        }
        if (i2 != 5) {
            return null;
        }
        return new OvershootInterpolator();
    }

    private float s() {
        char c2;
        float[] fArr = new float[2];
        float f2 = 1.0f / 99;
        double d2 = 0.0d;
        double d3 = 0.0d;
        int i2 = 0;
        float f3 = 0.0f;
        while (i2 < 100) {
            float f4 = i2 * f2;
            double d4 = f4;
            Easing easing = this.f2219g.f2279c;
            Iterator it = this.y.iterator();
            float f5 = Float.NaN;
            float f6 = 0.0f;
            while (it.hasNext()) {
                MotionPaths motionPaths = (MotionPaths) it.next();
                Easing easing2 = motionPaths.f2279c;
                if (easing2 != null) {
                    float f7 = motionPaths.f2281i;
                    if (f7 < f4) {
                        easing = easing2;
                        f6 = f7;
                    } else if (Float.isNaN(f5)) {
                        f5 = motionPaths.f2281i;
                    }
                }
            }
            if (easing != null) {
                if (Float.isNaN(f5)) {
                    f5 = 1.0f;
                }
                d4 = (((float) easing.a((f4 - f6) / r7)) * (f5 - f6)) + f6;
            }
            this.f2223k[0].d(d4, this.f2231s);
            int i3 = i2;
            this.f2219g.j(d4, this.f2230r, this.f2231s, fArr, 0);
            if (i3 > 0) {
                c2 = 0;
                f3 += (float) Math.hypot(d3 - fArr[1], d2 - fArr[0]);
            } else {
                c2 = 0;
            }
            d2 = fArr[c2];
            i2 = i3 + 1;
            d3 = fArr[1];
        }
        return f3;
    }

    private void w(MotionPaths motionPaths) {
        if (Collections.binarySearch(this.y, motionPaths) == 0) {
            Log.e("MotionController", " KeyPath position \"" + motionPaths.f2282j + "\" outside of range");
        }
        this.y.add((-r0) - 1, motionPaths);
    }

    private void y(MotionPaths motionPaths) {
        motionPaths.x((int) this.f2214b.getX(), (int) this.f2214b.getY(), this.f2214b.getWidth(), this.f2214b.getHeight());
    }

    void A(Rect rect, Rect rect2, int i2, int i3, int i4) {
        if (i2 == 1) {
            int i5 = rect.left + rect.right;
            rect2.left = ((rect.top + rect.bottom) - rect.width()) / 2;
            rect2.top = i4 - ((i5 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i2 == 2) {
            int i6 = rect.left + rect.right;
            rect2.left = i3 - (((rect.top + rect.bottom) + rect.width()) / 2);
            rect2.top = (i6 - rect.height()) / 2;
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i2 == 3) {
            int i7 = rect.left + rect.right;
            rect2.left = ((rect.height() / 2) + rect.top) - (i7 / 2);
            rect2.top = i4 - ((i7 + rect.height()) / 2);
            rect2.right = rect2.left + rect.width();
            rect2.bottom = rect2.top + rect.height();
            return;
        }
        if (i2 != 4) {
            return;
        }
        int i8 = rect.left + rect.right;
        rect2.left = i3 - (((rect.bottom + rect.top) + rect.width()) / 2);
        rect2.top = (i8 - rect.height()) / 2;
        rect2.right = rect2.left + rect.width();
        rect2.bottom = rect2.top + rect.height();
    }

    void B(View view) {
        MotionPaths motionPaths = this.f2219g;
        motionPaths.f2281i = 0.0f;
        motionPaths.f2282j = 0.0f;
        this.L = true;
        motionPaths.x(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f2220h.x(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f2221i.p(view);
        this.f2222j.p(view);
    }

    void C(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        int i4 = constraintSet.f2483e;
        if (i4 != 0) {
            A(rect, this.f2213a, i4, i2, i3);
            rect = this.f2213a;
        }
        MotionPaths motionPaths = this.f2220h;
        motionPaths.f2281i = 1.0f;
        motionPaths.f2282j = 1.0f;
        y(motionPaths);
        this.f2220h.x(rect.left, rect.top, rect.width(), rect.height());
        this.f2220h.c(constraintSet.y(this.f2215c));
        this.f2222j.o(rect, constraintSet, i4, this.f2215c);
    }

    public void D(int i2) {
        this.F = i2;
    }

    void E(View view) {
        MotionPaths motionPaths = this.f2219g;
        motionPaths.f2281i = 0.0f;
        motionPaths.f2282j = 0.0f;
        motionPaths.x(view.getX(), view.getY(), view.getWidth(), view.getHeight());
        this.f2221i.p(view);
    }

    void F(Rect rect, ConstraintSet constraintSet, int i2, int i3) {
        int i4 = constraintSet.f2483e;
        if (i4 != 0) {
            A(rect, this.f2213a, i4, i2, i3);
        }
        MotionPaths motionPaths = this.f2219g;
        motionPaths.f2281i = 0.0f;
        motionPaths.f2282j = 0.0f;
        y(motionPaths);
        this.f2219g.x(rect.left, rect.top, rect.width(), rect.height());
        ConstraintSet.Constraint y = constraintSet.y(this.f2215c);
        this.f2219g.c(y);
        this.f2225m = y.f2490d.f2533g;
        this.f2221i.o(rect, constraintSet, i4, this.f2215c);
        this.G = y.f2492f.f2555i;
        ConstraintSet.Motion motion = y.f2490d;
        this.I = motion.f2537k;
        this.J = motion.f2536j;
        Context context = this.f2214b.getContext();
        ConstraintSet.Motion motion2 = y.f2490d;
        this.K = p(context, motion2.f2539m, motion2.f2538l, motion2.f2540n);
    }

    public void G(ViewState viewState, View view, int i2, int i3, int i4) {
        MotionPaths motionPaths = this.f2219g;
        motionPaths.f2281i = 0.0f;
        motionPaths.f2282j = 0.0f;
        Rect rect = new Rect();
        if (i2 == 1) {
            int i5 = viewState.f2106b + viewState.f2108d;
            rect.left = ((viewState.f2107c + viewState.f2109e) - viewState.b()) / 2;
            rect.top = i3 - ((i5 + viewState.a()) / 2);
            rect.right = rect.left + viewState.b();
            rect.bottom = rect.top + viewState.a();
        } else if (i2 == 2) {
            int i6 = viewState.f2106b + viewState.f2108d;
            rect.left = i4 - (((viewState.f2107c + viewState.f2109e) + viewState.b()) / 2);
            rect.top = (i6 - viewState.a()) / 2;
            rect.right = rect.left + viewState.b();
            rect.bottom = rect.top + viewState.a();
        }
        this.f2219g.x(rect.left, rect.top, rect.width(), rect.height());
        this.f2221i.n(rect, view, i2, viewState.f2105a);
    }

    public void H(View view) {
        this.f2214b = view;
        this.f2215c = view.getId();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            this.f2217e = ((ConstraintLayout.LayoutParams) layoutParams).a();
        }
    }

    public void I(int i2, int i3, float f2, long j2) {
        ArrayList arrayList;
        String[] strArr;
        double[][] dArr;
        ConstraintAttribute constraintAttribute;
        ViewTimeCycle h2;
        ConstraintAttribute constraintAttribute2;
        Integer num;
        ViewSpline g2;
        ConstraintAttribute constraintAttribute3;
        new HashSet();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashSet hashSet3 = new HashSet();
        HashMap hashMap = new HashMap();
        int i4 = this.F;
        if (i4 != Key.f2122f) {
            this.f2219g.f2289q = i4;
        }
        this.f2221i.j(this.f2222j, hashSet2);
        ArrayList arrayList2 = this.A;
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            arrayList = null;
            while (it.hasNext()) {
                Key key = (Key) it.next();
                if (key instanceof KeyPosition) {
                    KeyPosition keyPosition = (KeyPosition) key;
                    w(new MotionPaths(i2, i3, keyPosition, this.f2219g, this.f2220h));
                    int i5 = keyPosition.f2171g;
                    if (i5 != Key.f2122f) {
                        this.f2218f = i5;
                    }
                } else if (key instanceof KeyCycle) {
                    key.d(hashSet3);
                } else if (key instanceof KeyTimeCycle) {
                    key.d(hashSet);
                } else if (key instanceof KeyTrigger) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add((KeyTrigger) key);
                } else {
                    key.h(hashMap);
                    key.d(hashSet2);
                }
            }
        } else {
            arrayList = null;
        }
        if (arrayList != null) {
            this.E = (KeyTrigger[]) arrayList.toArray(new KeyTrigger[0]);
        }
        char c2 = 1;
        if (!hashSet2.isEmpty()) {
            this.C = new HashMap();
            Iterator it2 = hashSet2.iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                if (str.startsWith("CUSTOM,")) {
                    SparseArray sparseArray = new SparseArray();
                    String str2 = str.split(",")[1];
                    Iterator it3 = this.A.iterator();
                    while (it3.hasNext()) {
                        Key key2 = (Key) it3.next();
                        HashMap hashMap2 = key2.f2127e;
                        if (hashMap2 != null && (constraintAttribute3 = (ConstraintAttribute) hashMap2.get(str2)) != null) {
                            sparseArray.append(key2.f2123a, constraintAttribute3);
                        }
                    }
                    g2 = ViewSpline.f(str, sparseArray);
                } else {
                    g2 = ViewSpline.g(str);
                }
                if (g2 != null) {
                    g2.d(str);
                    this.C.put(str, g2);
                }
            }
            ArrayList arrayList3 = this.A;
            if (arrayList3 != null) {
                Iterator it4 = arrayList3.iterator();
                while (it4.hasNext()) {
                    Key key3 = (Key) it4.next();
                    if (key3 instanceof KeyAttributes) {
                        key3.a(this.C);
                    }
                }
            }
            this.f2221i.c(this.C, 0);
            this.f2222j.c(this.C, 100);
            for (String str3 : this.C.keySet()) {
                int intValue = (!hashMap.containsKey(str3) || (num = (Integer) hashMap.get(str3)) == null) ? 0 : num.intValue();
                SplineSet splineSet = (SplineSet) this.C.get(str3);
                if (splineSet != null) {
                    splineSet.e(intValue);
                }
            }
        }
        if (!hashSet.isEmpty()) {
            if (this.B == null) {
                this.B = new HashMap();
            }
            Iterator it5 = hashSet.iterator();
            while (it5.hasNext()) {
                String str4 = (String) it5.next();
                if (!this.B.containsKey(str4)) {
                    if (str4.startsWith("CUSTOM,")) {
                        SparseArray sparseArray2 = new SparseArray();
                        String str5 = str4.split(",")[1];
                        Iterator it6 = this.A.iterator();
                        while (it6.hasNext()) {
                            Key key4 = (Key) it6.next();
                            HashMap hashMap3 = key4.f2127e;
                            if (hashMap3 != null && (constraintAttribute2 = (ConstraintAttribute) hashMap3.get(str5)) != null) {
                                sparseArray2.append(key4.f2123a, constraintAttribute2);
                            }
                        }
                        h2 = ViewTimeCycle.g(str4, sparseArray2);
                    } else {
                        h2 = ViewTimeCycle.h(str4, j2);
                    }
                    if (h2 != null) {
                        h2.d(str4);
                        this.B.put(str4, h2);
                    }
                }
            }
            ArrayList arrayList4 = this.A;
            if (arrayList4 != null) {
                Iterator it7 = arrayList4.iterator();
                while (it7.hasNext()) {
                    Key key5 = (Key) it7.next();
                    if (key5 instanceof KeyTimeCycle) {
                        ((KeyTimeCycle) key5).U(this.B);
                    }
                }
            }
            for (String str6 : this.B.keySet()) {
                ((ViewTimeCycle) this.B.get(str6)).e(hashMap.containsKey(str6) ? ((Integer) hashMap.get(str6)).intValue() : 0);
            }
        }
        int size = this.y.size();
        int i6 = size + 2;
        MotionPaths[] motionPathsArr = new MotionPaths[i6];
        motionPathsArr[0] = this.f2219g;
        motionPathsArr[size + 1] = this.f2220h;
        if (this.y.size() > 0 && this.f2218f == -1) {
            this.f2218f = 0;
        }
        Iterator it8 = this.y.iterator();
        int i7 = 1;
        while (it8.hasNext()) {
            motionPathsArr[i7] = (MotionPaths) it8.next();
            i7++;
        }
        HashSet hashSet4 = new HashSet();
        for (String str7 : this.f2220h.u.keySet()) {
            if (this.f2219g.u.containsKey(str7)) {
                if (!hashSet2.contains("CUSTOM," + str7)) {
                    hashSet4.add(str7);
                }
            }
        }
        String[] strArr2 = (String[]) hashSet4.toArray(new String[0]);
        this.u = strArr2;
        this.v = new int[strArr2.length];
        int i8 = 0;
        while (true) {
            strArr = this.u;
            if (i8 >= strArr.length) {
                break;
            }
            String str8 = strArr[i8];
            this.v[i8] = 0;
            int i9 = 0;
            while (true) {
                if (i9 >= i6) {
                    break;
                }
                if (motionPathsArr[i9].u.containsKey(str8) && (constraintAttribute = (ConstraintAttribute) motionPathsArr[i9].u.get(str8)) != null) {
                    int[] iArr = this.v;
                    iArr[i8] = iArr[i8] + constraintAttribute.h();
                    break;
                }
                i9++;
            }
            i8++;
        }
        boolean z = motionPathsArr[0].f2289q != Key.f2122f;
        int length = 18 + strArr.length;
        boolean[] zArr = new boolean[length];
        for (int i10 = 1; i10 < i6; i10++) {
            motionPathsArr[i10].f(motionPathsArr[i10 - 1], zArr, this.u, z);
        }
        int i11 = 0;
        for (int i12 = 1; i12 < length; i12++) {
            if (zArr[i12]) {
                i11++;
            }
        }
        this.f2230r = new int[i11];
        int i13 = 2;
        int max = Math.max(2, i11);
        this.f2231s = new double[max];
        this.t = new double[max];
        int i14 = 0;
        for (int i15 = 1; i15 < length; i15++) {
            if (zArr[i15]) {
                this.f2230r[i14] = i15;
                i14++;
            }
        }
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i6, this.f2230r.length);
        double[] dArr3 = new double[i6];
        for (int i16 = 0; i16 < i6; i16++) {
            motionPathsArr[i16].h(dArr2[i16], this.f2230r);
            dArr3[i16] = motionPathsArr[i16].f2281i;
        }
        int i17 = 0;
        while (true) {
            int[] iArr2 = this.f2230r;
            if (i17 >= iArr2.length) {
                break;
            }
            if (iArr2[i17] < MotionPaths.z.length) {
                String str9 = MotionPaths.z[this.f2230r[i17]] + " [";
                for (int i18 = 0; i18 < i6; i18++) {
                    str9 = str9 + dArr2[i18][i17];
                }
            }
            i17++;
        }
        this.f2223k = new CurveFit[this.u.length + 1];
        int i19 = 0;
        while (true) {
            String[] strArr3 = this.u;
            if (i19 >= strArr3.length) {
                break;
            }
            String str10 = strArr3[i19];
            int i20 = 0;
            int i21 = 0;
            double[] dArr4 = null;
            double[][] dArr5 = null;
            while (i20 < i6) {
                if (motionPathsArr[i20].r(str10)) {
                    if (dArr5 == null) {
                        dArr4 = new double[i6];
                        int[] iArr3 = new int[i13];
                        iArr3[c2] = motionPathsArr[i20].o(str10);
                        iArr3[0] = i6;
                        dArr5 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, iArr3);
                    }
                    MotionPaths motionPaths = motionPathsArr[i20];
                    dArr = dArr2;
                    dArr4[i21] = motionPaths.f2281i;
                    motionPaths.n(str10, dArr5[i21], 0);
                    i21++;
                } else {
                    dArr = dArr2;
                }
                i20++;
                dArr2 = dArr;
                i13 = 2;
                c2 = 1;
            }
            i19++;
            this.f2223k[i19] = CurveFit.a(this.f2218f, Arrays.copyOf(dArr4, i21), (double[][]) Arrays.copyOf(dArr5, i21));
            dArr2 = dArr2;
            i13 = 2;
            c2 = 1;
        }
        this.f2223k[0] = CurveFit.a(this.f2218f, dArr3, dArr2);
        if (motionPathsArr[0].f2289q != Key.f2122f) {
            int[] iArr4 = new int[i6];
            double[] dArr6 = new double[i6];
            double[][] dArr7 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, i6, 2);
            for (int i22 = 0; i22 < i6; i22++) {
                iArr4[i22] = motionPathsArr[i22].f2289q;
                dArr6[i22] = r9.f2281i;
                double[] dArr8 = dArr7[i22];
                dArr8[0] = r9.f2283k;
                dArr8[1] = r9.f2284l;
            }
            this.f2224l = CurveFit.b(iArr4, dArr6, dArr7);
        }
        this.D = new HashMap();
        if (this.A != null) {
            Iterator it9 = hashSet3.iterator();
            float f3 = Float.NaN;
            while (it9.hasNext()) {
                String str11 = (String) it9.next();
                ViewOscillator i23 = ViewOscillator.i(str11);
                if (i23 != null) {
                    if (i23.h() && Float.isNaN(f3)) {
                        f3 = s();
                    }
                    i23.f(str11);
                    this.D.put(str11, i23);
                }
            }
            Iterator it10 = this.A.iterator();
            while (it10.hasNext()) {
                Key key6 = (Key) it10.next();
                if (key6 instanceof KeyCycle) {
                    ((KeyCycle) key6).Y(this.D);
                }
            }
            Iterator it11 = this.D.values().iterator();
            while (it11.hasNext()) {
                ((ViewOscillator) it11.next()).g(f3);
            }
        }
    }

    public void J(MotionController motionController) {
        this.f2219g.A(motionController, motionController.f2219g);
        this.f2220h.A(motionController, motionController.f2220h);
    }

    public void a(Key key) {
        this.A.add(key);
    }

    void b(ArrayList arrayList) {
        this.A.addAll(arrayList);
    }

    int c(float[] fArr, int[] iArr) {
        if (fArr == null) {
            return 0;
        }
        double[] h2 = this.f2223k[0].h();
        if (iArr != null) {
            Iterator it = this.y.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                iArr[i2] = ((MotionPaths) it.next()).v;
                i2++;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < h2.length; i4++) {
            this.f2223k[0].d(h2[i4], this.f2231s);
            this.f2219g.j(h2[i4], this.f2230r, this.f2231s, fArr, i3);
            i3 += 2;
        }
        return i3 / 2;
    }

    void d(float[] fArr, int i2) {
        double d2;
        float f2 = 1.0f;
        float f3 = 1.0f / (i2 - 1);
        HashMap hashMap = this.C;
        SplineSet splineSet = hashMap == null ? null : (SplineSet) hashMap.get("translationX");
        HashMap hashMap2 = this.C;
        SplineSet splineSet2 = hashMap2 == null ? null : (SplineSet) hashMap2.get("translationY");
        HashMap hashMap3 = this.D;
        ViewOscillator viewOscillator = hashMap3 == null ? null : (ViewOscillator) hashMap3.get("translationX");
        HashMap hashMap4 = this.D;
        ViewOscillator viewOscillator2 = hashMap4 != null ? (ViewOscillator) hashMap4.get("translationY") : null;
        int i3 = 0;
        while (i3 < i2) {
            float f4 = i3 * f3;
            float f5 = this.f2227o;
            float f6 = 0.0f;
            if (f5 != f2) {
                float f7 = this.f2226n;
                if (f4 < f7) {
                    f4 = 0.0f;
                }
                if (f4 > f7 && f4 < 1.0d) {
                    f4 = Math.min((f4 - f7) * f5, f2);
                }
            }
            float f8 = f4;
            double d3 = f8;
            Easing easing = this.f2219g.f2279c;
            Iterator it = this.y.iterator();
            float f9 = Float.NaN;
            while (it.hasNext()) {
                MotionPaths motionPaths = (MotionPaths) it.next();
                Easing easing2 = motionPaths.f2279c;
                double d4 = d3;
                if (easing2 != null) {
                    float f10 = motionPaths.f2281i;
                    if (f10 < f8) {
                        f6 = f10;
                        easing = easing2;
                    } else if (Float.isNaN(f9)) {
                        f9 = motionPaths.f2281i;
                    }
                }
                d3 = d4;
            }
            double d5 = d3;
            if (easing != null) {
                if (Float.isNaN(f9)) {
                    f9 = 1.0f;
                }
                d2 = (((float) easing.a((f8 - f6) / r16)) * (f9 - f6)) + f6;
            } else {
                d2 = d5;
            }
            this.f2223k[0].d(d2, this.f2231s);
            CurveFit curveFit = this.f2224l;
            if (curveFit != null) {
                double[] dArr = this.f2231s;
                if (dArr.length > 0) {
                    curveFit.d(d2, dArr);
                }
            }
            int i4 = i3 * 2;
            int i5 = i3;
            this.f2219g.j(d2, this.f2230r, this.f2231s, fArr, i4);
            if (viewOscillator != null) {
                fArr[i4] = fArr[i4] + viewOscillator.a(f8);
            } else if (splineSet != null) {
                fArr[i4] = fArr[i4] + splineSet.a(f8);
            }
            if (viewOscillator2 != null) {
                int i6 = i4 + 1;
                fArr[i6] = fArr[i6] + viewOscillator2.a(f8);
            } else if (splineSet2 != null) {
                int i7 = i4 + 1;
                fArr[i7] = fArr[i7] + splineSet2.a(f8);
            }
            i3 = i5 + 1;
            f2 = 1.0f;
        }
    }

    void e(float f2, float[] fArr, int i2) {
        this.f2223k[0].d(g(f2, null), this.f2231s);
        this.f2219g.p(this.f2230r, this.f2231s, fArr, i2);
    }

    void f(boolean z) {
        if (!"button".equals(Debug.d(this.f2214b)) || this.E == null) {
            return;
        }
        int i2 = 0;
        while (true) {
            KeyTrigger[] keyTriggerArr = this.E;
            if (i2 >= keyTriggerArr.length) {
                return;
            }
            keyTriggerArr[i2].y(z ? -100.0f : 100.0f, this.f2214b);
            i2++;
        }
    }

    public int h() {
        return this.f2219g.f2290r;
    }

    public void i(double d2, float[] fArr, float[] fArr2) {
        double[] dArr = new double[4];
        double[] dArr2 = new double[4];
        this.f2223k[0].d(d2, dArr);
        this.f2223k[0].g(d2, dArr2);
        Arrays.fill(fArr2, 0.0f);
        this.f2219g.l(d2, this.f2230r, dArr, fArr, dArr2, fArr2);
    }

    public float j() {
        return this.f2228p;
    }

    public float k() {
        return this.f2229q;
    }

    void l(float f2, float f3, float f4, float[] fArr) {
        double[] dArr;
        float g2 = g(f2, this.z);
        CurveFit[] curveFitArr = this.f2223k;
        int i2 = 0;
        if (curveFitArr == null) {
            MotionPaths motionPaths = this.f2220h;
            float f5 = motionPaths.f2283k;
            MotionPaths motionPaths2 = this.f2219g;
            float f6 = f5 - motionPaths2.f2283k;
            float f7 = motionPaths.f2284l - motionPaths2.f2284l;
            float f8 = (motionPaths.f2285m - motionPaths2.f2285m) + f6;
            float f9 = (motionPaths.f2286n - motionPaths2.f2286n) + f7;
            fArr[0] = (f6 * (1.0f - f3)) + (f8 * f3);
            fArr[1] = (f7 * (1.0f - f4)) + (f9 * f4);
            return;
        }
        double d2 = g2;
        curveFitArr[0].g(d2, this.t);
        this.f2223k[0].d(d2, this.f2231s);
        float f10 = this.z[0];
        while (true) {
            dArr = this.t;
            if (i2 >= dArr.length) {
                break;
            }
            dArr[i2] = dArr[i2] * f10;
            i2++;
        }
        CurveFit curveFit = this.f2224l;
        if (curveFit == null) {
            this.f2219g.y(f3, f4, fArr, this.f2230r, dArr, this.f2231s);
            return;
        }
        double[] dArr2 = this.f2231s;
        if (dArr2.length > 0) {
            curveFit.d(d2, dArr2);
            this.f2224l.g(d2, this.t);
            this.f2219g.y(f3, f4, fArr, this.f2230r, this.t, this.f2231s);
        }
    }

    public int m() {
        int i2 = this.f2219g.f2280h;
        Iterator it = this.y.iterator();
        while (it.hasNext()) {
            i2 = Math.max(i2, ((MotionPaths) it.next()).f2280h);
        }
        return Math.max(i2, this.f2220h.f2280h);
    }

    public float n() {
        return this.f2220h.f2283k;
    }

    public float o() {
        return this.f2220h.f2284l;
    }

    MotionPaths q(int i2) {
        return (MotionPaths) this.y.get(i2);
    }

    void r(float f2, int i2, int i3, float f3, float f4, float[] fArr) {
        float g2 = g(f2, this.z);
        HashMap hashMap = this.C;
        SplineSet splineSet = hashMap == null ? null : (SplineSet) hashMap.get("translationX");
        HashMap hashMap2 = this.C;
        SplineSet splineSet2 = hashMap2 == null ? null : (SplineSet) hashMap2.get("translationY");
        HashMap hashMap3 = this.C;
        SplineSet splineSet3 = hashMap3 == null ? null : (SplineSet) hashMap3.get("rotation");
        HashMap hashMap4 = this.C;
        SplineSet splineSet4 = hashMap4 == null ? null : (SplineSet) hashMap4.get("scaleX");
        HashMap hashMap5 = this.C;
        SplineSet splineSet5 = hashMap5 == null ? null : (SplineSet) hashMap5.get("scaleY");
        HashMap hashMap6 = this.D;
        ViewOscillator viewOscillator = hashMap6 == null ? null : (ViewOscillator) hashMap6.get("translationX");
        HashMap hashMap7 = this.D;
        ViewOscillator viewOscillator2 = hashMap7 == null ? null : (ViewOscillator) hashMap7.get("translationY");
        HashMap hashMap8 = this.D;
        ViewOscillator viewOscillator3 = hashMap8 == null ? null : (ViewOscillator) hashMap8.get("rotation");
        HashMap hashMap9 = this.D;
        ViewOscillator viewOscillator4 = hashMap9 == null ? null : (ViewOscillator) hashMap9.get("scaleX");
        HashMap hashMap10 = this.D;
        ViewOscillator viewOscillator5 = hashMap10 != null ? (ViewOscillator) hashMap10.get("scaleY") : null;
        VelocityMatrix velocityMatrix = new VelocityMatrix();
        velocityMatrix.b();
        velocityMatrix.d(splineSet3, g2);
        velocityMatrix.h(splineSet, splineSet2, g2);
        velocityMatrix.f(splineSet4, splineSet5, g2);
        velocityMatrix.c(viewOscillator3, g2);
        velocityMatrix.g(viewOscillator, viewOscillator2, g2);
        velocityMatrix.e(viewOscillator4, viewOscillator5, g2);
        CurveFit curveFit = this.f2224l;
        if (curveFit != null) {
            double[] dArr = this.f2231s;
            if (dArr.length > 0) {
                double d2 = g2;
                curveFit.d(d2, dArr);
                this.f2224l.g(d2, this.t);
                this.f2219g.y(f3, f4, fArr, this.f2230r, this.t, this.f2231s);
            }
            velocityMatrix.a(f3, f4, i2, i3, fArr);
            return;
        }
        int i4 = 0;
        if (this.f2223k == null) {
            MotionPaths motionPaths = this.f2220h;
            float f5 = motionPaths.f2283k;
            MotionPaths motionPaths2 = this.f2219g;
            float f6 = f5 - motionPaths2.f2283k;
            ViewOscillator viewOscillator6 = viewOscillator5;
            float f7 = motionPaths.f2284l - motionPaths2.f2284l;
            ViewOscillator viewOscillator7 = viewOscillator4;
            float f8 = (motionPaths.f2285m - motionPaths2.f2285m) + f6;
            float f9 = (motionPaths.f2286n - motionPaths2.f2286n) + f7;
            fArr[0] = (f6 * (1.0f - f3)) + (f8 * f3);
            fArr[1] = (f7 * (1.0f - f4)) + (f9 * f4);
            velocityMatrix.b();
            velocityMatrix.d(splineSet3, g2);
            velocityMatrix.h(splineSet, splineSet2, g2);
            velocityMatrix.f(splineSet4, splineSet5, g2);
            velocityMatrix.c(viewOscillator3, g2);
            velocityMatrix.g(viewOscillator, viewOscillator2, g2);
            velocityMatrix.e(viewOscillator7, viewOscillator6, g2);
            velocityMatrix.a(f3, f4, i2, i3, fArr);
            return;
        }
        double g3 = g(g2, this.z);
        this.f2223k[0].g(g3, this.t);
        this.f2223k[0].d(g3, this.f2231s);
        float f10 = this.z[0];
        while (true) {
            double[] dArr2 = this.t;
            if (i4 >= dArr2.length) {
                this.f2219g.y(f3, f4, fArr, this.f2230r, dArr2, this.f2231s);
                velocityMatrix.a(f3, f4, i2, i3, fArr);
                return;
            } else {
                dArr2[i4] = dArr2[i4] * f10;
                i4++;
            }
        }
    }

    public float t() {
        return this.f2219g.f2283k;
    }

    public String toString() {
        return " start: x: " + this.f2219g.f2283k + " y: " + this.f2219g.f2284l + " end: x: " + this.f2220h.f2283k + " y: " + this.f2220h.f2284l;
    }

    public float u() {
        return this.f2219g.f2284l;
    }

    public View v() {
        return this.f2214b;
    }

    boolean x(View view, float f2, long j2, KeyCache keyCache) {
        ViewTimeCycle.PathRotate pathRotate;
        boolean z;
        int i2;
        double d2;
        float g2 = g(f2, null);
        int i3 = this.I;
        if (i3 != Key.f2122f) {
            float f3 = 1.0f / i3;
            float floor = ((float) Math.floor(g2 / f3)) * f3;
            float f4 = (g2 % f3) / f3;
            if (!Float.isNaN(this.J)) {
                f4 = (f4 + this.J) % 1.0f;
            }
            Interpolator interpolator = this.K;
            g2 = ((interpolator != null ? interpolator.getInterpolation(f4) : ((double) f4) > 0.5d ? 1.0f : 0.0f) * f3) + floor;
        }
        float f5 = g2;
        HashMap hashMap = this.C;
        if (hashMap != null) {
            Iterator it = hashMap.values().iterator();
            while (it.hasNext()) {
                ((ViewSpline) it.next()).h(view, f5);
            }
        }
        HashMap hashMap2 = this.B;
        if (hashMap2 != null) {
            ViewTimeCycle.PathRotate pathRotate2 = null;
            boolean z2 = false;
            for (ViewTimeCycle viewTimeCycle : hashMap2.values()) {
                if (viewTimeCycle instanceof ViewTimeCycle.PathRotate) {
                    pathRotate2 = (ViewTimeCycle.PathRotate) viewTimeCycle;
                } else {
                    z2 |= viewTimeCycle.i(view, f5, j2, keyCache);
                }
            }
            z = z2;
            pathRotate = pathRotate2;
        } else {
            pathRotate = null;
            z = false;
        }
        CurveFit[] curveFitArr = this.f2223k;
        if (curveFitArr != null) {
            double d3 = f5;
            curveFitArr[0].d(d3, this.f2231s);
            this.f2223k[0].g(d3, this.t);
            CurveFit curveFit = this.f2224l;
            if (curveFit != null) {
                double[] dArr = this.f2231s;
                if (dArr.length > 0) {
                    curveFit.d(d3, dArr);
                    this.f2224l.g(d3, this.t);
                }
            }
            if (this.L) {
                d2 = d3;
            } else {
                d2 = d3;
                this.f2219g.z(f5, view, this.f2230r, this.f2231s, this.t, null, this.f2216d);
                this.f2216d = false;
            }
            if (this.G != Key.f2122f) {
                if (this.H == null) {
                    this.H = ((View) view.getParent()).findViewById(this.G);
                }
                if (this.H != null) {
                    float top = (r1.getTop() + this.H.getBottom()) / 2.0f;
                    float left = (this.H.getLeft() + this.H.getRight()) / 2.0f;
                    if (view.getRight() - view.getLeft() > 0 && view.getBottom() - view.getTop() > 0) {
                        view.setPivotX(left - view.getLeft());
                        view.setPivotY(top - view.getTop());
                    }
                }
            }
            HashMap hashMap3 = this.C;
            if (hashMap3 != null) {
                for (SplineSet splineSet : hashMap3.values()) {
                    if (splineSet instanceof ViewSpline.PathRotate) {
                        double[] dArr2 = this.t;
                        if (dArr2.length > 1) {
                            ((ViewSpline.PathRotate) splineSet).i(view, f5, dArr2[0], dArr2[1]);
                        }
                    }
                }
            }
            if (pathRotate != null) {
                double[] dArr3 = this.t;
                i2 = 1;
                z |= pathRotate.j(view, keyCache, f5, j2, dArr3[0], dArr3[1]);
            } else {
                i2 = 1;
            }
            int i4 = i2;
            while (true) {
                CurveFit[] curveFitArr2 = this.f2223k;
                if (i4 >= curveFitArr2.length) {
                    break;
                }
                curveFitArr2[i4].e(d2, this.x);
                CustomSupport.b((ConstraintAttribute) this.f2219g.u.get(this.u[i4 - 1]), view, this.x);
                i4++;
            }
            MotionConstrainedPoint motionConstrainedPoint = this.f2221i;
            if (motionConstrainedPoint.f2201h == 0) {
                if (f5 <= 0.0f) {
                    view.setVisibility(motionConstrainedPoint.f2202i);
                } else if (f5 >= 1.0f) {
                    view.setVisibility(this.f2222j.f2202i);
                } else if (this.f2222j.f2202i != motionConstrainedPoint.f2202i) {
                    view.setVisibility(0);
                }
            }
            if (this.E != null) {
                int i5 = 0;
                while (true) {
                    KeyTrigger[] keyTriggerArr = this.E;
                    if (i5 >= keyTriggerArr.length) {
                        break;
                    }
                    keyTriggerArr[i5].y(f5, view);
                    i5++;
                }
            }
        } else {
            i2 = 1;
            MotionPaths motionPaths = this.f2219g;
            float f6 = motionPaths.f2283k;
            MotionPaths motionPaths2 = this.f2220h;
            float f7 = f6 + ((motionPaths2.f2283k - f6) * f5);
            float f8 = motionPaths.f2284l;
            float f9 = f8 + ((motionPaths2.f2284l - f8) * f5);
            float f10 = motionPaths.f2285m;
            float f11 = motionPaths2.f2285m;
            float f12 = motionPaths.f2286n;
            float f13 = motionPaths2.f2286n;
            float f14 = f7 + 0.5f;
            int i6 = (int) f14;
            float f15 = f9 + 0.5f;
            int i7 = (int) f15;
            int i8 = (int) (f14 + ((f11 - f10) * f5) + f10);
            int i9 = (int) (f15 + ((f13 - f12) * f5) + f12);
            int i10 = i8 - i6;
            int i11 = i9 - i7;
            if (f11 != f10 || f13 != f12 || this.f2216d) {
                view.measure(View.MeasureSpec.makeMeasureSpec(i10, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(i11, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
                this.f2216d = false;
            }
            view.layout(i6, i7, i8, i9);
        }
        HashMap hashMap4 = this.D;
        if (hashMap4 != null) {
            for (ViewOscillator viewOscillator : hashMap4.values()) {
                if (viewOscillator instanceof ViewOscillator.PathRotateSet) {
                    double[] dArr4 = this.t;
                    ((ViewOscillator.PathRotateSet) viewOscillator).k(view, f5, dArr4[0], dArr4[i2]);
                } else {
                    viewOscillator.j(view, f5);
                }
            }
        }
        return z;
    }

    public void z() {
        this.f2216d = true;
    }
}
