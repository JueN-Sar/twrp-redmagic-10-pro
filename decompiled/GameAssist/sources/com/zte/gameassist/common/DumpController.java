package com.zte.gameassist.common;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public final class DumpController {

    /* renamed from: b, reason: collision with root package name */
    private static DumpController f16483b;

    /* renamed from: a, reason: collision with root package name */
    private final List f16484a = new ArrayList();

    public interface Dump {
        void c(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr);
    }

    private DumpController() {
    }

    public static synchronized DumpController c() {
        DumpController dumpController;
        synchronized (DumpController.class) {
            try {
                if (f16483b == null) {
                    f16483b = new DumpController();
                }
                dumpController = f16483b;
            } catch (Throwable th) {
                throw th;
            }
        }
        return dumpController;
    }

    public void a(Dump dump) {
        int i2 = 0;
        while (true) {
            if (i2 >= this.f16484a.size()) {
                this.f16484a.add(new WeakReference(dump));
                break;
            } else if (this.f16484a.get(i2) == dump) {
                break;
            } else {
                i2++;
            }
        }
        d(null);
    }

    public void b(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println("  mDumps.size()=" + this.f16484a.size());
        Iterator it = this.f16484a.iterator();
        while (it.hasNext()) {
            Dump dump = (Dump) ((WeakReference) it.next()).get();
            if (dump != null) {
                dump.c(fileDescriptor, printWriter, strArr);
            }
        }
    }

    public void d(Dump dump) {
        int size = this.f16484a.size();
        int i2 = 0;
        while (i2 < size) {
            if (this.f16484a.get(i2) == dump) {
                this.f16484a.remove(i2);
                size--;
                i2--;
            }
            i2++;
        }
    }
}
