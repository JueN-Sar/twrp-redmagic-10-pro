package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.NubiaConfig;
import cn.nubia.game.GameKeysHelper;
import java.util.Iterator;
import java.util.List;
import nubia.os.ApplicationManager;

/* loaded from: classes2.dex */
public class GameKeysHelperWrapper {
    public static final int DEFAULT_GAME_KEYS = 16;
    public static final String FEATURE_SUPPORT_AFK = "nubia_game_dock_mode_feature";
    public static final int GAME_KEYS_OFF_ON = 1;
    public static final int GAME_KEYS_OFF_ON_CHAT = 8;
    public static final int GAME_KEYS_OFF_ON_DEFAULT = 0;
    public static final int GAME_KEYS_OFF_ON_LIGHT = 16;
    public static final int GAME_KEYS_OFF_ON_NOTIFICATION = 4;
    public static final int GAME_KEYS_OFF_ON_PHONE = 2;
    public static final int GAME_KEYS_OFF_ON_PHONE_DEFAULT = 0;
    public static final int GAME_KEYS_OFF_ON_SUPER_PERFORMANCE = 32;
    public static final String SETTING_GAME_MODE_STATUS = "nubia_db_game_keys";
    public static final int WINDOWING_MODE_PINNED = 2;

    private static class InstanceHolder {
        private static final GameKeysHelperWrapper sInstance = new GameKeysHelperWrapper();

        private InstanceHolder() {
        }
    }

    public static GameKeysHelperWrapper getDefault() {
        return InstanceHolder.sInstance;
    }

    public static boolean supportAFK() {
        try {
            return Boolean.valueOf(NubiaConfig.getValue("nubia_game_dock_mode_feature")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supportVirtualHandle() {
        try {
            return Boolean.valueOf(NubiaConfig.getValue("nubia_virtual_game_handle")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeSub(Context context, int i) {
        GameKeysHelper.getDefault().closeSub(context, i);
    }

    public void closeWindow() {
        try {
            ActivityTaskManager.getService().removeStacksInWindowingModes(new int[]{2});
            Log.i("SmallWindowTile", "closeWindow");
        } catch (RemoteException e) {
            Log.i("SmallWindowTile", "error = " + e.getMessage());
        }
    }

    public int getGameKeysDBValue(Context context) {
        return GameKeysHelper.getDefault().getGameKeysDBValue(context);
    }

    public boolean isGameSpaceListApp(String str) {
        List gameLauncherAppNameList;
        if (!TextUtils.isEmpty(str) && (gameLauncherAppNameList = ApplicationManager.Trigger.getGameLauncherAppNameList()) != null && !gameLauncherAppNameList.isEmpty()) {
            Iterator it = gameLauncherAppNameList.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isOpenGameKeys(int i) {
        return GameKeysHelper.getDefault().isOpenGameKeys(i);
    }

    public boolean isPackageInstalled(Context context, String str, int i) {
        PackageManagerWrapper.getInstance();
        return PackageManagerWrapper.isPackageInstalled(context, str, i);
    }

    public void openSub(Context context, int i) {
        GameKeysHelper.getDefault().openSub(context, i);
    }

    public void startSmallWindow() {
        try {
            ActivityManager.getService().moveTopActivityToPinnedStack(-12, (Rect) null);
            Log.i("SmallWindowTile", "startSmallWindow");
        } catch (RemoteException e) {
            Log.i("SmallWindowTile", "error = " + e.getMessage());
        }
    }
}
