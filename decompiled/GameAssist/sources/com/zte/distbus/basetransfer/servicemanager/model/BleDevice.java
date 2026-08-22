package com.zte.distbus.basetransfer.servicemanager.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.zte.distbus.basetransfer.Constants;
import com.zte.distbus.basetransfer.servicemanager.DistService;
import com.zte.shared.wrapper.WindowManagerWrapper;
import java.io.Serializable;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class BleDevice extends ListedDevice implements Serializable {
    private static final String TAG = "BleDevice";
    private final String btMac;

    public BleDevice(@NonNull String str, @NonNull String str2) {
        super("", str, 100, "", 0, false, DiscoverType.DISCOVER_TYPE_BLE);
        this.serviceList = new ArrayList();
        this.btMac = str2;
    }

    public void connect(Context context) {
        Intent intent = new Intent();
        intent.setAction(DistService.getIntentActionToServiceManager(context, Constants.BLE_CONNECT_DEVICE));
        intent.setClassName(DistService.getServiceManagerPackage(), Constants.SERVICE_MANAGER_RECEIVER_CLS_NAME);
        intent.putExtra(Constants.EXTRA_CALLBACK_PKG, context.getPackageName());
        String json = new Gson().toJson(this);
        intent.putExtra(Constants.EXTRA_PROFILE, json);
        if (!DistService.getInstance().isSdk()) {
            intent.addFlags(WindowManagerWrapper.LayoutParams.PRIVATE_FLAG_FIT_INSETS_CONTROLLED);
        }
        context.sendBroadcast(intent);
        Log.d(TAG, "connect mPackageName:" + context.getPackageName() + ", profile:" + json);
    }

    public String getBtMac() {
        return this.btMac;
    }

    @Override // com.zte.distbus.basetransfer.servicemanager.model.ListedDevice
    public String toString() {
        return "btMac:" + this.btMac + " " + super.toString();
    }
}
