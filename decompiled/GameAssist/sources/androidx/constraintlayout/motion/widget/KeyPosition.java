package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.widget.R;
import java.util.HashMap;

/* loaded from: classes.dex */
public class KeyPosition extends KeyPositionBase {

    /* renamed from: h, reason: collision with root package name */
    String f2158h = null;

    /* renamed from: i, reason: collision with root package name */
    int f2159i = Key.f2122f;

    /* renamed from: j, reason: collision with root package name */
    int f2160j = 0;

    /* renamed from: k, reason: collision with root package name */
    float f2161k = Float.NaN;

    /* renamed from: l, reason: collision with root package name */
    float f2162l = Float.NaN;

    /* renamed from: m, reason: collision with root package name */
    float f2163m = Float.NaN;

    /* renamed from: n, reason: collision with root package name */
    float f2164n = Float.NaN;

    /* renamed from: o, reason: collision with root package name */
    float f2165o = Float.NaN;

    /* renamed from: p, reason: collision with root package name */
    float f2166p = Float.NaN;

    /* renamed from: q, reason: collision with root package name */
    int f2167q = 0;

    /* renamed from: r, reason: collision with root package name */
    private float f2168r = Float.NaN;

    /* renamed from: s, reason: collision with root package name */
    private float f2169s = Float.NaN;

    private static class Loader {

        /* renamed from: a, reason: collision with root package name */
        private static SparseIntArray f2170a;

        static {
            SparseIntArray sparseIntArray = new SparseIntArray();
            f2170a = sparseIntArray;
            sparseIntArray.append(R.styleable.KeyPosition_motionTarget, 1);
            f2170a.append(R.styleable.KeyPosition_framePosition, 2);
            f2170a.append(R.styleable.KeyPosition_transitionEasing, 3);
            f2170a.append(R.styleable.KeyPosition_curveFit, 4);
            f2170a.append(R.styleable.KeyPosition_drawPath, 5);
            f2170a.append(R.styleable.KeyPosition_percentX, 6);
            f2170a.append(R.styleable.KeyPosition_percentY, 7);
            f2170a.append(R.styleable.KeyPosition_keyPositionType, 9);
            f2170a.append(R.styleable.KeyPosition_sizePercent, 8);
            f2170a.append(R.styleable.KeyPosition_percentWidth, 11);
            f2170a.append(R.styleable.KeyPosition_percentHeight, 12);
            f2170a.append(R.styleable.KeyPosition_pathMotionArc, 10);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(KeyPosition keyPosition, TypedArray typedArray) {
            int indexCount = typedArray.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = typedArray.getIndex(i2);
                switch (f2170a.get(index)) {
                    case 1:
                        if (MotionLayout.IS_IN_EDIT_MODE) {
                            int resourceId = typedArray.getResourceId(index, keyPosition.f2124b);
                            keyPosition.f2124b = resourceId;
                            if (resourceId == -1) {
                                keyPosition.f2125c = typedArray.getString(index);
                                break;
                            } else {
                                break;
                            }
                        } else if (typedArray.peekValue(index).type == 3) {
                            keyPosition.f2125c = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.f2124b = typedArray.getResourceId(index, keyPosition.f2124b);
                            break;
                        }
                    case 2:
                        keyPosition.f2123a = typedArray.getInt(index, keyPosition.f2123a);
                        break;
                    case 3:
                        if (typedArray.peekValue(index).type == 3) {
                            keyPosition.f2158h = typedArray.getString(index);
                            break;
                        } else {
                            keyPosition.f2158h = Easing.f1763c[typedArray.getInteger(index, 0)];
                            break;
                        }
                    case 4:
                        keyPosition.f2171g = typedArray.getInteger(index, keyPosition.f2171g);
                        break;
                    case 5:
                        keyPosition.f2160j = typedArray.getInt(index, keyPosition.f2160j);
                        break;
                    case 6:
                        keyPosition.f2163m = typedArray.getFloat(index, keyPosition.f2163m);
                        break;
                    case 7:
                        keyPosition.f2164n = typedArray.getFloat(index, keyPosition.f2164n);
                        break;
                    case 8:
                        float f2 = typedArray.getFloat(index, keyPosition.f2162l);
                        keyPosition.f2161k = f2;
                        keyPosition.f2162l = f2;
                        break;
                    case 9:
                        keyPosition.f2167q = typedArray.getInt(index, keyPosition.f2167q);
                        break;
                    case 10:
                        keyPosition.f2159i = typedArray.getInt(index, keyPosition.f2159i);
                        break;
                    case 11:
                        keyPosition.f2161k = typedArray.getFloat(index, keyPosition.f2161k);
                        break;
                    case 12:
                        keyPosition.f2162l = typedArray.getFloat(index, keyPosition.f2162l);
                        break;
                    default:
                        Log.e("KeyPosition", "unused attribute 0x" + Integer.toHexString(index) + "   " + f2170a.get(index));
                        break;
                }
            }
            if (keyPosition.f2123a == -1) {
                Log.e("KeyPosition", "no frame position");
            }
        }
    }

    public KeyPosition() {
        this.f2126d = 2;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void a(HashMap hashMap) {
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    /* renamed from: b */
    public Key clone() {
        return new KeyPosition().c(this);
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public Key c(Key key) {
        super.c(key);
        KeyPosition keyPosition = (KeyPosition) key;
        this.f2158h = keyPosition.f2158h;
        this.f2159i = keyPosition.f2159i;
        this.f2160j = keyPosition.f2160j;
        this.f2161k = keyPosition.f2161k;
        this.f2162l = Float.NaN;
        this.f2163m = keyPosition.f2163m;
        this.f2164n = keyPosition.f2164n;
        this.f2165o = keyPosition.f2165o;
        this.f2166p = keyPosition.f2166p;
        this.f2168r = keyPosition.f2168r;
        this.f2169s = keyPosition.f2169s;
        return this;
    }

    @Override // androidx.constraintlayout.motion.widget.Key
    public void e(Context context, AttributeSet attributeSet) {
        Loader.b(this, context.obtainStyledAttributes(attributeSet, R.styleable.KeyPosition));
    }

    public void m(int i2) {
        this.f2167q = i2;
    }

    public void n(String str, Object obj) {
        str.hashCode();
        switch (str) {
            case "transitionEasing":
                this.f2158h = obj.toString();
                break;
            case "percentWidth":
                this.f2161k = k(obj);
                break;
            case "percentHeight":
                this.f2162l = k(obj);
                break;
            case "drawPath":
                this.f2160j = l(obj);
                break;
            case "sizePercent":
                float k2 = k(obj);
                this.f2161k = k2;
                this.f2162l = k2;
                break;
            case "percentX":
                this.f2163m = k(obj);
                break;
            case "percentY":
                this.f2164n = k(obj);
                break;
        }
    }
}
