package cn.nubia.neogamelib;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.NeoGameLibComService;
import cn.nubia.nbgame.sdk.NeoGameSdkHelp;
import cn.nubia.neogamelib.NeoGameLibComApplication;
import com.zte.gameassist.BaseApplication;
import com.zte.gameassist.common.GameCheck;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class NeoGameLibComApplication implements IComApplication, GameMonitor.Callback {

    /* renamed from: c, reason: collision with root package name */
    private Context f8330c;

    /* renamed from: i, reason: collision with root package name */
    private Handler f8332i;

    /* renamed from: j, reason: collision with root package name */
    private NeoGameLibComServiceImpl f8333j;

    /* renamed from: h, reason: collision with root package name */
    Router f8331h = Router.getInstance();

    /* renamed from: k, reason: collision with root package name */
    private final Runnable f8334k = new Runnable() { // from class: k.b
        @Override // java.lang.Runnable
        public final void run() {
            NeoGameLibComApplication.e();
        }
    };

    /* renamed from: l, reason: collision with root package name */
    private final Runnable f8335l = new Runnable() { // from class: k.c
        @Override // java.lang.Runnable
        public final void run() {
            NeoGameLibComApplication.this.f();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e() {
        String t = SystemMgr.t();
        GaLog.a("NeoGameLibComApplication", "pkg:" + t + "," + RotationMgr.j());
        NeoGameSdkHelp.c().a(BaseApplication.a(), t, !RotationMgr.j() ? 1 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f() {
        boolean d2 = NeoGameSdkHelp.c().d(this.f8330c);
        GaLog.a("NeoGameLibComApplication", "show " + d2);
        this.f8333j.c(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void g(Context context, List list) {
        GaLog.a("NeoGameLibComApplication", "onGameAppListChanged: " + list.size());
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GameCheck.GameAppInfo gameAppInfo = (GameCheck.GameAppInfo) it.next();
            if (!gameAppInfo.f()) {
                arrayList.add(gameAppInfo.c());
            }
        }
        NeoGameSdkHelp.c().h(context, arrayList);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void addDependence() {
        super.addDependence();
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void create(Context context) {
        super.create(context);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onCreate(final Context context) {
        GaLog.a("NeoGameLibComApplication", "register neo game sdk");
        this.f8330c = context;
        this.f8332i = new Handler(ThreadManager.c().a());
        NeoGameSdkHelp.c().g((Application) context.getApplicationContext(), 1832263, "4e9534f16d5b4d2a803bacba0ea9aef4", "6627dc86954e467d9962253497060188", context.getPackageName());
        SystemMgr.y(context).h(this);
        GameCheck.b(new GameCheck.Callback() { // from class: k.a
            @Override // com.zte.gameassist.common.GameCheck.Callback
            public final void a(List list) {
                NeoGameLibComApplication.g(context, list);
            }
        });
        this.f8333j = new NeoGameLibComServiceImpl();
        this.f8331h.addService(NeoGameLibComService.class.getSimpleName(), this.f8333j);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStart */
    public void y() {
        if (SystemMgr.L()) {
            return;
        }
        ThreadManager.f16580k.postDelayed(this.f8334k, 2000L);
        this.f8332i.post(this.f8335l);
    }

    @Override // com.zte.gameassist.common.GameMonitor.Callback
    /* renamed from: onGameStop */
    public void z() {
        ThreadManager.f16580k.removeCallbacks(this.f8334k);
        this.f8332i.removeCallbacks(this.f8335l);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
