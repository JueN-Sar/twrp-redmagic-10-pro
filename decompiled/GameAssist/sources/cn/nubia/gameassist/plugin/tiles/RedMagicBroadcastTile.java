package cn.nubia.gameassist.plugin.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class RedMagicBroadcastTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public RedMagicBroadcastTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.a("RedMagicBroadcastTile", "handleClick:  mCurPackage= " + this.f6163s + " mSwitch= " + this.t);
        if (!this.f6162r) {
            GaLog.a("RedMagicBroadcastTile", "handleClick: no in game");
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("setSwitchIsOpen : ");
        sb.append(!this.t);
        GaLog.e("RedMagicBroadcastTile", sb.toString());
        this.u = true;
        if (this.t) {
            Utils.U(this.f6153i, this.f6163s, "magic_elves_broadcast", true);
        } else {
            Utils.b(this.f6153i, this.f6163s, "magic_elves_broadcast", true);
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        GaLog.a("RedMagicBroadcastTile", "setListening: " + z);
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Secure.getUriFor("magic_elves_broadcast"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Secure.getUriFor("magic_elves_broadcast"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        GaLog.a("RedMagicBroadcastTile", "handleUpdateState: mTileEnable= " + this.t);
        if (this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_broadcast_on);
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_broadcast_off);
        }
        if (ZteFeature.isSupportDemi()) {
            state.f6169c = this.f6153i.getString(R.string.plugin_icon_demi_broadcast);
        } else {
            state.f6169c = this.f6153i.getString(R.string.plugin_icon_broadcast);
        }
        state.f6170d = this.f6153i.getString(R.string.plugin_broadcast_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        String t = Utils.t(this.f6153i, "magic_elves_broadcast", true);
        GaLog.a("RedMagicBroadcastTile", "isAppEnable: openedApps = " + t);
        return Utils.x(t, this.f6163s, ",");
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_M_broadcast".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                Utils.b(this.f6153i, this.f6163s, "magic_elves_broadcast", true);
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        } else if ("game_turn_off_M_broadcast".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                Utils.U(this.f6153i, this.f6163s, "magic_elves_broadcast", true);
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t) {
            Utils.U(this.f6153i, this.f6163s, "magic_elves_broadcast", true);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        GaLog.e("RedMagicBroadcastTile", "onChange");
        o0();
    }

    protected int z0() {
        return ZteFeature.isSupportDemi() ? R.string.plugin_icon_demi_broadcast : R.string.plugin_icon_broadcast;
    }
}
