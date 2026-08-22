package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.plugin.policy.GameVoiceController;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class VoiceControllerTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public VoiceControllerTile(QSTile.Host host) {
        super(host);
    }

    private void A0(String str) {
        B0(str, false);
    }

    private void B0(String str, boolean z) {
        Intent intent = new Intent(str);
        intent.setClassName("com.zte.onemorething", "com.zte.aispeaker.aigc.AudioRecordService");
        intent.putExtra("name", "voice_controller");
        intent.putExtra("package", this.f6163s);
        intent.putExtra("close_window", z);
        this.f6153i.startService(intent);
    }

    private void C0() {
        GaLog.e("VoiceControllerTile", "startEditView");
        A0("com.zte.onemorething.action.EDIT_VOICE_CONTROLLER");
    }

    private void D0() {
        GaLog.e("VoiceControllerTile", "startASR");
        A0("com.zte.onemorething.action.START_ASR");
        GameVoiceController.f().o(true);
    }

    private void E0() {
        GaLog.a("VoiceControllerTile", "stopASR");
        A0("com.zte.onemorething.action.STOP_ASR");
        GameVoiceController.f().o(false);
    }

    private void F0(boolean z) {
        GaLog.a("VoiceControllerTile", "stopASR");
        B0("com.zte.onemorething.action.STOP_ASR", z);
    }

    private void G0(QSTile.State state) {
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_voice_controller_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_voice_controller_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("VoiceControllerTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("VoiceControllerTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                E0();
                Utils.U(this.f6153i, this.f6163s, "voice_controller_enabled_pkg", false);
            } else {
                D0();
                this.f6152h.b();
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("VoiceControllerTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("VoiceControllerTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        C0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        Uri uriFor = Settings.Global.getUriFor("voice_controller_enabled_pkg");
        if (z) {
            ObserverManager.c().b(this.f6153i, uriFor, this);
        } else {
            ObserverManager.c().d(this.f6153i, uriFor, this);
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
        GaLog.e("VoiceControllerTile", sb.toString());
        G0(state);
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.voice_controller_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "voice_controller_enabled_pkg"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_voice_map_touch".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                D0();
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else if ("game_turn_off_voice_map_touch".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                Utils.U(this.f6153i, this.f6163s, "voice_controller_enabled_pkg", false);
                this.t = false;
            }
            F0(true);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_label_voice_controller;
    }
}
