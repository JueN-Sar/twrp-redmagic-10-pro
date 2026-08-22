package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.dagger.Component;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import java.io.Closeable;
import javax.inject.Singleton;

@Singleton
@Component
/* loaded from: classes.dex */
abstract class TransportRuntimeComponent implements Closeable {

    @Component.Builder
    interface Builder {
        Builder a(Context context);

        TransportRuntimeComponent build();
    }

    TransportRuntimeComponent() {
    }

    abstract EventStore a();

    abstract TransportRuntime c();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        a().close();
    }
}
