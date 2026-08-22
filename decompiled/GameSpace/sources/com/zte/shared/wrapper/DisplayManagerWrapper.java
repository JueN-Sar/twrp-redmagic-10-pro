package com.zte.shared.wrapper;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.DisplayInfo;
import android.view.WindowManager;
import com.zte.feature.Feature;

/* loaded from: classes2.dex */
public class DisplayManagerWrapper {
    private static final String TAG = "DisplayManagerWrapper";
    private DisplayManager mDisplayManager;
    private final int mMaximumBacklight;
    private final int mMinimumBacklight;
    public static final boolean ZTE_FEATURE_MIRROR_PROJECTION_SCREEN = Feature.getBoolean("ZTE_FEATURE_MIRROR_PROJECTION_SCREEN", false);
    public static final boolean ZTE_FEATURE_EXPAND_PROJECTION_SCREEN = Feature.getBoolean("ZTE_FEATURE_EXPAND_PROJECTION_SCREEN", false);

    public DisplayManagerWrapper(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mMinimumBacklight = powerManager.getMinimumScreenBrightnessSetting();
        this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
    }

    public static boolean isUnable(Context context) {
        int activeDisplayState = ((DisplayManager) context.getSystemService("display")).getWifiDisplayStatus().getActiveDisplayState();
        return activeDisplayState == 2 || activeDisplayState == 1;
    }

    public static void setCmdToDisplay(DisplayManager displayManager, int i) {
        displayManager.setCmdToDisplay(i, 0, 0, null);
    }

    public static void setCmdToDisplay(DisplayManager displayManager, int i, int i2, int i3, Bundle bundle) {
        displayManager.setCmdToDisplay(i, i2, i3, bundle);
    }

    public int[] getDisplayInfo(Context context) {
        DisplayInfo displayInfo = new DisplayInfo();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getDisplayInfo(displayInfo);
        return new int[]{displayInfo.logicalHeight, displayInfo.logicalWidth};
    }

    public int getMaximumBacklight() {
        return this.mMaximumBacklight;
    }

    public int getMinimumBacklight() {
        return this.mMinimumBacklight;
    }

    public int getRotation(Context context) {
        return context.getDisplay().getRotation();
    }

    public void setBrightness(int i) {
        setBrightness(0, i);
    }

    public void setBrightness(int i, int i2) {
        this.mDisplayManager.setTemporaryBrightness(i, i2);
    }

    public void setBrightnessAdj(float f) {
        this.mDisplayManager.setTemporaryAutoBrightnessAdjustment(f);
    }
}
