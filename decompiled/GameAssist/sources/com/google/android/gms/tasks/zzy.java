package com.google.android.gms.tasks;

/* loaded from: classes.dex */
public final /* synthetic */ class zzy implements OnCompleteListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ com.google.android.gms.internal.tasks.zza f13733a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f13734b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ zzb f13735c;

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void a(Task task) {
        this.f13733a.removeCallbacksAndMessages(null);
        TaskCompletionSource taskCompletionSource = this.f13734b;
        if (task.l()) {
            taskCompletionSource.e(task.i());
        } else {
            if (task.j()) {
                this.f13735c.c();
                return;
            }
            Exception h2 = task.h();
            h2.getClass();
            taskCompletionSource.d(h2);
        }
    }
}
