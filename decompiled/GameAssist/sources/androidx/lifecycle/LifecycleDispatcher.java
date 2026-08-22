package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes.dex */
public final class LifecycleDispatcher {

    /* renamed from: a, reason: collision with root package name */
    public static final LifecycleDispatcher f4299a = new LifecycleDispatcher();

    /* renamed from: b, reason: collision with root package name */
    private static final AtomicBoolean f4300b = new AtomicBoolean(false);

    @Metadata
    @VisibleForTesting
    public static final class DispatcherActivityCallback extends EmptyActivityLifecycleCallbacks {
        @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.b(activity);
        }
    }

    private LifecycleDispatcher() {
    }

    public static final void a(Context context) {
        Intrinsics.e(context, "context");
        if (f4300b.getAndSet(true)) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        Intrinsics.c(applicationContext, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext).registerActivityLifecycleCallbacks(new DispatcherActivityCallback());
    }
}
