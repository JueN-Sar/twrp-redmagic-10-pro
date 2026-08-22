package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class KeyPosition extends Keys {

    /* renamed from: a, reason: collision with root package name */
    private String f1628a;

    /* renamed from: b, reason: collision with root package name */
    private String f1629b;

    /* renamed from: c, reason: collision with root package name */
    private int f1630c;

    /* renamed from: d, reason: collision with root package name */
    private float f1631d;

    /* renamed from: e, reason: collision with root package name */
    private float f1632e;

    /* renamed from: f, reason: collision with root package name */
    private float f1633f;

    /* renamed from: g, reason: collision with root package name */
    private float f1634g;

    /* renamed from: h, reason: collision with root package name */
    private Type f1635h;

    public enum Type {
        CARTESIAN,
        SCREEN,
        PATH
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPositions:{\n");
        b(sb, "target", this.f1628a);
        sb.append("frame:");
        sb.append(this.f1630c);
        sb.append(",\n");
        if (this.f1635h != null) {
            sb.append("type:'");
            sb.append(this.f1635h);
            sb.append("',\n");
        }
        b(sb, "easing", this.f1629b);
        a(sb, "percentX", this.f1633f);
        a(sb, "percentY", this.f1634g);
        a(sb, "percentWidth", this.f1631d);
        a(sb, "percentHeight", this.f1632e);
        sb.append("},\n");
        return sb.toString();
    }
}
