package com.zte.aivibrate.processor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import cn.nubia.gamelab.IToyService;
import com.zte.aivibrate.DetectStrategyFactory;
import com.zte.aivibrate.Vibrate4DController;
import com.zte.aivibrate.scene.GameLabBaseScene;
import com.zte.aivibrate.scene.I4DVibrateScene;
import com.zte.aivibrate.scene.VibrateSceneState;
import com.zte.aivibrate.util.AIVibrateLog;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class GameLabDataProcessor implements I4DVibrateScene {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16208a;

    /* renamed from: b, reason: collision with root package name */
    private IToyService f16209b;

    /* renamed from: c, reason: collision with root package name */
    private GameLabBaseScene f16210c;

    /* renamed from: d, reason: collision with root package name */
    private Handler f16211d;

    /* renamed from: e, reason: collision with root package name */
    private final Vibrate4DController f16212e;

    /* renamed from: f, reason: collision with root package name */
    private int f16213f = 0;

    /* renamed from: g, reason: collision with root package name */
    private int f16214g = 0;

    /* renamed from: h, reason: collision with root package name */
    private boolean f16215h = false;

    /* renamed from: i, reason: collision with root package name */
    private final ServiceConnection f16216i = new ServiceConnection() { // from class: com.zte.aivibrate.processor.GameLabDataProcessor.2
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            GameLabDataProcessor.this.f16209b = IToyService.Stub.asInterface(iBinder);
            AIVibrateLog.b(".GameLab", "bindGameLab onServiceConnected " + GameLabDataProcessor.this.f16213f + "," + GameLabDataProcessor.this.f16214g);
            if (GameLabDataProcessor.this.f16211d != null && GameLabDataProcessor.this.f16211d.hasMessages(1)) {
                GameLabDataProcessor.this.f16211d.removeMessages(1);
                GameLabDataProcessor.this.f16211d.sendEmptyMessage(1);
            }
            GameLabDataProcessor.this.f16213f = 0;
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            AIVibrateLog.b(".GameLab", "bindGameLab onServiceDisconnected");
            GameLabDataProcessor.this.f16209b = null;
            GameLabDataProcessor.this.f16215h = false;
            if (GameLabDataProcessor.this.f16210c != null) {
                AIVibrateLog.b(".GameLab", "service disconnected end scene ");
                GameLabDataProcessor.this.f16210c.c();
                GameLabDataProcessor.this.f16210c = null;
            }
            if (GameLabDataProcessor.this.f16211d != null) {
                GameLabDataProcessor.this.f16211d.removeMessages(1);
                GameLabDataProcessor.this.u();
            }
        }
    };

    public GameLabDataProcessor(Context context, Vibrate4DController vibrate4DController) {
        this.f16208a = context;
        this.f16212e = vibrate4DController;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        if (this.f16215h) {
            AIVibrateLog.b(".GameLab", "has detected");
            return;
        }
        IToyService iToyService = this.f16209b;
        if (iToyService != null) {
            GameLabBaseScene a2 = DetectStrategyFactory.a(this.f16208a, iToyService, this, this.f16212e.q(), this.f16212e.r());
            this.f16210c = a2;
            if (a2 != null) {
                a2.i();
                this.f16215h = true;
                return;
            }
            return;
        }
        AIVibrateLog.b(".GameLab", "toy service is null,start rebind " + this.f16213f + "," + this.f16214g);
        u();
    }

    @Override // com.zte.aivibrate.scene.I4DVibrateScene
    public void b(VibrateSceneState vibrateSceneState) {
        if (!this.f16212e.A()) {
            AIVibrateLog.d(".GameLab", "not in the game scene, do not receive scene " + vibrateSceneState.d());
            return;
        }
        this.f16212e.E(vibrateSceneState);
        if (vibrateSceneState == VibrateSceneState.GAME_START || vibrateSceneState == VibrateSceneState.YS_ENTER_GAMING) {
            this.f16212e.H(true, getClass().getSimpleName());
        } else if (vibrateSceneState == VibrateSceneState.GAME_END) {
            this.f16212e.H(false, getClass().getSimpleName());
        }
    }

    protected void l() {
        try {
            AIVibrateLog.b(".GameLab", "bindGameLab");
            Intent intent = new Intent("cn.nubia.gamelab.toy");
            intent.setPackage("cn.nubia.gamelab");
            if (this.f16208a.bindService(intent, this.f16216i, 1)) {
                AIVibrateLog.b(".GameLab", "bindGameLab success");
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void m() {
        w();
    }

    public void n(PrintWriter printWriter) {
        printWriter.println(".GameLab");
        printWriter.println("  service:" + this.f16209b);
        printWriter.println("  has detected:" + this.f16215h);
        printWriter.println("  re bind count:" + this.f16213f);
        printWriter.println("  re bind total count:" + this.f16214g);
        GameLabBaseScene gameLabBaseScene = this.f16210c;
        if (gameLabBaseScene != null) {
            gameLabBaseScene.b(printWriter);
        }
    }

    public void o() {
        AIVibrateLog.b(".GameLab", "endDataProcessor " + this.f16215h);
        this.f16211d.removeMessages(1);
        if (this.f16215h) {
            this.f16215h = false;
            this.f16213f = 0;
            if (this.f16210c != null) {
                AIVibrateLog.b(".GameLab", "end scene " + this.f16212e.q());
                this.f16210c.c();
                this.f16210c = null;
            }
        }
    }

    public boolean q() {
        return this.f16215h;
    }

    public void r() {
        v();
    }

    public void s() {
        o();
    }

    public void t() {
        l();
        this.f16211d = new Handler(this.f16212e.r()) { // from class: com.zte.aivibrate.processor.GameLabDataProcessor.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what != 1) {
                    return;
                }
                GameLabDataProcessor.this.p();
            }
        };
    }

    public void u() {
        if (this.f16213f >= 3 || this.f16214g >= 1000 || !this.f16212e.m()) {
            return;
        }
        this.f16213f++;
        this.f16214g++;
        l();
        this.f16211d.removeMessages(1);
        this.f16211d.sendEmptyMessageDelayed(1, 1000L);
    }

    public void v() {
        AIVibrateLog.b(".GameLab", "startDataProcessor " + this.f16215h);
        if (this.f16215h) {
            return;
        }
        this.f16211d.removeMessages(1);
        this.f16211d.sendEmptyMessage(1);
    }

    protected void w() {
        try {
            if (this.f16209b != null) {
                this.f16208a.unbindService(this.f16216i);
                this.f16209b = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
