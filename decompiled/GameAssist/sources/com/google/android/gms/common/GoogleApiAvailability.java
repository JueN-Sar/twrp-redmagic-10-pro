package com.google.android.gms.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ProgressBar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.api.internal.zabw;
import com.google.android.gms.common.api.internal.zabx;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zag;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.InstantApps;
import com.google.android.gms.internal.base.zae;
import com.google.android.gms.internal.base.zao;
import com.google.android.gms.internal.base.zap;
import com.google.errorprone.annotations.RestrictedInheritance;
import com.zte.distbus.basetransfer.DistBusKeys;

@RestrictedInheritance(allowedOnPath = ".*java.*/com/google/android/gms.*", allowlistAnnotations = {com.google.android.gms.internal.base.zad.class, zae.class}, explanation = "Sub classing of GMS Core's APIs are restricted to GMS Core client libs and testing fakes.", link = "go/gmscore-restrictedinheritance")
/* loaded from: classes.dex */
public class GoogleApiAvailability extends GoogleApiAvailabilityLight {

    /* renamed from: c, reason: collision with root package name */
    private String f10501c;

    /* renamed from: e, reason: collision with root package name */
    private static final Object f10499e = new Object();

    /* renamed from: f, reason: collision with root package name */
    private static final GoogleApiAvailability f10500f = new GoogleApiAvailability();

    /* renamed from: d, reason: collision with root package name */
    public static final int f10498d = GoogleApiAvailabilityLight.f10502a;

    public static GoogleApiAvailability q() {
        return f10500f;
    }

    public final boolean A(Context context, ConnectionResult connectionResult, int i2) {
        PendingIntent p2;
        if (InstantApps.a(context) || (p2 = p(context, connectionResult)) == null) {
            return false;
        }
        x(context, connectionResult.G(), null, PendingIntent.getActivity(context, 0, GoogleApiActivity.a(context, p2, i2, true), zap.f11385a | 134217728));
        return true;
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public Intent d(Context context, int i2, String str) {
        return super.d(context, i2, str);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public PendingIntent e(Context context, int i2, int i3) {
        return super.e(context, i2, i3);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final String g(int i2) {
        return super.g(i2);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public int i(Context context) {
        return super.i(context);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public int j(Context context, int i2) {
        return super.j(context, i2);
    }

    @Override // com.google.android.gms.common.GoogleApiAvailabilityLight
    public final boolean m(int i2) {
        return super.m(i2);
    }

    public Dialog o(Activity activity, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        return t(activity, i2, zag.b(activity, d(activity, i2, DistBusKeys.KEY_WIFI_DBDC), i3), onCancelListener, null);
    }

    public PendingIntent p(Context context, ConnectionResult connectionResult) {
        return connectionResult.T() ? connectionResult.R() : e(context, connectionResult.G(), 0);
    }

    public boolean r(Activity activity, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        Dialog o2 = o(activity, i2, i3, onCancelListener);
        if (o2 == null) {
            return false;
        }
        w(activity, o2, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }

    public void s(Context context, int i2) {
        x(context, i2, null, f(context, i2, 0, "n"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    final Dialog t(Context context, int i2, zag zagVar, DialogInterface.OnCancelListener onCancelListener, DialogInterface.OnClickListener onClickListener) {
        if (i2 == 0) {
            return null;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.alertDialogTheme, typedValue, true);
        AlertDialog.Builder builder = "Theme.Dialog.Alert".equals(context.getResources().getResourceEntryName(typedValue.resourceId)) ? new AlertDialog.Builder(context, 5) : null;
        if (builder == null) {
            builder = new AlertDialog.Builder(context);
        }
        builder.setMessage(com.google.android.gms.common.internal.zac.c(context, i2));
        if (onCancelListener != null) {
            builder.setOnCancelListener(onCancelListener);
        }
        String b2 = com.google.android.gms.common.internal.zac.b(context, i2);
        if (b2 != null) {
            if (zagVar == null) {
                zagVar = onClickListener;
            }
            builder.setPositiveButton(b2, zagVar);
        }
        String f2 = com.google.android.gms.common.internal.zac.f(context, i2);
        if (f2 != null) {
            builder.setTitle(f2);
        }
        Log.w("GoogleApiAvailability", String.format("Creating dialog for Google Play services availability issue. ConnectionResult=%s", Integer.valueOf(i2)), new IllegalArgumentException());
        return builder.create();
    }

    public final Dialog u(Activity activity, DialogInterface.OnCancelListener onCancelListener) {
        ProgressBar progressBar = new ProgressBar(activity, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(0);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(progressBar);
        builder.setMessage(com.google.android.gms.common.internal.zac.c(activity, 18));
        builder.setPositiveButton("", (DialogInterface.OnClickListener) null);
        AlertDialog create = builder.create();
        w(activity, create, "GooglePlayServicesUpdatingDialog", onCancelListener);
        return create;
    }

    public final zabx v(Context context, zabw zabwVar) {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        zabx zabxVar = new zabx(zabwVar);
        zao.n(context, zabxVar, intentFilter);
        zabxVar.a(context);
        if (l(context, "com.google.android.gms")) {
            return zabxVar;
        }
        zabwVar.a();
        zabxVar.b();
        return null;
    }

    final void w(Activity activity, Dialog dialog, String str, DialogInterface.OnCancelListener onCancelListener) {
        try {
            if (activity instanceof FragmentActivity) {
                SupportErrorDialogFragment.r2(dialog, onCancelListener).q2(((FragmentActivity) activity).V(), str);
                return;
            }
        } catch (NoClassDefFoundError unused) {
        }
        ErrorDialogFragment.a(dialog, onCancelListener).show(activity.getFragmentManager(), str);
    }

    final void x(Context context, int i2, String str, PendingIntent pendingIntent) {
        int i3;
        String str2;
        Log.w("GoogleApiAvailability", String.format("GMS core API Availability. ConnectionResult=%s, tag=%s", Integer.valueOf(i2), null), new IllegalArgumentException());
        if (i2 == 18) {
            y(context);
            return;
        }
        if (pendingIntent == null) {
            if (i2 == 6) {
                Log.w("GoogleApiAvailability", "Missing resolution for ConnectionResult.RESOLUTION_REQUIRED. Call GoogleApiAvailability#showErrorNotification(Context, ConnectionResult) instead.");
                return;
            }
            return;
        }
        String e2 = com.google.android.gms.common.internal.zac.e(context, i2);
        String d2 = com.google.android.gms.common.internal.zac.d(context, i2);
        Resources resources = context.getResources();
        NotificationManager notificationManager = (NotificationManager) Preconditions.i(context.getSystemService("notification"));
        NotificationCompat.Builder n2 = new NotificationCompat.Builder(context).k(true).e(true).i(e2).n(new NotificationCompat.BigTextStyle().h(d2));
        if (DeviceProperties.b(context)) {
            Preconditions.l(PlatformVersion.c());
            n2.m(context.getApplicationInfo().icon).l(2);
            if (DeviceProperties.c(context)) {
                n2.a(com.google.android.gms.base.R.drawable.common_full_open_on_phone, resources.getString(com.google.android.gms.base.R.string.common_open_on_phone), pendingIntent);
            } else {
                n2.g(pendingIntent);
            }
        } else {
            n2.m(android.R.drawable.stat_sys_warning).o(resources.getString(com.google.android.gms.base.R.string.common_google_play_services_notification_ticker)).p(System.currentTimeMillis()).g(pendingIntent).h(d2);
        }
        if (PlatformVersion.f()) {
            Preconditions.l(PlatformVersion.f());
            synchronized (f10499e) {
                str2 = this.f10501c;
            }
            if (str2 == null) {
                str2 = "com.google.android.gms.availability";
                NotificationChannel notificationChannel = notificationManager.getNotificationChannel("com.google.android.gms.availability");
                String string = context.getResources().getString(com.google.android.gms.base.R.string.common_google_play_services_notification_channel_name);
                if (notificationChannel == null) {
                    notificationManager.createNotificationChannel(new NotificationChannel("com.google.android.gms.availability", string, 4));
                } else if (!string.contentEquals(notificationChannel.getName())) {
                    notificationChannel.setName(string);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
            }
            n2.f(str2);
        }
        Notification b2 = n2.b();
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            GooglePlayServicesUtilLight.f10506b.set(false);
            i3 = 10436;
        } else {
            i3 = 39789;
        }
        notificationManager.notify(i3, b2);
    }

    final void y(Context context) {
        new zad(this, context).sendEmptyMessageDelayed(1, 120000L);
    }

    public final boolean z(Activity activity, LifecycleFragment lifecycleFragment, int i2, int i3, DialogInterface.OnCancelListener onCancelListener) {
        Dialog t = t(activity, i2, zag.c(lifecycleFragment, d(activity, i2, DistBusKeys.KEY_WIFI_DBDC), 2), onCancelListener, null);
        if (t == null) {
            return false;
        }
        w(activity, t, "GooglePlayServicesErrorDialog", onCancelListener);
        return true;
    }
}
