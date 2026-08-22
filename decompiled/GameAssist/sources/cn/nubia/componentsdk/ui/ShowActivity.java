package cn.nubia.componentsdk.ui;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import cn.nubia.componentsdk.PayClientManager;
import cn.nubia.componentsdk.until.PayLog;

/* loaded from: classes.dex */
public class ShowActivity extends Activity {

    /* renamed from: h, reason: collision with root package name */
    public static ShowActivity f6052h;

    /* renamed from: c, reason: collision with root package name */
    public String f6053c = "ShowActivity";

    private void a() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        BasicDialog basicDialog = new BasicDialog();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", getIntent() != null ? getIntent().getStringExtra("msg") : "正在检测新版本");
        basicDialog.setArguments(bundle);
        basicDialog.show(beginTransaction, "dialog");
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f6052h = this;
        PayLog.b(this.f6053c, "ShowActivity isCancelDialog:" + PayClientManager.f5880l);
        if (!PayClientManager.f5880l) {
            a();
        }
        if (PayClientManager.f5880l) {
            PayClientManager.f5880l = false;
            finish();
        }
    }
}
