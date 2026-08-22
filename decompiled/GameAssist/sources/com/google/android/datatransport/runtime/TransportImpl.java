package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

/* loaded from: classes.dex */
final class TransportImpl<T> implements Transport<T> {

    /* renamed from: a, reason: collision with root package name */
    private final TransportContext f10228a;

    /* renamed from: b, reason: collision with root package name */
    private final String f10229b;

    /* renamed from: c, reason: collision with root package name */
    private final Encoding f10230c;

    /* renamed from: d, reason: collision with root package name */
    private final Transformer f10231d;

    /* renamed from: e, reason: collision with root package name */
    private final TransportInternal f10232e;

    TransportImpl(TransportContext transportContext, String str, Encoding encoding, Transformer transformer, TransportInternal transportInternal) {
        this.f10228a = transportContext;
        this.f10229b = str;
        this.f10230c = encoding;
        this.f10231d = transformer;
        this.f10232e = transportInternal;
    }

    static /* synthetic */ void b(Exception exc) {
    }

    @Override // com.google.android.datatransport.Transport
    public void a(Event event) {
        c(event, TransportImpl$$Lambda$1.b());
    }

    public void c(Event event, TransportScheduleCallback transportScheduleCallback) {
        this.f10232e.a(SendRequest.a().e(this.f10228a).c(event).f(this.f10229b).d(this.f10231d).b(this.f10230c).a(), transportScheduleCallback);
    }
}
