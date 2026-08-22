package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzjg implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzjg f13342a = new zzjg();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("useRecognition");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        a2.b(zzctVar.b()).a();
    }

    private zzjg() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
