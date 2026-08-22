package cn.nubia.plugin.superresolution;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class SuperResolutionViewController implements GameMonitor.Callback {

    /* renamed from: n, reason: collision with root package name */
    private static volatile SuperResolutionViewController f8696n;

    /* renamed from: o, reason: collision with root package name */
    private static final List f8697o = Arrays.asList("com.tencent.tmgp.pubgmhd", "com.tencent.tmgp.projectg");

    /* renamed from: c, reason: collision with root package name */
    private final Context f8698c;

    /* renamed from: i, reason: collision with root package name */
    private String f8700i;

    /* renamed from: j, reason: collision with root package name */
    private int f8701j;

    /* renamed from: k, reason: collision with root package name */
    private int f8702k;

    /* renamed from: l, reason: collision with root package name */
    private ContentObserver f8703l;

    /* renamed from: m, reason: collision with root package name */
    private boolean f8704m = false;

    /* renamed from: h, reason: collision with root package name */
    private final Handler f8699h = new Handler(ThreadManager.c().e());

    public SuperResolutionViewController(Context context) {
        this.f8698c = context;
        SystemMgr.y(context).h(this);
        PerformanceModeController.S().P(new PerformanceModeController.PerformanceModeCallback() { // from class: cn.nubia.plugin.superresolution.SuperResolutionViewController.1
            @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
            public void n(String str, int i2, boolean z) {
                GaLog.a("SuperResolutionViewController", "onPerformanceModeCallback");
                SuperResolutionViewController.this.i(str, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean A(String str) {
        return !str.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(String str) {
        SuperResolutionSettingWindowManager.l(this.f8698c).v(str);
    }

    private void D(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        for (String str2 : str.split(",")) {
            String[] split = str2.split("\\+", 2);
            if (split.length == 2) {
                String trim = split[0].trim();
                G(trim);
                GaLog.a("SuperResolutionViewController", "parseDataOnGameStart packageName = " + trim);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E(String str) {
        if (str != null && !str.isEmpty()) {
            for (String str2 : str.split(",")) {
                String[] split = str2.split("\\+");
                if (split.length == 2) {
                    String str3 = split[0];
                    String str4 = split[1];
                    String[] split2 = str4.split("");
                    GaLog.a("SuperResolutionViewController", "parseSettingsValue packageName = " + str3 + ", switchValue = " + str4);
                    for (int i2 = 0; i2 < split2.length; i2++) {
                        t(str3, i2, split2[i2]);
                    }
                    G(str3);
                }
            }
        }
        this.f8704m = false;
    }

    private void F(int i2) {
        String v = SystemMgr.v();
        SuperResolutionTypeDataManager c2 = SuperResolutionTypeDataManager.c();
        if (i2 == 0) {
            c2.h(v, "frameRate", "frameRate_origin");
            c2.h(v, "imageQuality", "origin");
            G(v);
            L(v, false);
            return;
        }
        this.f8701j = i2 / 10;
        GaLog.a("SuperResolutionViewController", "parseSupportFunction interpolationSupport = " + this.f8701j);
        if (this.f8701j == 0) {
            c2.h(v, "imageQuality", "origin");
        }
        this.f8702k = i2 % 10;
        GaLog.a("SuperResolutionViewController", "parseSupportFunction superResolutionSupport = " + this.f8702k);
        if (this.f8702k == 0) {
            c2.h(v, "frameRate", "frameRate_origin");
        }
        this.f8700i = s().contains(v) ? "1" : "0";
        H(v, ",");
    }

    private void H(String str, String str2) {
        if (this.f8704m) {
            return;
        }
        String p2 = p(SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "imageQuality"));
        String n2 = n(SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "frameRate"));
        int parseInt = Integer.parseInt(p2);
        int i2 = this.f8701j;
        if (parseInt > i2) {
            p2 = String.valueOf(i2);
            SuperResolutionTypeDataManager.c().h(SystemMgr.v(), "imageQuality", "high");
        }
        int parseInt2 = Integer.parseInt(n2);
        int i3 = this.f8702k;
        if (parseInt2 > i3) {
            n2 = String.valueOf(i3);
            SuperResolutionTypeDataManager.c().h(SystemMgr.v(), "frameRate", "frameRate_super");
        }
        Map r2 = r(str2);
        GaLog.a("SuperResolutionViewController", "putGameGFRCMode put pkg = " + str + ", switch = " + p2 + n2 + this.f8700i);
        StringBuilder sb = new StringBuilder();
        sb.append(p2);
        sb.append(n2);
        sb.append(this.f8700i);
        r2.put(str, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        for (Map.Entry entry : r2.entrySet()) {
            sb2.append((String) entry.getKey());
            sb2.append("+");
            sb2.append((String) entry.getValue());
            sb2.append(",");
        }
        if (sb2.length() > 0) {
            sb2.setLength(sb2.length() - 1);
        }
        GaLog.a("SuperResolutionViewController", "putGameGFRCMode total data = " + sb2.toString());
        Settings.Global.putString(this.f8698c.getContentResolver(), "game_gfrc_mode", sb2.toString());
    }

    private void I() {
        if (this.f8703l == null) {
            this.f8703l = new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: cn.nubia.plugin.superresolution.SuperResolutionViewController.2
                @Override // android.database.ContentObserver
                public void onChange(boolean z, Uri uri) {
                    super.onChange(z);
                    try {
                        if (SuperResolutionViewController.u(PerformanceModeController.S().getPerformanceMode(SystemMgr.v()))) {
                            return;
                        }
                        SuperResolutionViewController.this.f8704m = true;
                        SuperResolutionViewController superResolutionViewController = SuperResolutionViewController.this;
                        superResolutionViewController.E(Settings.Global.getString(superResolutionViewController.f8698c.getContentResolver(), "game_gfrc_mode"));
                    } catch (Exception e2) {
                        GaLog.b("SuperResolutionViewController", "Exception e = " + e2.getMessage());
                    }
                }
            };
        }
        this.f8698c.getContentResolver().registerContentObserver(Settings.Global.getUriFor("game_gfrc_mode"), false, this.f8703l);
    }

    private void K() {
        if (this.f8703l != null) {
            this.f8698c.getContentResolver().unregisterContentObserver(this.f8703l);
            this.f8703l = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(String str, int i2) {
        if (u(i2)) {
            L(str, false);
            G(str);
        }
    }

    private String m(String str) {
        return "0".equals(str) ? "frameRate_origin" : "1".equals(str) ? "frameRate_super" : "frameRate_ultra";
    }

    private String n(String str) {
        return "frameRate_origin".equals(str) ? "0" : "frameRate_super".equals(str) ? "1" : "2";
    }

    private String o(String str) {
        return "0".equals(str) ? "origin" : "1".equals(str) ? "high" : "super";
    }

    private String p(String str) {
        return "origin".equals(str) ? "0" : "high".equals(str) ? "1" : "2";
    }

    public static synchronized SuperResolutionViewController q(Context context) {
        SuperResolutionViewController superResolutionViewController;
        synchronized (SuperResolutionViewController.class) {
            try {
                if (f8696n == null) {
                    f8696n = new SuperResolutionViewController(context);
                }
                superResolutionViewController = f8696n;
            } catch (Throwable th) {
                throw th;
            }
        }
        return superResolutionViewController;
    }

    private Map r(String str) {
        HashMap hashMap = new HashMap();
        try {
            String string = Settings.Global.getString(this.f8698c.getContentResolver(), "game_gfrc_mode");
            if (string != null && !string.isEmpty()) {
                for (String str2 : string.split(str)) {
                    String[] split = str2.split("\\+");
                    if (split.length == 2) {
                        hashMap.put(split[0], split[1]);
                    }
                }
            }
        } catch (Exception e2) {
            GaLog.b("SuperResolutionViewController", "Exception e = " + e2.getMessage());
        }
        return hashMap;
    }

    private List s() {
        String A = SharedPreferencesUtil.k(this.f8698c).A();
        return !TextUtils.isEmpty(A) ? (List) Arrays.stream(A.split(",")).filter(new Predicate() { // from class: cn.nubia.plugin.superresolution.g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean A2;
                A2 = SuperResolutionViewController.A((String) obj);
                return A2;
            }
        }).distinct().collect(Collectors.toList()) : new ArrayList();
    }

    private void t(String str, int i2, String str2) {
        SuperResolutionTypeDataManager c2 = SuperResolutionTypeDataManager.c();
        if (i2 == 0) {
            c2.h(str, "imageQuality", o(str2));
            return;
        }
        if (i2 == 1) {
            c2.h(str, "frameRate", m(str2));
            return;
        }
        boolean y = y(str, str2);
        if ("8".equals(str2)) {
            ToastUtil.a(this.f8698c.getString(R.string.plugin_super_resolution_high_temperature_toast));
            this.f8704m = false;
        }
        L(str, y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean u(int i2) {
        return i2 == 1 || i2 == 2;
    }

    private boolean y(String str, String str2) {
        return f8697o.contains(str) ? str2.equals("6") || str2.equals("1") : str2.equals("1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean z(String str) {
        return !str.isEmpty();
    }

    public void C(String str) {
        GaLog.a("SuperResolutionViewController", "isFirstOpenPkg = " + v(str));
        if (v(str)) {
            J(str);
        } else {
            L(str, true);
        }
    }

    public void G(String str) {
        List l2 = l();
        if (l2.contains(str) || !x()) {
            if (l2.contains(str) && x()) {
                l2.remove(str);
            } else if (!l2.contains(str) && !x()) {
                l2.add(str);
            }
            String join = TextUtils.join(",", l2);
            GaLog.a("SuperResolutionViewController", "putFirstOpenPkg: pkg = " + str + ", join = " + join);
            SharedPreferencesUtil.k(this.f8698c).H(join);
        }
    }

    public void J(final String str) {
        this.f8699h.post(new Runnable() { // from class: cn.nubia.plugin.superresolution.i
            @Override // java.lang.Runnable
            public final void run() {
                SuperResolutionViewController.this.B(str);
            }
        });
    }

    public void L(String str, boolean z) {
        List s2 = s();
        if (s2.contains(str) || !x()) {
            this.f8700i = String.valueOf(z ? '1' : '0');
            if ((s2.contains(str) && !z) || (!s2.contains(str) && z)) {
                if (z) {
                    s2.add(str);
                    GaLog.a("SuperResolutionViewController", "updateEnableSwitchPkg: add packageName = " + str);
                } else {
                    s2.remove(str);
                    GaLog.a("SuperResolutionViewController", "updateEnableSwitchPkg: delete packageName = " + str);
                }
                String join = TextUtils.join(",", s2);
                GaLog.a("SuperResolutionViewController", "updateEnableSwitchPkg: join = " + join);
                SharedPreferencesUtil.k(this.f8698c).G(join);
            }
            H(str, ",");
        }
    }

    public void j(String str) {
        SuperResolutionSettingWindowManager.l(this.f8698c).f(str);
    }

    public void k(String str) {
        L(str, false);
    }

    public List l() {
        String B = SharedPreferencesUtil.k(this.f8698c).B();
        GaLog.a("SuperResolutionViewController", "getFirstOpenPkg = " + B);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(B)) {
            arrayList.addAll(List.of((Object[]) B.split(",")).stream().filter(new Predicate() { // from class: cn.nubia.plugin.superresolution.h
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean z;
                    z = SuperResolutionViewController.z((String) obj);
                    return z;
                }
            }).toList());
        }
        return arrayList;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        SuperResolutionTypeDataManager.c().g();
        String v = SystemMgr.v();
        F(PluginUtils.f(this.f8698c).e(v));
        i(v, PerformanceModeController.S().getPerformanceMode(v));
        String string = Settings.Global.getString(this.f8698c.getContentResolver(), "game_gfrc_mode");
        GaLog.a("SuperResolutionViewController", "game_gfrc_mode = " + string);
        D(string);
        if (SharedPreferencesUtil.k(this.f8698c).C() == null) {
            Settings.Global.putString(this.f8698c.getContentResolver(), "game_gfrc_mode", null);
        }
        I();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        K();
    }

    public boolean v(String str) {
        return (str == null || l().contains(str)) ? false : true;
    }

    public boolean w(String str) {
        return Utils.x(SharedPreferencesUtil.k(this.f8698c).A(), str, ",");
    }

    public boolean x() {
        return "origin".equals(SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "imageQuality")) && "frameRate_origin".equals(SuperResolutionTypeDataManager.c().d(SystemMgr.v(), "frameRate"));
    }
}
