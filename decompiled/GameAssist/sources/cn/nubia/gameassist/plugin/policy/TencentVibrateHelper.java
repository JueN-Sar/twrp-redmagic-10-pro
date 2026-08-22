package cn.nubia.gameassist.plugin.policy;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.tencent.inlab.tcsystem.ITCSystemService;
import com.zte.distbus.basetransfer.Constants;
import com.zte.gameassist.utils.GaLog;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TencentVibrateHelper {

    /* renamed from: d, reason: collision with root package name */
    private static ITCSystemService f7295d;

    /* renamed from: e, reason: collision with root package name */
    private static TencentVibrateHelper f7296e;

    /* renamed from: a, reason: collision with root package name */
    WeakReference f7297a;

    /* renamed from: b, reason: collision with root package name */
    WeakReference f7298b;

    /* renamed from: c, reason: collision with root package name */
    private ServiceConnection f7299c = new ServiceConnection() { // from class: cn.nubia.gameassist.plugin.policy.TencentVibrateHelper.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GaLog.a("TencentVibrateHelper", "TCSystemService connected");
            TencentVibrateHelper.f7295d = ITCSystemService.Stub.asInterface(iBinder);
            GaLog.a("TencentVibrateHelper", "TCSystemService connected complete");
            WeakReference weakReference = TencentVibrateHelper.this.f7298b;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            ((TencentVibrateCallBack) TencentVibrateHelper.this.f7298b.get()).v();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GaLog.a("TencentVibrateHelper", "TCSystemService disconnected");
            if (TencentVibrateHelper.this.f7299c != null) {
                try {
                    TencentVibrateHelper.f7295d = null;
                    WeakReference weakReference = TencentVibrateHelper.this.f7297a;
                    if (weakReference != null && weakReference.get() != null) {
                        ((Context) TencentVibrateHelper.this.f7297a.get()).unbindService(TencentVibrateHelper.this.f7299c);
                    }
                    GaLog.a("TencentVibrateHelper", "TCSystemService disconnect done");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    };

    public interface TencentVibrateCallBack {
        void v();
    }

    public TencentVibrateHelper(Context context, TencentVibrateCallBack tencentVibrateCallBack) {
        this.f7297a = new WeakReference(context);
        this.f7298b = new WeakReference(tencentVibrateCallBack);
    }

    public static TencentVibrateHelper f(Context context, TencentVibrateCallBack tencentVibrateCallBack) {
        if (f7296e == null) {
            f7296e = new TencentVibrateHelper(context, tencentVibrateCallBack);
        }
        return f7296e;
    }

    public void c() {
        GaLog.a("TencentVibrateHelper", "start bind TCSystemService");
        WeakReference weakReference = this.f7297a;
        if (weakReference == null || weakReference.get() == null) {
            GaLog.a("TencentVibrateHelper", "bindTCSystemService: context is null");
            return;
        }
        Context context = (Context) this.f7297a.get();
        Intent intent = new Intent(context, (Class<?>) ITCSystemService.class);
        intent.setClassName("com.tencent.inlab.solarcore", "com.tencent.inlab.solarcore.tcsystem.TCSystemService");
        intent.setAction("com.tencent.inlab.tcsystem.action.AIDL_TCSYSTEMSERVICE");
        intent.setPackage(context.getPackageName());
        try {
            context.bindService(intent, this.f7299c, 1);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String d(String str) {
        if (f7295d == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("field", "Controller");
            jSONObject.put("method", "get");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("module", "SolarCore");
            jSONObject2.put("function", "getModuleList");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject2.put("args", jSONArray);
            jSONObject.put("params", jSONObject2);
            GaLog.e("TencentVibrateHelper", "carryModuleList,packageName=" + str + " json = " + jSONObject);
            String str2 = new String(f7295d.carry(jSONObject.toString().getBytes(), 1), "UTF-8");
            GaLog.e("TencentVibrateHelper", "carry: resultJson = " + new JSONObject(str2));
            return str2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public boolean e(String str) {
        JSONArray jSONArray;
        try {
            String d2 = d(str);
            GaLog.a("TencentVibrateHelper", "checkIfSupportTencentHaptic pkgname:" + str + ", data = " + d2);
            if (!TextUtils.isEmpty(d2) && (jSONArray = new JSONObject(d2).getJSONObject(Constants.EXTRA_RESULT).getJSONArray("return")) != null && jSONArray.length() > 0) {
                int length = jSONArray.length();
                for (int i2 = 0; i2 < length; i2++) {
                    String string = jSONArray.getString(i2);
                    GaLog.a("TencentVibrateHelper", "checkIfSupportTencentHaptic key:" + string);
                    if (string.equals("haptic")) {
                        return true;
                    }
                }
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return false;
    }

    public void g() {
        WeakReference weakReference = this.f7297a;
        if (weakReference == null || weakReference.get() == null) {
            GaLog.a("TencentVibrateHelper", "unBindTCSystemService: context is null");
            return;
        }
        Context context = (Context) this.f7297a.get();
        GaLog.a("TencentVibrateHelper", "unBindTCSystemService: ");
        try {
            context.unbindService(this.f7299c);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
