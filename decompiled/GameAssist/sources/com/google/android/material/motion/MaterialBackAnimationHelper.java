package com.google.android.material.motion;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import androidx.activity.BackEventCompat;
import androidx.annotation.RestrictTo;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.google.android.material.R;

@RestrictTo
/* loaded from: classes.dex */
public abstract class MaterialBackAnimationHelper<V extends View> {

    /* renamed from: a, reason: collision with root package name */
    private final TimeInterpolator f14807a;

    /* renamed from: b, reason: collision with root package name */
    protected final View f14808b;

    /* renamed from: c, reason: collision with root package name */
    protected final int f14809c;

    /* renamed from: d, reason: collision with root package name */
    protected final int f14810d;

    /* renamed from: e, reason: collision with root package name */
    protected final int f14811e;

    /* renamed from: f, reason: collision with root package name */
    private BackEventCompat f14812f;

    public MaterialBackAnimationHelper(View view) {
        this.f14808b = view;
        Context context = view.getContext();
        this.f14807a = MotionUtils.g(context, R.attr.motionEasingStandardDecelerateInterpolator, PathInterpolatorCompat.a(0.0f, 0.0f, 0.0f, 1.0f));
        this.f14809c = MotionUtils.f(context, R.attr.motionDurationMedium2, 300);
        this.f14810d = MotionUtils.f(context, R.attr.motionDurationShort3, 150);
        this.f14811e = MotionUtils.f(context, R.attr.motionDurationShort2, 100);
    }

    public float a(float f2) {
        return this.f14807a.getInterpolation(f2);
    }

    protected BackEventCompat b() {
        if (this.f14812f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() and updateBackProgress() before cancelBackProgress()");
        }
        BackEventCompat backEventCompat = this.f14812f;
        this.f14812f = null;
        return backEventCompat;
    }

    public BackEventCompat c() {
        BackEventCompat backEventCompat = this.f14812f;
        this.f14812f = null;
        return backEventCompat;
    }

    protected void d(BackEventCompat backEventCompat) {
        this.f14812f = backEventCompat;
    }

    protected BackEventCompat e(BackEventCompat backEventCompat) {
        if (this.f14812f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        BackEventCompat backEventCompat2 = this.f14812f;
        this.f14812f = backEventCompat;
        return backEventCompat2;
    }
}
