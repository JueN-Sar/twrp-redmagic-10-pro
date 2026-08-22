package cn.nubia.plugin.screenextraction.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class DefaultUtils {
    public static String a() {
        return !FoldMgr.f() ? "screen_extraction_data_shared_preferences" : FoldMgr.c().e() ? "screen_extraction_data_shared_preferences_0" : "screen_extraction_data_shared_preferences_1";
    }

    public static boolean b(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "screen_extraction_helper_done", 0) == 1;
    }

    public static ScreenExtractionData c(Context context, String str) {
        ScreenExtractionData d2;
        ScreenExtractionData d3;
        if (!FoldMgr.f()) {
            return d(context, str, "screen_extraction_data_shared_preferences");
        }
        if (FoldMgr.c().e()) {
            d2 = d(context, str, "screen_extraction_data_shared_preferences_0");
            if (d2 == null) {
                d3 = d(context, str, "screen_extraction_data_shared_preferences_1");
                f(context, d3, "screen_extraction_data_shared_preferences_0");
                return d3;
            }
            return d2;
        }
        d2 = d(context, str, "screen_extraction_data_shared_preferences_1");
        if (d2 == null) {
            d3 = d(context, str, "screen_extraction_data_shared_preferences_0");
            if (d3 != null) {
                int i2 = ((InflaterHelper.FixedScreenState) InflaterHelper.f16516e.b()).f16528g;
                if (d3.c().bottom > i2 || d3.h().bottom > i2) {
                    int i3 = (-d3.c().centerY()) / 2;
                    int i4 = (-d3.h().centerY()) / 2;
                    d3.c().offset(0, i3);
                    d3.h().offset(0, i4);
                    GaLog.a("ScreenExtraction", "update dstOffset=" + i3 + " srcOffset=" + i4);
                }
                f(context, d3, "screen_extraction_data_shared_preferences_1");
            }
            return d3;
        }
        return d2;
    }

    public static ScreenExtractionData d(Context context, String str, String str2) {
        if (str == null) {
            return null;
        }
        return ScreenExtractionData.a(str, context.getSharedPreferences(str2, 0).getString("data:" + str, null));
    }

    public static void e(Context context, ScreenExtractionData screenExtractionData) {
        f(context, screenExtractionData, a());
    }

    public static void f(Context context, ScreenExtractionData screenExtractionData, String str) {
        if (screenExtractionData == null) {
            GaLog.k("ScreenExtraction", "saveScreenExtractionData data is null");
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(str, 0).edit();
        edit.putString("data:" + screenExtractionData.e(), screenExtractionData.g());
        edit.apply();
    }
}
