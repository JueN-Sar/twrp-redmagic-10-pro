package cn.nubia.gameassist.plugin.tiles;

import android.net.Uri;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.provider.FunctionCallController;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.plugin.gameshader.ShaderMgr;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.util.Arrays;

/* loaded from: classes.dex */
public class GameShaderTile extends QSTile implements ObserverManager.SettingCallback, FunctionCallController.Callback, ShaderMgr.onDataChangeCallback {
    public GameShaderTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        boolean z = !ShaderMgr.t().w();
        ShaderMgr.t().B(z, this);
        if (!ZteFeature.isSupportSort()) {
            o0();
        }
        this.u = true;
        NubiaTrackManager.p().u();
        if (!z) {
            return false;
        }
        this.f6152h.b();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("GameShaderTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else if (this.f6157m.f6175i) {
            this.f6152h.b();
            ShaderMgr.t().E();
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
        }
        return super.a0();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_hunt_on : R.drawable.plugin_hunt_off);
        state.f6171e = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_hunt_introduction);
        GaLog.a("GameShaderTile", "handleUpdateState= " + state.f6169c + " " + state.f6175i);
    }

    @Override // cn.nubia.plugin.gameshader.ShaderMgr.onDataChangeCallback
    public void d() {
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return ShaderMgr.t().w();
    }

    @Override // cn.nubia.gameassist.provider.FunctionCallController.Callback
    public void j(String... strArr) {
        this.f6152h.b();
        GaLog.e("GameShaderTile", "onFunctionCall: data = " + Arrays.toString(strArr));
        if ("1".equals(strArr[0])) {
            ShaderMgr.t().A(true);
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (str.equals("game_open_game_filter_mode") && i0(iGameAssistClientCallback, inMsg)) {
            ShaderMgr.t().D(true);
            o0();
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
        if (str.equals("game_turn_off_game_filter_mode") && i0(iGameAssistClientCallback, inMsg)) {
            ShaderMgr.t().D(false);
            o0();
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void l0(TileHost tileHost) {
        FunctionCallController.c(this.f6153i).b(O(), this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void n0(TileHost tileHost) {
        FunctionCallController.c(this.f6153i).f(O());
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_icon_hunt;
    }
}
