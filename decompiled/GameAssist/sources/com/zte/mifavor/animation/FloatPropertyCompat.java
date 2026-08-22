package com.zte.mifavor.animation;

import android.util.FloatProperty;

/* loaded from: classes2.dex */
public abstract class FloatPropertyCompat<T> {

    /* renamed from: a, reason: collision with root package name */
    final String f17293a;

    /* renamed from: com.zte.mifavor.animation.FloatPropertyCompat$1, reason: invalid class name */
    class AnonymousClass1 extends FloatPropertyCompat<Object> {

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FloatProperty f17294b;

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        public float a(Object obj) {
            return ((Float) this.f17294b.get(obj)).floatValue();
        }

        @Override // com.zte.mifavor.animation.FloatPropertyCompat
        public void b(Object obj, float f2) {
            this.f17294b.setValue(obj, f2);
        }
    }

    public FloatPropertyCompat(String str) {
        this.f17293a = str;
    }

    public abstract float a(Object obj);

    public abstract void b(Object obj, float f2);
}
