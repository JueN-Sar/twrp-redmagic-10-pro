package com.google.android.gms.internal.mlkit_vision_common;

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
public final class zzmp implements zzmc {

    /* renamed from: a, reason: collision with root package name */
    private Provider f12632a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f12633b;

    /* renamed from: c, reason: collision with root package name */
    private final zzme f12634c;

    public zzmp(Context context, zzme zzmeVar) {
        this.f12634c = zzmeVar;
        CCTDestination cCTDestination = CCTDestination.f10060g;
        TransportRuntime.f(context);
        final TransportFactory g2 = TransportRuntime.c().g(cCTDestination);
        if (cCTDestination.a().contains(Encoding.b("json"))) {
            this.f12632a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmm
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.a("FIREBASE_ML_SDK", byte[].class, Encoding.b("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmo
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f12633b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmn
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.a("FIREBASE_ML_SDK", byte[].class, Encoding.b("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzml
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    static Event zzb(zzme zzmeVar, zzmb zzmbVar) {
        return Event.e(zzmbVar.b(zzmeVar.a(), false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_common.zzmc
    public final void a(zzmb zzmbVar) {
        if (this.f12634c.a() != 0) {
            ((Transport) this.f12633b.get()).a(zzb(this.f12634c, zzmbVar));
            return;
        }
        Provider provider = this.f12632a;
        if (provider != null) {
            ((Transport) provider.get()).a(zzb(this.f12634c, zzmbVar));
        }
    }
}
