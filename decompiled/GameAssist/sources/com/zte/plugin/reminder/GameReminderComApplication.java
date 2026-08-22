package com.zte.plugin.reminder;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameReminderComService;

/* loaded from: classes2.dex */
public class GameReminderComApplication implements IComApplication {

    /* renamed from: c, reason: collision with root package name */
    private Context f18022c;

    /* renamed from: h, reason: collision with root package name */
    Router f18023h;

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void addDependence() {
        super.addDependence();
    }

    protected Router b() {
        if (this.f18023h == null) {
            this.f18023h = Router.getInstance();
        }
        return this.f18023h;
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void create(Context context) {
        super.create(context);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        GameReminderWindowManager.G(this.f18022c).L(configuration);
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onCreate(Context context) {
        this.f18022c = context;
        b().addService(GameReminderComService.class.getSimpleName(), new GameReminderComServiceImpl());
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
        b().removeService(GameReminderComService.class.getSimpleName());
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
