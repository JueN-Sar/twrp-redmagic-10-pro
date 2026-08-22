package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.policy.liquidcooling.LiquidCoolingAnimationController;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class LiquidCoolingTile extends QSTile implements ObserverManager.SettingCallback {
    public LiquidCoolingTile(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        Settings.System.putInt(this.f6153i.getContentResolver(), "liquid_cooling_main_switch", this.f6157m.f6175i ? -3 : 3);
        if (!this.f6157m.f6175i) {
            this.f6152h.b();
            LiquidCoolingAnimationController.h(this.f6153i).j();
        }
        NubiaTrackManager.p().C(O(), !this.f6157m.f6175i);
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.System.getUriFor("liquid_cooling_main_switch"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.System.getUriFor("liquid_cooling_main_switch"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        state.f6175i = Settings.System.getInt(this.f6153i.getContentResolver(), "liquid_cooling_main_switch", 0) > 0;
        state.f6169c = this.f6153i.getString(R.string.ic_qs_liquid_cooling);
        state.f6168b = state.f6175i ? QSTile.ResourceIcon.b(R.drawable.game_ic_qs_liquid_cooling_on) : QSTile.ResourceIcon.b(R.drawable.game_ic_qs_liquid_cooling_off);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
