package cn.nubia.projection.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import cn.nubia.projection.ProjectionUIController;
import cn.nubia.projection.R;
import cn.nubia.projection.ui.ProjectionWindowView;
import cn.nubia.projection.util.PLog;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class ProjectionWindowView extends FrameLayout {
    public static final PathInterpolator FAST_OUT_SLOW_IN = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
    private static final int LEFT_BOTTOM = 2;
    private static final int LEFT_TOP = 1;
    private static final float MIN_MOVE_DISTANCE = 20.0f;
    private static final int RIGHT_BOTTOM = 4;
    private static final int RIGHT_TOP = 3;
    private static final int VIEW_NOT_TOUCH_TIMEOUT = 3000;
    private volatile boolean mAnimationPlaying;
    private final Runnable mCollapsePanelRunnable;
    private int mCustomGravity;
    private boolean mDelayRemoveForFullScreen;
    private NubiaProjectionExpandedPanel mExpandedPanel;
    private final FoldMgr.Callback mFoldCallback;
    private float mHalfScreenHeight;
    private float mHalfScreenWidth;
    private boolean mKeepWindowOnTouch;
    private volatile int mOrientation;
    private NubiaProjectionPanel mPanel;
    private boolean mPanelExpanded;
    private final RotationMgr.Callback mRotationCallback;
    private int mScreenShotViewHeight;
    private int mScreenShotViewWidth;
    private float mStartX;
    private float mStartY;
    private float mStopX;
    private float mStopY;
    private final Runnable mTranslateRunnable;
    private ProjectionUIController mUIControl;
    private float mViewDownX;
    private float mViewDownY;
    private float mVisibleBottom;
    private float mVisibleLeft;
    private float mVisibleRight;
    private float mVisibleTop;
    private float mWindowPaddingHorizontal;
    private float mWindowPaddingVertical;
    private float rawMoveX;
    private float rawMoveY;

    /* renamed from: cn.nubia.projection.ui.ProjectionWindowView$2, reason: invalid class name */
    class AnonymousClass2 implements RotationMgr.Callback {
        AnonymousClass2() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            ProjectionWindowView.this.s();
        }

        @Override // com.zte.gameassist.common.RotationMgr.Callback
        /* renamed from: onRotationChanged */
        public void y(int i2) {
            if (ProjectionWindowView.this.mOrientation != i2) {
                ProjectionWindowView.this.mOrientation = i2;
                ProjectionWindowView.this.mUIControl.b0().post(new Runnable() { // from class: cn.nubia.projection.ui.v
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProjectionWindowView.AnonymousClass2.this.b();
                    }
                });
            }
        }
    }

    /* renamed from: cn.nubia.projection.ui.ProjectionWindowView$4, reason: invalid class name */
    class AnonymousClass4 extends AnimatorListenerAdapter {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f8897c;

        AnonymousClass4(boolean z) {
            this.f8897c = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            if (ProjectionWindowView.this.mPanel != null) {
                ProjectionWindowView.this.mPanel.setAlpha(1.0f);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator, boolean z) {
            ProjectionWindowView.this.setAnimationPlaying(false);
            if (ProjectionWindowView.this.mPanel.getVisibility() == 0) {
                ProjectionWindowView.this.mPanel.setAlpha(0.0f);
                ProjectionWindowView.this.post(new Runnable() { // from class: cn.nubia.projection.ui.w
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProjectionWindowView.AnonymousClass4.this.b();
                    }
                });
            }
            ProjectionWindowView.this.mExpandedPanel.setVisibility(8);
            ProjectionWindowView.this.V();
            ProjectionWindowView.this.U();
            if (this.f8897c) {
                ProjectionWindowView.this.mUIControl.T0();
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ProjectionWindowView.this.setAnimationPlaying(true);
            ProjectionWindowView.this.Y();
            ProjectionWindowView.this.mPanel.setVisibility(this.f8897c ? 8 : 0);
            ProjectionWindowView.this.mPanel.setAlpha(0.0f);
        }
    }

    public enum UIStatus {
        PROJECTION_MODE_CHANGE,
        UPDATE_APP_ICON,
        TIMEOUT_UI_TRANSLATE,
        RESET_UI,
        REMOVE_WINDOW,
        VIRTUAL_HANDLES_ENABLE,
        VIRTUAL_HANDLES_DISABLE,
        PROJECTION_TYPE_CHANGE,
        UPDATE_SUSPEND
    }

    public ProjectionWindowView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPanelExpanded = false;
        this.mAnimationPlaying = false;
        this.mDelayRemoveForFullScreen = false;
        this.mFoldCallback = new FoldMgr.Callback() { // from class: cn.nubia.projection.ui.ProjectionWindowView.1
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public void onDisplayInUseStateChanged(int i2) {
                ProjectionWindowView.this.mUIControl.b0().postDelayed(new Runnable() { // from class: cn.nubia.projection.ui.ProjectionWindowView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ProjectionWindowView.this.s();
                    }
                }, 2000L);
            }
        };
        this.mRotationCallback = new AnonymousClass2();
        this.mTranslateRunnable = new Runnable() { // from class: cn.nubia.projection.ui.q
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.K();
            }
        };
        this.mCollapsePanelRunnable = new Runnable() { // from class: cn.nubia.projection.ui.r
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.L();
            }
        };
    }

    private void A() {
        if (this.mUIControl.r0()) {
            return;
        }
        a0();
        this.mExpandedPanel.p0();
    }

    private void B() {
        this.mExpandedPanel.y();
    }

    private void C() {
        this.mExpandedPanel.z();
    }

    private boolean H() {
        return ZteFeatureWrapper.getBoolean(ZteFeature.ZTE_FEATURE_EXPAND_PROJECTION_SCREEN, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void J() {
        this.mPanel.f();
        this.mExpandedPanel.v();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K() {
        PLog.a("mTranslateRunnable mDelayRemoveForFullScreen=" + this.mDelayRemoveForFullScreen + ",istKeepWindowOnTouch:" + I() + ",isAnimationPlaying:" + D());
        if (I() || D()) {
            return;
        }
        T(UIStatus.TIMEOUT_UI_TRANSLATE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L() {
        if (I()) {
            return;
        }
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void M(boolean z, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        float f2 = 1.0f - animatedFraction;
        float width = this.mPanel.getWidth() * f2;
        float width2 = this.mExpandedPanel.getWidth() * animatedFraction;
        if (z) {
            width = -width;
            width2 = -width2;
        }
        this.mPanel.setTranslationX(width);
        this.mPanel.setAlpha(animatedFraction);
        this.mExpandedPanel.setTranslationX(width2);
        this.mExpandedPanel.setAlpha(f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(boolean z, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        float width = this.mPanel.getWidth() * animatedFraction;
        float f2 = 1.0f - animatedFraction;
        float width2 = this.mExpandedPanel.getWidth() * f2;
        if (z) {
            width = -width;
            width2 = -width2;
        }
        this.mPanel.setTranslationX(width);
        this.mPanel.setAlpha(f2);
        this.mExpandedPanel.setTranslationX(width2);
        this.mExpandedPanel.setAlpha(animatedFraction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void O(float f2, ValueAnimator valueAnimator) {
        X(((Float) valueAnimator.getAnimatedValue()).floatValue(), f2);
    }

    private void S(float f2, float f3, final float f4) {
        if (f2 == f3) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(f2, f3);
        ofFloat.setDuration(200L);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.u
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProjectionWindowView.this.O(f4, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.ProjectionWindowView.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ProjectionWindowView.this.V();
                ProjectionWindowView.this.a0();
            }
        });
        ofFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U() {
        WindowManager.LayoutParams d0 = this.mUIControl.d0();
        float rawFinalX = getRawFinalX();
        float f2 = d0.y;
        if (E()) {
            f2 = d0.y >= ((int) (this.mVisibleBottom - ((float) this.mExpandedPanel.getHeight()))) ? this.mVisibleBottom - this.mPanel.getHeight() : (d0.y + this.mExpandedPanel.getHeight()) - this.mPanel.getHeight();
        }
        X(rawFinalX, f2);
    }

    private void X(float f2, float f3) {
        WindowManager.LayoutParams d0 = this.mUIControl.d0();
        if (d0.x != ((int) f2)) {
            float f4 = this.mVisibleLeft;
            if (f2 < f4) {
                f2 = f4;
            } else if (f2 > this.mVisibleRight - getLatestWidth()) {
                f2 = this.mVisibleRight - getLatestWidth();
            }
        }
        if (d0.y != ((int) f3)) {
            float f5 = this.mVisibleTop;
            if (f3 < f5) {
                f3 = f5;
            } else if (f3 > this.mVisibleBottom - getLatestHeight()) {
                f3 = this.mVisibleBottom - getLatestHeight();
            }
        }
        this.mUIControl.l1((int) f2, (int) f3);
    }

    private float getLatestHeight() {
        return this.mPanelExpanded ? this.mExpandedPanel.getHeight() : this.mPanel.getHeight();
    }

    private float getLatestWidth() {
        float width;
        float f2;
        if (this.mPanelExpanded) {
            width = this.mExpandedPanel.getWidth();
            f2 = this.mWindowPaddingHorizontal;
        } else {
            width = this.mPanel.getWidth();
            f2 = this.mWindowPaddingHorizontal;
        }
        return width + (f2 * 2.0f);
    }

    private float getRawFinalX() {
        V();
        return F() ? this.mVisibleLeft : this.mVisibleRight - getLatestWidth();
    }

    private boolean n() {
        return Math.abs(this.mStopX - this.mStartX) > MIN_MOVE_DISTANCE || Math.abs(this.mStopY - this.mStartY) > MIN_MOVE_DISTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        getDisplayParams();
        ProjectionUIController projectionUIController = this.mUIControl;
        if (projectionUIController != null) {
            WindowManager.LayoutParams d0 = projectionUIController.d0();
            if (G()) {
                float f2 = d0.x;
                int i2 = this.mScreenShotViewWidth;
                int i3 = this.mScreenShotViewHeight;
                this.rawMoveX = f2 * (i2 / i3);
                this.rawMoveY = d0.y * (i3 / i2);
            } else {
                float f3 = d0.x;
                int i4 = this.mScreenShotViewHeight;
                int i5 = this.mScreenShotViewWidth;
                this.rawMoveX = f3 * (i4 / i5);
                this.rawMoveY = d0.y * (i5 / i4);
            }
            float f4 = this.rawMoveX;
            d0.x = (int) f4;
            float f5 = this.rawMoveY;
            d0.y = (int) f5;
            v(f4, f5);
        }
    }

    private void t() {
        postDelayed(new Runnable() { // from class: cn.nubia.projection.ui.s
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.J();
            }
        }, this.mPanelExpanded ? 300L : 0L);
    }

    private void u() {
        this.mExpandedPanel.w();
    }

    private void v(float f2, float f3) {
        S(f2, getRawFinalX(), f3);
    }

    private void w() {
        this.mPanelExpanded = false;
        this.mPanel.setVisibility(0);
        this.mExpandedPanel.setVisibility(4);
    }

    private void x() {
        PLog.a("handleResetUI: mPanelExpanded:" + this.mPanelExpanded);
        getDisplayParams();
        this.mPanel.g();
        this.mExpandedPanel.x();
        if (this.mUIControl.t0()) {
            T(UIStatus.UPDATE_APP_ICON);
        }
        WindowManager.LayoutParams d0 = this.mUIControl.d0();
        v(d0.x, d0.y);
        a0();
    }

    private void y() {
        if (this.mPanelExpanded) {
            postDelayed(this.mCollapsePanelRunnable, this.mUIControl.V() ? 0L : 6000L);
        } else if (this.mUIControl.V() && getDelayRemoveForFullScreen()) {
            this.mPanel.l();
        } else {
            setAlpha(0.6f);
        }
        setDelayRemoveForFullScreen(false);
    }

    private void z() {
        this.mUIControl.j1();
    }

    public boolean D() {
        return this.mAnimationPlaying;
    }

    public boolean E() {
        return this.mCustomGravity % 2 == 0;
    }

    public boolean F() {
        return this.mCustomGravity < 3;
    }

    public boolean G() {
        return this.mOrientation == 0 || this.mOrientation == 2;
    }

    public boolean I() {
        return this.mKeepWindowOnTouch;
    }

    public void P() {
        if (this.mPanelExpanded) {
            p(true);
        } else {
            this.mPanel.l();
        }
    }

    public void Q(boolean z) {
        final boolean F = F();
        boolean z2 = (this.mUIControl.V() && !getDelayRemoveForFullScreen()) || z;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, getWidth());
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(FAST_OUT_SLOW_IN);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.t
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProjectionWindowView.this.M(F, valueAnimator);
            }
        });
        ofFloat.addListener(new AnonymousClass4(z2));
        ofFloat.start();
    }

    public void R() {
        final boolean F = F();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(250L);
        ofFloat.setInterpolator(FAST_OUT_SLOW_IN);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: cn.nubia.projection.ui.p
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ProjectionWindowView.this.N(F, valueAnimator);
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: cn.nubia.projection.ui.ProjectionWindowView.3
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator, boolean z) {
                ProjectionWindowView.this.setAnimationPlaying(false);
                ProjectionWindowView.this.mPanel.setVisibility(8);
                ProjectionWindowView.this.V();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator, boolean z) {
                ProjectionWindowView.this.setAnimationPlaying(true);
                ProjectionWindowView.this.Y();
                ProjectionWindowView.this.W();
                ProjectionWindowView.this.mExpandedPanel.setVisibility(0);
                ProjectionWindowView.this.mExpandedPanel.setAlpha(0.0f);
            }
        });
        ofFloat.start();
    }

    public void T(final UIStatus uIStatus) {
        if (Looper.myLooper() != this.mUIControl.b0().getLooper()) {
            this.mUIControl.b0().post(new Runnable() { // from class: cn.nubia.projection.ui.ProjectionWindowView.6
                @Override // java.lang.Runnable
                public void run() {
                    ProjectionWindowView.this.T(uIStatus);
                }
            });
        }
        PLog.a("translateTo " + uIStatus);
        switch (uIStatus) {
            case PROJECTION_MODE_CHANGE:
                t();
                break;
            case UPDATE_APP_ICON:
                z();
                break;
            case TIMEOUT_UI_TRANSLATE:
                y();
                break;
            case RESET_UI:
                x();
                break;
            case REMOVE_WINDOW:
                w();
                break;
            case VIRTUAL_HANDLES_ENABLE:
                C();
                break;
            case VIRTUAL_HANDLES_DISABLE:
                B();
                break;
            case PROJECTION_TYPE_CHANGE:
                u();
                break;
            case UPDATE_SUSPEND:
                A();
                break;
        }
    }

    public void V() {
        ProjectionUIController projectionUIController = this.mUIControl;
        if (projectionUIController == null) {
            return;
        }
        WindowManager.LayoutParams d0 = projectionUIController.d0();
        float latestWidth = d0.x + (getLatestWidth() / 2.0f);
        float latestHeight = d0.y + (getLatestHeight() / 2.0f);
        float f2 = this.mHalfScreenWidth;
        if (latestWidth < f2) {
            if (latestHeight < this.mHalfScreenHeight) {
                this.mCustomGravity = 1;
                return;
            } else {
                this.mCustomGravity = 2;
                return;
            }
        }
        if (latestWidth > f2) {
            if (latestHeight < this.mHalfScreenHeight) {
                this.mCustomGravity = 3;
            } else {
                this.mCustomGravity = 4;
            }
        }
    }

    public void W() {
        if (E()) {
            X(r0.x, ((float) this.mUIControl.d0().y) > this.mVisibleBottom - ((float) this.mPanel.getHeight()) ? this.mVisibleBottom - this.mExpandedPanel.getHeight() : (r0.y - this.mExpandedPanel.getHeight()) + this.mPanel.getHeight());
        }
    }

    public void Y() {
        int i2;
        int i3;
        V();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.mPanel.getLayoutParams();
        if (F()) {
            i2 = this.mCustomGravity == 1 ? 48 : 80;
            i3 = 8388611;
        } else {
            i2 = this.mCustomGravity == 3 ? 48 : 80;
            i3 = 8388613;
        }
        layoutParams.gravity = i3 | i2;
        this.mPanel.setLayoutParams(layoutParams);
    }

    public void Z() {
        removeCallbacks(this.mTranslateRunnable);
        removeCallbacks(this.mCollapsePanelRunnable);
        postDelayed(this.mTranslateRunnable, 3000L);
    }

    public void a0() {
        setAlpha(1.0f);
        Z();
    }

    public Point getDefaultDisplayRealSize() {
        DisplayManager displayManager = (DisplayManager) getContext().getSystemService("display");
        Point point = new Point();
        displayManager.getDisplay(0).getRealSize(point);
        return point;
    }

    public boolean getDelayRemoveForFullScreen() {
        return this.mDelayRemoveForFullScreen;
    }

    public void getDisplayParams() {
        this.mWindowPaddingVertical = getResources().getDimension(R.dimen.projection_window_padding_vertical);
        this.mWindowPaddingHorizontal = getResources().getDimension(R.dimen.projection_window_padding_horizontal);
        Point defaultDisplayRealSize = getDefaultDisplayRealSize();
        this.mScreenShotViewWidth = Math.min(defaultDisplayRealSize.x, defaultDisplayRealSize.y);
        this.mScreenShotViewHeight = Math.max(defaultDisplayRealSize.x, defaultDisplayRealSize.y);
        if (G()) {
            int i2 = this.mScreenShotViewWidth;
            this.mHalfScreenWidth = i2 / 2.0f;
            int i3 = this.mScreenShotViewHeight;
            float f2 = this.mWindowPaddingVertical;
            this.mHalfScreenHeight = (i3 / 2.0f) - f2;
            this.mVisibleRight = i2;
            this.mVisibleBottom = i3 - f2;
        } else {
            int i4 = this.mScreenShotViewHeight;
            this.mHalfScreenWidth = i4 / 2.0f;
            int i5 = this.mScreenShotViewWidth;
            float f3 = this.mWindowPaddingVertical;
            this.mHalfScreenHeight = (i5 / 2.0f) - f3;
            this.mVisibleRight = i4;
            this.mVisibleBottom = i5 - f3;
        }
        this.mVisibleLeft = 0.0f;
        this.mVisibleTop = this.mWindowPaddingVertical;
        V();
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getScreenShotViewHeight() {
        return this.mScreenShotViewHeight;
    }

    public int getScreenShotViewWidth() {
        return this.mScreenShotViewWidth;
    }

    public void o() {
        this.mExpandedPanel.f0(false);
        p(false);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        RotationMgr.e(getContext()).c(this.mRotationCallback);
        FoldMgr.c().a(this.mFoldCallback);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RotationMgr.e(getContext()).p(this.mRotationCallback);
        FoldMgr.c().h(this.mFoldCallback);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mPanel = (NubiaProjectionPanel) findViewById(R.id.projection_panel);
        this.mExpandedPanel = (NubiaProjectionExpandedPanel) findViewById(R.id.projection_expanded_panel);
        this.mPanel.setupHost(this);
        this.mExpandedPanel.setupHost(this);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0036, code lost:
    
        if (r0 != 3) goto L18;
     */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            boolean r0 = r3.mAnimationPlaying
            r1 = 1
            if (r0 == 0) goto L1e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r0 = "onInterceptTouchEvent ignore,event="
            r3.append(r0)
            int r4 = r4.getAction()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            cn.nubia.projection.util.PLog.a(r3)
            return r1
        L1e:
            float r0 = r4.getRawX()
            r3.mStopX = r0
            float r0 = r4.getRawY()
            r3.mStopY = r0
            int r0 = r4.getAction()
            if (r0 == 0) goto L48
            if (r0 == r1) goto L40
            r2 = 2
            if (r0 == r2) goto L39
            r1 = 3
            if (r0 == r1) goto L40
            goto L43
        L39:
            boolean r0 = r3.n()
            if (r0 == 0) goto L43
            return r1
        L40:
            r3.a0()
        L43:
            boolean r3 = super.onInterceptTouchEvent(r4)
            return r3
        L48:
            float r0 = r4.getRawX()
            r3.mStartX = r0
            float r0 = r4.getRawY()
            r3.mStartY = r0
            float r0 = r4.getX()
            r3.mViewDownX = r0
            float r4 = r4.getY()
            r3.mViewDownY = r4
            r4 = 1065353216(0x3f800000, float:1.0)
            r3.setAlpha(r4)
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.projection.ui.ProjectionWindowView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mAnimationPlaying) {
            PLog.a("onTouchEvent ignore,event=" + motionEvent.getAction());
            return true;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            setKeepWindowOnTouch(true);
            this.mViewDownX = motionEvent.getX();
            this.mViewDownY = motionEvent.getY();
        } else if (action == 1) {
            setKeepWindowOnTouch(false);
            this.rawMoveX = motionEvent.getRawX();
            float rawY = motionEvent.getRawY();
            this.rawMoveY = rawY;
            v(this.rawMoveX - this.mViewDownX, rawY - this.mViewDownY);
        } else if (action == 2) {
            setKeepWindowOnTouch(true);
            this.rawMoveX = motionEvent.getRawX();
            float rawY2 = motionEvent.getRawY();
            this.rawMoveY = rawY2;
            X(this.rawMoveX - this.mViewDownX, rawY2 - this.mViewDownY);
        } else if (action == 3) {
            setKeepWindowOnTouch(false);
            WindowManager.LayoutParams d0 = this.mUIControl.d0();
            float f2 = d0.x;
            this.rawMoveX = f2;
            float f3 = d0.y;
            this.rawMoveY = f3;
            v(f2, f3);
        } else if (action == 4) {
            setKeepWindowOnTouch(false);
            o();
        }
        return true;
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 == 0) {
            a0();
        } else {
            removeCallbacks(this.mTranslateRunnable);
        }
    }

    public void p(boolean z) {
        if (this.mPanelExpanded) {
            this.mPanelExpanded = false;
            Q(z);
        }
    }

    public void q(PrintWriter printWriter, String[] strArr) {
        printWriter.println("Nubia ProjectionWindowView Status:");
        printWriter.println("    isSupportVirtualHandles:" + H());
        printWriter.println("    mPanelExpanded:" + this.mPanelExpanded);
        printWriter.println("    mAnimationPlaying:" + this.mAnimationPlaying);
        printWriter.println("    mKeepWindowOnTouch:" + this.mKeepWindowOnTouch);
        printWriter.println("    mOrientation:" + this.mOrientation);
        printWriter.println("    mCustomGravity:" + this.mCustomGravity);
        printWriter.println("    Alpha:" + getAlpha());
        printWriter.println("    mDelayRemoveForFullScreen:" + this.mDelayRemoveForFullScreen);
        printWriter.println("    ScreenShotView w/h=" + this.mScreenShotViewWidth + " x " + this.mScreenShotViewHeight);
        printWriter.println("    HalfScreen w/h=" + this.mHalfScreenWidth + " x " + this.mHalfScreenHeight);
        printWriter.println("    visible Rect=" + this.mVisibleLeft + "," + this.mVisibleTop + "," + this.mVisibleRight + "," + this.mVisibleBottom);
        NubiaProjectionExpandedPanel nubiaProjectionExpandedPanel = this.mExpandedPanel;
        if (nubiaProjectionExpandedPanel != null) {
            nubiaProjectionExpandedPanel.t(printWriter, strArr);
        }
        NubiaProjectionPanel nubiaProjectionPanel = this.mPanel;
        if (nubiaProjectionPanel != null) {
            nubiaProjectionPanel.e(printWriter, strArr);
        }
    }

    public void r() {
        if (this.mPanelExpanded) {
            return;
        }
        this.mPanelExpanded = true;
        setAlpha(1.0f);
        R();
    }

    public void setAnimationPlaying(boolean z) {
        this.mAnimationPlaying = z;
    }

    public void setDelayRemoveForFullScreen(boolean z) {
        PLog.a("setDelayRemoveForFullScreen " + z);
        this.mDelayRemoveForFullScreen = z;
    }

    public void setKeepWindowOnTouch(boolean z) {
        if (this.mKeepWindowOnTouch != z) {
            this.mKeepWindowOnTouch = z;
        }
    }

    public void setProjectionUIControl(ProjectionUIController projectionUIController) {
        this.mUIControl = projectionUIController;
        this.mOrientation = getContext().getDisplay().getRotation();
        getDisplayParams();
    }

    public ProjectionWindowView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPanelExpanded = false;
        this.mAnimationPlaying = false;
        this.mDelayRemoveForFullScreen = false;
        this.mFoldCallback = new FoldMgr.Callback() { // from class: cn.nubia.projection.ui.ProjectionWindowView.1
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public void onDisplayInUseStateChanged(int i22) {
                ProjectionWindowView.this.mUIControl.b0().postDelayed(new Runnable() { // from class: cn.nubia.projection.ui.ProjectionWindowView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ProjectionWindowView.this.s();
                    }
                }, 2000L);
            }
        };
        this.mRotationCallback = new AnonymousClass2();
        this.mTranslateRunnable = new Runnable() { // from class: cn.nubia.projection.ui.q
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.K();
            }
        };
        this.mCollapsePanelRunnable = new Runnable() { // from class: cn.nubia.projection.ui.r
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.L();
            }
        };
    }

    public ProjectionWindowView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        this.mPanelExpanded = false;
        this.mAnimationPlaying = false;
        this.mDelayRemoveForFullScreen = false;
        this.mFoldCallback = new FoldMgr.Callback() { // from class: cn.nubia.projection.ui.ProjectionWindowView.1
            @Override // com.zte.gameassist.common.FoldMgr.Callback
            public void onDisplayInUseStateChanged(int i22) {
                ProjectionWindowView.this.mUIControl.b0().postDelayed(new Runnable() { // from class: cn.nubia.projection.ui.ProjectionWindowView.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ProjectionWindowView.this.s();
                    }
                }, 2000L);
            }
        };
        this.mRotationCallback = new AnonymousClass2();
        this.mTranslateRunnable = new Runnable() { // from class: cn.nubia.projection.ui.q
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.K();
            }
        };
        this.mCollapsePanelRunnable = new Runnable() { // from class: cn.nubia.projection.ui.r
            @Override // java.lang.Runnable
            public final void run() {
                ProjectionWindowView.this.L();
            }
        };
    }
}
