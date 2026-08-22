package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.SplitScreenController;
import cn.nubia.gameassist.dessert.policy.SplitScreenControllerImpl;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class SmallWindowTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private SplitScreenController w;

    public SmallWindowTile(QSTile.Host host) {
        super(host);
        this.w = new SplitScreenControllerImpl(this.f6153i);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        this.f6152h.b();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Secure.getUriFor("hasWindowReply"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Secure.getUriFor("hasWindowReply"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6169c = this.f6153i.getString(R.string.ic_qs_smallwindow);
        boolean z = (Utils.J(this.f6153i) || Utils.H(this.f6153i) || Utils.P(this.f6153i) || this.w.o() || Utils.N(this.f6153i)) ? false : true;
        state.f6175i = Utils.P(this.f6153i);
        GaLog.e("SmallWindowTile", "visible=" + z + ", state.value" + state.f6175i);
        StringBuilder sb = new StringBuilder();
        sb.append("!Utils.isMirrorStatus(mContext) = ");
        sb.append(Utils.J(this.f6153i) ^ true);
        sb.append("!Utils.isInKeyGroundRestrictedInputMode(mContext) = ");
        sb.append(Utils.H(this.f6153i) ^ true);
        sb.append("!Utils.isSmallWindowOpen(mContext) = ");
        sb.append(!Utils.P(this.f6153i));
        sb.append("!mController.isSplitScreenEnabled() = ");
        sb.append(!this.w.o());
        sb.append("!Utils.isSingleMode(mContext) = ");
        sb.append(!Utils.N(this.f6153i));
        GaLog.e("SmallWindowTile", sb.toString());
        if (!z) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_smallwindow_unpress);
            state.f6175i = false;
        } else if (state.f6175i) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_smallwindow_light);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_smallwindow_normal);
            state.f6175i = false;
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
