package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class OperationDevicesTile extends QSTile implements ObserverManager.SettingCallback, GameMonitor.Callback {
    private boolean v;
    private int w;
    private boolean x;

    public OperationDevicesTile(QSTile.Host host) {
        super(host);
        this.w = 0;
        this.x = false;
    }

    private void A0(boolean z) {
        Intent intent = new Intent("cn.nubia.gamepad.startGamepadService");
        intent.setPackage("cn.nubia.gamepad");
        intent.putExtra("action_type", !z ? 1 : 0);
        intent.putExtra("packagename", this.f6163s);
        this.f6153i.startService(intent);
    }

    private void B0(boolean z) {
        Intent intent = new Intent("cn.nubia.keymapcenter.intent.action.LKM_MAP");
        intent.setPackage("cn.nubia.keymapcenter");
        intent.putExtra("reason", z ? "enable_local_key_mouse" : "disable_local_key_mouse");
        intent.putExtra("package_name", this.f6163s);
        this.f6153i.startService(intent);
    }

    private void C0() {
        Intent intent = new Intent();
        int i2 = this.w;
        if (i2 == 0) {
            intent.setAction("cn.nubia.controlcenter.CONTROLCENTERSERVICE");
            intent.setPackage("cn.nubia.gamepad");
            intent.putExtra("packagename", this.f6163s);
            intent.putExtra("pagetype", "LinkList");
        } else if (i2 == 1) {
            intent.setAction("cn.nubia.gamepad.startGamepadService");
            intent.setPackage("cn.nubia.gamepad");
            intent.putExtra("packagename", this.f6163s);
            intent.putExtra("action_type", 5);
        } else if (i2 == 2) {
            intent.setAction("cn.nubia.keymapcenter.intent.action.LKM_MAP");
            intent.setPackage("cn.nubia.keymapcenter");
            intent.putExtra("package_name", this.f6163s);
            intent.putExtra("reason", "set_local_key_mouse");
        }
        this.f6153i.startService(intent);
    }

    private void z0(boolean z) {
        GaLog.e(this.f6151c, "startOperationDevices :  mOperationDevicesState = " + this.w);
        int i2 = this.w;
        if (i2 == 0) {
            C0();
        } else if (i2 == 1) {
            A0(z);
        } else {
            if (i2 != 2) {
                return;
            }
            B0(z);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        this.f6152h.b();
        this.w = Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_operation_devices_state", 0);
        GaLog.e(this.f6151c, "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r + " " + this.w);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a(this.f6151c, "isInFreeformMode");
                return true;
            }
            this.u = true;
            if (this.t) {
                z0(false);
            } else {
                z0(true);
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        this.w = Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_operation_devices_state", 0);
        GaLog.e(this.f6151c, "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r + " " + this.w);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a(this.f6151c, "isInFreeformMode");
                return true;
            }
            if (!this.t || this.w <= 0) {
                ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
            } else {
                this.f6152h.b();
                C0();
            }
        }
        return false;
    }

    @Override // cn.nubia.gameassist.dessert.policy.Listenable
    public void c(boolean z) {
        if (this.v == z) {
            return;
        }
        this.v = z;
        if (z) {
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("nubia_operation_devices_state"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("nubia_operation_devices_state"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        this.f6162r = this.f6162r && RotationMgr.j();
        this.w = Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_operation_devices_state", 0);
        String str = this.f6151c;
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState(): ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable= ");
        sb.append(this.t);
        sb.append(" mOperationDevicesState= ");
        sb.append(this.w);
        GaLog.e(str, sb.toString());
        if (this.f6162r && this.w > 0 && this.t) {
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
            int i2 = this.w;
            if (i2 == 1) {
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_operation_handle);
            } else if (i2 == 2) {
                state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_operation_keymouse);
            }
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_operation_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        if (ZteFeature.isSupportPeripheralControl()) {
            state.f6169c = this.f6153i.getString(R.string.plugin_icon_peripheral_control);
        } else {
            state.f6169c = this.f6153i.getString(R.string.plugin_icon_operation);
        }
        if (ZteFeature.isSupportPeripheralControl()) {
            state.f6170d = this.f6153i.getString(R.string.plugin_peripheral_control_introduction);
        } else {
            state.f6170d = this.f6153i.getString(R.string.plugin_operation_introduction);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "nubia_operation_devices_enable"), this.f6163s, ";");
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t && this.w > 0) {
            z0(false);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void x0() {
        if (ZteFeature.isSupportSort()) {
            if (!this.f6162r || !RotationMgr.j() || Settings.Global.getInt(this.f6153i.getContentResolver(), "nubia_operation_devices_state", 0) <= 0) {
                v0();
            } else {
                y0();
                w0();
            }
        }
    }
}
