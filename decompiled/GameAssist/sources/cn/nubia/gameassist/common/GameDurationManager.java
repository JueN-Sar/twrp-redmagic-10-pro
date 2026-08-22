package cn.nubia.gameassist.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.ext.system.TopActivityMonitor;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public class GameDurationManager implements TopActivityMonitor.FullActivityResumedCallback, DumpController.Dump {

    /* renamed from: p, reason: collision with root package name */
    private static volatile GameDurationManager f6128p;

    /* renamed from: i, reason: collision with root package name */
    private final Context f6131i;

    /* renamed from: j, reason: collision with root package name */
    private final ActivityManager f6132j;

    /* renamed from: o, reason: collision with root package name */
    private boolean f6137o;

    /* renamed from: c, reason: collision with root package name */
    private final HashMap f6129c = new HashMap();

    /* renamed from: h, reason: collision with root package name */
    private final Handler f6130h = new Handler(ThreadManager.c().j());

    /* renamed from: k, reason: collision with root package name */
    private final GameDuration[] f6133k = new GameDuration[2];

    /* renamed from: l, reason: collision with root package name */
    private final HashMap f6134l = new HashMap();

    /* renamed from: m, reason: collision with root package name */
    private final GameDuration f6135m = new GameDuration(null, 0, 0, 0);

    /* renamed from: n, reason: collision with root package name */
    private final GameDuration f6136n = new GameDuration(null, 0, 0, 0);

    public interface CallBack {
        void onBundlePrepare(Bundle bundle);
    }

    public static class GameDuration {

        /* renamed from: a, reason: collision with root package name */
        public String f6138a;

        /* renamed from: b, reason: collision with root package name */
        public int f6139b;

        /* renamed from: c, reason: collision with root package name */
        public int f6140c;

        /* renamed from: d, reason: collision with root package name */
        public int f6141d;

        /* renamed from: e, reason: collision with root package name */
        public long f6142e;

        /* renamed from: f, reason: collision with root package name */
        public long f6143f;

        public GameDuration(String str, int i2, int i3, int i4) {
            this.f6138a = str;
            this.f6141d = i2;
            this.f6139b = i3;
            this.f6140c = i4;
        }

        public void a() {
            this.f6138a = null;
            this.f6141d = 0;
            this.f6139b = 0;
            this.f6140c = 0;
            this.f6142e = 0L;
            this.f6143f = 0L;
        }

        public void b(GameDuration gameDuration) {
            if (gameDuration == null) {
                return;
            }
            this.f6138a = gameDuration.f6138a;
            this.f6141d = gameDuration.f6141d;
            this.f6139b = gameDuration.f6139b;
            this.f6140c = gameDuration.f6140c;
            this.f6142e = gameDuration.f6142e;
            this.f6143f = gameDuration.f6143f;
        }

        public void c(String str, int i2, int i3, int i4) {
            this.f6138a = str;
            this.f6141d = i2;
            this.f6139b = i3;
            this.f6140c = i4;
        }

        public String toString() {
            return "GameDuration{pkg='" + this.f6138a + NubiaTextClock.QUOTE + ", displayId=" + this.f6139b + ", pid=" + this.f6140c + ", hash=" + this.f6141d + ", startTime=" + this.f6142e + ", endTime=" + this.f6143f + '}';
        }
    }

    private GameDurationManager() {
        Context context = ContextWrapper.getContext();
        this.f6131i = context;
        this.f6132j = (ActivityManager) context.getSystemService("activity");
        TopActivityMonitor.a(this);
        DumpController.c().a(this);
    }

    private void j(long j2, GameDuration gameDuration) {
        GaLog.b("GameDurationManager", "gameFirstLauncher st:" + j2 + "," + gameDuration);
        gameDuration.f6142e = j2;
        gameDuration.f6143f = 0L;
        String m2 = m(gameDuration.f6138a, gameDuration.f6141d);
        this.f6134l.put(m2, Integer.valueOf(gameDuration.f6140c));
        this.f6129c.put(m2, 0L);
        this.f6135m.b(gameDuration);
    }

    private void k(long j2, GameDuration gameDuration) {
        GaLog.b("GameDurationManager", "gameStart st:" + j2 + "," + gameDuration);
        gameDuration.f6142e = j2;
        gameDuration.f6143f = 0L;
        this.f6135m.b(gameDuration);
    }

    private void l(long j2, GameDuration gameDuration) {
        if (TextUtils.isEmpty(gameDuration.f6138a)) {
            return;
        }
        gameDuration.f6143f = j2;
        String m2 = m(gameDuration.f6138a, gameDuration.f6141d);
        Long l2 = (Long) this.f6129c.get(m2);
        long longValue = l2 != null ? l2.longValue() : 0L;
        long j3 = (gameDuration.f6143f + longValue) - gameDuration.f6142e;
        GaLog.b("GameDurationManager", "gameStop pt:" + j3 + ",lt:" + longValue + "," + gameDuration);
        this.f6129c.put(m2, Long.valueOf(j3));
        gameDuration.f6142e = 0L;
    }

    private String m(String str, int i2) {
        if (!"com.tencent.mm".equals(str) || i2 == 0) {
            return str;
        }
        return str + "@" + i2;
    }

    public static GameDurationManager n() {
        if (f6128p == null) {
            synchronized (GameDurationManager.class) {
                try {
                    if (f6128p == null) {
                        f6128p = new GameDurationManager();
                    }
                } finally {
                }
            }
        }
        return f6128p;
    }

    private boolean q(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : this.f6132j.getRunningAppProcesses()) {
            String str2 = runningAppProcessInfo.processName;
            if (str2 != null && str2.contains(str) && runningAppProcessInfo.pid == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean r() {
        return Settings.Global.getInt(this.f6131i.getContentResolver(), "gamebox_mirror_displayid", 0) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t(PrintWriter printWriter, String str, Integer num) {
        printWriter.println("  " + str + "," + num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(PrintWriter printWriter, String str, Long l2) {
        printWriter.println("  " + str + "," + l2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v(CallBack callBack) {
        GameDuration gameDuration = this.f6135m;
        boolean q2 = q(gameDuration.f6138a, gameDuration.f6140c);
        GaLog.a("GameDurationManager", "pkg " + this.f6135m.f6138a + ",pid:" + this.f6135m.f6140c + ",run " + q2);
        GameDuration gameDuration2 = this.f6135m;
        String m2 = m(gameDuration2.f6138a, gameDuration2.f6141d);
        Long l2 = (Long) this.f6129c.get(m2);
        if (l2 == null || !q2) {
            callBack.onBundlePrepare(null);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("pkg", m2);
        bundle.putLong("time", l2.longValue());
        callBack.onBundlePrepare(bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(String str, CallBack callBack) {
        Long l2 = (Long) this.f6129c.get(str);
        if (l2 != null) {
            long longValue = l2.longValue();
            for (GameDuration gameDuration : this.f6133k) {
                if (gameDuration != null && str.equals(m(gameDuration.f6138a, gameDuration.f6141d))) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long j2 = (longValue + elapsedRealtime) - gameDuration.f6142e;
                    GaLog.a("GameDurationManager", "playTime:" + j2 + ",strTime:" + l2 + ",time:" + elapsedRealtime + "," + gameDuration);
                    Bundle bundle = new Bundle();
                    bundle.putString("pkg", str);
                    bundle.putLong("time", j2);
                    callBack.onBundlePrepare(bundle);
                    return;
                }
            }
        }
        callBack.onBundlePrepare(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x(ComponentName componentName, int i2, int i3, int i4, Bundle bundle) {
        GaLog.a("GameDurationManager", "onFullActivityResumed: activity:" + componentName + ",pid:" + i2 + ",displayId:" + i3 + ",windowMode:" + i4);
        i(componentName.getPackageName(), i2, i3, bundle != null ? bundle.getInt("task_hash_code", 0) : 0);
        this.f6137o = r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y(boolean z) {
        int i2 = Settings.Global.getInt(this.f6131i.getContentResolver(), "app_mirror_displayid", 0);
        GaLog.a("GameDurationManager", "setKeyguardShow id:" + i2 + ",show:" + z);
        if (!z) {
            if (i2 == 0) {
                GameDuration gameDuration = this.f6136n;
                i(gameDuration.f6138a, gameDuration.f6140c, gameDuration.f6139b, gameDuration.f6141d);
                return;
            }
            return;
        }
        this.f6136n.b(this.f6133k[0]);
        i("com.zte.mifavor.launcher", 0, 0, 0);
        if (i2 > 0) {
            i("com.zte.mifavor.launcher", 0, i2, 0);
        }
    }

    @Override // com.zte.gameassist.ext.system.TopActivityMonitor.FullActivityResumedCallback
    public void a(ComponentName componentName) {
    }

    @Override // com.zte.gameassist.common.DumpController.Dump
    public void c(FileDescriptor fileDescriptor, final PrintWriter printWriter, String[] strArr) {
        printWriter.println("GameDurationManager");
        printWriter.println("  mRecentlyGameDuration:" + this.f6135m);
        printWriter.println("  mLockKeyguardGameDuration:" + this.f6136n);
        printWriter.println("  mDisplayLastGame:");
        for (GameDuration gameDuration : this.f6133k) {
            printWriter.println("  " + gameDuration);
        }
        if (!this.f6134l.isEmpty()) {
            printWriter.println("  mAppPidList:");
            this.f6134l.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.common.i
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    GameDurationManager.t(printWriter, (String) obj, (Integer) obj2);
                }
            });
        }
        if (this.f6129c.isEmpty()) {
            return;
        }
        printWriter.println("  AppRunningTime:");
        this.f6129c.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.common.j
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                GameDurationManager.u(printWriter, (String) obj, (Long) obj2);
            }
        });
    }

    public void i(String str, int i2, int i3, int i4) {
        GameDuration gameDuration;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String m2 = m(str, i4);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        char c2 = i3 == 0 ? (char) 0 : (char) 1;
        GameDuration gameDuration2 = this.f6133k[c2];
        if (gameDuration2 == null) {
            gameDuration2 = new GameDuration(null, 0, i3, 0);
        }
        if (i3 == 0 && (gameDuration = this.f6133k[1]) != null && m2.equals(m(gameDuration.f6138a, gameDuration.f6141d)) && i2 == gameDuration.f6140c) {
            GaLog.a("GameDurationManager", "has record " + gameDuration);
            if (this.f6137o) {
                l(elapsedRealtime, gameDuration);
            } else {
                if (!TextUtils.isEmpty(gameDuration2.f6138a)) {
                    l(elapsedRealtime, gameDuration2);
                }
                gameDuration.f6139b = 0;
                this.f6133k[0].b(gameDuration);
            }
            this.f6133k[1].a();
            return;
        }
        GaLog.a("GameDurationManager", "id:" + i3 + "," + gameDuration2);
        if (s(str, i4)) {
            if (!m2.equals(m(gameDuration2.f6138a, gameDuration2.f6141d))) {
                l(elapsedRealtime, gameDuration2);
                gameDuration2.c(str, i4, i3, i2);
                Integer num = (Integer) this.f6134l.get(m(gameDuration2.f6138a, gameDuration2.f6141d));
                if (num == null || num.intValue() != i2) {
                    j(elapsedRealtime, gameDuration2);
                } else {
                    k(elapsedRealtime, gameDuration2);
                }
            } else if (gameDuration2.f6142e == 0) {
                if (i2 == gameDuration2.f6140c) {
                    k(elapsedRealtime, gameDuration2);
                } else {
                    gameDuration2.c(str, i4, i3, i2);
                    j(elapsedRealtime, gameDuration2);
                }
            }
        } else if (s(gameDuration2.f6138a, gameDuration2.f6141d)) {
            l(elapsedRealtime, gameDuration2);
            gameDuration2.a();
        }
        this.f6133k[c2] = gameDuration2;
    }

    public void o(final CallBack callBack) {
        if (callBack == null) {
            return;
        }
        this.f6130h.post(new Runnable() { // from class: cn.nubia.gameassist.common.k
            @Override // java.lang.Runnable
            public final void run() {
                GameDurationManager.this.v(callBack);
            }
        });
    }

    @Override // com.zte.gameassist.ext.system.TopActivityMonitor.FullActivityResumedCallback
    public void onFullActivityResumed(final ComponentName componentName, ActivityInfo activityInfo, int i2, int i3, final int i4, final int i5, int i6, final int i7, final Bundle bundle) {
        if (componentName == null || i7 != 1) {
            return;
        }
        this.f6130h.post(new Runnable() { // from class: cn.nubia.gameassist.common.h
            @Override // java.lang.Runnable
            public final void run() {
                GameDurationManager.this.x(componentName, i4, i5, i7, bundle);
            }
        });
    }

    public void p(final String str, final CallBack callBack) {
        if (callBack == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            callBack.onBundlePrepare(null);
        } else {
            this.f6130h.post(new Runnable() { // from class: cn.nubia.gameassist.common.f
                @Override // java.lang.Runnable
                public final void run() {
                    GameDurationManager.this.w(str, callBack);
                }
            });
        }
    }

    public boolean s(String str, int i2) {
        if (this.f6131i.getPackageName().equals(str)) {
            return false;
        }
        return GameCheck.i(str, i2);
    }

    public void z(final boolean z) {
        this.f6130h.post(new Runnable() { // from class: cn.nubia.gameassist.common.g
            @Override // java.lang.Runnable
            public final void run() {
                GameDurationManager.this.y(z);
            }
        });
    }
}
