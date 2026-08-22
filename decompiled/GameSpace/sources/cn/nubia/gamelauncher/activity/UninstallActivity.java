package cn.nubia.gamelauncher.activity;

import android.os.Bundle;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.controller.UninstallController;

/* loaded from: classes.dex */
public class UninstallActivity extends BaseActivity {
    private UninstallController mUninstallController = null;

    private void initController() {
        UninstallController uninstallController = new UninstallController();
        this.mUninstallController = uninstallController;
        uninstallController.init(this);
    }

    private void initView() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.uninstall_layout);
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
        initController();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mUninstallController.onDestory();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mUninstallController.onPasue();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mUninstallController.onResume();
    }
}
