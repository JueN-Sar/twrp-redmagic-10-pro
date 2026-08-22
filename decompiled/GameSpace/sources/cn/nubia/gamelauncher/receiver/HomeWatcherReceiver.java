package cn.nubia.gamelauncher.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.util.LogUtil;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import java.util.List;

/* loaded from: classes.dex */
public class HomeWatcherReceiver extends BroadcastReceiver {
    Runnable mRunnable;

    private void onHomePressed() {
        Runnable runnable = this.mRunnable;
        if (runnable == null) {
            return;
        }
        runnable.run();
    }

    public boolean isForeground(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        if (context == null || TextUtils.isEmpty(str) || (runningTasks = ((ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY)).getRunningTasks(1)) == null || runningTasks.size() <= 0) {
            return false;
        }
        return str.equals(runningTasks.get(0).topActivity.getClassName());
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction()) && ActivityManagerWrapper.CLOSE_SYSTEM_WINDOWS_REASON_HOME_KEY.equals(intent.getStringExtra("reason")) && isForeground(context, context.getClass().getName())) {
            LogUtil.d("HomeWatcher", "--->onReceive() homekey !");
            onHomePressed();
        }
    }

    public void setRunnable(Runnable runnable) {
        this.mRunnable = runnable;
    }
}
