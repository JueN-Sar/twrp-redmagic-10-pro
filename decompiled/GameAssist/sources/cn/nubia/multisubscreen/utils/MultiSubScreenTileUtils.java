package cn.nubia.multisubscreen.utils;

import android.content.Context;
import android.text.TextUtils;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.tiles.ChargeSeparationTiles;
import cn.nubia.multisubscreen.tiles.MultiSubScreenDessertTile;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class MultiSubScreenTileUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f8169a = Collections.synchronizedMap(new LinkedHashMap());

    /* renamed from: b, reason: collision with root package name */
    private static final Map f8170b = Collections.synchronizedMap(new LinkedHashMap());

    public static void a() {
        Map map = f8169a;
        if (map != null) {
            map.clear();
        }
        Map map2 = f8170b;
        if (map2 != null) {
            map2.clear();
        }
    }

    private static ArrayList b(String str) {
        return TextUtils.isEmpty(str) ? new ArrayList() : new ArrayList(Arrays.asList(str.split(",")));
    }

    public static QSTile.Icon c(Context context, String str) {
        str.hashCode();
        switch (str) {
            case "fan":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_fan_on_unpress);
            case "wifi":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_wif_unpress);
            case "performance_monitor":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_performance_monitor_switch_unpress);
            case "charge_separation":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_charge_separation_unpress);
            case "notification_msg":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_notification_msg_unpress);
            case "mis_operate":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_misoperate_unpress);
            default:
                return null;
        }
    }

    public static String d(Context context, String str) {
        str.hashCode();
        switch (str) {
            case "fan":
                return context.getString(R.string.ic_qs_fan);
            case "wifi":
                return context.getString(R.string.ic_qs_wifi_switch);
            case "performance_monitor":
                return context.getString(R.string.ic_qs_performance_monitor);
            case "charge_separation":
                return ChargeSeparationTiles.z0(context, R.string.ic_qs_charge_separation);
            case "notification_msg":
                return context.getString(R.string.ic_qs_multi_subscreen_notification_msg);
            case "mis_operate":
                return context.getString(R.string.ic_qs_mis_operate);
            default:
                return null;
        }
    }

    private static String e(boolean z, Context context) {
        return context.getResources().getString(z ? R.string.multi_sub_screen_right_tiles : R.string.multi_sub_screen_tiles);
    }

    public static ArrayList f(boolean z, Context context) {
        return b(e(z, context));
    }

    public static MultiSubScreenDessertTile g(String str) {
        MultiSubScreenDessertTile multiSubScreenDessertTile = (MultiSubScreenDessertTile) f8169a.get(str);
        return multiSubScreenDessertTile == null ? (MultiSubScreenDessertTile) f8170b.get(str) : multiSubScreenDessertTile;
    }

    public static QSTile.Icon h(Context context, String str) {
        str.hashCode();
        switch (str) {
            case "fan":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_fan_off);
            case "wifi":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_wifi_normal);
            case "performance_monitor":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_performance_monitor_switch_off);
            case "charge_separation":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_charge_separation_normal);
            case "notification_msg":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_notification_msg_off);
            case "mis_operate":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_misoperate_off);
            default:
                return null;
        }
    }

    public static QSTile.Icon i(Context context, String str) {
        str.hashCode();
        switch (str) {
            case "fan":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_fan_on);
            case "wifi":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_wifi_light);
            case "performance_monitor":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_performance_monitor_switch_on);
            case "charge_separation":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_charge_separation_light);
            case "notification_msg":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_notification_msg_on);
            case "mis_operate":
                return QSTile.ResourceIcon.b(R.drawable.game_ic_qs_misoperate_on);
            default:
                return null;
        }
    }

    public static Collection j(Context context, QSTile.Host host, List list) {
        if (list == null || list.isEmpty()) {
            list = f(true, context);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : list) {
            try {
                Map map = f8170b;
                if (map.get(str) != null) {
                    linkedHashMap.put(str, (QSTile) map.get(str));
                } else if (map.isEmpty()) {
                    linkedHashMap.put(str, new MultiSubScreenDessertTile(host, str));
                }
            } catch (Throwable th) {
                th.printStackTrace();
                GaLog.l("MultiSubScreen_MultiSubScreenTileUtils", "Error creating gameTile for spec: " + str, th);
            }
        }
        Map map2 = f8170b;
        map2.clear();
        map2.putAll(linkedHashMap);
        return map2.values();
    }

    public static Collection k(Context context, QSTile.Host host, List list, boolean z) {
        if (z) {
            f8170b.clear();
        }
        return j(context, host, list);
    }

    public static Collection l(Context context, QSTile.Host host, List list) {
        if (list == null || list.isEmpty()) {
            list = f(false, context);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : list) {
            try {
                Map map = f8169a;
                if (map.get(str) != null) {
                    linkedHashMap.put(str, (QSTile) map.get(str));
                } else if (map.isEmpty()) {
                    linkedHashMap.put(str, new MultiSubScreenDessertTile(host, str));
                }
            } catch (Throwable th) {
                th.printStackTrace();
                GaLog.l("MultiSubScreen_MultiSubScreenTileUtils", "Error creating gameTile for spec: " + str, th);
            }
        }
        Map map2 = f8169a;
        map2.clear();
        map2.putAll(linkedHashMap);
        return map2.values();
    }

    public static Collection m(Context context, QSTile.Host host, List list, boolean z) {
        if (z) {
            f8169a.clear();
        }
        return l(context, host, list);
    }
}
