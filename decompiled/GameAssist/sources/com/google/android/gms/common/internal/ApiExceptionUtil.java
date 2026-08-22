package com.google.android.gms.common.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;

@KeepForSdk
/* loaded from: classes.dex */
public class ApiExceptionUtil {
    public static ApiException a(Status status) {
        return status.T() ? new ResolvableApiException(status) : new ApiException(status);
    }
}
