package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
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
public class KeyPositionAssistTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public KeyPositionAssistTile(QSTile.Host host) {
        super(host);
    }

    private Intent B0() {
        Intent intent = new Intent("cn.nubia.keymapcenter.intent.action.KEY_MAP");
        intent.setPackage("cn.nubia.keymapcenter");
        return intent;
    }

    private boolean C0(String str) {
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_screen_key_map_pkg_enable");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return string.contains(str + ",");
    }

    private void D0() {
        Intent B0 = B0();
        B0.putExtra("reason", "screen_key_settings");
        this.f6153i.startService(B0);
    }

    private void z0(boolean z) {
        Intent B0 = B0();
        B0.putExtra("package_name", this.f6163s);
        B0.putExtra("reason", "switch_screen_key");
        B0.putExtra("is_fold", g0());
        B0.putExtra(Constants.EXTRA_ENABLE, z);
        this.f6153i.startService(B0);
    }

    protected int A0() {
        return R.string.plugin_icon_keyposition;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("KeyPositionAssistTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (u0()) {
                return true;
            }
            this.u = true;
            if (this.t) {
                z0(false);
            } else {
                this.f6152h.b();
                z0(true);
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("KeyPositionAssistTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("KeyPositionAssistTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        D0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_screen_key_map_pkg_open"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_screen_key_map_pkg_open"), this);
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
        GaLog.e("KeyPositionAssistTile", sb.toString());
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_keyposition_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_keyposition_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(A0());
        state.f6170d = this.f6153i.getString(R.string.plugin_keyposition_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        String str;
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_screen_key_map_pkg_open");
        if (g0()) {
            str = "fold_screen_" + this.f6163s;
        } else {
            str = this.f6163s;
        }
        return Utils.x(string, str, ",") && !SystemMgr.F();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        InMsg f2;
        if ("game_turn_on_touch_map_mode".equals(inMsg.e())) {
            if (!this.f6162r || !RotationMgr.j()) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
                return;
            }
            if (SystemMgr.F()) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                ToastUtil.a(this.f6153i.getString(R.string.ic_qs_red_magic_broadcast_no_support_game_is_clone_text));
                return;
            }
            if (Utils.P(this.f6153i)) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                return;
            }
            String t = SystemMgr.t();
            this.f6163s = t;
            if (!C0(t)) {
                GameAgentUtil.a(this.f6153i, iGameAssistClientCallback, inMsg, A0());
                return;
            } else {
                z0(true);
                GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, A0());
                return;
            }
        }
        if ("game_turn_off_touch_map_mode".equals(inMsg.e())) {
            this.f6163s = SystemMgr.t();
            z0(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        if ("positive".equals(inMsg.e()) && (f2 = inMsg.f()) != null && "game_turn_on_touch_map_mode".equals(f2.e())) {
            if (!this.f6162r || !RotationMgr.j()) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
            } else if (Utils.P(this.f6153i)) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            } else {
                D0();
                GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            }
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t) {
            z0(false);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
