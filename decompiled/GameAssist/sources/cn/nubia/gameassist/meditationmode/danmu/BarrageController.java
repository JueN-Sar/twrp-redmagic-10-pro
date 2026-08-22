package cn.nubia.gameassist.meditationmode.danmu;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.view.View;
import cn.nubia.gameassist.meditationmode.danmu.BarrageTouchHelper;
import cn.nubia.gameassist.meditationmode.danmu.dispatch.BarrageConsumer;
import cn.nubia.gameassist.meditationmode.danmu.dispatch.BarrageProducer;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageConfig;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageModel;
import cn.nubia.gameassist.meditationmode.danmu.model.BarrageStyle;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView;
import cn.nubia.systemwrapper.ActivityManagerWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public final class BarrageController implements BarrageStyle.SettingChangeCallback, BarrageTouchHelper.OnItemBarrageClickListener {

    /* renamed from: j, reason: collision with root package name */
    private static final String f6560j = "BarrageController";

    /* renamed from: a, reason: collision with root package name */
    private final Context f6561a;

    /* renamed from: d, reason: collision with root package name */
    private final IBarrageView f6564d;

    /* renamed from: f, reason: collision with root package name */
    private int f6566f;

    /* renamed from: g, reason: collision with root package name */
    private BarrageStyle f6567g;

    /* renamed from: i, reason: collision with root package name */
    private BarrageTouchHelper f6569i;

    /* renamed from: e, reason: collision with root package name */
    private boolean f6565e = false;

    /* renamed from: h, reason: collision with root package name */
    private final ExecutorService f6568h = Executors.newSingleThreadExecutor();

    /* renamed from: b, reason: collision with root package name */
    private final BarrageConsumer f6562b = new BarrageConsumer(this);

    /* renamed from: c, reason: collision with root package name */
    private final BarrageProducer f6563c = new BarrageProducer(this);

    /* JADX WARN: Multi-variable type inference failed */
    public BarrageController(View view) {
        this.f6561a = view.getContext();
        this.f6564d = (IBarrageView) view;
        k();
    }

    private void k() {
        BarrageLog.b(f6560j, "init initInput");
        if (this.f6569i == null) {
            this.f6569i = new BarrageTouchHelper(this.f6561a, this);
        }
        this.f6569i.d(this);
    }

    private void q() {
        if (m()) {
            return;
        }
        BarrageLog.b(f6560j, "start producer looper");
        this.f6568h.execute(this.f6563c);
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.model.BarrageStyle.SettingChangeCallback
    public void a() {
        this.f6562b.i();
        this.f6563c.k();
        BarrageManager.r().I();
    }

    @Override // cn.nubia.gameassist.meditationmode.danmu.BarrageTouchHelper.OnItemBarrageClickListener
    public void b(BarrageModel barrageModel) {
        String str = f6560j;
        BarrageLog.b(str, "onItemClick: model:" + barrageModel);
        String i2 = barrageModel.i();
        String f2 = barrageModel.f();
        String a2 = barrageModel.a();
        PendingIntent d2 = barrageModel.d();
        if (BarrageManager.r().z(f2, a2)) {
            BarrageLog.b(str, "push notification, pkgName:" + f2 + " targetPkgName:" + i2 + " channelId:" + a2);
            try {
                Intent intent = new Intent();
                intent.putExtra("start_mini_window", true);
                intent.putExtra("call_from", 2);
                d2.send(this.f6561a, 0, intent, null, null, null, null);
            } catch (Exception e2) {
                BarrageLog.c(f6560j, e2.toString());
            }
        } else if (d2 == null) {
            ActivityManagerWrapper.b().h(this.f6561a.getPackageManager().getLaunchIntentForPackage(f2), this.f6561a);
        } else {
            ActivityManagerWrapper.b().i(d2, this.f6561a);
        }
        BarrageLog.b(f6560j, "start launch danmu window free form !");
    }

    public void c(BarrageModel barrageModel) {
        this.f6563c.b(barrageModel);
    }

    public void d(Object obj) {
        this.f6562b.c(obj);
    }

    public void e(PrintWriter printWriter) {
        printWriter.println("BarrageController:");
        if (this.f6567g != null) {
            printWriter.println("mBarrageStyle:" + this.f6567g);
        }
        printWriter.println();
        this.f6562b.e(printWriter);
        this.f6563c.g(printWriter);
    }

    public BarrageConfig f() {
        if (this.f6564d.getBarrageConfig() != null) {
            return this.f6564d.getBarrageConfig();
        }
        throw new SecurityException("barrage config not null");
    }

    public BarrageConsumer g() {
        return this.f6562b;
    }

    public BarrageStyle h() {
        return this.f6567g;
    }

    public int i() {
        int i2 = this.f6566f;
        return i2 == 0 ? (int) ((DisplayManager) BarrageFactory.a().getSystemService("display")).getDisplay(0).getRefreshRate() : i2;
    }

    public void j(int i2, int i3, int[] iArr, BarrageConfig barrageConfig) {
        String str = f6560j;
        BarrageLog.b(str, "init channel [" + i2 + "," + i3 + "]");
        if (this.f6565e) {
            return;
        }
        BarrageStyle barrageStyle = new BarrageStyle();
        this.f6567g = barrageStyle;
        barrageStyle.q();
        this.f6567g.b(this);
        this.f6563c.f(i2, i3, barrageConfig, this.f6567g);
        q();
        this.f6565e = true;
        BarrageLog.b(str, "addMotionEventListener");
        this.f6569i.g(iArr[1]);
        this.f6569i.c();
    }

    public boolean l() {
        return BarrageManager.r().x();
    }

    public boolean m() {
        return !this.f6563c.i();
    }

    public void n() {
        this.f6564d.f();
    }

    public void o(int i2) {
        this.f6566f = i2;
        this.f6562b.h(i2);
    }

    public void p(int i2, int i3) {
        BarrageLog.b(f6560j, "size change w:" + i2 + ",height:" + i3);
        this.f6563c.l(i2, i3);
        this.f6562b.j(i2, i3);
    }

    public void r(ArrayList arrayList) {
        this.f6562b.k(arrayList);
    }

    public void s() {
        this.f6563c.m();
        this.f6562b.l();
        this.f6565e = false;
        BarrageStyle barrageStyle = this.f6567g;
        if (barrageStyle != null) {
            barrageStyle.r();
        }
        BarrageLog.b(f6560j, "removeMotionEventListener");
        this.f6569i.h();
    }

    public void t(int[] iArr) {
        this.f6569i.g(iArr[1]);
    }

    public boolean u() {
        return this.f6562b.m();
    }
}
