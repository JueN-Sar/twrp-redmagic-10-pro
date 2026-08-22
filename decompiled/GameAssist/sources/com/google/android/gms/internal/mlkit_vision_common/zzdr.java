package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzdr implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzdr f12114a = new zzdr();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12115b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12116c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12117d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12118e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12119f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("remoteModelOptions");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12115b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("localModelOptions");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12116c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCodes");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12117d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("modelInitializationMs");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12118e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("isNnApiEnabled");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12119f = a6.b(zzaeVar5.b()).a();
    }

    private zzdr() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
