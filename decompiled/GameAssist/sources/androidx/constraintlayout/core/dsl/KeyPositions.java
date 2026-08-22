package androidx.constraintlayout.core.dsl;

import java.util.Arrays;

/* loaded from: classes.dex */
public class KeyPositions extends Keys {

    /* renamed from: a, reason: collision with root package name */
    private String[] f1636a;

    /* renamed from: b, reason: collision with root package name */
    private String f1637b;

    /* renamed from: c, reason: collision with root package name */
    private Type f1638c;

    /* renamed from: d, reason: collision with root package name */
    private int[] f1639d;

    /* renamed from: e, reason: collision with root package name */
    private float[] f1640e;

    /* renamed from: f, reason: collision with root package name */
    private float[] f1641f;

    /* renamed from: g, reason: collision with root package name */
    private float[] f1642g;

    /* renamed from: h, reason: collision with root package name */
    private float[] f1643h;

    public enum Type {
        CARTESIAN,
        SCREEN,
        PATH
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KeyPositions:{\n");
        d(sb, "target", this.f1636a);
        sb.append("frame:");
        sb.append(Arrays.toString(this.f1639d));
        sb.append(",\n");
        if (this.f1638c != null) {
            sb.append("type:'");
            sb.append(this.f1638c);
            sb.append("',\n");
        }
        b(sb, "easing", this.f1637b);
        c(sb, "percentX", this.f1642g);
        c(sb, "percentX", this.f1643h);
        c(sb, "percentWidth", this.f1640e);
        c(sb, "percentHeight", this.f1641f);
        sb.append("},\n");
        return sb.toString();
    }
}
