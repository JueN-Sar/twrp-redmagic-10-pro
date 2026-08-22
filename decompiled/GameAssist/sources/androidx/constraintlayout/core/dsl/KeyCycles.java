package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class KeyCycles extends KeyAttributes {

    /* renamed from: s, reason: collision with root package name */
    private Wave f1626s;
    private float[] t;
    private float[] u;
    private float[] v;

    public enum Wave {
        SIN,
        SQUARE,
        TRIANGLE,
        SAW,
        REVERSE_SAW,
        COS
    }

    @Override // androidx.constraintlayout.core.dsl.KeyAttributes
    protected void f(StringBuilder sb) {
        super.f(sb);
        if (this.f1626s != null) {
            sb.append("shape:'");
            sb.append(this.f1626s);
            sb.append("',\n");
        }
        c(sb, "period", this.t);
        c(sb, "offset", this.u);
        c(sb, "phase", this.v);
    }
}
