package com.zte.aivibrate.scene.sgame;

import cn.nubia.gamelab.IToyService;
import com.zte.aivibrate.scene.GameLabBaseScene;
import com.zte.aivibrate.scene.I4DVibrateScene;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class SGameGameLabScene extends GameLabBaseScene {

    /* renamed from: m, reason: collision with root package name */
    public boolean f16275m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f16276n;

    /* renamed from: o, reason: collision with root package name */
    private long f16277o;

    /* renamed from: p, reason: collision with root package name */
    private int f16278p;

    /* renamed from: q, reason: collision with root package name */
    private int f16279q;

    /* renamed from: r, reason: collision with root package name */
    private long f16280r;

    public SGameGameLabScene(IToyService iToyService, I4DVibrateScene i4DVibrateScene) {
        super(iToyService, i4DVibrateScene, "com.tencent.tmgp.sgame");
        this.f16275m = false;
        this.f16276n = false;
        this.f16277o = 0L;
        this.f16278p = 0;
        this.f16279q = 0;
        this.f16280r = 0L;
    }

    private void m() {
        this.f16277o = 0L;
        this.f16278p = 0;
        this.f16279q = 0;
        this.f16276n = false;
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void b(PrintWriter printWriter) {
        super.b(printWriter);
        printWriter.println(".SGameScene");
        printWriter.println("  is game end:" + this.f16275m);
        printWriter.println("  init score:" + this.f16276n);
        printWriter.println("  game start time:" + this.f16280r);
        printWriter.println("  last kill time:" + this.f16277o);
        printWriter.println("  current kill num:" + this.f16278p);
        printWriter.println("  current dead num:" + this.f16279q);
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void i() {
        super.i();
        k(2748779201099L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x014d, code lost:
    
        if ((r3 - r0) < 7000) goto L40;
     */
    @Override // com.zte.aivibrate.scene.GameLabBaseScene
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void l(android.os.Bundle r12) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.aivibrate.scene.sgame.SGameGameLabScene.l(android.os.Bundle):void");
    }
}
