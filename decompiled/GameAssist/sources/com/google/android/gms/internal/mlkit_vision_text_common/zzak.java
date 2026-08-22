package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
class zzak extends zzai implements List {

    /* renamed from: l, reason: collision with root package name */
    final /* synthetic */ zzal f13108l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzak(zzal zzalVar, Object obj, List list, zzai zzaiVar) {
        super(zzalVar, obj, list, zzaiVar);
        this.f13108l = zzalVar;
    }

    @Override // java.util.List
    public final void add(int i2, Object obj) {
        int i3;
        d();
        boolean isEmpty = this.f13103h.isEmpty();
        ((List) this.f13103h).add(i2, obj);
        zzal zzalVar = this.f13108l;
        i3 = zzalVar.zzb;
        zzalVar.zzb = i3 + 1;
        if (isEmpty) {
            b();
        }
    }

    @Override // java.util.List
    public final boolean addAll(int i2, Collection collection) {
        int i3;
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.f13103h).addAll(i2, collection);
        if (addAll) {
            int size2 = this.f13103h.size();
            zzal zzalVar = this.f13108l;
            i3 = zzalVar.zzb;
            zzalVar.zzb = i3 + (size2 - size);
            if (size == 0) {
                b();
                return true;
            }
        }
        return addAll;
    }

    @Override // java.util.List
    public final Object get(int i2) {
        d();
        return ((List) this.f13103h).get(i2);
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        d();
        return ((List) this.f13103h).indexOf(obj);
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        d();
        return ((List) this.f13103h).lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        d();
        return new zzaj(this);
    }

    @Override // java.util.List
    public final Object remove(int i2) {
        int i3;
        d();
        Object remove = ((List) this.f13103h).remove(i2);
        zzal zzalVar = this.f13108l;
        i3 = zzalVar.zzb;
        zzalVar.zzb = i3 - 1;
        f();
        return remove;
    }

    @Override // java.util.List
    public final Object set(int i2, Object obj) {
        d();
        return ((List) this.f13103h).set(i2, obj);
    }

    @Override // java.util.List
    public final List subList(int i2, int i3) {
        d();
        List subList = ((List) this.f13103h).subList(i2, i3);
        zzai zzaiVar = this.f13104i;
        if (zzaiVar == null) {
            zzaiVar = this;
        }
        return this.f13108l.k(this.f13102c, subList, zzaiVar);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i2) {
        d();
        return new zzaj(this, i2);
    }
}
