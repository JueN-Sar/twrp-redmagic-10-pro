package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class KeyCycle extends KeyAttribute {

    /* renamed from: s, reason: collision with root package name */
    private Wave f1625s;
    private float t;
    private float u;
    private float v;

    public enum Wave {
        SIN,
        SQUARE,
        TRIANGLE,
        SAW,
        REVERSE_SAW,
        COS
    }

    @Override // androidx.constraintlayout.core.dsl.KeyAttribute
    protected void f(StringBuilder sb) {
        super.f(sb);
        if (this.f1625s != null) {
            sb.append("shape:'");
            sb.append(this.f1625s);
            sb.append("',\n");
        }
        a(sb, "period", this.t);
        a(sb, "offset", this.u);
        a(sb, "phase", this.v);
    }
}
