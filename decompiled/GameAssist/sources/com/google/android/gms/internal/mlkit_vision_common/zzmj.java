package com.google.android.gms.internal.mlkit_vision_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class zzmj {

    /* renamed from: k, reason: collision with root package name */
    private static zzp f12614k;

    /* renamed from: l, reason: collision with root package name */
    private static final zzr f12615l = zzr.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a, reason: collision with root package name */
    private final String f12616a;

    /* renamed from: b, reason: collision with root package name */
    private final String f12617b;

    /* renamed from: c, reason: collision with root package name */
    private final zzmc f12618c;

    /* renamed from: d, reason: collision with root package name */
    private final SharedPrefManager f12619d;

    /* renamed from: e, reason: collision with root package name */
    private final Task f12620e;

    /* renamed from: f, reason: collision with root package name */
    private final Task f12621f;

    /* renamed from: g, reason: collision with root package name */
    private final String f12622g;

    /* renamed from: h, reason: collision with root package name */
    private final int f12623h;

    /* renamed from: i, reason: collision with root package name */
    private final Map f12624i = new HashMap();

    /* renamed from: j, reason: collision with root package name */
    private final Map f12625j = new HashMap();

    public zzmj(Context context, final SharedPrefManager sharedPrefManager, zzmc zzmcVar, String str) {
        this.f12616a = context.getPackageName();
        this.f12617b = CommonUtils.a(context);
        this.f12619d = sharedPrefManager;
        this.f12618c = zzmcVar;
        zzmw.a();
        this.f12622g = str;
        this.f12620e = MLTaskExecutor.b().c(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzmj.this.a();
            }
        });
        MLTaskExecutor b2 = MLTaskExecutor.b();
        sharedPrefManager.getClass();
        this.f12621f = b2.c(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmh
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.h();
            }
        });
        zzr zzrVar = f12615l;
        this.f12623h = zzrVar.containsKey(str) ? DynamiteModule.c(context, (String) zzrVar.get(str)) : -1;
    }

    private static synchronized zzp d() {
        synchronized (zzmj.class) {
            try {
                zzp zzpVar = f12614k;
                if (zzpVar != null) {
                    return zzpVar;
                }
                LocaleListCompat a2 = ConfigurationCompat.a(Resources.getSystem().getConfiguration());
                zzm zzmVar = new zzm();
                for (int i2 = 0; i2 < a2.g(); i2++) {
                    zzmVar.c(CommonUtils.b(a2.d(i2)));
                }
                zzp d2 = zzmVar.d();
                f12614k = d2;
                return d2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    final /* synthetic */ String a() {
        return LibraryVersion.a().b(this.f12622g);
    }

    final /* synthetic */ void b(zzmb zzmbVar, zziv zzivVar, String str) {
        zzmbVar.a(zzivVar);
        String zzc = zzmbVar.zzc();
        zzky zzkyVar = new zzky();
        zzkyVar.b(this.f12616a);
        zzkyVar.c(this.f12617b);
        zzkyVar.h(d());
        zzkyVar.g(Boolean.TRUE);
        zzkyVar.l(zzc);
        zzkyVar.j(str);
        zzkyVar.i(this.f12621f.l() ? (String) this.f12621f.i() : this.f12619d.h());
        zzkyVar.d(10);
        zzkyVar.k(Integer.valueOf(this.f12623h));
        zzmbVar.c(zzkyVar);
        this.f12618c.a(zzmbVar);
    }

    public final void c(zzmt zzmtVar, final zziv zzivVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f12624i.get(zzivVar) != null && elapsedRealtime - ((Long) this.f12624i.get(zzivVar)).longValue() <= TimeUnit.SECONDS.toMillis(30L)) {
            return;
        }
        this.f12624i.put(zzivVar, Long.valueOf(elapsedRealtime));
        int i2 = zzmtVar.f12636a;
        int i3 = zzmtVar.f12637b;
        int i4 = zzmtVar.f12638c;
        int i5 = zzmtVar.f12639d;
        int i6 = zzmtVar.f12640e;
        long j2 = zzmtVar.f12641f;
        int i7 = zzmtVar.f12642g;
        zzin zzinVar = new zzin();
        zzinVar.d(i2 != -1 ? i2 != 35 ? i2 != 842094169 ? i2 != 16 ? i2 != 17 ? zzii.UNKNOWN_FORMAT : zzii.NV21 : zzii.NV16 : zzii.YV12 : zzii.YUV_420_888 : zzii.BITMAP);
        zzinVar.f(i3 != 1 ? i3 != 2 ? i3 != 3 ? i3 != 4 ? zzio.ANDROID_MEDIA_IMAGE : zzio.FILEPATH : zzio.BYTEBUFFER : zzio.BYTEARRAY : zzio.BITMAP);
        zzinVar.c(Integer.valueOf(i4));
        zzinVar.e(Integer.valueOf(i5));
        zzinVar.g(Integer.valueOf(i6));
        zzinVar.b(Long.valueOf(j2));
        zzinVar.h(Integer.valueOf(i7));
        zziq j3 = zzinVar.j();
        zziw zziwVar = new zziw();
        zziwVar.d(j3);
        final zzmb d2 = zzmk.d(zziwVar);
        final String b2 = this.f12620e.l() ? (String) this.f12620e.i() : LibraryVersion.a().b(this.f12622g);
        MLTaskExecutor.e().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_common.zzmi
            @Override // java.lang.Runnable
            public final void run() {
                zzmj.this.b(d2, zzivVar, b2);
            }
        });
    }
}
