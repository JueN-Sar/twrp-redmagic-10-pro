package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IFragmentWrapper;

@KeepForSdk
/* loaded from: classes.dex */
public final class SupportFragmentWrapper extends IFragmentWrapper.Stub {
    private final Fragment zza;

    private SupportFragmentWrapper(Fragment fragment) {
        this.zza = fragment;
    }

    @Nullable
    @KeepForSdk
    public static SupportFragmentWrapper wrap(@Nullable Fragment fragment) {
        if (fragment != null) {
            return new SupportFragmentWrapper(fragment);
        }
        return null;
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzA() {
        return this.zza.w0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final int zzb() {
        return this.zza.J();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final int zzc() {
        return this.zza.f0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final Bundle zzd() {
        return this.zza.x();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final IFragmentWrapper zze() {
        return wrap(this.zza.N());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final IFragmentWrapper zzf() {
        return wrap(this.zza.d0());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @NonNull
    public final IObjectWrapper zzg() {
        return ObjectWrapper.wrap(this.zza.t());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @NonNull
    public final IObjectWrapper zzh() {
        return ObjectWrapper.wrap(this.zza.U());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @NonNull
    public final IObjectWrapper zzi() {
        return ObjectWrapper.wrap(this.zza.h0());
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    @Nullable
    public final String zzj() {
        return this.zza.c0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzk(@NonNull IObjectWrapper iObjectWrapper) {
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.i(view);
        this.zza.A1(view);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzl(boolean z) {
        this.zza.L1(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzm(boolean z) {
        this.zza.N1(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzn(boolean z) {
        this.zza.R1(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzo(boolean z) {
        this.zza.U1(z);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzp(@NonNull Intent intent) {
        this.zza.V1(intent);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzq(@NonNull Intent intent, int i2) {
        this.zza.startActivityForResult(intent, i2);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final void zzr(@NonNull IObjectWrapper iObjectWrapper) {
        View view = (View) ObjectWrapper.unwrap(iObjectWrapper);
        Preconditions.i(view);
        this.zza.Z1(view);
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzs() {
        return this.zza.V();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzt() {
        return this.zza.g0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzu() {
        return this.zza.m0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzv() {
        return this.zza.n0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzw() {
        return this.zza.o0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzx() {
        return this.zza.q0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzy() {
        return this.zza.t0();
    }

    @Override // com.google.android.gms.dynamic.IFragmentWrapper
    public final boolean zzz() {
        return this.zza.u0();
    }
}
