package cn.nubia.gameassist.dessert.tiles;

import android.content.Intent;
import cn.nubia.componentcenter.api.dessert.IMisOperateProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class MisOperateTile extends QSTile implements IModuleProxy.ICallback<IMisOperateProxy> {
    private final IMisOperateProxy v;
    private boolean w;

    public MisOperateTile(QSTile.Host host) {
        super(host);
        this.v = (IMisOperateProxy) host.a(IMisOperateProxy.class);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        return this.v.d();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void W() {
        super.W();
        try {
            Intent intent = new Intent();
            intent.setAction("cn.nubia.gamecenter.settings.action.GAME_CENTER");
            intent.putExtra("gcs_start_type", "fragment_falsetouch");
            intent.setPackage("cn.nubia.gamelauncher");
            intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
            this.f6153i.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
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
        state.f6175i = c2;
        state.f6168b = QSTile.ResourceIcon.b(c2 ? R.drawable.game_ic_qs_misoperate_on : R.drawable.game_ic_qs_misoperate_off);
        state.f6169c = this.f6153i.getString(R.string.ic_qs_mis_operate);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_mis_touch".equals(str)) {
            this.v.a(true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_mis_operate);
        } else if ("game_turn_off_mis_touch".equals(str)) {
            this.v.a(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_mis_operate);
        }
    }

    @Override // com.zte.gameassist.common.IModuleProxy.ICallback
    /* renamed from: z0, reason: merged with bridge method [inline-methods] */
    public void onChanged(IMisOperateProxy iMisOperateProxy) {
        o0();
    }
}
