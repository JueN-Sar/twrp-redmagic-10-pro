package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class ConstraintSet {

    /* renamed from: a, reason: collision with root package name */
    private final String f1580a;

    /* renamed from: b, reason: collision with root package name */
    ArrayList f1581b;

    /* renamed from: c, reason: collision with root package name */
    ArrayList f1582c;

    public String toString() {
        StringBuilder sb = new StringBuilder(this.f1580a + ":{\n");
        if (!this.f1581b.isEmpty()) {
            Iterator it = this.f1581b.iterator();
            while (it.hasNext()) {
                sb.append(((Constraint) it.next()).toString());
            }
        }
        if (!this.f1582c.isEmpty()) {
            Iterator it2 = this.f1582c.iterator();
            while (it2.hasNext()) {
                sb.append(((Helper) it2.next()).toString());
            }
        }
        sb.append("},\n");
        return sb.toString();
    }
}
