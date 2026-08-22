package cn.nubia.gameassist.dessert.panel;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import com.zte.gameassist.utils.GaLog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class DessertViewController extends BaseViewController<RecyclerView> {

    /* renamed from: q, reason: collision with root package name */
    private final DessertTilesAdapter f6271q;

    /* renamed from: r, reason: collision with root package name */
    private final Map f6272r;

    /* renamed from: s, reason: collision with root package name */
    private StaggeredGridLayoutManager f6273s;

    public DessertViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.f6272r = new HashMap();
        this.f6271q = new DessertTilesAdapter(this.f6117c);
        this.f6126p.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.panel.b
            @Override // java.lang.Runnable
            public final void run() {
                DessertViewController.this.W();
            }
        });
    }

    private void U() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        this.f6273s = staggeredGridLayoutManager;
        ((RecyclerView) this.f6123m).setLayoutManager(staggeredGridLayoutManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void W() {
        TilesUtil.c(this.f6117c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void X(String str) {
        this.f6118h.u0("tilehost");
        int N = this.f6271q.N(str);
        if (N == -1) {
            ToastUtil.a(this.f6117c.getString(R.string.small_window_not_support));
            return;
        }
        ((RecyclerView) this.f6123m).s1(N);
        this.f6271q.r();
        GlobalSearchUtil.x(str);
        GaLog.e("DessertViewController", "showFlicker " + str + " position at " + N);
    }

    private void b0() {
        if (this.f6120j) {
            this.f6273s.T2(1);
            this.f6273s.U2(false);
        } else {
            this.f6273s.T2(0);
            this.f6273s.U2(true);
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_dessert;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void G(boolean z) {
        if (z) {
            this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.dessert.panel.c
                @Override // java.lang.Runnable
                public final void run() {
                    DessertViewController.this.a0();
                }
            });
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    protected void L(Theme theme) {
        super.L(theme);
        this.f6271q.T(theme);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f6273s;
        if (staggeredGridLayoutManager != null) {
            if (staggeredGridLayoutManager.A2()) {
                this.f6273s.U2(false);
            }
            this.f6272r.put(Boolean.valueOf(this.f6120j), this.f6273s.m1());
            this.f6273s = null;
        }
        View view = this.f6123m;
        if (view != null) {
            ((RecyclerView) view).setAdapter(null);
            ((RecyclerView) this.f6123m).setLayoutManager(null);
            this.f6123m = null;
        }
        this.f6271q.Q();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    /* renamed from: V, reason: merged with bridge method [inline-methods] */
    public void o(RecyclerView recyclerView) {
        Context context;
        int i2;
        this.f6120j = this.f6121k;
        a0();
        U();
        RecyclerView recyclerView2 = (RecyclerView) this.f6123m;
        if (this.f6120j) {
            context = this.f6117c;
            i2 = R.string.dessert_horizontal;
        } else {
            context = this.f6117c;
            i2 = R.string.dessert_vertical;
        }
        recyclerView2.setContentDescription(context.getString(i2));
        this.f6271q.S(this.f6120j);
        ((RecyclerView) this.f6123m).setAdapter(this.f6271q);
        if (this.f6272r.containsKey(Boolean.valueOf(this.f6120j))) {
            try {
                this.f6273s.l1((Parcelable) this.f6272r.get(Boolean.valueOf(this.f6120j)));
            } catch (Exception e2) {
                GaLog.b("DessertViewController", "restore state e " + e2);
                U();
            }
        }
        b0();
    }

    public void Y(final String str) {
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.dessert.panel.a
            @Override // java.lang.Runnable
            public final void run() {
                DessertViewController.this.X(str);
            }
        }, 100L);
    }

    public void Z(boolean z) {
        ((RecyclerView) this.f6123m).setVisibility(z ? 0 : 8);
    }

    public void a0() {
        if (this.f6118h.S() == null) {
            GaLog.b("DessertViewController", "tile host is null");
        } else {
            this.f6271q.R(new ArrayList(this.f6118h.S().m()));
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        if (this.f6123m != null) {
            printWriter.println(str + "  Adapter= " + ((RecyclerView) this.f6123m).getAdapter());
            printWriter.println(str + "  LayoutManager = " + ((RecyclerView) this.f6123m).getLayoutManager());
            if (this.f6273s != null) {
                printWriter.println(str + "  reverse = " + this.f6273s.A2());
            }
        }
        DessertTilesAdapter dessertTilesAdapter = this.f6271q;
        if (dessertTilesAdapter != null) {
            dessertTilesAdapter.M(printWriter, str);
        }
    }
}
