package cn.nubia.hostassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public interface HostMonitor {

    /* renamed from: a, reason: collision with root package name */
    public static final List f7828a = new ArrayList();

    public interface Callback {
        default void onHostStart(boolean z) {
        }

        default void onHostStop() {
        }
    }

    default void onHostStart(boolean z) {
        ArrayList arrayList = new ArrayList();
        List list = f7828a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onHostStart(z);
        }
    }

    default void onHostStop() {
        ArrayList arrayList = new ArrayList();
        List list = f7828a;
        synchronized (list) {
            arrayList.addAll(list);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).onHostStop();
        }
    }
}
