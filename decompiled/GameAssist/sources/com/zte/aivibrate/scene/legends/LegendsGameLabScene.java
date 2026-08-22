package com.zte.aivibrate.scene.legends;

import cn.nubia.gamelab.IToyService;
import com.zte.aivibrate.scene.GameLabBaseScene;
import com.zte.aivibrate.scene.I4DVibrateScene;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class LegendsGameLabScene extends GameLabBaseScene {

    /* renamed from: m, reason: collision with root package name */
    public boolean f16260m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f16261n;

    /* renamed from: o, reason: collision with root package name */
    private long f16262o;

    /* renamed from: p, reason: collision with root package name */
    private long f16263p;

    /* renamed from: q, reason: collision with root package name */
    private int f16264q;

    /* renamed from: r, reason: collision with root package name */
    private int f16265r;

    public LegendsGameLabScene(IToyService iToyService, I4DVibrateScene i4DVibrateScene, String str) {
        super(iToyService, i4DVibrateScene, str);
        this.f16260m = false;
        this.f16261n = false;
        this.f16262o = 0L;
        this.f16263p = 0L;
        this.f16264q = 0;
        this.f16265r = 0;
    }

    private void m() {
        this.f16263p = 0L;
        this.f16264q = 0;
        this.f16265r = 0;
        this.f16261n = false;
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void b(PrintWriter printWriter) {
        super.b(printWriter);
        printWriter.println(".LegendsGameScene");
        printWriter.println("  is game end:" + this.f16260m);
        printWriter.println("  init score:" + this.f16261n);
        printWriter.println("  game start time:" + this.f16262o);
        printWriter.println("  last kill time:" + this.f16263p);
        printWriter.println("  current kill num:" + this.f16264q);
        printWriter.println("  current dead num:" + this.f16265r);
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void i() {
        super.i();
        k(1126449662787659L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x010c, code lost:
    
        if ((r7 - r0) < 11000) goto L34;
     */
    @Override // com.zte.aivibrate.scene.GameLabBaseScene
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void l(android.os.Bundle r10) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.aivibrate.scene.legends.LegendsGameLabScene.l(android.os.Bundle):void");
    }
}
