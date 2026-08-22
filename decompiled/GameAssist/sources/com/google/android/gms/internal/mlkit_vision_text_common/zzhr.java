package com.google.android.gms.internal.mlkit_vision_text_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzhr implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzhr f13263a = new zzhr();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("initialImageUriCount");
        zzct zzctVar = new zzct();
        zzctVar.a(1);
        a2.b(zzctVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("defaultCaptureMode");
        zzct zzctVar2 = new zzct();
        zzctVar2.a(2);
        a3.b(zzctVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("flashModeChangeAllowed");
        zzct zzctVar3 = new zzct();
        zzctVar3.a(3);
        a4.b(zzctVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("galleryImportAllowed");
        zzct zzctVar4 = new zzct();
        zzctVar4.a(4);
        a5.b(zzctVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("multiPageAllowed");
        zzct zzctVar5 = new zzct();
        zzctVar5.a(5);
        a6.b(zzctVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("filterAllowed");
        zzct zzctVar6 = new zzct();
        zzctVar6.a(6);
        a7.b(zzctVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("targetResolutionWidth");
        zzct zzctVar7 = new zzct();
        zzctVar7.a(7);
        a8.b(zzctVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("targetResolutionHeight");
        zzct zzctVar8 = new zzct();
        zzctVar8.a(8);
        a9.b(zzctVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("resultFormats");
        zzct zzctVar9 = new zzct();
        zzctVar9.a(9);
        a10.b(zzctVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("pageEditListenerSet");
        zzct zzctVar10 = new zzct();
        zzctVar10.a(10);
        a11.b(zzctVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("shadowRemovalAllowed");
        zzct zzctVar11 = new zzct();
        zzctVar11.a(11);
        a12.b(zzctVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("stainRemovalAllowed");
        zzct zzctVar12 = new zzct();
        zzctVar12.a(12);
        a13.b(zzctVar12.b()).a();
        FieldDescriptor.Builder a14 = FieldDescriptor.a("enableAllNewFeaturesByDefault");
        zzct zzctVar13 = new zzct();
        zzctVar13.a(13);
        a14.b(zzctVar13.b()).a();
        FieldDescriptor.Builder a15 = FieldDescriptor.a("pageLimitMax");
        zzct zzctVar14 = new zzct();
        zzctVar14.a(14);
        a15.b(zzctVar14.b()).a();
        FieldDescriptor.Builder a16 = FieldDescriptor.a("enableGalleryImportAutoTransform");
        zzct zzctVar15 = new zzct();
        zzctVar15.a(15);
        a16.b(zzctVar15.b()).a();
    }

    private zzhr() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
