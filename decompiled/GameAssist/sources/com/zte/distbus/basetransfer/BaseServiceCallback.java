package com.zte.distbus.basetransfer;

import android.content.Context;
import android.util.Log;
import com.zte.distbus.basetransfer.servicemanager.model.CallBackResult;
import com.zte.distbus.basetransfer.servicemanager.model.PublishServiceParam;
import com.zte.distbus.basetransfer.servicemanager.model.ServiceParam;

/* loaded from: classes.dex */
public abstract class BaseServiceCallback {
    private static final String TAG = "BaseServiceCallback";
    private Context context;

    protected Context getContext() {
        return this.context;
    }

    public abstract void onDiscoverCallback(PublishServiceParam publishServiceParam);

    public void onReceiveCommMsg(ServiceParam serviceParam) {
        Log.d(TAG, "onReceiveCommMsg not handled, uuid: " + serviceParam.getUuid());
    }

    public abstract void onStartService(ServiceParam serviceParam);

    public abstract void onStartServiceCallback(CallBackResult callBackResult);

    public abstract void onStopService(ServiceParam serviceParam);

    public abstract void onStopServiceCallback(CallBackResult callBackResult);

    public void setContext(Context context) {
        this.context = context;
    }
}
