package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.shared.wrapper.GameKeysHelperWrapper;

/* loaded from: classes.dex */
public class GameNoNotiTile extends QSTile implements ObserverManager.SettingCallback {
    private int v;

    public GameNoNotiTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        if (this.f6157m.f6175i) {
            GameKeysWrapper.b().a(this.f6153i, 4);
        } else {
            GameKeysWrapper.b().e(this.f6153i, 4);
        }
        NubiaTrackManager.p().C(O(), !this.f6157m.f6175i);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor(GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6175i = (this.v & 4) != 0;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_no_noti);
        state.f6168b = QSTile.ResourceIcon.b(state.f6175i ? R.drawable.game_ic_qs_noti_on : R.drawable.game_ic_qs_noti_off);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        this.v = GameKeysWrapper.b().c(this.f6153i);
        o0();
    }
}
