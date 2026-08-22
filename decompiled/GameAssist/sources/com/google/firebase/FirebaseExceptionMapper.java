package com.google.firebase;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

@KeepForSdk
/* loaded from: classes.dex */
public class FirebaseExceptionMapper implements StatusExceptionMapper {
    @Override // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public final Exception a(Status status) {
        return status.P() == 8 ? new FirebaseException(status.e0()) : new FirebaseApiNotAvailableException(status.e0());
    }
}
