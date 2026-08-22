package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class DataHolderResult implements Result, Releasable {

    /* renamed from: c, reason: collision with root package name */
    protected final Status f10582c;

    /* renamed from: h, reason: collision with root package name */
    protected final DataHolder f10583h;

    @Override // com.google.android.gms.common.api.Result
    public Status a() {
        return this.f10582c;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
        DataHolder dataHolder = this.f10583h;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }
}
