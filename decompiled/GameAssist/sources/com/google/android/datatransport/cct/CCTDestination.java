package com.google.android.datatransport.cct;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.runtime.EncodedDestination;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public final class CCTDestination implements EncodedDestination {

    /* renamed from: c, reason: collision with root package name */
    static final String f10056c;

    /* renamed from: d, reason: collision with root package name */
    static final String f10057d;

    /* renamed from: e, reason: collision with root package name */
    private static final String f10058e;

    /* renamed from: f, reason: collision with root package name */
    private static final Set f10059f;

    /* renamed from: g, reason: collision with root package name */
    public static final CCTDestination f10060g;

    /* renamed from: h, reason: collision with root package name */
    public static final CCTDestination f10061h;

    /* renamed from: a, reason: collision with root package name */
    private final String f10062a;

    /* renamed from: b, reason: collision with root package name */
    private final String f10063b;

    static {
        String a2 = StringMerger.a("hts/frbslgiggolai.o/0clgbthfra=snpoo", "tp:/ieaeogn.ogepscmvc/o/ac?omtjo_rt3");
        f10056c = a2;
        String a3 = StringMerger.a("hts/frbslgigp.ogepscmv/ieo/eaybtho", "tp:/ieaeogn-agolai.o/1frlglgc/aclg");
        f10057d = a3;
        String a4 = StringMerger.a("AzSCki82AwsLzKd5O8zo", "IayckHiZRO1EFl1aGoK");
        f10058e = a4;
        f10059f = Collections.unmodifiableSet(new HashSet(Arrays.asList(Encoding.b("proto"), Encoding.b("json"))));
        f10060g = new CCTDestination(a2, null);
        f10061h = new CCTDestination(a3, a4);
    }

    public CCTDestination(String str, String str2) {
        this.f10062a = str;
        this.f10063b = str2;
    }

    public static CCTDestination c(byte[] bArr) {
        String str = new String(bArr, Charset.forName("UTF-8"));
        if (!str.startsWith("1$")) {
            throw new IllegalArgumentException("Version marker missing from extras");
        }
        String[] split = str.substring(2).split(Pattern.quote("\\"), 2);
        if (split.length != 2) {
            throw new IllegalArgumentException("Extra is not a valid encoded LegacyFlgDestination");
        }
        String str2 = split[0];
        if (str2.isEmpty()) {
            throw new IllegalArgumentException("Missing endpoint in CCTDestination extras");
        }
        String str3 = split[1];
        if (str3.isEmpty()) {
            str3 = null;
        }
        return new CCTDestination(str2, str3);
    }

    @Override // com.google.android.datatransport.runtime.EncodedDestination
    public Set a() {
        return f10059f;
    }

    public byte[] b() {
        String str = this.f10063b;
        if (str == null && this.f10062a == null) {
            return null;
        }
        String str2 = this.f10062a;
        if (str == null) {
            str = "";
        }
        return String.format("%s%s%s%s", "1$", str2, "\\", str).getBytes(Charset.forName("UTF-8"));
    }

    public String d() {
        return this.f10063b;
    }

    public String e() {
        return this.f10062a;
    }

    @Override // com.google.android.datatransport.runtime.Destination
    public byte[] getExtras() {
        return b();
    }

    @Override // com.google.android.datatransport.runtime.Destination
    public String getName() {
        return "cct";
    }
}
