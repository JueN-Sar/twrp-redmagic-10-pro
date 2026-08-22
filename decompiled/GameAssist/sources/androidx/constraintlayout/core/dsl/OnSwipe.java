package androidx.constraintlayout.core.dsl;

/* loaded from: classes.dex */
public class OnSwipe {

    /* renamed from: a, reason: collision with root package name */
    private Drag f1646a;

    /* renamed from: b, reason: collision with root package name */
    private Side f1647b;

    /* renamed from: c, reason: collision with root package name */
    private String f1648c;

    /* renamed from: d, reason: collision with root package name */
    private String f1649d;

    /* renamed from: e, reason: collision with root package name */
    private TouchUp f1650e;

    /* renamed from: f, reason: collision with root package name */
    private String f1651f;

    /* renamed from: g, reason: collision with root package name */
    private float f1652g;

    /* renamed from: h, reason: collision with root package name */
    private float f1653h;

    /* renamed from: i, reason: collision with root package name */
    private float f1654i;

    /* renamed from: j, reason: collision with root package name */
    private float f1655j;

    /* renamed from: k, reason: collision with root package name */
    private float f1656k;

    /* renamed from: l, reason: collision with root package name */
    private float f1657l;

    /* renamed from: m, reason: collision with root package name */
    private float f1658m;

    /* renamed from: n, reason: collision with root package name */
    private float f1659n;

    /* renamed from: o, reason: collision with root package name */
    private Boundary f1660o;

    /* renamed from: p, reason: collision with root package name */
    private Mode f1661p;

    public enum Boundary {
        OVERSHOOT,
        BOUNCE_START,
        BOUNCE_END,
        BOUNCE_BOTH
    }

    public enum Drag {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        START,
        END,
        CLOCKWISE,
        ANTICLOCKWISE
    }

    public enum Mode {
        VELOCITY,
        SPRING
    }

    public enum Side {
        TOP,
        LEFT,
        RIGHT,
        BOTTOM,
        MIDDLE,
        START,
        END
    }

    public enum TouchUp {
        AUTOCOMPLETE,
        TO_START,
        NEVER_COMPLETE_END,
        TO_END,
        STOP,
        DECELERATE,
        DECELERATE_COMPLETE,
        NEVER_COMPLETE_START
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("OnSwipe:{\n");
        if (this.f1648c != null) {
            sb.append("anchor:'");
            sb.append(this.f1648c);
            sb.append("',\n");
        }
        if (this.f1646a != null) {
            sb.append("direction:'");
            sb.append(this.f1646a.toString().toLowerCase());
            sb.append("',\n");
        }
        if (this.f1647b != null) {
            sb.append("side:'");
            sb.append(this.f1647b.toString().toLowerCase());
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1654i)) {
            sb.append("scale:'");
            sb.append(this.f1654i);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1655j)) {
            sb.append("threshold:'");
            sb.append(this.f1655j);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1652g)) {
            sb.append("maxVelocity:'");
            sb.append(this.f1652g);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1653h)) {
            sb.append("maxAccel:'");
            sb.append(this.f1653h);
            sb.append("',\n");
        }
        if (this.f1649d != null) {
            sb.append("limitBounds:'");
            sb.append(this.f1649d);
            sb.append("',\n");
        }
        if (this.f1661p != null) {
            sb.append("mode:'");
            sb.append(this.f1661p.toString().toLowerCase());
            sb.append("',\n");
        }
        if (this.f1650e != null) {
            sb.append("touchUp:'");
            sb.append(this.f1650e.toString().toLowerCase());
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1657l)) {
            sb.append("springMass:'");
            sb.append(this.f1657l);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1658m)) {
            sb.append("springStiffness:'");
            sb.append(this.f1658m);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1656k)) {
            sb.append("springDamping:'");
            sb.append(this.f1656k);
            sb.append("',\n");
        }
        if (!Float.isNaN(this.f1659n)) {
            sb.append("stopThreshold:'");
            sb.append(this.f1659n);
            sb.append("',\n");
        }
        if (this.f1660o != null) {
            sb.append("springBoundary:'");
            sb.append(this.f1660o);
            sb.append("',\n");
        }
        if (this.f1651f != null) {
            sb.append("around:'");
            sb.append(this.f1651f);
            sb.append("',\n");
        }
        sb.append("},\n");
        return sb.toString();
    }
}
