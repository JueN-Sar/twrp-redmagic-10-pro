package cn.nubia.gamecenter.settings.applearning;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.usage.UsageEvents;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Time;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.applearning.AppDbSchema;
import cn.nubia.gamecenter.settings.other.TimeRemindItem;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.gamecenter.settings.utils.SettingUtil;
import cn.nubia.gamelauncher.service.GameFeatureService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class AppTimeLockHelper {
    private static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    private static final String APPSTATUS_COLUMN_PKGNAME = "packagename";
    private static final String APPSTATUS_COLUMN_STATUS = "status";
    private static final String APPSTATUS_URI = "content://cn.nubia.apptimelock/app_status";
    private static final String APPTIMELOCK_DELAY_ACTION = "cn.nubia.apptimelock.action.DELAY";
    private static final String APPTIMELOCK_DELAY_REMINDER_ACTION = "cn.nubia.apptimelock.action.REMINDER";
    private static final String APPTIME_COLUMN_DELAY = "delay";
    private static final String APPTIME_COLUMN_DELAY_ONE_KEY = "delay";
    private static final String APPTIME_COLUMN_PKGNAME = "packagename";
    private static final String APPTIME_COLUMN_PKGNAME_ONE_KEY = "packagename";
    private static final String APPTIME_COLUMN_SWITCH = "enable";
    private static final String APPTIME_COLUMN_SWITCH_ONE_KEY = "enable";
    private static final String APPTIME_COLUMN_TIME = "time";
    private static final String APPTIME_COLUMN_TIME_ONE_KEY = "time";
    private static final String APPTIME_URI = "content://cn.nubia.apptimelock/app_time";
    private static final String APPTIME_URI_ONE_KEY = "content://cn.nubia.apptimelock/app_time_one_key";
    private static final String APP_LOCKED_FOR_DEADLINE_EXPIRED = "2";
    private static final String APP_LOCKED_FOR_SLEEP_MODE = "1";
    private static final String APP_TIME_LIMITED_LOCKED_STATUS = "3";
    private static final String APP_UNLOCKED_STATUS = "0";
    private static final String ATTR_APP_NAME = "component";
    public static final long DAY_IN_MILLIS = 86400000;
    private static final String DB_HEALTH_REMINDER_SWITCH = "app_time_lock_health_reminder";
    private static final String DB_PARENTAL_CONTROL_SWITCH = "app_time_lock_parental_control_switch";
    private static final String DB_TIME_IN_SCOPE = "app_time_lock_time_in_scope";
    private static final String DB_TIME_SET_LIMIT = "app_time_lock_set_time_limit";
    private static final long DELAY_TRIGGER_AVOID_REPEATED_REMINDER = 20000;
    private static final String HEALTH_REMINDER_NOTIFICATION_CHANNEL = "health_reminder_notification_channel";
    private static final int LOCKED_APP_DEFAULT_ID = -11111111;
    private static final String LOCKED_PKG_NAME = "nubia_locked_package_name";
    private static final int MSG_HANDLE_INIT_DATA = 0;
    private static final int MSG_HANDLE_RESET_DATA = 1;
    private static final int MSG_HANDLE_RESET_ONE_KEY = 3;
    private static final int MSG_HANDLE_UPDATE_SCOPE_FLAG = 2;
    private static final long MS_1_HOUR = 3600000;
    private static final long MS_2_HOUR = 7200000;
    private static final long MS_4_HOUR = 14400000;
    private static final String MULTI_WIN_ENABLED = "ss_multi_window_enabled";
    private static final String NUBIA_LOCKED_START_ID = "nubia_locked_start_id";
    private static final long ONE_MINITE = 60000;
    private static final String SETAPPTIMELOCKDATE_COLUMN = "time_lock_date";
    private static final String SETAPPTIMELOCKDATE_LOCK_NAME = "name";
    private static final String SETAPPTIMELOCKDATE_SETED_DATA = "value";
    private static final String SETAPPTIMELOCKDATE_URI = "content://cn.nubia.apptimelock/set_app_time_lock_date";
    private static final long SHOW_REMINDER_DIALOG_TIME_FIRST = 1800000;
    private static final long SHOW_REMINDER_DIALOG_TIME_SECOND = 300000;
    private static final String SLEEPMODEMRG_BEGIN_TIME1 = "begin_time1";
    private static final String SLEEPMODEMRG_DAYTYPE = "dayType";
    private static final String SLEEPMODEMRG_END_TIME1 = "end_time1";
    private static final String SLEEPMODEMRG_RESTDAY = "restDay";
    private static final String SLEEPMODEMRG_URI = "content://cn.nubia.apptimelock/sleep_mode_manager";
    private static final String SLEEPMODEMRG_VALUE = "value";
    private static final String SLEEPMODEMRG_WORKDAY = "workDay";
    private static final String TAG = "AppTimeLockService";
    private static final int TYPE_END_TIME = 1;
    private static final int TYPE_RESET_DAY = 2;
    private static final int TYPE_START_TIME = 0;
    private static final int TYPE_WORK_DAY = 1;
    private static final String ZERO_TIME = "00:01";
    private ActivityManager mAms;
    private Handler mAppTimeLockHandler;
    private Context mContext;
    private ConcurrentHashMap<String, PackageLockRecord> mLockedAppMapOneKey;
    private NotificationManager mNotifyMgr;
    private String mSetAppTimeLockDate;
    private volatile Looper mWorkLooper;
    private volatile boolean mMultiWinEnabled = false;
    private MultiWindowObserver mMultiWindowObserver = new MultiWindowObserver(new Handler());
    private List<String> mGameApps = new ArrayList();
    private List<String> mLimitedList = new ArrayList();
    private NotificationChannel mNotificationChannel = null;
    private String mWorkDayBeginTime = "00:00";
    private String mWorkDayEndTime = "23:59";
    private String mRestDayBeginTime = "00:00";
    private String mRestDayEndTime = "23:59";
    private boolean mParentalControlMode = false;
    private ParentalControlModeObserver mParentalControlModeObserver = new ParentalControlModeObserver(new Handler());
    private EffectiveTimePeriodObserve mEffectiveTimePeriodObserve = new EffectiveTimePeriodObserve(new Handler());
    private GamePackageAddObserver mGamePackageAddObserver = new GamePackageAddObserver(new Handler());
    private EffectiveTimeObserve mEffectiveTimeObserve = new EffectiveTimeObserve(new Handler());
    private int mScopeFlag = -1;
    private int mIsScope = -1;
    private boolean mTimeLimitedLockedOn = false;
    private String mDelayLockApp = null;
    private int mDelayLockTime = 0;
    private ConcurrentHashMap<String, Integer> mLimitedLockedStatusApps = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Integer> mTimeLimitedAppList = new ConcurrentHashMap<>();
    private AppTimeObserver mAppTimeObserver = new AppTimeObserver(new Handler());
    private AppLockedObserver mAppLockedObserver = new AppLockedObserver(new Handler());
    private AppTimeLockDateObserver mAppTimeLockDateObserver = new AppTimeLockDateObserver(new Handler());
    private HandlerThread mAppTimeLockThread = new HandlerThread("AppTimeLockThread");
    private ArrayList<ReminderRunnable> mReminderRunnables = new ArrayList<>();
    private ConcurrentHashMap<String, Integer> mTimeLimitedAppListOneKey = new ConcurrentHashMap<>();
    private AppTimeOneKeyObserver mAppTimeOneKeyObserver = new AppTimeOneKeyObserver(new Handler());
    private ArrayList<ReminderRunnableOneKey> mReminderRunnablesOneKey = new ArrayList<>();
    private int mRetryProviderCount = 0;
    private final Object mGameAppsLock = new Object();
    private final Object mLimitedLockedStatusAppsLock = new Object();
    private final Object mTimeLimitedAppListLock = new Object();
    private BroadcastReceiver mTimeChangedReceiver = new BroadcastReceiver() { // from class: cn.nubia.gamecenter.settings.applearning.AppTimeLockHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            try {
                if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) || "android.intent.action.TIME_TICK".equals(intent.getAction())) {
                    AppTimeLockHelper.this.removeTimeoutOneKeyPkg();
                }
                AppTimeLockHelper.this.handleDateOrTimeChanged(intent);
            } catch (Exception e) {
                LogUtil.e("AppTimeLockService", "handle date or time changed exception: " + e);
            }
        }
    };
    private long totalPlayTime = 0;
    private long restTimeOfDay = 0;
    private Handler mUiHandler = new Handler(Looper.getMainLooper());

    private class AppLockedObserver extends ContentObserver {
        AppLockedObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppTimeLockHelper.this.getLimitedLockedStatusAppList();
        }
    }

    private class AppTimeLockDateObserver extends ContentObserver {
        AppTimeLockDateObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppTimeLockHelper.this.setAppTimeLockDate();
        }
    }

    private class AppTimeObserver extends ContentObserver {
        AppTimeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppTimeLockHelper.this.setTimeLimitedLockedSwitch();
            AppTimeLockHelper.this.updateAppStatusTable();
        }
    }

    private class AppTimeOneKeyObserver extends ContentObserver {
        AppTimeOneKeyObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppTimeLockHelper.this.syncNotifyOneKey();
        }
    }

    private static class AppUsageStats {
        public long mBeginTimeStamp;
        public long mEndTimeStamp;
        public String mPackageName;
        public long mTotalTimeInForeground;
    }

    private class EffectiveTimeObserve extends ContentObserver {
        EffectiveTimeObserve(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.d("AppTimeLockService", "EffectiveTimeObserve, onChange ");
            AppTimeLockHelper.this.updateAppStatusTable();
        }
    }

    private class EffectiveTimePeriodObserve extends ContentObserver {
        EffectiveTimePeriodObserve(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.d("AppTimeLockService", "EffectiveTimePeriodObserve, onChange ");
            AppTimeLockHelper.this.updateParentalControlTimeScope();
            AppTimeLockHelper.this.updateAppStatusTable();
        }
    }

    private class GamePackageAddObserver extends ContentObserver {
        GamePackageAddObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            LogUtil.d("AppTimeLockService", "GamePackageAddObserver, onChange ");
            AppTimeLockHelper.this.updateGameAppPkg();
        }
    }

    private class MultiWindowObserver extends ContentObserver {
        MultiWindowObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppTimeLockHelper appTimeLockHelper = AppTimeLockHelper.this;
            appTimeLockHelper.mMultiWinEnabled = Settings.System.getInt(appTimeLockHelper.mContext.getContentResolver(), "ss_multi_window_enabled", 0) != 0;
            LogUtil.d("AppTimeLockService", "onChange, mMultiWinEnabled = " + AppTimeLockHelper.this.mMultiWinEnabled);
        }
    }

    private class ParentalControlModeObserver extends ContentObserver {
        ParentalControlModeObserver(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            AppTimeLockHelper appTimeLockHelper = AppTimeLockHelper.this;
            appTimeLockHelper.mParentalControlMode = Settings.Global.getInt(appTimeLockHelper.mContext.getContentResolver(), AppTimeLockHelper.DB_PARENTAL_CONTROL_SWITCH, 0) != 0;
            LogUtil.d("AppTimeLockService", "onChange, mParentalControlMode = " + AppTimeLockHelper.this.mParentalControlMode);
        }
    }

    private class ReminderRunnable implements Runnable {
        private String mAppPkgName;
        private boolean mNotify;
        private int mWinMode;
        private String notifyText;
        private long time = AppTimeLockHelper.MS_1_HOUR;

        public ReminderRunnable(boolean z, int i, String str) {
            this.notifyText = null;
            this.mNotify = z;
            this.mAppPkgName = str;
            this.mWinMode = i;
            this.notifyText = AppTimeLockHelper.this.mContext.getResources().getString(R.string.app_time_lock_health_reminder_notify_content);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Notification createNotification() {
            Intent intent = new Intent();
            intent.setClassName("cn.nubia.apptimelock", "cn.nubia.apptimelock.GameHealth");
            intent.addFlags(268435456);
            PendingIntent.getActivity(AppTimeLockHelper.this.mContext, 0, intent, 201326592);
            return new Notification.Builder(AppTimeLockHelper.this.mContext).setContentTitle(AppTimeLockHelper.this.mContext.getResources().getString(R.string.app_time_lock_health_reminder_notify_title)).setContentText(this.notifyText).setChannelId(AppTimeLockHelper.HEALTH_REMINDER_NOTIFICATION_CHANNEL).setWhen(System.currentTimeMillis()).setShowWhen(true).setSmallIcon(R.drawable.ic_notification_health).setAutoCancel(true).setDefaults(-1).setPriority(2).setSortKey("z0").setStyle(new Notification.BigTextStyle().bigText(this.notifyText)).build();
        }

        private boolean isRightApp() {
            return (TextUtils.isEmpty(this.mAppPkgName) || !AppTimeLockHelper.this.getTopProcessName().contains(this.mAppPkgName) || AppTimeLockHelper.this.mLimitedLockedStatusApps.get(this.mAppPkgName) == null) ? false : true;
        }

        private void startDelaySettingsActivity() {
            boolean isRightApp = isRightApp();
            LogUtil.d("AppTimeLockService", "startDelaySettingsActivity sucess = " + isRightApp + ", mAppPkgName = " + this.mAppPkgName);
            if (isRightApp) {
                AppTimeLockHelper.this.startUnlockAppActivity(this.mAppPkgName);
                try {
                    ContentResolver contentResolver = AppTimeLockHelper.this.mContext.getContentResolver();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("status", "2");
                    contentResolver.update(Uri.parse(AppTimeLockHelper.APPSTATUS_URI), contentValues, "status = '3'", null);
                } catch (Exception unused) {
                    LogUtil.e("AppTimeLockService", "startDelaySettingsActivity exception");
                }
            }
        }

        private void startReminderDialog() {
            boolean isRightApp = isRightApp();
            LogUtil.d("AppTimeLockService", "startReminderDialog sucess = " + isRightApp + ", mAppPkgName = " + this.mAppPkgName);
            if (isRightApp) {
                Intent intent = new Intent();
                intent.setAction(AppTimeLockHelper.APPTIMELOCK_DELAY_REMINDER_ACTION);
                intent.setFlags(268435456);
                intent.addFlags(32768);
                AppTimeLockHelper.this.mContext.startActivity(intent);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mNotify) {
                AppTimeLockHelper.this.mUiHandler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.applearning.AppTimeLockHelper.ReminderRunnable.1
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.i("AppTimeLockService", "ReminderRunnable run --");
                        if (AppTimeLockHelper.this.enableHealthReminder() && !SettingUtil.getReminder(AppTimeLockHelper.this.mContext, ReminderRunnable.this.time)) {
                            if (AppTimeLockHelper.this.mNotificationChannel == null) {
                                LogUtil.d("AppTimeLockService", "mNotificationChannel == null");
                                AppTimeLockHelper.this.mNotificationChannel = new NotificationChannel(AppTimeLockHelper.HEALTH_REMINDER_NOTIFICATION_CHANNEL, AppTimeLockHelper.this.mContext.getResources().getString(R.string.app_time_lock_health_reminder_notify_title), 4);
                                AppTimeLockHelper.this.mNotifyMgr.createNotificationChannel(AppTimeLockHelper.this.mNotificationChannel);
                            }
                            AppTimeLockHelper.this.mNotifyMgr.notify(777, ReminderRunnable.this.createNotification());
                            SettingUtil.setReminder(AppTimeLockHelper.this.mContext, ReminderRunnable.this.time);
                        }
                    }
                });
            } else {
                startDelaySettingsActivity();
            }
        }

        public void setText(String str) {
            this.notifyText = str;
        }

        public void setTime(long j) {
            this.time = j;
        }
    }

    private class ReminderRunnableOneKey implements Runnable {
        private String mAppPkgName;
        private long mDelayTime;
        private boolean mNotify;

        public ReminderRunnableOneKey(boolean z, String str, long j) {
            this.mNotify = z;
            this.mAppPkgName = str;
            this.mDelayTime = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                LogUtil.d("AppTimeLockService", "mDelayTime " + this.mDelayTime);
                AppTimeLockHelper.this.mContext.getContentResolver().delete(AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI, "packagename = ?", new String[]{this.mAppPkgName});
                LogUtil.d("AppTimeLockService", "delete mAppPkgName " + this.mAppPkgName);
            } catch (Exception unused) {
                LogUtil.d("AppTimeLockService", "ReminderRunnableOneKey error ");
            }
        }

        public String toString() {
            return "ReminderRunnableOneKey{mNotify=" + this.mNotify + ", mAppPkgName='" + this.mAppPkgName + "', mDelayTime=" + this.mDelayTime + '}';
        }
    }

    private final class WorkHandler extends Handler {
        public WorkHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                AppTimeLockHelper.this.initData();
                return;
            }
            if (i == 1) {
                AppTimeLockHelper.this.clearDataWhenZeroTime();
            } else if (i != 2) {
                LogUtil.d("AppTimeLockService", "handleMessage invalid message!");
            } else {
                AppTimeLockHelper.this.handleTimeScopeMsg(message.arg1, message.arg2);
            }
        }
    }

    public AppTimeLockHelper(Context context) {
        this.mContext = context;
        this.mAppTimeLockThread.start();
        this.mWorkLooper = this.mAppTimeLockThread.getLooper();
        WorkHandler workHandler = new WorkHandler(this.mWorkLooper);
        this.mAppTimeLockHandler = workHandler;
        workHandler.sendEmptyMessage(0);
        this.mAms = (ActivityManager) this.mContext.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY);
        this.mNotifyMgr = (NotificationManager) this.mContext.getSystemService("notification");
    }

    private boolean checkDateOk() {
        boolean isParentalControlTimeScope = isParentalControlTimeScope();
        if (this.mScopeFlag == -1 && this.mIsScope != isParentalControlTimeScope) {
            this.mIsScope = isParentalControlTimeScope ? 1 : 0;
            Settings.Global.putInt(this.mContext.getContentResolver(), DB_TIME_IN_SCOPE, this.mIsScope);
            updateAppStatusTable();
        }
        return isParentalControlTimeScope;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDataWhenZeroTime() {
        LogUtil.d("AppTimeLockService", "clearDataWhenZeroTime");
        try {
            this.mScopeFlag = -1;
            ContentValues contentValues = new ContentValues();
            contentValues.put(AppDbSchema.OneKeyLockedAppsTable.Cols.DELAY_TIME, "0");
            this.mContext.getContentResolver().update(Uri.parse(APPTIME_URI), contentValues, null, null);
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("status", "3");
            this.mContext.getContentResolver().update(Uri.parse(APPSTATUS_URI), contentValues2, "status in (? , ?)", new String[]{"2"});
            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("name", SETAPPTIMELOCKDATE_COLUMN);
            contentValues3.put("value", new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis())));
            this.mContext.getContentResolver().insert(Uri.parse(SETAPPTIMELOCKDATE_URI), contentValues3);
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "clearDataWhenZeroTime exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean enableHealthReminder() {
        return new TimeRemindItem().getSettings(this.mContext);
    }

    private long getDelayTime() {
        long startTimetoMillis = getStartTimetoMillis();
        LogUtil.d("AppTimeLockService", "getDelayTime, startTime = " + startTimetoMillis + ", currentTimeMillis = " + System.currentTimeMillis());
        if (System.currentTimeMillis() - startTimetoMillis <= 0) {
            return 0L;
        }
        long parentalControlScopeTotalTime = getParentalControlScopeTotalTime(startTimetoMillis, false);
        long j = (Settings.Global.getInt(this.mContext.getContentResolver(), DB_TIME_SET_LIMIT, 120) * 60000) - parentalControlScopeTotalTime;
        LogUtil.d("AppTimeLockService", "getDelayTime, totalTime = " + parentalControlScopeTotalTime + ", delayMillis = " + j);
        long endTimetoMillis = getEndTimetoMillis() - System.currentTimeMillis();
        LogUtil.d("AppTimeLockService", "getDelayTime, availableTime = " + endTimetoMillis);
        if (endTimetoMillis <= 0) {
            return 0L;
        }
        if (endTimetoMillis > 0 && j > endTimetoMillis) {
            return endTimetoMillis;
        }
        if (j > 0) {
            return j;
        }
        return 0L;
    }

    private long getDelayTime(long j, long j2, long j3) {
        long j4 = j - j2;
        return (j4 <= 0 || j4 > j3) ? j3 + j : j4;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00b5 A[Catch: Exception -> 0x00b9, TRY_LEAVE, TryCatch #1 {Exception -> 0x00b9, blocks: (B:5:0x0056, B:8:0x00ae, B:10:0x00b5, B:30:0x00ac, B:29:0x00a9, B:23:0x00a3, B:13:0x0079, B:15:0x007f, B:17:0x0089), top: B:4:0x0056, inners: #0, #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private long getDelayTime(java.lang.String r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            java.lang.String r2 = "enable = 1 AND packagename = '"
            android.content.Context r3 = r0.mContext
            r4 = 1
            r5 = 0
            long r6 = r0.getSelectionArgs(r4, r5)
            long r8 = java.lang.System.currentTimeMillis()
            android.app.usage.UsageEvents r3 = cn.nubia.common.helper.AppUsageStatsHelper.queryEvents(r3, r6, r8)
            long r3 = r0.parseUsageEvents(r3, r1)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Integer> r6 = r0.mTimeLimitedAppList
            java.lang.Object r6 = r6.get(r1)
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            r7 = 60000(0xea60, float:8.4078E-41)
            int r6 = r6 * r7
            long r8 = (long) r6
            long r8 = r8 - r3
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r10 = "getDelayTime, todayUsedTime = "
            r6.<init>(r10)
            java.lang.StringBuilder r6 = r6.append(r3)
            java.lang.String r10 = ", delayMillis = "
            java.lang.StringBuilder r6 = r6.append(r10)
            java.lang.StringBuilder r6 = r6.append(r8)
            java.lang.String r6 = r6.toString()
            java.lang.String r10 = "AppTimeLockService"
            cn.nubia.gamecenter.settings.utils.LogUtil.d(r10, r6)
            r11 = 0
            int r6 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r6 >= 0) goto Lde
            android.content.Context r0 = r0.mContext
            android.content.ContentResolver r11 = r0.getContentResolver()
            java.lang.String r0 = "content://cn.nubia.apptimelock/app_time"
            android.net.Uri r12 = android.net.Uri.parse(r0)     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lb9
            r0.<init>(r2)     // Catch: java.lang.Exception -> Lb9
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r1 = "'"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Exception -> Lb9
            java.lang.String r14 = r0.toString()     // Catch: java.lang.Exception -> Lb9
            r15 = 0
            r16 = 0
            r13 = 0
            android.database.Cursor r1 = r11.query(r12, r13, r14, r15, r16)     // Catch: java.lang.Exception -> Lb9
            if (r1 == 0) goto Lad
            int r0 = r1.getCount()     // Catch: java.lang.Throwable -> L9f
            if (r0 <= 0) goto Lad
            r0 = -1
            r1.moveToPosition(r0)     // Catch: java.lang.Throwable -> L9f
            boolean r0 = r1.moveToNext()     // Catch: java.lang.Throwable -> L9f
            if (r0 == 0) goto Lad
            java.lang.String r0 = "time"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f
            int r5 = r1.getInt(r0)     // Catch: java.lang.Throwable -> L9f
            java.lang.String r0 = "delay"
            int r0 = r1.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L9f
            int r0 = r1.getInt(r0)     // Catch: java.lang.Throwable -> L9f
            goto Lae
        L9f:
            r0 = move-exception
            r2 = r0
            if (r1 == 0) goto Lac
            r1.close()     // Catch: java.lang.Throwable -> La7
            goto Lac
        La7:
            r0 = move-exception
            r1 = r0
            r2.addSuppressed(r1)     // Catch: java.lang.Exception -> Lb9
        Lac:
            throw r2     // Catch: java.lang.Exception -> Lb9
        Lad:
            r0 = r5
        Lae:
            int r5 = r5 + r0
            int r5 = r5 * r7
            long r5 = (long) r5     // Catch: java.lang.Exception -> Lb9
            long r8 = r5 - r3
            if (r1 == 0) goto Lcc
            r1.close()     // Catch: java.lang.Exception -> Lb9
            goto Lcc
        Lb9:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getDelayTime exception: "
            r1.<init>(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            cn.nubia.gamecenter.settings.utils.LogUtil.e(r10, r0)
        Lcc:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getDelayTime, delayMillis = "
            r0.<init>(r1)
            java.lang.StringBuilder r0 = r0.append(r8)
            java.lang.String r0 = r0.toString()
            cn.nubia.gamecenter.settings.utils.LogUtil.d(r10, r0)
        Lde:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.applearning.AppTimeLockHelper.getDelayTime(java.lang.String):long");
    }

    private long getEndTimetoMillis() {
        Calendar calendar = Calendar.getInstance();
        int[] timeStrToIntHourMin = timeStrToIntHourMin(this.mWorkDayEndTime);
        if (calendar.get(7) == 7 || calendar.get(7) == 1) {
            timeStrToIntHourMin = timeStrToIntHourMin(this.mRestDayEndTime);
        }
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), timeStrToIntHourMin[0], timeStrToIntHourMin[1], 0);
        long timeInMillis = (calendar.getTimeInMillis() / SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) * SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
        LogUtil.d("AppTimeLockService", "getStartTimetoMillis, result = " + timeInMillis);
        return timeInMillis;
    }

    private long getHealthReminderDelayTime() {
        long parentalControlScopeTotalTime = getParentalControlScopeTotalTime(getTodayStartTime(), true);
        this.totalPlayTime = parentalControlScopeTotalTime;
        long j = MS_4_HOUR - parentalControlScopeTotalTime;
        if (j > 0) {
            return j;
        }
        return 20L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean getLimitedLockedStatusAppList() {
        Cursor query = this.mContext.getContentResolver().query(Uri.parse(APPSTATUS_URI), null, "status = ? ", new String[]{"3"}, null);
        if (query == null) {
            if (query != null) {
                query.close();
            }
            return false;
        }
        try {
            int columnIndex = query.getColumnIndex("packagename");
            int columnIndex2 = query.getColumnIndex("status");
            ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
            query.moveToPosition(-1);
            while (query.moveToNext()) {
                concurrentHashMap.put(query.getString(columnIndex), Integer.valueOf(query.getInt(columnIndex2)));
            }
            synchronized (this.mLimitedLockedStatusAppsLock) {
                this.mLimitedLockedStatusApps = concurrentHashMap;
            }
            if (query != null) {
                query.close();
            }
            return true;
        } catch (Throwable th) {
            if (query != null) {
                try {
                    query.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private long getParentalControlScopeTotalTime(long j, boolean z) {
        List<Map<String, Long>> queryNbStatsDistribution = AppUsageStatsHelper.queryNbStatsDistribution(this.mContext, z ? this.mGameApps : this.mLimitedList, j, System.currentTimeMillis(), 86400000L);
        long j2 = 0;
        if (queryNbStatsDistribution == null || queryNbStatsDistribution.size() == 0) {
            LogUtil.i("AppTimeLockService", "queryNbStatsDistribution no data");
            return 0L;
        }
        for (Map.Entry<String, Long> entry : queryNbStatsDistribution.get(queryNbStatsDistribution.size() - 1).entrySet()) {
            LogUtil.i("AppTimeLockService", "(ms) " + entry.getKey() + ":" + entry.getValue());
            j2 += msToMinite(entry.getValue().longValue());
        }
        LogUtil.i("AppTimeLockService", "(min) total = " + j2);
        return j2 * 60000;
    }

    private long getSelectionArgs(boolean z, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        if (!z) {
            calendar.set(5, calendar.get(5) - i);
        }
        return (calendar.getTimeInMillis() / SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) * SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
    }

    private long getStartTimetoMillis() {
        Calendar calendar = Calendar.getInstance();
        int[] timeStrToIntHourMin = timeStrToIntHourMin(this.mWorkDayBeginTime);
        if (calendar.get(7) == 7 || calendar.get(7) == 1) {
            timeStrToIntHourMin = timeStrToIntHourMin(this.mRestDayBeginTime);
        }
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), timeStrToIntHourMin[0], timeStrToIntHourMin[1], 0);
        long timeInMillis = (calendar.getTimeInMillis() / SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) * SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
        LogUtil.d("AppTimeLockService", "getStartTimetoMillis, result = " + timeInMillis);
        return timeInMillis;
    }

    private long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        long timeInMillis = (calendar.getTimeInMillis() / SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) * SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
        LogUtil.i("AppTimeLockService", "getTodayStartTime " + timeInMillis);
        calendar.add(5, 1);
        this.restTimeOfDay = calendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        return timeInMillis;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<String> getTopProcessName() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            if (this.mMultiWinEnabled) {
                List<ActivityManager.RunningTaskInfo> runningTasks = this.mAms.getRunningTasks(2);
                if (runningTasks != null && !runningTasks.isEmpty()) {
                    Iterator<ActivityManager.RunningTaskInfo> it = runningTasks.iterator();
                    while (it.hasNext()) {
                        String packageName = it.next().topActivity.getPackageName();
                        arrayList.add(packageName);
                        LogUtil.d("AppTimeLockService", "getTopProcessName multiPkgName = " + packageName);
                    }
                }
            } else {
                List<ActivityManager.RunningTaskInfo> runningTasks2 = this.mAms.getRunningTasks(1);
                if (runningTasks2 != null && !runningTasks2.isEmpty()) {
                    String packageName2 = runningTasks2.get(0).topActivity.getPackageName();
                    arrayList.add(packageName2);
                    LogUtil.d("AppTimeLockService", "getTopProcessName name = " + packageName2);
                }
            }
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "getProcessName e");
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDateOrTimeChanged(Intent intent) {
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(System.currentTimeMillis()));
        String substring = format.substring(0, format.indexOf(" "));
        String substring2 = format.substring(format.indexOf(" "), format.length());
        String replace = substring.replace(" ", "");
        String replace2 = substring2.replace(" ", "");
        if (this.mTimeLimitedLockedOn && (ZERO_TIME.equals(replace2) || !replace.equals(this.mSetAppTimeLockDate))) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            this.mAppTimeLockHandler.sendMessage(obtain);
        } else {
            if (TextUtils.isEmpty(replace2)) {
                return;
            }
            if (replace2.equals(this.mWorkDayBeginTime)) {
                sendUpdateTimeScopeMsg(1, 0);
            } else if (replace2.equals(this.mWorkDayEndTime)) {
                sendUpdateTimeScopeMsg(1, 1);
            }
            if (replace2.equals(this.mRestDayBeginTime)) {
                sendUpdateTimeScopeMsg(2, 0);
            } else if (replace2.equals(this.mRestDayEndTime)) {
                sendUpdateTimeScopeMsg(2, 1);
            }
            checkDateOk();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleTimeScopeMsg(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        boolean z = calendar.get(7) == 7 || calendar.get(7) == 1;
        if (!(i == 2 && z) && (i != 1 || z)) {
            return;
        }
        int i3 = i2 != 0 ? 0 : 1;
        this.mScopeFlag = i3;
        this.mIsScope = i3;
        Settings.Global.putInt(this.mContext.getContentResolver(), DB_TIME_IN_SCOPE, this.mScopeFlag);
        updateAppStatusTable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        int i;
        LogUtil.d("AppTimeLockService", "initData mRetryProviderCount = " + this.mRetryProviderCount);
        boolean limitedLockedStatusAppList = getLimitedLockedStatusAppList();
        this.mParentalControlMode = Settings.Global.getInt(this.mContext.getContentResolver(), DB_PARENTAL_CONTROL_SWITCH, 0) == 1;
        if (!limitedLockedStatusAppList && (i = this.mRetryProviderCount) < 2) {
            this.mRetryProviderCount = i + 1;
            this.mAppTimeLockHandler.sendEmptyMessageDelayed(0, 5000L);
        } else {
            updateParentalControlTimeScope();
            updateGameAppPkg();
            setTimeLimitedLockedSwitch();
            setAppTimeLockDate();
        }
    }

    private boolean isCurrentInTimeScope(int i, int i2, int i3, int i4) {
        long currentTimeMillis = System.currentTimeMillis();
        Time time = new Time();
        time.set(currentTimeMillis);
        Time time2 = new Time();
        time2.set(currentTimeMillis);
        time2.hour = i;
        time2.minute = i2;
        Time time3 = new Time();
        time3.set(currentTimeMillis);
        time3.hour = i3;
        time3.minute = i4;
        boolean z = false;
        if (!time2.before(time3)) {
            time2.set(time2.toMillis(true) - 86400000);
            if (!time.before(time2) && !time.after(time3)) {
                z = true;
            }
            Time time4 = new Time();
            time4.set(time2.toMillis(true) + 86400000);
            if (!time.before(time4)) {
                return true;
            }
        } else if (!time.before(time2) && !time.after(time3)) {
            z = true;
        }
        return z;
    }

    private boolean isParentalControlTimeScope() {
        Calendar calendar = Calendar.getInstance();
        int[] timeStrToIntHourMin = timeStrToIntHourMin(this.mWorkDayBeginTime);
        int[] timeStrToIntHourMin2 = timeStrToIntHourMin(this.mWorkDayEndTime);
        if (calendar.get(7) == 7 || calendar.get(7) == 1) {
            timeStrToIntHourMin = timeStrToIntHourMin(this.mRestDayBeginTime);
            timeStrToIntHourMin2 = timeStrToIntHourMin(this.mRestDayEndTime);
        }
        if (timeStrToIntHourMin == null || timeStrToIntHourMin2 == null) {
            return false;
        }
        return isCurrentInTimeScope(timeStrToIntHourMin[0], timeStrToIntHourMin[1], timeStrToIntHourMin2[0], timeStrToIntHourMin2[1]);
    }

    public static int msToMinite(long j) {
        int i = (int) ((59999 + j) / 60000);
        if (j <= 0 || j >= 60000) {
            return i;
        }
        return 1;
    }

    private long parseUsageEvents(UsageEvents usageEvents, String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        while (usageEvents.hasNextEvent()) {
            UsageEvents.Event event = new UsageEvents.Event();
            usageEvents.getNextEvent(event);
            String packageName = event.getPackageName();
            if (!TextUtils.isEmpty(packageName) && packageName.equals(str)) {
                if (event.getEventType() == 1) {
                    if (!concurrentHashMap.containsKey(packageName)) {
                        concurrentHashMap.put(packageName, new AppUsageStats());
                    }
                    ((AppUsageStats) concurrentHashMap.get(packageName)).mBeginTimeStamp = event.getTimeStamp();
                    ((AppUsageStats) concurrentHashMap.get(packageName)).mPackageName = packageName;
                } else if (event.getEventType() == 2) {
                    if (concurrentHashMap.containsKey(packageName)) {
                        ((AppUsageStats) concurrentHashMap.get(packageName)).mEndTimeStamp = event.getTimeStamp();
                        ((AppUsageStats) concurrentHashMap.get(packageName)).mTotalTimeInForeground = (((AppUsageStats) concurrentHashMap.get(packageName)).mEndTimeStamp - ((AppUsageStats) concurrentHashMap.get(packageName)).mBeginTimeStamp) + ((AppUsageStats) concurrentHashMap.get(packageName)).mTotalTimeInForeground;
                    } else {
                        LogUtil.d("AppTimeLockService", "parseUsageEvents, is invalid data = " + packageName);
                    }
                }
            }
        }
        long j = 0;
        for (Map.Entry entry : concurrentHashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            long j2 = ((AppUsageStats) entry.getValue()).mTotalTimeInForeground;
            LogUtil.d("AppTimeLockService", "parseUsageEvents, name= " + str2 + ", apptime = " + j2);
            if (j2 <= 0) {
                j2 = 0;
            }
            j += j2;
        }
        concurrentHashMap.clear();
        return j;
    }

    private void postDelayNotify(long j, int i, String str) {
        long delayTime = getDelayTime(j, this.totalPlayTime, this.restTimeOfDay);
        LogUtil.i("AppTimeLockService", "health reminder:" + msToMinite(j) + "(remind " + j + ") " + msToMinite(this.totalPlayTime) + "(total " + this.totalPlayTime + ") " + msToMinite(this.restTimeOfDay) + "(rest " + this.restTimeOfDay + ") " + msToMinite(delayTime) + "(delay " + delayTime + ") ");
        ReminderRunnable reminderRunnable = new ReminderRunnable(true, 0, str);
        reminderRunnable.setText(this.mContext.getString(i));
        reminderRunnable.setTime(j);
        this.mReminderRunnables.add(reminderRunnable);
        this.mAppTimeLockHandler.postDelayed(reminderRunnable, delayTime);
    }

    private void removeDelayCallbacksOneKey() {
        Iterator<ReminderRunnableOneKey> it = this.mReminderRunnablesOneKey.iterator();
        while (it.hasNext()) {
            this.mAppTimeLockHandler.removeCallbacks(it.next());
        }
        this.mReminderRunnablesOneKey.clear();
    }

    private void sendUpdateTimeScopeMsg(int i, int i2) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        obtain.arg1 = i;
        obtain.arg2 = i2;
        this.mAppTimeLockHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAppTimeLockDate() {
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(SETAPPTIMELOCKDATE_URI), null, null, null, null);
            if (query == null) {
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            try {
                if (!query.moveToFirst()) {
                    if (query != null) {
                        query.close();
                    }
                } else {
                    this.mSetAppTimeLockDate = query.getString(query.getColumnIndex("value"));
                    if (query != null) {
                        query.close();
                    }
                }
            } finally {
            }
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "AppTimeLockDateObserver exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTimeLimitedLockedSwitch() {
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(APPTIME_URI), null, "enable = 1 ", null, null);
            try {
                ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
                ArrayList arrayList = new ArrayList();
                if (query != null && query.getCount() > 0) {
                    this.mTimeLimitedLockedOn = true;
                    int columnIndex = query.getColumnIndex("packagename");
                    int columnIndex2 = query.getColumnIndex(AppDbSchema.OneKeyLockedAppsTable.Cols.LIMIT_TIME_STAMP);
                    int columnIndex3 = query.getColumnIndex(AppDbSchema.OneKeyLockedAppsTable.Cols.DELAY_TIME);
                    query.moveToPosition(-1);
                    boolean z = false;
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndex);
                        int i = query.getInt(columnIndex2);
                        int i2 = query.getInt(columnIndex3);
                        concurrentHashMap.put(string, Integer.valueOf(i + i2));
                        if (i2 > 0 && i2 < 1380) {
                            z = true;
                        }
                        arrayList.add(string);
                    }
                    LogUtil.d("AppTimeLockService", "setTimeLimitedLockedSwitch, hasDelayOper = " + z);
                }
                synchronized (this.mTimeLimitedAppListLock) {
                    this.mTimeLimitedAppList = concurrentHashMap;
                    this.mLimitedList = arrayList;
                }
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "AppTimeObserver exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUnlockAppActivity(String str) {
        Intent intent = new Intent();
        intent.putExtra(NUBIA_LOCKED_START_ID, LOCKED_APP_DEFAULT_ID);
        intent.putExtra(LOCKED_PKG_NAME, str);
        intent.setAction(APPTIMELOCK_DELAY_ACTION);
        intent.setFlags(268435456);
        intent.addFlags(32768);
        this.mContext.startActivity(intent);
    }

    private int[] timeStrToIntHourMin(String str) {
        if (TextUtils.isEmpty(str) || str.indexOf(":") < 0) {
            return null;
        }
        int indexOf = str.indexOf(":");
        return new int[]{Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1, str.length()))};
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppStatusTable() {
        long delayTime = getDelayTime();
        try {
            ContentResolver contentResolver = this.mContext.getContentResolver();
            ContentValues contentValues = new ContentValues();
            if (delayTime > 0) {
                contentValues.put("status", "3");
                contentResolver.update(Uri.parse(APPSTATUS_URI), contentValues, "status = '2'", null);
            } else {
                contentValues.put("status", "2");
                contentResolver.update(Uri.parse(APPSTATUS_URI), contentValues, "status = '3'", null);
            }
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "updateAppStatusTable exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateGameAppPkg() {
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
            try {
                int columnIndex = query.getColumnIndex("component");
                ArrayList arrayList = new ArrayList();
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    arrayList.add(string.substring(0, string.indexOf(",")));
                }
                synchronized (this.mGameAppsLock) {
                    this.mGameApps = arrayList;
                }
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "Failed load game app data.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateParentalControlTimeScope() {
        try {
            Cursor query = this.mContext.getContentResolver().query(Uri.parse(SLEEPMODEMRG_URI), null, "name = ? ", new String[]{SLEEPMODEMRG_DAYTYPE}, null);
            try {
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    int columnIndex = query.getColumnIndex("value");
                    int columnIndex2 = query.getColumnIndex(SLEEPMODEMRG_BEGIN_TIME1);
                    int columnIndex3 = query.getColumnIndex(SLEEPMODEMRG_END_TIME1);
                    String string = query.getString(columnIndex);
                    if (SLEEPMODEMRG_WORKDAY.equals(string)) {
                        this.mWorkDayBeginTime = query.getString(columnIndex2);
                        this.mWorkDayEndTime = query.getString(columnIndex3);
                    } else if (SLEEPMODEMRG_RESTDAY.equals(string)) {
                        this.mRestDayBeginTime = query.getString(columnIndex2);
                        this.mRestDayEndTime = query.getString(columnIndex3);
                    }
                }
                this.mScopeFlag = -1;
                checkDateOk();
                if (query != null) {
                    query.close();
                }
            } finally {
            }
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "updateParentalControlTimeScope ");
        }
    }

    void delayLockApp(String str, int i) {
        this.mDelayLockApp = str;
        this.mDelayLockTime = i;
    }

    public void delayStartUnlockAppActivity(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        removeDelayCallbacks(concurrentHashMap);
        LogUtil.d("AppTimeLockService", "delayStartUnlockAppActivity");
        if (enableHealthReminder()) {
            LogUtil.d("AppTimeLockService", "enableHealthReminder");
            for (String str : concurrentHashMap.keySet()) {
                LogUtil.d("AppTimeLockService", str);
                if (this.mGameApps.contains(str)) {
                    if (concurrentHashMap.get(str).intValue() != 0) {
                        LogUtil.d("AppTimeLockService", str + " windowMode=" + concurrentHashMap.get(str));
                    } else {
                        LogUtil.i("AppTimeLockService", "delayMillis:" + getHealthReminderDelayTime());
                        postDelayNotify(MS_1_HOUR, R.string.app_time_lock_reminder_1, str);
                        postDelayNotify(MS_2_HOUR, R.string.app_time_lock_reminder_2, str);
                        postDelayNotify(MS_4_HOUR, R.string.app_time_lock_reminder_3, str);
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0069, code lost:
    
        if (r3 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0079, code lost:
    
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0076, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0074, code lost:
    
        if (r3 == null) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.concurrent.ConcurrentHashMap<java.lang.String, cn.nubia.gamecenter.settings.applearning.PackageLockRecord> queryOneKeyLockedAppFromDB() {
        /*
            r10 = this;
            java.lang.String r0 = "AppTimeLockService"
            java.lang.String r1 = "cursor size = "
            java.util.concurrent.ConcurrentHashMap r2 = new java.util.concurrent.ConcurrentHashMap
            r2.<init>()
            r3 = 0
            android.content.Context r10 = r10.mContext     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            android.content.ContentResolver r4 = r10.getContentResolver()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            android.net.Uri r5 = cn.nubia.gamecenter.settings.applearning.AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r8 = 0
            r9 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r3 == 0) goto L69
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r10.<init>(r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            int r1 = r3.getCount()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.StringBuilder r10 = r10.append(r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            cn.nubia.gamecenter.settings.utils.LogUtil.d(r0, r10)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
        L30:
            boolean r10 = r3.moveToNext()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            if (r10 == 0) goto L69
            r10 = 0
            java.lang.String r10 = r3.getString(r10)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r1 = 1
            long r4 = r3.getLong(r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r1 = 2
            long r6 = r3.getLong(r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r1 = 3
            r3.getInt(r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            cn.nubia.gamecenter.settings.applearning.PackageLockRecord r1 = new cn.nubia.gamecenter.settings.applearning.PackageLockRecord     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r1.<init>()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r1.packagename = r10     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r1.time = r8     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            long r8 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            long r8 = r8 - r4
            long r4 = java.lang.Math.abs(r8)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r8 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 / r8
            long r6 = r6 - r4
            r1.delayTime = r6     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            r2.put(r10, r1)     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6e
            goto L30
        L69:
            if (r3 == 0) goto L79
            goto L76
        L6c:
            r10 = move-exception
            goto L7a
        L6e:
            java.lang.String r10 = "queryOneKeyLockedAppFromDB error"
            cn.nubia.gamecenter.settings.utils.LogUtil.e(r0, r10)     // Catch: java.lang.Throwable -> L6c
            if (r3 == 0) goto L79
        L76:
            r3.close()
        L79:
            return r2
        L7a:
            if (r3 == 0) goto L7f
            r3.close()
        L7f:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamecenter.settings.applearning.AppTimeLockHelper.queryOneKeyLockedAppFromDB():java.util.concurrent.ConcurrentHashMap");
    }

    public void register() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DATE_CHANGED");
        intentFilter.addAction("android.intent.action.TIME_SET");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        this.mContext.registerReceiver(this.mTimeChangedReceiver, intentFilter, 2);
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.registerContentObserver(Settings.System.getUriFor("ss_multi_window_enabled"), false, this.mMultiWindowObserver);
        contentResolver.registerContentObserver(Settings.Global.getUriFor(DB_PARENTAL_CONTROL_SWITCH), false, this.mParentalControlModeObserver);
        contentResolver.registerContentObserver(Settings.Global.getUriFor(DB_TIME_SET_LIMIT), false, this.mEffectiveTimeObserve);
        try {
            contentResolver.registerContentObserver(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), false, this.mGamePackageAddObserver);
        } catch (Exception unused) {
            LogUtil.e("AppTimeLockService", "error register");
        }
    }

    public void removeDelayCallbacks(String str) {
        LogUtil.d("AppTimeLockService", "removeDelayCallbacks,pkgname = " + str);
        Iterator<ReminderRunnable> it = this.mReminderRunnables.iterator();
        while (it.hasNext()) {
            this.mAppTimeLockHandler.removeCallbacks(it.next());
        }
        if (this.mReminderRunnables.size() > 0) {
            LogUtil.d("AppTimeLockService", "removeDelayCallbacks, mReminderRunnables.size > 0");
        }
        this.mReminderRunnables.clear();
    }

    public void removeDelayCallbacks(ConcurrentHashMap<String, Integer> concurrentHashMap) {
        Iterator<String> it = concurrentHashMap.keySet().iterator();
        while (it.hasNext()) {
            LogUtil.d("AppTimeLockService", "removeDelayCallbacks,pkgname = " + it.next());
        }
        Iterator<ReminderRunnable> it2 = this.mReminderRunnables.iterator();
        while (it2.hasNext()) {
            this.mAppTimeLockHandler.removeCallbacks(it2.next());
        }
        if (this.mReminderRunnables.size() > 0) {
            LogUtil.d("AppTimeLockService", "removeDelayCallbacks, mReminderRunnables.size > 0");
        }
        this.mReminderRunnables.clear();
    }

    public void removeTimeoutOneKeyPkg() {
        Cursor cursor = null;
        try {
            try {
                ArrayList arrayList = new ArrayList();
                cursor = this.mContext.getContentResolver().query(AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI, null, null, null, null);
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(0);
                        long j = cursor.getLong(1);
                        long j2 = cursor.getLong(2);
                        cursor.getInt(3);
                        if (j + (j2 * 1000) + 1000 < System.currentTimeMillis()) {
                            arrayList.add(string);
                        }
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mContext.getContentResolver().delete(AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI, "packagename = ?", new String[]{(String) it.next()});
                }
                if (cursor == null) {
                    return;
                }
            } catch (Exception e) {
                LogUtil.e("AppTimeLockService", "error ", e);
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void syncNotifyOneKey() {
        this.mLockedAppMapOneKey = queryOneKeyLockedAppFromDB();
        removeDelayCallbacksOneKey();
        for (String str : this.mLockedAppMapOneKey.keySet()) {
            PackageLockRecord packageLockRecord = this.mLockedAppMapOneKey.get(str);
            long currentTimeMillis = (packageLockRecord.time + (packageLockRecord.delayTime * 1000)) - System.currentTimeMillis();
            if (currentTimeMillis > 0) {
                ReminderRunnableOneKey reminderRunnableOneKey = new ReminderRunnableOneKey(true, str, currentTimeMillis);
                this.mReminderRunnablesOneKey.add(reminderRunnableOneKey);
                this.mAppTimeLockHandler.postDelayed(reminderRunnableOneKey, currentTimeMillis);
            } else {
                ReminderRunnableOneKey reminderRunnableOneKey2 = new ReminderRunnableOneKey(true, str, 0L);
                this.mReminderRunnablesOneKey.add(reminderRunnableOneKey2);
                this.mAppTimeLockHandler.postDelayed(reminderRunnableOneKey2, 0L);
            }
        }
    }

    public void unregister() {
        LogUtil.d("AppTimeLockService", "unregister");
        BroadcastReceiver broadcastReceiver = this.mTimeChangedReceiver;
        if (broadcastReceiver != null) {
            this.mContext.unregisterReceiver(broadcastReceiver);
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        contentResolver.unregisterContentObserver(this.mMultiWindowObserver);
        contentResolver.unregisterContentObserver(this.mParentalControlModeObserver);
        contentResolver.unregisterContentObserver(this.mEffectiveTimeObserve);
        try {
            contentResolver.unregisterContentObserver(this.mGamePackageAddObserver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.mWorkLooper != null) {
            this.mWorkLooper.quit();
        }
    }

    public void updateOneKeyAsStartService() {
        Cursor cursor = null;
        try {
            try {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                cursor = this.mContext.getContentResolver().query(AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI, null, null, null, null);
                int i = 0;
                int i2 = 1;
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(i);
                        long j = cursor.getLong(i2);
                        long j2 = cursor.getLong(2);
                        cursor.getInt(3);
                        if (Math.abs(System.currentTimeMillis() - j) > j2 * 1000) {
                            arrayList2.add(string);
                        } else {
                            PackageLockRecord packageLockRecord = new PackageLockRecord();
                            packageLockRecord.packagename = string;
                            packageLockRecord.time = System.currentTimeMillis();
                            packageLockRecord.delayTime = j2 - (Math.abs(System.currentTimeMillis() - j) / 1000);
                            packageLockRecord.enable = 1;
                            arrayList.add(packageLockRecord);
                        }
                        i = 0;
                        i2 = 1;
                    }
                }
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    this.mContext.getContentResolver().delete(AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI, "packagename = ?", new String[]{(String) it.next()});
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    this.mContext.getContentResolver().insert(AppDbSchema.OneKeyLockedAppsTable.CONTENT_URI, PackageLockRecord.getContentValues((PackageLockRecord) it2.next()));
                }
                if (cursor == null) {
                    return;
                }
            } catch (Exception e) {
                LogUtil.e("AppTimeLockService", "error ", e);
                if (cursor == null) {
                    return;
                }
            }
            cursor.close();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
