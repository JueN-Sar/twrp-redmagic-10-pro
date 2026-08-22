package cn.nubia.gameassist.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.tiles.ChargeSeparationTiles;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class QSState {

    /* renamed from: a, reason: collision with root package name */
    public String f6145a;

    /* renamed from: b, reason: collision with root package name */
    public Icon f6146b = a();

    /* renamed from: c, reason: collision with root package name */
    public String f6147c;

    public static class DrawableIcon extends Icon {

        /* renamed from: a, reason: collision with root package name */
        protected final Drawable f6148a;

        public Drawable a(Context context) {
            return this.f6148a;
        }
    }

    public static abstract class Icon {
        public int hashCode() {
            return Icon.class.hashCode();
        }
    }

    public static class ResourceIcon extends Icon {

        /* renamed from: b, reason: collision with root package name */
        private static final SparseArray f6149b = new SparseArray();

        /* renamed from: a, reason: collision with root package name */
        protected final int f6150a;

        private ResourceIcon(int i2) {
            this.f6150a = i2;
        }

        public static synchronized Icon a(int i2) {
            Icon icon;
            synchronized (ResourceIcon.class) {
                SparseArray sparseArray = f6149b;
                icon = (Icon) sparseArray.get(i2);
                if (icon == null) {
                    icon = new ResourceIcon(i2);
                    sparseArray.put(i2, icon);
                }
            }
            return icon;
        }

        public int b() {
            return this.f6150a;
        }

        public boolean equals(Object obj) {
            return (obj instanceof ResourceIcon) && ((ResourceIcon) obj).f6150a == this.f6150a;
        }

        public String toString() {
            return String.format("ResourceIcon[resId=0x%08x]", Integer.valueOf(this.f6150a));
        }
    }

    public QSState(String str, Context context) {
        this.f6145a = str;
        this.f6147c = b(context);
    }

    private Icon a() {
        if ("quit".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_quit_off);
        }
        if ("mis_operate".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_misoperate_off);
        }
        if ("dock".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_dock_off);
        }
        if ("liquid_cool".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_liquid_cooling_off);
        }
        if ("image_search".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_image_search_off);
        }
        if ("afk".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_afk_off);
        }
        if ("snap".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_supersnap_normal);
        }
        if ("record".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_record_off);
        }
        if ("tel".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_tel_off);
        }
        if ("noti".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_noti_off);
        }
        if ("clean".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_clean_off);
        }
        if ("game_benefit".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_benefit_normal);
        }
        if ("link_mics_translation".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_link_mics_translation_normal);
        }
        if ("low_sugar".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_low_sugar_gameplay_normal);
        }
        if ("refreshrate".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_refresh_rate_60hz);
        }
        if ("vibrate".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_vibrate_off);
        }
        if ("wifidisplay".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_wifidisplay_off);
        }
        if ("wifi".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_wifi_normal);
        }
        if ("fan".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_fan_off);
        }
        if ("game_wechat".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_wechat_off);
        }
        if ("game_qq".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_qq_off);
        }
        if ("game_douyin".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_douin_off);
        }
        if ("game_bilibili".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_bilibili_off);
        }
        if ("game_kuaishou".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_kuaishou_off);
        }
        if ("game_browser".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_browser_off);
        }
        if ("sight_assist".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_assist_normal);
        }
        if ("handle".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_handle_normal);
        }
        if ("whatsapp".equals(this.f6145a)) {
            return ResourceIcon.a(R.drawable.game_ic_qs_whatsapp);
        }
        if (!"hunting_mode".equals(this.f6145a) && !"gameshader".equals(this.f6145a)) {
            if ("active_mode".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_activemode_off);
            }
            if ("framerate_display".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.ic_qs_framerate_display_normal);
            }
            if ("performance_monitor".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_performance_monitor_switch_off);
            }
            if ("manual_record".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_manual_record_normal);
            }
            if ("small_window".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_smallwindow_normal);
            }
            if ("virtual_handle".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_virtualhandle_normal);
            }
            if ("keylink".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_onekey_link_normal);
            }
            if ("voice".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_magicvoice_normal);
            }
            if ("translate_assistant".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_translate_assistant_normal);
            }
            if ("charge_separation".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_charge_separation_normal);
            }
            if ("help".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.plugin_help_on);
            }
            if ("redmagic_broadcast".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_red_magic_broadcast_normal);
            }
            if ("game_reminder".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_reminder_normal);
            }
            if ("competition_light".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_competition_light_normal);
            }
            if ("barrage_message".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_barrage_normal);
            }
            if ("range_line".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.plugin_rangeline_on);
            }
            if ("rotaton_lock".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_rotation_lock_off);
            }
            if ("chat_assit".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.plugin_chat_on);
            }
            if ("multi_sub_screen".equals(this.f6145a)) {
                return ResourceIcon.a(R.drawable.game_ic_qs_multi_subscreen_on);
            }
            return null;
        }
        return ResourceIcon.a(R.drawable.hunting_mode_off);
    }

    private String b(Context context) {
        if ("game_wechat".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_wechat);
        }
        if ("game_qq".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_qq);
        }
        if ("game_browser".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_browser);
        }
        if ("game_kuaishou".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_kuaishou);
        }
        if ("game_bilibili".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_bilibili);
        }
        if ("game_douyin".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_douyin);
        }
        if ("small_window".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_smallwindow);
        }
        if ("fan".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_fan);
        }
        if ("vibrate".equals(this.f6145a)) {
            return context.getString(R.string.game_vibrate);
        }
        if ("wifidisplay".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_wifidisplay);
        }
        if ("wifi".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_wifi_switch);
        }
        if ("refreshrate".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_refreshrate);
        }
        if ("clean".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_clean);
        }
        if ("game_benefit".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_game_benefit);
        }
        if ("link_mics_translation".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_link_mics_captions_title);
        }
        if ("low_sugar".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_low_sugar);
        }
        if ("noti".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_no_noti);
        }
        if ("tel".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_no_tel);
        }
        if ("record".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_record);
        }
        if ("snap".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_snap);
        }
        if ("liquid_cool".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_liquid_cooling);
        }
        if ("image_search".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_image_search);
        }
        if ("afk".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_afk);
        }
        if ("dock".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_expansion_dock);
        }
        if ("mis_operate".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_mis_operate);
        }
        if ("quit".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_quit);
        }
        if ("sight_assist".equals(this.f6145a)) {
            return context.getString(R.string.plugin_icon_sight);
        }
        if ("handle".equals(this.f6145a)) {
            return context.getString(R.string.handler_setting);
        }
        if ("whatsapp".equals(this.f6145a)) {
            return context.getString(R.string.ic_qs_whatsapp);
        }
        if (!"hunting_mode".equals(this.f6145a) && !"gameshader".equals(this.f6145a)) {
            if ("active_mode".equals(this.f6145a)) {
                return context.getString(R.string.ic_qs_active_mode);
            }
            if ("framerate_display".equals(this.f6145a)) {
                return context.getString(R.string.ic_qs_frame_rate_display);
            }
            if ("performance_monitor".equals(this.f6145a)) {
                return context.getString(R.string.ic_qs_performance_monitor);
            }
            if ("manual_record".equals(this.f6145a)) {
                return context.getString(ZteFeature.isSupportGameRandomRecord() ? R.string.ic_qs_your_record : R.string.ic_qs_manual_record);
            }
            return "small_window".equals(this.f6145a) ? context.getString(R.string.ic_qs_smallwindow) : "virtual_handle".equals(this.f6145a) ? context.getString(R.string.ic_qs_virtual_handle) : "keylink".equals(this.f6145a) ? context.getString(R.string.ic_qs_one_key_link) : "voice".equals(this.f6145a) ? context.getString(R.string.ic_qs_magic_voice) : "translate_assistant".equals(this.f6145a) ? context.getString(R.string.ic_qs_translate_assistant) : "charge_separation".equals(this.f6145a) ? ChargeSeparationTiles.z0(context, R.string.ic_qs_charge_separation) : "help".equals(this.f6145a) ? context.getString(R.string.plugin_icon_help) : "redmagic_broadcast".equals(this.f6145a) ? context.getString(R.string.ic_qs_red_magic_broadcast) : "game_reminder".equals(this.f6145a) ? context.getString(R.string.ic_qs_game_reminder) : "competition_light".equals(this.f6145a) ? context.getString(R.string.ic_qs_competition_light) : "barrage_message".equals(this.f6145a) ? context.getString(R.string.ic_qs_game_barrage) : "range_line".equals(this.f6145a) ? context.getString(R.string.plugin_icon_rangeline) : "rotaton_lock".equals(this.f6145a) ? context.getString(R.string.ic_qs_rotation_lock) : "chat_assit".equals(this.f6145a) ? context.getString(R.string.plugin_icon_chat) : "multi_sub_screen".equals(this.f6145a) ? context.getString(R.string.ic_qs_multi_subscreen) : this.f6145a;
        }
        return context.getString(R.string.str_hunting_mode);
    }
}
