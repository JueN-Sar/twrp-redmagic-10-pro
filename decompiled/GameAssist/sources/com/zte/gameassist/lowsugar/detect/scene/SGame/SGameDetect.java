package com.zte.gameassist.lowsugar.detect.scene.SGame;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.MotionEvent;
import cn.nubia.gamelab.IToyCallback;
import cn.nubia.gamelab.IToyService;
import cn.nubia.gamepi.IGameSceneCallback;
import cn.nubia.gamepi.IGameSceneInterface;
import com.zte.gameassist.lowsugar.detect.ISceneDetect;
import com.zte.gameassist.utils.GaLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class SGameDetect implements ISceneDetect {

    /* renamed from: k, reason: collision with root package name */
    private static volatile boolean f16883k = false;

    /* renamed from: l, reason: collision with root package name */
    private static int f16884l = 3;

    /* renamed from: a, reason: collision with root package name */
    private final Context f16885a;

    /* renamed from: b, reason: collision with root package name */
    private IToyService f16886b;

    /* renamed from: c, reason: collision with root package name */
    private int f16887c;

    /* renamed from: d, reason: collision with root package name */
    private SGame f16888d;

    /* renamed from: e, reason: collision with root package name */
    private ISceneDetect.SceneDetectCallback f16889e;

    /* renamed from: h, reason: collision with root package name */
    private IGameSceneInterface f16892h;

    /* renamed from: f, reason: collision with root package name */
    private final ServiceConnection f16890f = new ServiceConnection() { // from class: com.zte.gameassist.lowsugar.detect.scene.SGame.SGameDetect.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SGameDetect.this.f16886b = IToyService.Stub.asInterface(iBinder);
            GaLog.a("LowSugarGameplay.SGameDetect", "bindGameLab onServiceConnected");
            if (SGameDetect.this.f16886b == null) {
                return;
            }
            try {
                SGameDetect.this.f16886b.registerCallback("com.tencent.tmgp.sgame", SGameDetect.this.f16891g, 2748779069443L);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GaLog.a("LowSugarGameplay.SGameDetect", "bindGameLab onServiceDisconnected");
            SGameDetect.this.f16886b = null;
        }
    };

    /* renamed from: g, reason: collision with root package name */
    private final IToyCallback f16891g = new IToyCallback.Stub() { // from class: com.zte.gameassist.lowsugar.detect.scene.SGame.SGameDetect.2
        private void unpackData(Bundle bundle) {
            if (!bundle.containsKey("time") || !bundle.containsKey("flag")) {
                GaLog.a("LowSugarGameplay.SGameDetect", "no time or flag");
                return;
            }
            bundle.getLong("time");
            long j2 = bundle.getLong("flag");
            GaLog.a("LowSugarGameplay.SGameDetect", "unpackSGameData: " + j2 + ",bundle:" + bundle);
            if (j2 == 2748779069441L) {
                GaLog.a("LowSugarGameplay.SGameDetect", "start sgame game");
                SGameDetect.f16883k = true;
            } else if (j2 == 2748779069442L) {
                SGameDetect.f16883k = false;
                SGameDetect.this.f16889e.b();
            }
        }

        @Override // cn.nubia.gamelab.IToyCallback
        public void notifyEvent(List<Bundle> list) {
            if (list == null || list.isEmpty()) {
                GaLog.b("LowSugarGameplay.SGameDetect", "receiver null data");
                return;
            }
            synchronized (this) {
                try {
                    Iterator<Bundle> it = list.iterator();
                    while (it.hasNext()) {
                        unpackData(it.next());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    };

    /* renamed from: i, reason: collision with root package name */
    private final IGameSceneCallback f16893i = new IGameSceneCallback.Stub() { // from class: com.zte.gameassist.lowsugar.detect.scene.SGame.SGameDetect.3
        @Override // cn.nubia.gamepi.IGameSceneCallback
        public void onSceneChange(String str, String str2, String str3) {
            GaLog.a("LowSugarGameplay.SGameDetect", "tgpa onSceneChange packageName = " + str + ", key = " + str2 + ", value = " + str3);
            if ("4".equals(str2) && "4".equals(str3)) {
                GaLog.a("LowSugarGameplay.SGameDetect", "tgpa onSceneChange in sagme dating");
                synchronized (this) {
                    SGameDetect.this.f16889e.a(4);
                }
            }
        }
    };

    /* renamed from: j, reason: collision with root package name */
    private final ServiceConnection f16894j = new ServiceConnection() { // from class: com.zte.gameassist.lowsugar.detect.scene.SGame.SGameDetect.4
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GaLog.a("LowSugarGameplay.SGameDetect", "tgpa Connected");
            SGameDetect.this.f16892h = IGameSceneInterface.Stub.asInterface(iBinder);
            try {
                SGameDetect.this.f16892h.setCallback(null, null, SGameDetect.this.f16893i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            GaLog.a("LowSugarGameplay.SGameDetect", "tgpa Disconnected");
            SGameDetect.this.f16892h = null;
        }
    };

    public SGameDetect(Context context) {
        this.f16885a = context;
        this.f16888d = new SGame(context);
    }

    private void r() {
        try {
            GaLog.a("LowSugarGameplay.SGameDetect", "bindGameLab");
            Intent intent = new Intent("cn.nubia.gamelab.toy");
            intent.setPackage("cn.nubia.gamelab");
            if (this.f16885a.bindService(intent, this.f16890f, 1)) {
                GaLog.a("LowSugarGameplay.SGameDetect", "bindGameLab success");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void s() {
        GaLog.a("LowSugarGameplay.SGameDetect", "bind tgpa ");
        Intent intent = new Intent("gamepi.bind.action.gameassist");
        intent.setComponent(new ComponentName("cn.nubia.gamepi", "cn.nubia.gamepi.GamePerformanceService"));
        this.f16885a.bindService(intent, this.f16894j, 1);
    }

    private void t() {
        try {
            if (this.f16886b != null) {
                this.f16885a.unbindService(this.f16890f);
                this.f16886b.unregisterCallback("com.tencent.tmgp.sgame", this.f16891g);
                this.f16886b = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void u() {
        GaLog.a("LowSugarGameplay.SGameDetect", "unbind tgpa ");
        try {
            this.f16885a.unbindService(this.f16894j);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public void a() {
        t();
        u();
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean b(int i2, MotionEvent motionEvent) {
        return this.f16888d.b(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public void c() {
        s();
        r();
        this.f16887c = 0;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean d(int i2, MotionEvent motionEvent) {
        return this.f16888d.l(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public int e(List list, Map map) {
        return this.f16888d.k(list, map);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean f() {
        int i2 = this.f16887c;
        if (i2 < f16884l) {
            this.f16887c = i2 + 1;
            return false;
        }
        this.f16887c = 0;
        return true;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public void g(ISceneDetect.SceneDetectCallback sceneDetectCallback) {
        this.f16889e = sceneDetectCallback;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean h() {
        return f16883k;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public long i() {
        if (this.f16888d.j()) {
            return 500L;
        }
        if (this.f16888d.e() == 6) {
            return 1800L;
        }
        return this.f16888d.e() == 5 ? 1500L : 1000L;
    }
}
