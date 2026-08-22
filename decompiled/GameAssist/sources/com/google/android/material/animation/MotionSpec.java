package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.Property;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MotionSpec {

    /* renamed from: a, reason: collision with root package name */
    private final SimpleArrayMap f13827a = new SimpleArrayMap();

    /* renamed from: b, reason: collision with root package name */
    private final SimpleArrayMap f13828b = new SimpleArrayMap();

    private static void a(MotionSpec motionSpec, Animator animator) {
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            motionSpec.l(objectAnimator.getPropertyName(), objectAnimator.getValues());
            motionSpec.m(objectAnimator.getPropertyName(), MotionTiming.b(objectAnimator));
        } else {
            throw new IllegalArgumentException("Animator must be an ObjectAnimator: " + animator);
        }
    }

    private PropertyValuesHolder[] b(PropertyValuesHolder[] propertyValuesHolderArr) {
        PropertyValuesHolder[] propertyValuesHolderArr2 = new PropertyValuesHolder[propertyValuesHolderArr.length];
        for (int i2 = 0; i2 < propertyValuesHolderArr.length; i2++) {
            propertyValuesHolderArr2[i2] = propertyValuesHolderArr[i2].clone();
        }
        return propertyValuesHolderArr2;
    }

    public static MotionSpec c(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        if (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0) {
            return null;
        }
        return d(context, resourceId);
    }

    public static MotionSpec d(Context context, int i2) {
        try {
            Animator loadAnimator = AnimatorInflater.loadAnimator(context, i2);
            if (loadAnimator instanceof AnimatorSet) {
                return e(((AnimatorSet) loadAnimator).getChildAnimations());
            }
            if (loadAnimator == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(loadAnimator);
            return e(arrayList);
        } catch (Exception e2) {
            Log.w("MotionSpec", "Can't load animation resource ID #0x" + Integer.toHexString(i2), e2);
            return null;
        }
    }

    private static MotionSpec e(List list) {
        MotionSpec motionSpec = new MotionSpec();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            a(motionSpec, (Animator) list.get(i2));
        }
        return motionSpec;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MotionSpec) {
            return this.f13827a.equals(((MotionSpec) obj).f13827a);
        }
        return false;
    }

    public ObjectAnimator f(String str, Object obj, Property property) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(obj, g(str));
        ofPropertyValuesHolder.setProperty(property);
        h(str).a(ofPropertyValuesHolder);
        return ofPropertyValuesHolder;
    }

    public PropertyValuesHolder[] g(String str) {
        if (j(str)) {
            return b((PropertyValuesHolder[]) this.f13828b.get(str));
        }
        throw new IllegalArgumentException();
    }

    public MotionTiming h(String str) {
        if (k(str)) {
            return (MotionTiming) this.f13827a.get(str);
        }
        throw new IllegalArgumentException();
    }

    public int hashCode() {
        return this.f13827a.hashCode();
    }

    public long i() {
        int size = this.f13827a.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            MotionTiming motionTiming = (MotionTiming) this.f13827a.j(i2);
            j2 = Math.max(j2, motionTiming.c() + motionTiming.d());
        }
        return j2;
    }

    public boolean j(String str) {
        return this.f13828b.get(str) != null;
    }

    public boolean k(String str) {
        return this.f13827a.get(str) != null;
    }

    public void l(String str, PropertyValuesHolder[] propertyValuesHolderArr) {
        this.f13828b.put(str, propertyValuesHolderArr);
    }

    public void m(String str, MotionTiming motionTiming) {
        this.f13827a.put(str, motionTiming);
    }

    public String toString() {
        return '\n' + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.f13827a + "}\n";
    }
}
