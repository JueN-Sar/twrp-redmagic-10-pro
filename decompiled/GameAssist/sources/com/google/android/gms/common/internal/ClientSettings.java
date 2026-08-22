package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.view.View;
import androidx.collection.ArraySet;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.signin.SignInOptions;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@KeepForSdk
/* loaded from: classes.dex */
public final class ClientSettings {

    /* renamed from: a, reason: collision with root package name */
    private final Account f10967a;

    /* renamed from: b, reason: collision with root package name */
    private final Set f10968b;

    /* renamed from: c, reason: collision with root package name */
    private final Set f10969c;

    /* renamed from: d, reason: collision with root package name */
    private final Map f10970d;

    /* renamed from: e, reason: collision with root package name */
    private final int f10971e;

    /* renamed from: f, reason: collision with root package name */
    private final View f10972f;

    /* renamed from: g, reason: collision with root package name */
    private final String f10973g;

    /* renamed from: h, reason: collision with root package name */
    private final String f10974h;

    /* renamed from: i, reason: collision with root package name */
    private final SignInOptions f10975i;

    /* renamed from: j, reason: collision with root package name */
    private Integer f10976j;

    @KeepForSdk
    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private Account f10977a;

        /* renamed from: b, reason: collision with root package name */
        private ArraySet f10978b;

        /* renamed from: c, reason: collision with root package name */
        private String f10979c;

        /* renamed from: d, reason: collision with root package name */
        private String f10980d;

        /* renamed from: e, reason: collision with root package name */
        private final SignInOptions f10981e = SignInOptions.f13640p;

        public ClientSettings a() {
            return new ClientSettings(this.f10977a, this.f10978b, null, 0, null, this.f10979c, this.f10980d, this.f10981e, false);
        }

        public Builder b(String str) {
            this.f10979c = str;
            return this;
        }

        public final Builder c(Collection collection) {
            if (this.f10978b == null) {
                this.f10978b = new ArraySet();
            }
            this.f10978b.addAll(collection);
            return this;
        }

        public final Builder d(Account account) {
            this.f10977a = account;
            return this;
        }

        public final Builder e(String str) {
            this.f10980d = str;
            return this;
        }
    }

    public ClientSettings(Account account, Set set, Map map, int i2, View view, String str, String str2, SignInOptions signInOptions, boolean z) {
        this.f10967a = account;
        Set emptySet = set == null ? Collections.emptySet() : Collections.unmodifiableSet(set);
        this.f10968b = emptySet;
        map = map == null ? Collections.emptyMap() : map;
        this.f10970d = map;
        this.f10972f = view;
        this.f10971e = i2;
        this.f10973g = str;
        this.f10974h = str2;
        this.f10975i = signInOptions == null ? SignInOptions.f13640p : signInOptions;
        HashSet hashSet = new HashSet(emptySet);
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            hashSet.addAll(((zab) it.next()).f11048a);
        }
        this.f10969c = Collections.unmodifiableSet(hashSet);
    }

    public Account a() {
        return this.f10967a;
    }

    public Account b() {
        Account account = this.f10967a;
        return account != null ? account : new Account("<<default account>>", "com.google");
    }

    public Set c() {
        return this.f10969c;
    }

    public String d() {
        return this.f10973g;
    }

    public Set e() {
        return this.f10968b;
    }

    public final SignInOptions f() {
        return this.f10975i;
    }

    public final Integer g() {
        return this.f10976j;
    }

    public final String h() {
        return this.f10974h;
    }

    public final Map i() {
        return this.f10970d;
    }

    public final void j(Integer num) {
        this.f10976j = num;
    }
}
