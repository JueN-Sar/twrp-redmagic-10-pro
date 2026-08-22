package cn.nubia.gameassist.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.PrintWriterPrinter;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.gameassist.onemorething.OneMoreThingManager;
import cn.nubia.gameassist.plugin.config.PluginConfig;
import cn.nubia.gameassist.service.GameAssistService;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.hostassist.HostAssistMgr;
import cn.nubia.magicwindow.MagicWindowMgr;
import cn.nubia.multisubscreen.mgr.DistributeBusMgr;
import cn.nubia.screensaver.GameScreensaverManager;
import com.zte.gameassist.common.DisplayMgr;
import com.zte.gameassist.common.DumpController;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.SystemWindowMonitor;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.shared.wrapper.ZteFeatureWrapper;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes.dex */
public class GameAssistService extends Service {

    /* renamed from: j, reason: collision with root package name */
    private static String f7426j = "GameAssistService";

    /* renamed from: c, reason: collision with root package name */
    private final Handler f7427c = new Handler(Looper.getMainLooper());

    /* renamed from: h, reason: collision with root package name */
    private SystemWindowMonitor.ICallback f7428h = new SystemWindowMonitor.ICallback() { // from class: h.a
        @Override // com.zte.gameassist.common.SystemWindowMonitor.ICallback
        public final void a(boolean z, String str) {
            GameAssistService.this.b(z, str);
        }
    };

    /* renamed from: i, reason: collision with root package name */
    private boolean f7429i = false;

    public GameAssistService() {
        GaLog.a(f7426j, "GameAssistService()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, String str) {
        if (z) {
            c();
        }
    }

    private void c() {
        GaLog.k(f7426j, "screenshotDumpStringToLog - begin:\n");
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            printWriter.println(f7426j + ":");
            printWriter.println("    versionName:" + Utils.u(getApplicationContext()));
            SystemMgr.y(getApplicationContext()).r(printWriter);
            PluginConfig.b(this, printWriter);
            RotationMgr.e(getApplicationContext()).d(printWriter);
            GaLog.e(f7426j, stringWriter.toString());
        } catch (Exception e2) {
            GaLog.e(f7426j, "screenshotDumpStringToLog - dump error " + e2);
            e2.printStackTrace();
        }
        GaLog.k(f7426j, "screenshotDumpStringToLog - end:\n");
    }

    @Override // android.app.Service
    protected void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        try {
            printWriter.println(f7426j + ":");
            printWriter.println("    versionName:" + Utils.u(getApplicationContext()));
            new PrintWriterPrinter(printWriter);
            DumpController.c().b(fileDescriptor, printWriter, strArr);
            RecycleWatch.m(fileDescriptor, printWriter, "  ");
            printWriter.println("");
            SystemMgr.y(getApplicationContext()).r(printWriter);
            HostAssistMgr.n().c(fileDescriptor, printWriter, strArr);
            printWriter.println("");
            PluginConfig.b(this, printWriter);
            printWriter.flush();
            if (GameScreensaverManager.I) {
                GameScreensaverManager.L().E(fileDescriptor, printWriter, "");
            }
            if (ZteFeature.isSupportMultiSubScreen()) {
                DistributeBusMgr.getInstance().dump(fileDescriptor, printWriter, strArr);
            }
            if (ZteFeature.isSupportMagicWindow()) {
                MagicWindowMgr.l().c(fileDescriptor, printWriter, strArr);
            }
            DisplayMgr.d().c(printWriter);
            RotationMgr.e(getApplicationContext()).d(printWriter);
            FoldMgr.c().b(printWriter);
            EventListenerMgr.c(fileDescriptor, printWriter, strArr);
            OneMoreThingManager.g().e(fileDescriptor, printWriter, strArr);
            Router.getInstance().dump(fileDescriptor, printWriter, strArr);
        } catch (Exception e2) {
            GaLog.e(f7426j, "GameAssistService - dump error " + e2);
            e2.printStackTrace();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return SystemMgr.y(getApplicationContext()).B();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (!this.f7429i && !"qcom".equals(ZteFeatureWrapper.get("soc_vendor", null))) {
            this.f7429i = true;
            SystemWindowMonitor.h().c("ZteScreenshot_Window_LongCaptureView", this.f7428h, this.f7427c);
        }
        GaLog.e(f7426j, "GameAssistService - onCreate()");
    }

    @Override // android.app.Service
    public void onDestroy() {
        GaLog.e(f7426j, "GameAssistService - onDestroy()");
        super.onDestroy();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        GaLog.e(f7426j, "GameAssistService - onLowMemory()");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i2, int i3) {
        GaLog.e(f7426j, "GameAssistService - onStartCommand()");
        return 1;
    }
}
