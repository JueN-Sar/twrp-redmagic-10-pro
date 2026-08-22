package cn.nubia.gamelauncher.helper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import androidx.media3.common.MimeTypes;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.WorkThread;

/* loaded from: classes.dex */
public class SoundPoolHelper {
    public static final String TAG = "Sound";
    public static final String VOL_RECEIVER_ACTION = "android.media.VOLUME_CHANGED_ACTION";
    private Context mContext;
    private int mResId;
    private SoundPool mSoundPool;
    private int mSound = -1;
    private float mVolRatio = 1.0f;
    private VolRatioChangeObserver mVolRatioChangeObserver = null;

    private class VolRatioChangeObserver extends BroadcastReceiver {
        private VolRatioChangeObserver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (SoundPoolHelper.VOL_RECEIVER_ACTION.equals(intent.getAction())) {
                SoundPoolHelper.this.updateVolRatio();
            }
        }
    }

    public SoundPoolHelper(int i) {
        this.mResId = i;
        init();
        registerVolReceive();
    }

    private void createSoundPool(Context context, final int i) {
        this.mContext = context;
        initSound();
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.helper.SoundPoolHelper$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                SoundPoolHelper.this.m318xc449235f(i);
            }
        });
        updateVolRatio();
    }

    private void init() {
        createSoundPool(GameLauncherApplication.getAppContext(), this.mResId);
    }

    private void initSound() {
        SoundPool.Builder builder = new SoundPool.Builder();
        builder.setMaxStreams(2);
        AudioAttributes.Builder builder2 = new AudioAttributes.Builder();
        builder2.setLegacyStreamType(1);
        builder.setAudioAttributes(builder2.build());
        this.mSoundPool = builder.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: load, reason: merged with bridge method [inline-methods] */
    public void m318xc449235f(int i) {
        if (this.mSoundPool == null) {
            initSound();
        }
        this.mSound = this.mSoundPool.load(this.mContext, i, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void play() {
        if (this.mSoundPool == null || this.mSound < 0) {
            init();
        }
        updateVolRatio();
        SoundPool soundPool = this.mSoundPool;
        int i = this.mSound;
        float f = this.mVolRatio;
        soundPool.play(i, f, f, 0, 0, 1.0f);
        LogUtil.d(TAG, "play() mSound : " + this.mSound);
    }

    private void registerVolReceive() {
        if (this.mVolRatioChangeObserver == null) {
            this.mVolRatioChangeObserver = new VolRatioChangeObserver();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(VOL_RECEIVER_ACTION);
        this.mContext.registerReceiver(this.mVolRatioChangeObserver, intentFilter, 2);
    }

    private void unregisterVolReceive() {
        VolRatioChangeObserver volRatioChangeObserver = this.mVolRatioChangeObserver;
        if (volRatioChangeObserver == null) {
            return;
        }
        this.mContext.unregisterReceiver(volRatioChangeObserver);
        this.mVolRatioChangeObserver = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateVolRatio() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        AudioManager audioManager = (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        this.mVolRatio = audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3);
    }

    public void playSync() {
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.helper.SoundPoolHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SoundPoolHelper.this.play();
            }
        });
    }

    public void release() {
        unregisterVolReceive();
        SoundPool soundPool = this.mSoundPool;
        if (soundPool == null) {
            return;
        }
        soundPool.release();
        this.mSoundPool = null;
    }
}
