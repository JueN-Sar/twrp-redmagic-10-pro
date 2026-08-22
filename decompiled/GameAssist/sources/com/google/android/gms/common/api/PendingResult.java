package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Result;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public abstract class PendingResult<R extends Result> {

    @KeepForSdk
    public interface StatusListener {
        void a(Status status);
    }

    public void b(StatusListener statusListener) {
        throw new UnsupportedOperationException();
    }

    public abstract Result c(long j2, TimeUnit timeUnit);

    public abstract void d();

    public abstract void e(ResultCallback resultCallback);
}
