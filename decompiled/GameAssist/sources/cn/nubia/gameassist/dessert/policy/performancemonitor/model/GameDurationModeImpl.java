package cn.nubia.gameassist.dessert.policy.performancemonitor.model;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import cn.nubia.gameassist.dessert.policy.performancemonitor.present.PresenterCallback;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.utils.GaLog;
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
public class GameDurationModeImpl implements IGameDurationMode {

    /* renamed from: e, reason: collision with root package name */
    private static GameDurationModeImpl f6455e;

    /* renamed from: a, reason: collision with root package name */
    private PresenterCallback f6456a;

    /* renamed from: b, reason: collision with root package name */
    private GameTimeParmTask f6457b;

    /* renamed from: c, reason: collision with root package name */
    private Context f6458c;

    /* renamed from: d, reason: collision with root package name */
    private Handler f6459d;

    public static class AppUsageStats {
        public long mBeginTimeStamp;
        public long mEndTimeStamp;
        public String mPackageName;
        public long mTotalTimeInForeground = 0;
    }

    private class GameTimeParmTask extends AsyncTask<Void, Void, GameDurationInfo[]> {

        /* renamed from: a, reason: collision with root package name */
        private List f6460a;

        /* renamed from: b, reason: collision with root package name */
        private Map f6461b;

        private void a(GameDurationInfo[] gameDurationInfoArr) {
            this.f6460a.add(d(this.f6461b, gameDurationInfoArr[0]));
        }

        private List c() {
            ArrayList arrayList = new ArrayList();
            try {
                Cursor query = GameDurationModeImpl.this.f6458c.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
                try {
                    int columnIndex = query.getColumnIndex("component");
                    query.moveToPosition(-1);
                    while (query.moveToNext()) {
                        String string = query.getString(columnIndex);
                        if (!TextUtils.isEmpty(string)) {
                            arrayList.add(string.substring(0, string.indexOf(44)));
                        }
                    }
                    query.close();
                } finally {
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return arrayList;
        }

        private List d(Map map, GameDurationInfo gameDurationInfo) {
            ArrayList arrayList = new ArrayList();
            if (map == null) {
                GaLog.b("PerformanceMonitor-GameDurationModeImpl", "getGameAppList, gameTimeData is null !");
                return arrayList;
            }
            PackageManager packageManager = GameDurationModeImpl.this.f6458c.getPackageManager();
            int i2 = 0;
            for (Map.Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
                    if (applicationInfo != null) {
                        GameAppInfo gameAppInfo = new GameAppInfo();
                        gameAppInfo.icon = packageManager.getApplicationIcon(applicationInfo);
                        CharSequence loadLabel = applicationInfo.loadLabel(packageManager);
                        gameAppInfo.label = loadLabel != null ? loadLabel.toString() : str;
                        gameAppInfo.totalTimeInForeground = ((Long) entry.getValue()).longValue();
                        arrayList.add(gameAppInfo);
                        if (i2 == 0) {
                            GaLog.a("PerformanceMonitor-GameDurationModeImpl", "getGameAppList, max time : " + gameAppInfo.totalTimeInForeground + ", pkgName : " + str);
                            gameDurationInfo.mMaxTimeAppIcon = gameAppInfo.icon;
                            gameDurationInfo.mMaxTimePkgName = str;
                            gameDurationInfo.label = gameAppInfo.label;
                            gameDurationInfo.mGameMaxTime = gameAppInfo.totalTimeInForeground;
                        }
                        i2++;
                        gameDurationInfo.mGameTotalTime += gameAppInfo.totalTimeInForeground;
                    }
                } catch (PackageManager.NameNotFoundException e2) {
                    GaLog.b("PerformanceMonitor-GameDurationModeImpl", "getGameAppList, Exception: " + e2);
                    e2.printStackTrace();
                }
            }
            GaLog.a("PerformanceMonitor-GameDurationModeImpl", "getGameAppList, mGameTotalTime : " + gameDurationInfo.mGameTotalTime);
            return arrayList;
        }

        private long e(boolean z, int i2) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
            if (!z) {
                calendar.set(7, 2);
            }
            long timeInMillis = (calendar.getTimeInMillis() / 100000) * 100000;
            GaLog.a("PerformanceMonitor-GameDurationModeImpl", "getSelectionArgs, isToday = " + z + ", result = " + timeInMillis + ", preDayCount = " + i2);
            return timeInMillis;
        }

        private void g(UsageEvents usageEvents, List list, Map map) {
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            while (usageEvents.hasNextEvent()) {
                UsageEvents.Event event = new UsageEvents.Event();
                usageEvents.getNextEvent(event);
                String packageName = event.getPackageName();
                GaLog.a("PerformanceMonitor-GameDurationModeImpl", "parseUsageEvents, gameApps = " + list + " , event = " + event + " , pkgname = " + packageName + " , currentPkg = " + Utils.j());
                if (list.size() > 0 && packageName.contains(Utils.j()) && list.contains(packageName)) {
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
                            GaLog.b("PerformanceMonitor-GameDurationModeImpl", "parseUsageEvents, is invalid data = " + packageName);
                        }
                    }
                }
            }
            for (Map.Entry entry : concurrentHashMap.entrySet()) {
                String str = (String) entry.getKey();
                long j2 = ((AppUsageStats) entry.getValue()).mTotalTimeInForeground;
                GaLog.b("PerformanceMonitor-GameDurationModeImpl", "parseUsageEvents, name= " + str + ", apptime = " + j2);
                if (j2 <= 0) {
                    j2 = 0;
                }
                h(map, str, j2);
            }
            concurrentHashMap.clear();
        }

        private void h(Map map, String str, long j2) {
            if (map.containsKey(str)) {
                map.put(str, Long.valueOf(j2 + ((Long) map.get(str)).longValue()));
            } else {
                map.put(str, Long.valueOf(j2));
            }
        }

        private void i() {
            g(((UsageStatsManager) GameDurationModeImpl.this.f6458c.getApplicationContext().getSystemService("usagestats")).queryEvents(e(true, 0), System.currentTimeMillis()), c(), this.f6461b);
        }

        private void j(GameDurationInfo gameDurationInfo, GameDurationInfo gameDurationInfo2) {
            Calendar calendar = Calendar.getInstance();
            int i2 = calendar.get(2) + 1;
            int i3 = calendar.get(5);
            calendar.set(7, 2);
            int i4 = calendar.get(2) + 1;
            int i5 = calendar.get(5);
            gameDurationInfo.mGameTimeSpan = i2 + "." + i3;
            gameDurationInfo2.mGameTimeSpan = i4 + "." + i5 + "-" + i2 + "." + i3;
            StringBuilder sb = new StringBuilder();
            sb.append("setTimeSpan, today: ");
            sb.append(gameDurationInfo.mGameTimeSpan);
            sb.append(", dayOfSeven: ");
            sb.append(gameDurationInfo2.mGameTimeSpan);
            GaLog.a("PerformanceMonitor-GameDurationModeImpl", sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public GameDurationInfo[] doInBackground(Void... voidArr) {
            i();
            this.f6461b = GameDurationModeImpl.this.sortMapByValue(this.f6461b);
            GameDurationInfo[] gameDurationInfoArr = {new GameDurationInfo(), new GameDurationInfo()};
            j(gameDurationInfoArr[0], gameDurationInfoArr[1]);
            a(gameDurationInfoArr);
            return gameDurationInfoArr;
        }

        public void clearMap() {
            GaLog.b("PerformanceMonitor-GameDurationModeImpl", "clearMap: ");
            Map map = this.f6461b;
            if (map != null) {
                map.clear();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: f, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(GameDurationInfo[] gameDurationInfoArr) {
            ArrayList arrayList = new ArrayList();
            for (List list : this.f6460a) {
                ArrayList arrayList2 = new ArrayList();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    arrayList2.add((GameAppInfo) it.next());
                }
                arrayList.add(arrayList2);
            }
            List list2 = this.f6460a;
            if (list2 != null) {
                list2.clear();
            }
            if (gameDurationInfoArr == null || gameDurationInfoArr.length < 1) {
                GaLog.b("PerformanceMonitor-GameDurationModeImpl", "onPostExecute:loadedData error!");
            } else {
                GameDurationModeImpl.this.f6456a.onLoaded(gameDurationInfoArr, arrayList);
            }
        }

        private GameTimeParmTask() {
            this.f6460a = new ArrayList();
            this.f6461b = new HashMap();
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

    private GameDurationModeImpl(Context context, Handler handler) {
        this.f6458c = context;
        this.f6459d = handler;
    }

    public static GameDurationModeImpl getInstance(Context context, Handler handler) {
        if (f6455e == null) {
            synchronized (GameDurationModeImpl.class) {
                f6455e = new GameDurationModeImpl(context, handler);
            }
        }
        return f6455e;
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

    @Override // cn.nubia.gameassist.dessert.policy.performancemonitor.model.IGameDurationMode
    public void startLoadGameTimeParms(PresenterCallback presenterCallback) {
        GaLog.a("PerformanceMonitor-GameDurationModeImpl", "startLoadGameTimeParms");
        this.f6456a = presenterCallback;
        try {
            GameTimeParmTask gameTimeParmTask = new GameTimeParmTask();
            this.f6457b = gameTimeParmTask;
            gameTimeParmTask.execute(new Void[0]);
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            presenterCallback.onFaied();
        }
    }
}
