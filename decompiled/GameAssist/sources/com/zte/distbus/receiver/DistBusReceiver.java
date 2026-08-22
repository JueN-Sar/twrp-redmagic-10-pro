package com.zte.distbus.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.zte.distbus.basetransfer.BaseServiceCallback;
import com.zte.distbus.basetransfer.BusCallback;
import com.zte.distbus.basetransfer.Constants;
import com.zte.distbus.basetransfer.model.ConnectionParam;
import com.zte.distbus.basetransfer.model.DeviceParam;
import com.zte.distbus.basetransfer.model.NotificationParam;
import com.zte.distbus.basetransfer.servicemanager.DistService;
import com.zte.distbus.basetransfer.servicemanager.model.CallBackResult;
import com.zte.distbus.basetransfer.servicemanager.model.PublishServiceParam;
import com.zte.distbus.basetransfer.servicemanager.model.ServiceParam;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DistBusReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static Map f16328a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private static Map f16329b = new HashMap();

    private BaseServiceCallback a(String str, Context context) {
        BaseServiceCallback baseServiceCallback;
        InstantiationException e2;
        IllegalAccessException e3;
        ClassNotFoundException e4;
        Class<?> cls;
        BaseServiceCallback baseServiceCallback2 = (BaseServiceCallback) f16329b.get(str);
        if (baseServiceCallback2 != null) {
            return baseServiceCallback2;
        }
        Log.d("DistBusReceiver", "callbackClsName: " + str);
        try {
            cls = Class.forName(str);
            baseServiceCallback = (BaseServiceCallback) cls.newInstance();
        } catch (ClassNotFoundException e5) {
            baseServiceCallback = baseServiceCallback2;
            e4 = e5;
        } catch (IllegalAccessException e6) {
            baseServiceCallback = baseServiceCallback2;
            e3 = e6;
        } catch (InstantiationException e7) {
            baseServiceCallback = baseServiceCallback2;
            e2 = e7;
        }
        try {
            baseServiceCallback.setContext(context);
            f16329b.put(str, baseServiceCallback);
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (Method method : declaredMethods) {
                Log.d("DistBusReceiver", "methodName: " + method.getName());
                Class<?>[] parameterTypes = method.getParameterTypes();
                int length = parameterTypes.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Log.d("DistBusReceiver", "param: " + parameterTypes[i2].getSimpleName());
                }
            }
        } catch (ClassNotFoundException e8) {
            e4 = e8;
            e4.printStackTrace();
            return baseServiceCallback;
        } catch (IllegalAccessException e9) {
            e3 = e9;
            e3.printStackTrace();
            return baseServiceCallback;
        } catch (InstantiationException e10) {
            e2 = e10;
            e2.printStackTrace();
            return baseServiceCallback;
        }
        return baseServiceCallback;
    }

    private static String b(String str, String str2) {
        return str2 + "." + str;
    }

    private void c(Intent intent) {
        String stringExtra = intent.getStringExtra(Constants.EXTRA_UUID);
        String stringExtra2 = intent.getStringExtra(Constants.EXTRA_DEVICE_ID);
        Log.d("DistBusReceiver", "processConnection uuid: " + stringExtra + ", deviceId: " + stringExtra2);
        String b2 = b(stringExtra, stringExtra2);
        BusCallback busCallback = TextUtils.isEmpty(b2) ? null : (BusCallback) f16328a.get(b2);
        if (busCallback != null) {
            busCallback.onConnectionChange(intent);
            return;
        }
        Log.d("DistBusReceiver", "processConnection busCallback null, key: " + b2);
    }

    private void d(Intent intent, String str, String str2, BaseServiceCallback baseServiceCallback) {
        String stringExtra;
        String stringExtra2;
        ServiceParam serviceParam;
        stringExtra = intent.getStringExtra(Constants.EXTRA_DEVICE_ID);
        stringExtra2 = intent.getStringExtra(Constants.EXTRA_PROFILE);
        Log.d("DistBusReceiver", "processIntent uuid: " + str2 + ", deviceId: " + stringExtra + ", profile: " + stringExtra2);
        if (stringExtra == null) {
            stringExtra = "";
        }
        serviceParam = new ServiceParam(str2, stringExtra2);
        serviceParam.setDeviceId(stringExtra);
        str.hashCode();
        switch (str) {
            case "distributebus.action.onstartservice":
                baseServiceCallback.onStartService(serviceParam);
                break;
            case "distributebus.action.discovercallback":
                int intExtra = intent.getIntExtra(Constants.EXTRA_VERSION, 0);
                boolean booleanExtra = intent.getBooleanExtra(Constants.EXTRA_ENABLE, true);
                Log.d("DistBusReceiver", "version: " + intExtra + ", enable: " + booleanExtra);
                PublishServiceParam publishServiceParam = new PublishServiceParam(null, str2, null, stringExtra2, intExtra);
                publishServiceParam.setDeviceId(stringExtra);
                publishServiceParam.setEnable(booleanExtra);
                baseServiceCallback.onDiscoverCallback(publishServiceParam);
                break;
            case "distributebus.action.comm.msg":
                baseServiceCallback.onReceiveCommMsg(serviceParam);
                break;
            case "distributebus.action.stopservicecallback":
                Boolean valueOf = Boolean.valueOf(intent.getBooleanExtra(Constants.EXTRA_RESULT, false));
                Log.d("DistBusReceiver", "result: " + valueOf);
                CallBackResult callBackResult = new CallBackResult(str2, stringExtra2, valueOf);
                callBackResult.setDeviceId(stringExtra);
                baseServiceCallback.onStopServiceCallback(callBackResult);
                break;
            case "distributebus.action.startservicecallback":
                Boolean valueOf2 = Boolean.valueOf(intent.getBooleanExtra(Constants.EXTRA_RESULT, false));
                Log.d("DistBusReceiver", "result: " + valueOf2);
                CallBackResult callBackResult2 = new CallBackResult(str2, stringExtra2, valueOf2);
                callBackResult2.setDeviceId(stringExtra);
                baseServiceCallback.onStartServiceCallback(callBackResult2);
                break;
            case "distributebus.action.onstopservice":
                baseServiceCallback.onStopService(serviceParam);
                break;
            case "distributebus.action.onsendnotification":
                String stringExtra3 = intent.getStringExtra(Constants.EXTRA_REMOTE);
                Log.d("DistBusReceiver", "remoteBtAddress: " + stringExtra3);
                e(stringExtra, str2, stringExtra2, stringExtra3);
                break;
            default:
                Log.d("DistBusReceiver", "invalid action: " + str);
                break;
        }
    }

    private void e(String str, String str2, String str3, String str4) {
        Log.d("DistBusReceiver", "ON_SEND_NOTIFICATION uuid: " + str2 + ", deviceId: " + str);
        StringBuilder sb = new StringBuilder();
        sb.append("ON_SEND_NOTIFICATION profile: ");
        sb.append(str3);
        Log.d("DistBusReceiver", sb.toString());
        String b2 = b(str2, str);
        Gson gson = new Gson();
        BusCallback busCallback = TextUtils.isEmpty(str2) ? null : (BusCallback) f16328a.get(b2);
        if (busCallback == null) {
            Log.d("DistBusReceiver", "processNotification busCallback null, uuid: " + str2);
            return;
        }
        NotificationParam notificationParam = (NotificationParam) gson.fromJson(str3, NotificationParam.class);
        if (notificationParam == null) {
            Log.d("DistBusReceiver", "processNotification notificationParam null, uuid: " + str2);
            return;
        }
        if (1 == notificationParam.getCommand()) {
            busCallback.onConnectionInitiated(notificationParam.getProfile());
            return;
        }
        if (2 == notificationParam.getCommand()) {
            busCallback.acceptConnection(notificationParam.getProfile());
            return;
        }
        if (4 == notificationParam.getCommand()) {
            busCallback.rejectConnection();
            return;
        }
        if (3 != notificationParam.getCommand()) {
            if (5 == notificationParam.getCommand()) {
                busCallback.processNotification((ConnectionParam) gson.fromJson(notificationParam.getProfile(), ConnectionParam.class));
                return;
            }
            return;
        }
        Log.d("DistBusReceiver", "ESTABLISH_CONNECTION profile 1: " + notificationParam.getProfile());
        ConnectionParam connectionParam = (ConnectionParam) gson.fromJson(notificationParam.getProfile(), ConnectionParam.class);
        Log.d("DistBusReceiver", "getMsgTransferProfile 1: " + connectionParam.getMsgTransferProfile());
        if (!TextUtils.isEmpty(connectionParam.getMsgTransferProfile())) {
            DeviceParam deviceParam = (DeviceParam) gson.fromJson(connectionParam.getMsgTransferProfile(), DeviceParam.class);
            deviceParam.btAddress = str4;
            connectionParam.setMsgTransferProfile(gson.toJson(deviceParam));
            Log.d("DistBusReceiver", "MsgTransferProfile 2: " + connectionParam.getMsgTransferProfile());
            notificationParam = new NotificationParam(notificationParam.getCommand(), gson.toJson(connectionParam));
        }
        Log.d("DistBusReceiver", "ESTABLISH_CONNECTION profile 2: " + notificationParam.getProfile());
        busCallback.establishConnection(connectionParam);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        String constantAction = DistService.getInstance().getConstantAction(action);
        intent.setAction(constantAction);
        Log.d("DistBusReceiver", "onReceive actionOld: " + action + ", action: " + constantAction);
        if (Constants.ON_PHYSICAL_CONNECTION.equals(constantAction) || Constants.ON_CLOSE_PHYSICAL_CONNECTION.equals(constantAction)) {
            c(intent);
            return;
        }
        String stringExtra = intent.getStringExtra(Constants.EXTRA_UUID);
        String stringExtra2 = intent.getStringExtra(Constants.EXTRA_CALLBACK_CLS);
        Log.d("DistBusReceiver", "onReceive uuid: " + stringExtra + ", callbackClsName: " + stringExtra2);
        BaseServiceCallback a2 = a(stringExtra2, context);
        if (constantAction != null && a2 != null) {
            d(intent, constantAction, stringExtra, a2);
            return;
        }
        Log.d("DistBusReceiver", "error null uuid: " + stringExtra + "callBack: " + a2);
    }
}
