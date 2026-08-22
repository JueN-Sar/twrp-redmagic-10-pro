package cn.nubia.gameassist.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import cn.nubia.screensaver.GameScreensaverManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class GameScreenSaverService extends Service {
    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        GameScreensaverManager.L().E(fileDescriptor, printWriter, "");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return GameScreensaverManager.L().M();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        GameScreensaverManager.L().O();
    }
}
