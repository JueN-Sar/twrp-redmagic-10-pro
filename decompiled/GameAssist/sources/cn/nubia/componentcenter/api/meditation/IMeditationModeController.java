package cn.nubia.componentcenter.api.meditation;

import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public interface IMeditationModeController extends IModuleProxy<MeditationModeCallback> {

    public interface MeditationModeCallback extends IModuleProxy.ICallback<IMeditationModeController> {
        void onMeditationModeCallback(int i2);

        @Override // com.zte.gameassist.common.IModuleProxy.ICallback
        default void onChanged(IMeditationModeController iMeditationModeController) {
            onMeditationModeCallback(iMeditationModeController.getMeditationMode());
        }
    }

    int getMeditationMode();

    void setMeditationMode(int i2);
}
