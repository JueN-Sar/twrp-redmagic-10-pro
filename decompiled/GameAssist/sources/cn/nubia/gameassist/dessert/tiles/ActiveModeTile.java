package cn.nubia.gameassist.dessert.tiles;

import android.provider.Settings;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.ActiveModeController;
import cn.nubia.gameassist.install.InstallListener;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.DisplayMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class ActiveModeTile extends QSTile implements InstallListener, GameMonitor.Callback, DisplayMgr.Callback {
    private String A;
    private int B;
    private Runnable C;
    private ActiveModeController v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public ActiveModeTile(QSTile.Host host) {
        super(host);
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = false;
        this.A = null;
        this.B = -1;
        this.C = new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.ActiveModeTile.1
            @Override // java.lang.Runnable
            public void run() {
                if (SystemMgr.K(ActiveModeTile.this.A, ActiveModeTile.this.v.f())) {
                    GaLog.a("ActiveModeTile", " mToggleSwitch: already freeform, return");
                    return;
                }
                if (SystemMgr.I()) {
                    GaLog.a("ActiveModeTile", " mToggleSwitch: current is keyguard show, return");
                    return;
                }
                boolean v = ActiveModeTile.this.v.v(ActiveModeTile.this.B, 2);
                if (v) {
                    ActiveModeTile.this.v.t(ActiveModeTile.this.A, ActiveModeTile.this.v.d(ActiveModeTile.this.A) + 1);
                }
                GaLog.a("ActiveModeTile", " mDelayToggleSwitch: result= " + v + " pkg=" + ActiveModeTile.this.v.e(ActiveModeTile.this.A) + " mTaskId=" + ActiveModeTile.this.B + " TotalNum=" + ActiveModeTile.this.v.d(ActiveModeTile.this.A));
            }
        };
        this.v = new ActiveModeController(this.f6153i);
        SystemMgr.y(this.f6153i).h(this);
        DisplayMgr.d().a(this);
        GameAssistApplication.j().f(this);
        this.v.p();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E0() {
        this.z = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F0(String str) {
        if (this.v.g()) {
            return;
        }
        GaLog.a("ActiveModeTile", " onLauncherFirstPackage packageName=" + this.v.e(str));
        this.v.m(str);
        this.v.c();
    }

    private void H0() {
        this.v.w();
        this.A = SystemMgr.v();
        this.B = SystemMgr.B;
        GaLog.a("ActiveModeTile", " updateCurrApp: mCurrPkg=" + this.v.e(this.A) + " mTaskId=" + this.B);
        this.w = this.v.j(this.A);
        this.x = this.v.h(this.A);
        if (this.w) {
            this.v.b();
        } else {
            this.v.l();
        }
        this.v.s(this.x);
    }

    public void G0(boolean z) {
        e0(null);
        if (this.f6157m.f6175i != z) {
            S();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void J(PrintWriter printWriter, String str) {
        super.J(printWriter, str);
        printWriter.println("ActiveModeTile:   ");
        printWriter.println("mFreeFormOpen = " + this.x);
        printWriter.println("mWakeLockOpen = " + this.w);
        printWriter.println("mSupportFreeForm = " + this.y);
        printWriter.println("mTaskId = " + this.B);
        printWriter.println("mCurrPkg = " + this.v.e(this.A));
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.w = this.v.j(this.A);
        this.x = this.v.h(this.A);
        this.y = this.v.i(this.A, this.B);
        GaLog.a("ActiveModeTile", " handleClick  mSupportFreeForm = " + this.y + ", mFreeFormOpen = " + this.x + ", mWakeLockOpen = " + this.w + ", mTaskId = " + this.B);
        if (this.y) {
            if (this.x) {
                this.v.m(this.A);
                this.v.l();
                ActiveModeController activeModeController = this.v;
                String str = this.A;
                activeModeController.r(str, activeModeController.d(str));
                GaLog.a("ActiveModeTile", " handleClick close activeMode free form and wack lock mCurrPkg=" + this.v.e(this.A));
            } else {
                this.v.t(this.A, 0);
                this.v.u(this.A, 0);
                this.v.b();
                ToastUtil.a(this.f6153i.getString(R.string.ic_qs_active_mode_open_text_new));
                GaLog.a("ActiveModeTile", " handleClick open activeMode free form and wack lock mCurrPkg=" + this.v.e(this.A));
            }
        } else if (this.w) {
            this.v.o(this.A);
            this.v.l();
            GaLog.a("ActiveModeTile", " handleClick  close activeMode wack lock mCurrPkg=" + this.v.e(this.A));
        } else {
            this.v.u(this.A, 0);
            this.v.b();
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_active_mode_support_text));
            GaLog.a("ActiveModeTile", " handleClick  open activeMode wack lock mCurrPkg=" + this.v.e(this.A));
        }
        o0();
        this.v.s(this.v.h(this.A));
        this.v.c();
        return super.S();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        this.w = this.v.j(this.A);
        boolean h2 = this.v.h(this.A);
        this.x = h2;
        if (h2 || this.w) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_activemode_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_activemode_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_active_mode);
    }

    @Override // cn.nubia.gameassist.install.InstallListener
    public void f(String str) {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_active_mode".equals(str)) {
            G0(true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_active_mode);
        } else if ("game_turn_off_active_mode".equals(str)) {
            G0(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_active_mode);
        }
    }

    @Override // com.zte.gameassist.common.DisplayMgr.Callback
    public void on3DDisplayAdded(int i2) {
        GaLog.a("ActiveModeTile", " on3DDisplayAdded");
        this.z = true;
        this.f6154j.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.a
            @Override // java.lang.Runnable
            public final void run() {
                ActiveModeTile.this.E0();
            }
        }, 500L);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        GaLog.a("ActiveModeTile", " onGameStart");
        H0();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        if (this.z) {
            GaLog.a("ActiveModeTile", " onGameStop m3DSwitching");
            this.z = false;
            return;
        }
        this.y = this.v.i(this.A, this.B);
        this.x = this.v.h(this.A);
        boolean contains = SystemMgr.u.contains("com.android.quickstep.SplitActivity");
        boolean contains2 = SystemMgr.u.contains("cn.nubia.multisubscreen.ui.MultiSubScreenSourceActivity");
        GaLog.a("ActiveModeTile", "  onGameStop: mSupportFreeForm=" + this.y + " mFreeFormOpen=" + this.x + " mTaskId=" + this.B + " isSplitScreen=" + contains + " isMultiSubScreen=" + contains2);
        if (this.y && this.x && this.B != -1 && !contains && !contains2) {
            this.f6155k.removeCallbacks(this.C);
            this.f6155k.post(this.C);
        }
        this.v.l();
        if (Settings.Global.getInt(this.f6153i.getContentResolver(), "active_mode_on", 0) == 1) {
            this.v.s(false);
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        GaLog.a("ActiveModeTile", " onGameUpdate");
        H0();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    public void onLauncherFirstPackage(final String str) {
        this.f6154j.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.tiles.b
            @Override // java.lang.Runnable
            public final void run() {
                ActiveModeTile.this.F0(str);
            }
        }, 200L);
    }

    @Override // cn.nubia.gameassist.install.InstallListener
    public void x(String str) {
        GaLog.a("ActiveModeTile", " uninstall");
        this.v.m(str);
        this.v.c();
    }
}
