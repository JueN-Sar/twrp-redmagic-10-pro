package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentRuntime;
import com.google.mlkit.common.internal.MlKitComponentDiscoveryService;
import java.util.concurrent.Executor;

@KeepForSdk
/* loaded from: classes.dex */
public class MlKitContext {

    /* renamed from: b, reason: collision with root package name */
    private static final Object f15939b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static MlKitContext f15940c;

    /* renamed from: a, reason: collision with root package name */
    private ComponentRuntime f15941a;

    private MlKitContext() {
    }

    public static MlKitContext c() {
        MlKitContext mlKitContext;
        synchronized (f15939b) {
            Preconditions.m(f15940c != null, "MlKitContext has not been initialized");
            mlKitContext = (MlKitContext) Preconditions.i(f15940c);
        }
        return mlKitContext;
    }

    public static MlKitContext d(Context context) {
        MlKitContext e2;
        synchronized (f15939b) {
            e2 = e(context, TaskExecutors.f13671a);
        }
        return e2;
    }

    public static MlKitContext e(Context context, Executor executor) {
        MlKitContext mlKitContext;
        synchronized (f15939b) {
            Preconditions.m(f15940c == null, "MlKitContext is already initialized");
            MlKitContext mlKitContext2 = new MlKitContext();
            f15940c = mlKitContext2;
            Context f2 = f(context);
            ComponentRuntime c2 = ComponentRuntime.e(executor).b(ComponentDiscovery.b(f2, MlKitComponentDiscoveryService.class).a()).a(Component.n(f2, Context.class, new Class[0])).a(Component.n(mlKitContext2, MlKitContext.class, new Class[0])).c();
            mlKitContext2.f15941a = c2;
            c2.h(true);
            mlKitContext = f15940c;
        }
        return mlKitContext;
    }

    private static Context f(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext != null ? applicationContext : context;
    }

    public Object a(Class cls) {
        Preconditions.m(f15940c == this, "MlKitContext has been deleted");
        Preconditions.i(this.f15941a);
        return this.f15941a.a(cls);
    }

    public Context b() {
        return (Context) a(Context.class);
    }
}
