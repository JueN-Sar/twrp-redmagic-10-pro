package cn.nubia.gameassist.dessert.tiles;

import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.NeoGameLibComService;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class GameBenefit extends QSTile {
    public GameBenefit(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        NeoGameLibComService neoGameLibComService = (NeoGameLibComService) Router.getInstance().getService(NeoGameLibComService.class.getSimpleName());
        if (neoGameLibComService == null) {
            return false;
        }
        GaLog.a(this.f6151c, "top click");
        neoGameLibComService.b(this.f6153i, "topClick");
        this.f6152h.b();
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
        state.f6169c = this.f6153i.getString(R.string.ic_qs_game_benefit);
        state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_benefit_normal);
    }
}
