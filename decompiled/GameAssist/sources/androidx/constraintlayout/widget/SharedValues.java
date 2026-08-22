package androidx.constraintlayout.widget;

import android.util.SparseIntArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class SharedValues {

    /* renamed from: a, reason: collision with root package name */
    private SparseIntArray f2561a = new SparseIntArray();

    /* renamed from: b, reason: collision with root package name */
    private HashMap f2562b = new HashMap();

    public interface SharedValuesListener {
    }

    public void a(int i2, SharedValuesListener sharedValuesListener) {
        HashSet hashSet = (HashSet) this.f2562b.get(Integer.valueOf(i2));
        if (hashSet == null) {
            hashSet = new HashSet();
            this.f2562b.put(Integer.valueOf(i2), hashSet);
        }
        hashSet.add(new WeakReference(sharedValuesListener));
    }

    public void b(int i2, SharedValuesListener sharedValuesListener) {
        HashSet hashSet = (HashSet) this.f2562b.get(Integer.valueOf(i2));
        if (hashSet == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            WeakReference weakReference = (WeakReference) it.next();
            SharedValuesListener sharedValuesListener2 = (SharedValuesListener) weakReference.get();
            if (sharedValuesListener2 == null || sharedValuesListener2 == sharedValuesListener) {
                arrayList.add(weakReference);
            }
        }
        hashSet.removeAll(arrayList);
    }
}
