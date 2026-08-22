package cn.nubia.gamelauncher.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.helper.IdentifyHelper;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.controller.AppListController;
import cn.nubia.gamelauncher.controller.AppListTopBarController;
import cn.nubia.gamelauncher.model.AppAddModel;
import cn.nubia.gamelauncher.util.Util;

/* loaded from: classes.dex */
public class AppAddActivity extends BaseActivity {
    private RelativeLayout mActionBar;
    private ConstraintLayout mBottomView;
    private TextView mHostActionBar;
    private AppListTopBarController mAppListTopBarController = null;
    private AppListController mAddListController = null;
    private boolean mIsHostMode = false;

    private void initController() {
        AppListTopBarController appListTopBarController = new AppListTopBarController();
        this.mAppListTopBarController = appListTopBarController;
        appListTopBarController.init(this);
        AppListController appListController = new AppListController();
        this.mAddListController = appListController;
        appListController.init(this, this.mIsHostMode);
    }

    private void initHostMode() {
        this.mIsHostMode = getIntent().getBooleanExtra("HostMode", false);
        Log.d("HostMode", "AppAddActivity -- initHostMode() mIsHostMode : " + this.mIsHostMode);
    }

    private void initView() {
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.app_add_layout);
        this.mActionBar = (RelativeLayout) findViewById(R.id.addview_actionbar);
        this.mBottomView = (ConstraintLayout) findViewById(R.id.bottom_view);
        this.mHostActionBar = (TextView) findViewById(R.id.host_actionbar);
        initHostMode();
        if (this.mIsHostMode) {
            this.mActionBar.setVisibility(8);
            this.mHostActionBar.setVisibility(0);
            this.mBottomView.setVisibility(0);
        }
    }

    private void showIdentifyDialog() {
        AlertDialog create = new AlertDialog.Builder(this, 2131952382).setMessage(R.string.identify_dialog_message).setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.AppAddActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                IdentifyHelper.getInstance().setIdentifyClose();
                dialogInterface.dismiss();
            }
        }).setPositiveButton(getString(R.string.identify_dialog_ok), new DialogInterface.OnClickListener() { // from class: cn.nubia.gamelauncher.activity.AppAddActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i("identify", "GameSpace - showIdentifyDialog() - click to open identify !");
                IdentifyHelper.getInstance().setIdentifyOpen();
                AppAddModel.getInstance().verifyNotAddGameAppByFeature();
                dialogInterface.dismiss();
            }
        }).create();
        create.show();
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
    }

    private void showIdentifyDialogIfNeed() {
        if (IdentifyHelper.getInstance().isIdentifyNotInit() && !this.mIsHostMode && GameSpaceConfig.supportIdentify()) {
            showIdentifyDialog();
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initView();
        initController();
        showIdentifyDialogIfNeed();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mAddListController.onDestory();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mAddListController.onPasue();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mAddListController.onResume();
        if (this.mIsHostMode) {
            Util.updateAppAddResumed(true);
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mIsHostMode) {
            Util.updateAppAddResumed(false);
        }
    }
}
