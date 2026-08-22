package com.android.systemui.shared.system;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.PowerManager;
import android.view.DisplayInfo;
import android.view.WindowManager;

/* loaded from: classes2.dex */
public class DisplayManagerWrapper {
    private DisplayManager mDisplayManager;
    private final int mMaximumBacklight;
    private final int mMinimumBacklight;

    public DisplayManagerWrapper(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        this.mMinimumBacklight = powerManager.getMinimumScreenBrightnessSetting();
        this.mMaximumBacklight = powerManager.getMaximumScreenBrightnessSetting();
        this.mDisplayManager = (DisplayManager) context.getSystemService(DisplayManager.class);
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
        this.mDisplayManager.setTemporaryBrightness(i);
    }

    public void setBrightnessAdj(float f) {
        this.mDisplayManager.setTemporaryAutoBrightnessAdjustment(f);
    }
}
