package cn.nubia.nbgame.sdk.util;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;
import cn.nubia.nbgame.sdk.GameInnerSdk;
import cn.nubia.nbgame.sdk.interfaces.CallbackListener;
import cn.nubia.nbgame.sdk.interfaces.ListenerManager;
import cn.nubia.nbgame.sdk.ui.ShowActivity;
import com.zte.distbus.basetransfer.Constants;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* loaded from: classes.dex */
public class ApkUtils {

    /* renamed from: e, reason: collision with root package name */
    private static final String f8291e = "ApkUtils";

    /* renamed from: f, reason: collision with root package name */
    private static ApkUtils f8292f;

    /* renamed from: g, reason: collision with root package name */
    private static Context f8293g;

    /* renamed from: a, reason: collision with root package name */
    private String f8294a;

    /* renamed from: b, reason: collision with root package name */
    private Handler f8295b = new Handler() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i2 = message.what;
            if (i2 == 1) {
                ApkUtils.this.j();
                Toast.makeText(ApkUtils.f8293g, "安装完成！", 0).show();
                NeoLog.g(ApkUtils.f8291e, "静默安装完成");
                ApkUtils.this.k();
                return;
            }
            if (i2 != 2) {
                return;
            }
            ApkUtils.this.j();
            NeoLog.g(ApkUtils.f8291e, "静默安装失败");
            ApkUtils.this.k();
        }
    };

    /* renamed from: c, reason: collision with root package name */
    BroadcastReceiver f8296c = new BroadcastReceiver() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
                return;
            }
            String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
            NeoLog.g(ApkUtils.f8291e, encodedSchemeSpecificPart + " is uninstalled");
            if ("cn.nubia.nbgame".equals(encodedSchemeSpecificPart)) {
                Bundle bundle = new Bundle();
                bundle.putString("普通安装成功", "安装插件apk成功");
                ListenerManager.j(0, null);
                ListenerManager.h(39, bundle);
                NeoLog.g(ApkUtils.f8291e, "neoGameVersionCode is:" + GameInnerSdk.f8211r);
                if (GameInnerSdk.f8211r == 0) {
                    GameInnerSdk.f8211r = ApkUtils.this.t(context);
                    return;
                }
                int t = ApkUtils.this.t(context);
                NeoLog.g(ApkUtils.f8291e, "newVersion is:" + t);
                if (t > GameInnerSdk.f8211r) {
                    GameInnerSdk.t = true;
                    GameInnerSdk.f8211r = t;
                }
            }
        }
    };

    /* renamed from: d, reason: collision with root package name */
    AlertDialog f8297d = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void A(CallbackListener callbackListener) {
        try {
            C();
            String str = f8291e;
            NeoLog.g(str, "start normal install!");
            StringBuilder sb = new StringBuilder();
            sb.append(this.f8294a);
            String str2 = File.separator;
            sb.append(str2);
            sb.append("NeoGame.apk");
            File file = new File(sb.toString());
            NeoLog.g(str, "path:" + this.f8294a + str2 + "NeoGame.apk");
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
            intent.addFlags(1);
            intent.setDataAndType(UriUtils.a(f8293g, file), "application/vnd.android.package-archive");
            f8293g.startActivity(intent);
        } catch (Exception e2) {
            NeoLog.i(f8291e, "normal install失败:" + e2.getMessage());
            e2.printStackTrace();
            callbackListener.a(27, new Bundle());
            SPUtils.c(f8293g).f("IS_INSTALL_FAILED_KEY" + n(f8293g), true);
        }
    }

    private void B(boolean z, final CallbackListener callbackListener) {
        String str;
        AlertDialog alertDialog = this.f8297d;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.f8297d.dismiss();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(f8293g);
        if (z) {
            builder.setMessage("游戏中有新版本的“游戏中心组件”，无需耗费流量下载，点击“更新”立即安装。");
            str = "更新";
        } else {
            builder.setMessage("需要安装“游戏中心组件”服务才能使用此功能，点击“确定”立即安装。");
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    callbackListener.a(25, null);
                }
            });
            str = "确定";
        }
        builder.setCancelable(false);
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.6
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                return false;
            }
        });
        builder.setPositiveButton(str, new DialogInterface.OnClickListener() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.7
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
                callbackListener.a(26, null);
                ApkUtils.this.A(callbackListener);
            }
        });
        this.f8297d = builder.show();
    }

    private void C() {
        NeoLog.g(f8291e, "register install broadcast!");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        if (Build.VERSION.SDK_INT > 33) {
            f8293g.getApplicationContext().registerReceiver(this.f8296c, intentFilter, 2);
        } else {
            f8293g.getApplicationContext().registerReceiver(this.f8296c, intentFilter);
        }
    }

    private void E(String str) {
        NeoLog.g(f8291e, str);
        Intent intent = new Intent(f8293g, (Class<?>) ShowActivity.class);
        intent.putExtra("msg", str);
        intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        f8293g.startActivity(intent);
    }

    private void F() {
        new Thread(new Runnable() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.4
            @Override // java.lang.Runnable
            public void run() {
                String l2 = ApkUtils.this.l("pm install -r " + ApkUtils.this.f8294a + File.separator + "NeoGame.apk");
                if (TextUtils.isEmpty(l2) || !l2.contains("Success")) {
                    ApkUtils.this.f8295b.sendEmptyMessage(2);
                } else {
                    ApkUtils.this.f8295b.sendEmptyMessage(1);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ShowActivity showActivity = ShowActivity.f8283h;
        if (showActivity != null) {
            showActivity.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        new Thread(new Runnable() { // from class: cn.nubia.nbgame.sdk.util.ApkUtils.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new File(ApkUtils.this.f8294a + File.separator + "NeoGame.apk").delete();
                } catch (Exception e2) {
                    NeoLog.i(ApkUtils.f8291e, e2.getMessage());
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010d A[Catch: IOException -> 0x0109, TryCatch #6 {IOException -> 0x0109, blocks: (B:71:0x0105, B:62:0x010d, B:64:0x0112), top: B:70:0x0105 }] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0112 A[Catch: IOException -> 0x0109, TRY_LEAVE, TryCatch #6 {IOException -> 0x0109, blocks: (B:71:0x0105, B:62:0x010d, B:64:0x0112), top: B:70:0x0105 }] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r8v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String l(java.lang.String r8) {
        /*
            Method dump skipped, instructions count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.nbgame.sdk.util.ApkUtils.l(java.lang.String):java.lang.String");
    }

    private boolean m() {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = f8293g.getApplicationContext().getAssets().open("NeoGame.apk");
            try {
                try {
                    File file = new File(this.f8294a + File.separator + "NeoGame.apk");
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    fileOutputStream = new FileOutputStream(file);
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e3) {
            e = e3;
            inputStream = null;
        } catch (Throwable th2) {
            th = th2;
            inputStream = null;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (Exception e4) {
                        NeoLog.i(f8291e, "fos close e:" + e4.getMessage());
                    }
                }
            }
            fileOutputStream.close();
            try {
                inputStream.close();
            } catch (Exception e5) {
                NeoLog.i(f8291e, "is close e:" + e5.getMessage());
            }
            return true;
        } catch (Exception e6) {
            fileOutputStream2 = fileOutputStream;
            e = e6;
            NeoLog.g(f8291e, "copy Exception" + e.getMessage());
            e.printStackTrace();
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e7) {
                    NeoLog.i(f8291e, "fos close e:" + e7.getMessage());
                }
            }
            if (inputStream == null) {
                return false;
            }
            try {
                inputStream.close();
                return false;
            } catch (Exception e8) {
                NeoLog.i(f8291e, "is close e:" + e8.getMessage());
                return false;
            }
        } catch (Throwable th3) {
            fileOutputStream2 = fileOutputStream;
            th = th3;
            if (fileOutputStream2 != null) {
                try {
                    fileOutputStream2.close();
                } catch (Exception e9) {
                    NeoLog.i(f8291e, "fos close e:" + e9.getMessage());
                }
            }
            if (inputStream == null) {
                throw th;
            }
            try {
                inputStream.close();
                throw th;
            } catch (Exception e10) {
                NeoLog.i(f8291e, "is close e:" + e10.getMessage());
                throw th;
            }
        }
    }

    public static String n(Context context) {
        PackageInfo q2 = q(context);
        return q2 == null ? "-1" : String.valueOf(q2.versionCode);
    }

    public static synchronized ApkUtils o(Context context) {
        ApkUtils apkUtils;
        synchronized (ApkUtils.class) {
            try {
                f8293g = context;
                if (f8292f == null) {
                    f8292f = new ApkUtils();
                    File externalCacheDir = context.getExternalCacheDir();
                    if (externalCacheDir == null || !externalCacheDir.exists()) {
                        f8292f.f8294a = context.getCacheDir().getAbsolutePath();
                    } else {
                        f8292f.f8294a = externalCacheDir.getAbsolutePath();
                    }
                }
                apkUtils = f8292f;
            } catch (Throwable th) {
                throw th;
            }
        }
        return apkUtils;
    }

    public static int p(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null) {
                NeoLog.g(f8291e, "isInstallNeoGame pinfo.size() is:" + installedPackages.size());
                for (int i2 = 0; i2 < installedPackages.size(); i2++) {
                    PackageInfo packageInfo = installedPackages.get(i2);
                    if (packageInfo != null && "cn.nubia.nbgame".equals(packageInfo.packageName)) {
                        return packageInfo.versionCode;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return 0;
    }

    private static PackageInfo q(Context context) {
        return r(context, context.getApplicationContext().getPackageName());
    }

    private static PackageInfo r(Context context, String str) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo(str, 16384);
        } catch (Exception e2) {
            NeoLog.i(f8291e, e2.getLocalizedMessage());
            return null;
        }
    }

    private boolean w() {
        return f8293g.getApplicationContext().checkPermission("android.permission.INSTALL_PACKAGES", Process.myPid(), Process.myUid()) != -1;
    }

    private boolean x() {
        try {
            InputStream open = f8293g.getApplicationContext().getAssets().open("NeoGame.apk");
            if (open == null) {
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                        NeoLog.i(f8291e, "isExistApk close stream IOException:" + e2.getMessage());
                    }
                }
                return false;
            }
            try {
                open.close();
                return true;
            } catch (IOException e3) {
                NeoLog.i(f8291e, "isExistApk close stream IOException:" + e3.getMessage());
                return true;
            }
        } catch (Exception e4) {
            NeoLog.i(f8291e, "isExistApk Exception:" + e4.getMessage());
            return false;
        }
    }

    public static boolean y() {
        try {
            InputStream open = f8293g.getApplicationContext().getAssets().open("PayComponent.apk");
            if (open == null) {
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                        NeoLog.i(f8291e, "isExistApk close stream IOException:" + e2.getMessage());
                    }
                }
                return false;
            }
            try {
                open.close();
                return true;
            } catch (IOException e3) {
                NeoLog.i(f8291e, "isExistApk close stream IOException:" + e3.getMessage());
                return true;
            }
        } catch (Exception e4) {
            NeoLog.i(f8291e, "isExistApk Exception:" + e4.getMessage());
            return false;
        }
    }

    public boolean D() {
        long currentTimeMillis = System.currentTimeMillis();
        Intent intent = new Intent();
        intent.setAction("cn.nubia.NeoGameV50");
        List<ResolveInfo> queryIntentServices = f8293g.getPackageManager().queryIntentServices(intent, 131072);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        NeoLog.g(f8291e, "noPermissionIsInstalled: " + currentTimeMillis2 + "ms");
        return queryIntentServices != null && queryIntentServices.size() > 0;
    }

    public boolean a() {
        Context context;
        if (!x() || !m() || (context = f8293g) == null) {
            return false;
        }
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(this.f8294a + File.separator + "NeoGame.apk", 1);
        return packageArchiveInfo != null && packageArchiveInfo.versionCode >= 110;
    }

    public int s() {
        try {
            String str = f8291e;
            NeoLog.g(str, "getVersionByProvider: start....");
            int p2 = p(f8293g);
            NeoLog.g(str, "isInstallNeoGame newVersion is:" + p2);
            if (p2 != 0) {
                return p2;
            }
            Bundle call = f8293g.getContentResolver().call(Uri.parse("content://cn.nubia.neogame"), Constants.EXTRA_VERSION, (String) null, (Bundle) null);
            int i2 = call == null ? 0 : call.getInt(Constants.EXTRA_VERSION);
            NeoLog.g(str, "getVersionByProvider version is:" + i2);
            return i2;
        } catch (Exception e2) {
            NeoLog.g(f8291e, "getVersionByProvider has error");
            e2.printStackTrace();
            return 0;
        }
    }

    public int t(Context context) {
        if (context == null) {
            return 0;
        }
        try {
            Bundle call = context.getContentResolver().call(Uri.parse("content://cn.nubia.neogame"), Constants.EXTRA_VERSION, (String) null, (Bundle) null);
            if (call == null) {
                return 0;
            }
            return call.getInt(Constants.EXTRA_VERSION);
        } catch (Exception unused) {
            return 0;
        }
    }

    public boolean u(CallbackListener callbackListener) {
        PackageManager packageManager = f8293g.getPackageManager();
        int s2 = s();
        boolean z = s2 != 0;
        if (SPUtils.c(f8293g).a("IS_INSTALL_FAILED_KEY" + n(f8293g)) && z) {
            return false;
        }
        if (!z && D()) {
            return false;
        }
        if (!x()) {
            callbackListener.a(23, null);
            NeoLog.g(f8291e, "assets目录不存在联运控件安装包");
        } else if (m()) {
            String str = this.f8294a + File.separator + "NeoGame.apk";
            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo != null) {
                NeoLog.g(f8291e, "apkPath:" + str + ",pkgInfo:" + packageArchiveInfo.versionCode);
            } else {
                NeoLog.g(f8291e, "apkPath:" + str + ",pkgInfo:null");
            }
            String str2 = f8291e;
            NeoLog.g(str2, "apkPath exits:" + new File(str).exists());
            NeoLog.g(str2, "versionCodeInstalled1:" + s2);
            if (z && packageArchiveInfo != null && packageArchiveInfo.versionCode <= s2) {
                return false;
            }
            NeoLog.g(str2, "是否可以静默安装：" + w());
            if (w()) {
                E("正在安装联运服务控件");
                F();
            } else if (z) {
                B(true, callbackListener);
            } else {
                B(false, callbackListener);
            }
        } else {
            callbackListener.a(24, null);
            NeoLog.g(f8291e, "assets目录安装包读写错误");
        }
        return true;
    }

    public boolean v() {
        NeoLog.g(f8291e, "isAppInstalled start");
        return s() != 0 || D();
    }

    public boolean z() {
        Context context = f8293g;
        if (context == null) {
            return true;
        }
        boolean a2 = SPUtils.c(context).a("IS_SET_PROVIDER");
        if (a2) {
            return a2;
        }
        if (!m() || TextUtils.isEmpty(this.f8294a)) {
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.f8294a);
        String str = File.separator;
        sb.append(str);
        sb.append("NeoGame.apk");
        File file = new File(sb.toString());
        String str2 = f8291e;
        NeoLog.g(str2, "isSetProvider path:" + this.f8294a + str + "NeoGame.apk");
        if (!file.exists()) {
            return true;
        }
        try {
            NeoLog.i(str2, "UriUtils.file2Uri start..");
            UriUtils.a(f8293g, file);
            NeoLog.i(str2, "UriUtils.file2Uri end..");
            SPUtils.c(f8293g).f("IS_SET_PROVIDER", true);
            return true;
        } catch (Exception e2) {
            NeoLog.i(f8291e, "UriUtils.file2Uri has error..");
            e2.printStackTrace();
            return false;
        }
    }
}
