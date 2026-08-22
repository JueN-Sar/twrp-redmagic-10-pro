package cn.nubia.gamelauncher.redmagicplanet.ui;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer;
import cn.nubia.gamelauncher.redmagicplanet.util.CommonUtil;
import cn.nubia.gamelauncher.redmagicplanet.util.LogUtil;
import cn.nubia.gamelauncher.redmagicplanet.util.RedMagicVideoPlayerManager;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public class RedMagicVideoPlayer extends FrameLayout implements IRedMagicVideoPlayer, TextureView.SurfaceTextureListener {
    public static final int MODE_FULL_SCREEN = 11;
    public static final int MODE_NORMAL = 10;
    private static final int MSG_PREPARE_TIMEOUT = 0;
    public static final int STATE_COMPLETED = 5;
    public static final int STATE_ERROR = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    public static final int STATE_STOP_EXIT = 6;
    private static final String TAG = "RedMagicVideoPlayer";
    private static final int TIMEOUT_PREPARE = 3500;
    private static boolean mFlag;
    int MEDIA_INFO_VIDEO_RENDERING_START;
    int MEDIA_INFO_VIDEO_ROTATION_CHANGED;
    private boolean continueFromLastPosition;
    private FrameLayout mContainer;
    private Context mContext;
    private CommonVideoPlayerController mController;
    private int mCurrentMode;
    private int mCurrentState;
    private FrameLayout.LayoutParams mFullScreenParams;
    private Handler mHandler;
    private Map<String, String> mHeaders;
    private MediaPlayer mMediaPlayer;
    private FrameLayout.LayoutParams mNormalParams;
    private MediaPlayer.OnBufferingUpdateListener mOnBufferingUpdateListener;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnInfoListener mOnInfoListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    private MediaPlayer.OnVideoSizeChangedListener mOnVideoSizeChangedListener;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private RedMagicTextureView mTextureView;
    private String mUrl;
    RedMagicViewOutlineProvider mVideoViewOutlineProvider;
    private int mViewPagerPosition;
    private int skipToPosition;

    public RedMagicVideoPlayer(Context context) {
        this(context, null);
    }

    public RedMagicVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.MEDIA_INFO_VIDEO_RENDERING_START = 3;
        this.MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;
        this.mCurrentState = 0;
        this.mCurrentMode = 10;
        this.continueFromLastPosition = true;
        this.mHandler = new Handler() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what != 0) {
                    return;
                }
                if (RedMagicVideoPlayer.this.isPreparing() || RedMagicVideoPlayer.this.isPrepared()) {
                    if (RedMagicVideoPlayer.this.mMediaPlayer != null) {
                        RedMagicVideoPlayer.this.mMediaPlayer.stop();
                    }
                    RedMagicVideoPlayer.this.mCurrentState = -1;
                    RedMagicVideoPlayer.this.mController.onPlayStateChanged(RedMagicVideoPlayer.this.mCurrentState);
                }
            }
        };
        this.mOnPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                RedMagicVideoPlayer.this.mCurrentState = 2;
                RedMagicVideoPlayer.this.mController.onPlayStateChanged(RedMagicVideoPlayer.this.mCurrentState);
                RedMagicVideoPlayer.this.mController.setPlayBackSeekBarToMax(RedMagicVideoPlayer.this.getDuration());
                LogUtil.d(RedMagicVideoPlayer.TAG, "onPrepared ——> STATE_PREPARED");
                mediaPlayer.start();
                if (RedMagicVideoPlayer.this.skipToPosition != 0) {
                    mediaPlayer.seekTo(RedMagicVideoPlayer.this.skipToPosition);
                }
            }
        };
        this.mOnVideoSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.3
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                RedMagicVideoPlayer.this.mTextureView.adaptVideoSize(i, i2);
                LogUtil.d(RedMagicVideoPlayer.TAG, "onVideoSizeChanged ——> width：" + i + "， height：" + i2);
            }
        };
        this.mOnCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.4
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                RedMagicVideoPlayer.this.mCurrentState = 5;
                RedMagicVideoPlayer.this.mController.onPlayStateChanged(RedMagicVideoPlayer.this.mCurrentState);
                LogUtil.d(RedMagicVideoPlayer.TAG, "onCompletion ——> STATE_COMPLETED");
                RedMagicVideoPlayer.this.mContainer.setKeepScreenOn(false);
            }
        };
        this.mOnErrorListener = new MediaPlayer.OnErrorListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                LogUtil.d(RedMagicVideoPlayer.TAG, "onError ———— what：" + i + ", extra: " + i2);
                if (i == -38 || i == Integer.MIN_VALUE || i2 == -38 || i2 == Integer.MIN_VALUE) {
                    return true;
                }
                RedMagicVideoPlayer.this.mCurrentState = -1;
                RedMagicVideoPlayer.this.mController.onPlayStateChanged(RedMagicVideoPlayer.this.mCurrentState);
                return true;
            }
        };
        this.mOnInfoListener = new MediaPlayer.OnInfoListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.6
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                LogUtil.d(RedMagicVideoPlayer.TAG, "onInfo what : " + i);
                if (i == RedMagicVideoPlayer.this.MEDIA_INFO_VIDEO_RENDERING_START) {
                    RedMagicVideoPlayer.this.mHandler.removeMessages(0);
                    RedMagicVideoPlayer.this.mCurrentState = 3;
                    RedMagicVideoPlayer.this.mController.onPlayStateChanged(RedMagicVideoPlayer.this.mCurrentState);
                    LogUtil.d(RedMagicVideoPlayer.TAG, "onInfo ——> MEDIA_INFO_VIDEO_RENDERING_START：STATE_PLAYING");
                    return true;
                }
                if (i != RedMagicVideoPlayer.this.MEDIA_INFO_VIDEO_ROTATION_CHANGED) {
                    LogUtil.d(RedMagicVideoPlayer.TAG, "onInfo ——> what：" + i);
                    return true;
                }
                if (RedMagicVideoPlayer.this.mTextureView == null) {
                    return true;
                }
                RedMagicVideoPlayer.this.mTextureView.setRotation(i2);
                LogUtil.d(RedMagicVideoPlayer.TAG, "rotation change " + i2);
                return true;
            }
        };
        this.mOnBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: cn.nubia.gamelauncher.redmagicplanet.ui.RedMagicVideoPlayer.7
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                LogUtil.d(RedMagicVideoPlayer.TAG, "onBufferingUpdate: percent : " + i);
            }
        };
        this.mContext = context;
        init();
    }

    private void addTextureView() {
        LogUtil.d(TAG, "addTextureView: ");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 49);
        layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_preview_video_width);
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_preview_video_normal_height);
        this.mContainer.addView(this.mTextureView, 0, layoutParams);
    }

    private boolean executeExitFullScreen() {
        if (this.mCurrentMode != 11) {
            LogUtil.d(TAG, "exitFullScreen: Ignore");
            return false;
        }
        CommonUtil.scanForActivity(this.mContext).setRequestedOrientation(0);
        ((ViewGroup) CommonUtil.scanForActivity(this.mContext).findViewById(android.R.id.content)).removeView(this.mContainer);
        this.mContainer.removeView(this.mController);
        setNormalLayoutParams();
        addView(this.mController, new FrameLayout.LayoutParams(-1, -1));
        this.mController.addTextureView(this.mViewPagerPosition);
        this.mCurrentMode = 10;
        this.mController.onPlayModeChanged(10);
        this.mController.onPlayStateChanged(this.mCurrentState);
        LogUtil.d(TAG, "exitFullScreen: Success MODE_NORMAL");
        return true;
    }

    private void init() {
        setBackground(this.mContext.getDrawable(R.drawable.red_magic_default_bg));
        this.mContainer = new FrameLayout(this.mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_preview_video_width);
        layoutParams.height = this.mContext.getResources().getDimensionPixelSize(R.dimen.red_magic_preview_video_normal_height);
        this.mContainer.setLayoutParams(layoutParams);
    }

    private void initMediaPlayer() {
        if (this.mMediaPlayer == null) {
            LogUtil.d(TAG, "initMediaPlayer: ");
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mMediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
        }
    }

    private void initTextureView() {
        if (this.mTextureView == null) {
            LogUtil.d(TAG, "initTextureView: ");
            RedMagicTextureView redMagicTextureView = new RedMagicTextureView(this.mContext);
            this.mTextureView = redMagicTextureView;
            redMagicTextureView.setSurfaceTextureListener(this);
        }
    }

    private void openMediaPlayer() {
        LogUtil.d(TAG, "openMediaPlayer: mUrl : " + this.mUrl);
        this.mHandler.sendEmptyMessageDelayed(0, 3500L);
        this.mContainer.setKeepScreenOn(true);
        this.mMediaPlayer.setOnPreparedListener(this.mOnPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.mOnVideoSizeChangedListener);
        this.mMediaPlayer.setOnCompletionListener(this.mOnCompletionListener);
        this.mMediaPlayer.setOnErrorListener(this.mOnErrorListener);
        this.mMediaPlayer.setOnInfoListener(this.mOnInfoListener);
        this.mMediaPlayer.setOnBufferingUpdateListener(this.mOnBufferingUpdateListener);
        try {
            this.mMediaPlayer.setDataSource(this.mContext.getApplicationContext(), Uri.parse(this.mUrl), this.mHeaders);
            if (this.mSurface == null) {
                this.mSurface = new Surface(this.mSurfaceTexture);
            }
            this.mMediaPlayer.setSurface(this.mSurface);
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 1;
            this.mController.onPlayStateChanged(1);
            LogUtil.d(TAG, "STATE_PREPARING");
        } catch (IOException | IllegalStateException e) {
            e.printStackTrace();
            this.mHandler.removeMessages(0);
            this.mCurrentState = -1;
            this.mController.onPlayStateChanged(-1);
            LogUtil.e(TAG, "media player is error", e);
        }
    }

    private void setFullScreenLayoutParams() {
        LogUtil.d(TAG, "setFullScreenLayoutParams: ");
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mFullScreenParams = layoutParams;
        layoutParams.gravity = 17;
        this.mContainer.updateViewLayout(this.mTextureView, this.mFullScreenParams);
    }

    private void setNormalLayoutParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.mNormalParams = layoutParams;
        layoutParams.height = this.mContext.getResources().getDimensionPixelOffset(R.dimen.red_magic_preview_video_normal_height);
        LogUtil.d(TAG, "setNormalLayoutParams: ");
        this.mNormalParams.width = -1;
        this.mNormalParams.gravity = 49;
        this.mContainer.updateViewLayout(this.mTextureView, this.mNormalParams);
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void continueFromLastPosition(boolean z) {
        this.continueFromLastPosition = z;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void enterFullScreen() {
        if (this.mCurrentMode == 11) {
            return;
        }
        this.mCurrentMode = 11;
        this.mController.removeTextureView();
        CommonUtil.scanForActivity(this.mContext).setRequestedOrientation(0);
        ViewGroup viewGroup = (ViewGroup) CommonUtil.scanForActivity(this.mContext).findViewById(android.R.id.content);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        removeView(this.mController);
        this.mContainer.addView(this.mController);
        viewGroup.addView(this.mContainer, layoutParams);
        setFullScreenLayoutParams();
        this.mController.onPlayModeChanged(this.mCurrentMode);
        this.mController.onPlayStateChanged(this.mCurrentState);
        LogUtil.d(TAG, "MODE_FULL_SCREEN");
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean exitFullScreen() {
        if (mFlag) {
            return false;
        }
        return executeExitFullScreen();
    }

    public int getControllerPagerSize() {
        CommonVideoPlayerController commonVideoPlayerController = this.mController;
        if (commonVideoPlayerController != null) {
            return commonVideoPlayerController.getPagerSize();
        }
        return 0;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public int getDuration() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public FrameLayout getmContainer() {
        return this.mContainer;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isCompleted() {
        return this.mCurrentState == 5;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isError() {
        return this.mCurrentState == -1;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isFullScreen() {
        return this.mCurrentMode == 11;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isIdle() {
        return this.mCurrentState == 0;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isNormal() {
        return this.mCurrentMode == 10;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isPaused() {
        return this.mCurrentState == 4;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isPlaying() {
        return this.mCurrentState == 3;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isPrepared() {
        return this.mCurrentState == 2;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isPreparing() {
        return this.mCurrentState == 1;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public boolean isStopExit() {
        return this.mCurrentState == 6;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        LogUtil.d(TAG, "onSurfaceTextureAvailable: mSurfaceTexture : " + this.mSurfaceTexture);
        SurfaceTexture surfaceTexture2 = this.mSurfaceTexture;
        if (surfaceTexture2 != null) {
            this.mTextureView.setSurfaceTexture(surfaceTexture2);
            return;
        }
        this.mSurfaceTexture = surfaceTexture;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            openMediaPlayer();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        LogUtil.d(TAG, "onSurfaceTextureDestroyed " + (this.mSurfaceTexture == null));
        return this.mSurfaceTexture == null;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        CommonVideoPlayerController commonVideoPlayerController = this.mController;
        if (commonVideoPlayerController == null || commonVideoPlayerController.getPlayBackSeekBarIsTracking()) {
            return;
        }
        this.mController.updateProgress();
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void pause() {
        LogUtil.d(TAG, "pause: mCurrentState : " + this.mCurrentState);
        if (this.mCurrentState == 3) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
            this.mController.onPlayStateChanged(4);
            LogUtil.d(TAG, "STATE_PAUSED");
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void release() {
        LogUtil.d(TAG, "release: ");
        if (isFullScreen()) {
            exitFullScreen();
        }
        this.mCurrentMode = 10;
        releasePlayer();
        CommonVideoPlayerController commonVideoPlayerController = this.mController;
        if (commonVideoPlayerController != null) {
            commonVideoPlayerController.reset();
            this.mController.fragmentOnPause();
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void releasePlayer() {
        LogUtil.d(TAG, "releasePlayer: ");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(0);
        }
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mMediaPlayer = null;
        }
        this.mController.removeTextureView();
        this.mContainer.removeView(this.mTextureView);
        Surface surface = this.mSurface;
        if (surface != null) {
            surface.release();
            this.mSurface = null;
        }
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        this.mCurrentState = 0;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void restart() {
        LogUtil.d(TAG, "restart: mCurrentState : " + this.mCurrentState);
        int i = this.mCurrentState;
        if (i == 4) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            this.mController.onPlayStateChanged(3);
            LogUtil.d(TAG, "STATE_PLAYING");
            return;
        }
        if (i == 5 || i == -1 || i == 6) {
            this.mMediaPlayer.reset();
            this.mController.addTextureView(this.mViewPagerPosition);
            openMediaPlayer();
        }
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void seekTo(int i) {
        LogUtil.d(TAG, "seekTo:pos : " + i);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i, 3);
        }
    }

    public void setController(CommonVideoPlayerController commonVideoPlayerController) {
        removeView(this.mController);
        this.mController = commonVideoPlayerController;
        commonVideoPlayerController.reset();
        this.mController.setRedMagicVideoPlayer(this);
        addView(this.mController, new FrameLayout.LayoutParams(-1, -1));
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void setUp(String str, Map<String, String> map) {
        this.mUrl = str;
        this.mHeaders = map;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void setViewPagerPosition(int i) {
        this.mViewPagerPosition = i;
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void start() {
        LogUtil.d(TAG, "start: mCurrentState : " + this.mCurrentState);
        if (this.mCurrentState != 0) {
            LogUtil.d(TAG, "mCurrentState is not idle");
            return;
        }
        RedMagicVideoPlayerManager.instance().setCurrentRedMagicVideoPlayer(this);
        this.mController.addTextureView(this.mViewPagerPosition);
        initMediaPlayer();
        initTextureView();
        addTextureView();
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void start(int i) {
        LogUtil.d(TAG, "start: position : " + i);
        this.skipToPosition = i;
        start();
    }

    @Override // cn.nubia.gamelauncher.redmagicplanet.IRedMagicVideoPlayer
    public void stop() {
        MediaPlayer mediaPlayer;
        if ((isPlaying() || isPaused()) && (mediaPlayer = this.mMediaPlayer) != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.reset();
            this.mCurrentState = 6;
            this.mController.onPlayStateChanged(6);
        }
    }
}
