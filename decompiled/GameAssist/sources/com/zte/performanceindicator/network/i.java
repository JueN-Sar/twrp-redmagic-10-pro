package com.zte.performanceindicator.network;

import com.zte.performanceindicator.network.NetworkLatencyCheck;
import java.util.function.Function;

/* loaded from: classes2.dex */
public final /* synthetic */ class i implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((NetworkLatencyCheck.Measurement) obj).d();
    }
}
