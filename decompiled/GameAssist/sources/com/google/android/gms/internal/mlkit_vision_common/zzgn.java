package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgn implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgn f12462a = new zzgn();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12463b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12464c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12465d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("name");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12463b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("stages");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12464c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("runMiniBenchmark");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12465d = a4.b(zzaeVar3.b()).a();
    }

    private zzgn() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
