package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzgr implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgr f12490a = new zzgr();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12491b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12492c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12493d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12494e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12495f;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12491b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("handledErrors");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12492c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("partiallyHandledErrors");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12493d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("unhandledErrors");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12494e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("httpResponseCode");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12495f = a6.b(zzaeVar5.b()).a();
    }

    private zzgr() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
