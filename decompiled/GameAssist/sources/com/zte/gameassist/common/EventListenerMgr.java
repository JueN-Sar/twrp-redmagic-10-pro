package com.zte.gameassist.common;

import android.util.SparseArray;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class EventListenerMgr {

    /* renamed from: a, reason: collision with root package name */
    private static SparseArray f16485a = new SparseArray();

    /* renamed from: b, reason: collision with root package name */
    private static SparseArray f16486b = new SparseArray();

    private static void a(EventListener eventListener, int i2, SparseArray sparseArray) {
        if (eventListener == null) {
            return;
        }
        ArrayList arrayList = (ArrayList) sparseArray.get(i2);
        if (arrayList != null) {
            if (arrayList.contains(eventListener)) {
                return;
            }
            arrayList.add(eventListener);
        } else {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(eventListener);
            sparseArray.put(i2, arrayList2);
        }
    }

    public static synchronized void b(EventListener eventListener, int i2) {
        synchronized (EventListenerMgr.class) {
            a(eventListener, i2, f16485a);
        }
    }

    public static void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("EventListenerMgr:");
        d(printWriter);
    }

    private static void d(PrintWriter printWriter) {
        printWriter.println("EventListenerMgr:sListeners");
        for (int i2 = 0; i2 < f16485a.size(); i2++) {
            int keyAt = f16485a.keyAt(i2);
            ArrayList arrayList = (ArrayList) f16485a.get(keyAt);
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    printWriter.println("    key:" + keyAt + " " + ((EventListener) it.next()));
                }
            }
        }
        printWriter.println("EventListenerMgr:sStaticListeners");
        for (int i3 = 0; i3 < f16486b.size(); i3++) {
            int keyAt2 = f16486b.keyAt(i3);
            ArrayList arrayList2 = (ArrayList) f16486b.get(keyAt2);
            if (arrayList2 != null) {
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    printWriter.println("    key:" + keyAt2 + " " + ((EventListener) it2.next()));
                }
            }
        }
    }

    public static synchronized void e(int i2) {
        synchronized (EventListenerMgr.class) {
            g(i2, new Object());
        }
    }

    private static void f(int i2, ArrayList arrayList, Object... objArr) {
        if (arrayList == null) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((EventListener) it.next()).a(i2, objArr);
        }
    }

    public static synchronized void g(int i2, Object... objArr) {
        synchronized (EventListenerMgr.class) {
            f(i2, (ArrayList) f16485a.get(i2), objArr);
            f(i2, (ArrayList) f16486b.get(i2), objArr);
        }
    }

    private static void h(EventListener eventListener, SparseArray sparseArray) {
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ArrayList) sparseArray.valueAt(i2)).remove(eventListener);
        }
    }

    public static synchronized void i(EventListener eventListener) {
        synchronized (EventListenerMgr.class) {
            h(eventListener, f16485a);
            h(eventListener, f16486b);
        }
    }
}
