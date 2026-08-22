package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.fan.FanViewAnimationController;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class FanTile extends QSTile implements ObserverManager.SettingCallback {
    public FanTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        Settings.System.putInt(this.f6153i.getContentResolver(), "fan_state_of_manual", this.f6157m.f6175i ? -3 : 3);
        if (!this.f6157m.f6175i) {
            this.f6152h.b();
            FanViewAnimationController.b(this.f6153i).d(this.f6153i);
        }
        NubiaTrackManager.p().C(O(), !this.f6157m.f6175i);
        NubiaTrackManager.p().n(!this.f6157m.f6175i);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.System.getUriFor("fan_state_of_manual"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.System.getUriFor("fan_state_of_manual"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6175i = Settings.System.getInt(this.f6153i.getContentResolver(), "fan_state_of_manual", 0) > 0;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_fan);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.game_ic_qs_fan_on : R.drawable.game_ic_qs_fan_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        String e2 = inMsg.e();
        if ("game_turn_on_fan".equals(e2)) {
            Settings.System.putInt(this.f6153i.getContentResolver(), "fan_state_of_manual", 7);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_fan);
        } else if ("game_turn_off_fan".equals(e2)) {
            Settings.System.putInt(this.f6153i.getContentResolver(), "fan_state_of_manual", -7);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_fan);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
