package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.zte.shared.wrapper.VirtualHandleWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

@SafeParcelable.Class
@Deprecated
/* loaded from: classes.dex */
public class GoogleSignInAccount extends AbstractSafeParcelable implements ReflectedParcelable {

    @NonNull
    public static final Parcelable.Creator<GoogleSignInAccount> CREATOR = new zab();

    @NonNull
    @VisibleForTesting
    public static final Clock zaa = DefaultClock.a();

    /* renamed from: c, reason: collision with root package name */
    final int f10439c;

    /* renamed from: h, reason: collision with root package name */
    private final String f10440h;

    /* renamed from: i, reason: collision with root package name */
    private final String f10441i;

    /* renamed from: j, reason: collision with root package name */
    private final String f10442j;

    /* renamed from: k, reason: collision with root package name */
    private final String f10443k;

    /* renamed from: l, reason: collision with root package name */
    private final Uri f10444l;

    /* renamed from: m, reason: collision with root package name */
    private String f10445m;

    /* renamed from: n, reason: collision with root package name */
    private final long f10446n;

    /* renamed from: o, reason: collision with root package name */
    private final String f10447o;

    /* renamed from: p, reason: collision with root package name */
    final List f10448p;

    /* renamed from: q, reason: collision with root package name */
    private final String f10449q;

    /* renamed from: r, reason: collision with root package name */
    private final String f10450r;

    /* renamed from: s, reason: collision with root package name */
    private final Set f10451s = new HashSet();

    GoogleSignInAccount(int i2, String str, String str2, String str3, String str4, Uri uri, String str5, long j2, String str6, List list, String str7, String str8) {
        this.f10439c = i2;
        this.f10440h = str;
        this.f10441i = str2;
        this.f10442j = str3;
        this.f10443k = str4;
        this.f10444l = uri;
        this.f10445m = str5;
        this.f10446n = j2;
        this.f10447o = str6;
        this.f10448p = list;
        this.f10449q = str7;
        this.f10450r = str8;
    }

    public static GoogleSignInAccount h0(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Long l2, String str7, Set set) {
        return new GoogleSignInAccount(3, str, str2, str3, str4, uri, null, l2.longValue(), Preconditions.f(str7), new ArrayList((Collection) Preconditions.i(set)), str5, str6);
    }

    public static GoogleSignInAccount j0(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("photoUrl");
        Uri parse = !TextUtils.isEmpty(optString) ? Uri.parse(optString) : null;
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        HashSet hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            hashSet.add(new Scope(jSONArray.getString(i2)));
        }
        GoogleSignInAccount h0 = h0(jSONObject.optString(VirtualHandleWrapper.KEY_ID), jSONObject.has("tokenId") ? jSONObject.optString("tokenId") : null, jSONObject.has("email") ? jSONObject.optString("email") : null, jSONObject.has("displayName") ? jSONObject.optString("displayName") : null, jSONObject.has("givenName") ? jSONObject.optString("givenName") : null, jSONObject.has("familyName") ? jSONObject.optString("familyName") : null, parse, Long.valueOf(parseLong), jSONObject.getString("obfuscatedIdentifier"), hashSet);
        h0.f10445m = jSONObject.has("serverAuthCode") ? jSONObject.optString("serverAuthCode") : null;
        return h0;
    }

    public String G() {
        return this.f10443k;
    }

    public String P() {
        return this.f10442j;
    }

    public String R() {
        return this.f10450r;
    }

    public String T() {
        return this.f10449q;
    }

    public String W() {
        return this.f10440h;
    }

    public String Y() {
        return this.f10441i;
    }

    public Uri a0() {
        return this.f10444l;
    }

    public Set e0() {
        HashSet hashSet = new HashSet(this.f10448p);
        hashSet.addAll(this.f10451s);
        return hashSet;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GoogleSignInAccount)) {
            return false;
        }
        GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount) obj;
        return googleSignInAccount.f10447o.equals(this.f10447o) && googleSignInAccount.e0().equals(e0());
    }

    public String f0() {
        return this.f10445m;
    }

    public int hashCode() {
        return ((this.f10447o.hashCode() + 527) * 31) + e0().hashCode();
    }

    public Account k() {
        String str = this.f10442j;
        if (str == null) {
            return null;
        }
        return new Account(str, "com.google");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, this.f10439c);
        SafeParcelWriter.m(parcel, 2, W(), false);
        SafeParcelWriter.m(parcel, 3, Y(), false);
        SafeParcelWriter.m(parcel, 4, P(), false);
        SafeParcelWriter.m(parcel, 5, G(), false);
        SafeParcelWriter.l(parcel, 6, a0(), i2, false);
        SafeParcelWriter.m(parcel, 7, f0(), false);
        SafeParcelWriter.i(parcel, 8, this.f10446n);
        SafeParcelWriter.m(parcel, 9, this.f10447o, false);
        SafeParcelWriter.q(parcel, 10, this.f10448p, false);
        SafeParcelWriter.m(parcel, 11, T(), false);
        SafeParcelWriter.m(parcel, 12, R(), false);
        SafeParcelWriter.b(parcel, a2);
    }
}
