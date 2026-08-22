package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzir implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzir f11654a = new zzir();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("optionsType");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
    }

    private zzir() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
