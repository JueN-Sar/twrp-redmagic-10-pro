package com.google.android.gms.common.moduleinstall;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@SafeParcelable.Class
/* loaded from: classes.dex */
public class ModuleInstallStatusUpdate extends AbstractSafeParcelable {

    @NonNull
    public static final Parcelable.Creator<ModuleInstallStatusUpdate> CREATOR = new zae();

    /* renamed from: c, reason: collision with root package name */
    private final int f11147c;

    /* renamed from: h, reason: collision with root package name */
    private final int f11148h;

    /* renamed from: i, reason: collision with root package name */
    private final Long f11149i;

    /* renamed from: j, reason: collision with root package name */
    private final Long f11150j;

    /* renamed from: k, reason: collision with root package name */
    private final int f11151k;

    /* renamed from: l, reason: collision with root package name */
    private final ProgressInfo f11152l;

    @Retention(RetentionPolicy.CLASS)
    public @interface InstallState {
    }

    public static class ProgressInfo {

        /* renamed from: a, reason: collision with root package name */
        private final long f11153a;

        /* renamed from: b, reason: collision with root package name */
        private final long f11154b;

        ProgressInfo(long j2, long j3) {
            Preconditions.k(j3);
            this.f11153a = j2;
            this.f11154b = j3;
        }
    }

    public ModuleInstallStatusUpdate(int i2, int i3, Long l2, Long l3, int i4) {
        this.f11147c = i2;
        this.f11148h = i3;
        this.f11149i = l2;
        this.f11150j = l3;
        this.f11151k = i4;
        this.f11152l = (l2 == null || l3 == null || l3.longValue() == 0) ? null : new ProgressInfo(l2.longValue(), l3.longValue());
    }

    public int G() {
        return this.f11151k;
    }

    public int P() {
        return this.f11148h;
    }

    public int R() {
        return this.f11147c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, R());
        SafeParcelWriter.g(parcel, 2, P());
        SafeParcelWriter.j(parcel, 3, this.f11149i, false);
        SafeParcelWriter.j(parcel, 4, this.f11150j, false);
        SafeParcelWriter.g(parcel, 5, G());
        SafeParcelWriter.b(parcel, a2);
    }
}
