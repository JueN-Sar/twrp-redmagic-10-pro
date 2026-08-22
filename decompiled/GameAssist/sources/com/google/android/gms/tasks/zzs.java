package com.google.android.gms.tasks;

/* loaded from: classes.dex */
final class zzs implements OnTokenCanceledListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f13723a;

    zzs(TaskCompletionSource taskCompletionSource) {
        this.f13723a = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void b() {
        zzw zzwVar;
        zzwVar = this.f13723a.f13670a;
        zzwVar.p();
    }
}
