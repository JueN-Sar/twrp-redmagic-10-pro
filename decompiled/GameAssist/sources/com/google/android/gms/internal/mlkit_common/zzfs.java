package com.google.android.gms.internal.mlkit_common;

import com.google.firebase.encoders.FieldDescriptor;
import com.google.firebase.encoders.ObjectEncoder;

/* loaded from: classes.dex */
final class zzfs implements ObjectEncoder {

    /* renamed from: a, reason: collision with root package name */
    static final zzfs f11527a = new zzfs();

    static {
        FieldDescriptor.Builder a2 = FieldDescriptor.a("initialImageUriCount");
        zzay zzayVar = new zzay();
        zzayVar.a(1);
        a2.b(zzayVar.b()).a();
        FieldDescriptor.Builder a3 = FieldDescriptor.a("defaultCaptureMode");
        zzay zzayVar2 = new zzay();
        zzayVar2.a(2);
        a3.b(zzayVar2.b()).a();
        FieldDescriptor.Builder a4 = FieldDescriptor.a("flashModeChangeAllowed");
        zzay zzayVar3 = new zzay();
        zzayVar3.a(3);
        a4.b(zzayVar3.b()).a();
        FieldDescriptor.Builder a5 = FieldDescriptor.a("galleryImportAllowed");
        zzay zzayVar4 = new zzay();
        zzayVar4.a(4);
        a5.b(zzayVar4.b()).a();
        FieldDescriptor.Builder a6 = FieldDescriptor.a("multiPageAllowed");
        zzay zzayVar5 = new zzay();
        zzayVar5.a(5);
        a6.b(zzayVar5.b()).a();
        FieldDescriptor.Builder a7 = FieldDescriptor.a("filterAllowed");
        zzay zzayVar6 = new zzay();
        zzayVar6.a(6);
        a7.b(zzayVar6.b()).a();
        FieldDescriptor.Builder a8 = FieldDescriptor.a("targetResolutionWidth");
        zzay zzayVar7 = new zzay();
        zzayVar7.a(7);
        a8.b(zzayVar7.b()).a();
        FieldDescriptor.Builder a9 = FieldDescriptor.a("targetResolutionHeight");
        zzay zzayVar8 = new zzay();
        zzayVar8.a(8);
        a9.b(zzayVar8.b()).a();
        FieldDescriptor.Builder a10 = FieldDescriptor.a("resultFormats");
        zzay zzayVar9 = new zzay();
        zzayVar9.a(9);
        a10.b(zzayVar9.b()).a();
        FieldDescriptor.Builder a11 = FieldDescriptor.a("pageEditListenerSet");
        zzay zzayVar10 = new zzay();
        zzayVar10.a(10);
        a11.b(zzayVar10.b()).a();
        FieldDescriptor.Builder a12 = FieldDescriptor.a("shadowRemovalAllowed");
        zzay zzayVar11 = new zzay();
        zzayVar11.a(11);
        a12.b(zzayVar11.b()).a();
        FieldDescriptor.Builder a13 = FieldDescriptor.a("stainRemovalAllowed");
        zzay zzayVar12 = new zzay();
        zzayVar12.a(12);
        a13.b(zzayVar12.b()).a();
        FieldDescriptor.Builder a14 = FieldDescriptor.a("enableAllNewFeaturesByDefault");
        zzay zzayVar13 = new zzay();
        zzayVar13.a(13);
        a14.b(zzayVar13.b()).a();
        FieldDescriptor.Builder a15 = FieldDescriptor.a("pageLimitMax");
        zzay zzayVar14 = new zzay();
        zzayVar14.a(14);
        a15.b(zzayVar14.b()).a();
        FieldDescriptor.Builder a16 = FieldDescriptor.a("enableGalleryImportAutoTransform");
        zzay zzayVar15 = new zzay();
        zzayVar15.a(15);
        a16.b(zzayVar15.b()).a();
    }

    private zzfs() {
    }

    @Override // com.google.firebase.encoders.ObjectEncoder
    public final /* bridge */ /* synthetic */ void a(Object obj, Object obj2) {
        throw null;
    }
}
