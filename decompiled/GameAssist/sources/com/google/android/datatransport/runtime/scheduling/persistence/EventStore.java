package com.google.android.datatransport.runtime.scheduling.persistence;

import androidx.annotation.WorkerThread;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import java.io.Closeable;

@WorkerThread
/* loaded from: classes.dex */
public interface EventStore extends Closeable {
    long K(TransportContext transportContext);

    boolean M(TransportContext transportContext);

    void N(Iterable iterable);

    Iterable S(TransportContext transportContext);

    PersistedEvent c0(TransportContext transportContext, EventInternal eventInternal);

    int f();

    void g(Iterable iterable);

    void l(TransportContext transportContext, long j2);

    Iterable o();
}
