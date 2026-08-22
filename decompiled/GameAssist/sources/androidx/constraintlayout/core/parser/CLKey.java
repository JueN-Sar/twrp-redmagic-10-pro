package androidx.constraintlayout.core.parser;

import java.util.ArrayList;
import java.util.Objects;

/* loaded from: classes.dex */
public class CLKey extends CLContainer {

    /* renamed from: m, reason: collision with root package name */
    private static ArrayList f1898m;

    static {
        ArrayList arrayList = new ArrayList();
        f1898m = arrayList;
        arrayList.add("ConstraintSets");
        f1898m.add("Variables");
        f1898m.add("Generate");
        f1898m.add("Transitions");
        f1898m.add("KeyFrames");
        f1898m.add("KeyAttributes");
        f1898m.add("KeyPositions");
        f1898m.add("KeyCycles");
    }

    @Override // androidx.constraintlayout.core.parser.CLContainer, androidx.constraintlayout.core.parser.CLElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CLKey) || Objects.equals(l(), ((CLKey) obj).l())) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.parser.CLContainer, androidx.constraintlayout.core.parser.CLElement
    public int hashCode() {
        return super.hashCode();
    }

    public String l() {
        return f();
    }
}
