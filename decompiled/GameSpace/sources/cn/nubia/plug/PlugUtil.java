package cn.nubia.plug;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.Log;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.gamecontrolpanel.utils.Utils;
import cn.nubia.gamelauncher.util.CommonUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;

/* loaded from: classes.dex */
public class PlugUtil {
    private static final String FD_SHOCK_LOCAL_DRAWABLE_NAME = "fd_shock_drawable";
    private static final String HUNTING_MODE_LOCAL_DRAWABLE_NAME = "hunting_mode_pre";
    private static final int KEY_VALUE_SIZE_VERIFY = 2;
    private static final String TAG = "PlugUtil";
    private static final String ZTE_FEATURE_AI_GAME_PREDICTION = "ZTE_FEATURE_AI_GAME_PREDICTION";
    private static final String ZTE_FEATURE_AI_SPEAKER = "ZTE_FEATURE_AI_SPEAKER";
    private static final String ZTE_FEATURE_BASE_GAME_PLUGIN_GAME = "ZTE_FEATURE_BASE_GAME_PLUGIN_GAME";
    private static final String ZTE_FEATURE_DISPLAY_MAGIC = "ZTE_FEATURE_DISPLAY_MAGIC";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_4D_VIBRATE = "ZTE_FEATURE_GAMEASSIST_PLUGIN_4D_VIBRATE";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_TRIGGER = "ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_TRIGGER";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_BIABLO = "ZTE_FEATURE_GAMEASSIST_PLUGIN_BIABLO";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_CARD_ASSIST = "ZTE_FEATURE_GAMEASSIST_PLUGIN_CARD_ASSIST";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_CHAT_ASSIST = "ZTE_FEATURE_GAMEASSIST_PLUGIN_CHAT_ASSIST";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_DATA_PANEL = "ZTE_FEATURE_GAMEASSIST_PLUGIN_DATA_PANEL";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_FIXEDLOOK = "ZTE_FEATURE_GAMEASSIST_PLUGIN_FIXEDLOOK";
    private static final String ZTE_FEATURE_GAMEASSIST_PLUGIN_GAME_RATIO = "ZTE_FEATURE_GAMEASSIST_PLUGIN_GAME_RATIO";
    private static final String ZTE_FEATURE_GAMEASSIST_VOICE_CONTROLLER = "ZTE_FEATURE_GAMEASSIST_VOICE_CONTROLLER";
    private static final String ZTE_FEATURE_GAME_AI_TIPS = "ZTE_FEATURE_GAME_AI_TIPS";
    private static final String ZTE_FEATURE_GAME_DTS_EQ_FLOAT = "ZTE_FEATURE_GAME_DTS_EQ_FLOAT";
    private static final String ZTE_FEATURE_GAME_NEO_TRANSLATE = "ZTE_FEATURE_GAME_NEO_TRANSLATE";
    private static final String ZTE_FEATURE_GAME_PLUGIN_COUNTER = "ZTE_FEATURE_GAME_PLUGIN_COUNTER";
    private static final String ZTE_FEATURE_GAME_SOUND_PROBE = "ZTE_FEATURE_GAME_SOUND_PROBE";
    private static final String ZTE_FEATURE_GFRC = "ZTE_FEATURE_GFRC";
    private static final String ZTE_FEATURE_KEYMAP_SENSITIVITY_WHEEL_DISC = "ZTE_FEATURE_KEYMAP_SENSITIVITY_WHEEL_DISC";
    private static final String ZTE_FEATURE_PACKAGE_PLUGIN_VIBRATE = "ZTE_FEATURE_PACKAGE_PLUGIN_VIBRATE";
    private static final String ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY = "ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY";
    private static final String ZTE_FEATURE_SENSOR_OPERATION_TOUCH = "ZTE_FEATURE_SENSOR_OPERATION_TOUCH";
    private static final String[][] gradientArray;
    private static final boolean mIsInterVersion;
    private static final boolean mIsNeoTranslate;
    private static final boolean mIsQcomPlatform;
    private static final Map<Integer, int[]> mSupportGameMap;
    private static final Map<String, Integer> preAllSupportGameMap;
    private static final ArrayMap<String, int[]> preRatingsMap;
    private static final ArrayMap<String, String[]> preSourceIdMap;

    static {
        int i;
        char c;
        char c2;
        char c3;
        int[] iArr;
        int[] iArr2;
        ArrayMap<String, String[]> arrayMap = new ArrayMap<>();
        preSourceIdMap = arrayMap;
        gradientArray = new String[][]{new String[]{"#3D8BFF", "#316FCC"}, new String[]{"#B83DFF", "#9331CC"}, new String[]{"#24C778", "#1C9C5E"}};
        ArrayMap<String, int[]> arrayMap2 = new ArrayMap<>();
        preRatingsMap = arrayMap2;
        HashMap hashMap = new HashMap();
        mSupportGameMap = hashMap;
        HashMap hashMap2 = new HashMap();
        preAllSupportGameMap = hashMap2;
        boolean isInternalVersion = CommonUtil.isInternalVersion();
        mIsInterVersion = isInternalVersion;
        mIsQcomPlatform = Utils.isQcomPlatform();
        mIsNeoTranslate = FeatureUtil.getBoolean(ZTE_FEATURE_GAME_NEO_TRANSLATE, false).booleanValue();
        arrayMap.put(Constant.HUNTING_MODE_HELP_TAG, new String[]{HUNTING_MODE_LOCAL_DRAWABLE_NAME, "hunting_mode_drawable_un"});
        arrayMap.put(Constant.FD_SHOCK_HELP_TAG, new String[]{FD_SHOCK_LOCAL_DRAWABLE_NAME, "fd_shock_drawable_un"});
        arrayMap.put(Constant.SIGHT_HELP_TAG, new String[]{"sight_help_video", "sight_help_drawable_un"});
        arrayMap.put(Constant.CHAT_HELP_TAG, new String[]{"chat_help_video", "chat_help_video"});
        arrayMap.put(Constant.CONNECT_POINT_HELP_TAG, getConnectPointHelpSource());
        arrayMap.put(Constant.LONG_PRESS_HELP_TAG, getLongPressHelpSource());
        arrayMap.put(Constant.DECTED_MODE_TAG, new String[]{"detect_mode_video", "detect_mode_drawable_un"});
        arrayMap.put(Constant.SOUND_EQUALIZER_TAG, getSoundEqualizerSource());
        arrayMap.put(Constant.FREE_CHANGE_KEY_TAG, new String[]{"free_change_key_video", "free_change_key_drawable_un"});
        arrayMap.put(Constant.FAST_STOP_WATCH_TAG, new String[]{"fast_stop_watch_video", "fast_stop_watch_drawable_un"});
        arrayMap.put(Constant.RANGE_LINE_TAG, new String[]{"range_line_video", "range_line_drawable_un"});
        arrayMap.put(Constant.DATA_PANEL_TAG, new String[]{"data_panel_video", "data_panel_drawable_un"});
        arrayMap.put(Constant.DESTRUCTION_MODE_TAG, getDestructionModeSource());
        arrayMap.put(Constant.CARD_ASSIST_TAG, new String[]{"card_assist_video", "card_assist_drawable_un"});
        arrayMap.put(Constant.AI_TRIGGER_TAG, new String[]{"ai_trigger_video", "ai_trigger_drawable_un"});
        arrayMap.put(Constant.SUPERIOR_PIC_QUALITY_TAG, new String[]{"superior_pic_quality_video", "superior_pic_quality_drawable_un"});
        arrayMap.put(Constant.FRAME_EXTRACT_TAG, new String[]{"frame_extract_video", "frame_extract_drawable_un"});
        arrayMap.put(Constant.HIGH_SENS_ROULETTE_TAG, new String[]{"high_sens_roulette_video", "high_sens_roulette_drawable_un"});
        arrayMap.put(Constant.FREE_DISPLAY_TAG, new String[]{"free_display_video", "free_display_drawable_un"});
        arrayMap.put(Constant.SOUND_PROBE_TAG, new String[]{"sound_probe_video", "sound_probe_drawable_un"});
        arrayMap.put(Constant.MOTION_SENSING_TAG, new String[]{"motion_sensing_video", "motion_sensing_drawable_un"});
        arrayMap.put(Constant.GAME_COUNTER_TAG, new String[]{"game_counter_video", "game_counter_drawable_un"});
        arrayMap.put(Constant.MORA_SPEAKER_TAG, new String[]{"mora_speaker_video", "mora_speaker_drawable_un"});
        arrayMap.put(Constant.WIN_RATE_FORECAST_TAG, new String[]{"win_rate_forecast_video", "win_rate_forecast_drawable_un"});
        arrayMap.put(Constant.VOICE_CONTROL_TAG, new String[]{"voice_control_video", "voice_control_drawable_un"});
        arrayMap.put(Constant.TACTICAL_ADVICE_TAG, new String[]{"tactical_advice_video", "tactical_coach_drawable_un"});
        arrayMap2.put(Constant.RANGE_LINE_TAG, new int[]{3, 1, 2, 1});
        arrayMap2.put(Constant.FAST_STOP_WATCH_TAG, new int[]{3, 2, 2, 2});
        arrayMap2.put(Constant.FREE_CHANGE_KEY_TAG, new int[]{2, 3, 2, 2});
        arrayMap2.put(Constant.SOUND_EQUALIZER_TAG, new int[]{2, 1, 2, 2});
        arrayMap2.put(Constant.DECTED_MODE_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.LONG_PRESS_HELP_TAG, new int[]{2, 3, 2, 1});
        arrayMap2.put(Constant.CONNECT_POINT_HELP_TAG, new int[]{2, 3, 2, 1});
        arrayMap2.put(Constant.CHAT_HELP_TAG, new int[]{3, 1, 2, 1});
        arrayMap2.put(Constant.SIGHT_HELP_TAG, new int[]{1, 2, 2, 1});
        arrayMap2.put(Constant.FD_SHOCK_HELP_TAG, new int[]{1, 1, 1, 1});
        arrayMap2.put(Constant.HUNTING_MODE_HELP_TAG, new int[]{3, 1, 2, 1});
        arrayMap2.put(Constant.DATA_PANEL_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.DESTRUCTION_MODE_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.AI_TRIGGER_TAG, new int[]{3, 1, 3, 2});
        arrayMap2.put(Constant.CARD_ASSIST_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.SUPERIOR_PIC_QUALITY_TAG, new int[]{1, 1, 3, 1});
        arrayMap2.put(Constant.FRAME_EXTRACT_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.HIGH_SENS_ROULETTE_TAG, new int[]{1, 3, 1, 3});
        arrayMap2.put(Constant.FREE_DISPLAY_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.SOUND_PROBE_TAG, new int[]{3, 3, 2, 1});
        arrayMap2.put(Constant.MOTION_SENSING_TAG, new int[]{1, 3, 1, 3});
        arrayMap2.put(Constant.GAME_COUNTER_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.MORA_SPEAKER_TAG, new int[]{2, 1, 2, 1});
        arrayMap2.put(Constant.WIN_RATE_FORECAST_TAG, new int[]{3, 1, 1, 1});
        arrayMap2.put(Constant.VOICE_CONTROL_TAG, new int[]{1, 3, 1, 3});
        arrayMap2.put(Constant.TACTICAL_ADVICE_TAG, new int[]{3, 1, 3, 1});
        hashMap.put(3, new int[]{R.string.support_partial_game});
        hashMap.put(5, new int[]{R.string.rpg});
        hashMap.put(6, new int[]{R.string.fps, R.string.act});
        hashMap.put(14, new int[]{R.string.support_game_card_draw});
        hashMap.put(15, getSuperiorPicQualityGames());
        hashMap.put(17, new int[]{R.string.fps, R.string.moba});
        hashMap.put(18, new int[]{R.string.support_partial_game});
        hashMap.put(20, new int[]{R.string.support_partial_game});
        hashMap.put(19, new int[]{R.string.pubgmhd});
        hashMap.put(10, getSoundEqualizerGames());
        if (isInternalVersion) {
            i = 3;
            c2 = 0;
            c3 = 1;
            c = 2;
            iArr = new int[]{R.string.mlbb, R.string.free_fire, R.string.support_and_more_game};
        } else {
            i = 3;
            c = 2;
            c2 = 0;
            c3 = 1;
            iArr = new int[]{R.string.sgame, R.string.lolm, R.string.support_and_more_game};
        }
        hashMap.put(23, iArr);
        if (isInternalVersion) {
            iArr2 = new int[i];
            iArr2[c2] = R.string.pubg;
            iArr2[c3] = R.string.mlbb;
            iArr2[c] = R.string.support_and_more_game;
        } else {
            iArr2 = new int[i];
            iArr2[c2] = R.string.sgame;
            iArr2[c3] = R.string.lolm;
            iArr2[c] = R.string.support_and_more_game;
        }
        hashMap.put(25, iArr2);
        hashMap2.put("all", Integer.valueOf(R.string.all_game));
        hashMap2.put(Constant.GAME_TYPE_PARTIAL, Integer.valueOf(R.string.support_partial_game));
        hashMap2.put(Constant.GAME_TAG_PUBGMHD, Integer.valueOf(R.string.pubgmhd));
        hashMap2.put(Constant.GAME_TAG_HYXD, Integer.valueOf(R.string.hyxd));
        hashMap2.put(Constant.GAME_TAG_ASPHALT, Integer.valueOf(R.string.asphalt));
        hashMap2.put(Constant.GAME_TAG_PUBG, Integer.valueOf(R.string.pubg));
        hashMap2.put(Constant.GAME_TAG_QQSPEED, Integer.valueOf(R.string.speedmobile));
        hashMap2.put(Constant.GAME_TAG_SGAME, Integer.valueOf(R.string.sgame));
        hashMap2.put(Constant.GAME_TAG_CF, Integer.valueOf(R.string.cf));
        hashMap2.put(Constant.GAME_TAG_COD, Integer.valueOf(R.string.cod));
        hashMap2.put(Constant.GAME_TAG_AF, Integer.valueOf(R.string.af));
        hashMap2.put(Constant.GAME_TAG_GENSHIN, Integer.valueOf(R.string.genshin));
        hashMap2.put(Constant.GAME_TYPE_MOBA, Integer.valueOf(R.string.moba));
        hashMap2.put(Constant.GAME_TYPE_ACT, Integer.valueOf(R.string.act));
        hashMap2.put(Constant.GAME_TYPE_RPG, Integer.valueOf(R.string.rpg));
        hashMap2.put(Constant.GAME_TYPE_FPS, Integer.valueOf(R.string.fps));
        hashMap2.put(Constant.GAME_TYPE_CARDDRAW, Integer.valueOf(R.string.support_game_card_draw));
        hashMap2.put(Constant.GAME_TYPE_STARRAIL, Integer.valueOf(R.string.starrail));
        hashMap2.put(Constant.GAME_TYPE_MORE, Integer.valueOf(R.string.support_and_more_game));
        hashMap2.put(Constant.GAME_TAG_LOLM, Integer.valueOf(R.string.lolm));
    }

    public static int calcDetailAreaWidth(Context context) {
        Resources resources = context.getResources();
        float f = Resources.getSystem().getDisplayMetrics().widthPixels;
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.plug_detail_layout_padding_start);
        float dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.plug_detail_layout_padding_end);
        Log.i(TAG, "calcDetailAreaWidth: " + f + ", " + dimensionPixelSize + ", " + dimensionPixelSize2);
        return Math.round((((f * 11.0f) / 31.0f) - dimensionPixelSize) - dimensionPixelSize2);
    }

    public static int calcDimensionWidth(Context context) {
        Resources resources = context.getResources();
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.plug_dimension_title_width);
        float dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.plug_dimension_title_left);
        float dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.plug_dimension_bar_left);
        float dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.plug_dimension_rect_height);
        float dimensionPixelSize5 = resources.getDimensionPixelSize(R.dimen.plug_dimension_bar_distance);
        Log.i(TAG, "calcDimensionWidth Rect: " + dimensionPixelSize4 + ", " + dimensionPixelSize5);
        float f = (((dimensionPixelSize4 * 4.0f) / 6.0f) * 3.0f) + (dimensionPixelSize5 * 2.0f);
        Log.i(TAG, "calcDimensionWidth: " + dimensionPixelSize + ", " + dimensionPixelSize2 + ", " + dimensionPixelSize3 + ", " + f);
        return Math.round((dimensionPixelSize * 4.0f) + (dimensionPixelSize2 * 3.0f) + (dimensionPixelSize3 * 4.0f) + (f * 4.0f));
    }

    private static boolean contains679() {
        return Build.MODEL.contains("NX679");
    }

    private static boolean contains709() {
        return Build.MODEL.contains("NX709");
    }

    private static boolean getBooleanFeature(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return FeatureUtil.getBoolean(str, false).booleanValue();
    }

    private static String[] getConnectPointHelpSource() {
        return mIsQcomPlatform ? new String[]{"connect_point_help_video", "connect_point_help_drawable_un"} : new String[]{"connect_point_help_video_zte", "connect_point_help_drawable_un"};
    }

    private static String[] getDestructionModeSource() {
        return hasGameTouchKey() ? new String[]{"destruction_mode_video", "destruction_mode_drawable_un"} : new String[]{"destruction_mode_video_zte", "destruction_mode_drawable_zte_un"};
    }

    private static String[] getLongPressHelpSource() {
        return mIsQcomPlatform ? new String[]{"long_press_help_video", "long_press_help_drawable_un"} : new String[]{"long_press_help_video_zte", "long_press_help_drawable_un"};
    }

    public static String[] getNormalColors() {
        return new String[]{"#393E52", "#393E52"};
    }

    public static List<PlugData> getPlugList(Context context) {
        ArrayList arrayList = new ArrayList();
        loadConfig();
        if (mIsQcomPlatform && getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_BIABLO)) {
            arrayList.add(new PlugData(new int[]{R.drawable.destruction_mode_h_select, R.drawable.destruction_mode_h_normal}, R.string.destruction_mode_title, R.string.destruction_mode_content, R.string.destruction_mode_track, getPlugSupportGames(12), new int[]{R.string.device_669s}, getPreRatings(Constant.DESTRUCTION_MODE_TAG), getPreId(context, Constant.DESTRUCTION_MODE_TAG), Constant.DESTRUCTION_MODE_TAG));
        }
        arrayList.add(new PlugData(new int[]{R.drawable.sound_equalizer_h_select, R.drawable.sound_equalizer_h_normal}, R.string.sound_equalizer_title, R.string.sound_equalizer_content, R.string.sound_equalizer_track, getPlugSupportGames(10), new int[]{R.string.device_669s}, getPreRatings(Constant.SOUND_EQUALIZER_TAG), getPreId(context, Constant.SOUND_EQUALIZER_TAG), Constant.SOUND_EQUALIZER_TAG));
        arrayList.add(new PlugData(new int[]{R.drawable.free_change_key_h_select, R.drawable.free_change_key_h_normal}, R.string.free_change_key_title, R.string.free_change_key_content, R.string.free_change_key_track, getPlugSupportGames(9), new int[]{R.string.device_669s}, getPreRatings(Constant.FREE_CHANGE_KEY_TAG), getPreId(context, Constant.FREE_CHANGE_KEY_TAG), Constant.FREE_CHANGE_KEY_TAG));
        arrayList.add(new PlugData(new int[]{R.drawable.detect_mode_h_select, R.drawable.detect_mode_h_normal}, R.string.detect_mode_title, R.string.detect_mode_content, R.string.detect_mode_track, getPlugSupportGames(8), new int[]{R.string.device_669s}, getPreRatings(Constant.DECTED_MODE_TAG), getPreId(context, Constant.DECTED_MODE_TAG), Constant.DECTED_MODE_TAG));
        if (isSupportChatHelp()) {
            arrayList.add(new PlugData(new int[]{R.drawable.chat_help_h_select, R.drawable.chat_help_h_normal}, R.string.chat_help_title, R.string.chat_help_content, R.string.chat_help_track, getPlugSupportGames(7), new int[]{R.string.device_669s}, getPreRatings(Constant.CHAT_HELP_TAG), getPreId(context, Constant.CHAT_HELP_TAG), Constant.CHAT_HELP_TAG));
        }
        if (hasGameTouchKey()) {
            arrayList.add(new PlugData(new int[]{R.drawable.connect_point_help_h_select, R.drawable.connect_point_help_h_normal}, R.string.connect_point_help_title, R.string.connect_point_help_content, R.string.connect_point_help_track, getPlugSupportGames(6), new int[]{R.string.device_669s}, getPreRatings(Constant.CONNECT_POINT_HELP_TAG), getPreId(context, Constant.CONNECT_POINT_HELP_TAG), Constant.CONNECT_POINT_HELP_TAG));
        }
        if (hasGameTouchKey()) {
            arrayList.add(new PlugData(new int[]{R.drawable.long_press_help_h_select, R.drawable.long_press_help_h_normal}, R.string.long_press_help_title, R.string.long_press_help_content, R.string.long_press_help_track, getPlugSupportGames(5), new int[]{R.string.device_669s}, getPreRatings(Constant.LONG_PRESS_HELP_TAG), getPreId(context, Constant.LONG_PRESS_HELP_TAG), Constant.LONG_PRESS_HELP_TAG));
        }
        arrayList.add(new PlugData(new int[]{R.drawable.hunting_mode_h_select, R.drawable.hunting_mode_h_normal}, R.string.hunting_mode_title, R.string.hunting_mode_content, R.string.hunting_mode_track, getPlugSupportGames(4), new int[]{R.string.device_669s}, getPreRatings(Constant.HUNTING_MODE_HELP_TAG), getPreId(context, Constant.HUNTING_MODE_HELP_TAG), false, Constant.HUNTING_MODE_HELP_TAG));
        if (isSupportFDShock()) {
            arrayList.add(new PlugData(new int[]{R.drawable.fd_shock_h_select, R.drawable.fd_shock_h_normal}, R.string.fd_shock_title, R.string.fd_shock_content, R.string.fd_shock_track, getPlugSupportGames(3), new int[]{R.string.device_669s}, getPreRatings(Constant.FD_SHOCK_HELP_TAG), getPreId(context, Constant.FD_SHOCK_HELP_TAG), false, Constant.FD_SHOCK_HELP_TAG));
        }
        arrayList.add(new PlugData(new int[]{R.drawable.sight_help_h_select, R.drawable.sight_help_h_normal}, R.string.sight_help_title, R.string.sight_help_content, R.string.sight_help_track, getPlugSupportGames(2), new int[]{R.string.device_669s}, getPreRatings(Constant.SIGHT_HELP_TAG), getPreId(context, Constant.SIGHT_HELP_TAG), Constant.SIGHT_HELP_TAG));
        arrayList.add(new PlugData(new int[]{R.drawable.fast_stop_watch_h_select, R.drawable.fast_stop_watch_h_normal}, R.string.fast_stop_watch_title, R.string.fast_stop_watch_content, R.string.fast_stop_watch_track, getPlugSupportGames(1), new int[]{R.string.device_669s}, getPreRatings(Constant.FAST_STOP_WATCH_TAG), getPreId(context, Constant.FAST_STOP_WATCH_TAG), Constant.FAST_STOP_WATCH_TAG));
        arrayList.add(new PlugData(new int[]{R.drawable.range_line_h_select, R.drawable.range_line_h_normal}, R.string.range_line_title, R.string.range_line_content, R.string.range_line_track, getPlugSupportGames(0), new int[]{R.string.device_669s}, getPreRatings(Constant.RANGE_LINE_TAG), getPreId(context, Constant.RANGE_LINE_TAG), Constant.RANGE_LINE_TAG));
        if (getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_DATA_PANEL)) {
            arrayList.add(new PlugData(new int[]{R.drawable.data_panel_h_select, R.drawable.data_panel_h_normal}, R.string.data_panel_title, R.string.data_panel_content, R.string.data_panel_track, getPlugSupportGames(11), new int[]{R.string.device_669s}, getPreRatings(Constant.DATA_PANEL_TAG), getPreId(context, Constant.DATA_PANEL_TAG), Constant.DATA_PANEL_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_CARD_ASSIST)) {
            arrayList.add(new PlugData(new int[]{R.drawable.card_assist_h_select, R.drawable.card_assist_h_normal}, R.string.card_assist_title, R.string.card_assist_content, R.string.card_assist_track, getPlugSupportGames(14), new int[]{R.string.device_669s}, getPreRatings(Constant.CARD_ASSIST_TAG), getPreId(context, Constant.CARD_ASSIST_TAG), Constant.CARD_ASSIST_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_AI_TRIGGER)) {
            arrayList.add(new PlugData(new int[]{R.drawable.ai_trigger_h_select, R.drawable.ai_trigger_h_normal}, R.string.ai_trigger_title, R.string.ai_trigger_content, R.string.ai_trigger_track, getPlugSupportGames(13), new int[]{R.string.device_669s}, getPreRatings(Constant.AI_TRIGGER_TAG), getPreId(context, Constant.AI_TRIGGER_TAG), Constant.AI_TRIGGER_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GFRC) || getBooleanFeature(ZTE_FEATURE_DISPLAY_MAGIC)) {
            arrayList.add(new PlugData(new int[]{R.drawable.superior_pic_quality_h_select, R.drawable.superior_pic_quality_h_normal}, R.string.superior_pic_quality_title, R.string.superior_pic_quality_content, R.string.superior_pic_quality_track, getPlugSupportGames(15), new int[]{R.string.device_669s}, getPreRatings(Constant.SUPERIOR_PIC_QUALITY_TAG), getPreId(context, Constant.SUPERIOR_PIC_QUALITY_TAG), Constant.SUPERIOR_PIC_QUALITY_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_FIXEDLOOK)) {
            arrayList.add(new PlugData(new int[]{R.drawable.frame_extract_h_select, R.drawable.frame_extract_h_normal}, R.string.frame_extract_title, R.string.frame_extract_content, R.string.frame_extract_track, getPlugSupportGames(16), new int[]{R.string.device_669s}, getPreRatings(Constant.FRAME_EXTRACT_TAG), getPreId(context, Constant.FRAME_EXTRACT_TAG), Constant.FRAME_EXTRACT_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_KEYMAP_SENSITIVITY_WHEEL_DISC)) {
            arrayList.add(new PlugData(new int[]{R.drawable.high_sens_roulette_h_select, R.drawable.high_sens_roulette_h_normal}, R.string.high_sens_roulette_title, R.string.high_sens_roulette_content, R.string.high_sens_roulette_track, getPlugSupportGames(17), new int[]{R.string.device_669s}, getPreRatings(Constant.HIGH_SENS_ROULETTE_TAG), getPreId(context, Constant.HIGH_SENS_ROULETTE_TAG), Constant.HIGH_SENS_ROULETTE_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_GAME_RATIO)) {
            arrayList.add(new PlugData(new int[]{R.drawable.free_display_h_select, R.drawable.free_display_h_normal}, R.string.free_display_title, R.string.free_display_content, R.string.free_display_track, getPlugSupportGames(18), new int[]{R.string.device_669s}, getPreRatings(Constant.FREE_DISPLAY_TAG), getPreId(context, Constant.FREE_DISPLAY_TAG), Constant.FREE_DISPLAY_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAME_SOUND_PROBE)) {
            arrayList.add(new PlugData(new int[]{R.drawable.sound_probe_h_select, R.drawable.sound_probe_h_normal}, R.string.sound_probe_title, R.string.sound_probe_content, R.string.sound_probe_track, getPlugSupportGames(19), new int[]{R.string.device_669s}, getPreRatings(Constant.SOUND_PROBE_TAG), getPreId(context, Constant.SOUND_PROBE_TAG), Constant.SOUND_PROBE_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_SENSOR_OPERATION_TOUCH)) {
            arrayList.add(new PlugData(new int[]{R.drawable.motion_sensing_h_select, R.drawable.motion_sensing_h_normal}, R.string.motion_sensing_title, R.string.motion_sensing_content, R.string.motion_sensing_track, getPlugSupportGames(20), new int[]{R.string.device_669s}, getPreRatings(Constant.MOTION_SENSING_TAG), getPreId(context, Constant.MOTION_SENSING_TAG), Constant.MOTION_SENSING_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAME_PLUGIN_COUNTER)) {
            arrayList.add(new PlugData(new int[]{R.drawable.game_counter_h_select, R.drawable.game_counter_h_normal}, R.string.game_counter_title, R.string.game_counter_content, R.string.game_counter_track, getPlugSupportGames(21), new int[]{R.string.device_669s}, getPreRatings(Constant.GAME_COUNTER_TAG), getPreId(context, Constant.GAME_COUNTER_TAG), Constant.GAME_COUNTER_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_AI_SPEAKER)) {
            arrayList.add(new PlugData(new int[]{R.drawable.mora_speaker_h_select, R.drawable.mora_speaker_h_normal}, getPlugTitleResId(22), R.string.mora_speaker_content, R.string.mora_speaker_track, getPlugSupportGames(22), new int[]{R.string.device_669s}, getPreRatings(Constant.MORA_SPEAKER_TAG), getPreId(context, Constant.MORA_SPEAKER_TAG), Constant.MORA_SPEAKER_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_AI_GAME_PREDICTION)) {
            arrayList.add(new PlugData(new int[]{R.drawable.win_rate_forecast_h_select, R.drawable.win_rate_forecast_h_normal}, R.string.win_rate_forecast_title, R.string.win_rate_forecast_content, R.string.win_rate_forecast_track, getPlugSupportGames(23), new int[]{R.string.device_669s}, getPreRatings(Constant.WIN_RATE_FORECAST_TAG), getPreId(context, Constant.WIN_RATE_FORECAST_TAG), Constant.WIN_RATE_FORECAST_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAMEASSIST_VOICE_CONTROLLER)) {
            arrayList.add(new PlugData(new int[]{R.drawable.voice_control_h_select, R.drawable.voice_control_h_normal}, R.string.voice_control_title, R.string.voice_control_content, R.string.voice_control_track, getPlugSupportGames(24), new int[]{R.string.device_669s}, getPreRatings(Constant.VOICE_CONTROL_TAG), getPreId(context, Constant.VOICE_CONTROL_TAG), Constant.VOICE_CONTROL_TAG));
        }
        if (getBooleanFeature(ZTE_FEATURE_GAME_AI_TIPS)) {
            arrayList.add(new PlugData(new int[]{R.drawable.tactical_advice_h_select, R.drawable.tactical_advice_h_normal}, getPlugTitleResId(25), R.string.tactical_advice_content, R.string.tactical_advice_track, getPlugSupportGames(25), new int[]{R.string.device_669s}, getPreRatings(Constant.TACTICAL_ADVICE_TAG), getPreId(context, Constant.TACTICAL_ADVICE_TAG), Constant.TACTICAL_ADVICE_TAG));
        }
        Collections.reverse(arrayList);
        return arrayList;
    }

    private static int getPlugResourceId(Context context, String str, boolean z) {
        return context.getResources().getIdentifier(str, z ? "raw" : "drawable", context.getPackageName());
    }

    private static int[] getPlugSupportGames(int i) {
        return mSupportGameMap.getOrDefault(Integer.valueOf(i), new int[]{R.string.all_game});
    }

    private static int getPlugTitleResId(int i) {
        if (22 == i) {
            return mIsNeoTranslate ? R.string.mora_speaker_title_neo : R.string.mora_speaker_title;
        }
        if (25 == i) {
            return mIsNeoTranslate ? R.string.tactical_coach_title_neo : R.string.tactical_coach_title;
        }
        return -1;
    }

    private static int getPreId(Context context, String str) {
        if (mIsInterVersion) {
            return getPlugResourceId(context, preSourceIdMap.get(str)[1], false);
        }
        String str2 = preSourceIdMap.get(str)[0];
        return getPlugResourceId(context, str2, isLocalVideo(str2));
    }

    private static int[] getPreRatings(String str) {
        return preRatingsMap.get(str);
    }

    public static String[] getSelectorColors(String str, int i) {
        return gradientArray[i % 3];
    }

    private static int[] getSoundEqualizerGames() {
        return isNewSoundEqualizer() ? new int[]{R.string.dtsenabled} : new int[]{R.string.all_game};
    }

    private static String[] getSoundEqualizerSource() {
        return isNewSoundEqualizer() ? new String[]{"sound_equalizer_new_video", "sound_equalizer_new_drawable_un"} : new String[]{"sound_equalizer_video", "sound_equalizer_drawable_un"};
    }

    private static int[] getSuperiorPicQualityGames() {
        return getBooleanFeature(ZTE_FEATURE_GFRC) ? new int[]{R.string.support_partial_game} : new int[]{R.string.genshin};
    }

    private static void handleFeatureGameConfig(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String[] split = str.split(",");
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            String[] split2 = str2.split(":");
            if (split2.length == 2 && isNumeric(split2[0]) && !TextUtils.isEmpty(split2[1])) {
                int parseInt = Integer.parseInt(split2[0]);
                String[] split3 = split2[1].split("_");
                if (split3 != null && split3.length != 0) {
                    arrayList.clear();
                    for (String str3 : split3) {
                        Map<String, Integer> map = preAllSupportGameMap;
                        if (map.containsKey(str3)) {
                            arrayList.add(map.get(str3));
                        }
                    }
                    if (!arrayList.isEmpty()) {
                        mSupportGameMap.put(Integer.valueOf(parseInt), arrayList.stream().mapToInt(new ToIntFunction() { // from class: cn.nubia.plug.PlugUtil$$ExternalSyntheticLambda0
                            @Override // java.util.function.ToIntFunction
                            public final int applyAsInt(Object obj) {
                                int intValue;
                                intValue = ((Integer) obj).intValue();
                                return intValue;
                            }
                        }).toArray());
                    }
                }
            }
        }
    }

    private static boolean hasGameTouchKey() {
        return isRedMagic7() || getBooleanFeature("ZTE_FEATURE_REDMAGIC_TOUCH_GAMEKEY");
    }

    private static boolean is666() {
        return "NX666J".equals(Build.MODEL);
    }

    private static boolean is669() {
        return "NX669J".equals(Build.MODEL);
    }

    private static boolean is679() {
        return "NX679J".equals(Build.MODEL);
    }

    private static boolean is709() {
        return "NX709J".equals(Build.MODEL);
    }

    private static boolean isLocalVideo(String str) {
        return (HUNTING_MODE_LOCAL_DRAWABLE_NAME.equals(str) || FD_SHOCK_LOCAL_DRAWABLE_NAME.equals(str)) ? false : true;
    }

    private static boolean isNewSoundEqualizer() {
        return getBooleanFeature(ZTE_FEATURE_GAME_DTS_EQ_FLOAT);
    }

    public static boolean isNumeric(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("\\d+");
    }

    public static boolean isPreVideoType() {
        return !mIsInterVersion;
    }

    public static boolean isRedMagic7() {
        return contains679() || contains709();
    }

    public static boolean isSupportChatHelp() {
        if (mIsInterVersion || is679() || is709()) {
            return false;
        }
        return getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_CHAT_ASSIST);
    }

    private static boolean isSupportFDShock() {
        if (is666()) {
            return false;
        }
        String str = FeatureUtil.get(ZTE_FEATURE_PACKAGE_PLUGIN_VIBRATE, null);
        if (TextUtils.isEmpty(str)) {
            return getBooleanFeature(ZTE_FEATURE_GAMEASSIST_PLUGIN_4D_VIBRATE);
        }
        Log.i(TAG, "isSupportFDShock zFDShockSupportGames: " + str);
        return true;
    }

    private static void loadConfig() {
        String str = FeatureUtil.get(ZTE_FEATURE_BASE_GAME_PLUGIN_GAME, null);
        handleFeatureGameConfig(str);
        Log.i(TAG, "initFeatureConfigMap supportGameConfig: " + str);
    }

    public static void startPlugUnit(Context context) {
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.gamelauncher", Constant.PLUG_UNIT_CLASS_NAME);
        intent.addFlags(268435456);
        context.startActivity(intent);
    }
}
