package cn.nubia.gameassist.dessert.custom;

import android.content.Context;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.TileInfo;
import cn.nubia.gameassist.dessert.TileStateListener;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.util.List;

/* loaded from: classes.dex */
public class TileAdapter extends RecyclerView.Adapter<Holder> implements TileStateListener {

    /* renamed from: c, reason: collision with root package name */
    private final Context f6243c;

    /* renamed from: d, reason: collision with root package name */
    private float f6244d;

    /* renamed from: e, reason: collision with root package name */
    private final ItemTouchHelper f6245e;

    /* renamed from: f, reason: collision with root package name */
    private int f6246f;

    /* renamed from: g, reason: collision with root package name */
    private long f6247g;

    /* renamed from: h, reason: collision with root package name */
    private RecyclerView f6248h;

    /* renamed from: i, reason: collision with root package name */
    private Holder f6249i;

    /* renamed from: j, reason: collision with root package name */
    private OnRecyclerItemTouchListener f6250j;

    /* renamed from: k, reason: collision with root package name */
    private List f6251k;

    /* renamed from: l, reason: collision with root package name */
    private final GridLayoutManager.SpanSizeLookup f6252l = new GridLayoutManager.SpanSizeLookup(this) { // from class: cn.nubia.gameassist.dessert.custom.TileAdapter.1
        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int f(int i2) {
            return 1;
        }
    };

    /* renamed from: m, reason: collision with root package name */
    private final ItemTouchHelper.Callback f6253m;

    public class Holder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        private CustomizeTileView f6255s;

        public Holder(TileAdapter tileAdapter, View view) {
            super(view);
            if (view instanceof FrameLayout) {
                CustomizeTileView customizeTileView = (CustomizeTileView) view;
                this.f6255s = customizeTileView;
                customizeTileView.setBackground(null);
            }
        }

        public void O() {
            GaLog.a("TileAdapter", "clearDrag() tileView :" + this.f6255s);
            this.f5252a.clearAnimation();
            this.f6255s.getAppLabel().clearAnimation();
            this.f6255s.getAppLabel().setAlpha(1.0f);
            this.f6255s.getAppLabel().clearAnimation();
        }

        public void P() {
            GaLog.a("TileAdapter", "startDrag() tileView :" + this.f6255s);
            this.f5252a.animate().setDuration(100L).scaleX(1.2f).scaleY(1.2f);
            this.f6255s.getAppLabel().animate().setDuration(100L).alpha(0.0f);
            this.f6255s.getAppLabel().animate().setDuration(100L).alpha(0.0f);
        }

        public void Q() {
            GaLog.a("TileAdapter", "stopDrag() tileView :" + this.f6255s);
            this.f5252a.animate().setDuration(100L).scaleX(1.0f).scaleY(1.0f);
            this.f6255s.getAppLabel().animate().setDuration(100L).alpha(1.0f);
            this.f6255s.getAppLabel().animate().setDuration(100L).alpha(1.0f);
        }
    }

    public class OnRecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

        /* renamed from: a, reason: collision with root package name */
        private GestureDetectorCompat f6256a;

        private class OnItemGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                View S = TileAdapter.this.f6248h.S(motionEvent.getX(), motionEvent.getY());
                if (S != null) {
                    OnRecyclerItemTouchListener.this.a(TileAdapter.this.f6248h.h0(S));
                }
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return super.onSingleTapUp(motionEvent);
            }

            private OnItemGestureListener() {
            }
        }

        public OnRecyclerItemTouchListener() {
            this.f6256a = new GestureDetectorCompat(TileAdapter.this.f6248h.getContext(), new OnItemGestureListener());
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            GaLog.a("TileAdapter", "onItemLongClick() :" + viewHolder.n() + " " + TileAdapter.this.f6246f);
            if (viewHolder.n() >= TileAdapter.this.f6246f || TileAdapter.this.f6249i != null || SystemClock.elapsedRealtime() - TileAdapter.this.f6247g <= 500) {
                return;
            }
            TileAdapter.this.f6245e.v(viewHolder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.f6256a.a(motionEvent);
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.f6256a.a(motionEvent);
        }
    }

    public TileAdapter(Context context, RecyclerView recyclerView, List list) {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() { // from class: cn.nubia.gameassist.dessert.custom.TileAdapter.2
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void A(RecyclerView.ViewHolder viewHolder, int i2) {
                super.A(viewHolder, i2);
                GaLog.a("TileAdapter", "onSelectedChanged:" + viewHolder);
                if (i2 != 2) {
                    viewHolder = null;
                }
                if (viewHolder == TileAdapter.this.f6249i) {
                    return;
                }
                if (viewHolder != null) {
                    TileAdapter.this.f6249i = (Holder) viewHolder;
                    TileAdapter.this.f6249i.P();
                }
                TileAdapter tileAdapter = TileAdapter.this;
                tileAdapter.s(tileAdapter.f6246f);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void B(RecyclerView.ViewHolder viewHolder, int i2) {
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean a(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                return viewHolder2.k() >= 0 && viewHolder2.k() < TileAdapter.this.f6246f;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void c(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder) {
                super.c(recyclerView2, viewHolder);
                if (TileAdapter.this.f6249i != null) {
                    TileAdapter.this.f6249i.Q();
                    TileAdapter.this.f6249i = null;
                    TileAdapter.this.f6247g = SystemClock.elapsedRealtime();
                }
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int k(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder) {
                return (viewHolder.m() == 1 || viewHolder.m() == 4) ? ItemTouchHelper.Callback.t(0, 0) : ItemTouchHelper.Callback.t(15, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean q() {
                return false;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean r() {
                return false;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean y(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                int k2 = viewHolder.k();
                int k3 = viewHolder2.k();
                GaLog.a("TileAdapter", "onMove() from = " + k2 + ", to = " + k3);
                return TileAdapter.this.U(k2, k3, viewHolder2.f5252a);
            }
        };
        this.f6253m = callback;
        this.f6243c = context;
        this.f6248h = recyclerView;
        this.f6250j = new OnRecyclerItemTouchListener();
        this.f6245e = new ItemTouchHelper(callback);
        this.f6248h.k(this.f6250j);
        this.f6244d = context.getResources().getDisplayMetrics().density;
        this.f6251k = list;
        Y();
    }

    private void T(int i2, int i3) {
        List list = this.f6251k;
        list.add(i3, (TileInfo) list.remove(i2));
    }

    private void Y() {
        this.f6246f = -1;
        for (int i2 = 0; i2 < this.f6251k.size(); i2++) {
            if (this.f6251k.get(i2) == null) {
                this.f6246f = i2;
            }
        }
    }

    public ItemTouchHelper S() {
        return this.f6245e;
    }

    public boolean U(int i2, int i3, View view) {
        if (i3 == i2) {
            return true;
        }
        T(i2, i3);
        u(i2, i3);
        Y();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public void A(Holder holder, int i2) {
        TileInfo tileInfo;
        if (holder.m() != 0 || (tileInfo = (TileInfo) this.f6251k.get(i2)) == null || tileInfo.f6227a == null) {
            return;
        }
        holder.f6255s.setAppLabel(tileInfo.f6228b);
        if (i2 < this.f6246f) {
            if (tileInfo.f6227a.startsWith("custom(")) {
                holder.f6255s.setIcon(R.drawable.userdefine_custom_in);
                holder.f6255s.setOverLay(tileInfo.f6229c.f6146b);
            } else {
                holder.f6255s.setIcon(tileInfo.f6229c.f6146b);
            }
        } else if (tileInfo.f6227a.startsWith("custom(")) {
            holder.f6255s.setIcon(R.drawable.userdefine_custom_in);
            holder.f6255s.setOverLay(tileInfo.f6229c.f6146b);
        } else {
            holder.f6255s.setIcon(tileInfo.f6229c.f6146b);
        }
        tileInfo.f6233g = CustomizeLayoutManager.W1(i2);
        tileInfo.f6232f = (CustomizeLayoutManager.W1(i2) * 2) + CustomizeLayoutManager.V1(i2);
        tileInfo.f6231e = CustomizeLayoutManager.X1(i2);
        GaLog.e("TileAdapter", "onBindViewHolder: " + i2 + ", info : " + tileInfo);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public Holder C(ViewGroup viewGroup, int i2) {
        return i2 == 4 ? new Holder(this, InflaterHelper.g(R.layout.qs_customize_divider, viewGroup, false)) : new Holder(this, new CustomizeTileView(viewGroup.getContext(), this.f6244d));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: X, reason: merged with bridge method [inline-methods] */
    public boolean E(Holder holder) {
        holder.O();
        return true;
    }

    @Override // cn.nubia.gameassist.dessert.TileStateListener
    public void g(List list) {
        GaLog.a("TileAdapter", "onTilesChanged() mTiles.size() : " + this.f6251k.size() + ", tiles.size() : " + list.size());
        this.f6251k.clear();
        this.f6251k.addAll(list);
        Utils.V(this.f6251k);
        GaLog.a("TileAdapter", "onTilesChanged mTiles.size() : " + this.f6251k.size());
        Y();
        r();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f6251k.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        return (this.f6251k.get(i2) != null || i2 <= 0) ? 0 : 1;
    }
}
