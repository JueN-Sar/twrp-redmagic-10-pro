package com.google.android.datatransport.runtime.dagger.internal;

import com.google.android.datatransport.runtime.dagger.Lazy;
import com.google.android.datatransport.runtime.dagger.internal.AbstractMapFactory;
import java.util.Map;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MapProviderFactory<K, V> extends AbstractMapFactory<K, V, Provider<V>> implements Lazy<Map<K, Provider<V>>> {

    public static final class Builder<K, V> extends AbstractMapFactory.Builder<K, V, Provider<V>> {
    }

    @Override // javax.inject.Provider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public Map get() {
        return a();
    }
}
