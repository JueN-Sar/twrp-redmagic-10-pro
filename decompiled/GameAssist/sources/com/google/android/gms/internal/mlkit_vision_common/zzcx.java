package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzcx implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzcx f12032a = new zzcx();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12033b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12034c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12035d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12036e;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12033b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("detectorOptions");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12034c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("eventType");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12035d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("errorCode");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12036e = a5.b(zzaeVar4.b()).a();
    }

    private zzcx() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
