package com.google.android.datatransport.runtime.dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SetFactory<T> implements Factory<Set<T>> {

    /* renamed from: c, reason: collision with root package name */
    private static final Factory f10276c = InstanceFactory.a(Collections.emptySet());

    /* renamed from: a, reason: collision with root package name */
    private final List f10277a;

    /* renamed from: b, reason: collision with root package name */
    private final List f10278b;

    public static final class Builder<T> {
    }

    @Override // javax.inject.Provider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public Set get() {
        int size = this.f10277a.size();
        ArrayList arrayList = new ArrayList(this.f10278b.size());
        int size2 = this.f10278b.size();
        for (int i2 = 0; i2 < size2; i2++) {
            Collection collection = (Collection) ((Provider) this.f10278b.get(i2)).get();
            size += collection.size();
            arrayList.add(collection);
        }
        HashSet b2 = DaggerCollections.b(size);
        int size3 = this.f10277a.size();
        for (int i3 = 0; i3 < size3; i3++) {
            b2.add(Preconditions.b(((Provider) this.f10277a.get(i3)).get()));
        }
        int size4 = arrayList.size();
        for (int i4 = 0; i4 < size4; i4++) {
            Iterator it = ((Collection) arrayList.get(i4)).iterator();
            while (it.hasNext()) {
                b2.add(Preconditions.b(it.next()));
            }
        }
        return Collections.unmodifiableSet(b2);
    }
}
