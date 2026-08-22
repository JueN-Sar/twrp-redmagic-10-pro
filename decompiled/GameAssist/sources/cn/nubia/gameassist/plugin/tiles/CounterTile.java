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
public class CounterTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public CounterTile(QSTile.Host host) {
        super(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B0() {
        if (this.t) {
            C0();
        }
    }

    private void C0() {
        GaLog.e("CounterTile", "startCounter");
        Intent intent = new Intent("cn.zte.gamefloat.gamecounter.SHOW_PANEL");
        intent.setPackage("cn.zte.gamefloat");
        intent.putExtra("packageName", this.f6163s);
        this.f6153i.startService(intent);
    }

    private void D0() {
        GaLog.e("CounterTile", "startSettingsPanel: ");
        this.f6152h.b();
        Intent intent = new Intent("com.zte.gamecardassist.ACTION_SHOW_SETTING");
        intent.setPackage("cn.zte.gamefloat");
        intent.putExtra("request_code", 101);
        this.f6153i.startService(intent);
    }

    private void E0() {
        GaLog.e("CounterTile", "stopCounter");
        Intent intent = new Intent("cn.zte.gamefloat.gamecounter.HIDE_PANEL");
        intent.setPackage("cn.zte.gamefloat");
        this.f6153i.startService(intent);
    }

    protected int A0() {
        return R.string.plugin_label_counter;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("CounterTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r);
        if (!this.f6162r || Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("CounterTile", "isInFreeformMode");
            return true;
        }
        this.u = true;
        if (this.t) {
            E0();
        } else {
            C0();
            this.f6152h.b();
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("CounterTile", "handleSettingsClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (!this.f6162r || Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("CounterTile", "isInFreeformMode");
            return true;
        }
        if (this.t) {
            D0();
            return false;
        }
        ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        Uri uriFor = Settings.Global.getUriFor("game_counter_enabled_pkgs");
        if (z) {
            ObserverManager.c().b(this.f6153i, uriFor, this);
        } else {
            ObserverManager.c().d(this.f6153i, uriFor, this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("CounterTile", "handleUpdateState: mCurPackage = " + this.f6163s + " ,mIsGameScene = " + this.f6162r + " ,mTileEnable = " + this.t);
        state.f6168b = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_count_on : R.drawable.plugin_count_off);
        state.f6169c = this.f6153i.getString(A0());
        state.f6170d = this.f6153i.getString(R.string.plugin_counter_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "game_counter_enabled_pkgs"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_show_counter".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                C0();
                this.t = true;
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        if ("game_hide_counter".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                E0();
                this.t = false;
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, A0());
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
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.c
            @Override // java.lang.Runnable
            public final void run() {
                CounterTile.this.B0();
            }
        }, 800L);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
