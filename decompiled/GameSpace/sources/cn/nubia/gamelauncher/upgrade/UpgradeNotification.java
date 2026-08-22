package cn.nubia.gamelauncher.upgrade;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.media3.common.C;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class UpgradeNotification {
    public static final int FLAG_AUTOGROUP_SUMMARY = 1024;
    private static final String TAG = "Upgrade";
    OnClickListener mClickClistener;
    Context mContext;
    private Handler mHandler;
    private HandlerThread mNofityThread;
    Notification mNotification;
    NotificationManager mNotificationManager;
    NotificationReceiver mReceiver;
    private final String CHANNEL_ID = "gamelauncher_upgrade";
    private final String ACTION_CANCEL = "cn.nubia.gamelauncher.upgrade.cancel";
    private final String ACTION_INSTALL = "cn.nubia.gamelauncher.upgrade.install";
    private final String ACTION_UPDATE = "cn.nubia.gamelauncher.upgrade.update";

    private class NotificationReceiver extends BroadcastReceiver {
        private NotificationReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("Upgrade", "onReceive() action : " + action);
            if (UpgradeNotification.this.mClickClistener == null) {
            }
            action.hashCode();
            switch (action) {
                case "cn.nubia.gamelauncher.upgrade.install":
                    UpgradeNotification.this.mClickClistener.onInstallClick();
                    break;
                case "cn.nubia.gamelauncher.upgrade.cancel":
                    UpgradeNotification.this.cancelNotification(1);
                    UpgradeNotification.this.mClickClistener.onCancelClick();
                    break;
                case "cn.nubia.gamelauncher.upgrade.update":
                    if (!UpgradeManager.getInstance().isDownloading()) {
                        if (UpgradeManager.getInstance().isPaused()) {
                            UpgradeNotification.this.mClickClistener.onResumeClick();
                            break;
                        }
                    } else {
                        UpgradeNotification.this.mClickClistener.onPauseClick();
                        break;
                    }
                    break;
            }
        }
    }

    public interface OnClickListener {
        void onCancelClick();

        void onInstallClick();

        void onPauseClick();

        void onResumeClick();
    }

    public UpgradeNotification(Context context) {
        this.mContext = context;
        initWorkHandler();
        registerNotificationReceiver();
        createNotifycation();
    }

    private Context getAppContext() {
        return this.mContext.getApplicationContext();
    }

    private PendingIntent getPendingIntent(int i) {
        Intent intent = new Intent();
        switch (i) {
            case 10:
                intent.setAction("cn.nubia.gamelauncher.upgrade.cancel");
                break;
            case 11:
                intent.setAction("cn.nubia.gamelauncher.upgrade.update");
                break;
            case 12:
                intent.setAction("cn.nubia.gamelauncher.upgrade.install");
                break;
        }
        return PendingIntent.getBroadcast(getAppContext(), i, intent, C.BUFFER_FLAG_FIRST_SAMPLE);
    }

    private void initWorkHandler() {
        HandlerThread handlerThread = new HandlerThread("updateNotification");
        this.mNofityThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mNofityThread.getLooper());
    }

    private void registerNotificationReceiver() {
        Log.d("Upgrade", "registerNotificationReceiver()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("cn.nubia.gamelauncher.upgrade.cancel");
        intentFilter.addAction("cn.nubia.gamelauncher.upgrade.update");
        intentFilter.addAction("cn.nubia.gamelauncher.upgrade.install");
        NotificationReceiver notificationReceiver = new NotificationReceiver();
        this.mReceiver = notificationReceiver;
        this.mContext.registerReceiver(notificationReceiver, intentFilter, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNotification() {
        Log.d("Upgrade", "----->showNotification() state = " + UpgradeManager.getInstance().getStringState());
        if (!UpgradeManager.getInstance().isDownloadStarted()) {
            Log.d("Upgrade", "<=====showNotification() state error, return an cancel notification!");
            cancelNotification(1);
            return;
        }
        String contentTitle = UpgradeManager.getInstance().getContentTitle();
        String content = UpgradeManager.getInstance().getContent();
        String buttonText = UpgradeManager.getInstance().getButtonText();
        NotificationCompat.Builder contentTitle2 = new NotificationCompat.Builder(getAppContext(), "gamelauncher_upgrade").setSmallIcon(R.mipmap.ic_launcher).setContentTitle(contentTitle);
        if (UpgradeManager.getInstance().isInstalling()) {
            contentTitle2.setContentText(content).setContentIntent(getPendingIntent(12));
            Notification build = contentTitle2.build();
            build.flags |= 16;
            this.mNotificationManager.notify(2, build);
            cancelNotification(1);
            Log.d("Upgrade", "<=====showNotification() download complete cancel 1 and notify 2. title = " + contentTitle);
            return;
        }
        contentTitle2.setContentText(content).setProgress(100, UpgradeManager.getInstance().getProgress(), false);
        contentTitle2.addAction(0, buttonText, getPendingIntent(11));
        contentTitle2.addAction(0, getAppContext().getString(android.R.string.cancel), getPendingIntent(10));
        Notification build2 = contentTitle2.build();
        this.mNotification = build2;
        build2.flags |= 32;
        this.mNotificationManager.notify(1, build2);
        Log.d("Upgrade", "<=====showNotification() -> updateButtonText() size() = " + buttonText + ", title = " + contentTitle);
    }

    public void cancelNotification(int i) {
        if (this.mNotificationManager == null) {
            createNotifycation();
        }
        if (this.mNotification != null) {
            Log.d("Upgrade", "cancelNotification() flag & FLAG_FOREGROUND_SERVICE : " + (this.mNotification.flags & 64));
            Log.d("Upgrade", "cancelNotification() flag & FLAG_AUTOGROUP_SUMMARY : " + (this.mNotification.flags & 1024));
            Log.d("Upgrade", "cancelNotification() id: " + i + ", flag : " + this.mNotification.flags);
        } else {
            Log.d("Upgrade", "cancelNotification() id: " + i);
        }
        this.mNotificationManager.cancel(i);
    }

    public void createNotifycation() {
        Log.d("Upgrade", "createNotifycation()");
        this.mNotificationManager = (NotificationManager) getAppContext().getSystemService("notification");
        this.mNotificationManager.createNotificationChannel(new NotificationChannel("gamelauncher_upgrade", "null", 3));
    }

    public void runOnWorkThread(Runnable runnable) {
        if (Process.myTid() == this.mNofityThread.getThreadId()) {
            runnable.run();
        } else {
            this.mHandler.post(runnable);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mClickClistener = onClickListener;
    }

    public void unregisterNotificationReceiver() {
        if (this.mReceiver != null) {
            Log.d("Upgrade", "unregisterNotificationReceiver()");
            getAppContext().unregisterReceiver(this.mReceiver);
        }
    }

    public void updateNotification() {
        runOnWorkThread(new Runnable() { // from class: cn.nubia.gamelauncher.upgrade.UpgradeNotification$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                UpgradeNotification.this.showNotification();
            }
        });
    }
}
