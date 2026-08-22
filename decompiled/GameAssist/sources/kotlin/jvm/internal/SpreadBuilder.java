package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class SpreadBuilder {

    /* renamed from: a, reason: collision with root package name */
    private final ArrayList f18568a;

    public SpreadBuilder(int i2) {
        this.f18568a = new ArrayList(i2);
    }

    public void a(Object obj) {
        this.f18568a.add(obj);
    }

    public void b(Object obj) {
        if (obj == null) {
            return;
        }
        if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length > 0) {
                ArrayList arrayList = this.f18568a;
                arrayList.ensureCapacity(arrayList.size() + objArr.length);
                Collections.addAll(this.f18568a, objArr);
                return;
            }
            return;
        }
        if (obj instanceof Collection) {
            this.f18568a.addAll((Collection) obj);
            return;
        }
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                this.f18568a.add(it.next());
            }
            return;
        }
        if (obj instanceof Iterator) {
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                this.f18568a.add(it2.next());
            }
        } else {
            throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
        }
    }

    public int c() {
        return this.f18568a.size();
    }

    public Object[] d(Object[] objArr) {
        return this.f18568a.toArray(objArr);
    }
}
