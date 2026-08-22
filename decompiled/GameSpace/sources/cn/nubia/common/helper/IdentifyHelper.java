package cn.nubia.common.helper;

import android.content.SharedPreferences;
import android.util.Log;
import cn.nubia.common.CommonApplication;
import cn.nubia.common.util.WorkThread;

/* loaded from: classes.dex */
public class IdentifyHelper {
    public static final String IDENTIFY_STATE_KEY = "Identify_state";
    public static final String SHARED_PREFERENCES_NAME = "data";
    public static final String TAG = "identify";
    private static final int VERIFY_INTERVAL_TIME = 5000;
    private long mLastVerifyTime;
    public Runnable mOpenListener;
    private int mState;

    private static class IdentifyHolder {
        public static final IdentifyHelper INSTANCE = new IdentifyHelper();

        private IdentifyHolder() {
        }
    }

    private IdentifyHelper() {
        this.mLastVerifyTime = 0L;
        this.mState = 99;
        init();
    }

    public static IdentifyHelper getInstance() {
        return IdentifyHolder.INSTANCE;
    }

    private SharedPreferences getSharedPreferences() {
        return CommonApplication.getInstance().getAppContext().getSharedPreferences("data", 0);
    }

    private void init() {
        loadIdentifyState();
    }

    private void loadIdentifyState() {
        this.mState = getSharedPreferences().getInt(IDENTIFY_STATE_KEY, -1);
    }

    private void onIdentifyOpen() {
        if (this.mOpenListener == null) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mLastVerifyTime < 5000) {
            return;
        }
        Log.d("identify", "onIdentifyOpen()");
        this.mOpenListener.run();
        this.mLastVerifyTime = currentTimeMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveState() {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(IDENTIFY_STATE_KEY, this.mState);
        edit.apply();
    }

    private void setIdentifyState(int i) {
        if (i == this.mState) {
            return;
        }
        this.mState = i;
        WorkThread.runOnWorkThread(new Runnable() { // from class: cn.nubia.common.helper.IdentifyHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                IdentifyHelper.this.saveState();
            }
        });
    }

    public int getIdentifyState() {
        if (this.mState > 1) {
            loadIdentifyState();
        }
        return this.mState;
    }

    public boolean isIdentifyClose() {
        return getIdentifyState() == 0;
    }

    public boolean isIdentifyNotInit() {
        return getIdentifyState() == -1;
    }

    public boolean isIdentifyOpen() {
        return getIdentifyState() == 1;
    }

    public void setIdentifyClose() {
        setIdentifyState(0);
    }

    public void setIdentifyOpen() {
        Log.d("identify", "setIdentifyOpen()");
        setIdentifyState(1);
        onIdentifyOpen();
    }

    public void setOpenCallback(Runnable runnable) {
        this.mOpenListener = runnable;
    }
}
