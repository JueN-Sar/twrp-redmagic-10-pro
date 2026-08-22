package com.zte.aivibrate.scene.lol;

import cn.nubia.gamelab.IToyService;
import com.zte.aivibrate.scene.GameLabBaseScene;
import com.zte.aivibrate.scene.I4DVibrateScene;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class LolGameLabScene extends GameLabBaseScene {

    /* renamed from: m, reason: collision with root package name */
    public boolean f16267m;

    /* renamed from: n, reason: collision with root package name */
    public boolean f16268n;

    /* renamed from: o, reason: collision with root package name */
    private long f16269o;

    /* renamed from: p, reason: collision with root package name */
    private long f16270p;

    /* renamed from: q, reason: collision with root package name */
    private int f16271q;

    /* renamed from: r, reason: collision with root package name */
    private int f16272r;

    public LolGameLabScene(IToyService iToyService, I4DVibrateScene i4DVibrateScene) {
        super(iToyService, i4DVibrateScene, "com.tencent.lolm");
        this.f16267m = false;
        this.f16268n = false;
        this.f16269o = 0L;
        this.f16270p = 0L;
        this.f16271q = 0;
        this.f16272r = 0;
    }

    private void m() {
        this.f16270p = 0L;
        this.f16271q = 0;
        this.f16272r = 0;
        this.f16268n = false;
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void b(PrintWriter printWriter) {
        super.b(printWriter);
        printWriter.println(".LolScene");
        printWriter.println("  is game end:" + this.f16267m);
        printWriter.println("  init score:" + this.f16268n);
        printWriter.println("  game start time:" + this.f16269o);
        printWriter.println("  last kill time:" + this.f16270p);
        printWriter.println("  current kill num:" + this.f16271q);
        printWriter.println("  current dead num:" + this.f16272r);
    }

    @Override // com.zte.aivibrate.scene.GameLabBaseScene, com.zte.aivibrate.scene.BaseScene
    public void i() {
        super.i();
        k(9345848836171L);
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x010e, code lost:
    
        if ((r7 - r0) < 11000) goto L34;
     */
    @Override // com.zte.aivibrate.scene.GameLabBaseScene
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void l(android.os.Bundle r10) {
        /*
            Method dump skipped, instructions count: 386
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.zte.aivibrate.scene.lol.LolGameLabScene.l(android.os.Bundle):void");
    }
}
