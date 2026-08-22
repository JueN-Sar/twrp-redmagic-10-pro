package cn.nubia.gameassist.dessert.tiles;

import cn.nubia.componentcenter.api.dessert.ICompetitionLightProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.IModuleProxy;

/* loaded from: classes.dex */
public class CompetitionLightTiles extends QSTile implements IModuleProxy.ICallback<ICompetitionLightProxy> {
    private final ICompetitionLightProxy v;
    private boolean w;

    public CompetitionLightTiles(QSTile.Host host) {
        super(host);
        this.v = (ICompetitionLightProxy) host.a(ICompetitionLightProxy.class);
    }

    public void A0(boolean z) {
        e0(Boolean.TRUE);
        if (this.f6157m.f6175i != z) {
            S();
        }
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
        if (obj == null || !((Boolean) obj).booleanValue()) {
            return;
        }
        state.f6175i = this.v.c();
        int i2 = R.drawable.game_ic_qs_competition_light_unpress;
        if (this.v.b()) {
            i2 = state.f6175i ? R.drawable.game_ic_qs_competition_light_light : R.drawable.game_ic_qs_competition_light_normal;
        }
        state.f6168b = QSTile.ResourceIcon.b(i2);
        state.f6169c = this.f6153i.getString(R.string.ic_qs_competition_light);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_competition_light".equals(str)) {
            if (!this.v.b()) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
            A0(true);
            if (this.v.c()) {
                GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_competition_light);
                return;
            } else {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
        }
        if ("game_turn_off_competition_light".equals(str)) {
            if (!this.v.b()) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
                return;
            }
            A0(false);
            if (this.v.c()) {
                GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
            } else {
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_competition_light);
            }
        }
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: z0, reason: merged with bridge method [inline-methods] */
    public void onChanged(ICompetitionLightProxy iCompetitionLightProxy) {
        p0(Boolean.TRUE);
    }
}
