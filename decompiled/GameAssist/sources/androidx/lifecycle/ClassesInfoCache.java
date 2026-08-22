package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
/* loaded from: classes.dex */
final class ClassesInfoCache {

    /* renamed from: c, reason: collision with root package name */
    static ClassesInfoCache f4269c = new ClassesInfoCache();

    /* renamed from: a, reason: collision with root package name */
    private final Map f4270a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private final Map f4271b = new HashMap();

    @Deprecated
    static class CallbackInfo {

        /* renamed from: a, reason: collision with root package name */
        final Map f4272a = new HashMap();

        /* renamed from: b, reason: collision with root package name */
        final Map f4273b;

        CallbackInfo(Map map) {
            this.f4273b = map;
            for (Map.Entry entry : map.entrySet()) {
                Lifecycle.Event event = (Lifecycle.Event) entry.getValue();
                List list = (List) this.f4272a.get(event);
                if (list == null) {
                    list = new ArrayList();
                    this.f4272a.put(event, list);
                }
                list.add((MethodReference) entry.getKey());
            }
        }

        private static void b(List list, LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    ((MethodReference) list.get(size)).a(lifecycleOwner, event, obj);
                }
            }
        }

        void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            b((List) this.f4272a.get(event), lifecycleOwner, event, obj);
            b((List) this.f4272a.get(Lifecycle.Event.ON_ANY), lifecycleOwner, event, obj);
        }
    }

    @Deprecated
    static final class MethodReference {

        /* renamed from: a, reason: collision with root package name */
        final int f4274a;

        /* renamed from: b, reason: collision with root package name */
        final Method f4275b;

        MethodReference(int i2, Method method) {
            this.f4274a = i2;
            this.f4275b = method;
            method.setAccessible(true);
        }

        void a(LifecycleOwner lifecycleOwner, Lifecycle.Event event, Object obj) {
            try {
                int i2 = this.f4274a;
                if (i2 == 0) {
                    this.f4275b.invoke(obj, null);
                } else if (i2 == 1) {
                    this.f4275b.invoke(obj, lifecycleOwner);
                } else {
                    if (i2 != 2) {
                        return;
                    }
                    this.f4275b.invoke(obj, lifecycleOwner, event);
                }
            } catch (IllegalAccessException e2) {
                throw new RuntimeException(e2);
            } catch (InvocationTargetException e3) {
                throw new RuntimeException("Failed to call observer method", e3.getCause());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MethodReference)) {
                return false;
            }
            MethodReference methodReference = (MethodReference) obj;
            return this.f4274a == methodReference.f4274a && this.f4275b.getName().equals(methodReference.f4275b.getName());
        }

        public int hashCode() {
            return (this.f4274a * 31) + this.f4275b.getName().hashCode();
        }
    }

    ClassesInfoCache() {
    }

    private CallbackInfo a(Class cls, Method[] methodArr) {
        int i2;
        CallbackInfo c2;
        Class superclass = cls.getSuperclass();
        HashMap hashMap = new HashMap();
        if (superclass != null && (c2 = c(superclass)) != null) {
            hashMap.putAll(c2.f4273b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            for (Map.Entry entry : c(cls2).f4273b.entrySet()) {
                e(hashMap, (MethodReference) entry.getKey(), (Lifecycle.Event) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            methodArr = b(cls);
        }
        boolean z = false;
        for (Method method : methodArr) {
            OnLifecycleEvent onLifecycleEvent = (OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class);
            if (onLifecycleEvent != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i2 = 0;
                } else {
                    if (!LifecycleOwner.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i2 = 1;
                }
                Lifecycle.Event value = onLifecycleEvent.value();
                if (parameterTypes.length > 1) {
                    if (!Lifecycle.Event.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (value != Lifecycle.Event.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i2 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                e(hashMap, new MethodReference(i2, method), value, cls);
                z = true;
            }
        }
        CallbackInfo callbackInfo = new CallbackInfo(hashMap);
        this.f4270a.put(cls, callbackInfo);
        this.f4271b.put(cls, Boolean.valueOf(z));
        return callbackInfo;
    }

    private Method[] b(Class cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e2) {
            throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e2);
        }
    }

    private void e(Map map, MethodReference methodReference, Lifecycle.Event event, Class cls) {
        Lifecycle.Event event2 = (Lifecycle.Event) map.get(methodReference);
        if (event2 == null || event == event2) {
            if (event2 == null) {
                map.put(methodReference, event);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + methodReference.f4275b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + event2 + ", new value " + event);
    }

    CallbackInfo c(Class cls) {
        CallbackInfo callbackInfo = (CallbackInfo) this.f4270a.get(cls);
        return callbackInfo != null ? callbackInfo : a(cls, null);
    }

    boolean d(Class cls) {
        Boolean bool = (Boolean) this.f4271b.get(cls);
        if (bool != null) {
            return bool.booleanValue();
        }
        Method[] b2 = b(cls);
        for (Method method : b2) {
            if (((OnLifecycleEvent) method.getAnnotation(OnLifecycleEvent.class)) != null) {
                a(cls, b2);
                return true;
            }
        }
        this.f4271b.put(cls, Boolean.FALSE);
        return false;
    }
}
