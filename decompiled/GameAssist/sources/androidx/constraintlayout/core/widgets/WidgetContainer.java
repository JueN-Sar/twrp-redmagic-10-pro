package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class WidgetContainer extends ConstraintWidget {
    public ArrayList V0 = new ArrayList();

    public void A1() {
        this.V0.clear();
    }

    public void a(ConstraintWidget constraintWidget) {
        this.V0.add(constraintWidget);
        if (constraintWidget.M() != null) {
            ((WidgetContainer) constraintWidget.M()).z1(constraintWidget);
        }
        constraintWidget.h1(this);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void v0() {
        this.V0.clear();
        super.v0();
    }

    public void w1(ConstraintWidget... constraintWidgetArr) {
        for (ConstraintWidget constraintWidget : constraintWidgetArr) {
            a(constraintWidget);
        }
    }

    public ArrayList x1() {
        return this.V0;
    }

    public void y1() {
        ArrayList arrayList = this.V0;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) this.V0.get(i2);
            if (constraintWidget instanceof WidgetContainer) {
                ((WidgetContainer) constraintWidget).y1();
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void z0(Cache cache) {
        super.z0(cache);
        int size = this.V0.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ConstraintWidget) this.V0.get(i2)).z0(cache);
        }
    }

    public void z1(ConstraintWidget constraintWidget) {
        this.V0.remove(constraintWidget);
        constraintWidget.v0();
    }
}
