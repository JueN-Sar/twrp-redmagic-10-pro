package cn.nubia.gamelauncher.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.BitmapUtils;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamelauncher.GameLauncherApplication;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.aimhelper.ActivityUtils;
import cn.nubia.gamelauncher.service.GameFeatureService;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.systemwrapper.GameKeysWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* loaded from: classes.dex */
public class Util {
    private static final int CROP_WIDTH = 30;
    private static final String DB_GAME_SCENE = "nubia_game_scene";
    public static final String D_TAG = "Atmosphere";
    private static final String GAME_LIST_FILE = "game_list.xml";
    private static final int ICON_WIDTH = 216;
    public static final String PURE_MODE = "db_lite_mode";
    public static final String RED_MAGIC_PAD_DEVICES = "npad";
    private static final float REMAINING_RATE_AFTER_CROP = 0.7f;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_1172 = 0;
    public static int SCREEN_WIDTH_OR_HEIGHT_IS_2748 = 0;
    public static final String TAG = "Util";
    public static final String TENCENT_APP_STORE = "tencent";
    public static final int TWIN_PROFILEID;
    public static int mFlagHasNeoStoreAndNoZteMarket;
    private static boolean mIsAppAddResumed;
    private static boolean mIsHostModeGameSpaceResumed;

    static {
        TWIN_PROFILEID = isMyOs() ? 999 : HighLightsUtils.NUBIA_TWIN_USERID;
        mFlagHasNeoStoreAndNoZteMarket = 9;
        mIsHostModeGameSpaceResumed = false;
        mIsAppAddResumed = false;
        SCREEN_WIDTH_OR_HEIGHT_IS_2748 = 2748;
        SCREEN_WIDTH_OR_HEIGHT_IS_1172 = 1172;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int adjustSatAndVal(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return Color.HSVToColor(new float[]{fArr[0], Math.min(0.5f, fArr[1]), Math.min(0.5f, fArr[2])});
    }

    public static <T> List<T> castList(Object obj, Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        if (!(obj instanceof List)) {
            return null;
        }
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(cls.cast(it.next()));
        }
        return arrayList;
    }

    public static boolean checkChannel(String str) {
        return "nubiaInterNormal".contains(str);
    }

    public static boolean doMyOsVibrateOfHe(int i) {
        boolean doVibrate = doVibrate(getAppContext(), i, 1, 50, 255, 0);
        Log.d(TAG, "doMyOsVibrate() result : " + doVibrate);
        return doVibrate;
    }

    public static boolean doVibrate(Context context, int i, int i2, int i3, int i4, int i5) {
        try {
            Class<?> cls = Class.forName("com.zte.richtap.ZTERichtapUtils");
            return ((Boolean) cls.getMethod("richtapVibrate", Context.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE).invoke(cls, context, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5))).booleanValue();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LogUtil.i(TAG, "doVibrate() but ClassNotFoundException " + e.getMessage());
            return false;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            LogUtil.i(TAG, "doVibrate() but IllegalAccessException " + e2.getMessage());
            return false;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            LogUtil.i(TAG, "doVibrate() but NoSuchMethodException " + e3.getMessage());
            return false;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            LogUtil.i(TAG, "doVibrate() but InvocationTargetException " + e4.getMessage());
            return false;
        }
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (!(drawable instanceof AdaptiveIconDrawable)) {
            return null;
        }
        AdaptiveIconDrawable adaptiveIconDrawable = (AdaptiveIconDrawable) drawable;
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{adaptiveIconDrawable.getBackground(), adaptiveIconDrawable.getForeground()});
        Bitmap createBitmap = Bitmap.createBitmap(layerDrawable.getIntrinsicWidth(), layerDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        layerDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        layerDrawable.draw(canvas);
        int width = (int) (createBitmap.getWidth() * 0.7f);
        return BitmapUtils.centerCrop(createBitmap, width, width, 0, 0, false);
    }

    public static Context getAppContext() {
        return GameLauncherApplication.getAppContext();
    }

    public static Bitmap getAppIcon(String str) {
        try {
            return drawableToBitmap(getAppContext().getPackageManager().getApplicationIcon(str));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAppName(String str) {
        try {
            PackageManager packageManager = getAppContext().getPackageManager();
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getChannel() {
        return cn.nubia.common.util.CommonUtil.getChannel();
    }

    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(1) + "_" + (calendar.get(2) + 1) + "_" + calendar.get(5) + "_" + calendar.get(11) + "_" + calendar.get(12) + "_" + calendar.get(13);
    }

    public static Drawable getDialogIcon(String str) {
        PackageManager packageManager = getAppContext().getPackageManager();
        Drawable drawable = null;
        try {
            drawable = packageManager.getApplicationInfo(str, 0).loadIcon(packageManager);
            LogUtil.i(TAG, "getIcon: icon=" + drawable);
            return drawable;
        } catch (Exception e) {
            LogUtil.i(TAG, "updatePreference: pkg=" + e.getMessage());
            return drawable;
        }
    }

    public static int getDisplayLandscapeHeight(Context context) {
        Rect displayRect = getDisplayRect(context);
        return displayRect.width() < displayRect.height() ? displayRect.width() : displayRect.height();
    }

    public static int getDisplayLandscapeWidth(Context context) {
        Rect displayRect = getDisplayRect(context);
        return displayRect.width() > displayRect.height() ? displayRect.width() : displayRect.height();
    }

    public static Rect getDisplayRect(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(((Activity) context).getWindowManager().getDefaultDisplay(), displayMetrics);
        } catch (Exception e) {
            LogUtil.i(TAG, "initDisplayRect() err : " + e);
            e.printStackTrace();
        }
        Rect rect = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        LogUtil.d(TAG, "initDisplayRect() mRect : " + rect);
        return rect;
    }

    public static Drawable getDrawableIcon(String str) {
        Bitmap originalIcon = getOriginalIcon(str);
        if (originalIcon == null) {
            return null;
        }
        return BitmapUtils.convertBitmapToDrawable(originalIcon);
    }

    public static Drawable getIcon(String str) {
        return getDrawableIcon(str);
    }

    public static Bitmap getOriginalIcon(String str) {
        Bitmap zoomImage;
        Bitmap appIcon = getAppIcon(str);
        if (appIcon == null || (zoomImage = BitmapUtils.getZoomImage(appIcon, 216.0d, 216.0d, true)) == null) {
            return null;
        }
        return BitmapUtils.centerCrop(zoomImage, zoomImage.getWidth() - 30, zoomImage.getHeight() - 30, 34, 0, false);
    }

    public static PackageInfo getPackageInfoAsUser(Object obj, String str, int i, int i2) {
        try {
            Object invoke = Class.forName("android.content.pm.PackageManager").getMethod("getPackageInfoAsUser", String.class, Integer.TYPE, Integer.TYPE).invoke(obj, str, Integer.valueOf(i), Integer.valueOf(i2));
            if (invoke == null) {
                return null;
            }
            return (PackageInfo) invoke;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getRandomColorForCard(Context context) {
        int[] intArray = context.getResources().getIntArray(R.array.cardBgColors);
        int length = intArray.length;
        int nextInt = new Random().nextInt(length);
        if (nextInt < 0 || nextInt >= length) {
            nextInt = 0;
        }
        return intArray[nextInt];
    }

    public static float getScaleXByMatrix(Matrix matrix) {
        if (matrix == null) {
            return 0.0f;
        }
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        return fArr[0];
    }

    public static String getTopPackageNames() {
        try {
            Class<?> cls = Class.forName("android.app.NubiaSysState");
            List castList = castList(cls.getDeclaredMethod("getTopPackageNames", new Class[0]).invoke(cls.getConstructor(new Class[0]).newInstance(new Object[0]), new Object[0]), String.class);
            if (castList != null && castList.size() != 0) {
                return castList.toString();
            }
            Log.d(TAG, "mGetTopPkg is null");
            return null;
        } catch (Exception e) {
            Log.w(TAG, "", e);
            return null;
        }
    }

    public static UserHandle getTwinAppUserHandle(String str) {
        PackageInfo packageInfoAsUser = getPackageInfoAsUser(GameLauncherApplication.getAppContext().getPackageManager(), str, 0, TWIN_PROFILEID);
        if (packageInfoAsUser != null) {
            return UserHandle.getUserHandleForUid(packageInfoAsUser.applicationInfo.uid);
        }
        return null;
    }

    public static Drawable getTwinIcon(String str) {
        return getTwinIcon(str, getIcon(str));
    }

    public static Drawable getTwinIcon(String str, Drawable drawable) {
        return drawable != null ? getAppContext().getPackageManager().getUserBadgedIcon(drawable, getTwinAppUserHandle(str)) : drawable;
    }

    public static String getVersionName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.versionName;
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "getVersionName() e : " + e);
            return null;
        }
    }

    public static boolean hasPermission(String str, String str2) {
        return getAppContext().getPackageManager().checkPermission(str, str2) == 0;
    }

    public static boolean hasPermissionAsUser(String str, String str2, boolean z) {
        if (!z) {
            return hasPermission(str, str2);
        }
        boolean z2 = false;
        try {
            IBinder iBinder = (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "package");
            Class<?> cls = Class.forName("android.content.pm.IPackageManager$Stub");
            Object invoke = cls.getDeclaredMethod("asInterface", IBinder.class).invoke(cls, iBinder);
            if (((Integer) invoke.getClass().getDeclaredMethod("checkPermission", String.class, String.class, Integer.TYPE).invoke(invoke, str, str2, Integer.valueOf(TWIN_PROFILEID))).intValue() == 0) {
                z2 = true;
            }
        } catch (Exception e) {
            Log.d(TAG, "hasPermissionAsUser() e : " + e);
            e.printStackTrace();
        }
        Log.d(TAG, "hasPermissionAsUser() ---------->hasPermissionAsUser : " + z2);
        return z2;
    }

    public static boolean isAppInstall(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            packageInfo = null;
        }
        Log.v(TAG, str + " isAppInstall; " + (packageInfo != null ? packageInfo.versionName : null));
        return packageInfo != null || GameKeysWrapper.getDefault().isPackageInstalled(context, str, 0);
    }

    public static boolean isBackground() {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) getAppContext().getSystemService(GameFeatureService.ACTION_CONTROL_PANEL_EXTRA_ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(getAppContext().getPackageName())) {
                Log.d(TAG, "isBackground(" + runningAppProcessInfo.processName + ") info : " + runningAppProcessInfo.importance);
                return runningAppProcessInfo.importance > 125;
            }
        }
        return true;
    }

    public static boolean isContextValid(Context context) {
        if (context == null) {
            return false;
        }
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity = (Activity) context;
        return (activity.isDestroyed() || activity.isFinishing()) ? false : true;
    }

    public static boolean isGameByFlag(String str) {
        try {
            PackageInfo packageInfo = getAppContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return false;
            }
            boolean z = (packageInfo.applicationInfo.flags & WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_CONSUME_IME_INSETS) != 0;
            if (str != null && str.contains("com.antutu")) {
                return false;
            }
            Log.d(TAG, "isGameApp(" + str + ") isGame : " + z);
            return z;
        } catch (Exception e) {
            Log.d(TAG, "isGameApp(" + str + ") Exception : " + e);
            return false;
        }
    }

    public static boolean isGameKeyClose() {
        int gameModeDBValue = ReflectUtilities.getGameModeDBValue(getAppContext());
        boolean z = (gameModeDBValue & 1) == 0;
        LogUtil.i(TAG, "isGameKeyClose() isGameKeyClose = " + z + ", gameKeys = " + gameModeDBValue);
        return z;
    }

    public static boolean isGameKeyMultiFunctions() {
        return FeatureUtil.supportGameKey() && FeatureUtil.supportSideKey();
    }

    public static boolean isGameScene() {
        int i = Settings.Global.getInt(getAppContext().getContentResolver(), "nubia_game_scene", 0);
        LogUtil.i(TAG, "gameScene=" + i);
        return 1 == i;
    }

    public static boolean isGameSpaceForeground() {
        String currentTopPkg = (isZte() || isRedMagicRunOnMyOs()) ? ActivityUtils.getCurrentTopPkg(getAppContext()) : getTopPackageNames();
        Log.d(TAG, "isGameSpaceForeground() topPackages : " + currentTopPkg);
        return !TextUtils.isEmpty(currentTopPkg) && currentTopPkg.contains(getAppContext().getPackageName());
    }

    public static boolean isHomeApp(String str) {
        PackageManager packageManager = getAppContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null) {
            return false;
        }
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo.activityInfo.packageName != null && (str.equals(resolveInfo.activityInfo.packageName) || str.contains(resolveInfo.activityInfo.packageName))) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInter() {
        return cn.nubia.common.util.CommonUtil.isInter();
    }

    public static boolean isMyOs() {
        return cn.nubia.common.util.CommonUtil.isMyOs();
    }

    public static boolean isNubiaAppStore() {
        return cn.nubia.common.util.CommonUtil.isNubia();
    }

    public static boolean isProcessIdle() {
        int i = Settings.Global.getInt(getAppContext().getContentResolver(), GameKeyObserver.GAME_KEY_STATE, -1);
        if ((i & 1) != 1) {
            LogUtil.i(TAG, "isProcessIdle() false because gameKey : " + i);
            return false;
        }
        int i2 = Settings.Global.getInt(getAppContext().getContentResolver(), "gamebox_mirror_displayid", 0);
        if (i2 != 0) {
            LogUtil.i(TAG, "isProcessIdle() false because hostMode : " + i2);
            return false;
        }
        if (!(!UpgradeManager.getInstance().isDownloading())) {
            LogUtil.i(TAG, "isProcessIdle() false because UpgradeManager state : " + UpgradeManager.getInstance().getStringState());
            return false;
        }
        if (isGameSpaceForeground()) {
            LogUtil.i(TAG, "isProcessIdle() false because GameSpace is Foreground App!");
            return false;
        }
        LogUtil.i(TAG, "isProcessIdle() true!");
        return true;
    }

    public static boolean isPureMode() {
        return Settings.Global.getInt(getAppContext().getContentResolver(), PURE_MODE, 0) == 1;
    }

    public static boolean isRealGameKeyClose() {
        int i = Settings.Global.getInt(getAppContext().getContentResolver(), GameKeyObserver.GAME_KEY_STATE, -1);
        LogUtil.i(TAG, "isRealGameKeyClose() value = " + i);
        return i < 0 ? isGameKeyClose() : (i & 1) == 1;
    }

    public static boolean isRedMagicRunOnMyOs() {
        return cn.nubia.common.util.CommonUtil.isRedMagicRunOnMyOs();
    }

    public static boolean isSwitchGameKeyToOtherFunctions() {
        return isGameKeyMultiFunctions() && Settings.System.getInt(getAppContext().getContentResolver(), "fourth_physical_key_function_value", 2) != 2;
    }

    public static boolean isSystemApp(String str) {
        try {
            PackageInfo packageInfo = getAppContext().getPackageManager().getPackageInfo(str, 0);
            if (packageInfo == null) {
                return false;
            }
            boolean z = true;
            if ((packageInfo.applicationInfo.flags & 1) == 0) {
                z = false;
            }
            Log.d(TAG, "isSystemApp(" + str + ") isSystemApp : " + z);
            return z;
        } catch (Exception e) {
            Log.d(TAG, "isSystemApp(" + str + ") Exception : " + e);
            return false;
        }
    }

    public static boolean isTencentAppStore() {
        return checkChannel(TENCENT_APP_STORE);
    }

    public static boolean isZte() {
        return cn.nubia.common.util.CommonUtil.isZte();
    }

    public static boolean isZteZType() {
        if (!isZte()) {
            return false;
        }
        int i = mFlagHasNeoStoreAndNoZteMarket;
        if (9 != i) {
            return i == 1;
        }
        PackageManager packageManager = getAppContext().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (queryIntentActivities == null) {
            return false;
        }
        mFlagHasNeoStoreAndNoZteMarket = 0;
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            if (resolveInfo.activityInfo.packageName != null) {
                String str = resolveInfo.activityInfo.packageName;
                Log.d("zte", "isZteZType() pkg : " + str);
                if (str.equals("cn.nubia.neostore") && isSystemApp(str)) {
                    mFlagHasNeoStoreAndNoZteMarket++;
                } else if (str.equals("zte.com.market") && isSystemApp(str)) {
                    mFlagHasNeoStoreAndNoZteMarket--;
                }
            }
        }
        return mFlagHasNeoStoreAndNoZteMarket == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized android.os.Bundle loadGameListFromXml(android.content.Context r9) {
        /*
            java.lang.Class<cn.nubia.gamelauncher.util.Util> r0 = cn.nubia.gamelauncher.util.Util.class
            monitor-enter(r0)
            android.os.Bundle r1 = new android.os.Bundle     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            r2 = 0
            android.content.res.AssetManager r9 = r9.getAssets()     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            java.lang.String r3 = "game_list.xml"
            java.io.InputStream r9 = r9.open(r3)     // Catch: java.lang.Throwable -> L70 java.lang.Exception -> L72
            org.xmlpull.v1.XmlPullParserFactory r3 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            r4 = 1
            r3.setNamespaceAware(r4)     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            org.xmlpull.v1.XmlPullParser r3 = r3.newPullParser()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            java.lang.String r5 = "UTF-8"
            r3.setInput(r9, r5)     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            int r5 = r3.getEventType()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
        L28:
            if (r5 == r4) goto L5f
            r6 = 2
            if (r5 != r6) goto L5a
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            java.lang.String r6 = "app"
            boolean r5 = r6.equals(r5)     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            if (r5 == 0) goto L5a
            java.lang.String r5 = "packageName"
            java.lang.String r5 = r3.getAttributeValue(r2, r5)     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            java.lang.String r6 = "tag"
            java.lang.String r6 = r3.getAttributeValue(r2, r6)     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            if (r5 == 0) goto L5a
            if (r6 == 0) goto L5a
            boolean r7 = r5.isEmpty()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            if (r7 != 0) goto L5a
            boolean r7 = r6.isEmpty()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            if (r7 != 0) goto L5a
            r1.putString(r5, r6)     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
        L5a:
            int r5 = r3.next()     // Catch: java.lang.Exception -> L6e java.lang.Throwable -> L8b
            goto L28
        L5f:
            if (r9 == 0) goto L89
            r9.close()     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L9c
            goto L89
        L65:
            r9 = move-exception
            java.lang.String r2 = "Util"
            java.lang.String r3 = "Failed to close input stream"
        L6a:
            android.util.Log.e(r2, r3, r9)     // Catch: java.lang.Throwable -> L9c
            goto L89
        L6e:
            r2 = move-exception
            goto L76
        L70:
            r1 = move-exception
            goto L8d
        L72:
            r9 = move-exception
            r8 = r2
            r2 = r9
            r9 = r8
        L76:
            java.lang.String r3 = "Util"
            java.lang.String r4 = "Failed to parse XML data"
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L8b
            if (r9 == 0) goto L89
            r9.close()     // Catch: java.lang.Exception -> L83 java.lang.Throwable -> L9c
            goto L89
        L83:
            r9 = move-exception
            java.lang.String r2 = "Util"
            java.lang.String r3 = "Failed to close input stream"
            goto L6a
        L89:
            monitor-exit(r0)
            return r1
        L8b:
            r1 = move-exception
            r2 = r9
        L8d:
            if (r2 == 0) goto L9b
            r2.close()     // Catch: java.lang.Exception -> L93 java.lang.Throwable -> L9c
            goto L9b
        L93:
            r9 = move-exception
            java.lang.String r2 = "Util"
            java.lang.String r3 = "Failed to close input stream"
            android.util.Log.e(r2, r3, r9)     // Catch: java.lang.Throwable -> L9c
        L9b:
            throw r1     // Catch: java.lang.Throwable -> L9c
        L9c:
            r9 = move-exception
            monitor-exit(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gamelauncher.util.Util.loadGameListFromXml(android.content.Context):android.os.Bundle");
    }

    public static void setBackgroundColorWithIcon(final Context context, Bitmap bitmap, final CardView cardView) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() { // from class: cn.nubia.gamelauncher.util.Util.1
            @Override // androidx.palette.graphics.Palette.PaletteAsyncListener
            public void onGenerated(Palette palette) {
                cardView.setCardBackgroundColor(Util.adjustSatAndVal(palette.getDominantColor(Util.getRandomColorForCard(context))));
            }
        });
    }

    public static void showCurrentVersion() {
        LogUtil.i(TAG, "showCurrentVersion() channel : " + getChannel() + ", versionName : " + getVersionName(getAppContext()));
    }

    public static void startActivityAsUser(Object obj, Intent intent, Bundle bundle, UserHandle userHandle) {
        Log.d(TAG, "startActivityAsUser E");
        try {
            Method method = Activity.class.getMethod("startActivityAsUser", Intent.class, Bundle.class, UserHandle.class);
            method.setAccessible(true);
            method.invoke(obj, intent, bundle, userHandle);
        } catch (Exception e) {
            Log.e(TAG, "Failed to call startActivityAsUser. " + e);
        }
    }

    public static boolean supportStreamGame() {
        return FeatureUtil.getBoolean(FeatureUtil.ZTE_FEATURE_STREAM_GAME, false).booleanValue() || FeatureUtil.getBoolean("ZTE_FEATURE_REDMAGIC_PC_GAME", false).booleanValue();
    }

    public static boolean supportVirtualGameKey() {
        boolean z = "1".equals(SystemProperties.get("ro.nubia.virtualgamekey.enable", "0")) || !GameSpaceConfig.supportGameKey();
        Log.d(TAG, "supportVirtualGameKey() support : " + z);
        return z;
    }

    public static void updateAppAddResumed(boolean z) {
        mIsAppAddResumed = z;
        updateHostAssistPanel();
    }

    public static void updateDensity(Context context) {
    }

    public static void updateHostAssistPanel() {
        Settings.Global.putInt(getAppContext().getContentResolver(), "expand_host_assist_panel", (mIsHostModeGameSpaceResumed || mIsAppAddResumed) ? 1 : 0);
    }

    public static void updateHostModeGameSpace(boolean z) {
        mIsHostModeGameSpaceResumed = z;
        updateHostAssistPanel();
    }
}
