package cn.nubia.gamelauncher.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.common.view.NubiaFanView;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class HostModeFan extends ConstraintLayout {
    public static final String NUBIA_COLLING_FAN_SWITCH = "fan_state_of_manual";
    private static final String TAG = "HostModeFan";
    private static final int mFanOffBfID1 = 2131689547;
    private static final int mFanOffBfID2 = 2131689548;
    private static final int mFanOffBfID3 = 2131689549;
    private static final int mFanOnBgID1 = 2131689550;
    private static final int mFanOnBgID2 = 2131689551;
    private static final int mFanOnBgID3 = 2131689552;
    private Context mContext;
    private boolean mCoolingFanOpen;
    private ImageView mFan1;
    private ImageView mFan2;
    private ImageView mFan3;
    private GameFanContentObserver mGameFanContentObserver;

    private class GameFanContentObserver extends ContentObserver {
        public GameFanContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            HostModeFan hostModeFan = HostModeFan.this;
            hostModeFan.mCoolingFanOpen = hostModeFan.isFanOpenFromSystem();
            HostModeFan.this.setModeBackground();
        }

        public void register() {
            HostModeFan.this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("fan_state_of_manual"), false, this);
        }

        public void unregister() {
            HostModeFan.this.mContext.getContentResolver().unregisterContentObserver(this);
        }
    }

    public HostModeFan(Context context) {
        this(context, null);
    }

    public HostModeFan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCoolingFanOpen = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doAnimator(View view, View view2, boolean z) {
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat((Property<?, Float>) View.ROTATION, z ? 180.0f : 0.0f, z ? 0.0f : 180.0f);
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat((Property<?, Float>) View.ALPHA, z ? 1.0f : 0.0f, z ? 0.0f : 1.0f);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, ofFloat);
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(view2, ofFloat2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofPropertyValuesHolder).with(ofPropertyValuesHolder2);
        animatorSet.setDuration(200L);
        animatorSet.start();
    }

    private void init() {
        this.mContext = GameLauncherApplication.CONTEXT;
        this.mFan3 = (ImageView) findViewById(R.id.fan_3);
        this.mFan2 = (ImageView) findViewById(R.id.fan_2);
        this.mFan1 = (ImageView) findViewById(R.id.fan_1);
        initFanCool();
        setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamelauncher.view.HostModeFan.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HostModeFan.this.setNubiaCollingFanSwitch();
            }
        });
        setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: cn.nubia.gamelauncher.view.HostModeFan.2
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                HostModeFan hostModeFan = HostModeFan.this;
                hostModeFan.doAnimator(hostModeFan.mFan1, HostModeFan.this.mFan2, z);
            }
        });
    }

    private void initFanCool() {
        int i = Settings.System.getInt(this.mContext.getContentResolver(), "fan_state_of_manual", -1);
        int i2 = Settings.Global.getInt(this.mContext.getContentResolver(), "fan_state_of_manual", 0);
        if (i == -1) {
            i = i2;
        }
        if (i > 0) {
            this.mFan3.setBackgroundResource(R.mipmap.fan_on3);
            this.mFan2.setBackgroundResource(R.mipmap.fan_on2);
            this.mFan1.setBackgroundResource(R.mipmap.fan_on1);
            this.mCoolingFanOpen = true;
            return;
        }
        this.mFan3.setBackgroundResource(R.mipmap.fan_off3);
        this.mFan2.setBackgroundResource(R.mipmap.fan_off2);
        this.mFan1.setBackgroundResource(R.mipmap.fan_off1);
        this.mCoolingFanOpen = false;
    }

    private void registerGameFanContentObserver() {
        if (this.mGameFanContentObserver == null) {
            Log.d(TAG, "registerGameFanContentObserver success");
            this.mGameFanContentObserver = new GameFanContentObserver(new Handler());
        }
        this.mGameFanContentObserver.register();
    }

    private void setCollingFanValue(int i) {
        try {
            Settings.System.putInt(this.mContext.getContentResolver(), "fan_state_of_manual", i);
            Settings.Global.putInt(this.mContext.getContentResolver(), "fan_state_of_manual", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setModeBackground() {
        if (this.mCoolingFanOpen) {
            this.mFan3.setBackgroundResource(R.mipmap.fan_on3);
            this.mFan2.setBackgroundResource(R.mipmap.fan_on2);
            this.mFan1.setBackgroundResource(R.mipmap.fan_on1);
        } else {
            this.mFan3.setBackgroundResource(R.mipmap.fan_off3);
            this.mFan2.setBackgroundResource(R.mipmap.fan_off2);
            this.mFan1.setBackgroundResource(R.mipmap.fan_off1);
        }
    }

    public boolean isFanOpenFromSystem() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "fan_state_of_manual", 0) > 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        registerGameFanContentObserver();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        unRegisterGameFanContentObserver();
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            doAnimator(this.mFan1, this.mFan2, false);
        } else if (actionMasked == 10) {
            doAnimator(this.mFan1, this.mFan2, true);
        }
        return false;
    }

    public void setNubiaCollingFanSwitch() {
        if (this.mCoolingFanOpen) {
            setCollingFanValue(NubiaFanView.FAN_STATE_OFF);
        } else {
            setCollingFanValue(NubiaFanView.FAN_STATE_ON);
        }
        this.mCoolingFanOpen = !this.mCoolingFanOpen;
    }

    public void unRegisterGameFanContentObserver() {
        if (this.mGameFanContentObserver != null) {
            Log.d(TAG, "unRegisterGameFanContentObserver success");
            this.mGameFanContentObserver.unregister();
        }
    }
}
