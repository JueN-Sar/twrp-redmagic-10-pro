package cn.nubia.gameassist.plugin.tiles;

import android.net.Uri;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class PleasedDisplayTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public PleasedDisplayTile(QSTile.Host host) {
        super(host);
    }

    private void A0() {
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return;
        }
        this.f6152h.b();
        GameRatioMgr.q().F();
        NubiaTrackManager.p().l("pleased_display", "app_name", Utils.j());
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("PleasedDisplayTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("PleasedDisplayTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                GameRatioMgr.q().J(false);
            } else {
                GameRatioMgr.q().J(true);
            }
            this.f6152h.b();
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        GaLog.e("PleasedDisplayTile", "handleSettingsClick() : mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("PleasedDisplayTile", "isInFreeformMode");
            return true;
        }
        if (this.t) {
            A0();
            return false;
        }
        ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("PleasedDisplayTile", "handleUpdateState: mCurPackage = " + this.f6163s + " ,mIsGameScene = " + this.f6162r + " ,mTileEnable = " + this.t);
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_pleased_display_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_pleased_display_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
        }
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.pleased_display_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return GameRatioMgr.q().t();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_custom_screen_size".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                GameRatioMgr.q().J(true);
            }
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
        } else if ("game_turn_off_custom_screen_size".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            GameRatioMgr.q().m();
            if (this.t) {
                GameRatioMgr.q().J(false);
            }
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void l0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).h(this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void n0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).i(this);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_label_pleased_display;
    }
}
