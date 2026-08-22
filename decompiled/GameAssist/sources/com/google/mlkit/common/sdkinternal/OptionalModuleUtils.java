package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.ModuleInstall;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.internal.mlkit_common.zzaf;
import com.google.android.gms.internal.mlkit_common.zzah;
import com.google.android.gms.internal.mlkit_common.zzai;
import com.google.android.gms.tasks.OnFailureListener;
import java.util.List;
import java.util.Map;

@KeepForSdk
/* loaded from: classes.dex */
public class OptionalModuleUtils {
    private static final zzai A;
    private static final zzai B;

    /* renamed from: a, reason: collision with root package name */
    public static final Feature[] f15951a = new Feature[0];

    /* renamed from: b, reason: collision with root package name */
    public static final Feature f15952b;

    /* renamed from: c, reason: collision with root package name */
    public static final Feature f15953c;

    /* renamed from: d, reason: collision with root package name */
    public static final Feature f15954d;

    /* renamed from: e, reason: collision with root package name */
    public static final Feature f15955e;

    /* renamed from: f, reason: collision with root package name */
    public static final Feature f15956f;

    /* renamed from: g, reason: collision with root package name */
    public static final Feature f15957g;

    /* renamed from: h, reason: collision with root package name */
    public static final Feature f15958h;

    /* renamed from: i, reason: collision with root package name */
    public static final Feature f15959i;

    /* renamed from: j, reason: collision with root package name */
    public static final Feature f15960j;

    /* renamed from: k, reason: collision with root package name */
    public static final Feature f15961k;

    /* renamed from: l, reason: collision with root package name */
    public static final Feature f15962l;

    /* renamed from: m, reason: collision with root package name */
    public static final Feature f15963m;

    /* renamed from: n, reason: collision with root package name */
    public static final Feature f15964n;

    /* renamed from: o, reason: collision with root package name */
    public static final Feature f15965o;

    /* renamed from: p, reason: collision with root package name */
    public static final Feature f15966p;

    /* renamed from: q, reason: collision with root package name */
    public static final Feature f15967q;

    /* renamed from: r, reason: collision with root package name */
    public static final Feature f15968r;

    /* renamed from: s, reason: collision with root package name */
    public static final Feature f15969s;
    public static final Feature t;
    public static final Feature u;
    public static final Feature v;
    public static final Feature w;
    public static final Feature x;
    public static final Feature y;
    public static final Feature z;

    static {
        Feature feature = new Feature("vision.barcode", 1L);
        f15952b = feature;
        Feature feature2 = new Feature("vision.custom.ica", 1L);
        f15953c = feature2;
        Feature feature3 = new Feature("vision.face", 1L);
        f15954d = feature3;
        Feature feature4 = new Feature("vision.ica", 1L);
        f15955e = feature4;
        Feature feature5 = new Feature("vision.ocr", 1L);
        f15956f = feature5;
        f15957g = new Feature("mlkit.ocr.chinese", 1L);
        f15958h = new Feature("mlkit.ocr.common", 1L);
        f15959i = new Feature("mlkit.ocr.devanagari", 1L);
        f15960j = new Feature("mlkit.ocr.japanese", 1L);
        f15961k = new Feature("mlkit.ocr.korean", 1L);
        Feature feature6 = new Feature("mlkit.langid", 1L);
        f15962l = feature6;
        Feature feature7 = new Feature("mlkit.nlclassifier", 1L);
        f15963m = feature7;
        Feature feature8 = new Feature("tflite_dynamite", 1L);
        f15964n = feature8;
        Feature feature9 = new Feature("mlkit.barcode.ui", 1L);
        f15965o = feature9;
        Feature feature10 = new Feature("mlkit.smartreply", 1L);
        f15966p = feature10;
        f15967q = new Feature("mlkit.image.caption", 1L);
        f15968r = new Feature("mlkit.docscan.detect", 1L);
        f15969s = new Feature("mlkit.docscan.crop", 1L);
        t = new Feature("mlkit.docscan.enhance", 1L);
        u = new Feature("mlkit.docscan.ui", 1L);
        v = new Feature("mlkit.docscan.stain", 1L);
        w = new Feature("mlkit.docscan.shadow", 1L);
        x = new Feature("mlkit.quality.aesthetic", 1L);
        y = new Feature("mlkit.quality.technical", 1L);
        z = new Feature("mlkit.segmentation.subject", 1L);
        zzah zzahVar = new zzah();
        zzahVar.a("barcode", feature);
        zzahVar.a("custom_ica", feature2);
        zzahVar.a("face", feature3);
        zzahVar.a("ica", feature4);
        zzahVar.a("ocr", feature5);
        zzahVar.a("langid", feature6);
        zzahVar.a("nlclassifier", feature7);
        zzahVar.a("tflite_dynamite", feature8);
        zzahVar.a("barcode_ui", feature9);
        zzahVar.a("smart_reply", feature10);
        A = zzahVar.b();
        zzah zzahVar2 = new zzah();
        zzahVar2.a("com.google.android.gms.vision.barcode", feature);
        zzahVar2.a("com.google.android.gms.vision.custom.ica", feature2);
        zzahVar2.a("com.google.android.gms.vision.face", feature3);
        zzahVar2.a("com.google.android.gms.vision.ica", feature4);
        zzahVar2.a("com.google.android.gms.vision.ocr", feature5);
        zzahVar2.a("com.google.android.gms.mlkit.langid", feature6);
        zzahVar2.a("com.google.android.gms.mlkit.nlclassifier", feature7);
        zzahVar2.a("com.google.android.gms.tflite_dynamite", feature8);
        zzahVar2.a("com.google.android.gms.mlkit_smartreply", feature10);
        B = zzahVar2.b();
    }

    public static void a(Context context, String str) {
        b(context, zzaf.k(str));
    }

    public static void b(Context context, List list) {
        if (GoogleApiAvailabilityLight.h().b(context) >= 221500000) {
            c(context, d(A, list));
            return;
        }
        Intent intent = new Intent();
        intent.setClassName("com.google.android.gms", "com.google.android.gms.vision.DependencyBroadcastReceiverProxy");
        intent.setAction("com.google.android.gms.vision.DEPENDENCY");
        intent.putExtra("com.google.android.gms.vision.DEPENDENCIES", TextUtils.join(",", list));
        intent.putExtra("requester_app_package", context.getApplicationInfo().packageName);
        context.sendBroadcast(intent);
    }

    public static void c(Context context, final Feature[] featureArr) {
        ModuleInstall.a(context).b(ModuleInstallRequest.d().a(new OptionalModuleApi() { // from class: com.google.mlkit.common.sdkinternal.zzo
            @Override // com.google.android.gms.common.api.OptionalModuleApi
            public final Feature[] e() {
                Feature[] featureArr2 = OptionalModuleUtils.f15951a;
                return featureArr;
            }
        }).b()).d(new OnFailureListener() { // from class: com.google.mlkit.common.sdkinternal.zzp
            @Override // com.google.android.gms.tasks.OnFailureListener
            public final void d(Exception exc) {
                Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
            }
        });
    }

    private static Feature[] d(Map map, List list) {
        Feature[] featureArr = new Feature[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            featureArr[i2] = (Feature) Preconditions.i((Feature) map.get(list.get(i2)));
        }
        return featureArr;
    }
}
