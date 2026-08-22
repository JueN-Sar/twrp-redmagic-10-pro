package cn.nubia.gameassist.dessert.tiles;

import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class BarrageMessageTiles extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public BarrageMessageTiles(QSTile.Host host) {
        super(host);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        int i2 = Settings.Global.getInt(this.f6153i.getContentResolver(), "gsc_barrage_message", 0);
        GaLog.a("BarrageMessageTiles", "handleClick, value:" + i2);
        if (i2 == 1) {
            Settings.Global.putInt(this.f6153i.getContentResolver(), "gsc_barrage_message", 0);
        } else {
            Settings.Global.putInt(this.f6153i.getContentResolver(), "gsc_barrage_message", 1);
        }
        return super.S();
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("gsc_barrage_message"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("gsc_barrage_message"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        int i2 = Settings.Global.getInt(this.f6153i.getContentResolver(), "gsc_barrage_message", 0);
        GaLog.a("BarrageMessageTiles", "handleUpdateState: value : " + i2);
        if (i2 == 1) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_barrage_light);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_barrage_normal);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_game_barrage);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public void m0(boolean z) {
        super.m0(z);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
