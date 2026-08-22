package cn.nubia.gameassist.power;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.SystemClock;
import cn.nubia.componentcenter.api.power.IPowerStateMonitor;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.AbsModuleProxy;
import com.zte.gameassist.common.IGameAssistCommander;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.ext.system.PowerStateMonitorProxy;
import com.zte.gameassist.ext.utils.RemoteList;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class PowerStateMonitor extends AbsModuleProxy<IPowerStateMonitor.PowerStateCallback> implements IPowerStateMonitor, IGameAssistCommander {

    /* renamed from: j, reason: collision with root package name */
    private int f7370j;

    /* renamed from: k, reason: collision with root package name */
    private DozeData f7371k;

    /* renamed from: l, reason: collision with root package name */
    private GlobalData f7372l;

    /* renamed from: m, reason: collision with root package name */
    private final RemoteList f7373m;

    /* renamed from: n, reason: collision with root package name */
    private final PowerManager f7374n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f7375o;

    /* renamed from: p, reason: collision with root package name */
    private boolean f7376p;

    public PowerStateMonitor(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f7373m = new RemoteList(PowerStateMonitorProxy.POWER_STATE_MONITOR);
        this.f7374n = (PowerManager) moduleProxyContext.a().getSystemService(PowerManager.class);
        SystemMgr.y(moduleProxyContext.a()).o(this);
        k();
    }

    private void k() {
        boolean isInteractive = this.f7374n.isInteractive();
        this.f7376p = isInteractive;
        this.f7375o = !isInteractive;
        if (isInteractive) {
            this.f7373m.addValue(PowerStateMonitorProxy.POWER_STATE_WAKEUP);
        } else {
            this.f7373m.addValue(PowerStateMonitorProxy.POWER_STATE_DOZE);
        }
    }

    private void l(Bundle bundle) {
        boolean z = bundle.getBoolean(PowerStateMonitorProxy.POWER_STATE_DOZE, false);
        int i2 = bundle.getInt("groupId", 0);
        int i3 = bundle.getInt("wakefulness", 0);
        DozeData dozeData = new DozeData(z, i2, i3, bundle.getLong("time", SystemClock.elapsedRealtime()));
        if (dozeData.equals(this.f7371k)) {
            return;
        }
        this.f7371k = dozeData;
        o(i3);
        if (this.f7375o != z) {
            this.f7375o = z;
            if (z) {
                this.f7373m.addValue(PowerStateMonitorProxy.POWER_STATE_DOZE);
            } else {
                this.f7373m.removeValue(PowerStateMonitorProxy.POWER_STATE_DOZE);
            }
        }
    }

    private void m(Bundle bundle) {
        int i2 = bundle.getInt("wakefulness");
        GlobalData globalData = new GlobalData(i2, bundle.getLong("eventTime"), bundle.getInt("uid"), bundle.getInt("reason"), bundle.getString("opPackageName"), bundle.getString("details"), bundle.getLong("time", SystemClock.elapsedRealtime()));
        if (!globalData.equals(this.f7372l)) {
            this.f7372l = globalData;
            o(i2);
        }
        boolean z = 1 == i2;
        if (this.f7376p != z) {
            this.f7376p = z;
            if (z) {
                this.f7373m.addValue(PowerStateMonitorProxy.POWER_STATE_WAKEUP);
            } else {
                this.f7373m.removeValue(PowerStateMonitorProxy.POWER_STATE_WAKEUP);
            }
        }
    }

    private synchronized void o(int i2) {
        if (i2 != this.f7370j) {
            this.f7370j = i2;
            j();
            GaLog.e("PowerStateMonitor", "update power state : " + PowerReason.a(i2));
        }
    }

    @Override // com.zte.gameassist.common.IGameAssistCommander, com.zte.gameassist.AbsGameAssistToken.ICommander
    public void executive(String str, Bundle bundle, AbsGameAssistToken.Callback callback) {
        if ("updateDoze".equals(str)) {
            l(bundle);
        } else if ("updateWakefulness".equals(str)) {
            m(bundle);
        }
    }

    @Override // cn.nubia.componentcenter.api.power.IPowerStateMonitor
    public int getPowerState() {
        return this.f7370j;
    }
}
