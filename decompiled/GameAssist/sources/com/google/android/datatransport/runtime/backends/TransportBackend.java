package com.google.android.datatransport.runtime.backends;

import com.google.android.datatransport.runtime.EventInternal;

/* loaded from: classes.dex */
public interface TransportBackend {
    EventInternal a(EventInternal eventInternal);

    BackendResponse b(BackendRequest backendRequest);
}
