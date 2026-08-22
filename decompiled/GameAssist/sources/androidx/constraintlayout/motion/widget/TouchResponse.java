package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.R;
import androidx.core.widget.NestedScrollView;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
class TouchResponse {
    private static final float[][] G = {new float[]{0.5f, 0.0f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}, new float[]{0.5f, 1.0f}, new float[]{0.5f, 0.5f}, new float[]{0.0f, 0.5f}, new float[]{1.0f, 0.5f}};
    private static final float[][] H = {new float[]{0.0f, -1.0f}, new float[]{0.0f, 1.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}, new float[]{-1.0f, 0.0f}, new float[]{1.0f, 0.0f}};

    /* renamed from: r, reason: collision with root package name */
    private float f2351r;

    /* renamed from: s, reason: collision with root package name */
    private float f2352s;
    private final MotionLayout t;

    /* renamed from: a, reason: collision with root package name */
    private int f2334a = 0;

    /* renamed from: b, reason: collision with root package name */
    private int f2335b = 0;

    /* renamed from: c, reason: collision with root package name */
    private int f2336c = 0;

    /* renamed from: d, reason: collision with root package name */
    private int f2337d = -1;

    /* renamed from: e, reason: collision with root package name */
    private int f2338e = -1;

    /* renamed from: f, reason: collision with root package name */
    private int f2339f = -1;

    /* renamed from: g, reason: collision with root package name */
    private float f2340g = 0.5f;

    /* renamed from: h, reason: collision with root package name */
    private float f2341h = 0.5f;

    /* renamed from: i, reason: collision with root package name */
    float f2342i = 0.5f;

    /* renamed from: j, reason: collision with root package name */
    float f2343j = 0.5f;

    /* renamed from: k, reason: collision with root package name */
    private int f2344k = -1;

    /* renamed from: l, reason: collision with root package name */
    boolean f2345l = false;

    /* renamed from: m, reason: collision with root package name */
    private float f2346m = 0.0f;

    /* renamed from: n, reason: collision with root package name */
    private float f2347n = 1.0f;

    /* renamed from: o, reason: collision with root package name */
    private boolean f2348o = false;

    /* renamed from: p, reason: collision with root package name */
    private float[] f2349p = new float[2];

    /* renamed from: q, reason: collision with root package name */
    private int[] f2350q = new int[2];
    private float u = 4.0f;
    private float v = 1.2f;
    private boolean w = true;
    private float x = 1.0f;
    private int y = 0;
    private float z = 10.0f;
    private float A = 10.0f;
    private float B = 1.0f;
    private float C = Float.NaN;
    private float D = Float.NaN;
    private int E = 0;
    private int F = 0;

    TouchResponse(Context context, MotionLayout motionLayout, XmlPullParser xmlPullParser) {
        this.t = motionLayout;
        c(context, Xml.asAttributeSet(xmlPullParser));
    }

    private void b(TypedArray typedArray) {
        int indexCount = typedArray.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArray.getIndex(i2);
            if (index == R.styleable.OnSwipe_touchAnchorId) {
                this.f2337d = typedArray.getResourceId(index, this.f2337d);
            } else if (index == R.styleable.OnSwipe_touchAnchorSide) {
                int i3 = typedArray.getInt(index, this.f2334a);
                this.f2334a = i3;
                float[] fArr = G[i3];
                this.f2341h = fArr[0];
                this.f2340g = fArr[1];
            } else if (index == R.styleable.OnSwipe_dragDirection) {
                int i4 = typedArray.getInt(index, this.f2335b);
                this.f2335b = i4;
                float[][] fArr2 = H;
                if (i4 < fArr2.length) {
                    float[] fArr3 = fArr2[i4];
                    this.f2346m = fArr3[0];
                    this.f2347n = fArr3[1];
                } else {
                    this.f2347n = Float.NaN;
                    this.f2346m = Float.NaN;
                    this.f2345l = true;
                }
            } else if (index == R.styleable.OnSwipe_maxVelocity) {
                this.u = typedArray.getFloat(index, this.u);
            } else if (index == R.styleable.OnSwipe_maxAcceleration) {
                this.v = typedArray.getFloat(index, this.v);
            } else if (index == R.styleable.OnSwipe_moveWhenScrollAtTop) {
                this.w = typedArray.getBoolean(index, this.w);
            } else if (index == R.styleable.OnSwipe_dragScale) {
                this.x = typedArray.getFloat(index, this.x);
            } else if (index == R.styleable.OnSwipe_dragThreshold) {
                this.z = typedArray.getFloat(index, this.z);
            } else if (index == R.styleable.OnSwipe_touchRegionId) {
                this.f2338e = typedArray.getResourceId(index, this.f2338e);
            } else if (index == R.styleable.OnSwipe_onTouchUp) {
                this.f2336c = typedArray.getInt(index, this.f2336c);
            } else if (index == R.styleable.OnSwipe_nestedScrollFlags) {
                this.y = typedArray.getInteger(index, 0);
            } else if (index == R.styleable.OnSwipe_limitBoundsTo) {
                this.f2339f = typedArray.getResourceId(index, 0);
            } else if (index == R.styleable.OnSwipe_rotationCenterId) {
                this.f2344k = typedArray.getResourceId(index, this.f2344k);
            } else if (index == R.styleable.OnSwipe_springDamping) {
                this.A = typedArray.getFloat(index, this.A);
            } else if (index == R.styleable.OnSwipe_springMass) {
                this.B = typedArray.getFloat(index, this.B);
            } else if (index == R.styleable.OnSwipe_springStiffness) {
                this.C = typedArray.getFloat(index, this.C);
            } else if (index == R.styleable.OnSwipe_springStopThreshold) {
                this.D = typedArray.getFloat(index, this.D);
            } else if (index == R.styleable.OnSwipe_springBoundary) {
                this.E = typedArray.getInt(index, this.E);
            } else if (index == R.styleable.OnSwipe_autoCompleteMode) {
                this.F = typedArray.getInt(index, this.F);
            }
        }
    }

    private void c(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OnSwipe);
        b(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }

    void A() {
        View view;
        int i2 = this.f2337d;
        if (i2 != -1) {
            view = this.t.findViewById(i2);
            if (view == null) {
                Log.e("TouchResponse", "cannot find TouchAnchorId @id/" + Debug.c(this.t.getContext(), this.f2337d));
            }
        } else {
            view = null;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView nestedScrollView = (NestedScrollView) view;
            nestedScrollView.setOnTouchListener(new View.OnTouchListener() { // from class: androidx.constraintlayout.motion.widget.TouchResponse.1
                @Override // android.view.View.OnTouchListener
                public boolean onTouch(View view2, MotionEvent motionEvent) {
                    return false;
                }
            });
            nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: androidx.constraintlayout.motion.widget.TouchResponse.2
                @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
                public void a(NestedScrollView nestedScrollView2, int i3, int i4, int i5, int i6) {
                }
            });
        }
    }

    float a(float f2, float f3) {
        return (f2 * this.f2346m) + (f3 * this.f2347n);
    }

    public int d() {
        return this.F;
    }

    public int e() {
        return this.y;
    }

    RectF f(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i2 = this.f2339f;
        if (i2 == -1 || (findViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    float g() {
        return this.v;
    }

    public float h() {
        return this.u;
    }

    boolean i() {
        return this.w;
    }

    float j(float f2, float f3) {
        this.t.p0(this.f2337d, this.t.getProgress(), this.f2341h, this.f2340g, this.f2349p);
        float f4 = this.f2346m;
        if (f4 != 0.0f) {
            float[] fArr = this.f2349p;
            if (fArr[0] == 0.0f) {
                fArr[0] = 1.0E-7f;
            }
            return (f2 * f4) / fArr[0];
        }
        float[] fArr2 = this.f2349p;
        if (fArr2[1] == 0.0f) {
            fArr2[1] = 1.0E-7f;
        }
        return (f3 * this.f2347n) / fArr2[1];
    }

    public int k() {
        return this.E;
    }

    public float l() {
        return this.A;
    }

    public float m() {
        return this.B;
    }

    public float n() {
        return this.C;
    }

    public float o() {
        return this.D;
    }

    RectF p(ViewGroup viewGroup, RectF rectF) {
        View findViewById;
        int i2 = this.f2338e;
        if (i2 == -1 || (findViewById = viewGroup.findViewById(i2)) == null) {
            return null;
        }
        rectF.set(findViewById.getLeft(), findViewById.getTop(), findViewById.getRight(), findViewById.getBottom());
        return rectF;
    }

    int q() {
        return this.f2338e;
    }

    boolean r() {
        return this.f2348o;
    }

    void s(MotionEvent motionEvent, MotionLayout.MotionTracker motionTracker, int i2, MotionScene motionScene) {
        int i3;
        if (this.f2345l) {
            t(motionEvent, motionTracker, i2, motionScene);
            return;
        }
        motionTracker.a(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f2351r = motionEvent.getRawX();
            this.f2352s = motionEvent.getRawY();
            this.f2348o = false;
            return;
        }
        if (action == 1) {
            this.f2348o = false;
            motionTracker.e(1000);
            float c2 = motionTracker.c();
            float b2 = motionTracker.b();
            float progress = this.t.getProgress();
            int i4 = this.f2337d;
            if (i4 != -1) {
                this.t.p0(i4, progress, this.f2341h, this.f2340g, this.f2349p);
            } else {
                float min = Math.min(this.t.getWidth(), this.t.getHeight());
                float[] fArr = this.f2349p;
                fArr[1] = this.f2347n * min;
                fArr[0] = min * this.f2346m;
            }
            float f2 = this.f2346m;
            float[] fArr2 = this.f2349p;
            float f3 = f2 != 0.0f ? c2 / fArr2[0] : b2 / fArr2[1];
            float f4 = !Float.isNaN(f3) ? (f3 / 3.0f) + progress : progress;
            if (f4 == 0.0f || f4 == 1.0f || (i3 = this.f2336c) == 3) {
                if (0.0f >= f4 || 1.0f <= f4) {
                    this.t.setState(MotionLayout.TransitionState.FINISHED);
                    return;
                }
                return;
            }
            float f5 = ((double) f4) < 0.5d ? 0.0f : 1.0f;
            if (i3 == 6) {
                if (progress + f3 < 0.0f) {
                    f3 = Math.abs(f3);
                }
                f5 = 1.0f;
            }
            if (this.f2336c == 7) {
                if (progress + f3 > 1.0f) {
                    f3 = -Math.abs(f3);
                }
                f5 = 0.0f;
            }
            this.t.G0(this.f2336c, f5, f3);
            if (0.0f >= progress || 1.0f <= progress) {
                this.t.setState(MotionLayout.TransitionState.FINISHED);
                return;
            }
            return;
        }
        if (action != 2) {
            return;
        }
        float rawY = motionEvent.getRawY() - this.f2352s;
        float rawX = motionEvent.getRawX() - this.f2351r;
        if (Math.abs((this.f2346m * rawX) + (this.f2347n * rawY)) > this.z || this.f2348o) {
            float progress2 = this.t.getProgress();
            if (!this.f2348o) {
                this.f2348o = true;
                this.t.setProgress(progress2);
            }
            int i5 = this.f2337d;
            if (i5 != -1) {
                this.t.p0(i5, progress2, this.f2341h, this.f2340g, this.f2349p);
            } else {
                float min2 = Math.min(this.t.getWidth(), this.t.getHeight());
                float[] fArr3 = this.f2349p;
                fArr3[1] = this.f2347n * min2;
                fArr3[0] = min2 * this.f2346m;
            }
            float f6 = this.f2346m;
            float[] fArr4 = this.f2349p;
            if (Math.abs(((f6 * fArr4[0]) + (this.f2347n * fArr4[1])) * this.x) < 0.01d) {
                float[] fArr5 = this.f2349p;
                fArr5[0] = 0.01f;
                fArr5[1] = 0.01f;
            }
            float max = Math.max(Math.min(progress2 + (this.f2346m != 0.0f ? rawX / this.f2349p[0] : rawY / this.f2349p[1]), 1.0f), 0.0f);
            if (this.f2336c == 6) {
                max = Math.max(max, 0.01f);
            }
            if (this.f2336c == 7) {
                max = Math.min(max, 0.99f);
            }
            float progress3 = this.t.getProgress();
            if (max != progress3) {
                if (progress3 == 0.0f || progress3 == 1.0f) {
                    this.t.i0(progress3 == 0.0f);
                }
                this.t.setProgress(max);
                motionTracker.e(1000);
                this.t.mLastVelocity = this.f2346m != 0.0f ? motionTracker.c() / this.f2349p[0] : motionTracker.b() / this.f2349p[1];
            } else {
                this.t.mLastVelocity = 0.0f;
            }
            this.f2351r = motionEvent.getRawX();
            this.f2352s = motionEvent.getRawY();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0294  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void t(android.view.MotionEvent r24, androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker r25, int r26, androidx.constraintlayout.motion.widget.MotionScene r27) {
        /*
            Method dump skipped, instructions count: 834
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.TouchResponse.t(android.view.MotionEvent, androidx.constraintlayout.motion.widget.MotionLayout$MotionTracker, int, androidx.constraintlayout.motion.widget.MotionScene):void");
    }

    public String toString() {
        if (Float.isNaN(this.f2346m)) {
            return "rotation";
        }
        return this.f2346m + " , " + this.f2347n;
    }

    void u(float f2, float f3) {
        float progress = this.t.getProgress();
        if (!this.f2348o) {
            this.f2348o = true;
            this.t.setProgress(progress);
        }
        this.t.p0(this.f2337d, progress, this.f2341h, this.f2340g, this.f2349p);
        float f4 = this.f2346m;
        float[] fArr = this.f2349p;
        if (Math.abs((f4 * fArr[0]) + (this.f2347n * fArr[1])) < 0.01d) {
            float[] fArr2 = this.f2349p;
            fArr2[0] = 0.01f;
            fArr2[1] = 0.01f;
        }
        float f5 = this.f2346m;
        float max = Math.max(Math.min(progress + (f5 != 0.0f ? (f2 * f5) / this.f2349p[0] : (f3 * this.f2347n) / this.f2349p[1]), 1.0f), 0.0f);
        if (max != this.t.getProgress()) {
            this.t.setProgress(max);
        }
    }

    void v(float f2, float f3) {
        int i2;
        this.f2348o = false;
        float progress = this.t.getProgress();
        this.t.p0(this.f2337d, progress, this.f2341h, this.f2340g, this.f2349p);
        float f4 = this.f2346m;
        float[] fArr = this.f2349p;
        float f5 = f4 != 0.0f ? (f2 * f4) / fArr[0] : (f3 * this.f2347n) / fArr[1];
        if (!Float.isNaN(f5)) {
            progress += f5 / 3.0f;
        }
        if (progress == 0.0f || progress == 1.0f || (i2 = this.f2336c) == 3) {
            return;
        }
        this.t.G0(i2, ((double) progress) >= 0.5d ? 1.0f : 0.0f, f5);
    }

    void w(float f2, float f3) {
        this.f2351r = f2;
        this.f2352s = f3;
    }

    public void x(boolean z) {
        if (z) {
            float[][] fArr = H;
            fArr[4] = fArr[3];
            fArr[5] = fArr[2];
            float[][] fArr2 = G;
            fArr2[5] = fArr2[2];
            fArr2[6] = fArr2[1];
        } else {
            float[][] fArr3 = H;
            fArr3[4] = fArr3[2];
            fArr3[5] = fArr3[3];
            float[][] fArr4 = G;
            fArr4[5] = fArr4[1];
            fArr4[6] = fArr4[2];
        }
        float[] fArr5 = G[this.f2334a];
        this.f2341h = fArr5[0];
        this.f2340g = fArr5[1];
        int i2 = this.f2335b;
        float[][] fArr6 = H;
        if (i2 >= fArr6.length) {
            return;
        }
        float[] fArr7 = fArr6[i2];
        this.f2346m = fArr7[0];
        this.f2347n = fArr7[1];
    }

    public void y(int i2) {
        this.f2336c = i2;
    }

    void z(float f2, float f3) {
        this.f2351r = f2;
        this.f2352s = f3;
        this.f2348o = false;
    }
}
