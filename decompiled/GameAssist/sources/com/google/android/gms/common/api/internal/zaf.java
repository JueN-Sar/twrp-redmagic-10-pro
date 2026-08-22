package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes.dex */
public final class zaf extends zad {

    /* renamed from: c, reason: collision with root package name */
    public final zaci f10837c;

    public zaf(zaci zaciVar, TaskCompletionSource taskCompletionSource) {
        super(3, taskCompletionSource);
        this.f10837c = zaciVar;
    }

    @Override // com.google.android.gms.common.api.internal.zad, com.google.android.gms.common.api.internal.zai
    public final /* bridge */ /* synthetic */ void d(zaad zaadVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final boolean f(zabq zabqVar) {
        return this.f10837c.f10807a.f();
    }

    @Override // com.google.android.gms.common.api.internal.zac
    public final Feature[] g(zabq zabqVar) {
        return this.f10837c.f10807a.c();
    }

    @Override // com.google.android.gms.common.api.internal.zad
    public final void h(zabq zabqVar) {
        this.f10837c.f10807a.d(zabqVar.u(), this.f10823b);
        ListenerHolder.ListenerKey b2 = this.f10837c.f10807a.b();
        if (b2 != null) {
            zabqVar.w().put(b2, this.f10837c);
        }
    }
}
