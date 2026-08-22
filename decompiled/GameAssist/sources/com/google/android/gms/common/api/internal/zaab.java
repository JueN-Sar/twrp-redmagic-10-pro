package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Map;

/* loaded from: classes.dex */
final class zaab implements PendingResult.StatusListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ BasePendingResult f10658a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ zaad f10659b;

    zaab(zaad zaadVar, BasePendingResult basePendingResult) {
        this.f10659b = zaadVar;
        this.f10658a = basePendingResult;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void a(Status status) {
        Map map;
        map = this.f10659b.f10662a;
        map.remove(this.f10658a);
    }
}
