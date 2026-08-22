package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
abstract class SpecialEffectsController {

    /* renamed from: a, reason: collision with root package name */
    private final ViewGroup f4210a;

    /* renamed from: b, reason: collision with root package name */
    final ArrayList f4211b = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    final ArrayList f4212c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    boolean f4213d = false;

    /* renamed from: e, reason: collision with root package name */
    boolean f4214e = false;

    /* renamed from: androidx.fragment.app.SpecialEffectsController$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f4219a;

        /* renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f4220b;

        static {
            int[] iArr = new int[Operation.LifecycleImpact.values().length];
            f4220b = iArr;
            try {
                iArr[Operation.LifecycleImpact.ADDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f4220b[Operation.LifecycleImpact.REMOVING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f4220b[Operation.LifecycleImpact.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[Operation.State.values().length];
            f4219a = iArr2;
            try {
                iArr2[Operation.State.REMOVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f4219a[Operation.State.VISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f4219a[Operation.State.GONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f4219a[Operation.State.INVISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    private static class FragmentStateManagerOperation extends Operation {

        /* renamed from: h, reason: collision with root package name */
        private final FragmentStateManager f4221h;

        FragmentStateManagerOperation(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager, CancellationSignal cancellationSignal) {
            super(state, lifecycleImpact, fragmentStateManager.k(), cancellationSignal);
            this.f4221h = fragmentStateManager;
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        public void c() {
            super.c();
            this.f4221h.m();
        }

        @Override // androidx.fragment.app.SpecialEffectsController.Operation
        void l() {
            if (g() != Operation.LifecycleImpact.ADDING) {
                if (g() == Operation.LifecycleImpact.REMOVING) {
                    Fragment k2 = this.f4221h.k();
                    View E1 = k2.E1();
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "Clearing focus " + E1.findFocus() + " on view " + E1 + " for Fragment " + k2);
                    }
                    E1.clearFocus();
                    return;
                }
                return;
            }
            Fragment k3 = this.f4221h.k();
            View findFocus = k3.O.findFocus();
            if (findFocus != null) {
                k3.K1(findFocus);
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", "requestFocus: Saved focused view " + findFocus + " for Fragment " + k3);
                }
            }
            View E12 = f().E1();
            if (E12.getParent() == null) {
                this.f4221h.b();
                E12.setAlpha(0.0f);
            }
            if (E12.getAlpha() == 0.0f && E12.getVisibility() == 0) {
                E12.setVisibility(4);
            }
            E12.setAlpha(k3.S());
        }
    }

    static class Operation {

        /* renamed from: a, reason: collision with root package name */
        private State f4222a;

        /* renamed from: b, reason: collision with root package name */
        private LifecycleImpact f4223b;

        /* renamed from: c, reason: collision with root package name */
        private final Fragment f4224c;

        /* renamed from: d, reason: collision with root package name */
        private final List f4225d = new ArrayList();

        /* renamed from: e, reason: collision with root package name */
        private final HashSet f4226e = new HashSet();

        /* renamed from: f, reason: collision with root package name */
        private boolean f4227f = false;

        /* renamed from: g, reason: collision with root package name */
        private boolean f4228g = false;

        enum LifecycleImpact {
            NONE,
            ADDING,
            REMOVING
        }

        enum State {
            REMOVED,
            VISIBLE,
            GONE,
            INVISIBLE;

            static State d(int i2) {
                if (i2 == 0) {
                    return VISIBLE;
                }
                if (i2 == 4) {
                    return INVISIBLE;
                }
                if (i2 == 8) {
                    return GONE;
                }
                throw new IllegalArgumentException("Unknown visibility " + i2);
            }

            static State e(View view) {
                return (view.getAlpha() == 0.0f && view.getVisibility() == 0) ? INVISIBLE : d(view.getVisibility());
            }

            void c(View view) {
                int i2 = AnonymousClass3.f4219a[ordinal()];
                if (i2 == 1) {
                    ViewGroup viewGroup = (ViewGroup) view.getParent();
                    if (viewGroup != null) {
                        if (FragmentManager.N0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Removing view " + view + " from container " + viewGroup);
                        }
                        viewGroup.removeView(view);
                        return;
                    }
                    return;
                }
                if (i2 == 2) {
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to VISIBLE");
                    }
                    view.setVisibility(0);
                    return;
                }
                if (i2 == 3) {
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to GONE");
                    }
                    view.setVisibility(8);
                    return;
                }
                if (i2 != 4) {
                    return;
                }
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: Setting view " + view + " to INVISIBLE");
                }
                view.setVisibility(4);
            }
        }

        Operation(State state, LifecycleImpact lifecycleImpact, Fragment fragment, CancellationSignal cancellationSignal) {
            this.f4222a = state;
            this.f4223b = lifecycleImpact;
            this.f4224c = fragment;
            cancellationSignal.c(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.SpecialEffectsController.Operation.1
                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                public void onCancel() {
                    Operation.this.b();
                }
            });
        }

        final void a(Runnable runnable) {
            this.f4225d.add(runnable);
        }

        final void b() {
            if (h()) {
                return;
            }
            this.f4227f = true;
            if (this.f4226e.isEmpty()) {
                c();
                return;
            }
            Iterator it = new ArrayList(this.f4226e).iterator();
            while (it.hasNext()) {
                ((CancellationSignal) it.next()).a();
            }
        }

        public void c() {
            if (this.f4228g) {
                return;
            }
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: " + this + " has called complete.");
            }
            this.f4228g = true;
            Iterator it = this.f4225d.iterator();
            while (it.hasNext()) {
                ((Runnable) it.next()).run();
            }
        }

        public final void d(CancellationSignal cancellationSignal) {
            if (this.f4226e.remove(cancellationSignal) && this.f4226e.isEmpty()) {
                c();
            }
        }

        public State e() {
            return this.f4222a;
        }

        public final Fragment f() {
            return this.f4224c;
        }

        LifecycleImpact g() {
            return this.f4223b;
        }

        final boolean h() {
            return this.f4227f;
        }

        final boolean i() {
            return this.f4228g;
        }

        public final void j(CancellationSignal cancellationSignal) {
            l();
            this.f4226e.add(cancellationSignal);
        }

        final void k(State state, LifecycleImpact lifecycleImpact) {
            int i2 = AnonymousClass3.f4220b[lifecycleImpact.ordinal()];
            if (i2 == 1) {
                if (this.f4222a == State.REMOVED) {
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f4224c + " mFinalState = REMOVED -> VISIBLE. mLifecycleImpact = " + this.f4223b + " to ADDING.");
                    }
                    this.f4222a = State.VISIBLE;
                    this.f4223b = LifecycleImpact.ADDING;
                    return;
                }
                return;
            }
            if (i2 == 2) {
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f4224c + " mFinalState = " + this.f4222a + " -> REMOVED. mLifecycleImpact  = " + this.f4223b + " to REMOVING.");
                }
                this.f4222a = State.REMOVED;
                this.f4223b = LifecycleImpact.REMOVING;
                return;
            }
            if (i2 == 3 && this.f4222a != State.REMOVED) {
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", "SpecialEffectsController: For fragment " + this.f4224c + " mFinalState = " + this.f4222a + " -> " + state + ". ");
                }
                this.f4222a = state;
            }
        }

        void l() {
        }

        public String toString() {
            return "Operation {" + Integer.toHexString(System.identityHashCode(this)) + "} {mFinalState = " + this.f4222a + "} {mLifecycleImpact = " + this.f4223b + "} {mFragment = " + this.f4224c + "}";
        }
    }

    SpecialEffectsController(ViewGroup viewGroup) {
        this.f4210a = viewGroup;
    }

    private void a(Operation.State state, Operation.LifecycleImpact lifecycleImpact, FragmentStateManager fragmentStateManager) {
        synchronized (this.f4211b) {
            try {
                CancellationSignal cancellationSignal = new CancellationSignal();
                Operation h2 = h(fragmentStateManager.k());
                if (h2 != null) {
                    h2.k(state, lifecycleImpact);
                    return;
                }
                final FragmentStateManagerOperation fragmentStateManagerOperation = new FragmentStateManagerOperation(state, lifecycleImpact, fragmentStateManager, cancellationSignal);
                this.f4211b.add(fragmentStateManagerOperation);
                fragmentStateManagerOperation.a(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (SpecialEffectsController.this.f4211b.contains(fragmentStateManagerOperation)) {
                            fragmentStateManagerOperation.e().c(fragmentStateManagerOperation.f().O);
                        }
                    }
                });
                fragmentStateManagerOperation.a(new Runnable() { // from class: androidx.fragment.app.SpecialEffectsController.2
                    @Override // java.lang.Runnable
                    public void run() {
                        SpecialEffectsController.this.f4211b.remove(fragmentStateManagerOperation);
                        SpecialEffectsController.this.f4212c.remove(fragmentStateManagerOperation);
                    }
                });
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private Operation h(Fragment fragment) {
        Iterator it = this.f4211b.iterator();
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (operation.f().equals(fragment) && !operation.h()) {
                return operation;
            }
        }
        return null;
    }

    private Operation i(Fragment fragment) {
        Iterator it = this.f4212c.iterator();
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (operation.f().equals(fragment) && !operation.h()) {
                return operation;
            }
        }
        return null;
    }

    static SpecialEffectsController n(ViewGroup viewGroup, FragmentManager fragmentManager) {
        return o(viewGroup, fragmentManager.F0());
    }

    static SpecialEffectsController o(ViewGroup viewGroup, SpecialEffectsControllerFactory specialEffectsControllerFactory) {
        Object tag = viewGroup.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof SpecialEffectsController) {
            return (SpecialEffectsController) tag;
        }
        SpecialEffectsController a2 = specialEffectsControllerFactory.a(viewGroup);
        viewGroup.setTag(R.id.special_effects_controller_view_tag, a2);
        return a2;
    }

    private void q() {
        Iterator it = this.f4211b.iterator();
        while (it.hasNext()) {
            Operation operation = (Operation) it.next();
            if (operation.g() == Operation.LifecycleImpact.ADDING) {
                operation.k(Operation.State.d(operation.f().E1().getVisibility()), Operation.LifecycleImpact.NONE);
            }
        }
    }

    void b(Operation.State state, FragmentStateManager fragmentStateManager) {
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + fragmentStateManager.k());
        }
        a(state, Operation.LifecycleImpact.ADDING, fragmentStateManager);
    }

    void c(FragmentStateManager fragmentStateManager) {
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + fragmentStateManager.k());
        }
        a(Operation.State.GONE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    void d(FragmentStateManager fragmentStateManager) {
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + fragmentStateManager.k());
        }
        a(Operation.State.REMOVED, Operation.LifecycleImpact.REMOVING, fragmentStateManager);
    }

    void e(FragmentStateManager fragmentStateManager) {
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + fragmentStateManager.k());
        }
        a(Operation.State.VISIBLE, Operation.LifecycleImpact.NONE, fragmentStateManager);
    }

    abstract void f(List list, boolean z);

    void g() {
        if (this.f4214e) {
            return;
        }
        if (!ViewCompat.M(this.f4210a)) {
            j();
            this.f4213d = false;
            return;
        }
        synchronized (this.f4211b) {
            try {
                if (!this.f4211b.isEmpty()) {
                    ArrayList arrayList = new ArrayList(this.f4212c);
                    this.f4212c.clear();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        Operation operation = (Operation) it.next();
                        if (FragmentManager.N0(2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + operation);
                        }
                        operation.b();
                        if (!operation.i()) {
                            this.f4212c.add(operation);
                        }
                    }
                    q();
                    ArrayList arrayList2 = new ArrayList(this.f4211b);
                    this.f4211b.clear();
                    this.f4212c.addAll(arrayList2);
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Executing pending operations");
                    }
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        ((Operation) it2.next()).l();
                    }
                    f(arrayList2, this.f4213d);
                    this.f4213d = false;
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Finished executing pending operations");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void j() {
        String str;
        String str2;
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        boolean M = ViewCompat.M(this.f4210a);
        synchronized (this.f4211b) {
            try {
                q();
                Iterator it = this.f4211b.iterator();
                while (it.hasNext()) {
                    ((Operation) it.next()).l();
                }
                Iterator it2 = new ArrayList(this.f4212c).iterator();
                while (it2.hasNext()) {
                    Operation operation = (Operation) it2.next();
                    if (FragmentManager.N0(2)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("SpecialEffectsController: ");
                        if (M) {
                            str2 = "";
                        } else {
                            str2 = "Container " + this.f4210a + " is not attached to window. ";
                        }
                        sb.append(str2);
                        sb.append("Cancelling running operation ");
                        sb.append(operation);
                        Log.v("FragmentManager", sb.toString());
                    }
                    operation.b();
                }
                Iterator it3 = new ArrayList(this.f4211b).iterator();
                while (it3.hasNext()) {
                    Operation operation2 = (Operation) it3.next();
                    if (FragmentManager.N0(2)) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("SpecialEffectsController: ");
                        if (M) {
                            str = "";
                        } else {
                            str = "Container " + this.f4210a + " is not attached to window. ";
                        }
                        sb2.append(str);
                        sb2.append("Cancelling pending operation ");
                        sb2.append(operation2);
                        Log.v("FragmentManager", sb2.toString());
                    }
                    operation2.b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void k() {
        if (this.f4214e) {
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "SpecialEffectsController: Forcing postponed operations");
            }
            this.f4214e = false;
            g();
        }
    }

    Operation.LifecycleImpact l(FragmentStateManager fragmentStateManager) {
        Operation h2 = h(fragmentStateManager.k());
        Operation.LifecycleImpact g2 = h2 != null ? h2.g() : null;
        Operation i2 = i(fragmentStateManager.k());
        return (i2 == null || !(g2 == null || g2 == Operation.LifecycleImpact.NONE)) ? g2 : i2.g();
    }

    public ViewGroup m() {
        return this.f4210a;
    }

    void p() {
        synchronized (this.f4211b) {
            try {
                q();
                this.f4214e = false;
                int size = this.f4211b.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    Operation operation = (Operation) this.f4211b.get(size);
                    Operation.State e2 = Operation.State.e(operation.f().O);
                    Operation.State e3 = operation.e();
                    Operation.State state = Operation.State.VISIBLE;
                    if (e3 == state && e2 != state) {
                        this.f4214e = operation.f().s0();
                        break;
                    }
                    size--;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    void r(boolean z) {
        this.f4213d = z;
    }
}
