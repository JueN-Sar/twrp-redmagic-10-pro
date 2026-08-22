package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.dsl.Constraint;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class Chain extends Helper {

    /* renamed from: f, reason: collision with root package name */
    protected static final Map f1550f;

    public class Anchor {

        /* renamed from: a, reason: collision with root package name */
        Constraint.Anchor f1551a;

        /* renamed from: b, reason: collision with root package name */
        int f1552b;

        /* renamed from: c, reason: collision with root package name */
        int f1553c;

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            if (this.f1551a != null) {
                sb.append("'");
                sb.append(this.f1551a.b());
                sb.append("',");
                sb.append("'");
                sb.append(this.f1551a.f1573a.toString().toLowerCase());
                sb.append("'");
            }
            if (this.f1552b != 0) {
                sb.append(",");
                sb.append(this.f1552b);
            }
            if (this.f1553c != Integer.MIN_VALUE) {
                if (this.f1552b == 0) {
                    sb.append(",0,");
                    sb.append(this.f1553c);
                } else {
                    sb.append(",");
                    sb.append(this.f1553c);
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public enum Style {
        PACKED,
        SPREAD,
        SPREAD_INSIDE
    }

    static {
        HashMap hashMap = new HashMap();
        f1550f = hashMap;
        hashMap.put(Style.SPREAD, "'spread'");
        hashMap.put(Style.SPREAD_INSIDE, "'spread_inside'");
        hashMap.put(Style.PACKED, "'packed'");
    }
}
