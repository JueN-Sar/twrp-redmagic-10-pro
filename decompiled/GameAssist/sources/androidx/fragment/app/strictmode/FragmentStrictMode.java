package androidx.fragment.app.strictmode;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata
/* loaded from: classes.dex */
public final class FragmentStrictMode {

    /* renamed from: a, reason: collision with root package name */
    public static final FragmentStrictMode f4239a = new FragmentStrictMode();

    /* renamed from: b, reason: collision with root package name */
    private static Policy f4240b = Policy.f4242e;

    @Metadata
    public enum Flag {
        PENALTY_LOG,
        PENALTY_DEATH,
        DETECT_FRAGMENT_REUSE,
        DETECT_FRAGMENT_TAG_USAGE,
        DETECT_RETAIN_INSTANCE_USAGE,
        DETECT_SET_USER_VISIBLE_HINT,
        DETECT_TARGET_FRAGMENT_USAGE,
        DETECT_WRONG_FRAGMENT_CONTAINER
    }

    @Metadata
    public interface OnViolationListener {
        void a(Violation violation);
    }

    @Metadata
    public static final class Policy {

        /* renamed from: d, reason: collision with root package name */
        public static final Companion f4241d = new Companion(null);

        /* renamed from: e, reason: collision with root package name */
        public static final Policy f4242e;

        /* renamed from: a, reason: collision with root package name */
        private final Set f4243a;

        /* renamed from: b, reason: collision with root package name */
        private final OnViolationListener f4244b;

        /* renamed from: c, reason: collision with root package name */
        private final Map f4245c;

        @Metadata
        public static final class Builder {
        }

        @Metadata
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }

        static {
            Set d2;
            Map f2;
            d2 = SetsKt__SetsKt.d();
            f2 = MapsKt__MapsKt.f();
            f4242e = new Policy(d2, null, f2);
        }

        public Policy(Set flags, OnViolationListener onViolationListener, Map allowedViolations) {
            Intrinsics.e(flags, "flags");
            Intrinsics.e(allowedViolations, "allowedViolations");
            this.f4243a = flags;
            this.f4244b = onViolationListener;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (Map.Entry entry : allowedViolations.entrySet()) {
                linkedHashMap.put((String) entry.getKey(), (Set) entry.getValue());
            }
            this.f4245c = linkedHashMap;
        }

        public final Set a() {
            return this.f4243a;
        }

        public final OnViolationListener b() {
            return this.f4244b;
        }

        public final Map c() {
            return this.f4245c;
        }
    }

    private FragmentStrictMode() {
    }

    private final Policy c(Fragment fragment) {
        while (fragment != null) {
            if (fragment.m0()) {
                FragmentManager O = fragment.O();
                Intrinsics.d(O, "declaringFragment.parentFragmentManager");
                if (O.G0() != null) {
                    Policy G0 = O.G0();
                    Intrinsics.b(G0);
                    return G0;
                }
            }
            fragment = fragment.N();
        }
        return f4240b;
    }

    private final void d(final Policy policy, final Violation violation) {
        Fragment a2 = violation.a();
        final String name = a2.getClass().getName();
        if (policy.a().contains(Flag.PENALTY_LOG)) {
            Log.d("FragmentStrictMode", "Policy violation in " + name, violation);
        }
        if (policy.b() != null) {
            q(a2, new Runnable() { // from class: b.a
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentStrictMode.e(FragmentStrictMode.Policy.this, violation);
                }
            });
        }
        if (policy.a().contains(Flag.PENALTY_DEATH)) {
            q(a2, new Runnable() { // from class: b.b
                @Override // java.lang.Runnable
                public final void run() {
                    FragmentStrictMode.f(name, violation);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void e(Policy policy, Violation violation) {
        Intrinsics.e(policy, "$policy");
        Intrinsics.e(violation, "$violation");
        policy.b().a(violation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void f(String str, Violation violation) {
        Intrinsics.e(violation, "$violation");
        Log.e("FragmentStrictMode", "Policy violation with PENALTY_DEATH in " + str, violation);
        throw violation;
    }

    private final void g(Violation violation) {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "StrictMode violation in " + violation.a().getClass().getName(), violation);
        }
    }

    public static final void h(Fragment fragment, String previousFragmentId) {
        Intrinsics.e(fragment, "fragment");
        Intrinsics.e(previousFragmentId, "previousFragmentId");
        FragmentReuseViolation fragmentReuseViolation = new FragmentReuseViolation(fragment, previousFragmentId);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(fragmentReuseViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_FRAGMENT_REUSE) && fragmentStrictMode.r(c2, fragment.getClass(), fragmentReuseViolation.getClass())) {
            fragmentStrictMode.d(c2, fragmentReuseViolation);
        }
    }

    public static final void i(Fragment fragment, ViewGroup viewGroup) {
        Intrinsics.e(fragment, "fragment");
        FragmentTagUsageViolation fragmentTagUsageViolation = new FragmentTagUsageViolation(fragment, viewGroup);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(fragmentTagUsageViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_FRAGMENT_TAG_USAGE) && fragmentStrictMode.r(c2, fragment.getClass(), fragmentTagUsageViolation.getClass())) {
            fragmentStrictMode.d(c2, fragmentTagUsageViolation);
        }
    }

    public static final void j(Fragment fragment) {
        Intrinsics.e(fragment, "fragment");
        GetRetainInstanceUsageViolation getRetainInstanceUsageViolation = new GetRetainInstanceUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(getRetainInstanceUsageViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) && fragmentStrictMode.r(c2, fragment.getClass(), getRetainInstanceUsageViolation.getClass())) {
            fragmentStrictMode.d(c2, getRetainInstanceUsageViolation);
        }
    }

    public static final void k(Fragment fragment) {
        Intrinsics.e(fragment, "fragment");
        GetTargetFragmentRequestCodeUsageViolation getTargetFragmentRequestCodeUsageViolation = new GetTargetFragmentRequestCodeUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(getTargetFragmentRequestCodeUsageViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode.r(c2, fragment.getClass(), getTargetFragmentRequestCodeUsageViolation.getClass())) {
            fragmentStrictMode.d(c2, getTargetFragmentRequestCodeUsageViolation);
        }
    }

    public static final void l(Fragment fragment) {
        Intrinsics.e(fragment, "fragment");
        GetTargetFragmentUsageViolation getTargetFragmentUsageViolation = new GetTargetFragmentUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(getTargetFragmentUsageViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode.r(c2, fragment.getClass(), getTargetFragmentUsageViolation.getClass())) {
            fragmentStrictMode.d(c2, getTargetFragmentUsageViolation);
        }
    }

    public static final void m(Fragment fragment) {
        Intrinsics.e(fragment, "fragment");
        SetRetainInstanceUsageViolation setRetainInstanceUsageViolation = new SetRetainInstanceUsageViolation(fragment);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(setRetainInstanceUsageViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_RETAIN_INSTANCE_USAGE) && fragmentStrictMode.r(c2, fragment.getClass(), setRetainInstanceUsageViolation.getClass())) {
            fragmentStrictMode.d(c2, setRetainInstanceUsageViolation);
        }
    }

    public static final void n(Fragment violatingFragment, Fragment targetFragment, int i2) {
        Intrinsics.e(violatingFragment, "violatingFragment");
        Intrinsics.e(targetFragment, "targetFragment");
        SetTargetFragmentUsageViolation setTargetFragmentUsageViolation = new SetTargetFragmentUsageViolation(violatingFragment, targetFragment, i2);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(setTargetFragmentUsageViolation);
        Policy c2 = fragmentStrictMode.c(violatingFragment);
        if (c2.a().contains(Flag.DETECT_TARGET_FRAGMENT_USAGE) && fragmentStrictMode.r(c2, violatingFragment.getClass(), setTargetFragmentUsageViolation.getClass())) {
            fragmentStrictMode.d(c2, setTargetFragmentUsageViolation);
        }
    }

    public static final void o(Fragment fragment, boolean z) {
        Intrinsics.e(fragment, "fragment");
        SetUserVisibleHintViolation setUserVisibleHintViolation = new SetUserVisibleHintViolation(fragment, z);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(setUserVisibleHintViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_SET_USER_VISIBLE_HINT) && fragmentStrictMode.r(c2, fragment.getClass(), setUserVisibleHintViolation.getClass())) {
            fragmentStrictMode.d(c2, setUserVisibleHintViolation);
        }
    }

    public static final void p(Fragment fragment, ViewGroup container) {
        Intrinsics.e(fragment, "fragment");
        Intrinsics.e(container, "container");
        WrongFragmentContainerViolation wrongFragmentContainerViolation = new WrongFragmentContainerViolation(fragment, container);
        FragmentStrictMode fragmentStrictMode = f4239a;
        fragmentStrictMode.g(wrongFragmentContainerViolation);
        Policy c2 = fragmentStrictMode.c(fragment);
        if (c2.a().contains(Flag.DETECT_WRONG_FRAGMENT_CONTAINER) && fragmentStrictMode.r(c2, fragment.getClass(), wrongFragmentContainerViolation.getClass())) {
            fragmentStrictMode.d(c2, wrongFragmentContainerViolation);
        }
    }

    private final void q(Fragment fragment, Runnable runnable) {
        if (!fragment.m0()) {
            runnable.run();
            return;
        }
        Handler s2 = fragment.O().A0().s();
        Intrinsics.d(s2, "fragment.parentFragmentManager.host.handler");
        if (Intrinsics.a(s2.getLooper(), Looper.myLooper())) {
            runnable.run();
        } else {
            s2.post(runnable);
        }
    }

    private final boolean r(Policy policy, Class cls, Class cls2) {
        boolean x;
        Set set = (Set) policy.c().get(cls.getName());
        if (set == null) {
            return true;
        }
        if (!Intrinsics.a(cls2.getSuperclass(), Violation.class)) {
            x = CollectionsKt___CollectionsKt.x(set, cls2.getSuperclass());
            if (x) {
                return false;
            }
        }
        return !set.contains(cls2);
    }

    @VisibleForTesting
    public final void onPolicyViolation(@NotNull Violation violation) {
        Intrinsics.e(violation, "violation");
        g(violation);
        Fragment a2 = violation.a();
        Policy c2 = c(a2);
        if (r(c2, a2.getClass(), violation.getClass())) {
            d(c2, violation);
        }
    }
}
