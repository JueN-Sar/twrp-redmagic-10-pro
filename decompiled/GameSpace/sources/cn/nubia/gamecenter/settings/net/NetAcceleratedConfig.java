package cn.nubia.gamecenter.settings.net;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.utils.Utils;

/* loaded from: classes.dex */
public class NetAcceleratedConfig {
    public static final String DB_NET_ACCELERATED_SDK = "db_name_game_network_acceleration_sdk";
    public static final String DB_NET_ACCELERATED_TX_NET_INFO = "db_name_game_network_acceleration_tx_net_info";
    public static final String DB_NET_ACCELERATED_TX_PROMPT = "db_name_game_network_acceleration_tx_prompt";
    public static final int SDK_TYPE_CLOSE = 0;
    public static final int SDK_TYPE_TENCENT = 2;
    public static final int SDK_TYPE_UNLOGIN = 3;
    public static final int SDK_TYPE_XUNYOU = 1;
    public static final String Tencent_Acceleration_Package = "com.tencent.cmocmna";

    public static boolean checkIfExistTencentAcceleratedApp(Context context) {
        return Utils.isAppExist(context, Tencent_Acceleration_Package);
    }

    public static boolean checkIfIsOpenNetworkAcceletion(Context context) {
        return getNetAcceleratedSDKType(context) != 0;
    }

    public static boolean checkIfIsTencentAcceletion(Context context) {
        return getNetAcceleratedSDKType(context) == 2;
    }

    public static boolean checkIfIsXunyouAcceletion(Context context) {
        return getNetAcceleratedSDKType(context) == 1;
    }

    public static boolean checkIfSupportTencentAcceleration(Context context) {
        return Build.DEVICE.contains("NX669");
    }

    public static CharSequence[] getNetAcceleratedCharSequences(Context context) {
        return context.getResources().getStringArray(checkIfSupportTencentAcceleration(context) ? R.array.gcs_network_game_acceleration_sdk_type_with_tencent : R.array.gcs_network_game_acceleration_sdk_type);
    }

    public static int getNetAcceleratedSDKType(Context context) {
        return (checkIfSupportTencentAcceleration(context) && checkIfExistTencentAcceleratedApp(context)) ? Settings.Global.getInt(context.getContentResolver(), DB_NET_ACCELERATED_SDK, 0) : Settings.Global.getInt(context.getContentResolver(), DB_NET_ACCELERATED_SDK, 0) == 0 ? 0 : 1;
    }

    public static int[] getNetAcceleratedValues(Context context) {
        return context.getResources().getIntArray(checkIfSupportTencentAcceleration(context) ? R.array.gcs_network_game_acceleration_sdk_value_with_tencent : R.array.gcs_network_game_acceleration_sdk_value);
    }

    public static void routeToAppMarket(Context context) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("sppage://appdetail?pkgName=com.tencent.cmocmna&dl=false"));
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }
}
