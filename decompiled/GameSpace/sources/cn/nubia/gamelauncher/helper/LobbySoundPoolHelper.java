package cn.nubia.gamelauncher.helper;

import android.util.Log;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class LobbySoundPoolHelper {
    private SoundPoolHelper mHelper;
    private int mResId;

    private static class SoundPoolHelperHolder {
        public static final LobbySoundPoolHelper INSTANCE = new LobbySoundPoolHelper();

        private SoundPoolHelperHolder() {
        }
    }

    private LobbySoundPoolHelper() {
        this.mResId = R.raw.lobby_click;
        init();
    }

    public static LobbySoundPoolHelper getInstance() {
        return SoundPoolHelperHolder.INSTANCE;
    }

    private void init() {
        this.mHelper = new SoundPoolHelper(this.mResId);
    }

    public void play() {
        if (this.mHelper == null) {
            init();
        }
        this.mHelper.playSync();
        Log.d("play", "play() " + Log.getStackTraceString(new Throwable()));
    }

    public void release() {
        SoundPoolHelper soundPoolHelper = this.mHelper;
        if (soundPoolHelper == null) {
            return;
        }
        soundPoolHelper.release();
    }
}
