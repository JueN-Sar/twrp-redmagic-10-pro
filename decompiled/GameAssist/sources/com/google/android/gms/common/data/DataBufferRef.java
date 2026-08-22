package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

@KeepForSdk
/* loaded from: classes.dex */
public abstract class DataBufferRef {

    /* renamed from: a, reason: collision with root package name */
    protected final DataHolder f10895a;

    /* renamed from: b, reason: collision with root package name */
    protected int f10896b;

    /* renamed from: c, reason: collision with root package name */
    private int f10897c;

    protected final void a(int i2) {
        boolean z = false;
        if (i2 >= 0 && i2 < this.f10895a.getCount()) {
            z = true;
        }
        Preconditions.l(z);
        this.f10896b = i2;
        this.f10897c = this.f10895a.W(i2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof DataBufferRef) {
            DataBufferRef dataBufferRef = (DataBufferRef) obj;
            if (Objects.a(Integer.valueOf(dataBufferRef.f10896b), Integer.valueOf(this.f10896b)) && Objects.a(Integer.valueOf(dataBufferRef.f10897c), Integer.valueOf(this.f10897c)) && dataBufferRef.f10895a == this.f10895a) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.b(Integer.valueOf(this.f10896b), Integer.valueOf(this.f10897c), this.f10895a);
    }
}
