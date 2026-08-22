package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class ViewTransition {

    /* renamed from: a, reason: collision with root package name */
    private int f2355a;

    /* renamed from: e, reason: collision with root package name */
    int f2359e;

    /* renamed from: f, reason: collision with root package name */
    KeyFrames f2360f;

    /* renamed from: g, reason: collision with root package name */
    ConstraintSet.Constraint f2361g;

    /* renamed from: j, reason: collision with root package name */
    private int f2364j;

    /* renamed from: k, reason: collision with root package name */
    private String f2365k;

    /* renamed from: o, reason: collision with root package name */
    Context f2369o;

    /* renamed from: b, reason: collision with root package name */
    private int f2356b = -1;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2357c = false;

    /* renamed from: d, reason: collision with root package name */
    private int f2358d = 0;

    /* renamed from: h, reason: collision with root package name */
    private int f2362h = -1;

    /* renamed from: i, reason: collision with root package name */
    private int f2363i = -1;

    /* renamed from: l, reason: collision with root package name */
    private int f2366l = 0;

    /* renamed from: m, reason: collision with root package name */
    private String f2367m = null;

    /* renamed from: n, reason: collision with root package name */
    private int f2368n = -1;

    /* renamed from: p, reason: collision with root package name */
    private int f2370p = -1;

    /* renamed from: q, reason: collision with root package name */
    private int f2371q = -1;

    /* renamed from: r, reason: collision with root package name */
    private int f2372r = -1;

    /* renamed from: s, reason: collision with root package name */
    private int f2373s = -1;
    private int t = -1;
    private int u = -1;
    private int v = -1;

    static class Animate {

        /* renamed from: a, reason: collision with root package name */
        private final int f2376a;

        /* renamed from: b, reason: collision with root package name */
        private final int f2377b;

        /* renamed from: c, reason: collision with root package name */
        long f2378c;

        /* renamed from: d, reason: collision with root package name */
        MotionController f2379d;

        /* renamed from: e, reason: collision with root package name */
        int f2380e;

        /* renamed from: f, reason: collision with root package name */
        int f2381f;

        /* renamed from: h, reason: collision with root package name */
        ViewTransitionController f2383h;

        /* renamed from: i, reason: collision with root package name */
        Interpolator f2384i;

        /* renamed from: k, reason: collision with root package name */
        float f2386k;

        /* renamed from: l, reason: collision with root package name */
        float f2387l;

        /* renamed from: m, reason: collision with root package name */
        long f2388m;

        /* renamed from: o, reason: collision with root package name */
        boolean f2390o;

        /* renamed from: g, reason: collision with root package name */
        KeyCache f2382g = new KeyCache();

        /* renamed from: j, reason: collision with root package name */
        boolean f2385j = false;

        /* renamed from: n, reason: collision with root package name */
        Rect f2389n = new Rect();

        Animate(ViewTransitionController viewTransitionController, MotionController motionController, int i2, int i3, int i4, Interpolator interpolator, int i5, int i6) {
            this.f2390o = false;
            this.f2383h = viewTransitionController;
            this.f2379d = motionController;
            this.f2380e = i2;
            this.f2381f = i3;
            long nanoTime = System.nanoTime();
            this.f2378c = nanoTime;
            this.f2388m = nanoTime;
            this.f2383h.b(this);
            this.f2384i = interpolator;
            this.f2376a = i5;
            this.f2377b = i6;
            if (i4 == 3) {
                this.f2390o = true;
            }
            this.f2387l = i2 == 0 ? Float.MAX_VALUE : 1.0f / i2;
            a();
        }

        void a() {
            if (this.f2385j) {
                c();
            } else {
                b();
            }
        }

        void b() {
            long nanoTime = System.nanoTime();
            long j2 = nanoTime - this.f2388m;
            this.f2388m = nanoTime;
            float f2 = this.f2386k + (((float) (j2 * 1.0E-6d)) * this.f2387l);
            this.f2386k = f2;
            if (f2 >= 1.0f) {
                this.f2386k = 1.0f;
            }
            Interpolator interpolator = this.f2384i;
            float interpolation = interpolator == null ? this.f2386k : interpolator.getInterpolation(this.f2386k);
            MotionController motionController = this.f2379d;
            boolean x = motionController.x(motionController.f2214b, interpolation, nanoTime, this.f2382g);
            if (this.f2386k >= 1.0f) {
                if (this.f2376a != -1) {
                    this.f2379d.v().setTag(this.f2376a, Long.valueOf(System.nanoTime()));
                }
                if (this.f2377b != -1) {
                    this.f2379d.v().setTag(this.f2377b, null);
                }
                if (!this.f2390o) {
                    this.f2383h.g(this);
                }
            }
            if (this.f2386k < 1.0f || x) {
                this.f2383h.e();
            }
        }

        void c() {
            long nanoTime = System.nanoTime();
            long j2 = nanoTime - this.f2388m;
            this.f2388m = nanoTime;
            float f2 = this.f2386k - (((float) (j2 * 1.0E-6d)) * this.f2387l);
            this.f2386k = f2;
            if (f2 < 0.0f) {
                this.f2386k = 0.0f;
            }
            Interpolator interpolator = this.f2384i;
            float interpolation = interpolator == null ? this.f2386k : interpolator.getInterpolation(this.f2386k);
            MotionController motionController = this.f2379d;
            boolean x = motionController.x(motionController.f2214b, interpolation, nanoTime, this.f2382g);
            if (this.f2386k <= 0.0f) {
                if (this.f2376a != -1) {
                    this.f2379d.v().setTag(this.f2376a, Long.valueOf(System.nanoTime()));
                }
                if (this.f2377b != -1) {
                    this.f2379d.v().setTag(this.f2377b, null);
                }
                this.f2383h.g(this);
            }
            if (this.f2386k > 0.0f || x) {
                this.f2383h.e();
            }
        }

        public void d(int i2, float f2, float f3) {
            if (i2 == 1) {
                if (this.f2385j) {
                    return;
                }
                e(true);
            } else {
                if (i2 != 2) {
                    return;
                }
                this.f2379d.v().getHitRect(this.f2389n);
                if (this.f2389n.contains((int) f2, (int) f3) || this.f2385j) {
                    return;
                }
                e(true);
            }
        }

        void e(boolean z) {
            int i2;
            this.f2385j = z;
            if (z && (i2 = this.f2381f) != -1) {
                this.f2387l = i2 == 0 ? Float.MAX_VALUE : 1.0f / i2;
            }
            this.f2383h.e();
            this.f2388m = System.nanoTime();
        }
    }

    ViewTransition(Context context, XmlPullParser xmlPullParser) {
        char c2;
        this.f2369o = context;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    switch (name.hashCode()) {
                        case -1962203927:
                            if (name.equals("ConstraintOverride")) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1239391468:
                            if (name.equals("KeyFrameSet")) {
                                c2 = 1;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 61998586:
                            if (name.equals("ViewTransition")) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 366511058:
                            if (name.equals("CustomMethod")) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1791837707:
                            if (name.equals("CustomAttribute")) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    if (c2 == 0) {
                        l(context, xmlPullParser);
                    } else if (c2 == 1) {
                        this.f2360f = new KeyFrames(context, xmlPullParser);
                    } else if (c2 == 2) {
                        this.f2361g = ConstraintSet.m(context, xmlPullParser);
                    } else if (c2 == 3 || c2 == 4) {
                        ConstraintAttribute.i(context, xmlPullParser, this.f2361g.f2493g);
                    } else {
                        Log.e("ViewTransition", Debug.a() + " unknown tag " + name);
                        StringBuilder sb = new StringBuilder();
                        sb.append(".xml:");
                        sb.append(xmlPullParser.getLineNumber());
                        Log.e("ViewTransition", sb.toString());
                    }
                } else if (eventType == 3 && "ViewTransition".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e2) {
            Log.e("ViewTransition", "Error parsing XML resource", e2);
        } catch (XmlPullParserException e3) {
            Log.e("ViewTransition", "Error parsing XML resource", e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j(View[] viewArr) {
        if (this.f2370p != -1) {
            for (View view : viewArr) {
                view.setTag(this.f2370p, Long.valueOf(System.nanoTime()));
            }
        }
        if (this.f2371q != -1) {
            for (View view2 : viewArr) {
                view2.setTag(this.f2371q, null);
            }
        }
    }

    private void l(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.ViewTransition);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.ViewTransition_android_id) {
                this.f2355a = obtainStyledAttributes.getResourceId(index, this.f2355a);
            } else if (index == R.styleable.ViewTransition_motionTarget) {
                if (MotionLayout.IS_IN_EDIT_MODE) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, this.f2364j);
                    this.f2364j = resourceId;
                    if (resourceId == -1) {
                        this.f2365k = obtainStyledAttributes.getString(index);
                    }
                } else if (obtainStyledAttributes.peekValue(index).type == 3) {
                    this.f2365k = obtainStyledAttributes.getString(index);
                } else {
                    this.f2364j = obtainStyledAttributes.getResourceId(index, this.f2364j);
                }
            } else if (index == R.styleable.ViewTransition_onStateTransition) {
                this.f2356b = obtainStyledAttributes.getInt(index, this.f2356b);
            } else if (index == R.styleable.ViewTransition_transitionDisable) {
                this.f2357c = obtainStyledAttributes.getBoolean(index, this.f2357c);
            } else if (index == R.styleable.ViewTransition_pathMotionArc) {
                this.f2358d = obtainStyledAttributes.getInt(index, this.f2358d);
            } else if (index == R.styleable.ViewTransition_duration) {
                this.f2362h = obtainStyledAttributes.getInt(index, this.f2362h);
            } else if (index == R.styleable.ViewTransition_upDuration) {
                this.f2363i = obtainStyledAttributes.getInt(index, this.f2363i);
            } else if (index == R.styleable.ViewTransition_viewTransitionMode) {
                this.f2359e = obtainStyledAttributes.getInt(index, this.f2359e);
            } else if (index == R.styleable.ViewTransition_motionInterpolator) {
                int i3 = obtainStyledAttributes.peekValue(index).type;
                if (i3 == 1) {
                    int resourceId2 = obtainStyledAttributes.getResourceId(index, -1);
                    this.f2368n = resourceId2;
                    if (resourceId2 != -1) {
                        this.f2366l = -2;
                    }
                } else if (i3 == 3) {
                    String string = obtainStyledAttributes.getString(index);
                    this.f2367m = string;
                    if (string == null || string.indexOf("/") <= 0) {
                        this.f2366l = -1;
                    } else {
                        this.f2368n = obtainStyledAttributes.getResourceId(index, -1);
                        this.f2366l = -2;
                    }
                } else {
                    this.f2366l = obtainStyledAttributes.getInteger(index, this.f2366l);
                }
            } else if (index == R.styleable.ViewTransition_setsTag) {
                this.f2370p = obtainStyledAttributes.getResourceId(index, this.f2370p);
            } else if (index == R.styleable.ViewTransition_clearsTag) {
                this.f2371q = obtainStyledAttributes.getResourceId(index, this.f2371q);
            } else if (index == R.styleable.ViewTransition_ifTagSet) {
                this.f2372r = obtainStyledAttributes.getResourceId(index, this.f2372r);
            } else if (index == R.styleable.ViewTransition_ifTagNotSet) {
                this.f2373s = obtainStyledAttributes.getResourceId(index, this.f2373s);
            } else if (index == R.styleable.ViewTransition_SharedValueId) {
                this.u = obtainStyledAttributes.getResourceId(index, this.u);
            } else if (index == R.styleable.ViewTransition_SharedValue) {
                this.t = obtainStyledAttributes.getInteger(index, this.t);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void n(MotionScene.Transition transition, View view) {
        int i2 = this.f2362h;
        if (i2 != -1) {
            transition.E(i2);
        }
        transition.I(this.f2358d);
        transition.G(this.f2366l, this.f2367m, this.f2368n);
        int id = view.getId();
        KeyFrames keyFrames = this.f2360f;
        if (keyFrames != null) {
            ArrayList d2 = keyFrames.d(-1);
            KeyFrames keyFrames2 = new KeyFrames();
            Iterator it = d2.iterator();
            while (it.hasNext()) {
                keyFrames2.c(((Key) it.next()).clone().i(id));
            }
            transition.t(keyFrames2);
        }
    }

    void b(ViewTransitionController viewTransitionController, MotionLayout motionLayout, View view) {
        MotionController motionController = new MotionController(view);
        motionController.B(view);
        this.f2360f.a(motionController);
        motionController.I(motionLayout.getWidth(), motionLayout.getHeight(), this.f2362h, System.nanoTime());
        new Animate(viewTransitionController, motionController, this.f2362h, this.f2363i, this.f2356b, f(motionLayout.getContext()), this.f2370p, this.f2371q);
    }

    void c(ViewTransitionController viewTransitionController, MotionLayout motionLayout, int i2, ConstraintSet constraintSet, final View... viewArr) {
        if (this.f2357c) {
            return;
        }
        int i3 = this.f2359e;
        if (i3 == 2) {
            b(viewTransitionController, motionLayout, viewArr[0]);
            return;
        }
        if (i3 == 1) {
            for (int i4 : motionLayout.getConstraintSetIds()) {
                if (i4 != i2) {
                    ConstraintSet q0 = motionLayout.q0(i4);
                    for (View view : viewArr) {
                        ConstraintSet.Constraint v = q0.v(view.getId());
                        ConstraintSet.Constraint constraint = this.f2361g;
                        if (constraint != null) {
                            constraint.d(v);
                            v.f2493g.putAll(this.f2361g.f2493g);
                        }
                    }
                }
            }
        }
        ConstraintSet constraintSet2 = new ConstraintSet();
        constraintSet2.p(constraintSet);
        for (View view2 : viewArr) {
            ConstraintSet.Constraint v2 = constraintSet2.v(view2.getId());
            ConstraintSet.Constraint constraint2 = this.f2361g;
            if (constraint2 != null) {
                constraint2.d(v2);
                v2.f2493g.putAll(this.f2361g.f2493g);
            }
        }
        motionLayout.P0(i2, constraintSet2);
        motionLayout.P0(R.id.view_transition, constraintSet);
        motionLayout.C0(R.id.view_transition, -1, -1);
        MotionScene.Transition transition = new MotionScene.Transition(-1, motionLayout.mScene, R.id.view_transition, i2);
        for (View view3 : viewArr) {
            n(transition, view3);
        }
        motionLayout.setTransition(transition);
        motionLayout.I0(new Runnable() { // from class: androidx.constraintlayout.motion.widget.a
            @Override // java.lang.Runnable
            public final void run() {
                ViewTransition.this.j(viewArr);
            }
        });
    }

    boolean d(View view) {
        int i2 = this.f2372r;
        boolean z = i2 == -1 || view.getTag(i2) != null;
        int i3 = this.f2373s;
        return z && (i3 == -1 || view.getTag(i3) == null);
    }

    int e() {
        return this.f2355a;
    }

    Interpolator f(Context context) {
        int i2 = this.f2366l;
        if (i2 == -2) {
            return AnimationUtils.loadInterpolator(context, this.f2368n);
        }
        if (i2 == -1) {
            final Easing c2 = Easing.c(this.f2367m);
            return new Interpolator() { // from class: androidx.constraintlayout.motion.widget.ViewTransition.1
                @Override // android.animation.TimeInterpolator
                public float getInterpolation(float f2) {
                    return (float) c2.a(f2);
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
        if (i2 == 5) {
            return new OvershootInterpolator();
        }
        if (i2 != 6) {
            return null;
        }
        return new AnticipateInterpolator();
    }

    public int g() {
        return this.t;
    }

    public int h() {
        return this.u;
    }

    public int i() {
        return this.f2356b;
    }

    boolean k(View view) {
        String str;
        if (view == null) {
            return false;
        }
        if ((this.f2364j == -1 && this.f2365k == null) || !d(view)) {
            return false;
        }
        if (view.getId() == this.f2364j) {
            return true;
        }
        return this.f2365k != null && (view.getLayoutParams() instanceof ConstraintLayout.LayoutParams) && (str = ((ConstraintLayout.LayoutParams) view.getLayoutParams()).c0) != null && str.matches(this.f2365k);
    }

    boolean m(int i2) {
        int i3 = this.f2356b;
        return i3 == 1 ? i2 == 0 : i3 == 2 ? i2 == 1 : i3 == 3 && i2 == 0;
    }

    public String toString() {
        return "ViewTransition(" + Debug.c(this.f2369o, this.f2355a) + ")";
    }
}
