package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.mlkit.common.MlKitException;
import com.zte.distbus.basetransfer.Status;

@SafeParcelable.Class
/* loaded from: classes.dex */
public final class ConnectionResult extends AbstractSafeParcelable {

    /* renamed from: c, reason: collision with root package name */
    final int f10485c;

    /* renamed from: h, reason: collision with root package name */
    private final int f10486h;

    /* renamed from: i, reason: collision with root package name */
    private final PendingIntent f10487i;

    /* renamed from: j, reason: collision with root package name */
    private final String f10488j;

    /* renamed from: k, reason: collision with root package name */
    public static final ConnectionResult f10484k = new ConnectionResult(0);

    @NonNull
    public static final Parcelable.Creator<ConnectionResult> CREATOR = new zzb();

    ConnectionResult(int i2, int i3, PendingIntent pendingIntent, String str) {
        this.f10485c = i2;
        this.f10486h = i3;
        this.f10487i = pendingIntent;
        this.f10488j = str;
    }

    static String Y(int i2) {
        if (i2 == 99) {
            return "UNFINISHED";
        }
        if (i2 == 1500) {
            return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
        }
        switch (i2) {
            case -1:
                return "UNKNOWN";
            case 0:
                return "SUCCESS";
            case 1:
                return "SERVICE_MISSING";
            case 2:
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            case 3:
                return "SERVICE_DISABLED";
            case 4:
                return "SIGN_IN_REQUIRED";
            case 5:
                return "INVALID_ACCOUNT";
            case 6:
                return "RESOLUTION_REQUIRED";
            case 7:
                return "NETWORK_ERROR";
            case 8:
                return "INTERNAL_ERROR";
            case 9:
                return "SERVICE_INVALID";
            case 10:
                return "DEVELOPER_ERROR";
            case 11:
                return "LICENSE_CHECK_FAILED";
            default:
                switch (i2) {
                    case 13:
                        return "CANCELED";
                    case 14:
                        return "TIMEOUT";
                    case 15:
                        return "INTERRUPTED";
                    case 16:
                        return "API_UNAVAILABLE";
                    case MlKitException.NETWORK_ISSUE /* 17 */:
                        return "SIGN_IN_FAILED";
                    case MlKitException.UNSUPPORTED /* 18 */:
                        return "SERVICE_UPDATING";
                    case 19:
                        return "SERVICE_MISSING_PERMISSION";
                    case 20:
                        return "RESTRICTED_PROFILE";
                    case Status.ERROR_STREAM_REMOTE_FAILED /* 21 */:
                        return "API_VERSION_UPDATE_REQUIRED";
                    case 22:
                        return "RESOLUTION_ACTIVITY_NOT_FOUND";
                    case 23:
                        return "API_DISABLED";
                    case 24:
                        return "API_DISABLED_FOR_CONNECTION";
                    default:
                        return "UNKNOWN_ERROR_CODE(" + i2 + ")";
                }
        }
    }

    public int G() {
        return this.f10486h;
    }

    public String P() {
        return this.f10488j;
    }

    public PendingIntent R() {
        return this.f10487i;
    }

    public boolean T() {
        return (this.f10486h == 0 || this.f10487i == null) ? false : true;
    }

    public boolean W() {
        return this.f10486h == 0;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConnectionResult)) {
            return false;
        }
        ConnectionResult connectionResult = (ConnectionResult) obj;
        return this.f10486h == connectionResult.f10486h && Objects.a(this.f10487i, connectionResult.f10487i) && Objects.a(this.f10488j, connectionResult.f10488j);
    }

    public int hashCode() {
        return Objects.b(Integer.valueOf(this.f10486h), this.f10487i, this.f10488j);
    }

    public String toString() {
        Objects.ToStringHelper c2 = Objects.c(this);
        c2.a("statusCode", Y(this.f10486h));
        c2.a("resolution", this.f10487i);
        c2.a("message", this.f10488j);
        return c2.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f10485c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.g(parcel, 2, G());
        SafeParcelWriter.l(parcel, 3, R(), i2, false);
        SafeParcelWriter.m(parcel, 4, P(), false);
        SafeParcelWriter.b(parcel, a2);
    }

    public ConnectionResult(int i2) {
        this(i2, null, null);
    }

    public ConnectionResult(int i2, PendingIntent pendingIntent) {
        this(i2, pendingIntent, null);
    }

    public ConnectionResult(int i2, PendingIntent pendingIntent, String str) {
        this(1, i2, pendingIntent, str);
    }
}
