package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {

    /* renamed from: c, reason: collision with root package name */
    private final int f10550c;

    /* renamed from: h, reason: collision with root package name */
    private final String f10551h;

    /* renamed from: i, reason: collision with root package name */
    private final PendingIntent f10552i;

    /* renamed from: j, reason: collision with root package name */
    private final ConnectionResult f10553j;

    /* renamed from: k, reason: collision with root package name */
    public static final Status f10542k = new Status(-1);

    /* renamed from: l, reason: collision with root package name */
    public static final Status f10543l = new Status(0);

    /* renamed from: m, reason: collision with root package name */
    public static final Status f10544m = new Status(14);

    /* renamed from: n, reason: collision with root package name */
    public static final Status f10545n = new Status(8);

    /* renamed from: o, reason: collision with root package name */
    public static final Status f10546o = new Status(15);

    /* renamed from: p, reason: collision with root package name */
    public static final Status f10547p = new Status(16);

    /* renamed from: r, reason: collision with root package name */
    public static final Status f10549r = new Status(17);

    /* renamed from: q, reason: collision with root package name */
    public static final Status f10548q = new Status(18);

    @NonNull
    public static final Parcelable.Creator<Status> CREATOR = new zzb();

    Status(int i2, String str, PendingIntent pendingIntent, ConnectionResult connectionResult) {
        this.f10550c = i2;
        this.f10551h = str;
        this.f10552i = pendingIntent;
        this.f10553j = connectionResult;
    }

    public ConnectionResult G() {
        return this.f10553j;
    }

    public int P() {
        return this.f10550c;
    }

    public String R() {
        return this.f10551h;
    }

    public boolean T() {
        return this.f10552i != null;
    }

    public boolean W() {
        return this.f10550c == 16;
    }

    public boolean Y() {
        return this.f10550c <= 0;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status a() {
        return this;
    }

    public void a0(Activity activity, int i2) {
        if (T()) {
            PendingIntent pendingIntent = this.f10552i;
            Preconditions.i(pendingIntent);
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), i2, null, 0, 0, 0);
        }
    }

    public final String e0() {
        String str = this.f10551h;
        return str != null ? str : CommonStatusCodes.a(this.f10550c);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.f10550c == status.f10550c && Objects.a(this.f10551h, status.f10551h) && Objects.a(this.f10552i, status.f10552i) && Objects.a(this.f10553j, status.f10553j);
    }

    public int hashCode() {
        return Objects.b(Integer.valueOf(this.f10550c), this.f10551h, this.f10552i, this.f10553j);
    }

    public String toString() {
        Objects.ToStringHelper c2 = Objects.c(this);
        c2.a("statusCode", e0());
        c2.a("resolution", this.f10552i);
        return c2.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, P());
        SafeParcelWriter.m(parcel, 2, R(), false);
        SafeParcelWriter.l(parcel, 3, this.f10552i, i2, false);
        SafeParcelWriter.l(parcel, 4, G(), i2, false);
        SafeParcelWriter.b(parcel, a2);
    }

    public Status(int i2) {
        this(i2, (String) null);
    }

    public Status(ConnectionResult connectionResult, String str) {
        this(connectionResult, str, 17);
    }

    public Status(int i2, String str) {
        this(i2, str, (PendingIntent) null);
    }

    public Status(ConnectionResult connectionResult, String str, int i2) {
        this(i2, str, connectionResult.R(), connectionResult);
    }

    public Status(int i2, String str, PendingIntent pendingIntent) {
        this(i2, str, pendingIntent, null);
    }
}
