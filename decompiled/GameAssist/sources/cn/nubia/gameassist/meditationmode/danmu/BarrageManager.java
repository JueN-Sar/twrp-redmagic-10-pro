package cn.nubia.gameassist.meditationmode.danmu;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Configuration;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.meditationmode.danmu.util.BarrageLog;
import cn.nubia.plugin.gameratio.GameRatioCallback;
import cn.nubia.plugin.gameratio.GameRatioMgr;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class BarrageManager implements GameRatioCallback {

    /* renamed from: o, reason: collision with root package name */
    private static volatile BarrageManager f6575o;

    /* renamed from: p, reason: collision with root package name */
    private static final List f6576p = Arrays.asList("com.zte.aliveupdate", "com.zte.dbneopush", "com.vertu.vpush");

    /* renamed from: f, reason: collision with root package name */
    private Context f6582f;

    /* renamed from: g, reason: collision with root package name */
    private Handler f6583g;

    /* renamed from: h, reason: collision with root package name */
    private Handler f6584h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f6585i;

    /* renamed from: l, reason: collision with root package name */
    public Context f6588l;

    /* renamed from: m, reason: collision with root package name */
    private BarrageWindowManager f6589m;

    /* renamed from: n, reason: collision with root package name */
    private final ContentObserver f6590n;

    /* renamed from: a, reason: collision with root package name */
    private final Uri f6577a = Settings.Global.getUriFor("gsc_meditation_level");

    /* renamed from: b, reason: collision with root package name */
    private final Uri f6578b = Settings.Global.getUriFor("gsc_barrage_message_shield_notification");

    /* renamed from: c, reason: collision with root package name */
    private final Uri f6579c = Settings.Global.getUriFor("gsc_barrage_message_preview");

    /* renamed from: d, reason: collision with root package name */
    private final Uri f6580d = Settings.System.getUriFor("lock_apps_notification_list");

    /* renamed from: e, reason: collision with root package name */
    private final Uri f6581e = Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/barrage_message_source?notify=true");

    /* renamed from: j, reason: collision with root package name */
    private final List f6586j = new ArrayList();

    /* renamed from: k, reason: collision with root package name */
    private List f6587k = new ArrayList();

    private BarrageManager() {
        this.f6588l = null;
        this.f6590n = new ContentObserver(this.f6583g) { // from class: cn.nubia.gameassist.meditationmode.danmu.BarrageManager.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z, Uri uri) {
                BarrageLog.b("BarrageManager", "onChange, selfChange:" + z);
                ContentResolver contentResolver = BarrageManager.this.f6582f.getContentResolver();
                if (BarrageManager.this.f6577a.equals(uri)) {
                    BarrageManager.this.O();
                    return;
                }
                if (BarrageManager.this.f6578b.equals(uri)) {
                    BarrageManager.this.f6585i = Settings.Global.getInt(contentResolver, "gsc_barrage_message_shield_notification", 1) == 1;
                    BarrageLog.b("BarrageManager", "mIsDanmuIgnoreHideSwitch:" + BarrageManager.this.f6585i);
                    return;
                }
                if (BarrageManager.this.f6579c.equals(uri)) {
                    BarrageManager.this.O();
                } else if (BarrageManager.this.f6581e.equals(uri)) {
                    BarrageManager.this.W();
                } else if (BarrageManager.this.f6580d.equals(uri)) {
                    BarrageManager.this.X();
                }
            }
        };
        GameAssistApplication.j();
        Context a2 = BaseApplication.a();
        this.f6582f = a2;
        this.f6588l = a2;
        this.f6583g = new Handler(ThreadManager.c().j());
        this.f6584h = BarrageFactory.b();
        J();
    }

    private void E(int i2) {
        BarrageWindowManager barrageWindowManager = this.f6589m;
        if (barrageWindowManager == null) {
            return;
        }
        barrageWindowManager.l(i2);
    }

    private void J() {
        try {
            BarrageLog.f("BarrageManager", "registerBarrageDataObserver");
            this.f6582f.getContentResolver().registerContentObserver(this.f6577a, false, this.f6590n);
            this.f6582f.getContentResolver().registerContentObserver(this.f6578b, false, this.f6590n);
            this.f6582f.getContentResolver().registerContentObserver(this.f6579c, false, this.f6590n);
            this.f6582f.getContentResolver().registerContentObserver(this.f6580d, false, this.f6590n);
            this.f6582f.getContentResolver().registerContentObserver(this.f6581e, false, this.f6590n);
            this.f6590n.onChange(true, this.f6577a);
            this.f6590n.onChange(true, this.f6578b);
            this.f6590n.onChange(true, this.f6580d);
            this.f6590n.onChange(true, this.f6581e);
        } catch (Exception e2) {
            BarrageLog.d("BarrageManager", "registerBarrageDataObserver", e2);
        }
    }

    private void K() {
        GameRatioMgr.q().k(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O() {
        BarrageLog.b("BarrageManager", "showOrHidePreView");
        if (N()) {
            this.f6584h.post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.d
                @Override // java.lang.Runnable
                public final void run() {
                    BarrageManager.this.A();
                }
            });
        } else {
            this.f6584h.post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.e
                @Override // java.lang.Runnable
                public final void run() {
                    BarrageManager.this.B();
                }
            });
        }
    }

    private void U() {
        GameRatioMgr.q().H(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void V() {
        Cursor cursor = null;
        try {
            try {
                cursor = this.f6582f.getContentResolver().query(Uri.parse("content://cn.nubia.gamelauncher.db.AppAddProvider/barrage_message_source?notify=true"), null, null, null, null);
            } catch (Exception e2) {
                if (0 != 0) {
                    cursor.close();
                }
                BarrageLog.c("BarrageManager", "Failed to get barrage app list: " + e2);
                if (0 == 0) {
                    return;
                }
            }
            if (cursor == null) {
                BarrageLog.b("BarrageManager", "notice : updateBarrageAppList cursor == null !");
                if (cursor != null) {
                    cursor.close();
                    return;
                }
                return;
            }
            ArrayList arrayList = new ArrayList();
            int columnIndex = cursor.getColumnIndex("component");
            int columnIndex2 = cursor.getColumnIndex("isAdd");
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                String string = cursor.getString(columnIndex);
                if (cursor.getInt(columnIndex2) == 1) {
                    arrayList.add(string);
                }
            }
            synchronized (this.f6586j) {
                this.f6586j.clear();
                this.f6586j.addAll(arrayList);
            }
            cursor.close();
            cursor.close();
        } catch (Throwable th) {
            if (0 != 0) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W() {
        BarrageLog.b("BarrageManager", "updateBarrageAppListSafely");
        try {
            this.f6583g.removeCallbacks(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.b
                @Override // java.lang.Runnable
                public final void run() {
                    BarrageManager.this.V();
                }
            });
            this.f6583g.post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.b
                @Override // java.lang.Runnable
                public final void run() {
                    BarrageManager.this.V();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X() {
        this.f6587k = s();
        BarrageLog.f("BarrageManager", "updateLockAppList, lockAppNotificationList:" + this.f6587k);
    }

    public static BarrageManager r() {
        if (f6575o == null) {
            synchronized (BarrageManager.class) {
                try {
                    if (f6575o == null) {
                        f6575o = new BarrageManager();
                    }
                } finally {
                }
            }
        }
        return f6575o;
    }

    private List s() {
        String string = Settings.System.getString(this.f6582f.getContentResolver(), "lock_apps_notification_list");
        return (string == null || string.isEmpty()) ? new ArrayList() : new ArrayList(Arrays.asList(string.split(";")));
    }

    private boolean t() {
        return 1 == Settings.Global.getInt(this.f6582f.getContentResolver(), "gsc_meditation_level", 0);
    }

    private boolean u(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        String T = T(statusBarNotification);
        BarrageLog.f("BarrageManager", "isBarrageResListNotification, packageName:" + packageName + " targetPackageName:" + T);
        if (this.f6586j.contains(packageName) || this.f6586j.contains(T)) {
            return true;
        }
        BarrageLog.f("BarrageManager", "mBarrageResList no contains " + packageName + " targetPackageName:" + T);
        return false;
    }

    private boolean v() {
        return 3 == Settings.Global.getInt(this.f6582f.getContentResolver(), "gsc_meditation_level", 0);
    }

    private boolean y() {
        return Settings.Global.getInt(this.f6582f.getContentResolver(), "gsc_barrage_message_preview", 0) == 1;
    }

    public void D(Configuration configuration) {
    }

    public void F(boolean z) {
    }

    public void G() {
        BarrageLog.f("BarrageManager", "onGameStart");
        K();
        if (this.f6586j.isEmpty()) {
            BarrageLog.f("BarrageManager", "barrage list is empty");
            W();
        }
        X();
    }

    public void H() {
        BarrageLog.f("BarrageManager", "onGameStop");
        U();
    }

    public void I() {
        BarrageWindowManager barrageWindowManager = this.f6589m;
        if (barrageWindowManager == null) {
            return;
        }
        barrageWindowManager.m();
    }

    /* renamed from: L, reason: merged with bridge method [inline-methods] */
    public void C(DanmuNotificationBean danmuNotificationBean) {
        if (this.f6589m == null) {
            this.f6589m = new BarrageWindowManager(this.f6582f);
        }
        this.f6589m.o(danmuNotificationBean);
    }

    public boolean M(StatusBarNotification statusBarNotification) {
        if (t()) {
            return (this.f6585i || !v()) && u(statusBarNotification);
        }
        return false;
    }

    public boolean N() {
        BarrageLog.b("BarrageManager", "shouldShowWithPreBarrage, isPreViewOpen = " + y() + " isBarrageMode = " + t());
        return t() && y();
    }

    public void P(final DanmuNotificationBean danmuNotificationBean) {
        BarrageLog.f("BarrageManager", "startBarrage");
        this.f6584h.post(new Runnable() { // from class: cn.nubia.gameassist.meditationmode.danmu.c
            @Override // java.lang.Runnable
            public final void run() {
                BarrageManager.this.C(danmuNotificationBean);
            }
        });
    }

    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public void A() {
        if (this.f6589m == null) {
            this.f6589m = new BarrageWindowManager(this.f6582f);
        }
        this.f6589m.q();
    }

    public void R() {
        BarrageWindowManager barrageWindowManager = this.f6589m;
        if (barrageWindowManager == null) {
            return;
        }
        barrageWindowManager.r();
        this.f6589m = null;
    }

    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public void B() {
        BarrageWindowManager barrageWindowManager = this.f6589m;
        if (barrageWindowManager == null) {
            return;
        }
        barrageWindowManager.s();
        this.f6589m = null;
    }

    public String T(StatusBarNotification statusBarNotification) {
        try {
            if (z(statusBarNotification.getPackageName(), statusBarNotification.getNotification().getChannelId())) {
                String string = statusBarNotification.getNotification().extras.getString("target_pkg");
                BarrageLog.f("BarrageManager", "tryToGetTargetPackage, targetPkg:" + string);
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
            return null;
        } catch (Exception e2) {
            BarrageLog.d("BarrageManager", "tryToGetTargetPackage: can not get target package.", e2);
            return null;
        }
    }

    @Override // cn.nubia.plugin.gameratio.GameRatioCallback
    public void a(int i2) {
        BarrageLog.c("BarrageManager", "onVerticalPositionChanged, pos:" + i2);
        E(i2);
    }

    public void q(PrintWriter printWriter) {
        printWriter.println("BarrageManager：");
        printWriter.println("sIsGameScene = " + SystemMgr.H());
        printWriter.println("isBarrageMode = " + t());
        printWriter.println("isPreViewOpen = " + y());
        printWriter.println("mIsDanmuIgnoreHideSwitch = " + this.f6585i);
        printWriter.println("isGameKeyOffNotification = " + v());
        printWriter.println("current barrage list -------------------------");
        printWriter.println("mBarrageResList App list >> : " + this.f6586j);
        printWriter.println("");
        BarrageWindowManager barrageWindowManager = this.f6589m;
        if (barrageWindowManager != null) {
            barrageWindowManager.f(printWriter);
        }
        printWriter.println("");
    }

    public boolean w(String str) {
        List list = this.f6587k;
        if (list == null || list.isEmpty()) {
            return false;
        }
        return this.f6587k.contains(str);
    }

    public boolean x() {
        BarrageWindowManager barrageWindowManager = this.f6589m;
        if (barrageWindowManager == null) {
            return false;
        }
        return barrageWindowManager.i();
    }

    public boolean z(String str, String str2) {
        return f6576p.contains(str) || "channel_nubiapush".equals(str2);
    }
}
