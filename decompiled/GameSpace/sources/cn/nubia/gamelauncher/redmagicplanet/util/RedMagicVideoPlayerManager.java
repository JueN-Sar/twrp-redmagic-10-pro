package cn.nubia.gamelauncher.redmagicplanet.util;

import android.content.Context;
import android.util.Log;
import cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer;

/* loaded from: classes.dex */
public class RedMagicVideoPlayerManager {
    private static final String TAG = "RedMagicVideoPlayerManager";
    private static RedMagicVideoPlayerManager sInstance;
    private RedMagicVideoPlayer mVideoPlayer;
    private boolean mViewPagerOnTouch;

    private RedMagicVideoPlayerManager() {
    }

    public static synchronized RedMagicVideoPlayerManager instance() {
        RedMagicVideoPlayerManager redMagicVideoPlayerManager;
        synchronized (RedMagicVideoPlayerManager.class) {
            if (sInstance == null) {
                sInstance = new RedMagicVideoPlayerManager();
            }
            redMagicVideoPlayerManager = sInstance;
        }
        return redMagicVideoPlayerManager;
    }

    public RedMagicVideoPlayer getCurrentRedMagicVideoPlayer() {
        return this.mVideoPlayer;
    }

    public boolean getViewPagerOnTouch() {
        return this.mViewPagerOnTouch;
    }

    public boolean handleInvalidVideoPlayer(Context context) {
        Log.i(TAG, "handleInvalidVideoPlayer: start");
        if (this.mVideoPlayer != null) {
            return false;
        }
        this.mVideoPlayer = new RedMagicVideoPlayer(context);
        return true;
    }

    public boolean hasValidVideoPlayer(Context context) {
        Log.i(TAG, "hasValidVideoPlayer: start");
        return this.mVideoPlayer != null;
    }

    public boolean onBackPressed() {
        if (this.mVideoPlayer == null) {
            LogUtil.d(TAG, "onBackPressed: VideoPlayer not init");
            return false;
        }
        LogUtil.d(TAG, "onBackPressed: ");
        if (this.mVideoPlayer.isFullScreen()) {
            return this.mVideoPlayer.exitFullScreen();
        }
        return false;
    }

    public void releaseRedMagicVideoPlayer() {
        LogUtil.d(TAG, "releaseRedMagicVideoPlayer: ");
        RedMagicVideoPlayer redMagicVideoPlayer = this.mVideoPlayer;
        if (redMagicVideoPlayer != null) {
            redMagicVideoPlayer.release();
            this.mVideoPlayer = null;
        }
    }

    public void resumeRedMagicVideoPlayer() {
        RedMagicVideoPlayer redMagicVideoPlayer = this.mVideoPlayer;
        if (redMagicVideoPlayer == null || !redMagicVideoPlayer.isPaused()) {
            return;
        }
        this.mVideoPlayer.restart();
    }

    public void setCurrentRedMagicVideoPlayer(RedMagicVideoPlayer redMagicVideoPlayer) {
        if (this.mVideoPlayer != redMagicVideoPlayer) {
            releaseRedMagicVideoPlayer();
            this.mVideoPlayer = redMagicVideoPlayer;
        }
    }

    public void setViewPagerOnTouch(boolean z) {
        this.mViewPagerOnTouch = z;
        RedMagicVideoPlayer redMagicVideoPlayer = this.mVideoPlayer;
        if (redMagicVideoPlayer != null && (redMagicVideoPlayer.isPlaying() || this.mVideoPlayer.isPreparing() || this.mVideoPlayer.isPrepared())) {
            this.mViewPagerOnTouch = false;
        }
        RedMagicVideoPlayer redMagicVideoPlayer2 = this.mVideoPlayer;
        if (redMagicVideoPlayer2 == null || redMagicVideoPlayer2.getControllerPagerSize() >= 2) {
            return;
        }
        this.mViewPagerOnTouch = false;
    }

    public void suspendRedMagicVideoPlayer() {
        LogUtil.d(TAG, "suspendRedMagicVideoPlayer: ");
        RedMagicVideoPlayer redMagicVideoPlayer = this.mVideoPlayer;
        if (redMagicVideoPlayer == null || !redMagicVideoPlayer.isPlaying()) {
            return;
        }
        this.mVideoPlayer.pause();
    }
}
