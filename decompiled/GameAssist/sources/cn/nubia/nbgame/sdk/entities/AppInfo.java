package cn.nubia.nbgame.sdk.entities;

/* loaded from: classes.dex */
public class AppInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f8259a;

    /* renamed from: b, reason: collision with root package name */
    private String f8260b;

    /* renamed from: c, reason: collision with root package name */
    private int f8261c;

    /* renamed from: d, reason: collision with root package name */
    private int f8262d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f8263e;

    /* renamed from: f, reason: collision with root package name */
    private String f8264f;

    public int a() {
        return this.f8259a;
    }

    public String b() {
        return this.f8260b;
    }

    public int c() {
        return this.f8261c;
    }

    public int d() {
        return this.f8262d;
    }

    public String e() {
        return this.f8264f;
    }

    public boolean f() {
        return this.f8263e;
    }

    public void g(String str) {
        this.f8264f = str;
    }

    public String toString() {
        return "AppInfo [mAppId=" + this.f8259a + ", mAppKey=" + this.f8260b + ", mChannelId=" + this.f8261c + ", mOrientation=" + this.f8262d + ", mCanUseAdjunct=" + this.f8263e + "]";
    }
}
