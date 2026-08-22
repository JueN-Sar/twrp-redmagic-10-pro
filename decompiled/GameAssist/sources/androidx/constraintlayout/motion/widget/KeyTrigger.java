package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

/* loaded from: classes.dex */
public class KeyTrigger extends Key {
    private boolean A;

    /* renamed from: g, reason: collision with root package name */
    float f2186g = 0.1f;

    /* renamed from: h, reason: collision with root package name */
    int f2187h;

    /* renamed from: i, reason: collision with root package name */
    int f2188i;

    /* renamed from: j, reason: collision with root package name */
    int f2189j;

    /* renamed from: k, reason: collision with root package name */
    RectF f2190k;

    /* renamed from: l, reason: collision with root package name */
    RectF f2191l;

    /* renamed from: m, reason: collision with root package name */
    HashMap f2192m;

    /* renamed from: n, reason: collision with root package name */
    private int f2193n;

    /* renamed from: o, reason: collision with root package name */
    private String f2194o;

    /* renamed from: p, reason: collision with root package name */
    private int f2195p;

    /* renamed from: q, reason: collision with root package name */
    private String f2196q;

    /* renamed from: r, reason: collision with root package name */
    private String f2197r;

    /* renamed from: s, reason: collision with root package name */
    private int f2198s;
    private int t;
    private View u;
    private boolean v;
    private boolean w;
    private boolean x;
    private float y;
    private float z;

    private static class Loader {

        /* renamed from: a, reason: collision with root package name */
        private static SparseIntArray f2199a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2199a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTrigger_framePosition, 8);
            f2199a.append(R.styleable.KeyTrigger_onCross, 4);
            f2199a.append(R.styleable.KeyTrigger_onNegativeCross, 1);
            f2199a.append(R.styleable.KeyTrigger_onPositiveCross, 2);
            f2199a.append(R.styleable.KeyTrigger_motionTarget, 7);
            f2199a.append(R.styleable.KeyTrigger_triggerId, 6);
            f2199a.append(R.styleable.KeyTrigger_triggerSlack, 5);
            f2199a.append(R.styleable.KeyTrigger_motion_triggerOnCollision, 9);
            f2199a.append(R.styleable.KeyTrigger_motion_postLayoutCollision, 10);
            f2199a.append(R.styleable.KeyTrigger_triggerReceiver, 11);
            f2199a.append(R.styleable.KeyTrigger_viewTransitionOnCross, 12);
            f2199a.append(R.styleable.KeyTrigger_viewTransitionOnNegativeCross, 13);
            f2199a.append(R.styleable.KeyTrigger_viewTransitionOnPositiveCross, 14);
        }

        public static void a(KeyTrigger keyTrigger, TypedArray typedArray, Context context) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2199a.get(index)) {
                    case 1:
                        keyTrigger.f2196q = typedArray.getString(index);
                        break;
                    case 2:
                        keyTrigger.f2197r = typedArray.getString(index);
                        break;
                    case 3:
                    default:
                        Log.e("KeyTrigger", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2199a.get(index));
                        break;
                    case 4:
                        keyTrigger.f2194o = typedArray.getString(index);
                        break;
                    case 5:
                        keyTrigger.f2186g = typedArray.getFloat(index, keyTrigger.f2186g);
                        break;
                    case 6:
                        keyTrigger.f2198s = typedArray.getResourceId(index, keyTrigger.f2198s);
                        break;
                    case 7:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyTrigger.f2124b);
                            keyTrigger.f2124b = resourceId;
                            if (resourceId == -1) {
                                keyTrigger.f2125c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyTrigger.f2125c = typedArray.getString(index);
                            break;
                        } else {
                            keyTrigger.f2124b = typedArray.getResourceId(index, keyTrigger.f2124b);
                            break;
                        }
                    case 8:
                        int integer = typedArray.getInteger(index, keyTrigger.f2123a);
                        keyTrigger.f2123a = integer;
                        keyTrigger.y = (integer + 0.5f) / 100.0f;
                        break;
                    case 9:
                        keyTrigger.t = typedArray.getResourceId(index, keyTrigger.t);
                        break;
                    case 10:
                        keyTrigger.A = typedArray.getBoolean(index, keyTrigger.A);
                        break;
                    case 11:
                        keyTrigger.f2195p = typedArray.getResourceId(index, keyTrigger.f2195p);
                        break;
                    case 12:
                        keyTrigger.f2189j = typedArray.getResourceId(index, keyTrigger.f2189j);
                        break;
                    case 13:
                        keyTrigger.f2187h = typedArray.getResourceId(index, keyTrigger.f2187h);
                        break;
                    case 14:
                        keyTrigger.f2188i = typedArray.getResourceId(index, keyTrigger.f2188i);
                        break;
                }
            }
        }
    }

    public KeyTrigger() {
        int i2 = Key.f2122f;
        this.f2187h = i2;
        this.f2188i = i2;
        this.f2189j = i2;
        this.f2190k = new RectF();
        this.f2191l = new RectF();
        this.f2192m = new HashMap();
        this.f2193n = -1;
        this.f2194o = null;
        int i3 = Key.f2122f;
        this.f2195p = i3;
        this.f2196q = null;
        this.f2197r = null;
        this.f2198s = i3;
        this.t = i3;
        this.u = null;
        this.v = true;
        this.w = true;
        this.x = true;
        this.y = Float.NaN;
        this.A = false;
        this.f2126d = 5;
        this.f2127e = new HashMap();
    }

    private void A(String str, View view) {
        boolean z = str.length() == 1;
        if (!z) {
            str = str.substring(1).toLowerCase(Locale.ROOT);
        }
        for (String str2 : this.f2127e.keySet()) {
            String lowerCase = str2.toLowerCase(Locale.ROOT);
            if (z || lowerCase.matches(str)) {
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2127e.get(str2);
                if (constraintAttribute != null) {
                    constraintAttribute.a(view);
                }
            }
        }
    }

    private void B(RectF rectF, View view, boolean z) {
        rectF.top = view.getTop();
        rectF.bottom = view.getBottom();
        rectF.left = view.getLeft();
        rectF.right = view.getRight();
        if (z) {
            view.getMatrix().mapRect(rectF);
        }
    }

    private void z(String str, View view) {
        Method method;
        if (str == null) {
            return;
        }
        if (str.startsWith(".")) {
            A(str, view);
            return;
        }
        if (this.f2192m.containsKey(str)) {
            method = (Method) this.f2192m.get(str);
            if (method == null) {
                return;
            }
        } else {
            method = null;
        }
        if (method == null) {
            try {
                method = view.getClass().getMethod(str, null);
                this.f2192m.put(str, method);
            } catch (NoSuchMethodException unused) {
                this.f2192m.put(str, null);
                Log.e("KeyTrigger", "Could not find method \"" + str + "\"on class " + view.getClass().getSimpleName() + " " + Debug.d(view));
                return;
            }
        }
        try {
            method.invoke(view, null);
        } catch (Exception unused2) {
            Log.e("KeyTrigger", "Exception in call \"" + this.f2194o + "\"on class " + view.getClass().getSimpleName() + " " + Debug.d(view));
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void a(HashMap hashMap) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: b */
    public Key clone() {
        return new KeyTrigger().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key c(Key key) {
        super.c(key);
        KeyTrigger keyTrigger = (KeyTrigger) key;
        this.f2193n = keyTrigger.f2193n;
        this.f2194o = keyTrigger.f2194o;
        this.f2195p = keyTrigger.f2195p;
        this.f2196q = keyTrigger.f2196q;
        this.f2197r = keyTrigger.f2197r;
        this.f2198s = keyTrigger.f2198s;
        this.t = keyTrigger.t;
        this.u = keyTrigger.u;
        this.f2186g = keyTrigger.f2186g;
        this.v = keyTrigger.v;
        this.w = keyTrigger.w;
        this.x = keyTrigger.x;
        this.y = keyTrigger.y;
        this.z = keyTrigger.z;
        this.A = keyTrigger.A;
        this.f2190k = keyTrigger.f2190k;
        this.f2191l = keyTrigger.f2191l;
        this.f2192m = keyTrigger.f2192m;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void d(HashSet hashSet) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void e(Context context, AttributeSet attributeSet) {
        Loader.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTrigger), context);
    }

    /* JADX WARN: Removed duplicated region for block: B:67:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00a1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void y(float r10, android.view.View r11) {
        /*
            Method dump skipped, instructions count: 353
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTrigger.y(float, android.view.View):void");
    }
}
