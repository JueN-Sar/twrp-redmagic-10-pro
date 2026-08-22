package cn.nubia.gameassist.utils;

import android.content.Context;
import android.provider.Settings;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.GameKeysHelperWrapper;

/* loaded from: classes.dex */
public class GameKeysHelper {

    /* renamed from: b, reason: collision with root package name */
    private static final GameKeysHelper f7657b = new GameKeysHelper();

    /* renamed from: a, reason: collision with root package name */
    private final String f7658a = "GameKeysHelper";

    GameKeysHelper() {
    }

    public static GameKeysHelper b() {
        return f7657b;
    }

    public void a(Context context, int i2) {
        int c2 = (~i2) & c(context);
        GaLog.a("GameKeysHelper", " closeSub newGameKeys = " + Integer.toBinaryString(c2));
        e(context, c2);
    }

    public int c(Context context) {
        return Settings.Global.getInt(context.getContentResolver(), GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS, 16);
    }

    public void d(Context context, int i2) {
        int c2 = i2 | c(context);
        GaLog.a("GameKeysHelper", " openSub newGameKeys = " + Integer.toBinaryString(c2));
        e(context, c2);
    }

    public void e(Context context, int i2) {
        GaLog.a("GameKeysHelper", " setGameKeysDBValue = " + i2);
        Settings.Global.putInt(context.getContentResolver(), GameKeysHelperWrapper.SETTING_GAME_MODE_STATUS, i2);
    }
}
