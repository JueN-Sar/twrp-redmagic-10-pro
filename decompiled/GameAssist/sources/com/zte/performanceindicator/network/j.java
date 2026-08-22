package com.zte.performanceindicator.network;

import com.zte.performanceindicator.network.NetworkLatencyCheck;
import java.util.function.ToLongFunction;

/* loaded from: classes2.dex */
public final /* synthetic */ class j implements ToLongFunction {
    @Override // java.util.function.ToLongFunction
    public final long applyAsLong(Object obj) {
        return ((NetworkLatencyCheck.Measurement) obj).c();
    }
}
