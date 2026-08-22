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
                if (Boolean.valueOf(Class.forName("android.util.NubiaConfig") != null) != null) {
                    z = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            SUPPORT_NUBIA_CONFIG = z;
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
            return Boolean.valueOf(NubiaConfig.getValue("nubia_game_dock_mode_feature")).booleanValue();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean supportVirtualHandle() {
        if (!SUPPORT_NUBIA_CONFIG) {
            return false;
        }
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

    public int getGameKeysDBValue(Context context) {
        return GameKeysHelper.getDefault().getGameKeysDBValue(context);
    }

    public boolean isOpenGameKeys(int i) {
        return GameKeysHelper.getDefault().isOpenGameKeys(i);
    }

    public boolean isPackageInstalled(Context context, String str, int i) {
        return PackageManagerWrapper.isPackageInstalled(context, str, i);
    }

    public void openSub(Context context, int i) {
        GameKeysHelper.getDefault().openSub(context, i);
    }
}
