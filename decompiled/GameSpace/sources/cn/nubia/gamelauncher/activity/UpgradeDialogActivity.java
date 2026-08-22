package cn.nubia.gamelauncher.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.gamelauncher.R;
import cn.nubia.gamelauncher.aimhelper.ActivityUtils;
import cn.nubia.gamelauncher.upgrade.NetworkHelper;
import cn.nubia.gamelauncher.upgrade.UpgradeManager;
import cn.nubia.gamelauncher.util.ReflectUtilities;

/* loaded from: classes.dex */
public class UpgradeDialogActivity extends BaseActivity implements View.OnClickListener, UpgradeManager.Callback {
    public static final int STATE_CHECK_VERSION = 0;
    public static final int STATE_DATA_FLOW_PROMPT = 1;
    public static final int STATE_UPGRADE_NEW_VERSION = 2;
    Button mCancel;
    Button mCancelOnly;
    CheckBox mCheckBox;
    TextView mCheckBoxNote;
    TextView mContent;
    Button mOk;
    View mPlaceHolder;
    ProgressBar mProgressBar;
    TextView mTitle;
    private final String TAG = "Upgrade";
    private int mState = 0;

    private void checkNetworkConnected() {
        if (this.mState == 0 && !NetworkHelper.isNetworkConnected(this)) {
            Toast.makeText(getApplicationContext(), R.string.upgrade_no_network, 0).show();
            dismiss();
        }
    }

    private void checkReadyToWork() {
        Log.d("Upgrade", "Dialog ----------> checkReadyToWork()");
        initState();
        checkNetworkConnected();
    }

    private void checkStorageSpace() {
        if (this.mState == 0 && !UpgradeManager.getInstance().hasEnoughStorageSpace()) {
            showToast(R.string.upgrade_no_space);
            dismiss();
        }
    }

    private void clickCancel() {
        if (2 == this.mState) {
            UpgradeManager.getInstance().checkUpgradeDone();
            upgradeLater();
        }
        dismiss();
    }

    private void clickOk() {
        int i = this.mState;
        if (1 == i) {
            UpgradeManager.getInstance().continueDownloadEvenOnMobile();
            dismiss();
        } else if (2 == i) {
            UpgradeManager.getInstance().checkUpgradeDone();
            upgradeNow();
        }
    }

    private void dismiss() {
        Log.d("Upgrade", "Dialog ----------> dismiss() ");
        UpgradeManager.getInstance().unRegisterCallback();
        finish();
    }

    private String getUpgradeContent() {
        String upgradeContent = UpgradeManager.getInstance().getUpgradeContent();
        return upgradeContent != null ? upgradeContent : getString(R.string.upgrade_content_is_null);
    }

    private boolean hasEnoughStorageSpace() {
        return UpgradeManager.getInstance().hasEnoughStorageSpace();
    }

    private void initCheckVersionDialog() {
        UpgradeManager.getInstance().registerCallback(this);
        showTwoButton(false);
        updatePlaceHolderVisible(true);
        this.mTitle.setText("     " + getString(R.string.upgrade_checkversion));
        UpgradeManager.getInstance().startCheck(true);
    }

    private void initDataFlowPromptDialog() {
        showTwoButton(true);
        updatePlaceHolderVisible(true);
        this.mOk.setText(getString(R.string.upgrade_continue_download));
        this.mTitle.setText(getString(R.string.upgrade_flow_warnning));
    }

    private void initState() {
        this.mState = getIntent().getIntExtra("state", 0);
    }

    private void initUpgradeDialog() {
        showTwoButton(true);
        this.mOk.setText(getString(R.string.upgrade_immediately));
        this.mCancel.setText(getString(R.string.upgrade_later));
        updatePlaceHolderVisible(false);
        this.mTitle.setText(getString(R.string.upgrade_found_new_version));
        this.mContent.setText(getUpgradeContent());
        this.mContent.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void initView() {
        this.mOk = (Button) findViewById(R.id.dialog_button_ok);
        this.mCancel = (Button) findViewById(R.id.dialog_button_cancel);
        this.mCancelOnly = (Button) findViewById(R.id.dialog_button_cancel_only);
        this.mOk.setOnClickListener(this);
        this.mCancel.setOnClickListener(this);
        this.mCancelOnly.setOnClickListener(this);
        this.mTitle = (TextView) findViewById(R.id.dialog_title);
        this.mContent = (TextView) findViewById(R.id.dialog_content);
        this.mCheckBoxNote = (TextView) findViewById(R.id.check_box_note);
        this.mCheckBox = (CheckBox) findViewById(R.id.upgrade_check_box);
        this.mPlaceHolder = findViewById(R.id.place_holder);
        this.mProgressBar = (ProgressBar) findViewById(R.id.dialog_progress);
    }

    private void setLandscapeIfNeed() {
        int i = getResources().getConfiguration().orientation;
        Log.d("Upgrade", "orientation = " + i);
        if (i == 2) {
            setRequestedOrientation(0);
        } else {
            setRequestedOrientation(1);
        }
    }

    private void showToast(int i) {
        Log.d("Upgrade", "Dialog - showToast() : " + getString(i));
        Toast.makeText(getApplicationContext(), i, 0).show();
    }

    private void showTwoButton(boolean z) {
        this.mOk.setVisibility(z ? 0 : 8);
        this.mCancel.setVisibility(z ? 0 : 4);
        this.mCancelOnly.setVisibility(z ? 8 : 0);
        this.mProgressBar.setVisibility(z ? 8 : 0);
    }

    private String stateToString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "state : unknow" : "state : NEW_VERSION" : "state : FLOW_PROMPT" : "state : CHECK_VERSION";
    }

    private void updateDialogContent() {
        Log.d("Upgrade", "Dialog ----------> updateDialogContent(" + stateToString(this.mState) + ")");
        int i = this.mState;
        if (i == 0) {
            initCheckVersionDialog();
        } else if (i == 1) {
            initDataFlowPromptDialog();
        } else {
            if (i != 2) {
                return;
            }
            initUpgradeDialog();
        }
    }

    private void updatePlaceHolderVisible(boolean z) {
        this.mCheckBox.setVisibility(z ? 8 : 0);
        this.mCheckBoxNote.setVisibility(z ? 8 : 0);
        this.mContent.setVisibility(z ? 8 : 0);
        this.mPlaceHolder.setVisibility(z ? 0 : 8);
    }

    private void upgradeLater() {
        if (this.mCheckBox.isChecked()) {
            UpgradeManager.getInstance().ignoreThisVersion();
        } else {
            UpgradeManager.getInstance().setNewVersionFlag(true);
        }
    }

    private void upgradeNow() {
        if (!NetworkHelper.isNetworkConnected(getApplicationContext())) {
            showToast(R.string.upgrade_no_network);
            UpgradeManager.getInstance().setNewVersionFlag(true);
            dismiss();
        } else if (hasEnoughStorageSpace()) {
            UpgradeManager.getInstance().registerCallback(this);
            UpgradeManager.getInstance().doUpgradeNow();
        } else {
            Toast.makeText(getApplicationContext(), R.string.upgrade_no_space, 0).show();
            dismiss();
        }
    }

    @Override // cn.nubia.gamelauncher.upgrade.UpgradeManager.Callback
    public void dismissDialog(boolean z) {
        if (z) {
            showToast(R.string.upgrade_is_latest);
        }
        dismiss();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_button_cancel /* 2131362116 */:
                clickCancel();
                break;
            case R.id.dialog_button_cancel_only /* 2131362117 */:
                UpgradeManager.getInstance().cancelManualCheck();
                dismiss();
                break;
            case R.id.dialog_button_ok /* 2131362118 */:
                clickOk();
                break;
        }
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("Upgrade", "Dialog ----------> onCreate()");
        setLandscapeIfNeed();
        checkReadyToWork();
        setContentView(R.layout.upgrade_dialog_layout);
        getWindow().setGravity(80);
        initView();
        updateDialogContent();
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (ReflectUtilities.isGameKeyClose(this)) {
            Log.d("kyy", " onDestroy finish");
            finish();
        }
        UpgradeManager.getInstance().unRegisterCallback();
        Log.d("Upgrade", "Dialog ----------> onDestroy()");
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        Log.d("Upgrade", "Dialog ----------> onNewIntent() state : " + stateToString(intent.getIntExtra("state", 0)));
        initState();
        updateDialogContent();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        Log.d("Upgrade", "Dialog ----------> onPause()");
    }

    @Override // cn.nubia.gamelauncher.activity.BaseActivity, android.app.Activity
    protected void onResume() {
        Log.d("Upgrade", "onResume() ---------------------->top : " + ActivityUtils.getCurrentTopPkg(this));
        super.onResume();
        Log.d("Upgrade", "Dialog ----------> onResume() state : " + stateToString(getIntent().getIntExtra("state", 0)));
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (GameKeyObserver.getInstance(this).isNeedExit()) {
            Log.d("kyy", " onStop finish");
            finish();
        }
        Log.d("Upgrade", "Dialog ----------> onStop()");
    }

    @Override // cn.nubia.gamelauncher.upgrade.UpgradeManager.Callback
    public void updateDialog(int i) {
        this.mState = i;
        updateDialogContent();
    }
}
