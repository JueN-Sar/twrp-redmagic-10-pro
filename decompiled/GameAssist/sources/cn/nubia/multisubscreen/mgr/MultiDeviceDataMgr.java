package cn.nubia.multisubscreen.mgr;

import android.content.Context;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.multisubscreen.data.BatchData;
import cn.nubia.multisubscreen.data.TransferData;
import cn.nubia.multisubscreen.primary.PrimaryDeviceDataMgr;
import cn.nubia.multisubscreen.secondary.SecDeviceDataMgr;
import cn.nubia.multisubscreen.utils.ACTION;
import cn.nubia.multisubscreen.utils.COMMAND;
import com.google.gson.Gson;
import com.zte.distbus.basetransfer.servicemanager.model.ServiceParam;
import com.zte.gameassist.utils.GaLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MultiDeviceDataMgr {

    /* renamed from: b, reason: collision with root package name */
    private static volatile MultiDeviceDataMgr f7913b;

    /* renamed from: a, reason: collision with root package name */
    private Context f7914a;

    private MultiDeviceDataMgr(Context context) {
        this.f7914a = context;
    }

    private BatchData a(TransferData transferData) {
        BatchData batchData = new BatchData();
        try {
            batchData.updateFromJson(new JSONObject(transferData.getData()));
            return batchData;
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static MultiDeviceDataMgr b() {
        if (f7913b == null) {
            synchronized (DistributeBusMgr.class) {
                try {
                    if (f7913b == null) {
                        f7913b = new MultiDeviceDataMgr(GameAssistApplication.j());
                    }
                } finally {
                }
            }
        }
        return f7913b;
    }

    public void c(ServiceParam serviceParam) {
        String deviceId = serviceParam.getDeviceId();
        String profile = serviceParam.getProfile();
        try {
            TransferData transferData = (TransferData) new Gson().fromJson(profile, TransferData.class);
            GaLog.e("MultiSubScreen_MultiDeviceDataMgr", "process data " + profile);
            if (transferData == null) {
                return;
            }
            ACTION d2 = ACTION.d(transferData.getAction());
            COMMAND d3 = COMMAND.d(transferData.getCmd());
            GaLog.e("MultiSubScreen_MultiDeviceDataMgr", "process cmd " + d3 + ",action=" + d2);
            if (d3 == COMMAND.CONNECT) {
                ConnectCodeMgr.h().v(deviceId, a(transferData));
            }
            if (d2 != ACTION.REQUEST_FROM_PRI) {
                if (d2 != ACTION.RESPONSE_FROM_PRI && d2 == ACTION.REQUEST_FROM_SEC) {
                    if (d3 == COMMAND.MODIFY_DATA) {
                        PrimaryDeviceDataMgr.C().U(transferData.getData());
                        return;
                    }
                    if (d3 == COMMAND.GET_DATA) {
                        PrimaryDeviceDataMgr.C().a0(transferData.getData());
                        return;
                    } else if (d3 == COMMAND.GET_NUMERICAL) {
                        PrimaryDeviceDataMgr.C().c0(transferData.getData());
                        return;
                    } else {
                        if (d3 == COMMAND.NOTIFY_STATUS) {
                            PrimaryDeviceDataMgr.C().s0(transferData.getData());
                            return;
                        }
                        return;
                    }
                }
                return;
            }
            if (d3 == COMMAND.NOTIFY_DATA) {
                BatchData a2 = a(transferData);
                if (a2.isValid()) {
                    SecDeviceDataMgr.f().u(a2);
                    return;
                }
                return;
            }
            if (d3 == COMMAND.NOTIFY_NUMERICAL) {
                BatchData a3 = a(transferData);
                if (a3.isValid()) {
                    SecDeviceDataMgr.f().w(a3);
                    return;
                }
                return;
            }
            if (d3 == COMMAND.NOTIFY_KEYS) {
                SecDeviceDataMgr.f().v(transferData.getData());
            } else if (d3 == COMMAND.NOTIFY_STATUS) {
                SecDeviceDataMgr.f().x(transferData.getData());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
