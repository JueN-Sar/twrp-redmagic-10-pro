package cn.nubia.gamecenter.settings.basic;

import android.os.Bundle;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import cn.nubia.common.GameKeyObserver;
import cn.nubia.common.config.GameSpaceConfig;
import cn.nubia.common.util.CommonUtil;

/* loaded from: classes.dex */
public class BasicActivity extends FragmentActivity implements GameKeyObserver.Callback {
    private void setShortEdges() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.layoutInDisplayCutoutMode = 1;
        getWindow().setAttributes(attributes);
    }

    public void hideNavigationBar() {
        getWindow().getDecorView().setSystemUiVisibility(5382);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setShortEdges();
        requestWindowFeature(1);
        getWindow().addFlags(263968);
        hideNavigationBar();
        GameKeyObserver.getInstance(this).addCallback(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        GameKeyObserver.getInstance(this).removeCallback(this);
    }

    @Override // cn.nubia.common.GameKeyObserver.Callback
    public void onGameKeyChanged(boolean z) {
        if (CommonUtil.isZte()) {
            finish();
        }
        if (!GameSpaceConfig.supportGameKey() || z) {
            finish();
        }
    }
}
