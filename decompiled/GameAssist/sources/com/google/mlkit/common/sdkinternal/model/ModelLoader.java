package com.google.mlkit.common.sdkinternal.model;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.GmsLogger;

@KeepForSdk
@WorkerThread
/* loaded from: classes.dex */
public class ModelLoader {

    /* renamed from: a, reason: collision with root package name */
    private static final GmsLogger f15987a = new GmsLogger("ModelLoader", "");

    @Nullable
    @KeepForSdk
    @VisibleForTesting
    public final RemoteModelLoader remoteModelLoader;

    @KeepForSdk
    public interface ModelContentHandler {
    }

    @KeepForSdk
    public interface ModelLoadingLogger {
    }

    @KeepForSdk
    protected enum ModelLoadingState {
        NO_MODEL_LOADED,
        REMOTE_MODEL_LOADED,
        LOCAL_MODEL_LOADED
    }
}
