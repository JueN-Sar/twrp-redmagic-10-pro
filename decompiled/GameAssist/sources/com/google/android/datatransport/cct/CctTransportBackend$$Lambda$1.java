package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.Function;

/* loaded from: classes.dex */
final /* synthetic */ class CctTransportBackend$$Lambda$1 implements Function {

    /* renamed from: a, reason: collision with root package name */
    private final CctTransportBackend f10071a;

    private CctTransportBackend$$Lambda$1(CctTransportBackend cctTransportBackend) {
        this.f10071a = cctTransportBackend;
    }

    public static Function a(CctTransportBackend cctTransportBackend) {
        return new CctTransportBackend$$Lambda$1(cctTransportBackend);
    }

    @Override // com.google.android.datatransport.runtime.retries.Function
    public Object apply(Object obj) {
        CctTransportBackend.HttpResponse d2;
        d2 = this.f10071a.d((CctTransportBackend.HttpRequest) obj);
        return d2;
    }
}
