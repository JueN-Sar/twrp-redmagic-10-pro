package com.google.android.datatransport.runtime.backends;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
/* loaded from: classes.dex */
class MetadataBackendRegistry implements BackendRegistry {

    /* renamed from: a, reason: collision with root package name */
    private final BackendFactoryProvider f10260a;

    /* renamed from: b, reason: collision with root package name */
    private final CreationContextFactory f10261b;

    /* renamed from: c, reason: collision with root package name */
    private final Map f10262c;

    static class BackendFactoryProvider {

        /* renamed from: a, reason: collision with root package name */
        private final Context f10263a;

        /* renamed from: b, reason: collision with root package name */
        private Map f10264b = null;

        BackendFactoryProvider(Context context) {
            this.f10263a = context;
        }

        private Map a(Context context) {
            Bundle d2 = d(context);
            if (d2 == null) {
                Log.w("BackendRegistry", "Could not retrieve metadata, returning empty list of transport backends.");
                return Collections.emptyMap();
            }
            HashMap hashMap = new HashMap();
            for (String str : d2.keySet()) {
                Object obj = d2.get(str);
                if ((obj instanceof String) && str.startsWith("backend:")) {
                    for (String str2 : ((String) obj).split(",", -1)) {
                        String trim = str2.trim();
                        if (!trim.isEmpty()) {
                            hashMap.put(trim, str.substring(8));
                        }
                    }
                }
            }
            return hashMap;
        }

        private Map c() {
            if (this.f10264b == null) {
                this.f10264b = a(this.f10263a);
            }
            return this.f10264b;
        }

        private static Bundle d(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("BackendRegistry", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) TransportBackendDiscovery.class), 128);
                if (serviceInfo != null) {
                    return serviceInfo.metaData;
                }
                Log.w("BackendRegistry", "TransportBackendDiscovery has no service info.");
                return null;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("BackendRegistry", "Application info not found.");
                return null;
            }
        }

        BackendFactory b(String str) {
            String str2 = (String) c().get(str);
            if (str2 == null) {
                return null;
            }
            try {
                return (BackendFactory) Class.forName(str2).asSubclass(BackendFactory.class).getDeclaredConstructor(null).newInstance(null);
            } catch (ClassNotFoundException e2) {
                Log.w("BackendRegistry", String.format("Class %s is not found.", str2), e2);
                return null;
            } catch (IllegalAccessException e3) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s.", str2), e3);
                return null;
            } catch (InstantiationException e4) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s.", str2), e4);
                return null;
            } catch (NoSuchMethodException e5) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s", str2), e5);
                return null;
            } catch (InvocationTargetException e6) {
                Log.w("BackendRegistry", String.format("Could not instantiate %s", str2), e6);
                return null;
            }
        }
    }

    MetadataBackendRegistry(Context context, CreationContextFactory creationContextFactory) {
        this(new BackendFactoryProvider(context), creationContextFactory);
    }

    @Override // com.google.android.datatransport.runtime.backends.BackendRegistry
    public synchronized TransportBackend a(String str) {
        if (this.f10262c.containsKey(str)) {
            return (TransportBackend) this.f10262c.get(str);
        }
        BackendFactory b2 = this.f10260a.b(str);
        if (b2 == null) {
            return null;
        }
        TransportBackend create = b2.create(this.f10261b.a(str));
        this.f10262c.put(str, create);
        return create;
    }

    MetadataBackendRegistry(BackendFactoryProvider backendFactoryProvider, CreationContextFactory creationContextFactory) {
        this.f10262c = new HashMap();
        this.f10260a = backendFactoryProvider;
        this.f10261b = creationContextFactory;
    }
}
