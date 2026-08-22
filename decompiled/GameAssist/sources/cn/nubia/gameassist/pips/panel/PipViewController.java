package cn.nubia.gameassist.pips.panel;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import cn.nubia.gameassist.GameAssistApplication;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.common.BaseViewController;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.dessert.custom.CustomTileOrder;
import cn.nubia.gameassist.dessert.panel.DessertViewController;
import cn.nubia.gameassist.install.InstallListener;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.pips.PipFactory;
import cn.nubia.gameassist.pips.PipInfo;
import cn.nubia.gameassist.pips.PipStateListener;
import cn.nubia.gameassist.pips.custom.CustomPipOrder;
import cn.nubia.gameassist.utils.AppsHelper;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.common.InflaterHelper;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.dialog.GameAssistDialog;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import com.zte.mifavor.widget.AlertDialog;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/* loaded from: classes.dex */
public class PipViewController extends BaseViewController<ViewGroup> implements View.OnClickListener, InstallListener, RotationMgr.Callback {
    public static final String J = ZteFeature.getBrowserPackage();
    private boolean A;
    private ImageView B;
    private ImageView C;
    private ImageView D;
    private ImageView E;
    private ImageView F;
    private final View.OnClickListener G;
    private final Runnable H;
    private final Runnable I;

    /* renamed from: q, reason: collision with root package name */
    private RecyclerView f7186q;

    /* renamed from: r, reason: collision with root package name */
    private final PipTilesAdapter f7187r;

    /* renamed from: s, reason: collision with root package name */
    private final ArrayList f7188s;
    private final ArrayList t;
    private final Map u;
    private StaggeredGridLayoutManager v;
    private Dialog w;
    private String x;
    private String y;
    private String z;

    public PipViewController(GameAssistWindowManager gameAssistWindowManager) {
        super(gameAssistWindowManager);
        this.f7188s = new ArrayList();
        this.t = new ArrayList();
        this.u = new HashMap();
        this.A = false;
        this.G = new View.OnClickListener() { // from class: cn.nubia.gameassist.pips.panel.PipViewController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view.getId() == R.id.sourtitle) {
                    PipViewController pipViewController = PipViewController.this;
                    pipViewController.M0(pipViewController.x, false);
                } else if (view.getId() == R.id.twintitle) {
                    PipViewController pipViewController2 = PipViewController.this;
                    pipViewController2.M0(pipViewController2.x, true);
                }
                if (PipViewController.this.w != null) {
                    PipViewController.this.w.dismiss();
                }
            }
        };
        this.H = new Runnable() { // from class: cn.nubia.gameassist.pips.panel.h
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.R0();
            }
        };
        this.I = new Runnable() { // from class: cn.nubia.gameassist.pips.panel.PipViewController.2
            @Override // java.lang.Runnable
            public void run() {
                PipViewController.this.f7187r.R((ArrayList) PipViewController.this.f7188s.stream().skip(3L).collect(Collectors.toList()));
            }
        };
        PipTilesAdapter pipTilesAdapter = new PipTilesAdapter(this.f6117c);
        this.f7187r = pipTilesAdapter;
        pipTilesAdapter.S(this);
        RotationMgr.e(this.f6117c).c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A0(String str, boolean z) {
        AppsHelper.l(this.f6117c, str, z ? 999 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int B0(QSTile qSTile, QSTile qSTile2) {
        if (qSTile == null || qSTile2 == null) {
            return 0;
        }
        return qSTile.compareTo(qSTile2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean C0(QSTile[] qSTileArr, QSTile qSTile) {
        if (J.equals(qSTile.O())) {
            qSTileArr[0] = qSTile;
            return true;
        }
        if ("com.tencent.mm".equals(qSTile.O())) {
            qSTileArr[1] = qSTile;
            return true;
        }
        if (!"com.tencent.mobileqq".equals(qSTile.O())) {
            return false;
        }
        qSTileArr[2] = qSTile;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean D0(Map map, AtomicBoolean atomicBoolean, String str) {
        for (Map.Entry entry : map.entrySet()) {
            if (str.equals(entry.getKey())) {
                this.f7188s.add((QSTile) entry.getValue());
                return false;
            }
        }
        atomicBoolean.set(true);
        GaLog.a("PipViewController", "not contains package name,need delete " + str);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void E0() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.f7188s.size() && i2 < 3; i3++) {
            String O = ((QSTile) this.f7188s.get(i3)).O();
            if (i2 == 0) {
                I0(this.B, O);
            } else if (i2 == 1) {
                I0(this.C, O);
            } else {
                I0(this.D, O);
            }
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void F0() {
        Iterator it = this.t.iterator();
        while (it.hasNext()) {
            ((PipStateListener) it.next()).d(o0());
        }
    }

    private void H0(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("pip_app_name", str);
        bundle.putString("background_app_name", Utils.j());
        NubiaTrackManager.p().v("game_pip_app", bundle);
    }

    private void I0(ImageView imageView, String str) {
        if (str == null || imageView == null) {
            return;
        }
        PipFactory.LazyDrawable h2 = PipFactory.LazyDrawable.h(str);
        if (h2 != null) {
            h2.l(imageView);
        }
        if (str.equals(SystemMgr.t())) {
            imageView.setAlpha(0.26f);
            imageView.setClickable(false);
        } else {
            imageView.setAlpha(1.0f);
            imageView.setClickable(true);
        }
    }

    private void J0(String str) {
        Dialog dialog = this.w;
        if (dialog == null || !dialog.isShowing()) {
            GaLog.a("PipViewController", "showDialog(" + str + ")");
            View f2 = InflaterHelper.f(R.layout.qs_wechat_dialog, null);
            TextView textView = (TextView) f2.findViewById(R.id.sourtitle);
            textView.setText(q0(str));
            Drawable f3 = AppsHelper.f(this.f6117c, this.x);
            int dimensionPixelSize = this.f6117c.getResources().getDimensionPixelSize(R.dimen.pip_dialog_icon_width);
            if (f3 != null) {
                f3.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
            textView.setCompoundDrawables(null, f3, null, null);
            TextView textView2 = (TextView) f2.findViewById(R.id.twintitle);
            textView2.setText(q0(str));
            Drawable j2 = AppsHelper.j(this.f6117c, this.x);
            if (j2 != null) {
                j2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            }
            textView2.setCompoundDrawables(null, j2, null, null);
            textView.setOnClickListener(this.G);
            textView2.setOnClickListener(this.G);
            AlertDialog a2 = new AlertDialog.Builder(this.f6117c, com.zte.extres.R.style.Theme_ZTE_Light_Dialog_Alert).l(R.string.app_clone).c(true).n(f2).f(com.zte.gameassist.common.R.string.single_cancel, new DialogInterface.OnClickListener() { // from class: cn.nubia.gameassist.pips.panel.o
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    PipViewController.this.z0(dialogInterface, i2);
                }
            }).a();
            this.w = a2;
            a2.getWindow().setType(2008);
            this.w.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.w.show();
            GameAssistDialog.f(this.w.getWindow());
        }
    }

    private void L0(ViewGroup viewGroup) {
        LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.game_assist_pips);
        this.F = (ImageView) i(R.id.game_assist_logo);
        this.B.setVisibility(8);
        this.C.setVisibility(8);
        this.D.setVisibility(8);
        this.E.setVisibility(8);
        this.F.setVisibility(0);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.F.getLayoutParams();
        if (this.f6120j) {
            this.F.setRotation(0.0f);
            linearLayout.setGravity(8388629);
            layoutParams.setMarginEnd(this.f6117c.getResources().getDimensionPixelSize(R.dimen.game_assist_button_margin));
        } else {
            this.F.setRotation(90.0f);
            linearLayout.setGravity(17);
            layoutParams.setMarginEnd(0);
        }
        this.F.setLayoutParams(layoutParams);
    }

    private void N0() {
        if (this.f6120j) {
            this.v.T2(1);
            this.v.U2(false);
        } else {
            this.v.T2(0);
            this.v.U2(true);
        }
    }

    private void O0() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f7188s.iterator();
        while (it.hasNext()) {
            arrayList.add(((QSTile) it.next()).O());
        }
        String join = TextUtils.join(",", arrayList);
        GaLog.a("PipViewController", "save local pip " + join);
        SharedPreferencesUtil.k(this.f6117c).N(join);
        q(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.d
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.F0();
            }
        });
    }

    private void P0() {
        this.f6125o.removeCallbacks(this.I);
        this.f6125o.postDelayed(this.I, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void R0() {
        /*
            Method dump skipped, instructions count: 276
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.nubia.gameassist.pips.panel.PipViewController.R0():void");
    }

    private void S0() {
        this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.e
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.E0();
            }
        });
    }

    private void n0() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        this.v = staggeredGridLayoutManager;
        this.f7186q.setLayoutManager(staggeredGridLayoutManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void u0() {
        ((CustomPipOrder) InflaterHelper.e(R.layout.pip_customize_panel_content)).l();
        this.f6118h.g0("tilehost");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void v0() {
        ((CustomTileOrder) InflaterHelper.e(R.layout.qs_customize_panel_content)).l();
        this.f6118h.g0("tilehost");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void w0(String str, Context context) {
        GaLog.a("PipViewController", "app change " + str);
        if (str == null || TilesUtil.s(context, str)) {
            Q0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void x0() {
        this.f6118h.g0("tilehost");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void y0(View view) {
        if (this.A) {
            l0();
        } else {
            m0();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z0(DialogInterface dialogInterface, int i2) {
        this.w.dismiss();
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public int C() {
        return R.id.game_assist_right_panel;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void G(boolean z) {
        super.G(z);
        if (z) {
            this.y = SystemMgr.t();
        } else {
            k0();
        }
    }

    public void G0(PipStateListener pipStateListener) {
        this.t.remove(pipStateListener);
    }

    public void K0() {
        if (this.A) {
            this.A = false;
            this.E.setSelected(false);
            this.f7186q.setVisibility(8);
            ((DessertViewController) k(DessertViewController.class)).Z(true);
        } else {
            this.A = true;
            this.E.setSelected(true);
            this.f7186q.setVisibility(0);
            ((DessertViewController) k(DessertViewController.class)).Z(false);
        }
        this.E.setImageResource(this.A ? R.drawable.game_assist_button_clo : R.drawable.game_assist_button_exp);
    }

    public void M0(final String str, final boolean z) {
        try {
            GaLog.a("PipViewController", "start pn:" + str + ",twin:" + z);
            this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.i
                @Override // java.lang.Runnable
                public final void run() {
                    PipViewController.this.A0(str, z);
                }
            }, 300L);
            H0(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void P() {
        GameAssistApplication.j().v(this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.v;
        if (staggeredGridLayoutManager != null) {
            if (staggeredGridLayoutManager.A2()) {
                this.v.U2(false);
            }
            this.u.put(Boolean.valueOf(this.f6120j), this.v.m1());
            this.v = null;
        }
        ImageView imageView = this.B;
        if (imageView != null) {
            imageView.setOnClickListener(null);
            this.B = null;
        }
        ImageView imageView2 = this.C;
        if (imageView2 != null) {
            imageView2.setOnClickListener(null);
            this.C = null;
        }
        ImageView imageView3 = this.D;
        if (imageView3 != null) {
            imageView3.setOnClickListener(null);
            this.D = null;
        }
        ImageView imageView4 = this.E;
        if (imageView4 != null) {
            imageView4.setOnClickListener(null);
            this.E = null;
        }
        RecyclerView recyclerView = this.f7186q;
        if (recyclerView != null) {
            recyclerView.setAdapter(null);
            this.f7186q.setLayoutManager(null);
            this.f7186q = null;
        }
    }

    public void Q0() {
        this.f6126p.removeCallbacks(this.H);
        this.f6126p.post(this.H);
    }

    @Override // cn.nubia.gameassist.install.InstallListener
    public void f(String str) {
        r0(this.f6117c, str);
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    public void h(PrintWriter printWriter, String str) {
        super.h(printWriter, str);
        printWriter.println("PipViewController:");
        printWriter.println(str + "  mPackageName=" + this.x);
        printWriter.println(str + "  mPipTileEnable=" + this.A);
        printWriter.println(str + "  size=" + this.f7188s.size());
        if (this.v != null) {
            printWriter.println(str + "  reverse = " + this.v.A2());
        }
        PipTilesAdapter pipTilesAdapter = this.f7187r;
        if (pipTilesAdapter != null) {
            pipTilesAdapter.N(printWriter, str);
        }
    }

    public void j0(PipStateListener pipStateListener) {
        if (this.t.contains(pipStateListener)) {
            return;
        }
        this.t.add(pipStateListener);
    }

    public void k0() {
        this.A = false;
        PipFactory.LazyDrawable.g(this.f7188s);
        ArrayList arrayList = this.f7188s;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void l0() {
        this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.f
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.u0();
            }
        });
    }

    public void m0() {
        this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.c
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.v0();
            }
        });
    }

    public List o0() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f7188s.iterator();
        while (it.hasNext()) {
            QSTile qSTile = (QSTile) it.next();
            arrayList.add(new PipInfo(qSTile.N(), qSTile.O()));
        }
        return arrayList;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.game_assist_pip_exp) {
            K0();
            return;
        }
        if (id == R.id.game_assist_pip_one) {
            this.x = this.f7188s.size() > 0 ? ((QSTile) this.f7188s.get(0)).O() : null;
        } else if (id == R.id.game_assist_pip_two) {
            this.x = this.f7188s.size() > 1 ? ((QSTile) this.f7188s.get(1)).O() : null;
        } else if (id == R.id.game_assist_pip_three) {
            this.x = this.f7188s.size() > 2 ? ((QSTile) this.f7188s.get(2)).O() : null;
        }
        s0(this.x);
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        Dialog dialog = this.w;
        if (dialog == null || !dialog.isShowing()) {
            return;
        }
        GameAssistDialog.f(this.w.getWindow());
    }

    public String p0() {
        return this.y;
    }

    public String q0(String str) {
        return AppsHelper.b(str);
    }

    public void r0(final Context context, final String str) {
        GameAssistWindowManager gameAssistWindowManager = this.f6118h;
        if ((gameAssistWindowManager == null || gameAssistWindowManager.d0()) && this.t.size() == 0) {
            return;
        }
        p(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.b
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.w0(str, context);
            }
        });
    }

    protected boolean s0(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        if (Utils.z(this.f6117c, str)) {
            GaLog.a("PipViewController", "app is disabled");
            this.f6118h.g0("tilehost");
            ToastUtil.a(this.f6117c.getString(R.string.app_is_disabled));
            return true;
        }
        if (TilesUtil.n(this.f6117c, str)) {
            ArrayList k2 = TilesUtil.k(this.f6117c);
            String str2 = str + "#999";
            if (k2.contains(str) || k2.contains(str2)) {
                M0(str, !k2.contains(str2));
            } else {
                J0(str);
            }
        } else {
            M0(str, false);
        }
        this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.pips.panel.g
            @Override // java.lang.Runnable
            public final void run() {
                PipViewController.this.x0();
            }
        }, 100L);
        return false;
    }

    @Override // cn.nubia.gameassist.common.BaseViewController
    /* renamed from: t0, reason: merged with bridge method [inline-methods] */
    public void o(ViewGroup viewGroup) {
        this.f6120j = this.f6121k;
        this.B = (ImageView) i(R.id.game_assist_pip_one);
        this.C = (ImageView) i(R.id.game_assist_pip_two);
        this.D = (ImageView) i(R.id.game_assist_pip_three);
        this.E = (ImageView) i(R.id.game_assist_pip_exp);
        if (ZteFeature.supportWindowReply()) {
            this.B.setOnClickListener(this);
            this.C.setOnClickListener(this);
            this.D.setOnClickListener(this);
            this.E.setOnClickListener(this);
            if (!this.f6120j) {
                this.E.setRotation(90.0f);
            }
            this.f7186q = (RecyclerView) viewGroup.findViewById(R.id.game_assist_pip_recycler);
            this.f7187r.Q(this.f6120j);
            this.f7186q.setAdapter(this.f7187r);
            n0();
            this.f7186q.setLayoutManager(this.v);
            if (this.u.containsKey(Boolean.valueOf(this.f6120j))) {
                try {
                    this.v.l1((Parcelable) this.u.get(Boolean.valueOf(this.f6120j)));
                } catch (Exception e2) {
                    GaLog.b("PipViewController", "restore state e " + e2);
                    n0();
                }
            }
            N0();
            GameAssistApplication.j().f(this);
            Q0();
        } else {
            L0(viewGroup);
        }
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.iv_edit_order);
        if (imageView != null) {
            imageView.setOnClickListener(new View.OnClickListener() { // from class: cn.nubia.gameassist.pips.panel.j
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PipViewController.this.y0(view);
                }
            });
        }
    }

    @Override // cn.nubia.gameassist.install.InstallListener
    public void x(String str) {
        r0(this.f6117c, null);
    }
}
