package com.zte.gameassist.lowsugar;

import android.content.Context;
import cn.nubia.componentcenter.service.LowSugarComService;
import com.zte.gameassist.lowsugar.ai.LowSugarAiMgr;
import com.zte.gameassist.lowsugar.utils.LowSugarUtils;

/* loaded from: classes2.dex */
public class LowSugarComServiceImpl implements LowSugarComService {
    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public void b() {
        LowSugarGameplayController.l().z();
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public boolean c() {
        return LowSugarGameplayController.l().A();
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public boolean d() {
        return LowSugarGameplayController.l().n();
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public void e(int i2) {
        LowSugarAiMgr.F().U(i2);
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public boolean f(String str) {
        return LowSugarGameplayController.l().p(str);
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public void g(String str, Context context) {
        LowSugarUtils.v(str, context);
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public int getSceneIndex() {
        return LowSugarGameplayController.l().m();
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public void h(Context context) {
        LowSugarUtils.b(context);
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public void i(boolean z, LowSugarComService.ICallback iCallback) {
        LowSugarGameplayController.l().y(z, iCallback);
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public boolean isDetecting() {
        return LowSugarGameplayController.l().q();
    }

    @Override // cn.nubia.componentcenter.service.LowSugarComService
    public boolean j(Context context) {
        return LowSugarUtils.p(context);
    }
}
