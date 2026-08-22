package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class RangeLineTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public RangeLineTile(QSTile.Host host) {
        super(host);
    }

    private void A0(int i2) {
        GaLog.e(this.f6151c, "sendRangeLineBroadcast() : mCurPackage :  " + this.f6163s + " enable " + i2);
        Intent intent = new Intent();
        intent.setAction("cn.nubia.gamelauncher.action.START_RANGELINE");
        intent.putExtra("packagename", this.f6163s);
        intent.putExtra(Constants.EXTRA_ENABLE, i2);
        this.f6153i.sendBroadcast(intent);
    }

    private void B0() {
        this.f6152h.b();
        GaLog.e(this.f6151c, "startRangeLineSettings");
        Intent intent = new Intent("cn.nubia.gamehelperline.ACTION_OPEN_LINE_MAIN_VIEW");
        intent.setPackage("cn.nubia.gamehelperline");
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e(this.f6151c, "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            A0(0);
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a(this.f6151c, "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                A0(0);
                this.t = false;
            } else if (SharedPreferencesUtil.k(this.f6153i).n(this.f6163s)) {
                B0();
                SharedPreferencesUtil.k(this.f6153i).W(this.f6163s);
            } else {
                this.f6152h.b();
                A0(1);
                this.t = true;
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e(this.f6151c, "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a(this.f6151c, "isInFreeformMode");
            return true;
        }
        if (this.t) {
            B0();
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
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("gamehelperline_enable_pkgs"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("gamehelperline_enable_pkgs"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        String str = this.f6151c;
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState(): ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable= ");
        sb.append(this.t);
        GaLog.e(str, sb.toString());
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_rangeline_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_rangeline_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
        }
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_rangeline_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "gamehelperline_enable_pkgs"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (!"game_turn_on_game_help_line".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_game_help_line".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                A0(0);
                this.t = false;
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
                return;
            }
            return;
        }
        if (this.t) {
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if (SharedPreferencesUtil.k(this.f6153i).n(this.f6163s)) {
            B0();
            SharedPreferencesUtil.k(this.f6153i).W(this.f6163s);
            GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else {
            A0(1);
            this.t = true;
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void l0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).h(this);
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void n0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).i(this);
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t) {
            A0(0);
            this.t = false;
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_icon_rangeline;
    }
}
