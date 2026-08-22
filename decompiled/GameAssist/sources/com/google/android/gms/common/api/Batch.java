package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.BasePendingResult;

/* loaded from: classes.dex */
public final class Batch extends BasePendingResult<BatchResult> {

    /* renamed from: q, reason: collision with root package name */
    private int f10514q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f10515r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f10516s;
    private final PendingResult[] t;
    private final Object u;

    public static final class Builder {
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult, com.google.android.gms.common.api.PendingResult
    public void d() {
        super.d();
        int i2 = 0;
        while (true) {
            PendingResult[] pendingResultArr = this.t;
            if (i2 >= pendingResultArr.length) {
                return;
            }
            pendingResultArr[i2].d();
            i2++;
        }
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    /* renamed from: r, reason: merged with bridge method [inline-methods] */
    public BatchResult f(Status status) {
        return new BatchResult(status, this.t);
    }
}
