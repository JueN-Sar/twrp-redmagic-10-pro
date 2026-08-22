package cn.nubia.plugin.screenextraction.view;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import cn.nubia.gameassist.R;
import cn.nubia.plugin.screenextraction.ScreenExtractionManager;
import cn.nubia.plugin.screenextraction.bean.ScreenExtractionData;
import cn.nubia.plugin.screenextraction.view.SettingsDataView;
import cn.nubia.plugin.screenextraction.view.SettingsLayoutView;
import com.zte.gameassist.common.SystemMgr;

/* loaded from: classes.dex */
public class SettingsRootView extends FrameLayout implements SettingsDataView.Callback, SettingsLayoutView.Callback {
    private Callback mCallback;
    private Handler mHandler;
    private ScreenExtractionData mScreenExtractionData;
    private SettingsDataView mSettingsDataView;
    private SettingsLayoutView mSettingsLayoutView;

    public interface Callback {
        void c(boolean z, ScreenExtractionData screenExtractionData);
    }

    public SettingsRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g() {
        if (this.mSettingsDataView.c()) {
            this.mSettingsLayoutView.setVisibility(8);
        } else {
            this.mSettingsLayoutView.setVisibility(0);
        }
    }

    private ScreenExtractionData h() {
        Rect srcData = this.mSettingsDataView.getSrcData();
        Rect dstData = this.mSettingsDataView.getDstData();
        float screenExtractionAlpha = this.mSettingsLayoutView.getScreenExtractionAlpha();
        return new ScreenExtractionData(SystemMgr.t(), srcData, dstData, this.mSettingsLayoutView.getMode(), screenExtractionAlpha);
    }

    @Override // cn.nubia.plugin.screenextraction.view.SettingsLayoutView.Callback
    public void a() {
        i();
    }

    @Override // cn.nubia.plugin.screenextraction.view.SettingsDataView.Callback
    public void b() {
        i();
    }

    @Override // cn.nubia.plugin.screenextraction.view.SettingsDataView.Callback
    public void c(boolean z) {
        this.mHandler.postDelayed(new Runnable() { // from class: cn.nubia.plugin.screenextraction.view.d
            @Override // java.lang.Runnable
            public final void run() {
                SettingsRootView.this.g();
            }
        }, z ? 0L : 30L);
    }

    @Override // cn.nubia.plugin.screenextraction.view.SettingsLayoutView.Callback
    public void d(boolean z) {
    }

    @Override // cn.nubia.plugin.screenextraction.view.SettingsLayoutView.Callback
    public void e(boolean z) {
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.c(z, h());
        }
    }

    public void i() {
        ScreenExtractionManager.w().Q(h());
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mHandler = new Handler(Looper.myLooper());
        SettingsDataView settingsDataView = (SettingsDataView) findViewById(R.id.settings_data_view);
        this.mSettingsDataView = settingsDataView;
        settingsDataView.setCallback(this);
        this.mSettingsLayoutView = (SettingsLayoutView) findViewById(R.id.settings_layout_view);
        ScreenExtractionData screenExtractionData = this.mScreenExtractionData;
        if (screenExtractionData != null) {
            this.mSettingsDataView.setScreenExtractionData(screenExtractionData);
            this.mSettingsLayoutView.setScreenExtractionData(this.mScreenExtractionData);
        }
        this.mSettingsLayoutView.setCallback(this);
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public void setScreenExtractionData(ScreenExtractionData screenExtractionData) {
        this.mScreenExtractionData = screenExtractionData;
        SettingsDataView settingsDataView = this.mSettingsDataView;
        if (settingsDataView != null) {
            settingsDataView.setScreenExtractionData(screenExtractionData);
        }
        SettingsLayoutView settingsLayoutView = this.mSettingsLayoutView;
        if (settingsLayoutView != null) {
            settingsLayoutView.setScreenExtractionData(screenExtractionData);
        }
        h();
        postInvalidate();
    }

    public SettingsRootView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }
}
