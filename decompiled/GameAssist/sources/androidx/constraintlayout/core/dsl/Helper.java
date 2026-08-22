package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.dsl.Constraint;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Helper {

    /* renamed from: d, reason: collision with root package name */
    protected static final Map f1583d;

    /* renamed from: e, reason: collision with root package name */
    protected static final Map f1584e;

    /* renamed from: a, reason: collision with root package name */
    protected final String f1585a;

    /* renamed from: b, reason: collision with root package name */
    protected HelperType f1586b;

    /* renamed from: c, reason: collision with root package name */
    protected Map f1587c;

    public static final class HelperType {

        /* renamed from: a, reason: collision with root package name */
        final String f1588a;

        public String toString() {
            return this.f1588a;
        }
    }

    public enum Type {
        VERTICAL_GUIDELINE,
        HORIZONTAL_GUIDELINE,
        VERTICAL_CHAIN,
        HORIZONTAL_CHAIN,
        BARRIER
    }

    static {
        HashMap hashMap = new HashMap();
        f1583d = hashMap;
        hashMap.put(Constraint.Side.LEFT, "'left'");
        hashMap.put(Constraint.Side.RIGHT, "'right'");
        hashMap.put(Constraint.Side.TOP, "'top'");
        hashMap.put(Constraint.Side.BOTTOM, "'bottom'");
        hashMap.put(Constraint.Side.START, "'start'");
        hashMap.put(Constraint.Side.END, "'end'");
        hashMap.put(Constraint.Side.BASELINE, "'baseline'");
        HashMap hashMap2 = new HashMap();
        f1584e = hashMap2;
        hashMap2.put(Type.VERTICAL_GUIDELINE, "vGuideline");
        hashMap2.put(Type.HORIZONTAL_GUIDELINE, "hGuideline");
        hashMap2.put(Type.VERTICAL_CHAIN, "vChain");
        hashMap2.put(Type.HORIZONTAL_CHAIN, "hChain");
        hashMap2.put(Type.BARRIER, "barrier");
    }

    public void a(Map map, StringBuilder sb) {
        if (map.isEmpty()) {
            return;
        }
        for (String str : map.keySet()) {
            sb.append(str);
            sb.append(":");
            sb.append((String) map.get(str));
            sb.append(",\n");
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f1585a + ":{\n");
        if (this.f1586b != null) {
            sb.append("type:'");
            sb.append(this.f1586b.toString());
            sb.append("',\n");
        }
        Map map = this.f1587c;
        if (map != null) {
            a(map, sb);
        }
        sb.append("},\n");
        return sb.toString();
    }
}
