package cn.nubia.gameassist.meditationmode.danmu.dispatch;

import android.os.Process;
import android.os.SystemClock;
import cn.nubia.gameassist.meditationmode.danmu.BarrageController;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageChannel;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageConfig;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageStyle;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class BarrageProducer implements Runnable {

    /* renamed from: m, reason: collision with root package name */
    private static final String f6643m = "BarrageProducer";

    /* renamed from: k, reason: collision with root package name */
    private final BarrageController f6648k;

    /* renamed from: c, reason: collision with root package name */
    private volatile ArrayDeque f6644c = new ArrayDeque();

    /* renamed from: h, reason: collision with root package name */
    private final Object f6645h = new Object();

    /* renamed from: i, reason: collision with root package name */
    private final Random f6646i = new Random();

    /* renamed from: j, reason: collision with root package name */
    private final ArrayList f6647j = new ArrayList();

    /* renamed from: l, reason: collision with root package name */
    private volatile boolean f6649l = true;

    public BarrageProducer(BarrageController barrageController) {
        this.f6648k = barrageController;
    }

    private void c() {
        this.f6644c.clear();
    }

    private int h() {
        if (GameRatioMgr.q().r() > 0) {
            return 0;
        }
        return this.f6646i.nextInt(this.f6647j.size());
    }

    public void b(BarrageModel barrageModel) {
        this.f6644c.addLast(barrageModel);
        o();
    }

    public synchronized ArrayList d() {
        ArrayList arrayList;
        try {
            BarrageLog.a("dispatchBarrage");
            arrayList = new ArrayList();
            int min = Math.min(this.f6644c.size(), this.f6648k.f().g());
            for (int i2 = 0; i2 < min && !this.f6644c.isEmpty() && !i(); i2++) {
                BarrageModel barrageModel = (BarrageModel) this.f6644c.pop();
                e(barrageModel);
                if (barrageModel.m()) {
                    arrayList.add(barrageModel);
                } else {
                    BarrageLog.b(f6643m, "create barrage fail " + barrageModel);
                }
            }
            BarrageLog.e();
        } catch (Throwable th) {
            throw th;
        }
        return arrayList;
    }

    public void e(BarrageModel barrageModel) {
        if (barrageModel.y() || this.f6647j.size() <= 0) {
            return;
        }
        barrageModel.O((BarrageChannel) this.f6647j.get(h()));
    }

    public void f(int i2, int i3, BarrageConfig barrageConfig, BarrageStyle barrageStyle) {
        int d2 = this.f6648k.f().d();
        int i4 = i3 / d2;
        BarrageLog.b(f6643m, "divide count:" + i4 + " width:" + i2 + " height:" + i3 + " singleHeight:" + d2);
        for (int i5 = 0; i5 < i4; i5++) {
            BarrageChannel barrageChannel = new BarrageChannel();
            barrageChannel.i(i2);
            barrageChannel.k(i3);
            barrageChannel.j(i5 * d2);
            barrageChannel.g(barrageConfig);
            barrageChannel.h(barrageStyle);
            this.f6647j.add(barrageChannel);
        }
    }

    public void g(PrintWriter printWriter) {
        printWriter.println("BarrageProducer:");
        printWriter.println("product size:" + this.f6644c.size());
        printWriter.println("stop:" + i());
    }

    public boolean i() {
        return this.f6649l;
    }

    public void k() {
        this.f6647j.forEach(new Consumer() { // from class: d.b
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BarrageChannel) obj).f();
            }
        });
    }

    public void l(final int i2, int i3) {
        this.f6647j.forEach(new Consumer() { // from class: d.a
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((BarrageChannel) obj).i(i2);
            }
        });
    }

    public void m() {
        this.f6649l = true;
        c();
        o();
        this.f6647j.clear();
    }

    public boolean n() {
        synchronized (this.f6645h) {
            try {
                if (!this.f6644c.isEmpty() || this.f6649l) {
                    return false;
                }
                try {
                    this.f6645h.wait();
                    return true;
                } catch (InterruptedException e2) {
                    throw new RuntimeException(e2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void o() {
        synchronized (this.f6645h) {
            this.f6645h.notifyAll();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        Process.setThreadPriority(-8);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.f6649l = false;
        BarrageLog.b(f6643m, "product barrage thread start");
        while (!this.f6649l) {
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            long j2 = elapsedRealtime2 - elapsedRealtime;
            if (j2 < 10) {
                SystemClock.sleep(10 - j2);
                elapsedRealtime2 = SystemClock.elapsedRealtime();
            }
            if (n()) {
                elapsedRealtime2 = SystemClock.elapsedRealtime();
                BarrageLog.b(f6643m, "wait dispatcher finish " + elapsedRealtime2);
            }
            if (this.f6648k.u()) {
                elapsedRealtime = SystemClock.elapsedRealtime();
                BarrageLog.b(f6643m, "wait consumer finish " + elapsedRealtime);
            } else {
                elapsedRealtime = elapsedRealtime2;
            }
            ArrayList d2 = d();
            if (d2 != null && d2.size() > 0 && !i()) {
                BarrageLog.b(f6643m, "product barrage size:" + d2.size());
                this.f6648k.r(d2);
            }
        }
        BarrageLog.b(f6643m, "product barrage thread end");
    }
}
