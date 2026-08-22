package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionHelper;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Carousel extends MotionHelper {
    private static final boolean DEBUG = false;
    private static final String TAG = "Carousel";
    public static final int TOUCH_UP_CARRY_ON = 2;
    public static final int TOUCH_UP_IMMEDIATE_STOP = 1;
    private Adapter mAdapter;
    private int mAnimateTargetDelay;
    private int mBackwardTransition;
    private float mDampening;
    private int mEmptyViewBehavior;
    private int mFirstViewReference;
    private int mForwardTransition;
    private int mIndex;
    private boolean mInfiniteCarousel;
    int mLastStartId;
    private final ArrayList<View> mList;
    private MotionLayout mMotionLayout;
    private int mNextState;
    private int mPreviousIndex;
    private int mPreviousState;
    private int mStartIndex;
    private int mTargetIndex;
    private int mTouchUpMode;
    Runnable mUpdateRunnable;
    private float mVelocityThreshold;

    public interface Adapter {
        void a(int i2);

        void b(View view, int i2);

        int count();
    }

    public Carousel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAdapter = null;
        this.mList = new ArrayList<>();
        this.mPreviousIndex = 0;
        this.mIndex = 0;
        this.mFirstViewReference = -1;
        this.mInfiniteCarousel = false;
        this.mBackwardTransition = -1;
        this.mForwardTransition = -1;
        this.mPreviousState = -1;
        this.mNextState = -1;
        this.mDampening = 0.9f;
        this.mStartIndex = 0;
        this.mEmptyViewBehavior = 4;
        this.mTouchUpMode = 1;
        this.mVelocityThreshold = 2.0f;
        this.mTargetIndex = -1;
        this.mAnimateTargetDelay = 200;
        this.mLastStartId = -1;
        this.mUpdateRunnable = new Runnable() { // from class: androidx.constraintlayout.helper.widget.Carousel.1
            @Override // java.lang.Runnable
            public void run() {
                Carousel.this.mMotionLayout.setProgress(0.0f);
                Carousel.this.R();
                Carousel.this.mAdapter.a(Carousel.this.mIndex);
                float velocity = Carousel.this.mMotionLayout.getVelocity();
                if (Carousel.this.mTouchUpMode != 2 || velocity <= Carousel.this.mVelocityThreshold || Carousel.this.mIndex >= Carousel.this.mAdapter.count() - 1) {
                    return;
                }
                final float f2 = velocity * Carousel.this.mDampening;
                if (Carousel.this.mIndex != 0 || Carousel.this.mPreviousIndex <= Carousel.this.mIndex) {
                    if (Carousel.this.mIndex != Carousel.this.mAdapter.count() - 1 || Carousel.this.mPreviousIndex >= Carousel.this.mIndex) {
                        Carousel.this.mMotionLayout.post(new Runnable() { // from class: androidx.constraintlayout.helper.widget.Carousel.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Carousel.this.mMotionLayout.G0(5, 1.0f, f2);
                            }
                        });
                    }
                }
            }
        };
        P(context, attributeSet);
    }

    private boolean O(int i2, boolean z) {
        MotionLayout motionLayout;
        MotionScene.Transition s0;
        if (i2 == -1 || (motionLayout = this.mMotionLayout) == null || (s0 = motionLayout.s0(i2)) == null || z == s0.C()) {
            return false;
        }
        s0.F(z);
        return true;
    }

    private void P(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Carousel);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.Carousel_carousel_firstView) {
                    this.mFirstViewReference = obtainStyledAttributes.getResourceId(index, this.mFirstViewReference);
                } else if (index == R.styleable.Carousel_carousel_backwardTransition) {
                    this.mBackwardTransition = obtainStyledAttributes.getResourceId(index, this.mBackwardTransition);
                } else if (index == R.styleable.Carousel_carousel_forwardTransition) {
                    this.mForwardTransition = obtainStyledAttributes.getResourceId(index, this.mForwardTransition);
                } else if (index == R.styleable.Carousel_carousel_emptyViewsBehavior) {
                    this.mEmptyViewBehavior = obtainStyledAttributes.getInt(index, this.mEmptyViewBehavior);
                } else if (index == R.styleable.Carousel_carousel_previousState) {
                    this.mPreviousState = obtainStyledAttributes.getResourceId(index, this.mPreviousState);
                } else if (index == R.styleable.Carousel_carousel_nextState) {
                    this.mNextState = obtainStyledAttributes.getResourceId(index, this.mNextState);
                } else if (index == R.styleable.Carousel_carousel_touchUp_dampeningFactor) {
                    this.mDampening = obtainStyledAttributes.getFloat(index, this.mDampening);
                } else if (index == R.styleable.Carousel_carousel_touchUpMode) {
                    this.mTouchUpMode = obtainStyledAttributes.getInt(index, this.mTouchUpMode);
                } else if (index == R.styleable.Carousel_carousel_touchUp_velocityThreshold) {
                    this.mVelocityThreshold = obtainStyledAttributes.getFloat(index, this.mVelocityThreshold);
                } else if (index == R.styleable.Carousel_carousel_infinite) {
                    this.mInfiniteCarousel = obtainStyledAttributes.getBoolean(index, this.mInfiniteCarousel);
                }
            }
            obtainStyledAttributes.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Q() {
        this.mMotionLayout.setTransitionDuration(this.mAnimateTargetDelay);
        if (this.mTargetIndex < this.mIndex) {
            this.mMotionLayout.L0(this.mPreviousState, this.mAnimateTargetDelay);
        } else {
            this.mMotionLayout.L0(this.mNextState, this.mAnimateTargetDelay);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R() {
        Adapter adapter = this.mAdapter;
        if (adapter == null || this.mMotionLayout == null || adapter.count() == 0) {
            return;
        }
        int size = this.mList.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view = this.mList.get(i2);
            int i3 = (this.mIndex + i2) - this.mStartIndex;
            if (this.mInfiniteCarousel) {
                if (i3 < 0) {
                    int i4 = this.mEmptyViewBehavior;
                    if (i4 != 4) {
                        T(view, i4);
                    } else {
                        T(view, 0);
                    }
                    if (i3 % this.mAdapter.count() == 0) {
                        this.mAdapter.b(view, 0);
                    } else {
                        Adapter adapter2 = this.mAdapter;
                        adapter2.b(view, adapter2.count() + (i3 % this.mAdapter.count()));
                    }
                } else if (i3 >= this.mAdapter.count()) {
                    if (i3 == this.mAdapter.count()) {
                        i3 = 0;
                    } else if (i3 > this.mAdapter.count()) {
                        i3 %= this.mAdapter.count();
                    }
                    int i5 = this.mEmptyViewBehavior;
                    if (i5 != 4) {
                        T(view, i5);
                    } else {
                        T(view, 0);
                    }
                    this.mAdapter.b(view, i3);
                } else {
                    T(view, 0);
                    this.mAdapter.b(view, i3);
                }
            } else if (i3 < 0) {
                T(view, this.mEmptyViewBehavior);
            } else if (i3 >= this.mAdapter.count()) {
                T(view, this.mEmptyViewBehavior);
            } else {
                T(view, 0);
                this.mAdapter.b(view, i3);
            }
        }
        int i6 = this.mTargetIndex;
        if (i6 != -1 && i6 != this.mIndex) {
            this.mMotionLayout.post(new Runnable() { // from class: androidx.constraintlayout.helper.widget.a
                @Override // java.lang.Runnable
                public final void run() {
                    Carousel.this.Q();
                }
            });
        } else if (i6 == this.mIndex) {
            this.mTargetIndex = -1;
        }
        if (this.mBackwardTransition == -1 || this.mForwardTransition == -1) {
            Log.w(TAG, "No backward or forward transitions defined for Carousel!");
            return;
        }
        if (this.mInfiniteCarousel) {
            return;
        }
        int count = this.mAdapter.count();
        if (this.mIndex == 0) {
            O(this.mBackwardTransition, false);
        } else {
            O(this.mBackwardTransition, true);
            this.mMotionLayout.setTransition(this.mBackwardTransition);
        }
        if (this.mIndex == count - 1) {
            O(this.mForwardTransition, false);
        } else {
            O(this.mForwardTransition, true);
            this.mMotionLayout.setTransition(this.mForwardTransition);
        }
    }

    private boolean S(int i2, View view, int i3) {
        ConstraintSet.Constraint v;
        ConstraintSet q0 = this.mMotionLayout.q0(i2);
        if (q0 == null || (v = q0.v(view.getId())) == null) {
            return false;
        }
        v.f2489c.f2543c = 1;
        view.setVisibility(i3);
        return true;
    }

    private boolean T(View view, int i2) {
        MotionLayout motionLayout = this.mMotionLayout;
        if (motionLayout == null) {
            return false;
        }
        boolean z = false;
        for (int i3 : motionLayout.getConstraintSetIds()) {
            z |= S(i3, view, i2);
        }
        return z;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
    public void a(MotionLayout motionLayout, int i2, int i3, float f2) {
        this.mLastStartId = i2;
    }

    @Override // androidx.constraintlayout.motion.widget.MotionHelper, androidx.constraintlayout.motion.widget.MotionLayout.TransitionListener
    public void b(MotionLayout motionLayout, int i2) {
        int i3 = this.mIndex;
        this.mPreviousIndex = i3;
        if (i2 == this.mNextState) {
            this.mIndex = i3 + 1;
        } else if (i2 == this.mPreviousState) {
            this.mIndex = i3 - 1;
        }
        if (this.mInfiniteCarousel) {
            if (this.mIndex >= this.mAdapter.count()) {
                this.mIndex = 0;
            }
            if (this.mIndex < 0) {
                this.mIndex = this.mAdapter.count() - 1;
            }
        } else {
            if (this.mIndex >= this.mAdapter.count()) {
                this.mIndex = this.mAdapter.count() - 1;
            }
            if (this.mIndex < 0) {
                this.mIndex = 0;
            }
        }
        if (this.mPreviousIndex != this.mIndex) {
            this.mMotionLayout.post(this.mUpdateRunnable);
        }
    }

    public int getCount() {
        Adapter adapter = this.mAdapter;
        if (adapter != null) {
            return adapter.count();
        }
        return 0;
    }

    public int getCurrentIndex() {
        return this.mIndex;
    }

    @Override // androidx.constraintlayout.widget.ConstraintHelper, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getParent() instanceof MotionLayout) {
            MotionLayout motionLayout = (MotionLayout) getParent();
            this.mList.clear();
            for (int i2 = 0; i2 < this.mCount; i2++) {
                int i3 = this.mIds[i2];
                View q2 = motionLayout.q(i3);
                if (this.mFirstViewReference == i3) {
                    this.mStartIndex = i2;
                }
                this.mList.add(q2);
            }
            this.mMotionLayout = motionLayout;
            if (this.mTouchUpMode == 2) {
                MotionScene.Transition s0 = motionLayout.s0(this.mForwardTransition);
                if (s0 != null) {
                    s0.H(5);
                }
                MotionScene.Transition s02 = this.mMotionLayout.s0(this.mBackwardTransition);
                if (s02 != null) {
                    s02.H(5);
                }
            }
            R();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mList.clear();
    }

    public void setAdapter(Adapter adapter) {
        this.mAdapter = adapter;
    }

    public void setInfinite(boolean z) {
        this.mInfiniteCarousel = z;
    }

    public Carousel(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mAdapter = null;
        this.mList = new ArrayList<>();
        this.mPreviousIndex = 0;
        this.mIndex = 0;
        this.mFirstViewReference = -1;
        this.mInfiniteCarousel = false;
        this.mBackwardTransition = -1;
        this.mForwardTransition = -1;
        this.mPreviousState = -1;
        this.mNextState = -1;
        this.mDampening = 0.9f;
        this.mStartIndex = 0;
        this.mEmptyViewBehavior = 4;
        this.mTouchUpMode = 1;
        this.mVelocityThreshold = 2.0f;
        this.mTargetIndex = -1;
        this.mAnimateTargetDelay = 200;
        this.mLastStartId = -1;
        this.mUpdateRunnable = new Runnable() { // from class: androidx.constraintlayout.helper.widget.Carousel.1
            @Override // java.lang.Runnable
            public void run() {
                Carousel.this.mMotionLayout.setProgress(0.0f);
                Carousel.this.R();
                Carousel.this.mAdapter.a(Carousel.this.mIndex);
                float velocity = Carousel.this.mMotionLayout.getVelocity();
                if (Carousel.this.mTouchUpMode != 2 || velocity <= Carousel.this.mVelocityThreshold || Carousel.this.mIndex >= Carousel.this.mAdapter.count() - 1) {
                    return;
                }
                final float f2 = velocity * Carousel.this.mDampening;
                if (Carousel.this.mIndex != 0 || Carousel.this.mPreviousIndex <= Carousel.this.mIndex) {
                    if (Carousel.this.mIndex != Carousel.this.mAdapter.count() - 1 || Carousel.this.mPreviousIndex >= Carousel.this.mIndex) {
                        Carousel.this.mMotionLayout.post(new Runnable() { // from class: androidx.constraintlayout.helper.widget.Carousel.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                Carousel.this.mMotionLayout.G0(5, 1.0f, f2);
                            }
                        });
                    }
                }
            }
        };
        P(context, attributeSet);
    }
}
