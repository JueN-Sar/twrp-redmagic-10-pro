package com.zte.gameassist.aiagent.command;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import com.zte.gameassist.aiagent.BaseCommand;
import com.zte.gameassist.aiagent.CommandExecutor;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.R;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class GameSpaceCommand extends BaseCommand implements CommandExecutor {
    private static final String DATA_LOW_LATENCY_SETTINGS_KEY = "gsc_data_low_latency_mode";
    private static final String GAME_TGPA_PREDOWNLOAD_SETTINGS_KEY = "game_tgpa_predownload";
    private static final String WIFI_LOW_LATENCY_SETTINGS_KEY = "gsc_wifi_low_latency_mode";
    private static final String ZTE_FEATURE_MTGPA_PREDOWNLOAD = "ZTE_FEATURE_MTGPA_PREDOWNLOAD";
    private final String TAG = "GameSpaceCommand";
    private static final ArrayList<String> SUPPORT_RESOURCE_PRE_DOWNLOAD_WHITE_LIST = new ArrayList() { // from class: com.zte.gameassist.aiagent.command.GameSpaceCommand.1
        {
            add("com.tencent.tmgp.sgame");
            add("com.tencent.tmgp.pubgmhd");
        }
    };
    private static final boolean ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH = ZteFeature.getBoolean("ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH", false).booleanValue();

    private void CloseGameLauncherBgm() {
        GaLog.a("GameSpaceCommand", "CloseGameLauncherBgm");
        this.mContext.getContentResolver().call(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider"), "closeBgm", (String) null, (Bundle) null);
    }

    private void OpenGameLauncherBgm() {
        GaLog.a("GameSpaceCommand", "OpenGameLauncherBgm");
        this.mContext.getContentResolver().call(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider"), "openBgm", (String) null, (Bundle) null);
    }

    private void addGame() {
        GaLog.e("GameSpaceCommand", "enter add game activity");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("cn.nubia.gamelauncher", "cn.nubia.gamelauncher.activity.AppAddActivity"));
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.mContext.startActivity(intent);
    }

    private void guideToGameSpace(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        replyFinishMessage(iGameAssistClientCallback, inMsg, this.mContext.getString(R.string.aiagent_guide_to_game_space), 0, false);
    }

    private void openMyData() {
        GaLog.a("GameSpaceCommand", "openMyData");
        Intent intent = new Intent("cn.nubia.gamecenter.settings.action.GAME_CENTER");
        intent.putExtra("gcs_start_type", "ArkBaseFragment");
        intent.setPackage("cn.nubia.gamelauncher");
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.mContext.startActivity(intent);
    }

    private void saveGameStrengthenNewValueToDB(String str, String str2, int i2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String string = Settings.Global.getString(this.mContext.getContentResolver(), str2);
        GaLog.a("GameSpaceCommand", "oldvle " + string);
        if (!TextUtils.isEmpty(string) && string.contains(str)) {
            String[] split = string.split(",");
            int length = split.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                String str3 = split[i3];
                if (!TextUtils.isEmpty(str3) && str3.contains(str)) {
                    string = string.replace(str3, str + "+" + i2);
                    break;
                }
                i3++;
            }
        } else if (string != null) {
            string = string + str + "+" + i2 + ",";
        } else {
            string = str + "+" + i2 + ",";
        }
        GaLog.e("GameSpaceCommand", "newvle " + string);
        Settings.Global.putString(this.mContext.getContentResolver(), str2, string);
    }

    private void saveResourcePreDownloadNewValueToDB(String str, String str2, int i2) {
        String str3;
        String string = Settings.Global.getString(this.mContext.getContentResolver(), str2);
        if (TextUtils.isEmpty(string)) {
            str3 = str + ":" + i2 + ",";
        } else if (string.contains(str)) {
            String[] split = string.split(",");
            int length = split.length;
            int i3 = 0;
            while (true) {
                if (i3 >= length) {
                    break;
                }
                String str4 = split[i3];
                if (!TextUtils.isEmpty(str4) && str4.contains(str)) {
                    string = string.replace(str4, str + ":" + i2);
                    break;
                }
                i3++;
            }
            str3 = string;
        } else {
            str3 = string + str + ":" + i2 + ",";
        }
        GaLog.a("GameSpaceCommand", " buildValue new dbValue = " + str3);
        Settings.Global.putString(this.mContext.getContentResolver(), str2, str3);
    }

    private void setApkPreDownloadEnabled(boolean z, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        String t = SystemMgr.t();
        if (ZteFeature.getBoolean(ZTE_FEATURE_MTGPA_PREDOWNLOAD, false).booleanValue() && SUPPORT_RESOURCE_PRE_DOWNLOAD_WHITE_LIST.contains(t)) {
            saveResourcePreDownloadNewValueToDB(t, GAME_TGPA_PREDOWNLOAD_SETTINGS_KEY, z ? 1 : 0);
            if (z) {
                finishTurnOn(iGameAssistClientCallback, inMsg, R.string.resource_pre_download_text);
                return;
            } else {
                finishTurnOff(iGameAssistClientCallback, inMsg, R.string.resource_pre_download_text);
                return;
            }
        }
        GaLog.k("GameSpaceCommand", "current game: " + t + " does not support pre download !");
        replyToBeSupportedMessage(iGameAssistClientCallback, inMsg);
    }

    private void setMobileDataLowLatencyMode(boolean z) {
        saveGameStrengthenNewValueToDB(SystemMgr.t(), DATA_LOW_LATENCY_SETTINGS_KEY, z ? 1 : 0);
    }

    private void setWiFiLowLatencyMode(boolean z) {
        saveGameStrengthenNewValueToDB(SystemMgr.t(), WIFI_LOW_LATENCY_SETTINGS_KEY, z ? 1 : 0);
    }

    private void startGameApp(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (this.mContext.getContentResolver().call(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider"), "startGame", (String) null, (Bundle) null).getBoolean("startResult")) {
            replyFinishMessage(iGameAssistClientCallback, inMsg, R.string.aiagent_handled, true);
        } else {
            guideToGameSpace(iGameAssistClientCallback, inMsg);
        }
    }

    private void startRedMagicKyi() {
        GaLog.a("GameSpaceCommand", "startRedMagicKyi");
        Intent intent = new Intent();
        intent.setAction("intent.action.redmagickyi.main");
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.mContext.startActivity(intent);
    }

    private void turnOffConfigWindow() {
        GaLog.a("GameSpaceCommand", "turnOffConfigWindow");
        this.mContext.sendBroadcast(new Intent("cn.nubia.gamelauncher.action.close_controlpanel"));
    }

    private void turnOnConfigWindow() {
        GaLog.a("GameSpaceCommand", "turnOnConfigWindow");
        Intent intent = new Intent("cn.nubia.intent.action.PERFORMANCE_MODE_OPTION");
        intent.putExtra("packageName", SystemMgr.t());
        intent.putExtra("activity", SystemMgr.s());
        if (SystemMgr.L()) {
            intent.putExtra("shortcutLabel", SystemMgr.u());
            intent.putExtra("isShortCut", true);
        }
        this.mContext.sendBroadcast(intent);
    }

    private void turnOnNetConfigWindow() {
        GaLog.a("GameSpaceCommand", "turnOnNetConfigWindow");
        Intent intent = new Intent();
        intent.setAction("cn.nubia.gamecenter.settings.action.GAME_CENTER");
        intent.putExtra("gcs_start_type", "NetFragment");
        intent.setPackage("cn.nubia.gamelauncher");
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        this.mContext.startActivity(intent);
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void execute(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        String e2 = inMsg.e();
        GaLog.e("GameSpaceCommand", "execute intent=" + e2);
        if ("game_choice_app_to_game_list".equals(e2)) {
            addGame();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_open_game_launcher_music".equals(e2)) {
            OpenGameLauncherBgm();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_close_game_launcher_music".equals(e2)) {
            CloseGameLauncherBgm();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_open_my_data".equals(e2)) {
            openMyData();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_start_M_app".equals(e2)) {
            startRedMagicKyi();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_start_game_app".equals(e2)) {
            startGameApp(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_play_games_by_key_mouse".equals(e2) || "game_play_games_by_joystick".equals(e2) || "game_play_games_by_screen_casting".equals(e2)) {
            return;
        }
        if ("game_open_game_config_window".equals(e2)) {
            turnOnConfigWindow();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_turn_off_game_config_window".equals(e2)) {
            turnOffConfigWindow();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_open_net_config_window".equals(e2)) {
            turnOnNetConfigWindow();
            replyHandledMessage(iGameAssistClientCallback, inMsg);
            return;
        }
        if ("game_open_WIFI_low_latency_mode".equals(e2)) {
            setWiFiLowLatencyMode(true);
            finishTurnOn(iGameAssistClientCallback, inMsg, R.string.net_wifi_low_latency);
            return;
        }
        if ("game_close_WIFI_low_latency_mode".equals(e2)) {
            setWiFiLowLatencyMode(false);
            finishTurnOff(iGameAssistClientCallback, inMsg, R.string.net_wifi_low_latency);
            return;
        }
        if ("game_Open_MobileData_Low_Latency".equals(e2)) {
            if (!ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH) {
                replyToBeSupportedMessage(iGameAssistClientCallback, inMsg);
                return;
            } else {
                setMobileDataLowLatencyMode(true);
                finishTurnOn(iGameAssistClientCallback, inMsg, R.string.net_data_low_latency);
                return;
            }
        }
        if ("game_turn_off_low_latency_mobile_data".equals(e2)) {
            if (!ZTE_FEATURE_REDMAGIC_GAME_LATENCY_DATA_SWITCH) {
                replyToBeSupportedMessage(iGameAssistClientCallback, inMsg);
                return;
            } else {
                setMobileDataLowLatencyMode(false);
                finishTurnOff(iGameAssistClientCallback, inMsg, R.string.net_data_low_latency);
                return;
            }
        }
        if ("game_turn_on_apk_pre_download".equals(e2)) {
            setApkPreDownloadEnabled(true, iGameAssistClientCallback, inMsg);
        } else if ("game_turn_off_apk_pre_download".equals(e2)) {
            setApkPreDownloadEnabled(false, iGameAssistClientCallback, inMsg);
        }
    }

    public JSONObject getPlugOptions() {
        return null;
    }

    @Override // com.zte.gameassist.aiagent.CommandExecutor
    public void init(Context context) {
        this.mContext = context;
    }

    public boolean isPlugInit() {
        return true;
    }
}
