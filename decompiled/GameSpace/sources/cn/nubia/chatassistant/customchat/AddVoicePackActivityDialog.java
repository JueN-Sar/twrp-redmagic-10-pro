package cn.nubia.chatassistant.customchat;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import cn.nubia.chatassistant.db.ChatAssistantBean;
import cn.nubia.chatassistant.db.DBManager;
import cn.nubia.chatassistant.db.DBOpenHelper;
import cn.nubia.chatassistant.util.LogUtils;
import cn.nubia.chatassistant.util.ReportUtils;
import cn.nubia.chatassistant.util.ToastUtils;
import cn.nubia.gamelauncher.R;
import cn.nubia.globalsearch.GlobalSearchConstants;

/* loaded from: classes.dex */
public class AddVoicePackActivityDialog extends Activity implements View.OnClickListener {
    private static final String TAG = "AddVoicePackActivityDialog";
    private TextView cancelAddVoicePack;
    private TextView confirmAddVoicePack;
    private EditText voicePackName;

    private void initView() {
        this.voicePackName = (EditText) findViewById(R.id.input_voice_pack_name);
        this.confirmAddVoicePack = (TextView) findViewById(R.id.btn_confirm);
        this.cancelAddVoicePack = (TextView) findViewById(R.id.btn_cancel);
        this.confirmAddVoicePack.setOnClickListener(this);
        this.cancelAddVoicePack.setOnClickListener(this);
    }

    public void hideBottomUIMenu() {
        getWindow().addFlags(1024);
        getWindow().getDecorView().setSystemUiVisibility(4118);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_cancel) {
            finish();
            return;
        }
        if (id != R.id.btn_confirm) {
            return;
        }
        String obj = this.voicePackName.getText().toString();
        Cursor cursor = null;
        try {
            Cursor queryAllVoicePackData = DBManager.getInstance(getApplicationContext()).queryAllVoicePackData();
            if (queryAllVoicePackData != null) {
                Settings.Global.putInt(getContentResolver(), "voice_pack_quantity", queryAllVoicePackData.getCount());
                while (true) {
                    if (!queryAllVoicePackData.moveToNext()) {
                        break;
                    }
                    String string = queryAllVoicePackData.getString(queryAllVoicePackData.getColumnIndex(DBOpenHelper.VOICE_PACK_NAME));
                    LogUtils.i(TAG, "queryAllVoicePackData name: " + string);
                    if (string.equals(obj)) {
                        ToastUtils.showToast(getApplicationContext(), getResources().getString(R.string.voice_pack_exist), 0);
                        break;
                    }
                }
                if (!TextUtils.isEmpty(obj) && obj.length() <= 4) {
                    if (obj.contains(" ")) {
                        ToastUtils.showToast(getApplicationContext(), getResources().getString(R.string.edit_error_text), 0);
                    } else {
                        int i = Settings.Global.getInt(getContentResolver(), "voice_pack_quantity", 0);
                        ChatAssistantBean chatAssistantBean = new ChatAssistantBean();
                        chatAssistantBean.voicePackPosition = i;
                        chatAssistantBean.voicePackName = obj;
                        chatAssistantBean.voicePackSystem = 1;
                        DBManager.getInstance(getApplicationContext()).insertEventToDb(chatAssistantBean);
                        ReportUtils.onReportChatAssistantAddOrDelete(getApplicationContext(), GlobalSearchConstants.ADD);
                        finish();
                    }
                }
                ToastUtils.showToast(getApplicationContext(), getResources().getString(R.string.edit_voice_pack_error), 0);
            }
            if (queryAllVoicePackData != null) {
                try {
                    queryAllVoicePackData.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            throw th;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtils.i(TAG, "onCreate : ");
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        hideBottomUIMenu();
        getWindow().setGravity(80);
        setContentView(R.layout.add_voice_pack_dialog);
        initView();
    }
}
