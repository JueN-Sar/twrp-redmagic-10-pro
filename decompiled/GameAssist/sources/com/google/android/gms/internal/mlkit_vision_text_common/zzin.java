package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzin implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzin f13305a = new zzin();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("latencyMs");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("logSceneBrightness");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        a3.b(zzctVar2.b()).a();
    }

    private zzin() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
