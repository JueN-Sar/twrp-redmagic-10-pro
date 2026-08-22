package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzcl {
    static int a(Set set) {
        Iterator it = set.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i2 += next != null ? next.hashCode() : 0;
        }
        return i2;
    }

    static boolean b(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof zzcd) {
            collection = ((zzcd) collection).zza();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return c(set, collection.iterator());
        }
        Iterator it = set.iterator();
        collection.getClass();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }

    static boolean c(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }
}
