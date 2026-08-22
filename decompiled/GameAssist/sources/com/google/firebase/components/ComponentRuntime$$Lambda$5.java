package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Collections;

/* loaded from: classes.dex */
final /* synthetic */ class ComponentRuntime$$Lambda$5 implements Provider {

    /* renamed from: a, reason: collision with root package name */
    private static final ComponentRuntime$$Lambda$5 f15822a = new ComponentRuntime$$Lambda$5();

    private ComponentRuntime$$Lambda$5() {
    }

    public static Provider a() {
        return f15822a;
    }

    @Override // com.google.firebase.inject.Provider
    public Object get() {
        return Collections.emptySet();
    }
}
