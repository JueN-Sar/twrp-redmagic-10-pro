package com.google.android.gms.common;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.errorprone.annotations.CheckReturnValue;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@CheckReturnValue
/* loaded from: classes.dex */
final class zzn {

    /* renamed from: e, reason: collision with root package name */
    private static volatile zzag f11309e;

    /* renamed from: g, reason: collision with root package name */
    private static Context f11311g;

    /* renamed from: a, reason: collision with root package name */
    static final zzl f11305a = new zzf(zzj.zze("0\u0082\u0005È0\u0082\u0003° \u0003\u0002\u0001\u0002\u0002\u0014\u0010\u008ae\bsù/\u008eQí"));

    /* renamed from: b, reason: collision with root package name */
    static final zzl f11306b = new zzg(zzj.zze("0\u0082\u0006\u00040\u0082\u0003ì \u0003\u0002\u0001\u0002\u0002\u0014\u0003£²\u00ad×árÊkì"));

    /* renamed from: c, reason: collision with root package name */
    static final zzl f11307c = new zzh(zzj.zze("0\u0082\u0004C0\u0082\u0003+ \u0003\u0002\u0001\u0002\u0002\t\u0000Âà\u0087FdJ0\u008d0"));

    /* renamed from: d, reason: collision with root package name */
    static final zzl f11308d = new zzi(zzj.zze("0\u0082\u0004¨0\u0082\u0003\u0090 \u0003\u0002\u0001\u0002\u0002\t\u0000Õ\u0085¸l}ÓNõ0"));

    /* renamed from: f, reason: collision with root package name */
    private static final Object f11310f = new Object();

    static /* synthetic */ String a(boolean z, String str, zzj zzjVar) {
        String str2 = (z || !c(str, zzjVar, true, false).f11328a) ? "not allowed" : "debug cert rejected";
        MessageDigest a2 = AndroidUtilsLight.a("SHA-256");
        Preconditions.i(a2);
        return String.format("%s: pkg=%s, sha256=%s, atk=%s, ver=%s", str2, str, Hex.a(a2.digest(zzjVar.zzf())), Boolean.valueOf(z), "12451000.false");
    }

    static synchronized void b(Context context) {
        synchronized (zzn.class) {
            if (f11311g != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                f11311g = context.getApplicationContext();
            }
        }
    }

    private static zzx c(final String str, final zzj zzjVar, final boolean z, boolean z2) {
        try {
            d();
            Preconditions.i(f11311g);
            try {
                return f11309e.zzh(new zzs(str, zzjVar, z, z2), ObjectWrapper.wrap(f11311g.getPackageManager())) ? zzx.a() : new zzv(new Callable() { // from class: com.google.android.gms.common.zze
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return zzn.a(z, str, zzjVar);
                    }
                }, null);
            } catch (RemoteException e2) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e2);
                return zzx.b("module call", e2);
            }
        } catch (DynamiteModule.LoadingException e3) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e3);
            return zzx.b("module init: ".concat(String.valueOf(e3.getMessage())), e3);
        }
    }

    private static void d() {
        if (f11309e != null) {
            return;
        }
        Preconditions.i(f11311g);
        synchronized (f11310f) {
            try {
                if (f11309e == null) {
                    f11309e = zzaf.zzb(DynamiteModule.e(f11311g, DynamiteModule.f11344f, "com.google.android.gms.googlecertificates").d("com.google.android.gms.common.GoogleCertificatesImpl"));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
