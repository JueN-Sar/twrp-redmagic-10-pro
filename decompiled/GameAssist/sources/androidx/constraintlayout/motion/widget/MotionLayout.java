package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Flow;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.core.widgets.Placeholder;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import androidx.constraintlayout.motion.utils.StopLogic;
import androidx.constraintlayout.motion.utils.ViewState;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.widget.Barrier;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintLayoutStates;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Constraints;
import androidx.constraintlayout.widget.R;
import androidx.constraintlayout.widget.StateSet;
import androidx.core.view.NestedScrollingParent3;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class MotionLayout extends ConstraintLayout implements NestedScrollingParent3 {
    private static final boolean DEBUG = false;
    public static final int DEBUG_SHOW_NONE = 0;
    public static final int DEBUG_SHOW_PATH = 2;
    public static final int DEBUG_SHOW_PROGRESS = 1;
    private static final float EPSILON = 1.0E-5f;
    public static boolean IS_IN_EDIT_MODE = false;
    static final int MAX_KEY_FRAMES = 50;
    static final String TAG = "MotionLayout";
    public static final int TOUCH_UP_COMPLETE = 0;
    public static final int TOUCH_UP_COMPLETE_TO_END = 2;
    public static final int TOUCH_UP_COMPLETE_TO_START = 1;
    public static final int TOUCH_UP_DECELERATE = 4;
    public static final int TOUCH_UP_DECELERATE_AND_COMPLETE = 5;
    public static final int TOUCH_UP_NEVER_TO_END = 7;
    public static final int TOUCH_UP_NEVER_TO_START = 6;
    public static final int TOUCH_UP_STOP = 3;
    public static final int VELOCITY_LAYOUT = 1;
    public static final int VELOCITY_POST_LAYOUT = 0;
    public static final int VELOCITY_STATIC_LAYOUT = 3;
    public static final int VELOCITY_STATIC_POST_LAYOUT = 2;
    private long mAnimationStartTime;
    private int mBeginState;
    private RectF mBoundsCheck;
    int mCurrentState;
    int mDebugPath;
    private DecelerateInterpolator mDecelerateLogic;
    private ArrayList<MotionHelper> mDecoratorsHelpers;
    private boolean mDelayedApply;
    private DesignTool mDesignTool;
    DevModeDraw mDevModeDraw;
    private int mEndState;
    int mEndWrapHeight;
    int mEndWrapWidth;
    boolean mFirstDown;
    HashMap<View, MotionController> mFrameArrayList;
    private int mFrames;
    int mHeightMeasureMode;
    private boolean mInLayout;
    private boolean mInRotation;
    boolean mInTransition;
    boolean mIndirectTransition;
    private boolean mInteractionEnabled;
    Interpolator mInterpolator;
    private Matrix mInverseMatrix;
    boolean mIsAnimating;
    private boolean mKeepAnimating;
    private KeyCache mKeyCache;
    private long mLastDrawTime;
    private float mLastFps;
    private int mLastHeightMeasureSpec;
    int mLastLayoutHeight;
    int mLastLayoutWidth;
    private float mLastPos;
    float mLastVelocity;
    private int mLastWidthMeasureSpec;
    private float mLastY;
    private float mListenerPosition;
    private int mListenerState;
    protected boolean mMeasureDuringTransition;
    Model mModel;
    private boolean mNeedsFireTransitionCompleted;
    int mOldHeight;
    int mOldWidth;
    private Runnable mOnComplete;
    private ArrayList<MotionHelper> mOnHideHelpers;
    private ArrayList<MotionHelper> mOnShowHelpers;
    float mPostInterpolationPosition;
    HashMap<View, ViewState> mPreRotate;
    private int mPreRotateHeight;
    private int mPreRotateWidth;
    private int mPreviouseRotation;
    Interpolator mProgressInterpolator;
    private View mRegionView;
    int mRotatMode;
    MotionScene mScene;
    private int[] mScheduledTransitionTo;
    int mScheduledTransitions;
    float mScrollTargetDT;
    float mScrollTargetDX;
    float mScrollTargetDY;
    long mScrollTargetTime;
    int mStartWrapHeight;
    int mStartWrapWidth;
    private StateCache mStateCache;
    private StopLogic mStopLogic;
    Rect mTempRect;
    private boolean mTemporalInterpolator;
    ArrayList<Integer> mTransitionCompleted;
    private float mTransitionDuration;
    float mTransitionGoalPosition;
    private boolean mTransitionInstantly;
    float mTransitionLastPosition;
    private long mTransitionLastTime;
    private TransitionListener mTransitionListener;
    private CopyOnWriteArrayList<TransitionListener> mTransitionListeners;
    float mTransitionPosition;
    TransitionState mTransitionState;
    boolean mUndergoingMotion;
    int mWidthMeasureMode;

    /* renamed from: androidx.constraintlayout.motion.widget.MotionLayout$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ MotionLayout f2233c;

        @Override // java.lang.Runnable
        public void run() {
            this.f2233c.mStateCache.a();
        }
    }

    /* renamed from: androidx.constraintlayout.motion.widget.MotionLayout$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ MotionLayout f2234c;

        @Override // java.lang.Runnable
        public void run() {
            this.f2234c.mInRotation = false;
        }
    }

    class DecelerateInterpolator extends MotionInterpolator {

        /* renamed from: a, reason: collision with root package name */
        float f2238a = 0.0f;

        /* renamed from: b, reason: collision with root package name */
        float f2239b = 0.0f;

        /* renamed from: c, reason: collision with root package name */
        float f2240c;

        DecelerateInterpolator() {
        }

        @Override // androidx.constraintlayout.motion.widget.MotionInterpolator
        public float a() {
            return MotionLayout.this.mLastVelocity;
        }

        public void b(float f2, float f3, float f4) {
            this.f2238a = f2;
            this.f2239b = f3;
            this.f2240c = f4;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3;
            float f4;
            float f5 = this.f2238a;
            if (f5 > 0.0f) {
                float f6 = this.f2240c;
                if (f5 / f6 < f2) {
                    f2 = f5 / f6;
                }
                MotionLayout.this.mLastVelocity = f5 - (f6 * f2);
                f3 = (f5 * f2) - (((f6 * f2) * f2) / 2.0f);
                f4 = this.f2239b;
            } else {
                float f7 = this.f2240c;
                if ((-f5) / f7 < f2) {
                    f2 = (-f5) / f7;
                }
                MotionLayout.this.mLastVelocity = (f7 * f2) + f5;
                f3 = (f5 * f2) + (((f7 * f2) * f2) / 2.0f);
                f4 = this.f2239b;
            }
            return f3 + f4;
        }
    }

    private class DevModeDraw {

        /* renamed from: a, reason: collision with root package name */
        float[] f2242a;

        /* renamed from: b, reason: collision with root package name */
        int[] f2243b;

        /* renamed from: c, reason: collision with root package name */
        float[] f2244c;

        /* renamed from: d, reason: collision with root package name */
        Path f2245d;

        /* renamed from: e, reason: collision with root package name */
        Paint f2246e;

        /* renamed from: f, reason: collision with root package name */
        Paint f2247f;

        /* renamed from: g, reason: collision with root package name */
        Paint f2248g;

        /* renamed from: h, reason: collision with root package name */
        Paint f2249h;

        /* renamed from: i, reason: collision with root package name */
        Paint f2250i;

        /* renamed from: j, reason: collision with root package name */
        private float[] f2251j;

        /* renamed from: p, reason: collision with root package name */
        DashPathEffect f2257p;

        /* renamed from: q, reason: collision with root package name */
        int f2258q;
        int t;

        /* renamed from: k, reason: collision with root package name */
        final int f2252k = -21965;

        /* renamed from: l, reason: collision with root package name */
        final int f2253l = -2067046;

        /* renamed from: m, reason: collision with root package name */
        final int f2254m = -13391360;

        /* renamed from: n, reason: collision with root package name */
        final int f2255n = 1996488704;

        /* renamed from: o, reason: collision with root package name */
        final int f2256o = 10;

        /* renamed from: r, reason: collision with root package name */
        Rect f2259r = new Rect();

        /* renamed from: s, reason: collision with root package name */
        boolean f2260s = false;

        DevModeDraw() {
            this.t = 1;
            Paint paint = new Paint();
            this.f2246e = paint;
            paint.setAntiAlias(true);
            this.f2246e.setColor(-21965);
            this.f2246e.setStrokeWidth(2.0f);
            Paint paint2 = this.f2246e;
            Paint.Style style = Paint.Style.STROKE;
            paint2.setStyle(style);
            Paint paint3 = new Paint();
            this.f2247f = paint3;
            paint3.setAntiAlias(true);
            this.f2247f.setColor(-2067046);
            this.f2247f.setStrokeWidth(2.0f);
            this.f2247f.setStyle(style);
            Paint paint4 = new Paint();
            this.f2248g = paint4;
            paint4.setAntiAlias(true);
            this.f2248g.setColor(-13391360);
            this.f2248g.setStrokeWidth(2.0f);
            this.f2248g.setStyle(style);
            Paint paint5 = new Paint();
            this.f2249h = paint5;
            paint5.setAntiAlias(true);
            this.f2249h.setColor(-13391360);
            this.f2249h.setTextSize(MotionLayout.this.getContext().getResources().getDisplayMetrics().density * 12.0f);
            this.f2251j = new float[8];
            Paint paint6 = new Paint();
            this.f2250i = paint6;
            paint6.setAntiAlias(true);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{4.0f, 8.0f}, 0.0f);
            this.f2257p = dashPathEffect;
            this.f2248g.setPathEffect(dashPathEffect);
            this.f2244c = new float[100];
            this.f2243b = new int[MotionLayout.MAX_KEY_FRAMES];
            if (this.f2260s) {
                this.f2246e.setStrokeWidth(8.0f);
                this.f2250i.setStrokeWidth(8.0f);
                this.f2247f.setStrokeWidth(8.0f);
                this.t = 4;
            }
        }

        private void c(Canvas canvas) {
            canvas.drawLines(this.f2242a, this.f2246e);
        }

        private void d(Canvas canvas) {
            boolean z = false;
            boolean z2 = false;
            for (int i2 = 0; i2 < this.f2258q; i2++) {
                int i3 = this.f2243b[i2];
                if (i3 == 1) {
                    z = true;
                }
                if (i3 == 0) {
                    z2 = true;
                }
            }
            if (z) {
                g(canvas);
            }
            if (z2) {
                e(canvas);
            }
        }

        private void e(Canvas canvas) {
            float[] fArr = this.f2242a;
            float f2 = fArr[0];
            float f3 = fArr[1];
            float f4 = fArr[fArr.length - 2];
            float f5 = fArr[fArr.length - 1];
            canvas.drawLine(Math.min(f2, f4), Math.max(f3, f5), Math.max(f2, f4), Math.max(f3, f5), this.f2248g);
            canvas.drawLine(Math.min(f2, f4), Math.min(f3, f5), Math.min(f2, f4), Math.max(f3, f5), this.f2248g);
        }

        private void f(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f2242a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float min = Math.min(f4, f6);
            float max = Math.max(f5, f7);
            float min2 = f2 - Math.min(f4, f6);
            float max2 = Math.max(f5, f7) - f3;
            String str = "" + (((int) (((min2 * 100.0f) / Math.abs(f6 - f4)) + 0.5d)) / 100.0f);
            l(str, this.f2249h);
            canvas.drawText(str, ((min2 / 2.0f) - (this.f2259r.width() / 2)) + min, f3 - 20.0f, this.f2249h);
            canvas.drawLine(f2, f3, Math.min(f4, f6), f3, this.f2248g);
            String str2 = "" + (((int) (((max2 * 100.0f) / Math.abs(f7 - f5)) + 0.5d)) / 100.0f);
            l(str2, this.f2249h);
            canvas.drawText(str2, f2 + 5.0f, max - ((max2 / 2.0f) - (this.f2259r.height() / 2)), this.f2249h);
            canvas.drawLine(f2, f3, f2, Math.max(f5, f7), this.f2248g);
        }

        private void g(Canvas canvas) {
            float[] fArr = this.f2242a;
            canvas.drawLine(fArr[0], fArr[1], fArr[fArr.length - 2], fArr[fArr.length - 1], this.f2248g);
        }

        private void h(Canvas canvas, float f2, float f3) {
            float[] fArr = this.f2242a;
            float f4 = fArr[0];
            float f5 = fArr[1];
            float f6 = fArr[fArr.length - 2];
            float f7 = fArr[fArr.length - 1];
            float hypot = (float) Math.hypot(f4 - f6, f5 - f7);
            float f8 = f6 - f4;
            float f9 = f7 - f5;
            float f10 = (((f2 - f4) * f8) + ((f3 - f5) * f9)) / (hypot * hypot);
            float f11 = f4 + (f8 * f10);
            float f12 = f5 + (f10 * f9);
            Path path = new Path();
            path.moveTo(f2, f3);
            path.lineTo(f11, f12);
            float hypot2 = (float) Math.hypot(f11 - f2, f12 - f3);
            String str = "" + (((int) ((hypot2 * 100.0f) / hypot)) / 100.0f);
            l(str, this.f2249h);
            canvas.drawTextOnPath(str, path, (hypot2 / 2.0f) - (this.f2259r.width() / 2), -20.0f, this.f2249h);
            canvas.drawLine(f2, f3, f11, f12, this.f2248g);
        }

        private void i(Canvas canvas, float f2, float f3, int i2, int i3) {
            String str = "" + (((int) ((((f2 - (i2 / 2)) * 100.0f) / (MotionLayout.this.getWidth() - i2)) + 0.5d)) / 100.0f);
            l(str, this.f2249h);
            canvas.drawText(str, ((f2 / 2.0f) - (this.f2259r.width() / 2)) + 0.0f, f3 - 20.0f, this.f2249h);
            canvas.drawLine(f2, f3, Math.min(0.0f, 1.0f), f3, this.f2248g);
            String str2 = "" + (((int) ((((f3 - (i3 / 2)) * 100.0f) / (MotionLayout.this.getHeight() - i3)) + 0.5d)) / 100.0f);
            l(str2, this.f2249h);
            canvas.drawText(str2, f2 + 5.0f, 0.0f - ((f3 / 2.0f) - (this.f2259r.height() / 2)), this.f2249h);
            canvas.drawLine(f2, f3, f2, Math.max(0.0f, 1.0f), this.f2248g);
        }

        private void j(Canvas canvas, MotionController motionController) {
            this.f2245d.reset();
            for (int i2 = 0; i2 <= MotionLayout.MAX_KEY_FRAMES; i2++) {
                motionController.e(i2 / MotionLayout.MAX_KEY_FRAMES, this.f2251j, 0);
                Path path = this.f2245d;
                float[] fArr = this.f2251j;
                path.moveTo(fArr[0], fArr[1]);
                Path path2 = this.f2245d;
                float[] fArr2 = this.f2251j;
                path2.lineTo(fArr2[2], fArr2[3]);
                Path path3 = this.f2245d;
                float[] fArr3 = this.f2251j;
                path3.lineTo(fArr3[4], fArr3[5]);
                Path path4 = this.f2245d;
                float[] fArr4 = this.f2251j;
                path4.lineTo(fArr4[6], fArr4[7]);
                this.f2245d.close();
            }
            this.f2246e.setColor(1140850688);
            canvas.translate(2.0f, 2.0f);
            canvas.drawPath(this.f2245d, this.f2246e);
            canvas.translate(-2.0f, -2.0f);
            this.f2246e.setColor(-65536);
            canvas.drawPath(this.f2245d, this.f2246e);
        }

        private void k(Canvas canvas, int i2, int i3, MotionController motionController) {
            int i4;
            int i5;
            float f2;
            float f3;
            View view = motionController.f2214b;
            if (view != null) {
                i4 = view.getWidth();
                i5 = motionController.f2214b.getHeight();
            } else {
                i4 = 0;
                i5 = 0;
            }
            for (int i6 = 1; i6 < i3 - 1; i6++) {
                if (i2 != 4 || this.f2243b[i6 - 1] != 0) {
                    float[] fArr = this.f2244c;
                    int i7 = i6 * 2;
                    float f4 = fArr[i7];
                    float f5 = fArr[i7 + 1];
                    this.f2245d.reset();
                    this.f2245d.moveTo(f4, f5 + 10.0f);
                    this.f2245d.lineTo(f4 + 10.0f, f5);
                    this.f2245d.lineTo(f4, f5 - 10.0f);
                    this.f2245d.lineTo(f4 - 10.0f, f5);
                    this.f2245d.close();
                    int i8 = i6 - 1;
                    motionController.q(i8);
                    if (i2 == 4) {
                        int i9 = this.f2243b[i8];
                        if (i9 == 1) {
                            h(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else if (i9 == 0) {
                            f(canvas, f4 - 0.0f, f5 - 0.0f);
                        } else if (i9 == 2) {
                            f2 = f5;
                            f3 = f4;
                            i(canvas, f4 - 0.0f, f5 - 0.0f, i4, i5);
                            canvas.drawPath(this.f2245d, this.f2250i);
                        }
                        f2 = f5;
                        f3 = f4;
                        canvas.drawPath(this.f2245d, this.f2250i);
                    } else {
                        f2 = f5;
                        f3 = f4;
                    }
                    if (i2 == 2) {
                        h(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i2 == 3) {
                        f(canvas, f3 - 0.0f, f2 - 0.0f);
                    }
                    if (i2 == 6) {
                        i(canvas, f3 - 0.0f, f2 - 0.0f, i4, i5);
                    }
                    canvas.drawPath(this.f2245d, this.f2250i);
                }
            }
            float[] fArr2 = this.f2242a;
            if (fArr2.length > 1) {
                canvas.drawCircle(fArr2[0], fArr2[1], 8.0f, this.f2247f);
                float[] fArr3 = this.f2242a;
                canvas.drawCircle(fArr3[fArr3.length - 2], fArr3[fArr3.length - 1], 8.0f, this.f2247f);
            }
        }

        public void a(Canvas canvas, HashMap hashMap, int i2, int i3) {
            if (hashMap == null || hashMap.size() == 0) {
                return;
            }
            canvas.save();
            if (!MotionLayout.this.isInEditMode() && (i3 & 1) == 2) {
                String str = MotionLayout.this.getContext().getResources().getResourceName(MotionLayout.this.mEndState) + ":" + MotionLayout.this.getProgress();
                canvas.drawText(str, 10.0f, MotionLayout.this.getHeight() - 30, this.f2249h);
                canvas.drawText(str, 11.0f, MotionLayout.this.getHeight() - 29, this.f2246e);
            }
            for (MotionController motionController : hashMap.values()) {
                int m2 = motionController.m();
                if (i3 > 0 && m2 == 0) {
                    m2 = 1;
                }
                if (m2 != 0) {
                    this.f2258q = motionController.c(this.f2244c, this.f2243b);
                    if (m2 >= 1) {
                        int i4 = i2 / 16;
                        float[] fArr = this.f2242a;
                        if (fArr == null || fArr.length != i4 * 2) {
                            this.f2242a = new float[i4 * 2];
                            this.f2245d = new Path();
                        }
                        int i5 = this.t;
                        canvas.translate(i5, i5);
                        this.f2246e.setColor(1996488704);
                        this.f2250i.setColor(1996488704);
                        this.f2247f.setColor(1996488704);
                        this.f2248g.setColor(1996488704);
                        motionController.d(this.f2242a, i4);
                        b(canvas, m2, this.f2258q, motionController);
                        this.f2246e.setColor(-21965);
                        this.f2247f.setColor(-2067046);
                        this.f2250i.setColor(-2067046);
                        this.f2248g.setColor(-13391360);
                        int i6 = this.t;
                        canvas.translate(-i6, -i6);
                        b(canvas, m2, this.f2258q, motionController);
                        if (m2 == 5) {
                            j(canvas, motionController);
                        }
                    }
                }
            }
            canvas.restore();
        }

        public void b(Canvas canvas, int i2, int i3, MotionController motionController) {
            if (i2 == 4) {
                d(canvas);
            }
            if (i2 == 2) {
                g(canvas);
            }
            if (i2 == 3) {
                e(canvas);
            }
            c(canvas);
            k(canvas, i2, i3, motionController);
        }

        void l(String str, Paint paint) {
            paint.getTextBounds(str, 0, str.length(), this.f2259r);
        }
    }

    class Model {

        /* renamed from: a, reason: collision with root package name */
        ConstraintWidgetContainer f2261a = new ConstraintWidgetContainer();

        /* renamed from: b, reason: collision with root package name */
        ConstraintWidgetContainer f2262b = new ConstraintWidgetContainer();

        /* renamed from: c, reason: collision with root package name */
        ConstraintSet f2263c = null;

        /* renamed from: d, reason: collision with root package name */
        ConstraintSet f2264d = null;

        /* renamed from: e, reason: collision with root package name */
        int f2265e;

        /* renamed from: f, reason: collision with root package name */
        int f2266f;

        Model() {
        }

        private void b(int i2, int i3) {
            int optimizationLevel = MotionLayout.this.getOptimizationLevel();
            MotionLayout motionLayout = MotionLayout.this;
            if (motionLayout.mCurrentState == motionLayout.getStartState()) {
                MotionLayout motionLayout2 = MotionLayout.this;
                ConstraintWidgetContainer constraintWidgetContainer = this.f2262b;
                ConstraintSet constraintSet = this.f2264d;
                motionLayout2.x(constraintWidgetContainer, optimizationLevel, (constraintSet == null || constraintSet.f2483e == 0) ? i2 : i3, (constraintSet == null || constraintSet.f2483e == 0) ? i3 : i2);
                ConstraintSet constraintSet2 = this.f2263c;
                if (constraintSet2 != null) {
                    MotionLayout motionLayout3 = MotionLayout.this;
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f2261a;
                    int i4 = constraintSet2.f2483e;
                    int i5 = i4 == 0 ? i2 : i3;
                    if (i4 == 0) {
                        i2 = i3;
                    }
                    motionLayout3.x(constraintWidgetContainer2, optimizationLevel, i5, i2);
                    return;
                }
                return;
            }
            ConstraintSet constraintSet3 = this.f2263c;
            if (constraintSet3 != null) {
                MotionLayout motionLayout4 = MotionLayout.this;
                ConstraintWidgetContainer constraintWidgetContainer3 = this.f2261a;
                int i6 = constraintSet3.f2483e;
                motionLayout4.x(constraintWidgetContainer3, optimizationLevel, i6 == 0 ? i2 : i3, i6 == 0 ? i3 : i2);
            }
            MotionLayout motionLayout5 = MotionLayout.this;
            ConstraintWidgetContainer constraintWidgetContainer4 = this.f2262b;
            ConstraintSet constraintSet4 = this.f2264d;
            int i7 = (constraintSet4 == null || constraintSet4.f2483e == 0) ? i2 : i3;
            if (constraintSet4 == null || constraintSet4.f2483e == 0) {
                i2 = i3;
            }
            motionLayout5.x(constraintWidgetContainer4, optimizationLevel, i7, i2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void j(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet) {
            SparseArray sparseArray = new SparseArray();
            Constraints.LayoutParams layoutParams = new Constraints.LayoutParams(-2, -2);
            sparseArray.clear();
            sparseArray.put(0, constraintWidgetContainer);
            sparseArray.put(MotionLayout.this.getId(), constraintWidgetContainer);
            if (constraintSet != null && constraintSet.f2483e != 0) {
                MotionLayout motionLayout = MotionLayout.this;
                motionLayout.x(this.f2262b, motionLayout.getOptimizationLevel(), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getHeight(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME), View.MeasureSpec.makeMeasureSpec(MotionLayout.this.getWidth(), WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME));
            }
            Iterator it = constraintWidgetContainer.x1().iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                constraintWidget.E0(true);
                sparseArray.put(((View) constraintWidget.u()).getId(), constraintWidget);
            }
            Iterator it2 = constraintWidgetContainer.x1().iterator();
            while (it2.hasNext()) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) it2.next();
                View view = (View) constraintWidget2.u();
                constraintSet.l(view.getId(), layoutParams);
                constraintWidget2.p1(constraintSet.B(view.getId()));
                constraintWidget2.Q0(constraintSet.w(view.getId()));
                if (view instanceof ConstraintHelper) {
                    constraintSet.j((ConstraintHelper) view, constraintWidget2, layoutParams, sparseArray);
                    if (view instanceof Barrier) {
                        ((Barrier) view).w();
                    }
                }
                layoutParams.resolveLayoutDirection(MotionLayout.this.getLayoutDirection());
                MotionLayout.this.k(false, view, constraintWidget2, layoutParams, sparseArray);
                if (constraintSet.A(view.getId()) == 1) {
                    constraintWidget2.o1(view.getVisibility());
                } else {
                    constraintWidget2.o1(constraintSet.z(view.getId()));
                }
            }
            Iterator it3 = constraintWidgetContainer.x1().iterator();
            while (it3.hasNext()) {
                ConstraintWidget constraintWidget3 = (ConstraintWidget) it3.next();
                if (constraintWidget3 instanceof VirtualLayout) {
                    ConstraintHelper constraintHelper = (ConstraintHelper) constraintWidget3.u();
                    Helper helper = (Helper) constraintWidget3;
                    constraintHelper.u(constraintWidgetContainer, helper, sparseArray);
                    ((VirtualLayout) helper).z1();
                }
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00e8  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x013c A[SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a() {
            /*
                Method dump skipped, instructions count: 359
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.Model.a():void");
        }

        void c(ConstraintWidgetContainer constraintWidgetContainer, ConstraintWidgetContainer constraintWidgetContainer2) {
            ArrayList x1 = constraintWidgetContainer.x1();
            HashMap hashMap = new HashMap();
            hashMap.put(constraintWidgetContainer, constraintWidgetContainer2);
            constraintWidgetContainer2.x1().clear();
            constraintWidgetContainer2.n(constraintWidgetContainer, hashMap);
            Iterator it = x1.iterator();
            while (it.hasNext()) {
                ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
                ConstraintWidget barrier = constraintWidget instanceof androidx.constraintlayout.core.widgets.Barrier ? new androidx.constraintlayout.core.widgets.Barrier() : constraintWidget instanceof Guideline ? new Guideline() : constraintWidget instanceof Flow ? new Flow() : constraintWidget instanceof Placeholder ? new Placeholder() : constraintWidget instanceof Helper ? new HelperWidget() : new ConstraintWidget();
                constraintWidgetContainer2.a(barrier);
                hashMap.put(constraintWidget, barrier);
            }
            Iterator it2 = x1.iterator();
            while (it2.hasNext()) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) it2.next();
                ((ConstraintWidget) hashMap.get(constraintWidget2)).n(constraintWidget2, hashMap);
            }
        }

        ConstraintWidget d(ConstraintWidgetContainer constraintWidgetContainer, View view) {
            if (constraintWidgetContainer.u() == view) {
                return constraintWidgetContainer;
            }
            ArrayList x1 = constraintWidgetContainer.x1();
            int size = x1.size();
            for (int i2 = 0; i2 < size; i2++) {
                ConstraintWidget constraintWidget = (ConstraintWidget) x1.get(i2);
                if (constraintWidget.u() == view) {
                    return constraintWidget;
                }
            }
            return null;
        }

        void e(ConstraintWidgetContainer constraintWidgetContainer, ConstraintSet constraintSet, ConstraintSet constraintSet2) {
            this.f2263c = constraintSet;
            this.f2264d = constraintSet2;
            this.f2261a = new ConstraintWidgetContainer();
            this.f2262b = new ConstraintWidgetContainer();
            this.f2261a.c2(((ConstraintLayout) MotionLayout.this).mLayoutWidget.P1());
            this.f2262b.c2(((ConstraintLayout) MotionLayout.this).mLayoutWidget.P1());
            this.f2261a.A1();
            this.f2262b.A1();
            c(((ConstraintLayout) MotionLayout.this).mLayoutWidget, this.f2261a);
            c(((ConstraintLayout) MotionLayout.this).mLayoutWidget, this.f2262b);
            if (MotionLayout.this.mTransitionLastPosition > 0.5d) {
                if (constraintSet != null) {
                    j(this.f2261a, constraintSet);
                }
                j(this.f2262b, constraintSet2);
            } else {
                j(this.f2262b, constraintSet2);
                if (constraintSet != null) {
                    j(this.f2261a, constraintSet);
                }
            }
            this.f2261a.f2(MotionLayout.this.t());
            this.f2261a.h2();
            this.f2262b.f2(MotionLayout.this.t());
            this.f2262b.h2();
            ViewGroup.LayoutParams layoutParams = MotionLayout.this.getLayoutParams();
            if (layoutParams != null) {
                if (layoutParams.width == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.f2261a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer2.U0(dimensionBehaviour);
                    this.f2262b.U0(dimensionBehaviour);
                }
                if (layoutParams.height == -2) {
                    ConstraintWidgetContainer constraintWidgetContainer3 = this.f2261a;
                    ConstraintWidget.DimensionBehaviour dimensionBehaviour2 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    constraintWidgetContainer3.l1(dimensionBehaviour2);
                    this.f2262b.l1(dimensionBehaviour2);
                }
            }
        }

        public boolean f(int i2, int i3) {
            return (i2 == this.f2265e && i3 == this.f2266f) ? false : true;
        }

        public void g(int i2, int i3) {
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            MotionLayout motionLayout = MotionLayout.this;
            motionLayout.mWidthMeasureMode = mode;
            motionLayout.mHeightMeasureMode = mode2;
            b(i2, i3);
            if (!(MotionLayout.this.getParent() instanceof MotionLayout) || mode != 1073741824 || mode2 != 1073741824) {
                b(i2, i3);
                MotionLayout.this.mStartWrapWidth = this.f2261a.Y();
                MotionLayout.this.mStartWrapHeight = this.f2261a.z();
                MotionLayout.this.mEndWrapWidth = this.f2262b.Y();
                MotionLayout.this.mEndWrapHeight = this.f2262b.z();
                MotionLayout motionLayout2 = MotionLayout.this;
                motionLayout2.mMeasureDuringTransition = (motionLayout2.mStartWrapWidth == motionLayout2.mEndWrapWidth && motionLayout2.mStartWrapHeight == motionLayout2.mEndWrapHeight) ? false : true;
            }
            MotionLayout motionLayout3 = MotionLayout.this;
            int i4 = motionLayout3.mStartWrapWidth;
            int i5 = motionLayout3.mStartWrapHeight;
            int i6 = motionLayout3.mWidthMeasureMode;
            if (i6 == Integer.MIN_VALUE || i6 == 0) {
                i4 = (int) (i4 + (motionLayout3.mPostInterpolationPosition * (motionLayout3.mEndWrapWidth - i4)));
            }
            int i7 = i4;
            int i8 = motionLayout3.mHeightMeasureMode;
            if (i8 == Integer.MIN_VALUE || i8 == 0) {
                i5 = (int) (i5 + (motionLayout3.mPostInterpolationPosition * (motionLayout3.mEndWrapHeight - i5)));
            }
            MotionLayout.this.w(i2, i3, i7, i5, this.f2261a.X1() || this.f2262b.X1(), this.f2261a.V1() || this.f2262b.V1());
        }

        public void h() {
            g(MotionLayout.this.mLastWidthMeasureSpec, MotionLayout.this.mLastHeightMeasureSpec);
            MotionLayout.this.E0();
        }

        public void i(int i2, int i3) {
            this.f2265e = i2;
            this.f2266f = i3;
        }
    }

    protected interface MotionTracker {
        void a(MotionEvent motionEvent);

        float b();

        float c();

        void d();

        void e(int i2);
    }

    private static class MyTracker implements MotionTracker {

        /* renamed from: b, reason: collision with root package name */
        private static MyTracker f2268b = new MyTracker();

        /* renamed from: a, reason: collision with root package name */
        VelocityTracker f2269a;

        private MyTracker() {
        }

        public static MyTracker f() {
            f2268b.f2269a = VelocityTracker.obtain();
            return f2268b;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void a(MotionEvent motionEvent) {
            VelocityTracker velocityTracker = this.f2269a;
            if (velocityTracker != null) {
                velocityTracker.addMovement(motionEvent);
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float b() {
            VelocityTracker velocityTracker = this.f2269a;
            if (velocityTracker != null) {
                return velocityTracker.getYVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public float c() {
            VelocityTracker velocityTracker = this.f2269a;
            if (velocityTracker != null) {
                return velocityTracker.getXVelocity();
            }
            return 0.0f;
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void d() {
            VelocityTracker velocityTracker = this.f2269a;
            if (velocityTracker != null) {
                velocityTracker.recycle();
                this.f2269a = null;
            }
        }

        @Override // androidx.constraintlayout.motion.widget.MotionLayout.MotionTracker
        public void e(int i2) {
            VelocityTracker velocityTracker = this.f2269a;
            if (velocityTracker != null) {
                velocityTracker.computeCurrentVelocity(i2);
            }
        }
    }

    class StateCache {

        /* renamed from: a, reason: collision with root package name */
        float f2270a = Float.NaN;

        /* renamed from: b, reason: collision with root package name */
        float f2271b = Float.NaN;

        /* renamed from: c, reason: collision with root package name */
        int f2272c = -1;

        /* renamed from: d, reason: collision with root package name */
        int f2273d = -1;

        /* renamed from: e, reason: collision with root package name */
        final String f2274e = "motion.progress";

        /* renamed from: f, reason: collision with root package name */
        final String f2275f = "motion.velocity";

        /* renamed from: g, reason: collision with root package name */
        final String f2276g = "motion.StartState";

        /* renamed from: h, reason: collision with root package name */
        final String f2277h = "motion.EndState";

        StateCache() {
        }

        void a() {
            int i2 = this.f2272c;
            if (i2 != -1 || this.f2273d != -1) {
                if (i2 == -1) {
                    MotionLayout.this.K0(this.f2273d);
                } else {
                    int i3 = this.f2273d;
                    if (i3 == -1) {
                        MotionLayout.this.C0(i2, -1, -1);
                    } else {
                        MotionLayout.this.D0(i2, i3);
                    }
                }
                MotionLayout.this.setState(TransitionState.SETUP);
            }
            if (Float.isNaN(this.f2271b)) {
                if (Float.isNaN(this.f2270a)) {
                    return;
                }
                MotionLayout.this.setProgress(this.f2270a);
            } else {
                MotionLayout.this.B0(this.f2270a, this.f2271b);
                this.f2270a = Float.NaN;
                this.f2271b = Float.NaN;
                this.f2272c = -1;
                this.f2273d = -1;
            }
        }

        public Bundle b() {
            Bundle bundle = new Bundle();
            bundle.putFloat("motion.progress", this.f2270a);
            bundle.putFloat("motion.velocity", this.f2271b);
            bundle.putInt("motion.StartState", this.f2272c);
            bundle.putInt("motion.EndState", this.f2273d);
            return bundle;
        }

        public void c() {
            this.f2273d = MotionLayout.this.mEndState;
            this.f2272c = MotionLayout.this.mBeginState;
            this.f2271b = MotionLayout.this.getVelocity();
            this.f2270a = MotionLayout.this.getProgress();
        }

        public void d(int i2) {
            this.f2273d = i2;
        }

        public void e(float f2) {
            this.f2270a = f2;
        }

        public void f(int i2) {
            this.f2272c = i2;
        }

        public void g(Bundle bundle) {
            this.f2270a = bundle.getFloat("motion.progress");
            this.f2271b = bundle.getFloat("motion.velocity");
            this.f2272c = bundle.getInt("motion.StartState");
            this.f2273d = bundle.getInt("motion.EndState");
        }

        public void h(float f2) {
            this.f2271b = f2;
        }
    }

    public interface TransitionListener {
        void a(MotionLayout motionLayout, int i2, int i3, float f2);

        void b(MotionLayout motionLayout, int i2);

        void c(MotionLayout motionLayout, int i2, int i3);

        void d(MotionLayout motionLayout, int i2, boolean z, float f2);
    }

    enum TransitionState {
        UNDEFINED,
        SETUP,
        MOVING,
        FINISHED
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mProgressInterpolator = null;
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.mFirstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.mScheduledTransitions = 0;
        this.mInRotation = false;
        this.mRotatMode = 0;
        this.mPreRotate = new HashMap<>();
        this.mTempRect = new Rect();
        this.mDelayedApply = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.mTransitionCompleted = new ArrayList<>();
        v0(attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E0() {
        int childCount = getChildCount();
        this.mModel.a();
        this.mInTransition = true;
        SparseArray sparseArray = new SparseArray();
        int i2 = 0;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            sparseArray.put(childAt.getId(), this.mFrameArrayList.get(childAt));
        }
        int width = getWidth();
        int height = getHeight();
        int j2 = this.mScene.j();
        if (j2 != -1) {
            for (int i4 = 0; i4 < childCount; i4++) {
                MotionController motionController = this.mFrameArrayList.get(getChildAt(i4));
                if (motionController != null) {
                    motionController.D(j2);
                }
            }
        }
        SparseBooleanArray sparseBooleanArray = new SparseBooleanArray();
        int[] iArr = new int[this.mFrameArrayList.size()];
        int i5 = 0;
        for (int i6 = 0; i6 < childCount; i6++) {
            MotionController motionController2 = this.mFrameArrayList.get(getChildAt(i6));
            if (motionController2.h() != -1) {
                sparseBooleanArray.put(motionController2.h(), true);
                iArr[i5] = motionController2.h();
                i5++;
            }
        }
        if (this.mDecoratorsHelpers != null) {
            for (int i7 = 0; i7 < i5; i7++) {
                MotionController motionController3 = this.mFrameArrayList.get(findViewById(iArr[i7]));
                if (motionController3 != null) {
                    this.mScene.t(motionController3);
                }
            }
            Iterator<MotionHelper> it = this.mDecoratorsHelpers.iterator();
            while (it.hasNext()) {
                it.next().D(this, this.mFrameArrayList);
            }
            for (int i8 = 0; i8 < i5; i8++) {
                MotionController motionController4 = this.mFrameArrayList.get(findViewById(iArr[i8]));
                if (motionController4 != null) {
                    motionController4.I(width, height, this.mTransitionDuration, getNanoTime());
                }
            }
        } else {
            for (int i9 = 0; i9 < i5; i9++) {
                MotionController motionController5 = this.mFrameArrayList.get(findViewById(iArr[i9]));
                if (motionController5 != null) {
                    this.mScene.t(motionController5);
                    motionController5.I(width, height, this.mTransitionDuration, getNanoTime());
                }
            }
        }
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt2 = getChildAt(i10);
            MotionController motionController6 = this.mFrameArrayList.get(childAt2);
            if (!sparseBooleanArray.get(childAt2.getId()) && motionController6 != null) {
                this.mScene.t(motionController6);
                motionController6.I(width, height, this.mTransitionDuration, getNanoTime());
            }
        }
        float E = this.mScene.E();
        if (E != 0.0f) {
            boolean z = ((double) E) < 0.0d;
            float abs = Math.abs(E);
            float f2 = -3.4028235E38f;
            float f3 = Float.MAX_VALUE;
            float f4 = -3.4028235E38f;
            float f5 = Float.MAX_VALUE;
            for (int i11 = 0; i11 < childCount; i11++) {
                MotionController motionController7 = this.mFrameArrayList.get(getChildAt(i11));
                if (!Float.isNaN(motionController7.f2225m)) {
                    for (int i12 = 0; i12 < childCount; i12++) {
                        MotionController motionController8 = this.mFrameArrayList.get(getChildAt(i12));
                        if (!Float.isNaN(motionController8.f2225m)) {
                            f3 = Math.min(f3, motionController8.f2225m);
                            f2 = Math.max(f2, motionController8.f2225m);
                        }
                    }
                    while (i2 < childCount) {
                        MotionController motionController9 = this.mFrameArrayList.get(getChildAt(i2));
                        if (!Float.isNaN(motionController9.f2225m)) {
                            motionController9.f2227o = 1.0f / (1.0f - abs);
                            if (z) {
                                motionController9.f2226n = abs - (((f2 - motionController9.f2225m) / (f2 - f3)) * abs);
                            } else {
                                motionController9.f2226n = abs - (((motionController9.f2225m - f3) * abs) / (f2 - f3));
                            }
                        }
                        i2++;
                    }
                    return;
                }
                float n2 = motionController7.n();
                float o2 = motionController7.o();
                float f6 = z ? o2 - n2 : o2 + n2;
                f5 = Math.min(f5, f6);
                f4 = Math.max(f4, f6);
            }
            while (i2 < childCount) {
                MotionController motionController10 = this.mFrameArrayList.get(getChildAt(i2));
                float n3 = motionController10.n();
                float o3 = motionController10.o();
                float f7 = z ? o3 - n3 : o3 + n3;
                motionController10.f2227o = 1.0f / (1.0f - abs);
                motionController10.f2226n = abs - (((f7 - f5) * abs) / (f4 - f5));
                i2++;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Rect F0(ConstraintWidget constraintWidget) {
        this.mTempRect.top = constraintWidget.a0();
        this.mTempRect.left = constraintWidget.Z();
        Rect rect = this.mTempRect;
        int Y = constraintWidget.Y();
        Rect rect2 = this.mTempRect;
        rect.right = Y + rect2.left;
        int z = constraintWidget.z();
        Rect rect3 = this.mTempRect;
        rect2.bottom = z + rect3.top;
        return rect3;
    }

    private static boolean R0(float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            float f5 = f2 / f4;
            return f3 + ((f2 * f5) - (((f4 * f5) * f5) / 2.0f)) > 1.0f;
        }
        float f6 = (-f2) / f4;
        return f3 + ((f2 * f6) + (((f4 * f6) * f6) / 2.0f)) < 0.0f;
    }

    private boolean d0(View view, MotionEvent motionEvent, float f2, float f3) {
        Matrix matrix = view.getMatrix();
        if (matrix.isIdentity()) {
            motionEvent.offsetLocation(f2, f3);
            boolean onTouchEvent = view.onTouchEvent(motionEvent);
            motionEvent.offsetLocation(-f2, -f3);
            return onTouchEvent;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.offsetLocation(f2, f3);
        if (this.mInverseMatrix == null) {
            this.mInverseMatrix = new Matrix();
        }
        matrix.invert(this.mInverseMatrix);
        obtain.transform(this.mInverseMatrix);
        boolean onTouchEvent2 = view.onTouchEvent(obtain);
        obtain.recycle();
        return onTouchEvent2;
    }

    private void e0() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            Log.e(TAG, "CHECK: motion scene not set! set \"app:layoutDescription=\"@xml/file\"");
            return;
        }
        int F = motionScene.F();
        MotionScene motionScene2 = this.mScene;
        f0(F, motionScene2.l(motionScene2.F()));
        SparseIntArray sparseIntArray = new SparseIntArray();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        Iterator it = this.mScene.o().iterator();
        while (it.hasNext()) {
            MotionScene.Transition transition = (MotionScene.Transition) it.next();
            if (transition == this.mScene.f2294c) {
                Log.v(TAG, "CHECK: CURRENT");
            }
            g0(transition);
            int A = transition.A();
            int y = transition.y();
            String c2 = Debug.c(getContext(), A);
            String c3 = Debug.c(getContext(), y);
            if (sparseIntArray.get(A) == y) {
                Log.e(TAG, "CHECK: two transitions with the same start and end " + c2 + "->" + c3);
            }
            if (sparseIntArray2.get(y) == A) {
                Log.e(TAG, "CHECK: you can't have reverse transitions" + c2 + "->" + c3);
            }
            sparseIntArray.put(A, y);
            sparseIntArray2.put(y, A);
            if (this.mScene.l(A) == null) {
                Log.e(TAG, " no such constraintSetStart " + c2);
            }
            if (this.mScene.l(y) == null) {
                Log.e(TAG, " no such constraintSetEnd " + c2);
            }
        }
    }

    private void f0(int i2, ConstraintSet constraintSet) {
        String c2 = Debug.c(getContext(), i2);
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            int id = childAt.getId();
            if (id == -1) {
                Log.w(TAG, "CHECK: " + c2 + " ALL VIEWS SHOULD HAVE ID's " + childAt.getClass().getName() + " does not!");
            }
            if (constraintSet.v(id) == null) {
                Log.w(TAG, "CHECK: " + c2 + " NO CONSTRAINTS for " + Debug.d(childAt));
            }
        }
        int[] x = constraintSet.x();
        for (int i4 = 0; i4 < x.length; i4++) {
            int i5 = x[i4];
            String c3 = Debug.c(getContext(), i5);
            if (findViewById(x[i4]) == null) {
                Log.w(TAG, "CHECK: " + c2 + " NO View matches id " + c3);
            }
            if (constraintSet.w(i5) == -1) {
                Log.w(TAG, "CHECK: " + c2 + "(" + c3 + ") no LAYOUT_HEIGHT");
            }
            if (constraintSet.B(i5) == -1) {
                Log.w(TAG, "CHECK: " + c2 + "(" + c3 + ") no LAYOUT_HEIGHT");
            }
        }
    }

    private void g0(MotionScene.Transition transition) {
        if (transition.A() == transition.y()) {
            Log.e(TAG, "CHECK: start and end constraint set should not be the same!");
        }
    }

    private void h0() {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = this.mFrameArrayList.get(childAt);
            if (motionController != null) {
                motionController.E(childAt);
            }
        }
    }

    private void k0() {
        boolean z;
        float signum = Math.signum(this.mTransitionGoalPosition - this.mTransitionLastPosition);
        long nanoTime = getNanoTime();
        Interpolator interpolator = this.mInterpolator;
        float f2 = this.mTransitionLastPosition + (!(interpolator instanceof StopLogic) ? (((nanoTime - this.mTransitionLastTime) * signum) * 1.0E-9f) / this.mTransitionDuration : 0.0f);
        if (this.mTransitionInstantly) {
            f2 = this.mTransitionGoalPosition;
        }
        if ((signum <= 0.0f || f2 < this.mTransitionGoalPosition) && (signum > 0.0f || f2 > this.mTransitionGoalPosition)) {
            z = false;
        } else {
            f2 = this.mTransitionGoalPosition;
            z = true;
        }
        if (interpolator != null && !z) {
            f2 = this.mTemporalInterpolator ? interpolator.getInterpolation((nanoTime - this.mAnimationStartTime) * 1.0E-9f) : interpolator.getInterpolation(f2);
        }
        if ((signum > 0.0f && f2 >= this.mTransitionGoalPosition) || (signum <= 0.0f && f2 <= this.mTransitionGoalPosition)) {
            f2 = this.mTransitionGoalPosition;
        }
        this.mPostInterpolationPosition = f2;
        int childCount = getChildCount();
        long nanoTime2 = getNanoTime();
        Interpolator interpolator2 = this.mProgressInterpolator;
        if (interpolator2 != null) {
            f2 = interpolator2.getInterpolation(f2);
        }
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = getChildAt(i2);
            MotionController motionController = this.mFrameArrayList.get(childAt);
            if (motionController != null) {
                motionController.x(childAt, f2, nanoTime2, this.mKeyCache);
            }
        }
        if (this.mMeasureDuringTransition) {
            requestLayout();
        }
    }

    private void l0() {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if ((this.mTransitionListener == null && ((copyOnWriteArrayList = this.mTransitionListeners) == null || copyOnWriteArrayList.isEmpty())) || this.mListenerPosition == this.mTransitionPosition) {
            return;
        }
        if (this.mListenerState != -1) {
            n0();
            this.mIsAnimating = true;
        }
        this.mListenerState = -1;
        float f2 = this.mTransitionPosition;
        this.mListenerPosition = f2;
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.a(this, this.mBeginState, this.mEndState, f2);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList2 = this.mTransitionListeners;
        if (copyOnWriteArrayList2 != null) {
            Iterator<TransitionListener> it = copyOnWriteArrayList2.iterator();
            while (it.hasNext()) {
                it.next().a(this, this.mBeginState, this.mEndState, this.mTransitionPosition);
            }
        }
        this.mIsAnimating = true;
    }

    private void n0() {
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.c(this, this.mBeginState, this.mEndState);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.mTransitionListeners;
        if (copyOnWriteArrayList != null) {
            Iterator<TransitionListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().c(this, this.mBeginState, this.mEndState);
            }
        }
    }

    private boolean u0(float f2, float f3, View view, MotionEvent motionEvent) {
        boolean z;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                if (u0((r3.getLeft() + f2) - view.getScrollX(), (r3.getTop() + f3) - view.getScrollY(), viewGroup.getChildAt(childCount), motionEvent)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            this.mBoundsCheck.set(f2, f3, (view.getRight() + f2) - view.getLeft(), (view.getBottom() + f3) - view.getTop());
            if ((motionEvent.getAction() != 0 || this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY())) && d0(view, motionEvent, -f2, -f3)) {
                return true;
            }
        }
        return z;
    }

    private void v0(AttributeSet attributeSet) {
        MotionScene motionScene;
        IS_IN_EDIT_MODE = isInEditMode();
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.MotionLayout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            boolean z = true;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.MotionLayout_layoutDescription) {
                    this.mScene = new MotionScene(getContext(), this, obtainStyledAttributes.getResourceId(index, -1));
                } else if (index == R.styleable.MotionLayout_currentState) {
                    this.mCurrentState = obtainStyledAttributes.getResourceId(index, -1);
                } else if (index == R.styleable.MotionLayout_motionProgress) {
                    this.mTransitionGoalPosition = obtainStyledAttributes.getFloat(index, 0.0f);
                    this.mInTransition = true;
                } else if (index == R.styleable.MotionLayout_applyMotionScene) {
                    z = obtainStyledAttributes.getBoolean(index, z);
                } else if (index == R.styleable.MotionLayout_showPaths) {
                    if (this.mDebugPath == 0) {
                        this.mDebugPath = obtainStyledAttributes.getBoolean(index, false) ? 2 : 0;
                    }
                } else if (index == R.styleable.MotionLayout_motionDebug) {
                    this.mDebugPath = obtainStyledAttributes.getInt(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
            if (this.mScene == null) {
                Log.e(TAG, "WARNING NO app:layoutDescription tag");
            }
            if (!z) {
                this.mScene = null;
            }
        }
        if (this.mDebugPath != 0) {
            e0();
        }
        if (this.mCurrentState != -1 || (motionScene = this.mScene) == null) {
            return;
        }
        this.mCurrentState = motionScene.F();
        this.mBeginState = this.mScene.F();
        this.mEndState = this.mScene.q();
    }

    private void z0() {
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if (this.mTransitionListener == null && ((copyOnWriteArrayList = this.mTransitionListeners) == null || copyOnWriteArrayList.isEmpty())) {
            return;
        }
        this.mIsAnimating = false;
        Iterator<Integer> it = this.mTransitionCompleted.iterator();
        while (it.hasNext()) {
            Integer next = it.next();
            TransitionListener transitionListener = this.mTransitionListener;
            if (transitionListener != null) {
                transitionListener.b(this, next.intValue());
            }
            CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList2 = this.mTransitionListeners;
            if (copyOnWriteArrayList2 != null) {
                Iterator<TransitionListener> it2 = copyOnWriteArrayList2.iterator();
                while (it2.hasNext()) {
                    it2.next().b(this, next.intValue());
                }
            }
        }
        this.mTransitionCompleted.clear();
    }

    public void A0() {
        this.mModel.h();
        invalidate();
    }

    public void B0(float f2, float f3) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.e(f2);
            this.mStateCache.h(f3);
            return;
        }
        setProgress(f2);
        setState(TransitionState.MOVING);
        this.mLastVelocity = f3;
        if (f3 != 0.0f) {
            b0(f3 > 0.0f ? 1.0f : 0.0f);
        } else {
            if (f2 == 0.0f || f2 == 1.0f) {
                return;
            }
            b0(f2 > 0.5f ? 1.0f : 0.0f);
        }
    }

    public void C0(int i2, int i3, int i4) {
        setState(TransitionState.SETUP);
        this.mCurrentState = i2;
        this.mBeginState = -1;
        this.mEndState = -1;
        ConstraintLayoutStates constraintLayoutStates = this.mConstraintLayoutSpec;
        if (constraintLayoutStates != null) {
            constraintLayoutStates.d(i2, i3, i4);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.l(i2).i(this);
        }
    }

    public void D0(int i2, int i3) {
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.f(i2);
            this.mStateCache.d(i3);
            return;
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            this.mBeginState = i2;
            this.mEndState = i3;
            motionScene.X(i2, i3);
            this.mModel.e(this.mLayoutWidget, this.mScene.l(i2), this.mScene.l(i3));
            A0();
            this.mTransitionLastPosition = 0.0f;
            J0();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
    
        if (r10 != 7) goto L36;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void G0(int r10, float r11, float r12) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.G0(int, float, float):void");
    }

    public void H0() {
        b0(1.0f);
        this.mOnComplete = null;
    }

    public void I0(Runnable runnable) {
        b0(1.0f);
        this.mOnComplete = runnable;
    }

    public void J0() {
        b0(0.0f);
    }

    public void K0(int i2) {
        if (isAttachedToWindow()) {
            M0(i2, -1, -1);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.d(i2);
    }

    public void L0(int i2, int i3) {
        if (isAttachedToWindow()) {
            N0(i2, -1, -1, i3);
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.d(i2);
    }

    public void M0(int i2, int i3, int i4) {
        N0(i2, i3, i4, -1);
    }

    public void N0(int i2, int i3, int i4, int i5) {
        StateSet stateSet;
        int a2;
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (stateSet = motionScene.f2293b) != null && (a2 = stateSet.a(this.mCurrentState, i2, i3, i4)) != -1) {
            i2 = a2;
        }
        int i6 = this.mCurrentState;
        if (i6 == i2) {
            return;
        }
        if (this.mBeginState == i2) {
            b0(0.0f);
            if (i5 > 0) {
                this.mTransitionDuration = i5 / 1000.0f;
                return;
            }
            return;
        }
        if (this.mEndState == i2) {
            b0(1.0f);
            if (i5 > 0) {
                this.mTransitionDuration = i5 / 1000.0f;
                return;
            }
            return;
        }
        this.mEndState = i2;
        if (i6 != -1) {
            D0(i6, i2);
            b0(1.0f);
            this.mTransitionLastPosition = 0.0f;
            H0();
            if (i5 > 0) {
                this.mTransitionDuration = i5 / 1000.0f;
                return;
            }
            return;
        }
        this.mTemporalInterpolator = false;
        this.mTransitionGoalPosition = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionLastTime = getNanoTime();
        this.mAnimationStartTime = getNanoTime();
        this.mTransitionInstantly = false;
        this.mInterpolator = null;
        if (i5 == -1) {
            this.mTransitionDuration = this.mScene.p() / 1000.0f;
        }
        this.mBeginState = -1;
        this.mScene.X(-1, this.mEndState);
        SparseArray sparseArray = new SparseArray();
        if (i5 == 0) {
            this.mTransitionDuration = this.mScene.p() / 1000.0f;
        } else if (i5 > 0) {
            this.mTransitionDuration = i5 / 1000.0f;
        }
        int childCount = getChildCount();
        this.mFrameArrayList.clear();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            this.mFrameArrayList.put(childAt, new MotionController(childAt));
            sparseArray.put(childAt.getId(), this.mFrameArrayList.get(childAt));
        }
        this.mInTransition = true;
        this.mModel.e(this.mLayoutWidget, null, this.mScene.l(i2));
        A0();
        this.mModel.a();
        h0();
        int width = getWidth();
        int height = getHeight();
        if (this.mDecoratorsHelpers != null) {
            for (int i8 = 0; i8 < childCount; i8++) {
                MotionController motionController = this.mFrameArrayList.get(getChildAt(i8));
                if (motionController != null) {
                    this.mScene.t(motionController);
                }
            }
            Iterator<MotionHelper> it = this.mDecoratorsHelpers.iterator();
            while (it.hasNext()) {
                it.next().D(this, this.mFrameArrayList);
            }
            for (int i9 = 0; i9 < childCount; i9++) {
                MotionController motionController2 = this.mFrameArrayList.get(getChildAt(i9));
                if (motionController2 != null) {
                    motionController2.I(width, height, this.mTransitionDuration, getNanoTime());
                }
            }
        } else {
            for (int i10 = 0; i10 < childCount; i10++) {
                MotionController motionController3 = this.mFrameArrayList.get(getChildAt(i10));
                if (motionController3 != null) {
                    this.mScene.t(motionController3);
                    motionController3.I(width, height, this.mTransitionDuration, getNanoTime());
                }
            }
        }
        float E = this.mScene.E();
        if (E != 0.0f) {
            float f2 = Float.MAX_VALUE;
            float f3 = -3.4028235E38f;
            for (int i11 = 0; i11 < childCount; i11++) {
                MotionController motionController4 = this.mFrameArrayList.get(getChildAt(i11));
                float o2 = motionController4.o() + motionController4.n();
                f2 = Math.min(f2, o2);
                f3 = Math.max(f3, o2);
            }
            for (int i12 = 0; i12 < childCount; i12++) {
                MotionController motionController5 = this.mFrameArrayList.get(getChildAt(i12));
                float n2 = motionController5.n();
                float o3 = motionController5.o();
                motionController5.f2227o = 1.0f / (1.0f - E);
                motionController5.f2226n = E - ((((n2 + o3) - f2) * E) / (f3 - f2));
            }
        }
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mInTransition = true;
        invalidate();
    }

    public void O0() {
        this.mModel.e(this.mLayoutWidget, this.mScene.l(this.mBeginState), this.mScene.l(this.mEndState));
        A0();
    }

    public void P0(int i2, ConstraintSet constraintSet) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.U(i2, constraintSet);
        }
        O0();
        if (this.mCurrentState == i2) {
            constraintSet.i(this);
        }
    }

    public void Q0(int i2, View... viewArr) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.c0(i2, viewArr);
        } else {
            Log.e(TAG, " no motionScene");
        }
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void b(View view, View view2, int i2, int i3) {
        this.mScrollTargetTime = getNanoTime();
        this.mScrollTargetDT = 0.0f;
        this.mScrollTargetDX = 0.0f;
        this.mScrollTargetDY = 0.0f;
    }

    void b0(float f2) {
        if (this.mScene == null) {
            return;
        }
        float f3 = this.mTransitionLastPosition;
        float f4 = this.mTransitionPosition;
        if (f3 != f4 && this.mTransitionInstantly) {
            this.mTransitionLastPosition = f4;
        }
        float f5 = this.mTransitionLastPosition;
        if (f5 == f2) {
            return;
        }
        this.mTemporalInterpolator = false;
        this.mTransitionGoalPosition = f2;
        this.mTransitionDuration = r0.p() / 1000.0f;
        setProgress(this.mTransitionGoalPosition);
        this.mInterpolator = null;
        this.mProgressInterpolator = this.mScene.s();
        this.mTransitionInstantly = false;
        this.mAnimationStartTime = getNanoTime();
        this.mInTransition = true;
        this.mTransitionPosition = f5;
        this.mTransitionLastPosition = f5;
        invalidate();
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void c(View view, int i2) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            float f2 = this.mScrollTargetDT;
            if (f2 == 0.0f) {
                return;
            }
            motionScene.Q(this.mScrollTargetDX / f2, this.mScrollTargetDY / f2);
        }
    }

    public boolean c0(int i2, MotionController motionController) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            return motionScene.g(i2, motionController);
        }
        return false;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void d(final View view, int i2, int i3, int[] iArr, int i4) {
        MotionScene.Transition transition;
        TouchResponse B;
        int q2;
        MotionScene motionScene = this.mScene;
        if (motionScene == null || (transition = motionScene.f2294c) == null || !transition.C()) {
            return;
        }
        int i5 = -1;
        if (!transition.C() || (B = transition.B()) == null || (q2 = B.q()) == -1 || view.getId() == q2) {
            if (motionScene.w()) {
                TouchResponse B2 = transition.B();
                if (B2 != null && (B2.e() & 4) != 0) {
                    i5 = i3;
                }
                float f2 = this.mTransitionPosition;
                if ((f2 == 1.0f || f2 == 0.0f) && view.canScrollVertically(i5)) {
                    return;
                }
            }
            if (transition.B() != null && (transition.B().e() & 1) != 0) {
                float x = motionScene.x(i2, i3);
                float f3 = this.mTransitionLastPosition;
                if ((f3 <= 0.0f && x < 0.0f) || (f3 >= 1.0f && x > 0.0f)) {
                    view.setNestedScrollingEnabled(false);
                    view.post(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.3
                        @Override // java.lang.Runnable
                        public void run() {
                            view.setNestedScrollingEnabled(true);
                        }
                    });
                    return;
                }
            }
            float f4 = this.mTransitionPosition;
            long nanoTime = getNanoTime();
            float f5 = i2;
            this.mScrollTargetDX = f5;
            float f6 = i3;
            this.mScrollTargetDY = f6;
            this.mScrollTargetDT = (float) ((nanoTime - this.mScrollTargetTime) * 1.0E-9d);
            this.mScrollTargetTime = nanoTime;
            motionScene.P(f5, f6);
            if (f4 != this.mTransitionPosition) {
                iArr[0] = i2;
                iArr[1] = i3;
            }
            j0(false);
            if (iArr[0] == 0 && iArr[1] == 0) {
                return;
            }
            this.mUndergoingMotion = true;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        ViewTransitionController viewTransitionController;
        ArrayList<MotionHelper> arrayList = this.mDecoratorsHelpers;
        if (arrayList != null) {
            Iterator<MotionHelper> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().C(canvas);
            }
        }
        j0(false);
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (viewTransitionController = motionScene.f2309r) != null) {
            viewTransitionController.c();
        }
        super.dispatchDraw(canvas);
        if (this.mScene == null) {
            return;
        }
        if ((this.mDebugPath & 1) == 1 && !isInEditMode()) {
            this.mFrames++;
            long nanoTime = getNanoTime();
            long j2 = this.mLastDrawTime;
            if (j2 != -1) {
                if (nanoTime - j2 > 200000000) {
                    this.mLastFps = ((int) ((this.mFrames / (r5 * 1.0E-9f)) * 100.0f)) / 100.0f;
                    this.mFrames = 0;
                    this.mLastDrawTime = nanoTime;
                }
            } else {
                this.mLastDrawTime = nanoTime;
            }
            Paint paint = new Paint();
            paint.setTextSize(42.0f);
            String str = this.mLastFps + " fps " + Debug.e(this, this.mBeginState) + " -> ";
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(Debug.e(this, this.mEndState));
            sb.append(" (progress: ");
            sb.append(((int) (getProgress() * 1000.0f)) / 10.0f);
            sb.append(" ) state=");
            int i2 = this.mCurrentState;
            sb.append(i2 == -1 ? "undefined" : Debug.e(this, i2));
            String sb2 = sb.toString();
            paint.setColor(-16777216);
            canvas.drawText(sb2, 11.0f, getHeight() - 29, paint);
            paint.setColor(-7864184);
            canvas.drawText(sb2, 10.0f, getHeight() - 30, paint);
        }
        if (this.mDebugPath > 1) {
            if (this.mDevModeDraw == null) {
                this.mDevModeDraw = new DevModeDraw();
            }
            this.mDevModeDraw.a(canvas, this.mFrameArrayList, this.mScene.p(), this.mDebugPath);
        }
        ArrayList<MotionHelper> arrayList2 = this.mDecoratorsHelpers;
        if (arrayList2 != null) {
            Iterator<MotionHelper> it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                it2.next().B(canvas);
            }
        }
    }

    @Override // androidx.core.view.NestedScrollingParent3
    public void g(View view, int i2, int i3, int i4, int i5, int i6, int[] iArr) {
        if (this.mUndergoingMotion || i2 != 0 || i3 != 0) {
            iArr[0] = iArr[0] + i4;
            iArr[1] = iArr[1] + i5;
        }
        this.mUndergoingMotion = false;
    }

    @IdRes
    public int[] getConstraintSetIds() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.n();
    }

    public int getCurrentState() {
        return this.mCurrentState;
    }

    public ArrayList<MotionScene.Transition> getDefinedTransitions() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.o();
    }

    public DesignTool getDesignTool() {
        if (this.mDesignTool == null) {
            this.mDesignTool = new DesignTool(this);
        }
        return this.mDesignTool;
    }

    public int getEndState() {
        return this.mEndState;
    }

    protected long getNanoTime() {
        return System.nanoTime();
    }

    public float getProgress() {
        return this.mTransitionLastPosition;
    }

    public MotionScene getScene() {
        return this.mScene;
    }

    public int getStartState() {
        return this.mBeginState;
    }

    public float getTargetPosition() {
        return this.mTransitionGoalPosition;
    }

    public Bundle getTransitionState() {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.c();
        return this.mStateCache.b();
    }

    public long getTransitionTimeMs() {
        if (this.mScene != null) {
            this.mTransitionDuration = r0.p() / 1000.0f;
        }
        return (long) (this.mTransitionDuration * 1000.0f);
    }

    public float getVelocity() {
        return this.mLastVelocity;
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public void h(View view, int i2, int i3, int i4, int i5, int i6) {
    }

    @Override // androidx.core.view.NestedScrollingParent2
    public boolean i(View view, View view2, int i2, int i3) {
        MotionScene.Transition transition;
        MotionScene motionScene = this.mScene;
        return (motionScene == null || (transition = motionScene.f2294c) == null || transition.B() == null || (this.mScene.f2294c.B().e() & 2) != 0) ? false : true;
    }

    void i0(boolean z) {
        int childCount = getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            MotionController motionController = this.mFrameArrayList.get(getChildAt(i2));
            if (motionController != null) {
                motionController.f(z);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0170  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void j0(boolean r23) {
        /*
            Method dump skipped, instructions count: 630
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.motion.widget.MotionLayout.j0(boolean):void");
    }

    protected void m0() {
        int i2;
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList;
        if ((this.mTransitionListener != null || ((copyOnWriteArrayList = this.mTransitionListeners) != null && !copyOnWriteArrayList.isEmpty())) && this.mListenerState == -1) {
            this.mListenerState = this.mCurrentState;
            if (this.mTransitionCompleted.isEmpty()) {
                i2 = -1;
            } else {
                ArrayList<Integer> arrayList = this.mTransitionCompleted;
                i2 = arrayList.get(arrayList.size() - 1).intValue();
            }
            int i3 = this.mCurrentState;
            if (i2 != i3 && i3 != -1) {
                this.mTransitionCompleted.add(Integer.valueOf(i3));
            }
        }
        z0();
        Runnable runnable = this.mOnComplete;
        if (runnable != null) {
            runnable.run();
            this.mOnComplete = null;
        }
        int[] iArr = this.mScheduledTransitionTo;
        if (iArr == null || this.mScheduledTransitions <= 0) {
            return;
        }
        K0(iArr[0]);
        int[] iArr2 = this.mScheduledTransitionTo;
        System.arraycopy(iArr2, 1, iArr2, 0, iArr2.length - 1);
        this.mScheduledTransitions--;
    }

    public void o0(int i2, boolean z, float f2) {
        TransitionListener transitionListener = this.mTransitionListener;
        if (transitionListener != null) {
            transitionListener.d(this, i2, z, f2);
        }
        CopyOnWriteArrayList<TransitionListener> copyOnWriteArrayList = this.mTransitionListeners;
        if (copyOnWriteArrayList != null) {
            Iterator<TransitionListener> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                it.next().d(this, i2, z, f2);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        MotionScene.Transition transition;
        int i2;
        super.onAttachedToWindow();
        Display display = getDisplay();
        if (display != null) {
            this.mPreviouseRotation = display.getRotation();
        }
        MotionScene motionScene = this.mScene;
        if (motionScene != null && (i2 = this.mCurrentState) != -1) {
            ConstraintSet l2 = motionScene.l(i2);
            this.mScene.T(this);
            ArrayList<MotionHelper> arrayList = this.mDecoratorsHelpers;
            if (arrayList != null) {
                Iterator<MotionHelper> it = arrayList.iterator();
                while (it.hasNext()) {
                    it.next().A(this);
                }
            }
            if (l2 != null) {
                l2.i(this);
            }
            this.mBeginState = this.mCurrentState;
        }
        y0();
        StateCache stateCache = this.mStateCache;
        if (stateCache != null) {
            if (this.mDelayedApply) {
                post(new Runnable() { // from class: androidx.constraintlayout.motion.widget.MotionLayout.4
                    @Override // java.lang.Runnable
                    public void run() {
                        MotionLayout.this.mStateCache.a();
                    }
                });
                return;
            } else {
                stateCache.a();
                return;
            }
        }
        MotionScene motionScene2 = this.mScene;
        if (motionScene2 == null || (transition = motionScene2.f2294c) == null || transition.x() != 4) {
            return;
        }
        H0();
        setState(TransitionState.SETUP);
        setState(TransitionState.MOVING);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        TouchResponse B;
        int q2;
        RectF p2;
        MotionScene motionScene = this.mScene;
        if (motionScene != null && this.mInteractionEnabled) {
            ViewTransitionController viewTransitionController = motionScene.f2309r;
            if (viewTransitionController != null) {
                viewTransitionController.h(motionEvent);
            }
            MotionScene.Transition transition = this.mScene.f2294c;
            if (transition != null && transition.C() && (B = transition.B()) != null && ((motionEvent.getAction() != 0 || (p2 = B.p(this, new RectF())) == null || p2.contains(motionEvent.getX(), motionEvent.getY())) && (q2 = B.q()) != -1)) {
                View view = this.mRegionView;
                if (view == null || view.getId() != q2) {
                    this.mRegionView = findViewById(q2);
                }
                if (this.mRegionView != null) {
                    this.mBoundsCheck.set(r0.getLeft(), this.mRegionView.getTop(), this.mRegionView.getRight(), this.mRegionView.getBottom());
                    if (this.mBoundsCheck.contains(motionEvent.getX(), motionEvent.getY()) && !u0(this.mRegionView.getLeft(), this.mRegionView.getTop(), this.mRegionView, motionEvent)) {
                        return onTouchEvent(motionEvent);
                    }
                }
            }
        }
        return false;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        this.mInLayout = true;
        try {
            if (this.mScene == null) {
                super.onLayout(z, i2, i3, i4, i5);
                return;
            }
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (this.mLastLayoutWidth != i6 || this.mLastLayoutHeight != i7) {
                A0();
                j0(true);
            }
            this.mLastLayoutWidth = i6;
            this.mLastLayoutHeight = i7;
            this.mOldWidth = i6;
            this.mOldHeight = i7;
        } finally {
            this.mInLayout = false;
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.mScene == null) {
            super.onMeasure(i2, i3);
            return;
        }
        boolean z = false;
        boolean z2 = (this.mLastWidthMeasureSpec == i2 && this.mLastHeightMeasureSpec == i3) ? false : true;
        if (this.mNeedsFireTransitionCompleted) {
            this.mNeedsFireTransitionCompleted = false;
            y0();
            z0();
            z2 = true;
        }
        if (this.mDirtyHierarchy) {
            z2 = true;
        }
        this.mLastWidthMeasureSpec = i2;
        this.mLastHeightMeasureSpec = i3;
        int F = this.mScene.F();
        int q2 = this.mScene.q();
        if ((z2 || this.mModel.f(F, q2)) && this.mBeginState != -1) {
            super.onMeasure(i2, i3);
            this.mModel.e(this.mLayoutWidget, this.mScene.l(F), this.mScene.l(q2));
            this.mModel.h();
            this.mModel.i(F, q2);
        } else {
            if (z2) {
                super.onMeasure(i2, i3);
            }
            z = true;
        }
        if (this.mMeasureDuringTransition || z) {
            int paddingTop = getPaddingTop() + getPaddingBottom();
            int Y = this.mLayoutWidget.Y() + getPaddingLeft() + getPaddingRight();
            int z3 = this.mLayoutWidget.z() + paddingTop;
            int i4 = this.mWidthMeasureMode;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                Y = (int) (this.mStartWrapWidth + (this.mPostInterpolationPosition * (this.mEndWrapWidth - r8)));
                requestLayout();
            }
            int i5 = this.mHeightMeasureMode;
            if (i5 == Integer.MIN_VALUE || i5 == 0) {
                z3 = (int) (this.mStartWrapHeight + (this.mPostInterpolationPosition * (this.mEndWrapHeight - r8)));
                requestLayout();
            }
            setMeasuredDimension(Y, z3);
        }
        k0();
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f3, boolean z) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f2, float f3) {
        return false;
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i2) {
        MotionScene motionScene = this.mScene;
        if (motionScene != null) {
            motionScene.W(t());
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null || !this.mInteractionEnabled || !motionScene.b0()) {
            return super.onTouchEvent(motionEvent);
        }
        MotionScene.Transition transition = this.mScene.f2294c;
        if (transition != null && !transition.C()) {
            return super.onTouchEvent(motionEvent);
        }
        this.mScene.R(motionEvent, getCurrentState(), this);
        if (this.mScene.f2294c.D(4)) {
            return this.mScene.f2294c.B().r();
        }
        return true;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        if (view instanceof MotionHelper) {
            MotionHelper motionHelper = (MotionHelper) view;
            if (this.mTransitionListeners == null) {
                this.mTransitionListeners = new CopyOnWriteArrayList<>();
            }
            this.mTransitionListeners.add(motionHelper);
            if (motionHelper.z()) {
                if (this.mOnShowHelpers == null) {
                    this.mOnShowHelpers = new ArrayList<>();
                }
                this.mOnShowHelpers.add(motionHelper);
            }
            if (motionHelper.y()) {
                if (this.mOnHideHelpers == null) {
                    this.mOnHideHelpers = new ArrayList<>();
                }
                this.mOnHideHelpers.add(motionHelper);
            }
            if (motionHelper.x()) {
                if (this.mDecoratorsHelpers == null) {
                    this.mDecoratorsHelpers = new ArrayList<>();
                }
                this.mDecoratorsHelpers.add(motionHelper);
            }
        }
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            arrayList.remove(view);
        }
        ArrayList<MotionHelper> arrayList2 = this.mOnHideHelpers;
        if (arrayList2 != null) {
            arrayList2.remove(view);
        }
    }

    void p0(int i2, float f2, float f3, float f4, float[] fArr) {
        String resourceName;
        HashMap<View, MotionController> hashMap = this.mFrameArrayList;
        View q2 = q(i2);
        MotionController motionController = hashMap.get(q2);
        if (motionController != null) {
            motionController.l(f2, f3, f4, fArr);
            float y = q2.getY();
            this.mLastPos = f2;
            this.mLastY = y;
            return;
        }
        if (q2 == null) {
            resourceName = "" + i2;
        } else {
            resourceName = q2.getContext().getResources().getResourceName(i2);
        }
        Log.w(TAG, "WARNING could not find view id " + resourceName);
    }

    public ConstraintSet q0(int i2) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return null;
        }
        return motionScene.l(i2);
    }

    MotionController r0(int i2) {
        return this.mFrameArrayList.get(findViewById(i2));
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View, android.view.ViewParent
    public void requestLayout() {
        MotionScene motionScene;
        MotionScene.Transition transition;
        if (!this.mMeasureDuringTransition && this.mCurrentState == -1 && (motionScene = this.mScene) != null && (transition = motionScene.f2294c) != null) {
            int z = transition.z();
            if (z == 0) {
                return;
            }
            if (z == 2) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    this.mFrameArrayList.get(getChildAt(i2)).z();
                }
                return;
            }
        }
        super.requestLayout();
    }

    public MotionScene.Transition s0(int i2) {
        return this.mScene.G(i2);
    }

    public void setDebugMode(int i2) {
        this.mDebugPath = i2;
        invalidate();
    }

    public void setDelayedApplicationOfInitialState(boolean z) {
        this.mDelayedApply = z;
    }

    public void setInteractionEnabled(boolean z) {
        this.mInteractionEnabled = z;
    }

    public void setInterpolatedProgress(float f2) {
        if (this.mScene != null) {
            setState(TransitionState.MOVING);
            Interpolator s2 = this.mScene.s();
            if (s2 != null) {
                setProgress(s2.getInterpolation(f2));
                return;
            }
        }
        setProgress(f2);
    }

    public void setOnHide(float f2) {
        ArrayList<MotionHelper> arrayList = this.mOnHideHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mOnHideHelpers.get(i2).setProgress(f2);
            }
        }
    }

    public void setOnShow(float f2) {
        ArrayList<MotionHelper> arrayList = this.mOnShowHelpers;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.mOnShowHelpers.get(i2).setProgress(f2);
            }
        }
    }

    public void setProgress(float f2) {
        if (f2 < 0.0f || f2 > 1.0f) {
            Log.w(TAG, "Warning! Progress is defined for values between 0.0 and 1.0 inclusive");
        }
        if (!isAttachedToWindow()) {
            if (this.mStateCache == null) {
                this.mStateCache = new StateCache();
            }
            this.mStateCache.e(f2);
            return;
        }
        if (f2 <= 0.0f) {
            if (this.mTransitionLastPosition == 1.0f && this.mCurrentState == this.mEndState) {
                setState(TransitionState.MOVING);
            }
            this.mCurrentState = this.mBeginState;
            if (this.mTransitionLastPosition == 0.0f) {
                setState(TransitionState.FINISHED);
            }
        } else if (f2 >= 1.0f) {
            if (this.mTransitionLastPosition == 0.0f && this.mCurrentState == this.mBeginState) {
                setState(TransitionState.MOVING);
            }
            this.mCurrentState = this.mEndState;
            if (this.mTransitionLastPosition == 1.0f) {
                setState(TransitionState.FINISHED);
            }
        } else {
            this.mCurrentState = -1;
            setState(TransitionState.MOVING);
        }
        if (this.mScene == null) {
            return;
        }
        this.mTransitionInstantly = true;
        this.mTransitionGoalPosition = f2;
        this.mTransitionPosition = f2;
        this.mTransitionLastTime = -1L;
        this.mAnimationStartTime = -1L;
        this.mInterpolator = null;
        this.mInTransition = true;
        invalidate();
    }

    public void setScene(MotionScene motionScene) {
        this.mScene = motionScene;
        motionScene.W(t());
        A0();
    }

    void setStartState(int i2) {
        if (isAttachedToWindow()) {
            this.mCurrentState = i2;
            return;
        }
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.f(i2);
        this.mStateCache.d(i2);
    }

    void setState(TransitionState transitionState) {
        TransitionState transitionState2 = TransitionState.FINISHED;
        if (transitionState == transitionState2 && this.mCurrentState == -1) {
            return;
        }
        TransitionState transitionState3 = this.mTransitionState;
        this.mTransitionState = transitionState;
        TransitionState transitionState4 = TransitionState.MOVING;
        if (transitionState3 == transitionState4 && transitionState == transitionState4) {
            l0();
        }
        int ordinal = transitionState3.ordinal();
        if (ordinal != 0 && ordinal != 1) {
            if (ordinal == 2 && transitionState == transitionState2) {
                m0();
                return;
            }
            return;
        }
        if (transitionState == transitionState4) {
            l0();
        }
        if (transitionState == transitionState2) {
            m0();
        }
    }

    public void setTransition(int i2) {
        if (this.mScene != null) {
            MotionScene.Transition s0 = s0(i2);
            this.mBeginState = s0.A();
            this.mEndState = s0.y();
            if (!isAttachedToWindow()) {
                if (this.mStateCache == null) {
                    this.mStateCache = new StateCache();
                }
                this.mStateCache.f(this.mBeginState);
                this.mStateCache.d(this.mEndState);
                return;
            }
            int i3 = this.mCurrentState;
            float f2 = i3 == this.mBeginState ? 0.0f : i3 == this.mEndState ? 1.0f : Float.NaN;
            this.mScene.Y(s0);
            this.mModel.e(this.mLayoutWidget, this.mScene.l(this.mBeginState), this.mScene.l(this.mEndState));
            A0();
            if (this.mTransitionLastPosition != f2) {
                if (f2 == 0.0f) {
                    i0(true);
                    this.mScene.l(this.mBeginState).i(this);
                } else if (f2 == 1.0f) {
                    i0(false);
                    this.mScene.l(this.mEndState).i(this);
                }
            }
            this.mTransitionLastPosition = Float.isNaN(f2) ? 0.0f : f2;
            if (!Float.isNaN(f2)) {
                setProgress(f2);
                return;
            }
            Log.v(TAG, Debug.b() + " transitionToStart ");
            J0();
        }
    }

    public void setTransitionDuration(int i2) {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            Log.e(TAG, "MotionScene not defined");
        } else {
            motionScene.V(i2);
        }
    }

    public void setTransitionListener(TransitionListener transitionListener) {
        this.mTransitionListener = transitionListener;
    }

    public void setTransitionState(Bundle bundle) {
        if (this.mStateCache == null) {
            this.mStateCache = new StateCache();
        }
        this.mStateCache.g(bundle);
        if (isAttachedToWindow()) {
            this.mStateCache.a();
        }
    }

    public void t0(View view, float f2, float f3, float[] fArr, int i2) {
        float f4;
        float f5 = this.mLastVelocity;
        float f6 = this.mTransitionLastPosition;
        if (this.mInterpolator != null) {
            float signum = Math.signum(this.mTransitionGoalPosition - f6);
            float interpolation = this.mInterpolator.getInterpolation(this.mTransitionLastPosition + EPSILON);
            f4 = this.mInterpolator.getInterpolation(this.mTransitionLastPosition);
            f5 = (signum * ((interpolation - f4) / EPSILON)) / this.mTransitionDuration;
        } else {
            f4 = f6;
        }
        Interpolator interpolator = this.mInterpolator;
        if (interpolator instanceof MotionInterpolator) {
            f5 = ((MotionInterpolator) interpolator).a();
        }
        MotionController motionController = this.mFrameArrayList.get(view);
        if ((i2 & 1) == 0) {
            motionController.r(f4, view.getWidth(), view.getHeight(), f2, f3, fArr);
        } else {
            motionController.l(f4, f2, f3, fArr);
        }
        if (i2 < 2) {
            fArr[0] = fArr[0] * f5;
            fArr[1] = fArr[1] * f5;
        }
    }

    @Override // android.view.View
    public String toString() {
        Context context = getContext();
        return Debug.c(context, this.mBeginState) + "->" + Debug.c(context, this.mEndState) + " (pos:" + this.mTransitionLastPosition + " Dpos/Dt:" + this.mLastVelocity;
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout
    protected void v(int i2) {
        this.mConstraintLayoutSpec = null;
    }

    public boolean w0() {
        return this.mInteractionEnabled;
    }

    protected MotionTracker x0() {
        return MyTracker.f();
    }

    void y0() {
        MotionScene motionScene = this.mScene;
        if (motionScene == null) {
            return;
        }
        if (motionScene.h(this, this.mCurrentState)) {
            requestLayout();
            return;
        }
        int i2 = this.mCurrentState;
        if (i2 != -1) {
            this.mScene.f(this, i2);
        }
        if (this.mScene.b0()) {
            this.mScene.Z();
        }
    }

    protected void setTransition(MotionScene.Transition transition) {
        this.mScene.Y(transition);
        setState(TransitionState.SETUP);
        if (this.mCurrentState == this.mScene.q()) {
            this.mTransitionLastPosition = 1.0f;
            this.mTransitionPosition = 1.0f;
            this.mTransitionGoalPosition = 1.0f;
        } else {
            this.mTransitionLastPosition = 0.0f;
            this.mTransitionPosition = 0.0f;
            this.mTransitionGoalPosition = 0.0f;
        }
        this.mTransitionLastTime = transition.D(1) ? -1L : getNanoTime();
        int F = this.mScene.F();
        int q2 = this.mScene.q();
        if (F == this.mBeginState && q2 == this.mEndState) {
            return;
        }
        this.mBeginState = F;
        this.mEndState = q2;
        this.mScene.X(F, q2);
        this.mModel.e(this.mLayoutWidget, this.mScene.l(this.mBeginState), this.mScene.l(this.mEndState));
        this.mModel.i(this.mBeginState, this.mEndState);
        this.mModel.h();
        A0();
    }

    public MotionLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mProgressInterpolator = null;
        this.mLastVelocity = 0.0f;
        this.mBeginState = -1;
        this.mCurrentState = -1;
        this.mEndState = -1;
        this.mLastWidthMeasureSpec = 0;
        this.mLastHeightMeasureSpec = 0;
        this.mInteractionEnabled = true;
        this.mFrameArrayList = new HashMap<>();
        this.mAnimationStartTime = 0L;
        this.mTransitionDuration = 1.0f;
        this.mTransitionPosition = 0.0f;
        this.mTransitionLastPosition = 0.0f;
        this.mTransitionGoalPosition = 0.0f;
        this.mInTransition = false;
        this.mIndirectTransition = false;
        this.mDebugPath = 0;
        this.mTemporalInterpolator = false;
        this.mStopLogic = new StopLogic();
        this.mDecelerateLogic = new DecelerateInterpolator();
        this.mFirstDown = true;
        this.mUndergoingMotion = false;
        this.mKeepAnimating = false;
        this.mOnShowHelpers = null;
        this.mOnHideHelpers = null;
        this.mDecoratorsHelpers = null;
        this.mTransitionListeners = null;
        this.mFrames = 0;
        this.mLastDrawTime = -1L;
        this.mLastFps = 0.0f;
        this.mListenerState = 0;
        this.mListenerPosition = 0.0f;
        this.mIsAnimating = false;
        this.mMeasureDuringTransition = false;
        this.mKeyCache = new KeyCache();
        this.mInLayout = false;
        this.mOnComplete = null;
        this.mScheduledTransitionTo = null;
        this.mScheduledTransitions = 0;
        this.mInRotation = false;
        this.mRotatMode = 0;
        this.mPreRotate = new HashMap<>();
        this.mTempRect = new Rect();
        this.mDelayedApply = false;
        this.mTransitionState = TransitionState.UNDEFINED;
        this.mModel = new Model();
        this.mNeedsFireTransitionCompleted = false;
        this.mBoundsCheck = new RectF();
        this.mRegionView = null;
        this.mInverseMatrix = null;
        this.mTransitionCompleted = new ArrayList<>();
        v0(attributeSet);
    }
}
