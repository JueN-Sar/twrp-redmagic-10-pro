package androidx.dynamicanimation.animation;

import android.util.FloatProperty;

/* loaded from: classes.dex */
public abstract class FloatPropertyCompat<T> {

    /* renamed from: a, reason: collision with root package name */
    final String f3676a;

    /* renamed from: androidx.dynamicanimation.animation.FloatPropertyCompat$1, reason: invalid class name */
    class AnonymousClass1 extends FloatPropertyCompat<Object> {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FloatProperty f3677b;

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float a(Object obj) {
            return ((Float) this.f3677b.get(obj)).floatValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void b(Object obj, float f2) {
            this.f3677b.setValue(obj, f2);
        }
    }

    public FloatPropertyCompat(String str) {
        this.f3676a = str;
    }

    public abstract float a(Object obj);

    public abstract void b(Object obj, float f2);
}
