package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.DoNotInline;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.lifecycle.ReportFragment;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
/* loaded from: classes.dex */
public final class ProcessLifecycleOwner implements LifecycleOwner {

    /* renamed from: o, reason: collision with root package name */
    public static final Companion f4340o = new Companion(null);

    /* renamed from: p, reason: collision with root package name */
    private static final ProcessLifecycleOwner f4341p = new ProcessLifecycleOwner();

    /* renamed from: c, reason: collision with root package name */
    private int f4342c;

    /* renamed from: h, reason: collision with root package name */
    private int f4343h;

    /* renamed from: k, reason: collision with root package name */
    private Handler f4346k;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4344i = true;

    /* renamed from: j, reason: collision with root package name */
    private boolean f4345j = true;

    /* renamed from: l, reason: collision with root package name */
    private final LifecycleRegistry f4347l = new LifecycleRegistry(this);

    /* renamed from: m, reason: collision with root package name */
    private final Runnable f4348m = new Runnable() { // from class: androidx.lifecycle.c
        @Override // java.lang.Runnable
        public final void run() {
            ProcessLifecycleOwner.j(ProcessLifecycleOwner.this);
        }
    };

    /* renamed from: n, reason: collision with root package name */
    private final ReportFragment.ActivityInitializationListener f4349n = new ReportFragment.ActivityInitializationListener() { // from class: androidx.lifecycle.ProcessLifecycleOwner$initializationListener$1
        @Override // androidx.lifecycle.ReportFragment.ActivityInitializationListener
        public void a() {
            ProcessLifecycleOwner.this.f();
        }

        @Override // androidx.lifecycle.ReportFragment.ActivityInitializationListener
        public void b() {
            ProcessLifecycleOwner.this.e();
        }

        @Override // androidx.lifecycle.ReportFragment.ActivityInitializationListener
        public void onCreate() {
        }
    };

    @RequiresApi
    @Metadata
    public static final class Api29Impl {

        /* renamed from: a, reason: collision with root package name */
        public static final Api29Impl f4350a = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        @DoNotInline
        public static final void a(@NotNull Activity activity, @NotNull Application.ActivityLifecycleCallbacks callback) {
            Intrinsics.e(activity, "activity");
            Intrinsics.e(callback, "callback");
            activity.registerActivityLifecycleCallbacks(callback);
        }
    }

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void getTIMEOUT_MS$lifecycle_process_release$annotations() {
        }

        public final LifecycleOwner a() {
            return ProcessLifecycleOwner.f4341p;
        }

        public final void b(Context context) {
            Intrinsics.e(context, "context");
            ProcessLifecycleOwner.f4341p.h(context);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private ProcessLifecycleOwner() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void j(ProcessLifecycleOwner this$0) {
        Intrinsics.e(this$0, "this$0");
        this$0.k();
        this$0.l();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        return this.f4347l;
    }

    public final void d() {
        int i2 = this.f4343h - 1;
        this.f4343h = i2;
        if (i2 == 0) {
            Handler handler = this.f4346k;
            Intrinsics.b(handler);
            handler.postDelayed(this.f4348m, 700L);
        }
    }

    public final void e() {
        int i2 = this.f4343h + 1;
        this.f4343h = i2;
        if (i2 == 1) {
            if (this.f4344i) {
                this.f4347l.h(Lifecycle.Event.ON_RESUME);
                this.f4344i = false;
            } else {
                Handler handler = this.f4346k;
                Intrinsics.b(handler);
                handler.removeCallbacks(this.f4348m);
            }
        }
    }

    public final void f() {
        int i2 = this.f4342c + 1;
        this.f4342c = i2;
        if (i2 == 1 && this.f4345j) {
            this.f4347l.h(Lifecycle.Event.ON_START);
            this.f4345j = false;
        }
    }

    public final void g() {
        this.f4342c--;
        l();
    }

    public final void h(Context context) {
        Intrinsics.e(context, "context");
        this.f4346k = new Handler();
        this.f4347l.h(Lifecycle.Event.ON_CREATE);
        Context applicationContext = context.getApplicationContext();
        Intrinsics.c(applicationContext, "null cannot be cast to non-null type android.app.Application");
        ((Application) applicationContext).registerActivityLifecycleCallbacks(new EmptyActivityLifecycleCallbacks() { // from class: androidx.lifecycle.ProcessLifecycleOwner$attach$1
            @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
                Intrinsics.e(activity, "activity");
            }

            @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(@NotNull Activity activity) {
                Intrinsics.e(activity, "activity");
                ProcessLifecycleOwner.this.d();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            @RequiresApi
            public void onActivityPreCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
                Intrinsics.e(activity, "activity");
                final ProcessLifecycleOwner processLifecycleOwner = ProcessLifecycleOwner.this;
                ProcessLifecycleOwner.Api29Impl.a(activity, new EmptyActivityLifecycleCallbacks() { // from class: androidx.lifecycle.ProcessLifecycleOwner$attach$1$onActivityPreCreated$1
                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPostResumed(@NotNull Activity activity2) {
                        Intrinsics.e(activity2, "activity");
                        ProcessLifecycleOwner.this.e();
                    }

                    @Override // android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityPostStarted(@NotNull Activity activity2) {
                        Intrinsics.e(activity2, "activity");
                        ProcessLifecycleOwner.this.f();
                    }
                });
            }

            @Override // androidx.lifecycle.EmptyActivityLifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(@NotNull Activity activity) {
                Intrinsics.e(activity, "activity");
                ProcessLifecycleOwner.this.g();
            }
        });
    }

    public final void k() {
        if (this.f4343h == 0) {
            this.f4344i = true;
            this.f4347l.h(Lifecycle.Event.ON_PAUSE);
        }
    }

    public final void l() {
        if (this.f4342c == 0 && this.f4344i) {
            this.f4347l.h(Lifecycle.Event.ON_STOP);
            this.f4345j = true;
        }
    }
}
