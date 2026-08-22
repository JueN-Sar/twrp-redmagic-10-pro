package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class SoundEffectTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private int w;
    private int x;
    private int y;

    public SoundEffectTile(QSTile.Host host) {
        super(host);
        this.w = 0;
        this.x = 6;
        this.y = 7;
    }

    private void A0(int i2) {
        B0(i2, false);
    }

    private void B0(int i2, boolean z) {
        GaLog.e("SoundEffectTile", "startService  " + i2);
        Intent intent = new Intent();
        if (ZteFeature.isDtsEqFloat()) {
            intent.setAction("cn.zte.intent.action.EQUALIZERSERVICE");
            intent.setPackage("cn.zte.gamefloat");
        } else {
            intent.setAction("cn.nubia.intent.action.EQUALIZERSERVICE");
            intent.setPackage("com.dts.dtsxultra");
        }
        intent.putExtra("packagename", this.f6163s);
        intent.putExtra("reason", i2);
        intent.putExtra("close_window", z);
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("SoundEffectTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (this.f6162r) {
            this.u = true;
            if (this.t) {
                A0(this.y);
            } else {
                this.f6152h.b();
                A0(this.x);
            }
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        }
        NubiaTrackManager.p().u();
        return true;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("SoundEffectTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("SoundEffectTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        A0(this.w);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("game_equalizer_enable"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("game_equalizer_enable"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState(): ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable= ");
        sb.append(this.t);
        GaLog.e("SoundEffectTile", sb.toString());
        state.f6168b = QSTile.ResourceIcon.b((this.t && this.f6162r) ? R.drawable.plugin_sound_on : R.drawable.plugin_sound_off);
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_sound_introduction);
        state.f6171e = QSTile.ResourceIcon.b((this.t && this.f6162r) ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Settings.Global.getInt(this.f6153i.getContentResolver(), "game_equalizer_enable", 0) == 1;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_custom_music_volume".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                A0(this.x);
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else if ("game_turn_off_custom_music_volume".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            B0(this.y, true);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_icon_sound;
    }
}
