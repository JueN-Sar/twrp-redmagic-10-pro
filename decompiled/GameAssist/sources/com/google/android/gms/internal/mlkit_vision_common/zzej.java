package com.google.android.gms.internal.mlkit_vision_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.ObjectEncoderContext;

/* loaded from: classes.dex */
final class zzej implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzej f12207a = new zzej();

    /* renamed from: b, reason: collision with root package name */
    private static final FieldDescriptor f12208b;

    /* renamed from: c, reason: collision with root package name */
    private static final FieldDescriptor f12209c;

    /* renamed from: d, reason: collision with root package name */
    private static final FieldDescriptor f12210d;

    /* renamed from: e, reason: collision with root package name */
    private static final FieldDescriptor f12211e;

    /* renamed from: f, reason: collision with root package name */
    private static final FieldDescriptor f12212f;

    /* renamed from: g, reason: collision with root package name */
    private static final FieldDescriptor f12213g;

    /* renamed from: h, reason: collision with root package name */
    private static final FieldDescriptor f12214h;

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("durationMs");
        zzae zzaeVar = new zzae();
        zzaeVar.a(1);
        f12208b = a2.b(zzaeVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("imageSource");
        zzae zzaeVar2 = new zzae();
        zzaeVar2.a(2);
        f12209c = a3.b(zzaeVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("imageFormat");
        zzae zzaeVar3 = new zzae();
        zzaeVar3.a(3);
        f12210d = a4.b(zzaeVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("imageByteSize");
        zzae zzaeVar4 = new zzae();
        zzaeVar4.a(4);
        f12211e = a5.b(zzaeVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("imageWidth");
        zzae zzaeVar5 = new zzae();
        zzaeVar5.a(5);
        f12212f = a6.b(zzaeVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("imageHeight");
        zzae zzaeVar6 = new zzae();
        zzaeVar6.a(6);
        f12213g = a7.b(zzaeVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("rotationDegrees");
        zzae zzaeVar7 = new zzae();
        zzaeVar7.a(7);
        f12214h = a8.b(zzaeVar7.b()).a();
    }

    private zzej() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        zziq zziqVar = (zziq) obj;
        ObjectEncoderContext objectEncoderContext = (ObjectEncoderContext) obj2;
        objectEncoderContext.c(f12208b, zziqVar.g());
        objectEncoderContext.c(f12209c, zziqVar.b());
        objectEncoderContext.c(f12210d, zziqVar.a());
        objectEncoderContext.c(f12211e, zziqVar.c());
        objectEncoderContext.c(f12212f, zziqVar.e());
        objectEncoderContext.c(f12213g, zziqVar.d());
        objectEncoderContext.c(f12214h, zziqVar.f());
    }
}
