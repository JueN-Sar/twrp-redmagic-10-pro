package com.zte.gameassist.common;

import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.IModuleProxy.ICallback;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* loaded from: classes2.dex */
public abstract class AbsModuleProxy<T extends IModuleProxy.ICallback> implements IModuleProxy<T> {

    /* renamed from: h, reason: collision with root package name */
    protected final ModuleProxyContext f16454h;

    /* renamed from: c, reason: collision with root package name */
    public String f16453c = getClass().getSimpleName();

    /* renamed from: i, reason: collision with root package name */
    private final List f16455i = new ArrayList();

    public AbsModuleProxy(ModuleProxyContext moduleProxyContext) {
        this.f16454h = moduleProxyContext;
    }

    public void e(IModuleProxy.ICallback iCallback) {
        if (iCallback == null) {
            return;
        }
        synchronized (this.f16455i) {
            try {
                if (this.f16455i.isEmpty()) {
                    g();
                    j();
                }
                if (!this.f16455i.contains(iCallback)) {
                    this.f16455i.add(iCallback);
                    iCallback.onChanged(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void f(Consumer consumer) {
        synchronized (this.f16455i) {
            this.f16455i.forEach(consumer);
        }
    }

    protected void g() {
    }

    public void h(IModuleProxy.ICallback iCallback) {
        if (iCallback == null) {
            return;
        }
        synchronized (this.f16455i) {
            try {
                this.f16455i.remove(iCallback);
                if (this.f16455i.isEmpty()) {
                    i();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    protected void i() {
    }

    protected void j() {
        synchronized (this.f16455i) {
            try {
                for (IModuleProxy.ICallback iCallback : this.f16455i) {
                    if (iCallback != null) {
                        iCallback.onChanged(this);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.zte.gameassist.common.IModuleProxy
    public void setListening(boolean z, IModuleProxy.ICallback iCallback) {
        if (iCallback == null) {
            return;
        }
        if (z) {
            e(iCallback);
        } else {
            h(iCallback);
        }
    }
}
