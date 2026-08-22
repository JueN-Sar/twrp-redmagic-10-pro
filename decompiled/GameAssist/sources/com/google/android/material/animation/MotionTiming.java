package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* loaded from: classes.dex */
public class MotionTiming {

    /* renamed from: a, reason: collision with root package name */
    private long f13829a;

    /* renamed from: b, reason: collision with root package name */
    private long f13830b;

    /* renamed from: c, reason: collision with root package name */
    private TimeInterpolator f13831c;

    /* renamed from: d, reason: collision with root package name */
    private int f13832d;

    /* renamed from: e, reason: collision with root package name */
    private int f13833e;

    public MotionTiming(long j2, long j3) {
        this.f13831c = null;
        this.f13832d = 0;
        this.f13833e = 1;
        this.f13829a = j2;
        this.f13830b = j3;
    }

    static MotionTiming b(ValueAnimator valueAnimator) {
        MotionTiming motionTiming = new MotionTiming(valueAnimator.getStartDelay(), valueAnimator.getDuration(), f(valueAnimator));
        motionTiming.f13832d = valueAnimator.getRepeatCount();
        motionTiming.f13833e = valueAnimator.getRepeatMode();
        return motionTiming;
    }

    private static TimeInterpolator f(ValueAnimator valueAnimator) {
        TimeInterpolator interpolator = valueAnimator.getInterpolator();
        return ((interpolator instanceof AccelerateDecelerateInterpolator) || interpolator == null) ? AnimationUtils.f13815b : interpolator instanceof AccelerateInterpolator ? AnimationUtils.f13816c : interpolator instanceof DecelerateInterpolator ? AnimationUtils.f13817d : interpolator;
    }

    public void a(Animator animator) {
        animator.setStartDelay(c());
        animator.setDuration(d());
        animator.setInterpolator(e());
        if (animator instanceof ValueAnimator) {
            ValueAnimator valueAnimator = (ValueAnimator) animator;
            valueAnimator.setRepeatCount(g());
            valueAnimator.setRepeatMode(h());
        }
    }

    public long c() {
        return this.f13829a;
    }

    public long d() {
        return this.f13830b;
    }

    public TimeInterpolator e() {
        TimeInterpolator timeInterpolator = this.f13831c;
        return timeInterpolator != null ? timeInterpolator : AnimationUtils.f13815b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MotionTiming)) {
            return false;
        }
        MotionTiming motionTiming = (MotionTiming) obj;
        if (c() == motionTiming.c() && d() == motionTiming.d() && g() == motionTiming.g() && h() == motionTiming.h()) {
            return e().getClass().equals(motionTiming.e().getClass());
        }
        return false;
    }

    public int g() {
        return this.f13832d;
    }

    public int h() {
        return this.f13833e;
    }

    public int hashCode() {
        return (((((((((int) (c() ^ (c() >>> 32))) * 31) + ((int) (d() ^ (d() >>> 32)))) * 31) + e().getClass().hashCode()) * 31) + g()) * 31) + h();
    }

    public String toString() {
        return '\n' + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " delay: " + c() + " duration: " + d() + " interpolator: " + e().getClass() + " repeatCount: " + g() + " repeatMode: " + h() + "}\n";
    }

    public MotionTiming(long j2, long j3, TimeInterpolator timeInterpolator) {
        this.f13832d = 0;
        this.f13833e = 1;
        this.f13829a = j2;
        this.f13830b = j3;
        this.f13831c = timeInterpolator;
    }
}
