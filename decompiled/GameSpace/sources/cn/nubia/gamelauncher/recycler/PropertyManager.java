package cn.nubia.gamelauncher.recycler;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Trace;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import cn.nubia.common.helper.AppUsageStatsHelper;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.aimhelper.PackageHelper;
import cn.nubia.gamelauncher.bean.AppListItemBean;
import cn.nubia.gamelauncher.util.Util;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Iterator;

/* loaded from: classes.dex */
public class PropertyManager {
    private static final int DAY = 86400000;
    private static final int GB = 1073741824;
    private static final int MB = 1048576;
    private final String TAG = "PropertyManager";
    Context mContext;
    TextView mDays;
    AppListItemBean mItem;
    TextView mSize;
    TextView mTime;

    public PropertyManager(Context context) {
        this.mContext = context;
    }

    private long getFirstInstallTime(Context context, String str) {
        if (str == null || context == null) {
            return 0L;
        }
        if (Util.isZte() || Util.isRedMagicRunOnMyOs()) {
            return getFirstInstallTime(str);
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0).firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("PropertyManager", "getFirstInstallTime() e : " + e);
            e.printStackTrace();
            return -1L;
        }
    }

    private long getIntervalDays(long j) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        LocalDate of = LocalDate.of(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
        LocalDate of2 = LocalDate.of(calendar2.get(1), calendar2.get(2) + 1, calendar2.get(5));
        long between = ChronoUnit.DAYS.between(of, of2);
        Log.d("PropertyManager", "getDays() install date : " + of + ", current date : " + of2 + ", interval : " + between);
        return Math.max(0L, between + 1);
    }

    private int getUid(Context context, String str) {
        if (context == null) {
            return -1;
        }
        try {
            return context.getPackageManager().getApplicationInfo(str, 128).uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private void update() {
        AppListItemBean appListItemBean = this.mItem;
        if (appListItemBean == null) {
            return;
        }
        String packageName = appListItemBean.getPackageName();
        Log.d("PropertyManager", "update() pkg : " + packageName);
        updateDays(this.mContext, packageName);
        updateAppSize(this.mContext, packageName);
        updatePlayTime(this.mItem);
    }

    private void updateAppSize(Context context, String str) {
        if (str == null || context == null) {
            return;
        }
        Trace.beginSection("updateAppSize()");
        Iterator<StorageVolume> it = ((StorageManager) context.getSystemService("storage")).getStorageVolumes().iterator();
        while (it.hasNext()) {
            String queryUsageStats = AppUsageStatsHelper.getInstance().queryUsageStats(context, it.next().getUuid(), str, UserHandle.getUserHandleForUid(getUid(context, str)));
            Log.d("PropertyManager", " updateAppSize() pkg = " + str + ", sizeStr : " + queryUsageStats);
            this.mSize.setText(queryUsageStats);
        }
        Trace.endSection();
    }

    private void updateDays(Context context, String str) {
        if (str == null || context == null) {
            return;
        }
        Trace.beginSection("updateDays()");
        long firstInstallTime = getFirstInstallTime(context, str);
        Log.d("PropertyManager", "updateDays() install : " + firstInstallTime + ", current : " + System.currentTimeMillis());
        this.mDays.setText(getIntervalDays(firstInstallTime) + "d");
        Trace.endSection();
    }

    private void updateView(View view) {
        if (view == null) {
            return;
        }
        this.mTime = (TextView) view.findViewById(R.id.property_time_value);
        this.mSize = (TextView) view.findViewById(R.id.property_size_value);
        this.mDays = (TextView) view.findViewById(R.id.property_days_value);
    }

    public long getFirstInstallTime(String str) {
        long firstInstallTime = PackageHelper.getInstance().getFirstInstallTime(str);
        Log.d("PropertyManager", "getFirstInstallTime(" + str + ") firstInstallTime : " + firstInstallTime);
        return firstInstallTime;
    }

    public void updatePlayTime(AppListItemBean appListItemBean) {
        if (appListItemBean == null || this.mTime == null) {
            return;
        }
        Trace.beginSection("updatePlayTime()");
        Log.d("PropertyManager", " getTotalString() gameName = " + appListItemBean.getName() + ", second : " + appListItemBean.getTotalTimeMillisecond() + ", h : " + appListItemBean.getTotalTimeHour());
        if (appListItemBean.getTotalTimeHour() == 0) {
            this.mTime.setText("<1h");
        } else {
            this.mTime.setText(appListItemBean.getTotalTimeHour() + "h");
        }
        Trace.endSection();
    }

    public void updateProperty(AppListItemBean appListItemBean, View view) {
        Log.d("PropertyManager", "updateProperty() bean : " + appListItemBean);
        if (view == null || appListItemBean == null) {
            return;
        }
        updateView(view);
        this.mItem = appListItemBean;
        update();
    }
}
