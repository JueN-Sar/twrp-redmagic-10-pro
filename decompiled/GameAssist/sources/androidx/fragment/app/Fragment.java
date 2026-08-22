package androidx.fragment.app;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.arch.core.util.Function;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ActivityResultCaller {
    static final Object h0 = new Object();
    FragmentHostCallback A;
    Fragment C;
    int D;
    int E;
    String F;
    boolean G;
    boolean H;
    boolean I;
    boolean J;
    boolean K;
    private boolean M;
    ViewGroup N;
    View O;
    boolean P;
    AnimationInfo R;
    boolean T;
    LayoutInflater U;
    boolean V;
    public String W;
    LifecycleRegistry Y;
    FragmentViewLifecycleOwner Z;
    ViewModelProvider.Factory b0;
    SavedStateRegistryController c0;
    private int d0;

    /* renamed from: h, reason: collision with root package name */
    Bundle f3975h;

    /* renamed from: i, reason: collision with root package name */
    SparseArray f3976i;

    /* renamed from: j, reason: collision with root package name */
    Bundle f3977j;

    /* renamed from: k, reason: collision with root package name */
    Boolean f3978k;

    /* renamed from: m, reason: collision with root package name */
    Bundle f3980m;

    /* renamed from: n, reason: collision with root package name */
    Fragment f3981n;

    /* renamed from: p, reason: collision with root package name */
    int f3983p;

    /* renamed from: r, reason: collision with root package name */
    boolean f3985r;

    /* renamed from: s, reason: collision with root package name */
    boolean f3986s;
    boolean t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    int y;
    FragmentManager z;

    /* renamed from: c, reason: collision with root package name */
    int f3974c = -1;

    /* renamed from: l, reason: collision with root package name */
    String f3979l = UUID.randomUUID().toString();

    /* renamed from: o, reason: collision with root package name */
    String f3982o = null;

    /* renamed from: q, reason: collision with root package name */
    private Boolean f3984q = null;
    FragmentManager B = new FragmentManagerImpl();
    boolean L = true;
    boolean Q = true;
    Runnable S = new Runnable() { // from class: androidx.fragment.app.Fragment.1
        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.Y1();
        }
    };
    Lifecycle.State X = Lifecycle.State.RESUMED;
    MutableLiveData a0 = new MutableLiveData();
    private final AtomicInteger e0 = new AtomicInteger();
    private final ArrayList f0 = new ArrayList();
    private final OnPreAttachedListener g0 = new OnPreAttachedListener() { // from class: androidx.fragment.app.Fragment.2
        @Override // androidx.fragment.app.Fragment.OnPreAttachedListener
        void a() {
            Fragment.this.c0.c();
            SavedStateHandleSupport.c(Fragment.this);
        }
    };

    /* renamed from: androidx.fragment.app.Fragment$10, reason: invalid class name */
    class AnonymousClass10 extends ActivityResultLauncher<Object> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ AtomicReference f3988a;

        @Override // androidx.activity.result.ActivityResultLauncher
        public void b(Object obj, ActivityOptionsCompat activityOptionsCompat) {
            ActivityResultLauncher activityResultLauncher = (ActivityResultLauncher) this.f3988a.get();
            if (activityResultLauncher == null) {
                throw new IllegalStateException("Operation cannot be started before fragment is in created state");
            }
            activityResultLauncher.b(obj, activityOptionsCompat);
        }

        @Override // androidx.activity.result.ActivityResultLauncher
        public void c() {
            ActivityResultLauncher activityResultLauncher = (ActivityResultLauncher) this.f3988a.getAndSet(null);
            if (activityResultLauncher != null) {
                activityResultLauncher.c();
            }
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$7, reason: invalid class name */
    class AnonymousClass7 implements Function<Void, ActivityResultRegistry> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Fragment f3995a;

        @Override // androidx.arch.core.util.Function
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ActivityResultRegistry apply(Void r2) {
            Fragment fragment = this.f3995a;
            Object obj = fragment.A;
            return obj instanceof ActivityResultRegistryOwner ? ((ActivityResultRegistryOwner) obj).f() : fragment.C1().f();
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$8, reason: invalid class name */
    class AnonymousClass8 implements Function<Void, ActivityResultRegistry> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ ActivityResultRegistry f3996a;

        @Override // androidx.arch.core.util.Function
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ActivityResultRegistry apply(Void r1) {
            return this.f3996a;
        }
    }

    /* renamed from: androidx.fragment.app.Fragment$9, reason: invalid class name */
    class AnonymousClass9 extends OnPreAttachedListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Function f3997a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ AtomicReference f3998b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ ActivityResultContract f3999c;

        /* renamed from: d, reason: collision with root package name */
        final /* synthetic */ ActivityResultCallback f4000d;

        /* renamed from: e, reason: collision with root package name */
        final /* synthetic */ Fragment f4001e;

        @Override // androidx.fragment.app.Fragment.OnPreAttachedListener
        void a() {
            String r2 = this.f4001e.r();
            this.f3998b.set(((ActivityResultRegistry) this.f3997a.apply(null)).j(r2, this.f4001e, this.f3999c, this.f4000d));
        }
    }

    static class AnimationInfo {

        /* renamed from: a, reason: collision with root package name */
        View f4002a;

        /* renamed from: b, reason: collision with root package name */
        boolean f4003b;

        /* renamed from: c, reason: collision with root package name */
        int f4004c;

        /* renamed from: d, reason: collision with root package name */
        int f4005d;

        /* renamed from: e, reason: collision with root package name */
        int f4006e;

        /* renamed from: f, reason: collision with root package name */
        int f4007f;

        /* renamed from: g, reason: collision with root package name */
        int f4008g;

        /* renamed from: h, reason: collision with root package name */
        ArrayList f4009h;

        /* renamed from: i, reason: collision with root package name */
        ArrayList f4010i;

        /* renamed from: j, reason: collision with root package name */
        Object f4011j = null;

        /* renamed from: k, reason: collision with root package name */
        Object f4012k;

        /* renamed from: l, reason: collision with root package name */
        Object f4013l;

        /* renamed from: m, reason: collision with root package name */
        Object f4014m;

        /* renamed from: n, reason: collision with root package name */
        Object f4015n;

        /* renamed from: o, reason: collision with root package name */
        Object f4016o;

        /* renamed from: p, reason: collision with root package name */
        Boolean f4017p;

        /* renamed from: q, reason: collision with root package name */
        Boolean f4018q;

        /* renamed from: r, reason: collision with root package name */
        SharedElementCallback f4019r;

        /* renamed from: s, reason: collision with root package name */
        SharedElementCallback f4020s;
        float t;
        View u;
        boolean v;

        AnimationInfo() {
            Object obj = Fragment.h0;
            this.f4012k = obj;
            this.f4013l = null;
            this.f4014m = obj;
            this.f4015n = null;
            this.f4016o = obj;
            this.f4019r = null;
            this.f4020s = null;
            this.t = 1.0f;
            this.u = null;
        }
    }

    @RequiresApi
    static class Api19Impl {
        static void a(View view) {
            view.cancelPendingInputEvents();
        }
    }

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(@NonNull String str, @Nullable Exception exc) {
            super(str, exc);
        }
    }

    private static abstract class OnPreAttachedListener {
        private OnPreAttachedListener() {
        }

        abstract void a();
    }

    public Fragment() {
        j0();
    }

    private void B1(OnPreAttachedListener onPreAttachedListener) {
        if (this.f3974c >= 0) {
            onPreAttachedListener.a();
        } else {
            this.f0.add(onPreAttachedListener);
        }
    }

    private void G1() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if (this.O != null) {
            H1(this.f3975h);
        }
        this.f3975h = null;
    }

    private int L() {
        Lifecycle.State state = this.X;
        return (state == Lifecycle.State.INITIALIZED || this.C == null) ? state.ordinal() : Math.min(state.ordinal(), this.C.L());
    }

    private Fragment e0(boolean z) {
        String str;
        if (z) {
            FragmentStrictMode.l(this);
        }
        Fragment fragment = this.f3981n;
        if (fragment != null) {
            return fragment;
        }
        FragmentManager fragmentManager = this.z;
        if (fragmentManager == null || (str = this.f3982o) == null) {
            return null;
        }
        return fragmentManager.i0(str);
    }

    private void j0() {
        this.Y = new LifecycleRegistry(this);
        this.c0 = SavedStateRegistryController.a(this);
        this.b0 = null;
        if (this.f0.contains(this.g0)) {
            return;
        }
        B1(this.g0);
    }

    public static Fragment l0(Context context, String str, Bundle bundle) {
        try {
            Fragment fragment = (Fragment) FragmentFactory.d(context.getClassLoader(), str).getConstructor(null).newInstance(null);
            if (bundle != null) {
                bundle.setClassLoader(fragment.getClass().getClassLoader());
                fragment.J1(bundle);
            }
            return fragment;
        } catch (IllegalAccessException e2) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (java.lang.InstantiationException e3) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new InstantiationException("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    private AnimationInfo p() {
        if (this.R == null) {
            this.R = new AnimationInfo();
        }
        return this.R;
    }

    int A() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.f4004c;
    }

    public void A0(Activity activity) {
        this.M = true;
    }

    public void A1(View view) {
        view.setOnCreateContextMenuListener(this);
    }

    public Object B() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.f4011j;
    }

    public void B0(Context context) {
        this.M = true;
        FragmentHostCallback fragmentHostCallback = this.A;
        Activity q2 = fragmentHostCallback == null ? null : fragmentHostCallback.q();
        if (q2 != null) {
            this.M = false;
            A0(q2);
        }
    }

    SharedElementCallback C() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.f4019r;
    }

    public void C0(Fragment fragment) {
    }

    public final FragmentActivity C1() {
        FragmentActivity t = t();
        if (t != null) {
            return t;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    int D() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.f4005d;
    }

    public boolean D0(MenuItem menuItem) {
        return false;
    }

    public final Context D1() {
        Context z = z();
        if (z != null) {
            return z;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    public Object E() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.f4013l;
    }

    public Animation E0(int i2, boolean z, int i3) {
        return null;
    }

    public final View E1() {
        View h02 = h0();
        if (h02 != null) {
            return h02;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    SharedElementCallback F() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.f4020s;
    }

    public Animator F0(int i2, boolean z, int i3) {
        return null;
    }

    void F1(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.B.r1(parcelable);
        this.B.E();
    }

    View G() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.u;
    }

    public void G0(Menu menu, MenuInflater menuInflater) {
    }

    public final FragmentManager H() {
        return this.z;
    }

    public View H0(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i2 = this.d0;
        if (i2 != 0) {
            return layoutInflater.inflate(i2, viewGroup, false);
        }
        return null;
    }

    final void H1(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f3976i;
        if (sparseArray != null) {
            this.O.restoreHierarchyState(sparseArray);
            this.f3976i = null;
        }
        if (this.O != null) {
            this.Z.e(this.f3977j);
            this.f3977j = null;
        }
        this.M = false;
        b1(bundle);
        if (this.M) {
            if (this.O != null) {
                this.Z.b(Lifecycle.Event.ON_CREATE);
            }
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    public final Object I() {
        FragmentHostCallback fragmentHostCallback = this.A;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.x();
    }

    public void I0() {
        this.M = true;
    }

    void I1(int i2, int i3, int i4, int i5) {
        if (this.R == null && i2 == 0 && i3 == 0 && i4 == 0 && i5 == 0) {
            return;
        }
        p().f4004c = i2;
        p().f4005d = i3;
        p().f4006e = i4;
        p().f4007f = i5;
    }

    public final int J() {
        return this.D;
    }

    public void J0() {
    }

    public void J1(Bundle bundle) {
        if (this.z != null && v0()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.f3980m = bundle;
    }

    public LayoutInflater K(Bundle bundle) {
        FragmentHostCallback fragmentHostCallback = this.A;
        if (fragmentHostCallback == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater y = fragmentHostCallback.y();
        LayoutInflaterCompat.a(y, this.B.B0());
        return y;
    }

    public void K0() {
        this.M = true;
    }

    void K1(View view) {
        p().u = view;
    }

    public void L0() {
        this.M = true;
    }

    public void L1(boolean z) {
        if (this.K != z) {
            this.K = z;
            if (!m0() || o0()) {
                return;
            }
            this.A.A();
        }
    }

    int M() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.f4008g;
    }

    public LayoutInflater M0(Bundle bundle) {
        return K(bundle);
    }

    public void M1(SavedState savedState) {
        Bundle bundle;
        if (this.z != null) {
            throw new IllegalStateException("Fragment already added");
        }
        if (savedState == null || (bundle = savedState.f4021c) == null) {
            bundle = null;
        }
        this.f3975h = bundle;
    }

    public final Fragment N() {
        return this.C;
    }

    public void N0(boolean z) {
    }

    public void N1(boolean z) {
        if (this.L != z) {
            this.L = z;
            if (this.K && m0() && !o0()) {
                this.A.A();
            }
        }
    }

    public final FragmentManager O() {
        FragmentManager fragmentManager = this.z;
        if (fragmentManager != null) {
            return fragmentManager;
        }
        throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
    }

    public void O0(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.M = true;
    }

    void O1(int i2) {
        if (this.R == null && i2 == 0) {
            return;
        }
        p();
        this.R.f4008g = i2;
    }

    boolean P() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return false;
        }
        return animationInfo.f4003b;
    }

    public void P0(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.M = true;
        FragmentHostCallback fragmentHostCallback = this.A;
        Activity q2 = fragmentHostCallback == null ? null : fragmentHostCallback.q();
        if (q2 != null) {
            this.M = false;
            O0(q2, attributeSet, bundle);
        }
    }

    void P1(boolean z) {
        if (this.R == null) {
            return;
        }
        p().f4003b = z;
    }

    int Q() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.f4006e;
    }

    public void Q0(boolean z) {
    }

    void Q1(float f2) {
        p().t = f2;
    }

    int R() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return 0;
        }
        return animationInfo.f4007f;
    }

    public boolean R0(MenuItem menuItem) {
        return false;
    }

    public void R1(boolean z) {
        FragmentStrictMode.m(this);
        this.I = z;
        FragmentManager fragmentManager = this.z;
        if (fragmentManager == null) {
            this.J = true;
        } else if (z) {
            fragmentManager.l(this);
        } else {
            fragmentManager.o1(this);
        }
    }

    float S() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return 1.0f;
        }
        return animationInfo.t;
    }

    public void S0(Menu menu) {
    }

    void S1(ArrayList arrayList, ArrayList arrayList2) {
        p();
        AnimationInfo animationInfo = this.R;
        animationInfo.f4009h = arrayList;
        animationInfo.f4010i = arrayList2;
    }

    public Object T() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.f4014m;
        return obj == h0 ? E() : obj;
    }

    public void T0(boolean z) {
    }

    public void T1(Fragment fragment, int i2) {
        if (fragment != null) {
            FragmentStrictMode.n(this, fragment, i2);
        }
        FragmentManager fragmentManager = this.z;
        FragmentManager fragmentManager2 = fragment != null ? fragment.z : null;
        if (fragmentManager != null && fragmentManager2 != null && fragmentManager != fragmentManager2) {
            throw new IllegalArgumentException("Fragment " + fragment + " must share the same FragmentManager to be set as a target fragment");
        }
        for (Fragment fragment2 = fragment; fragment2 != null; fragment2 = fragment2.e0(false)) {
            if (fragment2.equals(this)) {
                throw new IllegalArgumentException("Setting " + fragment + " as the target of " + this + " would create a target cycle");
            }
        }
        if (fragment == null) {
            this.f3982o = null;
            this.f3981n = null;
        } else if (this.z == null || fragment.z == null) {
            this.f3982o = null;
            this.f3981n = fragment;
        } else {
            this.f3982o = fragment.f3979l;
            this.f3981n = null;
        }
        this.f3983p = i2;
    }

    public final Resources U() {
        return D1().getResources();
    }

    public void U0(Menu menu) {
    }

    public void U1(boolean z) {
        FragmentStrictMode.o(this, z);
        if (!this.Q && z && this.f3974c < 5 && this.z != null && m0() && this.V) {
            FragmentManager fragmentManager = this.z;
            fragmentManager.e1(fragmentManager.y(this));
        }
        this.Q = z;
        this.P = this.f3974c < 5 && !z;
        if (this.f3975h != null) {
            this.f3978k = Boolean.valueOf(z);
        }
    }

    public final boolean V() {
        FragmentStrictMode.j(this);
        return this.I;
    }

    public void V0(boolean z) {
    }

    public void V1(Intent intent) {
        W1(intent, null);
    }

    public Object W() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.f4012k;
        return obj == h0 ? B() : obj;
    }

    public void W0(int i2, String[] strArr, int[] iArr) {
    }

    public void W1(Intent intent, Bundle bundle) {
        FragmentHostCallback fragmentHostCallback = this.A;
        if (fragmentHostCallback != null) {
            fragmentHostCallback.z(this, intent, -1, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public Object X() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.f4015n;
    }

    public void X0(Bundle bundle) {
    }

    public void X1(Intent intent, int i2, Bundle bundle) {
        if (this.A != null) {
            O().a1(this, intent, i2, bundle);
            return;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to Activity");
    }

    public Object Y() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        Object obj = animationInfo.f4016o;
        return obj == h0 ? X() : obj;
    }

    public void Y0() {
        this.M = true;
    }

    public void Y1() {
        if (this.R == null || !p().v) {
            return;
        }
        if (this.A == null) {
            p().v = false;
        } else if (Looper.myLooper() != this.A.s().getLooper()) {
            this.A.s().postAtFrontOfQueue(new Runnable() { // from class: androidx.fragment.app.Fragment.3
                @Override // java.lang.Runnable
                public void run() {
                    Fragment.this.l(false);
                }
            });
        } else {
            l(true);
        }
    }

    ArrayList Z() {
        ArrayList arrayList;
        AnimationInfo animationInfo = this.R;
        return (animationInfo == null || (arrayList = animationInfo.f4009h) == null) ? new ArrayList() : arrayList;
    }

    public void Z0() {
        this.M = true;
    }

    public void Z1(View view) {
        view.setOnCreateContextMenuListener(null);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle a() {
        return this.Y;
    }

    ArrayList a0() {
        ArrayList arrayList;
        AnimationInfo animationInfo = this.R;
        return (animationInfo == null || (arrayList = animationInfo.f4010i) == null) ? new ArrayList() : arrayList;
    }

    public void a1(View view, Bundle bundle) {
    }

    public final String b0(int i2) {
        return U().getString(i2);
    }

    public void b1(Bundle bundle) {
        this.M = true;
    }

    public final String c0() {
        return this.F;
    }

    void c1(Bundle bundle) {
        this.B.c1();
        this.f3974c = 3;
        this.M = false;
        y0(bundle);
        if (this.M) {
            G1();
            this.B.A();
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
        }
    }

    public final Fragment d0() {
        return e0(true);
    }

    void d1() {
        Iterator it = this.f0.iterator();
        while (it.hasNext()) {
            ((OnPreAttachedListener) it.next()).a();
        }
        this.f0.clear();
        this.B.n(this.A, m(), this);
        this.f3974c = 0;
        this.M = false;
        B0(this.A.r());
        if (this.M) {
            this.z.K(this);
            this.B.B();
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
        }
    }

    void e1(Configuration configuration) {
        onConfigurationChanged(configuration);
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int f0() {
        FragmentStrictMode.k(this);
        return this.f3983p;
    }

    boolean f1(MenuItem menuItem) {
        if (this.G) {
            return false;
        }
        if (D0(menuItem)) {
            return true;
        }
        return this.B.D(menuItem);
    }

    public boolean g0() {
        return this.Q;
    }

    void g1(Bundle bundle) {
        this.B.c1();
        this.f3974c = 1;
        this.M = false;
        this.Y.a(new LifecycleEventObserver() { // from class: androidx.fragment.app.Fragment.6
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                View view;
                if (event != Lifecycle.Event.ON_STOP || (view = Fragment.this.O) == null) {
                    return;
                }
                Api19Impl.a(view);
            }
        });
        this.c0.d(bundle);
        onCreate(bundle);
        this.V = true;
        if (this.M) {
            this.Y.h(Lifecycle.Event.ON_CREATE);
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    @Override // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore h() {
        if (this.z == null) {
            throw new IllegalStateException("Can't access ViewModels from detached fragment");
        }
        if (L() != Lifecycle.State.INITIALIZED.ordinal()) {
            return this.z.I0(this);
        }
        throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
    }

    public View h0() {
        return this.O;
    }

    boolean h1(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.G) {
            return false;
        }
        if (this.K && this.L) {
            G0(menu, menuInflater);
            z = true;
        }
        return z | this.B.F(menu, menuInflater);
    }

    public final int hashCode() {
        return super.hashCode();
    }

    @Override // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry i() {
        return this.c0.b();
    }

    public LiveData i0() {
        return this.a0;
    }

    void i1(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.B.c1();
        this.x = true;
        this.Z = new FragmentViewLifecycleOwner(this, h());
        View H0 = H0(layoutInflater, viewGroup, bundle);
        this.O = H0;
        if (H0 == null) {
            if (this.Z.d()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.Z = null;
        } else {
            this.Z.c();
            ViewTreeLifecycleOwner.a(this.O, this.Z);
            ViewTreeViewModelStoreOwner.a(this.O, this.Z);
            ViewTreeSavedStateRegistryOwner.a(this.O, this.Z);
            this.a0.o(this.Z);
        }
    }

    void j1() {
        this.B.G();
        this.Y.h(Lifecycle.Event.ON_DESTROY);
        this.f3974c = 0;
        this.M = false;
        this.V = false;
        I0();
        if (this.M) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void k0() {
        j0();
        this.W = this.f3979l;
        this.f3979l = UUID.randomUUID().toString();
        this.f3985r = false;
        this.f3986s = false;
        this.u = false;
        this.v = false;
        this.w = false;
        this.y = 0;
        this.z = null;
        this.B = new FragmentManagerImpl();
        this.A = null;
        this.D = 0;
        this.E = 0;
        this.F = null;
        this.G = false;
        this.H = false;
    }

    void k1() {
        this.B.H();
        if (this.O != null && this.Z.a().b().d(Lifecycle.State.CREATED)) {
            this.Z.b(Lifecycle.Event.ON_DESTROY);
        }
        this.f3974c = 1;
        this.M = false;
        K0();
        if (this.M) {
            LoaderManager.b(this).c();
            this.x = false;
        } else {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    void l(boolean z) {
        ViewGroup viewGroup;
        FragmentManager fragmentManager;
        AnimationInfo animationInfo = this.R;
        if (animationInfo != null) {
            animationInfo.v = false;
        }
        if (this.O == null || (viewGroup = this.N) == null || (fragmentManager = this.z) == null) {
            return;
        }
        final SpecialEffectsController n2 = SpecialEffectsController.n(viewGroup, fragmentManager);
        n2.p();
        if (z) {
            this.A.s().post(new Runnable() { // from class: androidx.fragment.app.Fragment.4
                @Override // java.lang.Runnable
                public void run() {
                    n2.g();
                }
            });
        } else {
            n2.g();
        }
    }

    void l1() {
        this.f3974c = -1;
        this.M = false;
        L0();
        this.U = null;
        if (this.M) {
            if (this.B.M0()) {
                return;
            }
            this.B.G();
            this.B = new FragmentManagerImpl();
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
    }

    FragmentContainer m() {
        return new FragmentContainer() { // from class: androidx.fragment.app.Fragment.5
            @Override // androidx.fragment.app.FragmentContainer
            public View m(int i2) {
                View view = Fragment.this.O;
                if (view != null) {
                    return view.findViewById(i2);
                }
                throw new IllegalStateException("Fragment " + Fragment.this + " does not have a view");
            }

            @Override // androidx.fragment.app.FragmentContainer
            public boolean p() {
                return Fragment.this.O != null;
            }
        };
    }

    public final boolean m0() {
        return this.A != null && this.f3985r;
    }

    LayoutInflater m1(Bundle bundle) {
        LayoutInflater M0 = M0(bundle);
        this.U = M0;
        return M0;
    }

    public final boolean n0() {
        return this.H;
    }

    void n1() {
        onLowMemory();
    }

    public void o(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.D));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.E));
        printWriter.print(" mTag=");
        printWriter.println(this.F);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f3974c);
        printWriter.print(" mWho=");
        printWriter.print(this.f3979l);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.y);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.f3985r);
        printWriter.print(" mRemoving=");
        printWriter.print(this.f3986s);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.u);
        printWriter.print(" mInLayout=");
        printWriter.println(this.v);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.G);
        printWriter.print(" mDetached=");
        printWriter.print(this.H);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.L);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.K);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.I);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.Q);
        if (this.z != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.z);
        }
        if (this.A != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.A);
        }
        if (this.C != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.C);
        }
        if (this.f3980m != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f3980m);
        }
        if (this.f3975h != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f3975h);
        }
        if (this.f3976i != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f3976i);
        }
        if (this.f3977j != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewRegistryState=");
            printWriter.println(this.f3977j);
        }
        Fragment e0 = e0(false);
        if (e0 != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(e0);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.f3983p);
        }
        printWriter.print(str);
        printWriter.print("mPopDirection=");
        printWriter.println(P());
        if (A() != 0) {
            printWriter.print(str);
            printWriter.print("getEnterAnim=");
            printWriter.println(A());
        }
        if (D() != 0) {
            printWriter.print(str);
            printWriter.print("getExitAnim=");
            printWriter.println(D());
        }
        if (Q() != 0) {
            printWriter.print(str);
            printWriter.print("getPopEnterAnim=");
            printWriter.println(Q());
        }
        if (R() != 0) {
            printWriter.print(str);
            printWriter.print("getPopExitAnim=");
            printWriter.println(R());
        }
        if (this.N != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.N);
        }
        if (this.O != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.O);
        }
        if (w() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(w());
        }
        if (z() != null) {
            LoaderManager.b(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.B + ":");
        this.B.Z(str + "  ", fileDescriptor, printWriter, strArr);
    }

    public final boolean o0() {
        FragmentManager fragmentManager;
        return this.G || ((fragmentManager = this.z) != null && fragmentManager.Q0(this.C));
    }

    void o1(boolean z) {
        Q0(z);
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.M = true;
    }

    @CallSuper
    @MainThread
    public void onCreate(@Nullable Bundle bundle) {
        this.M = true;
        F1(bundle);
        if (this.B.T0(1)) {
            return;
        }
        this.B.E();
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        C1().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.M = true;
    }

    @CallSuper
    @MainThread
    public void onPause() {
        this.M = true;
    }

    @CallSuper
    @MainThread
    public void onResume() {
        this.M = true;
    }

    final boolean p0() {
        return this.y > 0;
    }

    boolean p1(MenuItem menuItem) {
        if (this.G) {
            return false;
        }
        if (this.K && this.L && R0(menuItem)) {
            return true;
        }
        return this.B.M(menuItem);
    }

    Fragment q(String str) {
        return str.equals(this.f3979l) ? this : this.B.m0(str);
    }

    public final boolean q0() {
        return this.v;
    }

    void q1(Menu menu) {
        if (this.G) {
            return;
        }
        if (this.K && this.L) {
            S0(menu);
        }
        this.B.N(menu);
    }

    String r() {
        return "fragment_" + this.f3979l + "_rq#" + this.e0.getAndIncrement();
    }

    public final boolean r0() {
        FragmentManager fragmentManager;
        return this.L && ((fragmentManager = this.z) == null || fragmentManager.R0(this.C));
    }

    void r1() {
        this.B.P();
        if (this.O != null) {
            this.Z.b(Lifecycle.Event.ON_PAUSE);
        }
        this.Y.h(Lifecycle.Event.ON_PAUSE);
        this.f3974c = 6;
        this.M = false;
        onPause();
        if (this.M) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
    }

    @Override // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras s() {
        Application application;
        Context applicationContext = D1().getApplicationContext();
        while (true) {
            if (!(applicationContext instanceof ContextWrapper)) {
                application = null;
                break;
            }
            if (applicationContext instanceof Application) {
                application = (Application) applicationContext;
                break;
            }
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        if (application == null && FragmentManager.N0(3)) {
            Log.d("FragmentManager", "Could not find Application instance from Context " + D1().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
        }
        MutableCreationExtras mutableCreationExtras = new MutableCreationExtras();
        if (application != null) {
            mutableCreationExtras.c(ViewModelProvider.AndroidViewModelFactory.f4404g, application);
        }
        mutableCreationExtras.c(SavedStateHandleSupport.f4369a, this);
        mutableCreationExtras.c(SavedStateHandleSupport.f4370b, this);
        if (x() != null) {
            mutableCreationExtras.c(SavedStateHandleSupport.f4371c, x());
        }
        return mutableCreationExtras;
    }

    boolean s0() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return false;
        }
        return animationInfo.v;
    }

    void s1(boolean z) {
        T0(z);
    }

    public void startActivityForResult(Intent intent, int i2) {
        X1(intent, i2, null);
    }

    public final FragmentActivity t() {
        FragmentHostCallback fragmentHostCallback = this.A;
        if (fragmentHostCallback == null) {
            return null;
        }
        return (FragmentActivity) fragmentHostCallback.q();
    }

    public final boolean t0() {
        return this.f3986s;
    }

    boolean t1(Menu menu) {
        boolean z = false;
        if (this.G) {
            return false;
        }
        if (this.K && this.L) {
            U0(menu);
            z = true;
        }
        return z | this.B.R(menu);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(getClass().getSimpleName());
        sb.append("{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("}");
        sb.append(" (");
        sb.append(this.f3979l);
        if (this.D != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.D));
        }
        if (this.F != null) {
            sb.append(" tag=");
            sb.append(this.F);
        }
        sb.append(")");
        return sb.toString();
    }

    public boolean u() {
        Boolean bool;
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null || (bool = animationInfo.f4018q) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public final boolean u0() {
        return this.f3974c >= 7;
    }

    void u1() {
        boolean S0 = this.z.S0(this);
        Boolean bool = this.f3984q;
        if (bool == null || bool.booleanValue() != S0) {
            this.f3984q = Boolean.valueOf(S0);
            V0(S0);
            this.B.S();
        }
    }

    public boolean v() {
        Boolean bool;
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null || (bool = animationInfo.f4017p) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    public final boolean v0() {
        FragmentManager fragmentManager = this.z;
        if (fragmentManager == null) {
            return false;
        }
        return fragmentManager.U0();
    }

    void v1() {
        this.B.c1();
        this.B.d0(true);
        this.f3974c = 7;
        this.M = false;
        onResume();
        if (!this.M) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
        }
        LifecycleRegistry lifecycleRegistry = this.Y;
        Lifecycle.Event event = Lifecycle.Event.ON_RESUME;
        lifecycleRegistry.h(event);
        if (this.O != null) {
            this.Z.b(event);
        }
        this.B.T();
    }

    View w() {
        AnimationInfo animationInfo = this.R;
        if (animationInfo == null) {
            return null;
        }
        return animationInfo.f4002a;
    }

    public final boolean w0() {
        View view;
        return (!m0() || o0() || (view = this.O) == null || view.getWindowToken() == null || this.O.getVisibility() != 0) ? false : true;
    }

    void w1(Bundle bundle) {
        X0(bundle);
        this.c0.e(bundle);
        Bundle V0 = this.B.V0();
        if (V0 != null) {
            bundle.putParcelable("android:support:fragments", V0);
        }
    }

    public final Bundle x() {
        return this.f3980m;
    }

    void x0() {
        this.B.c1();
    }

    void x1() {
        this.B.c1();
        this.B.d0(true);
        this.f3974c = 5;
        this.M = false;
        Y0();
        if (!this.M) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
        }
        LifecycleRegistry lifecycleRegistry = this.Y;
        Lifecycle.Event event = Lifecycle.Event.ON_START;
        lifecycleRegistry.h(event);
        if (this.O != null) {
            this.Z.b(event);
        }
        this.B.U();
    }

    public final FragmentManager y() {
        if (this.A != null) {
            return this.B;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    public void y0(Bundle bundle) {
        this.M = true;
    }

    void y1() {
        this.B.W();
        if (this.O != null) {
            this.Z.b(Lifecycle.Event.ON_STOP);
        }
        this.Y.h(Lifecycle.Event.ON_STOP);
        this.f3974c = 4;
        this.M = false;
        Z0();
        if (this.M) {
            return;
        }
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
    }

    public Context z() {
        FragmentHostCallback fragmentHostCallback = this.A;
        if (fragmentHostCallback == null) {
            return null;
        }
        return fragmentHostCallback.r();
    }

    public void z0(int i2, int i3, Intent intent) {
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + i2 + " resultCode: " + i3 + " data: " + intent);
        }
    }

    void z1() {
        a1(this.O, this.f3975h);
        this.B.X();
    }

    @SuppressLint({"BanParcelableUsage, ParcelClassLoader"})
    public static class SavedState implements Parcelable {

        @NonNull
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: androidx.fragment.app.Fragment.SavedState.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }

            @Override // android.os.Parcelable.ClassLoaderCreator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public SavedState[] newArray(int i2) {
                return new SavedState[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        final Bundle f4021c;

        SavedState(Bundle bundle) {
            this.f4021c = bundle;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeBundle(this.f4021c);
        }

        SavedState(Parcel parcel, ClassLoader classLoader) {
            Bundle readBundle = parcel.readBundle();
            this.f4021c = readBundle;
            if (classLoader == null || readBundle == null) {
                return;
            }
            readBundle.setClassLoader(classLoader);
        }
    }
}
