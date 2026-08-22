package com.zte.gameassist.lowsugar.detect.scene;

import android.graphics.RectF;
import android.view.MotionEvent;
import cn.nubia.yolox.YOLOXncnn;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes2.dex */
public interface IYoloXScene {

    /* renamed from: a, reason: collision with root package name */
    public static final String[] f16861a = {"operation_icon", "operation_text", "wish_icon", "wish_text", "gift_icon", "task_done_icon", "close_icon", "go_back_icon", "go_back_icon", "message_icon", "settings_icon", "shop_icon", "wish_icon", "command_icon", "events_icon", "gift_book_icon", "global_go_back_icon", "global_message_icon", "global_settings_icon", "global_shop_icon", "global_menu_close_icon", "global_menu_open_icon", "global_command_icon", "global_attack_icon", "global_defense_icon", "global_gather_icon", "global_retreat_icon", "essence_icon", "poro_coins_icon", "collection_icon", "attack_icon", "defend_icon", "retreat_icon", "33_go_back_icon", "34_go_back_icon", "home_icon", "close_icon", "custom_icon", "reminder_icon", "global_operation_icon", "global_gift_icon", "global_telegramr_icon", "honor_rank_icon", "shop_icon", "events_icon", "events_star_icon", "events_gift_icon", "events_fire_icon", "events_cooperate_icon", "events_list_icon", "events_m_icon", "settings_icon", "coordinate_icon", "message_icon", "close_icon"};

    default boolean a(RectF rectF, MotionEvent motionEvent) {
        if (rectF == null || motionEvent == null) {
            return false;
        }
        int actionIndex = motionEvent.getActionIndex();
        boolean contains = rectF.contains(motionEvent.getX(actionIndex), motionEvent.getY(actionIndex));
        GaLog.a("LowSugarGameplay", "isTouchObj isTouchObj:" + contains);
        return contains;
    }

    default boolean b(int i2, MotionEvent motionEvent) {
        return true;
    }

    default boolean c(YOLOXncnn.Obj obj, MotionEvent motionEvent) {
        return d(obj, motionEvent, 20);
    }

    default boolean d(YOLOXncnn.Obj obj, MotionEvent motionEvent, int i2) {
        if (obj == null || motionEvent == null) {
            return false;
        }
        int actionIndex = motionEvent.getActionIndex();
        float x = motionEvent.getX(actionIndex);
        float y = motionEvent.getY(actionIndex);
        RectF a2 = obj.a();
        float f2 = i2;
        return new RectF(a2.left - f2, a2.top - f2, a2.right + f2, a2.bottom + f2).contains(x, y);
    }
}
