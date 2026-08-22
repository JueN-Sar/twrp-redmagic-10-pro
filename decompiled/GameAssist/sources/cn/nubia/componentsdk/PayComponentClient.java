package cn.nubia.componentsdk;

import android.app.Activity;
import cn.nubia.componentsdk.constant.CallbackListener;
import cn.nubia.componentsdk.until.CommonUtils;
import cn.nubia.componentsdk.until.NetUtil;
import cn.nubia.componentsdk.until.PayLog;
import java.util.HashMap;

/* loaded from: classes.dex */
public class PayComponentClient {
    public static void a(Activity activity, HashMap hashMap, CallbackListener callbackListener) {
        PayLog.a("PAY", "CommonUtils.isFastClick() check");
        if (CommonUtils.b()) {
            PayLog.a("PAY", "isFastClick return;");
            return;
        }
        PayLog.a("PAY", "setPayProcessListener listener：" + callbackListener);
        MiscCallbackListener.b(callbackListener);
        if (!NetUtil.a(activity.getApplication())) {
            MiscCallbackListener.a(110, "网络不可用");
            return;
        }
        PayLog.a("PAY", "doPay map:" + hashMap);
        PayClientManager.y(activity).u(hashMap);
    }
}
