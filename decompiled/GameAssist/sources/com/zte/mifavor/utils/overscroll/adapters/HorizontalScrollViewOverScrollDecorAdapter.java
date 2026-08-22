package com.zte.mifavor.utils.overscroll.adapters;

import android.view.View;
import android.widget.HorizontalScrollView;

/* loaded from: classes2.dex */
public class HorizontalScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected final HorizontalScrollView f17500c;

    public HorizontalScrollViewOverScrollDecorAdapter(HorizontalScrollView horizontalScrollView) {
        this.f17500c = horizontalScrollView;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        return !this.f17500c.canScrollHorizontally(1);
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        return !this.f17500c.canScrollHorizontally(-1);
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17500c;
    }
}
