package cn.nubia.screensaver.bean;

import cn.nubia.gameassist.view.NubiaTextClock;

/* loaded from: classes.dex */
public class MomentBean {

    /* renamed from: a, reason: collision with root package name */
    private String f8984a;

    /* renamed from: b, reason: collision with root package name */
    private int f8985b;

    /* renamed from: c, reason: collision with root package name */
    private long f8986c;

    /* renamed from: e, reason: collision with root package name */
    private String f8988e;

    /* renamed from: f, reason: collision with root package name */
    private boolean f8989f;

    /* renamed from: d, reason: collision with root package name */
    private String f8987d = this.f8987d;

    /* renamed from: d, reason: collision with root package name */
    private String f8987d = this.f8987d;

    public MomentBean(String str, int i2, String str2, long j2, boolean z) {
        this.f8984a = str;
        this.f8985b = i2;
        this.f8988e = str2;
        this.f8986c = j2;
        this.f8989f = z;
    }

    public String a() {
        return this.f8988e;
    }

    public String b() {
        return this.f8984a;
    }

    public long c() {
        return this.f8986c;
    }

    public boolean d() {
        return this.f8989f;
    }

    public String toString() {
        return "MomentBean{path='" + this.f8984a + NubiaTextClock.QUOTE + ", isPreview=" + this.f8985b + ", time=" + this.f8986c + ", packageName='" + this.f8987d + NubiaTextClock.QUOTE + ", appName='" + this.f8988e + NubiaTextClock.QUOTE + ", video=" + this.f8989f + '}';
    }
}
