package cn.nubia.multisubscreen.primary;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.ArrayMap;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.data.BatchData;
import cn.nubia.multisubscreen.data.TransferData;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.primary.AbsFunCtrl;
import cn.nubia.multisubscreen.utils.ACTION;
import cn.nubia.multisubscreen.utils.COMMAND;
import cn.nubia.multisubscreen.utils.MultiSubScreenConstant;
import cn.nubia.multisubscreen.utils.MultiSubScreenNotiMsgUtils;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class PrimaryDeviceDataMgr implements AbsFunCtrl.ChangeListener, GameMonitor.Callback, PerformanceModeController.PerformanceModeCallback {
    private static volatile PrimaryDeviceDataMgr x;

    /* renamed from: c, reason: collision with root package name */
    private Context f7979c;

    /* renamed from: h, reason: collision with root package name */
    private String f7980h;

    /* renamed from: l, reason: collision with root package name */
    private NumericalCtrl f7984l;

    /* renamed from: p, reason: collision with root package name */
    private boolean f7988p;

    /* renamed from: q, reason: collision with root package name */
    private boolean f7989q;

    /* renamed from: r, reason: collision with root package name */
    private boolean f7990r;

    /* renamed from: s, reason: collision with root package name */
    private boolean f7991s;
    private boolean t;
    private StatusCallback v;

    /* renamed from: i, reason: collision with root package name */
    private Map f7981i = new ArrayMap();

    /* renamed from: j, reason: collision with root package name */
    private BatchData f7982j = new BatchData();

    /* renamed from: k, reason: collision with root package name */
    private BatchData f7983k = new BatchData();

    /* renamed from: m, reason: collision with root package name */
    private Object f7985m = new Object();

    /* renamed from: n, reason: collision with root package name */
    private List f7986n = new ArrayList();

    /* renamed from: o, reason: collision with root package name */
    private Map f7987o = new ArrayMap();
    private List w = new ArrayList();
    private Handler u = new Handler(ThreadManager.c().f()) { // from class: cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            if (i2 == 1) {
                PrimaryDeviceDataMgr.this.d0();
            } else if (i2 == 2) {
                PrimaryDeviceDataMgr.this.e0();
            } else {
                if (i2 != 3) {
                    return;
                }
                PrimaryDeviceDataMgr.this.y0();
            }
        }
    };

    private PrimaryDeviceDataMgr(Context context) {
        this.f7979c = context;
        StatusCallback statusCallback = new StatusCallback() { // from class: cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr.2
            @Override // cn.nubia.multisubscreen.callback.StatusCallback
            public void b(String str, int i2) {
                if (MultiSubScreenUtils.v()) {
                    if (i2 != 2) {
                        PrimaryDeviceDataMgr.this.Y();
                        return;
                    }
                    if (PrimaryDeviceDataMgr.this.f7989q) {
                        PrimaryDeviceDataMgr.this.Y();
                    }
                    PrimaryDeviceDataMgr.this.W();
                }
            }
        };
        this.v = statusCallback;
        MultiSubScreenUtils.C(statusCallback);
        PerformanceModeController.S().P(this);
    }

    private List B(List list, Predicate predicate) {
        return (List) list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static PrimaryDeviceDataMgr C() {
        if (x == null) {
            synchronized (PrimaryDeviceDataMgr.class) {
                try {
                    if (x == null) {
                        x = new PrimaryDeviceDataMgr(GameAssistApplication.j());
                    }
                } finally {
                }
            }
        }
        return x;
    }

    private String D(BatchData batchData, int i2) {
        if (batchData.size() <= i2) {
            String jsonString = batchData.getJsonString();
            batchData.reset();
            return jsonString;
        }
        GaLog.e("MultiSubScreen_PrimaryData", "data size " + batchData.size() + ", limit size " + i2);
        JSONObject jSONObject = new JSONObject();
        int i3 = 0;
        for (String str : batchData.getKeys()) {
            if (i3 >= i2) {
                break;
            }
            try {
                jSONObject.put(str, batchData.get(str));
                i3++;
                batchData.remove(str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    private List E(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("keys");
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                arrayList.add(jSONArray.getString(i2));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private JSONObject F() {
        JSONObject jSONObject = new JSONObject();
        final JSONArray jSONArray = new JSONArray();
        final JSONArray jSONArray2 = new JSONArray();
        final JSONArray jSONArray3 = new JSONArray();
        final JSONArray jSONArray4 = new JSONArray();
        this.f7987o.forEach(new BiConsumer() { // from class: cn.nubia.multisubscreen.primary.u
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                PrimaryDeviceDataMgr.M(jSONArray, jSONArray2, jSONArray3, jSONArray4, (String) obj, (List) obj2);
            }
        });
        try {
            if (jSONArray.length() != 0) {
                jSONObject.put("numerical", jSONArray);
            }
            if (jSONArray2.length() != 0) {
                jSONObject.put("slide", jSONArray2);
            }
            if (jSONArray3.length() != 0) {
                jSONObject.put("dessert", jSONArray3);
            }
            if (jSONArray4.length() != 0) {
                jSONObject.put("right_dessert", jSONArray4);
            }
            jSONObject.put("value_region", G());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private JSONObject G() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (ZteFeature.isSupportCustom()) {
            jSONArray.put(1);
            jSONArray.put(2);
            jSONArray.put(3);
            jSONArray.put(4);
            jSONArray.put(5);
        } else {
            jSONArray.put(1);
            jSONArray.put(2);
            jSONArray.put(3);
            jSONArray.put(5);
        }
        jSONObject.put("performance_mode", jSONArray);
        return jSONObject;
    }

    private void H(String str, JSONObject jSONObject) {
        try {
            if (str.hashCode() == -2128564363 && str.equals("active_disconnect")) {
                MultiSubScreenUtils.E(jSONObject.getBoolean(str));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean I(String str) {
        str.hashCode();
        if (str.equals("fan_speed")) {
            return ZteFeature.supportFan();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean J(String str) {
        str.hashCode();
        if (str.equals("competition_light")) {
            return ZteFeature.supportColorfulLight();
        }
        if (str.equals("fan_mode")) {
            return ZteFeature.supportFan();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean K(String str) {
        str.hashCode();
        if (str.equals("fan")) {
            return ZteFeature.supportFan();
        }
        if (str.equals("charge_separation")) {
            return ZteFeature.supportChargeSeparation();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void L(StringBuilder sb, String str, AbsFunCtrl absFunCtrl) {
        if (absFunCtrl != null) {
            sb.append(str);
            sb.append(":");
            sb.append(absFunCtrl.l());
            sb.append(", ");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void M(final JSONArray jSONArray, final JSONArray jSONArray2, final JSONArray jSONArray3, final JSONArray jSONArray4, String str, List list) {
        str.hashCode();
        switch (str) {
            case "right_dessert":
                Objects.requireNonNull(jSONArray4);
                list.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.o
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        jSONArray4.put((String) obj);
                    }
                });
                break;
            case "slide":
                Objects.requireNonNull(jSONArray2);
                list.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.o
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        jSONArray2.put((String) obj);
                    }
                });
                break;
            case "dessert":
                Objects.requireNonNull(jSONArray3);
                list.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.o
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        jSONArray3.put((String) obj);
                    }
                });
                break;
            case "numerical":
                Objects.requireNonNull(jSONArray);
                list.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.o
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        jSONArray.put((String) obj);
                    }
                });
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void N(JSONObject jSONObject, String str) {
        try {
            V(str, jSONObject);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void S(JSONObject jSONObject, String str, AbsFunCtrl absFunCtrl) {
        P(str, jSONObject);
    }

    private boolean V(String str, JSONObject jSONObject) {
        AbsFunCtrl absFunCtrl = (AbsFunCtrl) this.f7981i.get(str);
        if (absFunCtrl != null) {
            return absFunCtrl.m(jSONObject);
        }
        GaLog.e("MultiSubScreen_PrimaryData", "modify data for non existed key " + str);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W() {
        if (this.f7988p) {
            GaLog.e("MultiSubScreen_PrimaryData", "repeat authorized");
            return;
        }
        GaLog.e("MultiSubScreen_PrimaryData", "device has been authorized");
        this.f7988p = true;
        this.f7980h = null;
        X();
        this.f7991s = false;
        v0();
        this.f7991s = true;
        SystemMgr.y(this.f7979c).h(this);
        h0(Settings.Global.getInt(this.f7979c.getContentResolver(), "low_power", 0) == 1);
    }

    private void X() {
        z();
        f0(COMMAND.NOTIFY_KEYS.name(), F().toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b0, reason: merged with bridge method [inline-methods] */
    public void P(String str, JSONObject jSONObject) {
        AbsFunCtrl absFunCtrl = (AbsFunCtrl) this.f7981i.get(str);
        if (absFunCtrl == null) {
            GaLog.e("MultiSubScreen_PrimaryData", "request to get device data for missing " + str);
            return;
        }
        String l2 = absFunCtrl.l();
        if (l2 != null) {
            try {
                jSONObject.put(str, l2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d0() {
        String D;
        synchronized (this.f7985m) {
            try {
                D = D(this.f7982j, 10);
                if (this.f7982j.size() > 0) {
                    i0(1, 0);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        f0(COMMAND.NOTIFY_DATA.name(), D);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e0() {
        String D;
        synchronized (this.f7985m) {
            try {
                D = D(this.f7983k, 10);
                if (this.f7982j.size() > 0) {
                    i0(2, 0);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        f0(COMMAND.NOTIFY_NUMERICAL.name(), D);
    }

    private void f0(final String str, final String str2) {
        if (TextUtils.isEmpty(str2)) {
            GaLog.e("MultiSubScreen_PrimaryData", "pri device send no data with cmd " + str);
            return;
        }
        if (MultiSubScreenUtils.f8174d != 2) {
            GaLog.e("MultiSubScreen_PrimaryData", "pri device not connected");
        } else if (this.u.getLooper().isCurrentThread()) {
            Q(str, str2);
        } else {
            this.u.post(new Runnable() { // from class: cn.nubia.multisubscreen.primary.t
                @Override // java.lang.Runnable
                public final void run() {
                    PrimaryDeviceDataMgr.this.Q(str, str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g0, reason: merged with bridge method [inline-methods] */
    public void Q(String str, String str2) {
        TransferData transferData = new TransferData(str, ACTION.REQUEST_FROM_PRI.name());
        transferData.setData(str2);
        DistributeBusMgr.getInstance().sendTransferData(transferData);
    }

    private void h0(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("low_power_mode", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        m0(jSONObject.toString());
    }

    private void i0(int i2, int i3) {
        if (this.u.hasMessages(i2)) {
            return;
        }
        this.u.sendEmptyMessageDelayed(i2, i3);
    }

    private void n0(boolean z) {
        JSONObject jSONObject = new JSONObject();
        o0(z, jSONObject);
        m0(jSONObject.toString());
    }

    private void o0(boolean z, JSONObject jSONObject) {
        this.f7990r = z;
        try {
            jSONObject.put("stop_send_data", z);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private void q0(boolean z) {
        this.t = z;
        if (z) {
            w();
        } else {
            w0();
        }
    }

    private void r0() {
        q0(false);
        MultiSubScreenUtils.L();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("in_game", false);
            jSONObject.put("stop_game", true);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        m0(jSONObject.toString());
        w0();
    }

    private boolean t0(String str) {
        return !MultiSubScreenNotiMsgUtils.f(this.f7979c, str) || "com.android.messaging".equals(str) || "com.zte.softda".equals(str);
    }

    private boolean u0(StatusBarNotification statusBarNotification) {
        String string = statusBarNotification.getNotification().extras.getString("target_pkg", null);
        String string2 = statusBarNotification.getNotification().extras.getString("no_color", null);
        if (!TextUtils.isEmpty(string)) {
            GaLog.a("MultiSubScreen_PrimaryData", "isPushNotification: " + string);
            return true;
        }
        if (TextUtils.isEmpty(string2)) {
            return false;
        }
        GaLog.a("MultiSubScreen_PrimaryData", "isNoColorNotification: " + string2);
        return true;
    }

    private void x() {
        if (this.f7987o.size() > 0) {
            return;
        }
        this.f7987o.clear();
        this.f7987o.put("numerical", B(MultiSubScreenConstant.f8157b, new Predicate() { // from class: cn.nubia.multisubscreen.primary.w
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean I;
                I = PrimaryDeviceDataMgr.I((String) obj);
                return I;
            }
        }));
        this.f7987o.put("slide", B(MultiSubScreenConstant.f8158c, new Predicate() { // from class: cn.nubia.multisubscreen.primary.x
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean J;
                J = PrimaryDeviceDataMgr.J((String) obj);
                return J;
            }
        }));
        this.f7987o.put("dessert", B(MultiSubScreenConstant.f8159d, new Predicate() { // from class: cn.nubia.multisubscreen.primary.m
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean K;
                K = PrimaryDeviceDataMgr.K((String) obj);
                return K;
            }
        }));
        this.f7987o.put("right_dessert", MultiSubScreenConstant.f8160e);
    }

    private void y(List list, String str) {
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            AbsFunCtrl a2 = MultiSubScreenCtrlFactory.a(this.f7979c, (String) it.next());
            if (a2 != null) {
                a2.p(this);
                this.f7981i.put(a2.k(), a2);
                this.f7986n.add(a2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y0() {
        x0();
        n0(true);
    }

    private void z() {
        x();
        y((List) this.f7987o.get("slide"), "slide");
        y((List) this.f7987o.get("dessert"), "dessert");
        y((List) this.f7987o.get("right_dessert"), "right_dessert");
        NumericalCtrl numericalCtrl = new NumericalCtrl(this.f7979c, "numerical");
        this.f7984l = numericalCtrl;
        this.f7986n.add(numericalCtrl);
    }

    public void A(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr, String str) {
        if (this.f7988p) {
            printWriter.append((CharSequence) str).println("MultiSubScreen_PrimaryDevice: ");
            printWriter.append((CharSequence) str).append("  start: ").println(this.f7989q);
            printWriter.append((CharSequence) str).append("  size: ").println(this.f7981i.size());
            printWriter.append((CharSequence) str).append("  in game: ").println(this.t);
            printWriter.append((CharSequence) str).append("  sink in fg: ").println(MultiSubScreenUtils.f8185o);
            printWriter.append((CharSequence) str).append("  stop send data: ").println(this.f7990r);
            printWriter.append((CharSequence) str).append("  has stop send data msg: ").println(this.u.hasMessages(3));
            ListedDevice k2 = MultiSubScreenUtils.k();
            if (k2 != null) {
                StringBuilder sb = new StringBuilder();
                try {
                    sb.append(str);
                    sb.append("  device: ");
                    sb.append(k2.getName());
                    sb.append(", id: ");
                    sb.append(k2.getDeviceId());
                    sb.append(", type: ");
                    sb.append(k2.getDeviceType());
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (sb.length() > 0) {
                    printWriter.println(sb);
                } else {
                    printWriter.append((CharSequence) str).println(" error device");
                    printWriter.append((CharSequence) str).append("  sink device id: ").println(DistributeBusMgr.getInstance().getSinkDeviceId());
                }
            } else {
                printWriter.append((CharSequence) str).append("  sink device id: ").println(DistributeBusMgr.getInstance().getSinkDeviceId());
            }
            final StringBuilder sb2 = new StringBuilder();
            this.f7981i.forEach(new BiConsumer() { // from class: cn.nubia.multisubscreen.primary.s
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    PrimaryDeviceDataMgr.L(sb2, (String) obj, (AbsFunCtrl) obj2);
                }
            });
            if (sb2.length() > 0) {
                printWriter.append((CharSequence) str).println("  fun key and data：");
                printWriter.append((CharSequence) str).append("  ").println(sb2);
            }
            NumericalCtrl numericalCtrl = this.f7984l;
            if (numericalCtrl != null) {
                numericalCtrl.S(fileDescriptor, printWriter, strArr, "  ");
            }
        }
    }

    public boolean U(String str) {
        try {
            final JSONObject jSONObject = new JSONObject(str);
            jSONObject.keys().forEachRemaining(new Consumer() { // from class: cn.nubia.multisubscreen.primary.r
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PrimaryDeviceDataMgr.this.N(jSONObject, (String) obj);
                }
            });
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void Y() {
        if (this.f7988p) {
            GaLog.e("MultiSubScreen_PrimaryData", "device has been disconnected");
            this.f7988p = false;
            SystemMgr.y(this.f7979c).i(this);
            x0();
            this.f7986n.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.n
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AbsCtrl) obj).h();
                }
            });
            this.f7986n.clear();
            this.f7981i.clear();
            this.f7980h = null;
            MultiSubScreenUtils.L();
            this.u.removeCallbacksAndMessages(null);
            synchronized (this.f7985m) {
                this.f7982j.reset();
                this.f7983k.reset();
            }
        }
    }

    public void Z(GameMonitor.Callback callback) {
        if (this.w.contains(callback)) {
            this.w.remove(callback);
        }
    }

    @Override // cn.nubia.multisubscreen.primary.AbsFunCtrl.ChangeListener
    public void a(String str, String str2) {
        synchronized (this.f7985m) {
            this.f7982j.put(str, str2);
        }
        i0(1, 150);
    }

    public void a0(String str) {
        List E = E(str);
        if (E == null || E.isEmpty()) {
            GaLog.e("MultiSubScreen_PrimaryData", "request to get numerical data error for " + str);
            return;
        }
        final JSONObject jSONObject = new JSONObject();
        E.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.q
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PrimaryDeviceDataMgr.this.P(jSONObject, (String) obj);
            }
        });
        if (jSONObject.length() == 0) {
            GaLog.e("MultiSubScreen_PrimaryData", "request to get device data but failed");
        } else {
            f0(COMMAND.NOTIFY_DATA.name(), jSONObject.toString());
        }
    }

    public void c0(String str) {
        List<String> E = E(str);
        if (E == null || E.isEmpty()) {
            GaLog.e("MultiSubScreen_PrimaryData", "request to get numerical data error for " + str);
            return;
        }
        if (this.f7984l == null) {
            GaLog.e("MultiSubScreen_PrimaryData", "request to get numerical data error for uninitialized, connect status " + MultiSubScreenUtils.f8174d);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str2 : E) {
            String X = this.f7984l.X(str2);
            if (X != null) {
                try {
                    jSONObject.put(str2, X);
                } catch (JSONException unused) {
                }
            }
        }
        if (jSONObject.length() == 0) {
            GaLog.e("MultiSubScreen_PrimaryData", "request to get numerical data but failed");
        } else {
            f0(COMMAND.NOTIFY_NUMERICAL.name(), jSONObject.toString());
        }
    }

    public void j0(StatusBarNotification statusBarNotification) {
        String str;
        String packageName = statusBarNotification.getPackageName();
        GaLog.e("MultiSubScreen_PrimaryData", "sendNotificationMsgData packageName  = " + packageName);
        Notification notification = statusBarNotification.getNotification();
        Bundle bundle = notification.extras;
        String str2 = "";
        if (bundle != null) {
            CharSequence charSequence = bundle.getCharSequence("android.text", "");
            GaLog.e("MultiSubScreen_PrimaryData", "sendNotificationMsgData content  = " + ((Object) charSequence));
            str2 = notification.extras.getCharSequence("android.title", "").toString();
            GaLog.e("MultiSubScreen_PrimaryData", "sendNotificationMsgData title  = " + str2);
            if (TextUtils.isEmpty(charSequence)) {
                charSequence = str2;
            }
            str = charSequence.toString();
            GaLog.e("MultiSubScreen_PrimaryData", "sendNotificationMsgData contentText  = " + str);
        } else {
            str = "";
        }
        boolean z = !u0(statusBarNotification) && t0(packageName);
        GaLog.e("MultiSubScreen_PrimaryData", "sendNotificationMsgData shouldShowAppIcon  = " + z);
        Drawable d2 = z ? MultiSubScreenNotiMsgUtils.d(packageName) : notification.getSmallIcon() != null ? notification.getSmallIcon().loadDrawable(this.f7979c) : null;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("multi_sub_screen_noti_msg_pkg_name", packageName);
            jSONObject.put("multi_sub_screen_noti_msg_app_icon", MultiSubScreenNotiMsgUtils.c(d2));
            jSONObject.put("multi_sub_screen_noti_msg_app_label", MultiSubScreenNotiMsgUtils.e(packageName));
            jSONObject.put("multi_sub_screen_noti_msg_title", str2);
            jSONObject.put("multi_sub_screen_noti_msg_content", str);
            jSONObject.put("multi_sub_screen_noti_msg_time", System.currentTimeMillis());
            jSONObject.put("multi_sub_screen_noti_msg_noti_id", statusBarNotification.getId());
        } catch (JSONException e2) {
            e2.printStackTrace();
            GaLog.e("MultiSubScreen_PrimaryData", "PrimaryDeviceDataMgr sendNotificationMsgData  e  = " + e2);
        }
        String str3 = "{ \"multi_sub_screen_notification_msg_content\":" + jSONObject.toString() + "}";
        GaLog.e("MultiSubScreen_PrimaryData", "PrimaryDeviceDataMgr sendNotificationMsgData data  = " + str3);
        f0(COMMAND.NOTIFY_DATA.name(), str3);
    }

    public void k0(String str, String str2) {
        synchronized (this.f7985m) {
            this.f7983k.put(str, str2);
        }
        i0(2, 150);
    }

    public void l0(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        GaLog.e("MultiSubScreen_PrimaryData", "sendRemoveNotificationMsgData packageName  = " + packageName);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("multi_sub_screen_noti_msg_pkg_name", packageName);
            jSONObject.put("multi_sub_screen_noti_msg_noti_id", statusBarNotification.getId());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        String str = "{ \"multi_sub_screen_remove_notification_msg\":" + jSONObject.toString() + "}";
        GaLog.e("MultiSubScreen_PrimaryData", "PrimaryDeviceDataMgr sendRemoveNotificationMsgData data  = " + str);
        f0(COMMAND.NOTIFY_DATA.name(), str);
    }

    public void m0(String str) {
        f0(COMMAND.NOTIFY_STATUS.name(), str);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        GaLog.e("MultiSubScreen_PrimaryData", "onGameStart");
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            ((GameMonitor.Callback) it.next()).y();
        }
        p0();
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        GaLog.e("MultiSubScreen_PrimaryData", "onGameStop");
        r0();
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            ((GameMonitor.Callback) it.next()).z();
        }
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameUpdate */
    public void A() {
        GaLog.e("MultiSubScreen_PrimaryData", "onGameUpdate");
        p0();
        Iterator it = this.w.iterator();
        while (it.hasNext()) {
            ((GameMonitor.Callback) it.next()).A();
        }
    }

    public void p0() {
        String t = SystemMgr.t();
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.f7980h == null && !MultiSubScreenUtils.s(t)) {
                this.f7980h = t;
                String g2 = MultiSubScreenUtils.g(this.f7979c, t);
                MultiSubScreenUtils.K(t, g2);
                jSONObject.put("package_name", t);
                if (!TextUtils.isEmpty(g2)) {
                    jSONObject.put("app_name", g2);
                }
            }
            jSONObject.put("in_game", true);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        m0(jSONObject.toString());
        q0(true);
    }

    @Override // cn.nubia.gameassist.performance.PerformanceModeController.PerformanceModeCallback
    public void r(boolean z) {
        GaLog.e("MultiSubScreen_PrimaryData", "onLowPowerModeChanged isLowPowerMode = " + z);
        if (MultiSubScreenUtils.f8174d == 2) {
            h0(z);
        }
    }

    public void s0(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                H(keys.next(), jSONObject);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void v(GameMonitor.Callback callback) {
        if (this.w.contains(callback)) {
            return;
        }
        this.w.add(callback);
    }

    public void v0() {
        if (this.f7989q) {
            return;
        }
        this.f7989q = true;
        this.f7986n.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.l
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((AbsCtrl) obj).i();
            }
        });
        if (this.f7991s) {
            final JSONObject jSONObject = new JSONObject();
            this.f7981i.forEach(new BiConsumer() { // from class: cn.nubia.multisubscreen.primary.p
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    PrimaryDeviceDataMgr.this.S(jSONObject, (String) obj, (AbsFunCtrl) obj2);
                }
            });
            if (jSONObject.length() > 0) {
                f0(COMMAND.NOTIFY_DATA.name(), jSONObject.toString());
            }
        }
    }

    public void w() {
        if (MultiSubScreenUtils.f8185o && this.t) {
            this.u.removeMessages(3);
            if (this.f7990r) {
                n0(false);
            }
            v0();
        }
    }

    public void w0() {
        if (this.u.hasMessages(3)) {
            return;
        }
        this.u.sendEmptyMessageDelayed(3, 300000L);
    }

    public void x0() {
        if (this.f7989q) {
            this.f7989q = false;
            this.f7986n.forEach(new Consumer() { // from class: cn.nubia.multisubscreen.primary.v
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((AbsCtrl) obj).j();
                }
            });
        }
    }
}
