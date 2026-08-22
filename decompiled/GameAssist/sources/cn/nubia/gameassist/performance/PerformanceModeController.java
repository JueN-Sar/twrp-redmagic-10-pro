package cn.nubia.gameassist.performance;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.VisibleForTesting;
import cn.nubia.componentcenter.api.performance.IPerformanceModeController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.utils.CommonUtil;
import cn.nubia.plugin.superresolution.SuperResolutionViewController;
import com.zte.gameassist.common.AbsModuleProxy;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.ModuleProxyContext;
import com.zte.gameassist.common.ObserverManager;
import com.zte.gameassist.common.R;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.mifavor.widget.AlertDialog;
import com.zte.shared.wrapper.ContextWrapper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class PerformanceModeController extends AbsModuleProxy<IPerformanceModeController.PerformanceModeCallback> implements GameMonitor.Callback, IPerformanceModeController {
    public static final boolean v;
    private static final Pair w;
    private static volatile PerformanceModeController x;

    /* renamed from: j, reason: collision with root package name */
    private final Runnable f7077j;

    /* renamed from: k, reason: collision with root package name */
    private final Runnable f7078k;

    /* renamed from: l, reason: collision with root package name */
    private String f7079l;

    /* renamed from: m, reason: collision with root package name */
    private long f7080m;

    /* renamed from: n, reason: collision with root package name */
    private AlertDialog f7081n;

    /* renamed from: o, reason: collision with root package name */
    private Map f7082o;

    /* renamed from: p, reason: collision with root package name */
    public final List f7083p;

    /* renamed from: q, reason: collision with root package name */
    private final Handler f7084q;

    /* renamed from: r, reason: collision with root package name */
    private final Context f7085r;

    /* renamed from: s, reason: collision with root package name */
    private int f7086s;
    private boolean t;
    private final PCGamePerformanceMode u;

    public interface PerformanceModeCallback {
        default void n(String str, int i2, boolean z) {
        }

        default void r(boolean z) {
        }

        default void t(String str, int i2) {
        }
    }

    static {
        v = Build.VERSION.SDK_INT >= 35;
        w = new Pair(2, 2);
    }

    private PerformanceModeController(Context context) {
        super(new ModuleProxyContext(context));
        this.f7077j = new Runnable() { // from class: cn.nubia.gameassist.performance.k
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceModeController.this.J0();
            }
        };
        this.f7078k = new Runnable() { // from class: cn.nubia.gameassist.performance.v
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceModeController.this.H0();
            }
        };
        this.f7082o = new HashMap();
        this.f7083p = new ArrayList();
        this.f7084q = new Handler(Looper.getMainLooper());
        this.f7085r = context;
        ObserverManager.c().b(context, Settings.Global.getUriFor("NubiaperformanceMode"), new ObserverManager.SettingCallback() { // from class: cn.nubia.gameassist.performance.C
            @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
            public final void w(boolean z, Uri uri) {
                PerformanceModeController.this.K0(z, uri);
            }
        });
        ObserverManager.c().b(context, Settings.Global.getUriFor("db_game_chicken_value"), new ObserverManager.SettingCallback() { // from class: cn.nubia.gameassist.performance.C
            @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
            public final void w(boolean z, Uri uri) {
                PerformanceModeController.this.K0(z, uri);
            }
        });
        ObserverManager.c().b(context, Settings.Global.getUriFor("low_power"), new ObserverManager.SettingCallback() { // from class: cn.nubia.gameassist.performance.D
            @Override // com.zte.gameassist.common.ObserverManager.SettingCallback
            public final void w(boolean z, Uri uri) {
                PerformanceModeController.this.I0(z, uri);
            }
        });
        SystemMgr.y(context).h(this);
        this.f7086s = getPerformanceMode(SystemMgr.t());
        H0();
        this.u = new PCGamePerformanceMode(context);
    }

    private void A0(String str, Function function) {
        int i2 = this.f7086s;
        String string = Settings.Global.getString(this.f7085r.getContentResolver(), "NubiaperformanceMode");
        if (string == null) {
            string = "";
        }
        if (!TextUtils.isEmpty(string)) {
            if (string.contains(str + "+")) {
                if (string.indexOf(str + "+") != string.lastIndexOf(str + "+")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("saveGameStrengthenNewValueToDB: packageName:");
                    sb.append(str);
                    sb.append(" index:");
                    sb.append(string.indexOf(str + "+"));
                    sb.append(" and ");
                    sb.append(string.lastIndexOf(str + "+"));
                    GaLog.k("PerformanceModeController", sb.toString());
                    string = string.replaceFirst(str + "\\+([0-9]{3}),", "");
                }
                String[] split = string.split(",");
                int length = split.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    String str2 = split[i3];
                    if (!TextUtils.isEmpty(str2)) {
                        if (str2.contains(str + "+")) {
                            int indexOf = str2.indexOf("+");
                            int W = W(str2, indexOf, 1, 2);
                            int V = V(str2, indexOf, 2);
                            int V2 = V(str2, indexOf, 3);
                            if (function != null) {
                                i2 = ((Integer) function.apply(Integer.valueOf(W))).intValue() % 10;
                            }
                            string = string.replace(str2, str + "+" + i2 + V + V2);
                        }
                    }
                    i3++;
                }
                Settings.Global.putString(this.f7085r.getContentResolver(), "NubiaperformanceMode", string);
                if (!Y() && i2 < 5) {
                    G0(str, false);
                } else if (!Y() && i2 >= 5) {
                    G0(str, true);
                }
                GaLog.a("PerformanceModeController", "saveGameStrengthenNewValueToDB: packageName = " + str + ", strengthenValue = " + string);
            }
        }
        if (function != null) {
            i2 = ((Integer) function.apply(2)).intValue() % 10;
        }
        string = string + str + "+" + i2 + "00,";
        Settings.Global.putString(this.f7085r.getContentResolver(), "NubiaperformanceMode", string);
        if (!Y()) {
        }
        if (!Y()) {
            G0(str, true);
        }
        GaLog.a("PerformanceModeController", "saveGameStrengthenNewValueToDB: packageName = " + str + ", strengthenValue = " + string);
    }

    private void D0(String str) {
        String[] split = str.split("@", 2);
        int parseInt = split.length > 1 ? Integer.parseInt(split[1]) : 0;
        Map map = this.f7082o;
        Pair pair = w;
        int intValue = ((Integer) ((Pair) map.getOrDefault(str, pair)).first).intValue();
        if (!GameCheck.i(split[0], parseInt) || 1 == intValue) {
            return;
        }
        O(str, (Pair) this.f7082o.getOrDefault(str, pair));
        savePerformanceMode(str, 1);
    }

    private void F0(final Runnable runnable) {
        X();
        this.f7085r.setTheme(R.style.GameAssist_Theme_ZTE_Light);
        AlertDialog a2 = new AlertDialog.Builder(this.f7085r, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(cn.nubia.gameassist.R.string.biablo_mode_dialog_title).c(true).e(this.f7085r.getString(T())).i(R.string.single_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.performance.p
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                runnable.run();
            }
        }).f(R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.performance.q
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        }).a();
        this.f7081n = a2;
        a2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: cn.nubia.gameassist.performance.r
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                PerformanceModeController.this.p0(dialogInterface);
            }
        });
        this.f7081n.setCanceledOnTouchOutside(true);
        this.f7081n.getWindow().setType(2008);
        this.f7081n.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.f7081n.getWindow().getDecorView().setSystemUiVisibility(6);
        this.f7081n.show();
        GameAssistWindowManager.O(this.f7085r).g0("showBiabloModeDialog");
        E0(this.f7081n.getWindow().findViewById(com.zte.extres.R.id.alertTitle), 4);
    }

    private synchronized void G0(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (z) {
                try {
                } catch (Exception e2) {
                    GaLog.b("PerformanceModeController", "sendBiabloModeEvent Exception !," + e2.getMessage());
                }
                if (this.f7080m == 0) {
                    this.f7080m = SystemClock.elapsedRealtime() / 1000;
                }
            }
            if (!z && this.f7080m != 0) {
                long elapsedRealtime = (SystemClock.elapsedRealtime() / 1000) - this.f7080m;
                this.f7080m = 0L;
                Bundle bundle = new Bundle();
                bundle.putString("app_name", str);
                bundle.putLong("duration", elapsedRealtime);
                NubiaTrackManager.p().x("cn.nubia.gamelauncher", "destroyer_mode_used", bundle);
                GaLog.a("PerformanceModeController", "sendBiabloModeEvent packageName=" + str + ", duration=" + elapsedRealtime);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H0() {
        boolean z = Settings.Global.getInt(this.f7085r.getContentResolver(), "low_power", 0) == 1;
        if (!z) {
            y0();
        } else if (SystemMgr.H()) {
            D0(SystemMgr.t());
        }
        if (z != this.t) {
            this.t = z;
            this.f7083p.forEach(new Consumer() { // from class: cn.nubia.gameassist.performance.w
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PerformanceModeController.this.q0((PerformanceModeController.PerformanceModeCallback) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I0(boolean z, Uri uri) {
        this.f7084q.removeCallbacks(this.f7078k);
        this.f7084q.postDelayed(this.f7078k, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0() {
        ArrayMap arrayMap = new ArrayMap();
        U(arrayMap);
        R(arrayMap);
        arrayMap.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.performance.F
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PerformanceModeController.this.v0((String) obj, (Pair) obj2);
            }
        });
        final String t = SystemMgr.t();
        int performanceMode = getPerformanceMode(t);
        if (this.f7086s != performanceMode) {
            this.f7086s = performanceMode;
            GaLog.e("PerformanceModeController", "updatePerformanceMode mPerformanceMode=" + this.f7086s + " map:" + this.f7082o);
            this.f7084q.post(new Runnable() { // from class: cn.nubia.gameassist.performance.G
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceModeController.this.s0(t);
                }
            });
        }
        if (Y() && a0() && ZteFeature.isSM8850Project()) {
            GaLog.e("PerformanceModeController", "reset BiabloMode pkg:" + t + " reason:isOpenSuperResolution");
            C0(t, false, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void K0(boolean z, Uri uri) {
        this.f7084q.removeCallbacks(this.f7077j);
        this.f7084q.postDelayed(this.f7077j, 50L);
    }

    private void O(String str, Pair pair) {
        String str2 = Settings.Global.getString(this.f7085r.getContentResolver(), "GameAssistPerformanceModeBackup") + "";
        if (str2.contains(str + "+")) {
            return;
        }
        List list = (List) Arrays.stream(str2.split(",")).filter(new Predicate() { // from class: cn.nubia.gameassist.performance.o
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean b0;
                b0 = PerformanceModeController.b0((String) obj);
                return b0;
            }
        }).collect(Collectors.toList());
        list.add(str + "+" + pair.first + ":" + pair.second);
        String str3 = (String) list.stream().collect(Collectors.joining(","));
        Settings.Global.putString(this.f7085r.getContentResolver(), "GameAssistPerformanceModeBackup", str3);
        StringBuilder sb = new StringBuilder();
        sb.append("addLowPowerModeData dataString=");
        sb.append(str3);
        GaLog.e("PerformanceModeController", sb.toString());
    }

    private void R(Map map) {
        if (v) {
            String string = Settings.Global.getString(this.f7085r.getContentResolver(), "db_game_chicken_value");
            GaLog.e("PerformanceModeController", "getBiabloFromSettings " + string);
            if (string != null) {
                for (String str : string.split(",")) {
                    String trim = str.trim();
                    if (!trim.isEmpty()) {
                        map.put(trim, new Pair(5, (Integer) ((Pair) map.getOrDefault(trim, w)).second));
                    }
                }
            }
        }
    }

    public static PerformanceModeController S() {
        if (x == null) {
            synchronized (PerformanceModeController.class) {
                try {
                    if (x == null) {
                        x = new PerformanceModeController(ContextWrapper.getContext());
                    }
                } finally {
                }
            }
        }
        return x;
    }

    private int T() {
        boolean isSupportGameRandomRecord = ZteFeature.isSupportGameRandomRecord();
        int[] iArr = {cn.nubia.gameassist.R.string.biablo_mode_dialog_content_internal, cn.nubia.gameassist.R.string.biablo_mode_dialog_content_internal2, cn.nubia.gameassist.R.string.biablo_mode_dialog_content, cn.nubia.gameassist.R.string.biablo_mode_dialog_content2, cn.nubia.gameassist.R.string.biablo_mode_dialog_content_zte, cn.nubia.gameassist.R.string.biablo_mode_dialog_content_zte2};
        return CommonUtil.b() ? isSupportGameRandomRecord ? iArr[1] : iArr[0] : ZteFeature.isRedMagicProduct() ? isSupportGameRandomRecord ? iArr[3] : iArr[2] : isSupportGameRandomRecord ? iArr[5] : iArr[4];
    }

    private void U(Map map) {
        int indexOf;
        String string = Settings.Global.getString(this.f7085r.getContentResolver(), "NubiaperformanceMode");
        GaLog.e("PerformanceModeController", "getPerformanceFromSettings " + string);
        if (string != null) {
            for (String str : string.split(",")) {
                String trim = str.trim();
                if (!trim.isEmpty() && (indexOf = trim.indexOf("+")) > 0) {
                    String substring = trim.substring(0, indexOf);
                    int W = W(trim, indexOf, 1, 2);
                    if (W >= 4) {
                        if (v) {
                            if (ZteFeature.isSupportCustom()) {
                                W = 4;
                            }
                            W &= 3;
                        } else {
                            if (W > 4) {
                                W = 5;
                            }
                            W &= 3;
                        }
                    }
                    int i2 = W > 0 ? W : 2;
                    map.put(substring, new Pair(Integer.valueOf(i2), Integer.valueOf(i2)));
                }
            }
        }
    }

    private int V(String str, int i2, int i3) {
        return W(str, i2, i3, 0);
    }

    private int W(String str, int i2, int i3, int i4) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(i2 + i3)));
        } catch (Exception unused) {
            return i4;
        }
    }

    private void X() {
        AlertDialog alertDialog = this.f7081n;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.f7081n.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean b0(String str) {
        return str.contains("+");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c0(PerformanceModeCallback performanceModeCallback) {
        if (this.f7083p.contains(performanceModeCallback)) {
            return;
        }
        this.f7083p.add(performanceModeCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d0(PerformanceModeCallback performanceModeCallback) {
        if (this.f7083p.contains(performanceModeCallback)) {
            this.f7083p.remove(performanceModeCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean e0(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void f0(List list, String str) {
        if (list.contains(str)) {
            return;
        }
        list.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean g0(String str) {
        return !TextUtils.isEmpty(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer h0(int i2, Integer num) {
        return Integer.valueOf(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer i0(int i2, Integer num) {
        return Integer.valueOf(i2 & 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void j0(String str) {
        C0(str, true, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer k0(Integer num) {
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer l0(boolean z, Integer num) {
        return z ? Integer.valueOf(num.intValue() | 4) : Integer.valueOf(num.intValue() & 3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void p0(DialogInterface dialogInterface) {
        this.f7081n = null;
        f(new Consumer() { // from class: cn.nubia.gameassist.performance.A
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((IPerformanceModeController.PerformanceModeCallback) obj).onDialogDismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void q0(PerformanceModeCallback performanceModeCallback) {
        performanceModeCallback.r(this.t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r0(IPerformanceModeController.PerformanceModeCallback performanceModeCallback) {
        performanceModeCallback.onPerformanceModeCallback(this.f7086s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s0(final String str) {
        this.f7083p.forEach(new Consumer() { // from class: cn.nubia.gameassist.performance.s
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PerformanceModeController.this.w0(str, (PerformanceModeController.PerformanceModeCallback) obj);
            }
        });
        f(new Consumer() { // from class: cn.nubia.gameassist.performance.t
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PerformanceModeController.this.r0((IPerformanceModeController.PerformanceModeCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t0(String str, Pair pair, PerformanceModeCallback performanceModeCallback) {
        performanceModeCallback.t(str, ((Integer) pair.first).intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u0(final String str, final Pair pair) {
        this.f7083p.forEach(new Consumer() { // from class: cn.nubia.gameassist.performance.B
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PerformanceModeController.t0(str, pair, (PerformanceModeController.PerformanceModeCallback) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v0(final String str, final Pair pair) {
        if (this.f7082o.getOrDefault(str, w) != pair) {
            this.f7082o.put(str, pair);
            this.f7084q.post(new Runnable() { // from class: cn.nubia.gameassist.performance.x
                @Override // java.lang.Runnable
                public final void run() {
                    PerformanceModeController.this.u0(str, pair);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w0(String str, PerformanceModeCallback performanceModeCallback) {
        performanceModeCallback.n(str, this.f7086s, Y());
    }

    private void y0() {
        String str;
        String str2;
        ArrayMap arrayMap;
        Iterator it;
        String str3;
        String[] strArr;
        String str4 = "GameAssistPerformanceModeBackup";
        String string = Settings.Global.getString(this.f7085r.getContentResolver(), "GameAssistPerformanceModeBackup");
        if (string == null || string.length() <= 0) {
            return;
        }
        GaLog.e("PerformanceModeController", "restoreLowPowerModeData dataString=" + string);
        String[] split = string.split(",");
        if (split == null && split.length == 0) {
            return;
        }
        ArrayMap arrayMap2 = new ArrayMap();
        ArrayList arrayList = new ArrayList();
        for (String str5 : split) {
            int indexOf = str5.indexOf("+");
            if (indexOf > 0) {
                String substring = str5.substring(0, indexOf);
                String[] split2 = str5.substring(indexOf).split(":");
                int parseInt = Integer.parseInt(split2[0]);
                int parseInt2 = Integer.parseInt(split2[1]);
                boolean z = v;
                if (z && parseInt == 5) {
                    arrayList.add(substring);
                } else if (!z && parseInt == 5) {
                    parseInt2 &= 3;
                }
                arrayMap2.put(substring, Integer.valueOf(parseInt2));
            }
        }
        if (arrayMap2.size() > 0) {
            String str6 = Settings.Global.getString(this.f7085r.getContentResolver(), "NubiaperformanceMode") + "";
            StringBuilder sb = new StringBuilder(str6);
            sb.append(" ===> ");
            Iterator it2 = arrayMap2.keySet().iterator();
            while (it2.hasNext()) {
                String str7 = (String) it2.next();
                int intValue = ((Integer) arrayMap2.get(str7)).intValue();
                if (!TextUtils.isEmpty(str6)) {
                    if (str6.contains(str7 + "+")) {
                        String[] split3 = str6.split(",");
                        arrayMap = arrayMap2;
                        int length = split3.length;
                        it = it2;
                        int i2 = 0;
                        while (true) {
                            if (i2 >= length) {
                                str2 = str4;
                                break;
                            }
                            int i3 = length;
                            String str8 = split3[i2];
                            if (TextUtils.isEmpty(str8)) {
                                str3 = str4;
                                strArr = split3;
                            } else {
                                strArr = split3;
                                if (str8.contains(str7 + "+")) {
                                    int indexOf2 = str8.indexOf("+");
                                    str2 = str4;
                                    str6 = str6.replace(str8, str7 + "+" + intValue + V(str8, indexOf2, 2) + V(str8, indexOf2, 3));
                                    break;
                                }
                                str3 = str4;
                            }
                            i2++;
                            length = i3;
                            split3 = strArr;
                            str4 = str3;
                        }
                        arrayMap2 = arrayMap;
                        it2 = it;
                        str4 = str2;
                    }
                }
                str2 = str4;
                arrayMap = arrayMap2;
                it = it2;
                str6 = str6 + str7 + "+" + intValue + "00,";
                arrayMap2 = arrayMap;
                it2 = it;
                str4 = str2;
            }
            str = str4;
            sb.append(str6);
            Settings.Global.putString(this.f7085r.getContentResolver(), "NubiaperformanceMode", str6);
            GaLog.e("PerformanceModeController", "changing PERFORMANCE_MODE " + ((Object) sb));
        } else {
            str = "GameAssistPerformanceModeBackup";
        }
        if (arrayList.size() > 0) {
            String str9 = Settings.Global.getString(this.f7085r.getContentResolver(), "db_game_chicken_value") + "";
            StringBuilder sb2 = new StringBuilder(str9);
            sb2.append(" ===> ");
            final List list = (List) Arrays.stream(str9.split(",")).filter(new Predicate() { // from class: cn.nubia.gameassist.performance.y
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean e0;
                    e0 = PerformanceModeController.e0((String) obj);
                    return e0;
                }
            }).collect(Collectors.toList());
            arrayList.forEach(new Consumer() { // from class: cn.nubia.gameassist.performance.z
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PerformanceModeController.f0(list, (String) obj);
                }
            });
            StringBuilder sb3 = new StringBuilder();
            sb3.append((String) list.stream().collect(Collectors.joining(",")));
            sb3.append(list.size() <= 0 ? "" : ",");
            String sb4 = sb3.toString();
            sb2.append(sb4);
            Settings.Global.putString(this.f7085r.getContentResolver(), "db_game_chicken_value", sb4);
            GaLog.e("PerformanceModeController", "changing BIABLO_MODE " + ((Object) sb2));
        }
        Settings.Global.putString(this.f7085r.getContentResolver(), str, "");
    }

    private void z0(String str, boolean z) {
        String string = Settings.Global.getString(this.f7085r.getContentResolver(), "db_game_chicken_value");
        if (string == null) {
            string = "";
        }
        List list = (List) Arrays.stream(string.split(",")).filter(new Predicate() { // from class: cn.nubia.gameassist.performance.u
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean g0;
                g0 = PerformanceModeController.g0((String) obj);
                return g0;
            }
        }).collect(Collectors.toList());
        if (list.contains(str) && !z) {
            list.remove(str);
        } else if (list.contains(str) || !z) {
            return;
        } else {
            list.add(str);
        }
        StringBuilder sb = new StringBuilder();
        sb.append((String) list.stream().collect(Collectors.joining(",")));
        sb.append(list.size() > 0 ? "," : "");
        String sb2 = sb.toString();
        Settings.Global.putString(this.f7085r.getContentResolver(), "db_game_chicken_value", sb2);
        if (Y() && !z) {
            G0(str, false);
        } else if (!Y() && z) {
            G0(str, true);
        }
        GaLog.a("PerformanceModeController", "saveBiabloNewValueToDB: packageName = " + str + " " + z + ", strengthenValue = " + string + " => " + sb2);
    }

    public void B0(String str, boolean z) {
        C0(str, z, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x005e, code lost:
    
        if (r5.contains(r3 + "+") == false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void C0(final java.lang.String r3, final boolean r4, boolean r5) {
        /*
            r2 = this;
            if (r4 == 0) goto L15
            r0 = 1
            boolean r0 = r2.Q(r0)
            if (r0 != 0) goto L15
            android.content.Context r2 = r2.f7085r
            cn.nubia.gameassist.panel.GameAssistWindowManager r2 = cn.nubia.gameassist.panel.GameAssistWindowManager.O(r2)
            java.lang.String r3 = "setBiabloModeEnable"
            r2.g0(r3)
            return
        L15:
            if (r4 == 0) goto L22
            if (r5 == 0) goto L22
            cn.nubia.gameassist.performance.l r4 = new cn.nubia.gameassist.performance.l
            r4.<init>()
            r2.F0(r4)
            return
        L22:
            boolean r5 = cn.nubia.gameassist.performance.PerformanceModeController.v
            if (r5 == 0) goto L6c
            java.util.Map r5 = r2.f7082o
            android.util.Pair r0 = cn.nubia.gameassist.performance.PerformanceModeController.w
            java.lang.Object r5 = r5.getOrDefault(r3, r0)
            android.util.Pair r5 = (android.util.Pair) r5
            java.lang.Object r5 = r5.second
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            r0 = 2
            if (r5 != r0) goto L68
            android.content.Context r5 = r2.f7085r
            android.content.ContentResolver r5 = r5.getContentResolver()
            java.lang.String r0 = "NubiaperformanceMode"
            java.lang.String r5 = android.provider.Settings.Global.getString(r5, r0)
            if (r5 == 0) goto L60
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r3)
            java.lang.String r1 = "+"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            boolean r5 = r5.contains(r0)
            if (r5 != 0) goto L68
        L60:
            cn.nubia.gameassist.performance.m r5 = new cn.nubia.gameassist.performance.m
            r5.<init>()
            r2.A0(r3, r5)
        L68:
            r2.z0(r3, r4)
            goto L74
        L6c:
            cn.nubia.gameassist.performance.n r5 = new cn.nubia.gameassist.performance.n
            r5.<init>()
            r2.A0(r3, r5)
        L74:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.performance.PerformanceModeController.C0(java.lang.String, boolean, boolean):void");
    }

    public void E0(View view, int i2) {
        if (view == null || !(view instanceof TextView)) {
            return;
        }
        TextView textView = (TextView) view;
        textView.setSingleLine(true);
        textView.setTextAlignment(i2);
        textView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        textView.setSelected(true);
    }

    public void P(final PerformanceModeCallback performanceModeCallback) {
        this.f7084q.post(new Runnable() { // from class: cn.nubia.gameassist.performance.E
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceModeController.this.c0(performanceModeCallback);
            }
        });
    }

    public boolean Q(boolean z) {
        if (!Z()) {
            return true;
        }
        if (z) {
            Context context = this.f7085r;
            Toast.makeText(context, context.getText(cn.nubia.gameassist.R.string.performancemode_is_lowpowermode_tip), 0).show();
        }
        return false;
    }

    public boolean Y() {
        return this.f7086s == 5;
    }

    public boolean Z() {
        return this.t;
    }

    public boolean a0() {
        return SuperResolutionViewController.q(this.f7085r).w(SystemMgr.t());
    }

    public void dump(PrintWriter printWriter, String str) {
        printWriter.println(str + "  mPerformanceViewMode=" + this.f7086s);
        printWriter.println(str + "  _PERFORMANCE_MODE_ALL_LIST=" + Settings.Global.getString(this.f7085r.getContentResolver(), "NubiaperformanceMode"));
        printWriter.println(str + "  DB_BIABLO_MODE_ALL_LIST=" + Settings.Global.getString(this.f7085r.getContentResolver(), "db_game_chicken_value"));
    }

    @Override // cn.nubia.componentcenter.api.performance.IPerformanceModeController
    public int getPerformanceMode() {
        return this.f7086s;
    }

    @VisibleForTesting
    public boolean isEconomizeMode() {
        return this.f7086s == 1;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        this.f7079l = SystemMgr.t();
        J0();
        G0(this.f7079l, Y());
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        X();
        G0(this.f7079l, false);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        X();
        G0(this.f7079l, false);
        this.f7079l = SystemMgr.t();
        J0();
        G0(this.f7079l, Y());
    }

    @VisibleForTesting
    public void savePerformanceMode(String str, final int i2) {
        if (5 == i2) {
            B0(str, true);
        } else if (!v) {
            A0(str, new Function() { // from class: cn.nubia.gameassist.performance.I
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Integer i0;
                    i0 = PerformanceModeController.i0(i2, (Integer) obj);
                    return i0;
                }
            });
        } else {
            B0(str, false);
            A0(str, new Function() { // from class: cn.nubia.gameassist.performance.H
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Integer h0;
                    h0 = PerformanceModeController.h0(i2, (Integer) obj);
                    return h0;
                }
            });
        }
    }

    @Override // cn.nubia.componentcenter.api.performance.IPerformanceModeController
    public boolean setPerformanceMode(String str, int i2) {
        if (!Q(i2 != 1)) {
            return false;
        }
        savePerformanceMode(str, i2);
        return true;
    }

    public void x0(final PerformanceModeCallback performanceModeCallback) {
        this.f7084q.post(new Runnable() { // from class: cn.nubia.gameassist.performance.J
            @Override // java.lang.Runnable
            public final void run() {
                PerformanceModeController.this.d0(performanceModeCallback);
            }
        });
    }

    @Override // cn.nubia.componentcenter.api.performance.IPerformanceModeController
    public int getPerformanceMode(String str) {
        if (!Z()) {
            return ((Integer) ((Pair) this.f7082o.getOrDefault(str, w)).first).intValue();
        }
        D0(str);
        return 1;
    }
}
