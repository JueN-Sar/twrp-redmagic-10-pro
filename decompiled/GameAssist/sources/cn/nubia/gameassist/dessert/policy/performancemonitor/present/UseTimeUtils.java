package cn.nubia.gameassist.dessert.policy.performancemonitor.present;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.zte.gameassist.utils.GaLog;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class UseTimeUtils {
    public static List<String> getAppListOfGameLauncher(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            Cursor query = context.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/appadd?notify=false"), null, null, null, null);
            try {
                GaLog.e("PerformanceMonitor-UseTimeUtils", "getAppListOfGameLauncher getCount:" + query.getCount());
                int columnIndex = query.getColumnIndex("component");
                query.moveToPosition(-1);
                while (query.moveToNext()) {
                    String string = query.getString(columnIndex);
                    GaLog.e("PerformanceMonitor-UseTimeUtils", "" + string);
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

    public static String msToH(long j2) {
        double msToMinite = msToMinite(j2) / 60.0d;
        return new DecimalFormat(msToMinite >= 0.1d ? "0.0" : "0.00").format(msToMinite);
    }

    public static long[] msToHm(long j2) {
        int msToMinite = msToMinite(j2);
        return new long[]{msToMinite / 60, msToMinite % 60};
    }

    public static int msToMinite(long j2) {
        return (int) (j2 / 60000);
    }
}
