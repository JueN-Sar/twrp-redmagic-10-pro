package cn.nubia.gameassist.plugin.sort;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import cn.nubia.gameassist.R;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class PluginInfo {

    /* renamed from: a, reason: collision with root package name */
    private String f7321a;

    /* renamed from: b, reason: collision with root package name */
    private Drawable f7322b;

    /* renamed from: c, reason: collision with root package name */
    private String f7323c;

    /* renamed from: d, reason: collision with root package name */
    private String f7324d;

    public PluginInfo(Context context, String str) {
        f(context, str);
    }

    public static String d(Context context, String str) {
        str.hashCode();
        switch (str) {
            case "range_line":
                return context.getString(R.string.plugin_icon_rangeline);
            case "super_resolution":
            case "super_resolution_old":
                return context.getString(R.string.plugin_label_super_resolution);
            case "sensor_operation":
                return context.getString(R.string.plugin_label_sensor_operation);
            case "ai_tip":
                return Build.VERSION.SDK_INT >= 36 ? context.getString(R.string.plugin_ai_tip_coach) : context.getString(R.string.plugin_ai_tip);
            case "ai_trigger":
                return context.getString(R.string.plugin_label_trigger);
            case "chat_assit":
                return context.getString(R.string.plugin_icon_chat);
            case "pleased_display":
                return context.getString(R.string.plugin_label_pleased_display);
            case "keylink":
                return context.getString(R.string.plugin_icon_keylink);
            case "game_prediction":
                return context.getString(R.string.plugin_game_predicition);
            case "help":
                return ZteFeature.isSupportDemi() ? context.getString(R.string.plugin_icon_demi_help) : context.getString(R.string.plugin_icon_help);
            case "timer":
                return context.getString(R.string.plugin_string_timer);
            case "mora_ai_speaker":
                return ZteFeature.isNeoProduct() ? context.getString(R.string.plugin_label_neo_ai_speaker) : ZteFeature.isSupportDemi() ? context.getString(R.string.plugin_label_demi_ai_speaker) : context.getString(R.string.plugin_label_ai_speaker);
            case "keyposition_assist":
                return context.getString(R.string.plugin_icon_keyposition);
            case "card_assist":
                return context.getString(R.string.plugin_label_card_assist);
            case "vibrate":
                return context.getString(R.string.plugin_icon_4d);
            case "ai_detect":
                return context.getString(R.string.plugin_label_ai_detect);
            case "operation_devices":
                return ZteFeature.isSupportPeripheralControl() ? context.getString(R.string.plugin_icon_peripheral_control) : context.getString(R.string.plugin_icon_operation);
            case "biablo_mode":
                return context.getString(R.string.nubia_game_performance_mode_diablo_title);
            case "counter":
                return context.getString(R.string.plugin_label_counter);
            case "voice_controller":
                return context.getString(R.string.plugin_label_voice_controller);
            case "sound_effect":
                return context.getString(R.string.plugin_icon_sound);
            case "redmagic_broadcast":
                return ZteFeature.isSupportDemi() ? context.getString(R.string.plugin_icon_demi_broadcast) : context.getString(R.string.plugin_icon_broadcast);
            case "combat_power":
                return context.getString(R.string.plugin_combat_power_title);
            case "high_sensitivity_wheel":
                return context.getString(R.string.plugin_label_high_wheel);
            case "investigation_mode":
                return context.getString(R.string.plugin_icon_investigate);
            case "gameshader":
            case "hunting_mode":
                return context.getString(R.string.plugin_icon_hunt);
            case "sight_assist":
                return context.getString(R.string.plugin_icon_sight);
            case "screen_extraction":
                return context.getString(R.string.plugin_icon_screen_extraction);
            default:
                return null;
        }
    }

    private void f(Context context, String str) {
        this.f7321a = str;
        str.hashCode();
        switch (str) {
            case "range_line":
                this.f7322b = context.getDrawable(R.drawable.plugin_rangeline_off);
                this.f7324d = context.getString(R.string.plugin_rangeline_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_rangeline);
                break;
            case "super_resolution":
            case "super_resolution_old":
                this.f7322b = context.getDrawable(R.drawable.plugin_super_resolution_off);
                this.f7324d = context.getString(R.string.plugin_label_super_resolution_introduction);
                this.f7323c = context.getString(R.string.plugin_label_super_resolution);
                break;
            case "sensor_operation":
                this.f7322b = context.getDrawable(R.drawable.plugin_sensor_operation_off);
                this.f7324d = context.getString(R.string.sensor_operation_introduction);
                this.f7323c = context.getString(R.string.plugin_label_sensor_operation);
                break;
            case "ai_tip":
                this.f7322b = context.getDrawable(R.drawable.plugin_ai_tip_off);
                this.f7324d = context.getString(R.string.plugin_ai_tip_introduction);
                if (Build.VERSION.SDK_INT < 36) {
                    this.f7323c = context.getString(R.string.plugin_ai_tip);
                    break;
                } else {
                    this.f7323c = context.getString(R.string.plugin_ai_tip_coach);
                    break;
                }
            case "ai_trigger":
                this.f7322b = context.getDrawable(R.drawable.plugin_trigger_off);
                this.f7324d = context.getString(R.string.plugin_trigger_introduction);
                this.f7323c = context.getString(R.string.plugin_label_trigger);
                break;
            case "chat_assit":
                this.f7322b = context.getDrawable(R.drawable.plugin_chat_off);
                this.f7324d = context.getString(R.string.plugin_chat_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_chat);
                break;
            case "pleased_display":
                this.f7322b = context.getDrawable(R.drawable.plugin_pleased_display_off);
                this.f7324d = context.getString(R.string.pleased_display_introduction);
                this.f7323c = context.getString(R.string.plugin_label_pleased_display);
                break;
            case "keylink":
                this.f7322b = context.getDrawable(R.drawable.plugin_keylink_off);
                this.f7324d = context.getString(R.string.plugin_keylink_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_keylink);
                break;
            case "game_prediction":
                this.f7322b = context.getDrawable(R.drawable.plugin_game_prediction_off);
                this.f7324d = context.getString(R.string.plugin_game_prediction_introduction);
                this.f7323c = context.getString(R.string.plugin_game_predicition);
                break;
            case "help":
                this.f7322b = context.getDrawable(R.drawable.plugin_help_off);
                this.f7324d = context.getString(R.string.plugin_help_introduction);
                if (!ZteFeature.isSupportDemi()) {
                    this.f7323c = context.getString(R.string.plugin_icon_help);
                    break;
                } else {
                    this.f7323c = context.getString(R.string.plugin_icon_demi_help);
                    break;
                }
            case "timer":
                this.f7322b = context.getDrawable(R.drawable.plugin_timer_off);
                this.f7324d = context.getString(R.string.plugin_timer_introduction);
                this.f7323c = context.getString(R.string.plugin_string_timer);
                break;
            case "mora_ai_speaker":
                this.f7322b = context.getDrawable(R.drawable.plugin_ai_speaker_off);
                if (!ZteFeature.isNeoProduct()) {
                    if (!ZteFeature.isSupportDemi()) {
                        this.f7323c = context.getString(R.string.plugin_label_ai_speaker);
                        this.f7324d = context.getString(R.string.ai_speaker_introduction);
                        break;
                    } else {
                        this.f7323c = context.getString(R.string.plugin_label_demi_ai_speaker);
                        this.f7324d = context.getString(R.string.ai_speaker_demi_introduction);
                        break;
                    }
                } else {
                    this.f7323c = context.getString(R.string.plugin_label_neo_ai_speaker);
                    this.f7324d = context.getString(R.string.ai_speaker_demi_introduction);
                    break;
                }
            case "keyposition_assist":
                this.f7322b = context.getDrawable(R.drawable.plugin_keyposition_off);
                this.f7324d = context.getString(R.string.plugin_keyposition_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_keyposition);
                break;
            case "card_assist":
                this.f7322b = context.getDrawable(R.drawable.plugin_card_assist_off);
                this.f7324d = context.getString(R.string.plugin_card_assist_introduction);
                this.f7323c = context.getString(R.string.plugin_label_card_assist);
                break;
            case "vibrate":
                this.f7322b = context.getDrawable(R.drawable.plugin_4d_off);
                this.f7324d = context.getString(R.string.plugin_4d_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_4d);
                break;
            case "ai_detect":
                this.f7322b = context.getDrawable(R.drawable.plugin_probe_off);
                this.f7324d = context.getString(R.string.ai_detect_introduction);
                this.f7323c = context.getString(R.string.plugin_label_ai_detect);
                break;
            case "operation_devices":
                this.f7322b = context.getDrawable(R.drawable.plugin_operation_off);
                if (!ZteFeature.isSupportPeripheralControl()) {
                    this.f7323c = context.getString(R.string.plugin_icon_operation);
                    this.f7324d = context.getString(R.string.plugin_operation_introduction);
                    break;
                } else {
                    this.f7323c = context.getString(R.string.plugin_icon_peripheral_control);
                    this.f7324d = context.getString(R.string.plugin_peripheral_control_introduction);
                    break;
                }
            case "biablo_mode":
                this.f7322b = context.getDrawable(R.drawable.plugin_biablo_mode_off);
                this.f7324d = context.getString(R.string.plugin_diablo_introduction);
                this.f7323c = context.getString(R.string.nubia_game_performance_mode_diablo_title);
                break;
            case "counter":
                this.f7322b = context.getDrawable(R.drawable.plugin_count_off);
                this.f7324d = context.getString(R.string.plugin_counter_introduction);
                this.f7323c = context.getString(R.string.plugin_label_counter);
                break;
            case "voice_controller":
                this.f7322b = context.getDrawable(R.drawable.plugin_voice_controller_off);
                this.f7324d = context.getString(R.string.voice_controller_introduction);
                this.f7323c = context.getString(R.string.plugin_label_voice_controller);
                break;
            case "sound_effect":
                this.f7322b = context.getDrawable(R.drawable.plugin_sound_off);
                this.f7324d = context.getString(R.string.plugin_sound_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_sound);
                break;
            case "redmagic_broadcast":
                this.f7322b = context.getDrawable(R.drawable.plugin_broadcast_off);
                this.f7324d = context.getString(R.string.plugin_broadcast_introduction);
                if (!ZteFeature.isSupportDemi()) {
                    this.f7323c = context.getString(R.string.plugin_icon_broadcast);
                    break;
                } else {
                    this.f7323c = context.getString(R.string.plugin_icon_demi_broadcast);
                    break;
                }
            case "combat_power":
                this.f7322b = context.getDrawable(R.drawable.plugin_data_panel_switch_off);
                this.f7324d = context.getString(R.string.plugin_combat_power_introduction);
                this.f7323c = context.getString(R.string.plugin_combat_power_title);
                break;
            case "high_sensitivity_wheel":
                this.f7322b = context.getDrawable(R.drawable.plugin_high_wheel_off);
                this.f7324d = context.getString(R.string.high_wheel_introduction);
                this.f7323c = context.getString(R.string.plugin_label_high_wheel);
                break;
            case "investigation_mode":
                this.f7322b = context.getDrawable(R.drawable.plugin_investigate_off);
                this.f7324d = context.getString(R.string.plugin_investigate_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_investigate);
                break;
            case "gameshader":
            case "hunting_mode":
                this.f7322b = context.getDrawable(R.drawable.plugin_hunt_off);
                this.f7324d = context.getString(R.string.plugin_hunt_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_hunt);
                break;
            case "sight_assist":
                this.f7322b = context.getDrawable(R.drawable.plugin_aim_off);
                this.f7324d = context.getString(R.string.plugin_sight_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_sight);
                break;
            case "screen_extraction":
                this.f7322b = context.getDrawable(R.drawable.plugin_screen_extraction_off);
                this.f7324d = context.getString(R.string.plugin_screen_extraction_introduction);
                this.f7323c = context.getString(R.string.plugin_icon_screen_extraction);
                break;
        }
    }

    public Drawable a() {
        return this.f7322b;
    }

    public String b() {
        return this.f7324d;
    }

    public String c() {
        return this.f7323c;
    }

    public String e() {
        return this.f7321a;
    }

    public String toString() {
        return "spec " + this.f7321a + " label = " + this.f7323c;
    }
}
