package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public abstract class zbtx {
    static zbtp b(Class cls) {
        String format;
        ClassLoader classLoader = zbtx.class.getClassLoader();
        if (cls.equals(zbtp.class)) {
            format = "com.google.protobuf.BlazeGeneratedExtensionRegistryLiteLoader";
        } else {
            if (!cls.getPackage().equals(zbtx.class.getPackage())) {
                throw new IllegalArgumentException(cls.getName());
            }
            format = String.format("%s.BlazeGenerated%sLoader", cls.getPackage().getName(), cls.getSimpleName());
        }
        try {
            try {
                try {
                    return (zbtp) cls.cast(((zbtx) Class.forName(format, true, classLoader).getConstructor(null).newInstance(null)).a());
                } catch (IllegalAccessException e2) {
                    throw new IllegalStateException(e2);
                } catch (InvocationTargetException e3) {
                    throw new IllegalStateException(e3);
                }
            } catch (InstantiationException e4) {
                throw new IllegalStateException(e4);
            } catch (NoSuchMethodException e5) {
                throw new IllegalStateException(e5);
            }
        } catch (ClassNotFoundException unused) {
            Iterator it = ServiceLoader.load(zbtx.class, classLoader).iterator();
            ArrayList arrayList = new ArrayList();
            while (it.hasNext()) {
                try {
                    arrayList.add((zbtp) cls.cast(((zbtx) it.next()).a()));
                } catch (ServiceConfigurationError e6) {
                    Logger.getLogger(zbtk.class.getName()).logp(Level.SEVERE, "com.google.protobuf.GeneratedExtensionRegistryLoader", "load", "Unable to load ".concat(cls.getSimpleName()), (Throwable) e6);
                }
            }
            if (arrayList.size() == 1) {
                return (zbtp) arrayList.get(0);
            }
            if (arrayList.size() == 0) {
                return null;
            }
            try {
                return (zbtp) cls.getMethod("combine", Collection.class).invoke(null, arrayList);
            } catch (IllegalAccessException e7) {
                throw new IllegalStateException(e7);
            } catch (NoSuchMethodException e8) {
                throw new IllegalStateException(e8);
            } catch (InvocationTargetException e9) {
                throw new IllegalStateException(e9);
            }
        }
    }

    protected abstract zbtp a();
}
