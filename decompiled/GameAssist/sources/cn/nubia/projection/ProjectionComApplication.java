package cn.nubia.projection;

import android.content.Context;
import android.content.res.Configuration;
import androidx.annotation.NonNull;
import cn.nubia.componentcenter.IComApplication;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.ProjectionComService;
import com.zte.gameassist.config.ZteFeature;
import com.zte.shared.wrapper.ZteFeatureWrapper;

/* loaded from: classes.dex */
public class ProjectionComApplication implements IComApplication {

    /* renamed from: c, reason: collision with root package name */
    private boolean f8802c;

    /* renamed from: h, reason: collision with root package name */
    Router f8803h = Router.getInstance();

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
        boolean z = ZteFeatureWrapper.getBoolean(ZteFeature.ZTE_FEATURE_EXPAND_PROJECTION_SCREEN, false);
        this.f8802c = z;
        if (z) {
            this.f8803h.addService(ProjectionComService.class.getSimpleName(), new ProjectionComServiceImpl());
            ProjectionManager.o().w(context);
        }
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public void onStop() {
        if (this.f8802c) {
            this.f8803h.removeService(ProjectionComService.class.getSimpleName());
        }
    }

    @Override // cn.nubia.componentcenter.IComApplication
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
