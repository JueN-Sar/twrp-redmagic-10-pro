package com.zte.gameassist.common;

import android.content.ComponentName;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.text.TextUtils;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.common.GameLauncherHelper;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* loaded from: classes2.dex */
public class GameCheck {

    /* renamed from: a, reason: collision with root package name */
    private static List f16494a = Arrays.asList("cn.nubia.gameassist");

    /* renamed from: b, reason: collision with root package name */
    private static List f16495b = Arrays.asList("android");

    /* renamed from: c, reason: collision with root package name */
    protected static GameLauncherObserver f16496c;

    public interface Callback {
        void a(List list);
    }

    public static class GameAppInfo {

        /* renamed from: a, reason: collision with root package name */
        protected final int f16497a;

        /* renamed from: b, reason: collision with root package name */
        protected final ComponentName f16498b;

        /* renamed from: c, reason: collision with root package name */
        protected final int f16499c;

        /* renamed from: d, reason: collision with root package name */
        protected final String f16500d;

        /* renamed from: e, reason: collision with root package name */
        protected final String f16501e;

        /* renamed from: f, reason: collision with root package name */
        protected final String f16502f;

        /* renamed from: g, reason: collision with root package name */
        protected final String f16503g;

        /* renamed from: h, reason: collision with root package name */
        protected final String f16504h;

        /* renamed from: i, reason: collision with root package name */
        protected final boolean f16505i;

        protected GameAppInfo(Map map, Cursor cursor) {
            this.f16497a = b("_id", cursor, map);
            this.f16498b = ComponentName.unflattenFromString(d("component", cursor, map).replace(",", "/"));
            this.f16504h = d("gamename", cursor, map);
            this.f16499c = b("urlType", cursor, map);
            this.f16500d = d("imageUrl", cursor, map);
            this.f16501e = d("netUrl", cursor, map);
            this.f16502f = d("middleImageUrl", cursor, map);
            this.f16503g = d("widgetUrl", cursor, map);
            this.f16505i = b("autoOpenFan", cursor, map) > 0;
        }

        public String a() {
            return this.f16504h;
        }

        protected int b(String str, Cursor cursor, Map map) {
            if (cursor == null || !map.containsKey(str)) {
                return -1;
            }
            try {
                return cursor.getInt(((Integer) map.get(str)).intValue());
            } catch (Exception unused) {
                return -1;
            }
        }

        public String c() {
            ComponentName componentName = this.f16498b;
            return componentName != null ? componentName.getPackageName() : "";
        }

        protected String d(String str, Cursor cursor, Map map) {
            if (cursor != null && map.containsKey(str)) {
                try {
                    return cursor.getString(((Integer) map.get(str)).intValue());
                } catch (Exception unused) {
                }
            }
            return str;
        }

        public boolean e(String str, int i2) {
            return str != null && this.f16498b.getPackageName().equals(str);
        }

        public boolean f() {
            return false;
        }

        public String toString() {
            return this.f16504h + "<" + this.f16498b.getPackageName() + ">";
        }
    }

    protected static class GameLauncherObserver extends ContentObserver implements Runnable {

        /* renamed from: l, reason: collision with root package name */
        public static boolean f16506l;

        /* renamed from: c, reason: collision with root package name */
        private final List f16507c;

        /* renamed from: h, reason: collision with root package name */
        private final List f16508h;

        /* renamed from: i, reason: collision with root package name */
        private final Context f16509i;

        /* renamed from: j, reason: collision with root package name */
        private final Handler f16510j;

        /* renamed from: k, reason: collision with root package name */
        private int f16511k;

        public GameLauncherObserver(Context context, Handler handler) {
            super(handler);
            this.f16507c = new ArrayList();
            this.f16508h = new ArrayList();
            this.f16509i = context;
            this.f16510j = handler;
            try {
                context.getContentResolver().registerContentObserver(Constants.f16461a, false, this);
                context.getContentResolver().registerContentObserver(Constants.f16462b, false, this);
                handler.post(new Runnable() { // from class: com.zte.gameassist.common.k
                    @Override // java.lang.Runnable
                    public final void run() {
                        GameCheck.GameLauncherObserver.this.j();
                    }
                });
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        private void i(List list) {
            ArrayList arrayList;
            synchronized (this.f16508h) {
                arrayList = new ArrayList(this.f16508h);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Callback) it.next()).a(list);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't wrap try/catch for region: R(12:3|4|5|(3:7|(10:65|66|68|69|(3:71|(2:73|74)(1:76)|75)|77|78|(2:81|79)|82|83)(1:9)|(1:11))|12|13|14|(11:33|34|36|37|(1:39)|40|41|(2:44|42)|45|46|47)|(1:17)|19|143|26) */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x0117, code lost:
        
            r4 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0118, code lost:
        
            r6 = r0;
            r0 = r4;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0113 A[Catch: Exception -> 0x0117, TRY_ENTER, TRY_LEAVE, TryCatch #4 {Exception -> 0x0117, blocks: (B:14:0x00b8, B:17:0x0113), top: B:13:0x00b8 }] */
        /* JADX WARN: Removed duplicated region for block: B:21:0x0144 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r6v13, types: [android.content.ContentResolver] */
        /* JADX WARN: Type inference failed for: r6v14 */
        /* JADX WARN: Type inference failed for: r6v15 */
        /* JADX WARN: Type inference failed for: r6v16 */
        /* JADX WARN: Type inference failed for: r6v21 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void j() {
            /*
                Method dump skipped, instructions count: 361
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.zte.gameassist.common.GameCheck.GameLauncherObserver.j():void");
        }

        public void d(Callback callback) {
            synchronized (this.f16508h) {
                try {
                    if (!this.f16508h.contains(callback)) {
                        this.f16508h.add(callback);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public GameAppInfo e(String str) {
            synchronized (this.f16507c) {
                try {
                    for (GameAppInfo gameAppInfo : this.f16507c) {
                        if (str.equals(gameAppInfo.c())) {
                            return gameAppInfo;
                        }
                    }
                    return null;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public List f() {
            ArrayList arrayList;
            synchronized (this.f16507c) {
                arrayList = new ArrayList(this.f16507c);
            }
            return arrayList;
        }

        public String g(String str, int i2) {
            synchronized (this.f16507c) {
                try {
                    for (GameAppInfo gameAppInfo : this.f16507c) {
                        if ("com.tencent.mm".equals(str) && (gameAppInfo instanceof WechatMiniAppInfo)) {
                            WechatMiniAppInfo wechatMiniAppInfo = (WechatMiniAppInfo) gameAppInfo;
                            if (wechatMiniAppInfo.f16514l == i2) {
                                return wechatMiniAppInfo.f16513k;
                            }
                        }
                    }
                    return "";
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public boolean h(String str, int i2) {
            synchronized (this.f16507c) {
                try {
                    Iterator it = this.f16507c.iterator();
                    while (it.hasNext()) {
                        if (((GameAppInfo) it.next()).e(str, i2)) {
                            return true;
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            super.onChange(z);
            int i2 = this.f16511k;
            this.f16511k = i2 + 1;
            boolean z2 = i2 > 100;
            this.f16510j.removeCallbacks(this);
            this.f16510j.postDelayed(this, z2 ? 0L : 100L);
        }

        @Override // java.lang.Runnable
        public void run() {
            j();
        }
    }

    public static class WechatMiniAppInfo extends GameAppInfo {

        /* renamed from: j, reason: collision with root package name */
        protected final String f16512j;

        /* renamed from: k, reason: collision with root package name */
        protected final String f16513k;

        /* renamed from: l, reason: collision with root package name */
        protected final int f16514l;

        protected WechatMiniAppInfo(Map map, Cursor cursor) {
            super(map, cursor);
            this.f16513k = d("label", cursor, map);
            this.f16512j = d("shortcutId", cursor, map);
            this.f16514l = b("hashcode", cursor, map);
        }

        @Override // com.zte.gameassist.common.GameCheck.GameAppInfo
        public String c() {
            if (this.f16498b == null) {
                return "";
            }
            return this.f16498b.getPackageName() + "@" + this.f16514l;
        }

        @Override // com.zte.gameassist.common.GameCheck.GameAppInfo
        public boolean e(String str, int i2) {
            return "com.tencent.mm".equals(str) ? this.f16514l == i2 : super.e(str, i2);
        }

        @Override // com.zte.gameassist.common.GameCheck.GameAppInfo
        public boolean f() {
            return true;
        }

        public String g() {
            return this.f16513k;
        }

        public String h() {
            return this.f16512j;
        }

        @Override // com.zte.gameassist.common.GameCheck.GameAppInfo
        public String toString() {
            return this.f16513k + "<" + this.f16498b.getPackageName() + "@" + this.f16514l + ">";
        }
    }

    public static void b(Callback callback) {
        GameLauncherObserver gameLauncherObserver = f16496c;
        if (gameLauncherObserver != null) {
            gameLauncherObserver.d(callback);
        }
    }

    public static void c(PrintWriter printWriter) {
        printWriter.println("GameCheck:");
        printWriter.println("  isGameSpaceListApp= " + h(SystemMgr.w));
        if (f16496c != null) {
            printWriter.println("  isGameFromDatabase= " + GameLauncherHelper.e(f16496c.f16509i, SystemMgr.w));
            printWriter.println("  GameList = " + ((String) f16496c.f().stream().flatMap(new Function() { // from class: com.zte.gameassist.common.j
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Stream j2;
                    j2 = GameCheck.j((GameCheck.GameAppInfo) obj);
                    return j2;
                }
            }).collect(Collectors.joining(", "))));
        }
    }

    public static GameAppInfo d(String str, int i2) {
        synchronized (f16496c.f16507c) {
            try {
                for (GameAppInfo gameAppInfo : f16496c.f16507c) {
                    if (gameAppInfo.e(str, i2)) {
                        return gameAppInfo;
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static GameAppInfo e(String str) {
        GameLauncherObserver gameLauncherObserver = f16496c;
        if (gameLauncherObserver != null) {
            return gameLauncherObserver.e(str);
        }
        return null;
    }

    public static String f(String str, int i2) {
        GameLauncherObserver gameLauncherObserver = f16496c;
        return gameLauncherObserver != null ? gameLauncherObserver.g(str, i2) : "";
    }

    public static synchronized void g(Context context) {
        synchronized (GameCheck.class) {
            if (f16496c == null) {
                f16496c = new GameLauncherObserver(context, new Handler(ThreadManager.c().b()));
            }
        }
    }

    public static boolean h(String str) {
        return i(str, SystemMgr.A);
    }

    public static boolean i(String str, int i2) {
        if (TextUtils.isEmpty(str) || "com.zte.mifavor.launcher".equals(str)) {
            return false;
        }
        if ("cn.nubia.gameassist".equals(str) && SystemMgr.s().contains("cn.nubia.multisubscreen")) {
            GaLog.k("GameCheck", "multi sub screen activity is not game");
            return false;
        }
        if (str.contains(".cts") && str.contains("android")) {
            GaLog.k("GameCheck", "cts test not game");
            return false;
        }
        Iterator it = f16495b.iterator();
        while (it.hasNext()) {
            if (((String) it.next()).equals(str)) {
                GaLog.e("GameCheck", "app is not a game: " + str);
                return false;
            }
        }
        if (f16494a.contains(str)) {
            return true;
        }
        GameLauncherObserver gameLauncherObserver = f16496c;
        if (gameLauncherObserver == null) {
            GaLog.k("GameCheck", "mGameLauncherObserver null");
            return false;
        }
        synchronized (gameLauncherObserver.f16507c) {
            try {
                if (GameLauncherObserver.f16506l || SystemMgr.f16556q == null) {
                    return f16496c.h(str, i2);
                }
                GaLog.k("GameCheck", "not init mGameLauncherObserver, so return mResumedFullActivity.mIsInGameList=" + SystemMgr.f16556q.mIsInGameList);
                return SystemMgr.f16556q.mIsInGameList;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Stream j(GameAppInfo gameAppInfo) {
        return Stream.of(gameAppInfo.toString());
    }
}
