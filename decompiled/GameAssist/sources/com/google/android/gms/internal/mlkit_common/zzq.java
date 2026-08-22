package com.google.android.gms.internal.mlkit_common;

import java.util.Arrays;

/* loaded from: classes.dex */
public final class zzq {

    /* renamed from: a, reason: collision with root package name */
    private final String f11776a;

    /* renamed from: b, reason: collision with root package name */
    private final zzo f11777b;

    /* renamed from: c, reason: collision with root package name */
    private zzo f11778c;

    /* synthetic */ zzq(String str, zzp zzpVar) {
        zzo zzoVar = new zzo();
        this.f11777b = zzoVar;
        this.f11778c = zzoVar;
        str.getClass();
        this.f11776a = str;
    }

    public final zzq a(String str, Object obj) {
        zzo zzoVar = new zzo();
        this.f11778c.f11775c = zzoVar;
        this.f11778c = zzoVar;
        zzoVar.f11774b = obj;
        zzoVar.f11773a = str;
        return this;
    }

    public final zzq b(String str, boolean z) {
        String valueOf = String.valueOf(z);
        zzn zznVar = new zzn(null);
        this.f11778c.f11775c = zznVar;
        this.f11778c = zznVar;
        zznVar.f11774b = valueOf;
        zznVar.f11773a = "isManifestFile";
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f11776a);
        sb.append('{');
        zzo zzoVar = this.f11777b.f11775c;
        String str = "";
        while (zzoVar != null) {
            Object obj = zzoVar.f11774b;
            sb.append(str);
            String str2 = zzoVar.f11773a;
            if (str2 != null) {
                sb.append(str2);
                sb.append('=');
            }
            if (obj == null || !obj.getClass().isArray()) {
                sb.append(obj);
            } else {
                sb.append((CharSequence) Arrays.deepToString(new Object[]{obj}), 1, r1.length() - 1);
            }
            zzoVar = zzoVar.f11775c;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
