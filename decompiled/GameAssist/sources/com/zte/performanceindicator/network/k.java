package com.zte.performanceindicator.network;

import com.zte.performanceindicator.network.NetworkLatencyCheck;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final /* synthetic */ class k implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Long.valueOf(((NetworkLatencyCheck.Measurement) obj).c());
    }
}
