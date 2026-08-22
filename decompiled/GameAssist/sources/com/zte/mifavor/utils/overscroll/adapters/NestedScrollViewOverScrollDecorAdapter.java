package com.zte.mifavor.utils.overscroll.adapters;

import android.util.Log;
import android.view.View;
import androidx.core.widget.NestedScrollView;

/* loaded from: classes2.dex */
public class NestedScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /* renamed from: c, reason: collision with root package name */
    protected final NestedScrollView f17501c;

    public NestedScrollViewOverScrollDecorAdapter(NestedScrollView nestedScrollView) {
        this.f17501c = nestedScrollView;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean a() {
        boolean z = !this.f17501c.canScrollVertically(1);
        Log.d("Z#QScroll-NSVAdapter", "isInAbsoluteEnd isCanDrag = " + z);
        return z;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public boolean b() {
        boolean z = !this.f17501c.canScrollVertically(-1);
        Log.d("Z#QScroll-NSVAdapter", "isInAbsoluteStart isCanDrag = " + z);
        return z;
    }

    @Override // com.zte.mifavor.utils.overscroll.adapters.IOverScrollDecoratorAdapter
    public View c() {
        return this.f17501c;
    }
}
