package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportFactory;
import com.google.android.datatransport.cct.CCTDestination;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.inject.Provider;

/* loaded from: classes.dex */
public final class zzsp implements zzrz {

    /* renamed from: a, reason: collision with root package name */
    private Provider f11848a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f11849b;

    /* renamed from: c, reason: collision with root package name */
    private final zzsb f11850c;

    public zzsp(Context context, zzsb zzsbVar) {
        this.f11850c = zzsbVar;
        CCTDestination cCTDestination = CCTDestination.f10060g;
        TransportRuntime.f(context);
        final TransportFactory g2 = TransportRuntime.c().g(cCTDestination);
        if (cCTDestination.a().contains(Encoding.b("json"))) {
            this.f11848a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_common.zzsm
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.a("FIREBASE_ML_SDK", byte[].class, Encoding.b("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_common.zzso
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f11849b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_common.zzsn
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.a("FIREBASE_ML_SDK", byte[].class, Encoding.b("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_common.zzsl
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    static Event zzb(zzsb zzsbVar, zzry zzryVar) {
        return Event.e(zzryVar.a(zzsbVar.a(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_common.zzrz
    public final void a(zzry zzryVar) {
        if (this.f11850c.a() != 0) {
            ((Transport) this.f11849b.get()).a(zzb(this.f11850c, zzryVar));
            return;
        }
        Provider provider = this.f11848a;
        if (provider != null) {
            ((Transport) provider.get()).a(zzb(this.f11850c, zzryVar));
        }
    }
}
