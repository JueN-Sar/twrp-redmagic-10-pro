package cn.nubia.chatassistant.customchat;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.nubia.chatassistant.bean.ChatAssistantVoiceBean;
import cn.nubia.chatassistant.fragment.BaseFragment;
import cn.nubia.chatassistant.fragment.ImportFragment;
import cn.nubia.chatassistant.fragment.RecorderFragment;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.common.app.AlertDialog;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class AddRecorderActivityDialog extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "AddRecorderActivityDialog";
    private ChatAssistantVoiceBean chatAssistantVoiceBean;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment mImportFragment;
    private TextView mImportTitle;
    private Fragment mRecorderFragment;
    private TextView mRecorderTitle;

    private void initData() {
        LogUtils.i(TAG, "initData showRecordFragment : ");
        this.fragmentManager = getSupportFragmentManager();
        this.chatAssistantVoiceBean = (ChatAssistantVoiceBean) getIntent().getSerializableExtra("chatAssistantVoiceBean");
        showRecordFragment();
    }

    private void initView() {
        this.mRecorderTitle = (TextView) findViewById(R.id.recorder_title);
        this.mImportTitle = (TextView) findViewById(R.id.import_title);
        this.mRecorderTitle.setOnClickListener(this);
        this.mImportTitle.setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRecordPermission() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.RECORD_AUDIO") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.RECORD_AUDIO"}, 999);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestWritePermission() {
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 998);
        }
    }

    private void showImportFragment() {
        requestWritePermission();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        if (this.mImportFragment == null) {
            this.mImportFragment = BaseFragment.newInstance(ImportFragment.class, this.chatAssistantVoiceBean);
        }
        this.fragmentTransaction.replace(R.id.fragment_layout, this.mImportFragment);
        this.fragmentTransaction.commit();
        this.mImportTitle.setBackgroundResource(R.mipmap.chat_assistant_recorder_select);
        this.mRecorderTitle.setBackgroundColor(Color.parseColor("#222531"));
        this.mRecorderTitle.setAlpha(0.6f);
        this.mImportTitle.setAlpha(1.0f);
    }

    private void showRecordFragment() {
        requestRecordPermission();
        this.fragmentTransaction = this.fragmentManager.beginTransaction();
        if (this.mRecorderFragment == null) {
            this.mRecorderFragment = BaseFragment.newInstance(RecorderFragment.class, this.chatAssistantVoiceBean);
        }
        this.fragmentTransaction.replace(R.id.fragment_layout, this.mRecorderFragment);
        this.fragmentTransaction.commit();
        this.mImportTitle.setBackgroundColor(Color.parseColor("#222531"));
        this.mRecorderTitle.setBackgroundResource(R.mipmap.chat_assistant_recorder_select);
        this.mRecorderTitle.setAlpha(1.0f);
        this.mImportTitle.setAlpha(0.6f);
    }

    private void showRecordPermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.request_recorder_permission_text));
        builder.setPositiveButton(getResources().getString(R.string.com_text), new DialogInterface.OnClickListener() { // from class: cn.nubia.chatassistant.customchat.AddRecorderActivityDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AddRecorderActivityDialog.this.requestRecordPermission();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel_text), (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    private void showWritePermissionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.request_read_permission_text));
        builder.setPositiveButton(getResources().getString(R.string.com_text), new DialogInterface.OnClickListener() { // from class: cn.nubia.chatassistant.customchat.AddRecorderActivityDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                AddRecorderActivityDialog.this.requestWritePermission();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel_text), (DialogInterface.OnClickListener) null);
        builder.create().show();
    }

    public void hideBottomUIMenu() {
        getWindow().addFlags(1024);
        getWindow().getDecorView().setSystemUiVisibility(4118);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.import_title) {
            showImportFragment();
        } else {
            if (id != R.id.recorder_title) {
                return;
            }
            showRecordFragment();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i(TAG, "onCreate : ");
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        hideBottomUIMenu();
        setContentView(R.layout.scen_dialog);
        initView();
        initData();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (iArr != null) {
            try {
                if (iArr.length <= 0) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (i == 999) {
            if (iArr[0] == 0) {
                return;
            }
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
                showRecordPermissionDialog();
                return;
            } else {
                Toast.makeText(this, getResources().getString(R.string.hint_permission_text), 1).show();
                return;
            }
        }
        if (i == 998 && iArr[0] != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE")) {
                showWritePermissionDialog();
            } else {
                Toast.makeText(this, getResources().getString(R.string.hint_read_permission_text), 1).show();
            }
        }
    }
}
