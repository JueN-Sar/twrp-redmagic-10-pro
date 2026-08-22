package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzgj implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzgj f13221a = new zzgj();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f13222b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f13223c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f13224d;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("logEventKey");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        f13222b = a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("eventCount");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        f13223c = a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("inferenceDurationStats");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        f13224d = a4.b(zzctVar3.b()).a();
    }

    private zzgj() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zzeu zzeuVar = (zzeu) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f13222b, zzeuVar.a());
        objectEncoderContext.c(f13223c, zzeuVar.c());
        objectEncoderContext.c(f13224d, zzeuVar.b());
    }
}
