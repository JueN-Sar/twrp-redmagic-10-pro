package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.mlkit.common.sdkinternal.MLTaskInput;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class MLTask<T, S extends MLTaskInput> extends ModelResource {
    protected MLTask(TaskQueue taskQueue) {
        super(taskQueue);
    }

    public abstract Object h(MLTaskInput mLTaskInput);
}
