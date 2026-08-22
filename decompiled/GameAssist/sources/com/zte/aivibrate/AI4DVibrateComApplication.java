package com.zte.aivibrate;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.AI4DVibrateComService;

/* loaded from: classes.dex */
public class AI4DVibrateComApplication implements IComApplication {

    /* renamed from: c, reason: collision with root package name */
    Router f16171c = Router.getInstance();

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
        this.f16171c.addService(AI4DVibrateComService.class.getSimpleName(), new AI4DVibrateComServiceImpl(new Vibrate4DController(context)));
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
        this.f16171c.removeService(AI4DVibrateComService.class.getSimpleName());
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
