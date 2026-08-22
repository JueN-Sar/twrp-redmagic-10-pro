package cn.nubia.gameassist.plugin.tiles;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class OneKeyLinkTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private static final Uri w = Uri.parse("content://cn.nubia.gamehelper.db.recordmotion");
    private boolean v;

    public OneKeyLinkTile(QSTile.Host host) {
        super(host);
    }

    private void A0(int i2) {
        GaLog.e("OneKeyLinkTile", "sendOneKeyLinkBroadcast: " + i2);
        Intent intent = new Intent();
        intent.setAction("cn.nubia.gamelauncher.action.START_ONEKEYLINGK");
        intent.putExtra("packagename", this.f6163s);
        intent.putExtra(Constants.EXTRA_ENABLE, i2);
        this.f6153i.sendBroadcast(intent);
    }

    private void B0() {
        this.f6152h.b();
        ContentResolver contentResolver = this.f6153i.getContentResolver();
        Uri uri = w;
        Bundle call = contentResolver.call(uri, "control_center", "need_show", (Bundle) null);
        boolean z = call != null && call.getBoolean("need_show");
        GaLog.a("OneKeyLinkTile", "startOneKeyLinkSettings: " + z);
        if (z) {
            this.f6153i.getContentResolver().call(uri, "control_center", "show_main_page", (Bundle) null);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("OneKeyLinkTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            A0(0);
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("OneKeyLinkTile", "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                A0(0);
            } else if (SharedPreferencesUtil.k(this.f6153i).m(this.f6163s)) {
                B0();
                SharedPreferencesUtil.k(this.f6153i).V(this.f6163s);
            } else {
                this.f6152h.b();
                A0(1);
            }
        }
        NubiaTrackManager.p().l(O(), "app_name", this.f6163s);
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("OneKeyLinkTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r) {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return false;
        }
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            GaLog.a("OneKeyLinkTile", "isInFreeformMode");
            return true;
        }
        if (!this.t) {
            ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            return false;
        }
        this.f6152h.b();
        B0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("gamehelpermodule_enable_pkgs"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("gamehelpermodule_enable_pkgs"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState(): ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable= ");
        sb.append(this.t);
        GaLog.a("OneKeyLinkTile", sb.toString());
        if (this.f6162r && this.t) {
            state.f6175i = true;
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_keylink_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
        } else {
            state.f6175i = false;
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_keylink_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
        }
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_keylink_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "gamehelpermodule_enable_pkgs"), this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if (!"game_turn_on_one_click".equals(str) || !i0(iGameAssistClientCallback, inMsg)) {
            if ("game_turn_off_one_click".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
                A0(0);
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
                return;
            }
            return;
        }
        if (this.t) {
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if (SharedPreferencesUtil.k(this.f6153i).m(this.f6163s)) {
            B0();
            SharedPreferencesUtil.k(this.f6153i).V(this.f6163s);
            GameAgentUtil.c(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else {
            this.f6152h.b();
            A0(1);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
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

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_icon_keylink;
    }
}
