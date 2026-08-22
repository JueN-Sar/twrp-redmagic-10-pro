package cn.nubia.gameassist.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.TileInfo;
import cn.nubia.gameassist.fold.FoldBigMgr;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.plugin.config.PluginConfig;
import cn.nubia.plugin.timer.TimerMgr;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.common.Constants;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.GameAssistControllerWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class Utils {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f7696a;

    /* renamed from: b, reason: collision with root package name */
    public static boolean f7697b;

    /* renamed from: c, reason: collision with root package name */
    public static final Typeface f7698c;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f7699d;

    /* renamed from: e, reason: collision with root package name */
    private static final List f7700e;

    /* renamed from: f, reason: collision with root package name */
    private static final List f7701f;

    /* renamed from: g, reason: collision with root package name */
    private static GameAssistControllerWrapper.Callback f7702g;

    public static class AndroidVerison {

        /* renamed from: a, reason: collision with root package name */
        public static final int f7703a;

        static {
            int i2 = Build.VERSION.SDK_INT;
            if (i2 == 33) {
                try {
                    Class.forName("android.window.ScreenCapture");
                    i2++;
                } catch (Exception unused) {
                    Log.v("AndroidVerison", "is android s");
                }
            }
            f7703a = i2;
        }
    }

    static {
        Typeface typeface;
        boolean z = true;
        f7696a = SystemProperties.get("ro.build.type", "user").contains("debug") || SystemProperties.getBoolean("persist.sys.gameassist_debug", false);
        f7697b = !b0() && SystemProperties.getBoolean("persist.sys.gameassist_network_config", true);
        try {
            Class.forName("cn.nubia.gameassist.TestApplication");
        } catch (Exception unused) {
            z = false;
        }
        f7699d = z;
        try {
            typeface = Typeface.create("sans-serif-regular-stable-family", 0);
        } catch (Exception e2) {
            Typeface typeface2 = Typeface.DEFAULT;
            GaLog.c("GameAssistUtils", "Typeface createFromFile exception!", e2);
            typeface = typeface2;
        }
        f7698c = typeface;
        f7700e = Arrays.asList("com.tencent.tmgp.sgame", "com.levelinfinite.sgameGlobal", "com.levelinfinite.sgameGlobal.midaspay", "com.tencent.tmgp.sgamece");
        f7701f = Arrays.asList("com.tencent.KiHan");
        f7702g = new GameAssistControllerWrapper.Callback() { // from class: cn.nubia.gameassist.utils.Utils.1
            @Override // com.zte.shared.wrapper.GameAssistControllerWrapper.Callback
            protected void onCallback(Bundle bundle) {
            }
        };
    }

    public static boolean A(Context context) {
        return PerformanceModeController.S().Y();
    }

    public static Boolean B(Context context, int i2) {
        Boolean bool;
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        try {
            Method declaredMethod = audioManager.getClass().getDeclaredMethod("isDtsESupportedForUid", Integer.TYPE);
            declaredMethod.setAccessible(true);
            bool = (Boolean) declaredMethod.invoke(audioManager, Integer.valueOf(i2));
        } catch (Exception e2) {
            GaLog.c("GameAssistUtils", "isDtsESupportedForUid reflect exception!", e2);
            bool = null;
        }
        GaLog.b("GameAssistUtils", "isDtsESupportedForUid: value = " + bool);
        return bool;
    }

    public static boolean C(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "nubia_game_scene", 0) == 1;
    }

    private static boolean D() {
        String j2 = j();
        if (!(ZteFeature.isSupport4DVIBRATEPlugin() && Constants.f16467g && ("com.mobile.legends".equals(j2) || "com.mobile.legends.usa".equals(j2) || "com.dfjz.moba".equals(j2))) && ZteFeature.isSupport4DVIBRATEPlugin()) {
            return ZteFeature.isSuperiorQualityGame() && "com.tencent.tmgp.pubgmhd".equals(j());
        }
        return true;
    }

    private static boolean E() {
        return !ZteFeature.isSupportPleasedDisplay() || "com.tencent.mm".equals(j()) || SystemMgr.L();
    }

    private static boolean F() {
        if (!ZteFeature.isSupportAudioEqualizer()) {
            return true;
        }
        AudioManager audioManager = (AudioManager) f().getSystemService("audio");
        int g2 = g();
        Boolean B = B(f(), g2);
        GaLog.e("GameAssistUtils", "isHideSound: isSupport = " + B);
        if (B != null) {
            return !B.booleanValue();
        }
        GaLog.e("GameAssistUtils", "isHideSound: currentPkg = " + j() + " , isDtsGameEnhanceSupported = " + audioManager.getParameters("isDtsGameEnhanceSupported=" + g2));
        return !"isDtsGameEnhanceSupported=on".equals(r0);
    }

    private static boolean G() {
        return (ZteFeature.isSupportSoundProbe() && "com.tencent.tmgp.pubgmhd".equals(j())) ? false : true;
    }

    public static boolean H(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    public static boolean I(String str) {
        return !TextUtils.isEmpty(str) && f7701f.contains(str);
    }

    public static boolean J(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "nubia_systemui_wifidisplay_status", 0) == 1;
    }

    public static boolean K(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "navigation_mode", 0) == 0;
    }

    public static boolean L(String str) {
        return ZteFeature.isPluginNeedRemove() && (M(str) || I(str));
    }

    public static boolean M(String str) {
        return !TextUtils.isEmpty(str) && f7700e.contains(str);
    }

    public static boolean N(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "single_hand_on_off", 0) != 0;
    }

    public static boolean O(Context context) {
        return "1".equals(Settings.Global.getString(context.getContentResolver(), "app_mirror_status"));
    }

    public static boolean P(Context context) {
        return "1".equals(Settings.Secure.getString(context.getContentResolver(), "hasWindowReply"));
    }

    public static boolean Q(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "ss_multi_window_enabled", 0) != 0;
    }

    public static boolean R() {
        return 33 == Build.VERSION.SDK_INT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void S(Context context) {
        try {
            String string = Settings.Global.getString(context.getContentResolver(), "game_assist_version_name");
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
            if (packageInfo == null || TextUtils.equals(packageInfo.versionName, string)) {
                return;
            }
            Settings.Global.putString(context.getContentResolver(), "game_assist_version_name", packageInfo.versionName);
        } catch (Exception e2) {
            GaLog.e("GameAssistUtils", "saveVersionToSettings " + e2.getMessage());
        }
    }

    public static void T(Context context) {
        if (ZteFeature.isPluginNeedRemove()) {
            Iterator it = f7701f.iterator();
            while (it.hasNext()) {
                U(context, (String) it.next(), "nubia_gameratio_enable_pkgs", false);
            }
            TimerMgr.r().x(f7701f);
        }
    }

    public static boolean U(Context context, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String t = t(context, str2, z);
        String str3 = str + ",";
        if (t == null || !t.contains(str3)) {
            return false;
        }
        String replace = t.replace(str3, "");
        if (z) {
            Settings.Secure.putString(context.getContentResolver(), str2, replace);
            return true;
        }
        Settings.Global.putString(context.getContentResolver(), str2, replace);
        return true;
    }

    public static void V(List list) {
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            TileInfo tileInfo = (TileInfo) it.next();
            if (tileInfo != null && "game_custom".equals(tileInfo.f6227a)) {
                list.remove(tileInfo);
                break;
            }
        }
        list.add(null);
    }

    public static void W(String str, String str2) {
        GaLog.a("GameAssistUtils", "restartGame packageName=" + str + " reason=" + str2);
        Bundle bundle = new Bundle();
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("");
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, sb.toString());
        try {
            GameAssistControllerWrapper.invake("restart_package", bundle, f7702g);
            FoldBigMgr.c().e();
        } catch (Exception e2) {
            GaLog.b("GameAssistUtils", "Could not restartGame" + e2.toString());
        }
    }

    public static void X(Context context, String str, int i2) {
        Y(context, str, i2, false);
        Y(context, str, i2, true);
    }

    public static void Y(Context context, String str, int i2, boolean z) {
        String str2;
        if (z) {
            str2 = "game_strengthen_mode_value_assist";
        } else if (i2 == 5) {
            return;
        } else {
            str2 = "game_strengthen_mode_value_assist_no_invert";
        }
        String string = Settings.Global.getString(context.getContentResolver(), str2);
        if (!TextUtils.isEmpty(string) && string.contains(str)) {
            String[] split = string.split(",");
            int length = split.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                String str3 = split[i3];
                if (TextUtils.isEmpty(str3) || !str3.contains(str)) {
                    i3++;
                } else {
                    if (s(str3, str3.indexOf("+")) == i2) {
                        return;
                    }
                    string = string.replace(str3, str + "+" + i2);
                }
            }
        } else {
            string = string + str + "+" + i2 + ",";
        }
        Settings.Global.putString(context.getContentResolver(), str2, string);
    }

    public static void Z(final Context context, Handler handler) {
        handler.post(new Runnable() { // from class: cn.nubia.gameassist.utils.q
            @Override // java.lang.Runnable
            public final void run() {
                Utils.S(context);
            }
        });
    }

    public static void a0(int i2) {
        GaLog.a("GameAssistUtils", "startHome displayId=" + i2);
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_key_code", i2);
        try {
            GameAssistControllerWrapper.invake("start_home", bundle, f7702g);
        } catch (Exception e2) {
            GaLog.b("GameAssistUtils", "Could not startHome" + e2.toString());
        }
    }

    public static boolean b(Context context, String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String t = t(context, str2, z);
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(t)) {
            sb.append(str);
            sb.append(",");
        } else {
            String str3 = str + ",";
            if (t.contains(str3)) {
                return true;
            }
            sb.append(t);
            sb.append(str3);
        }
        if (z) {
            Settings.Secure.putString(context.getContentResolver(), str2, sb.toString());
        } else {
            Settings.Global.putString(context.getContentResolver(), str2, sb.toString());
        }
        return true;
    }

    public static boolean b0() {
        return CommonUtil.b();
    }

    public static boolean c(Context context) {
        Bundle call = context.getContentResolver().call(Constants.f16463c, "checkFloatingWindowPermission", (String) null, (Bundle) null);
        Intent intent = new Intent("cn.nubia.gamelauncher.action.START_HELPER");
        intent.setPackage("cn.nubia.gameassist");
        if (call == null || !call.containsKey("permission") || call.getBoolean("permission")) {
            return true;
        }
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        context.startActivity(intent);
        return false;
    }

    public static void c0(String str) {
        GaLog.a("GameAssistUtils", "update_app_bounds packageName=" + str);
        Bundle bundle = new Bundle();
        bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, str + "");
        try {
            GameAssistControllerWrapper.invake("update_app_bounds", bundle, f7702g);
        } catch (Exception e2) {
            GaLog.b("GameAssistUtils", "Could not update_app_bounds" + e2.toString());
        }
    }

    private static ArrayList d(ArrayList arrayList) {
        if (!ZteFeature.isSupportDataPanelInPlugin()) {
            arrayList.remove("combat_power");
        }
        if (!ZteFeature.isSupportRedmagicBroadcastInPlugin()) {
            arrayList.remove("redmagic_broadcast");
        }
        if (!ZteFeature.isSupportRedmagicElvesaidInPlugin()) {
            arrayList.remove("help");
        }
        if (!ZteFeature.isSupportChatAssistInPlugin()) {
            arrayList.remove("chat_assit");
        }
        if (!ZteFeature.isSupportBiabloPlugin()) {
            arrayList.remove("biablo_mode");
        }
        if (!ZteFeature.isSupportXGravityPlugin()) {
            arrayList.remove("operation_devices");
        }
        if (!ZteFeature.isSupportFixedLookPlugin()) {
            arrayList.remove("screen_extraction");
        }
        if (D()) {
            arrayList.remove("vibrate");
        }
        if (!ZteFeature.isSupportAITriggerPlugin()) {
            arrayList.remove("ai_trigger");
        }
        if (!ZteFeature.isSupportCardAssistPlugin()) {
            arrayList.remove("card_assist");
        }
        if (ZteFeature.isSupportGameDisplayFilterEffect()) {
            arrayList.remove("hunting_mode");
        } else {
            arrayList.remove("gameshader");
        }
        if (!ZteFeature.isSupportSuperResolution()) {
            arrayList.remove("super_resolution");
        }
        if (!PluginUtils.f(f()).h() || ZteFeature.isSupportSuperResolution()) {
            arrayList.remove("super_resolution_old");
        }
        if (G()) {
            arrayList.remove("ai_detect");
        }
        if (E()) {
            arrayList.remove("pleased_display");
        }
        if (!ZteFeature.isSupportHighSensitivityWheel()) {
            arrayList.remove("high_sensitivity_wheel");
        }
        if (F()) {
            arrayList.remove("sound_effect");
        }
        if (!ZteFeature.isSupportSensorOperation()) {
            arrayList.remove("sensor_operation");
        }
        if (!ZteFeature.isSupportAISpeaker()) {
            arrayList.remove("mora_ai_speaker");
        }
        if (!ZteFeature.isSupportCounter()) {
            arrayList.remove("counter");
        }
        if (!ZteFeature.isSupportGamePrediction()) {
            arrayList.remove("game_prediction");
        }
        if (!ZteFeature.isSupportVoiceController()) {
            arrayList.remove("voice_controller");
        }
        if (!ZteFeature.isSupportAITip()) {
            arrayList.remove("ai_tip");
        }
        return arrayList;
    }

    public static StringBuilder d0(View view, StringBuilder sb, String str) {
        if (sb == null) {
            sb = new StringBuilder();
        }
        if (view != null) {
            sb.append(str + view.toString() + "\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append("  ");
            String sb3 = sb2.toString();
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    d0(viewGroup.getChildAt(childCount), sb, sb3);
                }
            }
        } else {
            sb.append("null");
        }
        return sb;
    }

    public static Bitmap e(int i2, int i3, List list) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(-65536);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10.0f);
        Path path = new Path();
        path.moveTo(((Point) list.get(0)).x, ((Point) list.get(0)).y);
        for (int i4 = 1; i4 < list.size(); i4++) {
            path.lineTo(((Point) list.get(i4)).x, ((Point) list.get(i4)).y);
        }
        path.lineTo(i2, 0.0f);
        path.lineTo(0.0f, 0.0f);
        path.close();
        canvas.drawPath(path, paint);
        return createBitmap;
    }

    public static Context f() {
        GameAssistApplication.j();
        return BaseApplication.a();
    }

    public static int g() {
        try {
            ApplicationInfo applicationInfo = f().getPackageManager().getApplicationInfo(SystemMgr.z(), 128);
            if (applicationInfo != null) {
                return applicationInfo.uid;
            }
            return 0;
        } catch (Exception e2) {
            GaLog.c("GameAssistUtils", "getAppUidByPkgName exception!", e2);
            return 0;
        }
    }

    public static Typeface h(String str) {
        return Typeface.createFromAsset(f().getAssets(), "fonts/" + str);
    }

    public static String i(Context context, String str) {
        PackageInfo packageInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            packageInfo = packageManager.getPackageInfo(SystemMgr.A(str), 0);
        } catch (Exception e2) {
            GaLog.b("ControlCenter", "Exception" + e2);
            packageInfo = null;
        }
        if (packageInfo != null) {
            Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(packageInfo.packageName);
            List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
            if (queryIntentActivities.size() > 0) {
                return queryIntentActivities.iterator().next().activityInfo.name;
            }
        }
        return null;
    }

    public static String j() {
        return SystemMgr.t();
    }

    public static ArrayList k() {
        return d(TilesUtil.e(f().getResources().getString(R.string.plugin_list_tiles)));
    }

    public static String l(String str) {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            try {
                bufferedReader = new BufferedReader(new FileReader(str));
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            String readLine = bufferedReader.readLine();
            try {
                bufferedReader.close();
                return readLine;
            } catch (IOException e3) {
                e3.printStackTrace();
                return readLine;
            }
        } catch (Exception e4) {
            e = e4;
            bufferedReader2 = bufferedReader;
            e.printStackTrace();
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return "-1";
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            if (bufferedReader2 != null) {
                try {
                    bufferedReader2.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static int m(Context context, String str, boolean z) {
        int i2 = 0;
        try {
            if (!TextUtils.isEmpty(str)) {
                String n2 = n(context, str, z);
                if (TextUtils.isEmpty(n2)) {
                    return 0;
                }
                int s2 = s(n2, n2.indexOf("+"));
                GaLog.a("GameAssistUtils", "getGameStrengMode: mode = " + s2);
                i2 = s2;
            }
        } catch (Exception unused) {
        }
        GaLog.a("GameAssistUtils", "getGameStrengMode: gameStrengMode = " + i2);
        return i2;
    }

    private static String n(Context context, String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String string = Settings.Global.getString(context.getContentResolver(), z ? "game_strengthen_mode_value_assist" : "game_strengthen_mode_value_assist_no_invert");
        if (string != null && string.contains(str)) {
            for (String str2 : string.split(",")) {
                String trim = str2.trim();
                if (!trim.isEmpty() && trim.contains(str)) {
                    return trim;
                }
            }
        }
        return null;
    }

    public static String o(Resources resources, int i2) {
        try {
            TypedValue typedValue = new TypedValue();
            resources.getValue(i2, typedValue, false);
            return typedValue.string.toString();
        } catch (Error | Exception e2) {
            e2.printStackTrace();
            return resources.getResourceName(i2);
        }
    }

    public static int[] p(String str) {
        try {
            JSONArray jSONArray = new JSONObject(PluginConfig.f7222d).getJSONArray(str);
            int[] iArr = new int[jSONArray.length()];
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                iArr[i2] = jSONArray.getInt(i2);
            }
            return iArr;
        } catch (Exception unused) {
            GaLog.a("GameAssistUtils", "getPluginAttributes: attributes is null in " + str);
            return null;
        }
    }

    public static String q() {
        return f().getResources().getString(R.string.plugin_list_attributes);
    }

    public static List r(Context context) {
        return PluginConfig.g(context);
    }

    private static int s(String str, int i2) {
        try {
            return Integer.parseInt(String.valueOf(str.charAt(i2 + 1)));
        } catch (Exception unused) {
            return 0;
        }
    }

    public static String t(Context context, String str, boolean z) {
        return z ? Settings.Secure.getString(context.getContentResolver(), str) : Settings.Global.getString(context.getContentResolver(), str);
    }

    public static String u(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static boolean v(String str, String str2) {
        return f().getPackageManager().checkPermission(str, SystemMgr.A(str2)) == 0;
    }

    public static boolean w(String str, String str2, boolean z) {
        if (!z) {
            return v(str, str2);
        }
        boolean z2 = false;
        try {
            IBinder iBinder = (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "package");
            Class<?> cls = Class.forName("android.content.pm.IPackageManager$Stub");
            Object invoke = cls.getDeclaredMethod("asInterface", IBinder.class).invoke(cls, iBinder);
            if (((Integer) invoke.getClass().getDeclaredMethod("checkPermission", String.class, String.class, Integer.TYPE).invoke(invoke, str, str2, 999)).intValue() == 0) {
                z2 = true;
            }
        } catch (Exception e2) {
            GaLog.a("GameAssistUtils", "hasPermissionAsUser() e : " + e2);
            e2.printStackTrace();
        }
        GaLog.e("GameAssistUtils", "hasPermissionAsUser() ---------->hasPermissionAsUser : " + z2);
        return z2;
    }

    public static boolean x(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str4 : str.split(str3)) {
            if (str4.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean y(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(SystemMgr.A(str), 0);
        } catch (Exception e2) {
            e2.printStackTrace();
            packageInfo = null;
        }
        return packageInfo != null || GameKeysWrapper.b().d(context, SystemMgr.A(str), 0);
    }

    public static boolean z(Context context, String str) {
        try {
            int applicationEnabledSetting = context.getPackageManager().getApplicationEnabledSetting(SystemMgr.A(str));
            GaLog.e("GameAssistUtils", "isApplicationDisabled= " + applicationEnabledSetting);
            return applicationEnabledSetting == 2 || applicationEnabledSetting == 3;
        } catch (IllegalArgumentException e2) {
            e2.getMessage();
            GaLog.k("GameAssistUtils", "packageName not exist");
            return false;
        }
    }
}
