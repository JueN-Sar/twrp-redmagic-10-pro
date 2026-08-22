package cn.nubia.gamelauncher.helper;

import android.app.KeyguardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import androidx.media3.common.C;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.aimhelper.ActivityUtils;
import cn.nubia.gamelauncher.util.GameKeysConstant;

/* loaded from: classes.dex */
public class BgmHelper implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    public static final String TAG = "BGM";
    private static final int TIME_OUT = 3000;
    private static final int TIME_SECONDS = 1000;
    private int mBgmSign;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private MediaPlayer mMediaPlayer;
    private SharedPreferences mSharedPref;
    Uri mUri;

    private static class BgmHelperHolder {
        public static final BgmHelper INSTANCE = new BgmHelper();

        private BgmHelperHolder() {
        }
    }

    private BgmHelper() {
        this.mUri = Uri.parse("android.resource://cn.nubia.gamelauncher/" + (GameSpaceConfig.supportCustomizedBgm() ? R.raw.bgm_sprd : R.raw.bgm));
        this.mHandlerThread = null;
        this.mHandler = null;
        this.mMediaPlayer = null;
        this.mBgmSign = 1;
        initBgmHelper();
    }

    private boolean canSwitchBgm() {
        if (!Controller.getInstance().isSpaceResumed()) {
            Log.d(TAG, "canSwitchBgm() game space is not Resumed!");
            return false;
        }
        if (!isScreenLocked()) {
            return true;
        }
        Log.d(TAG, "canSwitchBgm() false, because screen off");
        return false;
    }

    private Context getContext() {
        return GameLauncherApplication.getAppContext();
    }

    public static BgmHelper getInstance() {
        return BgmHelperHolder.INSTANCE;
    }

    private void initHandleThread() {
        if (this.mHandler == null || this.mHandlerThread == null) {
            HandlerThread handlerThread = new HandlerThread("BgmHelperEvent", 10);
            this.mHandlerThread = handlerThread;
            handlerThread.start();
            this.mHandler = new Handler(this.mHandlerThread.getLooper());
        }
    }

    private void initSwitchState() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(GameKeysConstant.IS_FIRST_DIALOG_NAME, 0);
        this.mSharedPref = sharedPreferences;
        this.mBgmSign = sharedPreferences.getInt(GameKeysConstant.BGM_STATUS, 1);
    }

    private boolean isPlayReady() {
        if (!Controller.getInstance().isSpaceResumed()) {
            Log.d(TAG, "isPlayReady() game space is not Resumed!");
            return false;
        }
        if (!isBgmSwitchOn()) {
            Log.d(TAG, "isPlayReady() false, bgm switch is " + this.mBgmSign);
            return false;
        }
        if (!isScreenLocked()) {
            return this.mMediaPlayer != null;
        }
        Log.d(TAG, "isPlayReady() false, because screen off");
        return false;
    }

    private boolean isScreenLocked() {
        return ((KeyguardManager) getContext().getSystemService("keyguard")).inKeyguardRestrictedInputMode();
    }

    private boolean isTopActivity(String str) {
        String currentTopPkg = ActivityUtils.getCurrentTopPkg(getContext());
        Log.d(TAG, " isTopActivity() ---> topActivity = " + currentTopPkg);
        if (currentTopPkg == null) {
            return false;
        }
        return currentTopPkg.contains(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordState() {
        this.mSharedPref.edit().putInt(GameKeysConstant.BGM_STATUS, this.mBgmSign).apply();
    }

    private void resumeBgm(boolean z) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            Log.d(TAG, " resumeBgm() but null or isPlaying");
            return;
        }
        if (isPlayReady()) {
            Log.d(TAG, " resumeBgm() - start()");
            this.mMediaPlayer.start();
        } else {
            Log.d(TAG, "resumeBgm() but not play ready! recheck : " + z);
            if (z) {
                this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.helper.BgmHelper$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        BgmHelper.this.m312lambda$resumeBgm$1$cnnubiagamelauncherhelperBgmHelper();
                    }
                }, 1000L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlayBgm() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            if (this.mMediaPlayer == null) {
                initMediaPlayer();
            } else {
                Log.d(TAG, "startPlayBgm()");
                resumeBgm();
            }
        }
    }

    private void switchState(int i) {
        Log.d(TAG, "switchState() state : " + i);
        if (i == this.mBgmSign) {
            return;
        }
        switchBgmState();
    }

    public void closeBgm() {
        switchState(0);
    }

    public void initBgmHelper() {
        initHandleThread();
        initSwitchState();
        Log.d(TAG, "initBgmHelper() end !");
    }

    public void initMediaPlayer() {
        Log.i(TAG, "initMediaPlayer()");
        stopPlayback();
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.mMediaPlayer = mediaPlayer;
        mediaPlayer.reset();
        this.mMediaPlayer.setAudioStreamType(3);
        this.mMediaPlayer.setOnCompletionListener(this);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnErrorListener(this);
        try {
            this.mMediaPlayer.setDataSource(getContext(), this.mUri);
            this.mMediaPlayer.prepare();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "initMediaPlayer() e : " + e.getMessage());
        }
    }

    public boolean isBgmSwitchOn() {
        return 1 == this.mBgmSign;
    }

    public boolean isPlaying() {
        Log.d(TAG, "isPlaying() ---> mMediaPlayer = " + this.mMediaPlayer);
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    /* renamed from: lambda$onCompletion$0$cn-nubia-gamelauncher-helper-BgmHelper, reason: not valid java name */
    /* synthetic */ void m311lambda$onCompletion$0$cnnubiagamelauncherhelperBgmHelper() {
        playBgm("repeat");
    }

    /* renamed from: lambda$resumeBgm$1$cn-nubia-gamelauncher-helper-BgmHelper, reason: not valid java name */
    /* synthetic */ void m312lambda$resumeBgm$1$cnnubiagamelauncherhelperBgmHelper() {
        resumeBgm(false);
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d(TAG, "onCompletion()");
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.helper.BgmHelper$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                BgmHelper.this.m311lambda$onCompletion$0$cnnubiagamelauncherhelperBgmHelper();
            }
        }, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Log.i(TAG, "onError() what : " + i);
        initMediaPlayer();
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.i(TAG, " onPrepared()");
        resumeBgm();
    }

    public void openBgm() {
        switchState(1);
    }

    public void pauseBgm() {
        if (this.mMediaPlayer == null || this.mHandler == null) {
            return;
        }
        Log.d(TAG, "pauseBgm()");
        try {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.pause();
            }
        } catch (IllegalStateException e) {
            Log.w(TAG, " pauseBgm() - e : " + e.getMessage());
        }
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void playBgm(String str) {
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.helper.BgmHelper$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                BgmHelper.this.startPlayBgm();
            }
        });
        Log.d(TAG, "start play() by : " + str);
    }

    public void release() {
        Log.d(TAG, " release() ");
        stopPlayback();
    }

    public void resumeBgm() {
        try {
            resumeBgm(true);
        } catch (Exception e) {
            Log.w(TAG, " resumeBgm() - e : " + e.getMessage());
        }
    }

    public void stopBgm() {
        if (this.mMediaPlayer == null || this.mHandler == null) {
            return;
        }
        Log.d(TAG, " stopBgm()");
        try {
            this.mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            Log.w(TAG, " stopBgm() - e : " + e.getMessage());
        }
        this.mHandler.removeCallbacksAndMessages(null);
    }

    public void stopPlayback() {
        Log.i(TAG, "stopPlayback()");
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null) {
            return;
        }
        try {
            if (mediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.reset();
            this.mMediaPlayer.release();
            Log.i(TAG, "stopPlayback() ---> mMediaPlayer.release()");
            this.mMediaPlayer = null;
        } catch (Exception e) {
            Log.d(TAG, "stopPlayback() e : " + e.getMessage());
        }
    }

    public void switchBgmState() {
        this.mBgmSign = 1 == this.mBgmSign ? 0 : 1;
        Log.d(TAG, "switchBgmState() mBgmSign = " + this.mBgmSign);
        if (!canSwitchBgm()) {
            Log.d(TAG, "switchBgmState() but canSwitchBgm false !");
            return;
        }
        if (isBgmSwitchOn()) {
            playBgm("switch");
        } else {
            pauseBgm();
        }
        this.mHandler.post(new Runnable() { // from class: cn.nubia.gamelauncher.helper.BgmHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                BgmHelper.this.recordState();
            }
        });
    }
}
