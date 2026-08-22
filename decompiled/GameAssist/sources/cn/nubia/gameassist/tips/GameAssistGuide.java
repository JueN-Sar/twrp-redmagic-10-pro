package cn.nubia.gameassist.tips;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemProperties;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.tips.guide.GuideVideoView;
import cn.nubia.gameassist.utils.RecycleWatch;
import com.zte.gameassist.common.DensityHelper;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class GameAssistGuide extends FrameLayout implements GuideVideoView.Callback, WindowInsetsController.OnControllableInsetsChangedListener {
    private static final int BUTTON_BOTTOM_MARGIN_H_DP = 27;
    private static final int BUTTON_BOTTOM_MARGIN_V_DP = 100;
    private static final int BUTTON_MIN_WIDTH_DP = 84;
    private static final float BUTTON_TEXT_SIZE = 14.0f;
    private static final int BUTTON_WIDTH_DP = 40;
    public static final String TAG = "LaunchTips";
    private static final boolean TEST_ROTATION = SystemProperties.getBoolean("launch_guide_test_rotation", false);
    private Context mContext;
    private final DisplayManager mDisplayManager;
    private final Runnable mDoneCallback;
    private String mErrorStr;
    private Handler mHandler;
    private boolean mIsAddView;
    private boolean mIsHorizontal;
    private boolean mIsPlayDone;
    private boolean mIsShowGuide;
    private Button mNextButton;
    private BroadcastReceiver mReceiver;
    private int mRotation;
    Runnable mShowGuideRun;
    private int mSizeH;
    private int mSizeW;
    private IBinder mToken;
    private GuideVideoView mVideoView;
    private final WindowManager.LayoutParams mWindowLayoutParams;
    private final WindowManager mWindowManager;

    private GameAssistGuide(Context context, Runnable runnable) {
        super(context);
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mReceiver = new BroadcastReceiver() { // from class: cn.nubia.gameassist.tips.GameAssistGuide.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                String action = intent.getAction();
                action.hashCode();
                if (action.equals("android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                    GameAssistGuide.this.w(false);
                }
            }
        };
        this.mShowGuideRun = new Runnable() { // from class: cn.nubia.gameassist.tips.GameAssistGuide.4
            @Override // java.lang.Runnable
            public void run() {
                synchronized (this) {
                    GameAssistGuide.this.mHandler.removeCallbacks(GameAssistGuide.this.mShowGuideRun);
                    if (!GameAssistGuide.this.mIsShowGuide) {
                        GameAssistGuide.this.mIsShowGuide = true;
                        try {
                            WindowManager windowManager = GameAssistGuide.this.mWindowManager;
                            GameAssistGuide gameAssistGuide = GameAssistGuide.this;
                            windowManager.addView(gameAssistGuide, gameAssistGuide.mWindowLayoutParams);
                            GameAssistGuide.this.mIsAddView = true;
                            GameAssistGuide.this.F();
                            if (GameAssistGuide.TEST_ROTATION) {
                                GameAssistGuide.this.C();
                            }
                        } catch (Exception e2) {
                            GaLog.k("LaunchTips", "showGuide Exception e=" + e2.getMessage());
                            GameAssistGuide.this.w(true);
                        }
                        GaLog.e("LaunchTips", "showGuide");
                    }
                }
            }
        };
        this.mContext = context;
        this.mToken = new Binder();
        this.mDoneCallback = runnable;
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mWindowLayoutParams = getWindowLayoutParams();
        RecycleWatch.i(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(boolean z) {
        Runnable runnable;
        synchronized (this) {
            try {
                this.mHandler.removeCallbacks(this.mShowGuideRun);
                if (this.mIsShowGuide) {
                    this.mIsShowGuide = false;
                    if (z && (runnable = this.mDoneCallback) != null && runnable != null) {
                        runnable.run();
                    }
                    if (this.mIsAddView) {
                        this.mWindowManager.removeView(this);
                        this.mIsAddView = false;
                    }
                    GaLog.e("LaunchTips", "hideGuide");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C() {
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.tips.GameAssistGuide.3

            /* renamed from: c, reason: collision with root package name */
            int f7513c;

            @Override // java.lang.Runnable
            public void run() {
                GameAssistGuide.this.mHandler.removeCallbacks(this);
                if (GameAssistGuide.this.mIsShowGuide) {
                    int i2 = this.f7513c + 1;
                    this.f7513c = i2;
                    if (i2 % 2 == 0) {
                        GameAssistGuide.this.mWindowLayoutParams.screenOrientation = 1;
                    } else {
                        GameAssistGuide.this.mWindowLayoutParams.screenOrientation = 0;
                    }
                    WindowManager windowManager = GameAssistGuide.this.mWindowManager;
                    GameAssistGuide gameAssistGuide = GameAssistGuide.this;
                    windowManager.updateViewLayout(gameAssistGuide, gameAssistGuide.mWindowLayoutParams);
                    GameAssistGuide.this.mHandler.postDelayed(this, 3000L);
                }
            }
        }, 5000L);
    }

    private void D(boolean z) {
        FrameLayout.LayoutParams t = t(z);
        Button button = this.mNextButton;
        if (button != null) {
            button.setLayoutParams(t);
            return;
        }
        Button button2 = new Button(getContext());
        this.mNextButton = button2;
        button2.setId(R.id.guide_next_btn);
        this.mNextButton.setMinWidth((int) s(84.0f));
        this.mNextButton.setPadding(0, 0, 0, 5);
        q(t);
    }

    private void E(boolean z) {
        FrameLayout.LayoutParams u = u(z);
        GuideVideoView guideVideoView = this.mVideoView;
        if (guideVideoView == null) {
            this.mVideoView = new GuideVideoView(this.mContext, this);
            FrameLayout frameLayout = new FrameLayout(this.mContext);
            addView(frameLayout, u);
            frameLayout.addView(this.mVideoView);
            return;
        }
        FrameLayout frameLayout2 = (FrameLayout) guideVideoView.getParent();
        if (frameLayout2 != null) {
            frameLayout2.setLayoutParams(u);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F() {
        this.mIsHorizontal = y();
        DensityHelper.d(getContext());
        GaLog.e("LaunchTips", "updateView mIsHorizontal=" + this.mIsHorizontal + " mRotation=" + this.mRotation);
        setBackgroundColor(-1442840576);
        E(this.mIsHorizontal);
        D(this.mIsHorizontal);
    }

    private WindowManager.LayoutParams getWindowLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1);
        layoutParams.type = 2008;
        new Binder();
        layoutParams.flags = 75826944;
        layoutParams.gravity = 51;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
        layoutParams.token = this.mToken;
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.layoutInDisplayCutoutMode = 3;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        layoutParams.setTitle("GameAssistGuide");
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    private void q(FrameLayout.LayoutParams layoutParams) {
        this.mNextButton.setBackground(new Drawable() { // from class: cn.nubia.gameassist.tips.GameAssistGuide.2

            /* renamed from: a, reason: collision with root package name */
            Paint f7511a;

            private void a() {
                if (this.f7511a == null) {
                    Paint paint = new Paint();
                    this.f7511a = paint;
                    paint.setAntiAlias(true);
                    this.f7511a.setStyle(Paint.Style.FILL);
                    this.f7511a.setColor(-582728636);
                }
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                a();
                float s2 = GameAssistGuide.this.s(5.0f);
                Rect bounds = getBounds();
                float height = (bounds.height() - (s2 * 2.0f)) / 2.0f;
                canvas.drawRoundRect(0.0f, s2, bounds.width(), bounds.height() - s2, height, height, this.f7511a);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int i2) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter colorFilter) {
            }
        });
        this.mNextButton.setTextColor(ColorStateList.valueOf(-1));
        this.mNextButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gameassist.tips.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameAssistGuide.this.z(view);
            }
        });
        this.mNextButton.setTextSize(2, BUTTON_TEXT_SIZE);
        addView(this.mNextButton, layoutParams);
    }

    public static GameAssistGuide r(Context context, Runnable runnable) {
        if (!ActivityManager.isUserAMonkey()) {
            return new GameAssistGuide(context, runnable).B();
        }
        runnable.run();
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float s(float f2) {
        return TypedValue.applyDimension(1, f2, this.mContext.getResources().getDisplayMetrics());
    }

    private FrameLayout.LayoutParams t(boolean z) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, (int) s(40.0f));
        layoutParams.gravity = 81;
        layoutParams.bottomMargin = (int) s(z ? 27.0f : 100.0f);
        return layoutParams;
    }

    private FrameLayout.LayoutParams u(boolean z) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams((int) (s(z ? 1350.0f : 1080.0f) / 3.0f), (int) (s(z ? 608.0f : 485.0f) / 3.0f));
        layoutParams.gravity = 81;
        boolean z2 = ZteFeature.isTabletProduct() || x();
        layoutParams.bottomMargin = this.mContext.getResources().getDimensionPixelSize(z ? z2 ? R.dimen.game_assist_guide_margin_pad_hbottom : R.dimen.game_assist_guide_margin_hbottom : z2 ? R.dimen.game_assist_guide_margin_pad_vbottom : R.dimen.game_assist_guide_margin_vbottom);
        if (z2) {
            layoutParams.width = (int) (layoutParams.width * 1.35f);
            layoutParams.height = (int) (layoutParams.height * 1.35f);
            if (getWidth() > 0 && layoutParams.width > getWidth() - s(30.0f)) {
                float width = getWidth() - s(30.0f);
                int i2 = layoutParams.width;
                float f2 = width / i2;
                layoutParams.width = (int) (i2 * f2);
                layoutParams.height = (int) (layoutParams.height * f2);
            }
            if (getHeight() > 0) {
                layoutParams.bottomMargin = (getHeight() - layoutParams.height) / 2;
            }
        }
        return layoutParams;
    }

    private boolean x() {
        return FoldMgr.f() && FoldMgr.c().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(View view) {
        if (this.mIsPlayDone) {
            w(true);
        } else {
            this.mVideoView.g();
            this.mNextButton.invalidate();
        }
    }

    public GameAssistGuide B() {
        this.mHandler.postDelayed(this.mShowGuideRun, 1000L);
        return this;
    }

    @Override // cn.nubia.gameassist.tips.guide.GuideVideoView.Callback
    public void a() {
        this.mIsPlayDone = false;
        Button button = this.mNextButton;
        if (button != null) {
            button.setText(getContext().getText(R.string.nubia_game_assist_guide_pass));
        }
    }

    @Override // cn.nubia.gameassist.tips.guide.GuideVideoView.Callback
    public void b() {
        this.mIsPlayDone = true;
        Button button = this.mNextButton;
        if (button != null) {
            button.setText(getContext().getText(R.string.nubia_game_assist_guide_done));
        }
    }

    @Override // cn.nubia.gameassist.tips.guide.GuideVideoView.Callback
    public void c() {
        Button button = this.mNextButton;
        if (button != null) {
            button.setText(getContext().getText(R.string.nubia_game_assist_guide_continue));
        }
    }

    public String getErrorMsg() {
        return this.mErrorStr;
    }

    public Point getSize() {
        return new Point(this.mSizeW, this.mSizeH);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContext.registerReceiver(this.mReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"), 2);
        WindowInsetsController windowInsetsController = getWindowInsetsController();
        windowInsetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
        windowInsetsController.addOnControllableInsetsChangedListener(this);
    }

    @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
    public void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i2) {
        if ((WindowInsets.Type.navigationBars() & i2) != 0) {
            windowInsetsController.hide(WindowInsets.Type.navigationBars());
        }
        if ((WindowInsets.Type.statusBars() & i2) != 0) {
            windowInsetsController.hide(WindowInsets.Type.statusBars());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mContext.unregisterReceiver(this.mReceiver);
        getWindowInsetsController().removeOnControllableInsetsChangedListener(this);
    }

    @Override // cn.nubia.gameassist.tips.guide.GuideVideoView.Callback
    public void onError(int i2, int i3) {
        this.mErrorStr = "what=" + i2 + ", extra" + i3;
        w(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.mSizeW != i2 || i2 == 0 || this.mSizeH != i3 || i3 == 0) {
            boolean z = FoldMgr.f() && !(this.mSizeW == i2 && this.mSizeH == i3);
            this.mSizeW = i2;
            this.mSizeH = i3;
            int i6 = i3 <= i2 ? 1 : 0;
            if (i6 != this.mRotation || z) {
                this.mRotation = i6;
                this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.tips.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        GameAssistGuide.this.F();
                    }
                }, 100L);
            }
        }
    }

    public boolean v() {
        return this.mErrorStr != null;
    }

    public GameAssistGuide w(final boolean z) {
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.post(new Runnable() { // from class: cn.nubia.gameassist.tips.a
                @Override // java.lang.Runnable
                public final void run() {
                    GameAssistGuide.this.A(z);
                }
            });
        }
        return this;
    }

    public boolean y() {
        int i2 = this.mRotation;
        return i2 == 1 || i2 == 3;
    }
}
