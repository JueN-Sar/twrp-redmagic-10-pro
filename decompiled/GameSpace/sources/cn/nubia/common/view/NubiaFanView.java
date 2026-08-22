package cn.nubia.common.view;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import cn.nubia.common.util.CommonUtil;

/* loaded from: classes.dex */
public class NubiaFanView extends ZoomLottieAnimationView {
    public static int FAN_STATE_OFF = 0;
    public static int FAN_STATE_ON = 0;
    public static final String NUBIA_COLLING_FAN_SWITCH = "fan_state_of_manual";
    public static final String TAG = "fan";
    private boolean isInit;
    private GameFanContentObserver mGameFanContentObserver;
    private boolean mIsFanOpen;

    private class GameFanContentObserver extends ContentObserver {
        public GameFanContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            NubiaFanView nubiaFanView = NubiaFanView.this;
            nubiaFanView.mIsFanOpen = nubiaFanView.isFanOpenFromSystem();
            NubiaFanView.this.update();
        }

        public void register() {
            NubiaFanView.this.getContext().getContentResolver().registerContentObserver(Settings.System.getUriFor("fan_state_of_manual"), false, this);
        }

        public void unregister() {
            NubiaFanView.this.getContext().getContentResolver().unregisterContentObserver(this);
        }
    }

    static {
        FAN_STATE_OFF = CommonUtil.isNubiaOs() ? 0 : -4;
        FAN_STATE_ON = CommonUtil.isNubiaOs() ? 1 : 4;
    }

    public NubiaFanView(Context context) {
        super(context);
        this.mIsFanOpen = false;
        this.isInit = false;
        initObserver();
    }

    public NubiaFanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsFanOpen = false;
        this.isInit = false;
        initObserver();
    }

    private void init() {
        if (this.isInit) {
            return;
        }
        int i = Settings.System.getInt(getContext().getContentResolver(), "fan_state_of_manual", -1);
        int i2 = Settings.Global.getInt(getContext().getContentResolver(), "fan_state_of_manual", 0);
        if (i == -1) {
            i = i2;
        }
        this.mIsFanOpen = i > 0;
        Log.d(TAG, "init(" + i + ") mIsFanOpen : " + this.mIsFanOpen);
        update();
    }

    private void initObserver() {
        if (this.mGameFanContentObserver == null) {
            this.mGameFanContentObserver = new GameFanContentObserver(new Handler());
        }
    }

    private void setCollingFanValue(int i) {
        try {
            Settings.System.putInt(getContext().getContentResolver(), "fan_state_of_manual", i);
            Settings.Global.putInt(getContext().getContentResolver(), "fan_state_of_manual", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        doAnim(isOpen());
    }

    public void doAnim(boolean z) {
        Log.d(TAG, "doAnim(" + z + ")");
        cancelAnimation();
        setAnimation(z ? "lottie/fan_open.json" : "lottie/fan_close.json");
        playAnimation();
    }

    public void doInit() {
        if (!this.isInit) {
            init();
            this.isInit = true;
        }
        doAnim(isOpen());
    }

    public boolean isFanOpenFromSystem() {
        int i = Settings.System.getInt(getContext().getContentResolver(), "fan_state_of_manual", 0);
        Log.d(TAG, "isFanOpenFromSystem() cooling_fan : " + i);
        return i > 0;
    }

    @Override // android.view.View
    public boolean isFocused() {
        return true;
    }

    public boolean isOpen() {
        return this.mIsFanOpen;
    }

    @Override // com.airbnb.lottie.LottieAnimationView, android.widget.ImageView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        initObserver();
        this.mGameFanContentObserver.register();
        init();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        GameFanContentObserver gameFanContentObserver = this.mGameFanContentObserver;
        if (gameFanContentObserver != null) {
            gameFanContentObserver.unregister();
        }
    }

    public void switchFanState() {
        setCollingFanValue(this.mIsFanOpen ? FAN_STATE_OFF : FAN_STATE_ON);
        this.mIsFanOpen = !this.mIsFanOpen;
        Log.d(TAG, "switchFanState() mIsFanOpen : " + this.mIsFanOpen);
    }
}
