package cn.nubia.gameassist.dessert.panel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSState;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.TileView;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.theme.Theme;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DessertTilesAdapter extends RecyclerView.Adapter<DessertTilesHolder> {

    /* renamed from: c, reason: collision with root package name */
    private Context f6259c;

    /* renamed from: d, reason: collision with root package name */
    private ArrayList f6260d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    private TileView f6261e;

    /* renamed from: f, reason: collision with root package name */
    protected boolean f6262f;

    /* renamed from: g, reason: collision with root package name */
    private Theme f6263g;

    /* renamed from: h, reason: collision with root package name */
    private boolean f6264h;

    class DessertTilesHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        protected TileView f6270s;

        public DessertTilesHolder(DessertTilesAdapter dessertTilesAdapter, View view) {
            super(view);
            this.f6270s = (TileView) view;
        }
    }

    public DessertTilesAdapter(Context context) {
        this.f6259c = context;
    }

    public void M(PrintWriter printWriter, String str) {
        printWriter.println("  mDessertTilesAdapter:");
        printWriter.println("  mDessertTiles:");
        if (this.f6260d != null) {
            for (int i2 = 0; i2 < this.f6260d.size(); i2++) {
                printWriter.print("    index=" + i2);
                ((QSTile) this.f6260d.get(i2)).J(printWriter, str);
            }
        }
    }

    public int N(String str) {
        Iterator it = this.f6260d.iterator();
        int i2 = 0;
        while (it.hasNext() && !str.equals(new QSState(((QSTile) it.next()).O(), this.f6259c).f6147c)) {
            i2++;
        }
        if (i2 >= this.f6260d.size()) {
            return -1;
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public void A(DessertTilesHolder dessertTilesHolder, int i2) {
        final TileView tileView = dessertTilesHolder.f6270s;
        final QSTile qSTile = (QSTile) this.f6260d.get(i2);
        QSTile.Callback callback = new QSTile.Callback() { // from class: cn.nubia.gameassist.dessert.panel.DessertTilesAdapter.1
            @Override // cn.nubia.gameassist.common.QSTile.Callback
            public void a(QSTile.State state) {
                state.f6178l = false;
                DessertTilesAdapter.this.f6264h = state.f6175i;
                tileView.g(state);
            }
        };
        ((QSTile) this.f6260d.get(i2)).E().D(callback);
        View.OnClickListener onClickListener = new View.OnClickListener(this) { // from class: cn.nubia.gameassist.dessert.panel.DessertTilesAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GaLog.a("DessertTilesAdapter", "onClick(tile) - " + qSTile.O());
                qSTile.F();
            }
        };
        new View.OnClickListener(this) { // from class: cn.nubia.gameassist.dessert.panel.DessertTilesAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GaLog.a("DessertTilesAdapter", "onClickSettings(tile) - " + qSTile.O());
                qSTile.G();
            }
        };
        View.OnLongClickListener onLongClickListener = new View.OnLongClickListener(this) { // from class: cn.nubia.gameassist.dessert.panel.DessertTilesAdapter.4
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                GaLog.a("DessertTilesAdapter", "onLongClick(tile) - " + qSTile.O());
                qSTile.j0();
                return true;
            }
        };
        tileView.e(onClickListener);
        if (qSTile.O() != null && qSTile.O().equals("mis_operate")) {
            tileView.f(onLongClickListener);
        }
        qSTile.c(true);
        callback.a(qSTile.M());
        qSTile.o0();
        GlobalSearchUtil.r(dessertTilesHolder.f5252a, new QSState(qSTile.O(), this.f6259c).f6147c);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public DessertTilesHolder C(ViewGroup viewGroup, int i2) {
        this.f6261e = (TileView) InflaterHelper.g(this.f6262f ? R.layout.item_tile_dessert : R.layout.item_tile_dessert_port, viewGroup, false);
        DessertTilesHolder dessertTilesHolder = new DessertTilesHolder(this, this.f6261e);
        dessertTilesHolder.H(false);
        return dessertTilesHolder;
    }

    public void Q() {
        Iterator it = this.f6260d.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).E();
        }
        this.f6260d.clear();
        this.f6261e = null;
    }

    public void R(ArrayList arrayList) {
        this.f6260d = arrayList;
        r();
    }

    public void S(boolean z) {
        this.f6262f = z;
    }

    public void T(Theme theme) {
        this.f6263g = theme;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f6260d.size();
    }
}
