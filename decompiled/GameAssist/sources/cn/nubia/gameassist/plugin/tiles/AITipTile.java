package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class AITipTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public AITipTile(QSTile.Host host) {
        super(host);
    }

    private void A0(String str) {
        Intent intent = new Intent("cn.zte.gamefloat.ai.tip");
        intent.putExtra("event", str);
        intent.putExtra("packageName", this.f6163s);
        intent.setPackage("cn.zte.gamefloat");
        this.f6153i.startService(intent);
    }

    private void B0() {
        GaLog.e("AITipTile", "startAITip");
        A0("open");
    }

    private void C0() {
        GaLog.e("AITipTile", "startEditView");
        A0("set");
    }

    private void D0() {
        GaLog.a("AITipTile", "stopAITip");
        A0("close");
    }

    private void z0() {
        GaLog.e("AITipTile", "requestAITip");
        A0("request_tip");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("AITipTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (this.f6162r) {
            this.u = true;
            if (this.t) {
                D0();
            } else {
                B0();
                this.f6152h.b();
            }
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("AITipTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("AITipTile", "isInFreeformMode");
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
        Uri uriFor = Settings.Global.getUriFor("game_ai_tip_enable_pkgs");
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
        GaLog.e("AITipTile", sb.toString());
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_ai_tip_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_ai_tip_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        if (Build.VERSION.SDK_INT < 36) {
            state.f6169c = this.f6153i.getString(R.string.plugin_ai_tip);
        } else if (ZteFeature.isNeoProduct()) {
            state.f6169c = this.f6153i.getString(R.string.plugin_neo_ai_tip_coach);
        } else {
            state.f6169c = this.f6153i.getString(R.string.plugin_ai_tip_coach);
        }
        state.f6170d = this.f6153i.getString(R.string.plugin_ai_tip_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "game_ai_tip_enable_pkgs"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_tactical_coach".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            B0();
        } else if ("game_turn_on_tactical_advice".equals(str)) {
            z0();
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
}
