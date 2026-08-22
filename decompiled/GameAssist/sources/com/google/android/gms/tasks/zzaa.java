package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes.dex */
final class zzaa implements Continuation {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Collection f13674a;

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object a(Task task) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f13674a.iterator();
        while (it.hasNext()) {
            arrayList.add(((Task) it.next()).i());
        }
        return arrayList;
    }
}
