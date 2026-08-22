package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;

/* loaded from: classes.dex */
final class zzab implements Continuation {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ Collection f13675a;

    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object a(Task task) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f13675a);
        return Tasks.c(arrayList);
    }
}
