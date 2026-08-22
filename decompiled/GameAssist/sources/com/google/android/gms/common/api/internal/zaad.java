package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public final class zaad {

    /* renamed from: a, reason: collision with root package name */
    private final Map f10662a = Collections.synchronizedMap(new WeakHashMap());

    /* renamed from: b, reason: collision with root package name */
    private final Map f10663b = Collections.synchronizedMap(new WeakHashMap());

    private final void h(boolean z, Status status) {
        HashMap hashMap;
        HashMap hashMap2;
        synchronized (this.f10662a) {
            hashMap = new HashMap(this.f10662a);
        }
        synchronized (this.f10663b) {
            hashMap2 = new HashMap(this.f10663b);
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (z || ((Boolean) entry.getValue()).booleanValue()) {
                ((BasePendingResult) entry.getKey()).g(status);
            }
        }
        for (Map.Entry entry2 : hashMap2.entrySet()) {
            if (z || ((Boolean) entry2.getValue()).booleanValue()) {
                ((TaskCompletionSource) entry2.getKey()).d(new ApiException(status));
            }
        }
    }

    final void c(BasePendingResult basePendingResult, boolean z) {
        this.f10662a.put(basePendingResult, Boolean.valueOf(z));
        basePendingResult.b(new zaab(this, basePendingResult));
    }

    final void d(TaskCompletionSource taskCompletionSource, boolean z) {
        this.f10663b.put(taskCompletionSource, Boolean.valueOf(z));
        taskCompletionSource.a().b(new zaac(this, taskCompletionSource));
    }

    final void e(int i2, String str) {
        StringBuilder sb = new StringBuilder("The connection to Google Play services was lost");
        if (i2 == 1) {
            sb.append(" due to service disconnection.");
        } else if (i2 == 3) {
            sb.append(" due to dead object exception.");
        }
        if (str != null) {
            sb.append(" Last reason for disconnect: ");
            sb.append(str);
        }
        h(true, new Status(20, sb.toString()));
    }

    public final void f() {
        h(false, GoogleApiManager.v);
    }

    final boolean g() {
        return (this.f10662a.isEmpty() && this.f10663b.isEmpty()) ? false : true;
    }
}
