package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public class ListenerHolders {

    /* renamed from: a, reason: collision with root package name */
    private final Set f10609a;

    public static ListenerHolder a(Object obj, Looper looper, String str) {
        Preconditions.j(obj, "Listener must not be null");
        Preconditions.j(looper, "Looper must not be null");
        Preconditions.j(str, "Listener type must not be null");
        return new ListenerHolder(looper, obj, str);
    }

    public static ListenerHolder b(Object obj, Executor executor, String str) {
        Preconditions.j(obj, "Listener must not be null");
        Preconditions.j(executor, "Executor must not be null");
        Preconditions.j(str, "Listener type must not be null");
        return new ListenerHolder(executor, obj, str);
    }

    public static ListenerHolder.ListenerKey c(Object obj, String str) {
        Preconditions.j(obj, "Listener must not be null");
        Preconditions.j(str, "Listener type must not be null");
        Preconditions.g(str, "Listener type must not be empty");
        return new ListenerHolder.ListenerKey(obj, str);
    }

    public final void d() {
        Iterator it = this.f10609a.iterator();
        while (it.hasNext()) {
            ((ListenerHolder) it.next()).a();
        }
        this.f10609a.clear();
    }
}
