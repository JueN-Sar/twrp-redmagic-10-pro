package cn.nubia.gameassist.search;

import android.content.ContentProviderClient;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Rect;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.bright.BrightSeekbarViewController;
import cn.nubia.gameassist.dessert.panel.DessertViewController;
import cn.nubia.gameassist.meditationmode.MeditationModeViewController;
import cn.nubia.gameassist.operation.SubViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceViewController;
import cn.nubia.gameassist.plugin.panel.PluginSwitchController;
import cn.nubia.gameassist.volume.VolumeSeekbarViewController;
import com.zte.gameassist.ai.AIFlickerTips;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes.dex */
public class GlobalSearchUtil {

    public static class SearchInfo {

        /* renamed from: a, reason: collision with root package name */
        String f7392a;

        /* renamed from: b, reason: collision with root package name */
        String f7393b;

        /* renamed from: c, reason: collision with root package name */
        String f7394c;

        SearchInfo(String str, String str2, String str3) {
            this.f7392a = str;
            this.f7393b = str2;
            this.f7394c = str3;
        }

        public String toString() {
            return "name:" + this.f7392a + " help:" + this.f7393b + " appName:" + this.f7394c;
        }
    }

    static {
        AIFlickerTips.f16339h = true;
    }

    public static boolean a(Context context, String str) {
        String c2;
        String str2;
        str.hashCode();
        if (str.equals("cn.nubia.gameassist")) {
            c2 = c(context);
            str2 = "v26.03.10";
        } else {
            if (!str.equals("cn.nubia.gamelauncher")) {
                return false;
            }
            str2 = e(context);
            c2 = d(context);
        }
        GaLog.e("GameassistGlobalSearch", str + " globalSearchSavedVersion: " + c2 + " globalSearchVersion " + str2);
        return TextUtils.isEmpty(c2) || !c2.equals(str2);
    }

    public static Intent b(ContentValues contentValues) {
        Intent intent = new Intent();
        if (contentValues.containsKey("action")) {
            intent.setAction(contentValues.getAsString("action"));
        }
        if (contentValues.containsKey("category")) {
            intent.addCategory(contentValues.getAsString("category"));
        }
        if (contentValues.containsKey("package_name")) {
            if (contentValues.containsKey("class_name")) {
                intent.setClassName(contentValues.getAsString("package_name"), contentValues.getAsString("class_name"));
            } else {
                intent.setPackage(contentValues.getAsString("package_name"));
            }
        }
        if (contentValues.containsKey("intent_flag")) {
            intent.addFlags(g(contentValues.getAsString("intent_flag")));
        }
        if (contentValues.containsKey("view_id")) {
            intent.putExtra("view_id", contentValues.getAsString("view_id"));
        }
        v(intent, contentValues, "param_string", "param_string1", "param_string2", "param_boolean", "param_boolean1", "param_int", "param_int1");
        return intent;
    }

    public static String c(Context context) {
        return i(context).getString("gameassist_global_search_config_version", "");
    }

    public static String d(Context context) {
        return i(context).getString("gamespace_global_search_saved_version", "");
    }

    public static String e(Context context) {
        return Settings.Global.getString(context.getContentResolver(), "gamespace_global_search_config_version");
    }

    public static synchronized List f(Context context) {
        synchronized (GlobalSearchUtil.class) {
            ArrayList arrayList = new ArrayList();
            if (context == null) {
                return arrayList;
            }
            Cursor cursor = null;
            try {
                try {
                    GaLog.e("GameassistGlobalSearch", "getGlobalSearchInfo " + SystemMgr.v());
                    cursor = context.getContentResolver().query(GlobalSearchConstants.f7383a, null, null, null);
                    while (cursor != null) {
                        if (!cursor.moveToNext()) {
                            break;
                        }
                        String string = cursor.getString(cursor.getColumnIndex("package_list"));
                        String str = "";
                        String str2 = "";
                        if (!TextUtils.isEmpty(string)) {
                            GaLog.e("GameassistGlobalSearch", "packageList " + string);
                            if (string.startsWith("whitelist,")) {
                                str = j(context, string.substring(10));
                                GaLog.e("GameassistGlobalSearch", "whiteList:" + str);
                            } else if (string.startsWith("blacklist,")) {
                                str2 = j(context, string.substring(10));
                                GaLog.e("GameassistGlobalSearch", "blackList:" + str2);
                            }
                        }
                        if (TextUtils.isEmpty(str) || str.contains(SystemMgr.v())) {
                            if (TextUtils.isEmpty(str2) || !str2.contains(SystemMgr.v())) {
                                arrayList.add(new SearchInfo(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("help")), cursor.getString(cursor.getColumnIndex("app_label"))));
                            }
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                } catch (Exception e2) {
                    GaLog.b("GameassistGlobalSearch", "Exception: " + e2.getMessage());
                    if (cursor != null) {
                        cursor.close();
                    }
                    return arrayList;
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
    }

    public static int g(String str) {
        str.hashCode();
        switch (str) {
            case "Intent.FLAG_ACTIVITY_NO_HISTORY":
                return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_INSET_PARENT_FRAME_BY_IME;
            case "Intent.FLAG_ACTIVITY_SINGLE_TOP":
                return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_TRUSTED_OVERLAY;
            case "Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT":
                return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_NOT_MAGNIFIABLE;
            case "Intent.FLAG_ACTIVITY_NO_USER_ACTION":
                return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OVERRIDE_LAYOUT_IN_DISPLAY_CUTOUT_MODE;
            case "Intent.FLAG_ACTIVITY_CLEAR_TOP":
                return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OPT_OUT_EDGE_TO_EDGE;
            default:
                return WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED;
        }
    }

    public static List h() {
        return GlobalSearchConstants.f7386d;
    }

    private static SharedPreferences i(Context context) {
        return context.getSharedPreferences("data", 0);
    }

    private static String j(Context context, String str) {
        return TextUtils.isEmpty(str) ? "" : (str.startsWith("Settings.Global.") || str.startsWith("Settings.Secure.") || str.startsWith("Settings.System.")) ? Settings.Global.getString(context.getContentResolver(), str.substring(16)) : "";
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x0088, code lost:
    
        if (com.zte.shared.wrapper.ZteFeatureWrapper.getBoolean(r6, false) == false) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x008a, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0094, code lost:
    
        if (com.zte.shared.wrapper.ZteFeatureWrapper.getBoolean(r6, false) != false) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean k(java.lang.String r8, java.lang.String r9) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r9)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            java.lang.String r0 = ","
            boolean r2 = r9.contains(r0)
            java.lang.String r3 = "GameassistGlobalSearch"
            r4 = 1
            if (r2 == 0) goto L56
            java.lang.String[] r9 = r9.split(r0)
            int r0 = r9.length
            r2 = r1
        L19:
            if (r2 >= r0) goto L41
            r5 = r9[r2]
            boolean r6 = android.text.TextUtils.isEmpty(r5)
            if (r6 != 0) goto L3e
            boolean r6 = com.zte.shared.wrapper.ZteFeatureWrapper.getBoolean(r5, r1)
            if (r6 == 0) goto L3e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "supportItem "
            r8.append(r9)
            r8.append(r5)
            java.lang.String r8 = r8.toString()
            com.zte.gameassist.utils.GaLog.e(r3, r8)
            return r4
        L3e:
            int r2 = r2 + 1
            goto L19
        L41:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "not SupportItem "
            r9.append(r0)
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            com.zte.gameassist.utils.GaLog.e(r3, r8)
            return r1
        L56:
            java.lang.String r0 = ":"
            boolean r2 = r9.contains(r0)
            if (r2 == 0) goto Lb7
            java.lang.String[] r9 = r9.split(r0)
            int r0 = r9.length
            r2 = r1
            r5 = r4
        L65:
            if (r2 >= r0) goto L9a
            r6 = r9[r2]
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 == 0) goto L70
            goto L97
        L70:
            java.lang.String r7 = "!"
            boolean r7 = r6.startsWith(r7)
            if (r7 == 0) goto L8e
            java.lang.String r6 = r6.substring(r4)
            boolean r7 = android.text.TextUtils.isEmpty(r6)
            if (r7 != 0) goto L97
            if (r5 == 0) goto L8c
            boolean r5 = com.zte.shared.wrapper.ZteFeatureWrapper.getBoolean(r6, r1)
            if (r5 != 0) goto L8c
        L8a:
            r5 = r4
            goto L97
        L8c:
            r5 = r1
            goto L97
        L8e:
            if (r5 == 0) goto L8c
            boolean r5 = com.zte.shared.wrapper.ZteFeatureWrapper.getBoolean(r6, r1)
            if (r5 == 0) goto L8c
            goto L8a
        L97:
            int r2 = r2 + 1
            goto L65
        L9a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r0 = "isSupportItem "
            r9.append(r0)
            r9.append(r8)
            java.lang.String r8 = " "
            r9.append(r8)
            r9.append(r5)
            java.lang.String r8 = r9.toString()
            com.zte.gameassist.utils.GaLog.e(r3, r8)
            return r5
        Lb7:
            boolean r8 = com.zte.shared.wrapper.ZteFeatureWrapper.getBoolean(r9, r1)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.search.GlobalSearchUtil.k(java.lang.String, java.lang.String):boolean");
    }

    public static void l(Context context, ContentValues contentValues) {
        GameAssistWindowManager O;
        String asString = contentValues.getAsString("start_type");
        GaLog.e("GameassistGlobalSearch", "startType：" + asString);
        if (!"Provider".equals(asString)) {
            Intent b2 = b(contentValues);
            if ("Activity".equals(asString)) {
                context.startActivity(b2);
                return;
            } else if ("Service".equals(asString)) {
                context.startService(b2);
                return;
            } else {
                if ("Broadcast".equals(asString)) {
                    context.sendBroadcast(b2);
                }
                return;
            }
        }
        if (contentValues.containsKey("app_label") && context.getString(R.string.app_label).equals(contentValues.getAsString("app_label"))) {
            ContentValues n2 = n(contentValues, "param_string", "param_string1", "param_string2", "param_boolean", "param_boolean1", "param_int", "param_int1");
            String asString2 = n2.containsKey("from") ? n2.getAsString("from") : "plugin";
            GaLog.e("GameassistGlobalSearch", " from " + asString2);
            O = GameAssistWindowManager.O(context);
            asString2.hashCode();
            switch (asString2) {
                case "performance_effect":
                    ((PerformanceViewController) O.T(PerformanceViewController.class)).R(contentValues.getAsString("view_id"));
                    break;
                case "bright":
                    ((BrightSeekbarViewController) O.T(BrightSeekbarViewController.class)).R(contentValues.getAsString("view_id"));
                    break;
                case "plugin":
                    ((PluginSwitchController) O.T(PluginSwitchController.class)).m0(contentValues.getAsString("name"));
                    break;
                case "volume":
                    ((VolumeSeekbarViewController) O.T(VolumeSeekbarViewController.class)).R(contentValues.getAsString("view_id"));
                    break;
                case "notification":
                    ((MeditationModeViewController) O.T(MeditationModeViewController.class)).R(contentValues.getAsString("view_id"));
                    break;
                case "ai_search":
                    ((SearchViewController) O.T(SearchViewController.class)).R(contentValues.getAsString("view_id"));
                    break;
                case "dessert":
                    ((DessertViewController) O.T(DessertViewController.class)).Y(contentValues.getAsString("name"));
                    break;
                case "plugin_panel":
                    ((SubViewController) O.T(SubViewController.class)).R(contentValues.getAsString("view_id"));
                    break;
            }
        }
    }

    public static List m(Context context) {
        StringBuilder sb;
        InputStream open;
        ArrayList arrayList = new ArrayList();
        InputStream inputStream = null;
        try {
            try {
                open = context.getAssets().open("globalsearch/global_search_config.xml");
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
        }
        try {
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            newPullParser.setInput(open, "UTF-8");
            String string = context.getString(R.string.app_label);
            ContentValues contentValues = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    String trim = newPullParser.getName().trim();
                    if ("plugin_config".equals(trim)) {
                        GaLog.e("GameassistGlobalSearch", "configVersion " + newPullParser.getAttributeValue(null, "config_version").trim());
                    } else if ("item_config".equals(newPullParser.getName())) {
                        contentValues = new ContentValues();
                    } else if ("name".equals(trim)) {
                        u(context, "name", newPullParser.nextText(), contentValues);
                        o("app_label", string, contentValues);
                    } else if ("help".equals(trim)) {
                        u(context, "help", newPullParser.nextText(), contentValues);
                    } else {
                        o(trim, newPullParser.nextText(), contentValues);
                    }
                } else if (eventType != 3) {
                    continue;
                } else if ("item_config".equals(newPullParser.getName()) && contentValues != null && !contentValues.isEmpty()) {
                    if (!contentValues.containsKey("feature")) {
                        arrayList.add(contentValues);
                    } else if (k(contentValues.getAsString("name"), contentValues.getAsString("feature"))) {
                        arrayList.add(contentValues);
                    }
                }
            }
            if (open != null) {
                try {
                    open.close();
                } catch (IOException e3) {
                    e = e3;
                    sb = new StringBuilder();
                    sb.append("IOException: ");
                    sb.append(e.getMessage());
                    GaLog.e("GameassistGlobalSearch", sb.toString());
                    return arrayList;
                }
            }
        } catch (Exception e4) {
            e = e4;
            inputStream = open;
            GaLog.e("GameassistGlobalSearch", "Exception: " + e.getMessage());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e5) {
                    e = e5;
                    sb = new StringBuilder();
                    sb.append("IOException: ");
                    sb.append(e.getMessage());
                    GaLog.e("GameassistGlobalSearch", sb.toString());
                    return arrayList;
                }
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            inputStream = open;
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    GaLog.e("GameassistGlobalSearch", "IOException: " + e6.getMessage());
                }
            }
            throw th;
        }
        return arrayList;
    }

    public static ContentValues n(ContentValues contentValues, String... strArr) {
        ContentValues contentValues2 = new ContentValues();
        for (String str : strArr) {
            try {
                if (contentValues.containsKey(str)) {
                    String[] split = contentValues.getAsString(str).split(",");
                    if (split.length == 2) {
                        contentValues2.put(split[0], split[1]);
                        GaLog.e("GameassistGlobalSearch", "put " + split[0] + " value " + split[1]);
                    }
                }
            } catch (Exception e2) {
                GaLog.b("GameassistGlobalSearch", "setParamValue: " + e2.getMessage());
            }
        }
        return contentValues2;
    }

    public static void o(String str, String str2, ContentValues contentValues) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        contentValues.put(str, str2);
    }

    public static void p(Context context) {
        List m2 = m(context);
        if (m2.isEmpty()) {
            GaLog.b("GameassistGlobalSearch", "Parsed XML is empty");
            return;
        }
        ContentResolver contentResolver = context.getContentResolver();
        ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
        Iterator it = m2.iterator();
        while (it.hasNext()) {
            arrayList.add(ContentProviderOperation.newInsert(GlobalSearchConstants.f7383a).withValues((ContentValues) it.next()).build());
        }
        try {
            contentResolver.applyBatch("cn.nubia.gameassist.globalsearch", arrayList);
            s(context, "v26.03.10");
        } catch (Exception e2) {
            GaLog.b("GameassistGlobalSearch", "Exception: " + e2.getMessage());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v5, types: [android.os.Bundle] */
    public static Bundle q(Context context) {
        Bundle bundle;
        ContentProviderClient acquireUnstableContentProviderClient;
        ContentProviderClient contentProviderClient = 0;
        Bundle bundle2 = null;
        ContentProviderClient contentProviderClient2 = null;
        try {
            try {
                acquireUnstableContentProviderClient = context.getContentResolver().acquireUnstableContentProviderClient(GlobalSearchConstants.f7384b);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e2) {
            e = e2;
            bundle = null;
        }
        try {
            bundle2 = acquireUnstableContentProviderClient.call("initGlobalSearchList", context.getPackageName(), null);
            if (bundle2 != null && bundle2.containsKey("package_name")) {
                t(context, e(context));
            }
            acquireUnstableContentProviderClient.close();
            contentProviderClient = bundle2;
        } catch (Exception e3) {
            e = e3;
            bundle = bundle2;
            contentProviderClient2 = acquireUnstableContentProviderClient;
            GaLog.b("GameassistGlobalSearch", "Exception: " + e.getMessage());
            if (contentProviderClient2 != null) {
                contentProviderClient2.close();
            }
            contentProviderClient = bundle;
            return contentProviderClient;
        } catch (Throwable th2) {
            th = th2;
            contentProviderClient = acquireUnstableContentProviderClient;
            if (contentProviderClient != 0) {
                contentProviderClient.close();
            }
            throw th;
        }
        return contentProviderClient;
    }

    public static void r(View view, String str) {
        if (!ZteFeature.isSupportGlobalSearch() || view == null) {
            return;
        }
        try {
            if (view.getContext() != null) {
                AIFlickerTips.J(view, str);
                AIFlickerTips.K(view, new Rect(15, -20, 15, -5));
            }
        } catch (Exception e2) {
            GaLog.b("GameassistGlobalSearch", "setFlickerName exception: " + e2.getMessage());
        }
    }

    public static void s(Context context, String str) {
        GaLog.e("GameassistGlobalSearch", "setGameassistGlobalSearchVersion: " + str);
        i(context).edit().putString("gameassist_global_search_config_version", str).apply();
    }

    public static void t(Context context, String str) {
        GaLog.e("GameassistGlobalSearch", "setGamespaceGlobalSearchSavedVersion: " + str);
        i(context).edit().putString("gamespace_global_search_saved_version", str).apply();
    }

    public static void u(Context context, String str, String str2, ContentValues contentValues) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        if (!str2.contains(",")) {
            int identifier = context.getResources().getIdentifier(str2, "string", context.getPackageName());
            if (identifier != 0) {
                contentValues.put(str, context.getString(identifier));
                return;
            }
            return;
        }
        String[] split = str2.split(",");
        StringBuilder sb = new StringBuilder("");
        for (String str3 : split) {
            int identifier2 = context.getResources().getIdentifier(str3, "string", context.getPackageName());
            if (identifier2 != 0) {
                sb.append(context.getString(identifier2));
                sb.append(",");
            }
        }
        GaLog.e("GameassistGlobalSearch", "setItem:" + ((Object) sb));
        contentValues.put(str, sb.toString());
    }

    public static void v(Intent intent, ContentValues contentValues, String... strArr) {
        try {
            for (String str : strArr) {
                if (contentValues.containsKey(str)) {
                    String[] split = contentValues.getAsString(str).split(",");
                    if (split.length == 2) {
                        if ("from".equals(split[0]) && "gamecontrol_menu".equals(split[1])) {
                            intent.putExtra("packageName", SystemMgr.t());
                            intent.putExtra("activity", SystemMgr.s());
                            intent.putExtra("type", "global_search");
                            if (SystemMgr.L()) {
                                intent.putExtra("shortcutLabel", SystemMgr.u());
                                intent.putExtra("isShortCut", true);
                            }
                        } else {
                            intent.putExtra(split[0], split[1]);
                            GaLog.e("GameassistGlobalSearch", "putExtra " + split[0] + " param " + split[1]);
                        }
                    }
                }
            }
        } catch (Exception e2) {
            GaLog.b("GameassistGlobalSearch", "setParamValue: " + e2.getMessage());
        }
    }

    public static void w(String str, String str2, ContentValues contentValues) {
        o(str, str2, contentValues);
    }

    public static void x(String str) {
        if (ZteFeature.isSupportGlobalSearch()) {
            GaLog.e("GameassistGlobalSearch", " showFlicker " + str);
            AIFlickerTips.M(str);
        }
    }
}
