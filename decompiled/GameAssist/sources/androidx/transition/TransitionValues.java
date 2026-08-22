package androidx.transition;

import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TransitionValues {

    /* renamed from: b, reason: collision with root package name */
    public View f5571b;

    /* renamed from: a, reason: collision with root package name */
    public final Map f5570a = new HashMap();

    /* renamed from: c, reason: collision with root package name */
    final ArrayList f5572c = new ArrayList();

    public TransitionValues(View view) {
        this.f5571b = view;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TransitionValues)) {
            return false;
        }
        TransitionValues transitionValues = (TransitionValues) obj;
        return this.f5571b == transitionValues.f5571b && this.f5570a.equals(transitionValues.f5570a);
    }

    public int hashCode() {
        return (this.f5571b.hashCode() * 31) + this.f5570a.hashCode();
    }

    public String toString() {
        String str = (("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n") + "    view = " + this.f5571b + "\n") + "    values:";
        for (String str2 : this.f5570a.keySet()) {
            str = str + "    " + str2 + ": " + this.f5570a.get(str2) + "\n";
        }
        return str;
    }
}
