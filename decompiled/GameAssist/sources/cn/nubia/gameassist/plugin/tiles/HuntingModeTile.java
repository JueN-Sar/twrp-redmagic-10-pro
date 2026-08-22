package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.provider.FunctionCallController;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.util.Arrays;

/* loaded from: classes.dex */
public class HuntingModeTile extends FloatButtonQSTile implements ObserverManager.SettingCallback, FunctionCallController.Callback {
    private boolean q0;
    private String r0;
    private int s0;

    public HuntingModeTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    protected void G1() {
        int m2 = Utils.m(this.v, this.r0, false);
        Utils.X(this.v, this.r0, m2);
        GaLog.a("HuntingModeTile", "resetGameStrengMode: mode = " + m2);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    public void I1(boolean z) {
        GaLog.a("HuntingModeTile", "setFloatButtonListening ：" + z);
        if (this.q0 == z) {
            return;
        }
        this.q0 = z;
        if (!z) {
            ObserverManager.c().d(this.v, Settings.Global.getUriFor("db_game_color_invert"), this);
            ObserverManager.c().d(this.v, Settings.Global.getUriFor("game_strengthen_mode_value"), this);
        } else {
            this.r0 = Utils.j();
            ObserverManager.c().b(this.v, Settings.Global.getUriFor("db_game_color_invert"), this);
            ObserverManager.c().b(this.v, Settings.Global.getUriFor("game_strengthen_mode_value"), this);
        }
    }

    protected void R1() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.HuntingModeTile.1
            @Override // java.lang.Runnable
            public void run() {
                HuntingModeTile huntingModeTile = HuntingModeTile.this;
                View view = huntingModeTile.C;
                if (view != null) {
                    view.setBackgroundResource(huntingModeTile.S1() ? R.drawable.plugin_button_open : R.drawable.plugin_button_close);
                }
                GaLog.a("HuntingModeTile", "handleUpdateFloatButton= " + HuntingModeTile.this.S1());
            }
        });
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile, cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("HuntingModeTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (this.f6162r) {
            this.u = true;
            return false;
        }
        ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
        return false;
    }

    boolean S1() {
        return this.s0 == 5;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_hunt_on : R.drawable.plugin_hunt_off);
        state.f6169c = this.v.getString(R.string.plugin_icon_hunt);
        state.f6170d = this.v.getString(R.string.plugin_hunt_introduction);
        GaLog.a("HuntingModeTile", "handleUpdateState= " + state.f6169c + " " + state.f6175i + " , mIsGameScene = " + this.f6162r);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    protected void c1() {
        if (!S1() || SystemMgr.I()) {
            return;
        }
        this.v.sendBroadcast(new Intent("cn.nubia.intent.action.game_color_invert"));
        GaLog.e("HuntingModeTile", "callStopPluginService HuntingMode");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return this.f6157m.f6175i;
    }

    @Override // cn.nubia.gameassist.provider.FunctionCallController.Callback
    public void j(String... strArr) {
        GaLog.e("HuntingModeTile", "onFunctionCall: data = " + Arrays.toString(strArr));
        if (!"1".equals(strArr[0])) {
            g1();
        } else if (K1()) {
            Settings.Global.putInt(this.v.getContentResolver(), "game_strengthen_mode_value", 5);
            this.v.sendBroadcast(new Intent("cn.nubia.intent.action.game_color_invert"));
        }
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    int j1() {
        return 84;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    String k1() {
        return this.v.getString(R.string.plugin_float_button_hunt);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile, cn.nubia.gameassist.common.QSTile
    public void l0(TileHost tileHost) {
        super.l0(tileHost);
        FunctionCallController.c(this.v).b(O(), this);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    int l1() {
        return 114;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile, cn.nubia.gameassist.common.QSTile
    public void n0(TileHost tileHost) {
        super.n0(tileHost);
        FunctionCallController.c(this.v).f(O());
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    String n1() {
        return "hunting_mode";
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    void q1() {
        this.f6157m.f6175i = false;
        WindowManager.LayoutParams layoutParams = this.D;
        if (layoutParams == null) {
            GaLog.b("HuntingModeTile", "mLayoutParams is null");
            return;
        }
        layoutParams.x = 266;
        layoutParams.y = 222;
        GaLog.e("HuntingModeTile", "initDefaultValue: " + this.B);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    View r1() {
        return InflaterHelper.f(R.layout.plugin_button_root, null);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.a("HuntingModeTile", "refreshState-->");
        if (Settings.Global.getUriFor("game_strengthen_mode_value").equals(uri)) {
            this.s0 = Settings.Global.getInt(this.v.getContentResolver(), "game_strengthen_mode_value", 0);
            GaLog.a("HuntingModeTile", "onChange: mGameStrengthenMode = " + this.s0);
        }
        o0();
        R1();
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    void z1() {
        if (this.f6162r) {
            GaLog.a("HuntingModeTile", "onFloatButtonClick: isFloatButtonCheck() = " + S1());
            if (S1()) {
                int m2 = Utils.m(this.v, this.r0, false);
                GaLog.e("HuntingModeTile", "onFloatButtonClick: mode = " + m2);
                Settings.Global.putInt(this.v.getContentResolver(), "game_strengthen_mode_value", m2);
                Utils.X(this.v, this.r0, m2);
            } else {
                Settings.Global.putInt(this.v.getContentResolver(), "game_strengthen_mode_value", 5);
                Utils.X(this.v, this.r0, 5);
            }
            this.v.sendBroadcast(new Intent("cn.nubia.intent.action.game_color_invert"));
        } else {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
        }
        NubiaTrackManager.p().u();
    }
}
