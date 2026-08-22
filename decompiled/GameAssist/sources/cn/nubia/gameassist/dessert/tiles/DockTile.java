package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.common.ObserverManager;

/* loaded from: classes.dex */
public class DockTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public DockTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        Settings.Global.putInt(this.f6153i.getContentResolver(), "nubia_game_dock_station_enable", !this.f6157m.f6175i ? 1 : 0);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_game_dock_station_enable"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_game_dock_station_enable"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        boolean z = Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_game_dock_station_enable", 0) == 1;
        state.f6175i = z;
        if (z) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_dock_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_dock_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_expansion_dock);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
