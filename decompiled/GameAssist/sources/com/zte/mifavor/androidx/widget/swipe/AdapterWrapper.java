package com.zte.mifavor.androidx.widget.swipe;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Field;
import java.util.List;

/* loaded from: classes2.dex */
public class AdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: c, reason: collision with root package name */
    private RecyclerView.Adapter f17200c;

    /* renamed from: d, reason: collision with root package name */
    private LayoutInflater f17201d;

    /* renamed from: e, reason: collision with root package name */
    private int f17202e;

    /* renamed from: f, reason: collision with root package name */
    private SwipeMenuCreator f17203f;

    /* renamed from: g, reason: collision with root package name */
    private OnItemMenuClickListener f17204g;

    /* renamed from: h, reason: collision with root package name */
    private OnItemClickListener f17205h;

    /* renamed from: i, reason: collision with root package name */
    private OnItemLongClickListener f17206i;

    /* renamed from: j, reason: collision with root package name */
    private boolean f17207j = true;

    public AdapterWrapper(Context context, RecyclerView.Adapter adapter) {
        this.f17201d = LayoutInflater.from(context);
        this.f17200c = adapter;
        this.f17202e = context.getResources().getColor(R.color.transparent);
    }

    private int N() {
        RecyclerView.Adapter adapter = this.f17200c;
        if (adapter != null) {
            return adapter.m();
        }
        return 0;
    }

    private Class P(Class cls) {
        Class superclass = cls.getSuperclass();
        return (superclass == null || superclass.equals(Object.class)) ? cls : P(superclass);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void A(RecyclerView.ViewHolder viewHolder, int i2) {
        View view = viewHolder.f5252a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void B(RecyclerView.ViewHolder viewHolder, int i2, List list) {
        Drawable background;
        View view = viewHolder.f5252a;
        if ((view instanceof SwipeMenuLayout) && this.f17203f != null) {
            SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) view;
            SwipeMenu swipeMenu = new SwipeMenu(swipeMenuLayout);
            this.f17203f.a(swipeMenu, i2);
            SwipeMenuView swipeMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(com.zte.extres.R.id.swipe_menu);
            if (swipeMenu.c() && this.f17204g != null) {
                swipeMenuView.setOrientation(swipeMenu.b());
                swipeMenuView.b(viewHolder, swipeMenu, this.f17204g);
                int childCount = swipeMenuView.getChildCount();
                boolean isCardDelete = swipeMenuView.getIsCardDelete();
                if (1 == childCount && isCardDelete && (background = swipeMenuView.getBackground()) != null) {
                    swipeMenuLayout.setBackground(background);
                }
            } else if (swipeMenuView.getChildCount() > 0) {
                swipeMenuView.removeAllViews();
            }
            if (swipeMenuLayout.p()) {
                swipeMenuLayout.l();
            }
        }
        this.f17200c.B(viewHolder, i2, list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder C(ViewGroup viewGroup, int i2) {
        ViewGroup viewGroup2;
        final RecyclerView.ViewHolder C = this.f17200c.C(viewGroup, i2);
        C.f5252a.setOnClickListener(new View.OnClickListener() { // from class: com.zte.mifavor.androidx.widget.swipe.AdapterWrapper.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AdapterWrapper.this.f17205h != null) {
                    AdapterWrapper.this.f17205h.a(view, C.k());
                }
            }
        });
        C.f5252a.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.zte.mifavor.androidx.widget.swipe.AdapterWrapper.2
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                if (AdapterWrapper.this.f17206i == null) {
                    return true;
                }
                AdapterWrapper.this.f17206i.a(view, C.k());
                return true;
            }
        });
        if (this.f17203f == null) {
            return C;
        }
        try {
            View inflate = this.f17201d.inflate(this.f17207j ? com.zte.extres.R.layout.x_recycler_view_item : com.zte.extres.R.layout.x_recycler_view_item_not_card, viewGroup, false);
            ViewGroup viewGroup3 = (ViewGroup) inflate.findViewById(com.zte.extres.R.id.swipe_content);
            View view = C.f5252a;
            if (view != null && (viewGroup2 = (ViewGroup) view.getParent()) != null) {
                viewGroup2.removeView(C.f5252a);
            }
            viewGroup3.addView(C.f5252a);
            viewGroup3.setBackgroundColor(this.f17202e);
            Field declaredField = P(C.getClass()).getDeclaredField("itemView");
            if (!declaredField.isAccessible()) {
                declaredField.setAccessible(true);
            }
            declaredField.set(C, inflate);
        } catch (Exception e2) {
            Log.e("Z#Swipe-AdapterWper", "onCreateViewHolder error e = ", e2);
        }
        return C;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void D(RecyclerView recyclerView) {
        this.f17200c.D(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean E(RecyclerView.ViewHolder viewHolder) {
        return this.f17200c.E(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void F(RecyclerView.ViewHolder viewHolder) {
        this.f17200c.F(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void G(RecyclerView.ViewHolder viewHolder) {
        this.f17200c.G(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void H(RecyclerView.ViewHolder viewHolder) {
        this.f17200c.H(viewHolder);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void I(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.I(adapterDataObserver);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void K(RecyclerView.AdapterDataObserver adapterDataObserver) {
        super.K(adapterDataObserver);
    }

    public RecyclerView.Adapter O() {
        return this.f17200c;
    }

    public void Q(OnItemClickListener onItemClickListener) {
        this.f17205h = onItemClickListener;
    }

    public void R(OnItemLongClickListener onItemLongClickListener) {
        this.f17206i = onItemLongClickListener;
    }

    public void S(OnItemMenuClickListener onItemMenuClickListener) {
        this.f17204g = onItemMenuClickListener;
    }

    public void T(boolean z) {
        this.f17207j = z;
    }

    public void U(SwipeMenuCreator swipeMenuCreator) {
        this.f17203f = swipeMenuCreator;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return N();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long n(int i2) {
        return this.f17200c.n(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        RecyclerView.Adapter adapter = this.f17200c;
        if (adapter != null) {
            return adapter.o(i2);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void z(RecyclerView recyclerView) {
        com.zte.mifavor.androidx.widget.RecyclerView recyclerView2 = (com.zte.mifavor.androidx.widget.RecyclerView) recyclerView;
        recyclerView2.setItemViewSwipeEnabled(false);
        this.f17200c.z(recyclerView2);
        RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup l3 = gridLayoutManager.l3();
            gridLayoutManager.q3(new GridLayoutManager.SpanSizeLookup(this) { // from class: com.zte.mifavor.androidx.widget.swipe.AdapterWrapper.3
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int f(int i2) {
                    GridLayoutManager.SpanSizeLookup spanSizeLookup = l3;
                    if (spanSizeLookup != null) {
                        return spanSizeLookup.f(i2);
                    }
                    return 1;
                }
            });
        }
    }
}
