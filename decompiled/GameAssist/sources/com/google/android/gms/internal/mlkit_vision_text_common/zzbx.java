package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.android.gms.common.api.Api;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/* loaded from: classes.dex */
abstract class zzbx extends zzck {
    zzbx() {
    }

    abstract Map b();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        b().clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object a2 = zzcb.a(b(), key);
        if (zzw.a(a2, entry.getValue())) {
            return a2 != null || zzcb.b(((zzad) b()).f13093j, key);
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return b().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (contains(obj) && (obj instanceof Map.Entry)) {
            return ((zzad) b()).f13094k.d().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzck, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        try {
            return zzcl.b(this, collection);
        } catch (UnsupportedOperationException unused) {
            return zzcl.c(this, collection.iterator());
        }
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzck, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean retainAll(Collection collection) {
        int ceil;
        collection.getClass();
        try {
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            int size = collection.size();
            if (size < 3) {
                zzaq.a(size, "expectedSize");
                ceil = size + 1;
            } else {
                ceil = size < 1073741824 ? (int) Math.ceil(size / 0.75d) : Api.BaseClientBuilder.API_PRIORITY_OTHER;
            }
            HashSet hashSet = new HashSet(ceil);
            for (Object obj : collection) {
                if (this.contains(obj) && (obj instanceof Map.Entry)) {
                    hashSet.add(((Map.Entry) obj).getKey());
                }
            }
            return ((zzad) this.b()).f13094k.d().retainAll(hashSet);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return b().size();
    }
}
