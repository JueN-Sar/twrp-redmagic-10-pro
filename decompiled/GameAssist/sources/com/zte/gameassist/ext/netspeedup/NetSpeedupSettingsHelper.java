package com.zte.gameassist.ext.netspeedup;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
public final class NetSpeedupSettingsHelper {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16670a;

    /* renamed from: b, reason: collision with root package name */
    private final Object f16671b;

    /* renamed from: c, reason: collision with root package name */
    private final CopyOnWriteArrayList f16672c;

    /* renamed from: d, reason: collision with root package name */
    private final Handler f16673d;

    /* renamed from: e, reason: collision with root package name */
    private final AtomicReference f16674e;

    /* renamed from: com.zte.gameassist.ext.netspeedup.NetSpeedupSettingsHelper$1, reason: invalid class name */
    class AnonymousClass1 extends ContentObserver {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ NetSpeedupSettingsHelper f16675a;

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            this.f16675a.g();
        }
    }

    public interface NetSpeedupConfigChangeCallback {
        void a(NetSpeedupConfig netSpeedupConfig);
    }

    private static List c(Map map, Map map2) {
        TreeSet<String> treeSet = new TreeSet();
        treeSet.addAll(map.keySet());
        treeSet.addAll(map2.keySet());
        ArrayList arrayList = new ArrayList();
        for (String str : treeSet) {
            NetSpeedupConfig netSpeedupConfig = (NetSpeedupConfig) map.get(str);
            NetSpeedupConfig netSpeedupConfig2 = (NetSpeedupConfig) map2.get(str);
            if (netSpeedupConfig == null && netSpeedupConfig2 != null) {
                arrayList.add(netSpeedupConfig2.b());
            } else if (netSpeedupConfig != null && netSpeedupConfig2 == null) {
                arrayList.add(NetSpeedupConfig.c(str));
            } else if (netSpeedupConfig != null && netSpeedupConfig2 != null && !m(netSpeedupConfig, netSpeedupConfig2)) {
                arrayList.add(netSpeedupConfig2.b());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void f(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NetSpeedupConfig netSpeedupConfig = (NetSpeedupConfig) it.next();
            Iterator it2 = this.f16672c.iterator();
            while (it2.hasNext()) {
                try {
                    ((NetSpeedupConfigChangeCallback) it2.next()).a(netSpeedupConfig);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private void e(Map map, Map map2) {
        List c2 = c(map, map2);
        if (c2.isEmpty()) {
            return;
        }
        final ArrayList arrayList = new ArrayList(c2);
        this.f16673d.post(new Runnable() { // from class: com.zte.gameassist.ext.netspeedup.a
            @Override // java.lang.Runnable
            public final void run() {
                NetSpeedupSettingsHelper.this.f(arrayList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Map n2;
        Map n3;
        synchronized (this.f16671b) {
            n2 = n((Map) this.f16674e.get());
            k();
            n3 = n((Map) this.f16674e.get());
        }
        e(n2, n3);
    }

    private List h(String str) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            return arrayList;
        }
        Iterator it = p(str).iterator();
        while (it.hasNext()) {
            NetSpeedupConfig i2 = i((String) it.next());
            if (i2 != null) {
                arrayList.add(i2);
            }
        }
        return arrayList;
    }

    private NetSpeedupConfig i(String str) {
        String trim = str.trim();
        if (TextUtils.isEmpty(trim)) {
            return null;
        }
        List o2 = o(trim);
        if (o2.size() != 3) {
            return null;
        }
        String trim2 = ((String) o2.get(0)).trim();
        String str2 = (String) o2.get(1);
        String str3 = (String) o2.get(2);
        if (TextUtils.isEmpty(trim2)) {
            return null;
        }
        boolean equalsIgnoreCase = "Y".equalsIgnoreCase(str2);
        boolean z = (equalsIgnoreCase || "N".equalsIgnoreCase(str2)) ? equalsIgnoreCase : false;
        String a2 = NetSpeedupConfig.a(str3);
        NetSpeedupConfig netSpeedupConfig = new NetSpeedupConfig(trim2);
        netSpeedupConfig.f16668b = z;
        netSpeedupConfig.f16669c = a2;
        return netSpeedupConfig;
    }

    private String j(String str) {
        try {
            return Settings.Global.getString(this.f16670a.getContentResolver(), str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private void k() {
        l(h(j("game_net_speedup_support_packages")));
    }

    private void l(List list) {
        if (list.isEmpty()) {
            this.f16674e.set(Collections.emptyMap());
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            NetSpeedupConfig netSpeedupConfig = (NetSpeedupConfig) it.next();
            linkedHashMap.put(netSpeedupConfig.f16667a, netSpeedupConfig.b());
        }
        this.f16674e.set(linkedHashMap);
    }

    private static boolean m(NetSpeedupConfig netSpeedupConfig, NetSpeedupConfig netSpeedupConfig2) {
        NetSpeedupConfig b2 = netSpeedupConfig.b();
        NetSpeedupConfig b3 = netSpeedupConfig2.b();
        return b2.f16668b == b3.f16668b && b2.f16669c.equals(b3.f16669c);
    }

    private static Map n(Map map) {
        if (map.isEmpty()) {
            return Collections.emptyMap();
        }
        HashMap hashMap = new HashMap(map.size());
        for (Map.Entry entry : map.entrySet()) {
            hashMap.put((String) entry.getKey(), ((NetSpeedupConfig) entry.getValue()).b());
        }
        return hashMap;
    }

    private static List o(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) == ':') {
                arrayList.add(str.substring(i2, i3));
                i2 = i3 + 1;
            }
        }
        arrayList.add(str.substring(i2));
        return arrayList;
    }

    private static List p(String str) {
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            if (str.charAt(i3) == ',') {
                arrayList.add(str.substring(i2, i3));
                i2 = i3 + 1;
            }
        }
        arrayList.add(str.substring(i2));
        return arrayList;
    }
}
