package cn.nubia.multisubscreen.secondary;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.multisubscreen.callback.StatusCallback;
import cn.nubia.multisubscreen.data.BatchData;
import cn.nubia.multisubscreen.data.TransferData;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.multisubscreen.mgr.MultiSubScreenThemeMgr;
import cn.nubia.multisubscreen.utils.ACTION;
import cn.nubia.multisubscreen.utils.COMMAND;
import cn.nubia.multisubscreen.utils.MultiSubScreenConstant;
import cn.nubia.multisubscreen.utils.MultiSubScreenUtils;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SecDeviceDataMgr {

    /* renamed from: j, reason: collision with root package name */
    private static volatile SecDeviceDataMgr f8046j;

    /* renamed from: a, reason: collision with root package name */
    private Context f8047a;

    /* renamed from: f, reason: collision with root package name */
    private DataChangeListener f8052f;

    /* renamed from: g, reason: collision with root package name */
    private String f8053g;

    /* renamed from: h, reason: collision with root package name */
    private StatusCallback f8054h;

    /* renamed from: i, reason: collision with root package name */
    private String f8055i;

    /* renamed from: c, reason: collision with root package name */
    private Object f8049c = new Object();

    /* renamed from: d, reason: collision with root package name */
    private BatchData f8050d = new BatchData();

    /* renamed from: e, reason: collision with root package name */
    private BatchData f8051e = new BatchData();

    /* renamed from: b, reason: collision with root package name */
    private Handler f8048b = new Handler(ThreadManager.c().f()) { // from class: cn.nubia.multisubscreen.secondary.SecDeviceDataMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            SecDeviceDataMgr.this.r();
        }
    };

    public interface DataChangeListener {
        void b(String str);

        void c(BatchData batchData);

        void d(BatchData batchData);
    }

    private SecDeviceDataMgr(Context context) {
        this.f8047a = context;
        StatusCallback statusCallback = new StatusCallback() { // from class: cn.nubia.multisubscreen.secondary.SecDeviceDataMgr.2
            @Override // cn.nubia.multisubscreen.callback.StatusCallback
            public void b(String str, int i2) {
                if (MultiSubScreenUtils.v() || i2 == 2) {
                    return;
                }
                MultiSubScreenUtils.L();
                SecDeviceDataMgr.this.f8055i = null;
                SecDeviceDataMgr.this.f8053g = null;
            }
        };
        this.f8054h = statusCallback;
        MultiSubScreenUtils.C(statusCallback);
    }

    public static SecDeviceDataMgr f() {
        if (f8046j == null) {
            synchronized (SecDeviceDataMgr.class) {
                try {
                    if (f8046j == null) {
                        f8046j = new SecDeviceDataMgr(GameAssistApplication.j());
                    }
                } finally {
                }
            }
        }
        return f8046j;
    }

    private String g(List list) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        try {
            jSONObject.put("keys", jSONArray);
            return jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void i(String str, JSONObject jSONObject) {
        char c2;
        try {
            GaLog.a("MultiSubScreen_SecData", "handleStatusData key = " + str);
            switch (str.hashCode()) {
                case -2128564363:
                    if (str.equals("active_disconnect")) {
                        c2 = 3;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1877165340:
                    if (str.equals("package_name")) {
                        c2 = 1;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1558685080:
                    if (str.equals("low_power_mode")) {
                        c2 = 4;
                        break;
                    }
                    c2 = 65535;
                    break;
                case -1394166268:
                    if (str.equals("stop_send_data")) {
                        c2 = 2;
                        break;
                    }
                    c2 = 65535;
                    break;
                case 1938712268:
                    if (str.equals("in_game")) {
                        c2 = 0;
                        break;
                    }
                    c2 = 65535;
                    break;
                default:
                    c2 = 65535;
                    break;
            }
            if (c2 == 0) {
                boolean z = jSONObject.getBoolean("in_game");
                GaLog.a("MultiSubScreen_SecData", "handleStatusData KEY_STATUS_IN_GAME inGame = " + z);
                MultiSubScreenUtils.y(z);
                return;
            }
            if (c2 == 1) {
                this.f8055i = jSONObject.getString("package_name");
                MultiSubScreenUtils.K(this.f8055i, jSONObject.getString("app_name"));
            } else if (c2 == 2) {
                MultiSubScreenUtils.z(jSONObject.getBoolean(str));
            } else if (c2 == 3) {
                MultiSubScreenUtils.E(jSONObject.getBoolean(str));
            } else {
                if (c2 != 4) {
                    return;
                }
                MultiSubScreenUtils.J(jSONObject.getBoolean(str));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private boolean j() {
        return MultiSubScreenUtils.f8174d == 2;
    }

    private void p(final String str, final String str2) {
        if (TextUtils.isEmpty(str2)) {
            GaLog.e("MultiSubScreen_SecData", "sec device send no data with cmd " + str);
            return;
        }
        if (!j()) {
            GaLog.e("MultiSubScreen_SecData", "sec device not connected");
        } else if (this.f8048b.getLooper().isCurrentThread()) {
            q(str, str2);
        } else {
            this.f8048b.post(new Runnable() { // from class: cn.nubia.multisubscreen.secondary.SecDeviceDataMgr.3
                @Override // java.lang.Runnable
                public void run() {
                    SecDeviceDataMgr.this.q(str, str2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q(String str, String str2) {
        TransferData transferData = new TransferData(str, ACTION.REQUEST_FROM_SEC.name());
        transferData.setData(str2);
        DistributeBusMgr.getInstance().sendTransferData(transferData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        String jsonString;
        synchronized (this.f8049c) {
            jsonString = this.f8050d.getJsonString();
            this.f8050d.reset();
        }
        p(COMMAND.MODIFY_DATA.name(), jsonString);
    }

    private void s(int i2, int i3) {
        if (this.f8048b.hasMessages(i2)) {
            return;
        }
        this.f8048b.sendEmptyMessageDelayed(i2, i3);
    }

    public void e(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr, String str) {
        if (!j() || MultiSubScreenUtils.v()) {
            return;
        }
        printWriter.append((CharSequence) str).println("MultiSubScreen_SecondaryDevice:");
        printWriter.append((CharSequence) str).append("  theme mode:").println(MultiSubScreenThemeMgr.e().f().f7436c);
        printWriter.append((CharSequence) str).append("  theme ani progress:").println(MultiSubScreenThemeMgr.e().f().f7449p);
        printWriter.append((CharSequence) str).append("  in game:").println(MultiSubScreenUtils.f8182l);
        printWriter.append((CharSequence) str).append("  stop send data:").println(MultiSubScreenUtils.f8183m);
        if (!TextUtils.isEmpty(this.f8055i)) {
            printWriter.append((CharSequence) str).append("  primary current package:").println(this.f8055i);
        }
        ListedDevice k2 = MultiSubScreenUtils.k();
        if (k2 == null) {
            printWriter.append((CharSequence) str).append("  source device id: ").println(DistributeBusMgr.getInstance().getSinkDeviceId());
            return;
        }
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
            printWriter.append((CharSequence) str).append("  source device id: ").println(DistributeBusMgr.getInstance().getSinkDeviceId());
        }
    }

    public String h() {
        return this.f8053g;
    }

    public void k(String str, String str2) {
        synchronized (this.f8049c) {
            this.f8050d.put(str, str2);
        }
        s(1, 150);
    }

    public void l(DataChangeListener dataChangeListener) {
        this.f8052f = dataChangeListener;
    }

    public void m() {
        n();
        o();
    }

    public void n() {
        p(COMMAND.GET_DATA.name(), g(MultiSubScreenConstant.f8161f));
    }

    public void o() {
        p(COMMAND.GET_NUMERICAL.name(), g(MultiSubScreenConstant.f8157b));
    }

    public void t(String str) {
        p(COMMAND.NOTIFY_STATUS.name(), str);
    }

    public boolean u(BatchData batchData) {
        DataChangeListener dataChangeListener = this.f8052f;
        if (dataChangeListener == null) {
            return true;
        }
        dataChangeListener.c(batchData);
        return true;
    }

    public void v(String str) {
        this.f8053g = str;
        DataChangeListener dataChangeListener = this.f8052f;
        if (dataChangeListener != null) {
            dataChangeListener.b(str);
        }
    }

    public boolean w(BatchData batchData) {
        DataChangeListener dataChangeListener = this.f8052f;
        if (dataChangeListener == null) {
            return true;
        }
        dataChangeListener.d(batchData);
        return true;
    }

    public void x(String str) {
        GaLog.a("MultiSubScreen_SecData", "setStatus data = " + str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                i(keys.next(), jSONObject);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void y(DataChangeListener dataChangeListener) {
        this.f8052f = null;
    }
}
