package cn.nubia.multisubscreen.utils;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import cn.nubia.multisubscreen.CastRole;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.mgr.ConnectCodeMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.mgr.MultiSubScreenNotificationMgr;
import cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr;
import cn.nubia.multisubscreen.secondary.SecDeviceDataMgr;
import com.zte.distbus.basetransfer.servicemanager.ServiceUtil;
import com.zte.distbus.basetransfer.servicemanager.model.DeviceChangeCallBack;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.WechatHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MultiSubScreenUtils {

    /* renamed from: c, reason: collision with root package name */
    private static ListedDevice f8173c;

    /* renamed from: f, reason: collision with root package name */
    private static ServiceUtil f8176f;

    /* renamed from: r, reason: collision with root package name */
    private static volatile PerformanceMonitorGameDurationCallback f8188r;

    /* renamed from: s, reason: collision with root package name */
    private static String f8189s;
    private static String t;

    /* renamed from: a, reason: collision with root package name */
    public static final Uri f8171a = Settings.Global.getUriFor("multi_sub_screen_enable");

    /* renamed from: b, reason: collision with root package name */
    public static final Uri f8172b = Settings.Global.getUriFor("gcs_need_kill_game_launcher");

    /* renamed from: d, reason: collision with root package name */
    public static int f8174d = 0;

    /* renamed from: e, reason: collision with root package name */
    public static int f8175e = 1;

    /* renamed from: g, reason: collision with root package name */
    private static CastRole f8177g = CastRole.UN_KNOW;

    /* renamed from: h, reason: collision with root package name */
    static final List f8178h = new ArrayList();

    /* renamed from: i, reason: collision with root package name */
    private static final ArrayList f8179i = new ArrayList();

    /* renamed from: j, reason: collision with root package name */
    private static final MutableLiveData f8180j = new MutableLiveData();

    /* renamed from: k, reason: collision with root package name */
    private static DeviceChangeCallBack f8181k = new DeviceChangeCallBack() { // from class: cn.nubia.multisubscreen.utils.MultiSubScreenUtils.1
        private void a() {
            if (MultiSubScreenUtils.v()) {
                DistributeBusMgr.getInstance().disConnectDevice(MultiSubScreenUtils.f8173c);
            } else {
                ConnectCodeMgr.h().x("SINK_REQUIRED_DISCONNECT_CODE");
            }
            MultiSubScreenUtils.H(null);
        }

        @Override // com.zte.distbus.basetransfer.servicemanager.model.DeviceChangeCallBack
        public void onItemChange(ListedDevice listedDevice) {
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "sDeviceChangeCallBack -> onItemChange deviceId = " + listedDevice.getDeviceId() + ", status = " + listedDevice.getStatus());
            if (MultiSubScreenUtils.f8179i != null && !MultiSubScreenUtils.f8179i.isEmpty()) {
                int i2 = 0;
                while (true) {
                    if (i2 >= MultiSubScreenUtils.f8179i.size()) {
                        break;
                    }
                    if (((ListedDevice) MultiSubScreenUtils.f8179i.get(i2)).getDeviceId().equalsIgnoreCase(listedDevice.getDeviceId())) {
                        MultiSubScreenUtils.f8179i.set(i2, listedDevice);
                        MultiSubScreenUtils.f8180j.m(MultiSubScreenUtils.f8179i);
                        break;
                    }
                    i2++;
                }
            }
            if ((MultiSubScreenUtils.f8173c != null && !MultiSubScreenUtils.f8173c.getDeviceId().equalsIgnoreCase(listedDevice.getDeviceId())) || (MultiSubScreenUtils.f8173c == null && !listedDevice.getDeviceId().equalsIgnoreCase(DistributeBusMgr.getInstance().getSinkDeviceId()))) {
                GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onItemChange device not match the connected device, so not handle!");
                return;
            }
            int status = listedDevice.getStatus();
            if (status == 100 || status == 104) {
                DistributeBusMgr.getInstance().disConnectDevice(listedDevice);
                MultiSubScreenUtils.w(listedDevice.getDeviceId(), 0);
            }
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onItemChange gattConnectedDevice: " + MultiSubScreenUtils.f8173c);
            if (MultiSubScreenUtils.f8173c == null || !listedDevice.getDeviceId().equalsIgnoreCase(MultiSubScreenUtils.f8173c.getDeviceId())) {
                return;
            }
            Iterator it = MultiSubScreenUtils.f8178h.iterator();
            while (it.hasNext()) {
                ((StatusCallback) it.next()).a(listedDevice);
            }
        }

        @Override // com.zte.distbus.basetransfer.servicemanager.model.DeviceChangeCallBack
        public void onListChange(ArrayList arrayList) {
            StringBuilder sb = new StringBuilder();
            sb.append("sDeviceChangeCallBack onListChange list size = ");
            sb.append(arrayList != null ? arrayList.size() : 0);
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", sb.toString());
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "sDeviceChangeCallBack onListChange gattConnectedDevice = " + MultiSubScreenUtils.f8173c);
            MultiSubScreenUtils.f8179i.clear();
            if (arrayList != null && !arrayList.isEmpty()) {
                MultiSubScreenUtils.f8179i.addAll(arrayList);
                Iterator it = arrayList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        ListedDevice listedDevice = (ListedDevice) it.next();
                        if (MultiSubScreenUtils.f8173c != null && MultiSubScreenUtils.f8173c.getDeviceId().equalsIgnoreCase(listedDevice.getDeviceId())) {
                            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onListChange -> gattConnectedDevice equals device");
                            break;
                        }
                    } else if (MultiSubScreenUtils.f8173c != null) {
                        a();
                    }
                }
            } else if (MultiSubScreenUtils.f8173c != null) {
                a();
            }
            MultiSubScreenUtils.f8180j.m(MultiSubScreenUtils.f8179i);
        }
    };

    /* renamed from: l, reason: collision with root package name */
    public static boolean f8182l = true;

    /* renamed from: m, reason: collision with root package name */
    public static boolean f8183m = false;

    /* renamed from: n, reason: collision with root package name */
    public static boolean f8184n = false;

    /* renamed from: o, reason: collision with root package name */
    public static boolean f8185o = false;

    /* renamed from: p, reason: collision with root package name */
    public static boolean f8186p = false;

    /* renamed from: q, reason: collision with root package name */
    static final List f8187q = new ArrayList();
    private static Map u = new HashMap();

    public interface GameStatusCallback {
        default void e(boolean z) {
        }

        default void p(boolean z) {
        }

        default void s(boolean z) {
        }
    }

    public interface PerformanceMonitorGameDurationCallback {
        default void a(long j2) {
        }
    }

    public static synchronized void A(Context context) {
        synchronized (MultiSubScreenUtils.class) {
            GaLog.b("MultiSubScreen_MultiSubScreenUtils", "registerDeviceChangeCallback");
            o(context).registerDeviceChangeCB(f8181k);
        }
    }

    public static synchronized void B(GameStatusCallback gameStatusCallback) {
        synchronized (MultiSubScreenUtils.class) {
            List list = f8187q;
            if (!list.contains(gameStatusCallback)) {
                list.add(gameStatusCallback);
            }
        }
    }

    public static synchronized void C(StatusCallback statusCallback) {
        synchronized (MultiSubScreenUtils.class) {
            List list = f8178h;
            if (!list.contains(statusCallback)) {
                list.add(statusCallback);
            }
        }
    }

    public static void D(boolean z) {
        f8184n = z;
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "sendActiveDisconnect sActiveDisconnect = " + f8184n);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("active_disconnect", z);
            if (v()) {
                PrimaryDeviceDataMgr.C().m0(jSONObject.toString());
            } else {
                SecDeviceDataMgr.f().t(jSONObject.toString());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static void E(boolean z) {
        f8184n = z;
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "setActiveDisconnect sActiveDisconnect = " + f8184n);
        q(z);
    }

    public static void F(CastRole castRole) {
        f8177g = castRole;
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "setCastRole 0 sink 1 source sCastRole = " + f8177g);
    }

    public static void G(String str, String str2) {
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "setConnectDeviceName deviceId = " + str + " , deviceName = " + str2);
        u.put(str, str2);
    }

    public static void H(ListedDevice listedDevice) {
        GaLog.b("MultiSubScreen_MultiSubScreenUtils", "setGattConnectedDevice device = " + listedDevice);
        f8173c = listedDevice;
        DistributeBusMgr.getInstance().setSinkDistributeBus(listedDevice == null ? null : listedDevice.getDeviceId());
    }

    public static void I(PerformanceMonitorGameDurationCallback performanceMonitorGameDurationCallback) {
        f8188r = performanceMonitorGameDurationCallback;
    }

    public static void J(boolean z) {
        f8186p = z;
        for (GameStatusCallback gameStatusCallback : f8187q) {
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "setSourceIsLowPowerMode callback = " + gameStatusCallback);
            gameStatusCallback.p(z);
        }
    }

    public static void K(String str, String str2) {
        f8189s = str;
        t = str2;
    }

    public static void L() {
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "trackMultiSubScreen sTrackPackageName = " + f8189s + ", isSource = " + v());
        if (f8189s == null || t == null) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("app_name", f8189s);
        bundle.putString("app_package_name", t);
        bundle.putString("device_type", v() ? "main" : "secondary");
        NubiaTrackManager.p().x("cn.nubia.gamelauncher", "multi_square_subscreen_used", bundle);
        e();
    }

    public static synchronized void M(GameStatusCallback gameStatusCallback) {
        synchronized (MultiSubScreenUtils.class) {
            List list = f8187q;
            if (list.contains(gameStatusCallback)) {
                list.remove(gameStatusCallback);
            }
        }
    }

    public static synchronized void N(StatusCallback statusCallback) {
        synchronized (MultiSubScreenUtils.class) {
            List list = f8178h;
            if (list.contains(statusCallback)) {
                list.remove(statusCallback);
            }
        }
    }

    public static boolean d() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static void e() {
        f8189s = null;
        t = null;
    }

    public static boolean f() {
        String s2 = SystemMgr.s();
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "currIsSinkActivity curActivity = " + s2);
        return !TextUtils.isEmpty(s2) && s2.contains("cn.nubia.multisubscreen.ui.MultiSubScreenSinkActivity");
    }

    public static String g(Context context, String str) {
        if (str == null || "cn.nubia.gameassist".equals(str)) {
            return null;
        }
        if (WechatHelper.i(str)) {
            return WechatHelper.a().f(str, true);
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0));
            return TextUtils.isEmpty(applicationLabel) ? "" : applicationLabel.toString();
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public static CastRole h() {
        return f8177g;
    }

    public static String i(String str) {
        String str2 = (String) u.get(str);
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "getConnectDeviceName deviceId = " + str + " , connectDeviceName = " + str2);
        return str2;
    }

    public static MutableLiveData j() {
        return f8180j;
    }

    public static ListedDevice k() {
        return f8173c;
    }

    public static ListedDevice l(String str) {
        ArrayList arrayList = f8179i;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ListedDevice listedDevice = (ListedDevice) it.next();
            if (str.equals(listedDevice.getDeviceId())) {
                return listedDevice;
            }
        }
        return null;
    }

    public static String m(Context context) {
        String str = "DSP Device";
        if (context == null) {
            return "DSP Device";
        }
        String string = Settings.System.getString(context.getApplicationContext().getContentResolver(), "system_device_name");
        if (string != null && string.length() > 32) {
            str = string.substring(0, 29) + "...";
        } else if (string != null && !string.isEmpty()) {
            str = string;
        }
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "getLocalDeviceName name = " + str);
        return str;
    }

    public static int n() {
        int intValue = ZteFeature.getInt("DSP_DEVICE_TYPE", 0).intValue();
        return intValue == 0 ? p("ro.vendor.feature.dsp_device_type", 0) : intValue;
    }

    public static synchronized ServiceUtil o(Context context) {
        ServiceUtil serviceUtil;
        synchronized (MultiSubScreenUtils.class) {
            try {
                if (f8176f == null) {
                    f8176f = new ServiceUtil(context, "ceb574816000");
                }
                GaLog.a("MultiSubScreen_MultiSubScreenUtils", "getServiceUtil sServiceUtil = " + f8176f);
                serviceUtil = f8176f;
            } catch (Throwable th) {
                throw th;
            }
        }
        return serviceUtil;
    }

    public static int p(String str, int i2) {
        try {
            return SystemProperties.getInt(str, i2);
        } catch (Exception e2) {
            GaLog.b("MultiSubScreen_MultiSubScreenUtils", "getSystemPropertiesInt exception : " + e2);
            return i2;
        }
    }

    private static void q(boolean z) {
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "handleDisconnectNotification isActiveDisconnect = " + z);
        if (z) {
            MultiSubScreenNotificationMgr.g().d();
        } else {
            MultiSubScreenNotificationMgr.g().b();
        }
        if (v()) {
            f8184n = false;
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "handleDisconnectNotification sActiveDisconnect = " + f8184n);
        }
    }

    public static boolean r(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "fan_state_of_manual", 0) > 0;
    }

    public static boolean s(String str) {
        return "cn.nubia.gameassist".equals(str);
    }

    public static boolean t() {
        if (!ZteFeature.isSupportMultiSubScreen()) {
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "isMultiSubScreenConnectedAsSourceRole not support multi_sub_screen!");
            return false;
        }
        if (f8174d != 2) {
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "isMultiSubScreenConnectedAsSourceRole not connected!");
            return false;
        }
        if (v()) {
            return true;
        }
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "isMultiSubScreenConnectedAsSourceRole not source role!");
        return false;
    }

    public static boolean u() {
        return n() == 4;
    }

    public static boolean v() {
        return f8177g == CastRole.SOURCE;
    }

    public static void w(String str, int i2) {
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onConnectChange deviceId = " + str + " , sConnectStatus = " + f8174d + " ,status = " + i2 + " ,sActiveDisconnect = " + f8184n);
        if (TextUtils.isEmpty(str)) {
            f8174d = 0;
            return;
        }
        if (f8174d == 2 && i2 == 0) {
            q(f8184n);
        }
        if (i2 == 0) {
            f8183m = false;
        }
        f8174d = i2;
        Iterator it = f8178h.iterator();
        while (it.hasNext()) {
            ((StatusCallback) it.next()).b(str, i2);
        }
    }

    public static void x(long j2) {
        if (f8188r != null) {
            f8188r.a(j2);
        }
    }

    public static void y(boolean z) {
        f8182l = z;
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onSourceGameStatusChange isInGame = " + z);
        StringBuilder sb = new StringBuilder();
        sb.append("onSourceGameStatusChange sGameStatusCallback.size = ");
        List<GameStatusCallback> list = f8187q;
        sb.append(list.size());
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", sb.toString());
        for (GameStatusCallback gameStatusCallback : list) {
            GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onSourceGameStatusChange callback = " + gameStatusCallback);
            gameStatusCallback.s(z);
        }
    }

    public static void z(boolean z) {
        f8183m = z;
        GaLog.a("MultiSubScreen_MultiSubScreenUtils", "onSourceSendDataStatusChange S_SOURCE_IS_STOP_SEND_DATA = " + f8183m);
        Iterator it = f8187q.iterator();
        while (it.hasNext()) {
            ((GameStatusCallback) it.next()).e(z);
        }
    }
}
