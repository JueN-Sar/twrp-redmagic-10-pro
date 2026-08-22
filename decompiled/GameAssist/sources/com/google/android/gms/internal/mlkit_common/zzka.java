package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzka implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzka f11689a = new zzka();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("errorCode");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
    }

    private zzka() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
