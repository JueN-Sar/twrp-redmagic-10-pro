package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

@KeepForSdk
@SafeParcelable.Class
@Deprecated
/* loaded from: classes.dex */
public final class WakeLockEvent extends StatsEvent {

    @NonNull
    public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zza();

    /* renamed from: c, reason: collision with root package name */
    final int f11240c;

    /* renamed from: h, reason: collision with root package name */
    private final long f11241h;

    /* renamed from: i, reason: collision with root package name */
    private final int f11242i;

    /* renamed from: j, reason: collision with root package name */
    private final String f11243j;

    /* renamed from: k, reason: collision with root package name */
    private final String f11244k;

    /* renamed from: l, reason: collision with root package name */
    private final String f11245l;

    /* renamed from: m, reason: collision with root package name */
    private final int f11246m;

    /* renamed from: n, reason: collision with root package name */
    private final List f11247n;

    /* renamed from: o, reason: collision with root package name */
    private final String f11248o;

    /* renamed from: p, reason: collision with root package name */
    private final long f11249p;

    /* renamed from: q, reason: collision with root package name */
    private final int f11250q;

    /* renamed from: r, reason: collision with root package name */
    private final String f11251r;

    /* renamed from: s, reason: collision with root package name */
    private final float f11252s;
    private final long t;
    private final boolean u;

    WakeLockEvent(int i2, long j2, int i3, String str, int i4, List list, String str2, long j3, int i5, String str3, String str4, float f2, long j4, String str5, boolean z) {
        this.f11240c = i2;
        this.f11241h = j2;
        this.f11242i = i3;
        this.f11243j = str;
        this.f11244k = str3;
        this.f11245l = str5;
        this.f11246m = i4;
        this.f11247n = list;
        this.f11248o = str2;
        this.f11249p = j3;
        this.f11250q = i5;
        this.f11251r = str4;
        this.f11252s = f2;
        this.t = j4;
        this.u = z;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final int G() {
        return this.f11242i;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final long P() {
        return this.f11241h;
    }

    @Override // com.google.android.gms.common.stats.StatsEvent
    public final String R() {
        List list = this.f11247n;
        String join = list == null ? "" : TextUtils.join(",", list);
        int i2 = this.f11250q;
        String str = this.f11244k;
        String str2 = this.f11251r;
        float f2 = this.f11252s;
        String str3 = this.f11245l;
        int i3 = this.f11246m;
        String str4 = this.f11243j;
        boolean z = this.u;
        StringBuilder sb = new StringBuilder();
        sb.append("\t");
        sb.append(str4);
        sb.append("\t");
        sb.append(i3);
        sb.append("\t");
        sb.append(join);
        sb.append("\t");
        sb.append(i2);
        sb.append("\t");
        if (str == null) {
            str = "";
        }
        sb.append(str);
        sb.append("\t");
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("\t");
        sb.append(f2);
        sb.append("\t");
        sb.append(str3 != null ? str3 : "");
        sb.append("\t");
        sb.append(z);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f11240c);
        SafeParcelWriter.i(parcel, 2, this.f11241h);
        SafeParcelWriter.m(parcel, 4, this.f11243j, false);
        SafeParcelWriter.g(parcel, 5, this.f11246m);
        SafeParcelWriter.o(parcel, 6, this.f11247n, false);
        SafeParcelWriter.i(parcel, 8, this.f11249p);
        SafeParcelWriter.m(parcel, 10, this.f11244k, false);
        SafeParcelWriter.g(parcel, 11, this.f11242i);
        SafeParcelWriter.m(parcel, 12, this.f11248o, false);
        SafeParcelWriter.m(parcel, 13, this.f11251r, false);
        SafeParcelWriter.g(parcel, 14, this.f11250q);
        SafeParcelWriter.e(parcel, 15, this.f11252s);
        SafeParcelWriter.i(parcel, 16, this.t);
        SafeParcelWriter.m(parcel, 17, this.f11245l, false);
        SafeParcelWriter.c(parcel, 18, this.u);
        SafeParcelWriter.b(parcel, a2);
    }
}
