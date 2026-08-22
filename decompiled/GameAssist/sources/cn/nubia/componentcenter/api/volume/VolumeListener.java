package cn.nubia.componentcenter.api.volume;

import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public interface VolumeListener extends IModuleProxy.ICallback<IVolumeController> {
    void onVolumeChanged(int i2, int i3);

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    default void onChanged(IVolumeController iVolumeController) {
        onVolumeChanged(iVolumeController.getProgress(), iVolumeController.getMax());
    }
}
