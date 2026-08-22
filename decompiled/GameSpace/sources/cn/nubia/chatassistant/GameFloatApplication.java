package cn.nubia.chatassistant;

import android.app.Application;
import android.content.Context;
import cn.nubia.settings.trackclient.NubiaTrackManager;

/* loaded from: classes.dex */
public class GameFloatApplication extends Application {
    private static GameFloatApplication instance;
    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    public static GameFloatApplication getInstance() {
        return instance;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        NubiaTrackManager.getInstance().init(this);
        instance = this;
        Context applicationContext = getApplicationContext();
        mContext = applicationContext;
        SystemMgr.getInstance(applicationContext).init();
    }
}
