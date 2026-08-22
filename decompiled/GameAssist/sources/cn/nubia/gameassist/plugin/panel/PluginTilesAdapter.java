package cn.nubia.gameassist.plugin.panel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.plugin.sort.PluginInfo;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class PluginTilesAdapter extends RecyclerView.Adapter<PluginTilesHolder> {

    /* renamed from: c, reason: collision with root package name */
    private Context f7251c;

    /* renamed from: d, reason: collision with root package name */
    private ArrayList f7252d = new ArrayList();

    /* renamed from: e, reason: collision with root package name */
    private PluginTileView f7253e;

    /* renamed from: f, reason: collision with root package name */
    protected boolean f7254f;

    /* renamed from: g, reason: collision with root package name */
    private boolean f7255g;

    /* renamed from: h, reason: collision with root package name */
    private int f7256h;

    static class PluginTilesHolder extends RecyclerView.ViewHolder implements QSTile.Callback {

        /* renamed from: s, reason: collision with root package name */
        protected PluginTileView f7257s;
        private QSTile t;

        public PluginTilesHolder(View view) {
            super(view);
            this.f7257s = (PluginTileView) view;
        }

        public void N(QSTile qSTile) {
            QSTile qSTile2 = this.t;
            if (qSTile2 != null) {
                qSTile2.q0(this);
            }
            this.t = qSTile;
            qSTile.D(this);
            a(qSTile.M());
        }

        @Override // cn.nubia.gameassist.common.QSTile.Callback
        public void a(QSTile.State state) {
            state.f6178l = true;
            this.f7257s.h(state);
        }
    }

    public PluginTilesAdapter(Context context, int i2) {
        this.f7251c = context;
        this.f7256h = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void P(QSTile qSTile, View view) {
        GaLog.e("PluginTilesAdapter", "onClick(tile) mMode = " + this.f7256h + " - " + qSTile.N() + " mIsScrolling " + this.f7255g);
        if (this.f7255g) {
            return;
        }
        qSTile.F();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void Q(QSTile qSTile, View view) {
        GaLog.a("PluginTilesAdapter", "clickSettings(tile)  mMode = " + this.f7256h + " - " + qSTile.O());
        qSTile.G();
    }

    public void N(PrintWriter printWriter, String str) {
        printWriter.println("  PluginTilesAdapter:");
        printWriter.println("  mPluginTiles:");
        if (this.f7252d != null) {
            for (int i2 = 0; i2 < this.f7252d.size(); i2++) {
                printWriter.print("    index=" + i2);
                ((QSTile) this.f7252d.get(i2)).J(printWriter, str);
            }
        }
    }

    public int O(String str) {
        Iterator it = this.f7252d.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (str.equals(PluginInfo.d(this.f7251c, ((QSTile) it.next()).O()))) {
                break;
            }
            i2++;
        }
        if (i2 >= this.f7252d.size()) {
            return -1;
        }
        return i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: R, reason: merged with bridge method [inline-methods] */
    public void A(PluginTilesHolder pluginTilesHolder, int i2) {
        GaLog.e("PluginTilesAdapter", "onBindViewHolder position= " + i2 + " mMode = " + this.f7256h + " " + ((QSTile) this.f7252d.get(i2)).N());
        PluginTileView pluginTileView = pluginTilesHolder.f7257s;
        final QSTile qSTile = (QSTile) this.f7252d.get(i2);
        pluginTilesHolder.N(qSTile);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: cn.nubia.gameassist.plugin.panel.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PluginTilesAdapter.this.P(qSTile, view);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: cn.nubia.gameassist.plugin.panel.i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PluginTilesAdapter.this.Q(qSTile, view);
            }
        };
        pluginTileView.e(onClickListener);
        pluginTileView.f(onClickListener2);
        GlobalSearchUtil.r(pluginTilesHolder.f5252a, PluginInfo.d(this.f7251c, qSTile.O()));
        qSTile.c(true);
        qSTile.o0();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: S, reason: merged with bridge method [inline-methods] */
    public PluginTilesHolder C(ViewGroup viewGroup, int i2) {
        if (ZteFeature.isSupportSort() && i2 == 1) {
            this.f7253e = (PluginTileView) InflaterHelper.g(this.f7254f ? R.layout.item_tile_custome : R.layout.item_tile_custome_port, viewGroup, false);
            return new PluginTilesHolder(this.f7253e);
        }
        if (this.f7256h == 1) {
            this.f7253e = (PluginTileView) InflaterHelper.g(this.f7254f ? R.layout.item_tile_plugin_card : R.layout.item_tile_plugin_card_port, viewGroup, false);
        } else {
            this.f7253e = (PluginTileView) InflaterHelper.g(this.f7254f ? R.layout.item_tile_plugin : R.layout.item_tile_plugin_port, viewGroup, false);
        }
        return new PluginTilesHolder(this.f7253e);
    }

    public void T() {
        Iterator it = this.f7252d.iterator();
        while (it.hasNext()) {
            ((QSTile) it.next()).E();
        }
        this.f7252d.clear();
        this.f7253e = null;
        this.f7255g = false;
        r();
    }

    public void U(boolean z) {
        this.f7254f = z;
    }

    public void V(boolean z) {
        this.f7255g = z;
    }

    public void W(int i2) {
        this.f7256h = i2;
    }

    public void X(ArrayList arrayList) {
        this.f7252d = arrayList;
        r();
        GaLog.e("PluginTilesAdapter", "setPluginTiles tiles= " + arrayList.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return this.f7252d.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        return "custome_sort".equals(((QSTile) this.f7252d.get(i2)).O()) ? 1 : 0;
    }
}
