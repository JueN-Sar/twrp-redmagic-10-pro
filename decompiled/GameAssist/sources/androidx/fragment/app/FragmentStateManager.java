package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.SpecialEffectsController;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;
import cn.nubia.multisubscreen.data.TransferData;

/* loaded from: classes.dex */
class FragmentStateManager {

    /* renamed from: a, reason: collision with root package name */
    private final FragmentLifecycleCallbacksDispatcher f4125a;

    /* renamed from: b, reason: collision with root package name */
    private final FragmentStore f4126b;

    /* renamed from: c, reason: collision with root package name */
    private final Fragment f4127c;

    /* renamed from: d, reason: collision with root package name */
    private boolean f4128d = false;

    /* renamed from: e, reason: collision with root package name */
    private int f4129e = -1;

    /* renamed from: androidx.fragment.app.FragmentStateManager$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4132a;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            f4132a = iArr;
            try {
                iArr[Lifecycle.State.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4132a[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4132a[Lifecycle.State.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f4132a[Lifecycle.State.INITIALIZED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment) {
        this.f4125a = fragmentLifecycleCallbacksDispatcher;
        this.f4126b = fragmentStore;
        this.f4127c = fragment;
    }

    private boolean l(View view) {
        if (view == this.f4127c.O) {
            return true;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (parent == this.f4127c.O) {
                return true;
            }
        }
        return false;
    }

    private Bundle q() {
        Bundle bundle = new Bundle();
        this.f4127c.w1(bundle);
        this.f4125a.j(this.f4127c, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.f4127c.O != null) {
            t();
        }
        if (this.f4127c.f3976i != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", this.f4127c.f3976i);
        }
        if (this.f4127c.f3977j != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBundle("android:view_registry_state", this.f4127c.f3977j);
        }
        if (!this.f4127c.Q) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", this.f4127c.Q);
        }
        return bundle;
    }

    void a() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.f4127c);
        }
        Fragment fragment = this.f4127c;
        fragment.c1(fragment.f3975h);
        FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.f4125a;
        Fragment fragment2 = this.f4127c;
        fragmentLifecycleCallbacksDispatcher.a(fragment2, fragment2.f3975h, false);
    }

    void b() {
        int j2 = this.f4126b.j(this.f4127c);
        Fragment fragment = this.f4127c;
        fragment.N.addView(fragment.O, j2);
    }

    void c() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.f4127c);
        }
        Fragment fragment = this.f4127c;
        Fragment fragment2 = fragment.f3981n;
        FragmentStateManager fragmentStateManager = null;
        if (fragment2 != null) {
            FragmentStateManager n2 = this.f4126b.n(fragment2.f3979l);
            if (n2 == null) {
                throw new IllegalStateException("Fragment " + this.f4127c + " declared target fragment " + this.f4127c.f3981n + " that does not belong to this FragmentManager!");
            }
            Fragment fragment3 = this.f4127c;
            fragment3.f3982o = fragment3.f3981n.f3979l;
            fragment3.f3981n = null;
            fragmentStateManager = n2;
        } else {
            String str = fragment.f3982o;
            if (str != null && (fragmentStateManager = this.f4126b.n(str)) == null) {
                throw new IllegalStateException("Fragment " + this.f4127c + " declared target fragment " + this.f4127c.f3982o + " that does not belong to this FragmentManager!");
            }
        }
        if (fragmentStateManager != null) {
            fragmentStateManager.m();
        }
        Fragment fragment4 = this.f4127c;
        fragment4.A = fragment4.z.A0();
        Fragment fragment5 = this.f4127c;
        fragment5.C = fragment5.z.D0();
        this.f4125a.g(this.f4127c, false);
        this.f4127c.d1();
        this.f4125a.b(this.f4127c, false);
    }

    int d() {
        Fragment fragment = this.f4127c;
        if (fragment.z == null) {
            return fragment.f3974c;
        }
        int i2 = this.f4129e;
        int i3 = AnonymousClass2.f4132a[fragment.X.ordinal()];
        if (i3 != 1) {
            i2 = i3 != 2 ? i3 != 3 ? i3 != 4 ? Math.min(i2, -1) : Math.min(i2, 0) : Math.min(i2, 1) : Math.min(i2, 5);
        }
        Fragment fragment2 = this.f4127c;
        if (fragment2.u) {
            if (fragment2.v) {
                i2 = Math.max(this.f4129e, 2);
                View view = this.f4127c.O;
                if (view != null && view.getParent() == null) {
                    i2 = Math.min(i2, 2);
                }
            } else {
                i2 = this.f4129e < 4 ? Math.min(i2, fragment2.f3974c) : Math.min(i2, 1);
            }
        }
        if (!this.f4127c.f3985r) {
            i2 = Math.min(i2, 1);
        }
        Fragment fragment3 = this.f4127c;
        ViewGroup viewGroup = fragment3.N;
        SpecialEffectsController.Operation.LifecycleImpact l2 = viewGroup != null ? SpecialEffectsController.n(viewGroup, fragment3.O()).l(this) : null;
        if (l2 == SpecialEffectsController.Operation.LifecycleImpact.ADDING) {
            i2 = Math.min(i2, 6);
        } else if (l2 == SpecialEffectsController.Operation.LifecycleImpact.REMOVING) {
            i2 = Math.max(i2, 3);
        } else {
            Fragment fragment4 = this.f4127c;
            if (fragment4.f3986s) {
                i2 = fragment4.p0() ? Math.min(i2, 1) : Math.min(i2, -1);
            }
        }
        Fragment fragment5 = this.f4127c;
        if (fragment5.P && fragment5.f3974c < 5) {
            i2 = Math.min(i2, 4);
        }
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + i2 + " for " + this.f4127c);
        }
        return i2;
    }

    void e() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.f4127c);
        }
        Fragment fragment = this.f4127c;
        if (fragment.V) {
            fragment.F1(fragment.f3975h);
            this.f4127c.f3974c = 1;
            return;
        }
        this.f4125a.h(fragment, fragment.f3975h, false);
        Fragment fragment2 = this.f4127c;
        fragment2.g1(fragment2.f3975h);
        FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.f4125a;
        Fragment fragment3 = this.f4127c;
        fragmentLifecycleCallbacksDispatcher.c(fragment3, fragment3.f3975h, false);
    }

    void f() {
        String str;
        if (this.f4127c.u) {
            return;
        }
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f4127c);
        }
        Fragment fragment = this.f4127c;
        LayoutInflater m1 = fragment.m1(fragment.f3975h);
        Fragment fragment2 = this.f4127c;
        ViewGroup viewGroup = fragment2.N;
        if (viewGroup == null) {
            int i2 = fragment2.E;
            if (i2 == 0) {
                viewGroup = null;
            } else {
                if (i2 == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.f4127c + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragment2.z.u0().m(this.f4127c.E);
                if (viewGroup == null) {
                    Fragment fragment3 = this.f4127c;
                    if (!fragment3.w) {
                        try {
                            str = fragment3.U().getResourceName(this.f4127c.E);
                        } catch (Resources.NotFoundException unused) {
                            str = "unknown";
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.f4127c.E) + " (" + str + ") for fragment " + this.f4127c);
                    }
                } else if (!(viewGroup instanceof FragmentContainerView)) {
                    FragmentStrictMode.p(this.f4127c, viewGroup);
                }
            }
        }
        Fragment fragment4 = this.f4127c;
        fragment4.N = viewGroup;
        fragment4.i1(m1, viewGroup, fragment4.f3975h);
        View view = this.f4127c.O;
        if (view != null) {
            view.setSaveFromParentEnabled(false);
            Fragment fragment5 = this.f4127c;
            fragment5.O.setTag(R.id.fragment_container_view_tag, fragment5);
            if (viewGroup != null) {
                b();
            }
            Fragment fragment6 = this.f4127c;
            if (fragment6.G) {
                fragment6.O.setVisibility(8);
            }
            if (ViewCompat.M(this.f4127c.O)) {
                ViewCompat.f0(this.f4127c.O);
            } else {
                final View view2 = this.f4127c.O;
                view2.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.fragment.app.FragmentStateManager.1
                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view3) {
                        view2.removeOnAttachStateChangeListener(this);
                        ViewCompat.f0(view2);
                    }

                    @Override // android.view.View.OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view3) {
                    }
                });
            }
            this.f4127c.z1();
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.f4125a;
            Fragment fragment7 = this.f4127c;
            fragmentLifecycleCallbacksDispatcher.m(fragment7, fragment7.O, fragment7.f3975h, false);
            int visibility = this.f4127c.O.getVisibility();
            this.f4127c.Q1(this.f4127c.O.getAlpha());
            Fragment fragment8 = this.f4127c;
            if (fragment8.N != null && visibility == 0) {
                View findFocus = fragment8.O.findFocus();
                if (findFocus != null) {
                    this.f4127c.K1(findFocus);
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + this.f4127c);
                    }
                }
                this.f4127c.O.setAlpha(0.0f);
            }
        }
        this.f4127c.f3974c = 2;
    }

    void g() {
        Fragment f2;
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.f4127c);
        }
        Fragment fragment = this.f4127c;
        boolean z = true;
        boolean z2 = fragment.f3986s && !fragment.p0();
        if (z2) {
            Fragment fragment2 = this.f4127c;
            if (!fragment2.t) {
                this.f4126b.B(fragment2.f3979l, null);
            }
        }
        if (!z2 && !this.f4126b.p().r(this.f4127c)) {
            String str = this.f4127c.f3982o;
            if (str != null && (f2 = this.f4126b.f(str)) != null && f2.I) {
                this.f4127c.f3981n = f2;
            }
            this.f4127c.f3974c = 0;
            return;
        }
        FragmentHostCallback fragmentHostCallback = this.f4127c.A;
        if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            z = this.f4126b.p().o();
        } else if (fragmentHostCallback.r() instanceof Activity) {
            z = true ^ ((Activity) fragmentHostCallback.r()).isChangingConfigurations();
        }
        if ((z2 && !this.f4127c.t) || z) {
            this.f4126b.p().g(this.f4127c);
        }
        this.f4127c.j1();
        this.f4125a.d(this.f4127c, false);
        for (FragmentStateManager fragmentStateManager : this.f4126b.k()) {
            if (fragmentStateManager != null) {
                Fragment k2 = fragmentStateManager.k();
                if (this.f4127c.f3979l.equals(k2.f3982o)) {
                    k2.f3981n = this.f4127c;
                    k2.f3982o = null;
                }
            }
        }
        Fragment fragment3 = this.f4127c;
        String str2 = fragment3.f3982o;
        if (str2 != null) {
            fragment3.f3981n = this.f4126b.f(str2);
        }
        this.f4126b.s(this);
    }

    void h() {
        View view;
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.f4127c);
        }
        Fragment fragment = this.f4127c;
        ViewGroup viewGroup = fragment.N;
        if (viewGroup != null && (view = fragment.O) != null) {
            viewGroup.removeView(view);
        }
        this.f4127c.k1();
        this.f4125a.n(this.f4127c, false);
        Fragment fragment2 = this.f4127c;
        fragment2.N = null;
        fragment2.O = null;
        fragment2.Z = null;
        fragment2.a0.o(null);
        this.f4127c.v = false;
    }

    void i() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.f4127c);
        }
        this.f4127c.l1();
        this.f4125a.e(this.f4127c, false);
        Fragment fragment = this.f4127c;
        fragment.f3974c = -1;
        fragment.A = null;
        fragment.C = null;
        fragment.z = null;
        if ((!fragment.f3986s || fragment.p0()) && !this.f4126b.p().r(this.f4127c)) {
            return;
        }
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "initState called for fragment: " + this.f4127c);
        }
        this.f4127c.k0();
    }

    void j() {
        Fragment fragment = this.f4127c;
        if (fragment.u && fragment.v && !fragment.x) {
            if (FragmentManager.N0(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.f4127c);
            }
            Fragment fragment2 = this.f4127c;
            fragment2.i1(fragment2.m1(fragment2.f3975h), null, this.f4127c.f3975h);
            View view = this.f4127c.O;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.f4127c;
                fragment3.O.setTag(R.id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.f4127c;
                if (fragment4.G) {
                    fragment4.O.setVisibility(8);
                }
                this.f4127c.z1();
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.f4125a;
                Fragment fragment5 = this.f4127c;
                fragmentLifecycleCallbacksDispatcher.m(fragment5, fragment5.O, fragment5.f3975h, false);
                this.f4127c.f3974c = 2;
            }
        }
    }

    Fragment k() {
        return this.f4127c;
    }

    void m() {
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        ViewGroup viewGroup3;
        if (this.f4128d) {
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + k());
                return;
            }
            return;
        }
        try {
            this.f4128d = true;
            boolean z = false;
            while (true) {
                int d2 = d();
                Fragment fragment = this.f4127c;
                int i2 = fragment.f3974c;
                if (d2 == i2) {
                    if (!z && i2 == -1 && fragment.f3986s && !fragment.p0() && !this.f4127c.t) {
                        if (FragmentManager.N0(3)) {
                            Log.d("FragmentManager", "Cleaning up state of never attached fragment: " + this.f4127c);
                        }
                        this.f4126b.p().g(this.f4127c);
                        this.f4126b.s(this);
                        if (FragmentManager.N0(3)) {
                            Log.d("FragmentManager", "initState called for fragment: " + this.f4127c);
                        }
                        this.f4127c.k0();
                    }
                    Fragment fragment2 = this.f4127c;
                    if (fragment2.T) {
                        if (fragment2.O != null && (viewGroup = fragment2.N) != null) {
                            SpecialEffectsController n2 = SpecialEffectsController.n(viewGroup, fragment2.O());
                            if (this.f4127c.G) {
                                n2.c(this);
                            } else {
                                n2.e(this);
                            }
                        }
                        Fragment fragment3 = this.f4127c;
                        FragmentManager fragmentManager = fragment3.z;
                        if (fragmentManager != null) {
                            fragmentManager.L0(fragment3);
                        }
                        Fragment fragment4 = this.f4127c;
                        fragment4.T = false;
                        fragment4.N0(fragment4.G);
                        this.f4127c.B.L();
                    }
                    this.f4128d = false;
                    return;
                }
                if (d2 <= i2) {
                    switch (i2 - 1) {
                        case -1:
                            i();
                            break;
                        case 0:
                            if (fragment.t && this.f4126b.q(fragment.f3979l) == null) {
                                s();
                            }
                            g();
                            break;
                        case 1:
                            h();
                            this.f4127c.f3974c = 1;
                            break;
                        case 2:
                            fragment.v = false;
                            fragment.f3974c = 2;
                            break;
                        case 3:
                            if (FragmentManager.N0(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.f4127c);
                            }
                            Fragment fragment5 = this.f4127c;
                            if (fragment5.t) {
                                s();
                            } else if (fragment5.O != null && fragment5.f3976i == null) {
                                t();
                            }
                            Fragment fragment6 = this.f4127c;
                            if (fragment6.O != null && (viewGroup2 = fragment6.N) != null) {
                                SpecialEffectsController.n(viewGroup2, fragment6.O()).d(this);
                            }
                            this.f4127c.f3974c = 3;
                            break;
                        case 4:
                            w();
                            break;
                        case 5:
                            fragment.f3974c = 5;
                            break;
                        case 6:
                            n();
                            break;
                    }
                } else {
                    switch (i2 + 1) {
                        case 0:
                            c();
                            break;
                        case 1:
                            e();
                            break;
                        case 2:
                            j();
                            f();
                            break;
                        case 3:
                            a();
                            break;
                        case 4:
                            if (fragment.O != null && (viewGroup3 = fragment.N) != null) {
                                SpecialEffectsController.n(viewGroup3, fragment.O()).b(SpecialEffectsController.Operation.State.d(this.f4127c.O.getVisibility()), this);
                            }
                            this.f4127c.f3974c = 4;
                            break;
                        case 5:
                            v();
                            break;
                        case 6:
                            fragment.f3974c = 6;
                            break;
                        case 7:
                            p();
                            break;
                    }
                }
                z = true;
            }
        } catch (Throwable th) {
            this.f4128d = false;
            throw th;
        }
    }

    void n() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.f4127c);
        }
        this.f4127c.r1();
        this.f4125a.f(this.f4127c, false);
    }

    void o(ClassLoader classLoader) {
        Bundle bundle = this.f4127c.f3975h;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.f4127c;
        fragment.f3976i = fragment.f3975h.getSparseParcelableArray("android:view_state");
        Fragment fragment2 = this.f4127c;
        fragment2.f3977j = fragment2.f3975h.getBundle("android:view_registry_state");
        Fragment fragment3 = this.f4127c;
        fragment3.f3982o = fragment3.f3975h.getString("android:target_state");
        Fragment fragment4 = this.f4127c;
        if (fragment4.f3982o != null) {
            fragment4.f3983p = fragment4.f3975h.getInt("android:target_req_state", 0);
        }
        Fragment fragment5 = this.f4127c;
        Boolean bool = fragment5.f3978k;
        if (bool != null) {
            fragment5.Q = bool.booleanValue();
            this.f4127c.f3978k = null;
        } else {
            fragment5.Q = fragment5.f3975h.getBoolean("android:user_visible_hint", true);
        }
        Fragment fragment6 = this.f4127c;
        if (fragment6.Q) {
            return;
        }
        fragment6.P = true;
    }

    void p() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.f4127c);
        }
        View G = this.f4127c.G();
        if (G != null && l(G)) {
            boolean requestFocus = G.requestFocus();
            if (FragmentManager.N0(2)) {
                StringBuilder sb = new StringBuilder();
                sb.append("requestFocus: Restoring focused view ");
                sb.append(G);
                sb.append(" ");
                sb.append(requestFocus ? "succeeded" : TransferData.MSG_FAILD);
                sb.append(" on Fragment ");
                sb.append(this.f4127c);
                sb.append(" resulting in focused view ");
                sb.append(this.f4127c.O.findFocus());
                Log.v("FragmentManager", sb.toString());
            }
        }
        this.f4127c.K1(null);
        this.f4127c.v1();
        this.f4125a.i(this.f4127c, false);
        Fragment fragment = this.f4127c;
        fragment.f3975h = null;
        fragment.f3976i = null;
        fragment.f3977j = null;
    }

    Fragment.SavedState r() {
        Bundle q2;
        if (this.f4127c.f3974c <= -1 || (q2 = q()) == null) {
            return null;
        }
        return new Fragment.SavedState(q2);
    }

    void s() {
        FragmentState fragmentState = new FragmentState(this.f4127c);
        Fragment fragment = this.f4127c;
        if (fragment.f3974c <= -1 || fragmentState.f4124s != null) {
            fragmentState.f4124s = fragment.f3975h;
        } else {
            Bundle q2 = q();
            fragmentState.f4124s = q2;
            if (this.f4127c.f3982o != null) {
                if (q2 == null) {
                    fragmentState.f4124s = new Bundle();
                }
                fragmentState.f4124s.putString("android:target_state", this.f4127c.f3982o);
                int i2 = this.f4127c.f3983p;
                if (i2 != 0) {
                    fragmentState.f4124s.putInt("android:target_req_state", i2);
                }
            }
        }
        this.f4126b.B(this.f4127c.f3979l, fragmentState);
    }

    void t() {
        if (this.f4127c.O == null) {
            return;
        }
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Saving view state for fragment " + this.f4127c + " with view " + this.f4127c.O);
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.f4127c.O.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.f4127c.f3976i = sparseArray;
        }
        Bundle bundle = new Bundle();
        this.f4127c.Z.f(bundle);
        if (bundle.isEmpty()) {
            return;
        }
        this.f4127c.f3977j = bundle;
    }

    void u(int i2) {
        this.f4129e = i2;
    }

    void v() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.f4127c);
        }
        this.f4127c.x1();
        this.f4125a.k(this.f4127c, false);
    }

    void w() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.f4127c);
        }
        this.f4127c.y1();
        this.f4125a.l(this.f4127c, false);
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, ClassLoader classLoader, FragmentFactory fragmentFactory, FragmentState fragmentState) {
        this.f4125a = fragmentLifecycleCallbacksDispatcher;
        this.f4126b = fragmentStore;
        Fragment a2 = fragmentState.a(fragmentFactory, classLoader);
        this.f4127c = a2;
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + a2);
        }
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, FragmentStore fragmentStore, Fragment fragment, FragmentState fragmentState) {
        this.f4125a = fragmentLifecycleCallbacksDispatcher;
        this.f4126b = fragmentStore;
        this.f4127c = fragment;
        fragment.f3976i = null;
        fragment.f3977j = null;
        fragment.y = 0;
        fragment.v = false;
        fragment.f3985r = false;
        Fragment fragment2 = fragment.f3981n;
        fragment.f3982o = fragment2 != null ? fragment2.f3979l : null;
        fragment.f3981n = null;
        Bundle bundle = fragmentState.f4124s;
        if (bundle != null) {
            fragment.f3975h = bundle;
        } else {
            fragment.f3975h = new Bundle();
        }
    }
}
