package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ChainRun extends WidgetRun {

    /* renamed from: k, reason: collision with root package name */
    ArrayList f2025k;

    /* renamed from: l, reason: collision with root package name */
    private int f2026l;

    public ChainRun(ConstraintWidget constraintWidget, int i2) {
        super(constraintWidget);
        this.f2025k = new ArrayList();
        this.f2084f = i2;
        q();
    }

    private void q() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2 = this.f2080b;
        ConstraintWidget N = constraintWidget2.N(this.f2084f);
        while (true) {
            ConstraintWidget constraintWidget3 = N;
            constraintWidget = constraintWidget2;
            constraintWidget2 = constraintWidget3;
            if (constraintWidget2 == null) {
                break;
            } else {
                N = constraintWidget2.N(this.f2084f);
            }
        }
        this.f2080b = constraintWidget;
        this.f2025k.add(constraintWidget.P(this.f2084f));
        ConstraintWidget L = constraintWidget.L(this.f2084f);
        while (L != null) {
            this.f2025k.add(L.P(this.f2084f));
            L = L.L(this.f2084f);
        }
        Iterator it = this.f2025k.iterator();
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            int i2 = this.f2084f;
            if (i2 == 0) {
                widgetRun.f2080b.f1971c = this;
            } else if (i2 == 1) {
                widgetRun.f2080b.f1972d = this;
            }
        }
        if (this.f2084f == 0 && ((ConstraintWidgetContainer) this.f2080b.M()).W1() && this.f2025k.size() > 1) {
            ArrayList arrayList = this.f2025k;
            this.f2080b = ((WidgetRun) arrayList.get(arrayList.size() - 1)).f2080b;
        }
        this.f2026l = this.f2084f == 0 ? this.f2080b.B() : this.f2080b.U();
    }

    private ConstraintWidget r() {
        for (int i2 = 0; i2 < this.f2025k.size(); i2++) {
            WidgetRun widgetRun = (WidgetRun) this.f2025k.get(i2);
            if (widgetRun.f2080b.X() != 8) {
                return widgetRun.f2080b;
            }
        }
        return null;
    }

    private ConstraintWidget s() {
        for (int size = this.f2025k.size() - 1; size >= 0; size--) {
            WidgetRun widgetRun = (WidgetRun) this.f2025k.get(size);
            if (widgetRun.f2080b.X() != 8) {
                return widgetRun.f2080b;
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:288:0x03fe, code lost:
    
        r7 = r7 - r10;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00e9  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(androidx.constraintlayout.core.widgets.analyzer.Dependency r27) {
        /*
            Method dump skipped, instructions count: 1062
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.ChainRun.a(androidx.constraintlayout.core.widgets.analyzer.Dependency):void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        Iterator it = this.f2025k.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).d();
        }
        int size = this.f2025k.size();
        if (size < 1) {
            return;
        }
        ConstraintWidget constraintWidget = ((WidgetRun) this.f2025k.get(0)).f2080b;
        ConstraintWidget constraintWidget2 = ((WidgetRun) this.f2025k.get(size - 1)).f2080b;
        if (this.f2084f == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.Q;
            ConstraintAnchor constraintAnchor2 = constraintWidget2.S;
            DependencyNode i2 = i(constraintAnchor, 0);
            int f2 = constraintAnchor.f();
            ConstraintWidget r2 = r();
            if (r2 != null) {
                f2 = r2.Q.f();
            }
            if (i2 != null) {
                b(this.f2086h, i2, f2);
            }
            DependencyNode i3 = i(constraintAnchor2, 0);
            int f3 = constraintAnchor2.f();
            ConstraintWidget s2 = s();
            if (s2 != null) {
                f3 = s2.S.f();
            }
            if (i3 != null) {
                b(this.f2087i, i3, -f3);
            }
        } else {
            ConstraintAnchor constraintAnchor3 = constraintWidget.R;
            ConstraintAnchor constraintAnchor4 = constraintWidget2.T;
            DependencyNode i4 = i(constraintAnchor3, 1);
            int f4 = constraintAnchor3.f();
            ConstraintWidget r3 = r();
            if (r3 != null) {
                f4 = r3.R.f();
            }
            if (i4 != null) {
                b(this.f2086h, i4, f4);
            }
            DependencyNode i5 = i(constraintAnchor4, 1);
            int f5 = constraintAnchor4.f();
            ConstraintWidget s3 = s();
            if (s3 != null) {
                f5 = s3.T.f();
            }
            if (i5 != null) {
                b(this.f2087i, i5, -f5);
            }
        }
        this.f2086h.f2036a = this;
        this.f2087i.f2036a = this;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        for (int i2 = 0; i2 < this.f2025k.size(); i2++) {
            ((WidgetRun) this.f2025k.get(i2)).e();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f2081c = null;
        Iterator it = this.f2025k.iterator();
        while (it.hasNext()) {
            ((WidgetRun) it.next()).f();
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public long j() {
        int size = this.f2025k.size();
        long j2 = 0;
        for (int i2 = 0; i2 < size; i2++) {
            j2 = j2 + r4.f2086h.f2041f + ((WidgetRun) this.f2025k.get(i2)).j() + r4.f2087i.f2041f;
        }
        return j2;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        int size = this.f2025k.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!((WidgetRun) this.f2025k.get(i2)).m()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ChainRun ");
        sb.append(this.f2084f == 0 ? "horizontal : " : "vertical : ");
        Iterator it = this.f2025k.iterator();
        while (it.hasNext()) {
            WidgetRun widgetRun = (WidgetRun) it.next();
            sb.append("<");
            sb.append(widgetRun);
            sb.append("> ");
        }
        return sb.toString();
    }
}
