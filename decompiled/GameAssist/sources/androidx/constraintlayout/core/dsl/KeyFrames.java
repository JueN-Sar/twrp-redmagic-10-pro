package androidx.constraintlayout.core.dsl;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class KeyFrames {

    /* renamed from: a, reason: collision with root package name */
    ArrayList f1627a;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!this.f1627a.isEmpty()) {
            sb.append("keyFrames:{\n");
            Iterator it = this.f1627a.iterator();
            while (it.hasNext()) {
                sb.append(((Keys) it.next()).toString());
            }
            sb.append("},\n");
        }
        return sb.toString();
    }
}
