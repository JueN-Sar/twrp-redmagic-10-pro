package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class MagicElvesAidTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public MagicElvesAidTile(QSTile.Host host) {
        super(host);
    }

    private void B0() {
        GaLog.e("MagicElvesAidTile", "startMagicElvesSettings(): mIsGameScene = " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return;
        }
        try {
            GaLog.e("MagicElvesAidTile", "startMagicElvesSettings(): ");
            Intent intent = new Intent("cn.nubia.MagicElvesCardService.GAMECARDACTION");
            intent.setPackage("cn.nubia.magicelvesbroadcast");
            intent.putExtra("source", "GameAssist");
            this.f6153i.startService(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        NubiaTrackManager.p().l(O(), "app_name", this.f6163s);
    }

    public boolean A0() {
        GaLog.a("MagicElvesAidTile", "isAppEnable: openedApps = " + Settings.Global.getString(this.f6153i.getContentResolver(), "magic_aid_once_opened_pkgs"));
        return !Utils.x(r0, this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        boolean A0 = A0();
        GaLog.a("MagicElvesAidTile", "handleClick:  mCurPackage= " + this.f6163s + " mTileEnable= " + this.t + " , isFirstOpened = " + A0);
        if (!this.f6162r) {
            GaLog.a("MagicElvesAidTile", "handleClick: no in game");
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return true;
        }
        if (A0) {
            this.f6152h.b();
            B0();
        }
        this.u = true;
        if (this.t) {
            Utils.U(this.f6153i, this.f6163s, "magic_elves_aid", true);
        } else {
            Utils.b(this.f6153i, this.f6163s, "magic_elves_aid", true);
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("MagicElvesAidTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("MagicElvesAidTile", "isInFreeformMode");
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
        GaLog.a("MagicElvesAidTile", "setListening: " + z);
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Secure.getUriFor("magic_elves_aid"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Secure.getUriFor("magic_elves_aid"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.a("MagicElvesAidTile", "handleUpdateState: mTileEnable= " + this.t);
        if (this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_help_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_help_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
        }
        if (ZteFeature.isSupportDemi()) {
            state.f6169c = this.f6153i.getString(R.string.plugin_icon_demi_help);
            state.f6170d = this.f6153i.getString(R.string.plugin_demi_introduction);
        } else {
            state.f6169c = this.f6153i.getString(R.string.plugin_icon_help);
            state.f6170d = this.f6153i.getString(R.string.plugin_help_introduction);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        String string = Settings.Secure.getString(this.f6153i.getContentResolver(), "magic_elves_aid");
        GaLog.a("MagicElvesAidTile", "isAppEnable: openedApps = " + string);
        return Utils.x(string, this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (!"game_turn_on_M_Yell".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_M_Yell".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                if (this.t) {
                    Utils.U(this.f6153i, this.f6163s, "magic_elves_aid", true);
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
        if (!A0()) {
            Utils.b(this.f6153i, this.f6163s, "magic_elves_aid", true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else {
            this.f6152h.b();
            B0();
            GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t) {
            Utils.U(this.f6153i, this.f6163s, "magic_elves_aid", true);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.e("MagicElvesAidTile", "refreshState-->");
        o0();
    }

    protected int z0() {
        return ZteFeature.isSupportDemi() ? R.string.plugin_icon_demi_help : R.string.plugin_icon_help;
    }
}
