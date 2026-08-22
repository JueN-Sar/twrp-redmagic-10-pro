package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;

/* loaded from: classes.dex */
class DimensionDependency extends DependencyNode {

    /* renamed from: m, reason: collision with root package name */
    public int f2048m;

    DimensionDependency(WidgetRun widgetRun) {
        super(widgetRun);
        if (widgetRun instanceof HorizontalWidgetRun) {
            this.f2040e = DependencyNode.Type.HORIZONTAL_DIMENSION;
        } else {
            this.f2040e = DependencyNode.Type.VERTICAL_DIMENSION;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.DependencyNode
    public void d(int i2) {
        if (this.f2045j) {
            return;
        }
        this.f2045j = true;
        this.f2042g = i2;
        for (Dependency dependency : this.f2046k) {
            dependency.a(dependency);
        }
    }
}
