package cn.nubia.nbgame.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import cn.nubia.componentsdk.PayComponentClient;
import cn.nubia.componentsdk.until.HttpCallbackLister;
import cn.nubia.componentsdk.until.PayLog;
import cn.nubia.nbgame.sdk.entities.AppInfo;
import cn.nubia.nbgame.sdk.entities.FcmInfo;
import cn.nubia.nbgame.sdk.interfaces.CallbackListener;
import cn.nubia.nbgame.sdk.interfaces.ListenerManager;
import cn.nubia.nbgame.sdk.interfaces.OrderConfirmCallbackListener;
import cn.nubia.nbgame.sdk.receiver.PackageReceiver;
import cn.nubia.nbgame.sdk.service.GameService;
import cn.nubia.nbgame.sdk.service.ResponseBroadcastReceiver;
import cn.nubia.nbgame.sdk.util.ApkUtils;
import cn.nubia.nbgame.sdk.util.NeoLog;
import cn.nubia.nbgame.sdk.util.NetUtil;
import cn.nubia.nbgame.sdk.util.OrderStorage;
import cn.nubia.nbgame.sdk.util.PackageUtil;
import cn.nubia.nbgame.sdk.util.SharedPsHelps;
import cn.nubia.nbgame.sdk.util.ThreeDesUtil;
import com.zte.distbus.basetransfer.Constants;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class GameInnerSdk {

    /* renamed from: p, reason: collision with root package name */
    private static GameInnerSdk f8209p = null;

    /* renamed from: q, reason: collision with root package name */
    public static Context f8210q = null;

    /* renamed from: r, reason: collision with root package name */
    public static int f8211r = 0;

    /* renamed from: s, reason: collision with root package name */
    public static boolean f8212s = false;
    public static boolean t = false;
    public static boolean u = true;
    public static boolean v = true;
    public static boolean w = false;
    public static ArrayList x = new ArrayList();
    public static int y = 0;
    public static boolean z;

    /* renamed from: i, reason: collision with root package name */
    private String f8221i;

    /* renamed from: j, reason: collision with root package name */
    private int f8222j;

    /* renamed from: a, reason: collision with root package name */
    private boolean f8213a = false;

    /* renamed from: b, reason: collision with root package name */
    private boolean f8214b = false;

    /* renamed from: c, reason: collision with root package name */
    private AppInfo f8215c = null;

    /* renamed from: d, reason: collision with root package name */
    private String f8216d = null;

    /* renamed from: e, reason: collision with root package name */
    private String f8217e = null;

    /* renamed from: f, reason: collision with root package name */
    private String f8218f = null;

    /* renamed from: g, reason: collision with root package name */
    private String f8219g = null;

    /* renamed from: h, reason: collision with root package name */
    private String f8220h = null;

    /* renamed from: k, reason: collision with root package name */
    public boolean f8223k = true;

    /* renamed from: l, reason: collision with root package name */
    BroadcastReceiver f8224l = new BroadcastReceiver() { // from class: cn.nubia.nbgame.sdk.GameInnerSdk.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NeoLog.g("GameInnerSdk", "mBroadcastReceiver register install broadcast!..........");
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                NeoLog.g("GameInnerSdk", encodedSchemeSpecificPart + " is uninstalled");
                NeoLog.g("GameInnerSdk", "neoGameVersionCode is:" + GameInnerSdk.f8211r);
                if ("cn.nubia.nbgame".equals(encodedSchemeSpecificPart)) {
                    if (GameInnerSdk.f8211r == 0) {
                        GameInnerSdk.f8211r = GameInnerSdk.this.p(GameInnerSdk.f8210q);
                    } else {
                        int p2 = GameInnerSdk.this.p(GameInnerSdk.f8210q);
                        NeoLog.g("GameInnerSdk", "newVersion is:" + p2);
                        if (p2 > GameInnerSdk.f8211r) {
                            GameInnerSdk.t = true;
                            GameInnerSdk.f8211r = p2;
                        }
                    }
                }
                NeoLog.g("GameInnerSdk", "mBroadcastReceiver register install broadcast! PACKAGE_ADDED...............");
            }
        }
    };

    /* renamed from: m, reason: collision with root package name */
    BroadcastReceiver f8225m = new BroadcastReceiver() { // from class: cn.nubia.nbgame.sdk.GameInnerSdk.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            NeoLog.g("GameInnerSdk", "mFcmBroadcastReceiver register install broadcast!..........");
        }
    };

    /* renamed from: n, reason: collision with root package name */
    private boolean f8226n = false;

    /* renamed from: o, reason: collision with root package name */
    private ResponseBroadcastReceiver f8227o = new ResponseBroadcastReceiver();

    /* renamed from: cn.nubia.nbgame.sdk.GameInnerSdk$6, reason: invalid class name */
    class AnonymousClass6 implements HttpCallbackLister<String> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Bundle f8230a;

        /* renamed from: b, reason: collision with root package name */
        final /* synthetic */ OrderConfirmCallbackListener f8231b;

        /* renamed from: c, reason: collision with root package name */
        final /* synthetic */ Context f8232c;

        @Override // cn.nubia.componentsdk.until.HttpCallbackLister
        public void b(int i2, String str) {
            PayLog.b("GameInnerSdk", "orderConfirm onFailure: " + i2 + ", " + str);
        }

        @Override // cn.nubia.componentsdk.until.HttpCallbackLister
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void a(String str) {
            PayLog.b("GameInnerSdk", "orderConfirm response: " + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                int optInt = jSONObject.optInt("code");
                if (optInt == 0) {
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject != null) {
                        int optInt2 = optJSONObject.optInt("payStatus");
                        String string = this.f8230a.getString("cp_order_id");
                        if (optInt2 == 1) {
                            this.f8231b.a(0, string, this.f8230a);
                        } else {
                            OrderStorage.c(this.f8232c).a(string);
                        }
                    }
                } else {
                    String optString = jSONObject.optString("message");
                    PayLog.b("GameInnerSdk", "orderConfirm error: " + optInt + ", " + optString);
                    this.f8231b.a(-1, optString, this.f8230a);
                }
            } catch (JSONException e2) {
                PayLog.b("GameInnerSdk", "orderConfirm parseError: " + e2.getMessage());
                this.f8231b.a(-1, e2.getMessage(), this.f8230a);
            }
        }
    }

    public static class ActivityLifecycleCallbacksImpl implements Application.ActivityLifecycleCallbacks {

        /* renamed from: c, reason: collision with root package name */
        public static Context f8233c;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            NeoLog.g("GameInnerSdk", "onActivityStarted");
            ArrayList arrayList = GameInnerSdk.x;
            if (arrayList != null) {
                arrayList.add(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            GameInnerSdk.x.remove(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            NeoLog.g("GameInnerSdk", "onActivityPaused");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            NeoLog.g("GameInnerSdk", "onActivityResumed isFcm is:" + FcmInfo.isFcmStatus);
            NeoLog.g("GameInnerSdk", "onActivityResumed");
            if (FcmInfo.isFcmStatus) {
                GameSdk.c(GameInnerSdk.f8210q);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            GameInnerSdk.y++;
            NeoLog.g("GameInnerSdk", "onActivityStarted foregroundFlag is:" + GameInnerSdk.y);
            NeoLog.g("GameInnerSdk", "onActivityStarted isFcm is:" + FcmInfo.isFcmStatus);
            Context context = f8233c;
            if (context != null) {
                GameSdk.e(context);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            Context context;
            NeoLog.g("GameInnerSdk", "onActivityStopped");
            int i2 = GameInnerSdk.y - 1;
            GameInnerSdk.y = i2;
            if (i2 != 0 || (context = f8233c) == null) {
                return;
            }
            GameSdk.d(context);
        }
    }

    public class GetVersionTimer extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onFinish() {
            NeoLog.g("GameInnerSdk", "onFinish.....");
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j2) {
            Context context = GameInnerSdk.f8210q;
            if (context == null) {
                NeoLog.g("GameInnerSdk", "mContext is null");
                return;
            }
            NeoLog.g("GameInnerSdk", "....................neogame versionCode is:" + ApkUtils.o(context).s());
        }
    }

    private GameInnerSdk() {
    }

    private void M(Context context) {
        NeoLog.l("GameInnerSdk", "start GameService");
        try {
            context.startService(new Intent(context, (Class<?>) GameService.class));
        } catch (Exception e2) {
            NeoLog.l("GameInnerSdk", "捕获到后台启动服务时失败的错误" + e2.getMessage());
            e2.printStackTrace();
        }
    }

    public static void f() {
        Iterator it = x.iterator();
        while (it.hasNext()) {
            ((Activity) it.next()).finish();
        }
        x.clear();
    }

    public static synchronized GameInnerSdk j() {
        GameInnerSdk gameInnerSdk;
        synchronized (GameInnerSdk.class) {
            try {
                if (f8209p == null) {
                    f8209p = new GameInnerSdk();
                }
                gameInnerSdk = f8209p;
            } catch (Throwable th) {
                throw th;
            }
        }
        return gameInnerSdk;
    }

    public static String o() {
        return "2.1.9.0603";
    }

    private boolean t(Context context, String str, String str2, boolean z2, CallbackListener callbackListener) {
        Intent intent;
        if (callbackListener != null) {
            if (z2 && !s()) {
                callbackListener.a(11, null);
                return false;
            }
            if (ApkUtils.o(context).u(callbackListener)) {
                return false;
            }
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("packageName", context.getPackageName());
            bundle.putString("requestType", str2);
            bundle.putByteArray("desKey", ThreeDesUtil.a());
            if ("cn.nubia.account.SETTING_ENTER_ACCOUNT_SECURITY_ACTION".equals(str)) {
                intent = new Intent();
                intent.setClassName("cn.nubia.accounts", "cn.nubia.accounts.details.account.PersonalInformationActivity");
            } else {
                intent = new Intent(str);
            }
            intent.putExtras(bundle);
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    private boolean u(Context context, String str, String str2) {
        Intent intent;
        if (!ApkUtils.o(context).v()) {
            return false;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putString("packageName", context.getPackageName());
            bundle.putString("requestType", str2);
            bundle.putByteArray("desKey", ThreeDesUtil.a());
            if ("cn.nubia.account.SETTING_ENTER_ACCOUNT_SECURITY_ACTION".equals(str)) {
                intent = new Intent();
                intent.setClassName("cn.nubia.accounts", "cn.nubia.accounts.details.account.PersonalInformationActivity");
            } else {
                intent = new Intent(str);
            }
            intent.putExtras(bundle);
            intent.setFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
            context.startActivity(intent);
            return true;
        } catch (Exception e2) {
            NeoLog.f("openActivityNotLogin has error ");
            e2.printStackTrace();
            return false;
        }
    }

    private void y(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        int i2 = Build.VERSION.SDK_INT;
        if (i2 > 33) {
            context.getApplicationContext().registerReceiver(new PackageReceiver(), intentFilter, 2);
        } else {
            context.getApplicationContext().registerReceiver(new PackageReceiver(), intentFilter);
        }
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(context.getPackageName() + ".Response");
        if (i2 > 33) {
            context.getApplicationContext().registerReceiver(this.f8227o, intentFilter2, 2);
        } else {
            context.getApplicationContext().registerReceiver(this.f8227o, intentFilter2);
        }
    }

    public boolean A() {
        return TextUtils.isEmpty(this.f8221i);
    }

    public void B(AppInfo appInfo) {
        this.f8215c = appInfo;
    }

    public void C(String str) {
        this.f8220h = str;
    }

    public void D(String str) {
        this.f8217e = str;
    }

    public void E(boolean z2) {
        this.f8214b = z2;
    }

    public void F(int i2) {
        this.f8222j = i2;
    }

    public void G(String str) {
        this.f8219g = str;
    }

    public void H(String str) {
        this.f8221i = str;
    }

    public void I(String str) {
        this.f8216d = str;
    }

    public void J(boolean z2) {
        this.f8213a = z2;
    }

    public void K(String str) {
        this.f8218f = str;
    }

    void L(Context context) {
        t(context, "cn.nubia.sdk.activity.InitFloatViewActivity", "requestTypeInitFloatView", true, null);
    }

    public int a(Context context) {
        if (context == null) {
            return -1;
        }
        try {
            return ContextCompat.checkSelfPermission(context, "android.permission.QUERY_ALL_PACKAGES");
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    void b(Context context, CallbackListener callbackListener) {
        if (t(context, "cn.nubia.sdk.activity.CheckRealIdentityActivity", "requestTypeRealIdentity", true, callbackListener)) {
            ListenerManager.k(callbackListener);
            L(context);
        }
    }

    public void c() {
        J(false);
        G(null);
        I(null);
        K(null);
        H(null);
        D(null);
        C(null);
        F(0);
    }

    public void d() {
        E(false);
    }

    public void e(final Activity activity, final HashMap hashMap, final CallbackListener callbackListener) {
        if (!s() || A()) {
            callbackListener.a(11, "未登录");
            return;
        }
        NeoLog.g("GameInnerSdk", "isCert is:" + this.f8222j);
        if (this.f8222j == 0) {
            b(activity, new CallbackListener<Bundle>() { // from class: cn.nubia.nbgame.sdk.GameInnerSdk.4
                @Override // cn.nubia.nbgame.sdk.interfaces.CallbackListener
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void a(int i2, Bundle bundle) {
                    if (i2 != 0) {
                        callbackListener.a(2302, "用户跳过实名制");
                    } else {
                        GameInnerSdk.this.F(1);
                        GameInnerSdk.this.e(activity, hashMap, callbackListener);
                    }
                }
            });
            return;
        }
        if (hashMap != null) {
            hashMap.put("nubia_channel", "NiuGameCenter");
            String b2 = GameSdk.b();
            if (!TextUtils.isEmpty(b2)) {
                hashMap.put("game_id", b2);
            }
        }
        if (hashMap != null && !hashMap.containsKey("cp_order_sign")) {
            callbackListener.a(30001, "订单签名数据不能为空");
            return;
        }
        String b3 = PackageUtil.b(activity);
        if (!TextUtils.isEmpty(b3)) {
            hashMap.put("gameCl", b3);
        }
        hashMap.put("maigcUser", Boolean.valueOf(w));
        PayComponentClient.a(activity, hashMap, new cn.nubia.componentsdk.constant.CallbackListener<String>() { // from class: cn.nubia.nbgame.sdk.GameInnerSdk.5
            @Override // cn.nubia.componentsdk.constant.CallbackListener
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(int i2, String str) {
                if (i2 == 0 && "1".equals(hashMap.get("local_pay"))) {
                    OrderStorage.c(activity).e(hashMap);
                }
                callbackListener.a(i2, str);
            }
        });
        L(activity);
    }

    public AppInfo g() {
        return this.f8215c;
    }

    public String h() {
        return this.f8220h;
    }

    public String i() {
        return this.f8217e;
    }

    public String k() {
        String str = this.f8219g;
        return str != null ? str : "";
    }

    public String l() {
        return this.f8221i;
    }

    public String m() {
        return this.f8216d;
    }

    public String n() {
        return this.f8218f;
    }

    public int p(Context context) {
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

    public void q(Context context, AppInfo appInfo, CallbackListener callbackListener) {
        if (this.f8223k && context != null && !f8212s) {
            Context applicationContext = context.getApplicationContext();
            f8210q = applicationContext;
            f8211r = p(applicationContext);
            x();
        }
        y(context);
        NeoLog.g("GameInnerSdk", "init sdk-appInfo:" + appInfo);
        M(context);
        if (appInfo == null || TextUtils.isEmpty(appInfo.b()) || appInfo.a() == 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("errorCode", 37);
            callbackListener.a(37, bundle);
            return;
        }
        if (TextUtils.isEmpty(appInfo.e())) {
            appInfo.g(o());
        }
        B(appInfo);
        NeoLog.g("GameInnerSdk", "init sdk-appInfo: isAppInstalled " + appInfo);
        if (v && a(context) != 0) {
            z(30000, callbackListener);
            return;
        }
        if (!ApkUtils.o(context).v()) {
            Bundle bundle2 = new Bundle();
            bundle2.putInt("errorCode", 0);
            callbackListener.a(0, bundle2);
            return;
        }
        NeoLog.g("GameInnerSdk", "init sdk-appInfo: isInitAppInfo" + appInfo);
        if (r()) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("errorCode", 0);
            callbackListener.a(0, bundle3);
            return;
        }
        NeoLog.g("GameInnerSdk", "init sdk-appInfo: ListenerManager" + appInfo);
        ListenerManager.l(callbackListener);
        if (!z) {
            z(10000, callbackListener);
            return;
        }
        if (!ApkUtils.o(context).a()) {
            z(-1102, callbackListener);
            return;
        }
        Bundle bundle4 = new Bundle();
        bundle4.putString("packageName", context.getPackageName());
        bundle4.putString("requestType", "requestTypeInit");
        bundle4.putInt("appId", appInfo.a());
        bundle4.putString("appKey", appInfo.b());
        bundle4.putString("sdkCode", String.valueOf(19));
        bundle4.putString("fcmFlag", "fcmFlag");
        bundle4.putString("sdkName", "2.1.9.0603");
        bundle4.putInt("channelId", appInfo.c());
        bundle4.putInt("orientation", appInfo.d());
        bundle4.putBoolean("mCanUseAdjunct", appInfo.f());
        bundle4.putString("sdkVersion", appInfo.e());
        bundle4.putByteArray("desKey", ThreeDesUtil.a());
        Intent intent = new Intent("cn.nubia.sdk.activity.GameInitActivity");
        intent.setFlags(402653184);
        intent.putExtras(bundle4);
        context.startActivity(intent);
    }

    public boolean r() {
        return this.f8214b;
    }

    public boolean s() {
        return this.f8213a;
    }

    public void v(Context context) {
        NeoLog.f("openFcm isOpenSuccess is: " + u(context, "cn.nubia.sdk.activity.UserMinorNotifyActivity", "requestTypeUserMinor"));
    }

    public void w(final Context context, final CallbackListener callbackListener) {
        NeoLog.g("GameInnerSdk", "openLoginActivity");
        if (callbackListener == null) {
            throw new NullPointerException("CallbackListener is null, please set");
        }
        if (!z) {
            z(10000, callbackListener);
            return;
        }
        if (v && a(context) != 0) {
            z(30000, callbackListener);
            return;
        }
        if (!ApkUtils.o(context).a()) {
            z(-1102, callbackListener);
            return;
        }
        ApkUtils.o(context);
        if (!ApkUtils.y()) {
            z(4000, callbackListener);
            return;
        }
        if (!ApkUtils.o(context).z() && u) {
            z(20000, callbackListener);
            return;
        }
        if (ApkUtils.o(context).u(callbackListener)) {
            this.f8226n = true;
            return;
        }
        if (!this.f8226n && s() && !TextUtils.isEmpty(m()) && !TextUtils.isEmpty(i()) && !TextUtils.isEmpty(l())) {
            Bundle bundle = new Bundle();
            bundle.putString("uid", m());
            bundle.putString("gameId", i());
            bundle.putString("nickName", k());
            bundle.putString("userName", n());
            bundle.putString("avatarPath", h());
            bundle.putString("sessionId", l());
            callbackListener.a(0, bundle);
            return;
        }
        this.f8226n = false;
        if (!r()) {
            NeoLog.l("GameInnerSdk", "openLoginActivity app not success init,so will initSdk auto");
            q(context, g(), new CallbackListener<Bundle>() { // from class: cn.nubia.nbgame.sdk.GameInnerSdk.3
                @Override // cn.nubia.nbgame.sdk.interfaces.CallbackListener
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void a(int i2, Bundle bundle2) {
                    if (i2 == 0) {
                        NeoLog.l("GameInnerSdk", "openLoginActivity auto initSdk success");
                        GameInnerSdk.this.w(context, callbackListener);
                        return;
                    }
                    Bundle bundle3 = new Bundle();
                    bundle3.putInt("errorCode", i2);
                    callbackListener.a(i2, bundle3);
                    NeoLog.o("GameInnerSdk", "openLoginActivity auto initSdk failed:" + i2);
                }
            });
            return;
        }
        ListenerManager.m(callbackListener);
        if (context != null && !NetUtil.a(context)) {
            z(-102, callbackListener);
            return;
        }
        Bundle bundle2 = new Bundle();
        String b2 = PackageUtil.b(context);
        NeoLog.f("doPay-key: login c is:" + b2);
        if (!TextUtils.isEmpty(b2)) {
            bundle2.putString("gameCl", b2);
            if (context != null) {
                SharedPsHelps.c(context, "chl_key", b2);
            } else {
                NeoLog.f("context is null");
            }
        }
        if (PackageUtil.f8324b) {
            bundle2.putBoolean("getClFail", true);
        }
        bundle2.putBoolean("isSdk", true);
        bundle2.putString("packageName", context.getPackageName());
        bundle2.putString("requestType", "requestTypeLogin");
        bundle2.putByteArray("desKey", ThreeDesUtil.a());
        Intent intent = new Intent("cn.nubia.sdk.activity.InitActivity");
        intent.putExtras(bundle2);
        context.startActivity(intent);
    }

    public void x() {
        if (f8210q == null) {
            return;
        }
        NeoLog.g("GameInnerSdk", "register install broadcast!...............");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addDataScheme("package");
        if (Build.VERSION.SDK_INT > 33) {
            f8210q.getApplicationContext().registerReceiver(this.f8224l, intentFilter, 2);
        } else {
            f8210q.getApplicationContext().registerReceiver(this.f8224l, intentFilter);
        }
        f8212s = true;
    }

    public void z(int i2, CallbackListener callbackListener) {
        if (callbackListener != null) {
            callbackListener.a(i2, new Bundle());
        }
    }
}
