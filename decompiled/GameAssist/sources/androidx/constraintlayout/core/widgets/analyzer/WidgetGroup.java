package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Chain;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WidgetGroup {

    /* renamed from: g, reason: collision with root package name */
    static int f2065g;

    /* renamed from: b, reason: collision with root package name */
    int f2067b;

    /* renamed from: d, reason: collision with root package name */
    int f2069d;

    /* renamed from: a, reason: collision with root package name */
    ArrayList f2066a = new ArrayList();

    /* renamed from: c, reason: collision with root package name */
    boolean f2068c = false;

    /* renamed from: e, reason: collision with root package name */
    ArrayList f2070e = null;

    /* renamed from: f, reason: collision with root package name */
    private int f2071f = -1;

    static class MeasureResult {

        /* renamed from: a, reason: collision with root package name */
        WeakReference f2072a;

        /* renamed from: b, reason: collision with root package name */
        int f2073b;

        /* renamed from: c, reason: collision with root package name */
        int f2074c;

        /* renamed from: d, reason: collision with root package name */
        int f2075d;

        /* renamed from: e, reason: collision with root package name */
        int f2076e;

        /* renamed from: f, reason: collision with root package name */
        int f2077f;

        /* renamed from: g, reason: collision with root package name */
        int f2078g;

        MeasureResult(ConstraintWidget constraintWidget, LinearSystem linearSystem, int i2) {
            this.f2072a = new WeakReference(constraintWidget);
            this.f2073b = linearSystem.A(constraintWidget.Q);
            this.f2074c = linearSystem.A(constraintWidget.R);
            this.f2075d = linearSystem.A(constraintWidget.S);
            this.f2076e = linearSystem.A(constraintWidget.T);
            this.f2077f = linearSystem.A(constraintWidget.U);
            this.f2078g = i2;
        }
    }

    public WidgetGroup(int i2) {
        int i3 = f2065g;
        f2065g = i3 + 1;
        this.f2067b = i3;
        this.f2069d = i2;
    }

    private String e() {
        int i2 = this.f2069d;
        return i2 == 0 ? "Horizontal" : i2 == 1 ? "Vertical" : i2 == 2 ? "Both" : "Unknown";
    }

    private int j(LinearSystem linearSystem, ArrayList arrayList, int i2) {
        int A;
        int A2;
        ConstraintWidgetContainer constraintWidgetContainer = (ConstraintWidgetContainer) ((ConstraintWidget) arrayList.get(0)).M();
        linearSystem.G();
        constraintWidgetContainer.g(linearSystem, false);
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            ((ConstraintWidget) arrayList.get(i3)).g(linearSystem, false);
        }
        if (i2 == 0 && constraintWidgetContainer.h1 > 0) {
            Chain.b(constraintWidgetContainer, linearSystem, arrayList, 0);
        }
        if (i2 == 1 && constraintWidgetContainer.i1 > 0) {
            Chain.b(constraintWidgetContainer, linearSystem, arrayList, 1);
        }
        try {
            linearSystem.C();
        } catch (Exception e2) {
            System.err.println(e2.toString() + "\n" + Arrays.toString(e2.getStackTrace()).replace("[", "   at ").replace(",", "\n   at").replace("]", ""));
        }
        this.f2070e = new ArrayList();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            this.f2070e.add(new MeasureResult((ConstraintWidget) arrayList.get(i4), linearSystem, i2));
        }
        if (i2 == 0) {
            A = linearSystem.A(constraintWidgetContainer.Q);
            A2 = linearSystem.A(constraintWidgetContainer.S);
            linearSystem.G();
        } else {
            A = linearSystem.A(constraintWidgetContainer.R);
            A2 = linearSystem.A(constraintWidgetContainer.T);
            linearSystem.G();
        }
        return A2 - A;
    }

    public boolean a(ConstraintWidget constraintWidget) {
        if (this.f2066a.contains(constraintWidget)) {
            return false;
        }
        this.f2066a.add(constraintWidget);
        return true;
    }

    public void b(ArrayList arrayList) {
        int size = this.f2066a.size();
        if (this.f2071f != -1 && size > 0) {
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                WidgetGroup widgetGroup = (WidgetGroup) arrayList.get(i2);
                if (this.f2071f == widgetGroup.f2067b) {
                    g(this.f2069d, widgetGroup);
                }
            }
        }
        if (size == 0) {
            arrayList.remove(this);
        }
    }

    public int c() {
        return this.f2067b;
    }

    public int d() {
        return this.f2069d;
    }

    public int f(LinearSystem linearSystem, int i2) {
        if (this.f2066a.size() == 0) {
            return 0;
        }
        return j(linearSystem, this.f2066a, i2);
    }

    public void g(int i2, WidgetGroup widgetGroup) {
        Iterator it = this.f2066a.iterator();
        while (it.hasNext()) {
            ConstraintWidget constraintWidget = (ConstraintWidget) it.next();
            widgetGroup.a(constraintWidget);
            if (i2 == 0) {
                constraintWidget.S0 = widgetGroup.c();
            } else {
                constraintWidget.T0 = widgetGroup.c();
            }
        }
        this.f2071f = widgetGroup.f2067b;
    }

    public void h(boolean z) {
        this.f2068c = z;
    }

    public void i(int i2) {
        this.f2069d = i2;
    }

    public String toString() {
        String str = e() + " [" + this.f2067b + "] <";
        Iterator it = this.f2066a.iterator();
        while (it.hasNext()) {
            str = str + " " + ((ConstraintWidget) it.next()).v();
        }
        return str + " >";
    }
}
