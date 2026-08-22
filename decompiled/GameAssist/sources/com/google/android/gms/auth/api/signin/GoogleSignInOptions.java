package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.auth.api.signin.internal.GoogleSignInOptionsExtensionParcelable;
import com.google.android.gms.auth.api.signin.internal.HashAccumulator;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.zte.distbus.basetransfer.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SafeParcelable.Class
@Deprecated
/* loaded from: classes.dex */
public class GoogleSignInOptions extends AbstractSafeParcelable implements Api.ApiOptions.Optional, ReflectedParcelable {

    @NonNull
    public static final Parcelable.Creator<GoogleSignInOptions> CREATOR;

    /* renamed from: r, reason: collision with root package name */
    public static final GoogleSignInOptions f10452r;

    /* renamed from: s, reason: collision with root package name */
    public static final GoogleSignInOptions f10453s;
    private static final Comparator t;

    @NonNull
    @VisibleForTesting
    public static final Scope zaa = new Scope(Constants.EXTRA_PROFILE);

    @NonNull
    @VisibleForTesting
    public static final Scope zab = new Scope("email");

    @NonNull
    @VisibleForTesting
    public static final Scope zac = new Scope("openid");

    @NonNull
    @VisibleForTesting
    public static final Scope zad;

    @NonNull
    @VisibleForTesting
    public static final Scope zae;

    /* renamed from: c, reason: collision with root package name */
    final int f10454c;

    /* renamed from: h, reason: collision with root package name */
    private final ArrayList f10455h;

    /* renamed from: i, reason: collision with root package name */
    private Account f10456i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f10457j;

    /* renamed from: k, reason: collision with root package name */
    private final boolean f10458k;

    /* renamed from: l, reason: collision with root package name */
    private final boolean f10459l;

    /* renamed from: m, reason: collision with root package name */
    private String f10460m;

    /* renamed from: n, reason: collision with root package name */
    private String f10461n;

    /* renamed from: o, reason: collision with root package name */
    private ArrayList f10462o;

    /* renamed from: p, reason: collision with root package name */
    private String f10463p;

    /* renamed from: q, reason: collision with root package name */
    private Map f10464q;

    public static final class Builder {

        /* renamed from: b, reason: collision with root package name */
        private boolean f10466b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f10467c;

        /* renamed from: d, reason: collision with root package name */
        private boolean f10468d;

        /* renamed from: e, reason: collision with root package name */
        private String f10469e;

        /* renamed from: f, reason: collision with root package name */
        private Account f10470f;

        /* renamed from: g, reason: collision with root package name */
        private String f10471g;

        /* renamed from: i, reason: collision with root package name */
        private String f10473i;

        /* renamed from: a, reason: collision with root package name */
        private Set f10465a = new HashSet();

        /* renamed from: h, reason: collision with root package name */
        private Map f10472h = new HashMap();

        public GoogleSignInOptions a() {
            if (this.f10465a.contains(GoogleSignInOptions.zae)) {
                Set set = this.f10465a;
                Scope scope = GoogleSignInOptions.zad;
                if (set.contains(scope)) {
                    this.f10465a.remove(scope);
                }
            }
            if (this.f10468d && (this.f10470f == null || !this.f10465a.isEmpty())) {
                b();
            }
            return new GoogleSignInOptions(new ArrayList(this.f10465a), this.f10470f, this.f10468d, this.f10466b, this.f10467c, this.f10469e, this.f10471g, this.f10472h, this.f10473i);
        }

        public Builder b() {
            this.f10465a.add(GoogleSignInOptions.zac);
            return this;
        }

        public Builder c() {
            this.f10465a.add(GoogleSignInOptions.zaa);
            return this;
        }

        public Builder d(Scope scope, Scope... scopeArr) {
            this.f10465a.add(scope);
            this.f10465a.addAll(Arrays.asList(scopeArr));
            return this;
        }
    }

    static {
        Scope scope = new Scope("https://www.googleapis.com/auth/games_lite");
        zad = scope;
        zae = new Scope("https://www.googleapis.com/auth/games");
        Builder builder = new Builder();
        builder.b();
        builder.c();
        f10452r = builder.a();
        Builder builder2 = new Builder();
        builder2.d(scope, new Scope[0]);
        f10453s = builder2.a();
        CREATOR = new zae();
        t = new zac();
    }

    private static Map e0(List list) {
        HashMap hashMap = new HashMap();
        if (list != null) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                GoogleSignInOptionsExtensionParcelable googleSignInOptionsExtensionParcelable = (GoogleSignInOptionsExtensionParcelable) it.next();
                hashMap.put(Integer.valueOf(googleSignInOptionsExtensionParcelable.G()), googleSignInOptionsExtensionParcelable);
            }
        }
        return hashMap;
    }

    public ArrayList G() {
        return this.f10462o;
    }

    public String P() {
        return this.f10463p;
    }

    public ArrayList R() {
        return new ArrayList(this.f10455h);
    }

    public String T() {
        return this.f10460m;
    }

    public boolean W() {
        return this.f10459l;
    }

    public boolean Y() {
        return this.f10457j;
    }

    public boolean a0() {
        return this.f10458k;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x0048, code lost:
    
        if (r1.equals(r4.k()) != false) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean equals(java.lang.Object r4) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            com.google.android.gms.auth.api.signin.GoogleSignInOptions r4 = (com.google.android.gms.auth.api.signin.GoogleSignInOptions) r4     // Catch: java.lang.ClassCastException -> L90
            java.util.ArrayList r1 = r3.f10462o     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = r1.isEmpty()     // Catch: java.lang.ClassCastException -> L90
            if (r1 == 0) goto L90
            java.util.ArrayList r1 = r4.f10462o     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = r1.isEmpty()     // Catch: java.lang.ClassCastException -> L90
            if (r1 != 0) goto L18
            goto L90
        L18:
            java.util.ArrayList r1 = r3.f10455h     // Catch: java.lang.ClassCastException -> L90
            int r1 = r1.size()     // Catch: java.lang.ClassCastException -> L90
            java.util.ArrayList r2 = r4.R()     // Catch: java.lang.ClassCastException -> L90
            int r2 = r2.size()     // Catch: java.lang.ClassCastException -> L90
            if (r1 != r2) goto L90
            java.util.ArrayList r1 = r3.f10455h     // Catch: java.lang.ClassCastException -> L90
            java.util.ArrayList r2 = r4.R()     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = r1.containsAll(r2)     // Catch: java.lang.ClassCastException -> L90
            if (r1 != 0) goto L35
            goto L90
        L35:
            android.accounts.Account r1 = r3.f10456i     // Catch: java.lang.ClassCastException -> L90
            if (r1 != 0) goto L40
            android.accounts.Account r1 = r4.k()     // Catch: java.lang.ClassCastException -> L90
            if (r1 != 0) goto L90
            goto L4a
        L40:
            android.accounts.Account r2 = r4.k()     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = r1.equals(r2)     // Catch: java.lang.ClassCastException -> L90
            if (r1 == 0) goto L90
        L4a:
            java.lang.String r1 = r3.f10460m     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.ClassCastException -> L90
            if (r1 == 0) goto L5d
            java.lang.String r1 = r4.T()     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.ClassCastException -> L90
            if (r1 == 0) goto L90
            goto L6a
        L5d:
            java.lang.String r1 = r3.f10460m     // Catch: java.lang.ClassCastException -> L90
            java.lang.String r2 = r4.T()     // Catch: java.lang.ClassCastException -> L90
            boolean r1 = r1.equals(r2)     // Catch: java.lang.ClassCastException -> L90
            if (r1 != 0) goto L6a
            goto L90
        L6a:
            boolean r1 = r3.f10459l     // Catch: java.lang.ClassCastException -> L90
            boolean r2 = r4.W()     // Catch: java.lang.ClassCastException -> L90
            if (r1 != r2) goto L90
            boolean r1 = r3.f10457j     // Catch: java.lang.ClassCastException -> L90
            boolean r2 = r4.Y()     // Catch: java.lang.ClassCastException -> L90
            if (r1 != r2) goto L90
            boolean r1 = r3.f10458k     // Catch: java.lang.ClassCastException -> L90
            boolean r2 = r4.a0()     // Catch: java.lang.ClassCastException -> L90
            if (r1 != r2) goto L90
            java.lang.String r3 = r3.f10463p     // Catch: java.lang.ClassCastException -> L90
            java.lang.String r4 = r4.P()     // Catch: java.lang.ClassCastException -> L90
            boolean r3 = android.text.TextUtils.equals(r3, r4)     // Catch: java.lang.ClassCastException -> L90
            if (r3 == 0) goto L90
            r3 = 1
            return r3
        L90:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.auth.api.signin.GoogleSignInOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.f10455h;
        int size = arrayList2.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(((Scope) arrayList2.get(i2)).G());
        }
        Collections.sort(arrayList);
        HashAccumulator hashAccumulator = new HashAccumulator();
        hashAccumulator.a(arrayList);
        hashAccumulator.a(this.f10456i);
        hashAccumulator.a(this.f10460m);
        hashAccumulator.c(this.f10459l);
        hashAccumulator.c(this.f10457j);
        hashAccumulator.c(this.f10458k);
        hashAccumulator.a(this.f10463p);
        return hashAccumulator.b();
    }

    public Account k() {
        return this.f10456i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        int i3 = this.f10454c;
        int a2 = SafeParcelWriter.a(parcel);
        SafeParcelWriter.g(parcel, 1, i3);
        SafeParcelWriter.q(parcel, 2, R(), false);
        SafeParcelWriter.l(parcel, 3, k(), i2, false);
        SafeParcelWriter.c(parcel, 4, Y());
        SafeParcelWriter.c(parcel, 5, a0());
        SafeParcelWriter.c(parcel, 6, W());
        SafeParcelWriter.m(parcel, 7, T(), false);
        SafeParcelWriter.m(parcel, 8, this.f10461n, false);
        SafeParcelWriter.q(parcel, 9, G(), false);
        SafeParcelWriter.m(parcel, 10, P(), false);
        SafeParcelWriter.b(parcel, a2);
    }

    GoogleSignInOptions(int i2, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList arrayList2, String str3) {
        this(i2, arrayList, account, z, z2, z3, str, str2, e0(arrayList2), str3);
    }

    private GoogleSignInOptions(int i2, ArrayList arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map map, String str3) {
        this.f10454c = i2;
        this.f10455h = arrayList;
        this.f10456i = account;
        this.f10457j = z;
        this.f10458k = z2;
        this.f10459l = z3;
        this.f10460m = str;
        this.f10461n = str2;
        this.f10462o = new ArrayList(map.values());
        this.f10464q = map;
        this.f10463p = str3;
    }
}
