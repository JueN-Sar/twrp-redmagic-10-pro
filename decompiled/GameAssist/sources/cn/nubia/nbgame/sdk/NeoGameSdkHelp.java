package cn.nubia.nbgame.sdk;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import cn.nubia.nbgame.sdk.entities.UserGameInfo;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class NeoGameSdkHelp {

    /* renamed from: g, reason: collision with root package name */
    private static volatile NeoGameSdkHelp f8234g = null;

    /* renamed from: h, reason: collision with root package name */
    public static boolean f8235h = true;

    /* renamed from: i, reason: collision with root package name */
    public static int f8236i;

    /* renamed from: j, reason: collision with root package name */
    public static String f8237j;

    /* renamed from: k, reason: collision with root package name */
    public static String f8238k;

    /* renamed from: l, reason: collision with root package name */
    public static String f8239l;

    /* renamed from: a, reason: collision with root package name */
    Handler f8240a = new Handler();

    /* renamed from: b, reason: collision with root package name */
    public String f8241b = "";

    /* renamed from: c, reason: collision with root package name */
    public String f8242c = "";

    /* renamed from: d, reason: collision with root package name */
    public boolean f8243d = true;

    /* renamed from: e, reason: collision with root package name */
    public CopyOnWriteArrayList f8244e = new CopyOnWriteArrayList();

    /* renamed from: f, reason: collision with root package name */
    public boolean f8245f = false;

    private NeoGameSdkHelp() {
    }

    public static NeoGameSdkHelp c() {
        if (f8234g == null) {
            synchronized (NeoGameSdkHelp.class) {
                try {
                    if (f8234g == null) {
                        f8234g = new NeoGameSdkHelp();
                    }
                } finally {
                }
            }
        }
        return f8234g;
    }

    public void a(final Context context, final String str, final int i2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (i2 == 0 || i2 == 1) {
            UserGameInfo userGameInfo = new UserGameInfo(str, i2);
            CopyOnWriteArrayList copyOnWriteArrayList = this.f8244e;
            if (copyOnWriteArrayList == null) {
                return;
            }
            copyOnWriteArrayList.add(userGameInfo);
            Log.d("NeoGameSdkHelp", "gameInfos size is: " + this.f8244e.size());
            if (f8235h) {
                e(context, str, i2);
                this.f8240a.postDelayed(new Runnable() { // from class: cn.nubia.nbgame.sdk.NeoGameSdkHelp.2
                    @Override // java.lang.Runnable
                    public void run() {
                        String str2;
                        Log.d("NeoGameSdkHelp", "gameAppShow run start...");
                        if (NeoGameSdkHelp.this.f8244e != null) {
                            boolean z = false;
                            UserGameInfo userGameInfo2 = null;
                            int i3 = 0;
                            while (true) {
                                if (i3 < NeoGameSdkHelp.this.f8244e.size()) {
                                    if (str != null && (userGameInfo2 = (UserGameInfo) NeoGameSdkHelp.this.f8244e.get(i3)) != null && (str2 = userGameInfo2.pkgName) != null && str2.equals(str)) {
                                        z = true;
                                        break;
                                    }
                                    i3++;
                                } else {
                                    break;
                                }
                            }
                            Log.d("NeoGameSdkHelp", "isContains is: " + z);
                            if (z && userGameInfo2 != null) {
                                NeoGameSdkHelp.this.f8244e.remove(userGameInfo2);
                                Intent intent = new Intent("cn.nubia.sdk.activity.GameHelperActivity");
                                Bundle bundle = new Bundle();
                                bundle.putInt("appId", NeoGameSdkHelp.f8236i);
                                bundle.putString("appKey", NeoGameSdkHelp.f8237j);
                                bundle.putString("appSecret", NeoGameSdkHelp.f8238k);
                                bundle.putString("ghPackageName", NeoGameSdkHelp.f8239l);
                                bundle.putString("game_package", str);
                                bundle.putInt("orientation", i2);
                                intent.putExtras(bundle);
                                intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
                                try {
                                    context.startActivity(intent);
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            Log.d("NeoGameSdkHelp", "post gameInfos size is: " + NeoGameSdkHelp.this.f8244e.size());
                        }
                    }
                }, 2000L);
                return;
            }
            e(context, str, i2);
            this.f8244e.remove(userGameInfo);
            Log.d("NeoGameSdkHelp", "gameInfos size is: " + this.f8244e.size());
        }
    }

    public void b(final Context context, String str) {
        if (context == null) {
            return;
        }
        this.f8245f = false;
        if (!f8235h) {
            Intent intent = new Intent();
            intent.setAction("cn.nubia.game.gamehelp.click");
            intent.putExtra("type", str);
            intent.putExtra("startType", "click");
            context.sendBroadcast(intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setAction("cn.nubia.game.gamehelp.click");
        intent2.putExtra("type", str);
        intent2.putExtra("startType", "click");
        context.sendBroadcast(intent2);
        this.f8240a.postDelayed(new Runnable() { // from class: cn.nubia.nbgame.sdk.NeoGameSdkHelp.3
            @Override // java.lang.Runnable
            public void run() {
                Log.d("NeoGameSdkHelp", "gameAppShow run start...");
                if (!NeoGameSdkHelp.this.f8245f) {
                    Intent intent3 = new Intent("cn.nubia.sdk.activity.GameHelperActivity");
                    Bundle bundle = new Bundle();
                    bundle.putInt("appId", NeoGameSdkHelp.f8236i);
                    bundle.putString("appKey", NeoGameSdkHelp.f8237j);
                    bundle.putString("appSecret", NeoGameSdkHelp.f8238k);
                    bundle.putString("ghPackageName", NeoGameSdkHelp.f8239l);
                    intent3.putExtra("startType", "click");
                    intent3.putExtras(bundle);
                    intent3.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
                    try {
                        context.startActivity(intent3);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                NeoGameSdkHelp.this.f8245f = false;
            }
        }, 2000L);
    }

    public boolean d(Context context) {
        if (context == null) {
            return false;
        }
        try {
            List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
            if (installedPackages != null) {
                for (int i2 = 0; i2 < installedPackages.size(); i2++) {
                    PackageInfo packageInfo = installedPackages.get(i2);
                    if (packageInfo != null && "cn.nubia.nbgame".equals(packageInfo.packageName)) {
                        int i3 = packageInfo.versionCode;
                        Log.d("NeoGameSdkHelp", "neogame versionCode is:" + i3);
                        if (i3 >= 4010) {
                            return true;
                        }
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public void e(Context context, String str, int i2) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("cn.nubia.game.gamehelp.show.action");
        intent.putExtra("appId", f8236i);
        intent.putExtra("appKey", f8237j);
        intent.putExtra("appSecret", f8238k);
        intent.putExtra("ghPackageName", f8239l);
        intent.putExtra("game_package", str);
        intent.putExtra("orientation", i2);
        context.sendBroadcast(intent);
    }

    public void f(Application application) {
        if (application == null) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.nubia.nbgame.gamehelper.action");
        intentFilter.addAction("com.nubia.nbgame.gamehelper.click.action");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: cn.nubia.nbgame.sdk.NeoGameSdkHelp.4
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                CopyOnWriteArrayList copyOnWriteArrayList;
                String str;
                String action = intent.getAction();
                Log.d("NeoGameSdkHelp", "action is: " + action);
                boolean z = true;
                if (!"com.nubia.nbgame.gamehelper.action".equals(action)) {
                    if ("com.nubia.nbgame.gamehelper.click.action".equals(action)) {
                        Log.d("NeoGameSdkHelp", "com.nubia.nbgame.gamehelper.click.action start.................");
                        NeoGameSdkHelp.this.f8245f = true;
                        return;
                    }
                    return;
                }
                String stringExtra = intent.getStringExtra("game_package");
                Log.d("NeoGameSdkHelp", "onReceive: gamePackage is:" + stringExtra);
                if (TextUtils.isEmpty(stringExtra)) {
                    return;
                }
                UserGameInfo userGameInfo = null;
                int i2 = 0;
                while (true) {
                    if (i2 >= NeoGameSdkHelp.this.f8244e.size()) {
                        z = false;
                        break;
                    }
                    userGameInfo = (UserGameInfo) NeoGameSdkHelp.this.f8244e.get(i2);
                    if (userGameInfo != null && (str = userGameInfo.pkgName) != null && str.equals(stringExtra)) {
                        break;
                    } else {
                        i2++;
                    }
                }
                Log.d("NeoGameSdkHelp", "broadcastReceiver isContains is: " + z);
                Log.d("NeoGameSdkHelp", "gameInfos remove  before size is: " + NeoGameSdkHelp.this.f8244e.size());
                if (z && userGameInfo != null && (copyOnWriteArrayList = NeoGameSdkHelp.this.f8244e) != null) {
                    copyOnWriteArrayList.remove(userGameInfo);
                }
                Log.d("NeoGameSdkHelp", "gameInfos remove after size is: " + NeoGameSdkHelp.this.f8244e.size());
            }
        };
        if (Build.VERSION.SDK_INT > 33) {
            application.registerReceiver(broadcastReceiver, intentFilter, 2);
        } else {
            application.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public void g(final Application application, final int i2, final String str, final String str2, final String str3) {
        if (application == null) {
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.nbgame.sdk.NeoGameSdkHelp.1
            @Override // java.lang.Runnable
            public void run() {
                CopyOnWriteArrayList copyOnWriteArrayList = NeoGameSdkHelp.this.f8244e;
                if (copyOnWriteArrayList != null) {
                    copyOnWriteArrayList.clear();
                }
                NeoGameSdkHelp.this.f(application);
                Intent intent = new Intent("cn.nubia.sdk.activity.GameHelperActivity");
                Bundle bundle = new Bundle();
                bundle.putInt("appId", i2);
                NeoGameSdkHelp.f8236i = i2;
                bundle.putString("appKey", str);
                NeoGameSdkHelp.f8237j = str;
                bundle.putString("appSecret", str2);
                NeoGameSdkHelp.f8238k = str2;
                bundle.putString("ghPackageName", str3);
                NeoGameSdkHelp.f8239l = str3;
                intent.putExtras(bundle);
                intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
                try {
                    application.startActivity(intent);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }, 2000L);
    }

    public void h(Context context, ArrayList arrayList) {
        if (context == null || arrayList == null || arrayList.size() == 0) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("cn.nubia.game.gamehelp.delete.all");
        intent.putExtra("game_packages", arrayList);
        context.sendBroadcast(intent);
    }
}
