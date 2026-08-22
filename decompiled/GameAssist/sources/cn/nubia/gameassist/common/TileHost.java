package cn.nubia.gameassist.common;

import android.content.Context;
import android.content.pm.LauncherActivityInfo;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.text.TextUtils;
import cn.nubia.componentcenter.router.Router;
import cn.nubia.componentcenter.service.GameAssistComService;
import cn.nubia.gameassist.common.QSTile;
import cn.nubia.gameassist.common.TileHost;
import cn.nubia.gameassist.pips.PipFactory;
import cn.nubia.gameassist.utils.TilesUtil;
import cn.nubia.gameassist.utils.Utils;
import com.zte.gameassist.aiagent.GameAgentUtil;
import com.zte.gameassist.aiagent.IGameAssistClientCallback;
import com.zte.gameassist.aiagent.bean.InMsg;
import com.zte.gameassist.common.EventListener;
import com.zte.gameassist.common.EventListenerMgr;
import com.zte.gameassist.common.IModuleProxy;
import com.zte.gameassist.common.ThreadManager;
import com.zte.gameassist.config.ZteFeature;
import com.zte.gameassist.utils.GaLog;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/* loaded from: classes.dex */
public class TileHost implements QSTile.Host {

    /* renamed from: a, reason: collision with root package name */
    private final Context f6189a;

    /* renamed from: b, reason: collision with root package name */
    private final IHostPanel f6190b;

    /* renamed from: f, reason: collision with root package name */
    private final Looper f6194f;

    /* renamed from: g, reason: collision with root package name */
    private final Handler f6195g;

    /* renamed from: i, reason: collision with root package name */
    private final EventListener f6197i;

    /* renamed from: c, reason: collision with root package name */
    private final Map f6191c = Collections.synchronizedMap(new LinkedHashMap());

    /* renamed from: d, reason: collision with root package name */
    private final Map f6192d = Collections.synchronizedMap(new LinkedHashMap());

    /* renamed from: e, reason: collision with root package name */
    private final Map f6193e = Collections.synchronizedMap(new LinkedHashMap());

    /* renamed from: h, reason: collision with root package name */
    private List f6196h = new ArrayList();

    /* renamed from: cn.nubia.gameassist.common.TileHost$1, reason: invalid class name */
    class AnonymousClass1 implements EventListener {
        AnonymousClass1() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(Object[] objArr) {
            QSTile qSTile;
            QSTile qSTile2;
            String str = (String) objArr[4];
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String str2 = (String) objArr[0];
            String str3 = (String) objArr[1];
            IGameAssistClientCallback iGameAssistClientCallback = (IGameAssistClientCallback) objArr[2];
            InMsg inMsg = (InMsg) objArr[3];
            if (TileHost.this.f6191c.containsKey(str) && (qSTile2 = (QSTile) TileHost.this.f6191c.get(str)) != null) {
                qSTile2.k0(str2, str3, iGameAssistClientCallback, inMsg);
            } else if (!TileHost.this.f6193e.containsKey(str) || (qSTile = (QSTile) TileHost.this.f6193e.get(str)) == null) {
                GameAgentUtil.n(TileHost.this.f6189a, iGameAssistClientCallback, inMsg);
            } else {
                qSTile.k0(str2, str3, iGameAssistClientCallback, inMsg);
            }
        }

        @Override // com.zte.gameassist.common.EventListener
        public void a(int i2, final Object... objArr) {
            if (i2 == 4) {
                TileHost.this.f6195g.post(new Runnable() { // from class: cn.nubia.gameassist.common.p
                    @Override // java.lang.Runnable
                    public final void run() {
                        TileHost.AnonymousClass1.this.c(objArr);
                    }
                });
            }
        }
    }

    public TileHost(Context context, IHostPanel iHostPanel) {
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        this.f6197i = anonymousClass1;
        this.f6189a = context;
        this.f6190b = iHostPanel;
        Looper i2 = ThreadManager.c().i();
        this.f6194f = i2;
        this.f6195g = new Handler(i2);
        EventListenerMgr.b(anonymousClass1, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void q(List list, String str, QSTile qSTile) {
        if (list.contains(str)) {
            return;
        }
        qSTile.I();
        GaLog.e("TileHost", "destroy " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void r(LinkedHashMap linkedHashMap, String str, QSTile qSTile) {
        if (linkedHashMap.get(str) == null) {
            qSTile.n0(this);
            qSTile.I();
            GaLog.e("TileHost", "onRemoveFromHost: " + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void s(String str, QSTile qSTile) {
        if (this.f6193e.get(str) == null) {
            qSTile.l0(this);
            GaLog.e("TileHost", "onAddToHost: " + str);
        }
    }

    private Collection u() {
        final ArrayList<String> g2 = TilesUtil.g(this.f6189a);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : g2) {
            try {
                if (this.f6191c.get(str) != null) {
                    linkedHashMap.put(str, (QSTile) this.f6191c.get(str));
                } else {
                    QSTile l2 = l(str);
                    if (l2 != null) {
                        l2.s0(str);
                        linkedHashMap.put(str, l2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                GaLog.l("TileHost", "Error creating gameTile for spec: " + str, th);
            }
        }
        this.f6191c.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.common.m
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TileHost.q(g2, (String) obj, (QSTile) obj2);
            }
        });
        this.f6191c.clear();
        this.f6191c.putAll(linkedHashMap);
        return this.f6191c.values();
    }

    private Map v() {
        List<LauncherActivityInfo> j2 = TilesUtil.j(this.f6189a);
        ArrayList k2 = TilesUtil.k(this.f6189a);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (LauncherActivityInfo launcherActivityInfo : j2) {
            String str = launcherActivityInfo.getApplicationInfo().packageName;
            if (!p(k2, str)) {
                try {
                    if (this.f6192d.get(str) != null) {
                        linkedHashMap.put(str, (QSTile) this.f6192d.get(str));
                    } else {
                        GaLog.a("TileHost", "create pip: " + str);
                        QSTile k3 = k(launcherActivityInfo.getLabel().toString(), str);
                        if (k3 != null) {
                            k3.s0(str);
                            linkedHashMap.put(str, k3);
                        } else {
                            GaLog.a("TileHost", "create pip fail " + str);
                        }
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    GaLog.l("TileHost", "create pip error " + str, th);
                }
            }
        }
        this.f6192d.clear();
        this.f6192d.putAll(linkedHashMap);
        GaLog.a("TileHost", "refresh pips " + this.f6192d.size());
        return this.f6192d;
    }

    private Collection w(boolean z) {
        String j2 = Utils.j();
        List<String> r2 = Utils.r(this.f6189a);
        GaLog.e("TileHost", "refreshPluginTiles(): size = " + r2.size() + " , curApp = " + j2 + " fromStart " + z);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (String str : r2) {
            GaLog.e("TileHost", "Creating PluginTile: " + str);
            try {
                if (this.f6193e.get(str) != null) {
                    QSTile qSTile = (QSTile) this.f6193e.get(str);
                    if (!j2.equals(qSTile.f6163s)) {
                        qSTile.f6163s = j2;
                    }
                    linkedHashMap.put(str, qSTile);
                    if (z) {
                        y(qSTile);
                    }
                } else {
                    QSTile l2 = l(str);
                    if (l2 != null) {
                        if (!j2.equals(l2.f6163s)) {
                            l2.f6163s = j2;
                        }
                        l2.s0(str);
                        linkedHashMap.put(str, l2);
                        if (z) {
                            y(l2);
                        }
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
                GaLog.l("TileHost", "Error creating PluginTile for spec: " + str, th);
            }
        }
        GaLog.e("TileHost", "Creating newTiles: " + linkedHashMap.values().size());
        this.f6193e.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.common.n
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TileHost.this.r(linkedHashMap, (String) obj, (QSTile) obj2);
            }
        });
        linkedHashMap.forEach(new BiConsumer() { // from class: cn.nubia.gameassist.common.o
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                TileHost.this.s((String) obj, (QSTile) obj2);
            }
        });
        this.f6193e.clear();
        this.f6193e.putAll(linkedHashMap);
        if (!ZteFeature.isSupportSort()) {
            return this.f6193e.values();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.f6193e.values());
        Collections.sort(arrayList);
        return new ArrayList(arrayList);
    }

    private void y(QSTile qSTile) {
        if (ZteFeature.isSupportSort() && qSTile.h0()) {
            GaLog.e("TileHost", "update " + qSTile.O() + " usage time to " + (qSTile.P() + 1));
            qSTile.y0();
        }
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public IModuleProxy a(Class cls) {
        return ((GameAssistComService) Router.getInstance().getService(GameAssistComService.class.getSimpleName())).a(cls);
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public void b() {
        this.f6190b.g0("tilehost");
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public Looper c() {
        return this.f6194f;
    }

    @Override // cn.nubia.gameassist.common.QSTile.Host
    public Context getContext() {
        return this.f6189a;
    }

    protected QSTile k(String str, String str2) {
        return PipFactory.a(str, str2, this);
    }

    protected QSTile l(String str) {
        return TileFactory.a(str, this);
    }

    public Collection m() {
        return u();
    }

    public Map n() {
        return v();
    }

    public Collection o(boolean z) {
        return w(z);
    }

    public boolean p(ArrayList arrayList, String str) {
        if (!arrayList.contains(str)) {
            return false;
        }
        if (!TilesUtil.n(this.f6189a, str)) {
            return true;
        }
        return arrayList.contains(str + "#999");
    }

    public void t(boolean z) {
        Iterator it = this.f6191c.entrySet().iterator();
        while (it.hasNext()) {
            ((QSTile) ((Map.Entry) it.next()).getValue()).m0(z);
        }
        Iterator it2 = this.f6192d.entrySet().iterator();
        while (it2.hasNext()) {
            ((QSTile) ((Map.Entry) it2.next()).getValue()).m0(z);
        }
    }

    public void x(boolean z) {
        try {
            Iterator it = this.f6191c.entrySet().iterator();
            while (it.hasNext()) {
                ((QSTile) ((Map.Entry) it.next()).getValue()).c(z);
            }
            Iterator it2 = this.f6193e.entrySet().iterator();
            while (it2.hasNext()) {
                ((QSTile) ((Map.Entry) it2.next()).getValue()).c(z);
            }
            Iterator it3 = this.f6192d.entrySet().iterator();
            while (it3.hasNext()) {
                ((QSTile) ((Map.Entry) it3.next()).getValue()).c(z);
            }
            Settings.Global.putInt(this.f6189a.getContentResolver(), "controlcenter_expand_status", z ? 1 : 0);
        } catch (ConcurrentModificationException e2) {
            e2.printStackTrace();
        }
    }
}
