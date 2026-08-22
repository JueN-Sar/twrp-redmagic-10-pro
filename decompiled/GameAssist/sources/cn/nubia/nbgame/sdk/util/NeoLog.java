package cn.nubia.nbgame.sdk.util;

import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public class NeoLog {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f8315a = false;

    /* renamed from: b, reason: collision with root package name */
    private static HandlerThread f8316b;

    /* renamed from: c, reason: collision with root package name */
    private static Handler f8317c;

    /* renamed from: d, reason: collision with root package name */
    private static RandomAccessFile f8318d;

    /* renamed from: e, reason: collision with root package name */
    private static String f8319e;

    static class WriteLogsTask implements Runnable {

        /* renamed from: c, reason: collision with root package name */
        private String f8320c;

        WriteLogsTask(String str) {
            this.f8320c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            NeoLog.q(this.f8320c);
        }
    }

    static {
        if (m()) {
            HandlerThread handlerThread = new HandlerThread("thread-to-write-logs", 10);
            f8316b = handlerThread;
            handlerThread.start();
            f8317c = new Handler(f8316b.getLooper());
            File j2 = j();
            if (j2 != null) {
                f8319e = j2.getPath();
            }
            n();
        }
    }

    private static String a() {
        return "--------------------------------------------------------------\n" + Build.BRAND + " " + Build.MODEL + "/" + Build.VERSION.INCREMENTAL + " " + Build.VERSION.RELEASE + "(" + Build.VERSION.SDK_INT + ") \n\n--------------------------------------------------------------\n";
    }

    private static String b(String str, String str2) {
        if (str2.length() > 500) {
            str2 = str2.substring(0, 500) + "...";
        }
        return e() + " (" + Process.myPid() + "/" + Thread.currentThread().getId() + ") " + str + " " + str2 + "\n";
    }

    private static String c(String str, String str2, Object... objArr) {
        try {
            if (str.contains("%")) {
                int length = objArr.length;
                Object[] objArr2 = new Object[length + 1];
                objArr2[0] = str2;
                System.arraycopy(objArr, 0, objArr2, 1, length);
                return String.format(str, objArr2);
            }
            StringBuilder sb = new StringBuilder("[");
            sb.append(str);
            sb.append("] ");
            if (str2.contains("%")) {
                sb.append(String.format(str2, objArr));
                return sb.toString();
            }
            sb.append(str2);
            return sb.toString();
        } catch (Exception unused) {
            Log.d("NeoGameSDK", "format exception " + str2);
            return str;
        }
    }

    private static void d() {
        try {
            try {
                RandomAccessFile randomAccessFile = f8318d;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (IOException e2) {
                Log.w("NeoGameSDK", "Neolog close logs file fail, msg : " + e2.getMessage());
            }
        } finally {
            f8318d = null;
        }
    }

    private static String e() {
        return new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US).format(new Date());
    }

    public static void f(String str) {
        if (AppConfig.a() || f8315a) {
            Log.d("NeoGameSDK", str);
        }
    }

    public static void g(String str, String str2) {
        if (AppConfig.a() || f8315a) {
            Log.d("NeoGameSDK", c(str, str2, new Object[0]));
        }
    }

    private static void h() {
        try {
            File file = new File(f8319e);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e2) {
            Log.w("NeoGameSDK", "Neolog delete logs file fail, msg : " + e2.getMessage());
        }
    }

    public static void i(String str, String str2) {
        if (AppConfig.a() || f8315a) {
            Log.e("NeoGameSDK", c(str, str2, new Object[0]));
        }
        if (m()) {
            s("NeoGameSDK", c(str, str2, new Object[0]));
        }
    }

    private static File j() {
        File file;
        Exception e2;
        try {
        } catch (Exception e3) {
            file = null;
            e2 = e3;
        }
        if (!"mounted".equals(Environment.getExternalStorageState())) {
            return null;
        }
        File file2 = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + AppConfig.f8307a);
        file2.mkdirs();
        file = new File(file2, "log.txt");
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        } catch (Exception e5) {
            e2 = e5;
            e2.printStackTrace();
            return file;
        }
        return file;
    }

    public static void k(String str) {
        if (AppConfig.a() || f8315a) {
            Log.i("NeoGameSDK", str);
        }
        if (m()) {
            s("NeoGameSDK", str);
        }
    }

    public static void l(String str, String str2) {
        if (AppConfig.a() || f8315a) {
            Log.i("NeoGameSDK", c(str, str2, new Object[0]));
        }
        if (m()) {
            s("NeoGameSDK", c(str, str2, new Object[0]));
        }
    }

    private static boolean m() {
        return false;
    }

    private static void n() {
        if (f8318d == null) {
            try {
                File j2 = j();
                if (j2 == null) {
                    Log.w("NeoGameSDK", "Neolog creating logs file fail...");
                }
                f8318d = new RandomAccessFile(j2, "rw");
            } catch (Exception e2) {
                d();
                Log.w("NeoGameSDK", "Neolog open logs file fail, msg : " + e2.getMessage());
            }
        }
    }

    public static void o(String str, String str2) {
        if (AppConfig.a() || f8315a) {
            Log.w("NeoGameSDK", c(str, str2, new Object[0]));
        }
        if (m()) {
            s("NeoGameSDK", c(str, str2, new Object[0]));
        }
    }

    private static int p(RandomAccessFile randomAccessFile, byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        if (randomAccessFile == null) {
            return -2;
        }
        try {
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(bArr);
            return bArr.length;
        } catch (IOException e2) {
            Log.w("NeoGameSDK", "Neolog writing logs file fail, msg : " + e2.getMessage());
            return -1;
        }
    }

    public static void q(String str) {
        try {
            if (FileUtils.a(FileUtils.b(f8319e)) < 31457280) {
                return;
            }
            RandomAccessFile randomAccessFile = f8318d;
            if (randomAccessFile != null && randomAccessFile.length() > 5242880) {
                Log.w("NeoGameSDK", "Neolog write logs, but logs file overflows, delete and reopening...");
                h();
                d();
                n();
                r();
                return;
            }
            int p2 = p(f8318d, str.getBytes("utf-8"));
            if (p2 < 0) {
                Log.w("NeoGameSDK", "write logs fail, len : " + p2);
            }
        } catch (Exception e2) {
            Log.w("NeoGameSDK", "write logs fail, msg : " + e2.getMessage());
        }
    }

    private static void r() {
        f8317c.post(new WriteLogsTask(a()));
    }

    private static void s(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        f8317c.post(new WriteLogsTask(b(str, str2)));
    }
}
