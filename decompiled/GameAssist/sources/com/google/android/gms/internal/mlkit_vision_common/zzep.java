package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzep implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzep f12248a = new zzep();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12249b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12250c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12251d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12252e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12253f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12254g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12255h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("options");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12249b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("roughDownloadDurationMs");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12250c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("errorCode");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12251d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("exactDownloadDurationMs");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12252e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("downloadStatus");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12253f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("downloadFailureStatus");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12254g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("mddDownloadErrorCodes");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12255h = a8.b(zzaeVar7.b()).a();
    }

    private zzep() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
