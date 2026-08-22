package cn.nubia.plugin.gameratio;

/* loaded from: classes.dex */
public class GameRatioData {

    /* renamed from: a, reason: collision with root package name */
    private String f8348a;

    /* renamed from: b, reason: collision with root package name */
    private int f8349b;

    /* renamed from: c, reason: collision with root package name */
    private int f8350c;

    public GameRatioData(String str) {
        this.f8348a = str;
    }

    public int a() {
        return this.f8349b;
    }

    public String b() {
        return this.f8348a;
    }

    public int c() {
        return this.f8350c;
    }

    public boolean d() {
        return this.f8349b == 0 && this.f8350c == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(20);
        sb.append("pkg:");
        sb.append(this.f8348a);
        sb.append(", ori:");
        sb.append(this.f8349b);
        sb.append(", size:");
        sb.append(this.f8350c);
        return sb.toString();
    }

    public GameRatioData(String str, int i2, int i3) {
        this.f8348a = str;
        this.f8349b = i2;
        this.f8350c = i3;
    }
}
