package cn.nubia.gameassist.dessert.tiles;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.systemwrapper.ActivityManagerWrapper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class RecordTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private int w;
    private boolean x;

    public RecordTile(TileHost tileHost) {
        super(tileHost);
    }

    private void A0() {
        ComponentName componentName = new ComponentName("com.android.ztescreenshot", "com.android.ztescreenshot.recordscreen.RecordscreenService");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        this.f6153i.startService(intent);
    }

    private void z0() {
        A0();
        NubiaTrackManager.p().k(O());
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        QSTile.State state = this.f6157m;
        if (state.f6175i || !state.f6176j) {
            return true;
        }
        this.f6152h.b();
        z0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.x == z) {
            return;
        }
        this.x = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.System.getUriFor("ss_record_status"), this);
            ObserverManager.c().b(this.f6153i, Settings.System.getUriFor("nubia_wfd_on_off"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.System.getUriFor("ss_record_status"), this);
            ObserverManager.c().d(this.f6153i, Settings.System.getUriFor("nubia_wfd_on_off"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        boolean z = this.w == 1;
        state.f6175i = z;
        if (this.v) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_record_uninstall);
            state.f6176j = false;
        } else if (z) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_record_on);
            state.f6176j = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_record_off);
            state.f6176j = true;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_record);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        this.w = Settings.System.getInt(this.f6153i.getContentResolver(), "ss_record_status", 0);
        this.v = ActivityManagerWrapper.b().e(this.f6153i);
        GaLog.a(this.f6151c, "onChange() mStatus:" + this.w);
        o0();
    }
}
