package cn.nubia.gameassist.plugin.config;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.plugin.PluginUtils;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class PluginConfig {

    /* renamed from: c, reason: collision with root package name */
    public static ArrayList f7221c;

    /* renamed from: a, reason: collision with root package name */
    private static final DateFormat f7219a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    /* renamed from: b, reason: collision with root package name */
    public static boolean f7220b = false;

    /* renamed from: d, reason: collision with root package name */
    public static String f7222d = Utils.q();

    public static boolean a(String[] strArr, String str) {
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void b(Context context, PrintWriter printWriter) {
        if (f7221c == null) {
            return;
        }
        if (f7220b) {
            printWriter.write("  PluginConfigTest isLocal=" + j() + ":[\n");
            Iterator it = f7221c.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                printWriter.write("    " + str + ":{\n");
                printWriter.write("      pubgmhd=" + l(context, str, "com.tencent.tmgp.pubgmhd") + ",\t");
                printWriter.write("      sgame=" + l(context, str, "com.tencent.tmgp.sgame") + ",\t");
                printWriter.write("      kg=" + l(context, str, "com.garena.game.kg") + ",\t");
                printWriter.write("      cf=" + l(context, str, "com.tencent.tmgp.cf") + ",\t");
                printWriter.write("      hyxd=" + l(context, str, "com.netease.hyxd") + ",\t");
                printWriter.write("      ko=" + l(context, str, "com.netease.ko") + ",\t");
                printWriter.write("      ig=" + l(context, str, "com.tencent.ig") + ",\t");
                printWriter.write("      af=" + l(context, str, "com.tencent.af") + ",\t");
                printWriter.write("    },\n");
            }
            printWriter.write("  ]\n\n");
        }
        printWriter.write("PluginConfig :\n version=" + e(context) + " updateTime=" + f(context) + "\n");
        Iterator it2 = f7221c.iterator();
        while (it2.hasNext()) {
            String str2 = (String) it2.next();
            int h2 = h(context, str2);
            StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(str2);
            sb.append(" space=");
            sb.append(h2);
            sb.append(" enable=");
            sb.append(Settings.Global.getInt(context.getContentResolver(), "game_assist_enable_plugin_" + str2, 1) == 1);
            sb.append("{\n");
            printWriter.write(sb.toString());
            printWriter.write("   WhiteList [\n");
            String[] i2 = i(context, str2);
            if (i2 != null && i2.length > 0) {
                for (String str3 : i2) {
                    printWriter.write("    " + str3 + ",\n");
                }
            }
            printWriter.write("   ]\n");
            printWriter.write("   BlackList [\n");
            String[] d2 = d(context, str2);
            if (d2 != null && d2.length > 0) {
                for (String str4 : d2) {
                    printWriter.write("    " + str4 + ",\n");
                }
            }
            printWriter.write("   ]\n");
            printWriter.write(" },\n");
        }
    }

    public static boolean c(String[] strArr, String str) {
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (str.equals(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String[] d(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), "game_assist_black_list_" + str);
        return (string == null || string.length() == 0) ? new String[0] : string.trim().split(",");
    }

    public static String e(Context context) {
        return Settings.Global.getString(context.getContentResolver(), "game_assist_plugin_config_version");
    }

    public static String f(Context context) {
        return f7219a.format(new Date(Settings.Global.getLong(context.getContentResolver(), "game_assist_plugin_config_update_time", 0L)));
    }

    public static List g(Context context) {
        int z;
        String t = SystemMgr.t();
        f7221c = Utils.k();
        ArrayList arrayList = new ArrayList();
        String e2 = SharedPreferencesUtil.k(context).e(t);
        boolean z2 = !TextUtils.isEmpty(e2);
        ArrayList<String> arrayList2 = z2 ? new ArrayList(Arrays.asList(e2.split(","))) : null;
        Iterator it = f7221c.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (l(context, str, t)) {
                arrayList.add(str);
                if (z2 && !arrayList2.contains(str)) {
                    arrayList2.add(0, str);
                    GaLog.e("PluginConfig", "add plugin " + str);
                }
            }
        }
        if (z2) {
            ArrayList arrayList3 = new ArrayList();
            for (String str2 : arrayList2) {
                if (!arrayList.contains(str2)) {
                    arrayList3.add(str2);
                    GaLog.e("PluginConfig", "remove plugin " + str2);
                }
            }
            if (arrayList3.size() > 0) {
                arrayList2.removeAll(arrayList3);
            }
        }
        if (ZteFeature.isSupportSort() && ((z = SharedPreferencesUtil.k(context).z(t)) == 0 || z == 3)) {
            if (z == 0 && !FoldMgr.f()) {
                if (z2) {
                    if (!arrayList2.contains("custome_sort")) {
                        arrayList2.add(arrayList2.size(), "custome_sort");
                    }
                } else if (!arrayList.contains("custome_sort")) {
                    arrayList.add(arrayList.size(), "custome_sort");
                }
            }
            if (z2) {
                return arrayList2;
            }
        }
        return arrayList;
    }

    public static int h(Context context, String str) {
        return Settings.Global.getInt(context.getContentResolver(), "game_assist_trigger_space_" + str, 3);
    }

    public static String[] i(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), "game_assist_white_list_" + str);
        return (string == null || string.length() == 0) ? new String[0] : string.trim().split(",");
    }

    public static boolean j() {
        return !ZteFeature.IS_INTER_VERSION;
    }

    public static boolean k(Context context, String str) {
        ContentResolver contentResolver = context.getContentResolver();
        StringBuilder sb = new StringBuilder();
        sb.append("game_assist_enable_plugin_");
        sb.append(str);
        return Settings.Global.getInt(contentResolver, sb.toString(), 1) == 1;
    }

    public static boolean l(Context context, String str, String str2) {
        if (!k(context, str)) {
            return false;
        }
        if ("super_resolution".equals(str)) {
            return PluginUtils.f(context).p(str2);
        }
        if ("super_resolution_old".equals(str)) {
            return ZteFeature.isSuperResolutionDetachEnable();
        }
        if ((h(context, str) & (j() ? 1 : 2)) == 0) {
            return false;
        }
        String[] i2 = i(context, str);
        String[] d2 = d(context, str);
        return a(i2, str2) ? !c(d2, str2) : (i2 == null || i2.length <= 0) && !a(d2, str2);
    }

    public static int m(String str) {
        if (str == null) {
            return 0;
        }
        int i2 = 0;
        int i3 = 0;
        for (char c2 : str.toCharArray()) {
            if (c2 == '.') {
                i2 = (i2 * 100) + i3;
                i3 = 0;
            } else if (c2 >= '0' && c2 <= '9') {
                i3 = (i3 * 10) + (c2 - '0');
            }
        }
        return (i2 * 100) + i3;
    }
}
