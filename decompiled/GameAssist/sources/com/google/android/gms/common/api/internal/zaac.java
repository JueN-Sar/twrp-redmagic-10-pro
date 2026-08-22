package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

/* loaded from: classes.dex */
final class zaac implements OnCompleteListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ TaskCompletionSource f10660a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ zaad f10661b;

    zaac(zaad zaadVar, TaskCompletionSource taskCompletionSource) {
        this.f10661b = zaadVar;
        this.f10660a = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void a(Task task) {
        Map map;
        map = this.f10661b.f10663b;
        map.remove(this.f10660a);
    }
}
