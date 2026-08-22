package cn.nubia.gameassist.plugin.tiles;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;

/* loaded from: classes.dex */
public class SensorOperationTile extends QSTile implements ObserverManager.SettingCallback {
    private boolean v;

    public SensorOperationTile(QSTile.Host host) {
        super(host);
    }

    private void A0(boolean z) {
        Intent intent = new Intent("cn.nubia.sensoroperation.startSensorOperationService");
        intent.setPackage("cn.nubia.gamepad");
        intent.putExtra("action_type", 0);
        intent.putExtra("action_operation_type", z ? 1 : 2);
        intent.putExtra("packagename", this.f6163s);
        this.f6153i.startService(intent);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean S() {
        super.S();
        GaLog.e("SensorOperationTile", "handleClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("SensorOperationTile", "isInFreeformMode");
                return true;
            }
            if (u0()) {
                return true;
            }
            this.u = true;
            if (this.t) {
                A0(false);
            } else {
                this.f6152h.b();
                A0(true);
            }
        }
        NubiaTrackManager.p().u();
        return false;
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected boolean a0() {
        super.a0();
        GaLog.e("SensorOperationTile", "handleSettingsClick() : mCurPackage :  " + this.f6163s + " " + this.f6162r);
        if (!this.f6162r || !RotationMgr.j()) {
            ToastUtil.a(this.f6153i.getString(R.string.touch_key_toast));
        } else {
            if (Utils.P(this.f6153i)) {
                ToastUtil.a(this.f6153i.getString(R.string.game_close_pip));
                GaLog.a("SensorOperationTile", "isInFreeformMode");
                return true;
            }
            if (this.t) {
                this.f6152h.b();
                Intent intent = new Intent("cn.nubia.sensoroperation.startSensorOperationService");
                intent.setPackage("cn.nubia.gamepad");
                intent.putExtra("action_type", 0);
                intent.putExtra("action_operation_type", 0);
                intent.putExtra("packagename", this.f6163s);
                this.f6153i.startService(intent);
            } else {
                ToastUtil.a(this.f6153i.getString(R.string.ic_qs_function_enable));
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
            ObserverManager.c().b(this.f6153i, Settings.Global.getUriFor("zte_sensor_operation_devices_enable_infos"), this);
        } else {
            ObserverManager.c().d(this.f6153i, Settings.Global.getUriFor("zte_sensor_operation_devices_enable_infos"), this);
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void c0(QSTile.State state, Object obj) {
        super.c0(state, obj);
        StringBuilder sb = new StringBuilder();
        sb.append("handleUpdateState() : ");
        sb.append(this.f6163s);
        sb.append(this.f6162r ? " isGameScene" : " ");
        sb.append(" mTileEnable= ");
        sb.append(this.t);
        GaLog.e("SensorOperationTile", sb.toString());
        if (this.f6162r && this.t) {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_sensor_operation_on);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_on);
            state.f6175i = true;
        } else {
            state.f6168b = QSTile.ResourceIcon.b(R.drawable.plugin_sensor_operation_off);
            state.f6171e = QSTile.ResourceIcon.b(R.drawable.plugin_settings_off);
            state.f6175i = false;
        }
        state.f6169c = this.f6153i.getString(z0());
        state.f6170d = this.f6153i.getString(R.string.sensor_operation_introduction);
    }

    @Override // cn.nubia.gameassist.common.QSTile
    public boolean f0() {
        return Utils.x(Settings.Global.getString(this.f6153i.getContentResolver(), "zte_sensor_operation_devices_enable_infos"), this.f6163s, ";") && !SystemMgr.F();
    }

    @Override // cn.nubia.gameassist.common.QSTile
    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        super.k0(str, str2, iGameAssistClientCallback, inMsg);
        if ("game_turn_on_sensor_operation".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (u0()) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
                return;
            }
            if (!this.t) {
                A0(true);
            }
            GameAgentUtil.e(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            return;
        }
        if ("game_turn_off_sensor_operation".equals(str) && i0(iGameAssistClientCallback, inMsg)) {
            if (u0()) {
                GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
            } else {
                A0(false);
                GameAgentUtil.d(this.f6153i, iGameAssistClientCallback, inMsg, z0());
            }
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile, com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        super.y(i2);
        if (this.f6162r && GameRatioMgr.q().u() && this.t) {
            A0(false);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        o0();
    }

    protected int z0() {
        return R.string.plugin_label_sensor_operation;
    }
}
