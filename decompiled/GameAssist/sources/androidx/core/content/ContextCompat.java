package androidx.core.content;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.util.ObjectsCompat;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.concurrent.Executor;

@SuppressLint({"PrivateConstructorForUtilityClass"})
/* loaded from: classes.dex */
public class ContextCompat {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2839a = new Object();

    @RequiresApi
    static class Api21Impl {
        @DoNotInline
        static File a(Context context) {
            return context.getCodeCacheDir();
        }

        @DoNotInline
        static Drawable b(Context context, int i2) {
            return context.getDrawable(i2);
        }

        @DoNotInline
        static File c(Context context) {
            return context.getNoBackupFilesDir();
        }
    }

    @RequiresApi
    static class Api23Impl {
        @DoNotInline
        static int a(Context context, int i2) {
            return context.getColor(i2);
        }

        @DoNotInline
        static <T> T b(Context context, Class<T> cls) {
            return (T) context.getSystemService(cls);
        }

        @DoNotInline
        static String c(Context context, Class<?> cls) {
            return context.getSystemServiceName(cls);
        }
    }

    @RequiresApi
    static class Api24Impl {
        @DoNotInline
        static Context a(Context context) {
            return context.createDeviceProtectedStorageContext();
        }

        @DoNotInline
        static File b(Context context) {
            return context.getDataDir();
        }

        @DoNotInline
        static boolean c(Context context) {
            return context.isDeviceProtectedStorage();
        }
    }

    @RequiresApi
    static class Api26Impl {
        @DoNotInline
        static Intent a(Context context, @Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
            return ((i2 & 4) == 0 || str != null) ? context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i2 & 1) : context.registerReceiver(broadcastReceiver, intentFilter, ContextCompat.j(context), handler);
        }

        @DoNotInline
        static ComponentName b(Context context, Intent intent) {
            return context.startForegroundService(intent);
        }
    }

    @RequiresApi
    static class Api28Impl {
        @DoNotInline
        static Executor a(Context context) {
            return context.getMainExecutor();
        }
    }

    @RequiresApi
    static class Api30Impl {
        @NonNull
        @DoNotInline
        static Context a(@NonNull Context context, @Nullable String str) {
            return context.createAttributionContext(str);
        }

        @DoNotInline
        static String b(Context context) {
            return context.getAttributionTag();
        }

        @DoNotInline
        static Display c(Context context) {
            try {
                return context.getDisplay();
            } catch (UnsupportedOperationException unused) {
                Log.w("ContextCompat", "The context:" + context + " is not associated with any display. Return a fallback display instead.");
                return ((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplay(0);
            }
        }
    }

    @RequiresApi
    static class Api33Impl {
        @DoNotInline
        static Intent a(Context context, @Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
            return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i2);
        }
    }

    private static final class LegacyServiceMapHolder {

        /* renamed from: a, reason: collision with root package name */
        static final HashMap f2840a;

        static {
            HashMap hashMap = new HashMap();
            f2840a = hashMap;
            hashMap.put(SubscriptionManager.class, "telephony_subscription_service");
            hashMap.put(UsageStatsManager.class, "usagestats");
            hashMap.put(AppWidgetManager.class, "appwidget");
            hashMap.put(BatteryManager.class, "batterymanager");
            hashMap.put(CameraManager.class, "camera");
            hashMap.put(JobScheduler.class, "jobscheduler");
            hashMap.put(LauncherApps.class, "launcherapps");
            hashMap.put(MediaProjectionManager.class, "media_projection");
            hashMap.put(MediaSessionManager.class, "media_session");
            hashMap.put(RestrictionsManager.class, "restrictions");
            hashMap.put(TelecomManager.class, "telecom");
            hashMap.put(TvInputManager.class, "tv_input");
            hashMap.put(AppOpsManager.class, "appops");
            hashMap.put(CaptioningManager.class, "captioning");
            hashMap.put(ConsumerIrManager.class, "consumer_ir");
            hashMap.put(PrintManager.class, "print");
            hashMap.put(BluetoothManager.class, "bluetooth");
            hashMap.put(DisplayManager.class, "display");
            hashMap.put(UserManager.class, "user");
            hashMap.put(InputManager.class, "input");
            hashMap.put(MediaRouter.class, "media_router");
            hashMap.put(NsdManager.class, "servicediscovery");
            hashMap.put(AccessibilityManager.class, "accessibility");
            hashMap.put(AccountManager.class, "account");
            hashMap.put(ActivityManager.class, "activity");
            hashMap.put(AlarmManager.class, "alarm");
            hashMap.put(AudioManager.class, "audio");
            hashMap.put(ClipboardManager.class, "clipboard");
            hashMap.put(ConnectivityManager.class, "connectivity");
            hashMap.put(DevicePolicyManager.class, "device_policy");
            hashMap.put(DownloadManager.class, "download");
            hashMap.put(DropBoxManager.class, "dropbox");
            hashMap.put(InputMethodManager.class, "input_method");
            hashMap.put(KeyguardManager.class, "keyguard");
            hashMap.put(LayoutInflater.class, "layout_inflater");
            hashMap.put(LocationManager.class, "location");
            hashMap.put(NfcManager.class, "nfc");
            hashMap.put(NotificationManager.class, "notification");
            hashMap.put(PowerManager.class, "power");
            hashMap.put(SearchManager.class, "search");
            hashMap.put(SensorManager.class, "sensor");
            hashMap.put(StorageManager.class, "storage");
            hashMap.put(TelephonyManager.class, "phone");
            hashMap.put(TextServicesManager.class, "textservices");
            hashMap.put(UiModeManager.class, "uimode");
            hashMap.put(UsbManager.class, "usb");
            hashMap.put(Vibrator.class, "vibrator");
            hashMap.put(WallpaperManager.class, "wallpaper");
            hashMap.put(WifiP2pManager.class, "wifip2p");
            hashMap.put(WifiManager.class, "wifi");
            hashMap.put(WindowManager.class, "window");
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo
    public @interface RegisterReceiverFlags {
    }

    public static int a(Context context, String str) {
        ObjectsCompat.d(str, "permission must be non-null");
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    public static Context b(Context context) {
        return Api24Impl.a(context);
    }

    public static int c(Context context, int i2) {
        return Api23Impl.a(context, i2);
    }

    public static ColorStateList d(Context context, int i2) {
        return ResourcesCompat.d(context.getResources(), i2, context.getTheme());
    }

    public static Drawable e(Context context, int i2) {
        return Api21Impl.b(context, i2);
    }

    public static File[] f(Context context) {
        return context.getExternalCacheDirs();
    }

    public static File[] g(Context context, String str) {
        return context.getExternalFilesDirs(str);
    }

    public static Executor h(Context context) {
        return Api28Impl.a(context);
    }

    public static Object i(Context context, Class cls) {
        return Api23Impl.b(context, cls);
    }

    static String j(Context context) {
        String str = context.getPackageName() + ".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION";
        if (PermissionChecker.b(context, str) == 0) {
            return str;
        }
        throw new RuntimeException("Permission " + str + " is required by your application to receive broadcasts, please add it to your manifest");
    }

    public static Intent k(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
        int i3 = i2 & 1;
        if (i3 != 0 && (i2 & 4) != 0) {
            throw new IllegalArgumentException("Cannot specify both RECEIVER_VISIBLE_TO_INSTANT_APPS and RECEIVER_NOT_EXPORTED");
        }
        if (i3 != 0) {
            i2 |= 2;
        }
        int i4 = i2;
        int i5 = i4 & 2;
        if (i5 == 0 && (i4 & 4) == 0) {
            throw new IllegalArgumentException("One of either RECEIVER_EXPORTED or RECEIVER_NOT_EXPORTED is required");
        }
        if (i5 == 0 || (i4 & 4) == 0) {
            return Api33Impl.a(context, broadcastReceiver, intentFilter, str, handler, i4);
        }
        throw new IllegalArgumentException("Cannot specify both RECEIVER_EXPORTED and RECEIVER_NOT_EXPORTED");
    }

    public static boolean l(Context context, Intent[] intentArr, Bundle bundle) {
        context.startActivities(intentArr, bundle);
        return true;
    }

    public static void m(Context context, Intent intent, Bundle bundle) {
        context.startActivity(intent, bundle);
    }
}
