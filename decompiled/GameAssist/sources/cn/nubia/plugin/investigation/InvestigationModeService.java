package cn.nubia.plugin.investigation;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;

/* loaded from: classes.dex */
public class InvestigationModeService extends Service {

    /* renamed from: c, reason: collision with root package name */
    private Context f8561c;

    /* renamed from: h, reason: collision with root package name */
    private InvestigationModeFloatingPanel f8562h = null;

    /* renamed from: i, reason: collision with root package name */
    private boolean f8563i = ZteFeature.isSupportGameDisplayFilterEffect();

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        GaLog.a("InvestigationService", "onCreate");
        this.f8561c = this;
        if (this.f8563i) {
            return;
        }
        this.f8562h = new InvestigationModeFloatingPanel(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        GaLog.a("InvestigationService", "onDestroy!");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        GaLog.a("InvestigationService", "onStartCommand");
        InvestigationModeFloatingPanel investigationModeFloatingPanel = this.f8562h;
        if (investigationModeFloatingPanel == null) {
            return 2;
        }
        investigationModeFloatingPanel.p();
        return 2;
    }
}
