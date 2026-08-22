package com.google.android.material.circularreveal;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.math.MathUtils;

/* loaded from: classes.dex */
public interface CircularRevealWidget extends CircularRevealHelper.Delegate {

    public static class CircularRevealEvaluator implements TypeEvaluator<RevealInfo> {

        /* renamed from: b, reason: collision with root package name */
        public static final TypeEvaluator f14212b = new CircularRevealEvaluator();

        /* renamed from: a, reason: collision with root package name */
        private final RevealInfo f14213a = new RevealInfo();

        @Override // android.animation.TypeEvaluator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RevealInfo evaluate(float f2, RevealInfo revealInfo, RevealInfo revealInfo2) {
            this.f14213a.b(MathUtils.d(revealInfo.f14216a, revealInfo2.f14216a, f2), MathUtils.d(revealInfo.f14217b, revealInfo2.f14217b, f2), MathUtils.d(revealInfo.f14218c, revealInfo2.f14218c, f2));
            return this.f14213a;
        }
    }

    public static class CircularRevealProperty extends Property<CircularRevealWidget, RevealInfo> {

        /* renamed from: a, reason: collision with root package name */
        public static final Property f14214a = new CircularRevealProperty("circularReveal");

        private CircularRevealProperty(String str) {
            super(RevealInfo.class, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RevealInfo get(CircularRevealWidget circularRevealWidget) {
            return circularRevealWidget.getRevealInfo();
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(CircularRevealWidget circularRevealWidget, RevealInfo revealInfo) {
            circularRevealWidget.setRevealInfo(revealInfo);
        }
    }

    public static class CircularRevealScrimColorProperty extends Property<CircularRevealWidget, Integer> {

        /* renamed from: a, reason: collision with root package name */
        public static final Property f14215a = new CircularRevealScrimColorProperty("circularRevealScrimColor");

        private CircularRevealScrimColorProperty(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(CircularRevealWidget circularRevealWidget) {
            return Integer.valueOf(circularRevealWidget.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(CircularRevealWidget circularRevealWidget, Integer num) {
            circularRevealWidget.setCircularRevealScrimColor(num.intValue());
        }
    }

    void a();

    void e();

    int getCircularRevealScrimColor();

    RevealInfo getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i2);

    void setRevealInfo(RevealInfo revealInfo);

    public static class RevealInfo {

        /* renamed from: a, reason: collision with root package name */
        public float f14216a;

        /* renamed from: b, reason: collision with root package name */
        public float f14217b;

        /* renamed from: c, reason: collision with root package name */
        public float f14218c;

        private RevealInfo() {
        }

        public boolean a() {
            return this.f14218c == Float.MAX_VALUE;
        }

        public void b(float f2, float f3, float f4) {
            this.f14216a = f2;
            this.f14217b = f3;
            this.f14218c = f4;
        }

        public void c(RevealInfo revealInfo) {
            b(revealInfo.f14216a, revealInfo.f14217b, revealInfo.f14218c);
        }

        public RevealInfo(float f2, float f3, float f4) {
            this.f14216a = f2;
            this.f14217b = f3;
            this.f14218c = f4;
        }

        public RevealInfo(RevealInfo revealInfo) {
            this(revealInfo.f14216a, revealInfo.f14217b, revealInfo.f14218c);
        }
    }
}
