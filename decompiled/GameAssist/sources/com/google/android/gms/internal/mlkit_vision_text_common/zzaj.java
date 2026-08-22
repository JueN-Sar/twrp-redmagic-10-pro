package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.List;
import java.util.ListIterator;

/* loaded from: classes.dex */
final class zzaj extends zzah implements ListIterator {

    /* renamed from: j, reason: collision with root package name */
    final /* synthetic */ zzak f13107j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaj(zzak zzakVar) {
        super(zzakVar);
        this.f13107j = zzakVar;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        int i2;
        boolean isEmpty = this.f13107j.isEmpty();
        b();
        ((ListIterator) this.f13099c).add(obj);
        zzal zzalVar = this.f13107j.f13108l;
        i2 = zzalVar.zzb;
        zzalVar.zzb = i2 + 1;
        if (isEmpty) {
            this.f13107j.b();
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        b();
        return ((ListIterator) this.f13099c).hasPrevious();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        b();
        return ((ListIterator) this.f13099c).nextIndex();
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        b();
        return ((ListIterator) this.f13099c).previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        b();
        return ((ListIterator) this.f13099c).previousIndex();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        b();
        ((ListIterator) this.f13099c).set(obj);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaj(zzak zzakVar, int i2) {
        super(zzakVar, ((List) zzakVar.f13103h).listIterator(i2));
        this.f13107j = zzakVar;
    }
}
