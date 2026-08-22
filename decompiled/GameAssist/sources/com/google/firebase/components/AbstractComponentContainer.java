package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import java.util.Set;

/* loaded from: classes.dex */
abstract class AbstractComponentContainer implements ComponentContainer {
    AbstractComponentContainer() {
    }

    @Override // com.google.firebase.components.ComponentContainer
    public Object a(Class cls) {
        Provider d2 = d(cls);
        if (d2 == null) {
            return null;
        }
        return d2.get();
    }

    @Override // com.google.firebase.components.ComponentContainer
    public Set c(Class cls) {
        return (Set) b(cls).get();
    }
}
