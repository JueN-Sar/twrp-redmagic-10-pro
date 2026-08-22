package cn.nubia.gamelauncher.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamelauncher.util.GameKeysConstant;
import cn.nubia.gamelauncher.util.NubiaTrackManager;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class BuryReceiver extends BroadcastReceiver {
    public static final String BOOT_ACTION;

    static {
        BOOT_ACTION = (Util.isZte() || Util.isRedMagicRunOnMyOs()) ? "com.zte.analytics.action.FIRST_BOOT" : "cn.nubia.owlsystem.firstbootdayaction";
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null || intent.getAction().equals(BOOT_ACTION)) {
            String string = context.getSharedPreferences(GameKeysConstant.IS_FIRST_DIALOG_NAME, 0).getString(GameKeysConstant.FAN_STATUS, "关");
            Bundle bundle = new Bundle();
            bundle.putString("package_name", "cn.nubia.gamelauncher");
            bundle.putString(NubiaTrackManager.EVENT_NAME, "gamespace_cooling_fan_switch");
            bundle.putString("action_type", "switch_status");
            bundle.putString(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, string);
            bundle.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle);
            Bundle bundle2 = new Bundle();
            int i = Settings.Global.getInt(context.getContentResolver(), "redmagic_broadcast_switch", 0);
            int i2 = Settings.Secure.getInt(context.getContentResolver(), "magic_elves_broadcast_count_down", 0);
            int i3 = Settings.Secure.getInt(context.getContentResolver(), "magic_elves_broadcast_red_blue", 0);
            int i4 = Settings.Secure.getInt(context.getContentResolver(), "magic_elves_broadcast_finals", 0);
            int i5 = Settings.Secure.getInt(context.getContentResolver(), "magic_elves_broadcast_king_dragon", 0);
            int i6 = Settings.Secure.getInt(context.getContentResolver(), "magic_elves_broadcast_king_primer", 0);
            bundle2.putString("package_name", "cn.nubia.gamelauncher");
            bundle2.putString("event_name", "game_broadcast_switch");
            bundle2.putString("action_type", "redmagic_broadcast_switch magic_elves_broadcast_count_down magic_elves_broadcast_red_blue magic_elves_broadcast_finals magic_elves_broadcast_king_dragon magic_elves_broadcast_king_primer");
            bundle2.putString(AppDbSchema.AppTable.OneDayCols.ACTION_VALUE, i + " " + i2 + " " + i3 + " " + i4 + " " + i5 + " " + i6);
            bundle2.putInt(AppDbSchema.AppTable.OneDayCols.REPORT_INTERVAL, 1);
            NubiaTrackManager.getInstance().sendEvent("cn.nubia.gamelauncher", bundle2);
        }
    }
}
