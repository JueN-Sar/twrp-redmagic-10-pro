package androidx.constraintlayout.core.dsl;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class Constraint {
    public static final Constraint J = new Constraint("parent");
    static int K = Integer.MIN_VALUE;
    static Map L;
    private int A;
    private int B;
    private int C;
    private int D;
    private float E;
    private float F;
    private String[] G;
    private boolean H;
    private boolean I;

    /* renamed from: a, reason: collision with root package name */
    private final String f1554a;

    /* renamed from: b, reason: collision with root package name */
    String f1555b = null;

    /* renamed from: c, reason: collision with root package name */
    String f1556c = null;

    /* renamed from: d, reason: collision with root package name */
    private HAnchor f1557d = new HAnchor(HSide.LEFT);

    /* renamed from: e, reason: collision with root package name */
    private HAnchor f1558e = new HAnchor(HSide.RIGHT);

    /* renamed from: f, reason: collision with root package name */
    private VAnchor f1559f = new VAnchor(VSide.TOP);

    /* renamed from: g, reason: collision with root package name */
    private VAnchor f1560g = new VAnchor(VSide.BOTTOM);

    /* renamed from: h, reason: collision with root package name */
    private HAnchor f1561h = new HAnchor(HSide.START);

    /* renamed from: i, reason: collision with root package name */
    private HAnchor f1562i = new HAnchor(HSide.END);

    /* renamed from: j, reason: collision with root package name */
    private VAnchor f1563j = new VAnchor(VSide.BASELINE);

    /* renamed from: k, reason: collision with root package name */
    private int f1564k;

    /* renamed from: l, reason: collision with root package name */
    private int f1565l;

    /* renamed from: m, reason: collision with root package name */
    private float f1566m;

    /* renamed from: n, reason: collision with root package name */
    private float f1567n;

    /* renamed from: o, reason: collision with root package name */
    private String f1568o;

    /* renamed from: p, reason: collision with root package name */
    private String f1569p;

    /* renamed from: q, reason: collision with root package name */
    private int f1570q;

    /* renamed from: r, reason: collision with root package name */
    private float f1571r;

    /* renamed from: s, reason: collision with root package name */
    private int f1572s;
    private int t;
    private float u;
    private float v;
    private ChainMode w;
    private ChainMode x;
    private Behaviour y;
    private Behaviour z;

    public class Anchor {

        /* renamed from: a, reason: collision with root package name */
        final Side f1573a;

        /* renamed from: c, reason: collision with root package name */
        int f1575c;

        /* renamed from: b, reason: collision with root package name */
        Anchor f1574b = null;

        /* renamed from: d, reason: collision with root package name */
        int f1576d = Integer.MIN_VALUE;

        Anchor(Side side) {
            this.f1573a = side;
        }

        public void a(StringBuilder sb) {
            if (this.f1574b != null) {
                sb.append(this.f1573a.toString().toLowerCase());
                sb.append(":");
                sb.append(this);
                sb.append(",\n");
            }
        }

        public String b() {
            return Constraint.this.f1554a;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            if (this.f1574b != null) {
                sb.append("'");
                sb.append(this.f1574b.b());
                sb.append("',");
                sb.append("'");
                sb.append(this.f1574b.f1573a.toString().toLowerCase());
                sb.append("'");
            }
            if (this.f1575c != 0) {
                sb.append(",");
                sb.append(this.f1575c);
            }
            if (this.f1576d != Integer.MIN_VALUE) {
                if (this.f1575c == 0) {
                    sb.append(",0,");
                    sb.append(this.f1576d);
                } else {
                    sb.append(",");
                    sb.append(this.f1576d);
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public enum Behaviour {
        SPREAD,
        WRAP,
        PERCENT,
        RATIO,
        RESOLVED
    }

    public enum ChainMode {
        SPREAD,
        SPREAD_INSIDE,
        PACKED
    }

    public class HAnchor extends Anchor {
        HAnchor(HSide hSide) {
            super(Side.valueOf(hSide.name()));
        }
    }

    public enum HSide {
        LEFT,
        RIGHT,
        START,
        END
    }

    public enum Side {
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        START,
        END,
        BASELINE
    }

    public class VAnchor extends Anchor {
        VAnchor(VSide vSide) {
            super(Side.valueOf(vSide.name()));
        }
    }

    public enum VSide {
        TOP,
        BOTTOM,
        BASELINE
    }

    static {
        HashMap hashMap = new HashMap();
        L = hashMap;
        hashMap.put(ChainMode.SPREAD, "spread");
        L.put(ChainMode.SPREAD_INSIDE, "spread_inside");
        L.put(ChainMode.PACKED, "packed");
    }

    public Constraint(String str) {
        int i2 = K;
        this.f1564k = i2;
        this.f1565l = i2;
        this.f1566m = Float.NaN;
        this.f1567n = Float.NaN;
        this.f1568o = null;
        this.f1569p = null;
        this.f1570q = Integer.MIN_VALUE;
        this.f1571r = Float.NaN;
        this.f1572s = Integer.MIN_VALUE;
        this.t = Integer.MIN_VALUE;
        this.u = Float.NaN;
        this.v = Float.NaN;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = i2;
        this.B = i2;
        this.C = i2;
        this.D = i2;
        this.E = Float.NaN;
        this.F = Float.NaN;
        this.G = null;
        this.H = false;
        this.I = false;
        this.f1554a = str;
    }

    protected void b(StringBuilder sb, String str, float f2) {
        if (Float.isNaN(f2)) {
            return;
        }
        sb.append(str);
        sb.append(":");
        sb.append(f2);
        sb.append(",\n");
    }

    public String c(String[] strArr) {
        StringBuilder sb = new StringBuilder("[");
        int i2 = 0;
        while (i2 < strArr.length) {
            sb.append(i2 == 0 ? "'" : ",'");
            sb.append(strArr[i2]);
            sb.append("'");
            i2++;
        }
        sb.append("]");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f1554a + ":{\n");
        this.f1557d.a(sb);
        this.f1558e.a(sb);
        this.f1559f.a(sb);
        this.f1560g.a(sb);
        this.f1561h.a(sb);
        this.f1562i.a(sb);
        this.f1563j.a(sb);
        if (this.f1564k != K) {
            sb.append("width:");
            sb.append(this.f1564k);
            sb.append(",\n");
        }
        if (this.f1565l != K) {
            sb.append("height:");
            sb.append(this.f1565l);
            sb.append(",\n");
        }
        b(sb, "horizontalBias", this.f1566m);
        b(sb, "verticalBias", this.f1567n);
        if (this.f1568o != null) {
            sb.append("dimensionRatio:'");
            sb.append(this.f1568o);
            sb.append("',\n");
        }
        if (this.f1569p != null && (!Float.isNaN(this.f1571r) || this.f1570q != Integer.MIN_VALUE)) {
            sb.append("circular:['");
            sb.append(this.f1569p);
            sb.append("'");
            if (!Float.isNaN(this.f1571r)) {
                sb.append(",");
                sb.append(this.f1571r);
            }
            if (this.f1570q != Integer.MIN_VALUE) {
                if (Float.isNaN(this.f1571r)) {
                    sb.append(",0,");
                    sb.append(this.f1570q);
                } else {
                    sb.append(",");
                    sb.append(this.f1570q);
                }
            }
            sb.append("],\n");
        }
        b(sb, "verticalWeight", this.u);
        b(sb, "horizontalWeight", this.v);
        if (this.w != null) {
            sb.append("horizontalChainStyle:'");
            sb.append((String) L.get(this.w));
            sb.append("',\n");
        }
        if (this.x != null) {
            sb.append("verticalChainStyle:'");
            sb.append((String) L.get(this.x));
            sb.append("',\n");
        }
        if (this.y != null) {
            int i2 = this.A;
            int i3 = K;
            if (i2 == i3 && this.C == i3) {
                sb.append("width:'");
                sb.append(this.y.toString().toLowerCase());
                sb.append("',\n");
            } else {
                sb.append("width:{value:'");
                sb.append(this.y.toString().toLowerCase());
                sb.append("'");
                if (this.A != K) {
                    sb.append(",max:");
                    sb.append(this.A);
                }
                if (this.C != K) {
                    sb.append(",min:");
                    sb.append(this.C);
                }
                sb.append("},\n");
            }
        }
        if (this.z != null) {
            int i4 = this.B;
            int i5 = K;
            if (i4 == i5 && this.D == i5) {
                sb.append("height:'");
                sb.append(this.z.toString().toLowerCase());
                sb.append("',\n");
            } else {
                sb.append("height:{value:'");
                sb.append(this.z.toString().toLowerCase());
                sb.append("'");
                if (this.B != K) {
                    sb.append(",max:");
                    sb.append(this.B);
                }
                if (this.D != K) {
                    sb.append(",min:");
                    sb.append(this.D);
                }
                sb.append("},\n");
            }
        }
        if (!Double.isNaN(this.E)) {
            sb.append("width:'");
            sb.append((int) this.E);
            sb.append("%',\n");
        }
        if (!Double.isNaN(this.F)) {
            sb.append("height:'");
            sb.append((int) this.F);
            sb.append("%',\n");
        }
        if (this.G != null) {
            sb.append("referenceIds:");
            sb.append(c(this.G));
            sb.append(",\n");
        }
        if (this.H) {
            sb.append("constrainedWidth:");
            sb.append(this.H);
            sb.append(",\n");
        }
        if (this.I) {
            sb.append("constrainedHeight:");
            sb.append(this.I);
            sb.append(",\n");
        }
        sb.append("},\n");
        return sb.toString();
    }
}
