package com.zte.gameassist.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.annotation.VisibleForTesting;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class SharedPreferencesUtil {

    /* renamed from: f, reason: collision with root package name */
    private static Context f17047f;

    /* renamed from: a, reason: collision with root package name */
    private final String f17048a = "notice_id";

    /* renamed from: b, reason: collision with root package name */
    private final String f17049b = "wallpaper_type";

    /* renamed from: c, reason: collision with root package name */
    private boolean f17050c = false;

    /* renamed from: d, reason: collision with root package name */
    private boolean f17051d = true;

    /* renamed from: e, reason: collision with root package name */
    private boolean f17052e = true;

    private static class SharedPreferencesUtilHolder {

        /* renamed from: a, reason: collision with root package name */
        public static final SharedPreferencesUtil f17053a = new SharedPreferencesUtil();
    }

    public static SharedPreferencesUtil k(Context context) {
        if (f17047f == null) {
            f17047f = context.getApplicationContext();
        }
        return SharedPreferencesUtilHolder.f17053a;
    }

    private SharedPreferences y() {
        return f17047f.getSharedPreferences("data", 0);
    }

    public String A() {
        return y().getString("plugin_enable_pkg_super_resolution", null);
    }

    public String B() {
        return y().getString("plugin_first_switch_pkg_super_resolution", null);
    }

    public String C() {
        return y().getString("plugin_super_resolution_typeItem_Data", null);
    }

    public boolean D() {
        return y().getBoolean("change_auto_brightness", false);
    }

    public boolean E(String str, String str2) {
        return v(str, str2) > u(str, str2);
    }

    public void F(String str, int i2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putInt(str + "_permission_denied_times", i2);
        edit.apply();
    }

    public void G(String str) {
        y().edit().putString("plugin_enable_pkg_super_resolution", str).apply();
    }

    public void H(String str) {
        y().edit().putString("plugin_first_switch_pkg_super_resolution", str).apply();
    }

    public void I(String str) {
        y().edit().putString("plugin_super_resolution_typeItem_Data", str).apply();
    }

    public void J(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        y().registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void K(int i2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putInt("before_light_status", i2);
        edit.apply();
    }

    public void L(boolean z) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("change_auto_brightness", z);
        edit.apply();
    }

    public void M(HashMap hashMap) {
        Z("close_by_endurance_mode", hashMap, false);
    }

    public void N(String str) {
        P("cc_custom_pips", str);
    }

    public void O(String str, String str2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putString(str + "_custome_tile_list", str2);
        edit.apply();
        GaLog.e("SharedPreferencesUtil", "set " + str + " custom tile list: " + str2);
    }

    public void P(String str, String str2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public void Q(String str) {
        P("cc_custom_tiles", str);
    }

    public void R(int i2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putInt("game_ratio_version", i2);
        edit.apply();
    }

    public void S(boolean z) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("game_reminder_power_off_alarm_permission_flag", z);
        edit.apply();
    }

    public void T(boolean z) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("host_performance_panel_enable", z);
        edit.apply();
    }

    public void U(String str, int i2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putInt(str, i2);
        edit.apply();
    }

    public void V(String str) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("first_click_one_keylink_" + str, false);
        edit.apply();
    }

    public void W(String str) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("first_click_range_line_" + str, false);
        edit.apply();
    }

    public void X(String str) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("first_click_sight_assist_" + str, false);
        edit.apply();
    }

    public void Y(String str) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("first_click_vibrate_" + str, false);
        edit.apply();
    }

    public void Z(String str, HashMap hashMap, boolean z) {
        if (hashMap == null || hashMap.isEmpty()) {
            P(str, null);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : hashMap.entrySet()) {
            sb.append((String) entry.getKey());
            sb.append(":");
            sb.append((String) entry.getValue());
            sb.append(",");
        }
        String substring = sb.substring(0, sb.length() - 1);
        if (z) {
            h0(str, substring);
        } else {
            P(str, substring);
        }
    }

    public int a() {
        return y().getInt("before_light_status", -1);
    }

    public void a0(String str) {
        SharedPreferences.Editor edit = y().edit();
        edit.putString("multi_sub_screen_connected_device_id", str);
        edit.apply();
    }

    public int b() {
        return Settings.Global.getInt(f17047f.getContentResolver(), "charge_separation_warning_flag", 0);
    }

    public void b0(boolean z) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("multi_sub_screen_first_guide_flag", z);
        edit.apply();
    }

    public HashMap c() {
        return q("close_by_endurance_mode");
    }

    public void c0(String str, String str2, long j2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putLong(str + "_" + str2 + "_last_close_time", j2);
        edit.apply();
        GaLog.e("SharedPreferencesUtil", "set " + str + " pluginName " + str2 + " last close time to " + j2);
    }

    public String d() {
        return f("cc_custom_pips", null);
    }

    public void d0(String str, String str2, long j2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putLong(str + "_" + str2 + "_last_usage_time", j2);
        edit.apply();
        GaLog.e("SharedPreferencesUtil", "set " + str + " " + str2 + " last usage time to " + j2);
    }

    public String e(String str) {
        return y().getString(str + "_custome_tile_list", null);
    }

    public void e0(String str, String str2, int i2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putInt(str + "_" + str2 + "_usage_count", i2);
        edit.apply();
        GaLog.e("SharedPreferencesUtil", "set " + str + " " + str2 + " usage count to " + i2);
    }

    public String f(String str, String str2) {
        return y().getString(str, str2);
    }

    public void f0(boolean z) {
        SharedPreferences.Editor edit = y().edit();
        edit.putBoolean("screen_saver_first_toast", z);
        edit.apply();
    }

    public String g() {
        return f("cc_custom_tiles", null);
    }

    public void g0(String str, int i2) {
        GaLog.e("SharedPreferencesUtil", "set " + str + " sortMode " + i2);
        SharedPreferences.Editor edit = y().edit();
        edit.putInt(str + "_sort_mode", i2);
        edit.apply();
    }

    @VisibleForTesting
    public int getFpsData(String str) {
        return y().getInt("custom_defined_fps_" + str, 0);
    }

    public int h() {
        return y().getInt("game_ratio_version", 1);
    }

    public void h0(String str, String str2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public boolean i() {
        if (!this.f17050c) {
            this.f17050c = y().getBoolean("game_reminder_power_off_alarm_permission_flag", false);
        }
        return this.f17050c;
    }

    public void i0(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        y().unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public boolean j() {
        return y().getBoolean("host_performance_panel_enable", true);
    }

    public int l(String str, int i2) {
        return y().getInt(str, i2);
    }

    public boolean m(String str) {
        return y().getBoolean("first_click_one_keylink_" + str, true);
    }

    public boolean n(String str) {
        return y().getBoolean("first_click_range_line_" + str, true);
    }

    public boolean o(String str) {
        return y().getBoolean("first_click_sight_assist_" + str, true);
    }

    public boolean p(String str) {
        return y().getBoolean("first_click_vibrate_" + str, true);
    }

    public HashMap q(String str) {
        String str2;
        HashMap hashMap = new HashMap();
        String f2 = f(str, null);
        if (f2 == null) {
            return hashMap;
        }
        for (String str3 : f2.split(",")) {
            String[] split = str3.split(":");
            String str4 = split[0];
            if (str4 != null && (str2 = split[1]) != null) {
                hashMap.put(str4, str2);
            }
        }
        return hashMap;
    }

    public String r() {
        return y().getString("multi_sub_screen_connected_device_id", "");
    }

    public boolean s() {
        if (this.f17051d) {
            this.f17051d = y().getBoolean("multi_sub_screen_first_guide_flag", true);
        }
        return this.f17051d;
    }

    @VisibleForTesting
    public void setFpsData(String str, int i2) {
        SharedPreferences.Editor edit = y().edit();
        edit.putInt("custom_defined_fps_" + str, i2);
        edit.apply();
    }

    public int t(String str) {
        return y().getInt(str + "_permission_denied_times", 0);
    }

    public long u(String str, String str2) {
        return y().getLong(str + "_" + str2 + "_last_close_time", 0L);
    }

    public long v(String str, String str2) {
        return y().getLong(str + "_" + str2 + "_last_usage_time", 0L);
    }

    public int w(String str, String str2) {
        return y().getInt(str + "_" + str2 + "_usage_count", 0);
    }

    public boolean x() {
        return y().getBoolean("screen_saver_first_toast", false);
    }

    public int z(String str) {
        return y().getInt(str + "_sort_mode", 0);
    }
}
