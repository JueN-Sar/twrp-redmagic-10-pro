package cn.nubia.screensaver.view;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import com.zte.gameassist.utils.GaLog;
import java.io.IOException;

/* loaded from: classes.dex */
public class MomentVideoView extends TextureView implements TextureView.SurfaceTextureListener {
    private static final String TAG = "MomentVideoView";
    private OnCompletionListener mCompletionListener;
    private MediaPlayer mMediaPlayer;
    private String mPath;
    private boolean mPause;
    private boolean mPrepared;
    private final MediaPlayer.OnPreparedListener mPreparedListener;
    private Surface mSurface;

    public interface OnCompletionListener {
        void onCompletion(MediaPlayer mediaPlayer);
    }

    public MomentVideoView(Context context) {
        this(context, null);
    }

    private void f() {
        if (this.mMediaPlayer == null) {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.screensaver.view.d
                @Override // android.media.MediaPlayer.OnCompletionListener
                public final void onCompletion(MediaPlayer mediaPlayer2) {
                    MomentVideoView.this.h(mediaPlayer2);
                }
            });
            this.mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: cn.nubia.screensaver.view.e
                @Override // android.media.MediaPlayer.OnErrorListener
                public final boolean onError(MediaPlayer mediaPlayer2, int i2, int i3) {
                    boolean i4;
                    i4 = MomentVideoView.this.i(mediaPlayer2, i2, i3);
                    return i4;
                }
            });
            this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
        }
    }

    private void g() {
        setSurfaceTextureListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(MediaPlayer mediaPlayer) {
        OnCompletionListener onCompletionListener = this.mCompletionListener;
        if (onCompletionListener != null) {
            onCompletionListener.onCompletion(mediaPlayer);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean i(MediaPlayer mediaPlayer, int i2, int i3) {
        GaLog.a(TAG, "set video error " + i2 + "extra:" + i3);
        OnCompletionListener onCompletionListener = this.mCompletionListener;
        if (onCompletionListener == null) {
            return true;
        }
        onCompletionListener.onCompletion(mediaPlayer);
        return true;
    }

    private void j(SurfaceTexture surfaceTexture) {
        try {
            f();
            this.mMediaPlayer.setDataSource(this.mPath);
            Surface surface = new Surface(surfaceTexture);
            this.mSurface = surface;
            this.mMediaPlayer.setSurface(surface);
            this.mMediaPlayer.setVolume(0.0f, 0.0f);
            this.mMediaPlayer.prepareAsync();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private void l() {
        this.mPrepared = false;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            GaLog.a(TAG, "media player release");
            this.mMediaPlayer = null;
        }
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
    }

    public void e(OnCompletionListener onCompletionListener) {
        this.mCompletionListener = onCompletionListener;
    }

    public void k() {
        this.mPause = true;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mMediaPlayer.pause();
        GaLog.a(TAG, "media player pause");
    }

    public void m() {
        this.mPause = false;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !this.mPrepared) {
            return;
        }
        mediaPlayer.start();
        GaLog.a(TAG, "media player start");
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        j(surfaceTexture);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        l();
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public MomentVideoView(Context context, AttributeSet attributeSet) {
        this(context, null, 0);
    }

    public MomentVideoView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mPause = true;
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: cn.nubia.screensaver.view.MomentVideoView.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                MomentVideoView.this.mPrepared = true;
                if (!MomentVideoView.this.mPause) {
                    mediaPlayer.start();
                }
                GaLog.a(MomentVideoView.TAG, "media player  prepared start " + MomentVideoView.this.mPause);
            }
        };
        g();
    }
}
