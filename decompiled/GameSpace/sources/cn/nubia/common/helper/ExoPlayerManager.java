package cn.nubia.common.helper;

import android.net.Uri;
import android.util.Log;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import cn.nubia.common.CommonApplication;

/* loaded from: classes.dex */
public class ExoPlayerManager implements Player.Listener {
    private final String TAG;
    boolean mIsMuted;
    ExoPlayer mPlayer;
    int mRepeatMode;
    String mStartTag;
    Uri mUri;

    private static class ExoPlayerHolder {
        public static final ExoPlayerManager INSTANCE = new ExoPlayerManager();

        private ExoPlayerHolder() {
        }
    }

    private ExoPlayerManager() {
        this.TAG = "ExoManager";
        this.mStartTag = "";
        initPlayer();
    }

    public static ExoPlayerManager getInstance() {
        return ExoPlayerHolder.INSTANCE;
    }

    private void initPlayer() {
        Log.d("ExoManager", "initPlayer()");
        ExoPlayer build = new ExoPlayer.Builder(CommonApplication.getInstance().getAppContext()).build();
        this.mPlayer = build;
        build.addListener(this);
    }

    private void shotState() {
        ExoPlayer exoPlayer = this.mPlayer;
        if (exoPlayer == null) {
            return;
        }
        int playbackState = exoPlayer.getPlaybackState();
        if (playbackState == 1) {
            Log.d("ExoManager", "shotState() ---> STATE_IDLE");
            return;
        }
        if (playbackState == 2) {
            Log.d("ExoManager", "shotState() ---> STATE_BUFFERING");
        } else if (playbackState == 3) {
            Log.d("ExoManager", "shotState() ---> STATE_READY");
        } else {
            if (playbackState != 4) {
                return;
            }
            Log.d("ExoManager", "shotState() ---> STATE_ENDED");
        }
    }

    public ExoPlayer getPlayer() {
        if (this.mPlayer == null) {
            initPlayer();
        }
        return this.mPlayer;
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlaybackStateChanged(int i) {
        super.onPlaybackStateChanged(i);
        shotState();
        ExoPlayer exoPlayer = this.mPlayer;
        if (exoPlayer == null || 4 != exoPlayer.getPlaybackState()) {
            return;
        }
        releasePlayer(null);
    }

    @Override // androidx.media3.common.Player.Listener
    public void onPlayerError(PlaybackException playbackException) {
        super.onPlayerError(playbackException);
        Log.d("ExoManager", "onPlayerError() error : " + playbackException);
        releasePlayer(null);
    }

    public void pause(String str) {
        if (this.mPlayer == null || str == null || !str.equals(this.mStartTag)) {
            return;
        }
        Log.d("ExoManager", "pause() tag : " + str);
        shotState();
        if (this.mPlayer.isPlaying()) {
            this.mPlayer.pause();
        }
    }

    public void preparePlayer(Uri uri, boolean z, int i) {
        if (uri == null) {
            Log.w("ExoManager", "preparePlayer() error, the uri can not be null !");
            return;
        }
        if (this.mPlayer == null) {
            initPlayer();
        }
        Log.d("ExoManager", "preparePlayer()");
        this.mUri = uri;
        this.mIsMuted = z;
        this.mRepeatMode = i;
        this.mPlayer.setMediaItem(MediaItem.fromUri(uri));
        this.mPlayer.setPlayWhenReady(true);
        this.mPlayer.setRepeatMode(i);
        this.mPlayer.setVolume(z ? 0.0f : 1.0f);
        this.mPlayer.prepare();
    }

    public void releasePlayer(String str) {
        if (this.mPlayer != null) {
            if (str == null || str.equals(this.mStartTag)) {
                Log.d("ExoManager", "releasePlayer() tag : " + str);
                shotState();
                this.mPlayer.stop();
                this.mPlayer.release();
                this.mPlayer = null;
            }
        }
    }

    public void setRepeatMode(int i) {
        this.mRepeatMode = i;
        Log.d("ExoManager", "setRepeatMode() repeatMode : " + i);
        getPlayer().setRepeatMode(i);
    }

    public void start(String str) {
        Uri uri = this.mUri;
        if (uri == null) {
            Log.w("ExoManager", "start() error, the uri can not be null !");
            return;
        }
        if (this.mPlayer == null) {
            preparePlayer(uri, this.mIsMuted, this.mRepeatMode);
        }
        this.mStartTag = str;
        Log.d("ExoManager", "start() tag : " + str);
        shotState();
        this.mPlayer.play();
    }
}
