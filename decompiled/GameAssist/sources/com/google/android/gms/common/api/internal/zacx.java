package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
final class zacx implements Continuation {
    @Override // com.google.android.gms.tasks.Continuation
    public final /* bridge */ /* synthetic */ Object a(Task task) {
        if (((Boolean) task.i()).booleanValue()) {
            return null;
        }
        throw new ApiException(new Status(13, "listener already unregistered"));
    }
}
