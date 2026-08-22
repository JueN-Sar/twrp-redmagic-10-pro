package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgb implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgb f12405a = new zzgb();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12406b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12407c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12408d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12409e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12410f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12406b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("errorCode");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12407c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("totalInitializationMs");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12408d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("loggingInitializationMs");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12409e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("otherErrors");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12410f = a6.b(zzaeVar5.b()).a();
    }

    private zzgb() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
