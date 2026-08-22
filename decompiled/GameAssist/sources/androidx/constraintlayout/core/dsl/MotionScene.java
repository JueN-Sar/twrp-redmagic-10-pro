package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class MotionScene {

    /* renamed from: a, reason: collision with root package name */
    ArrayList f1644a;

    /* renamed from: b, reason: collision with root package name */
    ArrayList f1645b;

    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        if (!this.f1644a.isEmpty()) {
            sb.append("Transitions:{\n");
            Iterator it = this.f1644a.iterator();
            while (it.hasNext()) {
                sb.append(((Transition) it.next()).toString());
            }
            sb.append("},\n");
        }
        if (!this.f1645b.isEmpty()) {
            sb.append("ConstraintSets:{\n");
            Iterator it2 = this.f1645b.iterator();
            while (it2.hasNext()) {
                sb.append(((ConstraintSet) it2.next()).toString());
            }
            sb.append("},\n");
        }
        sb.append("}\n");
        return sb.toString();
    }
}
