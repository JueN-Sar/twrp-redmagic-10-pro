package cn.nubia.gameassist.dessert.tiles;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.tips.TipsUtils;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class GameHandlerTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;
    private boolean w;
    private boolean x;
    private final BroadcastReceiver y;

    public GameHandlerTile(QSTile.Host host) {
        super(host);
        this.x = false;
        this.y = new BroadcastReceiver() { // from class: cn.nubia.gameassist.dessert.tiles.GameHandlerTile.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                GameHandlerTile.this.x = intent.getBooleanExtra("isConnected", false);
                GaLog.b(((QSTile) GameHandlerTile.this).f6151c, "receiver game handler:" + ((QSTile) GameHandlerTile.this).f6157m.f6175i);
                GameHandlerTile.this.o0();
            }
        };
        D0();
    }

    private boolean C0(String str) {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_operation_devices_enable"), str, ";");
    }

    private void D0() {
        this.f6153i.registerReceiver(this.y, new IntentFilter("cn.nubia.gamelauncher.ACTION_GAMEHANDLE_CONNECTION_STATE_CHANGE"), null, this.f6154j, 2);
        TipsUtils.r(this.f6153i);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        NubiaTrackManager.p().k("handler");
        if (!this.f6157m.f6175i) {
            return true;
        }
        this.f6152h.b();
        GaLog.e(this.f6151c, "handleClick isHorizontalGame=" + this.w);
        if (!this.w) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
            return false;
        }
        this.f6153i.sendBroadcast(new Intent("cn.nubia.intent.action.HAND_SHANK_SCREEN_MAP_OPTION"));
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (!z) {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_operation_devices_state"), this);
            return;
        }
        this.w = SystemMgr.H() && RotationMgr.j();
        ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_operation_devices_state"), this);
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        String j2 = Utils.j();
        GaLog.e(this.f6151c, j2 + " mOn= " + this.x);
        boolean z = false;
        boolean z2 = C0(j2) && Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_operation_devices_state", 0) > 0;
        if (this.x && !z2) {
            z = true;
        }
        state.f6175i = z;
        state.f6168b = QSTile.ResourceIcon.b(z ? R.drawable.game_ic_qs_handle_light : R.drawable.game_ic_qs_handle_unpress);
        state.f6169c = this.f6153i.getString(R.string.handler_setting);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }
}
