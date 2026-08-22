package cn.nubia.gameassist.common;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;
import android.view.View;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.search.GlobalSearchUtil;
import cn.nubia.gameassist.theme.Theme;
import cn.nubia.gameassist.theme.ThemeController;
import cn.nubia.gameassist.theme.ThemeWidget;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public abstract class BaseViewController<U extends View> implements ThemeWidget {

    /* renamed from: c, reason: collision with root package name */
    protected final Context f6117c;

    /* renamed from: h, reason: collision with root package name */
    protected final GameAssistWindowManager f6118h;

    /* renamed from: i, reason: collision with root package name */
    protected final ThemeController f6119i;

    /* renamed from: j, reason: collision with root package name */
    protected boolean f6120j;

    /* renamed from: k, reason: collision with root package name */
    protected boolean f6121k;

    /* renamed from: l, reason: collision with root package name */
    protected final Point f6122l = new Point();

    /* renamed from: m, reason: collision with root package name */
    protected View f6123m;

    /* renamed from: n, reason: collision with root package name */
    protected Theme f6124n;

    /* renamed from: o, reason: collision with root package name */
    protected final Handler f6125o;

    /* renamed from: p, reason: collision with root package name */
    protected final Handler f6126p;

    public BaseViewController(GameAssistWindowManager gameAssistWindowManager) {
        Handler handler = new Handler(ThreadManager.c().e());
        this.f6125o = handler;
        this.f6126p = new Handler(ThreadManager.c().b());
        this.f6118h = gameAssistWindowManager;
        this.f6119i = ThemeController.m();
        this.f6117c = gameAssistWindowManager.N();
        handler.post(new Runnable() { // from class: cn.nubia.gameassist.common.c
            @Override // java.lang.Runnable
            public final void run() {
                BaseViewController.this.l();
            }
        });
        EventListenerMgr.b(new EventListener() { // from class: cn.nubia.gameassist.common.d
            @Override // com.zte.gameassist.common.EventListener
            public final void a(int i2, Object[] objArr) {
                BaseViewController.this.A(i2, objArr);
            }
        }, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void A(int i2, final Object[] objArr) {
        if (i2 == 4) {
            this.f6125o.post(new Runnable() { // from class: cn.nubia.gameassist.common.e
                @Override // java.lang.Runnable
                public final void run() {
                    BaseViewController.this.z(objArr);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void B(String str) {
        this.f6118h.u0("tilehost");
        GlobalSearchUtil.x(str);
    }

    protected static boolean v() {
        return FoldMgr.f() && FoldMgr.c().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void z(Object[] objArr) {
        D((String) objArr[0], (String) objArr[1], (IGameAssistClientCallback) objArr[2], (InMsg) objArr[3]);
    }

    public abstract int C();

    protected void D(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
    }

    public void E(boolean z) {
    }

    public void F(Configuration configuration) {
    }

    public void G(boolean z) {
    }

    public void H() {
    }

    public void I() {
    }

    public void J() {
    }

    public void K(boolean z, Point point) {
        this.f6120j = z;
        this.f6121k = z || v();
        this.f6122l.set(point.x, point.y);
    }

    protected void L(Theme theme) {
    }

    public void M() {
    }

    public void N() {
    }

    public void O() {
        P();
        this.f6123m = null;
    }

    protected abstract void P();

    protected void Q(int i2, int i3) {
        View i4 = i(i2);
        if (i4 != null) {
            i4.setVisibility(i3);
        }
    }

    public void R(final String str) {
        if (ZteFeature.isSupportGlobalSearch()) {
            this.f6125o.postDelayed(new Runnable() { // from class: cn.nubia.gameassist.common.b
                @Override // java.lang.Runnable
                public final void run() {
                    BaseViewController.this.B(str);
                }
            }, 100L);
        }
    }

    @Override // cn.nubia.gameassist.theme.ThemeWidget
    public void d(Theme theme) {
        if (this.f6124n != theme) {
            this.f6124n = theme;
            L(theme);
        }
    }

    public void h(PrintWriter printWriter, String str) {
        printWriter.println(str + getClass().getSimpleName() + ":");
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("  hasRootView=");
        sb.append(this.f6123m != null);
        printWriter.println(sb.toString());
        printWriter.println(str + "  mIsHorizontal=" + this.f6120j);
    }

    public final View i(int i2) {
        View view;
        if (i2 == 0 || (view = this.f6123m) == null) {
            return null;
        }
        return view.findViewById(i2);
    }

    public Context j() {
        return this.f6117c;
    }

    public BaseViewController k(Class cls) {
        return this.f6118h.T(cls);
    }

    protected void l() {
        this.f6119i.h(this);
    }

    public void m(View view) {
        this.f6123m = view;
        o(view);
    }

    protected abstract void o(View view);

    protected void p(Runnable runnable) {
        if (u()) {
            runnable.run();
        } else {
            this.f6126p.post(runnable);
        }
    }

    protected void q(Runnable runnable) {
        if (y()) {
            runnable.run();
        } else {
            this.f6125o.post(runnable);
        }
    }

    public int s() {
        return -1;
    }

    protected boolean u() {
        return this.f6126p.getLooper().isCurrentThread();
    }

    public boolean w() {
        return this.f6123m != null;
    }

    protected boolean y() {
        return this.f6125o.getLooper().isCurrentThread();
    }
}
