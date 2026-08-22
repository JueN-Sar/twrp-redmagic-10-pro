package cn.nubia.gameassist.plugin.tiles;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.superresolution.SuperResolutionViewController;
import com.zte.extres.R;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.mifavor.widget.AlertDialog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SuperResolutionTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;
    private SharedPreferences w;
    private SharedPreferences.OnSharedPreferenceChangeListener x;
    private final SuperResolutionViewController y;
    private AlertDialog z;

    public SuperResolutionTile(QSTile.Host host) {
        super(host);
        this.y = SuperResolutionViewController.q(GameAssistApplication.j());
        SystemMgr.y(this.f6153i).h(this);
    }

    private void E0() {
        C0("positiveButton");
        PerformanceModeController.S().setPerformanceMode(3);
        this.y.C(this.f6163s);
        this.f6152h.b();
        this.t = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void F0(SharedPreferences sharedPreferences, String str) {
        if (str == null || !str.equals("plugin_enable_pkg_super_resolution")) {
            return;
        }
        o0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void G0(DialogInterface dialogInterface, int i2) {
        E0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void H0(DialogInterface dialogInterface, int i2) {
        C0("negativeButton");
    }

    private void I0() {
        AlertDialog alertDialog = this.z;
        if (alertDialog == null || !alertDialog.isShowing()) {
            AlertDialog a2 = new AlertDialog.Builder(this.f6153i, R.style.Theme_ZTE_Light_Dialog_Alert).m(this.f6153i.getString(com.zte.gameassist.common.R.string.dialog_default_title)).e(this.f6153i.getString(cn.nubia.gameassist.R.string.plugin_super_resolution_tips)).c(true).b(false).i(cn.nubia.gameassist.R.string.sink_state_on, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.plugin.tiles.o
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    SuperResolutionTile.this.G0(dialogInterface, i2);
                }
            }).f(cn.nubia.gameassist.R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.plugin.tiles.p
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    SuperResolutionTile.this.H0(dialogInterface, i2);
                }
            }).a();
            this.z = a2;
            a2.getWindow().setType(2038);
            this.z.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.z.show();
        }
    }

    public void C0(String str) {
        GaLog.a("SuperResolutionTile", "SuperResolution Tips dismiss reason = " + str);
        AlertDialog alertDialog = this.z;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.z.dismiss();
    }

    protected int D0() {
        return cn.nubia.gameassist.R.string.plugin_label_super_resolution;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void J(PrintWriter printWriter, String str) {
        super.J(printWriter, str);
        printWriter.println("      SuperResolutionTile:");
        printWriter.println("      mTileEnable = " + this.t);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("SuperResolutionTile", "handleClick: mCurPackage = " + this.f6163s + ", mIsGameScene = " + this.f6162r + ", mTileEnable = " + this.t + ", mode = " + PerformanceModeController.S().getPerformanceMode(this.f6163s));
        boolean z = Settings.Global.getInt(this.f6153i.getContentResolver(), "low_power", 0) == 1;
        int performanceMode = PerformanceModeController.S().getPerformanceMode(this.f6163s);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.game_close_pip));
                GaLog.a("SuperResolutionTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                this.y.k(this.f6163s);
                this.t = false;
            } else if (z) {
                ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.performancemode_is_lowpowermode_tip));
            } else if (performanceMode == 5 && ZteFeature.isSM8850Project()) {
                ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.performancemode_is_DIABLO_tip));
            } else if (performanceMode == 1 || performanceMode == 2) {
                this.f6152h.b();
                I0();
            } else {
                this.y.C(this.f6163s);
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
        GaLog.e("SuperResolutionTile", "handleSettingsClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.game_close_pip));
            GaLog.a("SuperResolutionTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        this.y.J(this.f6163s);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        this.w = this.f6153i.getSharedPreferences("data", 0);
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: cn.nubia.gameassist.plugin.tiles.n
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                SuperResolutionTile.this.F0(sharedPreferences, str);
            }
        };
        this.x = onSharedPreferenceChangeListener;
        if (z) {
            this.w.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        } else {
            this.w.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("SuperResolutionTile", "handleUpdateState: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.plugin_super_resolution_on);
            state.f6171e = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.plugin_super_resolution_off);
            state.f6171e = QSTile.ResourceIcon.b(cn.nubia.gameassist.R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(D0());
        state.f6170d = this.f6153i.getString(cn.nubia.gameassist.R.string.plugin_label_super_resolution_introduction_new);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(SharedPreferencesUtil.k(this.f6153i).A(), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (!"game_open_super_screen_quality_config".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_super_screen_quality_config".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                if (this.t) {
                    this.t = false;
                }
                this.y.j("onAICommand");
                this.y.k(this.f6163s);
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, D0());
                return;
            }
            return;
        }
        if (this.t) {
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, D0());
            return;
        }
        boolean z = Settings.Global.getInt(this.f6153i.getContentResolver(), "low_power", 0) == 1;
        int performanceMode = PerformanceModeController.S().getPerformanceMode(this.f6163s);
        if (z) {
            ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.performancemode_is_lowpowermode_tip));
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
            return;
        }
        if (performanceMode == 5 && ZteFeature.isSM8850Project()) {
            ToastUtil.a(this.f6153i.getString(cn.nubia.gameassist.R.string.performancemode_is_DIABLO_tip));
            return;
        }
        if (performanceMode == 1 || performanceMode == 2) {
            I0();
            GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, true);
        } else {
            this.y.C(this.f6163s);
            this.t = true;
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, D0());
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        super.z();
        GaLog.a("SuperResolutionTile", "onGameStop");
        try {
            C0("no game scene");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
