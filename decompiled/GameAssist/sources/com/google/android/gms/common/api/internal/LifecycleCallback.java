package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Keep;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

@KeepForSdk
/* loaded from: classes.dex */
public class LifecycleCallback {

    @NonNull
    @KeepForSdk
    protected final LifecycleFragment mLifecycleFragment;

    protected LifecycleCallback(LifecycleFragment lifecycleFragment) {
        this.mLifecycleFragment = lifecycleFragment;
    }

    @Keep
    private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity lifecycleActivity) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    @NonNull
    @KeepForSdk
    public static LifecycleFragment getFragment(@NonNull Activity activity) {
        return getFragment(new LifecycleActivity(activity));
    }

    @KeepForSdk
    @MainThread
    public void dump(@NonNull String str, @NonNull FileDescriptor fileDescriptor, @NonNull PrintWriter printWriter, @NonNull String[] strArr) {
    }

    @NonNull
    @KeepForSdk
    public Activity getActivity() {
        Activity g2 = this.mLifecycleFragment.g();
        Preconditions.i(g2);
        return g2;
    }

    @KeepForSdk
    @MainThread
    public void onActivityResult(int i2, int i3, @NonNull Intent intent) {
    }

    @KeepForSdk
    @MainThread
    public void onCreate(@Nullable Bundle bundle) {
    }

    @KeepForSdk
    @MainThread
    public void onDestroy() {
    }

    @KeepForSdk
    @MainThread
    public void onResume() {
    }

    @KeepForSdk
    @MainThread
    public void onSaveInstanceState(@NonNull Bundle bundle) {
    }

    @KeepForSdk
    @MainThread
    public void onStart() {
    }

    public void onStop() {
    }

    @NonNull
    @KeepForSdk
    public static LifecycleFragment getFragment(@NonNull ContextWrapper contextWrapper) {
        throw new UnsupportedOperationException();
    }

    @NonNull
    @KeepForSdk
    protected static LifecycleFragment getFragment(@NonNull LifecycleActivity lifecycleActivity) {
        if (lifecycleActivity.d()) {
            return zzd.c2(lifecycleActivity.b());
        }
        if (lifecycleActivity.c()) {
            return zzb.e(lifecycleActivity.a());
        }
        throw new IllegalArgumentException("Can't get fragment for unexpected activity.");
    }
}
