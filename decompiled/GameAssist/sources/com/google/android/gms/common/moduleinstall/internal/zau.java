package com.google.android.gms.common.moduleinstall.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
final class zau extends zaa {
    final /* synthetic */ AtomicReference zaa;
    final /* synthetic */ TaskCompletionSource zab;
    final /* synthetic */ InstallStatusListener zac;
    final /* synthetic */ zay zad;

    zau(zay zayVar, AtomicReference atomicReference, TaskCompletionSource taskCompletionSource, InstallStatusListener installStatusListener) {
        this.zad = zayVar;
        this.zaa = atomicReference;
        this.zab = taskCompletionSource;
        this.zac = installStatusListener;
    }

    @Override // com.google.android.gms.common.moduleinstall.internal.zaa, com.google.android.gms.common.moduleinstall.internal.zae
    public final void zad(Status status, @Nullable ModuleInstallResponse moduleInstallResponse) {
        if (moduleInstallResponse != null) {
            this.zaa.set(moduleInstallResponse);
        }
        TaskUtil.a(status, null, this.zab);
        if (!status.Y() || (moduleInstallResponse != null && moduleInstallResponse.P())) {
            this.zad.g(ListenerHolders.c(this.zac, InstallStatusListener.class.getSimpleName()), 27306);
        }
    }
}
