package cn.nubia.componentsdk.ui;

import android.app.Activity;
import android.os.Bundle;
import cn.nubia.componentsdk.MiscCallbackListener;
import cn.nubia.componentsdk.PayClientManager;

/* loaded from: classes.dex */
public class CheckActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        finish();
        PayClientManager.p();
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i2, strArr, iArr);
        if (strArr == null || i2 != 18) {
            return;
        }
        if (iArr.length <= 0 || iArr[0] != 0) {
            finish();
            MiscCallbackListener.a(127, "未获得相关权限,无法安装努比亚安全支付应用");
        } else {
            finish();
            PayClientManager.p();
        }
    }
}
