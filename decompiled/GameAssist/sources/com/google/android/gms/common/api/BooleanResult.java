package com.google.android.gms.common.api;

/* loaded from: classes.dex */
public class BooleanResult implements Result {

    /* renamed from: c, reason: collision with root package name */
    private final Status f10519c;

    /* renamed from: h, reason: collision with root package name */
    private final boolean f10520h;

    @Override // com.google.android.gms.common.api.Result
    public Status a() {
        return this.f10519c;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.f10519c.equals(booleanResult.f10519c) && this.f10520h == booleanResult.f10520h;
    }

    public final int hashCode() {
        return ((this.f10519c.hashCode() + 527) * 31) + (this.f10520h ? 1 : 0);
    }
}
