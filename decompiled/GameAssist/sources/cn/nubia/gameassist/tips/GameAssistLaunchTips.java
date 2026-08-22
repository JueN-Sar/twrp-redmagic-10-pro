package cn.nubia.gameassist.tips;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.clean.CleanAnimationController;
import cn.nubia.gameassist.tips.launch.TipsBackground;
import cn.nubia.gameassist.tips.launch.TipsBase;
import cn.nubia.gameassist.tips.launch.TipsCube;
import cn.nubia.gameassist.tips.launch.TipsCubeText;
import cn.nubia.gameassist.tips.launch.TipsIce;
import cn.nubia.gameassist.tips.launch.TipsMessage;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.List;

/* loaded from: classes.dex */
public class GameAssistLaunchTips extends View {
    public static final String TAG = "LaunchTips";
    private final int ONE_FRAME_TIME;
    private boolean mAddToWindow;
    protected String mCurApp;
    private final Handler mDecoderHandler;
    private DisplayManager mDisplayManager;
    private final DisplayMetrics mDisplayMetrics;
    private final Runnable mDoneCallback;
    private String mGamePackage;
    private final Handler mHandler;
    private final Runnable mHideRun;
    private boolean mIsShowTips;
    private String mLaunchWay;
    private final Handler mMainHandler;
    private final int mMaxPixels;
    private final int mMinPixels;
    private int mRotation;
    private final Runnable mShowRun;
    private int mSizeH;
    private int mSizeW;
    private long mStartTime;
    private final TipsBase[] mTipsItems;
    private IBinder mToken;
    public final Point mTranslate;
    private final WindowManager mWindowManager;

    public GameAssistLaunchTips(Context context, Handler handler, Handler handler2, String str, String str2, List list, Runnable runnable, String str3) {
        super(context);
        this.ONE_FRAME_TIME = 40;
        this.mStartTime = -1L;
        this.mDisplayMetrics = new DisplayMetrics();
        this.mMaxPixels = 2400;
        this.mMinPixels = 1080;
        this.mTipsItems = new TipsBase[5];
        this.mShowRun = new Runnable() { // from class: cn.nubia.gameassist.tips.GameAssistLaunchTips.1
            @Override // java.lang.Runnable
            public void run() {
                synchronized (GameAssistLaunchTips.this) {
                    if (!GameAssistLaunchTips.this.mIsShowTips) {
                        GameAssistLaunchTips.this.mIsShowTips = true;
                        try {
                            ContextWrapper.updateDisplay(GameAssistLaunchTips.this.getContext());
                            WindowManager windowManager = GameAssistLaunchTips.this.mWindowManager;
                            GameAssistLaunchTips gameAssistLaunchTips = GameAssistLaunchTips.this;
                            windowManager.addView(gameAssistLaunchTips, gameAssistLaunchTips.getWindowLayoutParams());
                            GameAssistLaunchTips.this.mAddToWindow = true;
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            GameAssistLaunchTips.this.s();
                        }
                        GaLog.e("LaunchTips", "showTips");
                    }
                }
            }
        };
        this.mHideRun = new Runnable() { // from class: cn.nubia.gameassist.tips.GameAssistLaunchTips.2
            @Override // java.lang.Runnable
            public void run() {
                synchronized (GameAssistLaunchTips.this) {
                    try {
                        if (GameAssistLaunchTips.this.mIsShowTips) {
                            GameAssistLaunchTips.this.mIsShowTips = false;
                            if (GameAssistLaunchTips.this.mAddToWindow) {
                                GameAssistLaunchTips.this.mWindowManager.removeView(GameAssistLaunchTips.this);
                                GameAssistLaunchTips.this.mAddToWindow = false;
                            }
                            GameAssistLaunchTips.this.q();
                            GaLog.e("LaunchTips", "hideTips");
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
        this.mTranslate = new Point();
        this.mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
        this.mDoneCallback = runnable;
        this.mGamePackage = str;
        this.mToken = new Binder();
        this.mHandler = handler;
        this.mDecoderHandler = handler2;
        this.mMainHandler = new Handler(Looper.getMainLooper());
        this.mLaunchWay = str3;
        RecycleWatch.j(this, 1);
        setId(R.id.game_assist_launch_tips);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WindowManager.LayoutParams getWindowLayoutParams() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1);
        layoutParams.type = 2027;
        new Binder();
        layoutParams.flags = 75564824;
        layoutParams.gravity = 51;
        layoutParams.format = -3;
        layoutParams.windowAnimations = 0;
        layoutParams.token = this.mToken;
        layoutParams.x = 0;
        layoutParams.y = 0;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        layoutParams.setTitle("GameAssistLaunchTips");
        layoutParams.layoutInDisplayCutoutMode = 3;
        WindowManagerWrapper.LayoutParams.addHidePrivateTrustedOverlayFlags(layoutParams);
        return layoutParams;
    }

    private boolean j() {
        return Settings.Global.getInt(getContext().getContentResolver(), "db_game_start_animation", 1) == 1;
    }

    public static GameAssistLaunchTips k(Context context, Handler handler, Handler handler2, String str, String str2, List list, Runnable runnable, String str3) {
        return new GameAssistLaunchTips(context, handler, handler2, str, str2, list, runnable, str3).r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void o(Runnable runnable) {
        if (runnable != null) {
            runnable.run();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        synchronized (this) {
            int i2 = 0;
            while (true) {
                try {
                    TipsBase[] tipsBaseArr = this.mTipsItems;
                    if (i2 < tipsBaseArr.length) {
                        TipsBase tipsBase = tipsBaseArr[i2];
                        if (tipsBase != null) {
                            tipsBase.k();
                        }
                        this.mTipsItems[i2] = null;
                        i2++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private GameAssistLaunchTips r() {
        this.mHandler.removeCallbacks(this.mHideRun);
        this.mHandler.removeCallbacks(this.mShowRun);
        this.mCurApp = Utils.j();
        StringBuilder sb = new StringBuilder();
        sb.append("---showTips--- ");
        sb.append(this.mCurApp);
        sb.append(", ");
        sb.append(SystemMgr.f16556q != null ? SystemMgr.f16556q.mPid : 9999);
        GaLog.e("LaunchTips", sb.toString());
        if (j() || p()) {
            this.mHandler.postDelayed(this.mShowRun, 10L);
        }
        SystemMgr.y(getContext()).e();
        CleanAnimationController.d(getContext()).h();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        m();
        final Runnable runnable = this.mDoneCallback;
        if (runnable != null) {
            this.mMainHandler.post(new Runnable() { // from class: cn.nubia.gameassist.tips.d
                @Override // java.lang.Runnable
                public final void run() {
                    GameAssistLaunchTips.o(runnable);
                }
            });
        }
    }

    public int getFixHeight() {
        return getHeight() > getWidth() ? 2400 : 1080;
    }

    public int getFixWidth() {
        return getHeight() > getWidth() ? 1080 : 2400;
    }

    public String getGamePackage() {
        return this.mGamePackage;
    }

    public int getMaxPixels() {
        return 2400;
    }

    public int getMinPixels() {
        return 1080;
    }

    public Point getSize() {
        return new Point(this.mSizeW, this.mSizeH);
    }

    public void l(Runnable runnable) {
        this.mDecoderHandler.post(runnable);
    }

    public GameAssistLaunchTips m() {
        this.mHandler.removeCallbacks(this.mHideRun);
        this.mHandler.removeCallbacks(this.mShowRun);
        this.mHandler.post(this.mHideRun);
        return this;
    }

    public boolean n() {
        int i2 = this.mRotation;
        return i2 == 1 || i2 == 3;
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Display display = this.mDisplayManager.getDisplay(0);
        this.mRotation = display.getRotation();
        GaLog.e("LaunchTips", "onAttachedToWindow: " + this.mRotation);
        display.getRealMetrics(this.mDisplayMetrics);
        this.mTipsItems[0] = new TipsBackground(this, getContext());
        this.mTipsItems[1] = new TipsCube(this, getContext());
        this.mTipsItems[2] = new TipsIce(this, getContext());
        this.mTipsItems[3] = new TipsCubeText(this, getContext());
        this.mTipsItems[4] = new TipsMessage(this, getContext());
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int width;
        int height;
        int i2;
        int i3;
        TipsBase[] tipsBaseArr;
        if (this.mStartTime == Long.MAX_VALUE) {
            return;
        }
        if (getHeight() > getWidth()) {
            width = (getWidth() - 1080) / 2;
            height = (getHeight() - 2400) / 2;
        } else {
            width = (getWidth() - 2400) / 2;
            height = (getHeight() - 1080) / 2;
        }
        int save = canvas.save();
        this.mTranslate.set(width, height);
        canvas.translate(width, height);
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mStartTime == -1) {
            this.mStartTime = 40 + currentTimeMillis;
        }
        int i4 = ((int) (currentTimeMillis - this.mStartTime)) / 40;
        if (this.mIsShowTips && i4 >= 0 && i4 < 125) {
            float f2 = ((r1 % 40) * 1.0f) / 40.0f;
            TipsBase[] tipsBaseArr2 = this.mTipsItems;
            int length = tipsBaseArr2.length;
            int i5 = 0;
            while (i5 < length) {
                TipsBase tipsBase = tipsBaseArr2[i5];
                if (tipsBase != null) {
                    i2 = i5;
                    i3 = length;
                    tipsBaseArr = tipsBaseArr2;
                    tipsBase.a(canvas, 0L, 0L, i4, f2);
                } else {
                    i2 = i5;
                    i3 = length;
                    tipsBaseArr = tipsBaseArr2;
                }
                i5 = i2 + 1;
                length = i3;
                tipsBaseArr2 = tipsBaseArr;
            }
        }
        canvas.restoreToCount(save);
        if (i4 < 125) {
            postInvalidate();
        } else {
            s();
            this.mStartTime = Long.MAX_VALUE;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (this.mSizeW != i2 || i2 == 0 || this.mSizeH != i3 || i3 == 0) {
            this.mSizeW = i2;
            this.mSizeH = i3;
            int i6 = i3 > i2 ? 0 : 1;
            if (i6 != this.mRotation) {
                this.mRotation = i6;
                for (TipsBase tipsBase : this.mTipsItems) {
                    if (tipsBase != null) {
                        tipsBase.j();
                    }
                }
            }
        }
    }

    public boolean p() {
        if (TextUtils.isEmpty(this.mLaunchWay)) {
            return false;
        }
        return "cube".equals(this.mLaunchWay);
    }

    @Override // android.view.View
    public boolean post(Runnable runnable) {
        return this.mHandler.post(runnable);
    }
}
