package com.zte.distbus.basetransfer.servicemanager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.zte.distbus.basetransfer.Constants;
import com.zte.distbus.basetransfer.device.DeviceUtil;
import com.zte.distbus.basetransfer.servicemanager.model.DistServiceInitParam;

/* loaded from: classes.dex */
public class DistService {
    public static final String ACTION_START_RFCOMM_SERVER = "startRfcommServer";
    public static final String ACTION_START_RFCOMM_SERVER_SUCCESS = "startRfcommServerSuccess";
    public static final String SERVICE_MANAGER_SHORT_CONNECTION_SERVER_UUID = "187f8000-0000-1000-8000-ceb574815011";
    public static final String START_INTERNAL_SERVICE_MANAGER = "startInternalServiceManager";
    public static final String STOP_INTERNAL_SERVICE_MANAGER = "stopInternalServiceManager";
    private static String TAG = "DistService";
    private static DistService instance = new DistService();
    private static String serviceManagerPackageName = "com.zte.distservice.servicemanager";
    private Context context;
    private DistServiceInitParam initParam;
    private boolean sdkInit;

    private DistService() {
    }

    public static DistService getInstance() {
        return instance;
    }

    public static String getIntentActionToServiceManager(Context context, String str) {
        if (instance.sdkInit) {
            return Constants.SDK_ACTION_TAG + str;
        }
        if (isSystemApp(context)) {
            return str;
        }
        return Constants.NON_SYSTEM_ACTION_TAG + str;
    }

    public static String getServiceManagerPackage() {
        return serviceManagerPackageName;
    }

    public static void init(DistServiceInitParam distServiceInitParam) {
        if (distServiceInitParam == null || distServiceInitParam.context == null) {
            Log.d(TAG, "init, context null");
            return;
        }
        instance.initInstance(distServiceInitParam);
        Log.d(TAG, "init, serviceManagerPackageName: " + serviceManagerPackageName);
    }

    private void initInstance(DistServiceInitParam distServiceInitParam) {
        this.sdkInit = true;
        Context context = distServiceInitParam.context;
        this.context = context;
        serviceManagerPackageName = context.getPackageName();
        DeviceUtil.setDevType(4);
        this.initParam = distServiceInitParam;
    }

    public static boolean isSystemApp(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
            if (packageInfo != null) {
                Log.d(TAG, "sharedUserId: " + packageInfo.sharedUserId);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                if (applicationInfo != null) {
                    r0 = (applicationInfo.flags & 1) > 0;
                    Log.d(TAG, "is system app: " + r0);
                }
            }
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return r0;
    }

    public static void start() {
        Context context = getInstance().context;
        if (context != null && instance.sdkInit) {
            Log.d(TAG, "start");
            Intent intent = new Intent();
            intent.setAction(START_INTERNAL_SERVICE_MANAGER);
            intent.setClassName(getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
            context.sendBroadcast(intent);
            return;
        }
        Log.d(TAG, "start, context: " + context + ", sdkInit: " + instance.sdkInit);
    }

    public static void stop() {
        Context context = getInstance().context;
        if (context == null) {
            Log.d(TAG, "start, context null");
            return;
        }
        Log.d(TAG, "stop");
        Intent intent = new Intent();
        intent.setAction(STOP_INTERNAL_SERVICE_MANAGER);
        intent.setClassName(getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        context.sendBroadcast(intent);
    }

    public String getConstantAction(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.startsWith(Constants.SDK_ACTION_TAG)) {
            Log.d(TAG, "SDK_ACTION: " + str);
            return str.replaceFirst(Constants.SDK_ACTION_TAG, "");
        }
        if (!str.startsWith(Constants.NON_SYSTEM_ACTION_TAG)) {
            return str;
        }
        Log.d(TAG, "NON_SYSTEM_ACTION: " + str);
        return str.replaceFirst(Constants.NON_SYSTEM_ACTION_TAG, "");
    }

    public DistServiceInitParam getInitParam() {
        return this.initParam;
    }

    public String getIntentAction(String str) {
        if (!this.sdkInit) {
            return str;
        }
        return Constants.SDK_ACTION_TAG + str;
    }

    public boolean isSdk() {
        return this.sdkInit;
    }
}
