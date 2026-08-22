package cn.nubia.chatassistant.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import java.io.IOException;

/* loaded from: classes.dex */
public class MusicManagerUtils {
    private static final String TAG = "MusicManagerUtils";
    private static volatile MusicManagerUtils musicManagerUtils;
    private MediaPlayer mediaPlayer;

    private MusicManagerUtils() {
    }

    public static MusicManagerUtils getInstance() {
        if (musicManagerUtils == null) {
            synchronized (MusicManagerUtils.class) {
                if (musicManagerUtils == null) {
                    musicManagerUtils = new MusicManagerUtils();
                }
            }
        }
        return musicManagerUtils;
    }

    public synchronized int getCurrentPosition() {
        int currentPosition;
        MediaPlayer mediaPlayer = this.mediaPlayer;
        currentPosition = mediaPlayer != null ? mediaPlayer.getCurrentPosition() : -1;
        LogUtils.i(TAG, "getCurrentPosition : " + currentPosition);
        return currentPosition;
    }

    public synchronized int getDuration() {
        int duration;
        MediaPlayer mediaPlayer = this.mediaPlayer;
        duration = mediaPlayer != null ? mediaPlayer.getDuration() : -1;
        LogUtils.i(TAG, "getDuration : " + duration);
        return duration;
    }

    public synchronized boolean isPlay() {
        MediaPlayer mediaPlayer;
        LogUtils.i(TAG, "isPlay");
        mediaPlayer = this.mediaPlayer;
        return mediaPlayer != null ? mediaPlayer.isPlaying() : false;
    }

    public synchronized void pause() {
        LogUtils.i(TAG, "reStartPlay");
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public synchronized void pausePlay() {
        LogUtils.i(TAG, "pausePlay");
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public synchronized void reStartPlay() {
        LogUtils.i(TAG, "reStartPlay");
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public synchronized void startPlay(Context context, String str, MediaPlayer.OnCompletionListener onCompletionListener) {
        LogUtils.i(TAG, "startPlay");
        stopPlay();
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        try {
            AssetFileDescriptor openFd = context.getAssets().openFd(str);
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
            this.mediaPlayer.prepare();
            this.mediaPlayer.setOnCompletionListener(onCompletionListener);
            this.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void startPlay(String str, MediaPlayer.OnCompletionListener onCompletionListener) {
        LogUtils.i(TAG, "startPlay");
        stopPlay();
        if (this.mediaPlayer == null) {
            this.mediaPlayer = new MediaPlayer();
        }
        try {
            this.mediaPlayer.reset();
            this.mediaPlayer.setDataSource(str);
            this.mediaPlayer.prepare();
            this.mediaPlayer.setOnCompletionListener(onCompletionListener);
            this.mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stopPlay() {
        LogUtils.i(TAG, "stopPlay");
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mediaPlayer.release();
            this.mediaPlayer = null;
        }
    }
}
