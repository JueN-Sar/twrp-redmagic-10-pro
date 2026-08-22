package cn.nubia.gameassist.dessert.tiles;

import cn.nubia.componentcenter.api.dessert.IPerformanceMonitorProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.PerformanceMonitorTileProxy;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PerformanceMonitorTile extends QSTile implements IModuleProxy.ICallback<IPerformanceMonitorProxy> {
    private final PerformanceMonitorTileProxy v;
    private boolean w;

    public PerformanceMonitorTile(QSTile.Host host) {
        super(host);
        PerformanceMonitorTileProxy performanceMonitorTileProxy = (PerformanceMonitorTileProxy) host.a(IPerformanceMonitorProxy.class);
        this.v = performanceMonitorTileProxy;
        performanceMonitorTileProxy.l(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        return this.v.d();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.w == z) {
            return;
        }
        this.w = z;
        this.v.setListening(z, this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.a("PerformanceMonitorTile", "handleUpdateState, mIsGameScene:" + this.f6162r + " tileEnable:" + this.v.b() + " mCurPackage:" + this.f6163s);
        if (this.f6162r && this.v.c()) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_performance_monitor_switch_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_performance_monitor_switch_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_performance_monitor);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_performance_monitor".equals(str)) {
            this.v.a(true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_performance_monitor);
        } else if ("game_turn_off_performance_monitor".equals(str)) {
            this.v.a(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_performance_monitor);
        }
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: z0, reason: merged with bridge method [inline-methods] */
    public void onChanged(IPerformanceMonitorProxy iPerformanceMonitorProxy) {
        o0();
    }
}
