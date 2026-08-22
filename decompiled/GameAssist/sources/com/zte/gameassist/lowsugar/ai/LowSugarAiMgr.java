package com.zte.gameassist.lowsugar.ai;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.provider.Settings;
import android.text.TextUtils;
import com.zte.aispeaker.sentryMode.ISentryModeInterface;
import com.zte.gameassist.aiagent.R;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.lowsugar.LowSugarApplication;
import com.zte.gameassist.lowsugar.ai.LowSugarAiMgr;
import com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel;
import com.zte.gameassist.lowsugar.ai.ocr.OcrModelFactory;
import com.zte.gameassist.lowsugar.common.Constants;
import com.zte.gameassist.lowsugar.common.DetectParam;
import com.zte.gameassist.lowsugar.detect.scene.GameBaseScene;
import com.zte.gameassist.lowsugar.detect.scene.GameSceneFactory;
import com.zte.gameassist.lowsugar.provider.LowSugarColumn;
import com.zte.gameassist.lowsugar.receiver.AccountChangeReceiver;
import com.zte.gameassist.lowsugar.ui.LowSugarWindowManager;
import com.zte.gameassist.lowsugar.utils.ImageDeduplicationUtil;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes2.dex */
public class LowSugarAiMgr {
    private static volatile LowSugarAiMgr A;

    /* renamed from: a, reason: collision with root package name */
    private final Context f16717a;

    /* renamed from: b, reason: collision with root package name */
    private int f16718b;

    /* renamed from: c, reason: collision with root package name */
    private HandlerThread f16719c;

    /* renamed from: f, reason: collision with root package name */
    private LowSugarPurposeData f16722f;

    /* renamed from: g, reason: collision with root package name */
    private Handler f16723g;

    /* renamed from: j, reason: collision with root package name */
    private AlarmManager f16726j;

    /* renamed from: k, reason: collision with root package name */
    private int f16727k;

    /* renamed from: l, reason: collision with root package name */
    private BroadcastReceiver f16728l;

    /* renamed from: m, reason: collision with root package name */
    private ISentryModeInterface f16729m;

    /* renamed from: n, reason: collision with root package name */
    private BaseOcrModel f16730n;

    /* renamed from: o, reason: collision with root package name */
    private NotificationManager f16731o;

    /* renamed from: d, reason: collision with root package name */
    private ArrayList f16720d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    private ArrayList f16721e = new ArrayList();

    /* renamed from: h, reason: collision with root package name */
    private boolean f16724h = false;

    /* renamed from: i, reason: collision with root package name */
    public Handler f16725i = new Handler(Looper.getMainLooper());

    /* renamed from: p, reason: collision with root package name */
    private boolean f16732p = false;

    /* renamed from: q, reason: collision with root package name */
    private boolean f16733q = false;

    /* renamed from: r, reason: collision with root package name */
    private final ServiceConnection f16734r = new ServiceConnection() { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            LowSugarAiMgr.this.f16729m = ISentryModeInterface.Stub.asInterface(iBinder);
            GaLog.a("LowSugarAiMgr", "mISentryModeService Connected mISentryModeService = " + LowSugarAiMgr.this.f16729m);
            try {
                GaLog.a("LowSugarAiMgr", "mISentryModeService Connected add = " + LowSugarAiMgr.this.f16729m.add(5, 6));
            } catch (RemoteException e2) {
                GaLog.b("LowSugarAiMgr", "mISentryModeService onServiceConnected has exception and e= " + e2.toString());
            }
            LowSugarAiMgr.this.f16732p = true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GaLog.a("LowSugarAiMgr", "mISentryModeService Disconnected");
            LowSugarAiMgr.this.f16729m = null;
            LowSugarAiMgr.this.f16732p = false;
        }
    };

    /* renamed from: s, reason: collision with root package name */
    private ContentObserver f16735s = new AnonymousClass2(this.f16725i);
    private ContentObserver t = new ContentObserver(this.f16725i) { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.3
        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            GaLog.b("LowSugarAiMgr", "mLowSugarDbObserver onChange uri = " + uri.toString());
            if (LowSugarColumn.f16922a.equals(uri)) {
                LowSugarAiMgr.this.V();
            }
        }
    };
    private AlarmManager.OnAlarmListener u = new AlarmManager.OnAlarmListener() { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.4
        @Override // android.app.AlarmManager.OnAlarmListener
        public void onAlarm() {
            if (Settings.Global.getInt(LowSugarAiMgr.this.f16717a.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) != 1) {
                GaLog.b("LowSugarAiMgr", "purposeAlarmListener lowSugar disable and not alarm!");
                LowSugarAiMgr.this.B();
                return;
            }
            long j2 = LowSugarAiMgr.this.f16717a.getSharedPreferences("low_sugar", 0).getLong("low_sugar_alarm_purpose_id", -1L);
            GaLog.b("LowSugarAiMgr", "purposeAlarmListener lowSugarAlarmId = " + j2);
            if (j2 != -1) {
                Cursor query = LowSugarAiMgr.this.f16717a.getContentResolver().query(LowSugarColumn.f16922a, null, "_id=?", new String[]{Long.toString(j2)}, null);
                GaLog.b("LowSugarAiMgr", "setLowSugarAlarm cursor = " + query);
                if (query != null) {
                    try {
                        try {
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        if (query.getCount() != 0) {
                            query.moveToFirst();
                            String string = query.getString(query.getColumnIndex("title"));
                            String string2 = query.getString(query.getColumnIndex("package"));
                            long j3 = query.getLong(query.getColumnIndex("_id"));
                            long j4 = query.getLong(query.getColumnIndex("alarm_time"));
                            GaLog.b("LowSugarAiMgr", "purposeAlarmListener title = " + string + ", pkgName = " + string2 + ", alarmTime = " + j4);
                            if (System.currentTimeMillis() > 10000 + j4) {
                                GaLog.b("LowSugarAiMgr", "purposeAlarmListener alarm time has timeout!");
                            } else if (System.currentTimeMillis() < j4) {
                                GaLog.b("LowSugarAiMgr", "purposeAlarmListener alarm time not arrived!");
                            } else if (!SystemMgr.H()) {
                                LowSugarAiMgr.this.z(j3, string, string2);
                            } else if (SystemMgr.t().equals(string2)) {
                                LowSugarUtils.v(string, LowSugarAiMgr.this.f16717a);
                            } else {
                                LowSugarUtils.w(string, LowSugarAiMgr.this.f16717a, string2);
                            }
                            query.close();
                        }
                    } catch (Throwable th) {
                        query.close();
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                    return;
                }
                return;
            }
            LowSugarAiMgr.this.V();
            LowSugarUtils.z(LowSugarAiMgr.this.f16717a, "broadcast");
        }
    };
    private ContentObserver v = new ContentObserver(null) { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.5
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            boolean z2 = Settings.Global.getInt(LowSugarAiMgr.this.f16717a.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1;
            GaLog.b("LowSugarAiMgr", "mLowSugarEnableContentObserver onChange enable = " + z2);
            if (z2) {
                LowSugarAiMgr.this.x();
            } else {
                LowSugarAiMgr.this.w();
            }
        }
    };
    private AccountChangeReceiver.AccountChangeCallback w = new AccountChangeReceiver.AccountChangeCallback() { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.6
        @Override // com.zte.gameassist.lowsugar.receiver.AccountChangeReceiver.AccountChangeCallback
        public void a(Context context, String str) {
            GaLog.a("LowSugarAiMgr", "doAccountChange: start extraChange = " + str);
            if ("logout".equals(str)) {
                Settings.Global.putInt(LowSugarAiMgr.this.f16717a.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0);
            } else if ("login".equals(str)) {
                GaLog.a("LowSugarAiMgr", "doAccountChange: start mLogining = " + LowSugarAiMgr.this.f16727k);
                if (LowSugarAiMgr.this.f16727k != 0) {
                    Settings.Global.putInt(LowSugarAiMgr.this.f16717a.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 1);
                    if (LowSugarAiMgr.this.f16727k == 2 && LowSugarUtils.v.contains(SystemMgr.v())) {
                        GaLog.a("LowSugarAiMgr", "mAccountChangeCallback doAccountChange manual should toast!");
                        LowSugarUtils.v(LowSugarAiMgr.this.f16717a.getString(R.string.aiagent_turn_on_function, LowSugarAiMgr.this.f16717a.getString(com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar)), LowSugarAiMgr.this.f16717a);
                    }
                    LowSugarAiMgr.this.f16727k = 0;
                    LowSugarAiMgr lowSugarAiMgr = LowSugarAiMgr.this;
                    lowSugarAiMgr.f16725i.removeCallbacks(lowSugarAiMgr.z);
                }
            }
            GaLog.a("LowSugarAiMgr", "doAccountChange: end");
        }
    };
    private BaseOcrModel.OcrResultCallback x = new AnonymousClass7();
    private Runnable y = new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.8
        @Override // java.lang.Runnable
        public void run() {
            GaLog.a("LowSugarAiMgr", "quest ai over 20 second should end");
            LowSugarAiMgr.this.D(false);
        }
    };
    private Runnable z = new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.9
        @Override // java.lang.Runnable
        public void run() {
            GaLog.a("LowSugarAiMgr", "mLoginingRunnable run");
            LowSugarAiMgr.this.f16727k = 0;
        }
    };

    /* renamed from: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr$2, reason: invalid class name */
    class AnonymousClass2 extends ContentObserver {
        AnonymousClass2(Handler handler) {
            super(handler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b() {
            LowSugarAiMgr lowSugarAiMgr = LowSugarAiMgr.this;
            lowSugarAiMgr.S(lowSugarAiMgr.f16722f);
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z, Uri uri) {
            GaLog.b("LowSugarAiMgr", "mAiDataObserver onChange uri = " + uri.toString());
            if (LowSugarUtils.f17021p.equals(uri)) {
                LowSugarAiMgr.this.f16723g.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.b
                    @Override // java.lang.Runnable
                    public final void run() {
                        LowSugarAiMgr.AnonymousClass2.this.b();
                    }
                });
            }
        }
    }

    /* renamed from: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr$7, reason: invalid class name */
    class AnonymousClass7 implements BaseOcrModel.OcrResultCallback {
        AnonymousClass7() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c() {
            GaLog.b("LowSugarAiMgr", "callAiEngineOcrGet mLowSugarAiWorkHandler post mAiPurposeDataList = " + LowSugarAiMgr.this.f16721e);
            if (LowSugarAiMgr.this.f16721e.isEmpty()) {
                return;
            }
            LowSugarAiMgr.this.X();
            LowSugarAiMgr lowSugarAiMgr = LowSugarAiMgr.this;
            lowSugarAiMgr.S((LowSugarPurposeData) lowSugarAiMgr.f16721e.remove(0));
        }

        @Override // com.zte.gameassist.lowsugar.ai.ocr.BaseOcrModel.OcrResultCallback
        public void a(boolean z) {
            if (LowSugarAiMgr.this.f16730n == null) {
                GaLog.k("LowSugarAiMgr", "onOcrResultCallback mOcrModel == null");
                return;
            }
            if (z && !TextUtils.isEmpty(LowSugarAiMgr.this.f16730n.b().f16757f)) {
                LowSugarAiMgr.this.f16721e.add(LowSugarAiMgr.this.f16730n.b());
                GaLog.b("LowSugarAiMgr", "callAiEngineOcrGet mAiPurposeDataList" + LowSugarAiMgr.this.f16721e);
                GaLog.b("LowSugarAiMgr", "callAiEngineOcrGet mIsAiWorking" + LowSugarAiMgr.this.f16724h);
                if (!LowSugarAiMgr.this.f16724h && !LowSugarAiMgr.this.f16721e.isEmpty()) {
                    LowSugarAiMgr.this.f16723g.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.c
                        @Override // java.lang.Runnable
                        public final void run() {
                            LowSugarAiMgr.AnonymousClass7.this.c();
                        }
                    });
                }
            }
            if (LowSugarAiMgr.this.f16720d.isEmpty()) {
                return;
            }
            LowSugarAiMgr.this.f16730n.i((LowSugarPurposeData) LowSugarAiMgr.this.f16720d.remove(0));
        }
    }

    private LowSugarAiMgr(Context context) {
        GaLog.b("LowSugarAiMgr", "LowSugarAiMgr");
        this.f16717a = context;
        this.f16731o = (NotificationManager) context.getSystemService("notification");
        HandlerThread handlerThread = new HandlerThread("LowSugarAi", -2);
        this.f16719c = handlerThread;
        handlerThread.start();
        this.f16723g = new Handler(this.f16719c.getLooper());
        this.f16726j = (AlarmManager) context.getSystemService("alarm");
        context.getContentResolver().registerContentObserver(Constants.f16792a, true, this.v);
        context.getContentResolver().registerContentObserver(LowSugarColumn.f16922a, true, this.t);
        boolean z = Settings.Global.getInt(context.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1;
        GaLog.b("LowSugarAiMgr", "mLowSugarEnableContentObserver onChange enable = " + z);
        if (z) {
            x();
        }
    }

    private void A(long j2) {
        this.f16731o.cancel((int) j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        AlarmManager alarmManager = this.f16726j;
        if (alarmManager != null) {
            alarmManager.cancel(this.u);
            GaLog.e("LowSugarAiMgr", "cancelPurposeAlarm");
        }
    }

    private NotificationChannel C() {
        NotificationChannel notificationChannel = new NotificationChannel("low_sugar_plan_notify", "low_sugar_plan_notify_ticker", 4);
        notificationChannel.setSound(null, null);
        notificationChannel.enableVibration(false);
        notificationChannel.setImportance(4);
        this.f16731o.createNotificationChannel(notificationChannel);
        return notificationChannel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(boolean z) {
        LowSugarPurposeData lowSugarPurposeData;
        GaLog.e("LowSugarAiMgr", "endAiSpeaker isSuccess = " + z);
        a0();
        if (!z && (lowSugarPurposeData = this.f16722f) != null && lowSugarPurposeData.b() && !this.f16722f.a()) {
            GaLog.e("LowSugarAiMgr", "manual add purpose failed or has no purpose!");
            LowSugarUtils.v(this.f16717a.getString(com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar_manual_no_purpose), this.f16717a);
        }
        this.f16722f = null;
        this.f16724h = false;
        this.f16718b = 0;
        this.f16725i.removeCallbacks(this.y);
    }

    private void E() {
        if (this.f16730n == null) {
            String language = Locale.getDefault().getLanguage();
            GaLog.e("LowSugarAiMgr", "requiredParseGameTask language = " + language);
            BaseOcrModel a2 = OcrModelFactory.a(this.f16717a, language);
            this.f16730n = a2;
            a2.g(this.x);
        }
    }

    public static LowSugarAiMgr F() {
        if (A == null) {
            synchronized (LowSugarAiMgr.class) {
                try {
                    if (A == null) {
                        A = new LowSugarAiMgr(LowSugarApplication.c().b());
                    }
                } finally {
                }
            }
        }
        return A;
    }

    private void G(Intent intent) {
        long longExtra = intent.getLongExtra("lowsugar_id", -1L);
        intent.getStringExtra("lowsugar_package");
        if (longExtra <= 0) {
            return;
        }
        A(longExtra);
    }

    private void I(Intent intent) {
        long longExtra = intent.getLongExtra("lowsugar_id", -1L);
        intent.getStringExtra("lowsugar_package");
        if (longExtra <= 0) {
            return;
        }
        this.f16717a.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        String stringExtra = intent.getStringExtra("lowsugar_package");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                this.f16717a.startActivity(this.f16717a.getPackageManager().getLaunchIntentForPackage(stringExtra));
            } catch (Exception e2) {
                GaLog.a("LowSugarAiMgr", "handleOpenGame startActivity and has exception = " + e2);
            }
        }
        A(longExtra);
    }

    private boolean K(String str, Long l2, String str2) {
        long longValue;
        long j2;
        Uri uri;
        Cursor query;
        GaLog.b("LowSugarAiMgr", "insertAiResultToDB aiResultName = " + str);
        GaLog.b("LowSugarAiMgr", "insertAiResultToDB aiResultTime = " + l2);
        GaLog.b("LowSugarAiMgr", "insertAiResultToDB aiResultDesc = " + str2);
        if (LowSugarUtils.d(this.f16722f, this.f16717a, null)) {
            GaLog.b("LowSugarAiMgr", "insertAiResultToDB has same task and effectMode = " + this.f16722f.f16752a);
            LowSugarPurposeData lowSugarPurposeData = this.f16722f;
            lowSugarPurposeData.f16760i = true;
            if (lowSugarPurposeData.b()) {
                LowSugarUtils.v(this.f16717a.getString(this.f16722f.c() ? com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar_manual_no_purpose : com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar_manual_repeat_purpose), this.f16717a);
            }
            return false;
        }
        long longValue2 = l2.longValue() - System.currentTimeMillis();
        GaLog.a("LowSugarAiMgr", "insertAiResultToDB remainingTime=" + longValue2);
        long j3 = 3600000;
        if (longValue2 <= 3600000) {
            GaLog.a("LowSugarAiMgr", "insertAiResultToDB less 1 hour not insert purpose");
            return false;
        }
        if (longValue2 > 259200000) {
            GaLog.a("LowSugarAiMgr", "insertAiResultToDB Over 3 days set 24 hour to notify");
            j2 = l2.longValue() - 86400000;
        } else {
            if (longValue2 < 86400000) {
                GaLog.a("LowSugarAiMgr", "insertAiResultToDB less one days set 1 hour to notify");
                longValue = l2.longValue();
            } else {
                GaLog.a("LowSugarAiMgr", "insertAiResultToDB 1-3 days set 12 hour to notify");
                longValue = l2.longValue();
                j3 = 43200000;
            }
            j2 = longValue - j3;
        }
        while (true) {
            ContentResolver contentResolver = this.f16717a.getContentResolver();
            uri = LowSugarColumn.f16922a;
            query = contentResolver.query(uri, new String[]{"_id"}, "alarm_time=?", new String[]{Long.toString(j2)}, null);
            if (query == null) {
                break;
            }
            try {
                if (query.getCount() == 0) {
                    break;
                }
                j2 += 10000;
                GaLog.a("LowSugarAiMgr", "insertAiResultToDB found duplicate alarmTime, adjusted to " + j2);
                query.close();
            } finally {
                query.close();
            }
        }
        if (query != null) {
        }
        String str3 = this.f16722f.f16754c;
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", l2);
        contentValues.put("alarm_time", Long.valueOf(j2));
        contentValues.put("package", str3);
        contentValues.put("content", str2);
        contentValues.put("ocr_bitmap_dhash", this.f16722f.f16762k);
        int update = this.f16717a.getContentResolver().update(uri, contentValues, "title=?", new String[]{str});
        GaLog.a("LowSugarAiMgr", "insertAiResultToDB update rowsUpdated=" + update);
        if (update == 0) {
            contentValues.put("title", str);
            Uri insert = this.f16717a.getContentResolver().insert(uri, contentValues);
            GaLog.a("LowSugarAiMgr", "insertAiResultToDB insert success=" + insert);
            if (insert != null) {
                if (this.f16722f.b()) {
                    GaLog.a("LowSugarAiMgr", "insertAiResultToDB add manual purpose success!");
                    LowSugarUtils.v(this.f16717a.getString(com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar_manual_success, str, LowSugarUtils.i(j2) + " " + LowSugarUtils.m(j2)), this.f16717a);
                }
                LowSugarUtils.z(this.f16717a, this.f16722f.b() ? "manual" : "auto");
            }
        } else if (this.f16722f.b()) {
            GaLog.a("LowSugarAiMgr", "insertAiResultToDB update success manual so toast task has been added!");
            LowSugarUtils.v(this.f16717a.getString(com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar_manual_repeat_purpose), this.f16717a);
        }
        V();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void L() {
        if (this.f16721e.isEmpty()) {
            return;
        }
        X();
        S((LowSugarPurposeData) this.f16721e.remove(0));
    }

    private void P() {
        try {
            ContentResolver contentResolver = this.f16717a.getContentResolver();
            if (contentResolver == null) {
                GaLog.b("LowSugarAiMgr", "registerAIEngineOcrObserver ContentResolver is null");
            } else {
                contentResolver.registerContentObserver(LowSugarUtils.f17021p, true, this.f16735s);
                this.f16733q = true;
            }
        } catch (Exception e2) {
            GaLog.c("LowSugarAiMgr", "registerAIEngineOcrObserver error", e2);
        }
    }

    private void Q() {
        if (this.f16728l == null) {
            this.f16728l = new BroadcastReceiver() { // from class: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.10
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    GaLog.a("LowSugarAiMgr", "registerTimeChange action = " + intent.getAction());
                    if ("android.intent.action.TIMEZONE_CHANGED".equals(intent.getAction()) || "android.intent.action.TIME_SET".equals(intent.getAction())) {
                        LowSugarAiMgr.this.V();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.TIME_SET");
            intentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
            this.f16717a.registerReceiver(this.f16728l, intentFilter, 2);
        }
    }

    private void R() {
        if (this.f16730n != null) {
            GaLog.e("LowSugarAiMgr", "releaseOcrModel");
            this.f16730n.f();
            this.f16730n.g(null);
        }
        this.f16730n = null;
        GaLog.e("LowSugarAiMgr", "releaseOcrModel mOcrPurposeDataList = " + this.f16720d);
        if (!this.f16720d.isEmpty()) {
            Iterator it = this.f16720d.iterator();
            while (it.hasNext()) {
                LowSugarPurposeData lowSugarPurposeData = (LowSugarPurposeData) it.next();
                Bitmap bitmap = lowSugarPurposeData.f16755d;
                if (bitmap != null && !bitmap.isRecycled()) {
                    lowSugarPurposeData.f16755d.recycle();
                    lowSugarPurposeData.f16755d = null;
                }
            }
            this.f16720d.clear();
        }
        GaLog.e("LowSugarAiMgr", "releaseOcrModel mAiPurposeDataList = " + this.f16721e);
        if (!this.f16721e.isEmpty()) {
            Iterator it2 = this.f16721e.iterator();
            while (it2.hasNext()) {
                LowSugarPurposeData lowSugarPurposeData2 = (LowSugarPurposeData) it2.next();
                Bitmap bitmap2 = lowSugarPurposeData2.f16755d;
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    lowSugarPurposeData2.f16755d.recycle();
                    lowSugarPurposeData2.f16755d = null;
                }
            }
            this.f16721e.clear();
        }
        D(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S(LowSugarPurposeData lowSugarPurposeData) {
        String str;
        long j2;
        if (lowSugarPurposeData == null) {
            GaLog.e("LowSugarAiMgr", "requestOcrInfoData now not has ai purpose data to handler!");
            return;
        }
        this.f16722f = lowSugarPurposeData;
        Bundle bundle = new Bundle();
        bundle.putString("sentryMode_ai", this.f16722f.f16757f);
        bundle.putString("sentryMode_ai_task_belongs", GameSceneFactory.a(this.f16717a, this.f16722f.f16754c).c(this.f16722f.f16753b));
        Bundle s2 = LowSugarUtils.s("sentryMode_ai", bundle, this.f16717a);
        if (s2 != null) {
            str = s2.getString("sentryMode_ai_name");
            j2 = s2.getLong("sentryMode_ai_time");
        } else {
            str = null;
            j2 = 0;
        }
        GaLog.e("LowSugarAiMgr", "requestOcrInfoData aiResultName = " + str + " aiResultTime = " + j2);
        if (TextUtils.isEmpty(str)) {
            this.f16718b++;
            GaLog.b("LowSugarAiMgr", "requestOcrInfoData mAiRetryCounts = " + this.f16718b);
            if (this.f16718b > 3) {
                GaLog.b("LowSugarAiMgr", "requestOcrInfoData mAiRetryCounts over MAX_AI_RETRY_COUNTS 3");
                D(false);
                return;
            }
            return;
        }
        if ("parse error".equals(str) || "task done".equals(str) || LowSugarUtils.f17020o.equals(str)) {
            D(false);
        } else {
            if (j2 == -1 || j2 == 0) {
                j2 = LowSugarUtils.j(this.f16722f.f16757f);
            }
            if (j2 == -1 || j2 == 0) {
                D(false);
            } else {
                GaLog.e("LowSugarAiMgr", "requestOcrInfoData mCurrAiPurposeData.mDeadLineTime = " + this.f16722f.f16759h);
                long j3 = this.f16722f.f16759h;
                if (j3 != 0) {
                    j2 = j3;
                }
                D(K(str, Long.valueOf(j2), this.f16722f.f16757f));
            }
        }
        GaLog.b("LowSugarAiMgr", "requestOcrInfoData mAiPurposeDataList = " + this.f16721e);
        if (this.f16721e.isEmpty()) {
            return;
        }
        this.f16723g.post(new Runnable() { // from class: com.zte.gameassist.lowsugar.ai.a
            @Override // java.lang.Runnable
            public final void run() {
                LowSugarAiMgr.this.L();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00d9, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x00dc, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void V() {
        /*
            r9 = this;
            java.util.Locale r0 = java.util.Locale.getDefault()
            java.lang.String r0 = r0.getLanguage()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "setLowSugarAlarm language = "
            r1.append(r2)
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "LowSugarAiMgr"
            com.zte.gameassist.utils.GaLog.e(r2, r1)
            java.util.List r1 = com.zte.gameassist.lowsugar.utils.LowSugarUtils.E
            boolean r1 = r1.contains(r0)
            if (r1 != 0) goto L43
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "setLowSugarAlarm not support lowsugar in "
            r1.append(r3)
            r1.append(r0)
            java.lang.String r0 = " and cancel alarm!"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.zte.gameassist.utils.GaLog.b(r2, r0)
            r9.B()
            return
        L43:
            android.content.Context r0 = r9.f16717a
            android.content.ContentResolver r3 = r0.getContentResolver()
            android.net.Uri r4 = com.zte.gameassist.lowsugar.provider.LowSugarColumn.f16922a
            java.lang.String r0 = "alarm_time"
            java.lang.String r1 = "_id"
            java.lang.String[] r5 = new java.lang.String[]{r0, r1}
            long r6 = java.lang.System.currentTimeMillis()
            java.lang.String r6 = java.lang.Long.toString(r6)
            java.lang.String[] r7 = new java.lang.String[]{r6}
            java.lang.String r8 = "time asc, _id asc"
            java.lang.String r6 = "alarm_time>? AND app_exist==1"
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "setLowSugarAlarm cursor = "
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            com.zte.gameassist.utils.GaLog.b(r2, r4)
            r9.B()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            if (r3 == 0) goto Ld7
            int r4 = r3.getCount()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            if (r4 != 0) goto L87
            goto Ld7
        L87:
            r3.moveToFirst()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            int r0 = r3.getColumnIndex(r0)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            long r4 = r3.getLong(r0)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            int r0 = r3.getColumnIndex(r1)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            long r0 = r3.getLong(r0)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            r6.<init>()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            java.lang.String r7 = "setLowSugarAlarm time = "
            r6.append(r7)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            r6.append(r4)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            java.lang.String r7 = ", id = "
            r6.append(r7)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            r6.append(r0)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            com.zte.gameassist.utils.GaLog.a(r2, r6)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            r9.W(r4)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            android.content.Context r9 = r9.f16717a     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            java.lang.String r2 = "low_sugar"
            r4 = 0
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r2, r4)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            android.content.SharedPreferences$Editor r9 = r9.edit()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            java.lang.String r2 = "low_sugar_alarm_purpose_id"
            android.content.SharedPreferences$Editor r9 = r9.putLong(r2, r0)     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
            r9.apply()     // Catch: java.lang.Throwable -> Ld3 java.lang.Exception -> Ld5
        Lcf:
            r3.close()
            goto Le3
        Ld3:
            r9 = move-exception
            goto Le4
        Ld5:
            r9 = move-exception
            goto Ldd
        Ld7:
            if (r3 == 0) goto Ldc
            r3.close()
        Ldc:
            return
        Ldd:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> Ld3
            if (r3 == 0) goto Le3
            goto Lcf
        Le3:
            return
        Le4:
            if (r3 == 0) goto Le9
            r3.close()
        Le9:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.lowsugar.ai.LowSugarAiMgr.V():void");
    }

    private void W(long j2) {
        GaLog.a("LowSugarAiMgr", "setPurposeAlarm time = " + j2);
        GaLog.a("LowSugarAiMgr", "setPurposeAlarm remain = " + (j2 - System.currentTimeMillis()));
        AlarmManager alarmManager = this.f16726j;
        if (alarmManager != null) {
            try {
                alarmManager.setExact(0, j2, "low_sugar_alarm", this.u, this.f16725i);
            } catch (Exception e2) {
                e2.printStackTrace();
                GaLog.e("LowSugarAiMgr", "setPurposeAlarm error = " + e2.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        P();
        this.f16724h = true;
        this.f16725i.postDelayed(this.y, 20000L);
    }

    private void Y(LowSugarPurposeData lowSugarPurposeData) {
        GaLog.b("LowSugarAiMgr", "startOcrRequest isOcrWorking = " + this.f16730n.c());
        GaLog.b("LowSugarAiMgr", "startOcrRequest mOcrPurposeDataList = " + this.f16720d);
        if (!this.f16730n.c()) {
            this.f16730n.i(lowSugarPurposeData);
        } else if (lowSugarPurposeData.b()) {
            this.f16720d.add(0, lowSugarPurposeData);
        } else {
            this.f16720d.add(lowSugarPurposeData);
        }
    }

    private void a0() {
        if (this.f16733q) {
            try {
                this.f16717a.getContentResolver().unregisterContentObserver(this.f16735s);
                this.f16733q = false;
            } catch (Exception e2) {
                GaLog.c("LowSugarAiMgr", "unregisterAiSpeakerObserver error", e2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        GaLog.b("LowSugarAiMgr", "LowSugarDisable");
        B();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x() {
        GaLog.b("LowSugarAiMgr", "LowSugarEnable");
        LowSugarUtils.q(this.f16717a);
        V();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z(long j2, String str, String str2) {
        String charSequence;
        Intent intent = new Intent("com.zte.gameassist.lowsugar.OPERATION");
        intent.putExtra("lowsugar_action", "opengame");
        intent.putExtra("lowsugar_package", str2);
        intent.putExtra("lowsugar_id", j2);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.f16717a, UUID.randomUUID().hashCode(), intent, 201326592);
        Intent intent2 = new Intent("com.zte.gameassist.lowsugar.OPERATION");
        intent2.putExtra("lowsugar_action", "close");
        intent2.putExtra("lowsugar_package", str2);
        intent2.putExtra("lowsugar_id", j2);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(this.f16717a, UUID.randomUUID().hashCode(), intent2, 201326592);
        int i2 = com.zte.gameassist.lowsugar.R.drawable.low_sugar_zte_notification_icon;
        if (ZteFeature.isRedMagicProduct()) {
            i2 = com.zte.gameassist.lowsugar.R.drawable.low_sugar_notification_icon;
        }
        int i3 = i2;
        PackageManager packageManager = this.f16717a.getPackageManager();
        if (!TextUtils.isEmpty(str2)) {
            try {
                charSequence = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str2, 0)).toString();
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
            }
            Notification.Action build = new Notification.Action.Builder(i3, charSequence, broadcast).build();
            Notification.Action build2 = new Notification.Action.Builder(i3, this.f16717a.getResources().getString(com.zte.gameassist.lowsugar.R.string.low_sugar_notification_close), broadcast2).build();
            Intent intent3 = new Intent("com.zte.gameassist.lowsugar.OPERATION");
            intent3.putExtra("lowsugar_action", "content");
            intent3.putExtra("lowsugar_package", str2);
            intent3.putExtra("lowsugar_id", j2);
            PendingIntent broadcast3 = PendingIntent.getBroadcast(this.f16717a, UUID.randomUUID().hashCode(), intent3, 201326592);
            Notification.Builder category = new Notification.Builder(this.f16717a).setContentTitle(str).addAction(build).addAction(build2).setContentText(charSequence).setCategory("alarm");
            category.setPriority(2);
            category.setDefaults(7);
            category.setSmallIcon(i3);
            category.setContentIntent(broadcast3);
            category.setPriority(0);
            Bundle bundle = new Bundle();
            GaLog.e("LowSugarAiMgr", "appName: " + charSequence);
            bundle.putString("android.substName", this.f16717a.getString(com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar));
            bundle.putBoolean("use_custom_icon", true);
            bundle.putBoolean("keep_in_lockscreen", true);
            bundle.putParcelableArray("ticker_action", new Notification.Action[]{build, build2});
            category.setExtras(bundle);
            category.setChannelId(C().getId());
            this.f16731o.notify((int) j2, category.build());
        }
        charSequence = "";
        Notification.Action build3 = new Notification.Action.Builder(i3, charSequence, broadcast).build();
        Notification.Action build22 = new Notification.Action.Builder(i3, this.f16717a.getResources().getString(com.zte.gameassist.lowsugar.R.string.low_sugar_notification_close), broadcast2).build();
        Intent intent32 = new Intent("com.zte.gameassist.lowsugar.OPERATION");
        intent32.putExtra("lowsugar_action", "content");
        intent32.putExtra("lowsugar_package", str2);
        intent32.putExtra("lowsugar_id", j2);
        PendingIntent broadcast32 = PendingIntent.getBroadcast(this.f16717a, UUID.randomUUID().hashCode(), intent32, 201326592);
        Notification.Builder category2 = new Notification.Builder(this.f16717a).setContentTitle(str).addAction(build3).addAction(build22).setContentText(charSequence).setCategory("alarm");
        category2.setPriority(2);
        category2.setDefaults(7);
        category2.setSmallIcon(i3);
        category2.setContentIntent(broadcast32);
        category2.setPriority(0);
        Bundle bundle2 = new Bundle();
        GaLog.e("LowSugarAiMgr", "appName: " + charSequence);
        bundle2.putString("android.substName", this.f16717a.getString(com.zte.gameassist.lowsugar.R.string.ic_qs_low_sugar));
        bundle2.putBoolean("use_custom_icon", true);
        bundle2.putBoolean("keep_in_lockscreen", true);
        bundle2.putParcelableArray("ticker_action", new Notification.Action[]{build3, build22});
        category2.setExtras(bundle2);
        category2.setChannelId(C().getId());
        this.f16731o.notify((int) j2, category2.build());
    }

    public void H(Intent intent) {
        String stringExtra = intent.getStringExtra("lowsugar_action");
        GaLog.e("LowSugarAiMgr", "handleNotiOperation action = " + stringExtra);
        if ("opengame".equals(stringExtra)) {
            I(intent);
        } else if ("close".equals(stringExtra)) {
            G(intent);
        } else if ("content".equals(stringExtra)) {
            I(intent);
        }
    }

    public void J() {
        GaLog.a("LowSugarAiMgr", "init");
        LowSugarApplication.c().a(this.w);
        Q();
    }

    public void M() {
        String t = SystemMgr.t();
        GaLog.a("LowSugarAiMgr", "onGameStart currPkgName = " + t);
        if (LowSugarUtils.o(t) && Settings.Global.getInt(this.f16717a.getContentResolver(), "nubia_low_sugar_gameplay_pkg_open", 0) == 1) {
            GaLog.a("LowSugarAiMgr", "onGameStart send bind service to init service before use!");
            if (!LowSugarUtils.G) {
                LowSugarUtils.q(this.f16717a);
            }
            y();
        }
    }

    public void N() {
        GaLog.a("LowSugarAiMgr", "onGameStop currPkgName = " + SystemMgr.t());
        Z();
        R();
    }

    public void O(String str) {
        GaLog.e("LowSugarAiMgr", "onLanguageChanged: language = " + str);
        V();
    }

    public boolean T(int i2, int i3, Bitmap bitmap, String str, DetectParam detectParam) {
        GaLog.b("LowSugarAiMgr", "requiredParseGameTask bitmap = " + bitmap);
        E();
        GameBaseScene a2 = GameSceneFactory.a(this.f16717a, str);
        Bitmap a3 = a2.a(bitmap, i3);
        Rect b2 = a2.b(a3, i3);
        if (LowSugarUtils.f17012g) {
            GaLog.b("LowSugarAiMgr", "requiredParseGameTask set test bitmap cropBitmap = " + a3);
            LowSugarWindowManager.d().j(a3);
            return false;
        }
        GaLog.b("LowSugarAiMgr", "requiredParseGameTask cropBitmap = " + a3);
        Y(new LowSugarPurposeData(i2, i3, str, a3, "", "", detectParam, b2, ImageDeduplicationUtil.e(a3, ImageDeduplicationUtil.ImageHashAlgorithm.DHASH)));
        return i3 == 0;
    }

    public void U(int i2) {
        GaLog.a("LowSugarAiMgr", "setLogining logining = " + i2);
        this.f16727k = i2;
        if (i2 != 0) {
            this.f16725i.postDelayed(this.z, 1800000L);
        }
    }

    public void Z() {
        if (this.f16732p) {
            GaLog.a("LowSugarAiMgr", "unbind SENTRY_MODE ");
            this.f16717a.unbindService(this.f16734r);
            this.f16732p = false;
        }
    }

    public void y() {
        if (this.f16732p) {
            return;
        }
        GaLog.a("LowSugarAiMgr", "bind SENTRY_MODE ");
        Intent intent = new Intent("com.zte.aispeaker.sentryMode.action.START");
        intent.setComponent(new ComponentName("com.zte.onemorething", "com.zte.aispeaker.sentryMode.SentryModeService"));
        this.f16717a.bindService(intent, this.f16734r, 1);
    }
}
