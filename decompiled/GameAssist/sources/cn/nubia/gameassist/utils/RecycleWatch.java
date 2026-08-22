package cn.nubia.gameassist.utils;

import android.R;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.ParcelFileDescriptor;
import android.os.SystemProperties;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import cn.nubia.gameassist.utils.RecycleWatch;
import com.zte.gameassist.AbsGameAssistToken;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ContextWrapper;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* loaded from: classes.dex */
public class RecycleWatch implements Runnable {

    /* renamed from: k, reason: collision with root package name */
    public static boolean f7675k = SystemProperties.getBoolean("persist.sys.gameassist_debug", false);

    /* renamed from: l, reason: collision with root package name */
    private static final DateFormat f7676l = new SimpleDateFormat("HH:mm:ss.SSS");

    /* renamed from: m, reason: collision with root package name */
    private static boolean f7677m;

    /* renamed from: n, reason: collision with root package name */
    private static volatile RecycleWatch f7678n;

    /* renamed from: c, reason: collision with root package name */
    private final Handler f7679c;

    /* renamed from: j, reason: collision with root package name */
    private Runnable f7682j = new AnonymousClass1();

    /* renamed from: h, reason: collision with root package name */
    private final Map f7680h = new HashMap();

    /* renamed from: i, reason: collision with root package name */
    private final Map f7681i = new HashMap();

    /* renamed from: cn.nubia.gameassist.utils.RecycleWatch$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void b(PrintWriter printWriter, String str, Count count, Class cls, WatchList watchList) {
            printWriter.println(str + cls + "[");
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append("    ");
            int d2 = watchList.d(printWriter, sb.toString());
            printWriter.println(str + "] count=" + d2);
            count.a(d2);
        }

        @Override // java.lang.Runnable
        public void run() {
            System.gc();
            int size = RecycleWatch.this.f7681i.size();
            StringWriter stringWriter = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(stringWriter);
            if (size <= 0) {
                GaLog.e("watchObj", "mem good");
                return;
            }
            printWriter.println("ObjectWatch:[");
            final Count count = new Count();
            final String str = "    ";
            RecycleWatch.this.f7681i.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.utils.j
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RecycleWatch.AnonymousClass1.b(printWriter, str, count, (Class) obj, (RecycleWatch.WatchList) obj2);
                }
            });
            printWriter.println("]" + count);
            RecycleWatch.m(null, printWriter, "");
            GaLog.k("watchObj", stringWriter.getBuffer().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Count {

        /* renamed from: a, reason: collision with root package name */
        int f7684a;

        void a(int i2) {
            this.f7684a += i2;
        }

        public String toString() {
            return "AllCount=" + this.f7684a;
        }

        private Count() {
            this.f7684a = 0;
        }
    }

    static class TestRecyleView extends View {
        public TestRecyleView(Context context) {
            super(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class WatchList {

        /* renamed from: a, reason: collision with root package name */
        private List f7685a;

        /* renamed from: b, reason: collision with root package name */
        private int f7686b;

        /* renamed from: c, reason: collision with root package name */
        private int f7687c;

        private boolean c(Object obj) {
            for (int i2 = 0; i2 < this.f7685a.size(); i2++) {
                if (((WatchObject) this.f7685a.get(i2)).f7690b.get() == obj) {
                    return true;
                }
            }
            return false;
        }

        public void a(Object obj) {
            if (c(obj)) {
                return;
            }
            this.f7685a.add(new WatchObject(obj));
        }

        public boolean b() {
            int size = this.f7685a.size();
            int i2 = 0;
            while (i2 < size) {
                if (((WatchObject) this.f7685a.get(i2)).b()) {
                    this.f7685a.remove(i2);
                    i2--;
                    size--;
                }
                i2++;
            }
            if (size > this.f7686b) {
                this.f7687c++;
            } else {
                this.f7687c = 0;
            }
            if (this.f7687c > 10) {
                Object obj = ((WatchObject) this.f7685a.get(0)).f7690b.get();
                GaLog.b("watchObj", "mem out:size=" + size + " class=" + (obj != null ? obj.getClass().getName() : ""));
            }
            return size == 0;
        }

        public int d(PrintWriter printWriter, String str) {
            int size = this.f7685a.size() - 1;
            int i2 = 0;
            while (size >= 0) {
                if (((WatchObject) this.f7685a.get(size)).b()) {
                    this.f7685a.remove(size);
                    size--;
                } else {
                    i2++;
                    printWriter.println(str + this.f7685a.get(size));
                }
                size--;
            }
            return i2;
        }

        public int e() {
            return this.f7686b;
        }

        public int f() {
            for (int size = this.f7685a.size() - 1; size >= 0; size--) {
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        break;
                    }
                    if (((WatchObject) this.f7685a.get(size)).f7690b.get() == ((WatchObject) this.f7685a.get(size)).f7690b.get()) {
                        this.f7685a.remove(size);
                        break;
                    }
                    i2++;
                }
            }
            return this.f7685a.size();
        }

        public void g(int i2) {
            this.f7686b = i2;
        }

        private WatchList(int i2) {
            this.f7685a = new ArrayList();
            this.f7686b = i2;
        }
    }

    private class WatchObject {

        /* renamed from: a, reason: collision with root package name */
        private final long f7689a;

        /* renamed from: b, reason: collision with root package name */
        private final WeakReference f7690b;

        /* renamed from: c, reason: collision with root package name */
        private final String f7691c;

        public boolean b() {
            return this.f7690b.get() == null;
        }

        public String toString() {
            Object obj = this.f7690b.get();
            if (obj == null) {
                return "";
            }
            return this.f7691c + "(" + (System.currentTimeMillis() - this.f7689a) + "ms) ->  : " + obj;
        }

        private WatchObject(RecycleWatch recycleWatch, Object obj) {
            this.f7690b = new WeakReference(obj);
            long currentTimeMillis = System.currentTimeMillis();
            this.f7689a = currentTimeMillis;
            this.f7691c = RecycleWatch.q(currentTimeMillis);
        }
    }

    private RecycleWatch() {
        HandlerThread handlerThread = new HandlerThread("watch_recycle");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper());
        this.f7679c = handler;
        handler.postDelayed(this, 5000L);
    }

    private void h(final Object obj, final int i2) {
        this.f7679c.post(new Runnable() { // from class: cn.nubia.gameassist.utils.i
            @Override // java.lang.Runnable
            public final void run() {
                RecycleWatch.this.r(obj, i2);
            }
        });
    }

    public static void i(Object obj) {
        j(obj, 1);
    }

    public static void j(Object obj, int i2) {
        if (f7675k) {
            p().h(obj, i2);
        }
    }

    public static void m(FileDescriptor fileDescriptor, PrintWriter printWriter, String str) {
        if (f7675k) {
            synchronized (RecycleWatch.class) {
                p().x(printWriter, str);
            }
        }
        p().f7679c.post(new Runnable() { // from class: cn.nubia.gameassist.utils.c
            @Override // java.lang.Runnable
            public final void run() {
                RecycleWatch.s();
            }
        });
    }

    private void n() {
        File file = new File(ContextWrapper.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "gameassist");
        if (!file.isDirectory()) {
            file.mkdirs();
            GaLog.k("watchObj", "mkdir " + file.getAbsolutePath());
        }
        Context context = ContextWrapper.getContext();
        try {
            try {
                System.gc();
                Thread.sleep(10L);
                File file2 = new File(file, "GameAssist.hprof");
                if (!file2.exists()) {
                    file2.createNewFile();
                }
                GaLog.k("watchObj", "dumpHprofData start  :  " + file2);
                Debug.class.getMethod("dumpHprofData", String.class).invoke(null, file2.getAbsolutePath());
                GaLog.k("watchObj", "dumpHprofData end");
                GaLog.k("watchObj", "description start");
                FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "GameAssist.description"));
                try {
                    PrintWriter printWriter = new PrintWriter(fileOutputStream);
                    printWriter.print("Build:");
                    printWriter.println(SystemProperties.get("ro.build.description"));
                    printWriter.println();
                    InputStream inputStream = Runtime.getRuntime().exec("dumpsys meminfo cn.nubia.gameassist").getInputStream();
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream.read(bArr, 0, 4096);
                        if (read <= 0) {
                            break;
                        } else {
                            fileOutputStream.write(bArr, 0, read);
                        }
                    }
                    inputStream.close();
                    fileOutputStream.flush();
                    printWriter.println();
                    GaLog.k("watchObj", "dumpService meminfo start");
                    Debug.class.getMethod("dumpService", String.class, FileDescriptor.class, String[].class).invoke(null, "meminfo", fileOutputStream.getFD(), new String[]{context.getPackageName()});
                    printWriter.flush();
                    printWriter.println();
                    Object newInstance = Class.forName("android.os.Debug$MemoryInfo").getConstructor(null).newInstance(null);
                    Debug.class.getMethod("getMemoryInfo", newInstance.getClass()).invoke(null, newInstance);
                    printWriter.println(newInstance.getClass().getMethod("getMemoryStats", null).invoke(newInstance, null).toString());
                    printWriter.close();
                    fileOutputStream.close();
                    GaLog.k("watchObj", "dumpService meminfo end");
                    NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
                    NotificationChannel notificationChannel = new NotificationChannel("leak", "Leak Alerts", 4);
                    notificationChannel.enableVibration(true);
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationManager.notify("watchObj", 0, new Notification.Builder(context, notificationChannel.getId()).setAutoCancel(true).setShowWhen(true).setContentTitle("Memory leak detected").setContentText("Game assist dump meminfo done").setSmallIcon(R.mipmap.sym_def_app_icon).build());
                } catch (Throwable th) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Exception e2) {
                GaLog.k("watchObj", "description error:" + e2.getMessage());
                e2.printStackTrace();
            }
            GaLog.k("watchObj", "description end");
        } finally {
            GaLog.k("watchObj", "description end");
        }
    }

    private void o() {
        try {
            if (SystemMgr.f16555p != null) {
                Bundle bundle = new Bundle();
                File file = new File(ContextWrapper.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "gameassist");
                if (!file.isDirectory()) {
                    file.mkdirs();
                    GaLog.k("watchObj", "mkdir " + file.getAbsolutePath());
                }
                File file2 = new File(file, "service.hprof");
                if (!file2.exists()) {
                    file2.createNewFile();
                    GaLog.k("watchObj", "createNewFile " + file2.getAbsolutePath());
                }
                bundle.putParcelable("fileDescriptor", ParcelFileDescriptor.open(file2, WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_TRUSTED_OVERLAY));
                bundle.putString(AbsGameAssistToken.BUNDLE_KEY_NAME, "persist.sys.debug_dump_service_hprof_file");
                bundle.putString(AbsGameAssistToken.BUNDLE_KEY_VALUE, "/data/system/service.hprof");
                SystemMgr.f16555p.invake("set_prop", bundle, null);
                SystemMgr.f16555p.invake("dumpSystemHprof", bundle, null);
                GaLog.a("watchObj", "dumpSystemHprof " + file2);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static RecycleWatch p() {
        if (f7678n == null) {
            synchronized (RecycleWatch.class) {
                try {
                    if (f7678n == null) {
                        f7678n = new RecycleWatch();
                    }
                } finally {
                }
            }
        }
        return f7678n;
    }

    public static String q(long j2) {
        return f7676l.format(new Date(j2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(Object obj, int i2) {
        WatchList watchList;
        if (obj != null) {
            Class<?> cls = obj.getClass();
            synchronized (RecycleWatch.class) {
                try {
                    if (this.f7680h.containsKey(cls)) {
                        watchList = (WatchList) this.f7680h.get(cls);
                    } else {
                        WatchList watchList2 = new WatchList(i2);
                        this.f7680h.put(cls, watchList2);
                        watchList = watchList2;
                    }
                    if (i2 > watchList.e()) {
                        watchList.g(i2);
                    }
                    watchList.a(obj);
                } finally {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void s() {
        if (f7677m) {
            return;
        }
        if (Settings.Global.getInt(ContextWrapper.getContext().getContentResolver(), "game_assist_dump_hprof", 0) == 1) {
            try {
                f7677m = true;
                p().n();
                p().o();
            } finally {
                f7677m = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void t(PrintWriter printWriter, String str, Count count, Class cls, WatchList watchList) {
        printWriter.println(str + cls + "[");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("    ");
        int d2 = watchList.d(printWriter, sb.toString());
        printWriter.println(str + "] count=" + d2);
        count.a(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void u(PrintWriter printWriter, String str, Count count, Class cls, WatchList watchList) {
        watchList.f();
        printWriter.println(str + cls + "[");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("    ");
        int d2 = watchList.d(printWriter, sb.toString());
        printWriter.println(str + "] count=" + d2);
        count.a(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void v(List list, Class cls, WatchList watchList) {
        if (watchList.b()) {
            list.add(cls);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w(Class cls) {
        this.f7680h.remove(cls);
    }

    private void x(final PrintWriter printWriter, String str) {
        synchronized (RecycleWatch.class) {
            try {
                int size = this.f7680h.size();
                final Count count = new Count();
                if (size > 0) {
                    printWriter.println(str + "RecycleWatch:[");
                    final String str2 = str + "    ";
                    this.f7680h.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.utils.g
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            RecycleWatch.t(printWriter, str2, count, (Class) obj, (RecycleWatch.WatchList) obj2);
                        }
                    });
                    printWriter.println(str + "]" + count);
                }
                if (this.f7681i.size() > 0) {
                    printWriter.println("remove ObjectWatch:[");
                    final String str3 = "    ";
                    final Count count2 = new Count();
                    this.f7681i.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.utils.h
                        @Override // java.util.function.BiConsumer
                        public final void accept(Object obj, Object obj2) {
                            RecycleWatch.u(printWriter, str3, count2, (Class) obj, (RecycleWatch.WatchList) obj2);
                        }
                    });
                    printWriter.println("]" + count2);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static void y(WindowManager windowManager, View view) {
        GaLog.e("watchObj", "removeViewAndCheck root=" + view);
        windowManager.removeView(view);
        if (f7675k) {
            if (view instanceof ViewGroup) {
                TestRecyleView testRecyleView = new TestRecyleView(view.getContext());
                testRecyleView.setId(-16711936);
                ((ViewGroup) view).addView(testRecyleView);
            }
            p().k(view);
            System.gc();
        }
    }

    public void k(View view) {
        p().l(view);
        this.f7679c.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.utils.f
            @Override // java.lang.Runnable
            public final void run() {
                System.gc();
            }
        }, 1000L);
        this.f7679c.postDelayed(this.f7682j, 5000L);
    }

    public void l(View view) {
        WatchList watchList;
        if (view != null) {
            Class<?> cls = view.getClass();
            if (this.f7681i.containsKey(cls)) {
                watchList = (WatchList) this.f7681i.get(cls);
            } else {
                WatchList watchList2 = new WatchList(100);
                this.f7681i.put(cls, watchList2);
                watchList = watchList2;
            }
            watchList.a(view);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                    View childAt = viewGroup.getChildAt(childCount);
                    viewGroup.removeView(childAt);
                    l(childAt);
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f7679c.removeCallbacks(this);
        synchronized (RecycleWatch.class) {
            final ArrayList arrayList = new ArrayList();
            this.f7680h.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.utils.d
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    RecycleWatch.v(arrayList, (Class) obj, (RecycleWatch.WatchList) obj2);
                }
            });
            synchronized (RecycleWatch.class) {
                arrayList.forEach(new Consumer() { // from class: cn.nubia.gameassist.utils.e
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        RecycleWatch.this.w((Class) obj);
                    }
                });
            }
            this.f7679c.postDelayed(this, 5000L);
        }
        this.f7679c.postDelayed(this, 5000L);
    }
}
