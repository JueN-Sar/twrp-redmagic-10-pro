package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfo implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfo f11523a = new zzfo();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("useCases");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
    }

    private zzfo() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
