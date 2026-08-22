package cn.nubia.gameassist.pips.panel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.pips.PipFactory;
import cn.nubia.gameassist.pips.panel.PipTilesAdapter;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PipTilesAdapter extends RecyclerView.Adapter<PipTilesHolder> {

    /* renamed from: c, reason: collision with root package name */
    private final Context f7180c;

    /* renamed from: d, reason: collision with root package name */
    protected boolean f7181d;

    /* renamed from: e, reason: collision with root package name */
    private PipViewController f7182e;

    /* renamed from: f, reason: collision with root package name */
    private final ArrayList f7183f = new ArrayList();

    class PipTilesHolder extends RecyclerView.ViewHolder {

        /* renamed from: s, reason: collision with root package name */
        public ImageView f7184s;

        public PipTilesHolder(View view) {
            super(view);
            this.f7184s = (ImageView) view.findViewById(R.id.pip_module);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void O(View view) {
            PipTilesAdapter.this.f7182e.l0();
        }

        public void P() {
            ViewGroup.LayoutParams layoutParams = this.f7184s.getLayoutParams();
            int dimensionPixelSize = PipTilesAdapter.this.f7180c.getResources().getDimensionPixelSize(R.dimen.dessert_tile_icon_width);
            layoutParams.width = dimensionPixelSize;
            layoutParams.height = dimensionPixelSize;
            this.f7184s.setLayoutParams(layoutParams);
            this.f7184s.setImageDrawable(ContextCompat.e(PipTilesAdapter.this.f7180c, R.drawable.game_assist_icon_pip_add));
            this.f7184s.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gameassist.pips.panel.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PipTilesAdapter.PipTilesHolder.this.O(view);
                }
            });
        }

        public void Q(final QSTile qSTile) {
            this.f7184s.setImageDrawable(ContextCompat.e(PipTilesAdapter.this.f7180c, R.drawable.pip_icon_placeholder));
            String O = qSTile.O();
            if (O == null) {
                return;
            }
            PipFactory.LazyDrawable h2 = PipFactory.LazyDrawable.h(O);
            if (h2 != null) {
                h2.l(this.f7184s).setOnClickListener(new View.OnClickListener(this) { // from class: cn.nubia.gameassist.pips.panel.PipTilesAdapter.PipTilesHolder.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        qSTile.F();
                    }
                });
            } else {
                GaLog.k("PipTilesAdapter", qSTile.O() + " not have drawable");
            }
            if (O.equals(SystemMgr.t())) {
                this.f5252a.setAlpha(0.26f);
                this.f7184s.setClickable(false);
            } else {
                this.f5252a.setAlpha(1.0f);
                this.f7184s.setClickable(true);
            }
        }
    }

    public PipTilesAdapter(Context context) {
        this.f7180c = context;
    }

    public void N(PrintWriter printWriter, String str) {
        printWriter.println("  PluginTilesAdapter:");
        printWriter.println("  mPluginTiles:");
        for (int i2 = 0; i2 < this.f7183f.size(); i2++) {
            printWriter.print("    index=" + i2);
            ((QSTile) this.f7183f.get(i2)).J(printWriter, str);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: O, reason: merged with bridge method [inline-methods] */
    public void A(PipTilesHolder pipTilesHolder, int i2) {
        if (pipTilesHolder.m() == 1) {
            pipTilesHolder.P();
        } else if (pipTilesHolder.m() == 0) {
            pipTilesHolder.Q((QSTile) this.f7183f.get(i2));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: P, reason: merged with bridge method [inline-methods] */
    public PipTilesHolder C(ViewGroup viewGroup, int i2) {
        return new PipTilesHolder(InflaterHelper.g(this.f7181d ? R.layout.item_tile_pip : R.layout.item_tile_pip_port, viewGroup, false));
    }

    public void Q(boolean z) {
        this.f7181d = z;
    }

    public void R(ArrayList arrayList) {
        this.f7183f.clear();
        this.f7183f.addAll(arrayList);
        r();
    }

    public void S(PipViewController pipViewController) {
        this.f7182e = pipViewController;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int m() {
        return (FoldMgr.f() && FoldMgr.c().e()) ? this.f7183f.size() : this.f7183f.size() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int o(int i2) {
        return i2 >= this.f7183f.size() ? 1 : 0;
    }
}
