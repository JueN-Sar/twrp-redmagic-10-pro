package cn.nubia.common.helper;

import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.os.UserHandle;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import cn.nubia.common.CommonApplication;
import cn.nubia.permissioncapsule.INbUsageStatsManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class AppUsageStatsHelper {
    private static final String METHOD_QUERY_NB_STATS_DISTRIBUTION = "queryNbStatsDistribution";
    private static final String METHOD_QUERY_NB_STATS_TIME = "queryNbStatsTime";
    private static final String METHOD_QUERY_NB_STATS_TOTAL_TIME = "queryNbStatsTotalTime";
    public static final long MINUTE = 60000;
    public static final long MIN_INTERVAL = 2000;
    private static final String PERMISSION_CAPSULE_CLASS_NAME = "cn.nubia.permissioncapsule.NbUsageStatsService";
    private static final String PERMISSION_CAPSULE_PKG_NAME = "cn.nubia.permissioncapsule";
    private static final String TAG = "Usage";
    public static final long THREE_YEARS = 94608000000L;
    private boolean isBind;
    Runnable mCallback;
    private long mCutoffTotalTime;
    long mLastUpdateTime;
    CopyOnWriteArrayList<AppUsageStatsChangedListener> mListener;
    private INbUsageStatsManager mNbUsMgr;
    private ServiceConnection mNbUsageStatsConn;
    HashMap<String, UsageStats> mUsageStatsMaps;

    public interface AppUsageStatsChangedListener {
        void onUsageStatsChanged(boolean z);
    }

    private static class AppUsageStatsHelperHolder {
        public static final AppUsageStatsHelper INSTANCE = new AppUsageStatsHelper();

        private AppUsageStatsHelperHolder() {
        }
    }

    private class LoadAppsTimeTask extends AsyncTask<Void, Void, HashMap<String, UsageStats>> {
        private LoadAppsTimeTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public HashMap<String, UsageStats> doInBackground(Void... voidArr) {
            Log.d(AppUsageStatsHelper.TAG, "doInBackground()");
            AppUsageStatsHelper.this.mUsageStatsMaps.clear();
            try {
                AppUsageStatsHelper appUsageStatsHelper = AppUsageStatsHelper.this;
                for (UsageStats usageStats : appUsageStatsHelper.queryAppUsageStats(appUsageStatsHelper.getAppContext(), 3, AppUsageStatsHelper.this.getBeginTime(), System.currentTimeMillis())) {
                    AppUsageStatsHelper.this.mUsageStatsMaps.put(usageStats.getPackageName(), usageStats);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return AppUsageStatsHelper.this.mUsageStatsMaps;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(HashMap<String, UsageStats> hashMap) {
            boolean z;
            Log.d(AppUsageStatsHelper.TAG, "onPostExecute() maps.size() : " + hashMap.size());
            AppUsageStatsHelper.this.mLastUpdateTime = System.currentTimeMillis();
            if (AppUsageStatsHelper.this.mCallback != null) {
                AppUsageStatsHelper.this.mCallback.run();
                AppUsageStatsHelper.this.mCallback = null;
                z = true;
            } else {
                z = false;
            }
            Iterator<AppUsageStatsChangedListener> it = AppUsageStatsHelper.this.mListener.iterator();
            while (it.hasNext()) {
                it.next().onUsageStatsChanged(z);
            }
            AppUsageStatsHelper.this.unbindUsageStatsService();
        }
    }

    private AppUsageStatsHelper() {
        this.mNbUsMgr = null;
        this.isBind = false;
        this.mLastUpdateTime = 0L;
        this.mListener = new CopyOnWriteArrayList<>();
        this.mUsageStatsMaps = new HashMap<>();
        this.mCutoffTotalTime = 1L;
        this.mNbUsageStatsConn = new ServiceConnection() { // from class: cn.nubia.common.helper.AppUsageStatsHelper.1
            @Override // android.content.ServiceConnection
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(AppUsageStatsHelper.TAG, "onServiceConnected");
                AppUsageStatsHelper.this.mNbUsMgr = INbUsageStatsManager.Stub.asInterface(iBinder);
                AppUsageStatsHelper.this.startLoadAppTime();
                AppUsageStatsHelper.this.isBind = true;
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(AppUsageStatsHelper.TAG, "onServiceConnected, is disconnected.");
                AppUsageStatsHelper.this.mNbUsMgr = null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context getAppContext() {
        return CommonApplication.getInstance().getAppContext();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getBeginTime() {
        return Math.max(System.currentTimeMillis() - THREE_YEARS, 0L);
    }

    public static AppUsageStatsHelper getInstance() {
        return AppUsageStatsHelperHolder.INSTANCE;
    }

    private List<UsageStats> queryAppUsageStats() {
        Log.d(TAG, "queryAppUsageStats()");
        try {
            INbUsageStatsManager iNbUsageStatsManager = this.mNbUsMgr;
            if (iNbUsageStatsManager != null) {
                return iNbUsageStatsManager.queryUsageStats(3, getBeginTime(), System.currentTimeMillis());
            }
            Log.d(TAG, "[queryAppUsageStats] mNbUsMgr is null");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<UsageStats> queryAppUsageStats(Context context, int i, long j, long j2) {
        return Build.VERSION.SDK_INT < 34 ? queryAppUsageStats() : queryUsageStats(context, i, j, j2);
    }

    public static UsageEvents queryEvents(Context context, long j, long j2) {
        return ((UsageStatsManager) context.getApplicationContext().getSystemService("usagestats")).queryEvents(j, j2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.lang.StringBuilder] */
    public static List<Map<String, Long>> queryNbStatsDistribution(Context context, List<String> list, long j, long j2, long j3) {
        List list2;
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getApplicationContext().getSystemService("usagestats");
        List arrayList = new ArrayList();
        try {
            Method declaredMethod = UsageStatsManager.class.getDeclaredMethod(METHOD_QUERY_NB_STATS_DISTRIBUTION, List.class, Long.TYPE, Long.TYPE, Long.TYPE);
            declaredMethod.setAccessible(true);
            list2 = (List) declaredMethod.invoke(usageStatsManager, list, Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3));
        } catch (Exception e) {
            e = e;
        }
        try {
            Log.d(TAG, "queryNbStatsDistribution: " + list2);
            return list2;
        } catch (Exception e2) {
            e = e2;
            arrayList = list2;
            Log.wtf(TAG, e);
            return arrayList;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.StringBuilder] */
    public static Map<String, Long> queryNbStatsTime(Context context, List<String> list, long j, long j2) {
        Map map;
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getApplicationContext().getSystemService("usagestats");
        Map hashMap = new HashMap();
        try {
            Method declaredMethod = UsageStatsManager.class.getDeclaredMethod(METHOD_QUERY_NB_STATS_TIME, List.class, Long.TYPE, Long.TYPE);
            declaredMethod.setAccessible(true);
            map = (Map) declaredMethod.invoke(usageStatsManager, list, Long.valueOf(j), Long.valueOf(j2));
        } catch (Exception e) {
            e = e;
        }
        try {
            Log.d(TAG, "queryNbStatsTime: " + map);
            return map;
        } catch (Exception e2) {
            e = e2;
            hashMap = map;
            Log.wtf(TAG, e);
            return hashMap;
        }
    }

    public static long queryNbStatsTotalTime(Context context, String str) {
        long j = -1;
        try {
            ((UsageStatsManager) context.getApplicationContext().getSystemService("usagestats")).getClass().getDeclaredMethod(METHOD_QUERY_NB_STATS_TOTAL_TIME, List.class);
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            for (Map.Entry<String, Long> entry : queryNbStatsTotalTime(context, arrayList).entrySet()) {
                if (!TextUtils.isEmpty(str) && str.equals(entry.getKey())) {
                    j += entry.getValue().longValue();
                }
            }
        } catch (NoSuchMethodException unused) {
        }
        return j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.StringBuilder] */
    public static Map<String, Long> queryNbStatsTotalTime(Context context, List<String> list) {
        Map map;
        UsageStatsManager usageStatsManager = (UsageStatsManager) context.getApplicationContext().getSystemService("usagestats");
        Map hashMap = new HashMap();
        try {
            Method declaredMethod = UsageStatsManager.class.getDeclaredMethod(METHOD_QUERY_NB_STATS_TOTAL_TIME, List.class);
            declaredMethod.setAccessible(true);
            map = (Map) declaredMethod.invoke(usageStatsManager, list);
        } catch (Exception e) {
            e = e;
        }
        try {
            Log.d(TAG, "queryNbStatsTotalTime: " + map);
            return map;
        } catch (Exception e2) {
            e = e2;
            hashMap = map;
            Log.wtf(TAG, e);
            return hashMap;
        }
    }

    public static List<UsageStats> queryUsageStats(Context context, int i, long j, long j2) {
        return ((UsageStatsManager) context.getApplicationContext().getSystemService("usagestats")).queryUsageStats(i, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startLoadAppTime() {
        Log.d(TAG, "startLoadAppTime");
        try {
            long currentTimeMillis = System.currentTimeMillis() - this.mLastUpdateTime;
            if (currentTimeMillis <= 2000) {
                Log.e(TAG, "updateAppUsageStat() reject, interval : " + currentTimeMillis);
                return;
            }
            try {
                new LoadAppsTimeTask().execute(new Void[0]);
            } catch (Exception e) {
                e.printStackTrace();
                Log.d(TAG, "updateAppUsageStat(), exception: " + e);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void bindUsageStatsService() {
        Log.d(TAG, "bind....");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(PERMISSION_CAPSULE_PKG_NAME, PERMISSION_CAPSULE_CLASS_NAME));
        getAppContext().bindService(intent, this.mNbUsageStatsConn, 1);
    }

    public long getAppLastTimeUsed(String str) {
        UsageStats usageStats;
        if (this.mUsageStatsMaps.containsKey(str) && (usageStats = this.mUsageStatsMaps.get(str)) != null) {
            return usageStats.getLastTimeUsed();
        }
        return -1L;
    }

    public String getAppTotalSize(String str, String str2, UserHandle userHandle) {
        try {
            INbUsageStatsManager iNbUsageStatsManager = this.mNbUsMgr;
            if (iNbUsageStatsManager == null) {
                Log.d(TAG, "[queryAppUsageStats] mNbUsMgr is null");
                return "0GB";
            }
            StorageStats queryStatsForPackage = iNbUsageStatsManager.queryStatsForPackage(str, str2, userHandle);
            String formatFileSize = Formatter.formatFileSize(getAppContext(), queryStatsForPackage.getAppBytes() + queryStatsForPackage.getDataBytes());
            Log.d(TAG, "getAppSize() app : " + queryStatsForPackage.getAppBytes() + ", cache : " + queryStatsForPackage.getCacheBytes() + ", data : " + queryStatsForPackage.getDataBytes() + ", sizeStr : " + formatFileSize);
            return formatFileSize;
        } catch (Exception e) {
            Log.w(TAG, "[queryAppUsageStats] getAppTotalSize Exception : " + e.getMessage());
            return "0GB";
        }
    }

    public long getAppTotalTimeInForeground(String str) {
        UsageStats usageStats = this.mUsageStatsMaps.get(str);
        if (usageStats == null) {
            Log.d(TAG, "getAppTotalTimeInForeground(" + str + "), stats is null");
            return -1L;
        }
        long totalTimeInForeground = usageStats.getTotalTimeInForeground();
        long j = totalTimeInForeground / 60000;
        Log.d(TAG, "getAppTotalTimeInForeground(" + str + "), total : " + totalTimeInForeground + ", totalMinute : " + j);
        return j;
    }

    public long getAppTotalTimeInForegroundByMillisecond(String str) {
        UsageStats usageStats;
        long queryNbStatsTotalTime = queryNbStatsTotalTime(getAppContext(), str);
        if (queryNbStatsTotalTime >= 0) {
            return queryNbStatsTotalTime;
        }
        if (!this.mUsageStatsMaps.containsKey(str) || (usageStats = this.mUsageStatsMaps.get(str)) == null) {
            return -1L;
        }
        long totalTimeInForeground = usageStats.getTotalTimeInForeground();
        Log.d(TAG, "getAppTotalTimeInForegroundByMillisecond(" + str + "), total : " + totalTimeInForeground);
        return totalTimeInForeground;
    }

    public long getCutoffTotalTime() {
        return this.mCutoffTotalTime;
    }

    public void preUpdateData(Runnable runnable) {
        this.mCallback = runnable;
        updateAppUsageStat();
    }

    public String queryUsageStats(Context context, String str, String str2, UserHandle userHandle) {
        return Build.VERSION.SDK_INT < 34 ? getAppTotalSize(str, str2, userHandle) : queryUsageStatsZte(context, str, str2, userHandle);
    }

    public String queryUsageStatsZte(Context context, String str, String str2, UserHandle userHandle) {
        StorageStatsManager storageStatsManager = (StorageStatsManager) context.getApplicationContext().getSystemService("storagestats");
        String str3 = null;
        try {
            Method declaredMethod = StorageStatsManager.class.getDeclaredMethod("queryStatsForPackage", String.class, String.class, UserHandle.class);
            declaredMethod.setAccessible(true);
            StorageStats storageStats = (StorageStats) declaredMethod.invoke(storageStatsManager, str, str2, userHandle);
            str3 = Formatter.formatFileSize(context, storageStats.getAppBytes() + storageStats.getDataBytes());
            Log.d(TAG, "getAppSize() app : " + storageStats.getAppBytes() + ", cache : " + storageStats.getCacheBytes() + ", data : " + storageStats.getDataBytes() + ", sizeStr : " + str3);
            return str3;
        } catch (Exception e) {
            Log.wtf(TAG, e);
            return str3;
        }
    }

    public void registerAppUsageStatsChangedListener(AppUsageStatsChangedListener appUsageStatsChangedListener) {
        if (this.mListener.contains(appUsageStatsChangedListener)) {
            return;
        }
        this.mListener.add(appUsageStatsChangedListener);
    }

    public void resetLastUpdateTime() {
        this.mLastUpdateTime = 0L;
    }

    public void setCutoffTotalTime(long j) {
        this.mCutoffTotalTime = Math.max(1L, j);
        Log.d(TAG, "setCutoffTotalTime() value : " + j);
    }

    public void unbindUsageStatsService() {
        if (this.mNbUsMgr == null || !this.isBind) {
            return;
        }
        Log.d(TAG, "unbind....");
        this.isBind = false;
        getAppContext().unbindService(this.mNbUsageStatsConn);
    }

    public void unregisterAppUsageStatsChangedListener(AppUsageStatsChangedListener appUsageStatsChangedListener) {
        if (this.mListener.contains(appUsageStatsChangedListener)) {
            this.mListener.remove(appUsageStatsChangedListener);
        }
    }

    public void updateAppUsageStat() {
        if (!this.isBind) {
            bindUsageStatsService();
        }
        startLoadAppTime();
    }
}
