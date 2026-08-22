package com.zte.gameassist.common;

import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.IModuleProxy.ICallback;

/* loaded from: classes2.dex */
public interface ISliderProxy<T extends IModuleProxy.ICallback> extends IModuleProxy<T> {
    int getMax();

    int getProgress();

    void startTrackingTouch(IModuleProxy.ICallback iCallback);

    void stopTrackingTouch(IModuleProxy.ICallback iCallback);
}
