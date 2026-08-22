package cn.nubia.gameassist;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.api.performance.ICpuMonitor;
import cn.nubia.componentcenter.api.performance.IGpuMonitor;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameAssistComService;
import cn.nubia.gameassist.performance.monitor.CpuMonitor;
import cn.nubia.gameassist.performance.monitor.GpuMonitor;

/* loaded from: classes.dex */
public class GameAssistComApplication implements IComApplication {

    /* renamed from: c, reason: collision with root package name */
    Router f6088c = Router.getInstance();

    @Override // cn.nubia.componentcenter.IComApplication
    public void a() {
        Router.removeDependence(ICpuMonitor.class);
        Router.removeDependence(IGpuMonitor.class);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void addDependence() {
        Router.addDependence(ICpuMonitor.class, new CpuMonitor());
        Router.addDependence(IGpuMonitor.class, new GpuMonitor());
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
    public void onCreate(Context context) {
        this.f6088c.addService(GameAssistComService.class.getSimpleName(), new GameAssistComServiceImpl(context));
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
        this.f6088c.removeService(GameAssistComService.class.getSimpleName());
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
