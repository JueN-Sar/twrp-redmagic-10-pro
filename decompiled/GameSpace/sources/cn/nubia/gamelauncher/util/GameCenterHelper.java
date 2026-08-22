package cn.nubia.gamelauncher.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;

/* loaded from: classes.dex */
public class GameCenterHelper {
    private static final String GAME_PLACE_SDK_APP_DETAIL = "gameplacesdk://appdetail?packageName=";
    private static final String GAME_PLACE_SDK_TOPIC = "gameplacesdk://topic?topic_type=901";

    private static boolean clickGameCenter(Context context, Intent intent) {
        if (intent.resolveActivityInfo(context.getPackageManager(), 0) != null) {
            return false;
        }
        showGameCenterNotFoundDialog(context);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void downLoadNeoGameCenter(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(""));
        intent.addFlags(268435456);
        intent.addFlags(32768);
        intent.addFlags(536870912);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.i("GCH", "downLoadNeoGameCenter " + e);
        }
    }

    private static void showGameCenterNotFoundDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, 2131952382);
        builder.setTitle(context.getString(R.string.gamecenter_not_fonund_dialog_text)).setPositiveButton(R.string.nubia_game_performance_super_dialog_ok, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.util.GameCenterHelper.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                GameCenterHelper.downLoadNeoGameCenter(context);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.util.GameCenterHelper.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).create();
        try {
            builder.show();
        } catch (WindowManager.BadTokenException e) {
            e.printStackTrace();
        }
    }

    public static void startAppDetail(Context context, String str) {
        startGameCenter(context, GAME_PLACE_SDK_APP_DETAIL + str);
    }

    public static void startGameCenter(Context context, String str) {
        try {
            startGameCenterActivity(context, str);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startGameCenterActivity(Context context, String str) {
        ReflectUtilities.requestCPUBoost();
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.addFlags(32768);
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        context.startActivity(intent);
    }

    public static void startGameCenterSearchPage(Context context) {
        try {
            startGameCenterActivity(context, "gameplace://search");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startGameRecommend(Context context) {
        try {
            startGameCenterActivity(context, "gameplacesdk://home");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startGift(Context context) {
        try {
            startGameCenterActivity(context, "neogamecenter://gift?id=id&type=type&tokenId=tokenid");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startHandHeldGame(Context context) {
        startGameCenter(context, GAME_PLACE_SDK_TOPIC);
    }

    public static void startOperation(Context context, String str) {
        Log.d("Full", "startOperation() data : " + str);
        try {
            startGameCenterActivity(context, str);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startPlayCenterPlaza(Context context) {
        try {
            startGameCenterActivity(context, "gameplacesdk://playcenter");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startRedMagicDevice(Context context) {
        try {
            if (!CommonUtil.isInstalled(context, "cn.nubia.externdevice")) {
                if (CommonUtil.isInternalVersion()) {
                    Toast.makeText(context, R.string.ic_qs_pip_uninstall_toast, 0).show();
                    return;
                }
                Uri parse = Uri.parse("");
                if (CommonUtil.isNX669J_Project()) {
                    parse = Uri.parse("");
                }
                Intent intent = new Intent("android.intent.action.VIEW", parse);
                intent.addFlags(268435456);
                intent.addFlags(32768);
                intent.addFlags(536870912);
                context.startActivity(intent);
            }
            ReflectUtilities.requestCPUBoost();
            Intent intent2 = new Intent();
            intent2.setAction("cn.nubia.externdevice.MAIN");
            intent2.addFlags(268435456);
            intent2.addFlags(32768);
            intent2.addFlags(536870912);
            context.startActivity(intent2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startRelevant(Context context) {
        try {
            startGameCenterActivity(context, "gameplacesdk://appdetail?packageName=packageName");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startTencentGameRecommend(Context context) {
        try {
            ReflectUtilities.requestCPUBoost();
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.addFlags(32768);
            intent.setData(Uri.parse("gpage://nubia_game_recommend"));
            intent.setPackage("com.tencent.southpole.gamecenter");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            LogUtil.d("GCH", "startTencentGameRecommend() e : " + e.getMessage());
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startUsersGameSetttings(Context context, boolean z) {
        try {
            ReflectUtilities.requestCPUBoost();
            Intent intent = new Intent();
            intent.setAction("cn.nubia.gamecenter.settings.action.GAME_CENTER");
            if (z) {
                intent.putExtra("gcs_start_type", "summary_keyword_week");
            }
            intent.putExtra("hasNewVersion", UpgradeManager.getInstance().hasNewVersion());
            intent.addFlags(268435456);
            intent.addFlags(32768);
            intent.addFlags(536870912);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startVip(Context context) {
        try {
            startGameCenterActivity(context, "neogamecenter://web?linkUrl=https://h5-appstore.nubia.com/vip/vip.html");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }

    public static void startZteRecommend(Context context) {
        try {
            Intent intent = new Intent();
            intent.setAction("zte.com.market.game.recommend");
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            showGameCenterNotFoundDialog(context);
        }
    }
}
