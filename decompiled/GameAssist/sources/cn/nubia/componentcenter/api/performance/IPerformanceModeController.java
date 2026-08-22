package cn.nubia.componentcenter.api.performance;

import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.SystemMgr;

/* loaded from: classes.dex */
public interface IPerformanceModeController extends IModuleProxy<PerformanceModeCallback> {

    public interface PerformanceModeCallback extends IModuleProxy.ICallback<IPerformanceModeController> {
        void onDialogDismiss();

        void onPerformanceModeCallback(int i2);

        @Override // com.zte.gameassist.common.IModuleProxy.ICallback
        default void onChanged(IPerformanceModeController iPerformanceModeController) {
            onPerformanceModeCallback(iPerformanceModeController.getPerformanceMode());
        }
    }

    default int getPerformanceMode() {
        return getPerformanceMode(SystemMgr.t());
    }

    int getPerformanceMode(String str);

    default boolean setPerformanceMode(int i2) {
        return setPerformanceMode(SystemMgr.t(), i2);
    }

    boolean setPerformanceMode(String str, int i2);
}
