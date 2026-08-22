package com.google.android.gms.common;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class zzs extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzs> CREATOR = new zzt();

    /* renamed from: c, reason: collision with root package name */
    private final String f11322c;

    /* renamed from: h, reason: collision with root package name */
    private final zzj f11323h;

    /* renamed from: i, reason: collision with root package name */
    private final boolean f11324i;

    /* renamed from: j, reason: collision with root package name */
    private final boolean f11325j;

    zzs(String str, zzj zzjVar, boolean z, boolean z2) {
        this.f11322c = str;
        this.f11323h = zzjVar;
        this.f11324i = z;
        this.f11325j = z2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        String str = this.f11322c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.m(parcel, 1, str, false);
        zzj zzjVar = this.f11323h;
        if (zzjVar == null) {
            Log.w("GoogleCertificatesQuery", "certificate binder is null");
            zzjVar = null;
        }
        SafeParcelWriter.f(parcel, 2, zzjVar, false);
        SafeParcelWriter.c(parcel, 3, this.f11324i);
        SafeParcelWriter.c(parcel, 4, this.f11325j);
        SafeParcelWriter.b(parcel, a2);
    }

    zzs(String str, IBinder iBinder, boolean z, boolean z2) {
        this.f11322c = str;
        zzk zzkVar = null;
        if (iBinder != null) {
            try {
                IObjectWrapper zzd = com.google.android.gms.common.internal.zzz.zzg(iBinder).zzd();
                byte[] bArr = zzd == null ? null : (byte[]) ObjectWrapper.unwrap(zzd);
                if (bArr != null) {
                    zzkVar = new zzk(bArr);
                } else {
                    Log.e("GoogleCertificatesQuery", "Could not unwrap certificate");
                }
            } catch (RemoteException e2) {
                Log.e("GoogleCertificatesQuery", "Could not unwrap certificate", e2);
            }
        }
        this.f11323h = zzkVar;
        this.f11324i = z;
        this.f11325j = z2;
    }
}
