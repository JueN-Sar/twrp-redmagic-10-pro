package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.plugin.gameshader.ShaderUtils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class InvestigateModeTile extends FloatButtonQSTile implements ObserverManager.SettingCallback {
    private boolean q0;
    private int r0;
    private boolean s0;
    private long t0;
    private String u0;
    private PackageManager v0;
    private boolean w0;

    public InvestigateModeTile(QSTile.Host host) {
        super(host);
        this.r0 = 0;
        this.w0 = ZteFeature.isSupportGameDisplayFilterEffect();
        this.v0 = this.v.getPackageManager();
    }

    private String R1(long j2) {
        return j2 == 0 ? "0" : String.format("%.1f", Float.valueOf((j2 * 1.0f) / 1000.0f));
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    public void I1(boolean z) {
        GaLog.a("InvestigateModeTile", "setFloatButtonListening ：" + z);
        if (this.q0 == z) {
            return;
        }
        this.r0 = Settings.Global.getInt(this.v.getContentResolver(), "investigation_mode_enable" + this.f6163s, 0);
        this.q0 = z;
        if (z) {
            ObserverManager.c().b(this.v, Settings.Global.getUriFor("investigation_mode_enable" + this.f6163s), this);
            return;
        }
        ObserverManager.c().d(this.v, Settings.Global.getUriFor("investigation_mode_enable" + this.f6163s), this);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile, cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("InvestigateModeTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (this.f6162r) {
            this.u = true;
        } else {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
        }
        if (!this.f6157m.f6175i) {
            return false;
        }
        this.f6152h.b();
        return false;
    }

    protected int S1() {
        return R.string.plugin_icon_investigate;
    }

    protected void T1() {
        this.f6155k.post(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.InvestigateModeTile.1
            @Override // java.lang.Runnable
            public void run() {
                InvestigateModeTile investigateModeTile = InvestigateModeTile.this;
                View view = investigateModeTile.C;
                if (view != null) {
                    view.setBackgroundResource(investigateModeTile.U1() ? R.drawable.plugin_button_open : R.drawable.plugin_button_close);
                }
                GaLog.a("InvestigateModeTile", "handleUpdateFloatButton= " + InvestigateModeTile.this.U1());
            }
        });
    }

    boolean U1() {
        int i2 = Settings.Global.getInt(this.v.getContentResolver(), "investigation_mode_enable" + this.f6163s, 0);
        this.r0 = i2;
        return i2 == 1;
    }

    public void V1(String str) {
        if (this.s0) {
            return;
        }
        GaLog.a("InvestigateModeTile", "track start " + str);
        this.s0 = true;
        this.t0 = SystemClock.uptimeMillis();
        this.u0 = str;
    }

    public void W1() {
        CharSequence charSequence;
        if (this.s0) {
            this.s0 = false;
            if (TextUtils.isEmpty(this.u0)) {
                GaLog.a("InvestigateModeTile", "track end but empty pkg " + this.u0);
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis() - this.t0;
            try {
                charSequence = this.v0.getApplicationInfo(this.u0, 0).loadLabel(this.v0);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                charSequence = "";
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("app_name", charSequence);
            bundle.putString("package_name", this.u0);
            bundle.putString("duration", R1(uptimeMillis));
            GaLog.a("InvestigateModeTile", "track end, package name: " + this.u0 + ", label: " + ((Object) charSequence) + ",duration=" + uptimeMillis);
            NubiaTrackManager.p().x("cn.nubia.gamelauncher", "investigation_mode_used", bundle);
        }
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_investigate_on : R.drawable.plugin_investigate_off);
        state.f6169c = this.v.getString(S1());
        state.f6170d = this.v.getString(R.string.plugin_investigate_introduction);
        this.r0 = Settings.Global.getInt(this.v.getContentResolver(), "investigation_mode_enable" + this.f6163s, 0);
        GaLog.a("InvestigateModeTile", "handleUpdateState= " + state.f6169c + " " + state.f6175i + " , mIsInvestigateModeEnable = " + this.r0);
        if (this.w0 && state.f6175i && this.r0 == 1) {
            ShaderUtils.l(this.I, true);
            GaLog.e("InvestigateModeTile", "handleUpdateState setInvestigate");
        }
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    protected void c1() {
        if (this.r0 != 1) {
            if (this.w0) {
                ShaderUtils.l(this.I, false);
                return;
            }
            return;
        }
        if (this.w0) {
            ShaderUtils.l(this.I, false);
        } else {
            Settings.Global.putInt(this.v.getContentResolver(), "investigation_mode_enable" + this.f6163s, 0);
        }
        GaLog.e("InvestigateModeTile", "callStopPluginService InvestigateMode");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return this.f6157m.f6175i;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    int j1() {
        return 84;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (str.equals("game_turn_on_Investigation_mode") && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.f6157m.f6175i) {
                S();
            }
            if (this.r0 == 0) {
                z1();
            }
            GameAgentUtil.e(this.v, iGameAssistClientCallback, inMsg, S1());
        }
        if (str.equals("game_turn_off_Investigation_mode") && i0(iGameAssistClientCallback, inMsg)) {
            if (this.f6157m.f6175i) {
                S();
            }
            if (this.r0 != 0) {
                z1();
            }
            GameAgentUtil.d(this.v, iGameAssistClientCallback, inMsg, S1());
        }
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    String k1() {
        return this.v.getString(R.string.plugin_float_button_investigate);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    int l1() {
        return 114;
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    String n1() {
        return "investigation_mode";
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile, com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        super.z();
        if (this.w0) {
            ShaderUtils.l(this.I, false);
        }
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    void q1() {
        this.f6157m.f6175i = false;
        WindowManager.LayoutParams layoutParams = this.D;
        if (layoutParams == null) {
            GaLog.b("InvestigateModeTile", "mLayoutParams is null");
            return;
        }
        layoutParams.x = 266;
        layoutParams.y = 486;
        GaLog.e("InvestigateModeTile", "initDefaultValue: " + this.B);
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    View r1() {
        return InflaterHelper.f(R.layout.plugin_button_root, null);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.a("InvestigateModeTile", "refreshState-->");
        o0();
        T1();
    }

    @Override // cn.nubia.gameassist.plugin.tiles.FloatButtonQSTile
    void z1() {
        if (!this.f6162r) {
            ToastUtil.a(this.v.getString(R.string.toast_unsupport_app));
        } else if (this.r0 == 0) {
            if (this.w0) {
                ShaderUtils.l(this.I, true);
                Settings.Global.putInt(this.v.getContentResolver(), "investigation_mode_enable" + this.f6163s, 1);
                GaLog.e("InvestigateModeTile", "handleClick() InvestigateMode : display filter");
            } else {
                Intent intent = new Intent("cn.nubia.gameassist.action.START_INVESTIGATIONMODE_BROADCAST");
                intent.setPackage("cn.nubia.gameassist");
                this.v.startService(intent);
                GaLog.e("InvestigateModeTile", "handleClick() InvestigateMode : " + intent);
            }
            this.r0 = 1;
            V1(this.f6163s);
        } else {
            if (this.w0) {
                ShaderUtils.l(this.I, false);
            }
            this.r0 = 0;
            Settings.Global.putInt(this.v.getContentResolver(), "investigation_mode_enable" + this.f6163s, 0);
            W1();
        }
        NubiaTrackManager.p().u();
    }
}
