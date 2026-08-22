package com.zte.gameassist.common;

import androidx.annotation.VisibleForTesting;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.IModuleProxy.ICallback;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class AbsSlideProxy<T extends IModuleProxy.ICallback> extends AbsModuleProxy<T> implements ISliderProxy<T> {

    /* renamed from: j, reason: collision with root package name */
    protected int f16456j;

    /* renamed from: k, reason: collision with root package name */
    protected int f16457k;

    /* renamed from: l, reason: collision with root package name */
    private List f16458l;

    public AbsSlideProxy(ModuleProxyContext moduleProxyContext) {
        super(moduleProxyContext);
        this.f16458l = new ArrayList();
    }

    public int getMax() {
        return this.f16456j;
    }

    public int getProgress() {
        return this.f16457k;
    }

    protected abstract void k(int i2, boolean z);

    protected abstract void l();

    protected abstract void m();

    @VisibleForTesting
    public void setProgress(int i2, boolean z) {
        k(i2, z);
    }

    @Override // com.zte.gameassist.common.ISliderProxy
    public synchronized void startTrackingTouch(IModuleProxy.ICallback iCallback) {
        try {
            if (!this.f16458l.contains(iCallback)) {
                this.f16458l.add(iCallback);
            }
            if (this.f16458l.size() == 1) {
                l();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // com.zte.gameassist.common.ISliderProxy
    public synchronized void stopTrackingTouch(IModuleProxy.ICallback iCallback) {
        try {
            if (this.f16458l.contains(iCallback)) {
                this.f16458l.remove(iCallback);
            }
            if (this.f16458l.size() == 0) {
                m();
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
