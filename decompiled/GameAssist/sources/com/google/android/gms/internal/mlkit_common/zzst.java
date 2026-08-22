package com.google.android.gms.internal.mlkit_common;

import android.os.SystemClock;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.mlkit.common.model.RemoteModel;
import com.google.mlkit.common.sdkinternal.ModelType;
import com.google.mlkit.common.sdkinternal.SharedPrefManager;

/* loaded from: classes.dex */
public final class zzst {

    /* renamed from: a, reason: collision with root package name */
    private static final GmsLogger f11852a = new GmsLogger("RemoteModelUtils", "");

    public static zznc a(RemoteModel remoteModel, SharedPrefManager sharedPrefManager, zzsj zzsjVar) {
        ModelType b2 = zzsjVar.b();
        String a2 = remoteModel.a();
        zzni zzniVar = new zzni();
        zznd zzndVar = new zznd();
        zzndVar.c(remoteModel.c());
        zzndVar.d(zznf.CLOUD);
        zzndVar.a(zzu.b(a2));
        int ordinal = b2.ordinal();
        zzndVar.b(ordinal != 2 ? ordinal != 4 ? ordinal != 5 ? zzne.TYPE_UNKNOWN : zzne.BASE_DIGITAL_INK : zzne.CUSTOM : zzne.BASE_TRANSLATE);
        zzniVar.b(zzndVar.g());
        zznl c2 = zzniVar.c();
        zzmz zzmzVar = new zzmz();
        zzmzVar.d(zzsjVar.c());
        zzmzVar.c(zzsjVar.d());
        zzmzVar.b(Long.valueOf(zzsjVar.a()));
        zzmzVar.f(c2);
        if (zzsjVar.g()) {
            long i2 = sharedPrefManager.i(remoteModel);
            if (i2 == 0) {
                f11852a.g("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                long j2 = sharedPrefManager.j(remoteModel);
                if (j2 == 0) {
                    j2 = SystemClock.elapsedRealtime();
                    sharedPrefManager.o(remoteModel, j2);
                }
                zzmzVar.g(Long.valueOf(j2 - i2));
            }
        }
        if (zzsjVar.f()) {
            long i3 = sharedPrefManager.i(remoteModel);
            if (i3 == 0) {
                f11852a.g("RemoteModelUtils", "Model downloaded without its beginning time recorded.");
            } else {
                zzmzVar.e(Long.valueOf(SystemClock.elapsedRealtime() - i3));
            }
        }
        return zzmzVar.i();
    }
}
