package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R;
import com.google.mlkit.common.MlKitException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class KeyAttributes extends Key {

    /* renamed from: g, reason: collision with root package name */
    private String f2128g;

    /* renamed from: h, reason: collision with root package name */
    private int f2129h = -1;

    /* renamed from: i, reason: collision with root package name */
    private boolean f2130i = false;

    /* renamed from: j, reason: collision with root package name */
    private float f2131j = Float.NaN;

    /* renamed from: k, reason: collision with root package name */
    private float f2132k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    private float f2133l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    private float f2134m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    private float f2135n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    private float f2136o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    private float f2137p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    private float f2138q = Float.NaN;

    /* renamed from: r, reason: collision with root package name */
    private float f2139r = Float.NaN;

    /* renamed from: s, reason: collision with root package name */
    private float f2140s = Float.NaN;
    private float t = Float.NaN;
    private float u = Float.NaN;
    private float v = Float.NaN;
    private float w = Float.NaN;

    private static class Loader {

        /* renamed from: a, reason: collision with root package name */
        private static SparseIntArray f2141a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2141a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyAttribute_android_alpha, 1);
            f2141a.append(R.styleable.KeyAttribute_android_elevation, 2);
            f2141a.append(R.styleable.KeyAttribute_android_rotation, 4);
            f2141a.append(R.styleable.KeyAttribute_android_rotationX, 5);
            f2141a.append(R.styleable.KeyAttribute_android_rotationY, 6);
            f2141a.append(R.styleable.KeyAttribute_android_transformPivotX, 19);
            f2141a.append(R.styleable.KeyAttribute_android_transformPivotY, 20);
            f2141a.append(R.styleable.KeyAttribute_android_scaleX, 7);
            f2141a.append(R.styleable.KeyAttribute_transitionPathRotate, 8);
            f2141a.append(R.styleable.KeyAttribute_transitionEasing, 9);
            f2141a.append(R.styleable.KeyAttribute_motionTarget, 10);
            f2141a.append(R.styleable.KeyAttribute_framePosition, 12);
            f2141a.append(R.styleable.KeyAttribute_curveFit, 13);
            f2141a.append(R.styleable.KeyAttribute_android_scaleY, 14);
            f2141a.append(R.styleable.KeyAttribute_android_translationX, 15);
            f2141a.append(R.styleable.KeyAttribute_android_translationY, 16);
            f2141a.append(R.styleable.KeyAttribute_android_translationZ, 17);
            f2141a.append(R.styleable.KeyAttribute_motionProgress, 18);
        }

        public static void a(KeyAttributes keyAttributes, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2141a.get(index)) {
                    case 1:
                        keyAttributes.f2131j = typedArray.getFloat(index, keyAttributes.f2131j);
                        break;
                    case 2:
                        keyAttributes.f2132k = typedArray.getDimension(index, keyAttributes.f2132k);
                        break;
                    case 3:
                    case 11:
                    default:
                        Log.e("KeyAttribute", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2141a.get(index));
                        break;
                    case 4:
                        keyAttributes.f2133l = typedArray.getFloat(index, keyAttributes.f2133l);
                        break;
                    case 5:
                        keyAttributes.f2134m = typedArray.getFloat(index, keyAttributes.f2134m);
                        break;
                    case 6:
                        keyAttributes.f2135n = typedArray.getFloat(index, keyAttributes.f2135n);
                        break;
                    case 7:
                        keyAttributes.f2139r = typedArray.getFloat(index, keyAttributes.f2139r);
                        break;
                    case 8:
                        keyAttributes.f2138q = typedArray.getFloat(index, keyAttributes.f2138q);
                        break;
                    case 9:
                        keyAttributes.f2128g = typedArray.getString(index);
                        break;
                    case 10:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyAttributes.f2124b);
                            keyAttributes.f2124b = resourceId;
                            if (resourceId == -1) {
                                keyAttributes.f2125c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyAttributes.f2125c = typedArray.getString(index);
                            break;
                        } else {
                            keyAttributes.f2124b = typedArray.getResourceId(index, keyAttributes.f2124b);
                            break;
                        }
                    case 12:
                        keyAttributes.f2123a = typedArray.getInt(index, keyAttributes.f2123a);
                        break;
                    case 13:
                        keyAttributes.f2129h = typedArray.getInteger(index, keyAttributes.f2129h);
                        break;
                    case 14:
                        keyAttributes.f2140s = typedArray.getFloat(index, keyAttributes.f2140s);
                        break;
                    case 15:
                        keyAttributes.t = typedArray.getDimension(index, keyAttributes.t);
                        break;
                    case 16:
                        keyAttributes.u = typedArray.getDimension(index, keyAttributes.u);
                        break;
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        keyAttributes.v = typedArray.getDimension(index, keyAttributes.v);
                        break;
                    case MlKitException.UNSUPPORTED /* 18 */:
                        keyAttributes.w = typedArray.getFloat(index, keyAttributes.w);
                        break;
                    case 19:
                        keyAttributes.f2136o = typedArray.getDimension(index, keyAttributes.f2136o);
                        break;
                    case 20:
                        keyAttributes.f2137p = typedArray.getDimension(index, keyAttributes.f2137p);
                        break;
                }
            }
        }
    }

    public KeyAttributes() {
        this.f2126d = 1;
        this.f2127e = new HashMap();
    }

    public void R(String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "motionProgress":
                this.w = k(obj);
                break;
            case "transitionEasing":
                this.f2128g = obj.toString();
                break;
            case "rotationX":
                this.f2134m = k(obj);
                break;
            case "rotationY":
                this.f2135n = k(obj);
                break;
            case "translationX":
                this.t = k(obj);
                break;
            case "translationY":
                this.u = k(obj);
                break;
            case "translationZ":
                this.v = k(obj);
                break;
            case "scaleX":
                this.f2139r = k(obj);
                break;
            case "scaleY":
                this.f2140s = k(obj);
                break;
            case "transformPivotX":
                this.f2136o = k(obj);
                break;
            case "transformPivotY":
                this.f2137p = k(obj);
                break;
            case "rotation":
                this.f2133l = k(obj);
                break;
            case "elevation":
                this.f2132k = k(obj);
                break;
            case "transitionPathRotate":
                this.f2138q = k(obj);
                break;
            case "alpha":
                this.f2131j = k(obj);
                break;
            case "curveFit":
                this.f2129h = l(obj);
                break;
            case "visibility":
                this.f2130i = j(obj);
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:133:0x009a, code lost:
    
        if (r1.equals("scaleY") == false) goto L15;
     */
    @Override // androidx.constraintlayout.motion.widget.Key
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(java.util.HashMap r7) {
        /*
            Method dump skipped, instructions count: 572
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.KeyAttributes.a(java.util.HashMap):void");
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: b */
    public Key clone() {
        return new KeyAttributes().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key c(Key key) {
        super.c(key);
        KeyAttributes keyAttributes = (KeyAttributes) key;
        this.f2129h = keyAttributes.f2129h;
        this.f2130i = keyAttributes.f2130i;
        this.f2131j = keyAttributes.f2131j;
        this.f2132k = keyAttributes.f2132k;
        this.f2133l = keyAttributes.f2133l;
        this.f2134m = keyAttributes.f2134m;
        this.f2135n = keyAttributes.f2135n;
        this.f2136o = keyAttributes.f2136o;
        this.f2137p = keyAttributes.f2137p;
        this.f2138q = keyAttributes.f2138q;
        this.f2139r = keyAttributes.f2139r;
        this.f2140s = keyAttributes.f2140s;
        this.t = keyAttributes.t;
        this.u = keyAttributes.u;
        this.v = keyAttributes.v;
        this.w = keyAttributes.w;
        this.f2128g = keyAttributes.f2128g;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void d(HashSet hashSet) {
        if (!Float.isNaN(this.f2131j)) {
            hashSet.add("alpha");
        }
        if (!Float.isNaN(this.f2132k)) {
            hashSet.add("elevation");
        }
        if (!Float.isNaN(this.f2133l)) {
            hashSet.add("rotation");
        }
        if (!Float.isNaN(this.f2134m)) {
            hashSet.add("rotationX");
        }
        if (!Float.isNaN(this.f2135n)) {
            hashSet.add("rotationY");
        }
        if (!Float.isNaN(this.f2136o)) {
            hashSet.add("transformPivotX");
        }
        if (!Float.isNaN(this.f2137p)) {
            hashSet.add("transformPivotY");
        }
        if (!Float.isNaN(this.t)) {
            hashSet.add("translationX");
        }
        if (!Float.isNaN(this.u)) {
            hashSet.add("translationY");
        }
        if (!Float.isNaN(this.v)) {
            hashSet.add("translationZ");
        }
        if (!Float.isNaN(this.f2138q)) {
            hashSet.add("transitionPathRotate");
        }
        if (!Float.isNaN(this.f2139r)) {
            hashSet.add("scaleX");
        }
        if (!Float.isNaN(this.f2140s)) {
            hashSet.add("scaleY");
        }
        if (!Float.isNaN(this.w)) {
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
        Loader.a(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyAttribute));
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void h(HashMap hashMap) {
        if (this.f2129h == -1) {
            return;
        }
        if (!Float.isNaN(this.f2131j)) {
            hashMap.put("alpha", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2132k)) {
            hashMap.put("elevation", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2133l)) {
            hashMap.put("rotation", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2134m)) {
            hashMap.put("rotationX", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2135n)) {
            hashMap.put("rotationY", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2136o)) {
            hashMap.put("transformPivotX", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2137p)) {
            hashMap.put("transformPivotY", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.t)) {
            hashMap.put("translationX", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.u)) {
            hashMap.put("translationY", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.v)) {
            hashMap.put("translationZ", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2138q)) {
            hashMap.put("transitionPathRotate", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2139r)) {
            hashMap.put("scaleX", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.f2140s)) {
            hashMap.put("scaleY", Integer.valueOf(this.f2129h));
        }
        if (!Float.isNaN(this.w)) {
            hashMap.put("progress", Integer.valueOf(this.f2129h));
        }
        if (this.f2127e.size() > 0) {
            Iterator it = this.f2127e.keySet().iterator();
            while (it.hasNext()) {
                hashMap.put("CUSTOM," + ((String) it.next()), Integer.valueOf(this.f2129h));
            }
        }
    }
}
