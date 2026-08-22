package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class KeyTimeCycle extends Key {

    /* renamed from: g, reason: collision with root package name */
    private String f2172g;

    /* renamed from: h, reason: collision with root package name */
    private int f2173h = -1;

    /* renamed from: i, reason: collision with root package name */
    private float f2174i = Float.NaN;

    /* renamed from: j, reason: collision with root package name */
    private float f2175j = Float.NaN;

    /* renamed from: k, reason: collision with root package name */
    private float f2176k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    private float f2177l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    private float f2178m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    private float f2179n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    private float f2180o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    private float f2181p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    private float f2182q = Float.NaN;

    /* renamed from: r, reason: collision with root package name */
    private float f2183r = Float.NaN;

    /* renamed from: s, reason: collision with root package name */
    private float f2184s = Float.NaN;
    private float t = Float.NaN;
    private int u = 0;
    private String v = null;
    private float w = Float.NaN;
    private float x = 0.0f;

    private static class Loader {

        /* renamed from: a, reason: collision with root package name */
        private static SparseIntArray f2185a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2185a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyTimeCycle_android_alpha, 1);
            f2185a.append(R.styleable.KeyTimeCycle_android_elevation, 2);
            f2185a.append(R.styleable.KeyTimeCycle_android_rotation, 4);
            f2185a.append(R.styleable.KeyTimeCycle_android_rotationX, 5);
            f2185a.append(R.styleable.KeyTimeCycle_android_rotationY, 6);
            f2185a.append(R.styleable.KeyTimeCycle_android_scaleX, 7);
            f2185a.append(R.styleable.KeyTimeCycle_transitionPathRotate, 8);
            f2185a.append(R.styleable.KeyTimeCycle_transitionEasing, 9);
            f2185a.append(R.styleable.KeyTimeCycle_motionTarget, 10);
            f2185a.append(R.styleable.KeyTimeCycle_framePosition, 12);
            f2185a.append(R.styleable.KeyTimeCycle_curveFit, 13);
            f2185a.append(R.styleable.KeyTimeCycle_android_scaleY, 14);
            f2185a.append(R.styleable.KeyTimeCycle_android_translationX, 15);
            f2185a.append(R.styleable.KeyTimeCycle_android_translationY, 16);
            f2185a.append(R.styleable.KeyTimeCycle_android_translationZ, 17);
            f2185a.append(R.styleable.KeyTimeCycle_motionProgress, 18);
            f2185a.append(R.styleable.KeyTimeCycle_wavePeriod, 20);
            f2185a.append(R.styleable.KeyTimeCycle_waveOffset, 21);
            f2185a.append(R.styleable.KeyTimeCycle_waveShape, 19);
        }

        public static void a(KeyTimeCycle keyTimeCycle, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2185a.get(index)) {
                    case 1:
                        keyTimeCycle.f2174i = typedArray.getFloat(index, keyTimeCycle.f2174i);
                        break;
                    case 2:
                        keyTimeCycle.f2175j = typedArray.getDimension(index, keyTimeCycle.f2175j);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyTimeCycle", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2185a.get(index));
                        break;
                    case 4:
                        keyTimeCycle.f2176k = typedArray.getFloat(index, keyTimeCycle.f2176k);
                        break;
                    case 5:
                        keyTimeCycle.f2177l = typedArray.getFloat(index, keyTimeCycle.f2177l);
                        break;
                    case 6:
                        keyTimeCycle.f2178m = typedArray.getFloat(index, keyTimeCycle.f2178m);
                        break;
                    case 7:
                        keyTimeCycle.f2180o = typedArray.getFloat(index, keyTimeCycle.f2180o);
                        break;
                    case 8:
                        keyTimeCycle.f2179n = typedArray.getFloat(index, keyTimeCycle.f2179n);
                        break;
                    case 9:
                        keyTimeCycle.f2172g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyTimeCycle.f2124b);
                            keyTimeCycle.f2124b = resourceId;
                            if (resourceId == -1) {
                                keyTimeCycle.f2125c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyTimeCycle.f2125c = typedArray.getString(index);
                            break;
                        } else {
                            keyTimeCycle.f2124b = typedArray.getResourceId(index, keyTimeCycle.f2124b);
                            break;
                        }
                    case 12:
                        keyTimeCycle.f2123a = typedArray.getInt(index, keyTimeCycle.f2123a);
                        break;
                    case 13:
                        keyTimeCycle.f2173h = typedArray.getInteger(index, keyTimeCycle.f2173h);
                        break;
                    case 14:
                        keyTimeCycle.f2181p = typedArray.getFloat(index, keyTimeCycle.f2181p);
                        break;
                    case 15:
                        keyTimeCycle.f2182q = typedArray.getDimension(index, keyTimeCycle.f2182q);
                        break;
                    case 16:
                        keyTimeCycle.f2183r = typedArray.getDimension(index, keyTimeCycle.f2183r);
                        break;
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        keyTimeCycle.f2184s = typedArray.getDimension(index, keyTimeCycle.f2184s);
                        break;
                    case MlKitException.UNSUPPORTED /* 18 */:
                        keyTimeCycle.t = typedArray.getFloat(index, keyTimeCycle.t);
                        break;
                    case 19:
                        if (typedArray.peekValue(index).type == 3) {
                            keyTimeCycle.v = typedArray.getString(index);
                            keyTimeCycle.u = 7;
                            break;
                        } else {
                            keyTimeCycle.u = typedArray.getInt(index, keyTimeCycle.u);
                            break;
                        }
                    case 20:
                        keyTimeCycle.w = typedArray.getFloat(index, keyTimeCycle.w);
                        break;
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                        if (typedArray.peekValue(index).type == 5) {
                            keyTimeCycle.x = typedArray.getDimension(index, keyTimeCycle.x);
                            break;
                        } else {
                            keyTimeCycle.x = typedArray.getFloat(index, keyTimeCycle.x);
                            break;
                        }
                }
            }
        }
    }

    public KeyTimeCycle() {
        this.f2126d = 3;
        this.f2127e = new HashMap();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0089, code lost:
    
        if (r1.equals("scaleY") == false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void U(java.util.HashMap r11) {
        /*
            Method dump skipped, instructions count: 604
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyTimeCycle.U(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void a(HashMap hashMap) {
        throw new IllegalArgumentException(" KeyTimeCycles do not support SplineSet");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: b */
    public Key clone() {
        return new KeyTimeCycle().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key c(Key key) {
        super.c(key);
        KeyTimeCycle keyTimeCycle = (KeyTimeCycle) key;
        this.f2172g = keyTimeCycle.f2172g;
        this.f2173h = keyTimeCycle.f2173h;
        this.u = keyTimeCycle.u;
        this.w = keyTimeCycle.w;
        this.x = keyTimeCycle.x;
        this.t = keyTimeCycle.t;
        this.f2174i = keyTimeCycle.f2174i;
        this.f2175j = keyTimeCycle.f2175j;
        this.f2176k = keyTimeCycle.f2176k;
        this.f2179n = keyTimeCycle.f2179n;
        this.f2177l = keyTimeCycle.f2177l;
        this.f2178m = keyTimeCycle.f2178m;
        this.f2180o = keyTimeCycle.f2180o;
        this.f2181p = keyTimeCycle.f2181p;
        this.f2182q = keyTimeCycle.f2182q;
        this.f2183r = keyTimeCycle.f2183r;
        this.f2184s = keyTimeCycle.f2184s;
        this.v = keyTimeCycle.v;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void d(HashSet hashSet) {
        if (!Float.isNaN(this.f2174i)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f2175j)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f2176k)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f2177l)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f2178m)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f2182q)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.f2183r)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.f2184s)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f2179n)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f2180o)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f2181p)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("progress");
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
        Loader.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyTimeCycle));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void h(HashMap hashMap) {
        if (this.f2173h == -1) {
            return;
        }
        if (!Float.isNaN(this.f2174i)) {
            hashMap.put("alpha", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2175j)) {
            hashMap.put("elevation", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2176k)) {
            hashMap.put("rotation", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2177l)) {
            hashMap.put("rotationX", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2178m)) {
            hashMap.put("rotationY", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2182q)) {
            hashMap.put("translationX", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2183r)) {
            hashMap.put("translationY", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2184s)) {
            hashMap.put("translationZ", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2179n)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2180o)) {
            hashMap.put("scaleX", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.f2180o)) {
            hashMap.put("scaleY", Integer.valueOf(this.f2173h));
        }
        if (!Float.isNaN(this.t)) {
            hashMap.put("progress", Integer.valueOf(this.f2173h));
        }
        if (this.f2127e.size() > 0) {
            Iterator it = this.f2127e.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + ((String) it.next()), Integer.valueOf(this.f2173h));
            }
        }
    }
}
