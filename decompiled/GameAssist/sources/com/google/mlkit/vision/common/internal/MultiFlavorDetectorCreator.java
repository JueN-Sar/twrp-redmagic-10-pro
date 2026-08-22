package com.google.mlkit.vision.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@KeepForSdk
/* loaded from: classes.dex */
public class MultiFlavorDetectorCreator {

    /* renamed from: a, reason: collision with root package name */
    private final Map f16069a = new HashMap();

    @KeepForSdk
    public interface DetectorCreator<DetectorT extends MultiFlavorDetector, OptionsT extends DetectorOptions<DetectorT>> {
    }

    @KeepForSdk
    public interface DetectorOptions<DetectorT> {
    }

    @KeepForSdk
    public interface MultiFlavorDetector {
    }

    @KeepForSdk
    public static class Registration {

        /* renamed from: a, reason: collision with root package name */
        private final Class f16070a;

        /* renamed from: b, reason: collision with root package name */
        private final Provider f16071b;

        /* renamed from: c, reason: collision with root package name */
        private final int f16072c;

        final int a() {
            return this.f16072c;
        }

        final Provider b() {
            return this.f16071b;
        }

        final Class c() {
            return this.f16070a;
        }
    }

    MultiFlavorDetectorCreator(Set set) {
        HashMap hashMap = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Registration registration = (Registration) it.next();
            Class c2 = registration.c();
            if (!this.f16069a.containsKey(c2) || registration.a() >= ((Integer) Preconditions.i((Integer) hashMap.get(c2))).intValue()) {
                this.f16069a.put(c2, registration.b());
                hashMap.put(c2, Integer.valueOf(registration.a()));
            }
        }
    }
}
