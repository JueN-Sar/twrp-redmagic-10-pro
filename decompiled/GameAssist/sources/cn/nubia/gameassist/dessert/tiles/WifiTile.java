package cn.nubia.gameassist.dessert.tiles;

import android.os.Bundle;
import cn.nubia.componentcenter.api.dessert.IWifiProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class WifiTile extends QSTile implements IModuleProxy.ICallback<IWifiProxy> {
    private final IWifiProxy v;
    private boolean w;

    public WifiTile(QSTile.Host host) {
        super(host);
        this.v = (IWifiProxy) host.a(IWifiProxy.class);
    }

    private void z0() {
        Bundle bundle = new Bundle();
        bundle.putString("switch", this.f6157m.f6175i ? "close" : "open");
        bundle.putString("app_name", SystemMgr.v());
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "wifi_switch_used", bundle);
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: A0, reason: merged with bridge method [inline-methods] */
    public void onChanged(IWifiProxy iWifiProxy) {
        o0();
    }

    public void B0(boolean z) {
        e0(null);
        if (this.f6157m.f6175i != z) {
            S();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        z0();
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
        boolean c2 = this.v.c();
        state.f6168b = QSTile.ResourceIcon.b(c2 ? R.drawable.game_ic_qs_wifi_light : R.drawable.game_ic_qs_wifi_normal);
        state.f6175i = c2;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_wifi_switch);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_wifi".equals(str)) {
            B0(true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_wifi_switch);
        } else if ("game_turn_off_wifi".equals(str)) {
            B0(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_wifi_switch);
        }
    }
}
