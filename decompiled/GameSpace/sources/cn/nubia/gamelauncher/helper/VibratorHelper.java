package cn.nubia.gamelauncher.helper;

import android.content.Context;
import android.os.Vibrator;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;
import cn.nubia.gamelauncher.util.WorkThread;

/* loaded from: classes.dex */
public class VibratorHelper {
    public static final String TAG = "Vibrator";
    private Vibrator mVibrator;

    private static class VibratorHelperHolder {
        public static final VibratorHelper INSTANCE = new VibratorHelper();

        private VibratorHelperHolder() {
        }
    }

    private VibratorHelper() {
        init();
    }

    private void doSystemVibrate() {
        LogUtil.d(TAG, "doSystemVibrate()");
        if (this.mVibrator == null) {
            init();
        }
        try {
            this.mVibrator.vibrate(50L);
        } catch (Exception e) {
            LogUtil.e(TAG, "doSystemVibrate() vibrate error: " + e);
        }
    }

    public static VibratorHelper getInstance() {
        return VibratorHelperHolder.INSTANCE;
    }

    private void init() {
        Context appContext = GameLauncherApplication.getAppContext();
        if (appContext == null) {
            return;
        }
        this.mVibrator = (Vibrator) appContext.getSystemService("vibrator");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void vibrate() {
        LogUtil.d(TAG, "vibrate()");
        if (Util.doMyOsVibrateOfHe(R.raw.click)) {
            return;
        }
        doSystemVibrate();
    }

    public void vibrateSync() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.helper.VibratorHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VibratorHelper.this.vibrate();
            }
        });
    }
}
