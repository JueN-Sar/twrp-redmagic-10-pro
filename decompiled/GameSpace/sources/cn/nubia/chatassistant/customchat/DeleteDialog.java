package cn.nubia.chatassistant.customchat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.gamelauncher.R;

/* loaded from: classes.dex */
public class DeleteDialog extends Activity {
    private static final String TAG = "DeleteDialog";
    private TextView cancelButton;
    private TextView commitButton;
    private TextView text;
    private String voicePackName = "";
    private String voiceFileName = "";

    private void initData() {
        String stringExtra = getIntent().getStringExtra("text");
        this.voicePackName = getIntent().getStringExtra("voicePackName");
        this.voiceFileName = getIntent().getStringExtra("voiceFileName");
        this.text.setText(stringExtra);
        if (stringExtra.equals(getResources().getString(R.string.select_delete_voice_text)) || stringExtra.equals(getResources().getString(R.string.select_delete_voice_pack_text))) {
            return;
        }
        this.cancelButton.setText(getResources().getString(R.string.com_text));
        this.commitButton.setText(getResources().getString(R.string.cancel_text));
        this.commitButton.setTextColor(getResources().getColor(R.color.white, null));
        this.commitButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.customchat.DeleteDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeleteDialog.this.finish();
            }
        });
        this.cancelButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.customchat.DeleteDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ok", "ok");
                intent.putExtra("voicePackName", DeleteDialog.this.voicePackName);
                intent.putExtra("voiceFileName", DeleteDialog.this.voiceFileName);
                DeleteDialog.this.setResult(-1, intent);
                DeleteDialog.this.finish();
            }
        });
    }

    private void initView() {
        this.cancelButton = (TextView) findViewById(R.id.cancel_btn);
        this.commitButton = (TextView) findViewById(R.id.commit_btn);
        this.text = (TextView) findViewById(R.id.delete_text);
        this.cancelButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.customchat.DeleteDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeleteDialog.this.finish();
            }
        });
        this.commitButton.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.chatassistant.customchat.DeleteDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ok", "ok");
                intent.putExtra("voicePackName", DeleteDialog.this.voicePackName);
                intent.putExtra("voiceFileName", DeleteDialog.this.voiceFileName);
                DeleteDialog.this.setResult(-1, intent);
                DeleteDialog.this.finish();
            }
        });
    }

    public void hideBottomUIMenu() {
        getWindow().addFlags(1024);
        getWindow().getDecorView().setSystemUiVisibility(4118);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i(TAG, "onCreate : ");
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        hideBottomUIMenu();
        getWindow().setGravity(80);
        setContentView(R.layout.activity_delete_recorder_dialog);
        initView();
        initData();
    }
}
