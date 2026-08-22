package com.google.android.datatransport.cct.internal;

import com.google.android.datatransport.cct.internal.AndroidClientInfo;

/* loaded from: classes.dex */
final class AutoValue_AndroidClientInfo extends AndroidClientInfo {

    /* renamed from: a, reason: collision with root package name */
    private final Integer f10117a;

    /* renamed from: b, reason: collision with root package name */
    private final String f10118b;

    /* renamed from: c, reason: collision with root package name */
    private final String f10119c;

    /* renamed from: d, reason: collision with root package name */
    private final String f10120d;

    /* renamed from: e, reason: collision with root package name */
    private final String f10121e;

    /* renamed from: f, reason: collision with root package name */
    private final String f10122f;

    /* renamed from: g, reason: collision with root package name */
    private final String f10123g;

    /* renamed from: h, reason: collision with root package name */
    private final String f10124h;

    /* renamed from: i, reason: collision with root package name */
    private final String f10125i;

    /* renamed from: j, reason: collision with root package name */
    private final String f10126j;

    /* renamed from: k, reason: collision with root package name */
    private final String f10127k;

    /* renamed from: l, reason: collision with root package name */
    private final String f10128l;

    static final class Builder extends AndroidClientInfo.Builder {

        /* renamed from: a, reason: collision with root package name */
        private Integer f10129a;

        /* renamed from: b, reason: collision with root package name */
        private String f10130b;

        /* renamed from: c, reason: collision with root package name */
        private String f10131c;

        /* renamed from: d, reason: collision with root package name */
        private String f10132d;

        /* renamed from: e, reason: collision with root package name */
        private String f10133e;

        /* renamed from: f, reason: collision with root package name */
        private String f10134f;

        /* renamed from: g, reason: collision with root package name */
        private String f10135g;

        /* renamed from: h, reason: collision with root package name */
        private String f10136h;

        /* renamed from: i, reason: collision with root package name */
        private String f10137i;

        /* renamed from: j, reason: collision with root package name */
        private String f10138j;

        /* renamed from: k, reason: collision with root package name */
        private String f10139k;

        /* renamed from: l, reason: collision with root package name */
        private String f10140l;

        Builder() {
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo a() {
            return new AutoValue_AndroidClientInfo(this.f10129a, this.f10130b, this.f10131c, this.f10132d, this.f10133e, this.f10134f, this.f10135g, this.f10136h, this.f10137i, this.f10138j, this.f10139k, this.f10140l);
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder b(String str) {
            this.f10140l = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder c(String str) {
            this.f10138j = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder d(String str) {
            this.f10132d = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder e(String str) {
            this.f10136h = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder f(String str) {
            this.f10131c = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder g(String str) {
            this.f10137i = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder h(String str) {
            this.f10135g = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder i(String str) {
            this.f10139k = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder j(String str) {
            this.f10130b = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder k(String str) {
            this.f10134f = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder l(String str) {
            this.f10133e = str;
            return this;
        }

        @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder
        public AndroidClientInfo.Builder m(Integer num) {
            this.f10129a = num;
            return this;
        }
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String b() {
        return this.f10128l;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String c() {
        return this.f10126j;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String d() {
        return this.f10120d;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String e() {
        return this.f10124h;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AndroidClientInfo)) {
            return false;
        }
        AndroidClientInfo androidClientInfo = (AndroidClientInfo) obj;
        Integer num = this.f10117a;
        if (num != null ? num.equals(androidClientInfo.m()) : androidClientInfo.m() == null) {
            String str = this.f10118b;
            if (str != null ? str.equals(androidClientInfo.j()) : androidClientInfo.j() == null) {
                String str2 = this.f10119c;
                if (str2 != null ? str2.equals(androidClientInfo.f()) : androidClientInfo.f() == null) {
                    String str3 = this.f10120d;
                    if (str3 != null ? str3.equals(androidClientInfo.d()) : androidClientInfo.d() == null) {
                        String str4 = this.f10121e;
                        if (str4 != null ? str4.equals(androidClientInfo.l()) : androidClientInfo.l() == null) {
                            String str5 = this.f10122f;
                            if (str5 != null ? str5.equals(androidClientInfo.k()) : androidClientInfo.k() == null) {
                                String str6 = this.f10123g;
                                if (str6 != null ? str6.equals(androidClientInfo.h()) : androidClientInfo.h() == null) {
                                    String str7 = this.f10124h;
                                    if (str7 != null ? str7.equals(androidClientInfo.e()) : androidClientInfo.e() == null) {
                                        String str8 = this.f10125i;
                                        if (str8 != null ? str8.equals(androidClientInfo.g()) : androidClientInfo.g() == null) {
                                            String str9 = this.f10126j;
                                            if (str9 != null ? str9.equals(androidClientInfo.c()) : androidClientInfo.c() == null) {
                                                String str10 = this.f10127k;
                                                if (str10 != null ? str10.equals(androidClientInfo.i()) : androidClientInfo.i() == null) {
                                                    String str11 = this.f10128l;
                                                    if (str11 == null) {
                                                        if (androidClientInfo.b() == null) {
                                                            return true;
                                                        }
                                                    } else if (str11.equals(androidClientInfo.b())) {
                                                        return true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String f() {
        return this.f10119c;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String g() {
        return this.f10125i;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String h() {
        return this.f10123g;
    }

    public int hashCode() {
        Integer num = this.f10117a;
        int hashCode = ((num == null ? 0 : num.hashCode()) ^ 1000003) * 1000003;
        String str = this.f10118b;
        int hashCode2 = (hashCode ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f10119c;
        int hashCode3 = (hashCode2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.f10120d;
        int hashCode4 = (hashCode3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        String str4 = this.f10121e;
        int hashCode5 = (hashCode4 ^ (str4 == null ? 0 : str4.hashCode())) * 1000003;
        String str5 = this.f10122f;
        int hashCode6 = (hashCode5 ^ (str5 == null ? 0 : str5.hashCode())) * 1000003;
        String str6 = this.f10123g;
        int hashCode7 = (hashCode6 ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
        String str7 = this.f10124h;
        int hashCode8 = (hashCode7 ^ (str7 == null ? 0 : str7.hashCode())) * 1000003;
        String str8 = this.f10125i;
        int hashCode9 = (hashCode8 ^ (str8 == null ? 0 : str8.hashCode())) * 1000003;
        String str9 = this.f10126j;
        int hashCode10 = (hashCode9 ^ (str9 == null ? 0 : str9.hashCode())) * 1000003;
        String str10 = this.f10127k;
        int hashCode11 = (hashCode10 ^ (str10 == null ? 0 : str10.hashCode())) * 1000003;
        String str11 = this.f10128l;
        return hashCode11 ^ (str11 != null ? str11.hashCode() : 0);
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String i() {
        return this.f10127k;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String j() {
        return this.f10118b;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String k() {
        return this.f10122f;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String l() {
        return this.f10121e;
    }

    @Override // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public Integer m() {
        return this.f10117a;
    }

    public String toString() {
        return "AndroidClientInfo{sdkVersion=" + this.f10117a + ", model=" + this.f10118b + ", hardware=" + this.f10119c + ", device=" + this.f10120d + ", product=" + this.f10121e + ", osBuild=" + this.f10122f + ", manufacturer=" + this.f10123g + ", fingerprint=" + this.f10124h + ", locale=" + this.f10125i + ", country=" + this.f10126j + ", mccMnc=" + this.f10127k + ", applicationBuild=" + this.f10128l + "}";
    }

    private AutoValue_AndroidClientInfo(Integer num, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        this.f10117a = num;
        this.f10118b = str;
        this.f10119c = str2;
        this.f10120d = str3;
        this.f10121e = str4;
        this.f10122f = str5;
        this.f10123g = str6;
        this.f10124h = str7;
        this.f10125i = str8;
        this.f10126j = str9;
        this.f10127k = str10;
        this.f10128l = str11;
    }
}
