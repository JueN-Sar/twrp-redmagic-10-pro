package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintAnchor {

    /* renamed from: b, reason: collision with root package name */
    private int f1961b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f1962c;

    /* renamed from: d, reason: collision with root package name */
    public final ConstraintWidget f1963d;

    /* renamed from: e, reason: collision with root package name */
    public final Type f1964e;

    /* renamed from: f, reason: collision with root package name */
    public ConstraintAnchor f1965f;

    /* renamed from: i, reason: collision with root package name */
    SolverVariable f1968i;

    /* renamed from: a, reason: collision with root package name */
    private HashSet f1960a = null;

    /* renamed from: g, reason: collision with root package name */
    public int f1966g = 0;

    /* renamed from: h, reason: collision with root package name */
    int f1967h = Integer.MIN_VALUE;

    public enum Type {
        NONE,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        BASELINE,
        CENTER,
        CENTER_X,
        CENTER_Y
    }

    public ConstraintAnchor(ConstraintWidget constraintWidget, Type type) {
        this.f1963d = constraintWidget;
        this.f1964e = type;
    }

    public boolean a(ConstraintAnchor constraintAnchor, int i2) {
        return b(constraintAnchor, i2, Integer.MIN_VALUE, false);
    }

    public boolean b(ConstraintAnchor constraintAnchor, int i2, int i3, boolean z) {
        if (constraintAnchor == null) {
            q();
            return true;
        }
        if (!z && !p(constraintAnchor)) {
            return false;
        }
        this.f1965f = constraintAnchor;
        if (constraintAnchor.f1960a == null) {
            constraintAnchor.f1960a = new HashSet();
        }
        HashSet hashSet = this.f1965f.f1960a;
        if (hashSet != null) {
            hashSet.add(this);
        }
        this.f1966g = i2;
        this.f1967h = i3;
        return true;
    }

    public void c(int i2, ArrayList arrayList, WidgetGroup widgetGroup) {
        HashSet hashSet = this.f1960a;
        if (hashSet != null) {
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                Grouping.a(((ConstraintAnchor) it.next()).f1963d, i2, arrayList, widgetGroup);
            }
        }
    }

    public HashSet d() {
        return this.f1960a;
    }

    public int e() {
        if (this.f1962c) {
            return this.f1961b;
        }
        return 0;
    }

    public int f() {
        ConstraintAnchor constraintAnchor;
        if (this.f1963d.X() == 8) {
            return 0;
        }
        return (this.f1967h == Integer.MIN_VALUE || (constraintAnchor = this.f1965f) == null || constraintAnchor.f1963d.X() != 8) ? this.f1966g : this.f1967h;
    }

    public final ConstraintAnchor g() {
        switch (this.f1964e) {
            case NONE:
            case BASELINE:
            case CENTER:
            case CENTER_X:
            case CENTER_Y:
                return null;
            case LEFT:
                return this.f1963d.S;
            case TOP:
                return this.f1963d.T;
            case RIGHT:
                return this.f1963d.Q;
            case BOTTOM:
                return this.f1963d.R;
            default:
                throw new AssertionError(this.f1964e.name());
        }
    }

    public ConstraintWidget h() {
        return this.f1963d;
    }

    public SolverVariable i() {
        return this.f1968i;
    }

    public ConstraintAnchor j() {
        return this.f1965f;
    }

    public Type k() {
        return this.f1964e;
    }

    public boolean l() {
        HashSet hashSet = this.f1960a;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((ConstraintAnchor) it.next()).g().o()) {
                return true;
            }
        }
        return false;
    }

    public boolean m() {
        HashSet hashSet = this.f1960a;
        return hashSet != null && hashSet.size() > 0;
    }

    public boolean n() {
        return this.f1962c;
    }

    public boolean o() {
        return this.f1965f != null;
    }

    public boolean p(ConstraintAnchor constraintAnchor) {
        if (constraintAnchor == null) {
            return false;
        }
        Type k2 = constraintAnchor.k();
        Type type = this.f1964e;
        if (k2 == type) {
            return type != Type.BASELINE || (constraintAnchor.h().b0() && h().b0());
        }
        switch (type) {
            case NONE:
            case CENTER_X:
            case CENTER_Y:
                return false;
            case LEFT:
            case RIGHT:
                boolean z = k2 == Type.LEFT || k2 == Type.RIGHT;
                if (constraintAnchor.h() instanceof Guideline) {
                    return z || k2 == Type.CENTER_X;
                }
                return z;
            case TOP:
            case BOTTOM:
                boolean z2 = k2 == Type.TOP || k2 == Type.BOTTOM;
                if (constraintAnchor.h() instanceof Guideline) {
                    return z2 || k2 == Type.CENTER_Y;
                }
                return z2;
            case BASELINE:
                return (k2 == Type.LEFT || k2 == Type.RIGHT) ? false : true;
            case CENTER:
                return (k2 == Type.BASELINE || k2 == Type.CENTER_X || k2 == Type.CENTER_Y) ? false : true;
            default:
                throw new AssertionError(this.f1964e.name());
        }
    }

    public void q() {
        HashSet hashSet;
        ConstraintAnchor constraintAnchor = this.f1965f;
        if (constraintAnchor != null && (hashSet = constraintAnchor.f1960a) != null) {
            hashSet.remove(this);
            if (this.f1965f.f1960a.size() == 0) {
                this.f1965f.f1960a = null;
            }
        }
        this.f1960a = null;
        this.f1965f = null;
        this.f1966g = 0;
        this.f1967h = Integer.MIN_VALUE;
        this.f1962c = false;
        this.f1961b = 0;
    }

    public void r() {
        this.f1962c = false;
        this.f1961b = 0;
    }

    public void s(Cache cache) {
        SolverVariable solverVariable = this.f1968i;
        if (solverVariable == null) {
            this.f1968i = new SolverVariable(SolverVariable.Type.UNRESTRICTED, null);
        } else {
            solverVariable.h();
        }
    }

    public void t(int i2) {
        this.f1961b = i2;
        this.f1962c = true;
    }

    public String toString() {
        return this.f1963d.v() + ":" + this.f1964e.toString();
    }

    public void u(int i2) {
        if (o()) {
            this.f1967h = i2;
        }
    }
}
