package cn.nubia.gameassist.pips.custom;

import android.content.Context;
import android.os.SystemClock;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.pips.PipFactory;
import cn.nubia.gameassist.pips.PipInfo;
import cn.nubia.gameassist.pips.PipStateListener;
import cn.nubia.gameassist.pips.panel.PipViewController;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.util.List;

/* loaded from: classes.dex */
public class PipOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements PipStateListener, ItemTouchCallback {

    /* renamed from: c, reason: collision with root package name */
    private final RecyclerView f7163c;

    /* renamed from: d, reason: collision with root package name */
    private final Context f7164d;

    /* renamed from: e, reason: collision with root package name */
    private final ItemTouchHelper f7165e;

    /* renamed from: f, reason: collision with root package name */
    private final List f7166f;

    /* renamed from: g, reason: collision with root package name */
    private PipViewController f7167g;

    /* renamed from: h, reason: collision with root package name */
    private TileHolder f7168h;

    /* renamed from: i, reason: collision with root package name */
    private int f7169i = -1;

    /* renamed from: j, reason: collision with root package name */
    private final int f7170j;

    /* renamed from: k, reason: collision with root package name */
    private long f7171k;

    /* renamed from: l, reason: collision with root package name */
    private final ItemTouchHelper.Callback f7172l;

    public class OnRecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

        /* renamed from: a, reason: collision with root package name */
        private final GestureDetectorCompat f7174a;

        private class OnItemGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                View a2;
                if (PipOrderAdapter.this.f7168h != null || SystemClock.elapsedRealtime() - PipOrderAdapter.this.f7171k <= 500 || (a2 = OnRecyclerItemTouchListener.this.a(motionEvent.getX(), motionEvent.getY())) == null) {
                    return;
                }
                OnRecyclerItemTouchListener.this.b(PipOrderAdapter.this.f7163c.h0(a2));
            }

            private OnItemGestureListener() {
            }
        }

        public OnRecyclerItemTouchListener() {
            this.f7174a = new GestureDetectorCompat(PipOrderAdapter.this.f7163c.getContext(), new OnItemGestureListener());
        }

        public View a(float f2, float f3) {
            int childCount = PipOrderAdapter.this.f7163c.getChildCount();
            for (int i2 = 1; i2 < childCount; i2++) {
                View childAt = PipOrderAdapter.this.f7163c.getChildAt(i2);
                float translationX = childAt.getTranslationX();
                float translationY = childAt.getTranslationY();
                if (f2 >= childAt.getLeft() + translationX && f2 <= childAt.getRight() + translationX && f3 >= childAt.getTop() + translationY && f3 <= childAt.getBottom() + translationY) {
                    return childAt;
                }
            }
            return null;
        }

        public void b(RecyclerView.ViewHolder viewHolder) {
            PipOrderAdapter.this.f7165e.v(viewHolder);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.f7174a.a(motionEvent);
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.f7174a.a(motionEvent);
        }
    }

    private static class StableHolder extends RecyclerView.ViewHolder {
        public StableHolder(View view) {
            super(view);
        }
    }

    private static class TileHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        private final ImageView f7177s;
        private final TextView t;

        public TileHolder(View view) {
            super(view);
            this.f7177s = (ImageView) view.findViewById(R.id.icon);
            this.t = (TextView) view.findViewById(R.id.label);
        }

        public void P() {
            this.f5252a.clearAnimation();
            this.t.clearAnimation();
            this.t.setAlpha(1.0f);
            this.t.clearAnimation();
        }

        public void Q() {
            this.f5252a.animate().setDuration(100L).scaleX(1.2f).scaleY(1.2f);
            this.t.animate().setDuration(100L).alpha(0.0f);
            this.t.animate().setDuration(100L).alpha(0.0f);
        }

        public void R() {
            this.f5252a.animate().setDuration(100L).scaleX(1.0f).scaleY(1.0f);
            this.t.animate().setDuration(100L).alpha(1.0f);
            this.t.animate().setDuration(100L).alpha(1.0f);
        }
    }

    public PipOrderAdapter(Context context, RecyclerView recyclerView, List list) {
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() { // from class: cn.nubia.gameassist.pips.custom.PipOrderAdapter.1
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void A(RecyclerView.ViewHolder viewHolder, int i2) {
                super.A(viewHolder, i2);
                GaLog.a("PipTileAdapter", "on selected changed:" + viewHolder);
                if (viewHolder instanceof TileHolder) {
                    TileHolder tileHolder = (TileHolder) viewHolder;
                    if (i2 != 2) {
                        viewHolder = null;
                    }
                    if (viewHolder == PipOrderAdapter.this.f7168h || viewHolder == null) {
                        return;
                    }
                    PipOrderAdapter.this.f7168h = tileHolder;
                    PipOrderAdapter.this.f7168h.Q();
                    PipOrderAdapter pipOrderAdapter = PipOrderAdapter.this;
                    pipOrderAdapter.f7169i = pipOrderAdapter.f7168h.k();
                    PipOrderAdapter pipOrderAdapter2 = PipOrderAdapter.this;
                    pipOrderAdapter2.j(pipOrderAdapter2.f7169i, PipOrderAdapter.this.f7168h.f5252a);
                }
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void B(RecyclerView.ViewHolder viewHolder, int i2) {
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean a(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                int left = viewHolder2.f5252a.getLeft();
                int k2 = viewHolder2.k();
                return (left >= PipOrderAdapter.this.f7170j || k2 <= 3) && k2 != 0;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void c(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder) {
                super.c(recyclerView2, viewHolder);
                if (viewHolder instanceof TileHolder) {
                    if (PipOrderAdapter.this.f7168h != null) {
                        PipOrderAdapter.this.f7168h.R();
                        PipOrderAdapter.this.f7168h = null;
                        PipOrderAdapter.this.f7171k = SystemClock.elapsedRealtime();
                    }
                    PipOrderAdapter.this.f7169i = -1;
                }
                for (int i2 = 0; i2 < recyclerView2.getChildCount(); i2++) {
                    PipOrderAdapter.this.j(i2, recyclerView2.getChildAt(i2));
                }
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int k(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.m() == 0) {
                    return (viewHolder.f5252a.getLeft() >= PipOrderAdapter.this.f7170j || viewHolder.k() <= 3) ? ItemTouchHelper.Callback.t(15, 0) : ItemTouchHelper.Callback.t(0, 0);
                }
                return ItemTouchHelper.Callback.t(0, 0);
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean q() {
                return false;
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean y(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                int k2 = viewHolder.k();
                int k3 = viewHolder2.k();
                GaLog.a("PipTileAdapter", "onMove from = " + k2 + ", to = " + k3);
                if (k2 == 0 || k3 == 0) {
                    return false;
                }
                PipOrderAdapter.this.f7169i = k3;
                return PipOrderAdapter.this.X(k2, k3, viewHolder2.f5252a);
            }
        };
        this.f7172l = callback;
        this.f7164d = context;
        this.f7163c = recyclerView;
        this.f7165e = new ItemTouchHelper(callback);
        recyclerView.k(new OnRecyclerItemTouchListener());
        this.f7166f = list;
        this.f7170j = context.getResources().getDimensionPixelSize(R.dimen.game_pip_order_stable_zone_width);
    }

    private void W(int i2, int i3) {
        List list = this.f7166f;
        list.add(i3, (PipInfo) list.remove(i2));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void A(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder.m() == 0) {
            PipInfo pipInfo = (PipInfo) this.f7166f.get(i2 - 1);
            if (pipInfo == null || pipInfo.a()) {
                return;
            }
            TileHolder tileHolder = (TileHolder) viewHolder;
            PipFactory.LazyDrawable h2 = PipFactory.LazyDrawable.h(pipInfo.f7155b);
            if (h2 != null) {
                h2.l(tileHolder.f7177s);
            }
            tileHolder.t.setText(pipInfo.f7154a);
            if (pipInfo.f7155b.equals(this.f7167g.p0())) {
                tileHolder.f5252a.setAlpha(0.26f);
                tileHolder.f5252a.setClickable(false);
            } else {
                tileHolder.f5252a.setAlpha(1.0f);
                tileHolder.f5252a.setClickable(true);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder C(ViewGroup viewGroup, int i2) {
        return i2 == 1 ? new StableHolder(InflaterHelper.g(R.layout.pip_custom_stable, viewGroup, false)) : new TileHolder(InflaterHelper.g(R.layout.pip_customize_tile_frame, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public boolean E(RecyclerView.ViewHolder viewHolder) {
        if (!(viewHolder instanceof TileHolder)) {
            return true;
        }
        ((TileHolder) viewHolder).P();
        return true;
    }

    public List U() {
        return this.f7166f;
    }

    public ItemTouchHelper V() {
        return this.f7165e;
    }

    public boolean X(int i2, int i3, View view) {
        if (i3 == i2) {
            return true;
        }
        W(i2 - 1, i3 - 1);
        u(i2, i3);
        return true;
    }

    public void Y(PipViewController pipViewController) {
        this.f7167g = pipViewController;
    }

    @Override // cn.nubia.gameassist.pips.PipStateListener
    public void d(List list) {
        GaLog.a("PipTileAdapter", "on pip changed");
        this.f7166f.clear();
        this.f7166f.addAll(list);
        r();
    }

    @Override // cn.nubia.gameassist.pips.custom.ItemTouchCallback
    public int h() {
        return this.f7169i;
    }

    @Override // cn.nubia.gameassist.pips.custom.ItemTouchCallback
    public void j(int i2, View view) {
        if (i2 == h()) {
            view.setElevation(30.0f);
            return;
        }
        if (i2 == 0) {
            view.setElevation(10.0f);
        } else if (i2 <= 3) {
            view.setElevation(20.0f);
        } else {
            view.setElevation(1.0f);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f7166f.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        return i2 == 0 ? 1 : 0;
    }
}
