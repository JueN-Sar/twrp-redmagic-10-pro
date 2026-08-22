package cn.nubia.gameassist.common;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.SystemMgr;

/* loaded from: classes.dex */
public class GameAssistBaseActivity extends Activity implements GameMonitor.Callback {
    public void a(boolean z) {
        if (z) {
            getWindow().getDecorView().setSystemUiVisibility(5382);
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 3;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(512);
        getWindow().addFlags(256);
        SystemMgr.y(getApplicationContext()).h(this);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SystemMgr.y(getApplicationContext()).i(this);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        finish();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        a(true);
        new Handler().postDelayed(new Runnable() { // from class: cn.nubia.gameassist.common.GameAssistBaseActivity.1
            @Override // java.lang.Runnable
            public void run() {
                GameAssistBaseActivity.this.a(true);
            }
        }, 1000L);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        a(z);
    }
}
