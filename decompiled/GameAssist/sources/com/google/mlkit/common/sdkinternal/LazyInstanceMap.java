package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.HashMap;
import java.util.Map;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class LazyInstanceMap<K, V> {

    /* renamed from: a, reason: collision with root package name */
    private final Map f15935a = new HashMap();

    protected abstract Object a(Object obj);

    /* JADX WARN: Multi-variable type inference failed */
    public Object b(Object obj) {
        synchronized (this.f15935a) {
            try {
                if (this.f15935a.containsKey(obj)) {
                    return this.f15935a.get(obj);
                }
                Object a2 = a(obj);
                this.f15935a.put(obj, a2);
                return a2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
