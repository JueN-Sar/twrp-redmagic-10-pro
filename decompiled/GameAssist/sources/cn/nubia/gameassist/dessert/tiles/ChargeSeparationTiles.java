package cn.nubia.gameassist.dessert.tiles;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IChargeSeparationProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class ChargeSeparationTiles extends QSTile implements IModuleProxy.ICallback<IChargeSeparationProxy> {
    private final IChargeSeparationProxy v;
    private boolean w;

    public ChargeSeparationTiles(QSTile.Host host) {
        super(host);
        this.v = (IChargeSeparationProxy) host.a(IChargeSeparationProxy.class);
    }

    public static String z0(Context context, int i2) {
        String string = context.getString(i2);
        if (!ZteFeature.supportBypassChargeSeparation()) {
            return string;
        }
        String string2 = context.getString(R.string.ic_qs_charge_separation_zte);
        String string3 = context.getString(R.string.ic_qs_charge_separation_redmagic);
        return string.contains(string3) ? string.replace(string3, string2) : string;
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: A0, reason: merged with bridge method [inline-methods] */
    public void onChanged(IChargeSeparationProxy iChargeSeparationProxy) {
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.f6152h.b();
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
        state.f6168b = QSTile.ResourceIcon.b(c2 ? R.drawable.game_ic_qs_charge_separation_light : R.drawable.game_ic_qs_charge_separation_normal);
        state.f6175i = c2;
        state.f6169c = z0(this.f6153i, R.string.ic_qs_charge_separation);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        str.hashCode();
        if (str.equals("game_turn_off_charge_separation")) {
            this.v.aiAgent(false, iGameAssistClientCallback, inMsg);
        } else if (str.equals("game_turn_on_charge_separation")) {
            this.v.aiAgent(true, iGameAssistClientCallback, inMsg);
        }
    }
}
