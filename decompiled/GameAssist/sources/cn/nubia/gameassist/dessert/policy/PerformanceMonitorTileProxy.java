package cn.nubia.gameassist.dessert.policy;

import android.content.Context;
import android.content.SharedPreferences;
import cn.nubia.componentcenter.api.dessert.IPerformanceMonitorProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.AbsTileProxy;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.performancemonitor.PerformanceMonitorController;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class PerformanceMonitorTileProxy extends AbsTileProxy implements IPerformanceMonitorProxy, GameMonitor.Callback, SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: l, reason: collision with root package name */
    private Context f6297l;

    /* renamed from: m, reason: collision with root package name */
    private QSTile.Host f6298m;

    public PerformanceMonitorTileProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f6297l = moduleProxyContext.a();
    }

    @Override // com.zte.gameassist.common.ITileProxy
    public void a(boolean z) {
        if ((SharedPreferencesUtil.k(this.f6297l).l("spf_performance_monitor", 0) == 1) != z) {
            m(!z ? 1 : 0);
        }
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy, com.zte.gameassist.common.ITileProxy
    public boolean d() {
        super.d();
        int l2 = SharedPreferencesUtil.k(this.f6297l).l("spf_performance_monitor", 0);
        GaLog.a(this.f16453c, "handleClick, isGameScene:" + SystemMgr.H() + " value:" + l2);
        m(l2);
        return false;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void g() {
        SharedPreferencesUtil.k(this.f6297l).J(this);
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void i() {
        SharedPreferencesUtil.k(this.f6297l).i0(this);
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy
    protected void k() {
        this.f6111k = SharedPreferencesUtil.k(this.f6297l).l("spf_performance_monitor", 0) == 1;
    }

    public void l(QSTile.Host host) {
        this.f6298m = host;
    }

    public void m(int i2) {
        if (!SystemMgr.H()) {
            ToastUtil.a(this.f6297l.getString(R.string.toast_unsupport_app));
            return;
        }
        if (!Utils.c(this.f6297l)) {
            GaLog.a(this.f16453c, "handleClick no overlay permission ");
            return;
        }
        if (i2 == 1) {
            SharedPreferencesUtil.k(this.f6297l).U("spf_performance_monitor", 0);
            PerformanceMonitorController.getInstance(this.f6297l).unregister(true);
        } else {
            this.f6298m.b();
            SharedPreferencesUtil.k(this.f6297l).U("spf_performance_monitor", 1);
            PerformanceMonitorController.getInstance(this.f6297l).register(true);
        }
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        j();
    }
}
