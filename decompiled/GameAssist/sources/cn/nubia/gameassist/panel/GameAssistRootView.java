package cn.nubia.gameassist.panel;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.bright.BrightSeekbarViewController;
import cn.nubia.gameassist.common.IHostPanel;
import cn.nubia.gameassist.panel.PanelTouchHelper;
import cn.nubia.gameassist.panel.drawable.diplogen.PanelDrawable;
import cn.nubia.gameassist.search.SearchViewController;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.TraceWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class GameAssistRootView extends FrameLayout implements PanelTouchHelper.Callback, BrightSeekbarViewController.BrightViewCallback {
    private static final String TAG = "GameAssist.RootView";
    private static final boolean TOUCH_BRIGHT_ONLAY_SHOW_BRIGHT_VIEW = false;
    private final Handler mBgHandler;
    private View mBrightView;
    private boolean mBrightViewTouch;
    private IHostPanel.PanelCallback mCallback;
    private CollapseReceiver mCollapseReceiver;
    private View mHorizontalView;
    private boolean mIsHorizontal;
    private boolean mIsSlideAnimationInterceptTouch;
    private View mLeftPanel;
    private final List<Integer> mMiddleLayoutChangeList;
    private View mMiddlePanel;
    private final View.OnLayoutChangeListener mOMTLayoutChangeListener;
    private boolean mOpened;
    private PanelTouchHelper mPanelTouchHelper;
    private View mRightPanel;
    private PanelSlideAnimation mSlideAnimation;
    private View mVerticalView;
    private static final PanelDrawable[] mPanelDrawables = new PanelDrawable[4];
    private static final View NULL_VIEW = new View(ContextWrapper.getContext());

    private final class PanelSlideAnimation extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener {

        /* renamed from: c, reason: collision with root package name */
        private ValueAnimator f6768c;

        /* renamed from: h, reason: collision with root package name */
        private boolean f6769h;

        /* renamed from: i, reason: collision with root package name */
        private boolean f6770i;

        private void d(View view, float f2, int i2, int i3) {
            if (!this.f6769h) {
                if (GameAssistRootView.this.mIsHorizontal || this.f6770i) {
                    view.setTranslationX(335.0f * f2 * i2);
                    view.setTranslationY(128.0f * f2 * i3);
                } else {
                    view.setTranslationY(335.0f * f2 * i2);
                }
                view.setAlpha(1.0f - f2);
                return;
            }
            if (GameAssistRootView.this.mIsHorizontal || this.f6770i) {
                float f3 = 1.0f - f2;
                view.setTranslationX(335.0f * f3 * i2);
                view.setTranslationY(f3 * 128.0f * i3);
            } else {
                view.setTranslationY((1.0f - f2) * 335.0f * i2);
            }
            view.setAlpha(f2);
        }

        public boolean b() {
            ValueAnimator valueAnimator = this.f6768c;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        public void c(boolean z) {
            this.f6769h = z;
            this.f6770i = GameAssistRootView.this.isFoldScreen();
            ValueAnimator valueAnimator = this.f6768c;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.f6768c = ofFloat;
            ofFloat.setDuration(300L);
            this.f6768c.setRepeatCount(0);
            this.f6768c.addUpdateListener(this);
            this.f6768c.addListener(this);
            this.f6768c.start();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            GameAssistRootView.this.onSlideAnimationEnd(this.f6769h);
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            d(GameAssistRootView.this.mLeftPanel, floatValue, -1, 0);
            d(GameAssistRootView.this.mMiddlePanel, floatValue, 0, 1);
            d(GameAssistRootView.this.mRightPanel, floatValue, 1, 0);
        }

        private PanelSlideAnimation() {
        }
    }

    public GameAssistRootView(Context context) {
        super(context);
        this.mSlideAnimation = new PanelSlideAnimation();
        this.mBgHandler = new Handler(ThreadManager.c().b());
        this.mIsHorizontal = true;
        this.mMiddleLayoutChangeList = new ArrayList();
        this.mOMTLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: cn.nubia.gameassist.panel.GameAssistRootView.1

            /* renamed from: c, reason: collision with root package name */
            private final RectF f6758c = new RectF();

            /* renamed from: h, reason: collision with root package name */
            final int[] f6759h = new int[2];

            /* renamed from: i, reason: collision with root package name */
            final int[] f6760i = {0};

            /* renamed from: j, reason: collision with root package name */
            final int[] f6761j = {0};

            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
                View findViewById = GameAssistRootView.this.mMiddlePanel.findViewById(R.id.game_assist_middle_omt);
                int width = findViewById.getWidth();
                int height = findViewById.getHeight();
                findViewById.getLocationOnScreen(this.f6759h);
                int[] iArr = this.f6761j;
                int i10 = iArr[0];
                int[] iArr2 = this.f6759h;
                int i11 = iArr2[1];
                if (i10 != i11 || i10 == 0) {
                    this.f6760i[0] = iArr2[0];
                    iArr[0] = i11;
                    this.f6758c.set(iArr2[0], i11, r4 + width, i11 + height);
                    GameAssistRootView.this.mPanelTouchHelper.p(this.f6758c);
                }
            }
        };
        setId(R.id.game_assist_root);
        initDrawables();
        this.mCollapseReceiver = new CollapseReceiver(GameAssistWindowManager.O(getContext()));
        this.mPanelTouchHelper = new PanelTouchHelper(getContext(), this);
        ((BrightSeekbarViewController) GameAssistWindowManager.O(getContext()).T(BrightSeekbarViewController.class)).X(this);
        resetSettingCCStatus();
    }

    private void initDrawables() {
        PanelDrawable[] panelDrawableArr = mPanelDrawables;
        PanelDrawable panelDrawable = panelDrawableArr[0];
        if (panelDrawable == null) {
            panelDrawableArr[0] = new PanelDrawable(getContext(), 0);
            panelDrawableArr[1] = new PanelDrawable(getContext(), 1);
            panelDrawableArr[2] = new PanelDrawable(getContext(), 2);
            panelDrawableArr[3] = new PanelDrawable(getContext(), 3);
            return;
        }
        panelDrawable.h();
        panelDrawableArr[1].h();
        panelDrawableArr[2].h();
        panelDrawableArr[3].h();
        GaLog.a(TAG, "PanelDrawable PANEL_BASE_RIGHT=" + PanelDrawable.t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFoldScreen() {
        return ZteFeature.isSupportFoldBig() && FoldMgr.c().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setCCStatus$0(boolean z, AbsGameAssistToken.GameAssistControllerWrapper gameAssistControllerWrapper) {
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, "cc_status");
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_VALUE, String.valueOf(z ? 1 : 0));
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_TYPE, "system");
        gameAssistControllerWrapper.invake("set_settings", bundle, null);
    }

    private void onOrientationChanged() {
        if (getChildCount() == 0) {
            return;
        }
        boolean isFoldScreen = isFoldScreen();
        View view = this.mHorizontalView;
        int i2 = 8;
        if (view != null) {
            view.setVisibility((this.mIsHorizontal || isFoldScreen) ? 0 : 8);
        }
        View view2 = this.mVerticalView;
        if (view2 != null) {
            if (!this.mIsHorizontal && !isFoldScreen) {
                i2 = 0;
            }
            view2.setVisibility(i2);
        }
        View view3 = (this.mIsHorizontal || isFoldScreen()) ? this.mHorizontalView : this.mVerticalView;
        View findViewById = view3.findViewById(R.id.game_assist_left_panel);
        this.mLeftPanel = findViewById;
        findViewById.setVisibility(4);
        View findViewById2 = view3.findViewById(R.id.game_assist_middle_panel);
        this.mMiddlePanel = findViewById2;
        findViewById2.setVisibility(4);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mMiddlePanel.getLayoutParams();
        if (!this.mIsHorizontal) {
            if (SearchViewController.v) {
                layoutParams.setMargins(getContext().getResources().getDimensionPixelOffset(R.dimen.game_middlePanel_AISearch_ver_marginLeft), getContext().getResources().getDimensionPixelOffset(R.dimen.game_middlePanel_AISearch_ver_marginTop), 0, 0);
            } else if (ZteFeature.isTabletProduct() && ZteFeature.isSprdVendor()) {
                layoutParams.setMargins(getContext().getResources().getDimensionPixelOffset(R.dimen.game_middlePanel_Search_zhanxun_pad_ver_marginLeft), getContext().getResources().getDimensionPixelOffset(R.dimen.game_middlePanel_Search_zhanxun_pad_ver_marginTop), 0, 0);
            } else {
                layoutParams.setMargins(getContext().getResources().getDimensionPixelOffset(R.dimen.game_middlePanel_Search_ver_marginLeft), getContext().getResources().getDimensionPixelOffset(R.dimen.game_middlePanel_Search_ver_marginTop), 0, 0);
            }
        }
        this.mMiddlePanel.requestLayout();
        View findViewById3 = view3.findViewById(R.id.game_assist_right_panel);
        this.mRightPanel = findViewById3;
        findViewById3.setVisibility(4);
        View view4 = this.mHorizontalView;
        if (view4 != null) {
            View findViewById4 = view4.findViewById(R.id.game_assist_left_panel);
            PanelDrawable[] panelDrawableArr = mPanelDrawables;
            findViewById4.setBackground(panelDrawableArr[0]);
            this.mHorizontalView.findViewById(R.id.game_assist_right_panel).setBackground(panelDrawableArr[2]);
        }
        View view5 = this.mVerticalView;
        if (view5 != null) {
            View findViewById5 = view5.findViewById(R.id.game_assist_left_panel);
            PanelDrawable[] panelDrawableArr2 = mPanelDrawables;
            findViewById5.setBackground(panelDrawableArr2[1]);
            this.mVerticalView.findViewById(R.id.game_assist_right_panel).setBackground(panelDrawableArr2[3]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSlideAnimationEnd(final boolean z) {
        IHostPanel.PanelCallback panelCallback = this.mCallback;
        if (panelCallback != null) {
            panelCallback.b(z);
        }
        this.mLeftPanel.setAlpha(1.0f);
        this.mMiddlePanel.setAlpha(1.0f);
        this.mRightPanel.setAlpha(1.0f);
        if (!z) {
            this.mLeftPanel.setVisibility(4);
            this.mMiddlePanel.setVisibility(4);
            this.mRightPanel.setVisibility(4);
            this.mPanelTouchHelper.f();
            this.mBgHandler.post(new Runnable() { // from class: cn.nubia.gameassist.panel.GameAssistRootView.3
                @Override // java.lang.Runnable
                public void run() {
                    GameAssistRootView.this.setCCStatus(false);
                }
            });
        }
        this.mBgHandler.post(new Runnable() { // from class: cn.nubia.gameassist.panel.GameAssistRootView.4
            @Override // java.lang.Runnable
            public void run() {
                GameAssistRootView.this.setCCStatus(z);
                if (z) {
                    NubiaTrackManager.p().j();
                }
            }
        });
    }

    private void resetSettingCCStatus() {
        GaLog.a(TAG, "-------->resetSettingCCStatus() set setting status 0");
        setCCStatus(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCCStatus(final boolean z) {
        try {
            Settings.System.putInt(getContext().getContentResolver(), "cc_status", z ? 1 : 0);
        } catch (Exception unused) {
            SystemMgr.y(getContext()).x().ifPresent(new Consumer() { // from class: cn.nubia.gameassist.panel.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GameAssistRootView.lambda$setCCStatus$0(z, (AbsGameAssistToken.GameAssistControllerWrapper) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSlideAnimation(boolean z) {
        this.mSlideAnimation.c(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateOneMoreThingRegion() {
        View view;
        if (!SystemMgr.H() || (view = this.mMiddlePanel) == null || this.mMiddleLayoutChangeList.contains(Integer.valueOf(view.hashCode()))) {
            return;
        }
        this.mMiddlePanel.addOnLayoutChangeListener(this.mOMTLayoutChangeListener);
        this.mMiddleLayoutChangeList.add(Integer.valueOf(this.mMiddlePanel.hashCode()));
    }

    public void closePanel(String str) {
        closePanel(str, true);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.mPanelTouchHelper.d(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean z = motionEvent.getAction() == 1 || motionEvent.getAction() == 3;
        if (z) {
            try {
                if (this.mIsSlideAnimationInterceptTouch) {
                    this.mIsSlideAnimationInterceptTouch = false;
                    return true;
                }
            } catch (Exception e2) {
                this.mIsSlideAnimationInterceptTouch = false;
                GaLog.a(TAG, "dispatchTouchEvent " + e2.getMessage());
            }
        }
        if (!this.mSlideAnimation.b() || this.mIsSlideAnimationInterceptTouch) {
            if (this.mIsSlideAnimationInterceptTouch) {
                return true;
            }
            if (z) {
                this.mBrightViewTouch = false;
            }
            return super.dispatchTouchEvent(motionEvent);
        }
        this.mIsSlideAnimationInterceptTouch = true;
        if (motionEvent.getAction() == 2) {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            super.dispatchTouchEvent(obtain);
            obtain.recycle();
        }
        GaLog.a(TAG, "InterceptTouch, because mSlideAnimation is Running ");
        return true;
    }

    public <T extends View> T getLeftPanel() {
        return (T) this.mLeftPanel;
    }

    public <T extends View> T getMiddlePanel() {
        return (T) this.mMiddlePanel;
    }

    public <T extends View> T getRightPanel() {
        return (T) this.mRightPanel;
    }

    @Override // cn.nubia.gameassist.panel.PanelTouchHelper.Callback
    public void hidePanel() {
        GameAssistWindowManager.O(getContext()).g0("hidePanel");
    }

    public void inflateContent() {
        if (getChildCount() == 0) {
            InflaterHelper.f(R.layout.game_assist_root_layout, this);
            GaLog.a(TAG, "inflate rootview ==> " + Utils.o(InflaterHelper.b().getResources(), R.layout.game_assist_root_layout));
            this.mHorizontalView = super.findViewById(R.id.game_assist_root_horizontal);
            this.mVerticalView = super.findViewById(R.id.game_assist_root_vertical);
            initDrawables();
            onOrientationChanged();
        }
    }

    public boolean isOpened() {
        return this.mOpened;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateOneMoreThingRegion();
    }

    public void onDeviceScreenChanged() {
        if (this.mPanelTouchHelper != null) {
            updateOneMoreThingRegion();
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.mPanelTouchHelper.k(motionEvent);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if ((i2 > i3) != this.mIsHorizontal) {
            GameAssistWindowManager.O(getContext()).g0("SizeChanged");
        }
        if (ZteFeature.isSupportFoldBig()) {
            this.mPanelTouchHelper.q();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mPanelTouchHelper.m(motionEvent);
    }

    @Override // cn.nubia.gameassist.bright.BrightSeekbarViewController.BrightViewCallback
    public void onTrackingTouch(View view, boolean z) {
        if (!z) {
            view = null;
        }
        this.mBrightView = view;
        this.mBrightViewTouch = z;
        invalidate();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        if (i2 != 0) {
            this.mCollapseReceiver.b(getContext());
        } else {
            this.mCollapseReceiver.a(getContext());
        }
    }

    public void openPanel(String str) {
        openPanel(str, true);
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        super.removeAllViews();
        View view = NULL_VIEW;
        this.mVerticalView = view;
        this.mHorizontalView = view;
        this.mRightPanel = view;
        this.mMiddlePanel = view;
        this.mLeftPanel = view;
        PanelDrawable[] panelDrawableArr = mPanelDrawables;
        panelDrawableArr[1] = null;
        panelDrawableArr[0] = null;
        panelDrawableArr[3] = null;
        panelDrawableArr[2] = null;
    }

    public void resetMiddleLayoutChangeList() {
        this.mMiddleLayoutChangeList.clear();
    }

    public void setCallback(IHostPanel.PanelCallback panelCallback) {
        this.mCallback = panelCallback;
    }

    public void setOrientation(boolean z) {
        this.mIsHorizontal = z;
        onOrientationChanged();
    }

    @Override // android.view.View
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("-");
        View view = this.mLeftPanel;
        sb.append(view != null ? view.getAlpha() : 0.0f);
        sb.append("-");
        sb.append(this.mSlideAnimation.f6769h);
        return sb.toString();
    }

    @Override // cn.nubia.gameassist.panel.PanelTouchHelper.Callback
    public boolean touchCaptureView(float f2, float f3) {
        getLocationOnScreen(new int[2]);
        float f4 = f2 + r1[0];
        float f5 = f3 + r1[1];
        int[] iArr = new int[2];
        this.mLeftPanel.getLocationOnScreen(iArr);
        if (f4 > iArr[0] && f4 < r4 + this.mLeftPanel.getWidth()) {
            if (f5 > iArr[1] && f5 < r4 + this.mLeftPanel.getHeight()) {
                return true;
            }
        }
        this.mRightPanel.getLocationOnScreen(iArr);
        if (f4 > iArr[0] && f4 < r4 + this.mRightPanel.getWidth()) {
            if (f5 > iArr[1] && f5 < r1 + this.mRightPanel.getHeight()) {
                return true;
            }
        }
        int[] iArr2 = new int[2];
        this.mMiddlePanel.findViewById(R.id.game_assist_middle_omt).getLocationOnScreen(iArr2);
        if (f4 > iArr2[0] && f4 < r1 + r6.getWidth()) {
            if (f5 > iArr2[1] && f5 < r7 + r6.getHeight()) {
                return true;
            }
        }
        return false;
    }

    @Override // cn.nubia.gameassist.panel.PanelTouchHelper.Callback
    public boolean touchInChildView(float f2, float f3) {
        return false;
    }

    public void closePanel(String str, boolean z) {
        if (this.mOpened) {
            TraceWrapper.traceBegin(8L, "GameAssistRootView_closePanel");
            IHostPanel.PanelCallback panelCallback = this.mCallback;
            if (panelCallback != null) {
                panelCallback.a();
            }
            this.mOpened = false;
            if (z) {
                this.mSlideAnimation.c(false);
            } else {
                onSlideAnimationEnd(false);
            }
            TraceWrapper.traceEnd(8L);
        }
    }

    public void openPanel(String str, boolean z) {
        if (this.mOpened) {
            return;
        }
        IHostPanel.PanelCallback panelCallback = this.mCallback;
        if (panelCallback != null) {
            panelCallback.c();
        }
        this.mOpened = true;
        this.mLeftPanel.setVisibility(0);
        this.mMiddlePanel.setVisibility(0);
        this.mRightPanel.setVisibility(0);
        if (z) {
            final ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: cn.nubia.gameassist.panel.GameAssistRootView.2
                private void a() {
                    TraceWrapper.traceBegin(8L, "GameAssistRootView_loadDrawable");
                    Theme n2 = ThemeController.m().n();
                    if (n2 != null) {
                        n2.j(GameAssistRootView.this.getContext());
                    }
                    TraceWrapper.traceEnd(8L);
                }

                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    TraceWrapper.traceBegin(8L, "GameAssistRootView_PreDraw");
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this);
                    } else {
                        GameAssistRootView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                    if (GameAssistRootView.this.mOpened) {
                        a();
                        GameAssistRootView.this.updateOneMoreThingRegion();
                        GameAssistRootView.this.startSlideAnimation(true);
                    }
                    TraceWrapper.traceEnd(8L);
                    return true;
                }
            });
        }
    }
}
