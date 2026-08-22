package cn.nubia.projection;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: classes.dex */
public class ProjectionPanelService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        if (intent != null && ProjectionManager.o().C()) {
            ProjectionManager.o().q().y(intent.getStringExtra("cmd"), intent.getStringExtra("param"));
        }
        stopSelf();
        return super.onStartCommand(intent, i2, i3);
    }
}
