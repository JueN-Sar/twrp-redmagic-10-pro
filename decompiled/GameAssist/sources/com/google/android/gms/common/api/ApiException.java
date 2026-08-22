package com.google.android.gms.common.api;

import androidx.annotation.NonNull;

/* loaded from: classes.dex */
public class ApiException extends Exception {

    @NonNull
    @Deprecated
    protected final Status mStatus;

    public ApiException(@NonNull Status status) {
        super(status.P() + ": " + (status.R() != null ? status.R() : ""));
        this.mStatus = status;
    }

    public Status a() {
        return this.mStatus;
    }
}
