package com.zte.plugin.reminder;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.WindowManager;
import androidx.annotation.VisibleForTesting;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.ObserverData;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.reminder.R;
import com.zte.gameassist.utils.GaLog;
import com.zte.plugin.reminder.GameReminderView;
import com.zte.plugin.reminder.widget.GameReminderWidget;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class GameReminderWindowManager implements GameMonitor.Callback {
    private static volatile GameReminderWindowManager A;

    /* renamed from: c, reason: collision with root package name */
    private Context f18045c;

    /* renamed from: h, reason: collision with root package name */
    private WindowManager f18046h;

    /* renamed from: i, reason: collision with root package name */
    private WindowManager.LayoutParams f18047i;

    /* renamed from: j, reason: collision with root package name */
    private int f18048j;

    /* renamed from: k, reason: collision with root package name */
    private int f18049k;

    /* renamed from: l, reason: collision with root package name */
    private GameReminderView f18050l;

    /* renamed from: n, reason: collision with root package name */
    private String f18052n;

    /* renamed from: r, reason: collision with root package name */
    private String f18056r;

    /* renamed from: s, reason: collision with root package name */
    private String f18057s;
    private Calendar t;
    private int u;
    private boolean v;

    /* renamed from: o, reason: collision with root package name */
    private boolean f18053o = false;

    /* renamed from: p, reason: collision with root package name */
    private boolean f18054p = false;

    /* renamed from: q, reason: collision with root package name */
    private boolean f18055q = false;
    private GameReminderView.RemoveViewListener w = new GameReminderView.RemoveViewListener() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.1
        @Override // com.zte.plugin.reminder.GameReminderView.RemoveViewListener
        public void a() {
            GameReminderWindowManager.this.removeView();
        }
    };
    private final ServiceConnection x = new ServiceConnection() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GameReminderUtils.e("GameReminderWindowManager", "onServiceConnected service : " + iBinder + ", className :" + componentName);
            GameReminderWindowManager.this.f18054p = true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GameReminderUtils.e("GameReminderWindowManager", "onServiceConnected className :" + componentName);
            if (GameReminderWindowManager.this.f18054p) {
                GameReminderWindowManager.this.f18054p = false;
                GameReminderWindowManager.this.B();
            }
        }
    };
    private ContentObserver y = new ContentObserver(null) { // from class: com.zte.plugin.reminder.GameReminderWindowManager.3
        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            if (Settings.System.getInt(GameReminderWindowManager.this.f18045c.getContentResolver(), "keyguard_is_showing", 0) == 1) {
                GameReminderWindowManager.this.removeView();
            }
        }
    };
    private RotationMgr.Callback z = new RotationMgr.Callback() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.5
        @Override // com.zte.gameassist.common.RotationMgr.Callback
        /* renamed from: onRotationChanged */
        public void y(int i2) {
            GameReminderWindowManager.this.f18051m.post(new Runnable() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.5.1
                @Override // java.lang.Runnable
                public void run() {
                    GameReminderWindowManager.this.removeView();
                }
            });
        }
    };

    /* renamed from: m, reason: collision with root package name */
    private Handler f18051m = new Handler();

    public GameReminderWindowManager(Context context) {
        this.f18045c = context;
        this.f18046h = (WindowManager) context.getSystemService("window");
        H();
        U();
        if (FoldMgr.f()) {
            InflaterHelper.f16516e.e(true, new ObserverData.Observer() { // from class: com.zte.plugin.reminder.f
                @Override // com.zte.gameassist.common.ObserverData.Observer
                public final void a(Object obj) {
                    GameReminderWindowManager.this.M((InflaterHelper.FixedScreenState) obj);
                }
            });
            FoldMgr.c().a(new FoldMgr.Callback() { // from class: com.zte.plugin.reminder.g
                @Override // com.zte.gameassist.common.FoldMgr.Callback
                public final void onDisplayInUseStateChanged(int i2) {
                    GameReminderWindowManager.this.K(i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void B() {
        GameReminderUtils.e("GameReminderWindowManager", "bindService isBound : " + this.f18054p);
        if (this.f18054p) {
            return;
        }
        Intent intent = new Intent("com.zte.ai.knowledge.BIND_SERVICE");
        intent.setPackage("com.zte.ai.knowledge");
        this.f18045c.bindService(intent, this.x, 1);
    }

    private void C() {
        if (this.f18047i == null) {
            return;
        }
        boolean j2 = RotationMgr.j();
        WindowManager.LayoutParams layoutParams = this.f18047i;
        int i2 = layoutParams.width;
        int i3 = layoutParams.height;
        if (j2 && i2 < i3) {
            layoutParams.width = i3;
            layoutParams.height = i2;
        } else {
            if (j2 || i2 <= i3) {
                return;
            }
            layoutParams.width = i3;
            layoutParams.height = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D() {
        if (this.f18050l == null) {
            return;
        }
        U();
        C();
        GameReminderUtils.e("GameReminderWindowManager", "continueShowView mIsViewAdded: " + this.f18053o);
        if (this.f18053o) {
            return;
        }
        this.f18046h.addView(this.f18050l, this.f18047i);
        this.f18053o = true;
        Settings.Global.putInt(this.f18045c.getContentResolver(), "nubia_game_window_show", 1);
        SystemMgr.y(this.f18045c).h(this);
        RotationMgr.e(this.f18045c).c(this.z);
        N();
        Q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: F, reason: merged with bridge method [inline-methods] */
    public void J(final Runnable runnable) {
        if (this.f18051m.getLooper().isCurrentThread()) {
            runnable.run();
        } else {
            this.f18051m.post(new Runnable() { // from class: com.zte.plugin.reminder.h
                @Override // java.lang.Runnable
                public final void run() {
                    GameReminderWindowManager.this.J(runnable);
                }
            });
        }
    }

    public static GameReminderWindowManager G(Context context) {
        if (A == null) {
            synchronized (GameReminderWindowManager.class) {
                try {
                    if (A == null) {
                        A = new GameReminderWindowManager(context);
                    }
                } finally {
                }
            }
        }
        return A;
    }

    private void H() {
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(2008, 8718080, -3);
        this.f18047i = layoutParams;
        layoutParams.flags = (layoutParams.flags & (-131073)) | WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_COLOR_SPACE_AGNOSTIC;
        WindowManagerWrapper.LayoutParams.setFitInsetsTypes(layoutParams);
        WindowManager.LayoutParams layoutParams2 = this.f18047i;
        layoutParams2.gravity = 51;
        layoutParams2.setTitle("GameReminder");
        this.f18047i.packageName = this.f18045c.getPackageName();
    }

    private void I() {
        GameReminderView gameReminderView = this.f18050l;
        if (gameReminderView != null) {
            gameReminderView.n();
        }
        GameReminderView gameReminderView2 = (GameReminderView) InflaterHelper.f(R.layout.game_reminder_view, null);
        this.f18050l = gameReminderView2;
        gameReminderView2.o();
        this.f18050l.setRemoveViewListener(this.w);
        this.f18050l.setSystemUiVisibility(6);
        this.f18052n = this.f18045c.getResources().getConfiguration().locale.getCountry();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void K(int i2) {
        GaLog.b("GameReminderWindowManager", "onDisplayInUseStateChanged state = " + i2);
        if (SystemMgr.H() && this.f18053o && this.f18050l != null) {
            GaLog.b("GameReminderWindowManager", "onDisplayInUseStateChanged need remove view!");
            this.f18056r = this.f18050l.getTitle();
            this.v = this.f18050l.u();
            this.t = this.f18050l.getDateTime();
            this.u = this.f18050l.getMonthDay();
            this.f18057s = this.f18050l.getDateShown();
            removeView();
            this.f18055q = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M(InflaterHelper.FixedScreenState fixedScreenState) {
        GaLog.b("GameReminderWindowManager", "onFoldStateChanged state = " + fixedScreenState);
        I();
        if (SystemMgr.H() && this.f18055q) {
            GaLog.b("GameReminderWindowManager", "onFoldStateChanged window should add to update ui!");
            J(new Runnable() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.4
                @Override // java.lang.Runnable
                public void run() {
                    GameReminderWindowManager.this.R();
                    GameReminderWindowManager.this.f18050l.setTitle(GameReminderWindowManager.this.f18056r);
                    GameReminderWindowManager.this.f18056r = null;
                    GameReminderWindowManager.this.f18050l.setDateTime(GameReminderWindowManager.this.t);
                    GameReminderWindowManager.this.t = null;
                    GameReminderWindowManager.this.f18050l.setMonthDay(GameReminderWindowManager.this.u);
                    GameReminderWindowManager.this.u = 0;
                    GameReminderWindowManager.this.f18050l.setDateShown(GameReminderWindowManager.this.f18057s);
                    GameReminderWindowManager.this.f18057s = null;
                    GameReminderWindowManager.this.f18050l.setRingCheck(GameReminderWindowManager.this.v);
                    GameReminderWindowManager.this.v = false;
                    GameReminderWindowManager.this.f18055q = false;
                }
            });
        }
    }

    private void N() {
        this.f18045c.getContentResolver().registerContentObserver(Settings.System.getUriFor("keyguard_is_showing"), true, this.y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        this.f18051m.postDelayed(new Runnable() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.9
            @Override // java.lang.Runnable
            public void run() {
                Settings.Global.putString(GameReminderWindowManager.this.f18045c.getContentResolver(), "nubia_gameassistRect", "");
            }
        }, 500L);
    }

    private void Q() {
        if (RotationMgr.j()) {
            Settings.Global.putString(this.f18045c.getContentResolver(), "nubia_gameassistRect", "1652:96:2299:168");
        } else {
            Settings.Global.putString(this.f18045c.getContentResolver(), "nubia_gameassistRect", "264:1384:911:1456");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void S() {
        this.f18045c.getContentResolver().unregisterContentObserver(this.y);
    }

    private void T() {
        if (this.f18050l == null) {
            return;
        }
        this.f18050l.A(RotationMgr.j());
        this.f18050l.z();
    }

    private void U() {
        int g2 = RotationMgr.g();
        int f2 = RotationMgr.f();
        boolean k2 = RotationMgr.k();
        if (f2 > g2) {
            this.f18049k = f2;
            this.f18048j = g2;
        } else {
            this.f18049k = g2;
            this.f18048j = f2;
        }
        WindowManager.LayoutParams layoutParams = this.f18047i;
        layoutParams.height = k2 ? this.f18049k : this.f18048j;
        layoutParams.width = k2 ? this.f18048j : this.f18049k;
        GameReminderUtils.e("GameReminderWindowManager", "updateLayoutParams()  isPortrait = " + k2 + ", mLp = " + this.f18047i.height + "," + this.f18047i.width);
    }

    public void E() {
        this.f18051m.postDelayed(new Runnable() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.7
            @Override // java.lang.Runnable
            public void run() {
                GameReminderWindowManager.this.D();
            }
        }, 200L);
    }

    public void L(Configuration configuration) {
        GaLog.a("GameReminderWindowManager", "onConfigurationChanged orientation=" + configuration.orientation);
    }

    public void P(final String str, final String str2, final long j2, final int i2, final GameReminderView.RemoveViewListener removeViewListener) {
        final long currentTimeMillis = System.currentTimeMillis();
        new AsyncTask<Void, Void, Integer>() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Integer doInBackground(Void... voidArr) {
                ContentResolver contentResolver = GameReminderWindowManager.this.f18045c.getContentResolver();
                Uri uri = GameReminderColumn.f18021a;
                Cursor query = contentResolver.query(uri, null, "time=?", new String[]{Long.toString(j2)}, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            return 0;
                        }
                    } finally {
                    }
                }
                if (query != null) {
                    query.close();
                }
                query = GameReminderWindowManager.this.f18045c.getContentResolver().query(uri, null, "time>?", new String[]{Long.toString(currentTimeMillis)}, null);
                if (query != null) {
                    try {
                        if (query.getCount() >= 50) {
                            return 1;
                        }
                    } finally {
                    }
                }
                if (query != null) {
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("title", str);
                contentValues.put("time", Long.valueOf(j2));
                contentValues.put("package", str2);
                contentValues.put("alarm", Integer.valueOf(i2));
                GameReminderWindowManager.this.f18045c.getContentResolver().insert(uri, contentValues);
                GameReminderUtils.j(GameReminderWindowManager.this.f18045c, str, j2 - currentTimeMillis, str2, i2);
                GameReminderWidget.a(GameReminderWindowManager.this.f18045c);
                return 2;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onPostExecute(Integer num) {
                if (num.intValue() == 0) {
                    GameReminderUtils.i(GameReminderWindowManager.this.f18045c, GameReminderWindowManager.this.f18045c.getString(R.string.game_reminder_already_exists));
                }
                if (num.intValue() == 1) {
                    GameReminderUtils.i(GameReminderWindowManager.this.f18045c, GameReminderWindowManager.this.f18045c.getString(R.string.game_reminder_max_number_reached));
                }
                if (num.intValue() == 2) {
                    GameReminderUtils.i(GameReminderWindowManager.this.f18045c, GameReminderWindowManager.this.f18045c.getString(R.string.game_reminder_save_success));
                    GameReminderView.RemoveViewListener removeViewListener2 = removeViewListener;
                    if (removeViewListener2 != null) {
                        removeViewListener2.a();
                    }
                    Intent intent = new Intent(GameReminderWindowManager.this.f18045c, (Class<?>) AlarmService.class);
                    intent.setAction("cn.nubia.gamereminder.UPDATE");
                    GameReminderWindowManager.this.f18045c.startService(intent);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void R() {
        String country = this.f18045c.getResources().getConfiguration().locale.getCountry();
        if (this.f18050l == null || (!TextUtils.isEmpty(country) && !country.equals(this.f18052n))) {
            I();
        }
        U();
        C();
        T();
        GameReminderUtils.e("GameReminderWindowManager", "showView mIsViewAdded: " + this.f18053o);
        if (this.f18053o) {
            return;
        }
        this.f18051m.removeCallbacksAndMessages(null);
        this.f18046h.addView(this.f18050l, this.f18047i);
        this.f18053o = true;
        Settings.Global.putInt(this.f18045c.getContentResolver(), "nubia_game_window_show", 1);
        SystemMgr.y(this.f18045c).h(this);
        N();
        Q();
    }

    @VisibleForTesting
    public boolean isGameReminderViewShow() {
        return this.f18053o;
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        removeView();
    }

    @VisibleForTesting
    public void removeView() {
        if (this.f18050l == null) {
            return;
        }
        this.f18051m.post(new Runnable() { // from class: com.zte.plugin.reminder.GameReminderWindowManager.8
            @Override // java.lang.Runnable
            public void run() {
                GameReminderUtils.e("GameReminderWindowManager", "removeView mIsViewAdded: " + GameReminderWindowManager.this.f18053o);
                if (GameReminderWindowManager.this.f18053o) {
                    GameReminderWindowManager.this.f18050l.v();
                    GameReminderWindowManager.this.f18046h.removeView(GameReminderWindowManager.this.f18050l);
                    GameReminderWindowManager.this.f18053o = false;
                    Settings.Global.putInt(GameReminderWindowManager.this.f18045c.getContentResolver(), "nubia_game_window_show", 0);
                    SystemMgr.y(GameReminderWindowManager.this.f18045c).i(GameReminderWindowManager.this);
                    RotationMgr.e(GameReminderWindowManager.this.f18045c).c(GameReminderWindowManager.this.z);
                    GameReminderWindowManager.this.S();
                    GameReminderWindowManager.this.O();
                }
            }
        });
    }
}
