package cn.nubia.componentcenter.api.power;

import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public interface IPowerStateMonitor extends IModuleProxy<PowerStateCallback> {

    public interface PowerStateCallback extends IModuleProxy.ICallback<IPowerStateMonitor> {
        void onGotoSleep();

        void onWakingUp();

        @Override // com.zte.gameassist.common.IModuleProxy.ICallback
        default void onChanged(IPowerStateMonitor iPowerStateMonitor) {
            if (iPowerStateMonitor.getPowerState() == 1) {
                onWakingUp();
            } else if (iPowerStateMonitor.getPowerState() == 3) {
                onGotoSleep();
            }
        }
    }

    int getPowerState();
}
