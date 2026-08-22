package cn.nubia.gameassist.plugin.tiles;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.ButtonAction;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.aiagent.bean.OutMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class AITriggerTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public AITriggerTile(QSTile.Host host) {
        super(host);
    }

    private boolean A0() {
        String[] split;
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "plugintrigger_scheme_checked_pkgs");
        if (TextUtils.isEmpty(string) || (split = string.split(",")) == null) {
            return false;
        }
        for (String str : split) {
            if (str != null && str.equals(SystemMgr.t())) {
                return true;
            }
        }
        return false;
    }

    private void B0() {
        GaLog.e("AITriggerTile", "startHomeWindow: ");
        Intent intent = new Intent("com.zte.game.plugintrigger.ACTION_SHOW_WINDOW");
        intent.setPackage("com.zte.game.plugintrigger");
        intent.putExtra("packageName", this.f6163s);
        intent.putExtra("request_code", 101);
        this.f6153i.startService(intent);
    }

    private void C0() {
        GaLog.e("AITriggerTile", "startTrigger: ");
        Intent intent = new Intent("com.zte.game.plugintrigger.ACTION_START");
        intent.setPackage("com.zte.game.plugintrigger");
        intent.putExtra("packageName", this.f6163s);
        this.f6153i.startService(intent);
    }

    private void D0() {
        E0(false);
    }

    private void E0(boolean z) {
        GaLog.e("AITriggerTile", "stopTrigger " + z);
        Intent intent = new Intent("com.zte.game.plugintrigger.ACTION_STOP");
        intent.setPackage("com.zte.game.plugintrigger");
        intent.putExtra("packageName", this.f6163s);
        intent.putExtra("close_window", z);
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("AITriggerTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("AITriggerTile", "isInFreeformMode");
                return true;
            }
            if (u0()) {
                return true;
            }
            this.u = true;
            if (this.t) {
                D0();
                this.t = false;
            } else {
                C0();
                this.f6152h.b();
                this.t = true;
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("AITriggerTile", "handleSettingsClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTriggerEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("AITriggerTile", "isInFreeformMode");
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
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("plugintrigger_enabled_pkgs"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("plugintrigger_enabled_pkgs"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("AITriggerTile", "handleUpdateState: mCurPackage = " + this.f6163s + " ,mIsGameScene = " + this.f6162r + " ,mTriggerEnable = " + this.t);
        boolean z = this.t;
        state.f6175i = z;
        state.f6168b = QSTile.ResourceIcon.b((z && this.f6162r) ? R.drawable.plugin_trigger_on : R.drawable.plugin_trigger_off);
        state.f6171e = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_trigger_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "plugintrigger_enabled_pkgs"), this.f6163s, ",") && !SystemMgr.F();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (inMsg.j() == 1 && i0(iGameAssistClientCallback, inMsg)) {
            if ("game_open_ai_trigger_window".equals(str) && "1".equals(inMsg.a())) {
                B0();
                return;
            }
            return;
        }
        if ("positive".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            InMsg f2 = inMsg.f();
            if (f2 == null || !"game_open_ai_trigger_window".equals(f2.e())) {
                return;
            }
            B0();
            GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if (!"game_open_ai_trigger_window".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_ai_trigger_window".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                if (this.t) {
                    this.t = false;
                }
                E0(true);
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
                return;
            }
            return;
        }
        if (!A0()) {
            GameAgentUtil.a(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if (!this.t) {
            C0();
            this.t = true;
        }
        if (iGameAssistClientCallback != null) {
            Context context = this.f6153i;
            OutMsg outMsg = new OutMsg(context.getString(com.zte.gameassist.aiagent.R.string.aiagent_turn_on_function, context.getString(R.string.plugin_label_trigger)));
            outMsg.c(inMsg.c());
            outMsg.a(new ButtonAction("1", this.f6153i.getString(R.string.plugin_label_trigger)));
            outMsg.d(inMsg.b() + "|" + inMsg.e());
            try {
                iGameAssistClientCallback.onReceivedCallback(1, outMsg.toString());
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.e("AITriggerTile", "onChange " + this.t + " is " + f0());
        o0();
    }

    protected int z0() {
        return R.string.plugin_label_trigger;
    }
}
