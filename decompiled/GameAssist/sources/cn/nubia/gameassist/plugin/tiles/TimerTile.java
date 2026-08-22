package cn.nubia.gameassist.plugin.tiles;

import android.net.Uri;
import android.os.Handler;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.plugin.timer.TimerMgr;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class TimerTile extends QSTile implements ObserverManager.SettingCallback {
    public TimerTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.t = !f0();
        this.u = true;
        TimerMgr.r().o(this.t);
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.TimerTile.1
            @Override // java.lang.Runnable
            public void run() {
                TimerTile.this.o0();
            }
        }, 200L);
        NubiaTrackManager.p().u();
        return super.S();
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
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.plugin_timer_introduction);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.plugin_timer_on : R.drawable.plugin_timer_off);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return TimerMgr.r().w();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_game_stopwatch".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (!this.t) {
                TimerMgr.r().o(true);
                new Handler().postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.TimerTile.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TimerTile.this.o0();
                    }
                }, 200L);
                this.t = true;
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if ("game_turn_off_game_stopwatch".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (this.t) {
                TimerMgr.r().o(false);
                new Handler().postDelayed(new Runnable() { // from class: cn.nubia.gameassist.plugin.tiles.TimerTile.3
                    @Override // java.lang.Runnable
                    public void run() {
                        TimerTile.this.o0();
                    }
                }, 200L);
                this.t = false;
            }
            GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_string_timer;
    }
}
