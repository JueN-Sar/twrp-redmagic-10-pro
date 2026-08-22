package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class RotationLockTile extends QSTile implements ObserverManager.SettingCallback {
    public RotationLockTile(QSTile.Host host) {
        super(host);
    }

    private void z0(boolean z) {
        e0(null);
        if (this.f6157m.f6175i != z) {
            S();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        Settings.Global.putInt(this.f6153i.getContentResolver(), "nubia_game_lock_screen", !this.f6157m.f6175i ? 1 : 0);
        if (!this.f6157m.f6175i) {
            ToastUtil.b(this.f6153i.getResources().getString(R.string.ratation_not_change), 3);
        }
        Bundle bundle = new Bundle();
        bundle.putString("switch", this.f6157m.f6175i ? "unlock" : "lock");
        bundle.putString("app_name", SystemMgr.v());
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "rotation_lock_used", bundle);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_game_lock_screen"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_game_lock_screen"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6175i = Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_game_lock_screen", 0) != 0;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_rotation_lock);
        state.f6168b = state.f6175i ? QSTile.ResourceIcon.b(R.drawable.game_ic_qs_rotation_lock_on) : QSTile.ResourceIcon.b(R.drawable.game_ic_qs_rotation_lock_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_rotation_lock".equals(str)) {
            z0(true);
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_rotation_lock);
        } else if ("game_turn_off_rotation_lock".equals(str)) {
            z0(false);
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_rotation_lock);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
