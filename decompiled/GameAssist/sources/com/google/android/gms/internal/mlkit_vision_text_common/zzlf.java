package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzlf implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzlf f13404a = new zzlf();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("inferenceCommonLogEvent");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("smartReplies");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("resultStatus");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("suggestionsCount");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("blacklistErrorCode");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        a6.b(zzctVar5.b()).a();
    }

    private zzlf() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
