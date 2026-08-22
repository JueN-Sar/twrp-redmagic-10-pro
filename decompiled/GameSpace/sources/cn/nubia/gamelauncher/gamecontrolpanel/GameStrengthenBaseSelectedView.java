package cn.nubia.gamelauncher.gamecontrolpanel;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.controlcenter.MarqueeRadioButton;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.LogUtil;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import com.zte.gameassist.ai.AIFlickerTips;

/* loaded from: classes.dex */
public abstract class GameStrengthenBaseSelectedView extends LinearLayout implements View.OnClickListener {
    private static final String TAG = "GameStrengthenBaseSelectedView";
    protected IGameStrengthSelectedListener mGameStrengthSelectedListener;
    protected TextView vGameStrengthenDesc;
    protected ViewGroup vGameStrengthenGroup;

    public GameStrengthenBaseSelectedView(Context context) {
        this(context, null);
    }

    public GameStrengthenBaseSelectedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GameStrengthenBaseSelectedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private int getStrengthenPerformanceViewId() {
        return GameControlOrientationManager.getInstance().isPortrait() ? R.layout.gamecontrol_strengthen_view_performance_port : R.layout.gamecontrol_strengthen_view_performance;
    }

    private void initListener() {
        int childCount = this.vGameStrengthenGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            this.vGameStrengthenGroup.getChildAt(i).setOnClickListener(this);
        }
    }

    protected abstract int getGameStrengthenDescId();

    protected abstract int getGameStrengthenGroupId();

    protected abstract int getGameStrengthenLayout();

    protected abstract int getGameStrengthenType();

    protected void initView() {
        LayoutInflater.from(getContext()).inflate(getGameStrengthenLayout(), this);
        setOrientation(1);
        this.vGameStrengthenGroup = (ViewGroup) findViewById(getGameStrengthenGroupId());
        this.vGameStrengthenDesc = (TextView) findViewById(getGameStrengthenDescId());
        initListener();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        updateGameStrength(view.getId());
    }

    protected abstract void saveCurrentMode(int i);

    public void setGameStrengthSelectedListener(IGameStrengthSelectedListener iGameStrengthSelectedListener) {
        this.mGameStrengthSelectedListener = iGameStrengthSelectedListener;
    }

    public void showFlicker(View view) {
        String highLightViewId = Utils.getHighLightViewId();
        if (TextUtils.isEmpty(highLightViewId) || view == null) {
            return;
        }
        AIFlickerTips.setFlickerName(view, highLightViewId);
        AIFlickerTips.setFlickerPadding(view, 3, 3, 3, 3);
        AIFlickerTips.showFlicker(highLightViewId);
    }

    protected void updateGameStrength(int i) {
        int childCount = this.vGameStrengthenGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            MarqueeRadioButton marqueeRadioButton = (MarqueeRadioButton) this.vGameStrengthenGroup.getChildAt(i2);
            marqueeRadioButton.setSelected(marqueeRadioButton.getId() == i);
            marqueeRadioButton.setChecked(marqueeRadioButton.getId() == i);
            marqueeRadioButton.onWindowFocusChanged(false);
            if (this.mGameStrengthSelectedListener != null && marqueeRadioButton.getId() == i) {
                if (getGameStrengthenLayout() == getStrengthenPerformanceViewId() && Utils.isLowPowerMode(getContext())) {
                    LogUtil.d(TAG, " performance mode and low power mode, nothing to do ");
                } else {
                    this.mGameStrengthSelectedListener.onGameStrengthSelected(getGameStrengthenType(), i2, null);
                    if (getGameStrengthenLayout() == getStrengthenPerformanceViewId()) {
                        saveCurrentMode(i2);
                    }
                }
                marqueeRadioButton.onWindowFocusChanged(true);
            }
        }
    }
}
