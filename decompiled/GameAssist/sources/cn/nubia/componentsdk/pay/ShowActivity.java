package cn.nubia.componentsdk.pay;

import android.app.FragmentTransaction;
import android.os.Bundle;
import cn.nubia.componentsdk.pay.BaseActivity;

/* loaded from: classes.dex */
public class ShowActivity extends BaseActivity {

    /* renamed from: h, reason: collision with root package name */
    public static ShowActivity f6021h;

    private void d() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", getIntent() != null ? getIntent().getStringExtra("msg") : "加载中");
        progressDialogFragment.setArguments(bundle);
        progressDialogFragment.show(beginTransaction, "dialog");
    }

    @Override // cn.nubia.componentsdk.pay.BaseActivity
    public void c() {
        super.c();
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        this.f5911c.cancel();
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f6021h = this;
        d();
        BaseActivity.Timer timer = new BaseActivity.Timer(10000L, 2000L);
        this.f5911c = timer;
        timer.start();
    }
}
