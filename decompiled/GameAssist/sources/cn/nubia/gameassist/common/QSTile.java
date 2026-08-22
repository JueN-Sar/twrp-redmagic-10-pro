package cn.nubia.gameassist.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;
import cn.nubia.gameassist.R;
import cn.nubia.gameassist.dessert.policy.Listenable;
import cn.nubia.gameassist.panel.GameAssistWindowManager;
import cn.nubia.gameassist.performance.PerformanceViewController;
import cn.nubia.gameassist.plugin.config.PluginConfig;
import cn.nubia.gameassist.utils.RecycleWatch;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.ToastUtil;
import cn.nubia.gameassist.utils.Utils;
import cn.nubia.gameassist.view.NubiaTextClock;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.FoldMgr;
import com.zte.gameassist.common.GameMonitor;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.RotationMgr;
import com.zte.gameassist.common.SystemMgr;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import com.zte.gameassist.utils.NubiaTrackManager;
import com.zte.gameassist.utils.SharedPreferencesUtil;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public abstract class QSTile implements Listenable, Comparable<QSTile>, RotationMgr.Callback {

    /* renamed from: h, reason: collision with root package name */
    protected final Host f6152h;

    /* renamed from: i, reason: collision with root package name */
    protected final Context f6153i;

    /* renamed from: j, reason: collision with root package name */
    protected final H f6154j;

    /* renamed from: m, reason: collision with root package name */
    protected State f6157m;

    /* renamed from: n, reason: collision with root package name */
    protected State f6158n;

    /* renamed from: o, reason: collision with root package name */
    private boolean f6159o;

    /* renamed from: p, reason: collision with root package name */
    protected PerformanceViewController f6160p;

    /* renamed from: q, reason: collision with root package name */
    protected String f6161q;
    protected boolean t;
    protected boolean u;

    /* renamed from: c, reason: collision with root package name */
    protected final String f6151c = "QSTile." + getClass().getSimpleName();

    /* renamed from: k, reason: collision with root package name */
    protected final Handler f6155k = new Handler(Looper.getMainLooper());

    /* renamed from: l, reason: collision with root package name */
    private Set f6156l = new HashSet();

    /* renamed from: s, reason: collision with root package name */
    protected String f6163s = SystemMgr.t();

    /* renamed from: r, reason: collision with root package name */
    protected boolean f6162r = SystemMgr.H();

    public interface Callback {
        void a(State state);
    }

    protected final class H extends Handler {
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 1:
                        QSTile.this.Z((Callback) message.obj);
                        return;
                    case 2:
                        QSTile.this.f6159o = true;
                        QSTile.this.T();
                        return;
                    case 3:
                        QSTile.this.a0();
                        return;
                    case 4:
                        QSTile.this.W();
                        return;
                    case 5:
                        QSTile.this.X(message.obj);
                        return;
                    case 6:
                    case 8:
                    case 9:
                    default:
                        throw new IllegalArgumentException("Unknown msg : " + message.what);
                    case 7:
                        QSTile.this.d0(message.arg1);
                        return;
                    case 10:
                        QSTile.this.U();
                        return;
                    case 11:
                        QSTile.this.R();
                        return;
                    case 12:
                        QSTile.this.Y((Callback) message.obj);
                        return;
                    case 13:
                        QSTile.this.V(((Boolean) message.obj).booleanValue());
                        return;
                    case 14:
                        QSTile.this.Q();
                        return;
                }
            } catch (Throwable th) {
                GaLog.l(QSTile.this.f6151c, "Error in " + ((String) null), th);
            }
        }

        private H(Looper looper) {
            super(looper);
        }
    }

    public interface Host {

        public interface Callback {
        }

        default IModuleProxy a(Class cls) {
            return null;
        }

        void b();

        Looper c();

        Context getContext();
    }

    public static abstract class Icon {
        public abstract Drawable a(Context context);

        public int hashCode() {
            return Icon.class.hashCode();
        }
    }

    public static class ResourceIcon extends Icon {

        /* renamed from: b, reason: collision with root package name */
        private static final ConcurrentHashMap f6165b = new ConcurrentHashMap();

        /* renamed from: a, reason: collision with root package name */
        protected final int f6166a;

        private ResourceIcon(int i2) {
            this.f6166a = i2;
        }

        public static synchronized Icon b(int i2) {
            Icon icon;
            synchronized (ResourceIcon.class) {
                ConcurrentHashMap concurrentHashMap = f6165b;
                icon = (Icon) concurrentHashMap.get(Integer.valueOf(i2));
                if (icon == null) {
                    icon = new ResourceIcon(i2);
                    concurrentHashMap.put(Integer.valueOf(i2), icon);
                }
            }
            return icon;
        }

        @Override // cn.nubia.gameassist.common.QSTile.Icon
        public Drawable a(Context context) {
            return context.getDrawable(this.f6166a);
        }

        public boolean equals(Object obj) {
            return (obj instanceof ResourceIcon) && ((ResourceIcon) obj).f6166a == this.f6166a;
        }

        public String toString() {
            return String.format("ResourceIcon[resId=0x%08x]", Integer.valueOf(this.f6166a));
        }
    }

    public static class State {

        /* renamed from: b, reason: collision with root package name */
        public Icon f6168b;

        /* renamed from: d, reason: collision with root package name */
        public String f6170d;

        /* renamed from: e, reason: collision with root package name */
        public Icon f6171e;

        /* renamed from: f, reason: collision with root package name */
        public String f6172f;

        /* renamed from: g, reason: collision with root package name */
        public String f6173g;

        /* renamed from: i, reason: collision with root package name */
        public boolean f6175i;

        /* renamed from: k, reason: collision with root package name */
        public String f6177k;

        /* renamed from: l, reason: collision with root package name */
        public boolean f6178l;

        /* renamed from: a, reason: collision with root package name */
        public boolean f6167a = true;

        /* renamed from: c, reason: collision with root package name */
        public String f6169c = "";

        /* renamed from: h, reason: collision with root package name */
        public boolean f6174h = true;

        /* renamed from: j, reason: collision with root package name */
        public boolean f6176j = true;

        public boolean a(State state) {
            if (state == null || !state.getClass().equals(getClass())) {
                throw new IllegalArgumentException();
            }
            boolean z = (this.f6175i == state.f6175i && this.f6167a == state.f6167a && this.f6176j == state.f6176j && Objects.equals(this.f6168b, state.f6168b) && Objects.equals(this.f6169c, state.f6169c) && Objects.equals(this.f6170d, state.f6170d) && Objects.equals(this.f6171e, state.f6171e) && Objects.equals(this.f6172f, state.f6172f) && Objects.equals(this.f6173g, state.f6173g) && this.f6174h == state.f6174h) ? false : true;
            state.f6175i = this.f6175i;
            state.f6167a = this.f6167a;
            state.f6176j = this.f6176j;
            state.f6168b = this.f6168b;
            state.f6169c = this.f6169c;
            state.f6170d = this.f6170d;
            state.f6171e = this.f6171e;
            state.f6172f = this.f6172f;
            state.f6173g = this.f6173g;
            state.f6174h = this.f6174h;
            state.f6177k = this.f6177k;
            return z;
        }

        protected StringBuilder b() {
            StringBuilder sb = new StringBuilder(getClass().getSimpleName());
            sb.append('[');
            sb.append("value=");
            sb.append(this.f6175i);
            sb.append(",visible=");
            sb.append(this.f6167a);
            sb.append(",iconGame=");
            sb.append(this.f6168b);
            sb.append(",label=");
            sb.append(this.f6169c);
            sb.append(",introduction=");
            sb.append(this.f6170d);
            sb.append(",iconSettings=");
            sb.append(this.f6171e);
            sb.append(",spec=");
            sb.append(this.f6177k);
            sb.append(",isClickable=");
            sb.append(this.f6176j);
            sb.append(",contentDescription=");
            sb.append(this.f6172f);
            sb.append(",dualLabelContentDescription=");
            sb.append(this.f6173g);
            sb.append(",autoMirrorDrawable=");
            sb.append(this.f6174h);
            sb.append(']');
            return sb;
        }

        public String toString() {
            return b().toString();
        }
    }

    protected QSTile(Host host) {
        this.f6152h = host;
        Context context = host.getContext();
        this.f6153i = context;
        this.f6154j = new H(host.c());
        this.f6157m = new State();
        this.f6158n = new State();
        this.f6160p = (PerformanceViewController) GameAssistWindowManager.O(context).T(PerformanceViewController.class);
        RecycleWatch.i(this);
        RotationMgr.e(context).c(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        GaLog.j(this.f6151c, "handleClearCallback");
        this.f6156l.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        if (TextUtils.isEmpty(N())) {
            GaLog.g(this.f6151c, "you must implement getTileLabel() to track event");
        } else {
            NubiaTrackManager.p().t(N());
        }
        S();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y(Callback callback) {
        if (this.f6156l.contains(callback)) {
            this.f6156l.remove(callback);
            GaLog.e(this.f6151c, "handleRemoveCallback " + callback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z(Callback callback) {
        if (this.f6156l.contains(callback)) {
            return;
        }
        GaLog.j(this.f6151c, "handleSetCallback " + callback);
        this.f6156l.add(callback);
        X(null);
    }

    public void D(Callback callback) {
        this.f6154j.obtainMessage(1, callback).sendToTarget();
    }

    public QSTile E() {
        GaLog.j(this.f6151c, "clearCallback");
        this.f6154j.obtainMessage(14).sendToTarget();
        return this;
    }

    public void F() {
        GaLog.a(this.f6151c, "click() : " + N());
        this.f6154j.sendEmptyMessage(2);
    }

    public void G() {
        GaLog.a(this.f6151c, "clickSettings() : " + N());
        this.f6154j.sendEmptyMessage(3);
    }

    @Override // java.lang.Comparable
    /* renamed from: H, reason: merged with bridge method [inline-methods] */
    public int compareTo(QSTile qSTile) {
        if (!ZteFeature.isSupportSort()) {
            return qSTile.L() - L();
        }
        int z = SharedPreferencesUtil.k(this.f6153i).z(this.f6163s);
        if (z == 1) {
            return Long.compare(qSTile.K(), K());
        }
        if (z != 2) {
            return 0;
        }
        return Integer.compare(qSTile.P(), P());
    }

    public void I() {
        this.f6154j.sendEmptyMessage(10);
    }

    public void J(PrintWriter printWriter, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(this.f6151c);
        sb.append(":\n");
        sb.append(str);
        sb.append("mState=");
        sb.append(this.f6157m.toString());
        sb.append("\n");
        sb.append(str);
        sb.append("mTmpState=");
        sb.append(this.f6158n.toString());
        sb.append("\n");
        sb.append(str);
        sb.append("callbackSize=");
        sb.append(this.f6156l.size());
        sb.append("\n");
        printWriter.print(sb);
    }

    protected long K() {
        return SharedPreferencesUtil.k(this.f6153i).v(this.f6163s, this.f6161q);
    }

    protected int L() {
        return 0;
    }

    public State M() {
        return this.f6157m;
    }

    public String N() {
        String str;
        State state = this.f6157m;
        return (state == null || (str = state.f6169c) == null) ? "" : str;
    }

    public String O() {
        return this.f6161q;
    }

    protected int P() {
        return SharedPreferencesUtil.k(this.f6153i).w(this.f6163s, this.f6161q);
    }

    protected void R() {
        this.f6158n = new State();
        this.f6157m = new State();
    }

    protected boolean S() {
        this.f6163s = SystemMgr.t();
        this.f6162r = SystemMgr.H();
        GameAssistWindowManager.O(this.f6153i).B0();
        if (TilesUtil.o(this)) {
            if (Utils.Q(this.f6153i)) {
                Toast.makeText(this.f6153i, R.string.picture_unable_in_split_toast, 0).show();
                return true;
            }
            if (Utils.P(this.f6153i)) {
                Toast.makeText(this.f6153i, R.string.pls_exit_small_window, 0).show();
                this.f6152h.b();
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void U() {
        GaLog.e(this.f6151c, "handleDestroy " + this);
        c(false);
        if (this instanceof GameMonitor.Callback) {
            SystemMgr.y(this.f6153i).i((GameMonitor.Callback) this);
        }
        RotationMgr.e(this.f6153i).p(this);
        this.f6156l.clear();
    }

    public void V(boolean z) {
    }

    protected void W() {
    }

    protected void X(Object obj) {
        c0(this.f6158n, obj);
        if (this.f6158n.a(this.f6157m)) {
            b0();
        }
    }

    protected boolean a0() {
        this.f6163s = SystemMgr.t();
        this.f6162r = SystemMgr.H();
        return false;
    }

    protected void b0() {
        boolean t0 = t0();
        Iterator it = this.f6156l.iterator();
        while (it.hasNext()) {
            ((Callback) it.next()).a(this.f6157m);
        }
        this.f6159o = this.f6159o && t0;
    }

    protected void c0(State state, Object obj) {
        this.f6163s = SystemMgr.t();
        this.f6162r = SystemMgr.H();
        this.t = f0();
        if (ZteFeature.isSupportSort() && this.u) {
            GaLog.e(this.f6151c, "Tile enable " + this.t + " state.value " + state.f6175i);
            if (!this.t && h0()) {
                v0();
            } else if (this.t && !state.f6175i) {
                x0();
            }
        }
        this.u = false;
        state.f6175i = this.t;
    }

    protected void d0(int i2) {
        X(null);
    }

    protected void e0(Object obj) {
        X(obj);
    }

    public boolean f0() {
        return false;
    }

    protected boolean g0() {
        return FoldMgr.f() && FoldMgr.c().e();
    }

    protected boolean h0() {
        return SharedPreferencesUtil.k(this.f6153i).E(this.f6163s, this.f6161q);
    }

    protected boolean i0(IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        if (PluginConfig.l(this.f6153i, this.f6161q, SystemMgr.t())) {
            return true;
        }
        GameAgentUtil.l(this.f6153i, iGameAssistClientCallback, inMsg, false);
        ToastUtil.a(this.f6153i.getString(R.string.small_window_not_support));
        return false;
    }

    public void j0() {
        this.f6154j.sendEmptyMessage(4);
    }

    protected void k0(String str, String str2, IGameAssistClientCallback iGameAssistClientCallback, InMsg inMsg) {
        this.u = true;
        this.t = f0();
        GaLog.e(this.f6151c, "cmd" + str + " enable " + this.t);
    }

    protected void l0(TileHost tileHost) {
    }

    public void m0(boolean z) {
        this.f6162r = SystemMgr.H();
        this.f6154j.obtainMessage(13, Boolean.valueOf(z)).sendToTarget();
    }

    protected void n0(TileHost tileHost) {
    }

    public final void o0() {
        p0(null);
    }

    @Override // com.zte.gameassist.common.RotationMgr.Callback
    /* renamed from: onRotationChanged */
    public void y(int i2) {
        this.f6162r = SystemMgr.H();
    }

    protected final void p0(Object obj) {
        this.f6154j.obtainMessage(5, obj).sendToTarget();
    }

    public void q0(Callback callback) {
        this.f6154j.obtainMessage(12, callback).sendToTarget();
    }

    public void r0(boolean z) {
        Settings.Global.putInt(this.f6153i.getApplicationContext().getContentResolver(), "game_mode_floating_window_show", !z ? 1 : 0);
        GaLog.e(this.f6151c, "setFloatingWindowShow " + (!z ? 1 : 0));
    }

    public void s0(String str) {
        this.f6161q = str;
        this.f6157m.f6177k = str;
        this.f6158n.f6177k = str;
    }

    protected boolean t0() {
        return false;
    }

    public String toString() {
        return "QSTile{" + this.f6151c + NubiaTextClock.QUOTE + ", mState=" + this.f6157m + NubiaTextClock.QUOTE + ", mTmpState=" + this.f6158n + '}';
    }

    protected boolean u0() {
        if (!SystemMgr.F()) {
            return false;
        }
        ToastUtil.a(this.f6153i.getString(R.string.ic_qs_red_magic_broadcast_no_support_game_is_clone_text));
        GaLog.a(this.f6151c, "isCurClone");
        return true;
    }

    protected void v0() {
        SharedPreferencesUtil.k(this.f6153i).c0(this.f6163s, this.f6161q, System.currentTimeMillis());
    }

    protected void w0() {
        SharedPreferencesUtil.k(this.f6153i).d0(this.f6163s, this.f6161q, System.currentTimeMillis());
    }

    protected void x0() {
        if (ZteFeature.isSupportSort()) {
            if (!this.t || !this.f6162r) {
                v0();
            } else {
                y0();
                w0();
            }
        }
    }

    protected void y0() {
        SharedPreferencesUtil.k(this.f6153i).e0(this.f6163s, this.f6161q, P() + 1);
    }
}
