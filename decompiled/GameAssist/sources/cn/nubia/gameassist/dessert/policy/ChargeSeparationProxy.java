package cn.nubia.gameassist.dessert.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;
import cn.nubia.componentcenter.api.dessert.IChargeSeparationProxy;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.AbsTileProxy;
import cn.nubia.gameassist.dessert.tiles.ChargeSeparationTiles;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.Constants;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;

/* loaded from: classes.dex */
public class ChargeSeparationProxy extends AbsTileProxy implements IChargeSeparationProxy, GameMonitor.Callback, ObserverManager.SettingCallback {

    /* renamed from: s, reason: collision with root package name */
    public static int f6283s;
    public static int t;

    /* renamed from: l, reason: collision with root package name */
    private Context f6284l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f6285m;

    /* renamed from: n, reason: collision with root package name */
    private Handler f6286n;

    /* renamed from: o, reason: collision with root package name */
    private int f6287o;

    /* renamed from: p, reason: collision with root package name */
    private BroadcastReceiver f6288p;

    /* renamed from: q, reason: collision with root package name */
    public boolean f6289q;

    /* renamed from: r, reason: collision with root package name */
    public final Runnable f6290r;

    private class BatteryChangeBroadcast extends BroadcastReceiver {

        /* renamed from: a, reason: collision with root package name */
        boolean f6291a;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.BATTERY_CHANGED".equals(action)) {
                    ChargeSeparationProxy.f6283s = intent.getIntExtra("level", 0);
                    if (this.f6291a) {
                        ChargeSeparationProxy.t = intent.getIntExtra("plugged", 0);
                    } else {
                        ChargeSeparationProxy.t = 0;
                    }
                } else {
                    if ("android.intent.action.ACTION_POWER_CONNECTED".equals(action)) {
                        this.f6291a = true;
                    } else if ("android.intent.action.ACTION_POWER_DISCONNECTED".equals(action)) {
                        this.f6291a = false;
                        ChargeSeparationProxy.t = 0;
                        boolean z = Settings.Global.getInt(ChargeSeparationProxy.this.f6284l.getContentResolver(), "charge_separation_switch", 0) == 1;
                        ChargeSeparationProxy chargeSeparationProxy = ChargeSeparationProxy.this;
                        if (chargeSeparationProxy.f6289q && z) {
                            Settings.Global.putInt(chargeSeparationProxy.f6284l.getContentResolver(), "charge_separation_switch", 0);
                        }
                    }
                }
                GaLog.a(ChargeSeparationProxy.this.f16453c, "onReceive: isPluggedIn=" + this.f6291a + " mPluggedIn=" + ChargeSeparationProxy.t + " mLevel=" + ChargeSeparationProxy.f6283s);
            }
        }

        private BatteryChangeBroadcast() {
        }
    }

    public ChargeSeparationProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f6286n = null;
        this.f6287o = 0;
        this.f6288p = new BatteryChangeBroadcast();
        this.f6290r = new Runnable() { // from class: cn.nubia.gameassist.dessert.policy.b
            @Override // java.lang.Runnable
            public final void run() {
                ChargeSeparationProxy.this.v();
            }
        };
        this.f6284l = moduleProxyContext.a();
        this.f6286n = new Handler(ThreadManager.c().b());
        SystemMgr.y(this.f6284l).h(this);
        x();
    }

    private void o(boolean z, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        Settings.Global.putInt(this.f6284l.getContentResolver(), "charge_separation_switch", z ? 1 : 0);
        this.f6287o = z ? 3 : 2;
        if (z) {
            Context context = this.f6284l;
            Toast.makeText(context, ChargeSeparationTiles.z0(context, R.string.ic_qs_charge_separation_open_warning_text), 0).show();
            GameAgentUtil.e(this.f6284l, iGameAssistClientCallback, inMsg, R.string.ic_qs_charge_separation);
        } else {
            Context context2 = this.f6284l;
            Toast.makeText(context2, ChargeSeparationTiles.z0(context2, R.string.ic_qs_charge_separation_close_warning_text), 0).show();
            GameAgentUtil.d(this.f6284l, iGameAssistClientCallback, inMsg, R.string.ic_qs_charge_separation);
        }
    }

    private void p(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        this.f6111k = false;
        this.f6287o = 5;
        Context context = this.f6284l;
        Toast.makeText(context, ChargeSeparationTiles.z0(context, R.string.ic_qs_charge_separation_battery_low20_warning_text), 0).show();
        GameAgentUtil.l(this.f6284l, iGameAssistClientCallback, inMsg, false);
    }

    private void q(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        this.f6111k = false;
        this.f6287o = 4;
        Context context = this.f6284l;
        Toast.makeText(context, ChargeSeparationTiles.z0(context, R.string.ic_qs_charge_separation_disconnect_charge_warning_text), 0).show();
        GameAgentUtil.l(this.f6284l, iGameAssistClientCallback, inMsg, false);
    }

    private void s(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        this.f6111k = false;
        GameAgentUtil.l(this.f6284l, iGameAssistClientCallback, inMsg, false);
        z();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v() {
        if (this.f6285m) {
            return;
        }
        this.f6285m = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
        Intent registerReceiver = this.f6284l.registerReceiver(this.f6288p, intentFilter, null, this.f6286n, 2);
        int intExtra = registerReceiver != null ? registerReceiver.getIntExtra("status", -1) : -1;
        boolean isCharging = ((BatteryManager) this.f6284l.getSystemService("batterymanager")).isCharging();
        if (intExtra != 0) {
            t = intExtra;
        } else {
            t = isCharging ? 1 : 0;
        }
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter2.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.f6284l.registerReceiver(this.f6288p, intentFilter2, null, this.f6286n, 2);
        GaLog.a(this.f16453c, "register mPluggedIn=" + intExtra + " isChanging=" + isCharging);
    }

    @Override // cn.nubia.componentcenter.api.dessert.IChargeSeparationProxy
    public void aiAgent(boolean z, Object... objArr) {
        IGameAssistClientCallback iGameAssistClientCallback = (IGameAssistClientCallback) objArr[0];
        InMsg inMsg = (InMsg) objArr[1];
        if (z && t == 0) {
            q(iGameAssistClientCallback, inMsg);
            return;
        }
        if (z && f6283s < 20) {
            p(iGameAssistClientCallback, inMsg);
        } else if (z && SharedPreferencesUtil.k(this.f6284l).b() == 0) {
            s(iGameAssistClientCallback, inMsg);
        } else {
            o(z, iGameAssistClientCallback, inMsg);
        }
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy, com.zte.gameassist.common.ITileProxy
    public boolean d() {
        super.d();
        this.f6287o = 0;
        boolean z = Settings.Global.getInt(this.f6284l.getContentResolver(), "charge_separation_switch", 0) == 1;
        GaLog.a(this.f16453c, "handleClick, openStatus=" + z);
        if (z) {
            Settings.Global.putInt(this.f6284l.getContentResolver(), "charge_separation_switch", 0);
            this.f6287o = 2;
            Context context = this.f6284l;
            Toast.makeText(context, ChargeSeparationTiles.z0(context, R.string.ic_qs_charge_separation_close_warning_text), 0).show();
            return true;
        }
        if (t == 0) {
            this.f6111k = false;
            this.f6287o = 4;
            Context context2 = this.f6284l;
            Toast.makeText(context2, ChargeSeparationTiles.z0(context2, R.string.ic_qs_charge_separation_disconnect_charge_warning_text), 0).show();
            j();
            return true;
        }
        if (f6283s < 20) {
            this.f6111k = false;
            this.f6287o = 5;
            Context context3 = this.f6284l;
            Toast.makeText(context3, ChargeSeparationTiles.z0(context3, R.string.ic_qs_charge_separation_battery_low20_warning_text), 0).show();
            j();
            return true;
        }
        if (SharedPreferencesUtil.k(this.f6284l).b() == 0) {
            this.f6111k = false;
            z();
            return true;
        }
        Settings.Global.putInt(this.f6284l.getContentResolver(), "charge_separation_switch", 1);
        this.f6289q = true;
        this.f6287o = 3;
        Context context4 = this.f6284l;
        Toast.makeText(context4, ChargeSeparationTiles.z0(context4, R.string.ic_qs_charge_separation_open_warning_text), 0).show();
        return false;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void g() {
        ObserverManager.c().b(this.f6284l, Settings.Global.getUriFor("charge_separation_switch"), this);
    }

    @Override // cn.nubia.componentcenter.api.dessert.IChargeSeparationProxy
    public int getChargeSeparationResult() {
        int i2 = this.f6287o;
        y();
        return i2;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    public void i() {
        ObserverManager.c().d(this.f6284l, Settings.Global.getUriFor("charge_separation_switch"), this);
    }

    @Override // cn.nubia.gameassist.common.AbsTileProxy
    protected void k() {
        this.f6111k = Settings.Global.getInt(this.f6284l.getContentResolver(), "charge_separation_switch", 0) == 1;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameSceneStateChanged */
    public void m0(boolean z) {
        GaLog.a(this.f16453c, "onGameSceneStateChanged: isGameScene=" + z);
        if (z) {
            return;
        }
        u();
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy, com.zte.gameassist.common.IModuleProxy
    public void setListening(boolean z, IModuleProxy.ICallback iCallback) {
        super.setListening(z, iCallback);
        if (z) {
            return;
        }
        y();
    }

    public void u() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package", "GameAssist");
            Bundle call = this.f6284l.getContentResolver().call(Constants.f16464d, "hide_alert_dialog", (String) null, bundle);
            GaLog.a(this.f16453c, "call charge separation interface hide alert dialog result=" + call.getString(com.zte.distbus.basetransfer.Constants.EXTRA_RESULT));
        } catch (Exception e2) {
            GaLog.a(this.f16453c, " hideAlertDialog error:" + e2);
        }
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        int i2 = Settings.Global.getInt(this.f6284l.getContentResolver(), "charge_separation_switch", 0);
        if (this.f6287o != i2) {
            this.f6287o = i2 == 1 ? 3 : 2;
            GaLog.a(this.f16453c, "onChange mChargeSeparationResult = " + this.f6287o);
        }
        if (i2 == 0) {
            this.f6289q = false;
        }
        j();
    }

    public void x() {
        this.f6286n.removeCallbacks(this.f6290r);
        this.f6286n.post(this.f6290r);
    }

    public void y() {
        GaLog.b(this.f16453c, "resetChargeSeparationResult mChargeSeparationResult = " + this.f6287o);
        int i2 = this.f6287o;
        if (i2 == 3 || i2 == 1) {
            this.f6287o = 1;
        } else {
            this.f6287o = 0;
        }
    }

    public void z() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("package", "GameAssist");
            Bundle call = this.f6284l.getContentResolver().call(Constants.f16464d, "show_alert_dialog", (String) null, bundle);
            GaLog.a(this.f16453c, "call charge separation interface show alert dialog result=" + call.getString(com.zte.distbus.basetransfer.Constants.EXTRA_RESULT));
        } catch (Exception e2) {
            GaLog.a(this.f16453c, " showAlertDialog error:" + e2);
        }
    }
}
