package androidx.arch.core.internal;

import androidx.annotation.RestrictTo;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.HashMap;
import java.util.Map;

@RestrictTo
/* loaded from: classes.dex */
public class FastSafeIterableMap<K, V> extends SafeIterableMap<K, V> {

    /* renamed from: k, reason: collision with root package name */
    private final HashMap f1109k = new HashMap();

    public boolean contains(Object obj) {
        return this.f1109k.containsKey(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    protected SafeIterableMap.Entry d(Object obj) {
        return (SafeIterableMap.Entry) this.f1109k.get(obj);
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public Object i(Object obj, Object obj2) {
        SafeIterableMap.Entry d2 = d(obj);
        if (d2 != null) {
            return d2.f1115h;
        }
        this.f1109k.put(obj, h(obj, obj2));
        return null;
    }

    @Override // androidx.arch.core.internal.SafeIterableMap
    public Object j(Object obj) {
        Object j2 = super.j(obj);
        this.f1109k.remove(obj);
        return j2;
    }

    public Map.Entry k(Object obj) {
        if (contains(obj)) {
            return ((SafeIterableMap.Entry) this.f1109k.get(obj)).f1117j;
        }
        return null;
    }
}
