package cn.nubia.gameassist.meditationmode.danmu.dispatch;

import android.os.SystemClock;
import cn.nubia.gameassist.meditationmode.danmu.BarrageController;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel;
import cn.nubia.gameassist.meditationmode.danmu.painter.BaseBarragePainter;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class BarrageConsumer {

    /* renamed from: k, reason: collision with root package name */
    private static final String f6632k = "BarrageConsumer";

    /* renamed from: b, reason: collision with root package name */
    BaseBarragePainter f6634b;

    /* renamed from: c, reason: collision with root package name */
    private final BarrageController f6635c;

    /* renamed from: d, reason: collision with root package name */
    private long f6636d;

    /* renamed from: e, reason: collision with root package name */
    protected long f6637e;

    /* renamed from: f, reason: collision with root package name */
    protected long f6638f;

    /* renamed from: g, reason: collision with root package name */
    protected int f6639g;

    /* renamed from: h, reason: collision with root package name */
    private int f6640h;

    /* renamed from: a, reason: collision with root package name */
    private volatile ArrayList f6633a = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private final Object f6642j = new Object();

    /* renamed from: i, reason: collision with root package name */
    private volatile boolean f6641i = true;

    public BarrageConsumer(BarrageController barrageController) {
        this.f6635c = barrageController;
        this.f6634b = barrageController.f().b();
        this.f6640h = a(barrageController.i());
    }

    private int a(int i2) {
        return 1000 / i2;
    }

    private void b() {
        synchronized (this.f6642j) {
            try {
                Iterator it = this.f6633a.iterator();
                while (it.hasNext()) {
                    ((BarrageModel) it.next()).M(false);
                }
                this.f6633a.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b4, code lost:
    
        r1 = r1 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.String r0 = "consumeDraw"
            cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog.a(r0)
            java.util.ArrayList r0 = r9.f6633a
            boolean r0 = r0.isEmpty()
            r1 = 0
            if (r0 == 0) goto L5d
            long r3 = java.lang.System.currentTimeMillis()
            long r5 = r9.f6636d
            int r10 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r10 != 0) goto L32
            r9.f6636d = r3
            java.lang.String r10 = cn.nubia.gameassist.meditationmode.danmu.dispatch.BarrageConsumer.f6632k
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "consume queue empty "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog.b(r10, r0)
            goto L59
        L32:
            long r5 = r3 - r5
            r7 = 5000(0x1388, double:2.4703E-320)
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 <= 0) goto L59
            java.lang.String r10 = cn.nubia.gameassist.meditationmode.danmu.dispatch.BarrageConsumer.f6632k
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r5 = "consume queue empty than 5s "
            r0.append(r5)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog.b(r10, r0)
            cn.nubia.gameassist.meditationmode.danmu.BarrageManager r10 = cn.nubia.gameassist.meditationmode.danmu.BarrageManager.r()
            r10.R()
            r9.f6636d = r1
        L59:
            r9.n()
            return
        L5d:
            r9.f6636d = r1
            cn.nubia.gameassist.meditationmode.danmu.BarrageController r0 = r9.f6635c
            cn.nubia.gameassist.meditationmode.danmu.model.BarrageConfig r0 = r0.f()
            int r0 = r0.g()
            r1 = 0
        L6a:
            java.util.ArrayList r2 = r9.f6633a
            int r2 = r2.size()
            int r2 = java.lang.Math.min(r2, r0)
            if (r1 >= r2) goto Lbb
            java.lang.Object r2 = r9.f6642j
            monitor-enter(r2)
            java.util.ArrayList r3 = r9.f6633a     // Catch: java.lang.Throwable -> Laa
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> Laa
            if (r3 != 0) goto Lb7
            boolean r3 = r9.g()     // Catch: java.lang.Throwable -> Laa
            if (r3 == 0) goto L88
            goto Lb7
        L88:
            java.util.ArrayList r3 = r9.f6633a     // Catch: java.lang.Throwable -> Laa
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> Laa
            cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel r3 = (cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel) r3     // Catch: java.lang.Throwable -> Laa
            boolean r4 = r3.x()     // Catch: java.lang.Throwable -> Laa
            if (r4 == 0) goto Lac
            int r4 = r9.f6640h     // Catch: java.lang.Throwable -> Laa
            r3.P(r4)     // Catch: java.lang.Throwable -> Laa
            r3.l()     // Catch: java.lang.Throwable -> Laa
            boolean r4 = r3.y()     // Catch: java.lang.Throwable -> Laa
            if (r4 == 0) goto Lb3
            cn.nubia.gameassist.meditationmode.danmu.painter.BaseBarragePainter r4 = r9.f6634b     // Catch: java.lang.Throwable -> Laa
            r4.a(r10, r3)     // Catch: java.lang.Throwable -> Laa
            goto Lb3
        Laa:
            r9 = move-exception
            goto Lb9
        Lac:
            java.util.ArrayList r3 = r9.f6633a     // Catch: java.lang.Throwable -> Laa
            r3.remove(r1)     // Catch: java.lang.Throwable -> Laa
            int r1 = r1 + (-1)
        Lb3:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> Laa
            int r1 = r1 + 1
            goto L6a
        Lb7:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> Laa
            goto Lbb
        Lb9:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> Laa
            throw r9
        Lbb:
            java.util.ArrayList r10 = r9.f6633a
            int r10 = r10.size()
            if (r10 >= r0) goto Lc6
            r9.n()
        Lc6:
            cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog.e()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.meditationmode.danmu.dispatch.BarrageConsumer.d(java.lang.Object):void");
    }

    public void c(Object obj) {
        if (g()) {
            return;
        }
        if (this.f6635c.l()) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.f6638f = elapsedRealtime;
            int i2 = (int) (elapsedRealtime - this.f6637e);
            this.f6639g = i2;
            if (i2 < this.f6640h) {
                SystemClock.sleep(r1 - i2);
            }
            this.f6637e = SystemClock.elapsedRealtime();
        }
        this.f6634b.f(obj);
        d(obj);
    }

    public void e(PrintWriter printWriter) {
        printWriter.println("BarrageConsumer:");
        printWriter.println("consume size:" + this.f6633a.size());
        printWriter.println("stop:" + this.f6641i);
    }

    public ArrayList f() {
        return this.f6633a;
    }

    public boolean g() {
        return this.f6641i;
    }

    public void h(int i2) {
        this.f6640h = a(i2);
        BarrageLog.b(f6632k, "onRefreshRateChange, refreshRate:" + i2 + " frameIntervalTime:" + this.f6640h);
    }

    public void i() {
        b();
    }

    public void j(int i2, int i3) {
        this.f6634b.e(i2, i3);
        Iterator it = this.f6633a.iterator();
        while (it.hasNext()) {
            ((BarrageModel) it.next()).H(i2, i3);
        }
    }

    public void k(ArrayList arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        this.f6633a.addAll(arrayList);
        this.f6641i = false;
    }

    public void l() {
        this.f6641i = true;
        b();
        n();
    }

    public synchronized boolean m() {
        if (this.f6633a.size() <= this.f6635c.f().g()) {
            return false;
        }
        BarrageLog.b(f6632k, "consume queue size > 10");
        try {
            wait();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        return true;
    }

    public synchronized void n() {
        notifyAll();
    }
}
