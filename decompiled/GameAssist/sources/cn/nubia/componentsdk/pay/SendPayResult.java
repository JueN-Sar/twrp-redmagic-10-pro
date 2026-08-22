package cn.nubia.componentsdk.pay;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import cn.nubia.componentsdk.until.PayLog;
import com.zte.distbus.basetransfer.Constants;

/* loaded from: classes.dex */
public class SendPayResult {
    public static void a(String str, int i2, String str2, Context context) {
        Intent intent = new Intent();
        intent.setAction("nubia.pay.broadcast.action");
        intent.putExtra("response_code", i2);
        intent.putExtra(Constants.EXTRA_RESULT, str2);
        PayLog.a("pay", "send package:" + str);
        if (!TextUtils.isEmpty(str)) {
            intent.setPackage(str);
        }
        context.sendBroadcast(intent);
    }
}
