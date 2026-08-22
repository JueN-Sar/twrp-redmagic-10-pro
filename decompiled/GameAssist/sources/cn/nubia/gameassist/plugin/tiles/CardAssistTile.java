package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class CardAssistTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public CardAssistTile(QSTile.Host host) {
        super(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A0() {
        if (this.t) {
            B0();
        }
    }

    private void C0() {
        GaLog.e("CardAssistTile", "startSettingsPanel: ");
        this.f6152h.b();
        Intent intent = new Intent("com.zte.gamecardassist.ACTION_SHOW_SETTING");
        intent.setPackage("com.zte.gamecardassist");
        intent.putExtra("request_code", 101);
        this.f6153i.startService(intent);
    }

    public void B0() {
        GaLog.e("CardAssistTile", "startCardAssist: ");
        Intent intent = new Intent("com.zte.gamecardassist.ACTION_SHOW_PANEL");
        intent.setPackage("com.zte.gamecardassist");
        intent.putExtra("packageName", this.f6163s);
        this.f6153i.startService(intent);
    }

    public void D0() {
        GaLog.e("CardAssistTile", "stopCardAssist: ");
        Intent intent = new Intent("com.zte.gamecardassist.ACTION_HIDE_PANEL");
        intent.setPackage("com.zte.gamecardassist");
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("CardAssistTile", "handleClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("CardAssistTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                D0();
                this.t = false;
            } else {
                B0();
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
        GaLog.e("CardAssistTile", "handleSettingsClick: mCurPackage = " + this.f6163s + " , mIsGameScene = " + this.f6162r + " , mTileEnable = " + this.t);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("CardAssistTile", "isInFreeformMode");
            return true;
        }
        if (this.t) {
            C0();
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
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("card_assist_enabled_pkgs"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("card_assist_enabled_pkgs"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.e("CardAssistTile", "handleUpdateState: mCurPackage = " + this.f6163s + " ,mIsGameScene = " + this.f6162r + " ,mTileEnable = " + this.t + " state.value " + state.f6175i);
        boolean z = this.t;
        state.f6175i = z;
        state.f6168b = QSTile.ResourceIcon.b((z && this.f6162r) ? R.drawable.plugin_card_assist_on : R.drawable.plugin_card_assist_off);
        state.f6171e = QSTile.ResourceIcon.b((state.f6175i && this.f6162r) ? R.drawable.plugin_settings_on : R.drawable.plugin_settings_off);
        state.f6169c = this.f6153i.getString(R.string.plugin_label_card_assist);
        state.f6170d = this.f6153i.getString(R.string.plugin_card_assist_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "card_assist_enabled_pkgs"), this.f6163s, ",");
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
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.a
            @Override // java.lang.Runnable
            public final void run() {
                CardAssistTile.this.A0();
            }
        }, 800L);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
