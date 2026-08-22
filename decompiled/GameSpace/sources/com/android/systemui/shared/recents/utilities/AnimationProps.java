package com.android.systemui.shared.recents.utilities;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.util.SparseArray;
import android.util.SparseLongArray;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

/* loaded from: classes2.dex */
public class AnimationProps {
    public static final int ALL = 0;
    public static final int ALPHA = 4;
    public static final int BOUNDS = 6;
    public static final int DIM_ALPHA = 7;
    public static final AnimationProps IMMEDIATE;
    private static final Interpolator LINEAR_INTERPOLATOR;
    public static final int SCALE = 5;
    public static final int TRANSLATION_X = 1;
    public static final int TRANSLATION_Y = 2;
    public static final int TRANSLATION_Z = 3;
    private Animator.AnimatorListener mListener;
    private SparseLongArray mPropDuration;
    private SparseArray<Interpolator> mPropInterpolators;
    private SparseLongArray mPropStartDelay;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PropType {
    }

    static {
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        LINEAR_INTERPOLATOR = linearInterpolator;
        IMMEDIATE = new AnimationProps(0, linearInterpolator);
    }

    public AnimationProps() {
    }

    public AnimationProps(int i, int i2, Interpolator interpolator) {
        this(i, i2, interpolator, null);
    }

    public AnimationProps(int i, int i2, Interpolator interpolator, Animator.AnimatorListener animatorListener) {
        setStartDelay(0, i);
        setDuration(0, i2);
        setInterpolator(0, interpolator);
        setListener(animatorListener);
    }

    public AnimationProps(int i, Interpolator interpolator) {
        this(0, i, interpolator, null);
    }

    public AnimationProps(int i, Interpolator interpolator, Animator.AnimatorListener animatorListener) {
        this(0, i, interpolator, animatorListener);
    }

    public <T extends ValueAnimator> T apply(int i, T t) {
        t.setStartDelay(getStartDelay(i));
        t.setDuration(getDuration(i));
        t.setInterpolator(getInterpolator(i));
        return t;
    }

    public AnimatorSet createAnimator(List<Animator> list) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator.AnimatorListener animatorListener = this.mListener;
        if (animatorListener != null) {
            animatorSet.addListener(animatorListener);
        }
        animatorSet.playTogether(list);
        return animatorSet;
    }

    public long getDuration(int i) {
        SparseLongArray sparseLongArray = this.mPropDuration;
        if (sparseLongArray == null) {
            return 0L;
        }
        long j = sparseLongArray.get(i, -1L);
        return j != -1 ? j : this.mPropDuration.get(0, 0L);
    }

    public Interpolator getInterpolator(int i) {
        SparseArray<Interpolator> sparseArray = this.mPropInterpolators;
        if (sparseArray == null) {
            return LINEAR_INTERPOLATOR;
        }
        Interpolator interpolator = sparseArray.get(i);
        return interpolator != null ? interpolator : this.mPropInterpolators.get(0, LINEAR_INTERPOLATOR);
    }

    public Animator.AnimatorListener getListener() {
        return this.mListener;
    }

    public long getStartDelay(int i) {
        SparseLongArray sparseLongArray = this.mPropStartDelay;
        if (sparseLongArray == null) {
            return 0L;
        }
        long j = sparseLongArray.get(i, -1L);
        return j != -1 ? j : this.mPropStartDelay.get(0, 0L);
    }

    public boolean isImmediate() {
        int size = this.mPropDuration.size();
        for (int i = 0; i < size; i++) {
            if (this.mPropDuration.valueAt(i) > 0) {
                return false;
            }
        }
        return true;
    }

    public AnimationProps setDuration(int i, int i2) {
        if (this.mPropDuration == null) {
            this.mPropDuration = new SparseLongArray();
        }
        this.mPropDuration.append(i, i2);
        return this;
    }

    public AnimationProps setInterpolator(int i, Interpolator interpolator) {
        if (this.mPropInterpolators == null) {
            this.mPropInterpolators = new SparseArray<>();
        }
        this.mPropInterpolators.append(i, interpolator);
        return this;
    }

    public AnimationProps setListener(Animator.AnimatorListener animatorListener) {
        this.mListener = animatorListener;
        return this;
    }

    public AnimationProps setStartDelay(int i, int i2) {
        if (this.mPropStartDelay == null) {
            this.mPropStartDelay = new SparseLongArray();
        }
        this.mPropStartDelay.append(i, i2);
        return this;
    }
}
