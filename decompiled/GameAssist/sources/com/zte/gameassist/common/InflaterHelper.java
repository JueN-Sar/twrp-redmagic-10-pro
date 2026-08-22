package com.zte.gameassist.common;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;

/* loaded from: classes2.dex */
public class InflaterHelper {

    /* renamed from: e, reason: collision with root package name */
    public static ObserverData f16516e = new ObserverData();

    /* renamed from: f, reason: collision with root package name */
    private static final InflaterHelper f16517f = new InflaterHelper();

    /* renamed from: a, reason: collision with root package name */
    private Handler f16518a = new Handler(Looper.getMainLooper());

    /* renamed from: b, reason: collision with root package name */
    private final Context f16519b;

    /* renamed from: c, reason: collision with root package name */
    private final float f16520c;

    /* renamed from: d, reason: collision with root package name */
    private final LayoutInflater f16521d;

    public static class FixedScreenState {

        /* renamed from: a, reason: collision with root package name */
        public final int f16522a;

        /* renamed from: b, reason: collision with root package name */
        public final float f16523b;

        /* renamed from: c, reason: collision with root package name */
        public final int f16524c;

        /* renamed from: d, reason: collision with root package name */
        public final int f16525d;

        /* renamed from: e, reason: collision with root package name */
        public final int f16526e;

        /* renamed from: f, reason: collision with root package name */
        public final int f16527f;

        /* renamed from: g, reason: collision with root package name */
        public final int f16528g;

        /* renamed from: h, reason: collision with root package name */
        public final boolean f16529h;

        public FixedScreenState(int i2, float f2, int i3, int i4, int i5, int i6, int i7, boolean z) {
            this.f16522a = i2;
            this.f16523b = f2;
            this.f16524c = i3;
            this.f16525d = i4;
            this.f16526e = i5;
            this.f16527f = i6;
            this.f16528g = i7;
            this.f16529h = z;
        }
    }

    private InflaterHelper() {
        Context context = ContextWrapper.getContext();
        this.f16519b = context;
        context.setTheme(R.style.GameAssist_Theme_ZTE_Light);
        n(context.getResources().getDisplayMetrics());
        this.f16520c = context.getResources().getDisplayMetrics().density;
        this.f16521d = LayoutInflater.from(context).cloneInContext(context);
    }

    public static Context b() {
        return f16517f.f16519b;
    }

    public static int c(DisplayMetrics displayMetrics) {
        return SystemProperties.getInt("ro.sf.lcd_density", displayMetrics.densityDpi);
    }

    public static Resources d() {
        return f16517f.h();
    }

    public static View e(int i2) {
        return f16517f.i(i2, null);
    }

    public static View f(int i2, ViewGroup viewGroup) {
        return f16517f.i(i2, viewGroup);
    }

    public static View g(int i2, ViewGroup viewGroup, boolean z) {
        return f16517f.j(i2, viewGroup, z);
    }

    private Resources h() {
        l();
        return this.f16519b.getResources();
    }

    private View j(int i2, ViewGroup viewGroup, boolean z) {
        l();
        return this.f16521d.inflate(i2, viewGroup, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void k(FixedScreenState fixedScreenState) {
        ObserverData observerData = f16516e;
        observerData.f(fixedScreenState, observerData.b() != null);
    }

    public static void m(Application application) {
        f16517f.n(application.getResources().getDisplayMetrics());
    }

    public View i(int i2, ViewGroup viewGroup) {
        l();
        return this.f16521d.inflate(i2, viewGroup);
    }

    public synchronized void l() {
        n(this.f16519b.getResources().getDisplayMetrics());
    }

    public void n(DisplayMetrics displayMetrics) {
        int c2 = c(displayMetrics);
        float f2 = c2 / 160.0f;
        int i2 = (int) (displayMetrics.widthPixels / f2);
        int i3 = (int) (displayMetrics.heightPixels / f2);
        Configuration configuration = ContextWrapper.getContext().getResources().getConfiguration();
        if (configuration.densityDpi == c2 && displayMetrics.density == f2 && displayMetrics.densityDpi == c2 && configuration.screenWidthDp == i2 && configuration.screenHeightDp == i3) {
            return;
        }
        configuration.fontScale = 1.0f;
        configuration.densityDpi = c2;
        configuration.screenWidthDp = i2;
        configuration.screenHeightDp = i3;
        configuration.smallestScreenWidthDp = i2 < i3 ? i2 : i3;
        this.f16519b.getResources().updateConfiguration(configuration, displayMetrics);
        GaLog.a("InflaterHelper", "resetDensity density=" + f2 + " adapter=" + DensityHelper.b(this.f16519b.getResources().getDisplayMetrics()));
        final FixedScreenState fixedScreenState = new FixedScreenState(c2, 1.0f, i2, i3, configuration.smallestScreenWidthDp, displayMetrics.widthPixels, displayMetrics.heightPixels, i2 > i3);
        this.f16518a.post(new Runnable() { // from class: com.zte.gameassist.common.l
            @Override // java.lang.Runnable
            public final void run() {
                InflaterHelper.k(InflaterHelper.FixedScreenState.this);
            }
        });
    }
}
