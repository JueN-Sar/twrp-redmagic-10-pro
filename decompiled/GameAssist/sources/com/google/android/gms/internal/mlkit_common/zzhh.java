package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzhh implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzhh f11614a = new zzhh();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("useRecognition");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
    }

    private zzhh() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
