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
public class SightAssistTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private static final Uri w = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider");
    private boolean v;

    public SightAssistTile(QSTile.Host host) {
        super(host);
    }

    private void A0(int i2) {
        GaLog.e(this.f6151c, "sendSightAssistBroadcast : mCurPackage :  " + this.f6163s + " enable " + i2);
        Intent intent = new Intent();
        intent.setAction("cn.nubia.gamelauncher.action.START_SIGHTASSIST");
        if (Utils.R()) {
            intent.setPackage("cn.nubia.gamelauncher");
        } else {
            intent.setPackage("cn.nubia.gamehelperline");
        }
        intent.putExtra("packagename", this.f6163s);
        intent.putExtra(Constants.EXTRA_ENABLE, i2);
        this.f6153i.sendBroadcast(intent);
    }

    private void B0() {
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return;
        }
        this.f6152h.b();
        try {
            Intent intent = new Intent("cn.nubia.gamelauncher.action.START_HELPER");
            if (Utils.R()) {
                intent.setPackage("cn.nubia.gamelauncher");
            } else {
                intent.setPackage("cn.nubia.gamehelperline");
            }
            this.f6153i.startService(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        NubiaTrackManager.p().l("sight_assist", "app_name", Utils.j());
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e(this.f6151c, "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r + " " + this.t);
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
            } else if (SharedPreferencesUtil.k(this.f6153i).o(this.f6163s)) {
                A0(1);
                B0();
                SharedPreferencesUtil.k(this.f6153i).X(this.f6163s);
            } else {
                A0(1);
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e(this.f6151c, "handleSettingsClick() : mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a(this.f6151c, "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        B0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("aim_helper_open_pkgs"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("aim_helper_open_pkgs"), this);
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
        state.f6175i = this.t && RotationMgr.j();
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_aim_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_aim_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
        }
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_sight_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "aim_helper_open_pkgs"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (!"game_turn_on_front_sight".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_front_sight".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                if (this.t) {
                    A0(0);
                }
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
                return;
            }
            return;
        }
        if (this.t) {
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if (!SharedPreferencesUtil.k(this.f6153i).o(this.f6163s)) {
            A0(1);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else {
            A0(1);
            B0();
            SharedPreferencesUtil.k(this.f6153i).X(this.f6163s);
            GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, z0());
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
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_icon_sight;
    }
}
