package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zzc;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;

/* loaded from: classes.dex */
public interface IFragmentWrapper extends IInterface {

    public static abstract class Stub extends com.google.android.gms.internal.common.zzb implements IFragmentWrapper {
        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        @NonNull
        public static IFragmentWrapper asInterface(@NonNull IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return queryLocalInterface instanceof IFragmentWrapper ? (IFragmentWrapper) queryLocalInterface : new zza(iBinder);
        }

        @Override // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int i2, @NonNull Parcel parcel, @NonNull Parcel parcel2, int i3) {
            switch (i2) {
                case 2:
                    IObjectWrapper zzg = zzg();
                    parcel2.writeNoException();
                    zzc.e(parcel2, zzg);
                    return true;
                case 3:
                    Bundle zzd = zzd();
                    parcel2.writeNoException();
                    zzc.d(parcel2, zzd);
                    return true;
                case 4:
                    int zzb = zzb();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzb);
                    return true;
                case 5:
                    IFragmentWrapper zze = zze();
                    parcel2.writeNoException();
                    zzc.e(parcel2, zze);
                    return true;
                case 6:
                    IObjectWrapper zzh = zzh();
                    parcel2.writeNoException();
                    zzc.e(parcel2, zzh);
                    return true;
                case 7:
                    boolean zzs = zzs();
                    parcel2.writeNoException();
                    int i4 = zzc.f11392b;
                    parcel2.writeInt(zzs ? 1 : 0);
                    return true;
                case 8:
                    String zzj = zzj();
                    parcel2.writeNoException();
                    parcel2.writeString(zzj);
                    return true;
                case 9:
                    IFragmentWrapper zzf = zzf();
                    parcel2.writeNoException();
                    zzc.e(parcel2, zzf);
                    return true;
                case 10:
                    int zzc = zzc();
                    parcel2.writeNoException();
                    parcel2.writeInt(zzc);
                    return true;
                case 11:
                    boolean zzt = zzt();
                    parcel2.writeNoException();
                    int i5 = zzc.f11392b;
                    parcel2.writeInt(zzt ? 1 : 0);
                    return true;
                case 12:
                    IObjectWrapper zzi = zzi();
                    parcel2.writeNoException();
                    zzc.e(parcel2, zzi);
                    return true;
                case 13:
                    boolean zzu = zzu();
                    parcel2.writeNoException();
                    int i6 = zzc.f11392b;
                    parcel2.writeInt(zzu ? 1 : 0);
                    return true;
                case 14:
                    boolean zzv = zzv();
                    parcel2.writeNoException();
                    int i7 = zzc.f11392b;
                    parcel2.writeInt(zzv ? 1 : 0);
                    return true;
                case 15:
                    boolean zzw = zzw();
                    parcel2.writeNoException();
                    int i8 = zzc.f11392b;
                    parcel2.writeInt(zzw ? 1 : 0);
                    return true;
                case 16:
                    boolean zzx = zzx();
                    parcel2.writeNoException();
                    int i9 = zzc.f11392b;
                    parcel2.writeInt(zzx ? 1 : 0);
                    return true;
                case MlKitException.NETWORK_ISSUE /* 17 */:
                    boolean zzy = zzy();
                    parcel2.writeNoException();
                    int i10 = zzc.f11392b;
                    parcel2.writeInt(zzy ? 1 : 0);
                    return true;
                case MlKitException.UNSUPPORTED /* 18 */:
                    boolean zzz = zzz();
                    parcel2.writeNoException();
                    int i11 = zzc.f11392b;
                    parcel2.writeInt(zzz ? 1 : 0);
                    return true;
                case 19:
                    boolean zzA = zzA();
                    parcel2.writeNoException();
                    int i12 = zzc.f11392b;
                    parcel2.writeInt(zzA ? 1 : 0);
                    return true;
                case 20:
                    IObjectWrapper asInterface = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzc.b(parcel);
                    zzk(asInterface);
                    parcel2.writeNoException();
                    return true;
                case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                    boolean f2 = zzc.f(parcel);
                    zzc.b(parcel);
                    zzl(f2);
                    parcel2.writeNoException();
                    return true;
                case 22:
                    boolean f3 = zzc.f(parcel);
                    zzc.b(parcel);
                    zzm(f3);
                    parcel2.writeNoException();
                    return true;
                case 23:
                    boolean f4 = zzc.f(parcel);
                    zzc.b(parcel);
                    zzn(f4);
                    parcel2.writeNoException();
                    return true;
                case 24:
                    boolean f5 = zzc.f(parcel);
                    zzc.b(parcel);
                    zzo(f5);
                    parcel2.writeNoException();
                    return true;
                case 25:
                    Intent intent = (Intent) zzc.a(parcel, Intent.CREATOR);
                    zzc.b(parcel);
                    zzp(intent);
                    parcel2.writeNoException();
                    return true;
                case 26:
                    Intent intent2 = (Intent) zzc.a(parcel, Intent.CREATOR);
                    int readInt = parcel.readInt();
                    zzc.b(parcel);
                    zzq(intent2, readInt);
                    parcel2.writeNoException();
                    return true;
                case 27:
                    IObjectWrapper asInterface2 = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
                    zzc.b(parcel);
                    zzr(asInterface2);
                    parcel2.writeNoException();
                    return true;
                default:
                    return false;
            }
        }
    }

    boolean zzA();

    int zzb();

    int zzc();

    @Nullable
    Bundle zzd();

    @Nullable
    IFragmentWrapper zze();

    @Nullable
    IFragmentWrapper zzf();

    @NonNull
    IObjectWrapper zzg();

    @NonNull
    IObjectWrapper zzh();

    @NonNull
    IObjectWrapper zzi();

    @Nullable
    String zzj();

    void zzk(@NonNull IObjectWrapper iObjectWrapper);

    void zzl(boolean z);

    void zzm(boolean z);

    void zzn(boolean z);

    void zzo(boolean z);

    void zzp(@NonNull Intent intent);

    void zzq(@NonNull Intent intent, int i2);

    void zzr(@NonNull IObjectWrapper iObjectWrapper);

    boolean zzs();

    boolean zzt();

    boolean zzu();

    boolean zzv();

    boolean zzw();

    boolean zzx();

    boolean zzy();

    boolean zzz();
}
