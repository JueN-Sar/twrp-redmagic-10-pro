package com.google.mlkit.common.model;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class RemoteModelManager {

    /* renamed from: a, reason: collision with root package name */
    private final Map f15923a = new HashMap();

    @KeepForSdk
    public static class RemoteModelManagerRegistration {

        /* renamed from: a, reason: collision with root package name */
        private final Class f15924a;

        /* renamed from: b, reason: collision with root package name */
        private final Provider f15925b;

        public RemoteModelManagerRegistration(Class cls, Provider provider) {
            this.f15924a = cls;
            this.f15925b = provider;
        }

        final Provider a() {
            return this.f15925b;
        }

        final Class b() {
            return this.f15924a;
        }
    }

    public RemoteModelManager(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            RemoteModelManagerRegistration remoteModelManagerRegistration = (RemoteModelManagerRegistration) it.next();
            this.f15923a.put(remoteModelManagerRegistration.b(), remoteModelManagerRegistration.a());
        }
    }
}
