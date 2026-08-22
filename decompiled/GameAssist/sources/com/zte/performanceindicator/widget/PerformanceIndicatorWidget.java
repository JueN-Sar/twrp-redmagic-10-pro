package com.zte.performanceindicator.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import com.zte.performanceindicator.PerfIndicatorManager;
import com.zte.performanceindicator.R;
import com.zte.performanceindicator.utils.Utils;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Locale;

/* loaded from: classes2.dex */
public class PerformanceIndicatorWidget extends AppWidgetProvider {

    /* renamed from: a, reason: collision with root package name */
    private static RemoteViews f17968a = null;

    /* renamed from: b, reason: collision with root package name */
    private static PendingIntent f17969b = null;

    /* renamed from: c, reason: collision with root package name */
    private static ComponentName f17970c = null;

    /* renamed from: d, reason: collision with root package name */
    private static boolean f17971d = false;

    /* renamed from: e, reason: collision with root package name */
    private static long f17972e = -1;

    /* renamed from: f, reason: collision with root package name */
    private static long f17973f = -1;

    /* renamed from: g, reason: collision with root package name */
    private static int f17974g;

    /* renamed from: h, reason: collision with root package name */
    private static int f17975h;

    private void d(Context context) {
        if (Utils.c(context)) {
            f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_detect));
        } else {
            f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_no_network));
        }
    }

    private RemoteViews e(Context context) {
        return new RemoteViews(context.getPackageName(), R.layout.performance_indicator_widget_view);
    }

    private int f(Context context, int i2) {
        return context.getResources().getColor(i2);
    }

    private PendingIntent g(Context context) {
        Intent intent = new Intent(context, (Class<?>) PerformanceIndicatorWidget.class);
        intent.setAction("com.zte.performanceindicator.ACTION_WIDGET_DETECT");
        return PendingIntent.getBroadcast(context, 0, intent, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_OPT_OUT_EDGE_TO_EDGE);
    }

    private void h(Context context) {
        if (f17968a == null) {
            f17968a = e(context);
        }
        if (f17969b == null) {
            f17969b = g(context);
        }
        if (f17970c == null) {
            f17970c = new ComponentName(context, (Class<?>) PerformanceIndicatorWidget.class);
        }
        f17968a.setOnClickPendingIntent(R.id.detect_button, f17969b);
    }

    private void i(Context context, AppWidgetManager appWidgetManager) {
        Log.d("PerformanceIndicatorWidget", "initWidget: ");
        h(context);
        d(context);
        appWidgetManager.updateAppWidget(f17970c, f17968a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(final Context context) {
        PerfIndicatorManager.t().B(true);
        new CountDownTimer(10000L, 1000L) { // from class: com.zte.performanceindicator.widget.PerformanceIndicatorWidget.2
            @Override // android.os.CountDownTimer
            public void onFinish() {
                PerfIndicatorManager.t().B(false);
                PerformanceIndicatorWidget.f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_detect));
                AppWidgetManager.getInstance(context).updateAppWidget(PerformanceIndicatorWidget.f17970c, PerformanceIndicatorWidget.f17968a);
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j2) {
                PerformanceIndicatorWidget.f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_cooling, Integer.valueOf((int) (j2 / 1000))));
                AppWidgetManager.getInstance(context).updateAppWidget(PerformanceIndicatorWidget.f17970c, PerformanceIndicatorWidget.f17968a);
            }
        }.start();
    }

    private void k(final Context context) {
        if (!f17971d) {
            j(context);
            return;
        }
        f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_detect_fail));
        PerfIndicatorManager.t().C(true);
        new Handler().postDelayed(new Runnable() { // from class: com.zte.performanceindicator.widget.PerformanceIndicatorWidget.1
            @Override // java.lang.Runnable
            public void run() {
                PerformanceIndicatorWidget.this.j(context);
                PerfIndicatorManager.t().C(false);
            }
        }, 3000L);
    }

    private void l() {
        if (f17971d) {
            f17968a.setTextViewText(R.id.detected_time, "-- -- --");
        } else {
            f17968a.setTextViewText(R.id.detected_time, Utils.a());
        }
    }

    private void m(Context context) {
        if (f17973f == -1) {
            f17975h = 0;
            f17968a.setTextViewText(R.id.jitter_num, "--ms");
            f17968a.setTextColor(R.id.jitter_num, f(context, R.color.performance_indicator_no_detect));
            return;
        }
        f17968a.setTextViewText(R.id.jitter_num, f17973f + "ms");
        long j2 = f17973f;
        if (j2 <= 60) {
            f17975h = 1;
            f17968a.setTextColor(R.id.jitter_num, f(context, R.color.performance_indicator_good));
        } else if (j2 <= 120) {
            f17975h = 2;
            f17968a.setTextColor(R.id.jitter_num, f(context, R.color.performance_indicator_fair));
        } else {
            f17975h = 3;
            f17968a.setTextColor(R.id.jitter_num, f(context, R.color.performance_indicator_poor));
        }
    }

    private void n(Context context) {
        if (f17972e == -1) {
            f17974g = 0;
            f17968a.setTextViewText(R.id.latency_num, "--ms");
            f17968a.setTextColor(R.id.latency_num, f(context, R.color.performance_indicator_no_detect));
            return;
        }
        f17968a.setTextViewText(R.id.latency_num, f17972e + "ms");
        long j2 = f17972e;
        if (j2 <= 60) {
            f17974g = 1;
            f17968a.setTextColor(R.id.latency_num, f(context, R.color.performance_indicator_good));
        } else if (j2 <= 120) {
            f17974g = 2;
            f17968a.setTextColor(R.id.latency_num, f(context, R.color.performance_indicator_fair));
        } else {
            f17974g = 3;
            f17968a.setTextColor(R.id.latency_num, f(context, R.color.performance_indicator_poor));
        }
    }

    private void o(Context context) {
        int i2;
        int i3 = f17974g;
        if (i3 == 0 || (i2 = f17975h) == 0) {
            f17968a.setTextViewText(R.id.perf_indicator_detected_result, "--");
            f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_no_detect));
            f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_no_detected_main_bg);
        } else if (i3 == 1) {
            if (i2 == 1) {
                f17968a.setTextViewText(R.id.perf_indicator_detected_result, context.getString(R.string.performance_indicator_good_result));
                f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_good));
                f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_good_main_bg);
            } else if (i2 == 2) {
                f17968a.setTextViewText(R.id.perf_indicator_detected_result, context.getString(R.string.performance_indicator_fair_result));
                f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_fair));
                f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_fair_main_bg);
            } else {
                f17968a.setTextViewText(R.id.perf_indicator_detected_result, context.getString(R.string.performance_indicator_poor_result));
                f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_poor));
                f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_poor_main_bg);
            }
        } else if (i3 != 2) {
            f17968a.setTextViewText(R.id.perf_indicator_detected_result, context.getString(R.string.performance_indicator_poor_result));
            f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_poor));
            f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_poor_main_bg);
        } else if (i2 == 1) {
            f17968a.setTextViewText(R.id.perf_indicator_detected_result, context.getString(R.string.performance_indicator_fair_result));
            f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_fair));
            f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_fair_main_bg);
        } else {
            f17968a.setTextViewText(R.id.perf_indicator_detected_result, context.getString(R.string.performance_indicator_poor_result));
            f17968a.setTextColor(R.id.perf_indicator_detected_result, f(context, R.color.performance_indicator_poor));
            f17968a.setImageViewResource(R.id.perf_indicator_detected_main_bg, R.drawable.performance_indicator_poor_main_bg);
        }
        if ("zh".equals(Locale.getDefault().getLanguage())) {
            f17968a.setTextViewTextSize(R.id.perf_indicator_detected_result, 1, Utils.d(context, 156.0f));
            f17968a.setViewLayoutMargin(R.id.perf_indicator_detected_result, 5, Utils.d(context, 112.0f), 1);
        } else {
            f17968a.setTextViewTextSize(R.id.perf_indicator_detected_result, 1, Utils.d(context, 120.0f));
            f17968a.setViewLayoutMargin(R.id.perf_indicator_detected_result, 5, Utils.d(context, 84.0f), 1);
        }
        if (f17975h == 0 || f17974g == 0) {
            f17968a.setTextViewTextSize(R.id.perf_indicator_detected_result, 1, Utils.d(context, 156.0f));
            f17968a.setViewLayoutMargin(R.id.perf_indicator_detected_result, 5, Utils.d(context, 117.0f), 1);
        }
    }

    private void p(Context context) {
        k(context);
        n(context);
        m(context);
        o(context);
        l();
    }

    private void q(Context context) {
        Log.d("PerformanceIndicatorWidget", "updateWidget: ");
        h(context);
        p(context);
        AppWidgetManager.getInstance(context).updateAppWidget(f17970c, f17968a);
    }

    private void r(Context context) {
        if (!PerfIndicatorManager.t().f17910o && !PerfIndicatorManager.t().f17909n) {
            f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_detect));
        }
        o(context);
    }

    @Override // android.appwidget.AppWidgetProvider, android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        char c2;
        try {
            String action = intent.getAction();
            switch (action.hashCode()) {
                case -693374951:
                    if (action.equals("com.zte.performanceindicator.CONNECTIVITY_CHANGE")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -19011148:
                    if (action.equals("android.intent.action.LOCALE_CHANGED")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 30864884:
                    if (action.equals("com.zte.performanceindicator.ACTION_WIDGET_DETECT")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1174250808:
                    if (action.equals("com.zte.performanceindicator.ACTION_NETWORK_RESULT")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                Log.d("PerformanceIndicatorWidget", "onReceive: ACTION_WIDGET_DETECT");
                if (!PerfIndicatorManager.t().f17909n && !PerfIndicatorManager.t().f17910o) {
                    if (!Utils.c(context)) {
                        Log.d("PerformanceIndicatorWidget", "onReceive: current no network");
                        return;
                    }
                    PerfIndicatorManager.t().s();
                    h(context);
                    f17968a.setTextViewText(R.id.detect_text, context.getString(R.string.performance_indicator_detecting));
                    AppWidgetManager.getInstance(context).updateAppWidget(f17970c, f17968a);
                    return;
                }
                Log.d("PerformanceIndicatorWidget", "onReceive: current is detecting or showing that detect fail or cooling");
                return;
            }
            if (c2 == 1) {
                f17971d = intent.getBooleanExtra("isCheckError", false);
                f17972e = intent.getLongExtra("latency", -1L);
                f17973f = intent.getLongExtra("jitter", -1L);
                Log.d("PerformanceIndicatorWidget", "onReceive: ACTION_NETWORK_RESULT mIsCheckError=" + f17971d + " mLatencyNum=" + f17972e + " mJitterNum=" + f17973f);
                q(context);
                return;
            }
            if (c2 == 2) {
                Log.d("PerformanceIndicatorWidget", "onReceive: ACTION_NETWORK_CHANGE");
                h(context);
                d(context);
                AppWidgetManager.getInstance(context).updateAppWidget(f17970c, f17968a);
                return;
            }
            if (c2 != 3) {
                super.onReceive(context, intent);
                return;
            }
            Log.d("PerformanceIndicatorWidget", "onReceive: ACTION_LOCALE_CHANGED");
            h(context);
            r(context);
            AppWidgetManager.getInstance(context).updateAppWidget(f17970c, f17968a);
        } catch (Exception e2) {
            Log.d("PerformanceIndicatorWidget", "onReceive e = " + e2);
        }
    }

    @Override // android.appwidget.AppWidgetProvider
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        i(context, appWidgetManager);
    }
}
