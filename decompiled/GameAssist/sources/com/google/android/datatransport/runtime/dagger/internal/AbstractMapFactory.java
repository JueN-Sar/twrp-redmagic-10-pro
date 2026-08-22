package com.google.android.datatransport.runtime.dagger.internal;

import java.util.Map;

/* loaded from: classes.dex */
abstract class AbstractMapFactory<K, V, V2> implements Factory<Map<K, V2>> {

    /* renamed from: a, reason: collision with root package name */
    private final Map f10267a;

    public static abstract class Builder<K, V, V2> {
    }

    final Map a() {
        return this.f10267a;
    }
}
