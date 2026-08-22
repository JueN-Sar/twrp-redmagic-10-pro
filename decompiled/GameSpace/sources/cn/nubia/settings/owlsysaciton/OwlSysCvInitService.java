package cn.nubia.settings.owlsysaciton;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import cn.nubia.gamecenter.settings.utils.LogUtil;
import cn.nubia.settings.trackclient.NubiaTrackManager;

/* loaded from: classes.dex */
public class OwlSysCvInitService extends IntentService {
    private static final String TAG = "OwlSysCvInitService";

    public OwlSysCvInitService() {
        super("Settings:OwlSysCvInitService");
    }

    @Override // android.app.IntentService, android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        LogUtil.i(TAG, "onHandleIntent initOwlCv." + intent);
        if (intent == null) {
            return;
        }
        NubiaTrackManager.getInstance().init(getApplicationContext());
        OwlSysHelper.getInstance(getApplicationContext()).initOwlCv();
    }
}
