package cn.nubia.gameassist.dessert.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.common.ObserverManager;
import com.zte.shared.wrapper.WindowManagerWrapper;

/* loaded from: classes.dex */
public class WifiDisplayTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private final boolean w;
    private final boolean x;

    public WifiDisplayTile(QSTile.Host host) {
        super(host);
        this.w = GameKeysWrapper.b().d(host.getContext(), "cn.nubia.touping", 0);
        this.x = GameKeysWrapper.b().d(host.getContext(), "com.zte.smartcast", 0);
    }

    private boolean A0() {
        try {
            return Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_systemui_wifidisplay_status") == 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private void B0() {
        this.f6153i.startActivity(new Intent("android.settings.CAST_SETTINGS").setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED));
    }

    private void C0() {
        this.f6153i.startActivity(new Intent().setClassName("cn.nubia.touping", "cn.nubia.touping.HomeActivity").putExtra("fromotherapp_key", "FROMOTHERAPP_SYSTEMUI").setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED));
    }

    private void D0() {
        Intent intent = new Intent();
        intent.setClassName("com.zte.smartcast", "com.zte.smartcast.receiver.SmartcastReceiver");
        intent.setAction("SEARCH_DEVICE_LIST_ACTIVITY");
        intent.putExtra("From_Package", "cn.nubia.gamelauncher");
        this.f6153i.sendBroadcast(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        z0();
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_systemui_wifidisplay_status"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_systemui_wifidisplay_status"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        if (A0()) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_wifidisplay_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.game_ic_qs_wifidisplay_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(R.string.ic_qs_wifidisplay);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    public void z0() {
        if (this.w) {
            C0();
        } else if (this.x) {
            D0();
        } else {
            B0();
        }
        this.f6152h.b();
    }
}
