package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class FragmentStore {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f4140a = new ArrayList();

    /* renamed from: b, reason: collision with root package name */
    private final HashMap f4141b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private final HashMap f4142c = new HashMap();

    /* renamed from: d, reason: collision with root package name */
    private FragmentManagerViewModel f4143d;

    FragmentStore() {
    }

    void A(FragmentManagerViewModel fragmentManagerViewModel) {
        this.f4143d = fragmentManagerViewModel;
    }

    FragmentState B(String str, FragmentState fragmentState) {
        return fragmentState != null ? (FragmentState) this.f4142c.put(str, fragmentState) : (FragmentState) this.f4142c.remove(str);
    }

    void a(Fragment fragment) {
        if (this.f4140a.contains(fragment)) {
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
        synchronized (this.f4140a) {
            this.f4140a.add(fragment);
        }
        fragment.f3985r = true;
    }

    void b() {
        this.f4141b.values().removeAll(Collections.singleton(null));
    }

    boolean c(String str) {
        return this.f4141b.get(str) != null;
    }

    void d(int i2) {
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null) {
                fragmentStateManager.u(i2);
            }
        }
    }

    void e(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2 = str + "    ";
        if (!this.f4141b.isEmpty()) {
            printWriter.print(str);
            printWriter.println("Active Fragments:");
            for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
                printWriter.print(str);
                if (fragmentStateManager != null) {
                    Fragment k2 = fragmentStateManager.k();
                    printWriter.println(k2);
                    k2.o(str2, fileDescriptor, printWriter, strArr);
                } else {
                    printWriter.println("null");
                }
            }
        }
        int size = this.f4140a.size();
        if (size > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i2 = 0; i2 < size; i2++) {
                Fragment fragment = (Fragment) this.f4140a.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment.toString());
            }
        }
    }

    Fragment f(String str) {
        FragmentStateManager fragmentStateManager = (FragmentStateManager) this.f4141b.get(str);
        if (fragmentStateManager != null) {
            return fragmentStateManager.k();
        }
        return null;
    }

    Fragment g(int i2) {
        for (int size = this.f4140a.size() - 1; size >= 0; size--) {
            Fragment fragment = (Fragment) this.f4140a.get(size);
            if (fragment != null && fragment.D == i2) {
                return fragment;
            }
        }
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null) {
                Fragment k2 = fragmentStateManager.k();
                if (k2.D == i2) {
                    return k2;
                }
            }
        }
        return null;
    }

    Fragment h(String str) {
        if (str != null) {
            for (int size = this.f4140a.size() - 1; size >= 0; size--) {
                Fragment fragment = (Fragment) this.f4140a.get(size);
                if (fragment != null && str.equals(fragment.F)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null) {
                Fragment k2 = fragmentStateManager.k();
                if (str.equals(k2.F)) {
                    return k2;
                }
            }
        }
        return null;
    }

    Fragment i(String str) {
        Fragment q2;
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null && (q2 = fragmentStateManager.k().q(str)) != null) {
                return q2;
            }
        }
        return null;
    }

    int j(Fragment fragment) {
        View view;
        View view2;
        ViewGroup viewGroup = fragment.N;
        if (viewGroup == null) {
            return -1;
        }
        int indexOf = this.f4140a.indexOf(fragment);
        for (int i2 = indexOf - 1; i2 >= 0; i2--) {
            Fragment fragment2 = (Fragment) this.f4140a.get(i2);
            if (fragment2.N == viewGroup && (view2 = fragment2.O) != null) {
                return viewGroup.indexOfChild(view2) + 1;
            }
        }
        while (true) {
            indexOf++;
            if (indexOf >= this.f4140a.size()) {
                return -1;
            }
            Fragment fragment3 = (Fragment) this.f4140a.get(indexOf);
            if (fragment3.N == viewGroup && (view = fragment3.O) != null) {
                return viewGroup.indexOfChild(view);
            }
        }
    }

    List k() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager);
            }
        }
        return arrayList;
    }

    List l() {
        ArrayList arrayList = new ArrayList();
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null) {
                arrayList.add(fragmentStateManager.k());
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    ArrayList m() {
        return new ArrayList(this.f4142c.values());
    }

    FragmentStateManager n(String str) {
        return (FragmentStateManager) this.f4141b.get(str);
    }

    List o() {
        ArrayList arrayList;
        if (this.f4140a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f4140a) {
            arrayList = new ArrayList(this.f4140a);
        }
        return arrayList;
    }

    FragmentManagerViewModel p() {
        return this.f4143d;
    }

    FragmentState q(String str) {
        return (FragmentState) this.f4142c.get(str);
    }

    void r(FragmentStateManager fragmentStateManager) {
        Fragment k2 = fragmentStateManager.k();
        if (c(k2.f3979l)) {
            return;
        }
        this.f4141b.put(k2.f3979l, fragmentStateManager);
        if (k2.J) {
            if (k2.I) {
                this.f4143d.f(k2);
            } else {
                this.f4143d.p(k2);
            }
            k2.J = false;
        }
        if (FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Added fragment to active set " + k2);
        }
    }

    void s(FragmentStateManager fragmentStateManager) {
        Fragment k2 = fragmentStateManager.k();
        if (k2.I) {
            this.f4143d.p(k2);
        }
        if (((FragmentStateManager) this.f4141b.put(k2.f3979l, null)) != null && FragmentManager.N0(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + k2);
        }
    }

    void t() {
        Iterator it = this.f4140a.iterator();
        while (it.hasNext()) {
            FragmentStateManager fragmentStateManager = (FragmentStateManager) this.f4141b.get(((Fragment) it.next()).f3979l);
            if (fragmentStateManager != null) {
                fragmentStateManager.m();
            }
        }
        for (FragmentStateManager fragmentStateManager2 : this.f4141b.values()) {
            if (fragmentStateManager2 != null) {
                fragmentStateManager2.m();
                Fragment k2 = fragmentStateManager2.k();
                if (k2.f3986s && !k2.p0()) {
                    if (k2.t && !this.f4142c.containsKey(k2.f3979l)) {
                        fragmentStateManager2.s();
                    }
                    s(fragmentStateManager2);
                }
            }
        }
    }

    void u(Fragment fragment) {
        synchronized (this.f4140a) {
            this.f4140a.remove(fragment);
        }
        fragment.f3985r = false;
    }

    void v() {
        this.f4141b.clear();
    }

    void w(List list) {
        this.f4140a.clear();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Fragment f2 = f(str);
                if (f2 == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + str + ")");
                }
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + f2);
                }
                a(f2);
            }
        }
    }

    void x(ArrayList arrayList) {
        this.f4142c.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            FragmentState fragmentState = (FragmentState) it.next();
            this.f4142c.put(fragmentState.f4113h, fragmentState);
        }
    }

    ArrayList y() {
        ArrayList arrayList = new ArrayList(this.f4141b.size());
        for (FragmentStateManager fragmentStateManager : this.f4141b.values()) {
            if (fragmentStateManager != null) {
                Fragment k2 = fragmentStateManager.k();
                fragmentStateManager.s();
                arrayList.add(k2.f3979l);
                if (FragmentManager.N0(2)) {
                    Log.v("FragmentManager", "Saved state of " + k2 + ": " + k2.f3975h);
                }
            }
        }
        return arrayList;
    }

    ArrayList z() {
        synchronized (this.f4140a) {
            try {
                if (this.f4140a.isEmpty()) {
                    return null;
                }
                ArrayList arrayList = new ArrayList(this.f4140a.size());
                Iterator it = this.f4140a.iterator();
                while (it.hasNext()) {
                    Fragment fragment = (Fragment) it.next();
                    arrayList.add(fragment.f3979l);
                    if (FragmentManager.N0(2)) {
                        Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment.f3979l + "): " + fragment);
                    }
                }
                return arrayList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
