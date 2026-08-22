package com.zte.mifavor.widget;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentActivity;
import com.zte.ctsutils.CtsUtils;
import com.zte.extres.R;
import com.zte.feature.Feature;
import com.zte.mifavor.utils.SinkUtils;
import com.zte.mifavor.utils.UIUtils;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class Utils {

    /* renamed from: a, reason: collision with root package name */
    public static final boolean f17814a = true;

    /* renamed from: b, reason: collision with root package name */
    public static final boolean f17815b = true;

    /* renamed from: c, reason: collision with root package name */
    public static final boolean f17816c = true;

    /* renamed from: d, reason: collision with root package name */
    public static final boolean f17817d = true;

    /* renamed from: e, reason: collision with root package name */
    static String f17818e = "myswitchcolor";

    /* renamed from: f, reason: collision with root package name */
    static String f17819f = "myraidocolor";

    /* renamed from: g, reason: collision with root package name */
    static String f17820g = "mycheckcolor";

    /* renamed from: h, reason: collision with root package name */
    static String f17821h = "color_store";

    /* renamed from: i, reason: collision with root package name */
    private static final String f17822i = g();

    public static void A(Window window) {
        Log.v("Z#Utils", "set Window Layout Params in.");
        try {
            View rootView = window.getDecorView().getRootView();
            if (rootView != null) {
                ViewCompat.x0(rootView, new OnApplyWindowInsetsListener() { // from class: com.zte.mifavor.widget.l
                    @Override // androidx.core.view.OnApplyWindowInsetsListener
                    public final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
                        WindowInsetsCompat y;
                        y = Utils.y(view, windowInsetsCompat);
                        return y;
                    }
                });
            }
        } catch (Exception e2) {
            Log.e("Z#Utils", "set window Layout Params. error.e=", e2);
        }
        Log.v("Z#Utils", "set Window Layout Params out.");
    }

    public static void B(WindowManager.LayoutParams layoutParams, boolean z) {
        try {
            Method declaredMethod = Class.forName("android.view.WindowManager$LayoutParams").getDeclaredMethod("setZTEImeFitEnabled", Boolean.TYPE);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(layoutParams, Boolean.valueOf(z));
            Log.d("Z#Utils", "set ZTE Ime Fit Enabled out. enabled=" + z);
        } catch (Exception e2) {
            Log.d("Z#Utils", "set ZTE Ime Fit Enabled error. e=", e2);
        }
    }

    public static void C(Window window) {
        if (window == null) {
            Log.e("Z#Utils", "setup Navigation Area out. window is null.");
            return;
        }
        Context context = window.getContext();
        if (context == null) {
            Log.e("Z#Utils", "setup Navigation Area out. context is null.");
            return;
        }
        boolean c2 = SinkUtils.c(context.getResources());
        if (c2) {
            Log.d("Z#Utils", "setup Navigation Area out. do nothing. bIsLand=" + c2);
            return;
        }
        View findViewById = window.findViewById(R.id.navigation_key_area);
        if (findViewById == null) {
            Log.e("Z#Utils", "setup Navigation Area out.. navigationArea is null.");
            return;
        }
        if (o(context)) {
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.mfvc_indicator_height_extra);
            findViewById.setLayoutParams(layoutParams);
            findViewById.setVisibility(0);
            Log.i("Z#Utils", "setup Navigation Area out. show navigationArea in Indicator Mode.");
            return;
        }
        if (x(context)) {
            findViewById.setVisibility(0);
            Log.i("Z#Utils", "setup Navigation Area out. show navigationArea in Virtual Navigation Mode.");
        } else {
            findViewById.setVisibility(8);
            Log.i("Z#Utils", "setup Navigation Area out. hide navigationArea.");
        }
    }

    public static int b(Context context, double d2) {
        return (int) ((context.getResources().getDisplayMetrics().density * d2) + 0.5d);
    }

    public static int c(Context context, int i2) {
        return (int) ((context.getResources().getDisplayMetrics().density * i2) + 0.5f);
    }

    public static int d(int i2) {
        return (int) ((Color.red(i2) * 0.299d) + (Color.green(i2) * 0.587d) + (Color.blue(i2) * 0.114d));
    }

    public static int e(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        double d2 = 0.0d;
        int i2 = 0;
        for (int i3 = 0; i3 < width; i3++) {
            for (int i4 = 0; i4 < height; i4++) {
                i2++;
                int pixel = bitmap.getPixel(i3, i4);
                d2 = d2 + (Color.red(pixel) * 0.299d) + (Color.green(pixel) * 0.587d) + (Color.blue(pixel) * 0.114d);
            }
        }
        if (i2 == 0) {
            return 0;
        }
        int i5 = (int) (d2 / i2);
        Log.d("Z#Utils", "brightness=" + i5);
        return i5;
    }

    public static int f(Context context) {
        if (UIUtils.j(context)) {
            Log.d("Z#Utils", "get Current Font Weight, is 0.");
            return 0;
        }
        int i2 = Settings.Secure.getInt(context.getContentResolver(), "font_weight_adjustment", 0);
        Log.d("Z#Utils", "get Current Font Weight=" + i2);
        return i2;
    }

    private static String g() {
        String str = Build.HARDWARE;
        return str.matches("qcom") ? "QCOM" : str.matches("mt[0-9]*") ? "MTK" : "SPREADTRUM";
    }

    public static String h() {
        try {
            return SystemProperties.get("ro.product.name", "unkown");
        } catch (Exception e2) {
            Log.w("Z#Utils", "get Properties .name error, e", e2);
            return "";
        }
    }

    public static boolean i(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            Log.e("Z#Utils", "error:" + e2.toString());
            return false;
        }
    }

    public static boolean j() {
        try {
            return SystemProperties.getBoolean("persist.sys.stc", false);
        } catch (Exception e2) {
            Log.e("Z#Utils", "isCTSMode Exception", e2);
            return false;
        }
    }

    public static boolean k(Context context) {
        if (context == null) {
            return false;
        }
        boolean z = j() || i(context, "com.android.cts.verifier") || i(context, "com.google.android.gts.verifier") || l();
        Log.d("Z#Utils", "is CTS Or GTS Mode out. isCorGMode=" + z);
        return z;
    }

    public static boolean l() {
        try {
            boolean ctsRunningStatus = Feature.getCtsRunningStatus();
            boolean isTopAppCtsPackage = CtsUtils.isTopAppCtsPackage();
            r1 = ctsRunningStatus || isTopAppCtsPackage;
            Log.d("Z#Utils", "isCtsTesting=" + r1 + ", isCtsRunning=" + ctsRunningStatus + ", isTopAppCts=" + isTopAppCtsPackage);
        } catch (Exception e2) {
            Log.w("Z#Utils", "isCtsTesting error. e=", e2);
        } catch (NoSuchMethodError e3) {
            Log.w("Z#Utils", "isCtsTesting() but NoSuchMethodException " + e3.getMessage());
        }
        return r1;
    }

    public static boolean m(Context context) {
        boolean z = (context.getResources().getConfiguration().uiMode & 48) == 32;
        Log.d("Z#Utils", "is Dark Mode Enabled = " + z);
        return z;
    }

    public static boolean n() {
        try {
            return SystemProperties.getBoolean("ro.vendor.feature.myos_feature_complex_animation", true);
        } catch (Exception e2) {
            Log.w("Z#Utils", "get Properties myos_feature_complex_animation error, e = ", e2);
            return true;
        }
    }

    public static boolean o(Context context) {
        int i2 = Settings.System.getInt(context.getContentResolver(), "gesture_bottom_indicator", -1);
        Log.v("Z#Utils", "is Gesture Indicator Mode. ishow= " + i2);
        return !x(context) && i2 == 0;
    }

    public static boolean p() {
        try {
            return SystemProperties.getBoolean("ro.vendor.feature.myos_feature_low_end_phone", false);
        } catch (Exception e2) {
            Log.w("Z#Utils", "get Properties myos_feature_low_end_phone error, e = ", e2);
            return false;
        }
    }

    public static boolean q(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static boolean r(Context context) {
        return context.getResources().getConfiguration().orientation == 1;
    }

    public static boolean s(Bitmap bitmap) {
        return e(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight())) < 190;
    }

    public static boolean t() {
        return "SPREADTRUM".equals(f17822i);
    }

    public static boolean u() {
        try {
            return SystemProperties.getBoolean("ro.vendor.feature.zte_feature_voice_search", false);
        } catch (Exception e2) {
            Log.w("Z#Utils", "get Properties zte_feature_voice_search error, e = ", e2);
            return false;
        }
    }

    private static boolean v() {
        try {
            return SystemProperties.getBoolean("ro.vendor.feature.zte_tablet_enable", false);
        } catch (Exception e2) {
            Log.e("Z#Utils", "isTablet Exception", e2);
            return false;
        }
    }

    public static boolean w() {
        try {
            return ActivityManager.isUserAMonkey();
        } catch (Exception e2) {
            Log.e("Z#Utils", "is User A monkey error, e = ", e2);
            return false;
        }
    }

    public static boolean x(Context context) {
        int i2 = Settings.Secure.getInt(context.getContentResolver(), "navigation_mode", -1);
        Log.v("Z#Utils", "is Virtual Navigation. mode= " + i2);
        return i2 == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat y(View view, WindowInsetsCompat windowInsetsCompat) {
        try {
            Insets f2 = windowInsetsCompat.f(WindowInsetsCompat.Type.e());
            WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) view.getLayoutParams();
            Log.d("Z#Utils", "set Window Layout Margin. verticalMargin=" + layoutParams.verticalMargin + ", horizontalMargin=" + layoutParams.horizontalMargin + ", Insets left=" + f2.f2920a + ", top=" + f2.f2921b + ", right=" + f2.f2922c + ", bottom=" + f2.f2923d);
            layoutParams.horizontalMargin = (float) f2.f2920a;
            layoutParams.verticalMargin = 0.0f;
            view.setLayoutParams(layoutParams);
        } catch (Exception e2) {
            Log.e("Z#Utils", "set Layout Params. e=", e2);
        }
        return WindowInsetsCompat.f3439b;
    }

    public static void z(Window window) {
        if (window == null) {
            Log.w("Z#Utils", "set Fit Insets warning. do nothing. window is null.");
            return;
        }
        Context context = window.getContext();
        if (context == null) {
            Log.w("Z#Utils", "set Fit Insets warning. do nothing. context is null.");
            return;
        }
        Log.v("Z#Utils", "set Fit Insets in. context=" + context);
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (v() && ((context instanceof Activity) || (context instanceof FragmentActivity) || (context instanceof AppCompatActivity))) {
            Log.w("Z#Utils", "set Fit Insets warning. do nothing. is tablet & is not dialog.");
        } else {
            B(attributes, true);
        }
        attributes.setFitInsetsTypes(0);
        attributes.flags |= Integer.MIN_VALUE;
        window.setAttributes(attributes);
        Log.d("Z#Utils", "set Fit Insets Types 0 out. setZTEImeFitEnabled. layoutParams=" + attributes);
        if (t()) {
            WindowInsetsControllerCompat F = ViewCompat.F(window.getDecorView());
            if (F != null) {
                boolean z = !q(window.getContext());
                Log.d("Z#Utils", "set Fit Insets Types.  isSprd forceNavigationButtonsDark=" + z);
                F.c(z);
            } else {
                Log.w("Z#Utils", "set Fit Insets Types.  windowInsetsController=" + F);
            }
        }
        Log.v("Z#Utils", "set Fit Insets out.");
    }
}
