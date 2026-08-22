package cn.nubia.gameassist;

import android.content.Context;
import cn.nubia.componentcenter.api.dessert.IAppBrightnessProxy;
import cn.nubia.componentcenter.api.dessert.IChargeSeparationProxy;
import cn.nubia.componentcenter.api.dessert.ICompetitionLightProxy;
import cn.nubia.componentcenter.api.dessert.IMisOperateProxy;
import cn.nubia.componentcenter.api.dessert.IPerformanceMonitorProxy;
import cn.nubia.componentcenter.api.dessert.IWifiProxy;
import cn.nubia.componentcenter.api.meditation.IMeditationModeController;
import cn.nubia.componentcenter.api.performance.IPerformanceModeController;
import cn.nubia.componentcenter.api.power.IPowerStateMonitor;
import cn.nubia.componentcenter.api.volume.IVolumeController;
import cn.nubia.componentcenter.service.GameAssistComService;
import cn.nubia.gameassist.bright.AppBrightnessProxy;
import cn.nubia.gameassist.dessert.policy.ChargeSeparationProxy;
import cn.nubia.gameassist.dessert.policy.CompetitionLightTileProxy;
import cn.nubia.gameassist.dessert.policy.MisOperateTileProxy;
import cn.nubia.gameassist.dessert.policy.PerformanceMonitorTileProxy;
import cn.nubia.gameassist.dessert.policy.WifiTileProxy;
import cn.nubia.gameassist.meditationmode.MeditationController;
import cn.nubia.gameassist.performance.PerformanceModeController;
import cn.nubia.gameassist.power.PowerStateMonitor;
import cn.nubia.gameassist.volume.VolumeController;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.ModuleProxyContext;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class GameAssistComServiceImpl implements GameAssistComService {

    /* renamed from: a, reason: collision with root package name */
    private final Map f6089a = new HashMap();

    public GameAssistComServiceImpl(Context context) {
        b(context);
    }

    private void b(Context context) {
        ModuleProxyContext moduleProxyContext = new ModuleProxyContext(context);
        this.f6089a.put(IWifiProxy.class, new WifiTileProxy(moduleProxyContext));
        this.f6089a.put(IMisOperateProxy.class, new MisOperateTileProxy(moduleProxyContext));
        this.f6089a.put(ICompetitionLightProxy.class, new CompetitionLightTileProxy(moduleProxyContext));
        this.f6089a.put(IAppBrightnessProxy.class, new AppBrightnessProxy(moduleProxyContext));
        this.f6089a.put(IVolumeController.class, new VolumeController(moduleProxyContext));
        this.f6089a.put(IPerformanceModeController.class, PerformanceModeController.S());
        this.f6089a.put(IMeditationModeController.class, MeditationController.s());
        this.f6089a.put(IChargeSeparationProxy.class, new ChargeSeparationProxy(moduleProxyContext));
        this.f6089a.put(IPerformanceMonitorProxy.class, new PerformanceMonitorTileProxy(moduleProxyContext));
        this.f6089a.put(IPowerStateMonitor.class, new PowerStateMonitor(moduleProxyContext));
    }

    @Override // cn.nubia.componentcenter.service.GameAssistComService
    public IModuleProxy a(Class cls) {
        return (IModuleProxy) this.f6089a.getOrDefault(cls, null);
    }
}
