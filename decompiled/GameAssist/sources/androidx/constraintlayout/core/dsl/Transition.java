package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class Transition {

    /* renamed from: a, reason: collision with root package name */
    private OnSwipe f1666a;

    /* renamed from: b, reason: collision with root package name */
    private String f1667b;

    /* renamed from: c, reason: collision with root package name */
    private String f1668c;

    /* renamed from: d, reason: collision with root package name */
    private String f1669d;

    /* renamed from: e, reason: collision with root package name */
    private int f1670e;

    /* renamed from: f, reason: collision with root package name */
    private float f1671f;

    /* renamed from: g, reason: collision with root package name */
    private KeyFrames f1672g;

    public String toString() {
        String str = this.f1667b + ":{\nfrom:'" + this.f1669d + "',\nto:'" + this.f1668c + "',\n";
        if (this.f1670e != 400) {
            str = str + "duration:" + this.f1670e + ",\n";
        }
        if (this.f1671f != 0.0f) {
            str = str + "stagger:" + this.f1671f + ",\n";
        }
        if (this.f1666a != null) {
            str = str + this.f1666a.toString();
        }
        return (str + this.f1672g.toString()) + "},\n";
    }
}
