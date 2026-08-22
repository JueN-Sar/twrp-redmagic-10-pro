package cn.nubia.gamelauncher.upgrade;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.os.Environment;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import cn.nubia.gamelauncher.service.GameFeatureService;
import java.io.File;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public final class UpgradeUtil {
    private static final String GAME_MODE_CLASS_NAME = "cn.nubia.game.GameModeHelper";
    private static final int MAX_DAY_OF_YEAR_DIGIT = 1000;
    private static final String TAG = "Upgrade";

    public static boolean checkSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void deleteDir(String str) {
        Log.d("Upgrade", "deleteFile() srcDir = " + str);
        File file = new File(str);
        if (file.exists()) {
            deleteFile(file);
        } else {
            Log.d("Upgrade", "deleteFile() file not exists!");
        }
    }

    public static void deleteFile(File file) {
        Log.d("Upgrade", "deleteFile() delFile = " + file);
        if (!file.isDirectory()) {
            file.delete();
            return;
        }
        for (File file2 : file.listFiles()) {
            deleteFile(file2);
        }
    }

    public static String getCurrentTopPkgP(Context context) {
        try {
            Object invoke = ActivityManager.class.getMethod("getService", new Class[0]).invoke((ActivityManager) context.getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY), new Object[0]);
            Object invoke2 = invoke.getClass().getMethod("getFocusedStackInfo", new Class[0]).invoke(invoke, new Object[0]);
            ComponentName componentName = (ComponentName) invoke2.getClass().getField("topActivity").get(invoke2);
            if (componentName != null) {
                return componentName.getPackageName();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getCurrentTopPkgQ() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityTaskManager");
            Object invoke = cls.getDeclaredMethod("getService", new Class[0]).invoke(cls, new Object[0]);
            if (invoke != null) {
                return (String) Class.forName("android.app.IActivityTaskManager").getDeclaredMethod("getFocusedStackResumedPkg", new Class[0]).invoke(invoke, new Object[0]);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getCurrentVersionCode(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        return packageInfo.versionCode;
    }

    public static int getDay() {
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(1) * 1000) + calendar.get(6);
    }

    public static int getGameModeDBValue(Context context) {
        try {
            Class<?> cls = Class.forName(GAME_MODE_CLASS_NAME);
            Method declaredMethod = cls.getDeclaredMethod("getGameModeDBValue", Context.class);
            declaredMethod.setAccessible(true);
            return ((Integer) declaredMethod.invoke(cls.newInstance(), context)).intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getStringCurrentVersionCode(Context context) {
        return String.valueOf(getCurrentVersionCode(context));
    }

    public static boolean hasEnoughStorageSpace(Context context, long j) {
        if (context == null) {
            return true;
        }
        Log.d("Upgrade", "hasEnoughStorageSpace() fileSize = " + j);
        File externalFilesDir = context.getExternalFilesDir(null);
        long usableSpace = externalFilesDir.getUsableSpace();
        Log.d("Upgrade", "hasEnoughStorageSpace() usableSpace = " + usableSpace + ", path = " + externalFilesDir.getPath());
        Log.d("Upgrade", "hasEnoughStorageSpace() hasEnoughStorageSpace() = " + (j < usableSpace));
        return j < usableSpace;
    }

    public static boolean isFileExists(String str) {
        if (str == null) {
            return false;
        }
        return new File(str).exists();
    }

    public static boolean isGameKeyOpen(Context context) {
        boolean z = (getGameModeDBValue(context) & 1) != 0;
        Log.d("Upgrade", "isGameKeyOpen() : " + z);
        return z;
    }

    public static String parcelableToString(Parcelable parcelable) {
        if (parcelable == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        parcelable.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        return Base64.encodeToString(marshall, 0);
    }

    public static Parcel unmarshall(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        return obtain;
    }

    public static <T> T unmarshall(String str, Parcelable.Creator<T> creator) {
        Parcel parcel = null;
        if (str == null) {
            return null;
        }
        try {
            parcel = unmarshall(Base64.decode(str, 0));
            return creator.createFromParcel(parcel);
        } finally {
            if (parcel != null) {
                parcel.recycle();
            }
        }
    }
}
