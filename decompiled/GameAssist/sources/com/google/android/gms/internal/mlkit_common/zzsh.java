package com.google.android.gms.internal.mlkit_common;

import android.content.Context;
import android.content.res.Resources;
import androidx.core.os.ConfigurationCompat;
import androidx.core.os.LocaleListCompat;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.CommonUtils;
import com.google.mlkit.common.sdkinternal.MLTaskExecutor;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class zzsh {

    /* renamed from: i, reason: collision with root package name */
    private static zzaf f11834i;

    /* renamed from: j, reason: collision with root package name */
    private static final zzai f11835j = zzai.c("optional-module-barcode", "com.google.android.gms.vision.barcode");

    /* renamed from: a, reason: collision with root package name */
    private final String f11836a;

    /* renamed from: b, reason: collision with root package name */
    private final String f11837b;

    /* renamed from: c, reason: collision with root package name */
    private final zzrz f11838c;

    /* renamed from: d, reason: collision with root package name */
    private final SharedPrefManager f11839d;

    /* renamed from: e, reason: collision with root package name */
    private final Task f11840e;

    /* renamed from: f, reason: collision with root package name */
    private final Task f11841f;

    /* renamed from: g, reason: collision with root package name */
    private final String f11842g;

    /* renamed from: h, reason: collision with root package name */
    private final int f11843h;

    public zzsh(Context context, final SharedPrefManager sharedPrefManager, zzrz zzrzVar, String str) {
        new HashMap();
        new HashMap();
        this.f11836a = context.getPackageName();
        this.f11837b = CommonUtils.a(context);
        this.f11839d = sharedPrefManager;
        this.f11838c = zzrzVar;
        zzsv.a();
        this.f11842g = str;
        this.f11840e = MLTaskExecutor.b().c(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzse
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzsh.this.a();
            }
        });
        MLTaskExecutor b2 = MLTaskExecutor.b();
        Objects.requireNonNull(sharedPrefManager);
        this.f11841f = b2.c(new Callable() { // from class: com.google.android.gms.internal.mlkit_common.zzsf
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SharedPrefManager.this.h();
            }
        });
        zzai zzaiVar = f11835j;
        this.f11843h = zzaiVar.containsKey(str) ? DynamiteModule.c(context, (String) zzaiVar.get(str)) : -1;
    }

    private static synchronized zzaf h() {
        synchronized (zzsh.class) {
            try {
                zzaf zzafVar = f11834i;
                if (zzafVar != null) {
                    return zzafVar;
                }
                LocaleListCompat a2 = ConfigurationCompat.a(Resources.getSystem().getConfiguration());
                zzac zzacVar = new zzac();
                for (int i2 = 0; i2 < a2.g(); i2++) {
                    zzacVar.b(CommonUtils.b(a2.d(i2)));
                }
                zzaf c2 = zzacVar.c();
                f11834i = c2;
                return c2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private final zzqt i(String str, String str2) {
        zzqt zzqtVar = new zzqt();
        zzqtVar.b(this.f11836a);
        zzqtVar.c(this.f11837b);
        zzqtVar.h(h());
        zzqtVar.g(Boolean.TRUE);
        zzqtVar.l(str);
        zzqtVar.j(str2);
        zzqtVar.i(this.f11841f.l() ? (String) this.f11841f.i() : this.f11839d.h());
        zzqtVar.d(10);
        zzqtVar.k(Integer.valueOf(this.f11843h));
        return zzqtVar;
    }

    private final String j() {
        if (this.f11840e.l()) {
            return (String) this.f11840e.i();
        }
        return LibraryVersion.a().b(this.f11842g);
    }

    final /* synthetic */ String a() {
        return LibraryVersion.a().b(this.f11842g);
    }

    final /* synthetic */ void b(zzry zzryVar, zzmv zzmvVar, String str) {
        zzryVar.b(zzmvVar);
        zzryVar.d(i(zzryVar.zzd(), str));
        this.f11838c.a(zzryVar);
    }

    final /* synthetic */ void c(zzry zzryVar, zzsj zzsjVar, RemoteModel remoteModel) {
        zzryVar.b(zzmv.MODEL_DOWNLOAD);
        zzryVar.d(i(zzsjVar.e(), j()));
        zzryVar.c(zzst.a(remoteModel, this.f11839d, zzsjVar));
        this.f11838c.a(zzryVar);
    }

    public final void d(final zzry zzryVar, final zzmv zzmvVar) {
        final String j2 = j();
        MLTaskExecutor.e().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzsd
            @Override // java.lang.Runnable
            public final void run() {
                zzsh.this.b(zzryVar, zzmvVar, j2);
            }
        });
    }

    public final void e(zzry zzryVar, RemoteModel remoteModel, boolean z, int i2) {
        zzsi h2 = zzsj.h();
        h2.f(false);
        h2.d(remoteModel.d());
        h2.a(zzna.FAILED);
        h2.b(zzmu.DOWNLOAD_FAILED);
        h2.c(i2);
        g(zzryVar, remoteModel, h2.g());
    }

    public final void f(zzry zzryVar, RemoteModel remoteModel, zzmu zzmuVar, boolean z, ModelType modelType, zzna zznaVar) {
        zzsi h2 = zzsj.h();
        h2.f(z);
        h2.d(modelType);
        h2.b(zzmuVar);
        h2.a(zznaVar);
        g(zzryVar, remoteModel, h2.g());
    }

    public final void g(final zzry zzryVar, final RemoteModel remoteModel, final zzsj zzsjVar) {
        MLTaskExecutor.e().execute(new Runnable() { // from class: com.google.android.gms.internal.mlkit_common.zzsg
            @Override // java.lang.Runnable
            public final void run() {
                zzsh.this.c(zzryVar, zzsjVar, remoteModel);
            }
        });
    }
}
