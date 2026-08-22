package com.zte.gameassist.common;

import android.app.ActivityManager;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import androidx.annotation.VisibleForTesting;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SettingsUtils;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public class SystemMgr implements GameMonitor {
    public static volatile int A = 0;
    public static volatile int B = 0;
    public static volatile String C = "";
    public static volatile String D = "";
    public static volatile boolean E = false;
    public static volatile boolean F = false;
    public static volatile boolean G = false;
    public static List H = Arrays.asList("GameAssistActivityWindow", "ScreenExtraction.Window", "ScreenExtraction.Settings", "VirtualGame", "VirtualHandleKeySetting", "ScreenKeyMap", "WheelDiscSetting", "ScreenWheelDiscGuide", "FourDimensional", "touchgamekey", "FourDimensional", "GameEqWindow", "GameMagicVoice", "gamehelpermoudle", "InstructionState", "GameSettingPanel", "handshankscreen", "AimSettingFloat", "cn.nubia.gameassist", "GameReminder", "dualhandshank", "HelperLinerHome", "HelperLinerEdit", "smallView", "controlcenter", "LkmMapList", "SaveAsDialog", "LkmConfig", "RateSelectSetting", "GameAiTipSettings", "ChargeSepSetting", "gameaiasst", "GamepadCaseEditView", "GamepadCaseListView", "GamepadDialogView", "not_show_view_in_operation_device_window", "CardAssistSettingWindow", "AISpeakerSettingView", "magicevlescard", "pluginTrigger", "SuperResolutionSettingsPanel", "SuperResolutionSettingWindow", "PluginGameRatioSettings", "PluginGameRatioGuide", "PluginGameRatioAlertRestart", "LowSugar", "VoiceController", "AITranslationGuideWindow", "AITranslationSettingView");
    public static volatile boolean I = false;
    public static volatile int J = -1;
    static List K = Arrays.asList("cn.nubia.gameassist/cn.nubia.gameassist.permission.PermissionApplyActivity", "com.android.settings/cn.nubia.settings.bluetooth.BluetoothPairingDialog", "com.android.systemui/.media.MediaProjectionPermissionActivity", "cn.nubia.gamehighlights/.Activity.MainActivity", "cn.nubia.gamehighlights/.Activity.PreviewActivity", "cn.nubia.video/.player.PlayerActivity", "cn.nubia.video/.mediaeditorview.simpleeditor.SimpleVideoEditorActivity", "cn.nubia.share/.ChooserActivity", "com.excelliance.kxqp.app.assist.AssistantActivity", "com.android.settings/cn.nubia.settings.deviceinfo.UsbModeChooserActivity", "cn.nubia.touping/.WiredTouPingMainActivity", "com.android.mtp/com.android.mtp.ReceiverActivity", "com.zte.convert3d", "cn.nubia.gamenotes", "com.huawei.hms.core.activity.JumpActivity", "cn.nubia.gamehighlights", "com.zte.onemorething/com.zte.aispeaker.aigc.VoiceControllerPermissionActivity", "com.zte.onemorething/com.zte.aispeaker.aigc.AiSpeakerPermissionActivity", "com.zte.onemorething/com.zte.aispeaker.aigc.RequestPermissionActivity", "com.zte.onemorething/com.zte.aitranslation.permission.RequestPermissionActivity", "com.zte.onemorething/com.zte.aitranslation.permission.VoiceControllerPermissionActivity", "com.tencent.keepalive.screen.SinglePxActivity", "com.android.settings/com.zte.settings.connecteddevice.UsbModeChooserActivity", "cn.nubia.gameassist/com.zte.plugin.reminder.permission.RequestPermissionActivityBase", "cn.nubia.gameassist/com.zte.plugin.reminder.permission.RequestPermissionActivity");

    /* renamed from: o, reason: collision with root package name */
    private static volatile SystemMgr f16554o = null;

    /* renamed from: p, reason: collision with root package name */
    public static volatile AbsGameAssistToken.GameAssistControllerWrapper f16555p = null;

    /* renamed from: q, reason: collision with root package name */
    public static volatile AbsGameAssistToken.ActivityEntity f16556q = null;

    /* renamed from: r, reason: collision with root package name */
    public static volatile AbsGameAssistToken.ActivityEntity f16557r = null;

    /* renamed from: s, reason: collision with root package name */
    public static volatile AbsGameAssistToken.FocuesWindow f16558s = null;
    public static volatile String t = "";
    public static volatile String u = "";
    public static volatile String v = "";
    public static volatile String w = "";
    public static volatile int x;
    public static volatile int y;
    public static volatile int z;

    /* renamed from: c, reason: collision with root package name */
    private final GameAssistToken f16560c;

    /* renamed from: d, reason: collision with root package name */
    private final CommanderList f16561d;

    /* renamed from: m, reason: collision with root package name */
    private Context f16570m;

    /* renamed from: n, reason: collision with root package name */
    private Pioneer f16571n;

    /* renamed from: b, reason: collision with root package name */
    private Handler f16559b = new Handler(ThreadManager.c().e());

    /* renamed from: e, reason: collision with root package name */
    public volatile boolean f16562e = false;

    /* renamed from: f, reason: collision with root package name */
    public volatile boolean f16563f = false;

    /* renamed from: g, reason: collision with root package name */
    public volatile long f16564g = 0;

    /* renamed from: h, reason: collision with root package name */
    private String f16565h = null;

    /* renamed from: i, reason: collision with root package name */
    public final List f16566i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    public volatile String f16567j = "";

    /* renamed from: k, reason: collision with root package name */
    public volatile boolean f16568k = false;

    /* renamed from: l, reason: collision with root package name */
    public volatile boolean f16569l = false;

    public interface Pioneer extends Runnable {
    }

    private SystemMgr(Context context) {
        this.f16570m = context;
        CommanderList commanderList = new CommanderList(this.f16559b);
        this.f16561d = commanderList;
        commanderList.d(new DefaultCommander(context));
        this.f16560c = new GameAssistToken(this, commanderList, this.f16570m, this.f16559b);
        D();
    }

    public static String A(String str) {
        return (str != null && str.contains("@")) ? str.substring(0, str.indexOf("@")) : str;
    }

    private void C() {
        if (this.f16565h != null) {
            return;
        }
        try {
            this.f16565h = this.f16570m.getPackageManager().getPackageInfo(this.f16570m.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            this.f16565h = "";
            e2.printStackTrace();
        }
    }

    public static boolean F() {
        return z == 999;
    }

    public static boolean G() {
        return y == 2 || y == 3;
    }

    public static boolean H() {
        return G;
    }

    public static boolean I() {
        return I;
    }

    private boolean J() {
        return f16558s != null && f16558s.toString().contains("com.zte.mifavor.launcher");
    }

    public static boolean K(String str, int i2) {
        return x == 5 && t.equals(str) && z == i2;
    }

    public static boolean L() {
        GameCheck.GameAppInfo d2 = GameCheck.d(w, A);
        if (d2 != null) {
            return d2.f();
        }
        return false;
    }

    public static boolean M(String str) {
        return str != null && str.contains("@");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void N(PrintWriter printWriter, AbsGameAssistToken.SystemWindow systemWindow) {
        printWriter.println("    " + systemWindow);
    }

    private boolean P(boolean z2, String str, int i2) {
        return z2 && "com.tencent.mm".equals(str) && i2 == 0 && (v.contains(".ui.AppBrand") || v.contains("WXShortcutEntryActivity"));
    }

    private void W(boolean z2, boolean z3) {
        if (z2 == this.f16569l && z2 && z3) {
            GaLog.g("SystemMgr", "callGameUpdate:");
            b();
        } else if (z2 != this.f16569l || (z2 && z3)) {
            this.f16569l = z2;
            GaLog.g("SystemMgr", "callGameStateChanged:");
            c();
        }
    }

    private void a0() {
        if (ZteFeature.isSupportMultiSubScreen()) {
            return;
        }
        Settings.Global.putInt(this.f16570m.getContentResolver(), "game_scene_disable_ble_scan", G ? 1 : 0);
    }

    public static String s() {
        return v;
    }

    public static String t() {
        GameCheck.GameAppInfo d2 = GameCheck.d(w, A);
        return d2 != null ? d2.c() : w;
    }

    public static String u() {
        return C;
    }

    public static String v() {
        return t;
    }

    public static int w() {
        return z;
    }

    public static SystemMgr y(Context context) {
        if (f16554o == null) {
            synchronized (SystemMgr.class) {
                try {
                    if (f16554o == null) {
                        f16554o = new SystemMgr(context);
                    }
                } finally {
                }
            }
        }
        return f16554o;
    }

    public static String z() {
        return w;
    }

    public synchronized IBinder B() {
        return this.f16560c;
    }

    public void D() {
        Settings.Global.putInt(this.f16570m.getContentResolver(), "nubia_game_scene", 0);
        Settings.Global.putInt(this.f16570m.getContentResolver(), "game_scene_disable_ble_scan", 0);
        this.f16570m.getContentResolver().registerContentObserver(Settings.Global.getUriFor("debug_game_assist"), false, new ContentObserver(this, this.f16559b) { // from class: com.zte.gameassist.common.SystemMgr.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z2, Uri uri) {
                super.onChange(z2);
                GaLog.i();
            }
        });
        C();
        GameCheck.g(this.f16570m);
    }

    public boolean E() {
        return this.f16563f;
    }

    public boolean Q(String str) {
        if ((str.contains("com.android.systemui") && str.contains("PipBackgroundBoundActivity")) || str.contains("com.android.permissioncontroller")) {
            return true;
        }
        Iterator it = K.iterator();
        while (it.hasNext()) {
            if (str.contains((String) it.next())) {
                GaLog.e("SystemMgr", "onResume ignore activity : " + str);
                return true;
            }
        }
        return false;
    }

    public void R(String str) {
        if (Math.abs(this.f16564g - System.currentTimeMillis()) > 300000) {
            F = ActivityManager.isUserAMonkey();
            this.f16564g = System.currentTimeMillis();
        }
        GaLog.g("SystemMgr", "onActivityChange: " + u + " isfull=" + E + " isGame=" + G + " taskLabel=" + C);
    }

    public void S(AbsGameAssistToken.GameAssistControllerWrapper gameAssistControllerWrapper) {
        f16555p = gameAssistControllerWrapper;
        Pioneer pioneer = this.f16571n;
        if (pioneer != null) {
            this.f16559b.post(pioneer);
        }
    }

    public void T(AbsGameAssistToken.FocuesWindow focuesWindow) {
        f16558s = focuesWindow;
        f(focuesWindow);
    }

    public void U(String str) {
        d(f16556q.mActivity);
    }

    public void V(String str, int i2) {
        C = GameCheck.f(str, i2);
        p(str, i2);
        EventListenerMgr.g(2, str);
    }

    public void X(float f2) {
    }

    /* renamed from: Y, reason: merged with bridge method [inline-methods] */
    public void O(final boolean z2) {
        if (!this.f16559b.getLooper().isCurrentThread()) {
            this.f16559b.post(new Runnable() { // from class: com.zte.gameassist.common.o
                @Override // java.lang.Runnable
                public final void run() {
                    SystemMgr.this.O(z2);
                }
            });
            return;
        }
        if (I != z2) {
            I = z2;
            if (I) {
                p(w, A);
            } else {
                V(w, A);
            }
        }
    }

    public void Z(Pioneer pioneer) {
        this.f16571n = pioneer;
    }

    @Override // com.zte.gameassist.common.GameMonitor
    public boolean g() {
        return this.f16569l;
    }

    public void o(IGameAssistCommander iGameAssistCommander) {
        this.f16561d.d(iGameAssistCommander);
    }

    @VisibleForTesting
    public void onActivityResumed(AbsGameAssistToken.ActivityEntity activityEntity) {
        this.f16560c.onActivityResumed(activityEntity);
    }

    @VisibleForTesting
    public void onFullActivityFirstCreate(AbsGameAssistToken.ActivityEntity activityEntity) {
        this.f16560c.init(null);
        this.f16560c.onFullActivityFirstCreate(activityEntity);
    }

    public void p(String str, int i2) {
        String str2;
        if (I) {
            GaLog.e("SystemMgr", "checkGameScene: mKeyguardShow=true");
        }
        boolean z2 = z == 0 || z == 999;
        boolean z3 = z2 && !I && GameCheck.i(str, i2);
        if (P(z3, str, i2)) {
            GaLog.k("SystemMgr", "checkGameScene: checkGameScene enter twice, check game next time");
            return;
        }
        W(z3, this.f16562e);
        if (z3 == G) {
            return;
        }
        G = z3;
        StringBuilder sb = new StringBuilder();
        sb.append("checkGameScene: isGameScene= ");
        sb.append(G);
        if (G) {
            str2 = " version=" + this.f16565h;
        } else {
            str2 = "";
        }
        sb.append(str2);
        sb.append(" owner=");
        sb.append(z2);
        GaLog.e("SystemMgr", sb.toString());
        Settings.Global.putInt(this.f16570m.getContentResolver(), "nubia_game_scene", G ? 1 : 0);
        if (G) {
            SettingsUtils.e(this.f16570m, "nubia_game_scene_package_name", str);
        }
        a(G);
        a0();
    }

    public void q() {
        if (G) {
            GaLog.k("SystemMgr", "checkLauncher " + f16558s + " " + G);
        }
        if (G && J() && f16557r != null) {
            GaLog.k("SystemMgr", "checkLauncher restore launcher:" + f16557r);
            onActivityResumed(f16557r);
        }
    }

    public void r(final PrintWriter printWriter) {
        printWriter.println("SystemMgr:");
        printWriter.println("  sIsGameScene=" + G);
        printWriter.println("  sResumedPackage=" + t);
        printWriter.println("  sResumedActivity=" + u);
        printWriter.println("  sResumedWindowMode=" + x);
        printWriter.println("  sResumedActivityType=" + y);
        printWriter.println("  sResumedUserId=" + z);
        printWriter.println("  mStackId=" + B);
        printWriter.println("  sResumedTaskHashcode=" + A);
        printWriter.println("  sResumedTaskLabel=" + C);
        printWriter.println("  sResumedFullscreenPackage=" + w);
        printWriter.println("  getCurFullscreenPackage()=" + t());
        printWriter.println("  sResumedFullscreenActivity=" + v);
        printWriter.println("  sLauncherFirstFullscreenPackage=" + D);
        printWriter.println("  mCustomWindowList=" + this.f16567j);
        printWriter.println("  m3DDisplayId=" + J);
        printWriter.println("  mIs3DActivityBehind=" + this.f16563f);
        printWriter.println("  mHasBlackWindow=" + this.f16568k);
        printWriter.println("  mKeyguardShow=" + I);
        printWriter.println("  mFocuesWindow=" + f16558s);
        printWriter.println("  mSystemWindows=[");
        this.f16566i.forEach(new Consumer() { // from class: com.zte.gameassist.common.n
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                SystemMgr.N(printWriter, (AbsGameAssistToken.SystemWindow) obj);
            }
        });
        printWriter.println("  ]");
        if (f16555p != null) {
            f16555p.invake("dump", null, null);
        }
        GameCheck.c(printWriter);
    }

    public Optional x() {
        return Optional.ofNullable(f16555p);
    }
}
