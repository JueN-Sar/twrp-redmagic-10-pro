package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.util.StateSet;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo
/* loaded from: classes.dex */
public final class StateListAnimator {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f14757a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private Tuple f14758b = null;

    /* renamed from: c, reason: collision with root package name */
    ValueAnimator f14759c = null;

    /* renamed from: d, reason: collision with root package name */
    private final Animator.AnimatorListener f14760d = new AnimatorListenerAdapter() { // from class: com.google.android.material.internal.StateListAnimator.1
        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            StateListAnimator stateListAnimator = StateListAnimator.this;
            if (stateListAnimator.f14759c == animator) {
                stateListAnimator.f14759c = null;
            }
        }
    };

    static class Tuple {

        /* renamed from: a, reason: collision with root package name */
        final int[] f14762a;

        /* renamed from: b, reason: collision with root package name */
        final ValueAnimator f14763b;

        Tuple(int[] iArr, ValueAnimator valueAnimator) {
            this.f14762a = iArr;
            this.f14763b = valueAnimator;
        }
    }

    private void b() {
        ValueAnimator valueAnimator = this.f14759c;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.f14759c = null;
        }
    }

    private void e(Tuple tuple) {
        ValueAnimator valueAnimator = tuple.f14763b;
        this.f14759c = valueAnimator;
        valueAnimator.start();
    }

    public void a(int[] iArr, ValueAnimator valueAnimator) {
        Tuple tuple = new Tuple(iArr, valueAnimator);
        valueAnimator.addListener(this.f14760d);
        this.f14757a.add(tuple);
    }

    public void c() {
        ValueAnimator valueAnimator = this.f14759c;
        if (valueAnimator != null) {
            valueAnimator.end();
            this.f14759c = null;
        }
    }

    public void d(int[] iArr) {
        Tuple tuple;
        int size = this.f14757a.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                tuple = null;
                break;
            }
            tuple = (Tuple) this.f14757a.get(i2);
            if (StateSet.stateSetMatches(tuple.f14762a, iArr)) {
                break;
            } else {
                i2++;
            }
        }
        Tuple tuple2 = this.f14758b;
        if (tuple == tuple2) {
            return;
        }
        if (tuple2 != null) {
            b();
        }
        this.f14758b = tuple;
        if (tuple != null) {
            e(tuple);
        }
    }
}
