package com.google.common.eventbus;

import cn.nubia.gamelauncher.gamecontrolpanel.superresolution.SuperResolutionHelper;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.Executor;

@ElementTypesAreNonnullByDefault
/* loaded from: classes2.dex */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(String str, Executor executor) {
        super(str, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor) {
        super(SuperResolutionHelper.DEFAULT_SUPPORT, executor, Dispatcher.legacyAsync(), EventBus.LoggingHandler.INSTANCE);
    }

    public AsyncEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(SuperResolutionHelper.DEFAULT_SUPPORT, executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }
}
