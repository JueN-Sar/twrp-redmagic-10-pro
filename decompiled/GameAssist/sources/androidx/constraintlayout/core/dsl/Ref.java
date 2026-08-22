package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class Ref {

    /* renamed from: a, reason: collision with root package name */
    private String f1662a;

    /* renamed from: b, reason: collision with root package name */
    private float f1663b;

    /* renamed from: c, reason: collision with root package name */
    private float f1664c;

    /* renamed from: d, reason: collision with root package name */
    private float f1665d;

    public String toString() {
        String str = this.f1662a;
        if (str == null || str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = (Float.isNaN(this.f1663b) && Float.isNaN(this.f1664c) && Float.isNaN(this.f1665d)) ? false : true;
        if (z) {
            sb.append("[");
        }
        sb.append("'");
        sb.append(this.f1662a);
        sb.append("'");
        if (!Float.isNaN(this.f1665d)) {
            sb.append(",");
            sb.append(!Float.isNaN(this.f1663b) ? this.f1663b : 0.0f);
            sb.append(",");
            sb.append(Float.isNaN(this.f1664c) ? 0.0f : this.f1664c);
            sb.append(",");
            sb.append(this.f1665d);
        } else if (!Float.isNaN(this.f1664c)) {
            sb.append(",");
            sb.append(Float.isNaN(this.f1663b) ? 0.0f : this.f1663b);
            sb.append(",");
            sb.append(this.f1664c);
        } else if (!Float.isNaN(this.f1663b)) {
            sb.append(",");
            sb.append(this.f1663b);
        }
        if (z) {
            sb.append("]");
        }
        sb.append(",");
        return sb.toString();
    }
}
