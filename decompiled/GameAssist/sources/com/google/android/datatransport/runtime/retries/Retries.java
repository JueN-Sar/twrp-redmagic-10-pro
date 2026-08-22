package com.google.android.datatransport.runtime.retries;

/* loaded from: classes.dex */
public final class Retries {
    public static Object a(int i2, Object obj, Function function, RetryStrategy retryStrategy) {
        Object apply;
        if (i2 < 1) {
            return function.apply(obj);
        }
        do {
            apply = function.apply(obj);
            obj = retryStrategy.a(obj, apply);
            if (obj == null) {
                break;
            }
            i2--;
        } while (i2 >= 1);
        return apply;
    }
}
