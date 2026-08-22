package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

/* loaded from: classes.dex */
class GuidelineReference extends WidgetRun {
    GuidelineReference(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.f1973e.f();
        constraintWidget.f1974f.f();
        this.f2084f = ((Guideline) constraintWidget).x1();
    }

    private void q(DependencyNode dependencyNode) {
        this.f2086h.f2046k.add(dependencyNode);
        dependencyNode.f2047l.add(this.f2086h);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void a(Dependency dependency) {
        DependencyNode dependencyNode = this.f2086h;
        if (dependencyNode.f2038c && !dependencyNode.f2045j) {
            this.f2086h.d((int) ((((DependencyNode) dependencyNode.f2047l.get(0)).f2042g * ((Guideline) this.f2080b).A1()) + 0.5f));
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void d() {
        Guideline guideline = (Guideline) this.f2080b;
        int y1 = guideline.y1();
        int z1 = guideline.z1();
        guideline.A1();
        if (guideline.x1() == 1) {
            if (y1 != -1) {
                this.f2086h.f2047l.add(this.f2080b.c0.f1973e.f2086h);
                this.f2080b.c0.f1973e.f2086h.f2046k.add(this.f2086h);
                this.f2086h.f2041f = y1;
            } else if (z1 != -1) {
                this.f2086h.f2047l.add(this.f2080b.c0.f1973e.f2087i);
                this.f2080b.c0.f1973e.f2087i.f2046k.add(this.f2086h);
                this.f2086h.f2041f = -z1;
            } else {
                DependencyNode dependencyNode = this.f2086h;
                dependencyNode.f2037b = true;
                dependencyNode.f2047l.add(this.f2080b.c0.f1973e.f2087i);
                this.f2080b.c0.f1973e.f2087i.f2046k.add(this.f2086h);
            }
            q(this.f2080b.f1973e.f2086h);
            q(this.f2080b.f1973e.f2087i);
            return;
        }
        if (y1 != -1) {
            this.f2086h.f2047l.add(this.f2080b.c0.f1974f.f2086h);
            this.f2080b.c0.f1974f.f2086h.f2046k.add(this.f2086h);
            this.f2086h.f2041f = y1;
        } else if (z1 != -1) {
            this.f2086h.f2047l.add(this.f2080b.c0.f1974f.f2087i);
            this.f2080b.c0.f1974f.f2087i.f2046k.add(this.f2086h);
            this.f2086h.f2041f = -z1;
        } else {
            DependencyNode dependencyNode2 = this.f2086h;
            dependencyNode2.f2037b = true;
            dependencyNode2.f2047l.add(this.f2080b.c0.f1974f.f2087i);
            this.f2080b.c0.f1974f.f2087i.f2046k.add(this.f2086h);
        }
        q(this.f2080b.f1974f.f2086h);
        q(this.f2080b.f1974f.f2087i);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void e() {
        if (((Guideline) this.f2080b).x1() == 1) {
            this.f2080b.r1(this.f2086h.f2042g);
        } else {
            this.f2080b.s1(this.f2086h.f2042g);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void f() {
        this.f2086h.c();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean m() {
        return false;
    }
}
