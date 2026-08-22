package cn.nubia.magicwindow;

/* loaded from: classes.dex */
public class MagicWindowPosition {

    /* renamed from: a, reason: collision with root package name */
    private float f7875a;

    /* renamed from: b, reason: collision with root package name */
    private String f7876b;

    /* renamed from: c, reason: collision with root package name */
    private String f7877c;

    public MagicWindowPosition(String str, String str2, float f2) {
        this.f7877c = str;
        this.f7876b = str2;
        this.f7875a = f2;
    }

    public String a() {
        return this.f7876b;
    }

    public String b() {
        return this.f7877c;
    }

    public float c() {
        return this.f7875a;
    }

    public boolean equals(Object obj) {
        String str;
        if (obj == null || !(obj instanceof MagicWindowPosition)) {
            return super.equals(obj);
        }
        MagicWindowPosition magicWindowPosition = (MagicWindowPosition) obj;
        String str2 = this.f7877c;
        return str2 != null && str2.equals(magicWindowPosition.f7877c) && (str = this.f7876b) != null && str.equals(magicWindowPosition.f7876b) && Math.abs(this.f7875a - magicWindowPosition.f7875a) < 0.001f;
    }

    public String toString() {
        return "pkg=" + this.f7877c + ",ori=" + this.f7876b + ",pos=" + this.f7875a;
    }
}
