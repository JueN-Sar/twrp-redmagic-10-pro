package androidx.coordinatorlayout.widget;

import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestrictTo
/* loaded from: classes.dex */
public final class DirectedAcyclicGraph<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Pools.Pool f2600a = new Pools.SimplePool(10);

    /* renamed from: b, reason: collision with root package name */
    private final SimpleArrayMap f2601b = new SimpleArrayMap();

    /* renamed from: c, reason: collision with root package name */
    private final ArrayList f2602c = new ArrayList();

    /* renamed from: d, reason: collision with root package name */
    private final HashSet f2603d = new HashSet();

    private void e(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (arrayList.contains(obj)) {
            return;
        }
        if (hashSet.contains(obj)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        hashSet.add(obj);
        ArrayList arrayList2 = (ArrayList) this.f2601b.get(obj);
        if (arrayList2 != null) {
            int size = arrayList2.size();
            for (int i2 = 0; i2 < size; i2++) {
                e(arrayList2.get(i2), arrayList, hashSet);
            }
        }
        hashSet.remove(obj);
        arrayList.add(obj);
    }

    private ArrayList f() {
        ArrayList arrayList = (ArrayList) this.f2600a.acquire();
        return arrayList == null ? new ArrayList() : arrayList;
    }

    private void k(ArrayList arrayList) {
        arrayList.clear();
        this.f2600a.release(arrayList);
    }

    public void a(Object obj, Object obj2) {
        if (!this.f2601b.containsKey(obj) || !this.f2601b.containsKey(obj2)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before being added as an edge");
        }
        ArrayList arrayList = (ArrayList) this.f2601b.get(obj);
        if (arrayList == null) {
            arrayList = f();
            this.f2601b.put(obj, arrayList);
        }
        arrayList.add(obj2);
    }

    public void b(Object obj) {
        if (this.f2601b.containsKey(obj)) {
            return;
        }
        this.f2601b.put(obj, null);
    }

    public void c() {
        int size = this.f2601b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList arrayList = (ArrayList) this.f2601b.j(i2);
            if (arrayList != null) {
                k(arrayList);
            }
        }
        this.f2601b.clear();
    }

    public boolean d(Object obj) {
        return this.f2601b.containsKey(obj);
    }

    public List g(Object obj) {
        return (List) this.f2601b.get(obj);
    }

    public List h(Object obj) {
        int size = this.f2601b.size();
        ArrayList arrayList = null;
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList arrayList2 = (ArrayList) this.f2601b.j(i2);
            if (arrayList2 != null && arrayList2.contains(obj)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(this.f2601b.f(i2));
            }
        }
        return arrayList;
    }

    public ArrayList i() {
        this.f2602c.clear();
        this.f2603d.clear();
        int size = this.f2601b.size();
        for (int i2 = 0; i2 < size; i2++) {
            e(this.f2601b.f(i2), this.f2602c, this.f2603d);
        }
        return this.f2602c;
    }

    public boolean j(Object obj) {
        int size = this.f2601b.size();
        for (int i2 = 0; i2 < size; i2++) {
            ArrayList arrayList = (ArrayList) this.f2601b.j(i2);
            if (arrayList != null && arrayList.contains(obj)) {
                return true;
            }
        }
        return false;
    }
}
