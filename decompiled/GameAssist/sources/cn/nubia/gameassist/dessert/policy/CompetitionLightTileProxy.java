package cn.nubia.gameassist.dessert.policy;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.componentcenter.api.dessert.ICompetitionLightProxy;
import cn.nubia.gameassist.common.AbsTileProxy;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.utils.GameKeysHelper;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class CompetitionLightTileProxy extends AbsTileProxy implements ICompetitionLightProxy, PerformanceModeController.PerformanceModeCallback, ObserverManager.SettingCallback, GameMonitor.Callback {

    /* renamed from: l, reason: collision with root package name */
    private volatile int f6293l;

    /* renamed from: m, reason: collision with root package name */
    private PerformanceModeController f6294m;

    /* renamed from: n, reason: collision with root package name */
    final Runnable f6295n;

    public CompetitionLightTileProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f6293l = -1;
        this.f6295n = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.d
            @Override // java.lang.Runnable
            public final void run() {
                CompetitionLightTileProxy.this.u();
            }
        };
        o();
    }

    private void o() {
        SystemMgr.y(this.f16454h.a()).h(this);
        this.f6294m = PerformanceModeController.S();
        int a2 = SharedPreferencesUtil.k(this.f16454h.a()).a();
        GaLog.a(this.f16453c, "init before light " + a2);
        if (a2 > 0) {
            this.f6293l = a2;
        }
        ObserverManager.c().b(this.f16454h.a(), Settings.Global.getUriFor("switch_main_lamp_enable"), this);
        PerformanceModeController.S().P(this);
    }

    private boolean p(int i2) {
        return (i2 & 16) != 0;
    }

    private boolean q() {
        return 1 == Settings.Global.getInt(this.f16454h.a().getContentResolver(), "switch_main_lamp_enable", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u() {
        if (SystemMgr.H()) {
            j();
        }
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy, com.zte.gameassist.common.ITileProxy
    public boolean d() {
        if (m()) {
            return false;
        }
        int c2 = GameKeysHelper.b().c(this.f16454h.a());
        if (!q()) {
            Settings.Global.putInt(this.f16454h.a().getContentResolver(), "switch_main_lamp_enable", 1);
            GameKeysHelper.b().d(this.f16454h.a(), 16);
        } else if (p(c2)) {
            GameKeysHelper.b().a(this.f16454h.a(), 16);
        } else {
            GameKeysHelper.b().d(this.f16454h.a(), 16);
        }
        j();
        return super.d();
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy
    protected void k() {
        int c2 = GameKeysHelper.b().c(this.f16454h.a());
        GaLog.a(this.f16453c, "handleUpdateState: value : " + c2 + ",before light status:" + this.f6293l + ",d:" + m());
        if (m()) {
            if (this.f6293l == 1) {
                this.f6293l = c2;
                v();
            }
            GameKeysHelper.b().a(this.f16454h.a(), 16);
            this.f6111k = s(this.f6293l);
            this.f6110j = false;
        } else {
            if (this.f6293l > 1) {
                c2 |= this.f6293l;
                this.f6293l = 1;
                GameKeysHelper.b().e(this.f16454h.a(), c2);
                v();
            }
            this.f6111k = s(c2);
            this.f6110j = true;
        }
        if (this.f6293l == -1) {
            this.f6293l = 1;
            v();
        }
    }

    public boolean m() {
        return this.f6294m.isEconomizeMode();
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, int i2, boolean z) {
        GaLog.a(this.f16453c, "onPerformanceModeCallback:");
        j();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        GaLog.a(this.f16453c, "onGameSceneStateChanged:" + z);
        if (z) {
            this.f16454h.b().postDelayed(this.f6295n, 500L);
        } else {
            this.f16454h.b().removeCallbacks(this.f6295n);
        }
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void r(boolean z) {
        j();
    }

    public boolean s(int i2) {
        return p(i2) && q();
    }

    public void v() {
        SharedPreferencesUtil.k(this.f16454h.a()).K(this.f6293l);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.a(this.f16453c, "onChange:");
        j();
    }
}
