package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;

/* loaded from: classes.dex */
abstract class zzca extends AbstractMap {

    /* renamed from: c, reason: collision with root package name */
    private transient Set f13130c;

    /* renamed from: h, reason: collision with root package name */
    private transient Set f13131h;

    /* renamed from: i, reason: collision with root package name */
    private transient Collection f13132i;

    zzca() {
    }

    abstract Set a();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.f13130c;
        if (set != null) {
            return set;
        }
        Set a2 = a();
        this.f13130c = a2;
        return a2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set keySet() {
        Set set = this.f13131h;
        if (set != null) {
            return set;
        }
        zzby zzbyVar = new zzby(this);
        this.f13131h = zzbyVar;
        return zzbyVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.f13132i;
        if (collection != null) {
            return collection;
        }
        zzbz zzbzVar = new zzbz(this);
        this.f13132i = zzbzVar;
        return zzbzVar;
    }
}
