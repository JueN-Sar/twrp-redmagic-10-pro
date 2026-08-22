package com.google.android.gms.internal.mlkit_vision_text_bundled_common;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class zbvg extends LinkedHashMap {
    private static final zbvg zba;
    private boolean zbb;

    static {
        zbvg zbvgVar = new zbvg();
        zba = zbvgVar;
        zbvgVar.zbb = false;
    }

    private zbvg() {
        this.zbb = true;
    }

    public static zbvg a() {
        return zba;
    }

    private static int g(Object obj) {
        if (!(obj instanceof byte[])) {
            if (obj instanceof zbuh) {
                throw new UnsupportedOperationException();
            }
            return obj.hashCode();
        }
        byte[] bArr = (byte[]) obj;
        byte[] bArr2 = zbuo.f12985b;
        int length = bArr.length;
        int b2 = zbuo.b(length, bArr, 0, length);
        if (b2 == 0) {
            return 1;
        }
        return b2;
    }

    private final void h() {
        if (!this.zbb) {
            throw new UnsupportedOperationException();
        }
    }

    public final zbvg b() {
        return isEmpty() ? new zbvg() : new zbvg(this);
    }

    public final void c() {
        this.zbb = false;
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void clear() {
        h();
        super.clear();
    }

    public final void e(zbvg zbvgVar) {
        h();
        if (zbvgVar.isEmpty()) {
            return;
        }
        putAll(zbvgVar);
    }

    @Override // java.util.LinkedHashMap, java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return isEmpty() ? Collections.emptySet() : super.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this == map) {
            return true;
        }
        if (size() != map.size()) {
            return false;
        }
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!map.containsKey(entry.getKey())) {
                return false;
            }
            Object value = entry.getValue();
            Object obj2 = map.get(entry.getKey());
            if (!(((value instanceof byte[]) && (obj2 instanceof byte[])) ? Arrays.equals((byte[]) value, (byte[]) obj2) : value.equals(obj2))) {
                return false;
            }
        }
        return true;
    }

    public final boolean f() {
        return this.zbb;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        Iterator it = entrySet().iterator();
        int i2 = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            i2 += g(entry.getValue()) ^ g(entry.getKey());
        }
        return i2;
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        h();
        byte[] bArr = zbuo.f12985b;
        obj.getClass();
        obj2.getClass();
        return super.put(obj, obj2);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        h();
        for (Object obj : map.keySet()) {
            byte[] bArr = zbuo.f12985b;
            obj.getClass();
            map.get(obj).getClass();
        }
        super.putAll(map);
    }

    @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        h();
        return super.remove(obj);
    }

    private zbvg(Map map) {
        super(map);
        this.zbb = true;
    }
}
