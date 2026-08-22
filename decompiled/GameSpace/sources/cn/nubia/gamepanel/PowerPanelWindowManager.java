package cn.nubia.gamepanel;

import android.content.Context;
import android.view.WindowManager;

/* loaded from: classes.dex */
public class PowerPanelWindowManager {
    private static PowerPanelWindowManager instance;
    private PowerPanelDetailsView mPowerPanelDetailsView;

    public static PowerPanelWindowManager getInstance() {
        if (instance == null) {
            instance = new PowerPanelWindowManager();
        }
        return instance;
    }

    public void createPowerPanelView(Context context) {
        if (this.mPowerPanelDetailsView == null) {
            this.mPowerPanelDetailsView = new PowerPanelDetailsView(context);
        }
    }

    public void removePowerPanelView(Context context) {
        if (this.mPowerPanelDetailsView != null) {
            ((WindowManager) context.getSystemService("window")).removeView(this.mPowerPanelDetailsView);
            this.mPowerPanelDetailsView = null;
        }
    }
}
