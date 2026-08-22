package cn.nubia.gamelauncher.aimhelper;

import android.content.Context;
import android.provider.Settings;

/* loaded from: classes.dex */
public class NubiaGameTrackManager {
    private static NubiaGameTrackManager instance = new NubiaGameTrackManager();
    private static Context sContext;

    public static NubiaGameTrackManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        if (sContext == null) {
            sContext = context;
        }
    }

    public static void updateValue(String str) {
        Context context = sContext;
        if (context != null) {
            try {
                AimConfigs aimConfigs = AimConfigs.getInstance(context);
                int color = aimConfigs.getColor(str);
                int[] iArr = AimSettingFloatingWindow.colors;
                int length = iArr.length;
                int i = 1;
                for (int i2 = 0; i2 < length && iArr[i2] != color; i2++) {
                    i++;
                }
                Settings.Global.putString(sContext.getContentResolver(), "zhunxing_helper_" + str, String.format("%d;%d;%d", Integer.valueOf(aimConfigs.getStyle(str)), Integer.valueOf(i), Integer.valueOf(aimConfigs.getSize(str))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
