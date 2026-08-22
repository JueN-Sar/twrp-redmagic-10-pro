package cn.nubia.gamelauncher.gamecontrolpanel.config;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class PluginConfig {
    public static boolean DEBUG = false;
    public static final String PLUGIN_CONTROL = "operation_devices";
    public static final String PLUGIN_HELPER = "help";
    public static final String PLUGIN_RANGE = "range_line";
    public static final int SPACE_TRIGGER_ALL = 3;
    public static final int SPACE_TRIGGER_INTERNATIONAL = 2;
    public static final int SPACE_TRIGGER_LOCAL = 1;
    public static final String TAG = "PluginConfig";
    public static final String PLUGIN_SIGHT = "sight_assist";
    public static final String PLUGIN_BROADCAST = "redmagic_broadcast";
    public static final String PLUGIN_KEYLINK = "keylink";
    public static final String PLUGIN_4D = "vibrate";
    public static final String PLUGIN_HUNT = "hunting_mode";
    public static final String PLUGIN_CHAT = "chat_assit";
    public static final String PLUGIN_INVESTIGATE = "investigation_mode";
    public static final String PLUGIN_KEYPOSISTION = "keyposition_assist";
    public static final String PLUGIN_SOUND = "sound_effect";
    public static final String PLUGIN_TIMER = "timer";
    public static final String[] PLUGIN_LIST = {PLUGIN_SIGHT, PLUGIN_BROADCAST, PLUGIN_KEYLINK, PLUGIN_4D, PLUGIN_HUNT, PLUGIN_CHAT, PLUGIN_INVESTIGATE, PLUGIN_KEYPOSISTION, PLUGIN_SOUND, PLUGIN_TIMER, "help"};
    public static final String TOUCH_SINGLE_POINT = "touch_single_point";
    public static final String TOUCH_LONG_POINT = "touch_long_point";
    public static final String TOUCH_QUEUE_POINT = "touch_queue_point";
    public static final String TOUCH_DOUBLE_POINT = "touch_double_point";
    public static final String TOUCH_SEPARATE_POINT = "touch_separate_point";
    public static final String TOUCH_LONG_KEYLINK_POINT = "touch_long_keylink_point";
    public static final String[] TOUCH_LIST = {TOUCH_SINGLE_POINT, TOUCH_SINGLE_POINT, TOUCH_LONG_POINT, TOUCH_QUEUE_POINT, TOUCH_DOUBLE_POINT, TOUCH_SEPARATE_POINT, TOUCH_LONG_KEYLINK_POINT};

    public static boolean containItem(String[] strArr, String str) {
        if (strArr != null && strArr.length > 0) {
            for (String str2 : strArr) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void dump(Context context, PrintWriter printWriter) {
        printWriter.write("PluginConfig:\n");
        int i = 0;
        while (true) {
            String[] strArr = PLUGIN_LIST;
            if (i >= strArr.length) {
                return;
            }
            String str = strArr[i];
            printWriter.write(" " + str + " space=" + getTriggerSpace(context, str) + "\n");
            printWriter.write("  WhiteList [\n");
            for (String str2 : getWhiteList(context, str)) {
                printWriter.write("    " + str2 + ",\n");
            }
            printWriter.write("  ]\n");
            printWriter.write("  BlackList [\n");
            for (String str3 : getBlackList(context, str)) {
                printWriter.write("    " + str3 + ",\n");
            }
            printWriter.write("  ]\n");
            i++;
        }
    }

    public static String[] getBlackList(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), "game_assist_black_list_" + str);
        return (string == null || string.length() == 0) ? new String[0] : string.trim().split(",");
    }

    public static String getConfigVersion(Context context) {
        return Settings.Global.getString(context.getContentResolver(), "game_assist_plugin_config_version");
    }

    public static long getLastUpdateTime(Context context) {
        return Settings.Global.getLong(context.getContentResolver(), "game_assist_plugin_config_update_time", 0L);
    }

    public static List<String> getPluginList(Context context, String str) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = PLUGIN_LIST;
            if (i >= strArr.length) {
                return arrayList;
            }
            String str2 = strArr[i];
            if (isPluginEnable(context, str2, str, Utils.isInternalVersion())) {
                arrayList.add(str2);
            }
            i++;
        }
    }

    public static int getTriggerSpace(Context context, String str) {
        return Settings.Global.getInt(context.getContentResolver(), "game_assist_trigger_space_" + str, 3);
    }

    public static String[] getWhiteList(Context context, String str) {
        String string = Settings.Global.getString(context.getContentResolver(), "game_assist_white_list_" + str);
        return (string == null || string.length() == 0) ? new String[0] : string.trim().split(",");
    }

    public static boolean isPluginEnable(Context context, String str, String str2, boolean z) {
        Log.i("wjTest", "*****context*****");
        if (((z ? 1 : 2) & getTriggerSpace(context, str)) == 0) {
            return false;
        }
        String[] whiteList = getWhiteList(context, str);
        if (containItem(whiteList, str2)) {
            return true;
        }
        return (whiteList == null || whiteList.length <= 0) && !containItem(getBlackList(context, str), str2);
    }

    public static int versionToInt(String str) {
        if (str == null) {
            return 0;
        }
        int i = 0;
        for (char c : str.toCharArray()) {
            if (c == '.') {
                i *= 1000;
            } else if (c >= '0' && c <= '9') {
                i = (i * 10) + (c - '0');
            }
        }
        return i;
    }
}
