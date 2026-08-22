package com.zte.gameassist.lowsugar;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.LowSugarComService;

/* loaded from: classes2.dex */
public class LowSugarComApplication implements IComApplication {

    /* renamed from: c, reason: collision with root package name */
    private Context f16700c;

    /* renamed from: h, reason: collision with root package name */
    Router f16701h;

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void addDependence() {
        super.addDependence();
    }

    protected Router b() {
        if (this.f16701h == null) {
            this.f16701h = Router.getInstance();
        }
        return this.f16701h;
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void create(Context context) {
        super.create(context);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        LowSugarGameplayController.l().x(configuration);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onCreate(Context context) {
        this.f16700c = context;
        b().addService(LowSugarComService.class.getSimpleName(), new LowSugarComServiceImpl());
        LowSugarApplication.c().d(context);
        LowSugarGameplayController.l().o(context);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
        b().removeService(LowSugarComService.class.getSimpleName());
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
