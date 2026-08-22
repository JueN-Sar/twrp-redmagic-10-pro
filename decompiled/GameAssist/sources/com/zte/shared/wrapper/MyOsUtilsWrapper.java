package com.zte.shared.wrapper;

import android.graphics.Bitmap;
import android.view.SurfaceView;
import com.zte.gameassist.MyOsUtils;
import java.util.Map;

/* loaded from: classes2.dex */
public class MyOsUtilsWrapper {
    public static void drawScreenShotBitmapProx(SurfaceView surfaceView, Map<String, Object> map) {
        MyOsUtils.drawScreenShotBitmapProx(surfaceView, map);
    }

    public static Bitmap nubiaScreenshot(Map<String, Object> map) {
        return MyOsUtils.nubiaScreenshot(map);
    }
}
