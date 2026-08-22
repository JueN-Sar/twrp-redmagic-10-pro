package cn.nubia.gameassist.dessert.tiles;

import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.clean.CleanAnimationController;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class CleanTile extends QSTile {
    public CleanTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        GaLog.e(this.f6151c, "handleClick");
        this.f6152h.b();
        z0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6169c = this.f6153i.getString(R.string.ic_qs_clean);
        state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_clean_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_clean_memory".equals(str)) {
            z0();
            GameAgentUtil.k(this.f6153i, iGameAssistClientCallback, inMsg);
        }
    }

    public void z0() {
        CleanAnimationController.d(this.f6153i).h();
        NubiaTrackManager.p().k(O());
    }
}
