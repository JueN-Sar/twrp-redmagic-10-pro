package cn.nubia.gameassist.plugin.sort;

import android.content.Context;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.view.MarqueeText;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PluginCustomeAdapter extends RecyclerView.Adapter<Holder> {

    /* renamed from: c, reason: collision with root package name */
    private final Context f7307c;

    /* renamed from: d, reason: collision with root package name */
    private final ItemTouchHelper f7308d;

    /* renamed from: e, reason: collision with root package name */
    private RecyclerView f7309e;

    /* renamed from: f, reason: collision with root package name */
    private Holder f7310f;

    /* renamed from: g, reason: collision with root package name */
    private OnRecyclerItemTouchListener f7311g;

    /* renamed from: h, reason: collision with root package name */
    private ArrayList f7312h;

    /* renamed from: i, reason: collision with root package name */
    private boolean f7313i;

    /* renamed from: j, reason: collision with root package name */
    private final GridLayoutManager.SpanSizeLookup f7314j = new GridLayoutManager.SpanSizeLookup(this) { // from class: cn.nubia.gameassist.plugin.sort.PluginCustomeAdapter.1
        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int f(int i2) {
            return 1;
        }
    };

    /* renamed from: k, reason: collision with root package name */
    private final ItemTouchHelper.Callback f7315k;

    public class Holder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        private ImageView f7317s;
        private ImageView t;
        private MarqueeText u;
        private TextView v;

        public Holder(View view) {
            super(view);
            this.f7317s = (ImageView) view.findViewById(R.id.icon);
            this.t = (ImageView) view.findViewById(R.id.move);
            this.u = (MarqueeText) view.findViewById(R.id.title);
            this.v = (TextView) view.findViewById(R.id.introduction);
        }

        public void Q() {
            GaLog.e("PluginCustomeAdapter", "clearDrag() tileView");
            this.f5252a.clearAnimation();
        }

        protected boolean R(MotionEvent motionEvent) {
            ImageView imageView = this.t;
            if (imageView == null) {
                return false;
            }
            int[] iArr = new int[2];
            imageView.getLocationOnScreen(iArr);
            int i2 = iArr[0];
            return new Rect(i2, iArr[1], this.t.getWidth() + i2, iArr[1] + this.t.getHeight()).contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        }

        public void S() {
            GaLog.e("PluginCustomeAdapter", "startDrag() tileView");
            PluginCustomeAdapter.this.f7313i = true;
            this.f5252a.animate().setDuration(100L).scaleX(1.08f).scaleY(1.08f);
        }

        public void T() {
            GaLog.e("PluginCustomeAdapter", "stopDrag() tileView");
            PluginCustomeAdapter.this.f7313i = false;
            this.f5252a.animate().setDuration(100L).scaleX(1.0f).scaleY(1.0f);
        }
    }

    public class OnRecyclerItemTouchListener implements RecyclerView.OnItemTouchListener {

        /* renamed from: a, reason: collision with root package name */
        private GestureDetectorCompat f7318a;

        private class OnItemGestureListener extends GestureDetector.SimpleOnGestureListener {
            public void a(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder == null) {
                    return;
                }
                GaLog.a("PluginCustomeAdapter", "onItemClick() :" + viewHolder.n() + " startDrag " + PluginCustomeAdapter.this.f7313i);
                if (viewHolder.n() >= PluginCustomeAdapter.this.f7312h.size() || PluginCustomeAdapter.this.f7313i) {
                    return;
                }
                PluginCustomeAdapter.this.f7308d.v(viewHolder);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
                GaLog.e("PluginCustomeAdapter", "onShowPress()");
                if (PluginCustomeAdapter.this.f7309e.getScrollState() != 0) {
                    GaLog.e("PluginCustomeAdapter", "skip startDrag while RecyclerView is scrolling");
                    return;
                }
                View S = PluginCustomeAdapter.this.f7309e.S(motionEvent.getX(), motionEvent.getY());
                if (S == null) {
                    return;
                }
                RecyclerView.ViewHolder h0 = PluginCustomeAdapter.this.f7309e.h0(S);
                if (h0 instanceof Holder) {
                    Holder holder = (Holder) h0;
                    if (holder.R(motionEvent)) {
                        a(holder);
                    }
                }
            }

            private OnItemGestureListener() {
            }
        }

        public OnRecyclerItemTouchListener() {
            this.f7318a = new GestureDetectorCompat(PluginCustomeAdapter.this.f7309e.getContext(), new OnItemGestureListener());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.f7318a.a(motionEvent);
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onRequestDisallowInterceptTouchEvent(boolean z) {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
        public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            this.f7318a.a(motionEvent);
        }
    }

    public PluginCustomeAdapter(Context context, RecyclerView recyclerView, ArrayList arrayList) {
        this.f7312h = new ArrayList();
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() { // from class: cn.nubia.gameassist.plugin.sort.PluginCustomeAdapter.2
            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void A(RecyclerView.ViewHolder viewHolder, int i2) {
                super.A(viewHolder, i2);
                GaLog.a("PluginCustomeAdapter", "onSelectedChanged:" + viewHolder);
                if (i2 != 2) {
                    viewHolder = null;
                }
                if (viewHolder == PluginCustomeAdapter.this.f7310f || viewHolder == null) {
                    return;
                }
                PluginCustomeAdapter.this.f7310f = (Holder) viewHolder;
                PluginCustomeAdapter.this.f7310f.S();
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void B(RecyclerView.ViewHolder viewHolder, int i2) {
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public boolean a(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
                return viewHolder2.k() >= 0 && viewHolder2.k() < PluginCustomeAdapter.this.f7312h.size();
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public void c(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder) {
                super.c(recyclerView2, viewHolder);
                if (PluginCustomeAdapter.this.f7310f != null) {
                    PluginCustomeAdapter.this.f7310f.T();
                    PluginCustomeAdapter.this.f7310f = null;
                }
            }

            @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
            public int k(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder) {
                return ItemTouchHelper.Callback.t(15, 0);
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
                return PluginCustomeAdapter.this.U(viewHolder.k(), viewHolder2.k(), viewHolder2.f5252a);
            }
        };
        this.f7315k = callback;
        this.f7307c = context;
        this.f7309e = recyclerView;
        this.f7311g = new OnRecyclerItemTouchListener();
        this.f7308d = new ItemTouchHelper(callback);
        this.f7309e.k(this.f7311g);
        this.f7309e.h(new CustomDividerItemDecoration(ContextCompat.e(context, R.drawable.divider_item_decoration), context.getResources().getDimensionPixelSize(R.dimen.item_custome_sort_divider_padding)));
        this.f7312h = arrayList;
    }

    private void T(int i2, int i3) {
        ArrayList arrayList = this.f7312h;
        arrayList.add(i3, (PluginInfo) arrayList.remove(i2));
    }

    public ItemTouchHelper S() {
        return this.f7308d;
    }

    public boolean U(int i2, int i3, View view) {
        if (i3 == i2) {
            return true;
        }
        GaLog.e("PluginCustomeAdapter", "move from " + i2 + " to " + i3);
        T(i2, i3);
        u(i2, i3);
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public void A(Holder holder, int i2) {
        PluginInfo pluginInfo = (PluginInfo) this.f7312h.get(i2);
        if (pluginInfo != null) {
            holder.f7317s.setBackground(pluginInfo.a());
            holder.u.setText(pluginInfo.c());
            holder.v.setText(pluginInfo.b());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: W, reason: merged with bridge method [inline-methods] */
    public Holder C(ViewGroup viewGroup, int i2) {
        return new Holder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plugin_custome_tile_frame, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: X, reason: merged with bridge method [inline-methods] */
    public boolean E(Holder holder) {
        holder.Q();
        return true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f7312h.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        return 0;
    }
}
