package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
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
public class GamePredictionTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public GamePredictionTile(QSTile.Host host) {
        super(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B0() {
        if (this.t) {
            C0(true);
        }
    }

    private void C0(boolean z) {
        GaLog.e("GamePredictionTile", z ? "startGamePrediction" : " stopGamePrediction");
        Intent intent = new Intent();
        intent.setClassName("cn.zte.gamefloat", "cn.zte.gamefloat.wrp.WinRatePredictionService");
        intent.putExtra("package_name", Utils.j());
        intent.putExtra("show", z);
        this.f6153i.startService(intent);
    }

    private void D0() {
        if (this.t) {
            C0(false);
        } else {
            C0(true);
            this.f6152h.b();
        }
    }

    protected int A0() {
        return R.string.plugin_game_predicition;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("GamePredictionTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r);
        if (!this.f6162r || Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("GamePredictionTile", "isInFreeformMode");
            return true;
        }
        this.u = true;
        D0();
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        Uri uriFor = Settings.Global.getUriFor("game_prediction_enabled_pkgs");
        if (z) {
            ObserverManager.c().b(this.f6153i, uriFor, this);
        } else {
            ObserverManager.c().d(this.f6153i, uriFor, this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("GamePredictionTile", "handleUpdateState: mCurPackage = " + this.f6163s + " ,mIsGameScene = " + this.f6162r + " ,mTileEnable = " + this.t);
        state.f6168b = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_game_prediction_on : R.drawable.plugin_game_prediction_off);
        state.f6169c = this.f6153i.getString(A0());
        state.f6170d = this.f6153i.getString(R.string.plugin_game_prediction_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "game_prediction_enabled_pkgs"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        InMsg f2;
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_game_predicition".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                C0(true);
                this.t = true;
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        if ("game_turn_off_game_predicition".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                C0(false);
                this.t = false;
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        if (!"game_query_game_predicition".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("positive".equals(str) && (f2 = inMsg.f()) != null && "game_query_game_predicition".equals(f2.e())) {
                if (!this.t) {
                    C0(true);
                    this.t = true;
                }
                GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, A0());
                return;
            }
            return;
        }
        if (!this.t) {
            GameAgentUtil.b(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        float f3 = Settings.Global.getFloat(this.f6153i.getContentResolver(), "game_predication_value", -1.0f);
        double d2 = f3;
        if (d2 < -0.001d || d2 > 1.001d) {
            GameAgentUtil.n(this.f6153i, iGameAssistClientCallback, inMsg);
        } else {
            GameAgentUtil.i(this.f6153i, iGameAssistClientCallback, inMsg, this.f6153i.getString(com.zte.gameassist.aiagent.R.string.aiagent_winning_rate_prediction, Integer.valueOf((int) (f3 * 100.0f))), true);
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
        this.f6163s = SystemMgr.t();
        this.t = f0();
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.m
            @Override // java.lang.Runnable
            public final void run() {
                GamePredictionTile.this.B0();
            }
        }, 800L);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
