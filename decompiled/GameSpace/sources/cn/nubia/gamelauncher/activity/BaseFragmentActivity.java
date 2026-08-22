package cn.nubia.gamelauncher.activity;

import android.app.ActivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.HideAppsHelper;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.gamelauncher.util.Util;
import java.util.Iterator;

/* loaded from: classes.dex */
public class BaseFragmentActivity extends FragmentActivity implements GameKeyObserver.Callback {
    public void hideNavigationBar(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(5382);
        }
    }

    protected boolean isHostMode() {
        return false;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
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

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GameKeyObserver.getInstance(this).removeCallback(this);
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        if (isHostMode()) {
            return;
        }
        Log.d("switch", "BFA - onGameKeyChanged() isOff : " + z + ", supportGameKey() : " + GameSpaceConfig.supportGameKey());
        if (!GameSpaceConfig.supportGameKey() || z) {
            Iterator<ActivityManager.AppTask> it = ((ActivityManager) getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY)).getAppTasks().iterator();
            while (it.hasNext()) {
                it.next().finishAndRemoveTask();
            }
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        hideNavigationBar(true);
        UpgradeManager.getInstance().showWaitUpgradeDialog();
        HideAppsHelper.getInstance().update();
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.gamelauncher.activity.BaseFragmentActivity.1
            @Override // java.lang.Runnable
            public void run() {
                BaseFragmentActivity.this.hideNavigationBar(true);
            }
        }, 1000L);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        hideNavigationBar(z);
    }
}
