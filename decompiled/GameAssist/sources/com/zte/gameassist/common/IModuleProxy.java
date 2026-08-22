package com.zte.gameassist.common;

import com.zte.gameassist.common.IModuleProxy.ICallback;

/* loaded from: classes2.dex */
public interface IModuleProxy<T extends ICallback> {

    public interface ICallback<U extends IModuleProxy> {
        void onChanged(IModuleProxy iModuleProxy);
    }

    void setListening(boolean z, ICallback iCallback);
}
