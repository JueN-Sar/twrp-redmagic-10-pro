package cn.nubia.multisubscreen.callback;

import cn.nubia.multisubscreen.mgr.MultiDeviceDataMgr;
import com.zte.distbus.basetransfer.BaseServiceCallback;
import com.zte.distbus.basetransfer.servicemanager.model.CallBackResult;
import com.zte.distbus.basetransfer.servicemanager.model.PublishServiceParam;
import com.zte.distbus.basetransfer.servicemanager.model.ServiceParam;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class ScreenCastServiceCallback extends BaseServiceCallback {
    private static final String TAG = "MultiSubScreen_ScreenCastServiceCallback";

    @Override // com.zte.distbus.basetransfer.BaseServiceCallback
    public void onDiscoverCallback(PublishServiceParam publishServiceParam) {
        GaLog.a(TAG, "onDiscoverCallback");
    }

    @Override // com.zte.distbus.basetransfer.BaseServiceCallback
    public void onReceiveCommMsg(ServiceParam serviceParam) {
        GaLog.a(TAG, "onReceiveCommMsg, uuid: " + serviceParam.getUuid());
        GaLog.a(TAG, "onReceiveCommMsg, profile: " + serviceParam.getProfile());
        MultiDeviceDataMgr.b().c(serviceParam);
    }

    @Override // com.zte.distbus.basetransfer.BaseServiceCallback
    public void onStartService(ServiceParam serviceParam) {
        GaLog.a(TAG, "onStartService");
    }

    @Override // com.zte.distbus.basetransfer.BaseServiceCallback
    public void onStartServiceCallback(CallBackResult callBackResult) {
        GaLog.a(TAG, "onStartServiceCallback uuid = " + callBackResult.getUuid() + ", profile = " + callBackResult.getProfile());
    }

    @Override // com.zte.distbus.basetransfer.BaseServiceCallback
    public void onStopService(ServiceParam serviceParam) {
        GaLog.a(TAG, "onStopService uuid = " + serviceParam.getUuid() + ", profile = " + serviceParam.getProfile());
    }

    @Override // com.zte.distbus.basetransfer.BaseServiceCallback
    public void onStopServiceCallback(CallBackResult callBackResult) {
        GaLog.a(TAG, "onStopServiceCallback deviceId =" + callBackResult.getDeviceId());
    }
}
