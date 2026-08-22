package cn.nubia.componentsdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;
import cn.nubia.componentsdk.pay.CallbackListener;
import cn.nubia.componentsdk.pay.PayActivity;
import cn.nubia.componentsdk.pay.PayChannel;
import cn.nubia.componentsdk.pay.SendPayResult;
import cn.nubia.componentsdk.ui.CheckActivity;
import cn.nubia.componentsdk.ui.ShowActivity;
import cn.nubia.componentsdk.until.CommonUtils;
import cn.nubia.componentsdk.until.PayBroadcastReceiver;
import cn.nubia.componentsdk.until.PayLog;
import cn.nubia.componentsdk.until.UriUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
public class PayClientManager {

    /* renamed from: i, reason: collision with root package name */
    private static PayClientManager f5877i = null;

    /* renamed from: j, reason: collision with root package name */
    private static final Object f5878j = new Object();

    /* renamed from: k, reason: collision with root package name */
    private static Context f5879k = null;

    /* renamed from: l, reason: collision with root package name */
    public static boolean f5880l = false;

    /* renamed from: c, reason: collision with root package name */
    private HashMap f5883c;

    /* renamed from: d, reason: collision with root package name */
    private InstallTimer f5884d;

    /* renamed from: f, reason: collision with root package name */
    private String f5886f;

    /* renamed from: a, reason: collision with root package name */
    private String f5881a = "cn.nubia.paycomponent";

    /* renamed from: b, reason: collision with root package name */
    private String f5882b = "cn.nubia.paycomponent.activity.UpgradeActivity";

    /* renamed from: e, reason: collision with root package name */
    private String f5885e = getClass().getSimpleName();

    /* renamed from: g, reason: collision with root package name */
    private Handler f5887g = new Handler(f5879k.getMainLooper()) { // from class: cn.nubia.componentsdk.PayClientManager.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 1");
                    PayClientManager.this.E();
                    break;
                case 2:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 2");
                    PayClientManager.this.J("正在安装努比亚安全支付控件");
                    break;
                case 3:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 3");
                    PayClientManager.this.D(PayClientManager.f5879k);
                    break;
                case 4:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 4");
                    PayClientManager.this.q();
                    Toast.makeText(PayClientManager.f5879k, "安装完成！", 0).show();
                    if (PayClientManager.this.f5883c != null) {
                        PayClientManager payClientManager = PayClientManager.this;
                        payClientManager.F(payClientManager.f5883c);
                    } else {
                        MiscCallbackListener.a(-102, "静默安装完成，丢失数据");
                    }
                    PayClientManager.this.s();
                    break;
                case 5:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 5");
                    PayClientManager.this.q();
                    PayClientManager.this.E();
                    MiscCallbackListener.a(-106, "安全支付控件未安装");
                    break;
                case 6:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 6 5555");
                    PayClientManager.this.H();
                    PayClientManager.this.J("正在升级安全支付控件");
                    PayClientManager.this.f5884d = PayClientManager.this.new InstallTimer(12000L, 2000L);
                    PayClientManager.this.f5884d.start();
                    break;
                case 7:
                    PayLog.b(PayClientManager.this.f5885e, "mHandler msg 7");
                    PayClientManager.this.f5884d.cancel();
                    PayClientManager.this.q();
                    PayClientManager.this.M();
                    Toast.makeText(PayClientManager.f5879k, "升级完成！", 0).show();
                    if (PayClientManager.this.f5883c == null) {
                        MiscCallbackListener.a(-102, "升级完成，请重新发起支付");
                        break;
                    } else {
                        PayClientManager payClientManager2 = PayClientManager.this;
                        payClientManager2.F(payClientManager2.f5883c);
                        break;
                    }
            }
        }
    };

    /* renamed from: h, reason: collision with root package name */
    BroadcastReceiver f5888h = new BroadcastReceiver() { // from class: cn.nubia.componentsdk.PayClientManager.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                if (intent.getDataString().equals("package:" + PayClientManager.this.f5881a)) {
                    PayLog.a(PayClientManager.this.f5885e, intent.getDataString());
                    PayClientManager.this.f5887g.sendEmptyMessage(7);
                }
            }
        }
    };

    /* renamed from: cn.nubia.componentsdk.PayClientManager$9, reason: invalid class name */
    class AnonymousClass9 extends CallbackListener<List<PayChannel>> {
        private static final long serialVersionUID = 1;
        final /* synthetic */ HashMap val$payInfo;

        AnonymousClass9(HashMap hashMap) {
            this.val$payInfo = hashMap;
        }

        @Override // cn.nubia.componentsdk.pay.CallbackListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void a(int i2, List list) {
            PayLog.c(PayClientManager.this.f5885e, "getChannel--------callback");
            if (i2 == 0) {
                if (list == null || list.size() <= 0) {
                    SendPayResult.a(PayClientManager.this.f5881a, 126, "没有当前可使用的支付渠道", PayClientManager.f5879k.getApplicationContext());
                    return;
                }
                PayLog.a(PayClientManager.this.f5885e, "get channel success ! channel:" + list.toString());
                PayClientManager.this.v(this.val$payInfo, (ArrayList) list);
                return;
            }
            if (i2 == 120 || i2 == 110 || i2 == 122) {
                SendPayResult.a(PayClientManager.this.f5881a, 120, "网络不给力，请查看网络设置", PayClientManager.f5879k.getApplicationContext());
                return;
            }
            PayLog.b(PayClientManager.this.f5885e, "get channel errorCode：" + i2);
            SendPayResult.a(PayClientManager.this.f5881a, 126, "获取支付渠道失败", PayClientManager.f5879k.getApplicationContext());
        }
    }

    private class InstallTimer extends CountDownTimer {
        public InstallTimer(long j2, long j3) {
            super(j2, j3);
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            PayLog.a(PayClientManager.this.f5885e, "onFinish start....");
            MiscCallbackListener.a(-110, "升级失败");
            PayClientManager.this.f5884d.cancel();
            PayClientManager.this.M();
            PayClientManager.this.q();
            Toast.makeText(PayClientManager.f5879k, "升级失败，请稍后重试！", 0).show();
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            PayLog.a(PayClientManager.this.f5885e, " silentInstall timer run" + j2);
        }
    }

    private boolean B() {
        try {
            InputStream open = f5879k.getApplicationContext().getAssets().open("PayComponent.apk");
            if (open == null) {
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                        PayLog.b("isExistApk", "isExistApk close stream IOException:" + e2.getMessage());
                    }
                }
                return false;
            }
            try {
                open.close();
                return true;
            } catch (IOException e3) {
                PayLog.b("isExistApk", "isExistApk close stream IOException:" + e3.getMessage());
                return true;
            }
        } catch (IOException e4) {
            PayLog.b("isExistApk", "isExistApk IOException:" + e4.getMessage());
            return false;
        } catch (Exception e5) {
            PayLog.b("isExistApk", "isExistApk Exception:" + e5.getMessage());
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D(Context context) {
        try {
            PayLog.a("pay", "start normal install!");
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(3);
            PayLog.a("pay", "normal install >= 24");
            Uri a2 = UriUtils.a(context, new File(this.f5886f));
            Context context2 = f5879k;
            context2.grantUriPermission(context2.getPackageName(), a2, 1);
            intent.setDataAndType(a2, "application/vnd.android.package-archive");
            f5879k.startActivity(intent);
        } catch (Exception e2) {
            e2.printStackTrace();
            PayLog.b("pay", "normal install:" + e2.getMessage());
            MiscCallbackListener.a(-110, "支付控件安装失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void E() {
        AlertDialog.Builder message = new AlertDialog.Builder(f5879k).setMessage(A(this.f5883c) ? "当前您的支付组件版本较低，需要升级努比亚安全支付服务，才能进行付款。\n\n点击“确定”，立即安装" : "为了您的交易安全，需要安装“努比亚安全支付”服务，才能进行付款。\n\n点击“确定”，立即安装");
        message.setCancelable(true);
        message.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: cn.nubia.componentsdk.PayClientManager.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                return false;
            }
        });
        message.setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: cn.nubia.componentsdk.PayClientManager.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
                PayClientManager.this.f5887g.sendEmptyMessage(3);
            }
        });
        message.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: cn.nubia.componentsdk.PayClientManager.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        message.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F(HashMap hashMap) {
        PayLog.a(this.f5885e, "start open pay app1");
        PayLog.a(this.f5885e, "start open pay app222222");
        Intent intent = new Intent();
        intent.setClassName(this.f5881a, this.f5882b);
        if (hashMap == null) {
            MiscCallbackListener.a(-102, "支付信息为空");
            return;
        }
        intent.putExtra("pay_info", hashMap);
        intent.putExtra("package_name", f5879k.getApplicationContext().getPackageName());
        PayLog.a(this.f5885e, "package_name of SDK1:" + f5879k.getApplicationContext().getPackageName());
        PayBroadcastReceiver.a(f5879k.getApplicationContext(), new cn.nubia.componentsdk.constant.CallbackListener<String>() { // from class: cn.nubia.componentsdk.PayClientManager.8
            private static final long serialVersionUID = 1;

            @Override // cn.nubia.componentsdk.constant.CallbackListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(int i2, String str) {
                PayLog.a(PayClientManager.this.f5885e, "openPayComponentAPP callback s1 = " + str);
                if ("silent_install".equals(str)) {
                    PayClientManager.this.f5887g.sendEmptyMessage(6);
                    PayLog.a(PayClientManager.this.f5885e, "callback s22 = " + str);
                }
            }
        });
        f5879k.startActivity(intent);
        Context context = f5879k;
        if (context instanceof Activity) {
            ((Activity) context).overridePendingTransition(0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        PayLog.a(this.f5885e, "register install broadcast!");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        if (Build.VERSION.SDK_INT > 33) {
            f5879k.getApplicationContext().registerReceiver(this.f5888h, intentFilter, 2);
        } else {
            f5879k.getApplicationContext().registerReceiver(this.f5888h, intentFilter);
        }
    }

    public static void I() {
        f5877i = null;
    }

    private void K() {
        new Thread(new Runnable() { // from class: cn.nubia.componentsdk.PayClientManager.7
            @Override // java.lang.Runnable
            public void run() {
                String a2 = CommonUtils.a("pm install -r -i " + PayClientManager.f5879k.getPackageName() + " " + PayClientManager.this.f5886f);
                String str = PayClientManager.this.f5885e;
                StringBuilder sb = new StringBuilder();
                sb.append("silentInstall CommonUtils.doExec is:");
                sb.append(a2);
                PayLog.b(str, sb.toString());
                if (TextUtils.isEmpty(a2) || !a2.contains("Success")) {
                    PayClientManager.this.f5887g.sendEmptyMessage(5);
                } else {
                    PayClientManager.this.f5887g.sendEmptyMessage(4);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void M() {
        PayLog.a(this.f5885e, "unregister install broadcast!");
        if (this.f5888h != null) {
            f5879k.getApplicationContext().unregisterReceiver(this.f5888h);
        }
    }

    public static void p() {
        PayClientManager payClientManager = f5877i;
        if (payClientManager == null) {
            return;
        }
        payClientManager.t();
    }

    private void r() {
        if (!w()) {
            MiscCallbackListener.a(-108, "assets目录安装包读写错误");
        } else {
            this.f5887g.sendEmptyMessage(1);
            MiscCallbackListener.a(-106, "安全支付控件未安装");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        new Thread(new Runnable() { // from class: cn.nubia.componentsdk.PayClientManager.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    new File(PayClientManager.this.f5886f).delete();
                } catch (Exception e2) {
                    PayLog.b("pay", e2.getMessage());
                }
            }
        }).start();
    }

    private void t() {
        PayLog.b(this.f5885e, "isExistApk：" + B());
        if (!B()) {
            MiscCallbackListener.a(-104, "assets目录不存在支付控件安装包");
            return;
        }
        PayLog.b(this.f5885e, "isCanSilentInstall：" + z());
        if (!z()) {
            r();
            return;
        }
        this.f5887g.sendEmptyMessage(2);
        boolean w = w();
        PayLog.b(this.f5885e, "copyResult is：" + w);
        if (w) {
            K();
            return;
        }
        q();
        PayLog.b("TAG", "isCanSilentInstall：" + z());
        MiscCallbackListener.a(-108, "assets目录安装包读写错误");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v(HashMap hashMap, ArrayList arrayList) {
        PayLog.a(this.f5885e, "start open pay sdk");
        PayLog.a(this.f5885e, "start open pay sdk11 new ");
        Intent intent = new Intent(f5879k, (Class<?>) PayActivity.class);
        if (hashMap == null) {
            MiscCallbackListener.a(-102, "支付信息为空");
            return;
        }
        intent.putExtra("pay_info", hashMap);
        intent.putParcelableArrayListExtra("payChannels", arrayList);
        intent.putExtra("package_name", this.f5881a);
        PayBroadcastReceiver.a(f5879k.getApplicationContext(), new cn.nubia.componentsdk.constant.CallbackListener<String>() { // from class: cn.nubia.componentsdk.PayClientManager.10
            private static final long serialVersionUID = 1;

            @Override // cn.nubia.componentsdk.constant.CallbackListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(int i2, String str) {
                if ("silent_install".equals(str)) {
                    PayClientManager.this.f5887g.sendEmptyMessage(6);
                    PayLog.a(PayClientManager.this.f5885e, "callback s = " + str);
                }
            }
        });
        f5879k.startActivity(intent);
    }

    private boolean w() {
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            inputStream = f5879k.getApplicationContext().getAssets().open("PayComponent.apk");
            try {
                try {
                    File file = new File(this.f5886f);
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
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        try {
                            break;
                        } catch (Exception e3) {
                            PayLog.b("pay", "fos close e:" + e3.getMessage());
                        }
                    }
                }
                fileOutputStream.close();
                try {
                    inputStream.close();
                } catch (Exception e4) {
                    PayLog.b("pay", "is close e:" + e4.getMessage());
                }
                return true;
            } catch (Exception e5) {
                fileOutputStream2 = fileOutputStream;
                e = e5;
                PayLog.b("pay", "copy Exception" + e.getMessage());
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e6) {
                        PayLog.b("pay", "fos close e:" + e6.getMessage());
                    }
                }
                if (inputStream == null) {
                    return false;
                }
                try {
                    inputStream.close();
                    return false;
                } catch (Exception e7) {
                    PayLog.b("pay", "is close e:" + e7.getMessage());
                    return false;
                }
            } catch (Throwable th2) {
                fileOutputStream2 = fileOutputStream;
                th = th2;
                if (fileOutputStream2 != null) {
                    try {
                        fileOutputStream2.close();
                    } catch (Exception e8) {
                        PayLog.b("pay", "fos close e:" + e8.getMessage());
                    }
                }
                if (inputStream == null) {
                    throw th;
                }
                try {
                    inputStream.close();
                    throw th;
                } catch (Exception e9) {
                    PayLog.b("pay", "is close e:" + e9.getMessage());
                    throw th;
                }
            }
        } catch (Exception e10) {
            e = e10;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
        }
    }

    public static PayClientManager y(Activity activity) {
        f5879k = activity;
        if (f5877i == null) {
            synchronized (f5878j) {
                try {
                    if (f5877i == null) {
                        f5877i = new PayClientManager();
                        File externalCacheDir = f5879k.getExternalCacheDir();
                        if (externalCacheDir == null || !externalCacheDir.exists()) {
                            f5877i.f5886f = f5879k.getCacheDir().getAbsolutePath() + File.separator + "PayComponent.apk";
                        } else {
                            f5877i.f5886f = externalCacheDir.getAbsolutePath() + File.separator + "PayComponent.apk";
                        }
                    }
                } finally {
                }
            }
        }
        return f5877i;
    }

    private boolean z() {
        return f5879k.getApplicationContext().checkPermission("android.permission.INSTALL_PACKAGES", Process.myPid(), Process.myUid()) != -1;
    }

    public boolean A(HashMap hashMap) {
        String str = (String) this.f5883c.get("request_type");
        if (str != null) {
            return str.equals("request_type_contract") || str.equals("request_type_query_unContract") || str.equals("request_type_unContract");
        }
        return false;
    }

    public boolean C(HashMap hashMap) {
        if (hashMap == null || !hashMap.containsKey("pay_channel_tag_new") || TextUtils.isEmpty((String) hashMap.get("pay_channel_tag_new")) || !hashMap.containsKey("nubia_channel")) {
            return false;
        }
        String str = (String) hashMap.get("nubia_channel");
        PayLog.a("pay", "channel is:" + str);
        return !TextUtils.isEmpty(str) && "neostore".equals(str);
    }

    public boolean G() {
        PayLog.a("pay", "Build.VERSION.SDK_INT >= Build.VERSION_CODES.M is:true");
        Intent intent = new Intent();
        intent.setClassName("cn.nubia.paycomponent", "cn.nubia.paycomponent.activity.VersionActivity");
        List<ResolveInfo> queryIntentActivities = f5879k.getPackageManager().queryIntentActivities(intent, 131072);
        if (queryIntentActivities == null) {
            PayLog.a("pay", "list == null");
        } else {
            PayLog.a("pay", "list != null");
            PayLog.a("pay", "list.size is:" + queryIntentActivities.size());
        }
        return queryIntentActivities != null && queryIntentActivities.size() > 0;
    }

    public void J(String str) {
        PayLog.a(this.f5885e, "showProgress!");
        Intent intent = new Intent(f5879k, (Class<?>) ShowActivity.class);
        intent.putExtra("msg", str);
        f5879k.startActivity(intent);
    }

    public void L() {
        f5879k.startActivity(new Intent(f5879k, (Class<?>) CheckActivity.class));
    }

    public void q() {
        PayLog.b(this.f5885e, "cancelInstallProgress start....");
        f5880l = true;
        if (ShowActivity.f6052h != null) {
            PayLog.b(this.f5885e, "ShowActivity.sInstance != null");
            ShowActivity.f6052h.finish();
        }
    }

    public void u(HashMap hashMap) {
        this.f5883c = hashMap;
        if (!C(hashMap)) {
            boolean G = G();
            PayLog.a("pay", "isInstall is:" + G);
            PayLog.a("pay", "isInstall isInstall111 is:" + G);
            if (G) {
                F(hashMap);
                return;
            } else {
                PayLog.a("pay", "申请权限");
                L();
                return;
            }
        }
        boolean G2 = G();
        PayLog.a("pay", "isGameCenter isInstall is:" + G2);
        PayLog.a("pay", "isGameCenter isInstall1 is:" + G2);
        if (!G2) {
            L();
            return;
        }
        int x = x();
        PayLog.a("pay", "hasInstallVersion is:" + x);
        if (x >= 60) {
            F(hashMap);
            return;
        }
        boolean B = B();
        boolean w = w();
        if (!B || !w) {
            F(hashMap);
            return;
        }
        PackageInfo packageArchiveInfo = f5879k.getPackageManager().getPackageArchiveInfo(this.f5886f, 1);
        if (packageArchiveInfo == null) {
            F(hashMap);
            return;
        }
        PayLog.a("pay", "apkPackageInfo.versionCode is:" + packageArchiveInfo.versionCode);
        int i2 = packageArchiveInfo.versionCode;
        if (i2 < 60 || i2 < x) {
            F(hashMap);
        } else {
            L();
        }
    }

    public int x() {
        Context context = f5879k;
        if (context == null) {
            return 0;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(this.f5881a, 128);
            if (packageInfo == null) {
                return 0;
            }
            return packageInfo.versionCode;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }
}
