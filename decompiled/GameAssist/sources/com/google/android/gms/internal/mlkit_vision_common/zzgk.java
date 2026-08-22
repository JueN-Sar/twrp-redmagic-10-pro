package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgk implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgk f12446a = new zzgk();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12447b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12448c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12449d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("languageOption");
        zzae zzaeVar = new zzae();
        zzaeVar.a(3);
        f12447b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("isUsingLegacyApi");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(4);
        f12448c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("sdkVersion");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(5);
        f12449d = a4.b(zzaeVar3.b()).a();
    }

    private zzgk() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
