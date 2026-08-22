package com.zte.mifavor.utils.overscroll.adapters;

import android.view.View;
import android.widget.ScrollView;

/* loaded from: classes2.dex */
public class ScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected final ScrollView f17509c;

    public ScrollViewOverScrollDecorAdapter(ScrollView scrollView) {
        this.f17509c = scrollView;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        return !this.f17509c.canScrollVertically(1);
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        return !this.f17509c.canScrollVertically(-1);
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17509c;
    }
}
