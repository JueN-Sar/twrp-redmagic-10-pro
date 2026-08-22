package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
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

/* loaded from: classes.dex */
public class CombatPowerTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;

    public CombatPowerTile(QSTile.Host host) {
        super(host);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B0() {
        if (this.t) {
            E0(0, 1);
        }
    }

    private void C0() {
        String str;
        String string = Settings.Global.getString(this.f6153i.getContentResolver(), "redmagic_ce_switch");
        String str2 = this.f6163s + ",";
        String str3 = "," + this.f6163s;
        if (TextUtils.isEmpty(string)) {
            str = this.f6163s;
        } else if (string.equals(this.f6163s)) {
            str = string.replace(this.f6163s, "");
        } else if (string.contains(str2)) {
            str = string.replace(str2, "");
        } else if (string.contains(str3)) {
            str = string.replace(str3, "");
        } else if (String.valueOf(string.charAt(string.length() - 1)).equals(",")) {
            str = string + this.f6163s;
        } else {
            str = string + str3;
        }
        GaLog.a("CombatPowerTile", "putSettingsForCombatPower: openedApps = " + str + " , mCurPackage = " + this.f6163s);
        Settings.Global.putString(this.f6153i.getContentResolver(), "redmagic_ce_switch", str);
    }

    private void D0() {
        this.f6163s = SystemMgr.t();
        this.t = f0();
        this.f6155k.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.b
            @Override // java.lang.Runnable
            public final void run() {
                CombatPowerTile.this.B0();
            }
        }, 800L);
    }

    private void E0(int i2, int i3) {
        GaLog.a("CombatPowerTile", "startCombatPowerService: type = " + i2 + " , flag_showDialog = " + i3);
        Intent intent = new Intent();
        if (Utils.R()) {
            intent.setAction("cn.nubia.gamepanel.POWERPANELSERVICE");
            intent.setPackage("cn.nubia.gamelauncher");
        } else {
            intent.setAction("cn.zte.powerpanel.POWERPANELSERVICE");
            intent.setPackage("cn.zte.gamefloat");
        }
        intent.putExtra("currentPkg", this.f6163s);
        intent.putExtra("type", i2);
        intent.putExtra("powerPanelDialogShow", i3);
        this.f6153i.startService(intent);
    }

    protected int A0() {
        return R.string.plugin_combat_power_title;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.a("CombatPowerTile", "handleClick, mIsGameScene:" + this.f6162r + " mTileEnable:" + this.t + " curPkg:" + this.f6163s);
        if (this.f6162r) {
            this.u = true;
            if (this.t) {
                E0(1, 1);
            } else {
                this.f6152h.b();
                E0(0, 0);
            }
            C0();
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        }
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("redmagic_ce_switch"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("redmagic_ce_switch"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.a("CombatPowerTile", "handleUpdateState, mIsGameScene:" + this.f6162r + " mTileEnable:" + this.t + " curPkg:" + this.f6163s);
        boolean z = this.t;
        state.f6175i = z;
        if (this.f6162r && z) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_data_panel_switch_on);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_data_panel_switch_off);
        }
        state.f6169c = this.f6153i.getString(A0());
        state.f6170d = this.f6153i.getString(R.string.plugin_combat_power_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "redmagic_ce_switch"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_game_touch_data".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                E0(0, 0);
                C0();
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, A0());
            return;
        }
        if ("game_turn_off_game_touch_data".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                E0(1, 1);
                C0();
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, A0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void l0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).h(this);
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void n0(TileHost tileHost) {
        SystemMgr.y(this.f6153i).i(this);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        D0();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        D0();
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
