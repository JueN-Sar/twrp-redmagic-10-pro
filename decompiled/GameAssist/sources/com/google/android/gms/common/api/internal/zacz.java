package com.google.android.gms.common.api.internal;

import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* loaded from: classes.dex */
final class zacz extends com.google.android.gms.internal.base.zau {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ zada f10822a;

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object obj;
        zada zadaVar;
        int i2 = message.what;
        if (i2 != 0) {
            if (i2 == 1) {
                RuntimeException runtimeException = (RuntimeException) message.obj;
                Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: ".concat(String.valueOf(runtimeException.getMessage())));
                throw runtimeException;
            }
            Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + i2);
            return;
        }
        PendingResult pendingResult = (PendingResult) message.obj;
        obj = this.f10822a.f10828e;
        synchronized (obj) {
            try {
                zadaVar = this.f10822a.f10825b;
                zada zadaVar2 = (zada) Preconditions.i(zadaVar);
                if (pendingResult == null) {
                    zadaVar2.k(new Status(13, "Transform returned null"));
                } else if (pendingResult instanceof zacp) {
                    zadaVar2.k(((zacp) pendingResult).f());
                } else {
                    zadaVar2.j(pendingResult);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
