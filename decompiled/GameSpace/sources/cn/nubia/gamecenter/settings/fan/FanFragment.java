package cn.nubia.gamecenter.settings.fan;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import cn.nubia.gamecenter.settings.BaseFragment;
import cn.nubia.gamecenter.settings.FragmentInterface;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.helper.AnimatorHelper;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.widget.CircleClipImageView;
import cn.nubia.gamecenter.settings.widget.StateSeekBar;
import cn.nubia.gamecenter.settings.widget.particle.ParticleSurfaceView;
import cn.nubia.settings.trackclient.NubiaTrackManager;

/* loaded from: classes.dex */
public class FanFragment extends BaseFragment implements FragmentInterface {
    private static final float ALPHA_CHECKED = 1.0f;
    private static final float ALPHA_DISABLE = 0.3f;
    private static final float ALPHA_ENABLE = 0.5f;
    private static final int COLOR_PARTICLE = -4378359;
    private static final float[] COMMON_CURVE = {0.2f, 0.22f, 0.17f, 1.0f};
    private static final String DB_SWITCH_FAN_AUTO_MODE = "fan_mode_of_gamespace";
    private static final String DB_SWITCH_FAN_OFF_ON = "game_fan_gamespace_off_on";
    private static final String DB_SWITCH_FAN_SPEED = "game_fan_speed";
    private static final int SPEED_AUTO = 7;
    private static final int SPEED_HIGH = 5;
    private static final int SPEED_MIDDLE = 4;
    private static final int SPEED_SLOW = 3;
    private static final int SPEED_UNDEFINE = -1;
    private static final String TAG = "FanFragment";
    private static final int UP_COUNT = 1;
    private static final int UP_DELAY = 400;
    private final AnimatorHelper.Item[] ITEMs;
    private final AnimatorHelper.Item[] ITEMs_fan_down;
    private final AnimatorHelper.Item[] ITEMs_fan_up;
    private final AnimatorHelper.Item[] ITEMs_fan_updown;
    private final AnimatorHelper.Item[] ITEMs_hide;
    private View mAutoPreference;
    private TextView mAutoSummary;
    private TextView mAutoTextView;
    private TextView mAutoTitle;
    private Context mContext;
    private ToggleButton mFanSwitch;
    private boolean mFanSwitchEnabled;
    private View mManualPreference;
    private TextView mManualSummary;
    private TextView mManualTextView;
    private TextView mManualTitle;
    private LinearLayout mSwitchLayout;
    private TextView mSwitchOff;
    private TextView mSwitchOn;
    private LinearLayout mWidgetLayout;
    private ObjectAnimator m_animation;
    private boolean m_bAutoMode;
    private AnimatorHelper m_fanHelper_down;
    private AnimatorHelper m_fanHelper_up;
    private AnimatorHelper m_fanHelper_updown;
    private int m_fanSpeed;
    private View m_fanStop;
    private AnimatorHelper.Item m_fan_speed_2_down;
    private AnimatorHelper.Item m_guangdai;
    private AnimatorHelper m_helper;
    private AnimatorHelper m_helper_hide;
    private LinearInterpolator m_linear;
    ParticleSurfaceView m_particleView;
    private PathInterpolator m_path;
    private View m_root;
    private final ContentObserver mGameFanSwitchChangeObserver = new ContentObserver(new Handler()) { // from class: cn.nubia.gamecenter.settings.fan.FanFragment.1
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            FanFragment.this.updateAllView();
        }
    };
    private final Listener mListener = new Listener();
    private final View.OnClickListener m_autoModeClickListener = new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.fan.FanFragment.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FanFragment.this.setFanDB(FanFragment.DB_SWITCH_FAN_AUTO_MODE, !FanFragment.this.isAutoMode());
            FanFragment.this.updateViewEnableState(FanFragment.this.isFanSubSwitchOpen(FanFragment.DB_SWITCH_FAN_OFF_ON, 1), FanFragment.this.isFanSubSwitchOpen(FanFragment.DB_SWITCH_FAN_AUTO_MODE, 1));
        }
    };
    private final StateSeekBar.OnProgressChangeListener m_modeClickListener = new StateSeekBar.OnProgressChangeListener() { // from class: cn.nubia.gamecenter.settings.fan.FanFragment.6
        @Override // cn.nubia.gamecenter.settings.widget.StateSeekBar.OnProgressChangeListener
        public void onProgressChanged(StateSeekBar stateSeekBar, int i) {
            FanFragment.this.changeMode(i);
        }

        @Override // cn.nubia.gamecenter.settings.widget.StateSeekBar.OnProgressChangeListener
        public void onStartTrackingTouch(StateSeekBar stateSeekBar, int i) {
            FanFragment.this.changeMode(i);
        }

        @Override // cn.nubia.gamecenter.settings.widget.StateSeekBar.OnProgressChangeListener
        public void onStopTrackingTouch(StateSeekBar stateSeekBar, int i) {
            FanFragment.this.changeMode(i);
        }
    };
    private AnimatorHelper.Item m_fan_speed_up = new AnimatorHelper.Item(R.id.fan_fengshan, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 360.0f}, null, 600, UP_DELAY);
    private AnimatorHelper.Item m_fan_speed_normal = new AnimatorHelper.Item(R.id.fan_fengshan, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 360.0f}, null, 300, 1000);
    private AnimatorHelper.Item m_fan_speed_down = new AnimatorHelper.Item(R.id.fan_fengshan, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 360.0f}, null, 600, 0);
    private AnimatorHelper.Item m_fan_speed_2_up = new AnimatorHelper.Item(R.id.fan_fengshan, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 360.0f}, null, 600, 600);
    private AnimatorHelper.Item m_fan_speed_2_normal = new AnimatorHelper.Item(R.id.fan_fengshan, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 360.0f}, null, 300, 1200);

    private class Listener implements CompoundButton.OnCheckedChangeListener {
        private Listener() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (compoundButton == FanFragment.this.mFanSwitch) {
                FanFragment.this.setSwitchBtnTextColor(z);
                if (z && FanFragment.this.mFanSwitchEnabled) {
                    LogUtil.i(FanFragment.TAG, "onCheckedChanged return;");
                } else {
                    FanFragment.this.setFanDB(FanFragment.DB_SWITCH_FAN_OFF_ON, z);
                }
            }
        }
    }

    public FanFragment() {
        AnimatorHelper.Item item = new AnimatorHelper.Item(R.id.fan_fengshan, AnimatorHelper.Item.ROTATE, new float[]{0.0f, 360.0f}, null, 600, 0);
        this.m_fan_speed_2_down = item;
        this.ITEMs_fan_up = new AnimatorHelper.Item[]{this.m_fan_speed_up, this.m_fan_speed_normal};
        this.ITEMs_fan_updown = new AnimatorHelper.Item[]{item, this.m_fan_speed_2_up, this.m_fan_speed_2_normal};
        this.ITEMs_fan_down = new AnimatorHelper.Item[]{this.m_fan_speed_down};
        this.m_guangdai = new AnimatorHelper.Item(R.id.fan_guangdai, AnimatorHelper.Item.CUST_MOVE_Y, new float[]{0.0f, 1.0f}, null, 750, 750);
        float[] fArr = COMMON_CURVE;
        this.ITEMs = new AnimatorHelper.Item[]{new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.SCALEX, new float[]{0.95f, 1.0f}, fArr, 200, 0), new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.SCALEY, new float[]{0.95f, 1.0f}, fArr, 200, 0), new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.ROTATE, new float[]{-50.0f, 0.0f}, fArr, 200, 0), new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.ALPHA, new float[]{0.2f, 1.0f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, fArr, 450, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, fArr, 450, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.ROTATE, new float[]{-180.0f, 0.0f}, fArr, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.ALPHA, new float[]{0.4f, 1.0f}, null, 450, 0), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, fArr, 450, 50), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, fArr, 450, 50), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.ROTATE, new float[]{-270.0f, 0.0f}, fArr, UP_DELAY, 50), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, 450, 50), new AnimatorHelper.Item(R.id.fan_BG_light4, AnimatorHelper.Item.SCALEX, new float[]{0.9f, 1.0f}, fArr, 450, 100), new AnimatorHelper.Item(R.id.fan_BG_light4, AnimatorHelper.Item.SCALEY, new float[]{0.9f, 1.0f}, fArr, 450, 100), new AnimatorHelper.Item(R.id.fan_BG_light4, AnimatorHelper.Item.ROTATE, new float[]{-180.0f, 0.0f}, fArr, UP_DELAY, 100), new AnimatorHelper.Item(R.id.fan_BG, AnimatorHelper.Item.ALPHA, new float[]{0.4f, 1.0f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_zu37, AnimatorHelper.Item.ALPHA, new float[]{0.4f, 0.9f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_zu37, AnimatorHelper.Item.SCALEX, new float[]{0.95f, 1.0f}, fArr, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_zu37, AnimatorHelper.Item.SCALEY, new float[]{0.95f, 1.0f}, fArr, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_guangdian, AnimatorHelper.Item.ALPHA, new float[]{0.2f, 1.0f}, null, 650, 0), new AnimatorHelper.Item(R.id.fan_guanghuan, AnimatorHelper.Item.ALPHA, new float[]{0.7f, 1.0f}, null, 850, 0), new AnimatorHelper.Item(R.id.fan_fengshantuoyuan1, AnimatorHelper.Item.ALPHA, new float[]{0.5f, 1.0f}, null, 100, 100), new AnimatorHelper.Item(R.id.fan_fengshantuoyuan2, AnimatorHelper.Item.ALPHA, new float[]{0.5f, 1.0f}, null, 100, 100), new AnimatorHelper.Item(R.id.fan_light2, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_logo, AnimatorHelper.Item.ALPHA, new float[]{0.7f, 1.0f}, null, 300, 0), this.m_guangdai, new AnimatorHelper.Item(R.id.fan_fengshan_normal, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.9f, 0.45f, 0.0f}, null, 850, 1000), new AnimatorHelper.Item(R.id.fan_fengshan_blur, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f, 1.0f, 1.0f}, null, 850, 1000)};
        this.ITEMs_hide = new AnimatorHelper.Item[]{new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.SCALEX, new float[]{0.1f, 0.95f}, fArr, 200, 0), new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.SCALEY, new float[]{0.1f, 0.95f}, fArr, 200, 0), new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.ROTATE, new float[]{0.0f, -50.0f}, fArr, 200, 0), new AnimatorHelper.Item(R.id.fan_BG_light, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.2f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.SCALEX, new float[]{1.0f, 0.9f}, fArr, 450, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.SCALEY, new float[]{1.0f, 0.9f}, fArr, 450, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.ROTATE, new float[]{0.0f, -180.0f}, fArr, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_BG_light2, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.4f}, null, 450, 0), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.SCALEX, new float[]{1.0f, 0.9f}, fArr, 450, 50), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.SCALEY, new float[]{1.0f, 0.9f}, fArr, 450, 50), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.ROTATE, new float[]{0.0f, -270.0f}, fArr, UP_DELAY, 50), new AnimatorHelper.Item(R.id.fan_BG_light3, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.0f}, null, 450, 50), new AnimatorHelper.Item(R.id.fan_BG_light4, AnimatorHelper.Item.SCALEX, new float[]{1.0f, 0.9f}, fArr, 450, 100), new AnimatorHelper.Item(R.id.fan_BG_light4, AnimatorHelper.Item.SCALEY, new float[]{1.0f, 0.9f}, fArr, 450, 100), new AnimatorHelper.Item(R.id.fan_BG_light4, AnimatorHelper.Item.ROTATE, new float[]{0.0f, -180.0f}, fArr, UP_DELAY, 100), new AnimatorHelper.Item(R.id.fan_BG, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.4f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_zu37, AnimatorHelper.Item.ALPHA, new float[]{0.9f, 0.4f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_zu37, AnimatorHelper.Item.SCALEX, new float[]{1.0f, 0.95f}, fArr, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_zu37, AnimatorHelper.Item.SCALEY, new float[]{1.0f, 0.95f}, fArr, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_guangdian, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.2f}, null, 650, 0), new AnimatorHelper.Item(R.id.fan_guanghuan, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.7f}, null, 850, 0), new AnimatorHelper.Item(R.id.fan_fengshantuoyuan1, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.5f}, null, 100, 100), new AnimatorHelper.Item(R.id.fan_fengshantuoyuan2, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.5f}, null, 100, 100), new AnimatorHelper.Item(R.id.fan_light2, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.0f}, null, UP_DELAY, 0), new AnimatorHelper.Item(R.id.fan_logo, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.7f}, null, 300, 0), new AnimatorHelper.Item(R.id.fan_fengshan_normal, AnimatorHelper.Item.ALPHA, new float[]{0.0f, 1.0f, 1.0f, 1.0f}, null, 850, 0), new AnimatorHelper.Item(R.id.fan_fengshan_blur, AnimatorHelper.Item.ALPHA, new float[]{1.0f, 0.9f, 0.45f, 0.0f}, null, 850, 0)};
    }

    private void adjustGuangdai(boolean z) {
        if (z) {
            this.m_guangdai.setRepeatCount(-1);
            return;
        }
        View findViewById = this.m_root.findViewById(R.id.fan_blade);
        if (findViewById == null) {
            return;
        }
        this.m_guangdai.setRepeatCount(0);
        this.m_guangdai.cancel();
        View findViewById2 = findViewById.findViewById(this.m_guangdai.getViewId());
        if (findViewById2 == null || !(findViewById2 instanceof CircleClipImageView)) {
            return;
        }
        ((CircleClipImageView) findViewById2).setMoveY(0.0f);
    }

    private float getFanRotatePosition() {
        View view = this.m_fanStop;
        if (view == null) {
            return 0.0f;
        }
        return view.getRotation() % 360.0f;
    }

    private int getFanSpeed() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), DB_SWITCH_FAN_SPEED, 3);
    }

    private Interpolator getInterpolator(boolean z) {
        if (!z) {
            if (this.m_linear == null) {
                this.m_linear = new LinearInterpolator();
            }
            return this.m_linear;
        }
        if (this.m_path == null) {
            float[] fArr = COMMON_CURVE;
            this.m_path = new PathInterpolator(fArr[0], fArr[1], fArr[2], fArr[3]);
        }
        return this.m_path;
    }

    private ParticleSurfaceView getParticleView() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAutoMode() {
        return this.m_bAutoMode;
    }

    private boolean isFanAnimationIsRunning(int i) {
        ObjectAnimator objectAnimator = this.m_animation;
        if (objectAnimator != null && objectAnimator.isStarted() && this.m_fanSpeed == i) {
            return true;
        }
        this.m_fanSpeed = i;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isFanSubSwitchOpen(String str, int i) {
        return 1 == Settings.System.getInt(this.mContext.getContentResolver(), str, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAutoCheckChande(int i) {
        boolean isFanSubSwitchOpen = isFanSubSwitchOpen(DB_SWITCH_FAN_AUTO_MODE, 1);
        int fanSpeed = getFanSpeed();
        if (i == R.id.fan_auto_preference) {
            if (isFanSubSwitchOpen && fanSpeed == 0) {
                return;
            } else {
                setFanDB(DB_SWITCH_FAN_AUTO_MODE, true);
            }
        } else if (i == R.id.fan_manual_preference) {
            if (!isFanSubSwitchOpen && fanSpeed == 5) {
                return;
            } else {
                setFanDB(DB_SWITCH_FAN_AUTO_MODE, false);
            }
        }
        updateViewEnableState(isFanSubSwitchOpen(DB_SWITCH_FAN_OFF_ON, 1), isFanSubSwitchOpen(DB_SWITCH_FAN_AUTO_MODE, 1));
    }

    private void releaseAnimatorRes() {
        AnimatorHelper animatorHelper = this.m_fanHelper_up;
        if (animatorHelper != null) {
            animatorHelper.cancel();
        }
        AnimatorHelper animatorHelper2 = this.m_fanHelper_down;
        if (animatorHelper2 != null) {
            animatorHelper2.cancel();
        }
        AnimatorHelper animatorHelper3 = this.m_fanHelper_up;
        if (animatorHelper3 != null) {
            animatorHelper3.cancel();
        }
        AnimatorHelper animatorHelper4 = this.m_fanHelper_updown;
        if (animatorHelper4 != null) {
            animatorHelper4.cancel();
        }
        AnimatorHelper animatorHelper5 = this.m_helper;
        if (animatorHelper5 != null) {
            animatorHelper5.cancel();
        }
        AnimatorHelper animatorHelper6 = this.m_helper_hide;
        if (animatorHelper6 != null) {
            animatorHelper6.cancel();
        }
    }

    private void setAutoMode(boolean z, boolean z2) {
        this.mAutoTextView.setEnabled(z);
        this.mManualTextView.setEnabled(z);
        this.mAutoTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable((z && z2) ? R.drawable.gcs_auto_selected : R.drawable.gcs_gamecenter_fan_auto_selector), (Drawable) null, (Drawable) null);
        this.m_bAutoMode = z2;
        this.mManualTextView.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, getResources().getDrawable((!z || z2) ? R.drawable.gcs_gamecenter_fan_auto_selector : R.drawable.gcs_auto_selected), (Drawable) null, (Drawable) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFanDB(String str, boolean z) {
        Settings.System.putInt(this.mContext.getContentResolver(), str, z ? 1 : 0);
    }

    private void setFanSpeed(int i) {
        Settings.Global.putInt(this.mContext.getContentResolver(), DB_SWITCH_FAN_SPEED, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSwitchBtnTextColor(boolean z) {
        TextView textView = this.mSwitchOn;
        if (textView != null) {
            textView.setTextColor(z ? ColorStateList.valueOf(-1) : ColorStateList.valueOf(Integer.MAX_VALUE));
        }
        TextView textView2 = this.mSwitchOff;
        if (textView2 != null) {
            textView2.setTextColor(z ? ColorStateList.valueOf(Integer.MAX_VALUE) : ColorStateList.valueOf(-1));
        }
    }

    private void setTextColorAlpha(TextView textView, TextView textView2, boolean z, boolean z2) {
        textView.setAlpha(z ? z2 ? 1.0f : 0.5f : 0.3f);
        textView2.setAlpha(z ? 1.0f : 0.3f);
    }

    private void startAllAnimations(View view) {
        if (this.m_helper == null) {
            this.m_helper = new AnimatorHelper(view, this.ITEMs);
        }
        adjustGuangdai(true);
        this.m_helper.start();
        startParticleAnim();
    }

    private void startFanAnimation(int i) {
        View findViewById;
        View view = this.m_root;
        if (view == null || (findViewById = view.findViewById(R.id.fan_blade)) == null) {
            return;
        }
        startAllAnimations(findViewById);
        if (this.m_fanHelper_up == null) {
            this.m_fanHelper_up = new AnimatorHelper(findViewById, this.ITEMs_fan_up);
            this.m_fan_speed_up.setInterpolator(new AccelerateInterpolator());
            this.m_fan_speed_normal.setRepeatCount(-1);
        }
        if (this.m_fanHelper_down == null) {
            this.m_fanHelper_down = new AnimatorHelper(findViewById, this.ITEMs_fan_down);
            this.m_fan_speed_down.setInterpolator(new DecelerateInterpolator());
        }
        if (this.m_fanHelper_updown == null) {
            this.m_fanHelper_updown = new AnimatorHelper(findViewById, this.ITEMs_fan_updown);
            this.m_fan_speed_2_down.setInterpolator(new DecelerateInterpolator());
            this.m_fan_speed_2_up.setInterpolator(new AccelerateInterpolator());
            this.m_fan_speed_2_normal.setRepeatCount(-1);
        }
        float fanRotatePosition = getFanRotatePosition();
        if (!this.m_fanHelper_up.isStarted() && !this.m_fanHelper_updown.isStarted()) {
            float f = 360.0f + fanRotatePosition;
            this.m_fan_speed_up.setParams(new float[]{fanRotatePosition, f});
            this.m_fan_speed_normal.setParams(new float[]{fanRotatePosition, f});
            this.m_fanHelper_up.start();
            return;
        }
        this.m_fanHelper_up.cancel();
        this.m_fanHelper_updown.cancel();
        float f2 = 360.0f + fanRotatePosition;
        this.m_fan_speed_2_down.setParams(new float[]{fanRotatePosition, f2});
        this.m_fan_speed_2_up.setParams(new float[]{fanRotatePosition, f2});
        this.m_fan_speed_2_normal.setParams(new float[]{fanRotatePosition, f2});
        this.m_fanHelper_updown.start();
    }

    private void startParticleAnim() {
        if (getParticleView() == null) {
            return;
        }
        getParticleView().setVisibility(0);
        getParticleView().setColor(COLOR_PARTICLE);
        getParticleView().createAnim().start();
    }

    private void stopFanAnimation() {
        AnimatorHelper animatorHelper;
        View findViewById;
        AnimatorHelper animatorHelper2 = this.m_fanHelper_up;
        if ((animatorHelper2 == null || !animatorHelper2.isStarted()) && ((animatorHelper = this.m_fanHelper_updown) == null || !animatorHelper.isStarted())) {
            return;
        }
        AnimatorHelper animatorHelper3 = this.m_fanHelper_up;
        if (animatorHelper3 != null) {
            animatorHelper3.cancel();
        }
        AnimatorHelper animatorHelper4 = this.m_fanHelper_updown;
        if (animatorHelper4 != null) {
            animatorHelper4.cancel();
        }
        if (this.m_fanHelper_down != null) {
            float fanRotatePosition = getFanRotatePosition();
            this.m_fan_speed_down.setParams(new float[]{fanRotatePosition, 360.0f + fanRotatePosition});
            this.m_fanHelper_down.start();
        }
        adjustGuangdai(false);
        if (this.m_helper_hide == null && (findViewById = this.m_root.findViewById(R.id.fan_blade)) != null) {
            this.m_helper_hide = new AnimatorHelper(findViewById, this.ITEMs_hide);
        }
        AnimatorHelper animatorHelper5 = this.m_helper;
        if (animatorHelper5 != null) {
            animatorHelper5.cancel();
        }
        AnimatorHelper animatorHelper6 = this.m_helper_hide;
        if (animatorHelper6 != null) {
            animatorHelper6.start();
        }
        stopParticleAnim();
    }

    private void stopParticleAnim() {
        if (getParticleView() == null) {
            return;
        }
        getParticleView().setVisibility(8);
        getParticleView().stopAnim();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAllView() {
        boolean isFanSubSwitchOpen = isFanSubSwitchOpen(DB_SWITCH_FAN_OFF_ON, 1);
        boolean isFanSubSwitchOpen2 = isFanSubSwitchOpen(DB_SWITCH_FAN_AUTO_MODE, 1);
        this.mFanSwitchEnabled = isFanSubSwitchOpen;
        updateViewEnableState(isFanSubSwitchOpen, isFanSubSwitchOpen2);
    }

    private void updateAnimation(ObjectAnimator objectAnimator, int i, float f, float f2, Interpolator interpolator) {
        objectAnimator.setDuration(i);
        objectAnimator.setFloatValues(f, f2);
        objectAnimator.setInterpolator(interpolator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewEnableState(boolean z, boolean z2) {
        setAutoMode(true, z2);
        setTextColorAlpha(this.mAutoTitle, this.mAutoSummary, true, z2);
        setTextColorAlpha(this.mManualTitle, this.mManualSummary, true, !z2);
        if (z2) {
            startFanAnimation(7);
        } else {
            startFanAnimation(getFanSpeed());
        }
    }

    public void changeMode(int i) {
        if (i < 0 || i >= 3) {
            return;
        }
        if (i == 0) {
            setFanSpeed(3);
        } else if (i == 1) {
            setFanSpeed(4);
        } else if (i == 2) {
            setFanSpeed(5);
        }
        updateAllView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = getActivity();
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.gcs_gamecenter_fragment_fan, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.mContext.getContentResolver().unregisterContentObserver(this.mGameFanSwitchChangeObserver);
        stopFanAnimation();
    }

    @Override // cn.nubia.gamecenter.settings.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor(DB_SWITCH_FAN_OFF_ON), true, this.mGameFanSwitchChangeObserver);
        updateAllView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onResume();
        releaseAnimatorRes();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.m_root = view;
        ((TextView) view.findViewById(android.R.id.title)).setText(R.string.gcs_game_fan_title);
        ((TextView) view.findViewById(android.R.id.summary)).setText(R.string.gcs_game_fan_sumarry);
        this.m_fanStop = view.findViewById(R.id.fan_fengshan);
        this.mWidgetLayout = (LinearLayout) view.findViewById(android.R.id.widget_frame);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.gcs_preference_widget_switch, (ViewGroup) null);
        this.mSwitchOn = (TextView) inflate.findViewById(R.id.gamespace_switch_on_id);
        this.mSwitchOff = (TextView) inflate.findViewById(R.id.gamespace_switch_off_id);
        ToggleButton toggleButton = (ToggleButton) inflate.findViewById(R.id.gamespace_switch);
        this.mFanSwitch = toggleButton;
        toggleButton.setClickable(true);
        this.mWidgetLayout.addView(inflate);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.gcs_switch_layout);
        this.mSwitchLayout = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.fan.FanFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (FanFragment.this.mFanSwitch.isChecked()) {
                    FanFragment.this.mFanSwitch.setChecked(false);
                    NubiaTrackManager.getInstance().sendEvent("com.android.settings", "gamespace_fan_switch", "switch_on", false);
                } else {
                    FanFragment.this.mFanSwitch.setChecked(true);
                    NubiaTrackManager.getInstance().sendEvent("com.android.settings", "gamespace_fan_switch", "switch_on", true);
                }
            }
        });
        this.mFanSwitch.setOnCheckedChangeListener(this.mListener);
        this.mAutoPreference = view.findViewById(R.id.fan_auto_preference);
        this.mAutoTextView = (TextView) view.findViewById(R.id.auto_btn);
        this.mAutoTitle = (TextView) view.findViewById(R.id.auto_title);
        this.mAutoSummary = (TextView) view.findViewById(R.id.auto_summary);
        this.mManualPreference = view.findViewById(R.id.fan_manual_preference);
        this.mManualTextView = (TextView) view.findViewById(R.id.manual_btn);
        this.mManualTitle = (TextView) view.findViewById(R.id.manual_title);
        this.mManualSummary = (TextView) view.findViewById(R.id.manual_summary);
        this.mAutoPreference.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.fan.FanFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FanFragment.this.onAutoCheckChande(view2.getId());
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_fan_mode_status", "风扇模式", "智能调节");
            }
        });
        this.mManualPreference.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.fan.FanFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                FanFragment.this.onAutoCheckChande(view2.getId());
                NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", "gamespace_fan_mode_status", "风扇模式", "极速散热");
            }
        });
    }
}
