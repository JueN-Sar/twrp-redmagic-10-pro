package cn.nubia.gamecenter.settings.records.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCaller;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.util.CommonUtil;
import cn.nubia.common.util.FeatureUtil;
import cn.nubia.gamecenter.settings.R;
import cn.nubia.gamecenter.settings.compatible.GameKeysHelper;
import cn.nubia.gamecenter.settings.records.StartInfo;
import cn.nubia.gamecenter.settings.records.utils.HighLightsUtils;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.settings.trackclient.NubiaTrackManager;

/* loaded from: classes.dex */
public class RecordsActivity extends FragmentActivity implements GameKeyObserver.Callback {
    private static final String TAG = "RecordsActivity";
    private Fragment mainFragment;
    private HandlerThread handlerThread = null;
    private Handler handler = null;

    /* JADX INFO: Access modifiers changed from: private */
    public void finishActivity() {
        this.mainFragment.onDestroy();
        this.handler.postDelayed(new Runnable() { // from class: cn.nubia.gamecenter.settings.records.activity.RecordsActivity.2
            @Override // java.lang.Runnable
            public void run() {
                RecordsActivity.this.finish();
            }
        }, 250L);
    }

    private void getKeyAndFinish() {
        Handler handler = this.handler;
        if (handler == null) {
            return;
        }
        handler.post(new Runnable() { // from class: cn.nubia.gamecenter.settings.records.activity.RecordsActivity.3
            @Override // java.lang.Runnable
            public void run() {
                String readNodeValue = GameKeysHelper.getDefault().readNodeValue(RecordsActivity.this.getApplicationContext());
                if (readNodeValue == null) {
                    readNodeValue = "0";
                }
                if ("0".equals(readNodeValue)) {
                    LogUtil.i(RecordsActivity.TAG, "Finish Activity Because Close GameKey!");
                    RecordsActivity.this.finish();
                }
            }
        });
    }

    private void initHandler() {
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
    }

    private void initView() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ImageView imageView = (ImageView) findViewById(R.id.redmagictime_back_arrow);
        TextView textView = (TextView) findViewById(R.id.titlebar_text);
        this.mainFragment = getSupportFragmentManager().findFragmentById(R.id.gcs_main_content);
        imageView.setClickable(true);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gamecenter.settings.records.activity.RecordsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecordsActivity.this.finishActivity();
            }
        });
        if (CommonUtil.isRedMagicRunOnMyOs() || HighLightsUtils.isRedMagicPad() || HighLightsUtils.isNP02J() || HighLightsUtils.isNubiaOS() || HighLightsUtils.isRedMagic()) {
            textView.setText(getResources().getText(R.string.gcs_gamecenter_menu_records));
        } else {
            textView.setText(getResources().getText(R.string.gcs_gamecenter_menu_records_no_gamekey));
        }
    }

    private void initWindow() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
        getWindow().getDecorView().setSystemUiVisibility(5126);
    }

    private void setPackageName(boolean z) {
        if (getIntent() != null) {
            ActivityResultCaller activityResultCaller = this.mainFragment;
            if (activityResultCaller != null && (activityResultCaller instanceof StartInfo)) {
                ((StartInfo) activityResultCaller).setRMTPackageName(getIntent().getStringExtra("package_name"));
            }
            if (z) {
                LogUtil.i(TAG, "******onNewIntent : " + getIntent().getStringExtra("package_name"));
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initWindow();
        boolean booleanValue = FeatureUtil.getBoolean(HighLightsUtils.ZTE_FEATURE_ANTI_MISOPERATE_NUBIA, false).booleanValue();
        if (HighLightsUtils.isAboveU() || HighLightsUtils.isNP02J() || ((booleanValue || !HighLightsUtils.isNubiaOS()) && !booleanValue)) {
            setContentView(R.layout.gcs_record_main);
        } else {
            setContentView(R.layout.gcs_record_main_old);
        }
        initView();
        setPackageName(false);
        initHandler();
        NubiaTrackManager.getInstance().init(getApplicationContext());
        GameKeyObserver.getInstance(this).addCallback(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        HandlerThread handlerThread = this.handlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
        }
        super.onDestroy();
        GameKeyObserver.getInstance(this).removeCallback(this);
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        if (CommonUtil.isZte()) {
            finish();
        }
        if (z) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        setPackageName(true);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getKeyAndFinish();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }
}
