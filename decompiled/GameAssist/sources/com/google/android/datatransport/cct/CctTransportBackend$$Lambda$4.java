package com.google.android.datatransport.cct;

import com.google.android.datatransport.cct.CctTransportBackend;
import com.google.android.datatransport.runtime.retries.RetryStrategy;

/* loaded from: classes.dex */
final /* synthetic */ class CctTransportBackend$$Lambda$4 implements RetryStrategy {

    /* renamed from: a, reason: collision with root package name */
    private static final CctTransportBackend$$Lambda$4 f10072a = new CctTransportBackend$$Lambda$4();

    private CctTransportBackend$$Lambda$4() {
    }

    public static RetryStrategy b() {
        return f10072a;
    }

    @Override // com.google.android.datatransport.runtime.retries.RetryStrategy
    public Object a(Object obj, Object obj2) {
        return CctTransportBackend.j((CctTransportBackend.HttpRequest) obj, (CctTransportBackend.HttpResponse) obj2);
    }
}
