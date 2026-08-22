package com.google.android.gms.internal.mlkit_vision_text_common;

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
public final class zzuk implements zzts {

    /* renamed from: a, reason: collision with root package name */
    private Provider f13587a;

    /* renamed from: b, reason: collision with root package name */
    private final Provider f13588b;

    /* renamed from: c, reason: collision with root package name */
    private final zztu f13589c;

    public zzuk(Context context, zztu zztuVar) {
        this.f13589c = zztuVar;
        CCTDestination cCTDestination = CCTDestination.f10060g;
        TransportRuntime.f(context);
        final TransportFactory g2 = TransportRuntime.c().g(cCTDestination);
        if (cCTDestination.a().contains(Encoding.b("json"))) {
            this.f13587a = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzuh
                @Override // com.google.firebase.inject.Provider
                public final Object get() {
                    return TransportFactory.this.a("FIREBASE_ML_SDK", byte[].class, Encoding.b("json"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzuj
                        @Override // com.google.android.datatransport.Transformer
                        public final Object apply(Object obj) {
                            return (byte[]) obj;
                        }
                    });
                }
            });
        }
        this.f13588b = new Lazy(new Provider() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzui
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                return TransportFactory.this.a("FIREBASE_ML_SDK", byte[].class, Encoding.b("proto"), new Transformer() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzug
                    @Override // com.google.android.datatransport.Transformer
                    public final Object apply(Object obj) {
                        return (byte[]) obj;
                    }
                });
            }
        });
    }

    @VisibleForTesting
    static Event zzb(zztu zztuVar, zztr zztrVar) {
        int a2 = zztuVar.a();
        return zztrVar.zza() != 0 ? Event.d(zztrVar.a(a2, false)) : Event.e(zztrVar.a(a2, false));
    }

    @Override // com.google.android.gms.internal.mlkit_vision_text_common.zzts
    public final void a(zztr zztrVar) {
        if (this.f13589c.a() != 0) {
            ((Transport) this.f13588b.get()).a(zzb(this.f13589c, zztrVar));
            return;
        }
        Provider provider = this.f13587a;
        if (provider != null) {
            ((Transport) provider.get()).a(zzb(this.f13589c, zztrVar));
        }
    }
}
