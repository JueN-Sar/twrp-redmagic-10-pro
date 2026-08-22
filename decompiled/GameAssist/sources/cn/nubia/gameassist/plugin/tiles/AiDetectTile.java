package cn.nubia.gameassist.plugin.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class AiDetectTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public AiDetectTile(QSTile.Host host) {
        super(host);
    }

    public void A0() {
        GaLog.e("AiDetectTile", "startAiDetect: ");
        Settings.Global.putInt(this.f6153i.getContentResolver(), "game_foot_ai_detect", 1);
    }

    public void B0() {
        GaLog.e("AiDetectTile", "stopAiDetect: ");
        Settings.Global.putInt(this.f6153i.getContentResolver(), "game_foot_ai_detect", 0);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("AiDetectTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("AiDetectTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                B0();
            } else {
                A0();
                this.f6152h.b();
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("game_foot_ai_detect"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("game_foot_ai_detect"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("AiDetectTile", "handleUpdateState: mCurPackage = " + this.f6163s + " ,mIsGameScene = " + this.f6162r + " ,mAiDetectEnable = " + this.t);
        state.f6168b = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_probe_on : R.drawable.plugin_probe_off);
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.ai_detect_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Settings.Global.getInt(this.f6153i.getContentResolver(), "game_foot_ai_detect", 0) == 1;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_ai_detect_plug".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                A0();
                this.t = true;
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if ("game_turn_off_ai_detect_plug".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                B0();
                this.t = false;
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void l0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).h(this);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void n0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).i(this);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_label_ai_detect;
    }
}
