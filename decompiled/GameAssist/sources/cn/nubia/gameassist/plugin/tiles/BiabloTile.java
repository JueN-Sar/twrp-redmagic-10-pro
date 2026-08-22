package cn.nubia.gameassist.plugin.tiles;

import android.content.Context;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class BiabloTile extends QSTile implements PerformanceModeController.PerformanceModeCallback {
    private PerformanceModeController v;
    private long w;
    private boolean x;

    public BiabloTile(QSTile.Host host) {
        super(host);
        PerformanceModeController S = PerformanceModeController.S();
        this.v = S;
        S.P(this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        GaLog.e(this.f6151c, "handleClick() : System.currentTimeMillis() - mClickTime :  " + (System.currentTimeMillis() - this.w));
        if (Math.abs(System.currentTimeMillis() - this.w) > 500) {
            this.t = f0();
            if (this.v.a0() && ZteFeature.isSM8850Project()) {
                ToastUtil.a(this.f6153i.getString(R.string.toast_message_turn_off_the_superior_pic_quality));
                return false;
            }
            this.v.B0(SystemMgr.t(), !this.t);
            this.w = System.currentTimeMillis();
            this.u = true;
        }
        return super.S();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        this.x = z;
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        state.f6169c = this.f6153i.getString(R.string.nubia_game_performance_mode_diablo_title);
        state.f6170d = this.f6153i.getString(R.string.plugin_diablo_introduction);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_biablo_mode_on : R.drawable.plugin_biablo_mode_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return this.v.Y();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (!ZteFeature.isSupportBiabloPlugin()) {
            GameAgentUtil.g(this.f6153i, iGameAssistClientCallback, inMsg, "biablo reply");
        }
        str.hashCode();
        if (!str.equals("game_turn_on_super_perform_mode")) {
            if (str.equals("game_turn_off_super_perform_mode")) {
                if (f0()) {
                    this.v.B0(SystemMgr.t(), false);
                }
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.nubia_game_performance_mode_diablo_title);
                return;
            }
            return;
        }
        if (this.v.Z()) {
            Context context = this.f6153i;
            GameAgentUtil.i(context, iGameAssistClientCallback, inMsg, context.getString(R.string.performancemode_is_lowpowermode_tip), false);
        } else if (this.v.a0() && ZteFeature.isSM8850Project()) {
            Context context2 = this.f6153i;
            GameAgentUtil.i(context2, iGameAssistClientCallback, inMsg, context2.getString(R.string.toast_message_turn_off_the_superior_pic_quality), false);
        } else {
            if (!f0()) {
                this.v.B0(SystemMgr.t(), true);
            }
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
        }
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, int i2, boolean z) {
        if (!this.x || this.t == z) {
            return;
        }
        o0();
    }
}
