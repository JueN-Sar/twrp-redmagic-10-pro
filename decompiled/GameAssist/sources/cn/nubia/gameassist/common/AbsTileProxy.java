package cn.nubia.gameassist.common;

import com.zte.gameassist.common.AbsModuleProxy;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.IModuleProxy.ICallback;
import com.zte.gameassist.common.ITileProxy;
import com.zte.gameassist.common.ModuleProxyContext;

/* loaded from: classes.dex */
public abstract class AbsTileProxy<T extends IModuleProxy.ICallback> extends AbsModuleProxy<T> implements ITileProxy<T> {

    /* renamed from: j, reason: collision with root package name */
    protected boolean f6110j;

    /* renamed from: k, reason: collision with root package name */
    protected boolean f6111k;

    public AbsTileProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f6110j = true;
        this.f6111k = false;
    }

    @Override // com.zte.gameassist.common.ITileProxy
    public boolean b() {
        return this.f6110j;
    }

    @Override // com.zte.gameassist.common.ITileProxy
    public boolean c() {
        return this.f6111k;
    }

    @Override // com.zte.gameassist.common.ITileProxy
    public boolean d() {
        this.f6111k = !this.f6111k;
        return false;
    }

    @Override // com.zte.gameassist.common.AbsModuleProxy
    protected void j() {
        if (!this.f16454h.b().getLooper().isCurrentThread()) {
            this.f16454h.b().post(new Runnable() { // from class: cn.nubia.gameassist.common.a
                @Override // java.lang.Runnable
                public final void run() {
                    AbsTileProxy.this.j();
                }
            });
        } else {
            k();
            super.j();
        }
    }

    protected void k() {
    }
}
