package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

@KeepForSdk
@SafeParcelable.Class
/* loaded from: classes.dex */
public class TelemetryData extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<TelemetryData> CREATOR = new zaab();

    /* renamed from: c, reason: collision with root package name */
    private final int f11034c;

    /* renamed from: h, reason: collision with root package name */
    private List f11035h;

    public TelemetryData(int i2, List list) {
        this.f11034c = i2;
        this.f11035h = list;
    }

    public final int G() {
        return this.f11034c;
    }

    public final List P() {
        return this.f11035h;
    }

    public final void R(MethodInvocation methodInvocation) {
        if (this.f11035h == null) {
            this.f11035h = new ArrayList();
        }
        this.f11035h.add(methodInvocation);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f11034c);
        SafeParcelWriter.q(parcel, 2, this.f11035h, false);
        SafeParcelWriter.b(parcel, a2);
    }
}
