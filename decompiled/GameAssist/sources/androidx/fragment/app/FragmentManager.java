package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class FragmentManager implements FragmentResultOwner {
    private static boolean S = false;
    private ActivityResultLauncher D;
    private ActivityResultLauncher E;
    private ActivityResultLauncher F;
    private boolean H;
    private boolean I;
    private boolean J;
    private boolean K;
    private boolean L;
    private ArrayList M;
    private ArrayList N;
    private ArrayList O;
    private FragmentManagerViewModel P;
    private FragmentStrictMode.Policy Q;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4046b;

    /* renamed from: d, reason: collision with root package name */
    ArrayList f4048d;

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f4049e;

    /* renamed from: g, reason: collision with root package name */
    private OnBackPressedDispatcher f4051g;

    /* renamed from: m, reason: collision with root package name */
    private ArrayList f4057m;
    private FragmentHostCallback v;
    private FragmentContainer w;
    private Fragment x;
    Fragment y;

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f4045a = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    private final FragmentStore f4047c = new FragmentStore();

    /* renamed from: f, reason: collision with root package name */
    private final FragmentLayoutInflaterFactory f4050f = new FragmentLayoutInflaterFactory(this);

    /* renamed from: h, reason: collision with root package name */
    private final OnBackPressedCallback f4052h = new OnBackPressedCallback(false) { // from class: androidx.fragment.app.FragmentManager.1
        @Override // androidx.activity.OnBackPressedCallback
        public void d() {
            FragmentManager.this.J0();
        }
    };

    /* renamed from: i, reason: collision with root package name */
    private final AtomicInteger f4053i = new AtomicInteger();

    /* renamed from: j, reason: collision with root package name */
    private final Map f4054j = Collections.synchronizedMap(new HashMap());

    /* renamed from: k, reason: collision with root package name */
    private final Map f4055k = Collections.synchronizedMap(new HashMap());

    /* renamed from: l, reason: collision with root package name */
    private final Map f4056l = Collections.synchronizedMap(new HashMap());

    /* renamed from: n, reason: collision with root package name */
    private final FragmentLifecycleCallbacksDispatcher f4058n = new FragmentLifecycleCallbacksDispatcher(this);

    /* renamed from: o, reason: collision with root package name */
    private final CopyOnWriteArrayList f4059o = new CopyOnWriteArrayList();

    /* renamed from: p, reason: collision with root package name */
    private final Consumer f4060p = new Consumer() { // from class: androidx.fragment.app.e
        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            FragmentManager.this.W0((Configuration) obj);
        }
    };

    /* renamed from: q, reason: collision with root package name */
    private final Consumer f4061q = new Consumer() { // from class: androidx.fragment.app.f
        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            FragmentManager.this.X0((Integer) obj);
        }
    };

    /* renamed from: r, reason: collision with root package name */
    private final Consumer f4062r = new Consumer() { // from class: androidx.fragment.app.g
        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            FragmentManager.this.Y0((MultiWindowModeChangedInfo) obj);
        }
    };

    /* renamed from: s, reason: collision with root package name */
    private final Consumer f4063s = new Consumer() { // from class: androidx.fragment.app.h
        @Override // androidx.core.util.Consumer
        public final void accept(Object obj) {
            FragmentManager.this.Z0((PictureInPictureModeChangedInfo) obj);
        }
    };
    private final MenuProvider t = new MenuProvider() { // from class: androidx.fragment.app.FragmentManager.2
        @Override // androidx.core.view.MenuProvider
        public void a(Menu menu) {
            FragmentManager.this.N(menu);
        }

        @Override // androidx.core.view.MenuProvider
        public void b(Menu menu) {
            FragmentManager.this.R(menu);
        }

        @Override // androidx.core.view.MenuProvider
        public boolean c(MenuItem menuItem) {
            return FragmentManager.this.M(menuItem);
        }

        @Override // androidx.core.view.MenuProvider
        public void d(Menu menu, MenuInflater menuInflater) {
            FragmentManager.this.F(menu, menuInflater);
        }
    };
    int u = -1;
    private FragmentFactory z = null;
    private FragmentFactory A = new FragmentFactory() { // from class: androidx.fragment.app.FragmentManager.3
        @Override // androidx.fragment.app.FragmentFactory
        public Fragment a(ClassLoader classLoader, String str) {
            return FragmentManager.this.A0().j(FragmentManager.this.A0().r(), str, null);
        }
    };
    private SpecialEffectsControllerFactory B = null;
    private SpecialEffectsControllerFactory C = new SpecialEffectsControllerFactory() { // from class: androidx.fragment.app.FragmentManager.4
        @Override // androidx.fragment.app.SpecialEffectsControllerFactory
        public SpecialEffectsController a(ViewGroup viewGroup) {
            return new DefaultSpecialEffectsController(viewGroup);
        }
    };
    ArrayDeque G = new ArrayDeque();
    private Runnable R = new Runnable() { // from class: androidx.fragment.app.FragmentManager.5
        @Override // java.lang.Runnable
        public void run() {
            FragmentManager.this.d0(true);
        }
    };

    /* renamed from: androidx.fragment.app.FragmentManager$6, reason: invalid class name */
    class AnonymousClass6 implements LifecycleEventObserver {

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ String f4070c;

        /* renamed from: h, reason: collision with root package name */
        final /* synthetic */ FragmentResultListener f4071h;

        /* renamed from: i, reason: collision with root package name */
        final /* synthetic */ Lifecycle f4072i;

        /* renamed from: j, reason: collision with root package name */
        final /* synthetic */ FragmentManager f4073j;

        @Override // androidx.lifecycle.LifecycleEventObserver
        public void c(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Bundle bundle;
            if (event == Lifecycle.Event.ON_START && (bundle = (Bundle) this.f4073j.f4055k.get(this.f4070c)) != null) {
                this.f4071h.a(this.f4070c, bundle);
                this.f4073j.v(this.f4070c);
            }
            if (event == Lifecycle.Event.ON_DESTROY) {
                this.f4072i.c(this);
                this.f4073j.f4056l.remove(this.f4070c);
            }
        }
    }

    public interface BackStackEntry {
    }

    private class ClearBackStackState implements OpGenerator {

        /* renamed from: a, reason: collision with root package name */
        private final String f4078a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FragmentManager f4079b;

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            return this.f4079b.t(arrayList, arrayList2, this.f4078a);
        }
    }

    static class FragmentIntentSenderContract extends ActivityResultContract<IntentSenderRequest, ActivityResult> {
        FragmentIntentSenderContract() {
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public Intent a(Context context, IntentSenderRequest intentSenderRequest) {
            Bundle bundleExtra;
            Intent intent = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent a2 = intentSenderRequest.a();
            if (a2 != null && (bundleExtra = a2.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                a2.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if (a2.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                    intentSenderRequest = new IntentSenderRequest.Builder(intentSenderRequest.f()).b(null).c(intentSenderRequest.d(), intentSenderRequest.b()).a();
                }
            }
            intent.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent);
            }
            return intent;
        }

        @Override // androidx.activity.result.contract.ActivityResultContract
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ActivityResult c(int i2, Intent intent) {
            return new ActivityResult(i2, intent);
        }
    }

    public static abstract class FragmentLifecycleCallbacks {
        public void a(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void b(FragmentManager fragmentManager, Fragment fragment, Context context) {
        }

        public void c(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void d(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void e(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void f(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void g(FragmentManager fragmentManager, Fragment fragment, Context context) {
        }

        public void h(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void i(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void j(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
        }

        public void k(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void l(FragmentManager fragmentManager, Fragment fragment) {
        }

        public void m(FragmentManager fragmentManager, Fragment fragment, View view, Bundle bundle) {
        }

        public void n(FragmentManager fragmentManager, Fragment fragment) {
        }
    }

    private static class LifecycleAwareResultListener implements FragmentResultListener {

        /* renamed from: a, reason: collision with root package name */
        private final FragmentResultListener f4082a;

        @Override // androidx.fragment.app.FragmentResultListener
        public void a(String str, Bundle bundle) {
            this.f4082a.a(str, bundle);
        }
    }

    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    interface OpGenerator {
        boolean a(ArrayList arrayList, ArrayList arrayList2);
    }

    private class PopBackStackState implements OpGenerator {

        /* renamed from: a, reason: collision with root package name */
        final String f4083a;

        /* renamed from: b, reason: collision with root package name */
        final int f4084b;

        /* renamed from: c, reason: collision with root package name */
        final int f4085c;

        PopBackStackState(String str, int i2, int i3) {
            this.f4083a = str;
            this.f4084b = i2;
            this.f4085c = i3;
        }

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            Fragment fragment = FragmentManager.this.y;
            if (fragment == null || this.f4084b >= 0 || this.f4083a != null || !fragment.y().g1()) {
                return FragmentManager.this.j1(arrayList, arrayList2, this.f4083a, this.f4084b, this.f4085c);
            }
            return false;
        }
    }

    private class RestoreBackStackState implements OpGenerator {

        /* renamed from: a, reason: collision with root package name */
        private final String f4087a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FragmentManager f4088b;

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            return this.f4088b.q1(arrayList, arrayList2, this.f4087a);
        }
    }

    private class SaveBackStackState implements OpGenerator {

        /* renamed from: a, reason: collision with root package name */
        private final String f4089a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ FragmentManager f4090b;

        @Override // androidx.fragment.app.FragmentManager.OpGenerator
        public boolean a(ArrayList arrayList, ArrayList arrayList2) {
            return this.f4090b.u1(arrayList, arrayList2, this.f4089a);
        }
    }

    private void A1(Fragment fragment) {
        ViewGroup w0 = w0(fragment);
        if (w0 == null || fragment.A() + fragment.D() + fragment.Q() + fragment.R() <= 0) {
            return;
        }
        if (w0.getTag(R.id.visible_removing_fragment_view_tag) == null) {
            w0.setTag(R.id.visible_removing_fragment_view_tag, fragment);
        }
        ((Fragment) w0.getTag(R.id.visible_removing_fragment_view_tag)).P1(fragment.P());
    }

    private void C1() {
        Iterator it = this.f4047c.k().iterator();
        while (it.hasNext()) {
            e1((FragmentStateManager) it.next());
        }
    }

    private void D1(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback fragmentHostCallback = this.v;
        if (fragmentHostCallback != null) {
            try {
                fragmentHostCallback.u("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            Z("  ", null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    private void F1() {
        synchronized (this.f4045a) {
            try {
                if (this.f4045a.isEmpty()) {
                    this.f4052h.j(s0() > 0 && S0(this.x));
                } else {
                    this.f4052h.j(true);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    static Fragment H0(View view) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if (tag instanceof Fragment) {
            return (Fragment) tag;
        }
        return null;
    }

    public static boolean N0(int i2) {
        return S || Log.isLoggable("FragmentManager", i2);
    }

    private void O(Fragment fragment) {
        if (fragment == null || !fragment.equals(i0(fragment.f3979l))) {
            return;
        }
        fragment.u1();
    }

    private boolean O0(Fragment fragment) {
        return (fragment.K && fragment.L) || fragment.B.q();
    }

    private boolean P0() {
        Fragment fragment = this.x;
        if (fragment == null) {
            return true;
        }
        return fragment.m0() && this.x.O().P0();
    }

    private void V(int i2) {
        try {
            this.f4046b = true;
            this.f4047c.d(i2);
            b1(i2, false);
            Iterator it = w().iterator();
            while (it.hasNext()) {
                ((SpecialEffectsController) it.next()).j();
            }
            this.f4046b = false;
            d0(true);
        } catch (Throwable th) {
            this.f4046b = false;
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W0(Configuration configuration) {
        if (P0()) {
            C(configuration, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X0(Integer num) {
        if (P0() && num.intValue() == 80) {
            I(false);
        }
    }

    private void Y() {
        if (this.L) {
            this.L = false;
            C1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Y0(MultiWindowModeChangedInfo multiWindowModeChangedInfo) {
        if (P0()) {
            J(multiWindowModeChangedInfo.a(), false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Z0(PictureInPictureModeChangedInfo pictureInPictureModeChangedInfo) {
        if (P0()) {
            Q(pictureInPictureModeChangedInfo.a(), false);
        }
    }

    private void a0() {
        Iterator it = w().iterator();
        while (it.hasNext()) {
            ((SpecialEffectsController) it.next()).j();
        }
    }

    private void c0(boolean z) {
        if (this.f4046b) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if (this.v == null) {
            if (!this.K) {
                throw new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            throw new IllegalStateException("FragmentManager has been destroyed");
        }
        if (Looper.myLooper() != this.v.s().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if (!z) {
            r();
        }
        if (this.M == null) {
            this.M = new ArrayList();
            this.N = new ArrayList();
        }
    }

    private static void f0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        while (i2 < i3) {
            BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i2);
            if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                backStackRecord.u(-1);
                backStackRecord.A();
            } else {
                backStackRecord.u(1);
                backStackRecord.z();
            }
            i2++;
        }
    }

    private void g0(ArrayList arrayList, ArrayList arrayList2, int i2, int i3) {
        boolean z = ((BackStackRecord) arrayList.get(i2)).f4167r;
        ArrayList arrayList3 = this.O;
        if (arrayList3 == null) {
            this.O = new ArrayList();
        } else {
            arrayList3.clear();
        }
        this.O.addAll(this.f4047c.o());
        Fragment E0 = E0();
        boolean z2 = false;
        for (int i4 = i2; i4 < i3; i4++) {
            BackStackRecord backStackRecord = (BackStackRecord) arrayList.get(i4);
            E0 = !((Boolean) arrayList2.get(i4)).booleanValue() ? backStackRecord.B(this.O, E0) : backStackRecord.E(this.O, E0);
            z2 = z2 || backStackRecord.f4158i;
        }
        this.O.clear();
        if (!z && this.u >= 1) {
            for (int i5 = i2; i5 < i3; i5++) {
                Iterator it = ((BackStackRecord) arrayList.get(i5)).f4152c.iterator();
                while (it.hasNext()) {
                    Fragment fragment = ((FragmentTransaction.Op) it.next()).f4170b;
                    if (fragment != null && fragment.z != null) {
                        this.f4047c.r(y(fragment));
                    }
                }
            }
        }
        f0(arrayList, arrayList2, i2, i3);
        boolean booleanValue = ((Boolean) arrayList2.get(i3 - 1)).booleanValue();
        for (int i6 = i2; i6 < i3; i6++) {
            BackStackRecord backStackRecord2 = (BackStackRecord) arrayList.get(i6);
            if (booleanValue) {
                for (int size = backStackRecord2.f4152c.size() - 1; size >= 0; size--) {
                    Fragment fragment2 = ((FragmentTransaction.Op) backStackRecord2.f4152c.get(size)).f4170b;
                    if (fragment2 != null) {
                        y(fragment2).m();
                    }
                }
            } else {
                Iterator it2 = backStackRecord2.f4152c.iterator();
                while (it2.hasNext()) {
                    Fragment fragment3 = ((FragmentTransaction.Op) it2.next()).f4170b;
                    if (fragment3 != null) {
                        y(fragment3).m();
                    }
                }
            }
        }
        b1(this.u, true);
        for (SpecialEffectsController specialEffectsController : x(arrayList, i2, i3)) {
            specialEffectsController.r(booleanValue);
            specialEffectsController.p();
            specialEffectsController.g();
        }
        while (i2 < i3) {
            BackStackRecord backStackRecord3 = (BackStackRecord) arrayList.get(i2);
            if (((Boolean) arrayList2.get(i2)).booleanValue() && backStackRecord3.v >= 0) {
                backStackRecord3.v = -1;
            }
            backStackRecord3.D();
            i2++;
        }
        if (z2) {
            p1();
        }
    }

    private boolean i1(String str, int i2, int i3) {
        d0(false);
        c0(true);
        Fragment fragment = this.y;
        if (fragment != null && i2 < 0 && str == null && fragment.y().g1()) {
            return true;
        }
        boolean j1 = j1(this.M, this.N, str, i2, i3);
        if (j1) {
            this.f4046b = true;
            try {
                n1(this.M, this.N);
            } finally {
                s();
            }
        }
        F1();
        Y();
        this.f4047c.b();
        return j1;
    }

    private int j0(String str, int i2, boolean z) {
        ArrayList arrayList = this.f4048d;
        if (arrayList == null || arrayList.isEmpty()) {
            return -1;
        }
        if (str == null && i2 < 0) {
            if (z) {
                return 0;
            }
            return this.f4048d.size() - 1;
        }
        int size = this.f4048d.size() - 1;
        while (size >= 0) {
            BackStackRecord backStackRecord = (BackStackRecord) this.f4048d.get(size);
            if ((str != null && str.equals(backStackRecord.C())) || (i2 >= 0 && i2 == backStackRecord.v)) {
                break;
            }
            size--;
        }
        if (size < 0) {
            return size;
        }
        if (!z) {
            if (size == this.f4048d.size() - 1) {
                return -1;
            }
            return size + 1;
        }
        while (size > 0) {
            BackStackRecord backStackRecord2 = (BackStackRecord) this.f4048d.get(size - 1);
            if ((str == null || !str.equals(backStackRecord2.C())) && (i2 < 0 || i2 != backStackRecord2.v)) {
                return size;
            }
            size--;
        }
        return size;
    }

    static FragmentManager n0(View view) {
        FragmentActivity fragmentActivity;
        Fragment o0 = o0(view);
        if (o0 != null) {
            if (o0.m0()) {
                return o0.y();
            }
            throw new IllegalStateException("The Fragment " + o0 + " that owns View " + view + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
        }
        Context context = view.getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                fragmentActivity = null;
                break;
            }
            if (context instanceof FragmentActivity) {
                fragmentActivity = (FragmentActivity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (fragmentActivity != null) {
            return fragmentActivity.V();
        }
        throw new IllegalStateException("View " + view + " is not within a subclass of FragmentActivity.");
    }

    private void n1(ArrayList arrayList, ArrayList arrayList2) {
        if (arrayList.isEmpty()) {
            return;
        }
        if (arrayList.size() != arrayList2.size()) {
            throw new IllegalStateException("Internal error with the back stack records");
        }
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        while (i2 < size) {
            if (!((BackStackRecord) arrayList.get(i2)).f4167r) {
                if (i3 != i2) {
                    g0(arrayList, arrayList2, i3, i2);
                }
                i3 = i2 + 1;
                if (((Boolean) arrayList2.get(i2)).booleanValue()) {
                    while (i3 < size && ((Boolean) arrayList2.get(i3)).booleanValue() && !((BackStackRecord) arrayList.get(i3)).f4167r) {
                        i3++;
                    }
                }
                g0(arrayList, arrayList2, i2, i3);
                i2 = i3 - 1;
            }
            i2++;
        }
        if (i3 != size) {
            g0(arrayList, arrayList2, i3, size);
        }
    }

    private static Fragment o0(View view) {
        while (view != null) {
            Fragment H0 = H0(view);
            if (H0 != null) {
                return H0;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        return null;
    }

    private void p0() {
        Iterator it = w().iterator();
        while (it.hasNext()) {
            ((SpecialEffectsController) it.next()).k();
        }
    }

    private void p1() {
        if (this.f4057m != null) {
            for (int i2 = 0; i2 < this.f4057m.size(); i2++) {
                ((OnBackStackChangedListener) this.f4057m.get(i2)).onBackStackChanged();
            }
        }
    }

    private boolean q0(ArrayList arrayList, ArrayList arrayList2) {
        synchronized (this.f4045a) {
            if (this.f4045a.isEmpty()) {
                return false;
            }
            try {
                int size = this.f4045a.size();
                boolean z = false;
                for (int i2 = 0; i2 < size; i2++) {
                    z |= ((OpGenerator) this.f4045a.get(i2)).a(arrayList, arrayList2);
                }
                return z;
            } finally {
                this.f4045a.clear();
                this.v.s().removeCallbacks(this.R);
            }
        }
    }

    private void r() {
        if (U0()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void s() {
        this.f4046b = false;
        this.N.clear();
        this.M.clear();
    }

    static int s1(int i2) {
        int i3 = 4097;
        if (i2 == 4097) {
            return 8194;
        }
        if (i2 != 8194) {
            i3 = 8197;
            if (i2 == 8197) {
                return 4100;
            }
            if (i2 == 4099) {
                return 4099;
            }
            if (i2 != 4100) {
                return 0;
            }
        }
        return i3;
    }

    private FragmentManagerViewModel t0(Fragment fragment) {
        return this.P.k(fragment);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void u() {
        /*
            r4 = this;
            androidx.fragment.app.FragmentHostCallback r0 = r4.v
            boolean r1 = r0 instanceof androidx.lifecycle.ViewModelStoreOwner
            if (r1 == 0) goto L11
            androidx.fragment.app.FragmentStore r0 = r4.f4047c
            androidx.fragment.app.FragmentManagerViewModel r0 = r0.p()
            boolean r0 = r0.o()
            goto L27
        L11:
            android.content.Context r0 = r0.r()
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L29
            androidx.fragment.app.FragmentHostCallback r0 = r4.v
            android.content.Context r0 = r0.r()
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            r0 = r0 ^ 1
        L27:
            if (r0 == 0) goto L5b
        L29:
            java.util.Map r0 = r4.f4054j
            java.util.Collection r0 = r0.values()
            java.util.Iterator r0 = r0.iterator()
        L33:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L5b
            java.lang.Object r1 = r0.next()
            androidx.fragment.app.BackStackState r1 = (androidx.fragment.app.BackStackState) r1
            java.util.List r1 = r1.f3920c
            java.util.Iterator r1 = r1.iterator()
        L45:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L33
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            androidx.fragment.app.FragmentStore r3 = r4.f4047c
            androidx.fragment.app.FragmentManagerViewModel r3 = r3.p()
            r3.h(r2)
            goto L45
        L5b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.FragmentManager.u():void");
    }

    private Set w() {
        HashSet hashSet = new HashSet();
        Iterator it = this.f4047c.k().iterator();
        while (it.hasNext()) {
            ViewGroup viewGroup = ((FragmentStateManager) it.next()).k().N;
            if (viewGroup != null) {
                hashSet.add(SpecialEffectsController.o(viewGroup, F0()));
            }
        }
        return hashSet;
    }

    private ViewGroup w0(Fragment fragment) {
        ViewGroup viewGroup = fragment.N;
        if (viewGroup != null) {
            return viewGroup;
        }
        if (fragment.E > 0 && this.w.p()) {
            View m2 = this.w.m(fragment.E);
            if (m2 instanceof ViewGroup) {
                return (ViewGroup) m2;
            }
        }
        return null;
    }

    private Set x(ArrayList arrayList, int i2, int i3) {
        ViewGroup viewGroup;
        HashSet hashSet = new HashSet();
        while (i2 < i3) {
            Iterator it = ((BackStackRecord) arrayList.get(i2)).f4152c.iterator();
            while (it.hasNext()) {
                Fragment fragment = ((FragmentTransaction.Op) it.next()).f4170b;
                if (fragment != null && (viewGroup = fragment.N) != null) {
                    hashSet.add(SpecialEffectsController.n(viewGroup, this));
                }
            }
            i2++;
        }
        return hashSet;
    }

    void A() {
        this.I = false;
        this.J = false;
        this.P.q(false);
        V(4);
    }

    public FragmentHostCallback A0() {
        return this.v;
    }

    void B() {
        this.I = false;
        this.J = false;
        this.P.q(false);
        V(0);
    }

    LayoutInflater.Factory2 B0() {
        return this.f4050f;
    }

    void B1(Fragment fragment) {
        if (N0(2)) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.G) {
            fragment.G = false;
            fragment.T = !fragment.T;
        }
    }

    void C(Configuration configuration, boolean z) {
        if (z && (this.v instanceof OnConfigurationChangedProvider)) {
            D1(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null) {
                fragment.e1(configuration);
                if (z) {
                    fragment.B.C(configuration, true);
                }
            }
        }
    }

    FragmentLifecycleCallbacksDispatcher C0() {
        return this.f4058n;
    }

    boolean D(MenuItem menuItem) {
        if (this.u < 1) {
            return false;
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null && fragment.f1(menuItem)) {
                return true;
            }
        }
        return false;
    }

    Fragment D0() {
        return this.x;
    }

    void E() {
        this.I = false;
        this.J = false;
        this.P.q(false);
        V(1);
    }

    public Fragment E0() {
        return this.y;
    }

    public void E1(FragmentLifecycleCallbacks fragmentLifecycleCallbacks) {
        this.f4058n.p(fragmentLifecycleCallbacks);
    }

    boolean F(Menu menu, MenuInflater menuInflater) {
        if (this.u < 1) {
            return false;
        }
        ArrayList arrayList = null;
        boolean z = false;
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null && R0(fragment) && fragment.h1(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.f4049e != null) {
            for (int i2 = 0; i2 < this.f4049e.size(); i2++) {
                Fragment fragment2 = (Fragment) this.f4049e.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.J0();
                }
            }
        }
        this.f4049e = arrayList;
        return z;
    }

    SpecialEffectsControllerFactory F0() {
        SpecialEffectsControllerFactory specialEffectsControllerFactory = this.B;
        if (specialEffectsControllerFactory != null) {
            return specialEffectsControllerFactory;
        }
        Fragment fragment = this.x;
        return fragment != null ? fragment.z.F0() : this.C;
    }

    void G() {
        this.K = true;
        d0(true);
        a0();
        u();
        V(-1);
        Object obj = this.v;
        if (obj instanceof OnTrimMemoryProvider) {
            ((OnTrimMemoryProvider) obj).d(this.f4061q);
        }
        Object obj2 = this.v;
        if (obj2 instanceof OnConfigurationChangedProvider) {
            ((OnConfigurationChangedProvider) obj2).v(this.f4060p);
        }
        Object obj3 = this.v;
        if (obj3 instanceof OnMultiWindowModeChangedProvider) {
            ((OnMultiWindowModeChangedProvider) obj3).l(this.f4062r);
        }
        Object obj4 = this.v;
        if (obj4 instanceof OnPictureInPictureModeChangedProvider) {
            ((OnPictureInPictureModeChangedProvider) obj4).c(this.f4063s);
        }
        Object obj5 = this.v;
        if (obj5 instanceof MenuHost) {
            ((MenuHost) obj5).o(this.t);
        }
        this.v = null;
        this.w = null;
        this.x = null;
        if (this.f4051g != null) {
            this.f4052h.h();
            this.f4051g = null;
        }
        ActivityResultLauncher activityResultLauncher = this.D;
        if (activityResultLauncher != null) {
            activityResultLauncher.c();
            this.E.c();
            this.F.c();
        }
    }

    public FragmentStrictMode.Policy G0() {
        return this.Q;
    }

    void H() {
        V(1);
    }

    void I(boolean z) {
        if (z && (this.v instanceof OnTrimMemoryProvider)) {
            D1(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null) {
                fragment.n1();
                if (z) {
                    fragment.B.I(true);
                }
            }
        }
    }

    ViewModelStore I0(Fragment fragment) {
        return this.P.n(fragment);
    }

    void J(boolean z, boolean z2) {
        if (z2 && (this.v instanceof OnMultiWindowModeChangedProvider)) {
            D1(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null) {
                fragment.o1(z);
                if (z2) {
                    fragment.B.J(z, true);
                }
            }
        }
    }

    void J0() {
        d0(true);
        if (this.f4052h.g()) {
            g1();
        } else {
            this.f4051g.k();
        }
    }

    void K(Fragment fragment) {
        Iterator it = this.f4059o.iterator();
        while (it.hasNext()) {
            ((FragmentOnAttachListener) it.next()).g(this, fragment);
        }
    }

    void K0(Fragment fragment) {
        if (N0(2)) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.G) {
            return;
        }
        fragment.G = true;
        fragment.T = true ^ fragment.T;
        A1(fragment);
    }

    void L() {
        for (Fragment fragment : this.f4047c.l()) {
            if (fragment != null) {
                fragment.N0(fragment.o0());
                fragment.B.L();
            }
        }
    }

    void L0(Fragment fragment) {
        if (fragment.f3985r && O0(fragment)) {
            this.H = true;
        }
    }

    boolean M(MenuItem menuItem) {
        if (this.u < 1) {
            return false;
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null && fragment.p1(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public boolean M0() {
        return this.K;
    }

    void N(Menu menu) {
        if (this.u < 1) {
            return;
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null) {
                fragment.q1(menu);
            }
        }
    }

    void P() {
        V(5);
    }

    void Q(boolean z, boolean z2) {
        if (z2 && (this.v instanceof OnPictureInPictureModeChangedProvider)) {
            D1(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null) {
                fragment.s1(z);
                if (z2) {
                    fragment.B.Q(z, true);
                }
            }
        }
    }

    boolean Q0(Fragment fragment) {
        if (fragment == null) {
            return false;
        }
        return fragment.o0();
    }

    boolean R(Menu menu) {
        boolean z = false;
        if (this.u < 1) {
            return false;
        }
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null && R0(fragment) && fragment.t1(menu)) {
                z = true;
            }
        }
        return z;
    }

    boolean R0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        return fragment.r0();
    }

    void S() {
        F1();
        O(this.y);
    }

    boolean S0(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        FragmentManager fragmentManager = fragment.z;
        return fragment.equals(fragmentManager.E0()) && S0(fragmentManager.x);
    }

    void T() {
        this.I = false;
        this.J = false;
        this.P.q(false);
        V(7);
    }

    boolean T0(int i2) {
        return this.u >= i2;
    }

    void U() {
        this.I = false;
        this.J = false;
        this.P.q(false);
        V(5);
    }

    public boolean U0() {
        return this.I || this.J;
    }

    void W() {
        this.J = true;
        this.P.q(true);
        V(4);
    }

    void X() {
        V(2);
    }

    public void Z(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        String str2 = str + "    ";
        this.f4047c.e(str, fileDescriptor, printWriter, strArr);
        ArrayList arrayList = this.f4049e;
        if (arrayList != null && (size2 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size2; i2++) {
                Fragment fragment = (Fragment) this.f4049e.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
        ArrayList arrayList2 = this.f4048d;
        if (arrayList2 != null && (size = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size; i3++) {
                BackStackRecord backStackRecord = (BackStackRecord) this.f4048d.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(backStackRecord.toString());
                backStackRecord.x(str2, printWriter);
            }
        }
        printWriter.print(str);
        printWriter.println("Back Stack Index: " + this.f4053i.get());
        synchronized (this.f4045a) {
            try {
                int size3 = this.f4045a.size();
                if (size3 > 0) {
                    printWriter.print(str);
                    printWriter.println("Pending Actions:");
                    for (int i4 = 0; i4 < size3; i4++) {
                        OpGenerator opGenerator = (OpGenerator) this.f4045a.get(i4);
                        printWriter.print(str);
                        printWriter.print("  #");
                        printWriter.print(i4);
                        printWriter.print(": ");
                        printWriter.println(opGenerator);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.v);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.w);
        if (this.x != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.x);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.u);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.I);
        printWriter.print(" mStopped=");
        printWriter.print(this.J);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.K);
        if (this.H) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.H);
        }
    }

    void a1(Fragment fragment, Intent intent, int i2, Bundle bundle) {
        if (this.D == null) {
            this.v.z(fragment, intent, i2, bundle);
            return;
        }
        this.G.addLast(new LaunchedFragmentInfo(fragment.f3979l, i2));
        if (intent != null && bundle != null) {
            intent.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle);
        }
        this.D.a(intent);
    }

    void b0(OpGenerator opGenerator, boolean z) {
        if (!z) {
            if (this.v == null) {
                if (!this.K) {
                    throw new IllegalStateException("FragmentManager has not been attached to a host.");
                }
                throw new IllegalStateException("FragmentManager has been destroyed");
            }
            r();
        }
        synchronized (this.f4045a) {
            try {
                if (this.v == null) {
                    if (!z) {
                        throw new IllegalStateException("Activity has been destroyed");
                    }
                } else {
                    this.f4045a.add(opGenerator);
                    w1();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void b1(int i2, boolean z) {
        FragmentHostCallback fragmentHostCallback;
        if (this.v == null && i2 != -1) {
            throw new IllegalStateException("No activity");
        }
        if (z || i2 != this.u) {
            this.u = i2;
            this.f4047c.t();
            C1();
            if (this.H && (fragmentHostCallback = this.v) != null && this.u == 7) {
                fragmentHostCallback.A();
                this.H = false;
            }
        }
    }

    void c1() {
        if (this.v == null) {
            return;
        }
        this.I = false;
        this.J = false;
        this.P.q(false);
        for (Fragment fragment : this.f4047c.o()) {
            if (fragment != null) {
                fragment.x0();
            }
        }
    }

    boolean d0(boolean z) {
        c0(z);
        boolean z2 = false;
        while (q0(this.M, this.N)) {
            z2 = true;
            this.f4046b = true;
            try {
                n1(this.M, this.N);
            } finally {
                s();
            }
        }
        F1();
        Y();
        this.f4047c.b();
        return z2;
    }

    void d1(FragmentContainerView fragmentContainerView) {
        View view;
        for (FragmentStateManager fragmentStateManager : this.f4047c.k()) {
            Fragment k2 = fragmentStateManager.k();
            if (k2.E == fragmentContainerView.getId() && (view = k2.O) != null && view.getParent() == null) {
                k2.N = fragmentContainerView;
                fragmentStateManager.b();
            }
        }
    }

    void e0(OpGenerator opGenerator, boolean z) {
        if (z && (this.v == null || this.K)) {
            return;
        }
        c0(z);
        if (opGenerator.a(this.M, this.N)) {
            this.f4046b = true;
            try {
                n1(this.M, this.N);
            } finally {
                s();
            }
        }
        F1();
        Y();
        this.f4047c.b();
    }

    void e1(FragmentStateManager fragmentStateManager) {
        Fragment k2 = fragmentStateManager.k();
        if (k2.P) {
            if (this.f4046b) {
                this.L = true;
            } else {
                k2.P = false;
                fragmentStateManager.m();
            }
        }
    }

    void f1(int i2, int i3, boolean z) {
        if (i2 >= 0) {
            b0(new PopBackStackState(null, i2, i3), z);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    public boolean g1() {
        return i1(null, -1, 0);
    }

    public boolean h0() {
        boolean d0 = d0(true);
        p0();
        return d0;
    }

    public boolean h1(int i2, int i3) {
        if (i2 >= 0) {
            return i1(null, i2, i3);
        }
        throw new IllegalArgumentException("Bad id: " + i2);
    }

    void i(BackStackRecord backStackRecord) {
        if (this.f4048d == null) {
            this.f4048d = new ArrayList();
        }
        this.f4048d.add(backStackRecord);
    }

    Fragment i0(String str) {
        return this.f4047c.f(str);
    }

    FragmentStateManager j(Fragment fragment) {
        String str = fragment.W;
        if (str != null) {
            FragmentStrictMode.h(fragment, str);
        }
        if (N0(2)) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        FragmentStateManager y = y(fragment);
        fragment.z = this;
        this.f4047c.r(y);
        if (!fragment.H) {
            this.f4047c.a(fragment);
            fragment.f3986s = false;
            if (fragment.O == null) {
                fragment.T = false;
            }
            if (O0(fragment)) {
                this.H = true;
            }
        }
        return y;
    }

    boolean j1(ArrayList arrayList, ArrayList arrayList2, String str, int i2, int i3) {
        int j0 = j0(str, i2, (i3 & 1) != 0);
        if (j0 < 0) {
            return false;
        }
        for (int size = this.f4048d.size() - 1; size >= j0; size--) {
            arrayList.add((BackStackRecord) this.f4048d.remove(size));
            arrayList2.add(Boolean.TRUE);
        }
        return true;
    }

    public void k(FragmentOnAttachListener fragmentOnAttachListener) {
        this.f4059o.add(fragmentOnAttachListener);
    }

    public Fragment k0(int i2) {
        return this.f4047c.g(i2);
    }

    public void k1(Bundle bundle, String str, Fragment fragment) {
        if (fragment.z != this) {
            D1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        bundle.putString(str, fragment.f3979l);
    }

    void l(Fragment fragment) {
        this.P.f(fragment);
    }

    public Fragment l0(String str) {
        return this.f4047c.h(str);
    }

    public void l1(FragmentLifecycleCallbacks fragmentLifecycleCallbacks, boolean z) {
        this.f4058n.o(fragmentLifecycleCallbacks, z);
    }

    int m() {
        return this.f4053i.getAndIncrement();
    }

    Fragment m0(String str) {
        return this.f4047c.i(str);
    }

    void m1(Fragment fragment) {
        if (N0(2)) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.y);
        }
        boolean z = !fragment.p0();
        if (!fragment.H || z) {
            this.f4047c.u(fragment);
            if (O0(fragment)) {
                this.H = true;
            }
            fragment.f3986s = true;
            A1(fragment);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    void n(FragmentHostCallback fragmentHostCallback, FragmentContainer fragmentContainer, final Fragment fragment) {
        String str;
        if (this.v != null) {
            throw new IllegalStateException("Already attached");
        }
        this.v = fragmentHostCallback;
        this.w = fragmentContainer;
        this.x = fragment;
        if (fragment != null) {
            k(new FragmentOnAttachListener() { // from class: androidx.fragment.app.FragmentManager.7
                @Override // androidx.fragment.app.FragmentOnAttachListener
                public void g(FragmentManager fragmentManager, Fragment fragment2) {
                    fragment.C0(fragment2);
                }
            });
        } else if (fragmentHostCallback instanceof FragmentOnAttachListener) {
            k((FragmentOnAttachListener) fragmentHostCallback);
        }
        if (this.x != null) {
            F1();
        }
        if (fragmentHostCallback instanceof OnBackPressedDispatcherOwner) {
            OnBackPressedDispatcherOwner onBackPressedDispatcherOwner = (OnBackPressedDispatcherOwner) fragmentHostCallback;
            OnBackPressedDispatcher n2 = onBackPressedDispatcherOwner.n();
            this.f4051g = n2;
            LifecycleOwner lifecycleOwner = onBackPressedDispatcherOwner;
            if (fragment != null) {
                lifecycleOwner = fragment;
            }
            n2.h(lifecycleOwner, this.f4052h);
        }
        if (fragment != null) {
            this.P = fragment.z.t0(fragment);
        } else if (fragmentHostCallback instanceof ViewModelStoreOwner) {
            this.P = FragmentManagerViewModel.l(((ViewModelStoreOwner) fragmentHostCallback).h());
        } else {
            this.P = new FragmentManagerViewModel(false);
        }
        this.P.q(U0());
        this.f4047c.A(this.P);
        Object obj = this.v;
        if ((obj instanceof SavedStateRegistryOwner) && fragment == null) {
            SavedStateRegistry i2 = ((SavedStateRegistryOwner) obj).i();
            i2.h("android:support:fragments", new SavedStateRegistry.SavedStateProvider() { // from class: androidx.fragment.app.i
                @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
                public final Bundle a() {
                    Bundle V0;
                    V0 = FragmentManager.this.V0();
                    return V0;
                }
            });
            Bundle b2 = i2.b("android:support:fragments");
            if (b2 != null) {
                r1(b2);
            }
        }
        Object obj2 = this.v;
        if (obj2 instanceof ActivityResultRegistryOwner) {
            ActivityResultRegistry f2 = ((ActivityResultRegistryOwner) obj2).f();
            if (fragment != null) {
                str = fragment.f3979l + ":";
            } else {
                str = "";
            }
            String str2 = "FragmentManager:" + str;
            this.D = f2.i(str2 + "StartActivityForResult", new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.8
                @Override // androidx.activity.result.ActivityResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void a(ActivityResult activityResult) {
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) FragmentManager.this.G.pollFirst();
                    if (launchedFragmentInfo == null) {
                        Log.w("FragmentManager", "No Activities were started for result for " + this);
                        return;
                    }
                    String str3 = launchedFragmentInfo.f4080c;
                    int i3 = launchedFragmentInfo.f4081h;
                    Fragment i4 = FragmentManager.this.f4047c.i(str3);
                    if (i4 != null) {
                        i4.z0(i3, activityResult.b(), activityResult.a());
                        return;
                    }
                    Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + str3);
                }
            });
            this.E = f2.i(str2 + "StartIntentSenderForResult", new FragmentIntentSenderContract(), new ActivityResultCallback<ActivityResult>() { // from class: androidx.fragment.app.FragmentManager.9
                @Override // androidx.activity.result.ActivityResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void a(ActivityResult activityResult) {
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) FragmentManager.this.G.pollFirst();
                    if (launchedFragmentInfo == null) {
                        Log.w("FragmentManager", "No IntentSenders were started for " + this);
                        return;
                    }
                    String str3 = launchedFragmentInfo.f4080c;
                    int i3 = launchedFragmentInfo.f4081h;
                    Fragment i4 = FragmentManager.this.f4047c.i(str3);
                    if (i4 != null) {
                        i4.z0(i3, activityResult.b(), activityResult.a());
                        return;
                    }
                    Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + str3);
                }
            });
            this.F = f2.i(str2 + "RequestPermissions", new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() { // from class: androidx.fragment.app.FragmentManager.10
                @Override // androidx.activity.result.ActivityResultCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void a(Map map) {
                    String[] strArr = (String[]) map.keySet().toArray(new String[0]);
                    ArrayList arrayList = new ArrayList(map.values());
                    int[] iArr = new int[arrayList.size()];
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        iArr[i3] = ((Boolean) arrayList.get(i3)).booleanValue() ? 0 : -1;
                    }
                    LaunchedFragmentInfo launchedFragmentInfo = (LaunchedFragmentInfo) FragmentManager.this.G.pollFirst();
                    if (launchedFragmentInfo == null) {
                        Log.w("FragmentManager", "No permissions were requested for " + this);
                        return;
                    }
                    String str3 = launchedFragmentInfo.f4080c;
                    int i4 = launchedFragmentInfo.f4081h;
                    Fragment i5 = FragmentManager.this.f4047c.i(str3);
                    if (i5 != null) {
                        i5.W0(i4, strArr, iArr);
                        return;
                    }
                    Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + str3);
                }
            });
        }
        Object obj3 = this.v;
        if (obj3 instanceof OnConfigurationChangedProvider) {
            ((OnConfigurationChangedProvider) obj3).b(this.f4060p);
        }
        Object obj4 = this.v;
        if (obj4 instanceof OnTrimMemoryProvider) {
            ((OnTrimMemoryProvider) obj4).k(this.f4061q);
        }
        Object obj5 = this.v;
        if (obj5 instanceof OnMultiWindowModeChangedProvider) {
            ((OnMultiWindowModeChangedProvider) obj5).t(this.f4062r);
        }
        Object obj6 = this.v;
        if (obj6 instanceof OnPictureInPictureModeChangedProvider) {
            ((OnPictureInPictureModeChangedProvider) obj6).e(this.f4063s);
        }
        Object obj7 = this.v;
        if ((obj7 instanceof MenuHost) && fragment == null) {
            ((MenuHost) obj7).w(this.t);
        }
    }

    void o(Fragment fragment) {
        if (N0(2)) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.H) {
            fragment.H = false;
            if (fragment.f3985r) {
                return;
            }
            this.f4047c.a(fragment);
            if (N0(2)) {
                Log.v("FragmentManager", "add from attach: " + fragment);
            }
            if (O0(fragment)) {
                this.H = true;
            }
        }
    }

    void o1(Fragment fragment) {
        this.P.p(fragment);
    }

    public FragmentTransaction p() {
        return new BackStackRecord(this);
    }

    boolean q() {
        boolean z = false;
        for (Fragment fragment : this.f4047c.l()) {
            if (fragment != null) {
                z = O0(fragment);
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    boolean q1(ArrayList arrayList, ArrayList arrayList2, String str) {
        BackStackState backStackState = (BackStackState) this.f4054j.remove(str);
        if (backStackState == null) {
            return false;
        }
        HashMap hashMap = new HashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            BackStackRecord backStackRecord = (BackStackRecord) it.next();
            if (backStackRecord.w) {
                Iterator it2 = backStackRecord.f4152c.iterator();
                while (it2.hasNext()) {
                    Fragment fragment = ((FragmentTransaction.Op) it2.next()).f4170b;
                    if (fragment != null) {
                        hashMap.put(fragment.f3979l, fragment);
                    }
                }
            }
        }
        Iterator it3 = backStackState.a(this, hashMap).iterator();
        while (true) {
            boolean z = false;
            while (it3.hasNext()) {
                if (((BackStackRecord) it3.next()).a(arrayList, arrayList2) || z) {
                    z = true;
                }
            }
            return z;
        }
    }

    List r0() {
        return this.f4047c.l();
    }

    void r1(Parcelable parcelable) {
        FragmentStateManager fragmentStateManager;
        Bundle bundle;
        Bundle bundle2;
        if (parcelable == null) {
            return;
        }
        Bundle bundle3 = (Bundle) parcelable;
        for (String str : bundle3.keySet()) {
            if (str.startsWith("result_") && (bundle2 = bundle3.getBundle(str)) != null) {
                bundle2.setClassLoader(this.v.r().getClassLoader());
                this.f4055k.put(str.substring(7), bundle2);
            }
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : bundle3.keySet()) {
            if (str2.startsWith("fragment_") && (bundle = bundle3.getBundle(str2)) != null) {
                bundle.setClassLoader(this.v.r().getClassLoader());
                arrayList.add((FragmentState) bundle.getParcelable("state"));
            }
        }
        this.f4047c.x(arrayList);
        FragmentManagerState fragmentManagerState = (FragmentManagerState) bundle3.getParcelable("state");
        if (fragmentManagerState == null) {
            return;
        }
        this.f4047c.v();
        Iterator it = fragmentManagerState.f4091c.iterator();
        while (it.hasNext()) {
            FragmentState B = this.f4047c.B((String) it.next(), null);
            if (B != null) {
                Fragment j2 = this.P.j(B.f4113h);
                if (j2 != null) {
                    if (N0(2)) {
                        Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + j2);
                    }
                    fragmentStateManager = new FragmentStateManager(this.f4058n, this.f4047c, j2, B);
                } else {
                    fragmentStateManager = new FragmentStateManager(this.f4058n, this.f4047c, this.v.r().getClassLoader(), x0(), B);
                }
                Fragment k2 = fragmentStateManager.k();
                k2.z = this;
                if (N0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + k2.f3979l + "): " + k2);
                }
                fragmentStateManager.o(this.v.r().getClassLoader());
                this.f4047c.r(fragmentStateManager);
                fragmentStateManager.u(this.u);
            }
        }
        for (Fragment fragment : this.P.m()) {
            if (!this.f4047c.c(fragment.f3979l)) {
                if (N0(2)) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + fragmentManagerState.f4091c);
                }
                this.P.p(fragment);
                fragment.z = this;
                FragmentStateManager fragmentStateManager2 = new FragmentStateManager(this.f4058n, this.f4047c, fragment);
                fragmentStateManager2.u(1);
                fragmentStateManager2.m();
                fragment.f3986s = true;
                fragmentStateManager2.m();
            }
        }
        this.f4047c.w(fragmentManagerState.f4092h);
        if (fragmentManagerState.f4093i != null) {
            this.f4048d = new ArrayList(fragmentManagerState.f4093i.length);
            int i2 = 0;
            while (true) {
                BackStackRecordState[] backStackRecordStateArr = fragmentManagerState.f4093i;
                if (i2 >= backStackRecordStateArr.length) {
                    break;
                }
                BackStackRecord b2 = backStackRecordStateArr[i2].b(this);
                if (N0(2)) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i2 + " (index " + b2.v + "): " + b2);
                    PrintWriter printWriter = new PrintWriter(new LogWriter("FragmentManager"));
                    b2.y("  ", printWriter, false);
                    printWriter.close();
                }
                this.f4048d.add(b2);
                i2++;
            }
        } else {
            this.f4048d = null;
        }
        this.f4053i.set(fragmentManagerState.f4094j);
        String str3 = fragmentManagerState.f4095k;
        if (str3 != null) {
            Fragment i0 = i0(str3);
            this.y = i0;
            O(i0);
        }
        ArrayList arrayList2 = fragmentManagerState.f4096l;
        if (arrayList2 != null) {
            for (int i3 = 0; i3 < arrayList2.size(); i3++) {
                this.f4054j.put((String) arrayList2.get(i3), (BackStackState) fragmentManagerState.f4097m.get(i3));
            }
        }
        this.G = new ArrayDeque(fragmentManagerState.f4098n);
    }

    public int s0() {
        ArrayList arrayList = this.f4048d;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    boolean t(ArrayList arrayList, ArrayList arrayList2, String str) {
        if (q1(arrayList, arrayList2, str)) {
            return j1(arrayList, arrayList2, str, -1, 1);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: t1, reason: merged with bridge method [inline-methods] */
    public Bundle V0() {
        BackStackRecordState[] backStackRecordStateArr;
        int size;
        Bundle bundle = new Bundle();
        p0();
        a0();
        d0(true);
        this.I = true;
        this.P.q(true);
        ArrayList y = this.f4047c.y();
        ArrayList m2 = this.f4047c.m();
        if (!m2.isEmpty()) {
            ArrayList z = this.f4047c.z();
            ArrayList arrayList = this.f4048d;
            if (arrayList == null || (size = arrayList.size()) <= 0) {
                backStackRecordStateArr = null;
            } else {
                backStackRecordStateArr = new BackStackRecordState[size];
                for (int i2 = 0; i2 < size; i2++) {
                    backStackRecordStateArr[i2] = new BackStackRecordState((BackStackRecord) this.f4048d.get(i2));
                    if (N0(2)) {
                        Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.f4048d.get(i2));
                    }
                }
            }
            FragmentManagerState fragmentManagerState = new FragmentManagerState();
            fragmentManagerState.f4091c = y;
            fragmentManagerState.f4092h = z;
            fragmentManagerState.f4093i = backStackRecordStateArr;
            fragmentManagerState.f4094j = this.f4053i.get();
            Fragment fragment = this.y;
            if (fragment != null) {
                fragmentManagerState.f4095k = fragment.f3979l;
            }
            fragmentManagerState.f4096l.addAll(this.f4054j.keySet());
            fragmentManagerState.f4097m.addAll(this.f4054j.values());
            fragmentManagerState.f4098n = new ArrayList(this.G);
            bundle.putParcelable("state", fragmentManagerState);
            for (String str : this.f4055k.keySet()) {
                bundle.putBundle("result_" + str, (Bundle) this.f4055k.get(str));
            }
            Iterator it = m2.iterator();
            while (it.hasNext()) {
                FragmentState fragmentState = (FragmentState) it.next();
                Bundle bundle2 = new Bundle();
                bundle2.putParcelable("state", fragmentState);
                bundle.putBundle("fragment_" + fragmentState.f4113h, bundle2);
            }
        } else if (N0(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
        }
        return bundle;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.x;
        if (fragment != null) {
            sb.append(fragment.getClass().getSimpleName());
            sb.append("{");
            sb.append(Integer.toHexString(System.identityHashCode(this.x)));
            sb.append("}");
        } else {
            FragmentHostCallback fragmentHostCallback = this.v;
            if (fragmentHostCallback != null) {
                sb.append(fragmentHostCallback.getClass().getSimpleName());
                sb.append("{");
                sb.append(Integer.toHexString(System.identityHashCode(this.v)));
                sb.append("}");
            } else {
                sb.append("null");
            }
        }
        sb.append("}}");
        return sb.toString();
    }

    FragmentContainer u0() {
        return this.w;
    }

    boolean u1(ArrayList arrayList, ArrayList arrayList2, String str) {
        int i2;
        int j0 = j0(str, -1, true);
        if (j0 < 0) {
            return false;
        }
        for (int i3 = j0; i3 < this.f4048d.size(); i3++) {
            BackStackRecord backStackRecord = (BackStackRecord) this.f4048d.get(i3);
            if (!backStackRecord.f4167r) {
                D1(new IllegalArgumentException("saveBackStack(\"" + str + "\") included FragmentTransactions must use setReorderingAllowed(true) to ensure that the back stack can be restored as an atomic operation. Found " + backStackRecord + " that did not use setReorderingAllowed(true)."));
            }
        }
        HashSet hashSet = new HashSet();
        for (int i4 = j0; i4 < this.f4048d.size(); i4++) {
            BackStackRecord backStackRecord2 = (BackStackRecord) this.f4048d.get(i4);
            HashSet hashSet2 = new HashSet();
            HashSet hashSet3 = new HashSet();
            Iterator it = backStackRecord2.f4152c.iterator();
            while (it.hasNext()) {
                FragmentTransaction.Op op = (FragmentTransaction.Op) it.next();
                Fragment fragment = op.f4170b;
                if (fragment != null) {
                    if (!op.f4171c || (i2 = op.f4169a) == 1 || i2 == 2 || i2 == 8) {
                        hashSet.add(fragment);
                        hashSet2.add(fragment);
                    }
                    int i5 = op.f4169a;
                    if (i5 == 1 || i5 == 2) {
                        hashSet3.add(fragment);
                    }
                }
            }
            hashSet2.removeAll(hashSet3);
            if (!hashSet2.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append("saveBackStack(\"");
                sb.append(str);
                sb.append("\") must be self contained and not reference fragments from non-saved FragmentTransactions. Found reference to fragment");
                sb.append(hashSet2.size() == 1 ? " " + hashSet2.iterator().next() : "s " + hashSet2);
                sb.append(" in ");
                sb.append(backStackRecord2);
                sb.append(" that were previously added to the FragmentManager through a separate FragmentTransaction.");
                D1(new IllegalArgumentException(sb.toString()));
            }
        }
        ArrayDeque arrayDeque = new ArrayDeque(hashSet);
        while (!arrayDeque.isEmpty()) {
            Fragment fragment2 = (Fragment) arrayDeque.removeFirst();
            if (fragment2.I) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("saveBackStack(\"");
                sb2.append(str);
                sb2.append("\") must not contain retained fragments. Found ");
                sb2.append(hashSet.contains(fragment2) ? "direct reference to retained " : "retained child ");
                sb2.append("fragment ");
                sb2.append(fragment2);
                D1(new IllegalArgumentException(sb2.toString()));
            }
            for (Fragment fragment3 : fragment2.B.r0()) {
                if (fragment3 != null) {
                    arrayDeque.addLast(fragment3);
                }
            }
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            arrayList3.add(((Fragment) it2.next()).f3979l);
        }
        ArrayList arrayList4 = new ArrayList(this.f4048d.size() - j0);
        for (int i6 = j0; i6 < this.f4048d.size(); i6++) {
            arrayList4.add(null);
        }
        BackStackState backStackState = new BackStackState(arrayList3, arrayList4);
        for (int size = this.f4048d.size() - 1; size >= j0; size--) {
            BackStackRecord backStackRecord3 = (BackStackRecord) this.f4048d.remove(size);
            BackStackRecord backStackRecord4 = new BackStackRecord(backStackRecord3);
            backStackRecord4.v();
            arrayList4.set(size - j0, new BackStackRecordState(backStackRecord4));
            backStackRecord3.w = true;
            arrayList.add(backStackRecord3);
            arrayList2.add(Boolean.TRUE);
        }
        this.f4054j.put(str, backStackState);
        return true;
    }

    public final void v(String str) {
        this.f4055k.remove(str);
        if (N0(2)) {
            Log.v("FragmentManager", "Clearing fragment result with key " + str);
        }
    }

    public Fragment v0(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment i0 = i0(string);
        if (i0 == null) {
            D1(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        }
        return i0;
    }

    public Fragment.SavedState v1(Fragment fragment) {
        FragmentStateManager n2 = this.f4047c.n(fragment.f3979l);
        if (n2 == null || !n2.k().equals(fragment)) {
            D1(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        }
        return n2.r();
    }

    void w1() {
        synchronized (this.f4045a) {
            try {
                if (this.f4045a.size() == 1) {
                    this.v.s().removeCallbacks(this.R);
                    this.v.s().post(this.R);
                    F1();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public FragmentFactory x0() {
        FragmentFactory fragmentFactory = this.z;
        if (fragmentFactory != null) {
            return fragmentFactory;
        }
        Fragment fragment = this.x;
        return fragment != null ? fragment.z.x0() : this.A;
    }

    void x1(Fragment fragment, boolean z) {
        ViewGroup w0 = w0(fragment);
        if (w0 == null || !(w0 instanceof FragmentContainerView)) {
            return;
        }
        ((FragmentContainerView) w0).setDrawDisappearingViewsLast(!z);
    }

    FragmentStateManager y(Fragment fragment) {
        FragmentStateManager n2 = this.f4047c.n(fragment.f3979l);
        if (n2 != null) {
            return n2;
        }
        FragmentStateManager fragmentStateManager = new FragmentStateManager(this.f4058n, this.f4047c, fragment);
        fragmentStateManager.o(this.v.r().getClassLoader());
        fragmentStateManager.u(this.u);
        return fragmentStateManager;
    }

    FragmentStore y0() {
        return this.f4047c;
    }

    void y1(Fragment fragment, Lifecycle.State state) {
        if (fragment.equals(i0(fragment.f3979l)) && (fragment.A == null || fragment.z == this)) {
            fragment.X = state;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void z(Fragment fragment) {
        if (N0(2)) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.H) {
            return;
        }
        fragment.H = true;
        if (fragment.f3985r) {
            if (N0(2)) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            this.f4047c.u(fragment);
            if (O0(fragment)) {
                this.H = true;
            }
            A1(fragment);
        }
    }

    public List z0() {
        return this.f4047c.o();
    }

    void z1(Fragment fragment) {
        if (fragment == null || (fragment.equals(i0(fragment.f3979l)) && (fragment.A == null || fragment.z == this))) {
            Fragment fragment2 = this.y;
            this.y = fragment;
            O(fragment2);
            O(this.y);
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    @SuppressLint({"BanParcelableUsage"})
    static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator<LaunchedFragmentInfo> CREATOR = new Parcelable.Creator<LaunchedFragmentInfo>() { // from class: androidx.fragment.app.FragmentManager.LaunchedFragmentInfo.1
            @Override // android.os.Parcelable.Creator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public LaunchedFragmentInfo createFromParcel(Parcel parcel) {
                return new LaunchedFragmentInfo(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public LaunchedFragmentInfo[] newArray(int i2) {
                return new LaunchedFragmentInfo[i2];
            }
        };

        /* renamed from: c, reason: collision with root package name */
        String f4080c;

        /* renamed from: h, reason: collision with root package name */
        int f4081h;

        LaunchedFragmentInfo(String str, int i2) {
            this.f4080c = str;
            this.f4081h = i2;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f4080c);
            parcel.writeInt(this.f4081h);
        }

        LaunchedFragmentInfo(Parcel parcel) {
            this.f4080c = parcel.readString();
            this.f4081h = parcel.readInt();
        }
    }
}
