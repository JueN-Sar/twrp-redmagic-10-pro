package com.zte.gameassist.lowsugar.detect.scene.PubgGlobal;

import android.content.Context;
import android.view.MotionEvent;
import com.zte.gameassist.lowsugar.detect.ISceneDetect;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class PubgGlobalDetect implements ISceneDetect {

    /* renamed from: d, reason: collision with root package name */
    private static int f16867d = 3;

    /* renamed from: a, reason: collision with root package name */
    private final Context f16868a;

    /* renamed from: b, reason: collision with root package name */
    private PubgGlobal f16869b;

    /* renamed from: c, reason: collision with root package name */
    private int f16870c;

    public PubgGlobalDetect(Context context) {
        this.f16868a = context;
        this.f16869b = new PubgGlobal(context);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean b(int i2, MotionEvent motionEvent) {
        return this.f16869b.b(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public void c() {
        this.f16870c = 0;
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean d(int i2, MotionEvent motionEvent) {
        return this.f16869b.h(i2, motionEvent);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public int e(List list, Map map) {
        return this.f16869b.g(list, map);
    }

    @Override // com.zte.gameassist.lowsugar.detect.ISceneDetect
    public boolean f() {
        int i2 = this.f16870c;
        if (i2 < f16867d) {
            this.f16870c = i2 + 1;
            return false;
        }
        this.f16870c = 0;
        return true;
    }
}
