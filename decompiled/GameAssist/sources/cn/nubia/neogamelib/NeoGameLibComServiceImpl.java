package cn.nubia.neogamelib;

import android.content.Context;
import cn.nubia.componentcenter.service.NeoGameLibComService;
import cn.nubia.nbgame.sdk.NeoGameSdkHelp;

/* loaded from: classes.dex */
public class NeoGameLibComServiceImpl implements NeoGameLibComService {

    /* renamed from: a, reason: collision with root package name */
    private boolean f8336a;

    @Override // cn.nubia.componentcenter.service.NeoGameLibComService
    public boolean a() {
        return this.f8336a;
    }

    @Override // cn.nubia.componentcenter.service.NeoGameLibComService
    public void b(Context context, String str) {
        NeoGameSdkHelp.c().b(context, str);
    }

    public void c(boolean z) {
        this.f8336a = z;
    }
}
