package com.zte.gameassist.common;

import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.IModuleProxy.ICallback;

/* loaded from: classes2.dex */
public interface ITileProxy<T extends IModuleProxy.ICallback> extends IModuleProxy<T> {
    default void a(boolean z) {
    }

    boolean b();

    boolean c();

    boolean d();
}
