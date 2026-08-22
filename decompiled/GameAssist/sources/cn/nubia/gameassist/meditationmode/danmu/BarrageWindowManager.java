package cn.nubia.gameassist.meditationmode.danmu;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import cn.nubia.gameassist.meditationmode.danmu.view.IBarrageView;
import cn.nubia.gameassist.utils.WindowManagerUtil;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import cn.nubia.systemwrapper.ActivityManagerWrapper;
import com.google.android.material.card.MaterialCardView;
import com.zte.gameassist.common.DisplayMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class BarrageWindowManager implements GameMonitor.Callback, DisplayMgr.Callback {

    /* renamed from: s, reason: collision with root package name */
    private static final String f6600s = "BarrageWindowManager";

    /* renamed from: c, reason: collision with root package name */
    private WindowManagerUtil f6601c;

    /* renamed from: i, reason: collision with root package name */
    private Context f6603i;

    /* renamed from: j, reason: collision with root package name */
    private FrameLayout f6604j;

    /* renamed from: k, reason: collision with root package name */
    private IBarrageView f6605k;

    /* renamed from: l, reason: collision with root package name */
    private WindowManager.LayoutParams f6606l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f6607m;

    /* renamed from: n, reason: collision with root package name */
    private boolean f6608n;

    /* renamed from: p, reason: collision with root package name */
    private int f6610p;

    /* renamed from: q, reason: collision with root package name */
    private PreBarrageController f6611q;

    /* renamed from: r, reason: collision with root package name */
    private final Runnable f6612r = new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.g
        @Override // java.lang.Runnable
        public final void run() {
            BarrageWindowManager.this.u();
        }
    };

    /* renamed from: h, reason: collision with root package name */
    private DisplayManager f6602h = (DisplayManager) BarrageFactory.a().getSystemService("display");

    /* renamed from: o, reason: collision with root package name */
    private int f6609o = g();

    public BarrageWindowManager(Context context) {
        this.f6603i = context;
        this.f6601c = new WindowManagerUtil((WindowManager) context.getSystemService(WindowManager.class));
    }

    private void d() {
        if (this.f6607m) {
            return;
        }
        if (this.f6604j == null) {
            h();
        }
        try {
            BarrageLog.b(f6600s, "attach barrage window");
            this.f6601c.a(this.f6604j, this.f6606l);
            this.f6607m = true;
            this.f6605k.a();
        } catch (Exception e2) {
            BarrageLog.b(f6600s, "attach barrage window error:" + e2);
        }
        n();
    }

    private void e() {
        BarrageFactory.b().post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.f
            @Override // java.lang.Runnable
            public final void run() {
                BarrageWindowManager.this.j();
            }
        });
    }

    private int g() {
        int r2 = GameRatioMgr.q().r();
        BarrageLog.b(f6600s, "getBarrageLocation, gameRatioPosition:" + r2);
        return r2 > 0 ? (r2 == 3 || r2 == 2) ? 0 : 1 : Settings.Global.getInt(this.f6603i.getContentResolver(), "gsc_barrage_message_location", 0);
    }

    private void h() {
        FrameLayout frameLayout = (FrameLayout) View.inflate(this.f6603i, R.layout.nubia_danmu, null);
        this.f6604j = frameLayout;
        this.f6605k = (IBarrageView) frameLayout.findViewById(R.id.barrage_view);
        WindowManager.LayoutParams d2 = ActivityManagerWrapper.b().d();
        this.f6606l = d2;
        d2.flags |= 16777232;
        d2.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END;
        if (g() == 0) {
            this.f6606l.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END;
        } else {
            this.f6606l.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_BOTTOM_END;
        }
        this.f6606l.setTitle("NubiaDanmu");
        WindowManager.LayoutParams layoutParams = this.f6606l;
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.width = -1;
        layoutParams.packageName = this.f6603i.getPackageName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j() {
        BarrageLog.b(f6600s, "detach barrage window " + this.f6607m);
        if (this.f6607m) {
            try {
                try {
                    this.f6601c.b(this.f6604j);
                } catch (Exception e2) {
                    BarrageLog.b(f6600s, "detach barrage window error:" + e2);
                }
                t();
            } finally {
                this.f6607m = false;
                this.f6604j = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void k() {
        this.f6605k.d();
    }

    private void n() {
        BarrageLog.b(f6600s, "registerCallback...");
        DisplayMgr.d().a(this);
        onDisplayChanged(0);
    }

    private void t() {
        BarrageLog.b(f6600s, "unRegisterCallback...");
        DisplayMgr.d().f(this);
        BarrageFactory.b().removeCallbacks(this.f6612r);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        int g2 = g();
        if (this.f6609o != g2) {
            BarrageLog.b(f6600s, "updateBarrageLocation, currentLocation:" + g2 + " mLastBarrageLocation:" + this.f6609o);
            this.f6609o = g2;
            w();
        }
    }

    private void v() {
        BarrageFactory.b().removeCallbacks(this.f6612r);
        BarrageFactory.b().postDelayed(this.f6612r, 50L);
    }

    private void w() {
        BarrageLog.b(f6600s, "updateWindowLp, getBarrageLocation(): " + g() + " mAttachToWindow:" + this.f6607m);
        if (this.f6607m) {
            if (g() == 0) {
                this.f6606l.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_TOP_END;
            } else {
                this.f6606l.gravity = MaterialCardView.CHECKED_ICON_GRAVITY_BOTTOM_END;
            }
            this.f6601c.c(this.f6604j, this.f6606l);
            this.f6604j.post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.h
                @Override // java.lang.Runnable
                public final void run() {
                    BarrageWindowManager.this.k();
                }
            });
        }
    }

    public void f(PrintWriter printWriter) {
        printWriter.println("BarrageWindowManager:");
        printWriter.println("mAttachToWindow:" + this.f6607m);
        printWriter.println("");
        PreBarrageController preBarrageController = this.f6611q;
        if (preBarrageController != null) {
            preBarrageController.e(printWriter);
            printWriter.println("");
        }
        IBarrageView iBarrageView = this.f6605k;
        if (iBarrageView != null) {
            iBarrageView.b(printWriter);
        }
    }

    public boolean i() {
        PreBarrageController preBarrageController = this.f6611q;
        if (preBarrageController != null) {
            return preBarrageController.h();
        }
        return false;
    }

    public void l(int i2) {
        BarrageLog.b(f6600s, "onGameRatioChanged, location:" + i2);
        v();
    }

    public void m() {
        v();
        PreBarrageController preBarrageController = this.f6611q;
        if (preBarrageController != null) {
            preBarrageController.i();
        }
    }

    public void o(DanmuNotificationBean danmuNotificationBean) {
        BarrageLog.b(f6600s, "shotBarrage");
        if (!p()) {
            d();
            if (!this.f6608n) {
                SystemMgr.y(this.f6603i).h(this);
            }
        }
        BarrageLog.a("addBarrage");
        this.f6605k.e(danmuNotificationBean);
        BarrageLog.e();
    }

    @Override // com.zte.gameassist.common.DisplayMgr.Callback
    public void onDisplayChanged(int i2) {
        int refreshRate;
        if (i2 != 0 || (refreshRate = (int) this.f6602h.getDisplay(i2).getRefreshRate()) == this.f6610p) {
            return;
        }
        this.f6610p = refreshRate;
        BarrageLog.c(f6600s, "onDisplayChanged, refreshRate:" + refreshRate + " displayId:" + i2);
        IBarrageView iBarrageView = this.f6605k;
        if (iBarrageView != null) {
            iBarrageView.c(refreshRate);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        r();
        SystemMgr.y(this.f6603i).i(this);
    }

    public boolean p() {
        return this.f6607m;
    }

    public void q() {
        BarrageLog.b(f6600s, "startPreBarrage");
        if (!p()) {
            d();
        }
        this.f6608n = true;
        if (this.f6611q == null) {
            PreBarrageController preBarrageController = new PreBarrageController(this.f6603i);
            this.f6611q = preBarrageController;
            preBarrageController.j();
        }
    }

    public void r() {
        BarrageLog.b(f6600s, "stopBarrage");
        e();
    }

    public void s() {
        BarrageLog.b(f6600s, "stopPreBarrage");
        PreBarrageController preBarrageController = this.f6611q;
        if (preBarrageController != null) {
            preBarrageController.k();
            this.f6611q = null;
        }
        this.f6608n = false;
        r();
    }
}
