package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.dagger.internal.Factory;
import com.google.android.datatransport.runtime.dagger.internal.Preconditions;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class ExecutionModule_ExecutorFactory implements Factory<Executor> {

    private static final class InstanceHolder {

        /* renamed from: a, reason: collision with root package name */
        private static final ExecutionModule_ExecutorFactory f10222a = new ExecutionModule_ExecutorFactory();
    }

    public static ExecutionModule_ExecutorFactory a() {
        return InstanceHolder.f10222a;
    }

    public static Executor b() {
        return (Executor) Preconditions.c(ExecutionModule.a(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Executor get() {
        return b();
    }
}
