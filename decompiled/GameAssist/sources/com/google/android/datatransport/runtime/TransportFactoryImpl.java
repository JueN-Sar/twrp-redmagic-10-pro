package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import java.util.Set;

/* loaded from: classes.dex */
final class TransportFactoryImpl implements TransportFactory {

    /* renamed from: a, reason: collision with root package name */
    private final Set f10225a;

    /* renamed from: b, reason: collision with root package name */
    private final TransportContext f10226b;

    /* renamed from: c, reason: collision with root package name */
    private final TransportInternal f10227c;

    TransportFactoryImpl(Set set, TransportContext transportContext, TransportInternal transportInternal) {
        this.f10225a = set;
        this.f10226b = transportContext;
        this.f10227c = transportInternal;
    }

    @Override // com.google.android.datatransport.TransportFactory
    public Transport a(String str, Class cls, Encoding encoding, Transformer transformer) {
        if (this.f10225a.contains(encoding)) {
            return new TransportImpl(this.f10226b, str, encoding, transformer, this.f10227c);
        }
        throw new IllegalArgumentException(String.format("%s is not supported byt this factory. Supported encodings are: %s.", encoding, this.f10225a));
    }
}
