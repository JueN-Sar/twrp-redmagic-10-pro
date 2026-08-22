package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzem implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzem f12223a = new zzem();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12224b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12225c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12226d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("identifyLanguageConfidenceThreshold");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12224b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("identifyAllLanguagesConfidenceThreshold");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12225c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("confidenceThreshold");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12226d = a4.b(zzaeVar3.b()).a();
    }

    private zzem() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
