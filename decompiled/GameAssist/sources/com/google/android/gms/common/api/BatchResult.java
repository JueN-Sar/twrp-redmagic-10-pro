package com.google.android.gms.common.api;

/* loaded from: classes.dex */
public final class BatchResult implements Result {

    /* renamed from: c, reason: collision with root package name */
    private final Status f10517c;

    /* renamed from: h, reason: collision with root package name */
    private final PendingResult[] f10518h;

    BatchResult(Status status, PendingResult[] pendingResultArr) {
        this.f10517c = status;
        this.f10518h = pendingResultArr;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status a() {
        return this.f10517c;
    }
}
