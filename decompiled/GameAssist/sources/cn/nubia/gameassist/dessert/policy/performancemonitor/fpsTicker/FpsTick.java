package cn.nubia.gameassist.dessert.policy.performancemonitor.fpsTicker;

import com.zte.performance.fpsTicker.FpsDetectManager;
import com.zte.performance.fpsTicker.IFpsTickCallback;

/* loaded from: classes.dex */
public class FpsTick extends IFpsTickCallback.Stub {
    private FpsDetectManager mFpsDetectManager = FpsDetectManager.getInstance();
    private boolean mIsRegister;

    public void onFps(int i2, float f2, String str, String str2) {
    }

    public void onServiceDestroy() {
    }

    public boolean register() {
        FpsDetectManager fpsDetectManager = this.mFpsDetectManager;
        if (fpsDetectManager == null || this.mIsRegister) {
            return false;
        }
        fpsDetectManager.registerCallback(this);
        this.mIsRegister = true;
        return true;
    }

    public void unRegister() {
        FpsDetectManager fpsDetectManager;
        if (!this.mIsRegister || (fpsDetectManager = this.mFpsDetectManager) == null) {
            return;
        }
        this.mIsRegister = false;
        fpsDetectManager.unRegisterCallback(this);
    }
}
