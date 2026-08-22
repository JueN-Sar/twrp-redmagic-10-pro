package cn.nubia.gamelauncher.xgravitation;

import android.graphics.SurfaceTexture;

/* loaded from: classes.dex */
public interface IVideoPlayerController {
    void release();

    void restart(boolean z, SurfaceTexture surfaceTexture);

    void stop();
}
