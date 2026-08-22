package cn.nubia.gameassist.dessert.tiles;

import android.content.Intent;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class AFKTile extends QSTile {
    public AFKTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.f6152h.b();
        if (Utils.P(this.f6153i)) {
            ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
            this.f6152h.b();
            return true;
        }
        if (this.f6157m.f6176j) {
            this.f6153i.sendBroadcast(new Intent("cn.nubia.intent.action.lock_screen_hang_up_option"));
        } else {
            ToastUtil.a(this.f6153i.getString(R.string.toast_unsupport_app));
        }
        NubiaTrackManager.p().l(O(), "app_name", Utils.j());
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            o0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        state.f6176j = this.f6162r;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_afk);
        state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_afk_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if ("game_turn_on_game_dock_mode".equals(str)) {
            this.f6153i.sendBroadcast(new Intent("cn.nubia.intent.action.lock_screen_hang_up_option"));
            this.f6152h.b();
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_afk);
            return;
        }
        if ("game_turn_off_game_dock_mode".equals(str)) {
            this.f6153i.sendBroadcast(new Intent("cn.nubia.systemui.NOTIFICATION_CLICKED"));
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, R.string.ic_qs_afk);
        }
    }
}
