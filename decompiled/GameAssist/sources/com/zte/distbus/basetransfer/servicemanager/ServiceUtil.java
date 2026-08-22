package com.zte.distbus.basetransfer.servicemanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.zte.distbus.basetransfer.Constants;
import com.zte.distbus.basetransfer.servicemanager.model.DeviceChangeCallBack;
import com.zte.distbus.basetransfer.servicemanager.model.ListedDevice;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class ServiceUtil {
    public static final String TAG = "ServiceUtil";
    private static List<ListedDevice> deviceList = new CopyOnWriteArrayList();
    private final Context mContext;
    private DeviceChangeCallBack mDcCallBack;
    private final String mPackageName;
    private final String mUuid;
    private int serial = 0;
    private BroadcastReceiver mListBr = new BroadcastReceiver() { // from class: com.zte.distbus.basetransfer.servicemanager.ServiceUtil.1
        private String getListedDeviceItemLog(int i2, ListedDevice listedDevice) {
            return " Item [" + i2 + "]: " + listedDevice.getName() + " " + listedDevice.getDeviceId() + " " + listedDevice.isConnected() + " " + listedDevice.getAccount() + " " + listedDevice.getStatus();
        }

        private void handleCurrentExtra(Intent intent) {
            String stringExtra = intent.getStringExtra(Constants.EXTRA_CURRENT);
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            Log.d(ServiceUtil.TAG, "handleCurrentExtra: " + getListedDeviceItemLog(-1, (ListedDevice) new Gson().fromJson(stringExtra, ListedDevice.class)));
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(Constants.EXTRA_SERIAL, -1);
            ServiceUtil.this.LogListedDevice("BLE_ON_REQUEST_LIST onReceive. serial = " + intExtra);
            handleCurrentExtra(intent);
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra(Constants.EXTRA_LIST);
            ArrayList<ListedDevice> arrayList = new ArrayList<>();
            if (stringArrayListExtra != null && !stringArrayListExtra.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                String str = "";
                for (int i2 = 0; i2 < stringArrayListExtra.size(); i2++) {
                    ListedDevice listedDevice = (ListedDevice) new Gson().fromJson(stringArrayListExtra.get(i2), ListedDevice.class);
                    arrayList.add(listedDevice);
                    sb.append(getListedDeviceItemLog(i2, listedDevice));
                    if (listedDevice.isConnected()) {
                        str = stringArrayListExtra.get(i2);
                    }
                }
                Log.d(ServiceUtil.TAG, "LogListedDevice for(): " + ((Object) sb));
                Log.d(ServiceUtil.TAG, "LogListedDevice onReceive: " + intent.getAction() + "; size = " + stringArrayListExtra.size() + "; connectedItem = " + str);
            }
            if (ServiceUtil.this.mDcCallBack != null) {
                Log.d(ServiceUtil.TAG, "LogListedDevice, mDcCallBack.onListChange(); size = " + arrayList.size());
                ServiceUtil.deviceList.clear();
                ServiceUtil.deviceList.addAll(arrayList);
                ServiceUtil.this.mDcCallBack.onListChange(arrayList);
            }
        }
    };
    private BroadcastReceiver mItemBr = new BroadcastReceiver() { // from class: com.zte.distbus.basetransfer.servicemanager.ServiceUtil.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra(Constants.EXTRA_RESULT, 1);
            String stringExtra = intent.getStringExtra(Constants.EXTRA_UUID);
            String stringExtra2 = intent.getStringExtra(Constants.EXTRA_ITEM);
            if (ServiceUtil.this.mDcCallBack != null) {
                Log.d(ServiceUtil.TAG, "LogListedDevice, mDcCallBack.onItemChange() = " + ServiceUtil.this.mDcCallBack);
                ListedDevice listedDevice = (ListedDevice) new Gson().fromJson(stringExtra2, ListedDevice.class);
                ServiceUtil.this.updateListDevice(listedDevice);
                ServiceUtil.this.mDcCallBack.onItemChange(listedDevice);
            }
            Log.d(ServiceUtil.TAG, "connectDevice onReceive action: " + intent.getAction() + "; serial = " + intent.getIntExtra(Constants.EXTRA_SERIAL, -1) + "; deviceId = " + stringExtra + "; status = " + intExtra + "; context = " + context + "; item = " + stringExtra2);
        }
    };

    public ServiceUtil(Context context, String str) {
        this.mContext = context;
        this.mUuid = str;
        String packageName = context.getPackageName();
        this.mPackageName = packageName;
        Log.d(TAG, "ServiceUtil Constructor2. mPackageName = " + packageName + "; mUuid = " + str + "; mContext = " + context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LogListedDevice(String str) {
        LogListedDevice(str, null);
    }

    public static ListedDevice getDevice(String str) {
        for (ListedDevice listedDevice : deviceList) {
            if (listedDevice.getDeviceId().equals(str)) {
                return listedDevice;
            }
        }
        return null;
    }

    private void registerBroadcastReceiver(BroadcastReceiver broadcastReceiver, String str) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DistService.getInstance().getIntentAction(str));
        if (DistService.getInstance().isSdk()) {
            LocalBroadcastManager.b(this.mContext).c(broadcastReceiver, intentFilter);
        } else {
            this.mContext.registerReceiver(broadcastReceiver, intentFilter, 2);
        }
        LogListedDevice("registerBroadcastReceiver: " + str);
    }

    private void sendRequestBroadcast(String str, String str2) {
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.mContext, str));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_DEVICE_ID, str2);
        intent.putExtra(Constants.EXTRA_CALLBACK_PKG, this.mPackageName);
        intent.putExtra(Constants.EXTRA_UUID, this.mUuid);
        int i2 = this.serial + 1;
        this.serial = i2;
        intent.putExtra(Constants.EXTRA_SERIAL, i2);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.mContext.sendBroadcast(intent);
        LogListedDevice("sendRequestBroadcast: " + str + "; mPackageName = " + this.mPackageName + "; uuid = " + this.mUuid + "; deviceId = " + str2 + "; serial = " + this.serial);
    }

    private void unregisterItemReceiver() {
        Log.d(TAG, "unregisterItemReceiver mItemBr = " + this.mItemBr);
        if (this.mItemBr != null) {
            if (DistService.getInstance().isSdk()) {
                LocalBroadcastManager.b(this.mContext).d(this.mItemBr);
            } else {
                this.mContext.unregisterReceiver(this.mItemBr);
            }
            this.mItemBr = null;
        }
    }

    private void unregisterListReceiver() {
        Log.d(TAG, "unregisterListReceiver mListBr = " + this.mListBr);
        if (this.mListBr != null) {
            if (DistService.getInstance().isSdk()) {
                LocalBroadcastManager.b(this.mContext).d(this.mListBr);
            } else {
                this.mContext.unregisterReceiver(this.mListBr);
            }
            this.mListBr = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateListDevice(ListedDevice listedDevice) {
        for (int i2 = 0; i2 < deviceList.size(); i2++) {
            if (deviceList.get(i2).getDeviceId().equals(listedDevice.getDeviceId())) {
                deviceList.set(i2, listedDevice);
                return;
            }
        }
    }

    public void addDevice(String str) {
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.mContext, Constants.ADD_DEVICE));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_PROFILE, str);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.mContext.sendBroadcast(intent);
        Log.d(TAG, "addDevice mPackageName:" + this.mPackageName + ", uuid:" + this.mUuid + ",jsonStr:" + str);
    }

    public void connectDevice(String str) {
        sendRequestBroadcast(Constants.BLE_CONNECT_DEVICE, str);
    }

    public void disconnectDevice(String str) {
        sendRequestBroadcast(Constants.BLE_DISCONNECT_DEVICE, str);
    }

    public void discoverDevice(int i2) {
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.mContext, Constants.DISCOVER_DEVICE));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_PROFILE, i2);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.mContext.sendBroadcast(intent);
        Log.d(TAG, "discoverDevice mPackageName:" + this.mPackageName + ", uuid:" + this.mUuid + ",type:" + i2);
    }

    public void registerDeviceChangeCB(DeviceChangeCallBack deviceChangeCallBack) {
        LogListedDevice("enter registerDeviceChangeCB.", deviceChangeCallBack);
        this.mDcCallBack = deviceChangeCallBack;
        if (deviceChangeCallBack == null) {
            unregisterDeviceChangeCB();
            return;
        }
        registerBroadcastReceiver(this.mListBr, Constants.BLE_ON_REQUEST_LIST);
        registerBroadcastReceiver(this.mItemBr, Constants.BLE_ON_LIST_ITEM_CHANGED);
        sendRequestBroadcast(Constants.BLE_REQUEST_LIST, "");
    }

    public void setDeviceTrustStatus(String str, boolean z) {
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(this.mContext, Constants.TRUST_DEVICE));
        intent.setClassName(Constants.SERVICE_MANAGER_PACKAGE_NAME, Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_DEVICE_ID, str);
        intent.putExtra(Constants.EXTRA_CALLBACK_PKG, this.mPackageName);
        intent.putExtra(Constants.EXTRA_UUID, this.mUuid);
        intent.putExtra(Constants.EXTRA_TRUST, z);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        this.mContext.sendBroadcast(intent);
        LogListedDevice("setDeviceTrustStatus mPackageName: " + this.mPackageName + ", uuid: " + this.mUuid + ", deviceId: " + str + ", trust: " + z);
    }

    public void unregisterDeviceChangeCB() {
        this.mDcCallBack = null;
        try {
            unregisterListReceiver();
            unregisterItemReceiver();
        } catch (Exception e2) {
            Log.d(TAG, "unregisterDeviceChangeCB: " + e2);
        }
    }

    private void LogListedDevice(String str, DeviceChangeCallBack deviceChangeCallBack) {
        Log.i(TAG, "LogListedDevice --- " + str + "; dcCallBack = " + deviceChangeCallBack + "; mDcCallBack = " + this.mDcCallBack + "; mPackageName = " + this.mPackageName + "; mUuid = " + this.mUuid + "; mContext = " + this.mContext);
    }
}
