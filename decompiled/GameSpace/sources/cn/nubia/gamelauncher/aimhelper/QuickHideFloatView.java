package cn.nubia.gamelauncher.aimhelper;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.R;
import com.android.inputEventTool.ChoreographerToolsWrapper;
import com.android.inputEventTool.InputChannelWrapper;
import com.android.inputEventTool.MonitorTouch;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class QuickHideFloatView extends MonitorTouch {
    private static final String TAG = "QuickHideFloatView";
    private Context context;
    private View.OnClickListener l;
    float lastX;
    float lastY;
    private GameHelperController mGameHelperController;
    Handler mHandler;
    int oldOffsetX;
    int oldOffsetY;
    WindowManager.LayoutParams params;
    private int uid;
    private View view;
    private WindowManager wm;
    private int POSITION_TOP_OFFSET = 150;
    private int POSITION_RIGHT_OFFSET = HighLightsUtils.RESET_DELAY_TIME;
    int tag = 0;
    boolean isNotClickEvent = false;
    boolean missAimSettingWindow = false;
    int screenWidth = 0;
    int screenHeight = 0;
    InputChannelWrapper mInputChannelWrapper = null;
    private UpdateUiRunnable updateUi = new UpdateUiRunnable();
    private boolean isShowing = false;

    class UpdateUiRunnable implements Runnable {
        volatile boolean autoPost = false;

        UpdateUiRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (QuickHideFloatView.this.wm != null && QuickHideFloatView.this.view != null && QuickHideFloatView.this.params != null && QuickHideFloatView.this.isShowing) {
                    QuickHideFloatView.this.wm.updateViewLayout(QuickHideFloatView.this.view, QuickHideFloatView.this.params);
                }
                if (this.autoPost) {
                    ChoreographerToolsWrapper.postCallback(0, QuickHideFloatView.this.updateUi, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public QuickHideFloatView(Context context, GameHelperController gameHelperController) {
        this.mHandler = null;
        this.context = context;
        this.mHandler = new Handler();
        this.mGameHelperController = gameHelperController;
        setSupportMultiTouch(true);
    }

    private boolean canDrawOverlays() {
        return Settings.canDrawOverlays(this.context);
    }

    private void initValues() {
        this.lastX = 0.0f;
        this.lastY = 0.0f;
        this.oldOffsetX = 0;
        this.oldOffsetY = 0;
        this.tag = 0;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.wm.getDefaultDisplay().getRealMetrics(displayMetrics);
        LogUtil.d(TAG, "mDisplayMetrics.widthPixels = " + displayMetrics.widthPixels + " mDisplayMetrics.heightPixels = " + displayMetrics.heightPixels);
        this.screenWidth = displayMetrics.widthPixels >= displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
        this.screenHeight = displayMetrics.widthPixels <= displayMetrics.heightPixels ? displayMetrics.widthPixels : displayMetrics.heightPixels;
    }

    private void updatePositionInAimConfig() {
        int hideAimX = AimConfigs.getInstance(this.context).getHideAimX(this.mGameHelperController.getTopApplication());
        int hideAimY = AimConfigs.getInstance(this.context).getHideAimY(this.mGameHelperController.getTopApplication());
        if (hideAimX == -1 && hideAimY == -1) {
            this.params.gravity = 17;
            this.params.x = 0;
            this.params.y = 0;
        } else {
            this.params.x = hideAimX;
            this.params.y = hideAimY;
            this.params.gravity = 51;
        }
    }

    public void changeIcon(boolean z) {
        View view = this.view;
        if (view != null) {
            ((ImageView) view.findViewById(R.id.quickhide_image)).setImageDrawable(this.context.getResources().getDrawable(z ? R.drawable.quick_hide_off : R.drawable.quick_hide_on));
        }
    }

    public void createFloatView() {
        LogUtil.d(TAG, "createFloatView begin");
        if (this.view == null) {
            LogUtil.d(TAG, "createFloatView inflate");
            this.view = LayoutInflater.from(this.context).inflate(R.layout.quick_hide_floatview, (ViewGroup) null);
        }
        this.wm = (WindowManager) this.context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.params = layoutParams;
        layoutParams.type = 1;
        this.params.flags = 24;
        this.params.format = -3;
        this.params.windowAnimations = R.style.custom_toast_anim_view;
        this.params.layoutInDisplayCutoutMode = 1;
        this.params.type = 2038;
        this.params.alpha = 0.8f;
        this.params.width = -2;
        this.params.height = -2;
        this.params.setTitle(TAG);
        this.view.setBackgroundColor(0);
        initValues();
    }

    public void destroy() {
        InputChannelWrapper inputChannelWrapper = this.mInputChannelWrapper;
        if (inputChannelWrapper != null) {
            inputChannelWrapper.unregisterTouchListener(this);
            this.mInputChannelWrapper.dispose();
            this.mInputChannelWrapper = null;
        }
        this.mHandler.removeCallbacksAndMessages(null);
        removeFloatView();
    }

    public int getUid() {
        return this.uid;
    }

    public void hideFloatView() {
        LogUtil.d(TAG, "hideFloatView");
        if (this.wm == null || this.view == null || !this.isShowing) {
            return;
        }
        LogUtil.d(TAG, "hideFloatView setVisibility");
        this.wm.removeView(this.view);
        this.isShowing = false;
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.QuickHideFloatView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                QuickHideFloatView.this.m229xce887201();
            }
        }, 500L);
    }

    public boolean isFloatViewExist() {
        return this.view != null;
    }

    public boolean isFloatViewVisible() {
        return this.view != null && this.isShowing;
    }

    /* renamed from: lambda$hideFloatView$0$cn-nubia-gamelauncher-aimhelper-QuickHideFloatView, reason: not valid java name */
    /* synthetic */ void m229xce887201() {
        InputChannelWrapper inputChannelWrapper = this.mInputChannelWrapper;
        if (inputChannelWrapper != null) {
            inputChannelWrapper.unregisterTouchListener(this);
            this.mInputChannelWrapper.dispose();
            this.mInputChannelWrapper = null;
        }
    }

    /* renamed from: lambda$showFloatView$1$cn-nubia-gamelauncher-aimhelper-QuickHideFloatView, reason: not valid java name */
    /* synthetic */ void m230xcceee4db() {
        this.mInputChannelWrapper = InputChannelWrapper.getInputChannelWrapper(this.context, Looper.myLooper());
        Rect rect = new Rect();
        try {
            Method declaredMethod = View.class.getDeclaredMethod("getBoundsOnScreen", Rect.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this.view, rect);
        } catch (Exception e) {
            LogUtil.e(TAG, "getBoundsOnScreen " + e);
        }
        LogUtil.d(TAG, "showFloatView rect.x = " + rect.left + "rect.top = " + rect.top + "rect.bottom=" + rect.bottom + "rect.right" + rect.right);
        this.params.x = rect.left;
        this.params.y = rect.top;
        this.params.gravity = 51;
        this.params.layoutInDisplayCutoutMode = 1;
        setMonitorPointXY(this.params.x, this.params.y, this.view.getWidth(), this.view.getHeight());
        this.mInputChannelWrapper.registerTouchListener(this);
    }

    public void onFloatViewClick(View.OnClickListener onClickListener) {
        this.l = onClickListener;
    }

    @Override // com.android.inputEventTool.MonitorTouch, com.android.inputEventTool.MonitorTouchInterface
    public void onTouch(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int actionMasked = motionEvent.getActionMasked();
        int findPointerIndex = this.pointId != -1 ? motionEvent.findPointerIndex(this.pointId) : 0;
        float x = getSupportMultiTouch() ? motionEvent.getX(findPointerIndex) : motionEvent.getX();
        float f = x - this.params.x;
        float y = (getSupportMultiTouch() ? motionEvent.getY(findPointerIndex) : motionEvent.getY()) - this.params.y;
        if (this.tag == 0) {
            this.oldOffsetX = this.params.x;
            this.oldOffsetY = this.params.y;
        }
        if (this.view == null) {
            return;
        }
        if (action == 0 || actionMasked == 5) {
            this.lastX = f;
            this.lastY = y;
            this.updateUi.autoPost = true;
            ChoreographerToolsWrapper.postCallback(0, this.updateUi, null);
            return;
        }
        if (action == 2) {
            WindowManager.LayoutParams layoutParams = this.params;
            int i = layoutParams.x + (((int) (f - this.lastX)) / 3);
            layoutParams.x = i;
            layoutParams.x = i > 0 ? this.params.x > this.screenWidth - this.view.getWidth() ? this.screenWidth - this.view.getWidth() : this.params.x : 0;
            WindowManager.LayoutParams layoutParams2 = this.params;
            int i2 = layoutParams2.y + (((int) (y - this.lastY)) / 3);
            layoutParams2.y = i2;
            layoutParams2.y = i2 > 0 ? this.params.y > this.screenHeight - this.view.getHeight() ? this.screenHeight - this.view.getHeight() : this.params.y : 0;
            this.tag = 1;
            setMonitorPointXY(this.params.x, this.params.y, this.view.getWidth(), this.view.getHeight());
            if ((Math.abs(this.oldOffsetX - this.params.x) > 20 || Math.abs(this.oldOffsetY - this.params.y) > 20) && !this.isNotClickEvent) {
                this.isNotClickEvent = true;
                if (this.mGameHelperController.getAimSettingFloatingWindow().isShowing()) {
                    this.mGameHelperController.getAimSettingFloatingWindow().hide();
                    this.missAimSettingWindow = true;
                    return;
                }
                return;
            }
            return;
        }
        if (action != 1 && actionMasked != 6) {
            this.updateUi.autoPost = false;
            this.isNotClickEvent = false;
            return;
        }
        int i3 = this.params.x;
        int i4 = this.params.y;
        if (this.isNotClickEvent) {
            setMonitorPointXY(this.params.x, this.params.y, this.view.getWidth(), this.view.getHeight());
            if (this.missAimSettingWindow) {
                this.mGameHelperController.getAimSettingFloatingWindow().show();
            }
            AimConfigs.getInstance(this.context).setHideAimX(this.mGameHelperController.getTopApplication(), this.params.x);
            AimConfigs.getInstance(this.context).setHideAimY(this.mGameHelperController.getTopApplication(), this.params.y);
            this.missAimSettingWindow = false;
            this.isNotClickEvent = false;
            this.tag = 0;
        } else {
            View.OnClickListener onClickListener = this.l;
            if (onClickListener != null) {
                onClickListener.onClick(this.view);
            }
        }
        this.updateUi.autoPost = false;
    }

    public void removeFloatView() {
        View view;
        WindowManager windowManager = this.wm;
        if (windowManager != null && (view = this.view) != null && this.isShowing) {
            windowManager.removeViewImmediate(view);
        }
        this.view = null;
        this.wm = null;
        this.isShowing = false;
    }

    public void setUid(int i) {
        this.uid = i;
    }

    public void showFloatView() {
        LogUtil.d(TAG, "showFloatView wm=" + this.wm + " view=" + this.view + " isShowing=" + this.isShowing);
        if (this.wm == null || this.view == null || this.isShowing || !canDrawOverlays()) {
            return;
        }
        updatePositionInAimConfig();
        boolean isHideAim = AimConfigs.getInstance(this.context).isHideAim(this.mGameHelperController.getTopApplication());
        LogUtil.d(TAG, "showFloatView setVisibility  isHideAim=" + isHideAim);
        changeIcon(isHideAim);
        this.wm.addView(this.view, this.params);
        this.isShowing = true;
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.aimhelper.QuickHideFloatView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                QuickHideFloatView.this.m230xcceee4db();
            }
        }, 500L);
    }

    public void updateViewLayout() {
        LogUtil.d(TAG, "updateViewLayout");
        if (this.wm == null || this.view == null) {
            return;
        }
        int i = this.context.getResources().getDisplayMetrics().widthPixels;
        this.wm.updateViewLayout(this.view, this.params);
        LogUtil.d(TAG, "updateViewLayout real x=" + this.params.x + " y=" + this.params.y);
    }
}
