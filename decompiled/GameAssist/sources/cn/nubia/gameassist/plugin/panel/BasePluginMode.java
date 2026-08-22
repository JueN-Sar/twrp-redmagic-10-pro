package cn.nubia.gameassist.plugin.panel;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class BasePluginMode {

    /* renamed from: a, reason: collision with root package name */
    private final Map f7232a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    protected PluginTilesAdapter f7233b;

    /* renamed from: c, reason: collision with root package name */
    protected RecyclerView f7234c;

    /* renamed from: d, reason: collision with root package name */
    protected StaggeredGridLayoutManager f7235d;

    /* renamed from: e, reason: collision with root package name */
    private boolean f7236e;

    /* renamed from: f, reason: collision with root package name */
    private final Context f7237f;

    /* renamed from: g, reason: collision with root package name */
    private final GameAssistWindowManager f7238g;

    /* renamed from: h, reason: collision with root package name */
    protected boolean f7239h;

    /* JADX INFO: Access modifiers changed from: private */
    static class PluginLayoutManager extends StaggeredGridLayoutManager {
        public PluginLayoutManager(int i2, int i3) {
            super(i2, i3);
        }

        @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void g1(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.g1(recycler, state);
            } catch (IndexOutOfBoundsException e2) {
                GaLog.c("BasePluginMode", "onLayoutChildren IndexOutOfBoundsException!", e2);
            }
        }
    }

    public BasePluginMode(Context context, GameAssistWindowManager gameAssistWindowManager) {
        this.f7237f = context;
        this.f7238g = gameAssistWindowManager;
    }

    PluginLayoutManager a(int i2) {
        if (this.f7239h) {
            PluginLayoutManager pluginLayoutManager = new PluginLayoutManager(1, 1);
            pluginLayoutManager.U2(false);
            return pluginLayoutManager;
        }
        PluginLayoutManager pluginLayoutManager2 = i2 == 1 ? new PluginLayoutManager(1, 0) : new PluginLayoutManager(2, 0);
        pluginLayoutManager2.U2(true);
        return pluginLayoutManager2;
    }

    public void b(boolean z, View view, int i2) {
        if (!this.f7236e) {
            h();
        }
        this.f7233b.U(z);
        this.f7234c.setLayoutManager(this.f7235d);
        this.f7234c.setAdapter(this.f7233b);
        this.f7234c.l(new RecyclerView.OnScrollListener() { // from class: cn.nubia.gameassist.plugin.panel.BasePluginMode.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void a(RecyclerView recyclerView, int i3) {
                super.a(recyclerView, i3);
                GaLog.e("BasePluginMode", " newState = " + i3);
                BasePluginMode.this.f7233b.V(i3 != 0);
            }
        });
        if (this.f7232a.containsKey(Boolean.valueOf(z))) {
            try {
                this.f7235d.l1((Parcelable) this.f7232a.get(Boolean.valueOf(z)));
                this.f7235d.U2(!z);
            } catch (Exception unused) {
                PluginLayoutManager a2 = a(i2);
                this.f7235d = a2;
                this.f7234c.setLayoutManager(a2);
            }
        }
    }

    public void c() {
        this.f7233b.r();
    }

    public void d() {
        this.f7233b.T();
    }

    public void e() {
        StringBuilder sb = new StringBuilder();
        sb.append("recycleView: reverseLayout = ");
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f7235d;
        sb.append(staggeredGridLayoutManager == null ? null : Boolean.valueOf(staggeredGridLayoutManager.A2()));
        GaLog.a("BasePluginMode", sb.toString());
        StaggeredGridLayoutManager staggeredGridLayoutManager2 = this.f7235d;
        if (staggeredGridLayoutManager2 != null) {
            if (staggeredGridLayoutManager2.A2()) {
                this.f7235d.U2(false);
            }
            this.f7232a.put(Boolean.valueOf(this.f7239h), this.f7235d.m1());
            this.f7235d = null;
        }
        RecyclerView recyclerView = this.f7234c;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(null);
            this.f7234c.setAdapter(null);
            this.f7234c = null;
        }
    }

    public void f(String str) {
        int O = this.f7233b.O(str);
        if (O == -1) {
            ToastUtil.a(this.f7237f.getString(R.string.small_window_not_support));
        } else {
            this.f7234c.s1(O);
            this.f7233b.r();
            GlobalSearchUtil.x(str);
        }
        GaLog.a("BasePluginMode", "showFlicker " + str + " position at " + O);
    }

    public void g(boolean z) {
        RecyclerView recyclerView = this.f7234c;
        if (recyclerView == null) {
            return;
        }
        if (z) {
            recyclerView.setVisibility(0);
            this.f7234c.setContentDescription(this.f7239h ? this.f7237f.getString(R.string.plugin_horizontal) : this.f7237f.getString(R.string.plugin_vertical));
        } else {
            recyclerView.setContentDescription("");
            this.f7234c.setVisibility(4);
        }
    }

    public void h() {
        i(false);
    }

    public void i(boolean z) {
        TileHost S = this.f7238g.S();
        if (S == null) {
            this.f7236e = false;
            return;
        }
        ArrayList arrayList = new ArrayList(S.o(z));
        GaLog.e("BasePluginMode", "setPluginTiles(tiles) = " + arrayList.size() + " , mPluginTilesAdapter = " + this.f7233b);
        this.f7233b.X(arrayList);
        this.f7236e = true;
    }
}
