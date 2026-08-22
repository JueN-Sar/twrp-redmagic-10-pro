package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class ComponentDiscovery<T> {

    /* renamed from: a, reason: collision with root package name */
    private final Object f15804a;

    /* renamed from: b, reason: collision with root package name */
    private final RegistrarNameRetriever f15805b;

    private static class MetadataRegistrarNameRetriever implements RegistrarNameRetriever<Context> {

        /* renamed from: a, reason: collision with root package name */
        private final Class f15807a;

        private Bundle a(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w("ComponentDiscovery", "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, (Class<?>) this.f15807a), 128);
                if (serviceInfo != null) {
                    return serviceInfo.metaData;
                }
                Log.w("ComponentDiscovery", this.f15807a + " has no service info.");
                return null;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w("ComponentDiscovery", "Application info not found.");
                return null;
            }
        }

        @Override // com.google.firebase.components.ComponentDiscovery.RegistrarNameRetriever
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public List retrieve(Context context) {
            Bundle a2 = a(context);
            if (a2 == null) {
                Log.w("ComponentDiscovery", "Could not retrieve metadata, returning empty list of registrars.");
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            for (String str : a2.keySet()) {
                if ("com.google.firebase.components.ComponentRegistrar".equals(a2.get(str)) && str.startsWith("com.google.firebase.components:")) {
                    arrayList.add(str.substring(31));
                }
            }
            return arrayList;
        }

        private MetadataRegistrarNameRetriever(Class cls) {
            this.f15807a = cls;
        }
    }

    @VisibleForTesting
    interface RegistrarNameRetriever<T> {
        List<String> retrieve(T t);
    }

    @VisibleForTesting
    ComponentDiscovery(T t, RegistrarNameRetriever<T> registrarNameRetriever) {
        this.f15804a = t;
        this.f15805b = registrarNameRetriever;
    }

    public static ComponentDiscovery b(Context context, Class cls) {
        return new ComponentDiscovery(context, new MetadataRegistrarNameRetriever(cls));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ComponentRegistrar c(String str) {
        try {
            Class<?> cls = Class.forName(str);
            if (ComponentRegistrar.class.isAssignableFrom(cls)) {
                return (ComponentRegistrar) cls.getDeclaredConstructor(null).newInstance(null);
            }
            throw new InvalidRegistrarException(String.format("Class %s is not an instance of %s", str, "com.google.firebase.components.ComponentRegistrar"));
        } catch (ClassNotFoundException unused) {
            Log.w("ComponentDiscovery", String.format("Class %s is not an found.", str));
            return null;
        } catch (IllegalAccessException e2) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s.", str), e2);
        } catch (InstantiationException e3) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s.", str), e3);
        } catch (NoSuchMethodException e4) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s", str), e4);
        } catch (InvocationTargetException e5) {
            throw new InvalidRegistrarException(String.format("Could not instantiate %s", str), e5);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public List a() {
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = this.f15805b.retrieve(this.f15804a).iterator();
        while (it.hasNext()) {
            arrayList.add(ComponentDiscovery$$Lambda$1.a(it.next()));
        }
        return arrayList;
    }
}
