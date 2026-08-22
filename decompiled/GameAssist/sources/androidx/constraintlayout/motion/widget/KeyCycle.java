package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.SplineSet;
import androidx.constraintlayout.motion.utils.ViewOscillator;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.R;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class KeyCycle extends Key {

    /* renamed from: g, reason: collision with root package name */
    private String f2142g = null;

    /* renamed from: h, reason: collision with root package name */
    private int f2143h = 0;

    /* renamed from: i, reason: collision with root package name */
    private int f2144i = -1;

    /* renamed from: j, reason: collision with root package name */
    private String f2145j = null;

    /* renamed from: k, reason: collision with root package name */
    private float f2146k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    private float f2147l = 0.0f;

    /* renamed from: m, reason: collision with root package name */
    private float f2148m = 0.0f;

    /* renamed from: n, reason: collision with root package name */
    private float f2149n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    private int f2150o = -1;

    /* renamed from: p, reason: collision with root package name */
    private float f2151p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    private float f2152q = Float.NaN;

    /* renamed from: r, reason: collision with root package name */
    private float f2153r = Float.NaN;

    /* renamed from: s, reason: collision with root package name */
    private float f2154s = Float.NaN;
    private float t = Float.NaN;
    private float u = Float.NaN;
    private float v = Float.NaN;
    private float w = Float.NaN;
    private float x = Float.NaN;
    private float y = Float.NaN;
    private float z = Float.NaN;

    private static class Loader {

        /* renamed from: a, reason: collision with root package name */
        private static SparseIntArray f2155a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2155a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyCycle_motionTarget, 1);
            f2155a.append(R.styleable.KeyCycle_framePosition, 2);
            f2155a.append(R.styleable.KeyCycle_transitionEasing, 3);
            f2155a.append(R.styleable.KeyCycle_curveFit, 4);
            f2155a.append(R.styleable.KeyCycle_waveShape, 5);
            f2155a.append(R.styleable.KeyCycle_wavePeriod, 6);
            f2155a.append(R.styleable.KeyCycle_waveOffset, 7);
            f2155a.append(R.styleable.KeyCycle_waveVariesBy, 8);
            f2155a.append(R.styleable.KeyCycle_android_alpha, 9);
            f2155a.append(R.styleable.KeyCycle_android_elevation, 10);
            f2155a.append(R.styleable.KeyCycle_android_rotation, 11);
            f2155a.append(R.styleable.KeyCycle_android_rotationX, 12);
            f2155a.append(R.styleable.KeyCycle_android_rotationY, 13);
            f2155a.append(R.styleable.KeyCycle_transitionPathRotate, 14);
            f2155a.append(R.styleable.KeyCycle_android_scaleX, 15);
            f2155a.append(R.styleable.KeyCycle_android_scaleY, 16);
            f2155a.append(R.styleable.KeyCycle_android_translationX, 17);
            f2155a.append(R.styleable.KeyCycle_android_translationY, 18);
            f2155a.append(R.styleable.KeyCycle_android_translationZ, 19);
            f2155a.append(R.styleable.KeyCycle_motionProgress, 20);
            f2155a.append(R.styleable.KeyCycle_wavePhase, 21);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(KeyCycle keyCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2155a.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyCycle.f2124b);
                            keyCycle.f2124b = resourceId;
                            if (resourceId == -1) {
                                keyCycle.f2125c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyCycle.f2125c = typedArray.getString(index);
                            break;
                        } else {
                            keyCycle.f2124b = typedArray.getResourceId(index, keyCycle.f2124b);
                            break;
                        }
                    case 2:
                        keyCycle.f2123a = typedArray.getInt(index, keyCycle.f2123a);
                        break;
                    case 3:
                        keyCycle.f2142g = typedArray.getString(index);
                        break;
                    case 4:
                        keyCycle.f2143h = typedArray.getInteger(index, keyCycle.f2143h);
                        break;
                    case 5:
                        if (typedArray.peekValue(index).type == 3) {
                            keyCycle.f2145j = typedArray.getString(index);
                            keyCycle.f2144i = 7;
                            break;
                        } else {
                            keyCycle.f2144i = typedArray.getInt(index, keyCycle.f2144i);
                            break;
                        }
                    case 6:
                        keyCycle.f2146k = typedArray.getFloat(index, keyCycle.f2146k);
                        break;
                    case 7:
                        if (typedArray.peekValue(index).type == 5) {
                            keyCycle.f2147l = typedArray.getDimension(index, keyCycle.f2147l);
                            break;
                        } else {
                            keyCycle.f2147l = typedArray.getFloat(index, keyCycle.f2147l);
                            break;
                        }
                    case 8:
                        keyCycle.f2150o = typedArray.getInt(index, keyCycle.f2150o);
                        break;
                    case 9:
                        keyCycle.f2151p = typedArray.getFloat(index, keyCycle.f2151p);
                        break;
                    case 10:
                        keyCycle.f2152q = typedArray.getDimension(index, keyCycle.f2152q);
                        break;
                    case 11:
                        keyCycle.f2153r = typedArray.getFloat(index, keyCycle.f2153r);
                        break;
                    case 12:
                        keyCycle.t = typedArray.getFloat(index, keyCycle.t);
                        break;
                    case 13:
                        keyCycle.u = typedArray.getFloat(index, keyCycle.u);
                        break;
                    case 14:
                        keyCycle.f2154s = typedArray.getFloat(index, keyCycle.f2154s);
                        break;
                    case 15:
                        keyCycle.v = typedArray.getFloat(index, keyCycle.v);
                        break;
                    case 16:
                        keyCycle.w = typedArray.getFloat(index, keyCycle.w);
                        break;
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        keyCycle.x = typedArray.getDimension(index, keyCycle.x);
                        break;
                    case MlKitException.UNSUPPORTED /* 18 */:
                        keyCycle.y = typedArray.getDimension(index, keyCycle.y);
                        break;
                    case 19:
                        keyCycle.z = typedArray.getDimension(index, keyCycle.z);
                        break;
                    case 20:
                        keyCycle.f2149n = typedArray.getFloat(index, keyCycle.f2149n);
                        break;
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                        keyCycle.f2148m = typedArray.getFloat(index, keyCycle.f2148m) / 360.0f;
                        break;
                    default:
                        Log.e("KeyCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2155a.get(index));
                        break;
                }
            }
        }
    }

    public KeyCycle() {
        this.f2126d = 4;
        this.f2127e = new HashMap();
    }

    public void Y(HashMap hashMap) {
        ViewOscillator viewOscillator;
        ViewOscillator viewOscillator2;
        for (String str : hashMap.keySet()) {
            if (str.startsWith("CUSTOM")) {
                ConstraintAttribute constraintAttribute = (ConstraintAttribute) this.f2127e.get(str.substring(7));
                if (constraintAttribute != null && constraintAttribute.d() == ConstraintAttribute.AttributeType.FLOAT_TYPE && (viewOscillator = (ViewOscillator) hashMap.get(str)) != null) {
                    viewOscillator.e(this.f2123a, this.f2144i, this.f2145j, this.f2150o, this.f2146k, this.f2147l, this.f2148m, constraintAttribute.e(), constraintAttribute);
                }
            } else {
                float Z = Z(str);
                if (!Float.isNaN(Z) && (viewOscillator2 = (ViewOscillator) hashMap.get(str)) != null) {
                    viewOscillator2.d(this.f2123a, this.f2144i, this.f2145j, this.f2150o, this.f2146k, this.f2147l, this.f2148m, Z);
                }
            }
        }
    }

    public float Z(String str) {
        str.hashCode();
        switch (str) {
            case "rotationX":
                return this.t;
            case "rotationY":
                return this.u;
            case "translationX":
                return this.x;
            case "translationY":
                return this.y;
            case "translationZ":
                return this.z;
            case "progress":
                return this.f2149n;
            case "scaleX":
                return this.v;
            case "scaleY":
                return this.w;
            case "rotation":
                return this.f2153r;
            case "elevation":
                return this.f2152q;
            case "transitionPathRotate":
                return this.f2154s;
            case "alpha":
                return this.f2151p;
            case "waveOffset":
                return this.f2147l;
            case "wavePhase":
                return this.f2148m;
            default:
                if (str.startsWith("CUSTOM")) {
                    return Float.NaN;
                }
                Log.v("WARNING! KeyCycle", "  UNKNOWN  " + str);
                return Float.NaN;
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void a(HashMap hashMap) {
        Debug.g("KeyCycle", "add " + hashMap.size() + " values", 2);
        for (String str : hashMap.keySet()) {
            SplineSet splineSet = (SplineSet) hashMap.get(str);
            if (splineSet != null) {
                str.hashCode();
                switch (str) {
                    case "rotationX":
                        splineSet.c(this.f2123a, this.t);
                        break;
                    case "rotationY":
                        splineSet.c(this.f2123a, this.u);
                        break;
                    case "translationX":
                        splineSet.c(this.f2123a, this.x);
                        break;
                    case "translationY":
                        splineSet.c(this.f2123a, this.y);
                        break;
                    case "translationZ":
                        splineSet.c(this.f2123a, this.z);
                        break;
                    case "progress":
                        splineSet.c(this.f2123a, this.f2149n);
                        break;
                    case "scaleX":
                        splineSet.c(this.f2123a, this.v);
                        break;
                    case "scaleY":
                        splineSet.c(this.f2123a, this.w);
                        break;
                    case "rotation":
                        splineSet.c(this.f2123a, this.f2153r);
                        break;
                    case "elevation":
                        splineSet.c(this.f2123a, this.f2152q);
                        break;
                    case "transitionPathRotate":
                        splineSet.c(this.f2123a, this.f2154s);
                        break;
                    case "alpha":
                        splineSet.c(this.f2123a, this.f2151p);
                        break;
                    case "waveOffset":
                        splineSet.c(this.f2123a, this.f2147l);
                        break;
                    case "wavePhase":
                        splineSet.c(this.f2123a, this.f2148m);
                        break;
                    default:
                        if (str.startsWith("CUSTOM")) {
                            break;
                        } else {
                            Log.v("WARNING KeyCycle", "  UNKNOWN  " + str);
                            break;
                        }
                }
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: b */
    public Key clone() {
        return new KeyCycle().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key c(Key key) {
        super.c(key);
        KeyCycle keyCycle = (KeyCycle) key;
        this.f2142g = keyCycle.f2142g;
        this.f2143h = keyCycle.f2143h;
        this.f2144i = keyCycle.f2144i;
        this.f2145j = keyCycle.f2145j;
        this.f2146k = keyCycle.f2146k;
        this.f2147l = keyCycle.f2147l;
        this.f2148m = keyCycle.f2148m;
        this.f2149n = keyCycle.f2149n;
        this.f2150o = keyCycle.f2150o;
        this.f2151p = keyCycle.f2151p;
        this.f2152q = keyCycle.f2152q;
        this.f2153r = keyCycle.f2153r;
        this.f2154s = keyCycle.f2154s;
        this.t = keyCycle.t;
        this.u = keyCycle.u;
        this.v = keyCycle.v;
        this.w = keyCycle.w;
        this.x = keyCycle.x;
        this.y = keyCycle.y;
        this.z = keyCycle.z;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void d(HashSet hashSet) {
        if (!Float.isNaN(this.f2151p)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f2152q)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f2153r)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.w)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.f2154s)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.x)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.y)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.z)) {
            hashSet.add("translationZ");
        }
        if (this.f2127e.size() > 0) {
            Iterator it = this.f2127e.keySet().iterator();
            while (it.hasNext()) {
                hashSet.add("CUSTOM," + ((String) it.next()));
            }
        }
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void e(Context context, AttributeSet attributeSet) {
        Loader.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyCycle));
    }
}
