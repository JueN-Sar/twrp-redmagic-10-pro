package com.zte.gameassist.lowsugar.detect;

import android.graphics.Bitmap;
import androidx.annotation.VisibleForTesting;
import com.zte.gameassist.lowsugar.common.DetectParam;
import java.util.List;

/* loaded from: classes2.dex */
public interface ILowSugarDetect {

    public interface DetectCallback {
        boolean a(int i2, int i3, Bitmap bitmap, DetectParam detectParam);
    }

    void a();

    void b();

    void c();

    List d();

    void g(DetectCallback detectCallback);

    @VisibleForTesting
    int getSceneIndex();

    @VisibleForTesting
    boolean isDetecting();
}
