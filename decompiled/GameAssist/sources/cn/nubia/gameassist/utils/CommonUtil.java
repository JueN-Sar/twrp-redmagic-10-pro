package cn.nubia.gameassist.utils;

import android.content.Context;
import com.zte.gameassist.config.ZteFeature;

/* loaded from: classes.dex */
public class CommonUtil {
    public static final int a(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static boolean b() {
        return ZteFeature.IS_INTER_VERSION;
    }
}
