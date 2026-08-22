package androidx.lifecycle;

import androidx.annotation.RestrictTo;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

@Metadata
@RestrictTo
/* loaded from: classes.dex */
public final class Lifecycling {

    /* renamed from: a, reason: collision with root package name */
    public static final Lifecycling f4312a = new Lifecycling();

    /* renamed from: b, reason: collision with root package name */
    private static final Map f4313b = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    private static final Map f4314c = new HashMap();

    private Lifecycling() {
    }

    private final GeneratedAdapter a(Constructor constructor, Object obj) {
        try {
            Object newInstance = constructor.newInstance(obj);
            Intrinsics.d(newInstance, "{\n            constructo…tance(`object`)\n        }");
            return (GeneratedAdapter) newInstance;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    private final Constructor b(Class cls) {
        try {
            Package r3 = cls.getPackage();
            String name = cls.getCanonicalName();
            String fullPackage = r3 != null ? r3.getName() : "";
            Intrinsics.d(fullPackage, "fullPackage");
            if (fullPackage.length() != 0) {
                Intrinsics.d(name, "name");
                name = name.substring(fullPackage.length() + 1);
                Intrinsics.d(name, "this as java.lang.String).substring(startIndex)");
            }
            Intrinsics.d(name, "if (fullPackage.isEmpty(…g(fullPackage.length + 1)");
            String c2 = c(name);
            if (fullPackage.length() != 0) {
                c2 = fullPackage + '.' + c2;
            }
            Class<?> cls2 = Class.forName(c2);
            Intrinsics.c(cls2, "null cannot be cast to non-null type java.lang.Class<out androidx.lifecycle.GeneratedAdapter>");
            Constructor<?> declaredConstructor = cls2.getDeclaredConstructor(cls);
            if (declaredConstructor.isAccessible()) {
                return declaredConstructor;
            }
            declaredConstructor.setAccessible(true);
            return declaredConstructor;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static final String c(String className) {
        String n2;
        Intrinsics.e(className, "className");
        StringBuilder sb = new StringBuilder();
        n2 = StringsKt__StringsJVMKt.n(className, ".", "_", false, 4, null);
        sb.append(n2);
        sb.append("_LifecycleAdapter");
        return sb.toString();
    }

    private final int d(Class cls) {
        Map map = f4313b;
        Integer num = (Integer) map.get(cls);
        if (num != null) {
            return num.intValue();
        }
        int g2 = g(cls);
        map.put(cls, Integer.valueOf(g2));
        return g2;
    }

    private final boolean e(Class cls) {
        return cls != null && LifecycleObserver.class.isAssignableFrom(cls);
    }

    public static final LifecycleEventObserver f(Object object) {
        Intrinsics.e(object, "object");
        boolean z = object instanceof LifecycleEventObserver;
        boolean z2 = object instanceof DefaultLifecycleObserver;
        if (z && z2) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) object, (LifecycleEventObserver) object);
        }
        if (z2) {
            return new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) object, null);
        }
        if (z) {
            return (LifecycleEventObserver) object;
        }
        Class<?> cls = object.getClass();
        Lifecycling lifecycling = f4312a;
        if (lifecycling.d(cls) != 2) {
            return new ReflectiveGenericLifecycleObserver(object);
        }
        Object obj = f4314c.get(cls);
        Intrinsics.b(obj);
        List list = (List) obj;
        if (list.size() == 1) {
            return new SingleGeneratedAdapterObserver(lifecycling.a((Constructor) list.get(0), object));
        }
        int size = list.size();
        GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[size];
        for (int i2 = 0; i2 < size; i2++) {
            generatedAdapterArr[i2] = f4312a.a((Constructor) list.get(i2), object);
        }
        return new CompositeGeneratedAdaptersObserver(generatedAdapterArr);
    }

    private final int g(Class cls) {
        ArrayList arrayList;
        if (cls.getCanonicalName() == null) {
            return 1;
        }
        Constructor b2 = b(cls);
        if (b2 != null) {
            f4314c.put(cls, CollectionsKt__CollectionsJVMKt.e(b2));
            return 2;
        }
        if (ClassesInfoCache.f4269c.d(cls)) {
            return 1;
        }
        Class superclass = cls.getSuperclass();
        if (e(superclass)) {
            Intrinsics.d(superclass, "superclass");
            if (d(superclass) == 1) {
                return 1;
            }
            Object obj = f4314c.get(superclass);
            Intrinsics.b(obj);
            arrayList = new ArrayList((Collection) obj);
        } else {
            arrayList = null;
        }
        Class<?>[] interfaces = cls.getInterfaces();
        Intrinsics.d(interfaces, "klass.interfaces");
        for (Class<?> intrface : interfaces) {
            if (e(intrface)) {
                Intrinsics.d(intrface, "intrface");
                if (d(intrface) == 1) {
                    return 1;
                }
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                Object obj2 = f4314c.get(intrface);
                Intrinsics.b(obj2);
                arrayList.addAll((Collection) obj2);
            }
        }
        if (arrayList == null) {
            return 1;
        }
        f4314c.put(cls, arrayList);
        return 2;
    }
}
