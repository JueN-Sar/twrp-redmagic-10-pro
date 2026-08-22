package com.google.android.gms.internal.mlkit_vision_text_common;

import android.content.Context;
import android.content.res.Resources;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class zzuc {

    /* renamed from: k, reason: collision with root package name */
    private static zzbk f13566k;

    /* renamed from: l, reason: collision with root package name */
    private static final zzbm f13567l = zzbm.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a, reason: collision with root package name */
    private final String f13568a;

    /* renamed from: b, reason: collision with root package name */
    private final String f13569b;

    /* renamed from: c, reason: collision with root package name */
    private final zzts f13570c;

    /* renamed from: d, reason: collision with root package name */
    private final SharedPrefManager f13571d;

    /* renamed from: e, reason: collision with root package name */
    private final Task f13572e;

    /* renamed from: f, reason: collision with root package name */
    private final Task f13573f;

    /* renamed from: g, reason: collision with root package name */
    private final String f13574g;

    /* renamed from: h, reason: collision with root package name */
    private final int f13575h;

    /* renamed from: i, reason: collision with root package name */
    private final Map f13576i = new HashMap();

    /* renamed from: j, reason: collision with root package name */
    private final Map f13577j = new HashMap();

    public zzuc(Context context, final SharedPrefManager sharedPrefManager, zzts zztsVar, String str) {
        this.f13568a = context.getPackageName();
        this.f13569b = CommonUtils.a(context);
        this.f13571d = sharedPrefManager;
        this.f13570c = zztsVar;
        zzuo.a();
        this.f13574g = str;
        this.f13572e = MLTaskExecutor.b().c(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zzty
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzuc.this.a();
            }
        });
        MLTaskExecutor b2 = MLTaskExecutor.b();
        Objects.requireNonNull(sharedPrefManager);
        this.f13573f = b2.c(new Callable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zztz
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.h();
            }
        });
        zzbm zzbmVar = f13567l;
        this.f13575h = zzbmVar.containsKey(str) ? DynamiteModule.c(context, (String) zzbmVar.get(str)) : -1;
    }

    private static synchronized zzbk h() {
        synchronized (zzuc.class) {
            try {
                zzbk zzbkVar = f13566k;
                if (zzbkVar != null) {
                    return zzbkVar;
                }
                LocaleListCompat a2 = ConfigurationCompat.a(Resources.getSystem().getConfiguration());
                zzbh zzbhVar = new zzbh();
                for (int i2 = 0; i2 < a2.g(); i2++) {
                    zzbhVar.a(CommonUtils.b(a2.d(i2)));
                }
                zzbk b2 = zzbhVar.b();
                f13566k = b2;
                return b2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final String i() {
        if (this.f13572e.l()) {
            return (String) this.f13572e.i();
        }
        return LibraryVersion.a().b(this.f13574g);
    }

    private final boolean j(zzov zzovVar, long j2, long j3) {
        return this.f13576i.get(zzovVar) == null || j2 - ((Long) this.f13576i.get(zzovVar)).longValue() > TimeUnit.SECONDS.toMillis(30L);
    }

    @VisibleForTesting
    static long zza(List list, double d2) {
        return ((Long) list.get(Math.max(((int) Math.ceil((d2 / 100.0d) * list.size())) - 1, 0))).longValue();
    }

    final /* synthetic */ String a() {
        return LibraryVersion.a().b(this.f13574g);
    }

    final /* synthetic */ void b(zztr zztrVar, zzov zzovVar, String str) {
        zztrVar.c(zzovVar);
        String zzd = zztrVar.zzd();
        zzsr zzsrVar = new zzsr();
        zzsrVar.b(this.f13568a);
        zzsrVar.c(this.f13569b);
        zzsrVar.h(h());
        zzsrVar.g(Boolean.TRUE);
        zzsrVar.l(zzd);
        zzsrVar.j(str);
        zzsrVar.i(this.f13573f.l() ? (String) this.f13573f.i() : this.f13571d.h());
        zzsrVar.d(10);
        zzsrVar.k(Integer.valueOf(this.f13575h));
        zztrVar.b(zzsrVar);
        this.f13570c.a(zztrVar);
    }

    public final void c(zztr zztrVar, zzov zzovVar) {
        d(zztrVar, zzovVar, i());
    }

    public final void d(final zztr zztrVar, final zzov zzovVar, final String str) {
        MLTaskExecutor.e().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zztw
            @Override // java.lang.Runnable
            public final void run() {
                zzuc.this.b(zztrVar, zzovVar, str);
            }
        });
    }

    public final void e(zzub zzubVar, zzov zzovVar) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j(zzovVar, elapsedRealtime, 30L)) {
            this.f13576i.put(zzovVar, Long.valueOf(elapsedRealtime));
            d(zzubVar.zza(), zzovVar, i());
        }
    }

    final /* synthetic */ void f(zzov zzovVar, com.google.mlkit.vision.text.internal.zzr zzrVar) {
        zzbp zzbpVar = (zzbp) this.f13577j.get(zzovVar);
        if (zzbpVar != null) {
            for (Object obj : zzbpVar.d()) {
                ArrayList arrayList = new ArrayList(zzbpVar.b(obj));
                Collections.sort(arrayList);
                zznu zznuVar = new zznu();
                Iterator it = arrayList.iterator();
                long j2 = 0;
                while (it.hasNext()) {
                    j2 += ((Long) it.next()).longValue();
                }
                zznuVar.a(Long.valueOf(j2 / arrayList.size()));
                zznuVar.c(Long.valueOf(zza(arrayList, 100.0d)));
                zznuVar.f(Long.valueOf(zza(arrayList, 75.0d)));
                zznuVar.d(Long.valueOf(zza(arrayList, 50.0d)));
                zznuVar.b(Long.valueOf(zza(arrayList, 25.0d)));
                zznuVar.e(Long.valueOf(zza(arrayList, 0.0d)));
                d(zzrVar.a(obj, arrayList.size(), zznuVar.g()), zzovVar, i());
            }
            this.f13577j.remove(zzovVar);
        }
    }

    final /* synthetic */ void g(final zzov zzovVar, Object obj, long j2, final com.google.mlkit.vision.text.internal.zzr zzrVar) {
        if (!this.f13577j.containsKey(zzovVar)) {
            this.f13577j.put(zzovVar, zzao.p());
        }
        ((zzbp) this.f13577j.get(zzovVar)).a(obj, Long.valueOf(j2));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j(zzovVar, elapsedRealtime, 30L)) {
            this.f13576i.put(zzovVar, Long.valueOf(elapsedRealtime));
            MLTaskExecutor.e().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_vision_text_common.zztx
                @Override // java.lang.Runnable
                public final void run() {
                    zzuc.this.f(zzovVar, zzrVar);
                }
            });
        }
    }
}
