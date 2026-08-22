package cn.nubia.systemwrapper;

import android.content.Context;
import com.android.systemui.shared.system.DisplayManagerWrapper;

/* loaded from: classes2.dex */
public class BrightnessWrapper {
    private DisplayManagerWrapper mDisplayManager;

    public BrightnessWrapper(Context context) {
        this.mDisplayManager = new DisplayManagerWrapper(context);
    }

    public int[] getDisplay(Context context) {
        return this.mDisplayManager.getDisplayInfo(context);
    }

    public int getMaximumBacklight() {
        return this.mDisplayManager.getMaximumBacklight();
    }

    public int getMinimumBacklight() {
        return this.mDisplayManager.getMinimumBacklight();
    }

    public int getRotation(Context context) {
        return this.mDisplayManager.getRotation(context);
    }

    public void setBrightness(int i) {
        this.mDisplayManager.setBrightness(i);
    }

    public void setBrightnessAdj(float f) {
        this.mDisplayManager.setBrightnessAdj(f);
    }
}
