package com.zte.aivibrate.scene;

import android.os.Bundle;
import android.os.RemoteException;
import cn.nubia.gamelab.IToyCallback;
import cn.nubia.gamelab.IToyService;
import com.zte.aivibrate.util.AIVibrateLog;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public abstract class GameLabBaseScene extends BaseScene {

    /* renamed from: l, reason: collision with root package name */
    public static final Integer f16241l = 2;

    /* renamed from: i, reason: collision with root package name */
    protected final IToyService f16242i;

    /* renamed from: j, reason: collision with root package name */
    private final String f16243j;

    /* renamed from: k, reason: collision with root package name */
    private final IToyCallback f16244k;

    public GameLabBaseScene(IToyService iToyService, I4DVibrateScene i4DVibrateScene, String str) {
        super(i4DVibrateScene);
        this.f16244k = new IToyCallback.Stub() { // from class: com.zte.aivibrate.scene.GameLabBaseScene.1
            @Override // cn.nubia.gamelab.IToyCallback
            public void notifyEvent(List<Bundle> list) {
                if (list == null || list.isEmpty()) {
                    AIVibrateLog.c("receiver null data");
                    return;
                }
                synchronized (this) {
                    try {
                        Iterator<Bundle> it = list.iterator();
                        while (it.hasNext()) {
                            GameLabBaseScene.this.l(it.next());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        };
        this.f16242i = iToyService;
        this.f16243j = str;
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void b(PrintWriter printWriter) {
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void c() {
        try {
            if (this.f16242i != null) {
                AIVibrateLog.a("un register callback " + this.f16242i + ",this:" + this + "," + this.f16243j);
                this.f16242i.unregisterCallback(this.f16243j, this.f16244k);
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.zte.aivibrate.scene.BaseScene
    public void i() {
        c();
    }

    protected void k(long j2) {
        try {
            if (this.f16242i != null) {
                AIVibrateLog.a("register callback " + this.f16242i + ",this:" + this + "," + this.f16243j);
                this.f16242i.registerCallback(this.f16243j, this.f16244k, j2);
            } else {
                AIVibrateLog.c("binder is null");
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
        }
    }

    protected void l(Bundle bundle) {
    }
}
