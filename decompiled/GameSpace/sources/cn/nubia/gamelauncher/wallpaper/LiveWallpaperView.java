package cn.nubia.gamelauncher.wallpaper;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import cn.nubia.common.wallpaper.WallpaperManager;
import cn.nubia.gamelauncher.util.WorkThread;
import java.io.IOException;

/* loaded from: classes.dex */
public class LiveWallpaperView extends GLSurfaceView implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    public static final String TAG = "GSWallpaper";
    Context mContext;
    private boolean mIsLooping;
    private MediaPlayer mMediaPlayer;
    WallpaperRenderer mRenderer;
    private Runnable mRunnable;
    private String mUrl;

    public LiveWallpaperView(Context context) {
        this(context, null);
    }

    public LiveWallpaperView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsLooping = true;
        this.mUrl = null;
        this.mContext = context.getApplicationContext();
    }

    private void initRenderer() {
        if (this.mRenderer != null) {
            return;
        }
        this.mRenderer = new WallpaperRenderer(this.mContext, this.mMediaPlayer);
    }

    public void initMediaPlayer() {
        Log.i("GSWallpaper", "live - initMediaPlayer()");
        stopPlayback();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.reset();
        this.mMediaPlayer.setOnCompletionListener(this);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnErrorListener(this);
        this.mMediaPlayer.setLooping(this.mIsLooping);
        updateDataSource(this.mMediaPlayer);
        this.mMediaPlayer.setScreenOnWhilePlaying(true);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i("GSWallpaper", "live - onCompletion()");
        Runnable runnable = this.mRunnable;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.i("GSWallpaper", "live - onError() what : " + i);
        return false;
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        Log.i("GSWallpaper", "live - onPause()");
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.i("GSWallpaper", "live - onPrepared()");
        this.mMediaPlayer.start();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        Log.i("GSWallpaper", "live - onResume()");
    }

    public void release() {
        Log.d("GSWallpaper", "live - release()");
        stopPlayback();
        WallpaperRenderer wallpaperRenderer = this.mRenderer;
        if (wallpaperRenderer != null) {
            wallpaperRenderer.release();
            this.mRenderer = null;
        }
    }

    public void releaseOnWorkThread() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.wallpaper.LiveWallpaperView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LiveWallpaperView.this.release();
            }
        });
    }

    public void startLoad(String str, boolean z) {
        this.mUrl = str;
        this.mIsLooping = z;
        setEGLContextClientVersion(2);
        initMediaPlayer();
        initRenderer();
        setRenderer(this.mRenderer);
    }

    public void startLoad(String str, boolean z, Runnable runnable) {
        this.mRunnable = runnable;
        startLoad(str, z);
    }

    public void stopPlayback() {
        Log.i("GSWallpaper", "live - stopPlayback()");
        if (this.mMediaPlayer == null) {
            this.mMediaPlayer = new MediaPlayer();
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            Log.i("GSWallpaper", "live - stopPlayback() ---> mMediaPlayer.release()");
            this.mMediaPlayer = null;
        }
    }

    public void updateDataSource(MediaPlayer mediaPlayer) {
        try {
            String str = this.mUrl;
            if (str == null) {
                str = WallpaperManager.getInstance().getWallpaperUrl();
            }
            AssetFileDescriptor openFd = this.mContext.getAssets().openFd(str);
            Log.d("GSWallpaper", "live - updateDataSource() url : " + str + ", openFd : " + openFd);
            mediaPlayer.setDataSource(openFd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
