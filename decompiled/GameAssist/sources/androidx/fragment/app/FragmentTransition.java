package androidx.fragment.app;

import android.view.View;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.transition.FragmentTransitionSupport;
import java.util.ArrayList;

/* loaded from: classes.dex */
class FragmentTransition {

    /* renamed from: a, reason: collision with root package name */
    static final FragmentTransitionImpl f4178a = new FragmentTransitionCompat21();

    /* renamed from: b, reason: collision with root package name */
    static final FragmentTransitionImpl f4179b = c();

    static void a(Fragment fragment, Fragment fragment2, boolean z, ArrayMap arrayMap, boolean z2) {
        SharedElementCallback C = z ? fragment2.C() : fragment.C();
        if (C != null) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            int size = arrayMap == null ? 0 : arrayMap.size();
            for (int i2 = 0; i2 < size; i2++) {
                arrayList2.add((String) arrayMap.f(i2));
                arrayList.add((View) arrayMap.j(i2));
            }
            if (z2) {
                C.g(arrayList2, arrayList, null);
            } else {
                C.f(arrayList2, arrayList, null);
            }
        }
    }

    static String b(ArrayMap arrayMap, String str) {
        int size = arrayMap.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(arrayMap.j(i2))) {
                return (String) arrayMap.f(i2);
            }
        }
        return null;
    }

    private static FragmentTransitionImpl c() {
        try {
            return (FragmentTransitionImpl) FragmentTransitionSupport.class.getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
            return null;
        }
    }

    static void d(ArrayMap arrayMap, ArrayMap arrayMap2) {
        for (int size = arrayMap.size() - 1; size >= 0; size--) {
            if (!arrayMap2.containsKey((String) arrayMap.j(size))) {
                arrayMap.h(size);
            }
        }
    }

    static void e(ArrayList arrayList, int i2) {
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((View) arrayList.get(size)).setVisibility(i2);
        }
    }
}
