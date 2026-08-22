package com.zte.performanceindicator;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameReminderComService;
import cn.nubia.componentcenter.service.PerfIndicationComService;

/* loaded from: classes2.dex */
public class PerfIndicatorComApplication implements IComApplication {

    /* renamed from: c, reason: collision with root package name */
    Router f17895c = Router.getInstance();

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
    public void onCreate(Context context) {
        context.getApplicationContext();
        this.f17895c.addService(PerfIndicationComService.class.getSimpleName(), new PerfIndicationComServiceImpl());
        PerfIndicatorManager.t().w(context);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
        this.f17895c.removeService(GameReminderComService.class.getSimpleName());
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
