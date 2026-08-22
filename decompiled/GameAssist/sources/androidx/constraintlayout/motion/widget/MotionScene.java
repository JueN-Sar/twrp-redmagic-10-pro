package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.MotionEvent;
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
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class MotionScene {

    /* renamed from: a, reason: collision with root package name */
    private final MotionLayout f2292a;

    /* renamed from: m, reason: collision with root package name */
    private MotionEvent f2304m;

    /* renamed from: p, reason: collision with root package name */
    private MotionLayout.MotionTracker f2307p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f2308q;

    /* renamed from: r, reason: collision with root package name */
    final ViewTransitionController f2309r;

    /* renamed from: s, reason: collision with root package name */
    float f2310s;
    float t;

    /* renamed from: b, reason: collision with root package name */
    StateSet f2293b = null;

    /* renamed from: c, reason: collision with root package name */
    Transition f2294c = null;

    /* renamed from: d, reason: collision with root package name */
    private boolean f2295d = false;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f2296e = new ArrayList();

    /* renamed from: f, reason: collision with root package name */
    private Transition f2297f = null;

    /* renamed from: g, reason: collision with root package name */
    private ArrayList f2298g = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private SparseArray f2299h = new SparseArray();

    /* renamed from: i, reason: collision with root package name */
    private HashMap f2300i = new HashMap();

    /* renamed from: j, reason: collision with root package name */
    private SparseIntArray f2301j = new SparseIntArray();

    /* renamed from: k, reason: collision with root package name */
    private int f2302k = 400;

    /* renamed from: l, reason: collision with root package name */
    private int f2303l = 0;

    /* renamed from: n, reason: collision with root package name */
    private boolean f2305n = false;

    /* renamed from: o, reason: collision with root package name */
    private boolean f2306o = false;

    MotionScene(Context context, MotionLayout motionLayout, int i2) {
        this.f2292a = motionLayout;
        this.f2309r = new ViewTransitionController(motionLayout);
        K(context, i2);
        this.f2299h.put(R.id.motion_base, new ConstraintSet());
        this.f2300i.put("motion_base", Integer.valueOf(R.id.motion_base));
    }

    private boolean I(int i2) {
        int i3 = this.f2301j.get(i2);
        int size = this.f2301j.size();
        while (i3 > 0) {
            if (i3 == i2) {
                return true;
            }
            int i4 = size - 1;
            if (size < 0) {
                return true;
            }
            i3 = this.f2301j.get(i3);
            size = i4;
        }
        return false;
    }

    private boolean J() {
        return this.f2307p != null;
    }

    private void K(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            int eventType = xml.getEventType();
            Transition transition = null;
            while (true) {
                char c2 = 1;
                if (eventType == 1) {
                    return;
                }
                if (eventType == 2) {
                    String name = xml.getName();
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                c2 = 5;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -1239391468:
                            if (name.equals("KeyFrameSet")) {
                                c2 = '\b';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case -687739768:
                            if (name.equals("Include")) {
                                c2 = 7;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 61998586:
                            if (name.equals("ViewTransition")) {
                                c2 = '\t';
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 269306229:
                            if (name.equals("Transition")) {
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 312750793:
                            if (name.equals("OnClick")) {
                                c2 = 3;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 327855227:
                            if (name.equals("OnSwipe")) {
                                c2 = 2;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 793277014:
                            if (name.equals("MotionScene")) {
                                c2 = 0;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                c2 = 4;
                                break;
                            }
                            c2 = 65535;
                            break;
                        case 1942574248:
                            if (name.equals("include")) {
                                c2 = 6;
                                break;
                            }
                            c2 = 65535;
                            break;
                        default:
                            c2 = 65535;
                            break;
                    }
                    switch (c2) {
                        case 0:
                            O(context, xml);
                            break;
                        case 1:
                            ArrayList arrayList = this.f2296e;
                            transition = new Transition(this, context, xml);
                            arrayList.add(transition);
                            if (this.f2294c == null && !transition.f2314b) {
                                this.f2294c = transition;
                                if (transition.f2324l != null) {
                                    this.f2294c.f2324l.x(this.f2308q);
                                }
                            }
                            if (!transition.f2314b) {
                                break;
                            } else {
                                if (transition.f2315c == -1) {
                                    this.f2297f = transition;
                                } else {
                                    this.f2298g.add(transition);
                                }
                                this.f2296e.remove(transition);
                                break;
                            }
                        case 2:
                            if (transition == null) {
                                Log.v("MotionScene", " OnSwipe (" + context.getResources().getResourceEntryName(i2) + ".xml:" + xml.getLineNumber() + ")");
                            }
                            if (transition == null) {
                                break;
                            } else {
                                transition.f2324l = new TouchResponse(context, this.f2292a, xml);
                                break;
                            }
                        case 3:
                            if (transition != null && !this.f2292a.isInEditMode()) {
                                transition.u(context, xml);
                                break;
                            }
                            break;
                        case 4:
                            this.f2293b = new StateSet(context, xml);
                            break;
                        case 5:
                            L(context, xml);
                            break;
                        case 6:
                        case 7:
                            N(context, xml);
                            break;
                        case '\b':
                            KeyFrames keyFrames = new KeyFrames(context, xml);
                            if (transition == null) {
                                break;
                            } else {
                                transition.f2323k.add(keyFrames);
                                break;
                            }
                        case '\t':
                            this.f2309r.a(new ViewTransition(context, xml));
                            break;
                    }
                }
                eventType = xml.next();
            }
        } catch (IOException e2) {
            Log.e("MotionScene", "Error parsing resource: " + i2, e2);
        } catch (XmlPullParserException e3) {
            Log.e("MotionScene", "Error parsing resource: " + i2, e3);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int L(Context context, XmlPullParser xmlPullParser) {
        boolean z;
        boolean z2;
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.Q(false);
        int attributeCount = xmlPullParser.getAttributeCount();
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < attributeCount; i4++) {
            String attributeName = xmlPullParser.getAttributeName(i4);
            String attributeValue = xmlPullParser.getAttributeValue(i4);
            attributeName.hashCode();
            switch (attributeName.hashCode()) {
                case -1496482599:
                    if (attributeName.equals("deriveConstraintsFrom")) {
                        z = false;
                        break;
                    }
                    z = -1;
                    break;
                case -1153153640:
                    if (attributeName.equals("constraintRotate")) {
                        z = true;
                        break;
                    }
                    z = -1;
                    break;
                case 3355:
                    if (attributeName.equals(VirtualHandleWrapper.KEY_ID)) {
                        z = 2;
                        break;
                    }
                    z = -1;
                    break;
                case 973381616:
                    if (attributeName.equals("stateLabels")) {
                        z = 3;
                        break;
                    }
                    z = -1;
                    break;
                default:
                    z = -1;
                    break;
            }
            switch (z) {
                case false:
                    i3 = r(context, attributeValue);
                    break;
                case true:
                    try {
                        constraintSet.f2483e = Integer.parseInt(attributeValue);
                        break;
                    } catch (NumberFormatException unused) {
                        attributeValue.hashCode();
                        switch (attributeValue.hashCode()) {
                            case -768416914:
                                if (attributeValue.equals("x_left")) {
                                    z2 = false;
                                    break;
                                }
                                z2 = -1;
                                break;
                            case 3317767:
                                if (attributeValue.equals("left")) {
                                    z2 = true;
                                    break;
                                }
                                z2 = -1;
                                break;
                            case 3387192:
                                if (attributeValue.equals("none")) {
                                    z2 = 2;
                                    break;
                                }
                                z2 = -1;
                                break;
                            case 108511772:
                                if (attributeValue.equals("right")) {
                                    z2 = 3;
                                    break;
                                }
                                z2 = -1;
                                break;
                            case 1954540437:
                                if (attributeValue.equals("x_right")) {
                                    z2 = 4;
                                    break;
                                }
                                z2 = -1;
                                break;
                            default:
                                z2 = -1;
                                break;
                        }
                        switch (z2) {
                            case false:
                                constraintSet.f2483e = 4;
                                break;
                            case true:
                                constraintSet.f2483e = 2;
                                break;
                            case true:
                                constraintSet.f2483e = 0;
                                break;
                            case true:
                                constraintSet.f2483e = 1;
                                break;
                            case true:
                                constraintSet.f2483e = 3;
                                break;
                        }
                    }
                    break;
                case true:
                    i2 = r(context, attributeValue);
                    this.f2300i.put(a0(attributeValue), Integer.valueOf(i2));
                    constraintSet.f2480b = Debug.c(context, i2);
                    break;
                case true:
                    constraintSet.R(attributeValue);
                    break;
            }
        }
        if (i2 != -1) {
            if (this.f2292a.mDebugPath != 0) {
                constraintSet.S(true);
            }
            constraintSet.D(context, xmlPullParser);
            if (i3 != -1) {
                this.f2301j.put(i2, i3);
            }
            this.f2299h.put(i2, constraintSet);
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int M(Context context, int i2) {
        XmlResourceParser xml = context.getResources().getXml(i2);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                String name = xml.getName();
                if (2 == eventType && "ConstraintSet".equals(name)) {
                    return L(context, xml);
                }
            }
            return -1;
        } catch (IOException e2) {
            Log.e("MotionScene", "Error parsing resource: " + i2, e2);
            return -1;
        } catch (XmlPullParserException e3) {
            Log.e("MotionScene", "Error parsing resource: " + i2, e3);
            return -1;
        }
    }

    private void N(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.include);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.include_constraintSet) {
                M(context, obtainStyledAttributes.getResourceId(index, -1));
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void O(Context context, XmlPullParser xmlPullParser) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.MotionScene);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.MotionScene_defaultDuration) {
                int i3 = obtainStyledAttributes.getInt(index, this.f2302k);
                this.f2302k = i3;
                if (i3 < 8) {
                    this.f2302k = 8;
                }
            } else if (index == R.styleable.MotionScene_layoutDuringTransition) {
                this.f2303l = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void S(int i2, MotionLayout motionLayout) {
        ConstraintSet constraintSet = (ConstraintSet) this.f2299h.get(i2);
        constraintSet.f2481c = constraintSet.f2480b;
        int i3 = this.f2301j.get(i2);
        if (i3 > 0) {
            S(i3, motionLayout);
            ConstraintSet constraintSet2 = (ConstraintSet) this.f2299h.get(i3);
            if (constraintSet2 == null) {
                Log.e("MotionScene", "ERROR! invalid deriveConstraintsFrom: @id/" + Debug.c(this.f2292a.getContext(), i3));
                return;
            }
            constraintSet.f2481c += "/" + constraintSet2.f2481c;
            constraintSet.L(constraintSet2);
        } else {
            constraintSet.f2481c += "  layout";
            constraintSet.K(motionLayout);
        }
        constraintSet.h(constraintSet);
    }

    public static String a0(String str) {
        if (str == null) {
            return "";
        }
        int indexOf = str.indexOf(47);
        return indexOf < 0 ? str : str.substring(indexOf + 1);
    }

    private int r(Context context, String str) {
        int i2;
        if (str.contains("/")) {
            i2 = context.getResources().getIdentifier(str.substring(str.indexOf(47) + 1), VirtualHandleWrapper.KEY_ID, context.getPackageName());
        } else {
            i2 = -1;
        }
        if (i2 != -1) {
            return i2;
        }
        if (str.length() > 1) {
            return Integer.parseInt(str.substring(1));
        }
        Log.e("MotionScene", "error in parsing id");
        return i2;
    }

    private int y(int i2) {
        int c2;
        StateSet stateSet = this.f2293b;
        return (stateSet == null || (c2 = stateSet.c(i2, -1, -1)) == -1) ? i2 : c2;
    }

    float A() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.l();
    }

    float B() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.m();
    }

    float C() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.n();
    }

    float D() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.o();
    }

    public float E() {
        Transition transition = this.f2294c;
        if (transition != null) {
            return transition.f2321i;
        }
        return 0.0f;
    }

    int F() {
        Transition transition = this.f2294c;
        if (transition == null) {
            return -1;
        }
        return transition.f2316d;
    }

    public Transition G(int i2) {
        Iterator it = this.f2296e.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.f2313a == i2) {
                return transition;
            }
        }
        return null;
    }

    public List H(int i2) {
        int y = y(i2);
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f2296e.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.f2316d == y || transition.f2315c == y) {
                arrayList.add(transition);
            }
        }
        return arrayList;
    }

    void P(float f2, float f3) {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return;
        }
        this.f2294c.f2324l.u(f2, f3);
    }

    void Q(float f2, float f3) {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return;
        }
        this.f2294c.f2324l.v(f2, f3);
    }

    void R(MotionEvent motionEvent, int i2, MotionLayout motionLayout) {
        MotionLayout.MotionTracker motionTracker;
        MotionEvent motionEvent2;
        RectF rectF = new RectF();
        if (this.f2307p == null) {
            this.f2307p = this.f2292a.x0();
        }
        this.f2307p.a(motionEvent);
        if (i2 != -1) {
            int action = motionEvent.getAction();
            boolean z = false;
            if (action == 0) {
                this.f2310s = motionEvent.getRawX();
                this.t = motionEvent.getRawY();
                this.f2304m = motionEvent;
                this.f2305n = false;
                if (this.f2294c.f2324l != null) {
                    RectF f2 = this.f2294c.f2324l.f(this.f2292a, rectF);
                    if (f2 != null && !f2.contains(this.f2304m.getX(), this.f2304m.getY())) {
                        this.f2304m = null;
                        this.f2305n = true;
                        return;
                    }
                    RectF p2 = this.f2294c.f2324l.p(this.f2292a, rectF);
                    if (p2 == null || p2.contains(this.f2304m.getX(), this.f2304m.getY())) {
                        this.f2306o = false;
                    } else {
                        this.f2306o = true;
                    }
                    this.f2294c.f2324l.w(this.f2310s, this.t);
                    return;
                }
                return;
            }
            if (action == 2 && !this.f2305n) {
                float rawY = motionEvent.getRawY() - this.t;
                float rawX = motionEvent.getRawX() - this.f2310s;
                if ((rawX == 0.0d && rawY == 0.0d) || (motionEvent2 = this.f2304m) == null) {
                    return;
                }
                Transition i3 = i(i2, rawX, rawY, motionEvent2);
                if (i3 != null) {
                    motionLayout.setTransition(i3);
                    RectF p3 = this.f2294c.f2324l.p(this.f2292a, rectF);
                    if (p3 != null && !p3.contains(this.f2304m.getX(), this.f2304m.getY())) {
                        z = true;
                    }
                    this.f2306o = z;
                    this.f2294c.f2324l.z(this.f2310s, this.t);
                }
            }
        }
        if (this.f2305n) {
            return;
        }
        Transition transition = this.f2294c;
        if (transition != null && transition.f2324l != null && !this.f2306o) {
            this.f2294c.f2324l.s(motionEvent, this.f2307p, i2, this);
        }
        this.f2310s = motionEvent.getRawX();
        this.t = motionEvent.getRawY();
        if (motionEvent.getAction() != 1 || (motionTracker = this.f2307p) == null) {
            return;
        }
        motionTracker.d();
        this.f2307p = null;
        int i4 = motionLayout.mCurrentState;
        if (i4 != -1) {
            h(motionLayout, i4);
        }
    }

    void T(MotionLayout motionLayout) {
        for (int i2 = 0; i2 < this.f2299h.size(); i2++) {
            int keyAt = this.f2299h.keyAt(i2);
            if (I(keyAt)) {
                Log.e("MotionScene", "Cannot be derived from yourself");
                return;
            }
            S(keyAt, motionLayout);
        }
    }

    public void U(int i2, ConstraintSet constraintSet) {
        this.f2299h.put(i2, constraintSet);
    }

    public void V(int i2) {
        Transition transition = this.f2294c;
        if (transition != null) {
            transition.E(i2);
        } else {
            this.f2302k = i2;
        }
    }

    public void W(boolean z) {
        this.f2308q = z;
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return;
        }
        this.f2294c.f2324l.x(this.f2308q);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0013, code lost:
    
        if (r2 != (-1)) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void X(int r7, int r8) {
        /*
            r6 = this;
            androidx.constraintlayout.widget.StateSet r0 = r6.f2293b
            r1 = -1
            if (r0 == 0) goto L18
            int r0 = r0.c(r7, r1, r1)
            if (r0 == r1) goto Lc
            goto Ld
        Lc:
            r0 = r7
        Ld:
            androidx.constraintlayout.widget.StateSet r2 = r6.f2293b
            int r2 = r2.c(r8, r1, r1)
            if (r2 == r1) goto L16
            goto L1a
        L16:
            r2 = r8
            goto L1a
        L18:
            r0 = r7
            goto L16
        L1a:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r6.f2294c
            if (r3 == 0) goto L2d
            int r3 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r3)
            if (r3 != r8) goto L2d
            androidx.constraintlayout.motion.widget.MotionScene$Transition r3 = r6.f2294c
            int r3 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r3)
            if (r3 != r7) goto L2d
            return
        L2d:
            java.util.ArrayList r3 = r6.f2296e
            java.util.Iterator r3 = r3.iterator()
        L33:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L6d
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r2) goto L4b
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r4)
            if (r5 == r0) goto L57
        L4b:
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r8) goto L33
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.c(r4)
            if (r5 != r7) goto L33
        L57:
            r6.f2294c = r4
            if (r4 == 0) goto L6c
            androidx.constraintlayout.motion.widget.TouchResponse r7 = androidx.constraintlayout.motion.widget.MotionScene.Transition.l(r4)
            if (r7 == 0) goto L6c
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.f2294c
            androidx.constraintlayout.motion.widget.TouchResponse r7 = androidx.constraintlayout.motion.widget.MotionScene.Transition.l(r7)
            boolean r6 = r6.f2308q
            r7.x(r6)
        L6c:
            return
        L6d:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r7 = r6.f2297f
            java.util.ArrayList r3 = r6.f2298g
            java.util.Iterator r3 = r3.iterator()
        L75:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L89
            java.lang.Object r4 = r3.next()
            androidx.constraintlayout.motion.widget.MotionScene$Transition r4 = (androidx.constraintlayout.motion.widget.MotionScene.Transition) r4
            int r5 = androidx.constraintlayout.motion.widget.MotionScene.Transition.a(r4)
            if (r5 != r8) goto L75
            r7 = r4
            goto L75
        L89:
            androidx.constraintlayout.motion.widget.MotionScene$Transition r8 = new androidx.constraintlayout.motion.widget.MotionScene$Transition
            r8.<init>(r6, r7)
            androidx.constraintlayout.motion.widget.MotionScene.Transition.d(r8, r0)
            androidx.constraintlayout.motion.widget.MotionScene.Transition.b(r8, r2)
            if (r0 == r1) goto L9b
            java.util.ArrayList r7 = r6.f2296e
            r7.add(r8)
        L9b:
            r6.f2294c = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.X(int, int):void");
    }

    public void Y(Transition transition) {
        this.f2294c = transition;
        if (transition == null || transition.f2324l == null) {
            return;
        }
        this.f2294c.f2324l.x(this.f2308q);
    }

    void Z() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return;
        }
        this.f2294c.f2324l.A();
    }

    boolean b0() {
        Iterator it = this.f2296e.iterator();
        while (it.hasNext()) {
            if (((Transition) it.next()).f2324l != null) {
                return true;
            }
        }
        Transition transition = this.f2294c;
        return (transition == null || transition.f2324l == null) ? false : true;
    }

    public void c0(int i2, View... viewArr) {
        this.f2309r.i(i2, viewArr);
    }

    public void f(MotionLayout motionLayout, int i2) {
        Iterator it = this.f2296e.iterator();
        while (it.hasNext()) {
            Transition transition = (Transition) it.next();
            if (transition.f2325m.size() > 0) {
                Iterator it2 = transition.f2325m.iterator();
                while (it2.hasNext()) {
                    ((Transition.TransitionOnClick) it2.next()).c(motionLayout);
                }
            }
        }
        Iterator it3 = this.f2298g.iterator();
        while (it3.hasNext()) {
            Transition transition2 = (Transition) it3.next();
            if (transition2.f2325m.size() > 0) {
                Iterator it4 = transition2.f2325m.iterator();
                while (it4.hasNext()) {
                    ((Transition.TransitionOnClick) it4.next()).c(motionLayout);
                }
            }
        }
        Iterator it5 = this.f2296e.iterator();
        while (it5.hasNext()) {
            Transition transition3 = (Transition) it5.next();
            if (transition3.f2325m.size() > 0) {
                Iterator it6 = transition3.f2325m.iterator();
                while (it6.hasNext()) {
                    ((Transition.TransitionOnClick) it6.next()).a(motionLayout, i2, transition3);
                }
            }
        }
        Iterator it7 = this.f2298g.iterator();
        while (it7.hasNext()) {
            Transition transition4 = (Transition) it7.next();
            if (transition4.f2325m.size() > 0) {
                Iterator it8 = transition4.f2325m.iterator();
                while (it8.hasNext()) {
                    ((Transition.TransitionOnClick) it8.next()).a(motionLayout, i2, transition4);
                }
            }
        }
    }

    public boolean g(int i2, MotionController motionController) {
        return this.f2309r.d(i2, motionController);
    }

    boolean h(MotionLayout motionLayout, int i2) {
        Transition transition;
        if (J() || this.f2295d) {
            return false;
        }
        Iterator it = this.f2296e.iterator();
        while (it.hasNext()) {
            Transition transition2 = (Transition) it.next();
            if (transition2.f2326n != 0 && ((transition = this.f2294c) != transition2 || !transition.D(2))) {
                if (i2 == transition2.f2316d && (transition2.f2326n == 4 || transition2.f2326n == 2)) {
                    MotionLayout.TransitionState transitionState = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState);
                    motionLayout.setTransition(transition2);
                    if (transition2.f2326n == 4) {
                        motionLayout.H0();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(1.0f);
                        motionLayout.j0(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState);
                        motionLayout.y0();
                    }
                    return true;
                }
                if (i2 == transition2.f2315c && (transition2.f2326n == 3 || transition2.f2326n == 1)) {
                    MotionLayout.TransitionState transitionState2 = MotionLayout.TransitionState.FINISHED;
                    motionLayout.setState(transitionState2);
                    motionLayout.setTransition(transition2);
                    if (transition2.f2326n == 3) {
                        motionLayout.J0();
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                    } else {
                        motionLayout.setProgress(0.0f);
                        motionLayout.j0(true);
                        motionLayout.setState(MotionLayout.TransitionState.SETUP);
                        motionLayout.setState(MotionLayout.TransitionState.MOVING);
                        motionLayout.setState(transitionState2);
                        motionLayout.y0();
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public Transition i(int i2, float f2, float f3, MotionEvent motionEvent) {
        if (i2 == -1) {
            return this.f2294c;
        }
        List<Transition> H = H(i2);
        RectF rectF = new RectF();
        float f4 = 0.0f;
        Transition transition = null;
        for (Transition transition2 : H) {
            if (!transition2.f2327o && transition2.f2324l != null) {
                transition2.f2324l.x(this.f2308q);
                RectF p2 = transition2.f2324l.p(this.f2292a, rectF);
                if (p2 == null || motionEvent == null || p2.contains(motionEvent.getX(), motionEvent.getY())) {
                    RectF f5 = transition2.f2324l.f(this.f2292a, rectF);
                    if (f5 == null || motionEvent == null || f5.contains(motionEvent.getX(), motionEvent.getY())) {
                        float a2 = transition2.f2324l.a(f2, f3);
                        if (transition2.f2324l.f2345l && motionEvent != null) {
                            a2 = ((float) (Math.atan2(f3 + r10, f2 + r9) - Math.atan2(motionEvent.getX() - transition2.f2324l.f2342i, motionEvent.getY() - transition2.f2324l.f2343j))) * 10.0f;
                        }
                        float f6 = a2 * (transition2.f2315c == i2 ? -1.0f : 1.1f);
                        if (f6 > f4) {
                            transition = transition2;
                            f4 = f6;
                        }
                    }
                }
            }
        }
        return transition;
    }

    public int j() {
        Transition transition = this.f2294c;
        if (transition != null) {
            return transition.f2328p;
        }
        return -1;
    }

    int k() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0;
        }
        return this.f2294c.f2324l.d();
    }

    ConstraintSet l(int i2) {
        return m(i2, -1, -1);
    }

    ConstraintSet m(int i2, int i3, int i4) {
        int c2;
        StateSet stateSet = this.f2293b;
        if (stateSet != null && (c2 = stateSet.c(i2, i3, i4)) != -1) {
            i2 = c2;
        }
        if (this.f2299h.get(i2) != null) {
            return (ConstraintSet) this.f2299h.get(i2);
        }
        Log.e("MotionScene", "Warning could not find ConstraintSet id/" + Debug.c(this.f2292a.getContext(), i2) + " In MotionScene");
        SparseArray sparseArray = this.f2299h;
        return (ConstraintSet) sparseArray.get(sparseArray.keyAt(0));
    }

    public int[] n() {
        int size = this.f2299h.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = this.f2299h.keyAt(i2);
        }
        return iArr;
    }

    public ArrayList o() {
        return this.f2296e;
    }

    public int p() {
        Transition transition = this.f2294c;
        return transition != null ? transition.f2320h : this.f2302k;
    }

    int q() {
        Transition transition = this.f2294c;
        if (transition == null) {
            return -1;
        }
        return transition.f2315c;
    }

    public Interpolator s() {
        int i2 = this.f2294c.f2317e;
        if (i2 == -2) {
            return AnimationUtils.loadInterpolator(this.f2292a.getContext(), this.f2294c.f2319g);
        }
        if (i2 == -1) {
            final Easing c2 = Easing.c(this.f2294c.f2318f);
            return new Interpolator() { // from class: androidx.constraintlayout.motion.widget.MotionScene.1
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

    public void t(MotionController motionController) {
        Transition transition = this.f2294c;
        if (transition != null) {
            Iterator it = transition.f2323k.iterator();
            while (it.hasNext()) {
                ((KeyFrames) it.next()).b(motionController);
            }
        } else {
            Transition transition2 = this.f2297f;
            if (transition2 != null) {
                Iterator it2 = transition2.f2323k.iterator();
                while (it2.hasNext()) {
                    ((KeyFrames) it2.next()).b(motionController);
                }
            }
        }
    }

    float u() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.g();
    }

    float v() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.h();
    }

    boolean w() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return false;
        }
        return this.f2294c.f2324l.i();
    }

    float x(float f2, float f3) {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0.0f;
        }
        return this.f2294c.f2324l.j(f2, f3);
    }

    int z() {
        Transition transition = this.f2294c;
        if (transition == null || transition.f2324l == null) {
            return 0;
        }
        return this.f2294c.f2324l.k();
    }

    public static class Transition {

        /* renamed from: a, reason: collision with root package name */
        private int f2313a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f2314b;

        /* renamed from: c, reason: collision with root package name */
        private int f2315c;

        /* renamed from: d, reason: collision with root package name */
        private int f2316d;

        /* renamed from: e, reason: collision with root package name */
        private int f2317e;

        /* renamed from: f, reason: collision with root package name */
        private String f2318f;

        /* renamed from: g, reason: collision with root package name */
        private int f2319g;

        /* renamed from: h, reason: collision with root package name */
        private int f2320h;

        /* renamed from: i, reason: collision with root package name */
        private float f2321i;

        /* renamed from: j, reason: collision with root package name */
        private final MotionScene f2322j;

        /* renamed from: k, reason: collision with root package name */
        private ArrayList f2323k;

        /* renamed from: l, reason: collision with root package name */
        private TouchResponse f2324l;

        /* renamed from: m, reason: collision with root package name */
        private ArrayList f2325m;

        /* renamed from: n, reason: collision with root package name */
        private int f2326n;

        /* renamed from: o, reason: collision with root package name */
        private boolean f2327o;

        /* renamed from: p, reason: collision with root package name */
        private int f2328p;

        /* renamed from: q, reason: collision with root package name */
        private int f2329q;

        /* renamed from: r, reason: collision with root package name */
        private int f2330r;

        public static class TransitionOnClick implements View.OnClickListener {

            /* renamed from: c, reason: collision with root package name */
            private final Transition f2331c;

            /* renamed from: h, reason: collision with root package name */
            int f2332h;

            /* renamed from: i, reason: collision with root package name */
            int f2333i;

            public TransitionOnClick(Context context, Transition transition, XmlPullParser xmlPullParser) {
                this.f2332h = -1;
                this.f2333i = 17;
                this.f2331c = transition;
                TypedArray obtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), R.styleable.OnClick);
                int indexCount = obtainStyledAttributes.getIndexCount();
                for (int i2 = 0; i2 < indexCount; i2++) {
                    int index = obtainStyledAttributes.getIndex(i2);
                    if (index == R.styleable.OnClick_targetId) {
                        this.f2332h = obtainStyledAttributes.getResourceId(index, this.f2332h);
                    } else if (index == R.styleable.OnClick_clickAction) {
                        this.f2333i = obtainStyledAttributes.getInt(index, this.f2333i);
                    }
                }
                obtainStyledAttributes.recycle();
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r7v4, types: [android.view.View] */
            public void a(MotionLayout motionLayout, int i2, Transition transition) {
                int i3 = this.f2332h;
                MotionLayout motionLayout2 = motionLayout;
                if (i3 != -1) {
                    motionLayout2 = motionLayout.findViewById(i3);
                }
                if (motionLayout2 == null) {
                    Log.e("MotionScene", "OnClick could not find id " + this.f2332h);
                    return;
                }
                int i4 = transition.f2316d;
                int i5 = transition.f2315c;
                if (i4 == -1) {
                    motionLayout2.setOnClickListener(this);
                    return;
                }
                int i6 = this.f2333i;
                boolean z = false;
                boolean z2 = ((i6 & 1) != 0 && i2 == i4) | ((i6 & 1) != 0 && i2 == i4) | ((i6 & 256) != 0 && i2 == i4) | ((i6 & 16) != 0 && i2 == i5);
                if ((i6 & 4096) != 0 && i2 == i5) {
                    z = true;
                }
                if (z2 || z) {
                    motionLayout2.setOnClickListener(this);
                }
            }

            boolean b(Transition transition, MotionLayout motionLayout) {
                Transition transition2 = this.f2331c;
                if (transition2 == transition) {
                    return true;
                }
                int i2 = transition2.f2315c;
                int i3 = this.f2331c.f2316d;
                if (i3 == -1) {
                    return motionLayout.mCurrentState != i2;
                }
                int i4 = motionLayout.mCurrentState;
                return i4 == i3 || i4 == i2;
            }

            public void c(MotionLayout motionLayout) {
                int i2 = this.f2332h;
                if (i2 == -1) {
                    return;
                }
                View findViewById = motionLayout.findViewById(i2);
                if (findViewById != null) {
                    findViewById.setOnClickListener(null);
                    return;
                }
                Log.e("MotionScene", " (*)  could not find id " + this.f2332h);
            }

            /* JADX WARN: Removed duplicated region for block: B:35:0x009e  */
            /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
            @Override // android.view.View.OnClickListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onClick(android.view.View r8) {
                /*
                    Method dump skipped, instructions count: 228
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick.onClick(android.view.View):void");
            }
        }

        Transition(MotionScene motionScene, Transition transition) {
            this.f2313a = -1;
            this.f2314b = false;
            this.f2315c = -1;
            this.f2316d = -1;
            this.f2317e = 0;
            this.f2318f = null;
            this.f2319g = -1;
            this.f2320h = 400;
            this.f2321i = 0.0f;
            this.f2323k = new ArrayList();
            this.f2324l = null;
            this.f2325m = new ArrayList();
            this.f2326n = 0;
            this.f2327o = false;
            this.f2328p = -1;
            this.f2329q = 0;
            this.f2330r = 0;
            this.f2322j = motionScene;
            this.f2320h = motionScene.f2302k;
            if (transition != null) {
                this.f2328p = transition.f2328p;
                this.f2317e = transition.f2317e;
                this.f2318f = transition.f2318f;
                this.f2319g = transition.f2319g;
                this.f2320h = transition.f2320h;
                this.f2323k = transition.f2323k;
                this.f2321i = transition.f2321i;
                this.f2329q = transition.f2329q;
            }
        }

        private void v(MotionScene motionScene, Context context, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                if (index == R.styleable.Transition_constraintSetEnd) {
                    this.f2315c = typedArray.getResourceId(index, -1);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f2315c);
                    if ("layout".equals(resourceTypeName)) {
                        ConstraintSet constraintSet = new ConstraintSet();
                        constraintSet.C(context, this.f2315c);
                        motionScene.f2299h.append(this.f2315c, constraintSet);
                    } else if ("xml".equals(resourceTypeName)) {
                        this.f2315c = motionScene.M(context, this.f2315c);
                    }
                } else if (index == R.styleable.Transition_constraintSetStart) {
                    this.f2316d = typedArray.getResourceId(index, this.f2316d);
                    String resourceTypeName2 = context.getResources().getResourceTypeName(this.f2316d);
                    if ("layout".equals(resourceTypeName2)) {
                        ConstraintSet constraintSet2 = new ConstraintSet();
                        constraintSet2.C(context, this.f2316d);
                        motionScene.f2299h.append(this.f2316d, constraintSet2);
                    } else if ("xml".equals(resourceTypeName2)) {
                        this.f2316d = motionScene.M(context, this.f2316d);
                    }
                } else if (index == R.styleable.Transition_motionInterpolator) {
                    int i3 = typedArray.peekValue(index).type;
                    if (i3 == 1) {
                        int resourceId = typedArray.getResourceId(index, -1);
                        this.f2319g = resourceId;
                        if (resourceId != -1) {
                            this.f2317e = -2;
                        }
                    } else if (i3 == 3) {
                        String string = typedArray.getString(index);
                        this.f2318f = string;
                        if (string != null) {
                            if (string.indexOf("/") > 0) {
                                this.f2319g = typedArray.getResourceId(index, -1);
                                this.f2317e = -2;
                            } else {
                                this.f2317e = -1;
                            }
                        }
                    } else {
                        this.f2317e = typedArray.getInteger(index, this.f2317e);
                    }
                } else if (index == R.styleable.Transition_duration) {
                    int i4 = typedArray.getInt(index, this.f2320h);
                    this.f2320h = i4;
                    if (i4 < 8) {
                        this.f2320h = 8;
                    }
                } else if (index == R.styleable.Transition_staggered) {
                    this.f2321i = typedArray.getFloat(index, this.f2321i);
                } else if (index == R.styleable.Transition_autoTransition) {
                    this.f2326n = typedArray.getInteger(index, this.f2326n);
                } else if (index == R.styleable.Transition_android_id) {
                    this.f2313a = typedArray.getResourceId(index, this.f2313a);
                } else if (index == R.styleable.Transition_transitionDisable) {
                    this.f2327o = typedArray.getBoolean(index, this.f2327o);
                } else if (index == R.styleable.Transition_pathMotionArc) {
                    this.f2328p = typedArray.getInteger(index, -1);
                } else if (index == R.styleable.Transition_layoutDuringTransition) {
                    this.f2329q = typedArray.getInteger(index, 0);
                } else if (index == R.styleable.Transition_transitionFlags) {
                    this.f2330r = typedArray.getInteger(index, 0);
                }
            }
            if (this.f2316d == -1) {
                this.f2314b = true;
            }
        }

        private void w(MotionScene motionScene, Context context, AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Transition);
            v(motionScene, context, obtainStyledAttributes);
            obtainStyledAttributes.recycle();
        }

        public int A() {
            return this.f2316d;
        }

        public TouchResponse B() {
            return this.f2324l;
        }

        public boolean C() {
            return !this.f2327o;
        }

        public boolean D(int i2) {
            return (this.f2330r & i2) != 0;
        }

        public void E(int i2) {
            this.f2320h = Math.max(i2, 8);
        }

        public void F(boolean z) {
            this.f2327o = !z;
        }

        public void G(int i2, String str, int i3) {
            this.f2317e = i2;
            this.f2318f = str;
            this.f2319g = i3;
        }

        public void H(int i2) {
            TouchResponse B = B();
            if (B != null) {
                B.y(i2);
            }
        }

        public void I(int i2) {
            this.f2328p = i2;
        }

        public void t(KeyFrames keyFrames) {
            this.f2323k.add(keyFrames);
        }

        public void u(Context context, XmlPullParser xmlPullParser) {
            this.f2325m.add(new TransitionOnClick(context, this, xmlPullParser));
        }

        public int x() {
            return this.f2326n;
        }

        public int y() {
            return this.f2315c;
        }

        public int z() {
            return this.f2329q;
        }

        public Transition(int i2, MotionScene motionScene, int i3, int i4) {
            this.f2313a = -1;
            this.f2314b = false;
            this.f2315c = -1;
            this.f2316d = -1;
            this.f2317e = 0;
            this.f2318f = null;
            this.f2319g = -1;
            this.f2320h = 400;
            this.f2321i = 0.0f;
            this.f2323k = new ArrayList();
            this.f2324l = null;
            this.f2325m = new ArrayList();
            this.f2326n = 0;
            this.f2327o = false;
            this.f2328p = -1;
            this.f2329q = 0;
            this.f2330r = 0;
            this.f2313a = i2;
            this.f2322j = motionScene;
            this.f2316d = i3;
            this.f2315c = i4;
            this.f2320h = motionScene.f2302k;
            this.f2329q = motionScene.f2303l;
        }

        Transition(MotionScene motionScene, Context context, XmlPullParser xmlPullParser) {
            this.f2313a = -1;
            this.f2314b = false;
            this.f2315c = -1;
            this.f2316d = -1;
            this.f2317e = 0;
            this.f2318f = null;
            this.f2319g = -1;
            this.f2320h = 400;
            this.f2321i = 0.0f;
            this.f2323k = new ArrayList();
            this.f2324l = null;
            this.f2325m = new ArrayList();
            this.f2326n = 0;
            this.f2327o = false;
            this.f2328p = -1;
            this.f2329q = 0;
            this.f2330r = 0;
            this.f2320h = motionScene.f2302k;
            this.f2329q = motionScene.f2303l;
            this.f2322j = motionScene;
            w(motionScene, context, Xml.asAttributeSet(xmlPullParser));
        }
    }
}
