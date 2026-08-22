package cn.nubia.gamelauncher.activity;

import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import cn.nubia.gamelauncher.util.LogUtil;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class HostModeGameSpace extends GameSpaceActivity {
    private static final String TAG = "HostModeGameSpace";
    HostModeContentObserver mHostModeContentObserver;

    private class HostModeContentObserver extends ContentObserver {
        public HostModeContentObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            if (Settings.Global.getInt(HostModeGameSpace.this.getContentResolver(), "gamebox_mirror_displayid", -1) == 0) {
                LogUtil.i(HostModeGameSpace.TAG, "HostMode - onChange(HOST_MODE_STATE) - finish !");
                HostModeGameSpace.this.finish();
            }
        }

        public void register() {
            HostModeGameSpace.this.getContentResolver().registerContentObserver(Settings.Global.getUriFor("gamebox_mirror_displayid"), false, this);
        }

        public void unregister() {
            HostModeGameSpace.this.getContentResolver().unregisterContentObserver(this);
        }
    }

    private void registerObserver() {
        HostModeContentObserver hostModeContentObserver = new HostModeContentObserver(new Handler());
        this.mHostModeContentObserver = hostModeContentObserver;
        hostModeContentObserver.register();
    }

    private void unregisterObserver() {
        HostModeContentObserver hostModeContentObserver = this.mHostModeContentObserver;
        if (hostModeContentObserver != null) {
            hostModeContentObserver.unregister();
        }
    }

    @Override // cn.nubia.gamelauncher.activity.GameSpaceActivity, android.app.Activity
    public void finish() {
        super.finish();
        Log.d(TAG, "finish() -> : " + Log.getStackTraceString(new Throwable()));
    }

    @Override // cn.nubia.gamelauncher.activity.BaseFragmentActivity
    protected boolean isHostMode() {
        return true;
    }

    @Override // cn.nubia.gamelauncher.activity.GameSpaceActivity, cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        registerObserver();
    }

    @Override // cn.nubia.gamelauncher.activity.GameSpaceActivity, cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterObserver();
        LogUtil.i(TAG, " onDestroy()");
    }

    @Override // cn.nubia.gamelauncher.activity.GameSpaceActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // cn.nubia.gamelauncher.activity.GameSpaceActivity, cn.nubia.gamelauncher.activity.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (isHostMode()) {
            Util.updateHostModeGameSpace(true);
        }
    }

    @Override // cn.nubia.gamelauncher.activity.GameSpaceActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (isHostMode()) {
            Util.updateHostModeGameSpace(false);
        }
    }
}
