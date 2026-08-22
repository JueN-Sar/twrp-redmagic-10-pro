package androidx.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata
@RestrictTo
/* loaded from: classes.dex */
public class ReportFragment extends Fragment {

    /* renamed from: h, reason: collision with root package name */
    public static final Companion f4354h = new Companion(null);

    /* renamed from: c, reason: collision with root package name */
    private ActivityInitializationListener f4355c;

    @Metadata
    public interface ActivityInitializationListener {
        void a();

        void b();

        void onCreate();
    }

    @Metadata
    public static final class Companion {
        private Companion() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final void a(Activity activity, Lifecycle.Event event) {
            Intrinsics.e(activity, "activity");
            Intrinsics.e(event, "event");
            if (activity instanceof LifecycleRegistryOwner) {
                ((LifecycleRegistryOwner) activity).a().h(event);
            } else if (activity instanceof LifecycleOwner) {
                Lifecycle a2 = ((LifecycleOwner) activity).a();
                if (a2 instanceof LifecycleRegistry) {
                    ((LifecycleRegistry) a2).h(event);
                }
            }
        }

        public final void b(Activity activity) {
            Intrinsics.e(activity, "activity");
            LifecycleCallbacks.Companion.a(activity);
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragmentManager.beginTransaction().add(new ReportFragment(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
                fragmentManager.executePendingTransactions();
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata
    @RequiresApi
    public static final class LifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

        @NotNull
        public static final Companion Companion = new Companion(null);

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public final void a(Activity activity) {
                Intrinsics.e(activity, "activity");
                activity.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        @JvmStatic
        public static final void registerIn(@NotNull Activity activity) {
            Companion.a(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
            Intrinsics.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostCreated(@NotNull Activity activity, @Nullable Bundle bundle) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.a(activity, Lifecycle.Event.ON_CREATE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.a(activity, Lifecycle.Event.ON_RESUME);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostStarted(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.a(activity, Lifecycle.Event.ON_START);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreDestroyed(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.a(activity, Lifecycle.Event.ON_DESTROY);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPrePaused(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.a(activity, Lifecycle.Event.ON_PAUSE);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPreStopped(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
            ReportFragment.f4354h.a(activity, Lifecycle.Event.ON_STOP);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(@NotNull Activity activity, @NotNull Bundle bundle) {
            Intrinsics.e(activity, "activity");
            Intrinsics.e(bundle, "bundle");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(@NotNull Activity activity) {
            Intrinsics.e(activity, "activity");
        }
    }

    private final void a(Lifecycle.Event event) {
    }

    private final void b(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.onCreate();
        }
    }

    private final void c(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.b();
        }
    }

    private final void d(ActivityInitializationListener activityInitializationListener) {
        if (activityInitializationListener != null) {
            activityInitializationListener.a();
        }
    }

    public static final void e(Activity activity) {
        f4354h.b(activity);
    }

    @Override // android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b(this.f4355c);
        a(Lifecycle.Event.ON_CREATE);
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        a(Lifecycle.Event.ON_DESTROY);
        this.f4355c = null;
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        a(Lifecycle.Event.ON_PAUSE);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        c(this.f4355c);
        a(Lifecycle.Event.ON_RESUME);
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        d(this.f4355c);
        a(Lifecycle.Event.ON_START);
    }

    @Override // android.app.Fragment
    public void onStop() {
        super.onStop();
        a(Lifecycle.Event.ON_STOP);
    }
}
