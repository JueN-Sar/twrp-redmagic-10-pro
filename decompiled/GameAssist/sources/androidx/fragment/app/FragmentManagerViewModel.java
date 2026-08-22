package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes.dex */
final class FragmentManagerViewModel extends ViewModel {

    /* renamed from: k, reason: collision with root package name */
    private static final ViewModelProvider.Factory f4099k = new ViewModelProvider.Factory() { // from class: androidx.fragment.app.FragmentManagerViewModel.1
        @Override // androidx.lifecycle.ViewModelProvider.Factory
        public ViewModel b(Class cls) {
            return new FragmentManagerViewModel(true);
        }
    };

    /* renamed from: g, reason: collision with root package name */
    private final boolean f4103g;

    /* renamed from: d, reason: collision with root package name */
    private final HashMap f4100d = new HashMap();

    /* renamed from: e, reason: collision with root package name */
    private final HashMap f4101e = new HashMap();

    /* renamed from: f, reason: collision with root package name */
    private final HashMap f4102f = new HashMap();

    /* renamed from: h, reason: collision with root package name */
    private boolean f4104h = false;

    /* renamed from: i, reason: collision with root package name */
    private boolean f4105i = false;

    /* renamed from: j, reason: collision with root package name */
    private boolean f4106j = false;

    FragmentManagerViewModel(boolean z) {
        this.f4103g = z;
    }

    private void i(String str) {
        FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) this.f4101e.get(str);
        if (fragmentManagerViewModel != null) {
            fragmentManagerViewModel.d();
            this.f4101e.remove(str);
        }
        ViewModelStore viewModelStore = (ViewModelStore) this.f4102f.get(str);
        if (viewModelStore != null) {
            viewModelStore.a();
            this.f4102f.remove(str);
        }
    }

    static FragmentManagerViewModel l(ViewModelStore viewModelStore) {
        return (FragmentManagerViewModel) new ViewModelProvider(viewModelStore, f4099k).a(FragmentManagerViewModel.class);
    }

    @Override // androidx.lifecycle.ViewModel
    protected void d() {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f4104h = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FragmentManagerViewModel.class != obj.getClass()) {
            return false;
        }
        FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) obj;
        return this.f4100d.equals(fragmentManagerViewModel.f4100d) && this.f4101e.equals(fragmentManagerViewModel.f4101e) && this.f4102f.equals(fragmentManagerViewModel.f4102f);
    }

    void f(Fragment fragment) {
        if (this.f4106j) {
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else {
            if (this.f4100d.containsKey(fragment.f3979l)) {
                return;
            }
            this.f4100d.put(fragment.f3979l, fragment);
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    void g(Fragment fragment) {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i(fragment.f3979l);
    }

    void h(String str) {
        if (FragmentManager.N0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for saved state of Fragment " + str);
        }
        i(str);
    }

    public int hashCode() {
        return (((this.f4100d.hashCode() * 31) + this.f4101e.hashCode()) * 31) + this.f4102f.hashCode();
    }

    Fragment j(String str) {
        return (Fragment) this.f4100d.get(str);
    }

    FragmentManagerViewModel k(Fragment fragment) {
        FragmentManagerViewModel fragmentManagerViewModel = (FragmentManagerViewModel) this.f4101e.get(fragment.f3979l);
        if (fragmentManagerViewModel != null) {
            return fragmentManagerViewModel;
        }
        FragmentManagerViewModel fragmentManagerViewModel2 = new FragmentManagerViewModel(this.f4103g);
        this.f4101e.put(fragment.f3979l, fragmentManagerViewModel2);
        return fragmentManagerViewModel2;
    }

    Collection m() {
        return new ArrayList(this.f4100d.values());
    }

    ViewModelStore n(Fragment fragment) {
        ViewModelStore viewModelStore = (ViewModelStore) this.f4102f.get(fragment.f3979l);
        if (viewModelStore != null) {
            return viewModelStore;
        }
        ViewModelStore viewModelStore2 = new ViewModelStore();
        this.f4102f.put(fragment.f3979l, viewModelStore2);
        return viewModelStore2;
    }

    boolean o() {
        return this.f4104h;
    }

    void p(Fragment fragment) {
        if (this.f4106j) {
            if (FragmentManager.N0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else {
            if (this.f4100d.remove(fragment.f3979l) == null || !FragmentManager.N0(2)) {
                return;
            }
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    void q(boolean z) {
        this.f4106j = z;
    }

    boolean r(Fragment fragment) {
        if (this.f4100d.containsKey(fragment.f3979l)) {
            return this.f4103g ? this.f4104h : !this.f4105i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f4100d.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f4101e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f4102f.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
