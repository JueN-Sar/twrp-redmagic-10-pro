package cn.nubia.gamecenter.settings.summary.model;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.summary.entities.GameAppInfo;
import cn.nubia.gamecenter.settings.summary.entities.GameTimeInfo;
import cn.nubia.gamecenter.settings.summary.entities.OneGameTimeAndLaunchTimesInfo;
import cn.nubia.gamecenter.settings.summary.presenter.IGameParmsCallback;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.permissioncapsule.INbUsageStatsManager;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class GameTimeModeImpl implements IGameTimeMode {
    private static final String APPADD_URI_NO_NOTIFY = "content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false";
    private static final String ATTR_APP_NAME = "component";
    public static final long DAY_IN_MILLIS = 86400000;
    public static final String DB_GAME_CARRER_TIME = "game_carrer_totoal_time";
    private static final String PERMISSION_CAPSULE_CLASS_NAME = "cn.nubia.permissioncapsule.NbUsageStatsService";
    private static final String PERMISSION_CAPSULE_PKG_NAME = "cn.nubia.permissioncapsule";
    private static final String TAG = "GameTimeModeImpl";
    public static final long YEAR_IN_MILLIS = 31536000000L;
    private GameTimeParmTask mAsycTask;
    private Context mContext;
    private IGameParmsCallback mGameParmsCallback;
    private INbUsageStatsManager mNbUsMgr;
    private ServiceConnection mNbUsageStatsConn;
    private boolean mNormalizationAlgorithm;
    private OneGameTimeAndLaunchTimesInfo mOneGameAppTimeAndLaunchTimesInfo;
    private String mPkgName;
    private UsageStatsManager mUSM;

    public static class AppUsageStats {
        public long mBeginTimeStamp;
        public long mEndTimeStamp;
        public String mPackageName;
        public long mTotalTimeInForeground = 0;
    }

    private class GameTimeParmTask extends AsyncTask<Void, Void, GameTimeInfo[]> {
        public static final long ONE_MINITE = 60000;
        private final long FOUR_HOUR_MILLS;
        private Map<String, Long> mDayOfSevenGameTimeData;
        private List<List<GameAppInfo>> mGameAppList;
        private long mGames7DaysTimes;
        private Map<String, Long> mTodayGameTimeData;
        private Map<String, Long> mTotalGameAppLaunchTimesData;
        private Map<String, Long> mTotalGameTimeData;

        private GameTimeParmTask() {
            this.mGameAppList = new ArrayList();
            this.mTodayGameTimeData = new HashMap();
            this.mDayOfSevenGameTimeData = new HashMap();
            this.mTotalGameTimeData = new HashMap();
            this.mTotalGameAppLaunchTimesData = new HashMap();
            this.mGames7DaysTimes = 0L;
            this.FOUR_HOUR_MILLS = 14400000L;
        }

        private void addGameAppList(GameTimeInfo[] gameTimeInfoArr) {
            this.mGameAppList.add(getGameAppList(this.mTodayGameTimeData, gameTimeInfoArr[0]));
            this.mGameAppList.add(getGameAppList(this.mDayOfSevenGameTimeData, gameTimeInfoArr[1]));
        }

        private Long convertToAStandardDay(Long l, boolean z) {
            long longValue = l.longValue();
            if (z) {
                longValue = ((((longValue / 1000) / 60) / 60) / 24) * 86400000;
            }
            return Long.valueOf(longValue);
        }

        private void debugLogForAllAppsList() {
            for (String str : this.mTodayGameTimeData.keySet()) {
                LogUtil.d(GameTimeModeImpl.TAG, "debugLogForAllAppsList mTodayGameTimeData key:" + str + " v:" + this.mTodayGameTimeData.get(str));
            }
            for (String str2 : this.mDayOfSevenGameTimeData.keySet()) {
                LogUtil.d(GameTimeModeImpl.TAG, "debugLogForAllAppsList mDayOfSevenGameTimeData key:" + str2 + " v:" + this.mDayOfSevenGameTimeData.get(str2));
            }
        }

        private List<String> getAppListOfGameLauncher() {
            ArrayList arrayList = new ArrayList();
            try {
                Cursor query = GameTimeModeImpl.this.mContext.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
                try {
                    int columnIndex = query.getColumnIndex("component");
                    query.moveToPosition(-1);
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndex);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(string.substring(0, string.indexOf(44)));
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } finally {
                }
            } catch (Exception e) {
                LogUtil.e(GameTimeModeImpl.TAG, "Failed getAppListOfGameLauncher.", e);
            }
            return arrayList;
        }

        private List<GameAppInfo> getGameAppList(Map<String, Long> map, GameTimeInfo gameTimeInfo) {
            ArrayList arrayList = new ArrayList();
            if (map == null) {
                LogUtil.e(GameTimeModeImpl.TAG, "getGameAppList, gameTimeData is null !");
                return arrayList;
            }
            PackageManager packageManager = GameTimeModeImpl.this.mContext.getPackageManager();
            int i = 0;
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                String key = entry.getKey();
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(key, 0);
                    if (applicationInfo != null) {
                        GameAppInfo gameAppInfo = new GameAppInfo();
                        gameAppInfo.icon = packageManager.getApplicationIcon(applicationInfo);
                        CharSequence loadLabel = applicationInfo.loadLabel(packageManager);
                        gameAppInfo.label = loadLabel != null ? loadLabel.toString() : key;
                        gameAppInfo.totalTimeInForeground = entry.getValue().longValue();
                        gameAppInfo.pkgName = applicationInfo.packageName;
                        arrayList.add(gameAppInfo);
                        if (i == 0) {
                            LogUtil.d(GameTimeModeImpl.TAG, "getGameAppList, max time : " + gameAppInfo.totalTimeInForeground + ", pkgName : " + key);
                            gameTimeInfo.mMaxTimeAppIcon = gameAppInfo.icon;
                            gameTimeInfo.mMaxTimePkgName = key;
                            gameTimeInfo.label = gameAppInfo.label;
                            gameTimeInfo.mGameMaxTime = gameAppInfo.totalTimeInForeground;
                        }
                        i++;
                        gameTimeInfo.mGameTotalTime += gameAppInfo.totalTimeInForeground;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    LogUtil.e(GameTimeModeImpl.TAG, "getGameAppList, Exception: " + e);
                }
            }
            LogUtil.d(GameTimeModeImpl.TAG, "getGameAppList, mGameTotalTime : " + gameTimeInfo.mGameTotalTime);
            return arrayList;
        }

        private long getSelectionArgs(boolean z, int i) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
            if (!z) {
                calendar.set(5, calendar.get(5) - i);
            }
            long timeInMillis = (calendar.getTimeInMillis() / SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US) * SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US;
            LogUtil.d(GameTimeModeImpl.TAG, "getSelectionArgs, isToday = " + z + ", result = " + timeInMillis + ", preDayCount = " + i);
            return timeInMillis;
        }

        private void parseGameTotalTimes(List<UsageStats> list, List<String> list2, String str) {
            long j = 0;
            long j2 = 0;
            for (UsageStats usageStats : list) {
                String packageName = usageStats.getPackageName();
                if (list2.size() > 0 && list2.contains(packageName)) {
                    j += usageStats.getTotalTimeInForeground();
                    if (packageName.equals(str)) {
                        long totalTimeInForeground = usageStats.getTotalTimeInForeground();
                        j2 += totalTimeInForeground;
                        LogUtil.d(GameTimeModeImpl.TAG, "parseGameTotalTimes, pkgname = " + packageName + ", time = " + totalTimeInForeground);
                    }
                }
            }
            LogUtil.d(GameTimeModeImpl.TAG, "parseGameTotalTimes gamesTotalTimes:" + j + ", appTotalTime:" + j2);
            long msToMinite = msToMinite(j2) * 60000;
            if (msToMinite > GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.totalTimeInForeground7Days) {
                GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.totalTimeInForeground = msToMinite;
            } else {
                GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.totalTimeInForeground = GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.totalTimeInForeground7Days;
            }
            long msToMinite2 = msToMinite(j) * 60000;
            long j3 = this.mGames7DaysTimes;
            if (msToMinite2 <= j3) {
                GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.launchTimes = GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.launchTimes7Days;
                msToMinite2 = j3;
            }
            Settings.Global.putFloat(GameTimeModeImpl.this.mContext.getContentResolver(), GameTimeModeImpl.DB_GAME_CARRER_TIME, Float.valueOf(new BigDecimal(Float.valueOf(((msToMinite2 / 1000.0f) / 60.0f) / 60.0f).floatValue()).setScale(1, 5).floatValue()).floatValue());
        }

        private int parseLaunchCount(List<UsageStats> list, List<String> list2, String str) {
            int i = 0;
            for (UsageStats usageStats : list) {
                String packageName = usageStats.getPackageName();
                if (list2.size() > 0 && list2.contains(packageName) && packageName.equals(str)) {
                    try {
                        int intValue = ((Integer) usageStats.getClass().getDeclaredMethod("getAppLaunchCount", new Class[0]).invoke(usageStats, new Object[0])).intValue();
                        i += intValue;
                        LogUtil.d(GameTimeModeImpl.TAG, "parseLaunchCount, pkgname = " + packageName + ", launchCount = " + intValue);
                    } catch (Exception unused) {
                    }
                }
            }
            return i;
        }

        private void parseUsageEvents(UsageEvents usageEvents, List<String> list, Map<String, Long> map) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            while (usageEvents.hasNextEvent()) {
                UsageEvents.Event event = new UsageEvents.Event();
                usageEvents.getNextEvent(event);
                String packageName = event.getPackageName();
                if (list.size() > 0 && list.contains(packageName)) {
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
                            LogUtil.e(GameTimeModeImpl.TAG, "parseUsageEvents, is invalid data = " + packageName);
                        }
                    }
                }
            }
            for (Map.Entry entry : concurrentHashMap.entrySet()) {
                String str = (String) entry.getKey();
                long j = ((AppUsageStats) entry.getValue()).mTotalTimeInForeground;
                LogUtil.e(GameTimeModeImpl.TAG, "parseUsageEvents, name= " + str + ", apptime = " + j);
                if (j <= 0) {
                    j = 0;
                }
                putValue(map, str, j);
            }
            concurrentHashMap.clear();
        }

        private void parseUsageEventsNew(UsageEvents usageEvents, List<String> list, Map<String, Long> map, long j, long j2) {
            Map<String, Long> map2;
            int instanceId;
            int i;
            GameTimeParmTask gameTimeParmTask = this;
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            HashMap hashMap = new HashMap();
            long j3 = 0;
            while (usageEvents.hasNextEvent()) {
                UsageEvents.Event event = new UsageEvents.Event();
                usageEvents.getNextEvent(event);
                String packageName = event.getPackageName();
                if (list.size() > 0 && list.contains(packageName) && (instanceId = gameTimeParmTask.getInstanceId(event)) > 0) {
                    if (hashMap.containsKey(Integer.valueOf(instanceId))) {
                        if (2 == event.getEventType()) {
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTotalTimeInForeground += event.getTimeStamp() - ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTimeStamp;
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType = event.getEventType();
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTimeStamp = event.getTimeStamp();
                        } else if (1 == event.getEventType()) {
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType = event.getEventType();
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTimeStamp = event.getTimeStamp();
                        } else if (23 == event.getEventType() && 1 == ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType) {
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTotalTimeInForeground += event.getTimeStamp() - ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTimeStamp;
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType = 2;
                            ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTimeStamp = event.getTimeStamp();
                        }
                    } else if (1 == event.getEventType()) {
                        hashMap.put(Integer.valueOf(instanceId), new NbActivityEvent());
                        ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mPackageName = packageName;
                        ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType = event.getEventType();
                        ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTimeStamp = event.getTimeStamp();
                    } else {
                        if (2 == event.getEventType()) {
                            long j4 = j3 != 0 ? j3 : j;
                            if (event.getTimeStamp() - j4 <= 14400000) {
                                hashMap.put(Integer.valueOf(instanceId), new NbActivityEvent());
                                ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mPackageName = packageName;
                                ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType = event.getEventType();
                                ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTotalTimeInForeground = event.getTimeStamp() - j4;
                            }
                        } else if (23 == event.getEventType()) {
                            long j5 = j3 != 0 ? j3 : j;
                            if (event.getTimeStamp() - j5 <= 14400000) {
                                hashMap.put(Integer.valueOf(instanceId), new NbActivityEvent());
                                ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mPackageName = packageName;
                                i = 2;
                                ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mEventType = 2;
                                ((NbActivityEvent) hashMap.get(Integer.valueOf(instanceId))).mTotalTimeInForeground = event.getTimeStamp() - j5;
                            }
                        }
                        if (event.getEventType() != i || event.getEventType() == 23 || event.getEventType() == 1) {
                            j3 = event.getTimeStamp();
                        }
                    }
                    i = 2;
                    if (event.getEventType() != i) {
                    }
                    j3 = event.getTimeStamp();
                }
                gameTimeParmTask = this;
            }
            for (Map.Entry entry : hashMap.entrySet()) {
                NbActivityEvent nbActivityEvent = (NbActivityEvent) entry.getValue();
                if (!concurrentHashMap.containsKey(nbActivityEvent.mPackageName)) {
                    AppUsageStats appUsageStats = new AppUsageStats();
                    appUsageStats.mEndTimeStamp = nbActivityEvent.mTimeStamp;
                    concurrentHashMap.put(nbActivityEvent.mPackageName, appUsageStats);
                }
                if (1 == nbActivityEvent.mEventType) {
                    UsageEvents queryEvents = queryEvents(GameTimeModeImpl.this.mContext, j, j2);
                    boolean z = false;
                    while (true) {
                        if (!queryEvents.hasNextEvent()) {
                            break;
                        }
                        UsageEvents.Event event2 = new UsageEvents.Event();
                        queryEvents.getNextEvent(event2);
                        if (1 == event2.getEventType()) {
                            if (((Integer) entry.getKey()).intValue() == getInstanceId(event2) && nbActivityEvent.mTimeStamp == event2.getTimeStamp()) {
                                z = true;
                            } else if (z) {
                                if (event2.getTimeStamp() >= j2 || event2.getTimeStamp() <= nbActivityEvent.mTimeStamp) {
                                    ((AppUsageStats) concurrentHashMap.get(nbActivityEvent.mPackageName)).mTotalTimeInForeground += j2 - nbActivityEvent.mTimeStamp;
                                    nbActivityEvent.mEventType = 2;
                                } else {
                                    ((AppUsageStats) concurrentHashMap.get(nbActivityEvent.mPackageName)).mTotalTimeInForeground += event2.getTimeStamp() - nbActivityEvent.mTimeStamp;
                                    nbActivityEvent.mEventType = 2;
                                }
                            }
                        }
                    }
                    if (1 == nbActivityEvent.mEventType) {
                        ((AppUsageStats) concurrentHashMap.get(nbActivityEvent.mPackageName)).mTotalTimeInForeground += j2 - nbActivityEvent.mTimeStamp;
                        nbActivityEvent.mEventType = 2;
                    }
                }
                if (nbActivityEvent.mTimeStamp != 0) {
                    ((AppUsageStats) concurrentHashMap.get(nbActivityEvent.mPackageName)).mTotalTimeInForeground += nbActivityEvent.mTotalTimeInForeground;
                } else if (nbActivityEvent.mTotalTimeInForeground <= nbActivityEvent.mTimeStamp - ((AppUsageStats) concurrentHashMap.get(nbActivityEvent.mPackageName)).mEndTimeStamp) {
                    ((AppUsageStats) concurrentHashMap.get(nbActivityEvent.mPackageName)).mTotalTimeInForeground += nbActivityEvent.mTotalTimeInForeground;
                }
            }
            for (Map.Entry entry2 : concurrentHashMap.entrySet()) {
                String str = (String) entry2.getKey();
                long j6 = ((AppUsageStats) entry2.getValue()).mTotalTimeInForeground;
                LogUtil.e(GameTimeModeImpl.TAG, "parseUsageEvents, name= " + str + ", apptime = " + j6);
                if (j6 > 0) {
                    map2 = map;
                } else {
                    map2 = map;
                    j6 = 0;
                }
                putValue(map2, str, j6);
            }
            concurrentHashMap.clear();
            hashMap.clear();
        }

        private void parseUsageEventsOneGame(UsageEvents usageEvents, List<String> list) {
            LogUtil.e(GameTimeModeImpl.TAG, "parseUsageEventsOneGame");
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            while (usageEvents.hasNextEvent()) {
                UsageEvents.Event event = new UsageEvents.Event();
                usageEvents.getNextEvent(event);
                String packageName = event.getPackageName();
                if (list.size() > 0 && list.contains(packageName) && GameTimeModeImpl.this.mPkgName.equals(packageName)) {
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
                            LogUtil.e(GameTimeModeImpl.TAG, "parseUsageEvents, is invalid data = " + packageName);
                        }
                    }
                }
            }
            GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days.clear();
            Iterator it = concurrentHashMap.entrySet().iterator();
            while (true) {
                long j = 0;
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                String str = (String) entry.getKey();
                long j2 = ((AppUsageStats) entry.getValue()).mTotalTimeInForeground;
                LogUtil.e(GameTimeModeImpl.TAG, "parseUsageEventsOneGame, name= " + str + ", apptime = " + j2);
                List<Long> list2 = GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days;
                if (j2 > 0) {
                    j = j2;
                }
                list2.add(Long.valueOf(j));
            }
            if (concurrentHashMap.size() == 0) {
                GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days.add(0L);
            }
            concurrentHashMap.clear();
        }

        private void parseUsageStats(List<UsageStats> list, List<String> list2, int i, long j, long j2) {
            LogUtil.d(GameTimeModeImpl.TAG, "parseUsageStats");
            for (UsageStats usageStats : list) {
                String packageName = usageStats.getPackageName();
                if (list2.size() > 0 && list2.contains(packageName)) {
                    LogUtil.d(GameTimeModeImpl.TAG, "parseUsageStats, pkgname = " + packageName + ", mBeginTimeStamp = " + usageStats.getFirstTimeStamp() + ", mEndTimeStamp = " + usageStats.getLastTimeStamp() + ", mLastTimeUsed = " + usageStats.getLastTimeUsed() + ", mTotalTimeInForeground = " + usageStats.getTotalTimeInForeground());
                    if (i != 0 || (usageStats.getLastTimeUsed() <= j2 && usageStats.getLastTimeUsed() >= j)) {
                        long totalTimeInForeground = usageStats.getTotalTimeInForeground();
                        if (totalTimeInForeground > 0) {
                            if (i == 0) {
                                putValue(this.mTodayGameTimeData, packageName, totalTimeInForeground);
                            } else {
                                putValue(this.mDayOfSevenGameTimeData, packageName, totalTimeInForeground);
                            }
                        }
                    }
                }
            }
        }

        private void parseUsageStats(List<UsageStats> list, List<String> list2, Map<String, Long> map) {
            LogUtil.d(GameTimeModeImpl.TAG, "parseUsageStats");
            for (UsageStats usageStats : list) {
                String packageName = usageStats.getPackageName();
                if (list2.size() > 0 && list2.contains(packageName)) {
                    long totalTimeInForeground = usageStats.getTotalTimeInForeground();
                    if (totalTimeInForeground > 0) {
                        putValue(map, packageName, totalTimeInForeground);
                    }
                    try {
                        LogUtil.d(GameTimeModeImpl.TAG, "parseUsageStats, pkgname = " + packageName + ", time = " + totalTimeInForeground + ", launchTimes = " + ((Integer) usageStats.getClass().getDeclaredMethod("getAppLaunchCount", new Class[0]).invoke(usageStats, new Object[0])).intValue());
                    } catch (Exception unused) {
                    }
                }
            }
        }

        private void putValue(Map<String, Long> map, String str, long j) {
            if (map.containsKey(str)) {
                map.put(str, Long.valueOf(j + map.get(str).longValue()));
            } else {
                map.put(str, Long.valueOf(j));
            }
        }

        private void queryDataByUsageStatsMgr() {
            int i;
            long j = 0;
            this.mGames7DaysTimes = 0L;
            long currentTimeMillis = System.currentTimeMillis();
            List<String> appListOfGameLauncher = getAppListOfGameLauncher();
            int i2 = 1;
            long selectionArgs = getSelectionArgs(true, 0);
            if (GameTimeModeImpl.this.mNormalizationAlgorithm) {
                this.mTodayGameTimeData = queryNbStatsTime(GameTimeModeImpl.this.mContext, appListOfGameLauncher, selectionArgs, currentTimeMillis);
            } else {
                parseUsageEventsNew(queryEvents(GameTimeModeImpl.this.mContext, selectionArgs, currentTimeMillis), appListOfGameLauncher, this.mTodayGameTimeData, selectionArgs, currentTimeMillis);
            }
            mapToMins(this.mTodayGameTimeData);
            mapToMills(this.mTodayGameTimeData);
            long selectionArgs2 = getSelectionArgs(false, 6);
            List<Map<String, Long>> arrayList = new ArrayList<>();
            if (GameTimeModeImpl.this.mNormalizationAlgorithm) {
                arrayList = queryNbStatsDistribution(GameTimeModeImpl.this.mContext, appListOfGameLauncher, selectionArgs2, currentTimeMillis, 86400000L);
                i = 6;
            } else {
                i = 6;
                long j2 = selectionArgs2;
                int i3 = 7;
                while (i3 > 0) {
                    long j3 = i2 == i3 ? currentTimeMillis : j2 + 86400000;
                    HashMap hashMap = new HashMap();
                    List<Map<String, Long>> list = arrayList;
                    parseUsageEventsNew(queryEvents(GameTimeModeImpl.this.mContext, j2, j3), appListOfGameLauncher, hashMap, j2, j3);
                    list.add(hashMap);
                    i3--;
                    arrayList = list;
                    j2 = j3;
                    i2 = 1;
                }
            }
            GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days.clear();
            for (Map<String, Long> map : arrayList) {
                mapToMins(map);
                mapToMills(map);
                if (!map.containsKey(GameTimeModeImpl.this.mPkgName)) {
                    GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days.add(0L);
                }
                for (Map.Entry<String, Long> entry : map.entrySet()) {
                    this.mGames7DaysTimes += entry.getValue().longValue();
                    putValue(this.mDayOfSevenGameTimeData, entry.getKey(), entry.getValue().longValue());
                    if (!TextUtils.isEmpty(GameTimeModeImpl.this.mPkgName) && GameTimeModeImpl.this.mPkgName.equals(entry.getKey())) {
                        GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days.add(entry.getValue());
                    }
                }
            }
            Iterator<Long> it = GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.mDayTimesIn7Days.iterator();
            long j4 = 0;
            while (it.hasNext()) {
                j4 += it.next().longValue();
            }
            GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.totalTimeInForeground7Days = j4;
            GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.launchTimes7Days = parseLaunchCount(queryUsageStats(GameTimeModeImpl.this.mContext, 4, getSelectionArgs(false, i), System.currentTimeMillis()), appListOfGameLauncher, GameTimeModeImpl.this.mPkgName);
            long currentTimeMillis2 = System.currentTimeMillis() - AppUsageStatsHelper.THREE_YEARS;
            GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.launchTimes = parseLaunchCount(queryUsageStats(GameTimeModeImpl.this.mContext, 4, currentTimeMillis2, System.currentTimeMillis()), appListOfGameLauncher, GameTimeModeImpl.this.mPkgName);
            if (!GameTimeModeImpl.this.mNormalizationAlgorithm) {
                parseGameTotalTimes(queryUsageStats(GameTimeModeImpl.this.mContext, 4, currentTimeMillis2, System.currentTimeMillis()), appListOfGameLauncher, GameTimeModeImpl.this.mPkgName);
                return;
            }
            long j5 = 0;
            for (Map.Entry<String, Long> entry2 : queryNbStatsTotalTime(GameTimeModeImpl.this.mContext, appListOfGameLauncher).entrySet()) {
                if (!TextUtils.isEmpty(GameTimeModeImpl.this.mPkgName) && GameTimeModeImpl.this.mPkgName.equals(entry2.getKey())) {
                    j += entry2.getValue().longValue();
                }
                j5 += entry2.getValue().longValue();
            }
            GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo.totalTimeInForeground = msToMinite(j) * 60000;
            Settings.Global.putFloat(GameTimeModeImpl.this.mContext.getContentResolver(), GameTimeModeImpl.DB_GAME_CARRER_TIME, Float.valueOf(new BigDecimal(Float.valueOf((((msToMinite(j5) * 60000) / 1000.0f) / 60.0f) / 60.0f).floatValue()).setScale(1, 5).floatValue()).floatValue());
        }

        private UsageEvents queryEvents(Context context, long j, long j2) {
            if (!GameTimeModeImpl.this.usePermissionCapsule()) {
                return AppUsageStatsHelper.queryEvents(context, j, j2);
            }
            try {
                return GameTimeModeImpl.this.mNbUsMgr.queryEvents(j, j2);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }

        private List<Map<String, Long>> queryNbStatsDistribution(Context context, List<String> list, long j, long j2, long j3) {
            ArrayList arrayList = new ArrayList();
            if (!GameTimeModeImpl.this.usePermissionCapsule()) {
                return AppUsageStatsHelper.queryNbStatsDistribution(context, list, j, j2, j3);
            }
            try {
                return GameTimeModeImpl.this.mNbUsMgr.queryNbStatsDistribution(list, j, j2, j3);
            } catch (RemoteException e) {
                e.printStackTrace();
                return arrayList;
            }
        }

        private Map<String, Long> queryNbStatsTime(Context context, List<String> list, long j, long j2) {
            HashMap hashMap = new HashMap();
            LogUtil.i(GameTimeModeImpl.TAG, "time:" + j + "-" + j2 + ", pkg:" + list);
            if (!GameTimeModeImpl.this.usePermissionCapsule()) {
                return AppUsageStatsHelper.queryNbStatsTime(context, list, j, j2);
            }
            try {
                return GameTimeModeImpl.this.mNbUsMgr.queryNbStatsTime(list, j, j2);
            } catch (RemoteException e) {
                e.printStackTrace();
                return hashMap;
            }
        }

        private Map<String, Long> queryNbStatsTotalTime(Context context, List<String> list) {
            HashMap hashMap = new HashMap();
            if (!GameTimeModeImpl.this.usePermissionCapsule()) {
                return AppUsageStatsHelper.queryNbStatsTotalTime(context, list);
            }
            try {
                return GameTimeModeImpl.this.mNbUsMgr.queryNbStatsTotalTime(list);
            } catch (RemoteException e) {
                e.printStackTrace();
                return hashMap;
            }
        }

        private List<UsageStats> queryUsageStats(Context context, int i, long j, long j2) {
            if (!GameTimeModeImpl.this.usePermissionCapsule()) {
                return AppUsageStatsHelper.queryUsageStats(context, i, j, j2);
            }
            try {
                return GameTimeModeImpl.this.mNbUsMgr.queryUsageStats(i, j, j2);
            } catch (RemoteException e) {
                e.printStackTrace();
                return null;
            }
        }

        private void setTimeSpan(GameTimeInfo gameTimeInfo, GameTimeInfo gameTimeInfo2) {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(2) + 1;
            int i2 = calendar.get(5);
            calendar.add(5, -6);
            int i3 = calendar.get(2) + 1;
            int i4 = calendar.get(5);
            gameTimeInfo.mGameTimeSpan = i + "." + i2;
            gameTimeInfo2.mGameTimeSpan = i3 + "." + i4 + "-" + i + "." + i2;
            LogUtil.d(GameTimeModeImpl.TAG, "setTimeSpan, today: " + gameTimeInfo.mGameTimeSpan + ", dayOfSeven: " + gameTimeInfo2.mGameTimeSpan);
        }

        public void clearMap() {
            LogUtil.e(GameTimeModeImpl.TAG, "clearMap: ");
            Map<String, Long> map = this.mTodayGameTimeData;
            if (map != null) {
                map.clear();
            }
            Map<String, Long> map2 = this.mDayOfSevenGameTimeData;
            if (map2 != null) {
                map2.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public GameTimeInfo[] doInBackground(Void... voidArr) {
            GameTimeInfo[] gameTimeInfoArr = {new GameTimeInfo(), new GameTimeInfo()};
            try {
                queryDataByUsageStatsMgr();
                this.mTodayGameTimeData = GameTimeModeImpl.this.sortMapByValue(this.mTodayGameTimeData);
                this.mDayOfSevenGameTimeData = GameTimeModeImpl.this.sortMapByValue(this.mDayOfSevenGameTimeData);
                setTimeSpan(gameTimeInfoArr[0], gameTimeInfoArr[1]);
                addGameAppList(gameTimeInfoArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gameTimeInfoArr;
        }

        public int getInstanceId(UsageEvents.Event event) {
            try {
                Method declaredMethod = Class.forName(event.getClass().getName()).getDeclaredMethod("getInstanceId", new Class[0]);
                declaredMethod.setAccessible(true);
                return ((Integer) declaredMethod.invoke(event, new Object[0])).intValue();
            } catch (Exception e) {
                LogUtil.e(GameTimeModeImpl.TAG, "getInstanceId, " + e);
                return -1;
            }
        }

        public <K> void mapToMills(Map<K, Long> map) {
            for (Map.Entry<K, Long> entry : map.entrySet()) {
                map.put(entry.getKey(), Long.valueOf(entry.getValue().longValue() * 60000));
            }
        }

        public <K> void mapToMins(Map<K, Long> map) {
            for (Map.Entry<K, Long> entry : map.entrySet()) {
                map.put(entry.getKey(), Long.valueOf(msToMinite(entry.getValue().longValue())));
            }
        }

        public long msToMinite(long j) {
            long j2 = (59999 + j) / 60000;
            if (j <= 0 || j >= 60000) {
                return j2;
            }
            return 1L;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(GameTimeInfo[] gameTimeInfoArr) {
            ArrayList arrayList = new ArrayList();
            for (List<GameAppInfo> list : this.mGameAppList) {
                ArrayList arrayList2 = new ArrayList();
                Iterator<GameAppInfo> it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add(it.next());
                }
                arrayList.add(arrayList2);
            }
            List<List<GameAppInfo>> list2 = this.mGameAppList;
            if (list2 != null) {
                list2.clear();
            }
            GameTimeModeImpl.this.mGameParmsCallback.gameParmsCallback(gameTimeInfoArr, arrayList);
            GameTimeModeImpl.this.mGameParmsCallback.oneGameParmsCallback(GameTimeModeImpl.this.mOneGameAppTimeAndLaunchTimesInfo);
        }
    }

    private static class MapValueComparator implements Comparator<Map.Entry<String, Long>> {
        private MapValueComparator() {
        }

        @Override // java.util.Comparator
        public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
            return entry2.getValue().compareTo(entry.getValue());
        }
    }

    public static class NbActivityEvent {
        public String mPackageName;
        public int mEventType = 0;
        public long mTimeStamp = 0;
        public long mTotalTimeInForeground = 0;
    }

    public GameTimeModeImpl(Context context, IGameParmsCallback iGameParmsCallback) {
        this.mOneGameAppTimeAndLaunchTimesInfo = new OneGameTimeAndLaunchTimesInfo();
        this.mPkgName = "";
        this.mNbUsMgr = null;
        this.mNbUsageStatsConn = null;
        this.mContext = context;
        this.mGameParmsCallback = iGameParmsCallback;
        initNormalizationAlgorithm();
    }

    public GameTimeModeImpl(Context context, String str, IGameParmsCallback iGameParmsCallback) {
        this.mOneGameAppTimeAndLaunchTimesInfo = new OneGameTimeAndLaunchTimesInfo();
        this.mNbUsMgr = null;
        this.mNbUsageStatsConn = null;
        this.mPkgName = str;
        this.mContext = context;
        this.mGameParmsCallback = iGameParmsCallback;
        initNormalizationAlgorithm();
    }

    private void bindUsageStatsService() {
        LogUtil.d(TAG, "bind....");
        if (this.mNbUsageStatsConn == null) {
            this.mNbUsageStatsConn = new ServiceConnection() { // from class: cn.nubia.gamecenter.settings.summary.model.GameTimeModeImpl.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    LogUtil.d(GameTimeModeImpl.TAG, "onServiceConnected");
                    GameTimeModeImpl.this.mNbUsMgr = INbUsageStatsManager.Stub.asInterface(iBinder);
                    GameTimeModeImpl.this.executeGameTimeParmTask();
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    LogUtil.d(GameTimeModeImpl.TAG, "onServiceConnected, is disconnected.");
                    GameTimeModeImpl.this.mNbUsMgr = null;
                    GameTimeModeImpl.this.cancelGameTimeParmTask();
                }
            };
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PERMISSION_CAPSULE_PKG_NAME, PERMISSION_CAPSULE_CLASS_NAME));
        this.mContext.bindService(intent, this.mNbUsageStatsConn, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelGameTimeParmTask() {
        GameTimeParmTask gameTimeParmTask = this.mAsycTask;
        if (gameTimeParmTask != null) {
            gameTimeParmTask.cancel(true);
            this.mAsycTask.clearMap();
            this.mAsycTask = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeGameTimeParmTask() {
        LogUtil.d(TAG, "executeGameTimeParmTask");
        try {
            GameTimeParmTask gameTimeParmTask = this.mAsycTask;
            if (gameTimeParmTask != null) {
                gameTimeParmTask.cancel(true);
            }
            GameTimeParmTask gameTimeParmTask2 = new GameTimeParmTask();
            this.mAsycTask = gameTimeParmTask2;
            gameTimeParmTask2.execute(new Void[0]);
        } catch (IllegalStateException e) {
            LogUtil.d(TAG, "onServiceConnected, exception: " + e);
        }
    }

    private void initNormalizationAlgorithm() {
        UsageStatsManager usageStatsManager = (UsageStatsManager) this.mContext.getSystemService("usagestats");
        this.mUSM = usageStatsManager;
        try {
            usageStatsManager.getClass().getDeclaredMethod("queryNbStatsTotalTime", List.class);
            this.mNormalizationAlgorithm = true;
        } catch (NoSuchMethodException unused) {
            this.mNormalizationAlgorithm = false;
        }
    }

    private void unbindUsageStatsService() {
        LogUtil.d(TAG, "unbind....");
        if (this.mNbUsMgr != null) {
            this.mContext.unbindService(this.mNbUsageStatsConn);
            this.mNbUsageStatsConn = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean usePermissionCapsule() {
        return !CommonUtil.isAndroidU() && (FeatureUtil.contains679Or709() || CommonUtil.isP720P01() || CommonUtil.isK68());
    }

    @Override // cn.nubia.gamecenter.settings.summary.model.IGameTimeMode
    public void cancelLoadTask() {
        if (usePermissionCapsule()) {
            unbindUsageStatsService();
        } else {
            cancelGameTimeParmTask();
        }
    }

    public Map<String, Long> sortMapByValue(Map<String, Long> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList<Map.Entry> arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new MapValueComparator());
        for (Map.Entry entry : arrayList) {
            linkedHashMap.put((String) entry.getKey(), (Long) entry.getValue());
        }
        return linkedHashMap;
    }

    @Override // cn.nubia.gamecenter.settings.summary.model.IGameTimeMode
    public void startLoadGameTimeParms() {
        LogUtil.d(TAG, "startLoadGameTimeParms");
        if (usePermissionCapsule()) {
            bindUsageStatsService();
        } else {
            executeGameTimeParmTask();
        }
    }
}
