package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class Grouping {
    public static WidgetGroup a(ConstraintWidget constraintWidget, int i2, ArrayList arrayList, WidgetGroup widgetGroup) {
        int x1;
        int i3 = i2 == 0 ? constraintWidget.S0 : constraintWidget.T0;
        if (i3 != -1 && (widgetGroup == null || i3 != widgetGroup.c())) {
            int i4 = 0;
            while (true) {
                if (i4 >= arrayList.size()) {
                    break;
                }
                WidgetGroup widgetGroup2 = (WidgetGroup) arrayList.get(i4);
                if (widgetGroup2.c() == i3) {
                    if (widgetGroup != null) {
                        widgetGroup.g(i2, widgetGroup2);
                        arrayList.remove(widgetGroup);
                    }
                    widgetGroup = widgetGroup2;
                } else {
                    i4++;
                }
            }
        } else if (i3 != -1) {
            return widgetGroup;
        }
        if (widgetGroup == null) {
            if ((constraintWidget instanceof HelperWidget) && (x1 = ((HelperWidget) constraintWidget).x1(i2)) != -1) {
                int i5 = 0;
                while (true) {
                    if (i5 >= arrayList.size()) {
                        break;
                    }
                    WidgetGroup widgetGroup3 = (WidgetGroup) arrayList.get(i5);
                    if (widgetGroup3.c() == x1) {
                        widgetGroup = widgetGroup3;
                        break;
                    }
                    i5++;
                }
            }
            if (widgetGroup == null) {
                widgetGroup = new WidgetGroup(i2);
            }
            arrayList.add(widgetGroup);
        }
        if (widgetGroup.a(constraintWidget)) {
            if (constraintWidget instanceof Guideline) {
                Guideline guideline = (Guideline) constraintWidget;
                guideline.w1().c(guideline.x1() == 0 ? 1 : 0, arrayList, widgetGroup);
            }
            if (i2 == 0) {
                constraintWidget.S0 = widgetGroup.c();
                constraintWidget.Q.c(i2, arrayList, widgetGroup);
                constraintWidget.S.c(i2, arrayList, widgetGroup);
            } else {
                constraintWidget.T0 = widgetGroup.c();
                constraintWidget.R.c(i2, arrayList, widgetGroup);
                constraintWidget.U.c(i2, arrayList, widgetGroup);
                constraintWidget.T.c(i2, arrayList, widgetGroup);
            }
            constraintWidget.X.c(i2, arrayList, widgetGroup);
        }
        return widgetGroup;
    }

    private static WidgetGroup b(ArrayList arrayList, int i2) {
        int size = arrayList.size();
        for (int i3 = 0; i3 < size; i3++) {
            WidgetGroup widgetGroup = (WidgetGroup) arrayList.get(i3);
            if (i2 == widgetGroup.c()) {
                return widgetGroup;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:214:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x039d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0398  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer r16, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer r17) {
        /*
            Method dump skipped, instructions count: 932
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Grouping.c(androidx.constraintlayout.core.widgets.ConstraintWidgetContainer, androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measurer):boolean");
    }

    public static boolean d(ConstraintWidget.DimensionBehaviour dimensionBehaviour, ConstraintWidget.DimensionBehaviour dimensionBehaviour2, ConstraintWidget.DimensionBehaviour dimensionBehaviour3, ConstraintWidget.DimensionBehaviour dimensionBehaviour4) {
        ConstraintWidget.DimensionBehaviour dimensionBehaviour5;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour6;
        ConstraintWidget.DimensionBehaviour dimensionBehaviour7 = ConstraintWidget.DimensionBehaviour.FIXED;
        return (dimensionBehaviour3 == dimensionBehaviour7 || dimensionBehaviour3 == (dimensionBehaviour6 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour3 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour != dimensionBehaviour6)) || (dimensionBehaviour4 == dimensionBehaviour7 || dimensionBehaviour4 == (dimensionBehaviour5 = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) || (dimensionBehaviour4 == ConstraintWidget.DimensionBehaviour.MATCH_PARENT && dimensionBehaviour2 != dimensionBehaviour5));
    }
}
