package cn.nubia.gamelauncher.util;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.helper.GameKeysHelper;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class ReflectUtilities {
    private static final String GAME_KEYS_CLASS_NAME;
    private static final String GAME_KEYS_CLASS_NAME_INTER = "com.android.internal.policy.gamekeys.GameKeysHelper";
    private static final String GAME_MODE_CLASS_NAME;
    private static final int NETWORK_ACCELERATION_OFF_ON = 64;

    static {
        GAME_MODE_CLASS_NAME = Util.isRedMagicRunOnMyOs() ? "com.redmagic.game.GameModeHelper" : "cn.nubia.game.GameModeHelper";
        GAME_KEYS_CLASS_NAME = Util.isRedMagicRunOnMyOs() ? "com.redmagic.game.GameKeysHelper" : "cn.nubia.game.GameKeysHelper";
    }

    public static void closeSub(Context context, int i) {
        if (Util.isZte()) {
            GameKeysHelper.getDefault().closeSub(context, i);
            return;
        }
        try {
            Class<?> cls = Class.forName(getGameKeyClassName());
            cls.getMethod("closeSub", Context.class, Integer.TYPE).invoke(cls.newInstance(), context, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean geGameModeNetAccOffOn(Context context) {
        return (getGameModeDBValue(context) & 64) != 0;
    }

    private static String getGameKeyClassName() {
        return GAME_KEYS_CLASS_NAME;
    }

    public static int getGameKeysDBValue(Context context) {
        if (Util.isZte()) {
            return GameKeysHelper.getDefault().getGameKeysDBValue(context);
        }
        try {
            Class<?> cls = Class.forName(getGameKeyClassName());
            return ((Integer) cls.getMethod("getGameKeysDBValue", Context.class).invoke(cls.newInstance(), context)).intValue();
        } catch (Exception e) {
            LogUtil.w("ReflectUtilities", "getGameKeysDBValue() e : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static int getGameModeDBValue(Context context) {
        if (Util.isZte()) {
            return GameKeysHelper.getDefault().getGameKeysDBValue(context);
        }
        try {
            Class<?> cls = Class.forName(GAME_MODE_CLASS_NAME);
            Method declaredMethod = cls.getDeclaredMethod("getGameModeDBValue", Context.class);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(cls.newInstance(), context)).intValue();
        } catch (Exception e) {
            LogUtil.w("ReflectUtilities", "getGameModeDBValue() e : " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isGameKeyClose(Context context) {
        int gameModeDBValue = getGameModeDBValue(context.getApplicationContext());
        boolean z = (gameModeDBValue & 1) == 0;
        Log.d("switch", "isGameKeyOpen() isGameKeyClose = " + z + ", gameKeys = " + gameModeDBValue);
        return z;
    }

    public static void openSub(Context context, int i) {
        if (Util.isZte()) {
            GameKeysHelper.getDefault().openSub(context, i);
            return;
        }
        try {
            Class<?> cls = Class.forName(getGameKeyClassName());
            cls.getDeclaredMethod("openSub", Context.class, Integer.TYPE).invoke(cls.newInstance(), context, Integer.valueOf(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestCPUBoost() {
        try {
            Class<?> cls = Class.forName("android.os.BSPApplicationManager$Trigger");
            cls.getMethod("acquirePerformanceLock", IBinder.class, String.class, Integer.TYPE, Long.TYPE).invoke(cls, new Binder(), "startApp", 7, Integer.valueOf(HighLightsUtils.RESET_DELAY_TIME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
