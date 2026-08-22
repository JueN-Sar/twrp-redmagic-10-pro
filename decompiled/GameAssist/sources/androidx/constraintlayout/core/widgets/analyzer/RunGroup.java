package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.util.ArrayList;

/* loaded from: classes.dex */
class RunGroup {

    /* renamed from: h, reason: collision with root package name */
    public static int f2054h;

    /* renamed from: c, reason: collision with root package name */
    WidgetRun f2057c;

    /* renamed from: d, reason: collision with root package name */
    WidgetRun f2058d;

    /* renamed from: f, reason: collision with root package name */
    int f2060f;

    /* renamed from: g, reason: collision with root package name */
    int f2061g;

    /* renamed from: a, reason: collision with root package name */
    public int f2055a = 0;

    /* renamed from: b, reason: collision with root package name */
    public boolean f2056b = false;

    /* renamed from: e, reason: collision with root package name */
    ArrayList f2059e = new ArrayList();

    RunGroup(WidgetRun widgetRun, int i2) {
        this.f2057c = null;
        this.f2058d = null;
        int i3 = f2054h;
        this.f2060f = i3;
        f2054h = i3 + 1;
        this.f2057c = widgetRun;
        this.f2058d = widgetRun;
        this.f2061g = i2;
    }

    private long c(DependencyNode dependencyNode, long j2) {
        WidgetRun widgetRun = dependencyNode.f2039d;
        if (widgetRun instanceof HelperReferences) {
            return j2;
        }
        int size = dependencyNode.f2046k.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = (Dependency) dependencyNode.f2046k.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.f2039d != widgetRun) {
                    j3 = Math.min(j3, c(dependencyNode2, dependencyNode2.f2041f + j2));
                }
            }
        }
        if (dependencyNode != widgetRun.f2087i) {
            return j3;
        }
        long j4 = j2 - widgetRun.j();
        return Math.min(Math.min(j3, c(widgetRun.f2086h, j4)), j4 - widgetRun.f2086h.f2041f);
    }

    private long d(DependencyNode dependencyNode, long j2) {
        WidgetRun widgetRun = dependencyNode.f2039d;
        if (widgetRun instanceof HelperReferences) {
            return j2;
        }
        int size = dependencyNode.f2046k.size();
        long j3 = j2;
        for (int i2 = 0; i2 < size; i2++) {
            Dependency dependency = (Dependency) dependencyNode.f2046k.get(i2);
            if (dependency instanceof DependencyNode) {
                DependencyNode dependencyNode2 = (DependencyNode) dependency;
                if (dependencyNode2.f2039d != widgetRun) {
                    j3 = Math.max(j3, d(dependencyNode2, dependencyNode2.f2041f + j2));
                }
            }
        }
        if (dependencyNode != widgetRun.f2086h) {
            return j3;
        }
        long j4 = j2 + widgetRun.j();
        return Math.max(Math.max(j3, d(widgetRun.f2087i, j4)), j4 - widgetRun.f2087i.f2041f);
    }

    public void a(WidgetRun widgetRun) {
        this.f2059e.add(widgetRun);
        this.f2058d = widgetRun;
    }

    public long b(ConstraintWidgetContainer constraintWidgetContainer, int i2) {
        WidgetRun widgetRun = this.f2057c;
        if (widgetRun instanceof ChainRun) {
            if (((ChainRun) widgetRun).f2084f != i2) {
                return 0L;
            }
        } else if (i2 == 0) {
            if (!(widgetRun instanceof HorizontalWidgetRun)) {
                return 0L;
            }
        } else if (!(widgetRun instanceof VerticalWidgetRun)) {
            return 0L;
        }
        DependencyNode dependencyNode = (i2 == 0 ? constraintWidgetContainer.f1973e : constraintWidgetContainer.f1974f).f2086h;
        DependencyNode dependencyNode2 = (i2 == 0 ? constraintWidgetContainer.f1973e : constraintWidgetContainer.f1974f).f2087i;
        boolean contains = widgetRun.f2086h.f2047l.contains(dependencyNode);
        boolean contains2 = this.f2057c.f2087i.f2047l.contains(dependencyNode2);
        long j2 = this.f2057c.j();
        if (!contains || !contains2) {
            if (contains) {
                return Math.max(d(this.f2057c.f2086h, r12.f2041f), this.f2057c.f2086h.f2041f + j2);
            }
            if (!contains2) {
                return (r12.f2086h.f2041f + this.f2057c.j()) - this.f2057c.f2087i.f2041f;
            }
            return Math.max(-c(this.f2057c.f2087i, r12.f2041f), (-this.f2057c.f2087i.f2041f) + j2);
        }
        long d2 = d(this.f2057c.f2086h, 0L);
        long c2 = c(this.f2057c.f2087i, 0L);
        long j3 = d2 - j2;
        WidgetRun widgetRun2 = this.f2057c;
        int i3 = widgetRun2.f2087i.f2041f;
        if (j3 >= (-i3)) {
            j3 += i3;
        }
        int i4 = widgetRun2.f2086h.f2041f;
        long j4 = ((-c2) - j2) - i4;
        if (j4 >= i4) {
            j4 -= i4;
        }
        float s2 = widgetRun2.f2080b.s(i2);
        float f2 = s2 > 0.0f ? (long) ((j4 / s2) + (j3 / (1.0f - s2))) : 0L;
        long j5 = ((long) ((f2 * s2) + 0.5f)) + j2 + ((long) ((f2 * (1.0f - s2)) + 0.5f));
        WidgetRun widgetRun3 = this.f2057c;
        return (widgetRun3.f2086h.f2041f + j5) - widgetRun3.f2087i.f2041f;
    }
}
