package cn.nubia.tgk;

import android.app.Activity;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.util.Log;
import cn.nubia.gamecenter.settings.compatible.GameModeHelper;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.upgrade.UpgradeUtil;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class TgkSettings extends Activity {
    private static final String GAME_MODE_CLASS_NAME = "android.hardware.input.InputManager";
    private static final String TAG = "TgkSettings";

    public static int getDefaultValue() {
        try {
            Class<?> cls = Class.forName("cn.nubia.game.GameModeHelper");
            Log.e(TAG, "getDefaultValue in 111");
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                Log.e(TAG, "GameModeHelper method[" + i + "]=" + declaredMethods[i].getName());
            }
            Method declaredMethod = cls.getDeclaredMethod("getDefaultValue", new Class[0]);
            declaredMethod.setAccessible(true);
            Log.e(TAG, "getDefaultValue in ret=" + ((Integer) declaredMethod.invoke(cls.newInstance(), new Object[0])).intValue());
            return 2;
        } catch (Exception e) {
            e.printStackTrace();
            return 2;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_touping);
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume in");
        Log.e(TAG, "onResume value=" + GameModeHelper.getDefaultValue());
        getDefaultValue();
        Log.e(TAG, "onResume packageName=" + UpgradeUtil.getCurrentTopPkgQ());
        InputManager inputManager = (InputManager) getSystemService("input");
        try {
            Log.e(TAG, "onResume in 111");
            Class<?> cls = Class.forName(GAME_MODE_CLASS_NAME);
            Log.e(TAG, "onResume in 222");
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                Log.e(TAG, "input method[" + i + "]=" + declaredMethods[i].getName());
            }
            Method declaredMethod = cls.getDeclaredMethod("enableTgkDrive", Boolean.TYPE);
            Log.e(TAG, "onResume in 333");
            declaredMethod.setAccessible(true);
            Log.e(TAG, "onResume in 444");
            declaredMethod.invoke(inputManager, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
