package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zah extends zad {

    /* renamed from: c, reason: collision with root package name */
    public final ListenerHolder.ListenerKey f10841c;

    public zah(ListenerHolder.ListenerKey listenerKey, TaskCompletionSource taskCompletionSource) {
        super(4, taskCompletionSource);
        this.f10841c = listenerKey;
    }

    @Override // com.google.android.gms.common.api.internal.zad, com.google.android.gms.common.api.internal.zai
    public final /* bridge */ /* synthetic */ void d(zaad zaadVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean f(zabq zabqVar) {
        zaci zaciVar = (zaci) zabqVar.w().get(this.f10841c);
        return zaciVar != null && zaciVar.f10807a.f();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] g(zabq zabqVar) {
        zaci zaciVar = (zaci) zabqVar.w().get(this.f10841c);
        if (zaciVar == null) {
            return null;
        }
        return zaciVar.f10807a.c();
    }

    @Override // com.google.android.gms.common.api.internal.zad
    public final void h(zabq zabqVar) {
        zaci zaciVar = (zaci) zabqVar.w().remove(this.f10841c);
        if (zaciVar == null) {
            this.f10823b.e(Boolean.FALSE);
            return;
        }
        zaciVar.f10808b.b(zabqVar.u(), this.f10823b);
        zaciVar.f10807a.a();
    }
}
