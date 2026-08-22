package cn.nubia.gamelauncher.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.HideAppsHelper;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class BaseActivity extends Activity implements GameKeyObserver.Callback {
    public void hideNavigationBar(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(5382);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Util.updateDensity(this);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(512);
        getWindow().addFlags(256);
        GameKeyObserver.getInstance(this).addCallback(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GameKeyObserver.getInstance(this).removeCallback(this);
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        Log.d("switch", "BA - onGameKeyChanged() isOff : " + z + ", supportGameKey() : " + GameSpaceConfig.supportGameKey());
        if (!GameSpaceConfig.supportGameKey() || z) {
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        hideNavigationBar(true);
        UpgradeManager.getInstance().showWaitUpgradeDialog();
        HideAppsHelper.getInstance().update();
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.activity.BaseActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseActivity.this.hideNavigationBar(true);
            }
        }, 1000L);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        hideNavigationBar(z);
    }
}
