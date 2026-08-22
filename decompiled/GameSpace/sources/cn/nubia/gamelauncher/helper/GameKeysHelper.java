package cn.nubia.gamelauncher.helper;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class GameKeysHelper {
    public static final int DEFAULT_GAME_KEYS = 16;
    public static final int GAME_KEYS_OFF_ON = 1;
    public static final int GAME_KEYS_OFF_ON_CHAT = 8;
    public static final int GAME_KEYS_OFF_ON_DEFAULT = 0;
    public static final int GAME_KEYS_OFF_ON_LIGHT = 16;
    public static final int GAME_KEYS_OFF_ON_NOTIFICATION = 4;
    public static final int GAME_KEYS_OFF_ON_PHONE = 2;
    public static final int GAME_KEYS_OFF_ON_PHONE_DEFAULT = 0;
    public static final int GAME_KEYS_OFF_ON_SUPER_PERFORMANCE = 32;
    public static final String STR_GAME_KEYS_OFF_ON = "nubia_db_game_keys";
    private static final GameKeysHelper sIntance = new GameKeysHelper();
    private final String TAG = "GameKeysHelper";

    GameKeysHelper() {
    }

    public static GameKeysHelper getDefault() {
        return sIntance;
    }

    public void closeSub(Context context, int i) {
        int gameKeysDBValue = (~i) & getGameKeysDBValue(context);
        Log.d("GameKeysHelper", " closeSub newGameKeys = " + Integer.toBinaryString(gameKeysDBValue));
        setGameKeysDBValue(context, gameKeysDBValue);
    }

    public int getGameKeysDBValue(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), "nubia_db_game_keys", 16);
    }

    public boolean isGameKeyOn() {
        return isOpenGameKeys(getGameKeysDBValue(Util.getAppContext()));
    }

    public boolean isOpenGameKeys(int i) {
        return (i & 1) != 0;
    }

    public void openSub(Context context, int i) {
        int gameKeysDBValue = i | getGameKeysDBValue(context);
        Log.d("GameKeysHelper", " openSub newGameKeys = " + Integer.toBinaryString(gameKeysDBValue));
        setGameKeysDBValue(context, gameKeysDBValue);
    }

    public void setGameKeysDBValue(Context context, int i) {
        Settings.Global.putInt(context.getContentResolver(), "nubia_db_game_keys", i);
    }
}
