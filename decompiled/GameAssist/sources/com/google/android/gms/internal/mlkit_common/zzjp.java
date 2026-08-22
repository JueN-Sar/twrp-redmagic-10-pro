package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzjp implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzjp f11678a = new zzjp();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("languageOption");
        zzay zzayVar = new zzay();
        zzayVar.a(3);
        a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isUsingLegacyApi");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(4);
        a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("sdkVersion");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(5);
        a4.b(zzayVar3.b()).a();
    }

    private zzjp() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
