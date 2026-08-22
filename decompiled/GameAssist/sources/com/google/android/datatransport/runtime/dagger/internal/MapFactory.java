package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MapFactory<K, V> extends AbstractMapFactory<K, V, V> {

    /* renamed from: b, reason: collision with root package name */
    private static final Provider f10274b = InstanceFactory.a(Collections.emptyMap());

    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, V> {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Map get() {
        LinkedHashMap c2 = DaggerCollections.c(a().size());
        for (Map.Entry<K, V> entry : a().entrySet()) {
            c2.put(entry.getKey(), ((Provider) entry.getValue()).get());
        }
        return Collections.unmodifiableMap(c2);
    }
}
