package cn.nubia.gameassist.common;

import android.content.Context;
import android.net.Uri;
import android.provider.Settings;
import cn.nubia.gameassist.tips.TipsUtils;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SettingsListener implements ObserverManager.SettingCallback {

    /* renamed from: i, reason: collision with root package name */
    public static boolean f6179i = false;

    /* renamed from: j, reason: collision with root package name */
    public static boolean f6180j = false;

    /* renamed from: k, reason: collision with root package name */
    public static boolean f6181k = false;

    /* renamed from: l, reason: collision with root package name */
    public static boolean f6182l = false;

    /* renamed from: m, reason: collision with root package name */
    public static boolean f6183m = false;

    /* renamed from: n, reason: collision with root package name */
    public static boolean f6184n = false;

    /* renamed from: o, reason: collision with root package name */
    public static boolean f6185o = false;

    /* renamed from: p, reason: collision with root package name */
    public static boolean f6186p = false;

    /* renamed from: c, reason: collision with root package name */
    private Context f6187c;

    /* renamed from: h, reason: collision with root package name */
    private IHostPanel f6188h;

    public SettingsListener(Context context, IHostPanel iHostPanel) {
        this.f6187c = context;
        this.f6188h = iHostPanel;
        b();
        ObserverManager.c().b(context, Settings.Global.getUriFor("device_provisioned"), this);
        ObserverManager.c().b(context, Settings.System.getUriFor("keyguard_is_showing"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("cc_game_mis_operate"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("cc_status_guide"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("cc_game_mis_operate_type"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("controlcenter_ban"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("ExpandingVisionSwitch"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("nubia_game_dock_mode"), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor(TipsUtils.FIRST_LAUNCH_GUIDE_STR), this);
        ObserverManager.c().b(context, Settings.Global.getUriFor("cc_game_mis_operate_ban_toast"), this);
        f6185o = TipsUtils.isFirstLaunchTips(this.f6187c);
    }

    public static void a(PrintWriter printWriter) {
        printWriter.println("SettingsListener:");
        printWriter.println("  sKeyGuardShowing: " + f6179i);
        printWriter.println("  sGameAssistWindowViewBan: " + f6180j);
        printWriter.println("  sExpandingVisionSwitchOn: " + f6181k);
        printWriter.println("  sAntiOperate: " + f6182l);
        printWriter.println("  sDeviceProvisioned: " + f6183m);
        printWriter.println("  sDockMode: " + f6184n);
        printWriter.println("  sBanTouchToast: " + f6186p);
    }

    private void b() {
        boolean z = true;
        int i2 = Settings.Global.getInt(this.f6187c.getContentResolver(), "cc_game_mis_operate", 1);
        GaLog.e("SettingsListener", "updateAntiOperate() keyValue : " + i2);
        if (1 != i2) {
            f6182l = false;
            return;
        }
        int i3 = Settings.Global.getInt(this.f6187c.getContentResolver(), "cc_game_mis_operate_type", 1);
        if (1 != i3 && 3 != i3) {
            z = false;
        }
        f6182l = z;
        GaLog.e("SettingsListener", "updateAntiOperate() sAntiOperate=" + f6182l);
    }

    @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
    public void w(boolean z, Uri uri) {
        if (Settings.System.getUriFor("keyguard_is_showing").equals(uri)) {
            f6179i = Settings.System.getInt(this.f6187c.getContentResolver(), "keyguard_is_showing", 0) == 1;
            SystemMgr.y(this.f6187c).O(f6179i);
            GameDurationManager.n().z(f6179i);
            this.f6188h.g0("keyguard_is_showing");
            GaLog.e("SettingsListener", "onChange() keyguard : " + f6179i);
            return;
        }
        if (Settings.Global.getUriFor("controlcenter_ban").equals(uri)) {
            f6180j = Settings.Global.getInt(this.f6187c.getContentResolver(), "controlcenter_ban", 1) != 1;
            this.f6188h.g0("controlcenter_ban");
            GaLog.e("SettingsListener", "onChange() mGameAssistWindowViewBan : " + f6180j);
            return;
        }
        if (Settings.Global.getUriFor("ExpandingVisionSwitch").equals(uri)) {
            f6181k = Settings.Global.getInt(this.f6187c.getContentResolver(), "ExpandingVisionSwitch", 0) == 1;
            this.f6188h.g0("ExpandingVisionSwitch");
            GaLog.e("SettingsListener", "onChange() mExpandingVisionSwitchOn : " + f6181k);
            return;
        }
        if (Settings.Global.getUriFor("cc_game_mis_operate").equals(uri) || Settings.Global.getUriFor("cc_game_mis_operate_type").equals(uri)) {
            b();
            GaLog.e("SettingsListener", "onChange() -> updateAntiOperate() mAntiOperate : " + f6182l);
            return;
        }
        if (Settings.Global.getUriFor("cc_status_guide").equals(uri)) {
            return;
        }
        if (Settings.Global.getUriFor("device_provisioned").equals(uri)) {
            f6183m = Settings.Global.getInt(this.f6187c.getContentResolver(), "device_provisioned", 0) != 0;
            GaLog.e("SettingsListener", "onChange() mDeviceProvisioned:" + f6183m);
            return;
        }
        if (Settings.Global.getUriFor("nubia_game_dock_mode").equals(uri)) {
            f6184n = Settings.Global.getInt(this.f6187c.getContentResolver(), "nubia_game_dock_mode", 0) != 0;
            GaLog.e("SettingsListener", "onChange() -> dockMode : " + f6184n);
            return;
        }
        if (Settings.Global.getUriFor(TipsUtils.FIRST_LAUNCH_GUIDE_STR).equals(uri)) {
            f6185o = TipsUtils.isFirstLaunchTips(this.f6187c);
            GaLog.e("SettingsListener", "onChange() -> sGSGuide : " + f6185o);
            return;
        }
        if (!Settings.Global.getUriFor("cc_game_mis_operate_ban_toast").equals(uri)) {
            GaLog.e("SettingsListener", "onChange() uri : " + uri);
            return;
        }
        f6186p = Settings.Global.getInt(this.f6187c.getContentResolver(), "cc_game_mis_operate_ban_toast", 0) != 0;
        GaLog.e("SettingsListener", "onChange() -> sBanTouchToast : " + f6186p);
    }
}
