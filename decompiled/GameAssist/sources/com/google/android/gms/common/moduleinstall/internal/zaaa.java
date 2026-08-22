package com.google.android.gms.common.moduleinstall.internal;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleInstallStatusUpdate;

/* loaded from: classes.dex */
final class zaaa implements ListenerHolder.Notifier {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ ModuleInstallStatusUpdate f11160a;

    zaaa(zaab zaabVar, ModuleInstallStatusUpdate moduleInstallStatusUpdate) {
        this.f11160a = moduleInstallStatusUpdate;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* bridge */ /* synthetic */ void a(Object obj) {
        ((InstallStatusListener) obj).a(this.f11160a);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void b() {
    }
}
