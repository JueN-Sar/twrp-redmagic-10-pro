package com.google.android.gms.internal.mlkit_vision_text_common;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* loaded from: classes.dex */
public abstract class zzbm implements Map, Serializable {

    @CheckForNull
    private transient zzbn zza;

    @CheckForNull
    private transient zzbn zzb;

    @CheckForNull
    private transient zzbf zzc;

    zzbm() {
    }

    public static zzbm c(Object obj, Object obj2) {
        zzaq.b("optional-module-barcode", "com.google.android.gms.vision.barcode");
        return zzcj.g(1, new Object[]{"optional-module-barcode", "com.google.android.gms.vision.barcode"}, null);
    }

    abstract zzbf a();

    @Override // java.util.Map
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final zzbf values() {
        zzbf zzbfVar = this.zzc;
        if (zzbfVar != null) {
            return zzbfVar;
        }
        zzbf a2 = a();
        this.zzc = a2;
        return a2;
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    abstract zzbn d();

    abstract zzbn e();

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public final zzbn entrySet() {
        zzbn zzbnVar = this.zza;
        if (zzbnVar != null) {
            return zzbnVar;
        }
        zzbn d2 = d();
        this.zza = d2;
        return d2;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return zzcl.a(entrySet());
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return false;
    }

    @Override // java.util.Map
    public final /* bridge */ /* synthetic */ Set keySet() {
        zzbn zzbnVar = this.zzb;
        if (zzbnVar != null) {
            return zzbnVar;
        }
        zzbn e2 = e();
        this.zzb = e2;
        return e2;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        zzaq.a(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }
}
