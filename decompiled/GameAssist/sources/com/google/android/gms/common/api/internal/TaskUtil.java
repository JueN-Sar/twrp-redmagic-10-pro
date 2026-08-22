package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

@KeepForSdk
/* loaded from: classes.dex */
public class TaskUtil {
    public static boolean a(Status status, Object obj, TaskCompletionSource taskCompletionSource) {
        return status.Y() ? taskCompletionSource.e(obj) : taskCompletionSource.d(ApiExceptionUtil.a(status));
    }
}
