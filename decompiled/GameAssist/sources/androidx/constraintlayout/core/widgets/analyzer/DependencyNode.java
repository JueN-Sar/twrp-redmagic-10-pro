package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class DependencyNode implements Dependency {

    /* renamed from: d, reason: collision with root package name */
    WidgetRun f2039d;

    /* renamed from: f, reason: collision with root package name */
    int f2041f;

    /* renamed from: g, reason: collision with root package name */
    public int f2042g;

    /* renamed from: a, reason: collision with root package name */
    public Dependency f2036a = null;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2037b = false;

    /* renamed from: c, reason: collision with root package name */
    public boolean f2038c = false;

    /* renamed from: e, reason: collision with root package name */
    Type f2040e = Type.UNKNOWN;

    /* renamed from: h, reason: collision with root package name */
    int f2043h = 1;

    /* renamed from: i, reason: collision with root package name */
    DimensionDependency f2044i = null;

    /* renamed from: j, reason: collision with root package name */
    public boolean f2045j = false;

    /* renamed from: k, reason: collision with root package name */
    List f2046k = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    List f2047l = new ArrayList();

    enum Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE
    }

    public DependencyNode(WidgetRun widgetRun) {
        this.f2039d = widgetRun;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void a(Dependency dependency) {
        Iterator it = this.f2047l.iterator();
        while (it.hasNext()) {
            if (!((DependencyNode) it.next()).f2045j) {
                return;
            }
        }
        this.f2038c = true;
        Dependency dependency2 = this.f2036a;
        if (dependency2 != null) {
            dependency2.a(this);
        }
        if (this.f2037b) {
            this.f2039d.a(this);
            return;
        }
        DependencyNode dependencyNode = null;
        int i2 = 0;
        for (DependencyNode dependencyNode2 : this.f2047l) {
            if (!(dependencyNode2 instanceof DimensionDependency)) {
                i2++;
                dependencyNode = dependencyNode2;
            }
        }
        if (dependencyNode != null && i2 == 1 && dependencyNode.f2045j) {
            DimensionDependency dimensionDependency = this.f2044i;
            if (dimensionDependency != null) {
                if (!dimensionDependency.f2045j) {
                    return;
                } else {
                    this.f2041f = this.f2043h * dimensionDependency.f2042g;
                }
            }
            d(dependencyNode.f2042g + this.f2041f);
        }
        Dependency dependency3 = this.f2036a;
        if (dependency3 != null) {
            dependency3.a(this);
        }
    }

    public void b(Dependency dependency) {
        this.f2046k.add(dependency);
        if (this.f2045j) {
            dependency.a(dependency);
        }
    }

    public void c() {
        this.f2047l.clear();
        this.f2046k.clear();
        this.f2045j = false;
        this.f2042g = 0;
        this.f2038c = false;
        this.f2037b = false;
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f2039d.f2080b.v());
        sb.append(":");
        sb.append(this.f2040e);
        sb.append("(");
        sb.append(this.f2045j ? Integer.valueOf(this.f2042g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.f2047l.size());
        sb.append(":d=");
        sb.append(this.f2046k.size());
        sb.append(">");
        return sb.toString();
    }
}
