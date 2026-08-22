package cn.nubia.componentcenter.api.dessert;

import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.ISliderProxy;

/* loaded from: classes.dex */
public interface IAppBrightnessProxy extends ISliderProxy {
    void changeBrightnessMode();

    float convertToBrightVal(float f2);

    float getBrightnessValue();

    @Override // com.zte.gameassist.common.ISliderProxy
    /* synthetic */ int getMax();

    @Override // com.zte.gameassist.common.ISliderProxy
    /* synthetic */ int getProgress();

    void setBrightnessValue(float f2);

    @Override // com.zte.gameassist.common.IModuleProxy
    /* synthetic */ void setListening(boolean z, IModuleProxy.ICallback iCallback);

    /* synthetic */ void setProgress(int i2, boolean z);

    @Override // com.zte.gameassist.common.ISliderProxy
    /* synthetic */ void startTrackingTouch(IModuleProxy.ICallback iCallback);

    @Override // com.zte.gameassist.common.ISliderProxy
    /* synthetic */ void stopTrackingTouch(IModuleProxy.ICallback iCallback);
}
