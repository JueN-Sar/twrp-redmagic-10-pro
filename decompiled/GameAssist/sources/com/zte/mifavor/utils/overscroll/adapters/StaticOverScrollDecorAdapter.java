package com.zte.mifavor.utils.overscroll.adapters;

import android.view.View;

/* loaded from: classes2.dex */
public class StaticOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected final View f17510c;

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        return true;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        return true;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17510c;
    }
}
