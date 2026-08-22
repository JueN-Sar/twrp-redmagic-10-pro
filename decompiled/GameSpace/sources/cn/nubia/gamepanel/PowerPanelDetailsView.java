package cn.nubia.gamepanel;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.systemwrapper.InputChannelWrapper;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class PowerPanelDetailsView extends LinearLayout implements InputChannelWrapper.EventListener {
    private static final long MORE_POINT_TIMEOUT = 5000;
    private static final long ONE_POINT_TIMEOUT = 1000;
    private static final String TAG = "PowerPanelDetailsView";
    private static WindowManager windowManager;
    private final String POWER_PANEL_VIEW_X;
    private final String POWER_PANEL_VIEW_Y;
    private int allPointsCount;
    private LinearLayout cpsBack;
    private int cpsListSize;
    private CpsMpmData cpsMpmData;
    List<Integer> cpsValues;
    private String gamePkg;
    private boolean initViewPlace;
    private WindowManager.LayoutParams lp;
    private Context mContext;
    private float mLastX;
    private float mLastY;
    private PowerPanelView mPowerPanelView;
    private float mStartX;
    private float mStartY;
    private float mStopX;
    private float mStopY;
    private int mTranslationX;
    private int mTranslationY;
    private TextView morePointTextView;
    private int morePointsCount;
    private Handler morePointsHandler;
    private Runnable morePointsRunnable;
    private LinearLayout mpmBack;
    private int mpmListSize;
    List<Integer> mpmValues;
    private int onePointCount;
    private Handler onePointHandler;
    private Runnable onePointRunnable;
    private TextView onePointTextView;
    private View view;

    public PowerPanelDetailsView(Context context) {
        super(context);
        this.POWER_PANEL_VIEW_X = "power_panel_view_x";
        this.POWER_PANEL_VIEW_Y = "power_panel_view_y";
        this.mContext = null;
        this.view = null;
        this.lp = new WindowManager.LayoutParams();
        this.mpmListSize = 3;
        this.cpsListSize = 12;
        this.initViewPlace = false;
        this.onePointCount = 0;
        this.morePointsCount = 0;
        this.allPointsCount = 0;
        this.gamePkg = "";
        this.mpmValues = new ArrayList(this.mpmListSize);
        this.cpsValues = new ArrayList(this.cpsListSize);
        this.onePointHandler = new Handler();
        this.onePointRunnable = new Runnable() { // from class: cn.nubia.gamepanel.PowerPanelDetailsView.1
            @Override // java.lang.Runnable
            public void run() {
                PowerPanelDetailsView.this.onePointTextView.setText(PowerPanelDetailsView.this.onePointCount + "");
                if (PowerPanelDetailsView.this.cpsValues.size() >= PowerPanelDetailsView.this.cpsListSize) {
                    PowerPanelDetailsView.this.cpsValues.remove(0);
                    PowerPanelDetailsView.this.cpsValues.add(Integer.valueOf(PowerPanelDetailsView.this.onePointCount));
                }
                PowerPanelDetailsView.this.mPowerPanelView.setCpsValues(PowerPanelDetailsView.this.cpsValues);
                PowerPanelDetailsView.this.onePointHandler.postDelayed(this, 1000L);
                PowerPanelDetailsView.this.onePointCount = 0;
                PowerPanelDetailsView.this.mPowerPanelView.invalidate();
            }
        };
        this.morePointsHandler = new Handler();
        this.morePointsRunnable = new Runnable() { // from class: cn.nubia.gamepanel.PowerPanelDetailsView.2
            @Override // java.lang.Runnable
            public void run() {
                int divData = MathUtils.divData(PowerPanelDetailsView.this.morePointsCount, PowerPanelDetailsView.this.allPointsCount, 2);
                PowerPanelDetailsView.this.morePointTextView.setText(divData + "");
                if (PowerPanelDetailsView.this.mpmValues.size() >= PowerPanelDetailsView.this.mpmListSize) {
                    PowerPanelDetailsView.this.mpmValues.remove(0);
                    PowerPanelDetailsView.this.mpmValues.add(Integer.valueOf(divData));
                }
                PowerPanelDetailsView.this.mPowerPanelView.setMpmValues(PowerPanelDetailsView.this.mpmValues);
                PowerPanelDetailsView.this.morePointsHandler.postDelayed(this, 5000L);
                PowerPanelDetailsView.this.morePointsCount = 0;
                PowerPanelDetailsView.this.allPointsCount = 0;
            }
        };
        this.mContext = context;
        this.gamePkg = Settings.Global.getString(context.getContentResolver(), "game_pack_name");
        LogUtils.infoPowerPanel(TAG, "onCreate PowerPanelDetailsView");
        LayoutInflater.from(context).inflate(R.layout.power_panel_view, this);
        this.cpsMpmData = CpsMpmData.getInstance();
        initView();
        initLayoutParams();
        initEvent();
        initData();
    }

    static /* synthetic */ int access$012(PowerPanelDetailsView powerPanelDetailsView, int i) {
        int i2 = powerPanelDetailsView.onePointCount + i;
        powerPanelDetailsView.onePointCount = i2;
        return i2;
    }

    static /* synthetic */ int access$512(PowerPanelDetailsView powerPanelDetailsView, int i) {
        int i2 = powerPanelDetailsView.morePointsCount + i;
        powerPanelDetailsView.morePointsCount = i2;
        return i2;
    }

    static /* synthetic */ int access$612(PowerPanelDetailsView powerPanelDetailsView, int i) {
        int i2 = powerPanelDetailsView.allPointsCount + i;
        powerPanelDetailsView.allPointsCount = i2;
        return i2;
    }

    private void initData() {
        for (int i = 0; i < this.mpmListSize; i++) {
            this.mpmValues.add(0);
        }
        for (int i2 = 0; i2 < this.cpsListSize; i2++) {
            this.cpsValues.add(0);
        }
    }

    private void initEvent() {
        this.view.setOnTouchListener(new View.OnTouchListener() { // from class: cn.nubia.gamepanel.PowerPanelDetailsView.4
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return PowerPanelDetailsView.this.onFloatButtonTouch(motionEvent);
            }
        });
    }

    private void initLayoutParams() {
        windowManager = (WindowManager) this.mContext.getSystemService("window");
        this.lp.type = 2038;
        this.lp.flags = 40;
        this.lp.gravity = 8388659;
        this.lp.x = Settings.Global.getInt(this.mContext.getContentResolver(), this.gamePkg + "power_panel_view_x", 402);
        this.lp.y = Settings.Global.getInt(this.mContext.getContentResolver(), this.gamePkg + "power_panel_view_y", 150);
        this.lp.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.power_panel_width);
        this.lp.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.power_panel_height);
        this.lp.format = -2;
        this.lp.setTitle("PowerPanelWindow");
        windowManager.addView(this, this.lp);
    }

    private void initView() {
        InputChannelWrapper.getInstance().registerInputMonitor(Looper.getMainLooper(), this.mContext, "GamePanelChannel", new InputChannelWrapper.EventListener() { // from class: cn.nubia.gamepanel.PowerPanelDetailsView.3
            @Override // cn.nubia.systemwrapper.InputChannelWrapper.EventListener
            public void onInputEvent(InputEvent inputEvent) {
                if (inputEvent instanceof MotionEvent) {
                    int actionMasked = ((MotionEvent) inputEvent).getActionMasked();
                    if (actionMasked == 0) {
                        PowerPanelDetailsView.access$012(PowerPanelDetailsView.this, 1);
                        PowerPanelDetailsView.access$612(PowerPanelDetailsView.this, 1);
                        PowerPanelDetailsView.this.cpsMpmData.setCps(PowerPanelDetailsView.this.cpsMpmData.getCps() + 1);
                    } else if (actionMasked == 5) {
                        PowerPanelDetailsView.access$012(PowerPanelDetailsView.this, 1);
                        PowerPanelDetailsView.access$512(PowerPanelDetailsView.this, 1);
                        PowerPanelDetailsView.access$612(PowerPanelDetailsView.this, 1);
                        PowerPanelDetailsView.this.cpsMpmData.setMpm(PowerPanelDetailsView.this.cpsMpmData.getMpm() + 1);
                        PowerPanelDetailsView.this.cpsMpmData.setCps(PowerPanelDetailsView.this.cpsMpmData.getCps() + 1);
                    }
                } else if ((inputEvent instanceof KeyEvent) && ((KeyEvent) inputEvent).getAction() == 0) {
                    PowerPanelDetailsView.access$012(PowerPanelDetailsView.this, 1);
                    PowerPanelDetailsView.access$612(PowerPanelDetailsView.this, 1);
                    PowerPanelDetailsView.this.cpsMpmData.setCps(PowerPanelDetailsView.this.cpsMpmData.getCps() + 1);
                }
                super.onInputEvent(inputEvent);
            }
        });
        this.view = findViewById(R.id.ll_power_panel);
        this.mPowerPanelView = (PowerPanelView) findViewById(R.id.power_panel_view);
        this.onePointTextView = (TextView) findViewById(R.id.one_point_view);
        this.morePointTextView = (TextView) findViewById(R.id.more_points_view);
        this.mpmBack = (LinearLayout) findViewById(R.id.ll_mpm);
        this.cpsBack = (LinearLayout) findViewById(R.id.ll_cps);
        this.cpsValues.clear();
        this.mpmValues.clear();
        this.onePointHandler.postDelayed(this.onePointRunnable, 1000L);
        this.morePointsHandler.postDelayed(this.morePointsRunnable, 5000L);
        this.mpmBack.getBackground().setAlpha(51);
        this.cpsBack.getBackground().setAlpha(51);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onFloatButtonTouch(MotionEvent motionEvent) {
        if (motionEvent == null) {
            return false;
        }
        this.mStopX = motionEvent.getRawX();
        this.mStopY = motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mStartX = motionEvent.getRawX();
            this.mStartY = motionEvent.getRawY();
            this.mLastX = motionEvent.getRawX();
            this.mLastY = motionEvent.getRawY();
            return true;
        }
        if (action != 2) {
            return true;
        }
        this.mTranslationX = (int) (this.mStopX - this.mLastX);
        this.mTranslationY = (int) (this.mStopY - this.mLastY);
        this.mLastX = motionEvent.getRawX();
        this.mLastY = motionEvent.getRawY();
        updateViewPosition(this.mTranslationX, this.mTranslationY);
        return true;
    }

    private void outScreenCheck() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        LogUtils.infoPowerPanel(TAG, "outScreenCheck: " + this.lp.x + "*" + this.lp.y + " " + i + "*" + i2);
        if (this.lp.x < 0 || this.lp.y < 0 || this.lp.x > i - this.lp.width || this.lp.y > i2 - this.lp.height) {
            WindowManager.LayoutParams layoutParams = this.lp;
            layoutParams.x = layoutParams.x > i - this.lp.width ? i - this.lp.width : this.lp.x;
            WindowManager.LayoutParams layoutParams2 = this.lp;
            layoutParams2.x = layoutParams2.x < 0 ? 0 : this.lp.x;
            WindowManager.LayoutParams layoutParams3 = this.lp;
            layoutParams3.y = layoutParams3.y > i2 - this.lp.height ? i2 - this.lp.height : this.lp.y;
            WindowManager.LayoutParams layoutParams4 = this.lp;
            layoutParams4.y = layoutParams4.y >= 0 ? this.lp.y : 0;
        }
    }

    private void updateViewPosition(int i, int i2) {
        this.lp.x += i;
        this.lp.y += i2;
        outScreenCheck();
        Settings.Global.putInt(this.mContext.getContentResolver(), this.gamePkg + "power_panel_view_x", this.lp.x);
        Settings.Global.putInt(this.mContext.getContentResolver(), this.gamePkg + "power_panel_view_y", this.lp.y);
        windowManager.updateViewLayout(this, this.lp);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Runnable runnable;
        Runnable runnable2;
        super.onDetachedFromWindow();
        LogUtils.infoPowerPanel(TAG, "onDetachedFromWindow");
        Handler handler = this.onePointHandler;
        if (handler != null && (runnable2 = this.onePointRunnable) != null) {
            handler.removeCallbacks(runnable2);
        }
        Handler handler2 = this.morePointsHandler;
        if (handler2 == null || (runnable = this.morePointsRunnable) == null) {
            return;
        }
        handler2.removeCallbacks(runnable);
    }
}
