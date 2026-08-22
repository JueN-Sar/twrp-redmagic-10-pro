package androidx.constraintlayout.core.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes.dex */
public class CLContainer extends CLElement {

    /* renamed from: l, reason: collision with root package name */
    ArrayList f1892l;

    @Override // androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CLContainer) {
            return this.f1892l.equals(((CLContainer) obj).f1892l);
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        return Objects.hash(this.f1892l, Integer.valueOf(super.hashCode()));
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public CLContainer clone() {
        CLContainer cLContainer = (CLContainer) super.clone();
        ArrayList arrayList = new ArrayList(this.f1892l.size());
        Iterator it = this.f1892l.iterator();
        while (it.hasNext()) {
            CLElement clone = ((CLElement) it.next()).clone();
            clone.j(cLContainer);
            arrayList.add(clone);
        }
        cLContainer.f1892l = arrayList;
        return cLContainer;
    }

    public int size() {
        return this.f1892l.size();
    }

    @Override // androidx.constraintlayout.core.parser.CLElement
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.f1892l.iterator();
        while (it.hasNext()) {
            CLElement cLElement = (CLElement) it.next();
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append(cLElement);
        }
        return super.toString() + " = <" + ((Object) sb) + " >";
    }
}
