package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class HighSensitivityWheelTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public HighSensitivityWheelTile(QSTile.Host host) {
        super(host);
    }

    private void A0(boolean z) {
        Intent intent = new Intent("cn.nubia.wheeldisc.intent.action.WHEEL_DISC");
        intent.setPackage("cn.nubia.keymapcenter");
        intent.putExtra("reason", "switch_wheel_disc");
        intent.putExtra(Constants.EXTRA_ENABLE, z);
        intent.putExtra("package_name", this.f6163s);
        intent.putExtra("is_fold", g0());
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("HighSensitivityWheelTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (u0()) {
                return true;
            }
            this.u = true;
            if (this.t) {
                A0(false);
            } else {
                this.f6152h.b();
                A0(true);
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("HighSensitivityWheelTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("HighSensitivityWheelTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        Intent intent = new Intent("cn.nubia.wheeldisc.intent.action.WHEEL_DISC");
        intent.setPackage("cn.nubia.keymapcenter");
        intent.putExtra("reason", "wheel_disc_settings");
        this.f6153i.startService(intent);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_wheel_disc_enabled_pkg"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_wheel_disc_enabled_pkg"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState() : ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable= ");
        sb.append(this.t);
        GaLog.e("HighSensitivityWheelTile", sb.toString());
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_high_wheel_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_high_wheel_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.high_wheel_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        String str;
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_wheel_disc_enabled_pkg");
        if (g0()) {
            str = "fold_screen_" + this.f6163s;
        } else {
            str = this.f6163s;
        }
        return Utils.x(string, str, ",") && !SystemMgr.F();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_high_sensitivity_roulette_wheel".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                A0(true);
                this.t = true;
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if ("game_turn_off_high_sensitivity_roulette_wheel".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                A0(false);
                this.t = false;
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t) {
            A0(false);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_label_high_wheel;
    }
}
