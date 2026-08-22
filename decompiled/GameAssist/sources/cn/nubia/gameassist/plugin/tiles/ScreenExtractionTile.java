package cn.nubia.gameassist.plugin.tiles;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class ScreenExtractionTile extends QSTile implements ScreenExtractionManager.Callback {
    private String A;
    private PackageManager B;
    private ScreenExtractionManager C;
    private boolean v;
    private String w;
    private String x;
    private boolean y;
    private long z;

    public ScreenExtractionTile(QSTile.Host host) {
        super(host);
        this.B = this.f6153i.getPackageManager();
        this.C = ScreenExtractionManager.w();
    }

    private String z0(long j2) {
        return j2 == 0 ? "0" : String.format("%.1f", Float.valueOf((j2 * 1.0f) / 1000.0f));
    }

    protected int A0() {
        return R.string.plugin_icon_screen_extraction;
    }

    public void B0(String str) {
        if (this.y) {
            return;
        }
        GaLog.a("ScreenExtractionTile", "track start " + str);
        this.y = true;
        this.z = SystemClock.uptimeMillis();
        this.A = str;
    }

    public void C0() {
        CharSequence charSequence;
        if (this.y) {
            this.y = false;
            if (TextUtils.isEmpty(this.A)) {
                GaLog.a("ScreenExtractionTile", "track end but empty pkg " + this.A);
                return;
            }
            long uptimeMillis = SystemClock.uptimeMillis() - this.z;
            try {
                charSequence = this.B.getApplicationInfo(SystemMgr.A(this.A), 0).loadLabel(this.B);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                Settings.Global.putInt(this.f6153i.getContentResolver(), this.x, 0);
                charSequence = "";
            }
            Bundle bundle = new Bundle();
            bundle.putCharSequence("app_name", charSequence);
            bundle.putString("package_name", this.A);
            bundle.putString("duration", z0(uptimeMillis));
            GaLog.a("ScreenExtractionTile", "track end, package name: " + this.A + ", label: " + ((Object) charSequence) + ",duration=" + uptimeMillis);
            NubiaTrackManager.p().x("cn.nubia.gamelauncher", "screen_extraction_used", bundle);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void J(PrintWriter printWriter, String str) {
        super.J(printWriter, str);
        ScreenExtractionManager.w().r(null, printWriter, str);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.w = Utils.j();
        boolean H = SystemMgr.H();
        GaLog.e("ScreenExtractionTile", "handleClick() isGame : " + H + ", mTileEnable : " + this.t + ", mCurApp : " + this.w);
        if (H) {
            this.u = true;
            if (this.t) {
                ScreenExtractionManager.w().q(this.w);
                C0();
                ScreenExtractionManager.w().S(this.w);
            } else {
                this.f6152h.b();
                if (Utils.P(this.f6153i)) {
                    ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                    return true;
                }
                ScreenExtractionManager.w().s(this.w);
                B0(this.w);
            }
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            GaLog.e("ScreenExtractionTile", "handleClick() isGame : " + H + ", mCurApp : " + this.w);
        }
        NubiaTrackManager.p().u();
        return true;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            return true;
        }
        ScreenExtractionManager.w().T(this.w);
        return true;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        GaLog.a("ScreenExtractionTile", "setFloatButtonListening ：" + z);
        if (this.v == z) {
            return;
        }
        this.v = z;
        this.w = Utils.j();
        this.t = f0();
        if (z) {
            this.C.p(this);
        } else {
            this.C.R(this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_screen_extraction_on : R.drawable.plugin_screen_extraction_off);
        state.f6171e = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        state.f6169c = this.f6153i.getString(A0());
        state.f6170d = this.f6153i.getString(R.string.plugin_screen_extraction_introduction);
        GaLog.a("ScreenExtractionTile", "handleUpdateState= " + state.f6169c + " " + state.f6175i);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return this.C.y(this.w) && this.C.z();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_custom_screen_map".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                if (Utils.P(this.f6153i)) {
                    ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                    return;
                } else {
                    ScreenExtractionManager.w().s(this.w);
                    B0(this.w);
                }
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        if ("game_turn_off_custom_screen_map".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                ScreenExtractionManager.w().q(this.w);
                C0();
                ScreenExtractionManager.w().S(this.w);
                ScreenExtractionManager.w().t();
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, A0());
        }
    }

    @Override // cn.nubia.plugin.screenextraction.ScreenExtractionManager.Callback
    public void l() {
        GaLog.a("ScreenExtractionTile", "refreshState-->");
        o0();
    }
}
