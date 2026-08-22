package cn.nubia.multisubscreen;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.multisubscreen.view.MultiSubScreenTileView;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class DessertAdapter extends RecyclerView.Adapter<DessertTilesHolder> {

    /* renamed from: c, reason: collision with root package name */
    private Context f7885c;

    /* renamed from: d, reason: collision with root package name */
    private ArrayList f7886d = new ArrayList();

    class DessertTilesHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        protected MultiSubScreenTileView f7888s;

        public DessertTilesHolder(DessertAdapter dessertAdapter, View view) {
            super(view);
            this.f7888s = (MultiSubScreenTileView) view;
        }
    }

    public DessertAdapter(Context context) {
        this.f7885c = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void N(QSTile qSTile, View view) {
        GaLog.a("MultiSubScreen_DessertAdapter", "onClick(tile) - " + qSTile.O());
        qSTile.F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void O(QSTile qSTile, View view) {
        GaLog.a("MultiSubScreen_DessertAdapter", "onClickSettings(tile) - " + qSTile.O());
        qSTile.G();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public void A(DessertTilesHolder dessertTilesHolder, int i2) {
        final MultiSubScreenTileView multiSubScreenTileView = dessertTilesHolder.f7888s;
        final QSTile qSTile = (QSTile) this.f7886d.get(i2);
        QSTile.Callback callback = new QSTile.Callback(this) { // from class: cn.nubia.multisubscreen.DessertAdapter.1
            @Override // cn.nubia.gameassist.common.QSTile.Callback
            public void a(QSTile.State state) {
                state.f6178l = false;
                multiSubScreenTileView.e(state);
            }
        };
        ((QSTile) this.f7886d.get(i2)).E().D(callback);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: cn.nubia.multisubscreen.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DessertAdapter.N(QSTile.this, view);
            }
        };
        new View.OnClickListener() { // from class: cn.nubia.multisubscreen.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DessertAdapter.O(QSTile.this, view);
            }
        };
        multiSubScreenTileView.c(onClickListener);
        qSTile.c(true);
        callback.a(qSTile.M());
        qSTile.o0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: Q, reason: merged with bridge method [inline-methods] */
    public DessertTilesHolder C(ViewGroup viewGroup, int i2) {
        return new DessertTilesHolder(this, InflaterHelper.g(R.layout.multi_sub_screen_item_tile_dessert, viewGroup, false));
    }

    public void R() {
        Iterator it = this.f7886d.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).E();
        }
        this.f7886d.clear();
    }

    public void S(ArrayList arrayList) {
        GaLog.e("MultiSubScreen_DessertAdapter", "setDessertTiles tiles = " + arrayList);
        this.f7886d = arrayList;
        r();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f7886d.size();
    }
}
