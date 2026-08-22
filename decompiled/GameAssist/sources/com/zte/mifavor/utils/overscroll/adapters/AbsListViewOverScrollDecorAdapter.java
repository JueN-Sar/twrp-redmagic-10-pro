package com.zte.mifavor.utils.overscroll.adapters;

import android.view.View;
import android.widget.AbsListView;

/* loaded from: classes2.dex */
public class AbsListViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected final AbsListView f17499c;

    public AbsListViewOverScrollDecorAdapter(AbsListView absListView) {
        this.f17499c = absListView;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        return this.f17499c.getChildCount() > 0 && !d();
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        return this.f17499c.getChildCount() > 0 && !e();
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17499c;
    }

    public boolean d() {
        int childCount = this.f17499c.getChildCount();
        return this.f17499c.getFirstVisiblePosition() + childCount < this.f17499c.getCount() || this.f17499c.getChildAt(childCount - 1).getBottom() > this.f17499c.getHeight() - this.f17499c.getListPaddingBottom();
    }

    public boolean e() {
        return this.f17499c.getFirstVisiblePosition() > 0 || this.f17499c.getChildAt(0).getTop() < this.f17499c.getListPaddingTop();
    }
}
