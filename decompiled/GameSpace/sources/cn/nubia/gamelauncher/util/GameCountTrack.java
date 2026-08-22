package cn.nubia.gamelauncher.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import cn.nubia.arkbase.nbaccount.INbAccountLogin;
import cn.nubia.gamelauncher.GameLauncherApplication;

/* loaded from: classes.dex */
public class GameCountTrack {
    private static final String ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME = "cn.nubia.arkbase.service.NbAccountLoginService";
    private static final String ARK_BASE_PACKAGE_NAME = "cn.nubia.arkbase";
    public static final String TAG = "GameCountTrack";
    private static final long TIMEOUT = 3000;
    private static Handler mHandler;
    private static HandlerThread sCountThread;
    private static GameCountTrack sGameCountTrack;
    private boolean isConn;
    private final ServiceConnection mArkBaseServiceConnection;
    private Context mContext;
    private int mGameCount;
    private INbAccountLogin mNbAccountLoginImpl;

    private static class GameCountTrackHolder {
        public static final GameCountTrack INSTANCE = new GameCountTrack();

        private GameCountTrackHolder() {
        }
    }

    private GameCountTrack() {
        this.mGameCount = 0;
        this.mArkBaseServiceConnection = new ServiceConnection() { // from class: cn.nubia.gamelauncher.util.GameCountTrack.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(GameCountTrack.TAG, "[GameCountTrack] onServiceConnected");
                synchronized (GameCountTrack.sCountThread) {
                    GameCountTrack.this.isConn = true;
                    GameCountTrack.sCountThread.notify();
                }
                try {
                    GameCountTrack.this.mNbAccountLoginImpl = INbAccountLogin.Stub.asInterface(iBinder);
                    Log.d(GameCountTrack.TAG, "[GameCountTrack] mGameCount = " + GameCountTrack.this.mGameCount);
                    GameCountTrack.this.mNbAccountLoginImpl.reportInstalledGameApp(GameCountTrack.this.mGameCount);
                } catch (Exception unused) {
                    Log.d(GameCountTrack.TAG, "[GameCountTrack] mArkBaseServiceConnection Exception");
                }
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(GameCountTrack.TAG, "[GameCountTrack] onServiceDisconnected");
                synchronized (GameCountTrack.sCountThread) {
                    GameCountTrack.this.isConn = false;
                    GameCountTrack.sCountThread.notify();
                }
                GameCountTrack.this.mNbAccountLoginImpl = null;
            }
        };
        initGameCountTrack();
    }

    public static GameCountTrack getInstance() {
        return GameCountTrackHolder.INSTANCE;
    }

    public void bindArkService() {
        HandlerThread handlerThread;
        if (this.mContext == null || (handlerThread = sCountThread) == null) {
            return;
        }
        synchronized (handlerThread) {
            if (this.isConn) {
                return;
            }
            this.isConn = true;
            Log.d(TAG, "[GameCountTrack] bindArkService - isGameSpaceForeground() :" + Util.isGameSpaceForeground());
            Intent intent = new Intent();
            intent.setClassName("cn.nubia.arkbase", ARK_BASE_ACCOUNT_LOGIN_SERVICE_NAME);
            this.mContext.bindService(intent, this.mArkBaseServiceConnection, 1);
        }
    }

    public void initGameCountTrack() {
        Context appContext = GameLauncherApplication.getAppContext();
        this.mContext = appContext;
        if (!CommonUtil.isInstalled(appContext, "cn.nubia.arkbase")) {
            Log.d(TAG, "[GameCountTrack] initGameCountTrack arkbase not install");
            return;
        }
        if (mHandler == null) {
            HandlerThread handlerThread = new HandlerThread("GameCountTrackEvent", 10);
            sCountThread = handlerThread;
            handlerThread.start();
            mHandler = new Handler(sCountThread.getLooper());
        }
        if (Util.isGameSpaceForeground()) {
            bindArkService();
        }
    }

    public void sendChatAssistantCount(String str) {
        if (!CommonUtil.isInstalled(this.mContext, "cn.nubia.arkbase")) {
            Log.d(TAG, "[GameCountTrack] arkbase not install");
            return;
        }
        if (sCountThread == null) {
            Log.d(TAG, "sCountThread is null!!");
            return;
        }
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(new GameCountTrack$$ExternalSyntheticLambda0(this));
        mHandler.postDelayed(new GameCountTrack$$ExternalSyntheticLambda1(this), 3000L);
        try {
            Log.d(TAG, "[GameCountTrack] appName = " + str + "  mNbAccountLoginImpl = " + this.mNbAccountLoginImpl);
            this.mNbAccountLoginImpl.reportChatAssistant(str);
        } catch (Exception unused) {
            Log.d(TAG, "[GameCountTrack] sendChatAssistantCount Exception");
        }
    }

    public void sendGameCount(int i) {
        if (!CommonUtil.isInstalled(this.mContext, "cn.nubia.arkbase")) {
            Log.d(TAG, "[GameCountTrack] arkbase not install");
            return;
        }
        if (!Util.isGameSpaceForeground()) {
            Log.d(TAG, "sendGameCount() but background!");
            return;
        }
        this.mGameCount = i;
        if (sCountThread == null) {
            Log.d(TAG, "sCountThread is null!!");
            return;
        }
        mHandler.removeCallbacksAndMessages(null);
        mHandler.post(new GameCountTrack$$ExternalSyntheticLambda0(this));
        mHandler.postDelayed(new GameCountTrack$$ExternalSyntheticLambda1(this), 3000L);
    }

    public void unbindArkService() {
        HandlerThread handlerThread = sCountThread;
        if (handlerThread == null) {
            return;
        }
        synchronized (handlerThread) {
            if (this.isConn) {
                this.isConn = false;
                if (this.mContext != null) {
                    Log.d(TAG, "[GameCountTrack] unbindArkService");
                    try {
                        this.mContext.unbindService(this.mArkBaseServiceConnection);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
