package com.zte.shared.wrapper;

import android.content.Context;
import android.util.NubiaConfig;
import com.redmagic.game.GameKeysHelper;

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
    public static final boolean SUPPORT_NUBIA_CONFIG;
    public static final int WINDOWING_MODE_PINNED = 2;

    private static class InstanceHolder {
        private static final GameKeysHelperWrapper sInstance = new GameKeysHelperWrapper();

        private InstanceHolder() {
        }
    }

    static {
        boolean z = false;
        try {
            try {
                Class.forName("android.util.NubiaConfig");
                z = true;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            SUPPORT_NUBIA_CONFIG = false;
        }
    }

    public static GameKeysHelperWrapper getDefault() {
        return InstanceHolder.sInstance;
    }

    public static boolean supportAFK() {
        if (!SUPPORT_NUBIA_CONFIG) {
            return false;
        }
        try {
            return Boolean.valueOf(NubiaConfig.getValue(FEATURE_SUPPORT_AFK)).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean supportVirtualHandle() {
        if (!SUPPORT_NUBIA_CONFIG) {
            return false;
        }
        try {
            return Boolean.valueOf(NubiaConfig.getValue("nubia_virtual_game_handle")).booleanValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void closeSub(Context context, int i2) {
        GameKeysHelper.getDefault().closeSub(context, i2);
    }

    public int getGameKeysDBValue(Context context) {
        return GameKeysHelper.getDefault().getGameKeysDBValue(context);
    }

    public boolean isOpenGameKeys(int i2) {
        return GameKeysHelper.getDefault().isOpenGameKeys(i2);
    }

    public boolean isPackageInstalled(Context context, String str, int i2) {
        return PackageManagerWrapper.isPackageInstalled(context, str, i2);
    }

    public void openSub(Context context, int i2) {
        GameKeysHelper.getDefault().openSub(context, i2);
    }
}
