package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import java.util.Iterator;

/* loaded from: classes.dex */
class HelperReferences extends WidgetRun {
    HelperReferences(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private void q(DependencyNode dependencyNode) {
        this.f2086h.f2046k.add(dependencyNode);
        dependencyNode.f2047l.add(this.f2086h);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void a(Dependency dependency) {
        Barrier barrier = (Barrier) this.f2080b;
        int A1 = barrier.A1();
        Iterator it = this.f2086h.f2047l.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = ((DependencyNode) it.next()).f2042g;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (A1 == 0 || A1 == 2) {
            this.f2086h.d(i3 + barrier.B1());
        } else {
            this.f2086h.d(i2 + barrier.B1());
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        ConstraintWidget constraintWidget = this.f2080b;
        if (constraintWidget instanceof Barrier) {
            this.f2086h.f2037b = true;
            Barrier barrier = (Barrier) constraintWidget;
            int A1 = barrier.A1();
            boolean z1 = barrier.z1();
            int i2 = 0;
            if (A1 == 0) {
                this.f2086h.f2040e = DependencyNode.Type.LEFT;
                while (i2 < barrier.W0) {
                    ConstraintWidget constraintWidget2 = barrier.V0[i2];
                    if (z1 || constraintWidget2.X() != 8) {
                        DependencyNode dependencyNode = constraintWidget2.f1973e.f2086h;
                        dependencyNode.f2046k.add(this.f2086h);
                        this.f2086h.f2047l.add(dependencyNode);
                    }
                    i2++;
                }
                q(this.f2080b.f1973e.f2086h);
                q(this.f2080b.f1973e.f2087i);
                return;
            }
            if (A1 == 1) {
                this.f2086h.f2040e = DependencyNode.Type.RIGHT;
                while (i2 < barrier.W0) {
                    ConstraintWidget constraintWidget3 = barrier.V0[i2];
                    if (z1 || constraintWidget3.X() != 8) {
                        DependencyNode dependencyNode2 = constraintWidget3.f1973e.f2087i;
                        dependencyNode2.f2046k.add(this.f2086h);
                        this.f2086h.f2047l.add(dependencyNode2);
                    }
                    i2++;
                }
                q(this.f2080b.f1973e.f2086h);
                q(this.f2080b.f1973e.f2087i);
                return;
            }
            if (A1 == 2) {
                this.f2086h.f2040e = DependencyNode.Type.TOP;
                while (i2 < barrier.W0) {
                    ConstraintWidget constraintWidget4 = barrier.V0[i2];
                    if (z1 || constraintWidget4.X() != 8) {
                        DependencyNode dependencyNode3 = constraintWidget4.f1974f.f2086h;
                        dependencyNode3.f2046k.add(this.f2086h);
                        this.f2086h.f2047l.add(dependencyNode3);
                    }
                    i2++;
                }
                q(this.f2080b.f1974f.f2086h);
                q(this.f2080b.f1974f.f2087i);
                return;
            }
            if (A1 != 3) {
                return;
            }
            this.f2086h.f2040e = DependencyNode.Type.BOTTOM;
            while (i2 < barrier.W0) {
                ConstraintWidget constraintWidget5 = barrier.V0[i2];
                if (z1 || constraintWidget5.X() != 8) {
                    DependencyNode dependencyNode4 = constraintWidget5.f1974f.f2087i;
                    dependencyNode4.f2046k.add(this.f2086h);
                    this.f2086h.f2047l.add(dependencyNode4);
                }
                i2++;
            }
            q(this.f2080b.f1974f.f2086h);
            q(this.f2080b.f1974f.f2087i);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        ConstraintWidget constraintWidget = this.f2080b;
        if (constraintWidget instanceof Barrier) {
            int A1 = ((Barrier) constraintWidget).A1();
            if (A1 == 0 || A1 == 1) {
                this.f2080b.r1(this.f2086h.f2042g);
            } else {
                this.f2080b.s1(this.f2086h.f2042g);
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f2081c = null;
        this.f2086h.c();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
