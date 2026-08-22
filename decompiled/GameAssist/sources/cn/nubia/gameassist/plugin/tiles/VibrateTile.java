package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.plugin.policy.TencentVibrateHelper;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class VibrateTile extends QSTile implements ObserverManager.SettingCallback, TencentVibrateHelper.TencentVibrateCallBack, PerformanceModeController.PerformanceModeCallback {
    private static final List A = Arrays.asList("com.tencent.tmgp.pubgmhd", "com.gameloft.android.ANMP.GloftA9HM", "com.tencent.lolm", "com.aligames.kuang.kybc", "com.tencent.tmgp.aligames.kybc", "com.aligames.kuang.kybc.aligames", "com.aligames.kuang.kybc.tap", "com.pubg.krmobile", "com.tencent.ig", "com.vng.pubgmobile", "com.pubg.newstate", "com.rekoo.pubgm", "com.netease.ko", "com.netease.rs", "com.netease.hyxd", "com.netease.hyxd.ewan", "com.netease.hyxd.aligames", "com.tencent.tmgp.speedmobile", "com.miHoYo.Yuanshen", "com.tencent.iglite", "com.tencent.ig", "com.tencent.qqspmi", "com.tencent.ig", "com.tencent.tmgp.sgame", "com.mobile.legends", "com.mobile.legends.usa", "com.dfjz.moba");
    private static final List B = Arrays.asList("com.aligames.kuang.kybc", "com.tencent.tmgp.aligames.kybc", "com.gameloft.android.ANMP.GloftA9HM", "com.tencent.tmgp.speedmobile", "com.tencent.qqspmi");
    private static final List C = Arrays.asList("com.tencent.tmgp.pubgmhd");
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private boolean z;

    public VibrateTile(QSTile.Host host) {
        super(host);
        this.w = true;
        this.z = false;
    }

    private String A0() {
        return "";
    }

    private boolean C0() {
        if (!TextUtils.isEmpty(this.f6163s) && this.f6163s.startsWith("com")) {
            Iterator it = B.iterator();
            while (it.hasNext()) {
                if (this.f6163s.equals((String) it.next())) {
                    GaLog.a("VibrateTile", "isRacingCarGame: true");
                    return true;
                }
            }
        }
        GaLog.a("VibrateTile", "isRacingCarGame: false");
        return false;
    }

    private boolean D0() {
        return (ZteFeature.isSuperiorQualityGame() || C0() || H0() || ZteFeature.isSupportAudioVibrate()) ? false : true;
    }

    private void E0(boolean z, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (this.f6160p.q0() == 1 && !Utils.A(this.f6153i)) {
            GameAgentUtil.m(this.f6153i, iGameAssistClientCallback, inMsg);
            return;
        }
        if (Utils.P(this.f6153i)) {
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            return;
        }
        if (!this.w) {
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
            ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_no_support));
            return;
        }
        e0(null);
        if (this.f6157m.f6175i != z) {
            S();
        }
        if (z) {
            ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_open));
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, B0());
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_close));
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, B0());
        }
    }

    private void F0() {
        String str = this.f6163s + ",";
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_4d_shocks");
        if (TextUtils.isEmpty(string)) {
            if (!this.z) {
                ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_open));
            }
        } else if (z0(string, this.f6163s)) {
            str = string.replace(str, "");
            if (!this.z) {
                ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_close));
            }
        } else {
            str = string + str;
            if (!this.z) {
                ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_open));
            }
        }
        Settings.Global.putString(this.f6153i.getContentResolver(), "nubia_4d_shocks", str);
        NubiaTrackManager.p().C(O(), !this.f6157m.f6175i);
        NubiaTrackManager.p().y("cn.nubia.gamelauncher", "game_status", "ai_4d_shock", !this.f6157m.f6175i ? 1 : 0);
        StringBuilder sb = new StringBuilder();
        sb.append("startVibrateFunction: ");
        sb.append(str);
        sb.append(" , !mState.value = ");
        sb.append(!this.f6157m.f6175i);
        GaLog.a("VibrateTile", sb.toString());
    }

    private void G0() {
        this.f6153i.sendBroadcast(new Intent("cn.nubia.intent.action.FOUR_DIMENSIONAL_VIBRATION_MAP_OPTION"));
        GaLog.a("VibrateTile", "startVibrateSettings");
    }

    private boolean H0() {
        this.x = TencentVibrateHelper.f(this.f6153i, this).e(this.f6163s);
        GaLog.a("VibrateTile", "supportTencentVibrate: support : " + this.x);
        return this.x;
    }

    private boolean z0(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            for (String str3 : str.split(",")) {
                if (str2.equals(str3.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    protected int B0() {
        return R.string.plugin_icon_4d;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("VibrateTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (this.f6160p.q0() == 1 && !Utils.A(this.f6153i)) {
            return false;
        }
        if (!ZteFeature.isSuperiorQualityGame()) {
            this.f6152h.b();
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            return true;
        }
        this.u = true;
        if (!this.w) {
            ToastUtil.a(this.f6153i.getString(R.string.game_vibrate_toast_no_support));
        } else if (!D0()) {
            F0();
        } else if (this.t) {
            F0();
        } else if (SharedPreferencesUtil.k(this.f6153i).p(this.f6163s)) {
            G0();
            SharedPreferencesUtil.k(this.f6153i).Y(this.f6163s);
        } else {
            F0();
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        if (!D0()) {
            return false;
        }
        super.a0();
        GaLog.e("VibrateTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("VibrateTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        G0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_4d_shocks"), this);
            TencentVibrateHelper.f(this.f6153i, this).c();
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("NubiaperformanceMode"), this);
            PerformanceModeController.S().P(this);
            return;
        }
        ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_4d_shocks"), this);
        TencentVibrateHelper.f(this.f6153i, this).g();
        ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("NubiaperformanceMode"), this);
        PerformanceModeController.S().x0(this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        String str = this.f6163s + ",";
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_4d_shocks");
        GaLog.a("VibrateTile", "handleUpdateState : enables : " + string + " state.value : " + state.f6175i + " , formatPackage : " + str + " , isManualModified : " + this.y);
        if (this.f6160p.q0() != 1 || Utils.A(this.f6153i)) {
            if (this.y) {
                if (!TextUtils.isEmpty(string)) {
                    if (z0(string, this.f6163s)) {
                        str = string;
                    } else {
                        str = string + str;
                    }
                }
                Settings.Global.putString(this.f6153i.getContentResolver(), "nubia_4d_shocks", str);
                this.y = false;
            }
            state.f6168b = QSTile.ResourceIcon.b(this.t ? R.drawable.plugin_4d_on : R.drawable.plugin_4d_off);
        } else {
            if (!TextUtils.isEmpty(string) && z0(string, this.f6163s)) {
                Settings.Global.putString(this.f6153i.getContentResolver(), "nubia_4d_shocks", string.replace(str, ""));
                this.y = true;
            }
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_4d_unpress);
        }
        if (D0()) {
            state.f6171e = QSTile.ResourceIcon.b(this.t ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        } else {
            state.f6171e = null;
        }
        state.f6169c = this.f6153i.getString(B0());
        state.f6170d = this.f6153i.getString(R.string.plugin_4d_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return z0(Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_4d_shocks"), this.f6163s);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        this.z = true;
        if ("game_turn_on_game_virbate".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                E0(true, iGameAssistClientCallback, inMsg);
            }
        } else if ("game_turn_off_game_virbate".equals(str) && i0(iGameAssistClientCallback, inMsg) && this.t) {
            E0(false, iGameAssistClientCallback, inMsg);
        }
        this.z = false;
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void n(String str, int i2, boolean z) {
        o0();
    }

    @Override // cn.nubia.gameassist.plugin.policy.TencentVibrateHelper.TencentVibrateCallBack
    public void v() {
        GaLog.a("VibrateTile", "onBindTcsystemSuccess: ");
        if (H0()) {
            this.w = true;
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        if ("com.excean.gspace".equals(this.f6163s)) {
            this.f6163s = A0();
        }
        this.w = false;
        if (!TextUtils.isEmpty(this.f6163s) && this.f6163s.startsWith("com")) {
            Iterator it = A.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (this.f6163s.equals((String) it.next())) {
                    this.w = true;
                    break;
                }
            }
            if (H0()) {
                this.w = true;
            }
        }
        GaLog.a("VibrateTile", "mInstall = " + this.w + ",mCurApp = " + this.f6163s);
        o0();
    }
}
